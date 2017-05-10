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
public final static short unary_minus=294;
public final static short CAST=295;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    2,    5,    5,    8,    8,    6,
    6,    7,    7,    3,   12,   14,   14,   11,   11,   16,
   16,   16,   15,   15,   15,   15,   15,   15,   15,   15,
   15,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   19,   19,   19,
   19,   19,   19,   19,   19,   19,   19,   22,   22,   29,
   32,   32,   23,   26,   24,   25,   33,   33,   34,   34,
   34,   34,   35,   30,    9,    9,   36,   36,   10,   10,
   10,   31,   18,   18,   18,    4,    4,    4,   13,   13,
   28,   28,   28,   28,   28,   28,   28,   20,   21,   17,
   27,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    1,    1,    4,    3,    8,
    7,    8,    7,    7,    2,    2,    0,    3,    1,    2,
    2,    2,    2,    2,    2,    1,    1,    1,    1,    1,
    2,    3,    3,    3,    3,    3,    1,    1,    3,    3,
    3,    3,    3,    3,    3,    3,    2,    2,    1,    3,
    3,    1,    1,    1,    1,    1,    1,    7,    5,    5,
    3,    1,    5,    6,    8,    7,    2,    1,    5,    4,
    4,    3,    2,    4,    3,    1,    3,    1,    1,    1,
    1,    4,    4,    4,    4,    3,    3,    3,    2,    0,
    3,    2,    2,    3,    3,    3,    3,    2,    2,    4,
    2,
};
final static short yydefred[] = {                         5,
    0,    1,    0,    0,   79,   81,   80,    0,    2,    3,
    4,    6,    7,    0,    0,    0,    0,    0,   90,    0,
    0,    0,   78,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   86,    0,   88,    0,   87,    0,    0,
    0,   19,    0,    0,    0,  100,   89,    0,    0,    0,
   84,   77,   85,   83,   90,   20,    0,    0,   21,   22,
   90,   90,    0,    0,    0,   90,   18,    0,    0,   90,
   13,    0,    0,   14,   11,    0,   54,   56,   55,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   49,   16,    0,    0,    0,   26,   27,   28,   29,
   30,    0,   37,   38,   52,   53,   12,   10,    0,    0,
    0,    0,    0,    0,    0,    0,   17,   62,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   92,   93,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   23,   24,   25,
   31,    9,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   50,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   51,    8,    0,    0,    0,    0,   61,    0,
    0,    0,    0,   82,    0,   63,    0,    0,    0,    0,
    0,    0,    0,   64,    0,    0,    0,   68,   58,    0,
    0,   17,   66,   67,   65,   17,    0,    0,    0,   71,
   69,   73,
};
final static short yydgoto[] = {                          1,
    2,    3,    9,   47,   11,   12,   13,   92,  110,   40,
   41,   64,   65,   72,  118,   42,   15,   16,   94,   95,
   96,   97,   98,   99,  100,  101,  102,  103,  104,  105,
  106,  119,  207,  208,  220,   22,
};
final static short yysindex[] = {                         0,
    0,    0,  144, -222,    0,    0,    0, -112,    0,    0,
    0,    0,    0,  -90,  -89,  -88,  -20,   -9,    0,    4,
 -231,  -14,    0, -208,   31, -206,   78,  -13,   15,  -78,
   19,  -35, -180,    0,    3,    0,   11,    0,  -18,  -87,
   89,    0,  -86,  -83,   -2,    0,    0,  -82,    6,   90,
    0,    0,    0,    0,    0,    0,   16,  -45,    0,    0,
    0,    0,   17,   42,  -45,    0,    0,   59,   67,    0,
    0, 1095,   75,    0,    0,   76,    0,    0,    0,  116,
  251,  251,  174,  178,  251,  181, 1064,  182,  251,  251,
  187,    0,    0,  379,   82,  164,    0,    0,    0,    0,
    0,  175,    0,    0,    0,    0,    0,    0,   44,  189,
  860,  189,  251,  251,  860, 1095,    0,    0,  -41,  251,
  893,  -36,  199,  411,  251,  251,  251,  251,  251,  251,
    0,    0,  251,  251,  251,  251,  251,  251,  251,  251,
  251,  251,  251,  251,  251,  251,  251,    0,    0,    0,
    0,    0,  172,  251,  422,  454,  251, 1137,  201,  486,
  251,    0,  292,  292,  292,  292,  893,  893,  860,  860,
  860,  860,  860,  497,  292,  292,  -23,  -23,  -36,  -36,
  -36,  529,    0,    0,  860, 1064, 1064,  564,    0,  251,
  119,  -36,  251,    0,  -21,    0,  251,  796, -251, 1448,
 1064,  860,  202,    0,  251,  200, -119,    0,    0, 1064,
  828,    0,    0,    0,    0,    0,   -4,   -4,  186,    0,
    0,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   91,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0, 1189,    0,    0,    0,    0,    0,
    0,  160,    0,    0,    0,    0,    0,    0,    0,   20,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  215,
   32,  230,    0,    0,  231,    0,    0,    0,    0,    0,
 1329,   56,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  949,  958, 1006, 1216, 1339, 1367,   -6,  256,
  445,  477,  509,    0, 1257, 1266,  905,  913,   65,  101,
  110,    0,    0,    0,  158,    0,    0,    0,    0,    0,
    0,  146,    0,    0,  -33,    0,    0,    0,    0,  -19,
    0,    0,  871,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0, -110, -108,    0,    0,
    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  296,    0,    0,    0,    0,  -55,   29,
  273,  149,  286,  -80,  140,  255,  -15,  -10, 1559,    0,
    0,    0,    0,    0,    0,    0,    0,  123,    0,    0,
    0, -115,    0,  114,  106,   54,
};
final static int YYTABLESIZE=1764;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         59,
   21,   24,   26,   21,   24,  213,   59,   26,   21,  147,
   19,   59,   43,  145,   72,   43,   70,   44,  143,   28,
   44,   60,  147,  144,   60,   32,  112,   39,   89,   33,
   29,   14,  205,  206,   97,   91,  158,   97,   60,   60,
   90,   60,   43,   31,   34,   17,   46,   44,   35,   18,
   37,   97,   97,  153,  146,   45,   57,   51,   48,   49,
   57,   57,   57,   57,   57,   57,   57,  146,   25,   27,
  195,  196,   76,   60,   33,   76,   89,   57,   57,   57,
   57,   57,   57,   91,  152,  209,   97,   52,   90,   36,
   76,   59,   47,   48,  215,   53,   47,   47,   47,   47,
   47,   33,   47,   54,   55,   33,   33,   33,   33,   33,
   57,   33,   57,   47,   47,   47,   47,   47,   47,  123,
   61,   33,   33,   33,   33,   33,   33,   33,   62,   57,
   63,  217,   58,   58,   78,  218,   38,   34,   66,   70,
  149,   34,   34,   34,   34,   34,   36,   34,   47,   78,
   36,   36,   36,   36,   36,  109,   36,   33,   34,   34,
   34,   34,   34,   34,  205,  206,   71,   36,   36,   36,
   36,   36,   36,   72,   72,   70,   70,   20,   23,   23,
   56,   59,   74,   74,   60,   23,   74,   74,   74,   74,
   74,   75,   74,   34,    5,    6,    7,    8,   75,  107,
  108,   75,   36,   74,   74,   74,   74,   74,   74,   68,
   69,   93,  184,  113,   73,  154,   75,  114,   76,   89,
  116,  120,  150,   59,   59,   59,   91,    5,    6,    7,
    8,   90,  154,  151,   59,   59,   59,  159,   74,  161,
  190,  199,  210,   59,  222,   59,   59,   59,   59,   59,
   59,   59,   77,   78,   79,  157,  201,  212,   59,    5,
    6,    7,    8,   80,   81,   82,   60,   60,   60,   60,
   60,   60,   83,   99,   84,   85,   86,   87,   88,   57,
   57,   57,   57,   89,   15,   57,   57,  219,   98,  101,
   91,    5,    6,    7,    8,   90,   96,   93,   10,   96,
   77,   78,   79,   50,   30,   57,   57,   57,   57,   57,
   57,   80,   67,   96,   96,   47,   47,   47,   47,  203,
  214,   47,   47,  221,   33,   33,   33,   33,  145,    0,
   33,   33,    0,  143,  141,    0,  142,  147,  144,    0,
    0,   47,   47,   47,   47,   47,   47,    0,   96,    0,
   33,   33,   33,   33,   33,   33,   93,   93,    0,    0,
   34,   34,   34,   34,    0,    0,   34,   34,    0,   36,
   36,   36,   36,    0,    0,   36,   36,    0,    0,    0,
    0,    0,  146,    0,    0,    0,   34,   34,   34,   34,
   34,   34,    0,    0,    0,   36,   36,   36,   36,   36,
   36,    0,    0,    0,    0,   74,   74,   74,   74,    0,
    0,   74,   74,    0,    4,  145,    5,    6,    7,    8,
  143,  141,    0,  142,  147,  144,    0,    0,    0,    0,
    0,   74,   74,   74,   74,   74,   74,  148,  140,  137,
  139,  138,    0,   77,   78,   79,    0,  145,    0,    0,
    0,  162,  143,  141,   80,  142,  147,  144,  145,    5,
    6,    7,  186,  143,  141,    0,  142,  147,  144,  146,
  140,  137,  139,  138,    0,    0,    0,    0,    0,    0,
    0,  140,  137,  139,  138,   95,    0,    0,   95,    0,
  145,    0,    0,    0,  187,  143,  141,    0,  142,  147,
  144,  146,   95,   95,    0,    0,    0,   77,   78,   79,
    0,    0,  146,  140,  137,  139,  138,   94,   80,    0,
   94,    0,  145,    0,    0,    0,  191,  143,  141,    0,
  142,  147,  144,  145,   94,   94,    0,   95,  143,  141,
    0,  142,  147,  144,  146,  140,  137,  139,  138,   91,
    0,    0,   91,    0,  193,    0,  140,  137,  139,  138,
    0,    0,    0,    0,    0,  145,   91,   91,    0,   94,
  143,  141,    0,  142,  147,  144,  146,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  146,  140,  137,
  139,  138,    0,    0,    0,    0,    0,    0,    0,    0,
  145,   91,    0,    0,    0,  143,  141,    0,  142,  147,
  144,    0,    0,    0,    0,    0,    0,    0,    0,  146,
    0,  194,  197,  140,  137,  139,  138,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  125,  126,
  127,  128,    0,    0,  129,  130,    0,    0,    0,    0,
    0,    0,    0,    0,  146,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  131,  132,  133,  134,  135,  136,
  125,  126,  127,  128,    0,    0,  129,  130,    0,    0,
    0,  125,  126,  127,  128,    0,    0,  129,  130,    0,
    0,    0,    0,    0,    0,    0,  131,  132,  133,  134,
  135,  136,    0,    0,    0,    0,    0,  131,  132,  133,
  134,  135,  136,  125,  126,  127,  128,    0,    0,  129,
  130,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  131,
  132,  133,  134,  135,  136,  125,  126,  127,  128,    0,
    0,  129,  130,    0,    0,    0,  125,  126,  127,  128,
    0,    0,  129,  130,    0,    0,    0,    0,    0,    0,
    0,  131,  132,  133,  134,  135,  136,    0,    0,    0,
    0,    0,  131,  132,  133,  134,  135,  136,  125,  126,
  127,  128,    0,    0,  129,  130,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  131,  132,  133,  134,  135,  136,
    0,    0,    0,  125,  126,  127,  128,    0,    0,  129,
  130,    0,  145,    0,    0,    0,  204,  143,  141,    0,
  142,  147,  144,    0,    0,    0,    0,    0,    0,  131,
  132,  133,  134,  135,  136,  140,  137,  139,  138,    0,
    0,    0,    0,    0,  145,    0,    0,    0,    0,  143,
  141,    0,  142,  147,  144,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  216,  146,  140,  137,  139,
  138,    0,    0,    0,    0,    0,  145,    0,    0,    0,
    0,  143,  141,    0,  142,  147,  144,   37,    0,    0,
    0,    0,   37,   37,    0,   37,   37,   37,  146,  140,
  137,  139,  138,    0,    0,    0,    0,    0,    0,  145,
   37,   37,   37,   37,  143,  141,    0,  142,  147,  144,
    0,    0,    0,    0,    0,   32,    0,   32,   32,   32,
  146,    0,  140,   35,  139,   35,   35,   35,    0,    0,
    0,   37,   32,   32,   32,   32,   32,   32,    0,    0,
   35,   35,   35,   35,   35,   35,    0,    0,    0,    0,
    0,    0,    0,  146,    0,    0,    0,    0,    0,   43,
    0,    0,   43,    0,    0,    0,    0,   32,   41,    0,
    0,   41,    0,    0,    0,   35,   43,   43,   43,   43,
   43,   43,    0,    0,    0,   41,   41,   41,   41,   41,
   41,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   43,    0,    0,    0,    0,   39,    0,    0,   39,
   41,    0,    0,    0,    0,  125,  126,  127,  128,    0,
    0,  129,  130,   39,   39,   39,   39,   39,   39,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  131,  132,  133,  134,  135,  136,  125,  126,  127,
  128,    0,    0,  129,  130,    0,   89,    0,   39,    0,
    0,    0,    0,   91,    0,    0,    0,    0,   90,    0,
    0,    0,    0,  131,  132,  133,  134,  135,  136,  125,
  126,  127,  128,    0,    0,  129,  130,   89,    0,    0,
   37,   37,   37,   37,   91,    0,   37,   37,    0,   90,
    0,    0,    0,    0,    0,  131,  132,  133,  134,  135,
  136,    0,  125,  126,  127,  128,   37,   37,   37,   37,
   37,   37,    0,    0,   32,   32,   32,   32,    0,   89,
   32,   32,   35,   35,   35,   35,   91,    0,   35,   35,
    0,   90,    0,    0,    0,    0,  117,    0,    0,    0,
   32,   32,   32,   32,   32,   32,    0,    0,   35,   35,
   35,   35,   35,   35,    0,    0,    0,    0,   43,   43,
   43,   43,    0,    0,   43,   43,    0,   41,   41,   41,
   41,   17,    0,   41,   41,    0,    0,    0,   17,    0,
    0,    0,    0,   17,   43,   43,   43,   43,   43,   43,
    0,    0,    0,   41,   41,   41,   41,   41,   41,    0,
    0,    0,    0,    0,    0,    0,   44,    0,    0,   44,
    0,  189,    0,    0,    0,   39,   39,   39,   39,    0,
    0,   39,   39,   44,   44,   44,   44,   44,   44,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   39,   39,   39,   39,   39,   39,   40,    0,    0,
   40,    0,    0,    0,    0,    0,   42,    0,   44,   42,
    0,    0,    0,   17,   40,   40,   40,   40,   40,   40,
   77,   78,   79,   42,   42,   42,   42,   42,   42,    0,
    0,   80,   81,   82,    0,    0,    0,    0,    0,    0,
   83,    0,   84,   85,   86,   87,   88,    0,    0,   40,
    0,   77,   78,   79,    0,    0,    0,    0,   42,    0,
    0,    0,   80,   81,   82,    0,    0,    0,    0,   48,
    0,   83,   48,   84,   85,   86,   87,   88,    0,   45,
    0,    0,   45,    0,    0,    0,   48,   48,    0,   48,
    0,   48,    0,   77,   78,   79,   45,   45,    0,   45,
    0,   45,    0,    0,   80,   81,   82,   46,    0,    0,
   46,    0,    0,   83,    0,   84,   85,   86,   87,   88,
    0,   48,    0,    0,   46,   46,    0,   46,    0,   46,
    0,   45,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   17,   17,   17,    0,    0,
    0,    0,    0,    0,    0,    0,   17,   17,   17,   46,
    0,    0,    0,    0,    0,   17,    0,   17,   17,   17,
   17,   17,    0,    0,    0,   44,   44,   44,   44,    0,
    0,   44,   44,    0,  145,    0,    0,    0,    0,  143,
  141,    0,  142,  147,  144,    0,    0,    0,    0,    0,
    0,   44,   44,   44,   44,   44,   44,  140,    0,  139,
  138,    0,    0,    0,    0,    0,   40,   40,   40,   40,
    0,    0,   40,   40,    0,   42,   42,   42,   42,    0,
    0,   42,   42,    0,    0,    0,    0,    0,  146,    0,
    0,    0,   40,   40,   40,   40,   40,   40,    0,    0,
    0,   42,   42,   42,   42,   42,   42,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   48,   48,    0,    0,    0,    0,
    0,    0,    0,    0,   45,   45,    0,    0,    0,    0,
    0,    0,    0,    0,   48,   48,   48,   48,   48,   48,
    0,    0,    0,    0,   45,   45,   45,   45,   45,   45,
    0,    0,   46,   46,    0,    0,    0,    0,    0,  111,
  111,    0,    0,  115,    0,    0,    0,  121,  122,  124,
    0,    0,   46,   46,   46,   46,   46,   46,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  111,    0,    0,
    0,  155,  156,    0,    0,    0,    0,    0,  160,    0,
    0,    0,    0,  163,  164,  165,  166,  167,  168,    0,
    0,  169,  170,  171,  172,  173,  174,  175,  176,  177,
  178,  179,  180,  181,  182,  183,    0,  125,  126,  127,
  128,    0,  185,  129,  130,  188,    0,    0,    0,  192,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  198,    0,
    0,  200,    0,    0,    0,  202,    0,    0,    0,    0,
    0,    0,    0,  211,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   91,   91,   91,   91,   91,  125,   40,   91,   91,   46,
  123,   45,   28,   37,  125,   31,  125,   28,   42,   40,
   31,   41,   46,   47,   44,  257,   82,   41,   33,   44,
   40,    3,  284,  285,   41,   40,  117,   44,   58,   59,
   45,   61,   58,   40,   59,  268,  125,   58,  257,  272,
  257,   58,   59,  109,   91,   41,   37,   93,   30,   41,
   41,   42,   43,   44,   45,   46,   47,   91,   15,   16,
  186,  187,   41,   93,   44,   44,   33,   58,   59,   60,
   61,   62,   63,   40,   41,  201,   93,  268,   45,   59,
   59,  125,   37,   65,  210,   93,   41,   42,   43,   44,
   45,   37,   47,   93,  123,   41,   42,   43,   44,   45,
   91,   47,   93,   58,   59,   60,   61,   62,   63,   91,
  123,   44,   58,   59,   60,   61,   62,   63,  123,   41,
   41,  212,   44,   44,   44,  216,   59,   37,  123,  123,
   59,   41,   42,   43,   44,   45,   37,   47,   93,   59,
   41,   42,   43,   44,   45,   40,   47,   93,   58,   59,
   60,   61,   62,   63,  284,  285,  125,   58,   59,   60,
   61,   62,   63,  284,  285,  284,  285,  268,  268,  268,
  268,  268,   37,  125,  268,  268,   41,   42,   43,   44,
   45,  125,   47,   93,  273,  274,  275,  276,   41,  125,
  125,   44,   93,   58,   59,   60,   61,   62,   63,   61,
   62,   72,   41,   40,   66,   44,   59,   40,   70,   33,
   40,   40,   59,  257,  258,  259,   40,  273,  274,  275,
  276,   45,   44,   59,  268,  269,  270,  279,   93,   41,
   40,  123,   41,  277,   59,  279,  280,  281,  282,  283,
  284,  285,  257,  258,  259,  116,  278,   58,  292,  273,
  274,  275,  276,  268,  269,  270,  286,  287,  288,  289,
  290,  291,  277,   59,  279,  280,  281,  282,  283,  260,
  261,  262,  263,   33,  125,  266,  267,  292,   59,   59,
   40,  273,  274,  275,  276,   45,   41,  158,    3,   44,
  257,  258,  259,   31,   19,  286,  287,  288,  289,  290,
  291,  268,   58,   58,   59,  260,  261,  262,  263,  197,
  207,  266,  267,  218,  260,  261,  262,  263,   37,   -1,
  266,  267,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   -1,  286,  287,  288,  289,  290,  291,   -1,   93,   -1,
  286,  287,  288,  289,  290,  291,  217,  218,   -1,   -1,
  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,  260,
  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,   -1,
   -1,   -1,   91,   -1,   -1,   -1,  286,  287,  288,  289,
  290,  291,   -1,   -1,   -1,  286,  287,  288,  289,  290,
  291,   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,
   -1,  266,  267,   -1,  271,   37,  273,  274,  275,  276,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   59,   60,   61,
   62,   63,   -1,  257,  258,  259,   -1,   37,   -1,   -1,
   -1,   41,   42,   43,  268,   45,   46,   47,   37,  273,
  274,  275,   41,   42,   43,   -1,   45,   46,   47,   91,
   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   60,   61,   62,   63,   41,   -1,   -1,   44,   -1,
   37,   -1,   -1,   -1,   41,   42,   43,   -1,   45,   46,
   47,   91,   58,   59,   -1,   -1,   -1,  257,  258,  259,
   -1,   -1,   91,   60,   61,   62,   63,   41,  268,   -1,
   44,   -1,   37,   -1,   -1,   -1,   41,   42,   43,   -1,
   45,   46,   47,   37,   58,   59,   -1,   93,   42,   43,
   -1,   45,   46,   47,   91,   60,   61,   62,   63,   41,
   -1,   -1,   44,   -1,   58,   -1,   60,   61,   62,   63,
   -1,   -1,   -1,   -1,   -1,   37,   58,   59,   -1,   93,
   42,   43,   -1,   45,   46,   47,   91,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,   60,   61,
   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   37,   93,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   91,
   -1,   93,   59,   60,   61,   62,   63,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,
  262,  263,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,
  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,
   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  286,  287,  288,  289,
  290,  291,   -1,   -1,   -1,   -1,   -1,  286,  287,  288,
  289,  290,  291,  260,  261,  262,  263,   -1,   -1,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  286,
  287,  288,  289,  290,  291,  260,  261,  262,  263,   -1,
   -1,  266,  267,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,   -1,
   -1,   -1,  286,  287,  288,  289,  290,  291,  260,  261,
  262,  263,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,
   -1,   -1,   -1,  260,  261,  262,  263,   -1,   -1,  266,
  267,   -1,   37,   -1,   -1,   -1,   41,   42,   43,   -1,
   45,   46,   47,   -1,   -1,   -1,   -1,   -1,   -1,  286,
  287,  288,  289,  290,  291,   60,   61,   62,   63,   -1,
   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   58,   91,   60,   61,   62,
   63,   -1,   -1,   -1,   -1,   -1,   37,   -1,   -1,   -1,
   -1,   42,   43,   -1,   45,   46,   47,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   91,   60,
   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,   37,
   60,   61,   62,   63,   42,   43,   -1,   45,   46,   47,
   -1,   -1,   -1,   -1,   -1,   41,   -1,   43,   44,   45,
   91,   -1,   60,   41,   62,   43,   44,   45,   -1,   -1,
   -1,   91,   58,   59,   60,   61,   62,   63,   -1,   -1,
   58,   59,   60,   61,   62,   63,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   91,   -1,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,   -1,   -1,   -1,   -1,   93,   41,   -1,
   -1,   44,   -1,   -1,   -1,   93,   58,   59,   60,   61,
   62,   63,   -1,   -1,   -1,   58,   59,   60,   61,   62,
   63,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   93,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   93,   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,
   -1,  266,  267,   58,   59,   60,   61,   62,   63,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,  260,  261,  262,
  263,   -1,   -1,  266,  267,   -1,   33,   -1,   93,   -1,
   -1,   -1,   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,
   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,  260,
  261,  262,  263,   -1,   -1,  266,  267,   33,   -1,   -1,
  260,  261,  262,  263,   40,   -1,  266,  267,   -1,   45,
   -1,   -1,   -1,   -1,   -1,  286,  287,  288,  289,  290,
  291,   -1,  260,  261,  262,  263,  286,  287,  288,  289,
  290,  291,   -1,   -1,  260,  261,  262,  263,   -1,   33,
  266,  267,  260,  261,  262,  263,   40,   -1,  266,  267,
   -1,   45,   -1,   -1,   -1,   -1,  123,   -1,   -1,   -1,
  286,  287,  288,  289,  290,  291,   -1,   -1,  286,  287,
  288,  289,  290,  291,   -1,   -1,   -1,   -1,  260,  261,
  262,  263,   -1,   -1,  266,  267,   -1,  260,  261,  262,
  263,   33,   -1,  266,  267,   -1,   -1,   -1,   40,   -1,
   -1,   -1,   -1,   45,  286,  287,  288,  289,  290,  291,
   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,  125,   -1,   -1,   -1,  260,  261,  262,  263,   -1,
   -1,  266,  267,   58,   59,   60,   61,   62,   63,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   41,   -1,   -1,
   44,   -1,   -1,   -1,   -1,   -1,   41,   -1,   93,   44,
   -1,   -1,   -1,  125,   58,   59,   60,   61,   62,   63,
  257,  258,  259,   58,   59,   60,   61,   62,   63,   -1,
   -1,  268,  269,  270,   -1,   -1,   -1,   -1,   -1,   -1,
  277,   -1,  279,  280,  281,  282,  283,   -1,   -1,   93,
   -1,  257,  258,  259,   -1,   -1,   -1,   -1,   93,   -1,
   -1,   -1,  268,  269,  270,   -1,   -1,   -1,   -1,   41,
   -1,  277,   44,  279,  280,  281,  282,  283,   -1,   41,
   -1,   -1,   44,   -1,   -1,   -1,   58,   59,   -1,   61,
   -1,   63,   -1,  257,  258,  259,   58,   59,   -1,   61,
   -1,   63,   -1,   -1,  268,  269,  270,   41,   -1,   -1,
   44,   -1,   -1,  277,   -1,  279,  280,  281,  282,  283,
   -1,   93,   -1,   -1,   58,   59,   -1,   61,   -1,   63,
   -1,   93,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  257,  258,  259,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  268,  269,  270,   93,
   -1,   -1,   -1,   -1,   -1,  277,   -1,  279,  280,  281,
  282,  283,   -1,   -1,   -1,  260,  261,  262,  263,   -1,
   -1,  266,  267,   -1,   37,   -1,   -1,   -1,   -1,   42,
   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   60,   -1,   62,
   63,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,  260,  261,  262,  263,   -1,
   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,   91,   -1,
   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,
   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,
   -1,   -1,   -1,   -1,  286,  287,  288,  289,  290,  291,
   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   -1,   81,
   82,   -1,   -1,   85,   -1,   -1,   -1,   89,   90,   91,
   -1,   -1,  286,  287,  288,  289,  290,  291,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  109,   -1,   -1,
   -1,  113,  114,   -1,   -1,   -1,   -1,   -1,  120,   -1,
   -1,   -1,   -1,  125,  126,  127,  128,  129,  130,   -1,
   -1,  133,  134,  135,  136,  137,  138,  139,  140,  141,
  142,  143,  144,  145,  146,  147,   -1,  260,  261,  262,
  263,   -1,  154,  266,  267,  157,   -1,   -1,   -1,  161,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  190,   -1,
   -1,  193,   -1,   -1,   -1,  197,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  205,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=295;
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
"\"unary_minus\"","CAST",
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

//#line 390 "../../src/parser/parser.y"

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
//#line 789 "Parser.java"
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
//#line 50 "../../src/parser/parser.y"
{ this.ast = new Program(scanner.getLine(), scanner.getColumn()
															, (List<Definition>)val_peek(0)); }
break;
case 2:
//#line 53 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>)val_peek(1);
														defList.add((FunctionDefinition) val_peek(0));
	   													yyval = defList; }
break;
case 3:
//#line 58 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														for(Definition def : (List<Definition>)val_peek(0)){
															defList.add(def);
														}
														yyval = defList; }
break;
case 4:
//#line 63 "../../src/parser/parser.y"
{ List<Definition> defList = (List<Definition>) val_peek(1);
														defList.add((Definition)val_peek(0));
														yyval = defList; }
break;
case 5:
//#line 66 "../../src/parser/parser.y"
{ yyval = new ArrayList<Definition>(); }
break;
case 6:
//#line 68 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 7:
//#line 69 "../../src/parser/parser.y"
{ yyval = (FunctionDefinition) val_peek(0); }
break;
case 8:
//#line 72 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	,(List<Expression>) val_peek(1) , new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(3))); }
break;
case 9:
//#line 75 "../../src/parser/parser.y"
{ yyval = new Invocation(scanner.getLine(), scanner.getColumn()
																	, new ArrayList<Expression>(), new Variable(scanner.getLine()
																	, scanner.getColumn(), (String) val_peek(2))); }
break;
case 10:
//#line 81 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(6),
																			  		  (List<VariableDefinition>) val_peek(4), (Type) val_peek(7))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 11:
//#line 86 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																					  , new FunctionType(scanner.getLine()
																					  , scanner.getColumn(), (String) val_peek(5),
																			  		  new ArrayList<VariableDefinition>(), (Type) val_peek(6))
			 																  		  , (List<Statement>) val_peek(1)); }
break;
case 12:
//#line 95 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(6),
																			  (List<VariableDefinition>) val_peek(4), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 13:
//#line 100 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 14:
//#line 107 "../../src/parser/parser.y"
{ yyval = new FunctionDefinition(scanner.getLine(), scanner.getColumn()
																			  , new FunctionType(scanner.getLine()
																			  , scanner.getColumn(), (String) val_peek(5),
																			  new ArrayList<VariableDefinition>(), VoidType.getInstance())
			 																  , (List<Statement>) val_peek(1)); }
break;
case 15:
//#line 114 "../../src/parser/parser.y"
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
//#line 124 "../../src/parser/parser.y"
{ List<Statement> list = (List<Statement>) val_peek(1);
																  list.add((Statement) val_peek(0));
																  yyval = list; }
break;
case 17:
//#line 127 "../../src/parser/parser.y"
{ yyval = new ArrayList<>(); }
break;
case 18:
//#line 130 "../../src/parser/parser.y"
{ List<VariableDefinition> list = (List<VariableDefinition>) val_peek(2);
																  	list.add((VariableDefinition) val_peek(0));
																  yyval = list; }
break;
case 19:
//#line 133 "../../src/parser/parser.y"
{ List<VariableDefinition> list = new ArrayList<>();
																  list.add((VariableDefinition) val_peek(0));
																  yyval = list; }
break;
case 20:
//#line 138 "../../src/parser/parser.y"
{ yyval = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) val_peek(0), (Type) val_peek(1)); }
break;
case 21:
//#line 140 "../../src/parser/parser.y"
{ yyval = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) val_peek(0), (Type) val_peek(1)); }
break;
case 22:
//#line 142 "../../src/parser/parser.y"
{ yyval = new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, (String) val_peek(0), (Type) val_peek(1)); }
break;
case 23:
//#line 146 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 24:
//#line 147 "../../src/parser/parser.y"
{ yyval = (Read) val_peek(1); }
break;
case 25:
//#line 148 "../../src/parser/parser.y"
{ yyval = (Write) val_peek(1); }
break;
case 26:
//#line 149 "../../src/parser/parser.y"
{ yyval = (IfStatement) val_peek(0); }
break;
case 27:
//#line 150 "../../src/parser/parser.y"
{ yyval = (WhileStatement) val_peek(0); }
break;
case 28:
//#line 151 "../../src/parser/parser.y"
{ yyval = (ForStatement) val_peek(0); }
break;
case 29:
//#line 152 "../../src/parser/parser.y"
{ yyval = (SwitchCase) val_peek(0); }
break;
case 30:
//#line 153 "../../src/parser/parser.y"
{ yyval = (DoWhileStatement) val_peek(0); }
break;
case 31:
//#line 154 "../../src/parser/parser.y"
{ yyval = (Return) val_peek(1); }
break;
case 32:
//#line 157 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"+",(Expression) val_peek(0)); }
break;
case 33:
//#line 159 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"*",(Expression) val_peek(0)); }
break;
case 34:
//#line 161 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"/",(Expression) val_peek(0)); }
break;
case 35:
//#line 163 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"-",(Expression) val_peek(0)); }
break;
case 36:
//#line 165 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"%",(Expression) val_peek(0)); }
break;
case 37:
//#line 167 "../../src/parser/parser.y"
{ yyval = (Assignment)val_peek(0); }
break;
case 38:
//#line 169 "../../src/parser/parser.y"
{ yyval = (Ternary) val_peek(0); }
break;
case 39:
//#line 171 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"==",(Expression) val_peek(0)); }
break;
case 40:
//#line 173 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">",(Expression) val_peek(0)); }
break;
case 41:
//#line 175 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">=",(Expression) val_peek(0)); }
break;
case 42:
//#line 177 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<",(Expression) val_peek(0)); }
break;
case 43:
//#line 179 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<=",(Expression) val_peek(0)); }
break;
case 44:
//#line 181 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"!=",(Expression) val_peek(0)); }
break;
case 45:
//#line 183 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"&&",(Expression) val_peek(0)); }
break;
case 46:
//#line 185 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"||",(Expression) val_peek(0)); }
break;
case 47:
//#line 187 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 48:
//#line 189 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 49:
//#line 191 "../../src/parser/parser.y"
{ yyval = (Invocation) val_peek(0); }
break;
case 50:
//#line 192 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 51:
//#line 193 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn()
          																, (Expression) val_peek(2), ((Variable) val_peek(0)).getName()); }
break;
case 52:
//#line 195 "../../src/parser/parser.y"
{ yyval = (Cast) val_peek(0); }
break;
case 53:
//#line 196 "../../src/parser/parser.y"
{ yyval = (Indexing) val_peek(0); }
break;
case 54:
//#line 197 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn()
          																, (Integer) val_peek(0)); }
break;
case 55:
//#line 199 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn()
          																, (Double) val_peek(0)); }
break;
case 56:
//#line 201 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn()
          																, (Character) val_peek(0)); }
break;
case 57:
//#line 203 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn()
          																, (String) val_peek(0)); }
break;
case 58:
//#line 207 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(2), (List<Statement>) val_peek(0)
																		, (Expression) val_peek(4)); }
break;
case 59:
//#line 210 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(0), new ArrayList<Statement>()
																		, (Expression) val_peek(2)); }
break;
case 60:
//#line 215 "../../src/parser/parser.y"
{ yyval = new Ternary(scanner.getLine(), scanner.getColumn()
																		,(Expression) val_peek(4), (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 61:
//#line 218 "../../src/parser/parser.y"
{ yyval = (List<Statement>)val_peek(1); }
break;
case 62:
//#line 219 "../../src/parser/parser.y"
{ List<Statement> list = new ArrayList<Statement>();
															  list.add((Statement)val_peek(0));
															  yyval = list; }
break;
case 63:
//#line 224 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																	  yyval = new WhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(2)); }
break;
case 64:
//#line 229 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(4));
																	  yyval = new DoWhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(1)); }
break;
case 65:
//#line 234 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																			  yyval = new ForStatement(scanner.getLine(), scanner.getColumn()
																			       , (Statement) val_peek(5), (Expression) val_peek(4), (Assignment) val_peek(2), body); }
break;
case 66:
//#line 239 "../../src/parser/parser.y"
{ List<Case> cases = (List<Case>)val_peek(1);
																	  yyval = new SwitchCase(scanner.getLine(), scanner.getColumn()
																	  		, (Expression)val_peek(4), cases); }
break;
case 67:
//#line 245 "../../src/parser/parser.y"
{ List<Case> cases = (List<Case>)val_peek(1);
																	  cases.add((Case)val_peek(0));
																	  yyval = cases; }
break;
case 68:
//#line 248 "../../src/parser/parser.y"
{ List<Case> cases = new ArrayList<>();
																	  cases.add((Case)val_peek(0));
																	  yyval = cases; }
break;
case 69:
//#line 253 "../../src/parser/parser.y"
{ List<Statement> stms = (List<Statement>)val_peek(1);
																	  stms.add((BreakInstruction) val_peek(0));
																	  yyval = new NormalCase(scanner.getLine(), scanner.getColumn()
																			, (Expression)val_peek(3), stms); }
break;
case 70:
//#line 257 "../../src/parser/parser.y"
{ yyval = new NormalCase(scanner.getLine(), scanner.getColumn()
																			, (Expression)val_peek(2), (List<Statement>)val_peek(0)); }
break;
case 71:
//#line 259 "../../src/parser/parser.y"
{ List<Statement> stms = (List<Statement>)val_peek(1);
																	  stms.add((BreakInstruction) val_peek(0));
																	  yyval = new DefaultCase(scanner.getLine(), scanner.getColumn()
																			, stms); }
break;
case 72:
//#line 263 "../../src/parser/parser.y"
{ yyval = new DefaultCase(scanner.getLine(), scanner.getColumn()
																			, (List<Statement>)val_peek(0)); }
break;
case 73:
//#line 267 "../../src/parser/parser.y"
{ yyval = new BreakInstruction(scanner.getLine(), scanner.getColumn()); }
break;
case 74:
//#line 270 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn()
          																	,(Type) val_peek(2), (Expression) val_peek(0)); }
break;
case 75:
//#line 274 "../../src/parser/parser.y"
{ List<Expression> list = (List<Expression>) val_peek(2);
																	  list.add((Expression) val_peek(0));
																	  yyval = list; }
break;
case 76:
//#line 277 "../../src/parser/parser.y"
{ List<Expression> list = new ArrayList<>();
				 													  list.add((Expression)val_peek(0));
				 													  yyval = list; }
break;
case 77:
//#line 282 "../../src/parser/parser.y"
{ List<String> list = (List<String>) val_peek(2);
																	  if(list.contains((String)val_peek(0)))
																	  	new ErrorType(scanner.getLine(), scanner.getColumn()
																	  			, "The variable "+((String)val_peek(0))
																	  			+" has been already declared");
																	  list.add((String)val_peek(0));
																	  yyval = list; }
break;
case 78:
//#line 289 "../../src/parser/parser.y"
{ List<String> list = new ArrayList<String>();
		 															  list.add((String) val_peek(0));
		 															  yyval = list; }
break;
case 79:
//#line 294 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 80:
//#line 295 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 81:
//#line 296 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 82:
//#line 299 "../../src/parser/parser.y"
{yyval = new Indexing(scanner.getLine()
																, scanner.getColumn(), (Expression)val_peek(3)
																,(Expression)val_peek(1));}
break;
case 83:
//#line 304 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
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
//#line 315 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 87:
//#line 320 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 88:
//#line 325 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 89:
//#line 332 "../../src/parser/parser.y"
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
case 90:
//#line 341 "../../src/parser/parser.y"
{ yyval = new ArrayList<VariableDefinition>(); }
break;
case 91:
//#line 344 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
																		, (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 92:
//#line 346 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"+"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 93:
//#line 350 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"-"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 94:
//#line 354 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"+",(Expression)val_peek(0))); }
break;
case 95:
//#line 357 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"-",(Expression)val_peek(0))); }
break;
case 96:
//#line 360 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"*",(Expression)val_peek(0))); }
break;
case 97:
//#line 363 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"/",(Expression)val_peek(0))); }
break;
case 98:
//#line 368 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 99:
//#line 371 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 100:
//#line 374 "../../src/parser/parser.y"
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
case 101:
//#line 385 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(0)); }
break;
//#line 1503 "Parser.java"
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
