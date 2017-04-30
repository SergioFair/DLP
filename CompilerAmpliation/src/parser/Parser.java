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
public final static short FOR=279;
public final static short DO=280;
public final static short INC=281;
public final static short DEC=282;
public final static short DIVIDEEQUALS=283;
public final static short TIMESEQUALS=284;
public final static short MINUSEQUALS=285;
public final static short PLUSEQUALS=286;
public final static short IFX=287;
public final static short POTENTIAL=288;
public final static short unary_minus=289;
public final static short CAST=290;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    2,    5,    5,    8,    8,    6,
    6,    7,    7,    3,   12,   14,   14,   11,   11,   16,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   15,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   19,   19,   19,   19,   19,
   19,   19,   22,   22,   29,   29,   23,   25,   24,   27,
    9,    9,   30,   30,   10,   10,   10,   28,   31,   31,
   31,    4,    4,    4,   13,   13,   21,   18,   20,   32,
   26,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    1,    1,    4,    3,    8,
    7,    8,    7,    7,    2,    2,    0,    3,    1,    2,
    2,    2,    2,    2,    2,    1,    1,    1,    1,    2,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    3,    3,    2,    2,    1,    3,    3,    1,
    1,    1,    1,    1,    1,    0,    2,    2,    3,    3,
    3,    3,    7,    5,    3,    1,    5,    6,    8,    4,
    3,    1,    3,    1,    1,    1,    1,    4,    4,    4,
    4,    3,    3,    3,    2,    0,    3,    2,    2,    4,
    2,
};
final static short yydefred[] = {                         5,
    0,    1,    0,    0,   75,   77,   76,    0,    2,    3,
    4,    6,    7,    0,    0,    0,    0,    0,   86,    0,
    0,    0,   74,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   82,    0,   83,    0,   84,    0,    0,
    0,   19,    0,   90,   85,    0,    0,    0,   80,   73,
   79,   81,   86,   20,    0,    0,   86,   86,    0,    0,
    0,   86,   18,    0,    0,   86,   13,    0,    0,   14,
   11,    0,   52,   54,   53,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   47,   16,    0,    0,
    0,    0,    0,   26,   27,   28,   29,    0,   50,   51,
   12,   10,    0,    0,    0,    0,    0,    0,    0,    0,
   17,   66,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   57,   58,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   21,   22,   23,   24,   25,   30,    9,    0,    0,    0,
    0,    0,    0,    0,    0,   48,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   49,    8,    0,    0,
    0,    0,   65,    0,    0,   78,    0,   67,    0,    0,
    0,    0,    0,   68,   63,    0,   69,
};
final static short yydgoto[] = {                          1,
    2,    3,    9,   45,   11,   12,   13,   87,  104,   40,
   41,   60,   61,   68,  112,   42,   89,   90,   91,   92,
   93,   94,   95,   96,   97,   98,   99,  100,  113,   22,
   15,   16,
};
final static short yysindex[] = {                         0,
    0,    0, -100, -215,    0,    0,    0,  -93,    0,    0,
    0,    0,    0,  -90,  -89,  -88,   -8,   13,    0,   45,
 -168,  -16,    0, -147,   46, -129,   68,   -2,  100,    4,
    1,   64, -110,    0,   72,    0,   73,    0,   35,  -99,
   82,    0,   59,    0,    0,  -78,   66,   93,    0,    0,
    0,    0,    0,    0,   74, -213,    0,    0,   80,   69,
  -31,    0,    0,   83,   84,    0,    0,  373,   85,    0,
    0,   89,    0,    0,    0,  178,  334,  334,  180,  182,
  334,  191,  526,  334,  334,  919,    0,    0,   41,  174,
  194,  220,  221,    0,    0,    0,    0,  222,    0,    0,
    0,    0,  119,  238,  403,  238,  334,  334,  403,  373,
    0,    0,    6,  414,   -9,  249,  101,  334,  334,  334,
  334,  334,  334,    0,    0,  334,  334,  334,  334,  334,
  334,  334,  334,  334,  334,  334,  334,  334,  334,  334,
    0,    0,    0,    0,    0,    0,    0,  149,  334,  108,
  138,  334,  532,  258,  334,    0,  302,  302,  302,  302,
  414,  414,  403,  403,  403,  403,  403,  302,  302,   24,
   24,  -34,  -34,  -34,   -9,  159,    0,    0,  403,  526,
  526,  345,    0,  334,   -9,    0,   23,    0,  334,  170,
  526,   71,  259,    0,    0,  526,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   77,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  495,    0,    0,    0,    0,    0,    0,  -30,    0,    0,
    0,    0,    0,    0,    0,  -37,    0,    0,    0,    0,
    0,    0,  248,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  250,   63,  251,    0,    0,  252,  248,
    0,    0,    0,  851,  -26,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  248,    0,    0,    0,  672,  787,  816,  822,
  881,  887,  -21,  -10,   39,   40,  260,  828,  857,  553,
  781,  425,  436,  465,    3,    0,    0,    0,   65,  248,
  248,    0,    0,    0,   32,    0,  501,    0,  267,    0,
  248,    0,    0,    0,    0,  248,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  318,    0,    0,    0,    0,  -51,   11,
  297,  129,  311,  226,  -42,  282, 1102,    0,  151,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -61,  124,
    0,    0,
};
final static int YYTABLESIZE=1291;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         55,
   21,   24,   26,   55,   55,   55,   55,   55,   55,   55,
   45,  140,   21,   14,   45,   45,   45,   45,   45,   62,
   45,   55,   55,   55,   55,   88,  106,   33,   56,   19,
   61,   28,   45,   45,   45,   45,  140,   62,   39,   36,
   46,   47,   34,   36,   36,   36,   36,   36,   61,   36,
   17,  148,   29,   55,   18,   55,  139,    5,    6,    7,
  137,   36,   36,   36,   36,  135,   45,  152,   70,  140,
  136,   46,   70,   70,   70,   70,   70,  137,   70,   60,
   59,  139,  135,  133,   31,  134,  140,  136,   32,   33,
   70,   70,   70,   70,   15,   36,  116,   60,   59,  141,
  132,  130,  131,   72,   36,   71,   72,  137,   71,   35,
   88,   33,  135,  133,  139,  134,  140,  136,  187,  188,
   74,   72,   55,   71,   70,   56,   38,   37,   44,  195,
  132,  139,  131,   59,  197,   74,   56,  137,   25,   27,
   43,  156,  135,  133,  137,  134,  140,  136,  180,  135,
  133,   84,  134,  140,  136,   50,   49,   53,   86,  147,
  132,  139,  131,   85,   51,   52,   54,  132,    4,  131,
    5,    6,    7,    8,  137,   20,   23,   23,  181,  135,
  133,   57,  134,  140,  136,   64,   65,   23,   58,  178,
   69,  139,  149,   67,   72,  137,   62,  132,  139,  131,
  135,  133,   66,  134,  140,  136,  137,   70,   71,  101,
  194,  135,  133,  102,  134,  140,  136,  103,  132,  107,
  131,  108,   55,   55,   55,   55,   55,   55,  139,  132,
  110,  131,  142,   45,   45,   45,   45,   45,   45,    5,
    6,    7,    8,   55,   55,   55,   55,   55,   55,  139,
   55,  186,  143,  138,   45,   45,   45,   45,   45,   45,
  139,   45,   36,   36,   36,   36,   36,   36,    5,    6,
    7,    5,    6,    7,    5,    6,    7,    8,  144,  145,
  146,  149,  154,   36,   36,   36,   36,   36,   36,  155,
   36,   70,   70,   70,   70,   70,   70,  184,  191,  196,
  118,  119,  120,  121,  122,  123,   56,   56,   89,   88,
   91,  138,   70,   70,   70,   70,   70,   70,   87,   70,
   10,  124,  125,  126,  127,  128,  129,   48,  138,   30,
  118,  119,  120,  121,  122,  123,  153,   63,  137,  193,
    0,    0,    0,  135,  133,    0,  134,  140,  136,    0,
    0,  124,  125,  126,  127,  128,  129,    0,  138,    0,
  118,  119,  120,  121,  122,  123,   84,  118,  119,  120,
  121,  122,  123,   86,    0,   73,   74,   75,   85,    0,
    0,  137,    0,    0,   76,    0,  135,  133,  138,  134,
  140,  136,  139,    0,    0,  138,    0,  118,  119,  120,
  121,  122,  123,  189,  132,   84,  131,    0,    0,    0,
    0,    0,   86,    0,    0,    0,    0,   85,  118,  119,
  120,  121,  122,  123,    0,  138,    0,    0,    0,  118,
  119,  120,  121,  122,  123,  139,    0,    0,    0,  137,
    0,    0,    0,    0,  135,  133,  138,  134,  140,  136,
  137,    0,    0,    0,    0,  135,  133,  138,  134,  140,
  136,   32,  132,    0,  131,   32,   32,   32,   32,   32,
    0,   32,   33,  132,    0,  131,   33,   33,   33,   33,
   33,    0,   33,   32,   32,   32,   32,    0,    0,    0,
    0,    0,    0,  139,   33,   33,   33,   33,    0,    0,
    0,   35,    0,    0,  139,   35,   35,   35,   35,   35,
    0,   35,    0,    0,    0,    0,    0,   32,    0,    0,
    0,    0,    0,   35,   35,   35,   35,   17,   33,    0,
    0,    0,    0,   64,   17,    0,    0,    0,    0,   17,
   64,    0,    0,    0,    0,   64,    0,    0,    0,    0,
    0,    0,    0,   17,    0,    0,    0,   35,   84,   64,
    0,    0,    0,    0,   84,   86,    0,    0,    0,    0,
   85,   86,    0,    0,    0,    0,   85,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  138,
   73,   74,   75,   31,    0,   31,   31,   31,    0,   76,
    0,    0,    0,    0,  118,  119,  120,  121,  122,  123,
    0,   31,   31,   31,   31,    0,    0,    0,    0,   17,
    0,    0,    0,    0,    0,   64,    0,    0,    0,   73,
   74,   75,  138,    0,    0,    0,    0,    0,   76,   77,
   78,    0,    0,    0,    0,   31,    0,   79,  111,   80,
   81,   82,   83,    0,    0,    0,  183,    0,    0,    0,
    0,    0,  118,  119,  120,  121,  122,  123,    0,    0,
    0,    0,    0,  118,  119,  120,  121,    0,    0,    0,
    0,    0,    0,    0,   32,   32,   32,   32,   32,   32,
  138,    0,    0,    0,    0,   33,   33,   33,   33,   33,
   33,  138,    0,    0,    0,   32,   32,   32,   32,   32,
   32,    0,   41,    0,    0,   41,   33,   33,   33,   33,
   33,   33,    0,    0,   35,   35,   35,   35,   35,   35,
   41,   41,   41,   41,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   35,   35,   35,   35,   35,
   35,   17,   17,   17,    0,    0,    0,   64,   64,   64,
   17,   17,   17,    0,   41,    0,   64,   64,   64,   17,
    0,   17,   17,   17,   17,   64,    0,   64,   64,   64,
   64,    0,   73,   74,   75,    0,    0,    0,   73,   74,
   75,   76,   77,   78,    0,    0,    0,   76,   77,   78,
   79,    0,   80,   81,   82,   83,   79,    0,   80,   81,
   82,   83,   31,   31,   31,   31,   31,   31,    0,    0,
    0,   34,    0,   34,   34,   34,    0,   39,    0,    0,
   39,    0,    0,   31,   31,   31,   31,   31,   31,   34,
   34,   34,   34,    0,    0,   39,   39,   39,   39,    0,
    0,    0,    0,    0,    0,    0,   37,    0,    0,   37,
    0,    0,   42,    0,    0,   42,    0,    0,   38,    0,
    0,   38,    0,   34,   37,   37,   37,   37,    0,   39,
   42,   42,   42,   42,    0,    0,   38,   38,   38,   38,
    0,   46,    0,    0,   46,    0,    0,   40,    0,    0,
   40,    0,    0,    0,    0,    0,    0,    0,   37,   46,
    0,   46,    0,    0,   42,   40,   40,   40,   40,    0,
   38,   43,    0,    0,   43,    0,    0,   44,    0,    0,
   44,   41,   41,   41,   41,   41,   41,    0,    0,   43,
    0,   43,    0,   46,    0,   44,    0,   44,    0,   40,
    0,   84,   41,   41,   41,   41,   41,   41,   86,    0,
    0,    0,    0,   85,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   43,    0,    0,    0,    0,    0,   44,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   34,   34,   34,   34,   34,   34,   39,   39,   39,   39,
   39,   39,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   34,   34,   34,   34,   34,   34,   39,   39,   39,
   39,   39,   39,    0,    0,   37,   37,   37,   37,   37,
   37,   42,   42,   42,   42,   42,   42,   38,   38,   38,
   38,   38,   38,    0,    0,    0,   37,   37,   37,   37,
   37,   37,   42,   42,   42,   42,   42,   42,   38,   38,
   38,   38,   38,   38,   46,   46,   40,   40,   40,   40,
   40,   40,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   46,   46,   46,   46,   46,   46,   40,   40,   40,
   40,   40,   40,    0,   43,   43,    0,    0,    0,    0,
   44,   44,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   43,   43,   43,   43,   43,   43,   44,   44,   44,
   44,   44,   44,    0,    0,   73,   74,   75,  105,  105,
    0,    0,  109,    0,   76,  114,  115,  117,    0,    5,
    6,    7,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  105,    0,    0,    0,  150,  151,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  157,
  158,  159,  160,  161,  162,    0,    0,  163,  164,  165,
  166,  167,  168,  169,  170,  171,  172,  173,  174,  175,
  176,  177,    0,    0,    0,    0,    0,    0,    0,    0,
  179,    0,    0,  182,    0,    0,  185,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  190,    0,    0,    0,    0,
  192,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   91,   91,   91,   41,   42,   43,   44,   45,   46,   47,
   37,   46,   91,    3,   41,   42,   43,   44,   45,   41,
   47,   59,   60,   61,   62,   68,   78,   44,   59,  123,
   41,   40,   59,   60,   61,   62,   46,   59,   41,   37,
   30,   41,   59,   41,   42,   43,   44,   45,   59,   47,
  266,  103,   40,   91,  270,   93,   91,  271,  272,  273,
   37,   59,   60,   61,   62,   42,   93,  110,   37,   46,
   47,   61,   41,   42,   43,   44,   45,   37,   47,   41,
   41,   91,   42,   43,   40,   45,   46,   47,  257,   44,
   59,   60,   61,   62,  125,   93,   86,   59,   59,   59,
   60,   61,   62,   41,   59,   41,   44,   37,   44,  257,
  153,   44,   42,   43,   91,   45,   46,   47,  180,  181,
   44,   59,   41,   59,   93,   44,   59,  257,  125,  191,
   60,   91,   62,   41,  196,   59,   44,   37,   15,   16,
   41,   41,   42,   43,   37,   45,   46,   47,   41,   42,
   43,   33,   45,   46,   47,  266,   93,  123,   40,   41,
   60,   91,   62,   45,   93,   93,  266,   60,  269,   62,
  271,  272,  273,  274,   37,  266,  266,  266,   41,   42,
   43,  123,   45,   46,   47,   57,   58,  266,  123,   41,
   62,   91,   44,  125,   66,   37,  123,   60,   91,   62,
   42,   43,  123,   45,   46,   47,   37,  125,  125,  125,
   41,   42,   43,  125,   45,   46,   47,   40,   60,   40,
   62,   40,  260,  261,  262,  263,  264,  265,   91,   60,
   40,   62,   59,  260,  261,  262,  263,  264,  265,  271,
  272,  273,  274,  281,  282,  283,  284,  285,  286,   91,
  288,   93,   59,  288,  281,  282,  283,  284,  285,  286,
   91,  288,  260,  261,  262,  263,  264,  265,  271,  272,
  273,  271,  272,  273,  271,  272,  273,  274,   59,   59,
   59,   44,  277,  281,  282,  283,  284,  285,  286,   41,
  288,  260,  261,  262,  263,  264,  265,   40,  276,   41,
  260,  261,  262,  263,  264,  265,   59,   41,   59,   59,
   59,  288,  281,  282,  283,  284,  285,  286,   59,  288,
    3,  281,  282,  283,  284,  285,  286,   31,  288,   19,
  260,  261,  262,  263,  264,  265,  111,   56,   37,  189,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,  281,  282,  283,  284,  285,  286,   -1,  288,   -1,
  260,  261,  262,  263,  264,  265,   33,  260,  261,  262,
  263,  264,  265,   40,   -1,  257,  258,  259,   45,   -1,
   -1,   37,   -1,   -1,  266,   -1,   42,   43,  288,   45,
   46,   47,   91,   -1,   -1,  288,   -1,  260,  261,  262,
  263,  264,  265,   59,   60,   33,   62,   -1,   -1,   -1,
   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,  260,  261,
  262,  263,  264,  265,   -1,  288,   -1,   -1,   -1,  260,
  261,  262,  263,  264,  265,   91,   -1,   -1,   -1,   37,
   -1,   -1,   -1,   -1,   42,   43,  288,   45,   46,   47,
   37,   -1,   -1,   -1,   -1,   42,   43,  288,   45,   46,
   47,   37,   60,   -1,   62,   41,   42,   43,   44,   45,
   -1,   47,   37,   60,   -1,   62,   41,   42,   43,   44,
   45,   -1,   47,   59,   60,   61,   62,   -1,   -1,   -1,
   -1,   -1,   -1,   91,   59,   60,   61,   62,   -1,   -1,
   -1,   37,   -1,   -1,   91,   41,   42,   43,   44,   45,
   -1,   47,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   59,   60,   61,   62,   33,   93,   -1,
   -1,   -1,   -1,   33,   40,   -1,   -1,   -1,   -1,   45,
   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   59,   -1,   -1,   -1,   93,   33,   59,
   -1,   -1,   -1,   -1,   33,   40,   -1,   -1,   -1,   -1,
   45,   40,   -1,   -1,   -1,   -1,   45,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  288,
  257,  258,  259,   41,   -1,   43,   44,   45,   -1,  266,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,  265,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,   -1,  125,
   -1,   -1,   -1,   -1,   -1,  125,   -1,   -1,   -1,  257,
  258,  259,  288,   -1,   -1,   -1,   -1,   -1,  266,  267,
  268,   -1,   -1,   -1,   -1,   93,   -1,  275,  123,  277,
  278,  279,  280,   -1,   -1,   -1,  125,   -1,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,  264,  265,   -1,   -1,
   -1,   -1,   -1,  260,  261,  262,  263,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,  265,
  288,   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,
  265,  288,   -1,   -1,   -1,  281,  282,  283,  284,  285,
  286,   -1,   41,   -1,   -1,   44,  281,  282,  283,  284,
  285,  286,   -1,   -1,  260,  261,  262,  263,  264,  265,
   59,   60,   61,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  281,  282,  283,  284,  285,
  286,  257,  258,  259,   -1,   -1,   -1,  257,  258,  259,
  266,  267,  268,   -1,   93,   -1,  266,  267,  268,  275,
   -1,  277,  278,  279,  280,  275,   -1,  277,  278,  279,
  280,   -1,  257,  258,  259,   -1,   -1,   -1,  257,  258,
  259,  266,  267,  268,   -1,   -1,   -1,  266,  267,  268,
  275,   -1,  277,  278,  279,  280,  275,   -1,  277,  278,
  279,  280,  260,  261,  262,  263,  264,  265,   -1,   -1,
   -1,   41,   -1,   43,   44,   45,   -1,   41,   -1,   -1,
   44,   -1,   -1,  281,  282,  283,  284,  285,  286,   59,
   60,   61,   62,   -1,   -1,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   41,   -1,
   -1,   44,   -1,   93,   59,   60,   61,   62,   -1,   93,
   59,   60,   61,   62,   -1,   -1,   59,   60,   61,   62,
   -1,   41,   -1,   -1,   44,   -1,   -1,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   59,
   -1,   61,   -1,   -1,   93,   59,   60,   61,   62,   -1,
   93,   41,   -1,   -1,   44,   -1,   -1,   41,   -1,   -1,
   44,  260,  261,  262,  263,  264,  265,   -1,   -1,   59,
   -1,   61,   -1,   93,   -1,   59,   -1,   61,   -1,   93,
   -1,   33,  281,  282,  283,  284,  285,  286,   40,   -1,
   -1,   -1,   -1,   45,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  260,  261,  262,  263,  264,  265,  260,  261,  262,  263,
  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  281,  282,  283,  284,  285,  286,  281,  282,  283,
  284,  285,  286,   -1,   -1,  260,  261,  262,  263,  264,
  265,  260,  261,  262,  263,  264,  265,  260,  261,  262,
  263,  264,  265,   -1,   -1,   -1,  281,  282,  283,  284,
  285,  286,  281,  282,  283,  284,  285,  286,  281,  282,
  283,  284,  285,  286,  264,  265,  260,  261,  262,  263,
  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  281,  282,  283,  284,  285,  286,  281,  282,  283,
  284,  285,  286,   -1,  264,  265,   -1,   -1,   -1,   -1,
  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  281,  282,  283,  284,  285,  286,  281,  282,  283,
  284,  285,  286,   -1,   -1,  257,  258,  259,   77,   78,
   -1,   -1,   81,   -1,  266,   84,   85,   86,   -1,  271,
  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  103,   -1,   -1,   -1,  107,  108,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  118,
  119,  120,  121,  122,  123,   -1,   -1,  126,  127,  128,
  129,  130,  131,  132,  133,  134,  135,  136,  137,  138,
  139,  140,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  149,   -1,   -1,  152,   -1,   -1,  155,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  184,   -1,   -1,   -1,   -1,
  189,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=290;
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
"RETURN","FOR","DO","INC","DEC","DIVIDEEQUALS","TIMESEQUALS","MINUSEQUALS",
"PLUSEQUALS","IFX","POTENTIAL","\"unary_minus\"","CAST",
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
"statement : read ';'",
"statement : increment ';'",
"statement : write ';'",
"statement : assignment ';'",
"statement : if_else",
"statement : while",
"statement : for",
"statement : do_while",
"statement : return ';'",
"expression : expression '+' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '-' expression",
"expression : expression '%' expression",
"expression : expression POTENTIAL expression",
"expression : expression DOUBLEEQUALS expression",
"expression : expression '>' expression",
"expression : expression GEQ expression",
"expression : expression '<' expression",
"expression : expression LEQ expression",
"expression : expression DIFFERENT expression",
"expression : expression AND expression",
"expression : expression OR expression",
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
"increment :",
"increment : expression INC",
"increment : expression DEC",
"increment : expression PLUSEQUALS expression",
"increment : expression MINUSEQUALS expression",
"increment : expression TIMESEQUALS expression",
"increment : expression DIVIDEEQUALS expression",
"if_else : IF '(' expression ')' body ELSE body",
"if_else : IF '(' expression ')' body",
"body : '{' listOfStatements '}'",
"body : statement",
"while : WHILE '(' expression ')' body",
"do_while : DO body WHILE '(' expression ')'",
"for : FOR '(' statement expression ';' increment ')' body",
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
"array_creation : struct '[' INT_CONSTANT ']'",
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

//#line 353 "../../src/parser/parser.y"

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
//#line 667 "Parser.java"
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
//#line 49 "../../src/parser/parser.y"
{ this.ast = new Program(scanner.getLine(), scanner.getColumn()
															, (List<Definition>)val_peek(0)); }
break;
case 2:
//#line 52 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>)val_peek(1);
														defList.add((FunctionDefinition) val_peek(0));
	   													yyval = defList; }
break;
case 3:
//#line 57 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														for(Definition def : (List<Definition>)val_peek(0)){
															defList.add(def);
														}
														yyval = defList; }
break;
case 4:
//#line 62 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														defList.add((Definition)val_peek(0));
														yyval = defList; }
break;
case 5:
//#line 65 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 6:
//#line 67 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 7:
//#line 68 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 8:
//#line 71 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	,(List<Expression>) val_peek(1) , new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(3))); }
break;
case 9:
//#line 74 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	, new ArrayList<Expression>(), new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(2))); }
break;
case 10:
//#line 80 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(6),
																			  		  (List<VariableDefinition>) val_peek(4), (Type) val_peek(7))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 11:
//#line 85 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(5),
																			  		  new ArrayList<VariableDefinition>(), (Type) val_peek(6))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 12:
//#line 94 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(6),
																			  (List<VariableDefinition>) val_peek(4), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 13:
//#line 99 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 14:
//#line 106 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 15:
//#line 113 "../../src/parser/parser.y"
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
//#line 123 "../../src/parser/parser.y"
{ List<Statement> list = (List<Statement>) val_peek(1);
																  list.add((Statement) val_peek(0));
																  yyval = list; }
break;
case 17:
//#line 126 "../../src/parser/parser.y"
{ yyval = new ArrayList<>(); }
break;
case 18:
//#line 129 "../../src/parser/parser.y"
{ List<VariableDefinition> list = (List<VariableDefinition>) val_peek(2);
																  	list.add((VariableDefinition) val_peek(0));
																  yyval = list; }
break;
case 19:
//#line 132 "../../src/parser/parser.y"
{ List<VariableDefinition> list = new ArrayList<>();
																  list.add((VariableDefinition) val_peek(0));
																  yyval = list; }
break;
case 20:
//#line 137 "../../src/parser/parser.y"
{ yyval = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) val_peek(0), (Type) val_peek(1)); }
break;
case 21:
//#line 141 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 22:
//#line 142 "../../src/parser/parser.y"
{ yyval = (Read) val_peek(1); }
break;
case 23:
//#line 143 "../../src/parser/parser.y"
{ yyval = (Assignment) val_peek(1); }
break;
case 24:
//#line 144 "../../src/parser/parser.y"
{ yyval = (Write) val_peek(1); }
break;
case 25:
//#line 145 "../../src/parser/parser.y"
{ yyval = (Assignment) val_peek(1); }
break;
case 26:
//#line 146 "../../src/parser/parser.y"
{ yyval = (IfStatement) val_peek(0); }
break;
case 27:
//#line 147 "../../src/parser/parser.y"
{ yyval = (WhileStatement) val_peek(0); }
break;
case 28:
//#line 148 "../../src/parser/parser.y"
{ yyval = (ForStatement) val_peek(0); }
break;
case 29:
//#line 149 "../../src/parser/parser.y"
{ yyval = (DoWhileStatement) val_peek(0); }
break;
case 30:
//#line 150 "../../src/parser/parser.y"
{ yyval = (Return) val_peek(1); }
break;
case 31:
//#line 153 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"+",(Expression) val_peek(0)); }
break;
case 32:
//#line 155 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"*",(Expression) val_peek(0)); }
break;
case 33:
//#line 157 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"/",(Expression) val_peek(0)); }
break;
case 34:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"-",(Expression) val_peek(0)); }
break;
case 35:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"%",(Expression) val_peek(0)); }
break;
case 36:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Power(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 37:
//#line 165 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"==",(Expression) val_peek(0)); }
break;
case 38:
//#line 167 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">",(Expression) val_peek(0)); }
break;
case 39:
//#line 169 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">=",(Expression) val_peek(0)); }
break;
case 40:
//#line 171 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<",(Expression) val_peek(0)); }
break;
case 41:
//#line 173 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<=",(Expression) val_peek(0)); }
break;
case 42:
//#line 175 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"!=",(Expression) val_peek(0)); }
break;
case 43:
//#line 177 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"&&",(Expression) val_peek(0)); }
break;
case 44:
//#line 179 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"||",(Expression) val_peek(0)); }
break;
case 45:
//#line 181 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 46:
//#line 183 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 47:
//#line 185 "../../src/parser/parser.y"
{ yyval = (Invocation) val_peek(0); }
break;
case 48:
//#line 186 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 49:
//#line 187 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn()
          																, (Expression) val_peek(2), ((Variable) val_peek(0)).getName()); }
break;
case 50:
//#line 189 "../../src/parser/parser.y"
{ yyval = (Cast) val_peek(0); }
break;
case 51:
//#line 190 "../../src/parser/parser.y"
{ yyval = (Indexing) val_peek(0); }
break;
case 52:
//#line 191 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn()
          																, (Integer) val_peek(0)); }
break;
case 53:
//#line 193 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn()
          																, (Double) val_peek(0)); }
break;
case 54:
//#line 195 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn()
          																, (Character) val_peek(0)); }
break;
case 55:
//#line 197 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn()
          																, (String) val_peek(0)); }
break;
case 57:
//#line 201 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"+"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 58:
//#line 205 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"-"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 59:
//#line 209 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"+",(Expression)val_peek(0))); }
break;
case 60:
//#line 212 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"-",(Expression)val_peek(0))); }
break;
case 61:
//#line 215 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"*",(Expression)val_peek(0))); }
break;
case 62:
//#line 218 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"/",(Expression)val_peek(0))); }
break;
case 63:
//#line 223 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(2), (List<Statement>) val_peek(0)
																		, (Expression) val_peek(4)); }
break;
case 64:
//#line 226 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(0), new ArrayList<Statement>()
																		, (Expression) val_peek(2)); }
break;
case 65:
//#line 231 "../../src/parser/parser.y"
{yyval = (List<Statement>)val_peek(1);}
break;
case 66:
//#line 232 "../../src/parser/parser.y"
{ List<Statement> list = new ArrayList<Statement>();
															  list.add((Statement)val_peek(0));
															  yyval = list;}
break;
case 67:
//#line 237 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																	  yyval = new WhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(2)); }
break;
case 68:
//#line 242 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(4));
																	  yyval = new DoWhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(1)); }
break;
case 69:
//#line 247 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																			  yyval = new ForStatement(scanner.getLine(), scanner.getColumn()
																			       , (Statement) val_peek(5), (Expression) val_peek(4), (Assignment) val_peek(2), body); }
break;
case 70:
//#line 253 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn()
          																	,(Type) val_peek(2), (Expression) val_peek(0)); }
break;
case 71:
//#line 257 "../../src/parser/parser.y"
{ List<Expression> list = (List<Expression>) val_peek(2);
																	  list.add((Expression) val_peek(0));
																	  yyval = list; }
break;
case 72:
//#line 260 "../../src/parser/parser.y"
{ List<Expression> list = new ArrayList<>();
				 													  list.add((Expression)val_peek(0));
				 													  yyval = list; }
break;
case 73:
//#line 265 "../../src/parser/parser.y"
{ List<String> list = (List<String>) val_peek(2);
																	  if(list.contains((String)val_peek(0)))
																	  	new ErrorType(scanner.getLine(), scanner.getColumn()
																	  			, "The variable "+((String)val_peek(0))
																	  			+" has been already declared");
																	  list.add((String)val_peek(0));
																	  yyval = list; }
break;
case 74:
//#line 272 "../../src/parser/parser.y"
{ List<String> list = new ArrayList<String>();
		 															  list.add((String) val_peek(0));
		 															  yyval = list; }
break;
case 75:
//#line 277 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 76:
//#line 278 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 77:
//#line 279 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 78:
//#line 282 "../../src/parser/parser.y"
{yyval = new Indexing(scanner.getLine()
																, scanner.getColumn(), (Expression)val_peek(3)
																,(Expression)val_peek(1));}
break;
case 79:
//#line 287 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 80:
//#line 290 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 81:
//#line 293 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 82:
//#line 298 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 83:
//#line 303 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 84:
//#line 308 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 85:
//#line 315 "../../src/parser/parser.y"
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
case 86:
//#line 324 "../../src/parser/parser.y"
{ yyval = new ArrayList<VariableDefinition>(); }
break;
case 87:
//#line 327 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 88:
//#line 331 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 89:
//#line 334 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 90:
//#line 337 "../../src/parser/parser.y"
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
case 91:
//#line 348 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(0)); }
break;
//#line 1321 "Parser.java"
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
