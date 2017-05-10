%{
// * Java declarations
//   This code is copied in the beginning of the generated Java file
import scanner.Scanner;
import ast.expression.*;
import ast.program.*;
import ast.statement.*;
import ast.statement.switchCase.*;
import ast.type.*;
import ast.ASTNode;
import java.util.*;
%}

// * Yacc declarations
//   Token definition
%token INT_CONSTANT CHAR_CONSTANT REAL_CONSTANT
%token LEQ GEQ DOUBLEEQUALS DIFFERENT SHIFTLEFT SHIFTRIGHT
%token AND OR
%token ID
%token WRITE READ
%token VOID MAIN
%token WRITE READ
%token CHAR DOUBLE INT STRUCT
%token IF ELSE WHILE RETURN FOR DO SWITCH CASE DEFAULT
%token INC DEC
%token DIVIDEEQUALS TIMESEQUALS MINUSEQUALS PLUSEQUALS
%token BREAK


//Lower precedence go first
%nonassoc IFX
%nonassoc ELSE
%right '=' DIVIDEEQUALS TIMESEQUALS MINUSEQUALS PLUSEQUALS
%left INC DEC
%right '?' ':'
%left AND OR '!'
%left '>' GEQ '<' LEQ DIFFERENT DOUBLEEQUALS
%left '+' '-'
%left '*' '/' '%'
%right 'unary_minus'
%left CAST
%nonassoc '[' ']'
%left '.'
%nonassoc '(' ')'


%%
// * Actions

final: program										{ this.ast = new Program(scanner.getLine(), scanner.getColumn()
															, (List<Definition>)$1); }
	   
program: program_body main							{ List<Definition> defList = (List<Definition>)$1;
														defList.add((FunctionDefinition) $2);
	   													$$ = defList; }
	   ;
	   
program_body: program_body variable					{ List<Definition> defList = (List<Definition>) $1;
														for(Definition def : (List<Definition>)$2){
															defList.add(def);
														}
														$$ = defList; }
			| program_body function					{ List<Definition> defList = (List<Definition>) $1;
														defList.add((Definition)$2);
														$$ = defList; }
			| /*EMPTY*/								{ $$ = new ArrayList<Definition>(); } 

function: typed_function							{ $$ = (FunctionDefinition) $1; }
		| void_function								{ $$ = (FunctionDefinition) $1; }
		;
		
function_invocation: ID '(' listOfExpressions ')'			{ $$ = new Invocation(scanner.getLine(), scanner.getColumn()
																	,(List<Expression>) $3 , new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) $1)); }
				   | ID '(' ')'								{ $$ = new Invocation(scanner.getLine(), scanner.getColumn()
																	, new ArrayList<Expression>(), new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) $1)); }
				   ;


typed_function: built_in_type ID '(' listOfParameters ')' '{' function_body '}'		{ $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) $2,
																			  		  (List<VariableDefinition>) $4, (Type) $1)
			 																  		  , (List<Statement>) $7); }
			  | built_in_type ID '(' ')' '{' function_body '}'						{ $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) $2,
																			  		  new ArrayList<VariableDefinition>(), (Type) $1)
			 																  		  , (List<Statement>) $6); }
			  ;



void_function: VOID ID '(' listOfParameters ')' '{' function_body '}'		{ $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) $2,
																			  (List<VariableDefinition>) $4, VoidType.getInstance())
			 																  , (List<Statement>) $7); }
			 | VOID ID '(' ')' '{' function_body '}'						{ $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) $2,
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) $6); }
			 ;
		
main: VOID MAIN '(' ')' '{' function_body '}'								{ $$ = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) $2,
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) $6); }
	;
		
function_body: listOfVariables listOfStatements					{ List<Statement> body = new ArrayList<>();
																  	for(VariableDefinition varDef : (List<VariableDefinition>) $1){
																  		body.add(varDef);
																  }
																  for(Statement stm : (List<Statement>) $2){
																  	body.add(stm);
																  }
																  $$ = body; }
			 ; 
			 
listOfStatements: listOfStatements statement					{ List<Statement> list = (List<Statement>) $1;
																  list.add((Statement) $2);
																  $$ = list; }
				| /*EMPTY*/										{ $$ = new ArrayList<>(); }
				;

listOfParameters: listOfParameters ',' parameter				{ List<VariableDefinition> list = (List<VariableDefinition>) $1;
																  	list.add((VariableDefinition) $3);
																  $$ = list; } 
				| parameter										{ List<VariableDefinition> list = new ArrayList<>();
																  list.add((VariableDefinition) $1);
																  $$ = list; }
				;
				
parameter: built_in_type ID								{ $$ = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) $2, (Type) $1); }
		 | struct ID									{ $$ = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) $2, (Type) $1); }
		 | array_creation ID							{ $$ = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) $2, (Type) $1); }
		 ;

statement: expression ';'								{ $$ = (Expression) $1; }
		 | read ';'										{ $$ = (Read) $1; }
		 | write ';'									{ $$ = (Write) $1; }
		 | if_else										{ $$ = (IfStatement) $1; }
		 | while										{ $$ = (WhileStatement) $1; }
		 | for											{ $$ = (ForStatement) $1; }
		 | switch										{ $$ = (SwitchCase) $1; }
		 | do_while										{ $$ = (DoWhileStatement) $1; }
		 | return ';'									{ $$ = (Return) $1; }
		 ;

expression: expression '+' expression					{ $$ = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"+",(Expression) $3); }
          | expression '*' expression					{ $$ = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"*",(Expression) $3); }
          | expression '/' expression					{ $$ = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"/",(Expression) $3); }
          | expression '-' expression					{ $$ = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"-",(Expression) $3); }
          | expression '%' expression					{ $$ = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"%",(Expression) $3); }
          | assignment									{ $$ = (Assignment)$1; }
          
          | ternary 									{ $$ = (Ternary) $1; }
          
          | expression DOUBLEEQUALS expression			{ $$ = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"==",(Expression) $3); }
		  | expression '>' expression					{ $$ = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,">",(Expression) $3); }
		  | expression GEQ expression					{ $$ = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,">=",(Expression) $3); }
		  | expression '<' expression					{ $$ = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"<",(Expression) $3); }
		  | expression LEQ expression					{ $$ = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"<=",(Expression) $3); }
		  | expression DIFFERENT expression				{ $$ = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"!=",(Expression) $3); }
          | expression AND expression					{ $$ = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"&&",(Expression) $3); }
          | expression OR expression					{ $$ = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1,"||",(Expression) $3); }															
		  | '-' expression %prec 'unary_minus'			{ $$ = new UnaryMinus(scanner.getLine(), scanner.getColumn()
          																,(Expression) $2); }
		  | '!' expression								{ $$ = new UnaryNot(scanner.getLine(), scanner.getColumn()
          																,(Expression) $2); }
          | function_invocation							{ $$ = (Invocation) $1; }
          | '(' expression ')'							{ $$ = (Expression) $2; }
          | expression '.' expression					{ $$ = new FieldAccess(scanner.getLine(), scanner.getColumn()
          																, (Expression) $1, ((Variable) $3).getName()); }
          | cast										{ $$ = (Cast) $1; }
          | array										{ $$ = (Indexing) $1; }
          | INT_CONSTANT								{ $$ = new IntLiteral(scanner.getLine(), scanner.getColumn()
          																, (Integer) $1); }
          | REAL_CONSTANT								{ $$ = new RealLiteral(scanner.getLine(), scanner.getColumn()
          																, (Double) $1); }
          | CHAR_CONSTANT								{ $$ = new CharLiteral(scanner.getLine(), scanner.getColumn()
          																, (Character) $1); }
          | ID											{ $$ = new Variable(scanner.getLine(), scanner.getColumn()
          																, (String) $1); }
          ;
          
if_else: IF '(' expression ')' body ELSE body				{ $$ = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) $5, (List<Statement>) $7
																		, (Expression) $3); }
	   | IF '(' expression ')' body	%prec IFX				{ $$ = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) $5, new ArrayList<Statement>()
																		, (Expression) $3); } 
	   ;
	   
ternary: expression '?' expression ':' expression			{ $$ = new Ternary(scanner.getLine(), scanner.getColumn()
																		,(Expression) $1, (Expression) $3, (Expression) $5); }
	   
body : '{'listOfStatements'}'								{ $$ = (List<Statement>)$2; }
		| statement											{ List<Statement> list = new ArrayList<Statement>();
															  list.add((Statement)$1);
															  $$ = list; }
		;
	   
while: WHILE '(' expression ')' body	%prec IFX			{ List<Statement> body = new ArrayList<Statement>((List<Statement>)$5);
																	  $$ = new WhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) $3); }
	 ;
	 
do_while: DO body %prec IFX WHILE '(' expression ')'			{ List<Statement> body = new ArrayList<Statement>((List<Statement>)$2);
																	  $$ = new DoWhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) $5); }
	 ;

for: FOR '(' statement expression ';' assignment ')' body %prec IFX { List<Statement> body = new ArrayList<Statement>((List<Statement>)$8);
																			  $$ = new ForStatement(scanner.getLine(), scanner.getColumn()
																			       , (Statement) $3, (Expression) $4, (Assignment) $6, body); }
   ;
   
switch: SWITCH '(' expression ')' '{' switch_body '}'				{ List<Case> cases = (List<Case>)$6;
																	  $$ = new SwitchCase(scanner.getLine(), scanner.getColumn()
																	  		, (Expression)$3, cases); }
	  ;
		   ;
																  		
switch_body : switch_body case										{ List<Case> cases = (List<Case>)$1;
																	  cases.add((Case)$2);
																	  $$ = cases; }
			| case													{ List<Case> cases = new ArrayList<>();
																	  cases.add((Case)$1);
																	  $$ = cases; }
			;
			
case: CASE expression ':' listOfStatements break					{ List<Statement> stms = (List<Statement>)$4;
																	  stms.add((BreakInstruction) $5);
																	  $$ = new NormalCase(scanner.getLine(), scanner.getColumn()
																			, (Expression)$2, stms); }
	| CASE expression ':' listOfStatements							{ $$ = new NormalCase(scanner.getLine(), scanner.getColumn()
																			, (Expression)$2, (List<Statement>)$4); }
	| DEFAULT ':' listOfStatements break							{ List<Statement> stms = (List<Statement>)$3;
																	  stms.add((BreakInstruction) $4);
																	  $$ = new DefaultCase(scanner.getLine(), scanner.getColumn()
																			, stms); }
	| DEFAULT ':' listOfStatements									{ $$ = new DefaultCase(scanner.getLine(), scanner.getColumn()
																			, (List<Statement>)$3); }
    ;
    
break: BREAK ';'													{ $$ = new BreakInstruction(scanner.getLine(), scanner.getColumn()); }
	 ;
          
cast: '(' built_in_type ')' expression	%prec CAST					{ $$ = new Cast(scanner.getLine(), scanner.getColumn()
          																	,(Type) $2, (Expression) $4); }
	;
          
listOfExpressions: listOfExpressions ',' expression 				{ List<Expression> list = (List<Expression>) $1;
																	  list.add((Expression) $3);
																	  $$ = list; }
				 | expression										{ List<Expression> list = new ArrayList<>();
				 													  list.add((Expression)$1);
				 													  $$ = list; }
				 ;

listOfIds: listOfIds ',' ID											{ List<String> list = (List<String>) $1;
																	  if(list.contains((String)$3))
																	  	new ErrorType(scanner.getLine(), scanner.getColumn()
																	  			, "The variable "+((String)$3)
																	  			+" has been already declared");
																	  list.add((String)$3);
																	  $$ = list; }
		 | ID														{ List<String> list = new ArrayList<String>();
		 															  list.add((String) $1);
		 															  $$ = list; }
		 ;

built_in_type: CHAR											{ $$ = CharType.getInstance(); }
			 | INT											{ $$ = IntType.getInstance(); }
			 | DOUBLE										{ $$ = RealType.getInstance(); }
			 ;
			 		  
array: expression '[' expression ']'						{$$ = new Indexing(scanner.getLine()
																, scanner.getColumn(), (Expression)$1
																,(Expression)$3);}
	 ;
	 
array_creation: array_creation '[' INT_CONSTANT ']'			{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) $1, (Integer) $3);
															  $$ = array; }
	 		  | built_in_type '[' INT_CONSTANT ']'			{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) $1, (Integer) $3);
															  $$ = array; }
			  | struct '[' INT_CONSTANT ']'					{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) $1, (Integer) $3);
															  $$ = array; }
	 		  ;

variable: built_in_type listOfIds ';'					{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)$2)
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)$1));
															$$ = variables; }
		| array_creation listOfIds ';'					{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)$2) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)$1));
															$$ = variables; }
		| struct listOfIds ';'							{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)$2) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)$1));
															$$ = variables; }
		;
		
listOfVariables: listOfVariables variable				{ List<VariableDefinition> list = (List<VariableDefinition>) $1;
															for(VariableDefinition varDef : (List<VariableDefinition>) $2){
																if(list.contains(varDef))
																	new ErrorType(scanner.getLine(), scanner.getColumn()
																			, "The variable "+varDef.getName()
																			+" has been already defined");
															  	list.add(varDef);
															}
															$$ = list; }
			   | /*EMPTY*/								{ $$ = new ArrayList<VariableDefinition>(); }
			   ;
	
assignment: expression '=' expression						{ $$ = new Assignment(scanner.getLine(), scanner.getColumn()
																		, (Expression) $1, (Expression) $3); }
		  | expression INC									{ $$ = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1, new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)$1,"+"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
          | expression DEC									{ $$ = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1, new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)$1,"-"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
          | expression PLUSEQUALS expression				{ $$ = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1, new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)$1,"+",(Expression)$3)); }
          | expression MINUSEQUALS expression				{ $$ = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1, new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)$1,"-",(Expression)$3)); }
          | expression TIMESEQUALS expression				{ $$ = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1, new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)$1,"*",(Expression)$3)); }
          | expression DIVIDEEQUALS expression				{ $$ = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) $1, new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)$1,"/",(Expression)$3)); }
		  ;
		  
read: READ listOfExpressions					{ $$ = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) $2); }
	;

write: WRITE listOfExpressions					{ $$ = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) $2); }
	 ;
	 
struct: STRUCT '{' listOfVariables '}'			{ List<RecordField> list = new ArrayList<>();
													RecordField rec = null;
													for(VariableDefinition varDef : (List<VariableDefinition>) $3){
													  	rec = new RecordField(scanner.getLine(), scanner.getColumn()
													  		, varDef.getType(), varDef.getName());
													  	list.add(rec);
													}
													$$ = new Struct(scanner.getLine(), scanner.getColumn()
														  , list); }
	  ;
	  
return: RETURN expression						{ $$ = new Return(scanner.getLine(), scanner.getColumn()
														, (Expression) $2); }
	  ;

%%

// * Code
//   Members of the generated Parser class
// We must implement at least:
// - int yylex()
// - void yyerror(String)

// * Lexical analyzer

private Scanner scanner;
private ASTNode ast;

public ASTNode getAST(){
	return this.ast;
}

// * Invocation to the scanner
private int yylex () {
    int token=0;
    try { 
		token=scanner.yylex();
		this.yylval = scanner.getYylval();
    } catch(Throwable e) {
	    System.err.println ("Lexical error in line " + scanner.getLine()+
		" and column "+scanner.getColumn()+":\n\t"+e); 
    }
    return token;
}

// * Syntax error handler
public void yyerror (String error) {
    System.err.println ("Syntax error in line " + scanner.getLine()+
		" and column "+scanner.getColumn()+":\n\t"+error);
}

// * Constructor
public Parser(Scanner scanner) {
	this.scanner= scanner;
	//this.yydebug = true;
}
