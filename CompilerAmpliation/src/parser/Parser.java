//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";



package parser;



//#line 2 "../../src/parser/parser.y"
/* * Java declarations*/
/*   This code is copied in the beginning of the generated Java file*/
import scanner.Scanner;
import ast.expression.*;
import ast.program.*;
import ast.statement.*;
import ast.type.*;
import ast.ASTNode;
import java.util.*;
//#line 27 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:Object
String   yytext;//user variable to return contextual strings
Object yyval; //used to return semantic vals from action routines
Object yylval;//the 'lval' (result) I got from yylex()
Object valstk[] = new Object[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new Object();
  yylval=new Object();
  valptr=-1;
}
final void val_push(Object val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    Object[] newstack = new Object[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final Object val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final Object val_peek(int relative)
{
  return valstk[valptr-relative];
}
final Object dup_yyval(Object val)
{
  return val;
}
//#### end semantic value section ####
public final static short INT_CONSTANT=257;
public final static short CHAR_CONSTANT=258;
public final static short REAL_CONSTANT=259;
public final static short LEQ=260;
public final static short GEQ=261;
public final static short DOUBLEEQUALS=262;
public final static short DIFFERENT=263;
public final static short AND=264;
public final static short OR=265;
public final static short ID=266;
public final static short WRITE=267;
public final static short READ=268;
public final static short VOID=269;
public final static short MAIN=270;
public final static short CHAR=271;
public final static short DOUBLE=272;
public final static short INT=273;
public final static short STRUCT=274;
public final static short IF=275;
public final static short ELSE=276;
public final static short WHILE=277;
public final static short RETURN=278;
public final static short INC=279;
public final static short DEC=280;
public final static short DIVIDEEQUALS=281;
public final static short TIMESEQUALS=282;
public final static short MINUSEQUALS=283;
public final static short PLUSEQUALS=284;
public final static short IFX=285;
public final static short unary_minus=286;
public final static short CAST=287;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    2,    5,    5,    8,    8,    6,
    6,    7,    7,    3,   12,   14,   14,   11,   11,   16,
   15,   15,   15,   15,   15,   15,   15,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   21,   21,   26,
   26,   22,   24,    9,    9,   27,   27,   10,   10,   10,
   25,   28,   28,    4,    4,    4,   13,   13,   18,   19,
   20,   29,   23,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    1,    1,    4,    3,    8,
    7,    8,    7,    7,    2,    2,    0,    3,    1,    2,
    2,    2,    2,    2,    1,    1,    2,    3,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    2,    2,    3,    3,    3,    3,    2,    2,    1,    3,
    3,    1,    1,    1,    1,    1,    1,    7,    5,    3,
    1,    5,    4,    3,    1,    3,    1,    1,    1,    1,
    4,    4,    4,    3,    3,    3,    2,    0,    3,    2,
    2,    4,    2,
};
final static short yydefred[] = {                         5,
    0,    1,    0,    0,   68,   70,   69,    0,    2,    3,
    4,    6,    7,    0,    0,    0,    0,    0,   78,    0,
    0,    0,   67,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   74,    0,   75,   76,    0,    0,    0,   19,
    0,   82,   77,    0,    0,    0,   73,   66,   72,   78,
   20,    0,    0,   78,   78,    0,    0,    0,   78,   18,
    0,    0,   78,   13,    0,    0,   14,   11,    0,   54,
   56,   55,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   49,   16,    0,    0,    0,    0,   25,   26,    0,
   52,   53,   12,   10,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   41,   42,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   21,   22,   23,
   24,   27,    9,    0,    0,    0,    0,    0,   50,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   51,    8,
    0,    0,    0,    0,   71,   17,   61,    0,   62,    0,
    0,   60,   58,
};
final static short yydgoto[] = {                          1,
    2,    3,    9,   43,   11,   12,   13,   82,   96,   38,
   39,   57,   58,   65,  167,   40,   84,   85,   86,   87,
   88,   89,   90,   91,   92,  168,   22,   15,   16,
};
final static short yysindex[] = {                         0,
    0,    0,  354, -239,    0,    0,    0,  -95,    0,    0,
    0,    0,    0,  -90,  -89, -236,   38,   48,    0,   59,
 -157,  -30,    0, -146,   -4,    6,  -21,   74,  107,    4,
   24, -145,    0,   31,    0,    0,    5, -124,   49,    0,
   25,    0,    0,  -65,   33,   54,    0,    0,    0,    0,
    0,   36, -140,    0,    0,   43,   32, -188,    0,    0,
   42,   58,    0,    0,  260,   66,    0,    0,   68,    0,
    0,    0,  110,  496,  496,  145,  152,  496,  496,  496,
  604,    0,    0,    1,  109,  143,  148,    0,    0,  149,
    0,    0,    0,    0,  328,  166,  169,  166,  496,  496,
  169,  668,   -9,  176,   27,  496,  496,  496,  496,  496,
  496,    0,    0,  496,  496,  496,  496,  496,  496,  496,
  496,  496,  496,  496,  496,  496,  496,    0,    0,    0,
    0,    0,    0,   75,  496,   34,   60,  496,    0,  380,
  380,  380,  380,  668,  668,  169,  169,  169,  169,  169,
  380,  380,  670,  670,   -9,   -9,   -9,   67,    0,    0,
  169,  293,  293,  -33,    0,    0,    0,  -57,    0,  234,
  293,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    7,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  363,    0,    0,
    0,    0,    0,    0,   97,    0,    0,    0,    0,    0,
    0,    0,  -37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  174,   82,  181,    0,    0,
  182,  455,   93,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  462,
  503,  513,  539,  607,  688,   -2,  401,  647,  747,  189,
  549,  574,  414,  420,  102,  128,  137,    0,    0,    0,
  159,    0,    0,  -26,    0,    0,    0,  395,    0,    0,
    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  256,    0,    0,    0,    0,  -63,  191,
  238,  150,  250,  112,  -62,  220,  880,    0,    0,    0,
    0,    0,    0,    0,    0, -122,   37,    0,    0,
};
final static int YYTABLESIZE=1018;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         57,
   21,   24,   83,   57,   57,   57,   57,   57,   57,   57,
   63,   98,  127,   32,   63,   63,   63,   63,   63,   37,
   63,   57,   57,   57,   57,   21,   17,   19,   33,   23,
   18,  134,   63,   63,   63,   63,  127,  125,   46,   32,
  169,   46,  123,  121,   45,  122,  127,  124,  173,   32,
   67,   25,   26,   57,   35,   57,   46,  126,   46,  128,
  120,  118,  119,  125,   36,   67,   63,  139,  123,  121,
  125,  122,  127,  124,  162,  123,  121,   27,  122,  127,
  124,  126,    5,    6,    7,    8,  120,   28,  119,   52,
   46,  126,   53,  120,   56,  119,  125,   53,   30,   31,
  163,  123,  121,  125,  122,  127,  124,   83,  123,  121,
   34,  122,  127,  124,   41,  160,   47,  126,  135,  120,
   48,  119,   65,   49,  126,   65,  120,   50,  119,   47,
    5,    6,    7,   47,   47,   47,   47,   47,   29,   47,
   65,   51,   29,   29,   29,   29,   29,   54,   29,   95,
  126,   47,   47,   47,   47,   55,   64,  126,   59,  165,
   29,   29,   29,   29,   30,   63,   67,  129,   30,   30,
   30,   30,   30,   32,   30,   20,   23,   32,   32,   32,
   32,   32,   68,   32,   99,   47,   30,   30,   30,   30,
   93,  100,   94,   14,   29,   32,   32,   32,   32,   64,
   23,  130,   64,   61,   62,  125,  131,  132,   66,  135,
  123,  121,   69,  122,  127,  124,  138,   64,  171,   44,
   30,   15,   57,   57,   57,   57,   57,   57,  120,   32,
  119,   42,   81,   63,   63,   63,   63,   63,   63,   80,
   83,   57,   57,   57,   57,   57,   57,   79,   44,    5,
    6,    7,   63,   63,   63,   63,   63,   63,   10,  126,
  106,  107,  108,  109,  110,  111,   79,   46,   29,  112,
  113,  104,   60,   81,    5,    6,    7,  170,   80,  112,
  113,  114,  115,  116,  117,    0,  106,  107,  108,  109,
  110,  111,   79,  106,  107,  108,  109,  110,  111,   81,
    0,    0,    0,    0,   80,  112,  113,  114,  115,  116,
  117,    0,  112,  113,  114,  115,  116,  117,    0,  106,
  107,  108,  109,  110,  111,   79,  106,  107,  108,  109,
  110,  111,   81,    0,    0,    0,    0,   80,  112,  113,
  114,  115,  116,  117,    0,  112,  113,  114,  115,  116,
  117,    0,   47,   47,   47,   47,   47,   47,  172,    0,
   79,   29,   29,   29,   29,   29,   29,   81,  133,    0,
    0,    0,   80,   47,   47,   47,   47,    5,    6,    7,
    8,    0,   29,   29,   29,   29,    0,   30,   30,   30,
   30,   30,   30,    0,    0,   17,   32,   32,   32,   32,
   32,   32,   17,    0,    0,    0,    0,   17,   30,   30,
   30,   30,    0,    0,    0,  166,  125,   32,   32,   32,
   32,  123,  121,    0,  122,  127,  124,   59,  106,  107,
  108,  109,  110,  111,   59,    0,    0,    0,    0,   59,
    0,   45,    0,    0,   45,    0,    0,  112,  113,  114,
  115,  116,  117,    0,   28,    0,   28,   28,   28,   45,
   31,   45,   31,   31,   31,    0,    0,    0,    0,    0,
  126,    0,   28,   28,   28,   28,    0,    0,   31,   31,
   31,   31,    0,    0,    0,    0,    0,   17,    0,    0,
   70,   71,   72,   45,    0,   48,    0,    0,   48,   73,
   74,   75,   37,    0,    0,   37,   28,    0,   76,    0,
   77,   78,   31,   48,    0,   48,   70,   71,   72,   59,
   37,   37,   37,   37,    0,   73,   74,   75,   79,    0,
    0,    0,    0,    0,   76,   81,   77,   78,    0,    0,
   80,    0,    0,   35,    0,    0,   35,   48,    0,   70,
   71,   72,    0,   33,   37,    0,   33,    0,   73,   74,
   75,   35,   35,   35,   35,    0,    0,   76,    0,   77,
   78,   33,   33,   33,   33,    0,    0,    0,    0,   38,
    0,    0,   38,    0,   70,   71,   72,    0,    0,   34,
    0,    0,   34,   73,    0,   35,    0,   38,   38,   38,
   38,    0,    0,    0,    0,   33,    0,   34,   34,   34,
   34,    0,    0,    0,   36,    0,    0,   36,    0,   17,
   17,   17,    4,    0,    5,    6,    7,    8,   17,   17,
   17,   38,   36,   36,   36,   36,   79,   17,    0,   17,
   17,   34,    0,   81,    0,    0,    0,   39,   80,    0,
   39,   59,   59,   59,    0,    0,    0,    0,  112,  113,
   59,   59,   59,    0,    0,   39,   36,   39,    0,   59,
    0,   59,   59,   28,   28,   28,   28,   28,   28,   31,
   31,   31,   31,   31,   31,    0,    0,   44,    0,    0,
   44,    0,    0,    0,   28,   28,   28,   28,    0,   39,
   31,   31,   31,   31,  125,   44,  125,   44,    0,  123,
  121,  123,  122,  127,  124,  127,  124,    0,   48,   48,
    0,   37,   37,   37,   37,   37,   37,  120,   40,  119,
    0,   40,    0,    0,    0,   48,   48,   48,   48,   44,
    0,    0,   37,   37,   37,   37,   40,    0,   40,    0,
    0,    0,   70,   71,   72,    0,    0,    0,  126,    0,
  126,   73,   35,   35,   35,   35,   35,   35,    0,    0,
    0,    0,   33,   33,   33,   33,   33,   33,    0,    0,
   40,    0,    0,   35,   35,   35,   35,   43,    0,    0,
   43,    0,    0,   33,   33,   33,   33,    0,   38,   38,
   38,   38,   38,   38,    0,   43,    0,   43,   34,   34,
   34,   34,   34,   34,    0,    0,    0,    0,    0,   38,
   38,   38,   38,    0,    0,    0,    0,    0,    0,   34,
   34,   34,   34,   36,   36,   36,   36,   36,   36,   43,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   36,   36,   36,   36,    0,    0,
   70,   71,   72,    0,    0,    0,    0,    0,    0,   73,
   39,   39,    0,    0,    5,    6,    7,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   39,   39,   39,
   39,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  106,  107,  108,
  109,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  112,  113,  112,  113,
    0,   40,   40,   97,   97,    0,    0,  101,  102,  103,
  105,    0,    0,    0,    0,    0,    0,    0,   40,   40,
   40,   40,    0,    0,   97,    0,    0,    0,  136,  137,
    0,    0,    0,    0,    0,  140,  141,  142,  143,  144,
  145,    0,    0,  146,  147,  148,  149,  150,  151,  152,
  153,  154,  155,  156,  157,  158,  159,    0,    0,    0,
    0,    0,    0,    0,  161,    0,    0,  164,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   65,   41,   42,   43,   44,   45,   46,   47,
   37,   75,   46,   44,   41,   42,   43,   44,   45,   41,
   47,   59,   60,   61,   62,   91,  266,  123,   59,  266,
  270,   95,   59,   60,   61,   62,   46,   37,   41,   44,
  163,   44,   42,   43,   41,   45,   46,   47,  171,   44,
   44,   15,   16,   91,   59,   93,   59,   91,   61,   59,
   60,   61,   62,   37,   59,   59,   93,   41,   42,   43,
   37,   45,   46,   47,   41,   42,   43,   40,   45,   46,
   47,   91,  271,  272,  273,  274,   60,   40,   62,   41,
   93,   91,   44,   60,   41,   62,   37,   44,   40,  257,
   41,   42,   43,   37,   45,   46,   47,  170,   42,   43,
  257,   45,   46,   47,   41,   41,   93,   91,   44,   60,
  266,   62,   41,   93,   91,   44,   60,  123,   62,   37,
  271,  272,  273,   41,   42,   43,   44,   45,   37,   47,
   59,  266,   41,   42,   43,   44,   45,  123,   47,   40,
   91,   59,   60,   61,   62,  123,  125,   91,  123,   93,
   59,   60,   61,   62,   37,  123,  125,   59,   41,   42,
   43,   44,   45,   37,   47,  266,  266,   41,   42,   43,
   44,   45,  125,   47,   40,   93,   59,   60,   61,   62,
  125,   40,  125,    3,   93,   59,   60,   61,   62,   41,
  266,   59,   44,   54,   55,   37,   59,   59,   59,   44,
   42,   43,   63,   45,   46,   47,   41,   59,  276,   29,
   93,  125,  260,  261,  262,  263,  264,  265,   60,   93,
   62,  125,   59,  260,  261,  262,  263,  264,  265,   59,
   59,  279,  280,  281,  282,  283,  284,   59,   58,  271,
  272,  273,  279,  280,  281,  282,  283,  284,    3,   91,
  260,  261,  262,  263,  264,  265,   33,   30,   19,  279,
  280,   81,   53,   40,  271,  272,  273,  166,   45,  279,
  280,  281,  282,  283,  284,   -1,  260,  261,  262,  263,
  264,  265,   33,  260,  261,  262,  263,  264,  265,   40,
   -1,   -1,   -1,   -1,   45,  279,  280,  281,  282,  283,
  284,   -1,  279,  280,  281,  282,  283,  284,   -1,  260,
  261,  262,  263,  264,  265,   33,  260,  261,  262,  263,
  264,  265,   40,   -1,   -1,   -1,   -1,   45,  279,  280,
  281,  282,  283,  284,   -1,  279,  280,  281,  282,  283,
  284,   -1,  260,  261,  262,  263,  264,  265,  125,   -1,
   33,  260,  261,  262,  263,  264,  265,   40,   41,   -1,
   -1,   -1,   45,  281,  282,  283,  284,  271,  272,  273,
  274,   -1,  281,  282,  283,  284,   -1,  260,  261,  262,
  263,  264,  265,   -1,   -1,   33,  260,  261,  262,  263,
  264,  265,   40,   -1,   -1,   -1,   -1,   45,  281,  282,
  283,  284,   -1,   -1,   -1,  123,   37,  281,  282,  283,
  284,   42,   43,   -1,   45,   46,   47,   33,  260,  261,
  262,  263,  264,  265,   40,   -1,   -1,   -1,   -1,   45,
   -1,   41,   -1,   -1,   44,   -1,   -1,  279,  280,  281,
  282,  283,  284,   -1,   41,   -1,   43,   44,   45,   59,
   41,   61,   43,   44,   45,   -1,   -1,   -1,   -1,   -1,
   91,   -1,   59,   60,   61,   62,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,
  257,  258,  259,   93,   -1,   41,   -1,   -1,   44,  266,
  267,  268,   41,   -1,   -1,   44,   93,   -1,  275,   -1,
  277,  278,   93,   59,   -1,   61,  257,  258,  259,  125,
   59,   60,   61,   62,   -1,  266,  267,  268,   33,   -1,
   -1,   -1,   -1,   -1,  275,   40,  277,  278,   -1,   -1,
   45,   -1,   -1,   41,   -1,   -1,   44,   93,   -1,  257,
  258,  259,   -1,   41,   93,   -1,   44,   -1,  266,  267,
  268,   59,   60,   61,   62,   -1,   -1,  275,   -1,  277,
  278,   59,   60,   61,   62,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,   -1,  257,  258,  259,   -1,   -1,   41,
   -1,   -1,   44,  266,   -1,   93,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   -1,   93,   -1,   59,   60,   61,
   62,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,  257,
  258,  259,  269,   -1,  271,  272,  273,  274,  266,  267,
  268,   93,   59,   60,   61,   62,   33,  275,   -1,  277,
  278,   93,   -1,   40,   -1,   -1,   -1,   41,   45,   -1,
   44,  257,  258,  259,   -1,   -1,   -1,   -1,  279,  280,
  266,  267,  268,   -1,   -1,   59,   93,   61,   -1,  275,
   -1,  277,  278,  260,  261,  262,  263,  264,  265,  260,
  261,  262,  263,  264,  265,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   -1,  281,  282,  283,  284,   -1,   93,
  281,  282,  283,  284,   37,   59,   37,   61,   -1,   42,
   43,   42,   45,   46,   47,   46,   47,   -1,  264,  265,
   -1,  260,  261,  262,  263,  264,  265,   60,   41,   62,
   -1,   44,   -1,   -1,   -1,  281,  282,  283,  284,   93,
   -1,   -1,  281,  282,  283,  284,   59,   -1,   61,   -1,
   -1,   -1,  257,  258,  259,   -1,   -1,   -1,   91,   -1,
   91,  266,  260,  261,  262,  263,  264,  265,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,  264,  265,   -1,   -1,
   93,   -1,   -1,  281,  282,  283,  284,   41,   -1,   -1,
   44,   -1,   -1,  281,  282,  283,  284,   -1,  260,  261,
  262,  263,  264,  265,   -1,   59,   -1,   61,  260,  261,
  262,  263,  264,  265,   -1,   -1,   -1,   -1,   -1,  281,
  282,  283,  284,   -1,   -1,   -1,   -1,   -1,   -1,  281,
  282,  283,  284,  260,  261,  262,  263,  264,  265,   93,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  281,  282,  283,  284,   -1,   -1,
  257,  258,  259,   -1,   -1,   -1,   -1,   -1,   -1,  266,
  264,  265,   -1,   -1,  271,  272,  273,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  281,  282,  283,
  284,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,
  263,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  279,  280,  279,  280,
   -1,  264,  265,   74,   75,   -1,   -1,   78,   79,   80,
   81,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  281,  282,
  283,  284,   -1,   -1,   95,   -1,   -1,   -1,   99,  100,
   -1,   -1,   -1,   -1,   -1,  106,  107,  108,  109,  110,
  111,   -1,   -1,  114,  115,  116,  117,  118,  119,  120,
  121,  122,  123,  124,  125,  126,  127,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  135,   -1,   -1,  138,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=287;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"INT_CONSTANT","CHAR_CONSTANT",
"REAL_CONSTANT","LEQ","GEQ","DOUBLEEQUALS","DIFFERENT","AND","OR","ID","WRITE",
"READ","VOID","MAIN","CHAR","DOUBLE","INT","STRUCT","IF","ELSE","WHILE",
"RETURN","INC","DEC","DIVIDEEQUALS","TIMESEQUALS","MINUSEQUALS","PLUSEQUALS",
"IFX","\"unary_minus\"","CAST",
};
final static String yyrule[] = {
"$accept : final",
"final : program",
"program : program_body main",
"program_body : program_body variable",
"program_body : program_body function",
"program_body :",
"function : typed_function",
"function : void_function",
"function_invocation : ID '(' listOfExpressions ')'",
"function_invocation : ID '(' ')'",
"typed_function : built_in_type ID '(' listOfParameters ')' '{' function_body '}'",
"typed_function : built_in_type ID '(' ')' '{' function_body '}'",
"void_function : VOID ID '(' listOfParameters ')' '{' function_body '}'",
"void_function : VOID ID '(' ')' '{' function_body '}'",
"main : VOID MAIN '(' ')' '{' function_body '}'",
"function_body : listOfVariables listOfStatements",
"listOfStatements : listOfStatements statement",
"listOfStatements :",
"listOfParameters : listOfParameters ',' parameter",
"listOfParameters : parameter",
"parameter : built_in_type ID",
"statement : expression ';'",
"statement : assignment ';'",
"statement : read ';'",
"statement : write ';'",
"statement : if_else",
"statement : while",
"statement : return ';'",
"expression : expression '+' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '-' expression",
"expression : expression '%' expression",
"expression : expression DOUBLEEQUALS expression",
"expression : expression '>' expression",
"expression : expression GEQ expression",
"expression : expression '<' expression",
"expression : expression LEQ expression",
"expression : expression DIFFERENT expression",
"expression : expression AND expression",
"expression : expression OR expression",
"expression : expression INC",
"expression : expression DEC",
"expression : expression PLUSEQUALS expression",
"expression : expression MINUSEQUALS expression",
"expression : expression TIMESEQUALS expression",
"expression : expression DIVIDEEQUALS expression",
"expression : '-' expression",
"expression : '!' expression",
"expression : function_invocation",
"expression : '(' expression ')'",
"expression : expression '.' expression",
"expression : cast",
"expression : array",
"expression : INT_CONSTANT",
"expression : REAL_CONSTANT",
"expression : CHAR_CONSTANT",
"expression : ID",
"if_else : IF '(' expression ')' if_body ELSE if_body",
"if_else : IF '(' expression ')' if_body",
"if_body : '{' listOfStatements '}'",
"if_body : statement",
"while : WHILE '(' expression ')' if_body",
"cast : '(' built_in_type ')' expression",
"listOfExpressions : listOfExpressions ',' expression",
"listOfExpressions : expression",
"listOfIds : listOfIds ',' ID",
"listOfIds : ID",
"built_in_type : CHAR",
"built_in_type : INT",
"built_in_type : DOUBLE",
"array : expression '[' expression ']'",
"array_creation : array_creation '[' INT_CONSTANT ']'",
"array_creation : built_in_type '[' INT_CONSTANT ']'",
"variable : built_in_type listOfIds ';'",
"variable : array_creation listOfIds ';'",
"variable : struct listOfIds ';'",
"listOfVariables : listOfVariables variable",
"listOfVariables :",
"assignment : expression '=' expression",
"read : READ listOfExpressions",
"write : WRITE listOfExpressions",
"struct : STRUCT '{' listOfVariables '}'",
"return : RETURN expression",
};

//#line 329 "../../src/parser/parser.y"

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
//#line 590 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 48 "../../src/parser/parser.y"
{ this.ast = new Program(scanner.getLine(), scanner.getColumn()
															, (List<Definition>)val_peek(0)); }
break;
case 2:
//#line 51 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>)val_peek(1);
														defList.add((FunctionDefinition) val_peek(0));
	   													yyval = defList; }
break;
case 3:
//#line 56 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														for(Definition def : (List<Definition>)val_peek(0)){
															defList.add(def);
														}
														yyval = defList; }
break;
case 4:
//#line 61 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														defList.add((Definition)val_peek(0));
														yyval = defList; }
break;
case 5:
//#line 64 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 6:
//#line 66 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 7:
//#line 67 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 8:
//#line 70 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	,(List<Expression>) val_peek(1) , new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(3))); }
break;
case 9:
//#line 73 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	, new ArrayList<Expression>(), new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(2))); }
break;
case 10:
//#line 79 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(6),
																			  		  (List<VariableDefinition>) val_peek(4), (Type) val_peek(7))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 11:
//#line 84 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(5),
																			  		  new ArrayList<VariableDefinition>(), (Type) val_peek(6))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 12:
//#line 93 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(6),
																			  (List<VariableDefinition>) val_peek(4), new VoidType(
																			  scanner.getLine(), scanner.getColumn()))
			 																  , (List<Statement>) val_peek(1)); }
break;
case 13:
//#line 99 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), new VoidType(
																			  scanner.getLine(), scanner.getColumn()))
			 																  , (List<Statement>) val_peek(1)); }
break;
case 14:
//#line 107 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), new VoidType(
																			  scanner.getLine(), scanner.getColumn()))
			 																  , (List<Statement>) val_peek(1)); }
break;
case 15:
//#line 115 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<>();
																  	for(VariableDefinition varDef : (List<VariableDefinition>) val_peek(1)){
																  		body.add(varDef);
																  }
																  for(Statement stm : (List<Statement>) val_peek(0)){
																  	body.add(stm);
																  }
																  yyval = body; }
break;
case 16:
//#line 125 "../../src/parser/parser.y"
{ List<Statement> list = (List<Statement>) val_peek(1);
																  list.add((Statement) val_peek(0));
																  yyval = list; }
break;
case 17:
//#line 128 "../../src/parser/parser.y"
{ yyval = new ArrayList<>(); }
break;
case 18:
//#line 131 "../../src/parser/parser.y"
{ List<VariableDefinition> list = (List<VariableDefinition>) val_peek(2);
																  	list.add((VariableDefinition) val_peek(0));
																  yyval = list; }
break;
case 19:
//#line 134 "../../src/parser/parser.y"
{ List<VariableDefinition> list = new ArrayList<>();
																  list.add((VariableDefinition) val_peek(0));
																  yyval = list; }
break;
case 20:
//#line 139 "../../src/parser/parser.y"
{ yyval = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) val_peek(0), (Type) val_peek(1)); }
break;
case 21:
//#line 143 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 22:
//#line 144 "../../src/parser/parser.y"
{ yyval = (Assignment) val_peek(1); }
break;
case 23:
//#line 145 "../../src/parser/parser.y"
{ yyval = (Read) val_peek(1); }
break;
case 24:
//#line 146 "../../src/parser/parser.y"
{ yyval = (Write) val_peek(1); }
break;
case 25:
//#line 147 "../../src/parser/parser.y"
{ yyval = (IfStatement) val_peek(0); }
break;
case 26:
//#line 148 "../../src/parser/parser.y"
{ yyval = (WhileStatement) val_peek(0); }
break;
case 27:
//#line 149 "../../src/parser/parser.y"
{ yyval = (Return) val_peek(1); }
break;
case 28:
//#line 152 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"+",(Expression) val_peek(0)); }
break;
case 29:
//#line 154 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"*",(Expression) val_peek(0)); }
break;
case 30:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"/",(Expression) val_peek(0)); }
break;
case 31:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"-",(Expression) val_peek(0)); }
break;
case 32:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"%",(Expression) val_peek(0)); }
break;
case 33:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"==",(Expression) val_peek(0)); }
break;
case 34:
//#line 164 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">",(Expression) val_peek(0)); }
break;
case 35:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">=",(Expression) val_peek(0)); }
break;
case 36:
//#line 168 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<",(Expression) val_peek(0)); }
break;
case 37:
//#line 170 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<=",(Expression) val_peek(0)); }
break;
case 38:
//#line 172 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"!=",(Expression) val_peek(0)); }
break;
case 39:
//#line 174 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"&&",(Expression) val_peek(0)); }
break;
case 40:
//#line 176 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"||",(Expression) val_peek(0)); }
break;
case 41:
//#line 178 "../../src/parser/parser.y"
{ yyval = new Increment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1)); }
break;
case 42:
//#line 180 "../../src/parser/parser.y"
{ yyval = new Decrement(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1)); }
break;
case 43:
//#line 182 "../../src/parser/parser.y"
{ yyval = new ArithmeticAssignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (String) val_peek(1), (Expression) val_peek(0)); }
break;
case 44:
//#line 184 "../../src/parser/parser.y"
{ yyval = new ArithmeticAssignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (String) val_peek(1), (Expression) val_peek(0)); }
break;
case 45:
//#line 186 "../../src/parser/parser.y"
{ yyval = new ArithmeticAssignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (String) val_peek(1), (Expression) val_peek(0)); }
break;
case 46:
//#line 188 "../../src/parser/parser.y"
{ yyval = new ArithmeticAssignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (String) val_peek(1), (Expression) val_peek(0)); }
break;
case 47:
//#line 190 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 48:
//#line 192 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 49:
//#line 194 "../../src/parser/parser.y"
{ yyval = (Invocation) val_peek(0); }
break;
case 50:
//#line 195 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 51:
//#line 196 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn()
          																, (Expression) val_peek(2), ((Variable) val_peek(0)).getName()); }
break;
case 52:
//#line 198 "../../src/parser/parser.y"
{ yyval = (Cast) val_peek(0); }
break;
case 53:
//#line 199 "../../src/parser/parser.y"
{ yyval = (Indexing) val_peek(0);}
break;
case 54:
//#line 200 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn()
          																, (Integer) val_peek(0)); }
break;
case 55:
//#line 202 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn()
          																, (Double) val_peek(0)); }
break;
case 56:
//#line 204 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn()
          																, (Character) val_peek(0)); }
break;
case 57:
//#line 206 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn()
          																, (String) val_peek(0)); }
break;
case 58:
//#line 210 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(2), (List<Statement>) val_peek(0)
																		, (Expression) val_peek(4)); }
break;
case 59:
//#line 213 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(0), new ArrayList<Statement>()
																		, (Expression) val_peek(2)); }
break;
case 60:
//#line 218 "../../src/parser/parser.y"
{yyval = (List<Statement>)val_peek(1);}
break;
case 61:
//#line 219 "../../src/parser/parser.y"
{ List<Statement> list = new ArrayList<Statement>();
															  list.add((Statement)val_peek(0));
															  yyval = list;}
break;
case 62:
//#line 224 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																	  yyval = new WhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(2)); }
break;
case 63:
//#line 230 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn()
          																	,(Type) val_peek(2), (Expression) val_peek(0)); }
break;
case 64:
//#line 234 "../../src/parser/parser.y"
{ List<Expression> list = (List<Expression>) val_peek(2);
																	  list.add((Expression) val_peek(0));
																	  yyval = list; }
break;
case 65:
//#line 237 "../../src/parser/parser.y"
{ List<Expression> list = new ArrayList<>();
				 													  list.add((Expression)val_peek(0));
				 													  yyval = list; }
break;
case 66:
//#line 242 "../../src/parser/parser.y"
{ List<String> list = (List<String>) val_peek(2);
																	  if(list.contains((String)val_peek(0)))
																	  	new ErrorType(scanner.getLine(), scanner.getColumn()
																	  			, "The variable "+((String)val_peek(0))
																	  			+" has been already declared");
																	  list.add((String)val_peek(0));
																	  yyval = list; }
break;
case 67:
//#line 249 "../../src/parser/parser.y"
{ List<String> list = new ArrayList<String>();
		 															  list.add((String) val_peek(0));
		 															  yyval = list; }
break;
case 68:
//#line 254 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 69:
//#line 255 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 70:
//#line 256 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 71:
//#line 259 "../../src/parser/parser.y"
{yyval = new Indexing(scanner.getLine()
																, scanner.getColumn(), (Expression)val_peek(3)
																,(Expression)val_peek(1));}
break;
case 72:
//#line 264 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  array.getOf();
															  yyval = array; }
break;
case 73:
//#line 268 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  array.getOf();
															  yyval = array; }
break;
case 74:
//#line 274 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 75:
//#line 279 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 76:
//#line 284 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 77:
//#line 291 "../../src/parser/parser.y"
{ List<VariableDefinition> list = (List<VariableDefinition>) val_peek(1);
															for(VariableDefinition varDef : (List<VariableDefinition>) val_peek(0)){
																if(list.contains(varDef))
																	new ErrorType(scanner.getLine(), scanner.getColumn()
																			, "The variable "+varDef.getName()
																			+" has been already defined");
															  	list.add(varDef);
															}
															yyval = list; }
break;
case 78:
//#line 300 "../../src/parser/parser.y"
{ yyval = new ArrayList<VariableDefinition>(); }
break;
case 79:
//#line 303 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 80:
//#line 307 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 81:
//#line 310 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 82:
//#line 313 "../../src/parser/parser.y"
{ List<RecordField> list = new ArrayList<>();
													RecordField rec = null;
													for(VariableDefinition varDef : (List<VariableDefinition>) val_peek(1)){
													  	rec = new RecordField(scanner.getLine(), scanner.getColumn()
													  		, varDef.getType(), varDef.getName());
													  	list.add(rec);
													}
													yyval = new Struct(scanner.getLine(), scanner.getColumn()
														  , list); }
break;
case 83:
//#line 324 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(0)); }
break;
//#line 1206 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
