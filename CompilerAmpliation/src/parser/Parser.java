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
public final static short unary_minus=288;
public final static short CAST=289;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    2,    5,    5,    8,    8,    6,
    6,    7,    7,    3,   12,   14,   14,   11,   11,   16,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   21,
   21,   28,   28,   22,   24,   23,   26,    9,    9,   29,
   29,   10,   10,   10,   27,   30,   30,    4,    4,    4,
   13,   13,   20,   18,   19,   31,   25,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    1,    1,    4,    3,    8,
    7,    8,    7,    7,    2,    2,    0,    3,    1,    2,
    2,    2,    2,    2,    1,    1,    1,    1,    2,    3,
    3,    3,    3,    3,    3,    3,    3,    3,    3,    3,
    3,    3,    2,    2,    3,    3,    3,    3,    2,    2,
    1,    3,    3,    1,    1,    1,    1,    1,    1,    7,
    5,    3,    1,    5,    6,    8,    4,    3,    1,    3,
    1,    1,    1,    1,    4,    4,    4,    3,    3,    3,
    2,    0,    3,    2,    2,    4,    2,
};
final static short yydefred[] = {                         5,
    0,    1,    0,    0,   72,   74,   73,    0,    2,    3,
    4,    6,    7,    0,    0,    0,    0,    0,   82,    0,
    0,    0,   71,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   78,    0,   79,   80,    0,    0,    0,   19,
    0,   86,   81,    0,    0,    0,   77,   70,   76,   82,
   20,    0,    0,   82,   82,    0,    0,    0,   82,   18,
    0,    0,   82,   13,    0,    0,   14,   11,    0,   56,
   58,   57,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   51,   16,    0,    0,    0,    0,   25,
   26,   27,   28,    0,   54,   55,   12,   10,    0,    0,
    0,    0,    0,    0,    0,    0,   17,   63,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   43,
   44,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   21,   22,   23,   24,   29,
    9,    0,    0,    0,    0,    0,    0,    0,    0,   52,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   53,
    8,    0,    0,    0,    0,   62,    0,    0,   75,    0,
   64,    0,    0,    0,    0,   65,   60,    0,   66,
};
final static short yydgoto[] = {                          1,
    2,    3,    9,   43,   11,   12,   13,   84,  100,   38,
   39,   57,   58,   65,  108,   40,   86,   87,   88,   89,
   90,   91,   92,   93,   94,   95,   96,  109,   22,   15,
   16,
};
final static short yysindex[] = {                         0,
    0,    0,  241, -215,    0,    0,    0, -121,    0,    0,
    0,    0,    0,  -78,  -77, -254,   18,   19,    0,   39,
 -168,    5,    0, -163,   24,   43,  -40,   82,  -59,  -21,
   17, -132,    0,   58,    0,    0,   29, -119,   52,    0,
   31,    0,    0,  -65,   34,   84,    0,    0,    0,    0,
    0,   36,  -55,    0,    0,   38,   41, -243,    0,    0,
   45,   60,    0,    0,  261,   61,    0,    0,   66,    0,
    0,    0,  124,  502,  502,  128,  143,  502,  153,  295,
  502,  502,  622,    0,    0,    1,  136,  145,  160,    0,
    0,    0,    0,  161,    0,    0,    0,    0,   12,  178,
  432,  178,  502,  502,  432,  261,    0,    0,  -48,  785,
   -7,  199,   28,  502,  502,  502,  502,  502,  502,    0,
    0,  502,  502,  502,  502,  502,  502,  502,  502,  502,
  502,  502,  502,  502,  502,    0,    0,    0,    0,    0,
    0,   86,  502,   35,   62,  502,  329,  201,  502,    0,
  163,  163,  163,  163,  785,  785,  432,  432,  432,  432,
  432,  163,  163,   -5,   -5,   -7,   -7,   -7,   69,    0,
    0,  432,  295,  295,   96,    0,  502,   -6,    0,  -34,
    0,  502,  103,  295,  130,    0,    0,  295,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   54,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  363,    0,    0,
    0,    0,    0,    0,  118,    0,    0,    0,    0,    0,
    0,    0,  -37,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  194,
   77,  208,    0,    0,  209,    0,    0,    0,    0,  805,
  137,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  575,  683,  696,  723,  809,  813,   76,  366,  465,  842,
  213,  736,  750,  489,  540,  387,  396,  423,    0,    0,
    0,  446,    0,    0,    0,    0,    0,  -26,    0,  453,
    0,    0,    0,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  270,    0,    0,    0,    0,  -72,  473,
  249,  148,  262,  173,  -15,  250, 1001,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  323,   85,    0,
    0,
};
final static int YYTABLESIZE=1183;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         59,
   37,   19,  102,   59,   59,   59,   59,   59,   59,   59,
   67,   23,   21,   24,   67,   67,   67,   67,   67,   45,
   67,   59,   59,   59,   59,   21,  142,    5,    6,    7,
    8,  133,   67,   67,   67,   67,  131,  133,  135,  135,
  135,  132,  131,  129,   81,  130,  135,  132,   32,   85,
   17,   83,  141,   59,   18,   59,   82,   27,   28,  136,
  128,  126,  127,   33,  133,   42,   67,   32,  150,  131,
  129,  133,  130,  135,  132,  173,  131,  129,   30,  130,
  135,  132,   35,  134,  134,  134,   32,  128,   31,  127,
  146,  134,   52,   34,  128,   53,  127,   71,  133,   25,
   26,   36,  174,  131,  129,  133,  130,  135,  132,   47,
  131,  129,   71,  130,  135,  132,   48,   69,  134,   48,
   69,  128,   41,  127,   56,  134,  171,   53,  128,  143,
  127,   85,  133,   48,   48,   69,   48,  131,  129,  133,
  130,  135,  132,  186,  131,  129,   51,  130,  135,  132,
   49,   50,  134,   54,  182,  128,   55,  127,   59,  134,
   63,  179,  128,   99,  127,   64,  133,  103,   48,   67,
  188,  131,  129,   49,  130,  135,  132,   49,   49,   49,
   49,   49,  104,   49,   68,   97,  134,   20,   23,  128,
   98,  127,  106,  134,  137,   49,   49,   49,   49,  133,
   23,   61,   62,  138,  131,  129,   66,  130,  135,  132,
   69,    5,    6,    7,    8,    5,    6,    7,  139,  140,
  134,  143,   59,   59,   59,   59,   59,   59,  148,   49,
    5,    6,    7,   67,   67,   67,   67,   67,   67,  149,
  177,  184,   15,   59,   59,   59,   59,   59,   59,    5,
    6,    7,   85,  134,   67,   67,   67,   67,   67,   67,
  114,  115,  116,  117,  118,  119,   84,   87,   70,   71,
   72,   83,   10,  120,  121,  120,  121,   73,   46,  147,
   29,  120,  121,  122,  123,  124,  125,  114,  115,  116,
  117,  118,  119,   81,  114,  115,  116,  117,  118,  119,
   83,    0,   60,    0,    0,   82,    0,    0,  120,  121,
  122,  123,  124,  125,    0,  120,  121,  122,  123,  124,
  125,  114,  115,  116,  117,  118,  119,   81,  114,  115,
  116,  117,  118,  119,   83,    0,    0,    0,    0,   82,
    0,    0,  120,  121,  122,  123,  124,  125,    0,  120,
  121,  122,  123,  124,  125,  114,  115,  116,  117,  118,
  119,   81,  114,  115,  116,  117,  118,  119,   83,    0,
    0,    0,    0,   82,    0,    0,  120,  121,  122,  123,
  124,  125,    0,  120,  121,  122,  123,  124,  125,  114,
  115,  116,  117,  118,  119,   17,   49,   49,   49,   49,
   49,   49,   17,    0,    0,    0,   47,   17,    0,   47,
  120,  121,  122,  123,  124,  125,    0,  107,    0,   49,
   49,   49,   49,   31,   47,    0,   47,   31,   31,   31,
   31,   31,   32,   31,    0,    0,   32,   32,   32,   32,
   32,    0,   32,  120,  121,   31,   31,   31,   31,    0,
    0,    0,    0,  176,   32,   32,   32,   32,   47,   34,
    0,    0,    0,   34,   34,   34,   34,   34,  133,   34,
    0,    0,    0,  131,  129,   14,  130,  135,  132,   31,
    0,   34,   34,   34,   34,   61,   68,   17,   32,   68,
    0,  128,   61,  127,    0,  180,  181,   61,    0,    0,
    0,   44,    0,    0,   68,   46,  187,    0,   46,    4,
  189,    5,    6,    7,    8,   34,    0,   70,   71,   72,
    0,    0,  134,   46,    0,   46,   73,   74,   75,   30,
   44,   30,   30,   30,   81,   76,    0,   77,   78,   79,
   80,   83,    0,    0,    0,    0,   82,   30,   30,   30,
   30,   70,   71,   72,    0,  112,    0,   46,    0,    0,
   73,   74,   75,    0,    0,    0,    0,    0,    0,   76,
    0,   77,   78,   79,   80,    0,    0,   61,    0,    0,
   33,   30,   33,   33,   33,   70,   71,   72,    0,    0,
    0,    0,    0,    0,   73,   74,   75,    0,   33,   33,
   33,   33,    0,   76,    0,   77,   78,   79,   80,    0,
    0,    0,    0,    0,    0,   39,    0,    0,   39,   17,
   17,   17,    0,    0,    0,    0,    0,    0,   17,   17,
   17,    0,   33,   39,   39,   39,   39,   17,    0,   17,
   17,   17,   17,    0,    0,    0,   31,   31,   31,   31,
   31,   31,    0,    0,   81,   32,   32,   32,   32,   32,
   32,   83,    0,    0,    0,    0,   82,   39,    0,   31,
   31,   31,   31,    0,    0,    0,    0,    0,   32,   32,
   32,   32,   34,   34,   34,   34,   34,   34,    0,    0,
    0,  114,  115,  116,  117,  118,  119,    0,    0,    0,
    0,    0,    0,    0,    0,   34,   34,   34,   34,   61,
   61,   61,  120,  121,  122,  123,  124,  125,   61,   61,
   61,    0,    0,   37,    0,    0,   37,   61,    0,   61,
   61,   61,   61,    0,    0,    0,   35,    0,    0,   35,
    0,   37,   37,   37,   37,    0,    0,    0,   30,   30,
   30,   30,   30,   30,   35,   35,   35,   35,   70,   71,
   72,    0,    0,   40,    0,    0,   40,   73,    0,    0,
    0,   30,   30,   30,   30,   37,   36,    0,    0,   36,
    0,   40,   40,   40,   40,    0,    0,    0,   35,    0,
   38,    0,    0,   38,   36,   36,   36,   36,    0,   33,
   33,   33,   33,   33,   33,    0,    0,    0,   38,   38,
   38,   38,    0,    0,    0,   40,    0,    0,    0,    0,
    0,  133,   33,   33,   33,   33,  131,  129,   36,  130,
  135,  132,    0,    0,   39,   39,   39,   39,   39,   39,
    0,    0,   38,    0,  128,   50,  127,    0,   50,   41,
    0,    0,   41,   42,    0,    0,   42,   39,   39,   39,
   39,    0,    0,   50,    0,   50,    0,   41,    0,   41,
    0,   42,    0,   42,    0,  134,    0,    0,   70,   71,
   72,    0,   45,    0,    0,   45,    0,   73,    0,    0,
    0,    0,    5,    6,    7,    0,    0,   50,    0,    0,
   45,   41,   45,    0,    0,   42,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   45,    0,    0,    0,    0,    0,
    0,    0,   37,   37,   37,   37,   37,   37,    0,    0,
    0,    0,    0,    0,    0,   35,   35,   35,   35,   35,
   35,    0,    0,    0,    0,   37,   37,   37,   37,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   35,   35,
   35,   35,   40,   40,   40,   40,   40,   40,    0,    0,
    0,    0,    0,    0,    0,   36,   36,   36,   36,   36,
   36,    0,    0,    0,    0,   40,   40,   40,   40,   38,
   38,   38,   38,   38,   38,    0,    0,    0,   36,   36,
   36,   36,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   38,   38,   38,   38,    0,    0,    0,    0,
    0,    0,    0,    0,  114,  115,  116,  117,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  120,  121,    0,   50,   50,
    0,    0,   41,   41,  101,  101,   42,   42,  105,    0,
    0,  110,  111,  113,    0,    0,    0,   50,   50,   50,
   50,   41,   41,   41,   41,   42,   42,   42,   42,  101,
    0,    0,    0,  144,  145,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  151,  152,  153,  154,  155,  156,
    0,    0,  157,  158,  159,  160,  161,  162,  163,  164,
  165,  166,  167,  168,  169,  170,    0,    0,    0,    0,
    0,    0,    0,  172,    0,    0,  175,    0,    0,  178,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  183,    0,    0,
    0,    0,  185,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
   41,  123,   75,   41,   42,   43,   44,   45,   46,   47,
   37,  266,   91,   91,   41,   42,   43,   44,   45,   41,
   47,   59,   60,   61,   62,   91,   99,  271,  272,  273,
  274,   37,   59,   60,   61,   62,   42,   37,   46,   46,
   46,   47,   42,   43,   33,   45,   46,   47,   44,   65,
  266,   40,   41,   91,  270,   93,   45,   40,   40,   59,
   60,   61,   62,   59,   37,  125,   93,   44,   41,   42,
   43,   37,   45,   46,   47,   41,   42,   43,   40,   45,
   46,   47,   59,   91,   91,   91,   44,   60,  257,   62,
  106,   91,   41,  257,   60,   44,   62,   44,   37,   15,
   16,   59,   41,   42,   43,   37,   45,   46,   47,   93,
   42,   43,   59,   45,   46,   47,   41,   41,   91,   44,
   44,   60,   41,   62,   41,   91,   41,   44,   60,   44,
   62,  147,   37,  266,   59,   59,   61,   42,   43,   37,
   45,   46,   47,   41,   42,   43,  266,   45,   46,   47,
   93,  123,   91,  123,   59,   60,  123,   62,  123,   91,
  123,   93,   60,   40,   62,  125,   37,   40,   93,  125,
   41,   42,   43,   37,   45,   46,   47,   41,   42,   43,
   44,   45,   40,   47,  125,  125,   91,  266,  266,   60,
  125,   62,   40,   91,   59,   59,   60,   61,   62,   37,
  266,   54,   55,   59,   42,   43,   59,   45,   46,   47,
   63,  271,  272,  273,  274,  271,  272,  273,   59,   59,
   91,   44,  260,  261,  262,  263,  264,  265,  277,   93,
  271,  272,  273,  260,  261,  262,  263,  264,  265,   41,
   40,  276,  125,  281,  282,  283,  284,  285,  286,  271,
  272,  273,   59,   91,  281,  282,  283,  284,  285,  286,
  260,  261,  262,  263,  264,  265,   59,   59,  257,  258,
  259,   59,    3,  281,  282,  281,  282,  266,   30,  107,
   19,  281,  282,  283,  284,  285,  286,  260,  261,  262,
  263,  264,  265,   33,  260,  261,  262,  263,  264,  265,
   40,   -1,   53,   -1,   -1,   45,   -1,   -1,  281,  282,
  283,  284,  285,  286,   -1,  281,  282,  283,  284,  285,
  286,  260,  261,  262,  263,  264,  265,   33,  260,  261,
  262,  263,  264,  265,   40,   -1,   -1,   -1,   -1,   45,
   -1,   -1,  281,  282,  283,  284,  285,  286,   -1,  281,
  282,  283,  284,  285,  286,  260,  261,  262,  263,  264,
  265,   33,  260,  261,  262,  263,  264,  265,   40,   -1,
   -1,   -1,   -1,   45,   -1,   -1,  281,  282,  283,  284,
  285,  286,   -1,  281,  282,  283,  284,  285,  286,  260,
  261,  262,  263,  264,  265,   33,  260,  261,  262,  263,
  264,  265,   40,   -1,   -1,   -1,   41,   45,   -1,   44,
  281,  282,  283,  284,  285,  286,   -1,  123,   -1,  283,
  284,  285,  286,   37,   59,   -1,   61,   41,   42,   43,
   44,   45,   37,   47,   -1,   -1,   41,   42,   43,   44,
   45,   -1,   47,  281,  282,   59,   60,   61,   62,   -1,
   -1,   -1,   -1,  125,   59,   60,   61,   62,   93,   37,
   -1,   -1,   -1,   41,   42,   43,   44,   45,   37,   47,
   -1,   -1,   -1,   42,   43,    3,   45,   46,   47,   93,
   -1,   59,   60,   61,   62,   33,   41,  125,   93,   44,
   -1,   60,   40,   62,   -1,  173,  174,   45,   -1,   -1,
   -1,   29,   -1,   -1,   59,   41,  184,   -1,   44,  269,
  188,  271,  272,  273,  274,   93,   -1,  257,  258,  259,
   -1,   -1,   91,   59,   -1,   61,  266,  267,  268,   41,
   58,   43,   44,   45,   33,  275,   -1,  277,  278,  279,
  280,   40,   -1,   -1,   -1,   -1,   45,   59,   60,   61,
   62,  257,  258,  259,   -1,   83,   -1,   93,   -1,   -1,
  266,  267,  268,   -1,   -1,   -1,   -1,   -1,   -1,  275,
   -1,  277,  278,  279,  280,   -1,   -1,  125,   -1,   -1,
   41,   93,   43,   44,   45,  257,  258,  259,   -1,   -1,
   -1,   -1,   -1,   -1,  266,  267,  268,   -1,   59,   60,
   61,   62,   -1,  275,   -1,  277,  278,  279,  280,   -1,
   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,  257,
  258,  259,   -1,   -1,   -1,   -1,   -1,   -1,  266,  267,
  268,   -1,   93,   59,   60,   61,   62,  275,   -1,  277,
  278,  279,  280,   -1,   -1,   -1,  260,  261,  262,  263,
  264,  265,   -1,   -1,   33,  260,  261,  262,  263,  264,
  265,   40,   -1,   -1,   -1,   -1,   45,   93,   -1,  283,
  284,  285,  286,   -1,   -1,   -1,   -1,   -1,  283,  284,
  285,  286,  260,  261,  262,  263,  264,  265,   -1,   -1,
   -1,  260,  261,  262,  263,  264,  265,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  283,  284,  285,  286,  257,
  258,  259,  281,  282,  283,  284,  285,  286,  266,  267,
  268,   -1,   -1,   41,   -1,   -1,   44,  275,   -1,  277,
  278,  279,  280,   -1,   -1,   -1,   41,   -1,   -1,   44,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,  260,  261,
  262,  263,  264,  265,   59,   60,   61,   62,  257,  258,
  259,   -1,   -1,   41,   -1,   -1,   44,  266,   -1,   -1,
   -1,  283,  284,  285,  286,   93,   41,   -1,   -1,   44,
   -1,   59,   60,   61,   62,   -1,   -1,   -1,   93,   -1,
   41,   -1,   -1,   44,   59,   60,   61,   62,   -1,  260,
  261,  262,  263,  264,  265,   -1,   -1,   -1,   59,   60,
   61,   62,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   37,  283,  284,  285,  286,   42,   43,   93,   45,
   46,   47,   -1,   -1,  260,  261,  262,  263,  264,  265,
   -1,   -1,   93,   -1,   60,   41,   62,   -1,   44,   41,
   -1,   -1,   44,   41,   -1,   -1,   44,  283,  284,  285,
  286,   -1,   -1,   59,   -1,   61,   -1,   59,   -1,   61,
   -1,   59,   -1,   61,   -1,   91,   -1,   -1,  257,  258,
  259,   -1,   41,   -1,   -1,   44,   -1,  266,   -1,   -1,
   -1,   -1,  271,  272,  273,   -1,   -1,   93,   -1,   -1,
   59,   93,   61,   -1,   -1,   93,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   93,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,  264,  265,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,
  265,   -1,   -1,   -1,   -1,  283,  284,  285,  286,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,
  285,  286,  260,  261,  262,  263,  264,  265,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,  264,
  265,   -1,   -1,   -1,   -1,  283,  284,  285,  286,  260,
  261,  262,  263,  264,  265,   -1,   -1,   -1,  283,  284,
  285,  286,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  283,  284,  285,  286,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  281,  282,   -1,  264,  265,
   -1,   -1,  264,  265,   74,   75,  264,  265,   78,   -1,
   -1,   81,   82,   83,   -1,   -1,   -1,  283,  284,  285,
  286,  283,  284,  285,  286,  283,  284,  285,  286,   99,
   -1,   -1,   -1,  103,  104,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  114,  115,  116,  117,  118,  119,
   -1,   -1,  122,  123,  124,  125,  126,  127,  128,  129,
  130,  131,  132,  133,  134,  135,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,  143,   -1,   -1,  146,   -1,   -1,  149,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  177,   -1,   -1,
   -1,   -1,  182,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=289;
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
"PLUSEQUALS","IFX","\"unary_minus\"","CAST",
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
"do_while : DO if_body WHILE '(' expression ')'",
"for : FOR '(' statement expression ';' expression ')' if_body",
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

//#line 341 "../../src/parser/parser.y"

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
//#line 635 "Parser.java"
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
{ yyval = (Read) val_peek(1); }
break;
case 23:
//#line 145 "../../src/parser/parser.y"
{ yyval = (Write) val_peek(1); }
break;
case 24:
//#line 146 "../../src/parser/parser.y"
{ yyval = (Assignment) val_peek(1); }
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
{ yyval = (ForStatement) val_peek(0); }
break;
case 28:
//#line 150 "../../src/parser/parser.y"
{ yyval = (DoWhileStatement) val_peek(0); }
break;
case 29:
//#line 151 "../../src/parser/parser.y"
{ yyval = (Return) val_peek(1); }
break;
case 30:
//#line 154 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"+",(Expression) val_peek(0)); }
break;
case 31:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"*",(Expression) val_peek(0)); }
break;
case 32:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"/",(Expression) val_peek(0)); }
break;
case 33:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"-",(Expression) val_peek(0)); }
break;
case 34:
//#line 162 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"%",(Expression) val_peek(0)); }
break;
case 35:
//#line 164 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"==",(Expression) val_peek(0)); }
break;
case 36:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">",(Expression) val_peek(0)); }
break;
case 37:
//#line 168 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">=",(Expression) val_peek(0)); }
break;
case 38:
//#line 170 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<",(Expression) val_peek(0)); }
break;
case 39:
//#line 172 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<=",(Expression) val_peek(0)); }
break;
case 40:
//#line 174 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"!=",(Expression) val_peek(0)); }
break;
case 41:
//#line 176 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"&&",(Expression) val_peek(0)); }
break;
case 42:
//#line 178 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"||",(Expression) val_peek(0)); }
break;
case 43:
//#line 180 "../../src/parser/parser.y"
{ yyval = new Increment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1)); }
break;
case 44:
//#line 182 "../../src/parser/parser.y"
{ yyval = new Decrement(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1)); }
break;
case 45:
//#line 184 "../../src/parser/parser.y"
{ yyval = new ArithmeticAssignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (String) val_peek(1), (Expression) val_peek(0)); }
break;
case 46:
//#line 186 "../../src/parser/parser.y"
{ yyval = new ArithmeticAssignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (String) val_peek(1), (Expression) val_peek(0)); }
break;
case 47:
//#line 188 "../../src/parser/parser.y"
{ yyval = new ArithmeticAssignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (String) val_peek(1), (Expression) val_peek(0)); }
break;
case 48:
//#line 190 "../../src/parser/parser.y"
{ yyval = new ArithmeticAssignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (String) val_peek(1), (Expression) val_peek(0)); }
break;
case 49:
//#line 192 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 50:
//#line 194 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 51:
//#line 196 "../../src/parser/parser.y"
{ yyval = (Invocation) val_peek(0); }
break;
case 52:
//#line 197 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 53:
//#line 198 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn()
          																, (Expression) val_peek(2), ((Variable) val_peek(0)).getName()); }
break;
case 54:
//#line 200 "../../src/parser/parser.y"
{ yyval = (Cast) val_peek(0); }
break;
case 55:
//#line 201 "../../src/parser/parser.y"
{ yyval = (Indexing) val_peek(0); }
break;
case 56:
//#line 202 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn()
          																, (Integer) val_peek(0)); }
break;
case 57:
//#line 204 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn()
          																, (Double) val_peek(0)); }
break;
case 58:
//#line 206 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn()
          																, (Character) val_peek(0)); }
break;
case 59:
//#line 208 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn()
          																, (String) val_peek(0)); }
break;
case 60:
//#line 212 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(2), (List<Statement>) val_peek(0)
																		, (Expression) val_peek(4)); }
break;
case 61:
//#line 215 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(0), new ArrayList<Statement>()
																		, (Expression) val_peek(2)); }
break;
case 62:
//#line 220 "../../src/parser/parser.y"
{yyval = (List<Statement>)val_peek(1);}
break;
case 63:
//#line 221 "../../src/parser/parser.y"
{ List<Statement> list = new ArrayList<Statement>();
															  list.add((Statement)val_peek(0));
															  yyval = list;}
break;
case 64:
//#line 226 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																	  yyval = new WhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(2)); }
break;
case 65:
//#line 231 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(4));
																	  yyval = new DoWhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(1)); }
break;
case 66:
//#line 236 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																			  yyval = new ForStatement(scanner.getLine(), scanner.getColumn()
																			       , (Statement) val_peek(5), (Expression) val_peek(4), (Incrementable) val_peek(2), body); }
break;
case 67:
//#line 242 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn()
          																	,(Type) val_peek(2), (Expression) val_peek(0)); }
break;
case 68:
//#line 246 "../../src/parser/parser.y"
{ List<Expression> list = (List<Expression>) val_peek(2);
																	  list.add((Expression) val_peek(0));
																	  yyval = list; }
break;
case 69:
//#line 249 "../../src/parser/parser.y"
{ List<Expression> list = new ArrayList<>();
				 													  list.add((Expression)val_peek(0));
				 													  yyval = list; }
break;
case 70:
//#line 254 "../../src/parser/parser.y"
{ List<String> list = (List<String>) val_peek(2);
																	  if(list.contains((String)val_peek(0)))
																	  	new ErrorType(scanner.getLine(), scanner.getColumn()
																	  			, "The variable "+((String)val_peek(0))
																	  			+" has been already declared");
																	  list.add((String)val_peek(0));
																	  yyval = list; }
break;
case 71:
//#line 261 "../../src/parser/parser.y"
{ List<String> list = new ArrayList<String>();
		 															  list.add((String) val_peek(0));
		 															  yyval = list; }
break;
case 72:
//#line 266 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 73:
//#line 267 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 74:
//#line 268 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 75:
//#line 271 "../../src/parser/parser.y"
{yyval = new Indexing(scanner.getLine()
																, scanner.getColumn(), (Expression)val_peek(3)
																,(Expression)val_peek(1));}
break;
case 76:
//#line 276 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  array.getOf();
															  yyval = array; }
break;
case 77:
//#line 280 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  array.getOf();
															  yyval = array; }
break;
case 78:
//#line 286 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 79:
//#line 291 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 80:
//#line 296 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 81:
//#line 303 "../../src/parser/parser.y"
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
case 82:
//#line 312 "../../src/parser/parser.y"
{ yyval = new ArrayList<VariableDefinition>(); }
break;
case 83:
//#line 315 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 84:
//#line 319 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 85:
//#line 322 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 86:
//#line 325 "../../src/parser/parser.y"
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
case 87:
//#line 336 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(0)); }
break;
//#line 1271 "Parser.java"
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
