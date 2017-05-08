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
import ast.statement.switchCase.*;
import ast.type.*;
import ast.ASTNode;
import java.util.*;
//#line 28 "Parser.java"




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
public final static short SHIFTLEFT=264;
public final static short SHIFTRIGHT=265;
public final static short AND=266;
public final static short OR=267;
public final static short ID=268;
public final static short WRITE=269;
public final static short READ=270;
public final static short VOID=271;
public final static short MAIN=272;
public final static short CHAR=273;
public final static short DOUBLE=274;
public final static short INT=275;
public final static short STRUCT=276;
public final static short IF=277;
public final static short ELSE=278;
public final static short WHILE=279;
public final static short RETURN=280;
public final static short FOR=281;
public final static short DO=282;
public final static short SWITCH=283;
public final static short CASE=284;
public final static short DEFAULT=285;
public final static short INC=286;
public final static short DEC=287;
public final static short DIVIDEEQUALS=288;
public final static short TIMESEQUALS=289;
public final static short MINUSEQUALS=290;
public final static short PLUSEQUALS=291;
public final static short BREAK=292;
public final static short IFX=293;
public final static short POTENTIAL=294;
public final static short unary_minus=295;
public final static short CAST=296;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    2,    5,    5,    8,    8,    6,
    6,    7,    7,    3,   12,   14,   14,   11,   11,   16,
   16,   16,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   22,   22,
   29,   32,   32,   23,   26,   24,   25,   33,   33,   34,
   34,   34,   34,   35,   30,    9,    9,   36,   36,   10,
   10,   10,   31,   18,   18,   18,    4,    4,    4,   13,
   13,   28,   28,   28,   28,   28,   28,   28,   20,   21,
   17,   27,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    1,    1,    4,    3,    8,
    7,    8,    7,    7,    2,    2,    0,    3,    1,    2,
    2,    2,    2,    2,    2,    1,    1,    1,    1,    1,
    2,    3,    3,    3,    3,    3,    1,    1,    3,    3,
    3,    3,    3,    3,    3,    3,    3,    2,    2,    1,
    3,    3,    1,    1,    1,    1,    1,    1,    7,    5,
    5,    3,    1,    5,    6,    8,    7,    2,    1,    5,
    4,    4,    3,    2,    4,    3,    1,    3,    1,    1,
    1,    1,    4,    4,    4,    4,    3,    3,    3,    2,
    0,    3,    2,    2,    3,    3,    3,    3,    2,    2,
    4,    2,
};
final static short yydefred[] = {                         5,
    0,    1,    0,    0,   80,   82,   81,    0,    2,    3,
    4,    6,    7,    0,    0,    0,    0,    0,   91,    0,
    0,    0,   79,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   87,    0,   89,    0,   88,    0,    0,
    0,   19,    0,    0,    0,  101,   90,    0,    0,    0,
   85,   78,   86,   84,   91,   20,    0,    0,   21,   22,
   91,   91,    0,    0,    0,   91,   18,    0,    0,   91,
   13,    0,    0,   14,   11,    0,   55,   57,   56,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   50,   16,    0,    0,    0,   26,   27,   28,   29,
   30,    0,   37,   38,   53,   54,   12,   10,    0,    0,
    0,    0,    0,    0,    0,    0,   17,   63,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   93,   94,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   23,   24,
   25,   31,    9,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   51,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   52,    8,    0,    0,    0,    0,
   62,    0,    0,    0,    0,   83,    0,   64,    0,    0,
    0,    0,    0,    0,    0,   65,    0,    0,    0,   69,
   59,    0,    0,   17,   67,   68,   66,   17,    0,    0,
    0,   72,   70,   74,
};
final static short yydgoto[] = {                          1,
    2,    3,    9,   47,   11,   12,   13,   92,  110,   40,
   41,   64,   65,   72,  118,   42,   15,   16,   94,   95,
   96,   97,   98,   99,  100,  101,  102,  103,  104,  105,
  106,  119,  209,  210,  222,   22,
};
final static short yysindex[] = {                         0,
    0,    0,  246, -248,    0,    0,    0, -105,    0,    0,
    0,    0,    0,  -90,  -89,  -88,   -8,   16,    0,   35,
 -217,   14,    0, -203,   30, -180,   76,   -6,   54,  -57,
   19,    3, -146,    0,   38,    0,   39,    0,   18,  -87,
  124,    0,  -85,  -82,   43,    0,    0,  -70,   59,  133,
    0,    0,    0,    0,    0,    0,   61, -225,    0,    0,
    0,    0,   77,   78, -225,    0,    0,   83,   84,    0,
    0,  800,   89,    0,    0,   90,    0,    0,    0,  148,
  491,  491,  181,  182,  491,  187, 1215,  192,  491,  491,
  341,    0,    0,  110,  174,  175,    0,    0,    0,    0,
    0,  180,    0,    0,    0,    0,    0,    0,  374,  196,
  831,  196,  491,  491,  831,  800,    0,    0,  -37,  491,
  -32,  -15,  202,  150,  491,  491,  491,  491,  491,  491,
    0,    0,  491,  491,  491,  491,  491,  491,  491,  491,
  491,  491,  491,  491,  491,  491,  491,  491,    0,    0,
    0,    0,    0,  158,  491,  385,  420,  491, 1366,  205,
  432,  491,    0,  -20,  -20,  -20,  -20,  -32,  -32,  831,
  831,  831,  831,  831,  467,  -20,  -20,   -3,   -3,  -38,
  -38,  -38,  -15,  502,    0,    0,  831, 1215, 1215,  514,
    0,  491,  134,  -15,  491,    0,  -18,    0,  491,  565,
 -247,  878, 1215,  831,  217,    0,  491,  203, -109,    0,
    0, 1215,  576,    0,    0,    0,    0,    0,   -4,   -4,
  204,    0,    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   95,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, 1382,    0,    0,    0,    0,    0,
    0,  147,    0,    0,    0,    0,    0,    0,    0,   20,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  212,
   46,  225,    0,    0,  226,    0,    0,    0,    0,    0,
 1323,   56,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  893, 1242, 1250, 1286, 1387, 1400,   92,
  362,  412,  444,  457,    0, 1295, 1331,  983,  991,  902,
  938,  947,   65,    0,    0,    0,  179,    0,    0,    0,
    0,    0,    0,  101,    0,    0,  -33,    0,    0,    0,
    0,   11,    0,    0,  842,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -80,  -78,
    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  286,    0,    0,    0,    0,  -63,  318,
  259,  363,  277,  -84,  -30,  245,   63,  109, 1611,    0,
    0,    0,    0,    0,    0,    0,    0,  105,    0,    0,
    0,  146,    0,   96,   93,   69,
};
final static int YYTABLESIZE=1818;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         60,
   21,   24,   26,   21,  145,   24,   60,  148,   26,  143,
  141,   60,  142,  148,  144,  215,  145,   19,  112,   17,
   21,  143,  141,   18,  142,  148,  144,  140,   89,  139,
  148,   28,  159,  145,   39,   91,  207,  208,  143,   32,
   90,   93,  148,  144,   73,  154,   71,    5,    6,    7,
    8,   61,  147,   35,   61,   29,   58,   33,  147,   49,
   58,   58,   58,   58,   58,   58,   58,   46,   61,   61,
  147,   61,   34,   33,   31,  147,   37,   58,   58,   58,
   58,   58,   58,   25,   27,  158,   77,  147,   36,   77,
   43,   60,   48,   43,   45,   51,   48,   48,   48,   48,
   48,   39,   48,   61,   77,   39,   39,   39,   39,   39,
   58,   39,   58,   48,   48,   48,   48,   48,   48,   33,
   43,   52,   39,   39,   39,   39,   39,   39,   93,  219,
   53,   54,   98,  220,   38,   98,   44,   75,   79,   44,
   55,   75,   75,   75,   75,   75,  145,   75,   48,   98,
   98,  143,  141,   79,  142,  148,  144,   39,   75,   75,
   75,   75,   75,   75,   57,   61,   44,   58,  149,  140,
  137,  139,  138,   63,  207,  208,   58,   20,   23,   23,
   56,   62,   59,   66,   98,   60,  145,  109,   93,   93,
  163,  143,  141,   75,  142,  148,  144,   23,  186,   70,
  147,  155,   71,   73,   73,   71,   71,   74,   75,  140,
  137,  139,  138,  107,  108,    5,    6,    7,    8,   76,
  113,  114,   76,   60,   60,   60,  116,  125,  126,  127,
  128,  120,  150,  151,   60,   60,   60,   76,  152,  155,
  147,  160,  162,   60,  192,   60,   60,   60,   60,   60,
   60,   60,   77,   78,   79,  146,  201,  212,   60,  203,
  214,  146,  224,   80,   81,   82,    5,    6,    7,    8,
  100,   15,   83,  146,   84,   85,   86,   87,   88,   58,
   58,   58,   58,   99,  102,   58,   58,  221,   10,   50,
  146,    5,    6,    7,    8,   30,   61,   61,   61,   61,
   61,   61,   67,  205,  216,   58,   58,   58,   58,   58,
   58,    0,  223,   58,    0,   48,   48,   48,   48,    0,
   14,   48,   48,    0,   39,   39,   39,   39,    0,    0,
   39,   39,    0,  197,  198,    0,    0,    0,    0,    0,
    0,   48,   48,   48,   48,   48,   48,   48,  211,   48,
   39,   39,   39,   39,   39,   39,    0,  217,   39,    0,
   75,   75,   75,   75,    0,    0,   75,   75,    0,  125,
  126,  127,  128,   89,    0,  129,  130,    0,    0,    0,
   91,    0,   48,    0,    0,   90,   75,   75,   75,   75,
   75,   75,    0,    0,   75,  131,  132,  133,  134,  135,
  136,    0,   97,  146,    0,   97,   89,    0,  123,  125,
  126,  127,  128,   91,  153,  129,  130,    0,   90,   97,
   97,  145,    0,   68,   69,  188,  143,  141,   73,  142,
  148,  144,   76,    0,    0,  131,  132,  133,  134,  135,
  136,    0,    0,  146,  140,  137,  139,  138,    0,    0,
    0,    0,   96,    0,   97,   96,  145,    0,    0,    0,
  189,  143,  141,    0,  142,  148,  144,    0,  145,   96,
   96,    0,  193,  143,  141,  147,  142,  148,  144,  140,
  137,  139,  138,    0,   95,    0,    0,   95,    0,    0,
    0,  140,  137,  139,  138,    0,    0,   92,    0,    0,
   92,   95,   95,  145,   96,    0,    0,    0,  143,  141,
  147,  142,  148,  144,   92,   92,    4,    0,    5,    6,
    7,    8,  147,   89,  195,    0,  140,  137,  139,  138,
   91,    0,    0,    0,    0,   90,   95,    0,  145,    0,
    0,    0,    0,  143,  141,    0,  142,  148,  144,   92,
  145,    0,    0,    0,    0,  143,  141,  147,  142,  148,
  144,  140,  137,  139,  138,    0,    0,    0,    0,    0,
    0,    0,  199,  140,  137,  139,  138,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  147,    0,  196,    0,    0,   77,   78,   79,
    0,  145,    0,    0,  147,  206,  143,  141,   80,  142,
  148,  144,  145,    5,    6,    7,    0,  143,  141,    0,
  142,  148,  144,    0,  140,  137,  139,  138,    0,    0,
   77,   78,   79,  218,    0,  140,  137,  139,  138,    0,
    0,   80,    0,    0,  125,  126,  127,  128,    0,    0,
  129,  130,    0,    0,    0,  147,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  147,    0,    0,    0,
  131,  132,  133,  134,  135,  136,    0,    0,  146,  125,
  126,  127,  128,    0,    0,  129,  130,    0,    0,    0,
    0,  125,  126,  127,  128,    0,    0,  129,  130,    0,
    0,    0,    0,    0,    0,  131,  132,  133,  134,  135,
  136,    0,    0,  146,    0,    0,    0,  131,  132,  133,
  134,  135,  136,    0,    0,  146,  125,  126,  127,  128,
    0,    0,  129,  130,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   77,   78,   79,
    0,    0,  131,  132,  133,  134,  135,  136,   80,    0,
  146,  125,  126,  127,  128,    0,    0,  129,  130,    0,
    0,    0,    0,  125,  126,  127,  128,    0,    0,  129,
  130,    0,    0,    0,    0,    0,    0,  131,  132,  133,
  134,  135,  136,    0,    0,  146,    0,    0,    0,  131,
  132,  133,  134,  135,  136,    0,    0,  146,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  125,  126,  127,  128,    0,    0,
  129,  130,   89,    0,    0,  125,  126,  127,  128,   91,
    0,  129,  130,    0,   90,    0,    0,    0,    0,    0,
  131,  132,  133,  134,  135,  136,    0,    0,  146,    0,
    0,  131,  132,  133,  134,  135,  136,  145,    0,  146,
    0,    0,  143,  141,    0,  142,  148,  144,   37,    0,
    0,    0,    0,   37,   37,    0,   37,   37,   37,    0,
  140,  137,  139,  138,    0,    0,    0,    0,    0,    0,
    0,   37,   37,   37,   37,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  145,    0,    0,    0,    0,  143,
  141,  147,  142,  148,  144,    0,    0,    0,    0,    0,
    0,    0,   37,   44,    0,    0,   44,  140,   33,  139,
  138,    0,   33,   33,   33,   33,   33,    0,   33,    0,
   44,   44,   44,   44,   44,   44,    0,    0,    0,   33,
   33,   33,   33,   33,   33,    0,    0,    0,  147,    0,
    0,    0,    0,    0,   34,    0,    0,    0,   34,   34,
   34,   34,   34,   36,   34,   44,    0,   36,   36,   36,
   36,   36,    0,   36,   33,   34,   34,   34,   34,   34,
   34,    0,    0,    0,   36,   36,   36,   36,   36,   36,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   32,    0,   32,   32,   32,    0,    0,
   34,   35,    0,   35,   35,   35,    0,    0,    0,   36,
   32,   32,   32,   32,   32,   32,    0,    0,   35,   35,
   35,   35,   35,   35,    0,    0,   77,   78,   79,    0,
    0,    0,    0,    0,    0,    0,    0,   80,   81,   82,
    0,    0,    0,    0,    0,   32,   83,    0,   84,   85,
   86,   87,   88,   35,    0,    0,    0,    0,    0,    0,
  125,  126,  127,  128,    0,    0,  129,  130,    0,    0,
    0,   37,   37,   37,   37,    0,    0,   37,   37,    0,
    0,    0,    0,    0,    0,    0,  131,  132,  133,  134,
  135,  136,    0,    0,  146,    0,    0,   37,   37,   37,
   37,   37,   37,    0,    0,   37,    0,  125,  126,  127,
  128,    0,    0,  129,  130,    0,    0,    0,    0,    0,
    0,    0,   44,   44,   44,   44,    0,    0,   44,   44,
    0,   33,   33,   33,   33,    0,    0,   33,   33,    0,
    0,  146,    0,    0,    0,    0,    0,    0,   44,   44,
   44,   44,   44,   44,    0,    0,    0,   33,   33,   33,
   33,   33,   33,    0,    0,    0,    0,   34,   34,   34,
   34,    0,    0,   34,   34,    0,   36,   36,   36,   36,
    0,    0,   36,   36,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   34,   34,   34,   34,   34,   34,    0,
    0,    0,   36,   36,   36,   36,   36,   36,    0,    0,
    0,    0,   32,   32,   32,   32,    0,   89,   32,   32,
   35,   35,   35,   35,   91,    0,   35,   35,    0,   90,
    0,    0,    0,    0,    0,    0,    0,    0,   32,   32,
   32,   32,   32,   32,    0,    0,   35,   35,   35,   35,
   35,   35,   42,    0,    0,   42,    0,    0,    0,    0,
   40,    0,    0,   40,    0,    0,    0,    0,    0,   42,
   42,   42,   42,   42,   42,    0,    0,   40,   40,   40,
   40,   40,   40,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   45,    0,    0,   45,
    0,    0,    0,    0,   42,   41,    0,  117,   41,    0,
    0,    0,   40,   45,   45,   45,   45,   45,   45,    0,
    0,    0,   41,   41,   41,   41,   41,   41,    0,    0,
    0,    0,    0,   49,    0,    0,   49,    0,    0,    0,
    0,   43,    0,    0,   43,    0,    0,    0,   45,    0,
   49,   49,    0,   49,    0,   49,    0,   41,   43,   43,
   43,   43,   43,   43,    0,    0,    0,    0,   89,    0,
    0,    0,    0,    0,    0,   91,    0,    0,    0,    0,
   90,    0,    0,    0,   17,   49,    0,    0,    0,    0,
    0,   17,    0,   43,    0,    0,   17,   46,    0,    0,
   46,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   47,    0,    0,   47,   46,   46,    0,   46,    0,   46,
    0,    0,    0,    0,    0,    0,    0,   47,   47,    0,
   47,    0,   47,    0,    0,    0,    0,    0,    0,    0,
    0,   77,   78,   79,    0,    0,    0,    0,    0,   46,
    0,    0,   80,   81,   82,    0,    0,    0,    0,    0,
  191,   83,   47,   84,   85,   86,   87,   88,    0,    0,
    0,   42,   42,   42,   42,    0,   17,   42,   42,   40,
   40,   40,   40,    0,    0,   40,   40,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   42,   42,   42,
   42,   42,   42,    0,    0,   40,   40,   40,   40,   40,
   40,    0,    0,    0,    0,   45,   45,   45,   45,    0,
    0,   45,   45,    0,   41,   41,   41,   41,    0,    0,
   41,   41,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   45,   45,   45,   45,   45,   45,    0,    0,    0,
   41,   41,   41,   41,   41,   41,    0,    0,   49,   49,
   43,   43,   43,   43,    0,    0,   43,   43,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   49,   49,
   49,   49,   49,   49,    0,    0,   43,   43,   43,   43,
   43,   43,   77,   78,   79,    0,    0,    0,    0,    0,
    0,    0,    0,   80,   81,   82,    0,    0,   17,   17,
   17,    0,   83,    0,   84,   85,   86,   87,   88,   17,
   17,   17,   46,   46,    0,    0,    0,    0,   17,    0,
   17,   17,   17,   17,   17,   47,   47,    0,    0,    0,
    0,    0,   46,   46,   46,   46,   46,   46,    0,    0,
    0,    0,    0,    0,    0,   47,   47,   47,   47,   47,
   47,  111,  111,    0,    0,  115,    0,    0,    0,  121,
  122,  124,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  111,
    0,    0,    0,  156,  157,    0,    0,    0,    0,    0,
  161,    0,    0,    0,    0,  164,  165,  166,  167,  168,
  169,    0,    0,  170,  171,  172,  173,  174,  175,  176,
  177,  178,  179,  180,  181,  182,  183,  184,  185,    0,
    0,    0,    0,    0,    0,  187,    0,    0,  190,    0,
    0,    0,  194,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  200,    0,    0,  202,    0,    0,    0,  204,
    0,    0,    0,    0,    0,    0,    0,  213,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   91,   91,   91,   37,   91,   40,   46,   91,   42,
   43,   45,   45,   46,   47,  125,   37,  123,   82,  268,
   91,   42,   43,  272,   45,   46,   47,   60,   33,   62,
   46,   40,  117,   37,   41,   40,  284,  285,   42,  257,
   45,   72,   46,   47,  125,  109,  125,  273,  274,  275,
  276,   41,   91,  257,   44,   40,   37,   44,   91,   41,
   41,   42,   43,   44,   45,   46,   47,  125,   58,   59,
   91,   61,   59,   44,   40,   91,  257,   58,   59,   60,
   61,   62,   63,   15,   16,  116,   41,   91,   59,   44,
   28,  125,   37,   31,   41,   93,   41,   42,   43,   44,
   45,   37,   47,   93,   59,   41,   42,   43,   44,   45,
   91,   47,   93,   58,   59,   60,   61,   62,   63,   44,
   58,  268,   58,   59,   60,   61,   62,   63,  159,  214,
   93,   93,   41,  218,   59,   44,   28,   37,   44,   31,
  123,   41,   42,   43,   44,   45,   37,   47,   93,   58,
   59,   42,   43,   59,   45,   46,   47,   93,   58,   59,
   60,   61,   62,   63,   41,  123,   58,   44,   59,   60,
   61,   62,   63,   41,  284,  285,   44,  268,  268,  268,
  268,  123,  268,  123,   93,  268,   37,   40,  219,  220,
   41,   42,   43,   93,   45,   46,   47,  268,   41,  123,
   91,   44,  125,  284,  285,  284,  285,  125,  125,   60,
   61,   62,   63,  125,  125,  273,  274,  275,  276,   41,
   40,   40,   44,  257,  258,  259,   40,  260,  261,  262,
  263,   40,   59,   59,  268,  269,  270,   59,   59,   44,
   91,  279,   41,  277,   40,  279,  280,  281,  282,  283,
  284,  285,  257,  258,  259,  294,  123,   41,  292,  278,
   58,  294,   59,  268,  269,  270,  273,  274,  275,  276,
   59,  125,  277,  294,  279,  280,  281,  282,  283,  260,
  261,  262,  263,   59,   59,  266,  267,  292,    3,   31,
  294,  273,  274,  275,  276,   19,  286,  287,  288,  289,
  290,  291,   58,  199,  209,  286,  287,  288,  289,  290,
  291,   -1,  220,  294,   -1,  260,  261,  262,  263,   -1,
    3,  266,  267,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,   -1,  188,  189,   -1,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   30,  203,  294,
  286,  287,  288,  289,  290,  291,   -1,  212,  294,   -1,
  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,  260,
  261,  262,  263,   33,   -1,  266,  267,   -1,   -1,   -1,
   40,   -1,   65,   -1,   -1,   45,  286,  287,  288,  289,
  290,  291,   -1,   -1,  294,  286,  287,  288,  289,  290,
  291,   -1,   41,  294,   -1,   44,   33,   -1,   91,  260,
  261,  262,  263,   40,   41,  266,  267,   -1,   45,   58,
   59,   37,   -1,   61,   62,   41,   42,   43,   66,   45,
   46,   47,   70,   -1,   -1,  286,  287,  288,  289,  290,
  291,   -1,   -1,  294,   60,   61,   62,   63,   -1,   -1,
   -1,   -1,   41,   -1,   93,   44,   37,   -1,   -1,   -1,
   41,   42,   43,   -1,   45,   46,   47,   -1,   37,   58,
   59,   -1,   41,   42,   43,   91,   45,   46,   47,   60,
   61,   62,   63,   -1,   41,   -1,   -1,   44,   -1,   -1,
   -1,   60,   61,   62,   63,   -1,   -1,   41,   -1,   -1,
   44,   58,   59,   37,   93,   -1,   -1,   -1,   42,   43,
   91,   45,   46,   47,   58,   59,  271,   -1,  273,  274,
  275,  276,   91,   33,   58,   -1,   60,   61,   62,   63,
   40,   -1,   -1,   -1,   -1,   45,   93,   -1,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   93,
   37,   -1,   -1,   -1,   -1,   42,   43,   91,   45,   46,
   47,   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   59,   60,   61,   62,   63,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   93,   -1,   -1,  257,  258,  259,
   -1,   37,   -1,   -1,   91,   41,   42,   43,  268,   45,
   46,   47,   37,  273,  274,  275,   -1,   42,   43,   -1,
   45,   46,   47,   -1,   60,   61,   62,   63,   -1,   -1,
  257,  258,  259,   58,   -1,   60,   61,   62,   63,   -1,
   -1,  268,   -1,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,
  286,  287,  288,  289,  290,  291,   -1,   -1,  294,  260,
  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,   -1,
   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,
   -1,   -1,   -1,   -1,   -1,  286,  287,  288,  289,  290,
  291,   -1,   -1,  294,   -1,   -1,   -1,  286,  287,  288,
  289,  290,  291,   -1,   -1,  294,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  257,  258,  259,
   -1,   -1,  286,  287,  288,  289,  290,  291,  268,   -1,
  294,  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,
   -1,   -1,   -1,  260,  261,  262,  263,   -1,   -1,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,  288,
  289,  290,  291,   -1,   -1,  294,   -1,   -1,   -1,  286,
  287,  288,  289,  290,  291,   -1,   -1,  294,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,   33,   -1,   -1,  260,  261,  262,  263,   40,
   -1,  266,  267,   -1,   45,   -1,   -1,   -1,   -1,   -1,
  286,  287,  288,  289,  290,  291,   -1,   -1,  294,   -1,
   -1,  286,  287,  288,  289,  290,  291,   37,   -1,  294,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   91,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   91,   41,   -1,   -1,   44,   60,   37,   62,
   63,   -1,   41,   42,   43,   44,   45,   -1,   47,   -1,
   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,   58,
   59,   60,   61,   62,   63,   -1,   -1,   -1,   91,   -1,
   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,   41,   42,
   43,   44,   45,   37,   47,   93,   -1,   41,   42,   43,
   44,   45,   -1,   47,   93,   58,   59,   60,   61,   62,
   63,   -1,   -1,   -1,   58,   59,   60,   61,   62,   63,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   43,   44,   45,   -1,   -1,
   93,   41,   -1,   43,   44,   45,   -1,   -1,   -1,   93,
   58,   59,   60,   61,   62,   63,   -1,   -1,   58,   59,
   60,   61,   62,   63,   -1,   -1,  257,  258,  259,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  268,  269,  270,
   -1,   -1,   -1,   -1,   -1,   93,  277,   -1,  279,  280,
  281,  282,  283,   93,   -1,   -1,   -1,   -1,   -1,   -1,
  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,
   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,  288,  289,
  290,  291,   -1,   -1,  294,   -1,   -1,  286,  287,  288,
  289,  290,  291,   -1,   -1,  294,   -1,  260,  261,  262,
  263,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,
   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,
   -1,  294,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,   -1,  286,  287,  288,
  289,  290,  291,   -1,   -1,   -1,   -1,  260,  261,  262,
  263,   -1,   -1,  266,  267,   -1,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,
   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,   -1,   33,  266,  267,
  260,  261,  262,  263,   40,   -1,  266,  267,   -1,   45,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,  286,  287,  288,  289,
  290,  291,   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   58,
   59,   60,   61,   62,   63,   -1,   -1,   58,   59,   60,
   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   -1,   -1,   -1,   93,   41,   -1,  123,   44,   -1,
   -1,   -1,   93,   58,   59,   60,   61,   62,   63,   -1,
   -1,   -1,   58,   59,   60,   61,   62,   63,   -1,   -1,
   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,   41,   -1,   -1,   44,   -1,   -1,   -1,   93,   -1,
   58,   59,   -1,   61,   -1,   63,   -1,   93,   58,   59,
   60,   61,   62,   63,   -1,   -1,   -1,   -1,   33,   -1,
   -1,   -1,   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,
   45,   -1,   -1,   -1,   33,   93,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   93,   -1,   -1,   45,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   -1,   44,   58,   59,   -1,   61,   -1,   63,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   58,   59,   -1,
   61,   -1,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,   -1,   -1,   -1,   -1,   -1,   93,
   -1,   -1,  268,  269,  270,   -1,   -1,   -1,   -1,   -1,
  125,  277,   93,  279,  280,  281,  282,  283,   -1,   -1,
   -1,  260,  261,  262,  263,   -1,  125,  266,  267,  260,
  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,  288,
  289,  290,  291,   -1,   -1,  286,  287,  288,  289,  290,
  291,   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,
   -1,  266,  267,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,   -1,
  286,  287,  288,  289,  290,  291,   -1,   -1,  266,  267,
  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,  286,  287,  288,  289,
  290,  291,  257,  258,  259,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  268,  269,  270,   -1,   -1,  257,  258,
  259,   -1,  277,   -1,  279,  280,  281,  282,  283,  268,
  269,  270,  266,  267,   -1,   -1,   -1,   -1,  277,   -1,
  279,  280,  281,  282,  283,  266,  267,   -1,   -1,   -1,
   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  286,  287,  288,  289,  290,
  291,   81,   82,   -1,   -1,   85,   -1,   -1,   -1,   89,
   90,   91,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  109,
   -1,   -1,   -1,  113,  114,   -1,   -1,   -1,   -1,   -1,
  120,   -1,   -1,   -1,   -1,  125,  126,  127,  128,  129,
  130,   -1,   -1,  133,  134,  135,  136,  137,  138,  139,
  140,  141,  142,  143,  144,  145,  146,  147,  148,   -1,
   -1,   -1,   -1,   -1,   -1,  155,   -1,   -1,  158,   -1,
   -1,   -1,  162,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  192,   -1,   -1,  195,   -1,   -1,   -1,  199,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  207,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=296;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,"'%'",null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'","'/'",null,null,null,null,null,null,null,null,null,null,"':'",
"';'","'<'","'='","'>'","'?'",null,null,null,null,null,null,null,null,null,null,
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
"REAL_CONSTANT","LEQ","GEQ","DOUBLEEQUALS","DIFFERENT","SHIFTLEFT","SHIFTRIGHT",
"AND","OR","ID","WRITE","READ","VOID","MAIN","CHAR","DOUBLE","INT","STRUCT",
"IF","ELSE","WHILE","RETURN","FOR","DO","SWITCH","CASE","DEFAULT","INC","DEC",
"DIVIDEEQUALS","TIMESEQUALS","MINUSEQUALS","PLUSEQUALS","BREAK","IFX",
"POTENTIAL","\"unary_minus\"","CAST",
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
"parameter : struct ID",
"parameter : array_creation ID",
"statement : expression ';'",
"statement : read ';'",
"statement : write ';'",
"statement : if_else",
"statement : while",
"statement : for",
"statement : switch",
"statement : do_while",
"statement : return ';'",
"expression : expression '+' expression",
"expression : expression '*' expression",
"expression : expression '/' expression",
"expression : expression '-' expression",
"expression : expression '%' expression",
"expression : assignment",
"expression : ternary",
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
"if_else : IF '(' expression ')' body ELSE body",
"if_else : IF '(' expression ')' body",
"ternary : expression '?' expression ':' expression",
"body : '{' listOfStatements '}'",
"body : statement",
"while : WHILE '(' expression ')' body",
"do_while : DO body WHILE '(' expression ')'",
"for : FOR '(' statement expression ';' assignment ')' body",
"switch : SWITCH '(' expression ')' '{' switch_body '}'",
"switch_body : switch_body case",
"switch_body : case",
"case : CASE expression ':' listOfStatements break",
"case : CASE expression ':' listOfStatements",
"case : DEFAULT ':' listOfStatements break",
"case : DEFAULT ':' listOfStatements",
"break : BREAK ';'",
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
"assignment : expression INC",
"assignment : expression DEC",
"assignment : expression PLUSEQUALS expression",
"assignment : expression MINUSEQUALS expression",
"assignment : expression TIMESEQUALS expression",
"assignment : expression DIVIDEEQUALS expression",
"read : READ listOfExpressions",
"write : WRITE listOfExpressions",
"struct : STRUCT '{' listOfVariables '}'",
"return : RETURN expression",
};

//#line 393 "../../src/parser/parser.y"

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
//#line 801 "Parser.java"
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
//#line 51 "../../src/parser/parser.y"
{ this.ast = new Program(scanner.getLine(), scanner.getColumn()
															, (List<Definition>)val_peek(0)); }
break;
case 2:
//#line 54 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>)val_peek(1);
														defList.add((FunctionDefinition) val_peek(0));
	   													yyval = defList; }
break;
case 3:
//#line 59 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														for(Definition def : (List<Definition>)val_peek(0)){
															defList.add(def);
														}
														yyval = defList; }
break;
case 4:
//#line 64 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														defList.add((Definition)val_peek(0));
														yyval = defList; }
break;
case 5:
//#line 67 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 6:
//#line 69 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 7:
//#line 70 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 8:
//#line 73 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	,(List<Expression>) val_peek(1) , new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(3))); }
break;
case 9:
//#line 76 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	, new ArrayList<Expression>(), new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(2))); }
break;
case 10:
//#line 82 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(6),
																			  		  (List<VariableDefinition>) val_peek(4), (Type) val_peek(7))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 11:
//#line 87 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(5),
																			  		  new ArrayList<VariableDefinition>(), (Type) val_peek(6))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 12:
//#line 96 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(6),
																			  (List<VariableDefinition>) val_peek(4), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 13:
//#line 101 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 14:
//#line 108 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
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
//#line 141 "../../src/parser/parser.y"
{ yyval = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) val_peek(0), (Type) val_peek(1)); }
break;
case 22:
//#line 143 "../../src/parser/parser.y"
{ yyval = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) val_peek(0), (Type) val_peek(1)); }
break;
case 23:
//#line 147 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 24:
//#line 148 "../../src/parser/parser.y"
{ yyval = (Read) val_peek(1); }
break;
case 25:
//#line 149 "../../src/parser/parser.y"
{ yyval = (Write) val_peek(1); }
break;
case 26:
//#line 150 "../../src/parser/parser.y"
{ yyval = (IfStatement) val_peek(0); }
break;
case 27:
//#line 151 "../../src/parser/parser.y"
{ yyval = (WhileStatement) val_peek(0); }
break;
case 28:
//#line 152 "../../src/parser/parser.y"
{ yyval = (ForStatement) val_peek(0); }
break;
case 29:
//#line 153 "../../src/parser/parser.y"
{ yyval = (SwitchCase) val_peek(0); }
break;
case 30:
//#line 154 "../../src/parser/parser.y"
{ yyval = (DoWhileStatement) val_peek(0); }
break;
case 31:
//#line 155 "../../src/parser/parser.y"
{ yyval = (Return) val_peek(1); }
break;
case 32:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"+",(Expression) val_peek(0)); }
break;
case 33:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"*",(Expression) val_peek(0)); }
break;
case 34:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"/",(Expression) val_peek(0)); }
break;
case 35:
//#line 164 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"-",(Expression) val_peek(0)); }
break;
case 36:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"%",(Expression) val_peek(0)); }
break;
case 37:
//#line 168 "../../src/parser/parser.y"
{ yyval = (Assignment)val_peek(0); }
break;
case 38:
//#line 170 "../../src/parser/parser.y"
{ yyval = (Ternary) val_peek(0); }
break;
case 39:
//#line 172 "../../src/parser/parser.y"
{ yyval = new Power(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 40:
//#line 174 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"==",(Expression) val_peek(0)); }
break;
case 41:
//#line 176 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">",(Expression) val_peek(0)); }
break;
case 42:
//#line 178 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">=",(Expression) val_peek(0)); }
break;
case 43:
//#line 180 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<",(Expression) val_peek(0)); }
break;
case 44:
//#line 182 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<=",(Expression) val_peek(0)); }
break;
case 45:
//#line 184 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"!=",(Expression) val_peek(0)); }
break;
case 46:
//#line 186 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"&&",(Expression) val_peek(0)); }
break;
case 47:
//#line 188 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"||",(Expression) val_peek(0)); }
break;
case 48:
//#line 190 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 49:
//#line 192 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 50:
//#line 194 "../../src/parser/parser.y"
{ yyval = (Invocation) val_peek(0); }
break;
case 51:
//#line 195 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 52:
//#line 196 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn()
          																, (Expression) val_peek(2), ((Variable) val_peek(0)).getName()); }
break;
case 53:
//#line 198 "../../src/parser/parser.y"
{ yyval = (Cast) val_peek(0); }
break;
case 54:
//#line 199 "../../src/parser/parser.y"
{ yyval = (Indexing) val_peek(0); }
break;
case 55:
//#line 200 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn()
          																, (Integer) val_peek(0)); }
break;
case 56:
//#line 202 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn()
          																, (Double) val_peek(0)); }
break;
case 57:
//#line 204 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn()
          																, (Character) val_peek(0)); }
break;
case 58:
//#line 206 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn()
          																, (String) val_peek(0)); }
break;
case 59:
//#line 210 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(2), (List<Statement>) val_peek(0)
																		, (Expression) val_peek(4)); }
break;
case 60:
//#line 213 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(0), new ArrayList<Statement>()
																		, (Expression) val_peek(2)); }
break;
case 61:
//#line 218 "../../src/parser/parser.y"
{ yyval = new Ternary(scanner.getLine(), scanner.getColumn()
																		,(Expression) val_peek(4), (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 62:
//#line 221 "../../src/parser/parser.y"
{ yyval = (List<Statement>)val_peek(1); }
break;
case 63:
//#line 222 "../../src/parser/parser.y"
{ List<Statement> list = new ArrayList<Statement>();
															  list.add((Statement)val_peek(0));
															  yyval = list; }
break;
case 64:
//#line 227 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																	  yyval = new WhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(2)); }
break;
case 65:
//#line 232 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(4));
																	  yyval = new DoWhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(1)); }
break;
case 66:
//#line 237 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																			  yyval = new ForStatement(scanner.getLine(), scanner.getColumn()
																			       , (Statement) val_peek(5), (Expression) val_peek(4), (Assignment) val_peek(2), body); }
break;
case 67:
//#line 242 "../../src/parser/parser.y"
{ List<Case> cases = (List<Case>)val_peek(1);
																	  yyval = new SwitchCase(scanner.getLine(), scanner.getColumn()
																	  		, (Expression)val_peek(4), cases); }
break;
case 68:
//#line 248 "../../src/parser/parser.y"
{ List<Case> cases = (List<Case>)val_peek(1);
																	  cases.add((Case)val_peek(0));
																	  yyval = cases; }
break;
case 69:
//#line 251 "../../src/parser/parser.y"
{ List<Case> cases = new ArrayList<>();
																	  cases.add((Case)val_peek(0));
																	  yyval = cases; }
break;
case 70:
//#line 256 "../../src/parser/parser.y"
{ List<Statement> stms = (List<Statement>)val_peek(1);
																	  stms.add((BreakInstruction) val_peek(0));
																	  yyval = new NormalCase(scanner.getLine(), scanner.getColumn()
																			, (Expression)val_peek(3), stms); }
break;
case 71:
//#line 260 "../../src/parser/parser.y"
{ yyval = new NormalCase(scanner.getLine(), scanner.getColumn()
																			, (Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 72:
//#line 262 "../../src/parser/parser.y"
{ List<Statement> stms = (List<Statement>)val_peek(1);
																	  stms.add((BreakInstruction) val_peek(0));
																	  yyval = new DefaultCase(scanner.getLine(), scanner.getColumn()
																			, stms); }
break;
case 73:
//#line 266 "../../src/parser/parser.y"
{ yyval = new DefaultCase(scanner.getLine(), scanner.getColumn()
																			, (List<Statement>)val_peek(0)); }
break;
case 74:
//#line 270 "../../src/parser/parser.y"
{ yyval = new BreakInstruction(scanner.getLine(), scanner.getColumn()); }
break;
case 75:
//#line 273 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn()
          																	,(Type) val_peek(2), (Expression) val_peek(0)); }
break;
case 76:
//#line 277 "../../src/parser/parser.y"
{ List<Expression> list = (List<Expression>) val_peek(2);
																	  list.add((Expression) val_peek(0));
																	  yyval = list; }
break;
case 77:
//#line 280 "../../src/parser/parser.y"
{ List<Expression> list = new ArrayList<>();
				 													  list.add((Expression)val_peek(0));
				 													  yyval = list; }
break;
case 78:
//#line 285 "../../src/parser/parser.y"
{ List<String> list = (List<String>) val_peek(2);
																	  if(list.contains((String)val_peek(0)))
																	  	new ErrorType(scanner.getLine(), scanner.getColumn()
																	  			, "The variable "+((String)val_peek(0))
																	  			+" has been already declared");
																	  list.add((String)val_peek(0));
																	  yyval = list; }
break;
case 79:
//#line 292 "../../src/parser/parser.y"
{ List<String> list = new ArrayList<String>();
		 															  list.add((String) val_peek(0));
		 															  yyval = list; }
break;
case 80:
//#line 297 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 81:
//#line 298 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 82:
//#line 299 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 83:
//#line 302 "../../src/parser/parser.y"
{yyval = new Indexing(scanner.getLine()
																, scanner.getColumn(), (Expression)val_peek(3)
																,(Expression)val_peek(1));}
break;
case 84:
//#line 307 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 85:
//#line 310 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 86:
//#line 313 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 87:
//#line 318 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 88:
//#line 323 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 89:
//#line 328 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 90:
//#line 335 "../../src/parser/parser.y"
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
case 91:
//#line 344 "../../src/parser/parser.y"
{ yyval = new ArrayList<VariableDefinition>(); }
break;
case 92:
//#line 347 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
																		, (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 93:
//#line 349 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"+"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 94:
//#line 353 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"-"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 95:
//#line 357 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"+",(Expression)val_peek(0))); }
break;
case 96:
//#line 360 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"-",(Expression)val_peek(0))); }
break;
case 97:
//#line 363 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"*",(Expression)val_peek(0))); }
break;
case 98:
//#line 366 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"/",(Expression)val_peek(0))); }
break;
case 99:
//#line 371 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 100:
//#line 374 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 101:
//#line 377 "../../src/parser/parser.y"
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
case 102:
//#line 388 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(0)); }
break;
//#line 1520 "Parser.java"
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
