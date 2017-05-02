// ************  User Code  ********************

package scanner;
import parser.Parser;

%%
// ************  Options ********************

// % debug // * For debugging purposes
%byaccj
%class Scanner
%public
%unicode
%line
%column

%{
// ************  Fields and methods ********************

// * To get the line number
public int getLine() { 
	// * JFlex starts in zero
	return this.yyline+1;
}

// * To get the column number
public int getColumn() { 
	// * JFlex starts in zero
	return yycolumn+1;
}

// * Semantic value of the token
private Object yylval;
public Object getYylval() {
	return this.yylval;
}


%}

// ************  Macros ********************
Digit = [0-9]+
Letter = [a-zA-Z]
Spaces = [ \r\t\n]+
Comment = "//".*
MultiComment = "/*"~"*/"
Char = '.'
CharAscii = '\\[0-9]+'
Real = {Digit}*"."{Digit}+|{Digit}+"."{Digit}*
Identifier = {Letter}({Letter}|{Digit})*
Mantissa = ({Real}|{Digit})[Ee](-|\+)?{Digit}

%%
// ************  Lexical Rules ********************

// * Trash
{Spaces}			{ }

{Comment}			{ }

{MultiComment}		{ }

// * KeyWords
int					{ yylval = "int";
					  return Parser.INT; }
double				{ yylval = "double";
					  return Parser.DOUBLE; }
char				{ yylval = "char";
					  return Parser.CHAR; }
main				{ yylval = "main";
					  return Parser.MAIN; }
void				{ yylval = "void";
					  return Parser.VOID; }
return				{ yylval = "return";
					  return Parser.RETURN; }
struct				{ yylval = "struct";
					  return Parser.STRUCT; }
while				{ yylval = "while";
					  return Parser.WHILE; }
if					{ yylval = "if";
					  return Parser.IF; }
else				{ yylval = "else";
					  return Parser.ELSE; }
and					{ yylval = "and";
					  return Parser.AND; }
or					{ yylval = "or";
					  return Parser.OR; }
read				{ yylval = "read";
					  return Parser.READ; }
write				{ yylval = "write";
					  return Parser.WRITE; }
for					{ yylval = "for";
					  return Parser.FOR; }
do					{ yylval = "do";
					  return Parser.DO; }

// * Constants

{Digit}				{ this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;  }

'\\n'				{ this.yylval = '\n';
					  return Parser.CHAR_CONSTANT;	}

'\\t'				{ this.yylval = '\t';
					  return Parser.CHAR_CONSTANT;	}

'\\r'				{ this.yylval = '\r';
					  return Parser.CHAR_CONSTANT;	}

{Char}				{ this.yylval = yytext().charAt(1);
					  return Parser.CHAR_CONSTANT;	}
					  
{CharAscii}			{ this.yylval = (char)Integer.parseInt(yytext().split("'")[1].substring(1));
					  return Parser.CHAR_CONSTANT;	}
					  
{Real}				{ this.yylval = new Double(yytext());
					  return Parser.REAL_CONSTANT; }
					  
{Mantissa}			{ this.yylval = new Double(yytext());
					  return Parser.REAL_CONSTANT; }

{Identifier}		{ this.yylval = yytext();
					  return Parser.ID;	}


// * Operators of length 1

("+"|"-"|"/"|"*"|"%"|">"|"<"|"="|"("|")"|"!"|"["|"]"|","|";"|"{"|"}"|".")		{ this.yylval = yytext();
															  		  		  return (int) yytext().charAt(0); }

// * Operators of length 2

"=="				{ this.yylval = yytext();
					  return Parser.DOUBLEEQUALS; }
"<="				{ this.yylval = yytext();
					  return Parser.LEQ; }
">="				{ this.yylval = yytext();
					  return Parser.GEQ; }
"!="				{ this.yylval = yytext();
					  return Parser.DIFFERENT; }
"&&"				{ this.yylval = yytext();
					  return Parser.AND; }
"||"				{ this.yylval = yytext();
					  return Parser.OR; }
"++"				{ this.yylval = yytext();
					  return Parser.INC; }
"--"				{ this.yylval = yytext();
					  return Parser.DEC; }
"+="				{ this.yylval = yytext();
					  return Parser.PLUSEQUALS; }
"-="				{ this.yylval = yytext();
					  return Parser.MINUSEQUALS; }
"*="				{ this.yylval = yytext();
					  return Parser.TIMESEQUALS; }
"/="				{ this.yylval = yytext();
					  return Parser.DIVIDEEQUALS; }
"**"|"^"			{ this.yylval = yytext();
					  return Parser.POTENTIAL; }
">>"				{ this.yylval = yytext();
					  return Parser.SHIFTRIGHT; }
"<<"				{ this.yylval = yytext();
					  return Parser.SHIFTLEFT; }
					  
// * Dot
					  
.					{ System.err.println("Lexical error with element "+yytext()+" in line "+getLine()+" and column "+getColumn()); }
					  

