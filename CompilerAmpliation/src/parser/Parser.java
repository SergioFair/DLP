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
public final static short INC=283;
public final static short DEC=284;
public final static short DIVIDEEQUALS=285;
public final static short TIMESEQUALS=286;
public final static short MINUSEQUALS=287;
public final static short PLUSEQUALS=288;
public final static short IFX=289;
public final static short POTENTIAL=290;
public final static short unary_minus=291;
public final static short CAST=292;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    2,    2,    2,    5,    5,    8,    8,    6,
    6,    7,    7,    3,   12,   14,   14,   11,   11,   16,
   15,   15,   15,   15,   15,   15,   15,   15,   15,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   17,   17,   17,   17,
   17,   17,   17,   17,   17,   17,   19,   19,   19,   19,
   19,   19,   19,   21,   21,   27,   30,   30,   22,   24,
   23,   28,    9,    9,   31,   31,   10,   10,   10,   29,
   32,   32,   32,    4,    4,    4,   13,   13,   26,   18,
   20,   33,   25,
};
final static short yylen[] = {                            2,
    1,    2,    2,    2,    0,    1,    1,    4,    3,    8,
    7,    8,    7,    7,    2,    2,    0,    3,    1,    2,
    2,    2,    2,    2,    1,    1,    1,    1,    2,    3,
    3,    3,    3,    3,    1,    1,    3,    3,    3,    3,
    3,    3,    3,    3,    3,    2,    2,    1,    3,    3,
    1,    1,    1,    1,    1,    1,    0,    2,    2,    3,
    3,    3,    3,    7,    5,    5,    3,    1,    5,    6,
    8,    4,    3,    1,    3,    1,    1,    1,    1,    4,
    4,    4,    4,    3,    3,    3,    2,    0,    3,    2,
    2,    4,    2,
};
final static short yydefred[] = {                         5,
    0,    1,    0,    0,   77,   79,   78,    0,    2,    3,
    4,    6,    7,    0,    0,    0,    0,    0,   88,    0,
    0,    0,   76,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   84,    0,   85,    0,   86,    0,    0,
    0,   19,    0,   92,   87,    0,    0,    0,   82,   75,
   81,   83,   88,   20,    0,    0,   88,   88,    0,    0,
    0,   88,   18,    0,    0,   88,   13,    0,    0,   14,
   11,    0,   53,   55,   54,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   48,   16,    0,    0,
    0,    0,   25,   26,   27,   28,    0,   35,   36,   51,
   52,   12,   10,    0,    0,    0,    0,    0,    0,    0,
    0,   17,   68,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   58,   59,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   21,   22,   23,   24,   29,    9,    0,    0,
    0,    0,    0,    0,    0,    0,   49,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,   50,    8,
    0,    0,    0,    0,   67,    0,    0,    0,   80,    0,
   69,    0,    0,    0,    0,    0,    0,   70,   64,    0,
   71,
};
final static short yydgoto[] = {                          1,
    2,    3,    9,   45,   11,   12,   13,   87,  105,   40,
   41,   60,   61,   68,  113,   42,   89,   90,   91,   92,
   93,   94,   95,   96,   97,   98,   99,  100,  101,  114,
   22,   15,   16,
};
final static short yysindex[] = {                         0,
    0,    0,   26, -149,    0,    0,    0, -120,    0,    0,
    0,    0,    0,  -74,  -64,  -63,   40,   42,    0,   69,
 -243,  -24,    0, -218,   19, -131,   49,   80,  100, -124,
   83,   60, -135,    0,   62,    0,   63,    0,   34, -107,
   -7,    0,   43,    0,    0,  -62,   45,   76,    0,    0,
    0,    0,    0,    0,   58, -201,    0,    0,   59,   47,
    3,    0,    0,   61,   73,    0,    0,  637,   74,    0,
    0,   77,    0,    0,    0,  144,  341,  341,  145,  161,
  341,  167,  675,  341,  341, 1142,    0,    0,   53,  155,
  156,  157,    0,    0,    0,    0,  162,    0,    0,    0,
    0,    0,    0,  691,  165,  467,  165,  341,  341,  467,
  637,    0,    0,  -51,  522,  -44,  193,  117,  341,  341,
  341,  341,  341,  341,    0,    0,  341,  341,  341,  341,
  341,  341,  341,  341,  341,  341,  341,  341,  341,  341,
  341,  341,    0,    0,    0,    0,    0,    0,   99,  341,
  128,  359,  341,  732,  191,  341,    0,  190,  190,  190,
  190,  522,  522,  467,  467,  467,  467,  467,  391,  190,
  190,   92,   92,  -27,  -27,  -27,  -44,  150,    0,    0,
  467,  675,  675,  424,    0,  341,  -44,  341,    0,  -40,
    0,  341,  456,  489,  675,   85,  198,    0,    0,  675,
    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   66,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
  570,    0,    0,    0,    0,    0,    0,  -48,    0,    0,
    0,    0,    0,    0,    0,  -37,    0,    0,    0,    0,
    0,    0,  181,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,  183,  -28,  185,    0,    0,  186,
  181,    0,    0,    0,  159,   -1,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,  181,    0,    0,    0,  772,  987, 1025,
 1063, 1109, 1145,  -11,   24,   35,   38,  978,    0, 1071,
 1100,  815,  934,  534,  601,  700,    8,    0,    0,    0,
  -26,  181,  181,    0,    0,    0,   44,    0,    0,  643,
    0,  215,    0, 1166,  181,    0,    0,    0,    0,  181,
    0,
};
final static short yygindex[] = {                         0,
    0,    0,    0,  254,    0,    0,    0,    0,  -66,   81,
  227,  260,  245,  168,  -36,  217, 1357,    0,   96,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,   72,
  120,    0,    0,
};
final static int YYTABLESIZE=1549;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         56,
   44,  142,   19,   56,   56,   56,   56,   56,   56,   56,
   57,  107,   74,   32,   73,   74,   21,   73,  142,   33,
   56,   56,   56,   56,   56,   56,   24,   26,   21,   63,
   74,   88,   73,   55,   34,   46,   56,  149,   35,   46,
   46,   46,   46,   46,   37,   46,  141,   63,   37,   37,
   37,   37,   37,   56,   37,   56,   46,   46,   46,   46,
   46,   46,   33,  141,   62,   37,   37,   37,   37,   37,
   37,    5,    6,    7,  153,   61,   15,   36,   60,   28,
   72,   29,   62,   14,   72,   72,   72,   72,   72,  139,
   72,   46,   33,   61,  137,  135,   60,  136,  142,  138,
   37,   72,   72,   72,   72,   72,   72,   38,   31,   76,
   46,  143,  134,  131,  133,  132,   59,   88,   17,   56,
   39,  139,   18,   47,   76,   37,  137,  135,  139,  136,
  142,  138,   50,  137,   25,   27,   72,  142,  138,  180,
   43,   46,  150,  141,  134,  131,  133,  132,    5,    6,
    7,    8,   49,  139,   51,   52,   53,  157,  137,  135,
   54,  136,  142,  138,  139,   57,  117,   58,  182,  137,
  135,   67,  136,  142,  138,  141,  134,  131,  133,  132,
   62,   66,  141,  104,  108,   70,  139,  134,  131,  133,
  132,  137,  135,   20,  136,  142,  138,   71,  102,   47,
  109,  103,   47,   23,   23,   23,  111,  141,  150,  134,
  131,  133,  132,  144,  145,  146,   47,   47,  141,   47,
  147,   47,   56,   56,   56,   56,  139,  155,   56,   56,
  186,  137,  135,  156,  136,  142,  138,  195,  200,   57,
  141,   91,  189,   90,   93,   56,   56,   56,   56,   56,
   56,   47,   56,  190,  191,   57,   10,   48,   46,   46,
   46,   46,  140,   30,   46,   46,  199,   37,   37,   37,
   37,  201,   63,   37,   37,    5,    6,    7,    8,  154,
  141,   46,   46,   46,   46,   46,   46,  197,   46,    0,
   37,   37,   37,   37,   37,   37,    4,   37,    5,    6,
    7,    8,    0,   72,   72,   72,   72,    0,    0,   72,
   72,    0,  119,  120,  121,  122,   64,   65,  123,  124,
    0,   69,    0,    0,    0,   72,   72,   72,   72,   72,
   72,   72,    0,   72,    0,  125,  126,  127,  128,  129,
  130,    0,  140,    0,  119,  120,  121,  122,    0,    0,
  123,  124,    5,    6,    7,    5,    6,    7,    0,    0,
    0,    0,    0,    0,    0,    0,    0,  125,  126,  127,
  128,  129,  130,   84,  140,    0,  119,  120,  121,  122,
   86,  140,  123,  124,    0,   85,    0,  119,  120,  121,
  122,    0,    0,  123,  124,  139,    0,    0,    0,  183,
  137,  135,    0,  136,  142,  138,  140,    0,    0,  119,
  120,  121,  122,    0,    0,  123,  124,  140,  134,  131,
  133,  132,    0,    0,   47,   47,    0,  139,    0,    0,
    0,    0,  137,  135,    0,  136,  142,  138,    0,  140,
    0,   47,   47,   47,   47,   47,   47,    0,  188,  141,
  134,  131,  133,  132,    0,    0,    0,    0,    0,    0,
  139,    0,    0,    0,    0,  137,  135,    0,  136,  142,
  138,    0,    0,    0,    0,    0,    0,    0,    0,  140,
    0,  141,  192,  134,  131,  133,  132,    0,    0,    0,
    0,    0,  139,    0,    0,    0,  198,  137,  135,    0,
  136,  142,  138,  139,    0,    0,    0,    0,  137,  135,
    0,  136,  142,  138,  141,  134,  131,  133,  132,    0,
    0,    0,    0,    0,    0,  139,  134,  131,  133,  132,
  137,  135,    0,  136,  142,  138,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,  141,    0,  134,    0,
  133,  132,    0,    0,    0,    0,    0,  141,  139,    0,
    0,    0,    0,  137,  135,    0,  136,  142,  138,    0,
   31,    0,    0,    0,   31,   31,   31,   31,   31,  141,
   31,  134,    0,  133,    0,    0,    0,    0,    0,    0,
    0,   31,   31,   31,   31,   31,   31,   73,   74,   75,
    0,    0,   17,    0,    0,    0,    0,    0,   76,   17,
    0,    0,  141,    0,   17,    0,    0,    0,  119,  120,
  121,  122,    0,    0,  123,  124,   31,    0,   17,    0,
    0,    0,    0,    0,    0,    0,    0,   32,    0,    0,
    0,   32,   32,   32,   32,   32,    0,   32,  140,    0,
  119,  120,  121,  122,    0,    0,  123,  124,   32,   32,
   32,   32,   32,   32,    0,    0,    0,    0,    0,   84,
    0,    0,    0,    0,    0,   65,   86,    0,    0,    0,
  140,   85,   65,  119,  120,  121,  122,   65,    0,  123,
  124,    0,    0,   32,   17,    0,    0,    0,    0,    0,
    0,   65,    0,    0,    0,    0,    0,   84,    0,    0,
    0,    0,    0,  140,   86,  119,  120,  121,  122,   85,
    0,  123,  124,   84,    0,    0,  119,  120,  121,  122,
   86,  148,  123,  124,    0,   85,   34,    0,    0,    0,
   34,   34,   34,   34,   34,  140,   34,    0,  119,  120,
  121,  122,    0,    0,  123,  124,  140,   34,   34,   34,
   34,   34,   34,    0,   84,    0,    0,   65,    0,    0,
    0,   86,    0,    0,    0,    0,   85,    0,  140,    0,
    0,  119,  120,  121,  122,    0,    0,    0,    0,    0,
    0,    0,   34,   31,   31,   31,   31,  112,    0,   31,
   31,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  140,   42,    0,    0,   42,   31,   31,   31,   31,
   31,   31,    0,    0,    0,    0,   17,   17,   17,   42,
   42,   42,   42,   42,   42,    0,    0,   17,   17,   17,
    0,    0,    0,    0,    0,    0,   17,    0,   17,   17,
   17,   17,    0,    0,    0,   30,  185,   30,   30,   30,
   32,   32,   32,   32,   42,    0,   32,   32,    0,    0,
    0,    0,   30,   30,   30,   30,   30,   30,    0,    0,
    0,    0,    0,   32,   32,   32,   32,   32,   32,    0,
    0,    0,    0,   73,   74,   75,    0,    0,    0,   65,
   65,   65,    0,    0,   76,   77,   78,   30,    0,    0,
   65,   65,   65,   79,    0,   80,   81,   82,   83,   65,
    0,   65,   65,   65,   65,    0,    0,    0,    0,    0,
    0,   73,   74,   75,    0,    0,    0,    0,    0,    0,
    0,    0,   76,   77,   78,    0,    0,   73,   74,   75,
    0,   79,    0,   80,   81,   82,   83,    0,   76,   34,
   34,   34,   34,    0,    0,   34,   34,    0,    0,    0,
    0,    0,    0,    0,   33,    0,   33,   33,   33,    0,
    0,    0,   34,   34,   34,   34,   34,   34,   73,   74,
   75,   33,   33,   33,   33,   33,   33,    0,    0,   76,
   77,   78,    0,    0,    0,    0,    0,    0,   79,    0,
   80,   81,   82,   83,    0,    0,    0,    0,   89,    0,
    0,   89,    0,    0,    0,    0,   33,   40,    0,    0,
   40,   42,   42,   42,   42,   89,   89,   42,   42,    0,
    0,    0,    0,    0,   40,   40,   40,   40,   40,   40,
    0,    0,    0,    0,   42,   42,   42,   42,   42,   42,
    0,    0,    0,    0,    0,   38,    0,    0,   38,    0,
   89,    0,    0,    0,   30,   30,   30,   30,    0,   40,
   30,   30,   38,   38,   38,   38,   38,   38,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   30,   30,   30,
   30,   30,   30,   43,    0,    0,   43,    0,    0,    0,
    0,   39,    0,    0,   39,    0,    0,   38,    0,    0,
   43,   43,   43,   43,   43,   43,    0,    0,   39,   39,
   39,   39,   39,   39,    0,    0,    0,    0,    0,    0,
   41,    0,    0,   41,    0,    0,    0,    0,    0,   44,
    0,    0,   44,    0,    0,   43,    0,   41,   41,   41,
   41,   41,   41,   39,    0,    0,   44,   44,    0,   44,
    0,   44,    0,    0,   84,    0,    0,    0,    0,    0,
    0,   86,    0,    0,    0,   45,   85,    0,   45,    0,
    0,    0,   41,   33,   33,   33,   33,    0,    0,   33,
   33,   44,   45,   45,    0,   45,   66,   45,    0,   66,
    0,    0,    0,    0,    0,    0,   33,   33,   33,   33,
   33,   33,    0,   66,   66,    0,   66,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   45,    0,    0,
    0,    0,    0,    0,    0,    0,   40,   40,   40,   40,
    0,    0,   40,   40,    0,    0,    0,    0,   66,    0,
   89,   89,   89,   89,   89,   89,    0,    0,    0,   40,
   40,   40,   40,   40,   40,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   38,   38,   38,   38,    0,    0,
   38,   38,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   38,   38,   38,
   38,   38,   38,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   43,   43,   43,   43,    0,    0,   43,   43,
   39,   39,   39,   39,    0,    0,   39,   39,    0,    0,
    0,    0,    0,    0,    0,   43,   43,   43,   43,   43,
   43,    0,    0,   39,   39,   39,   39,   39,   39,   41,
   41,   41,   41,    0,    0,   41,   41,    0,    0,    0,
    0,    0,    0,    0,   44,   44,    0,    0,    0,    0,
    0,    0,   41,   41,   41,   41,   41,   41,    0,    0,
    0,   44,   44,   44,   44,   44,   44,    0,   73,   74,
   75,    0,    0,    0,    0,    0,    0,    0,    0,   76,
   45,   45,    0,    0,    5,    6,    7,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,   45,   45,   45,
   45,   45,   45,  106,  106,    0,    0,  110,    0,    0,
  115,  116,  118,    0,    0,    0,    0,    0,   66,   66,
   66,   66,   66,   66,    0,    0,    0,    0,    0,    0,
  106,    0,    0,    0,  151,  152,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  158,  159,  160,  161,  162,
  163,    0,    0,  164,  165,  166,  167,  168,  169,  170,
  171,  172,  173,  174,  175,  176,  177,  178,  179,    0,
    0,    0,    0,    0,    0,    0,  181,    0,    0,  184,
    0,    0,  187,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,  193,    0,  194,    0,    0,    0,  196,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         37,
  125,   46,  123,   41,   42,   43,   44,   45,   46,   47,
   59,   78,   41,  257,   41,   44,   91,   44,   46,   44,
   58,   59,   60,   61,   62,   63,   91,   91,   91,   41,
   59,   68,   59,   41,   59,   37,   44,  104,  257,   41,
   42,   43,   44,   45,   37,   47,   91,   59,   41,   42,
   43,   44,   45,   91,   47,   93,   58,   59,   60,   61,
   62,   63,   44,   91,   41,   58,   59,   60,   61,   62,
   63,  273,  274,  275,  111,   41,  125,   59,   41,   40,
   37,   40,   59,    3,   41,   42,   43,   44,   45,   37,
   47,   93,   44,   59,   42,   43,   59,   45,   46,   47,
   93,   58,   59,   60,   61,   62,   63,   59,   40,   44,
   30,   59,   60,   61,   62,   63,   41,  154,  268,   44,
   41,   37,  272,   41,   59,  257,   42,   43,   37,   45,
   46,   47,  268,   42,   15,   16,   93,   46,   47,   41,
   41,   61,   44,   91,   60,   61,   62,   63,  273,  274,
  275,  276,   93,   37,   93,   93,  123,   41,   42,   43,
  268,   45,   46,   47,   37,  123,   86,  123,   41,   42,
   43,  125,   45,   46,   47,   91,   60,   61,   62,   63,
  123,  123,   91,   40,   40,  125,   37,   60,   61,   62,
   63,   42,   43,  268,   45,   46,   47,  125,  125,   41,
   40,  125,   44,  268,  268,  268,   40,   91,   44,   60,
   61,   62,   63,   59,   59,   59,   58,   59,   91,   61,
   59,   63,  260,  261,  262,  263,   37,  279,  266,  267,
   40,   42,   43,   41,   45,   46,   47,  278,   41,   59,
   91,   59,   93,   59,   59,  283,  284,  285,  286,  287,
  288,   93,  290,  182,  183,   41,    3,   31,  260,  261,
  262,  263,  290,   19,  266,  267,  195,  260,  261,  262,
  263,  200,   56,  266,  267,  273,  274,  275,  276,  112,
   91,  283,  284,  285,  286,  287,  288,  192,  290,   -1,
  283,  284,  285,  286,  287,  288,  271,  290,  273,  274,
  275,  276,   -1,  260,  261,  262,  263,   -1,   -1,  266,
  267,   -1,  260,  261,  262,  263,   57,   58,  266,  267,
   -1,   62,   -1,   -1,   -1,   66,  283,  284,  285,  286,
  287,  288,   -1,  290,   -1,  283,  284,  285,  286,  287,
  288,   -1,  290,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,  273,  274,  275,  273,  274,  275,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,  285,
  286,  287,  288,   33,  290,   -1,  260,  261,  262,  263,
   40,  290,  266,  267,   -1,   45,   -1,  260,  261,  262,
  263,   -1,   -1,  266,  267,   37,   -1,   -1,   -1,   41,
   42,   43,   -1,   45,   46,   47,  290,   -1,   -1,  260,
  261,  262,  263,   -1,   -1,  266,  267,  290,   60,   61,
   62,   63,   -1,   -1,  266,  267,   -1,   37,   -1,   -1,
   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,  290,
   -1,  283,  284,  285,  286,  287,  288,   -1,   58,   91,
   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,
   37,   -1,   -1,   -1,   -1,   42,   43,   -1,   45,   46,
   47,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  290,
   -1,   91,   59,   60,   61,   62,   63,   -1,   -1,   -1,
   -1,   -1,   37,   -1,   -1,   -1,   41,   42,   43,   -1,
   45,   46,   47,   37,   -1,   -1,   -1,   -1,   42,   43,
   -1,   45,   46,   47,   91,   60,   61,   62,   63,   -1,
   -1,   -1,   -1,   -1,   -1,   37,   60,   61,   62,   63,
   42,   43,   -1,   45,   46,   47,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   91,   -1,   60,   -1,
   62,   63,   -1,   -1,   -1,   -1,   -1,   91,   37,   -1,
   -1,   -1,   -1,   42,   43,   -1,   45,   46,   47,   -1,
   37,   -1,   -1,   -1,   41,   42,   43,   44,   45,   91,
   47,   60,   -1,   62,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   58,   59,   60,   61,   62,   63,  257,  258,  259,
   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,  268,   40,
   -1,   -1,   91,   -1,   45,   -1,   -1,   -1,  260,  261,
  262,  263,   -1,   -1,  266,  267,   93,   -1,   59,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   37,   -1,   -1,
   -1,   41,   42,   43,   44,   45,   -1,   47,  290,   -1,
  260,  261,  262,  263,   -1,   -1,  266,  267,   58,   59,
   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   33,
   -1,   -1,   -1,   -1,   -1,   33,   40,   -1,   -1,   -1,
  290,   45,   40,  260,  261,  262,  263,   45,   -1,  266,
  267,   -1,   -1,   93,  125,   -1,   -1,   -1,   -1,   -1,
   -1,   59,   -1,   -1,   -1,   -1,   -1,   33,   -1,   -1,
   -1,   -1,   -1,  290,   40,  260,  261,  262,  263,   45,
   -1,  266,  267,   33,   -1,   -1,  260,  261,  262,  263,
   40,   41,  266,  267,   -1,   45,   37,   -1,   -1,   -1,
   41,   42,   43,   44,   45,  290,   47,   -1,  260,  261,
  262,  263,   -1,   -1,  266,  267,  290,   58,   59,   60,
   61,   62,   63,   -1,   33,   -1,   -1,  125,   -1,   -1,
   -1,   40,   -1,   -1,   -1,   -1,   45,   -1,  290,   -1,
   -1,  260,  261,  262,  263,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   93,  260,  261,  262,  263,  123,   -1,  266,
  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  290,   41,   -1,   -1,   44,  283,  284,  285,  286,
  287,  288,   -1,   -1,   -1,   -1,  257,  258,  259,   58,
   59,   60,   61,   62,   63,   -1,   -1,  268,  269,  270,
   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,  279,  280,
  281,  282,   -1,   -1,   -1,   41,  125,   43,   44,   45,
  260,  261,  262,  263,   93,   -1,  266,  267,   -1,   -1,
   -1,   -1,   58,   59,   60,   61,   62,   63,   -1,   -1,
   -1,   -1,   -1,  283,  284,  285,  286,  287,  288,   -1,
   -1,   -1,   -1,  257,  258,  259,   -1,   -1,   -1,  257,
  258,  259,   -1,   -1,  268,  269,  270,   93,   -1,   -1,
  268,  269,  270,  277,   -1,  279,  280,  281,  282,  277,
   -1,  279,  280,  281,  282,   -1,   -1,   -1,   -1,   -1,
   -1,  257,  258,  259,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  268,  269,  270,   -1,   -1,  257,  258,  259,
   -1,  277,   -1,  279,  280,  281,  282,   -1,  268,  260,
  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   41,   -1,   43,   44,   45,   -1,
   -1,   -1,  283,  284,  285,  286,  287,  288,  257,  258,
  259,   58,   59,   60,   61,   62,   63,   -1,   -1,  268,
  269,  270,   -1,   -1,   -1,   -1,   -1,   -1,  277,   -1,
  279,  280,  281,  282,   -1,   -1,   -1,   -1,   41,   -1,
   -1,   44,   -1,   -1,   -1,   -1,   93,   41,   -1,   -1,
   44,  260,  261,  262,  263,   58,   59,  266,  267,   -1,
   -1,   -1,   -1,   -1,   58,   59,   60,   61,   62,   63,
   -1,   -1,   -1,   -1,  283,  284,  285,  286,  287,  288,
   -1,   -1,   -1,   -1,   -1,   41,   -1,   -1,   44,   -1,
   93,   -1,   -1,   -1,  260,  261,  262,  263,   -1,   93,
  266,  267,   58,   59,   60,   61,   62,   63,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,  285,
  286,  287,  288,   41,   -1,   -1,   44,   -1,   -1,   -1,
   -1,   41,   -1,   -1,   44,   -1,   -1,   93,   -1,   -1,
   58,   59,   60,   61,   62,   63,   -1,   -1,   58,   59,
   60,   61,   62,   63,   -1,   -1,   -1,   -1,   -1,   -1,
   41,   -1,   -1,   44,   -1,   -1,   -1,   -1,   -1,   41,
   -1,   -1,   44,   -1,   -1,   93,   -1,   58,   59,   60,
   61,   62,   63,   93,   -1,   -1,   58,   59,   -1,   61,
   -1,   63,   -1,   -1,   33,   -1,   -1,   -1,   -1,   -1,
   -1,   40,   -1,   -1,   -1,   41,   45,   -1,   44,   -1,
   -1,   -1,   93,  260,  261,  262,  263,   -1,   -1,  266,
  267,   93,   58,   59,   -1,   61,   41,   63,   -1,   44,
   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,  285,  286,
  287,  288,   -1,   58,   59,   -1,   61,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   93,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  260,  261,  262,  263,
   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,   93,   -1,
  283,  284,  285,  286,  287,  288,   -1,   -1,   -1,  283,
  284,  285,  286,  287,  288,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  260,  261,  262,  263,   -1,   -1,
  266,  267,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,  285,
  286,  287,  288,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  260,  261,  262,  263,   -1,   -1,  266,  267,
  260,  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  283,  284,  285,  286,  287,
  288,   -1,   -1,  283,  284,  285,  286,  287,  288,  260,
  261,  262,  263,   -1,   -1,  266,  267,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  266,  267,   -1,   -1,   -1,   -1,
   -1,   -1,  283,  284,  285,  286,  287,  288,   -1,   -1,
   -1,  283,  284,  285,  286,  287,  288,   -1,  257,  258,
  259,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,  268,
  266,  267,   -1,   -1,  273,  274,  275,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,  283,  284,  285,
  286,  287,  288,   77,   78,   -1,   -1,   81,   -1,   -1,
   84,   85,   86,   -1,   -1,   -1,   -1,   -1,  283,  284,
  285,  286,  287,  288,   -1,   -1,   -1,   -1,   -1,   -1,
  104,   -1,   -1,   -1,  108,  109,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  119,  120,  121,  122,  123,
  124,   -1,   -1,  127,  128,  129,  130,  131,  132,  133,
  134,  135,  136,  137,  138,  139,  140,  141,  142,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  150,   -1,   -1,  153,
   -1,   -1,  156,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,  186,   -1,  188,   -1,   -1,   -1,  192,
};
}
final static short YYFINAL=1;
final static short YYMAXTOKEN=292;
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
"IF","ELSE","WHILE","RETURN","FOR","DO","INC","DEC","DIVIDEEQUALS",
"TIMESEQUALS","MINUSEQUALS","PLUSEQUALS","IFX","POTENTIAL","\"unary_minus\"",
"CAST",
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
"increment :",
"increment : expression INC",
"increment : expression DEC",
"increment : expression PLUSEQUALS expression",
"increment : expression MINUSEQUALS expression",
"increment : expression TIMESEQUALS expression",
"increment : expression DIVIDEEQUALS expression",
"if_else : IF '(' expression ')' body ELSE body",
"if_else : IF '(' expression ')' body",
"ternary : expression '?' expression ':' expression",
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

//#line 359 "../../src/parser/parser.y"

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
//#line 725 "Parser.java"
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
{ yyval = (IfStatement) val_peek(0); }
break;
case 26:
//#line 146 "../../src/parser/parser.y"
{ yyval = (WhileStatement) val_peek(0); }
break;
case 27:
//#line 147 "../../src/parser/parser.y"
{ yyval = (ForStatement) val_peek(0); }
break;
case 28:
//#line 148 "../../src/parser/parser.y"
{ yyval = (DoWhileStatement) val_peek(0); }
break;
case 29:
//#line 149 "../../src/parser/parser.y"
{ yyval = (Return) val_peek(1); }
break;
case 30:
//#line 152 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"+",(Expression) val_peek(0)); }
break;
case 31:
//#line 154 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"*",(Expression) val_peek(0)); }
break;
case 32:
//#line 156 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"/",(Expression) val_peek(0)); }
break;
case 33:
//#line 158 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"-",(Expression) val_peek(0)); }
break;
case 34:
//#line 160 "../../src/parser/parser.y"
{ yyval = new Arithmetic(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"%",(Expression) val_peek(0)); }
break;
case 35:
//#line 162 "../../src/parser/parser.y"
{ yyval = (Assignment)val_peek(0); }
break;
case 36:
//#line 164 "../../src/parser/parser.y"
{ yyval = (Ternary) val_peek(0); }
break;
case 37:
//#line 166 "../../src/parser/parser.y"
{ yyval = new Power(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 38:
//#line 168 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"==",(Expression) val_peek(0)); }
break;
case 39:
//#line 170 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">",(Expression) val_peek(0)); }
break;
case 40:
//#line 172 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),">=",(Expression) val_peek(0)); }
break;
case 41:
//#line 174 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<",(Expression) val_peek(0)); }
break;
case 42:
//#line 176 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"<=",(Expression) val_peek(0)); }
break;
case 43:
//#line 178 "../../src/parser/parser.y"
{ yyval = new Comparison(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"!=",(Expression) val_peek(0)); }
break;
case 44:
//#line 180 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"&&",(Expression) val_peek(0)); }
break;
case 45:
//#line 182 "../../src/parser/parser.y"
{ yyval = new Logical(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2),"||",(Expression) val_peek(0)); }
break;
case 46:
//#line 184 "../../src/parser/parser.y"
{ yyval = new UnaryMinus(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 47:
//#line 186 "../../src/parser/parser.y"
{ yyval = new UnaryNot(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(0)); }
break;
case 48:
//#line 188 "../../src/parser/parser.y"
{ yyval = (Invocation) val_peek(0); }
break;
case 49:
//#line 189 "../../src/parser/parser.y"
{ yyval = (Expression) val_peek(1); }
break;
case 50:
//#line 190 "../../src/parser/parser.y"
{ yyval = new FieldAccess(scanner.getLine(), scanner.getColumn()
          																, (Expression) val_peek(2), ((Variable) val_peek(0)).getName()); }
break;
case 51:
//#line 192 "../../src/parser/parser.y"
{ yyval = (Cast) val_peek(0); }
break;
case 52:
//#line 193 "../../src/parser/parser.y"
{ yyval = (Indexing) val_peek(0); }
break;
case 53:
//#line 194 "../../src/parser/parser.y"
{ yyval = new IntLiteral(scanner.getLine(), scanner.getColumn()
          																, (Integer) val_peek(0)); }
break;
case 54:
//#line 196 "../../src/parser/parser.y"
{ yyval = new RealLiteral(scanner.getLine(), scanner.getColumn()
          																, (Double) val_peek(0)); }
break;
case 55:
//#line 198 "../../src/parser/parser.y"
{ yyval = new CharLiteral(scanner.getLine(), scanner.getColumn()
          																, (Character) val_peek(0)); }
break;
case 56:
//#line 200 "../../src/parser/parser.y"
{ yyval = new Variable(scanner.getLine(), scanner.getColumn()
          																, (String) val_peek(0)); }
break;
case 58:
//#line 204 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"+"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 59:
//#line 208 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(1), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(1),"-"
          																,new IntLiteral(scanner.getLine(),scanner.getColumn(),1))); }
break;
case 60:
//#line 212 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"+",(Expression)val_peek(0))); }
break;
case 61:
//#line 215 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"-",(Expression)val_peek(0))); }
break;
case 62:
//#line 218 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"*",(Expression)val_peek(0))); }
break;
case 63:
//#line 221 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
          																,(Expression) val_peek(2), new Arithmetic(scanner.getLine()
          																,scanner.getColumn(),(Expression)val_peek(2),"/",(Expression)val_peek(0))); }
break;
case 64:
//#line 226 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(2), (List<Statement>) val_peek(0)
																		, (Expression) val_peek(4)); }
break;
case 65:
//#line 229 "../../src/parser/parser.y"
{ yyval = new IfStatement(scanner.getLine(), scanner.getColumn()
																		, (List<Statement>) val_peek(0), new ArrayList<Statement>()
																		, (Expression) val_peek(2)); }
break;
case 66:
//#line 234 "../../src/parser/parser.y"
{ yyval = new Ternary(scanner.getLine(), scanner.getColumn()
																		,(Expression) val_peek(4), (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 67:
//#line 237 "../../src/parser/parser.y"
{ yyval = (List<Statement>)val_peek(1); }
break;
case 68:
//#line 238 "../../src/parser/parser.y"
{ List<Statement> list = new ArrayList<Statement>();
															  list.add((Statement)val_peek(0));
															  yyval = list; }
break;
case 69:
//#line 243 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																	  yyval = new WhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(2)); }
break;
case 70:
//#line 248 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(4));
																	  yyval = new DoWhileStatement(scanner.getLine(), scanner.getColumn()
																			, body, (Expression) val_peek(1)); }
break;
case 71:
//#line 253 "../../src/parser/parser.y"
{ List<Statement> body = new ArrayList<Statement>((List<Statement>)val_peek(0));
																			  yyval = new ForStatement(scanner.getLine(), scanner.getColumn()
																			       , (Statement) val_peek(5), (Expression) val_peek(4), (Assignment) val_peek(2), body); }
break;
case 72:
//#line 259 "../../src/parser/parser.y"
{ yyval = new Cast(scanner.getLine(), scanner.getColumn()
          																	,(Type) val_peek(2), (Expression) val_peek(0)); }
break;
case 73:
//#line 263 "../../src/parser/parser.y"
{ List<Expression> list = (List<Expression>) val_peek(2);
																	  list.add((Expression) val_peek(0));
																	  yyval = list; }
break;
case 74:
//#line 266 "../../src/parser/parser.y"
{ List<Expression> list = new ArrayList<>();
				 													  list.add((Expression)val_peek(0));
				 													  yyval = list; }
break;
case 75:
//#line 271 "../../src/parser/parser.y"
{ List<String> list = (List<String>) val_peek(2);
																	  if(list.contains((String)val_peek(0)))
																	  	new ErrorType(scanner.getLine(), scanner.getColumn()
																	  			, "The variable "+((String)val_peek(0))
																	  			+" has been already declared");
																	  list.add((String)val_peek(0));
																	  yyval = list; }
break;
case 76:
//#line 278 "../../src/parser/parser.y"
{ List<String> list = new ArrayList<String>();
		 															  list.add((String) val_peek(0));
		 															  yyval = list; }
break;
case 77:
//#line 283 "../../src/parser/parser.y"
{ yyval = CharType.getInstance(); }
break;
case 78:
//#line 284 "../../src/parser/parser.y"
{ yyval = IntType.getInstance(); }
break;
case 79:
//#line 285 "../../src/parser/parser.y"
{ yyval = RealType.getInstance(); }
break;
case 80:
//#line 288 "../../src/parser/parser.y"
{yyval = new Indexing(scanner.getLine()
																, scanner.getColumn(), (Expression)val_peek(3)
																,(Expression)val_peek(1));}
break;
case 81:
//#line 293 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 82:
//#line 296 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 83:
//#line 299 "../../src/parser/parser.y"
{ ArrayType array = ArrayType.orderArray(scanner.getLine(), scanner.getColumn()
															  , (Type) val_peek(3), (Integer) val_peek(1));
															  yyval = array; }
break;
case 84:
//#line 304 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1))
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 85:
//#line 309 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 86:
//#line 314 "../../src/parser/parser.y"
{ List<VariableDefinition> variables = new ArrayList<>();
															for(String str : (List<String>)val_peek(1)) 
																variables.add(new VariableDefinition(scanner.getLine(), scanner.getColumn()
																, str, (Type)val_peek(2)));
															yyval = variables; }
break;
case 87:
//#line 321 "../../src/parser/parser.y"
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
case 88:
//#line 330 "../../src/parser/parser.y"
{ yyval = new ArrayList<VariableDefinition>(); }
break;
case 89:
//#line 333 "../../src/parser/parser.y"
{ yyval = new Assignment(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(2), (Expression) val_peek(0)); }
break;
case 90:
//#line 337 "../../src/parser/parser.y"
{ yyval = new Read(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 91:
//#line 340 "../../src/parser/parser.y"
{ yyval = new Write(scanner.getLine(), scanner.getColumn(), (List<Expression>) val_peek(0)); }
break;
case 92:
//#line 343 "../../src/parser/parser.y"
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
case 93:
//#line 354 "../../src/parser/parser.y"
{ yyval = new Return(scanner.getLine(), scanner.getColumn()
														, (Expression) val_peek(0)); }
break;
//#line 1388 "Parser.java"
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
