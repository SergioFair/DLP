/* The following code was generated by JFlex 1.4.1 on 8/05/17 22:58 */

// ************  User Code  ********************

package scanner;
import parser.Parser;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.1
 * on 8/05/17 22:58 from the specification file
 * <tt>scanner/scanner.jflex</tt>
 */
public class Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\5\2\0\1\3\22\0\1\3\1\44\3\0\1\40"+
    "\1\45\1\7\1\40\1\40\1\6\1\14\1\40\1\13\1\11\1\4"+
    "\12\1\1\40\1\40\1\42\1\43\1\41\1\40\1\0\4\2\1\12"+
    "\25\2\1\40\1\10\1\40\1\47\2\0\1\30\1\23\1\26\1\20"+
    "\1\25\1\36\1\2\1\27\1\15\1\2\1\37\1\24\1\32\1\16"+
    "\1\21\2\2\1\31\1\34\1\17\1\22\1\33\1\35\3\2\1\40"+
    "\1\46\1\40\uff82\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\2\5\1\1\3\5"+
    "\15\3\5\5\2\1\1\6\1\7\1\0\1\4\1\0"+
    "\1\10\1\11\2\0\1\12\1\13\1\14\1\15\1\3"+
    "\1\16\1\17\1\3\1\20\15\3\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\27\1\30\1\7\2\0\1\31"+
    "\4\0\1\32\6\3\1\33\10\3\1\34\1\4\1\35"+
    "\1\36\1\37\1\40\3\3\1\41\1\42\1\43\1\3"+
    "\1\44\1\45\1\46\6\3\1\47\3\3\1\50\1\51"+
    "\1\52\1\3\1\53\1\54\1\55\1\56";

  private static int [] zzUnpackAction() {
    int [] result = new int[128];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\50\0\120\0\170\0\240\0\310\0\360\0\u0118"+
    "\0\u0140\0\u0168\0\u0190\0\u01b8\0\u01e0\0\u0208\0\u0230\0\u0258"+
    "\0\u0280\0\u02a8\0\u02d0\0\u02f8\0\u0320\0\u0348\0\u0370\0\u0398"+
    "\0\50\0\u03c0\0\u03e8\0\u0410\0\u0438\0\u0460\0\u0488\0\50"+
    "\0\u04b0\0\u04d8\0\u0500\0\u0528\0\50\0\50\0\u0550\0\u0578"+
    "\0\50\0\50\0\50\0\50\0\u05a0\0\170\0\u05c8\0\u05f0"+
    "\0\170\0\u0618\0\u0640\0\u0668\0\u0690\0\u06b8\0\u06e0\0\u0708"+
    "\0\u0730\0\u0758\0\u0780\0\u07a8\0\u07d0\0\u07f8\0\50\0\50"+
    "\0\50\0\50\0\50\0\50\0\50\0\50\0\u0820\0\u0820"+
    "\0\u0848\0\50\0\u0870\0\u0898\0\u08c0\0\u08e8\0\170\0\u0910"+
    "\0\u0938\0\u0960\0\u0988\0\u09b0\0\u09d8\0\170\0\u0a00\0\u0a28"+
    "\0\u0a50\0\u0a78\0\u0aa0\0\u0ac8\0\u0af0\0\u0b18\0\170\0\50"+
    "\0\50\0\50\0\50\0\50\0\u0b40\0\u0b68\0\u0b90\0\170"+
    "\0\170\0\170\0\u0bb8\0\170\0\170\0\170\0\u0be0\0\u0c08"+
    "\0\u0c30\0\u0c58\0\u0c80\0\u0ca8\0\170\0\u0cd0\0\u0cf8\0\u0d20"+
    "\0\170\0\170\0\170\0\u0d48\0\170\0\170\0\170\0\170";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[128];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\1\4\1\5\1\6\1\5\1\7\1\10"+
    "\1\2\1\11\1\4\1\12\1\13\1\14\2\4\1\15"+
    "\1\16\1\4\1\17\1\4\1\20\1\21\1\4\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\4\1\31"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\40\51\0"+
    "\1\3\7\0\1\41\1\42\12\0\1\42\23\0\2\4"+
    "\7\0\1\4\2\0\23\4\13\0\1\5\1\0\1\5"+
    "\46\0\1\43\1\0\1\44\34\0\1\45\12\0\1\40"+
    "\34\0\1\46\4\0\5\47\1\0\2\47\1\50\37\47"+
    "\1\0\1\41\61\0\1\51\27\0\1\52\20\0\1\53"+
    "\26\0\1\54\5\0\2\4\7\0\1\4\2\0\1\4"+
    "\1\55\17\4\1\56\1\4\11\0\2\4\7\0\1\4"+
    "\2\0\4\4\1\57\3\4\1\60\12\4\11\0\2\4"+
    "\7\0\1\4\2\0\14\4\1\61\6\4\11\0\2\4"+
    "\7\0\1\4\2\0\14\4\1\62\6\4\11\0\2\4"+
    "\7\0\1\4\2\0\7\4\1\63\13\4\11\0\2\4"+
    "\7\0\1\4\2\0\12\4\1\64\1\65\7\4\11\0"+
    "\2\4\7\0\1\4\2\0\1\4\1\66\21\4\11\0"+
    "\2\4\7\0\1\4\2\0\10\4\1\67\12\4\11\0"+
    "\2\4\7\0\1\4\2\0\13\4\1\70\7\4\11\0"+
    "\2\4\7\0\1\4\2\0\4\4\1\71\16\4\11\0"+
    "\2\4\7\0\1\4\2\0\2\4\1\72\15\4\1\73"+
    "\2\4\11\0\2\4\7\0\1\4\2\0\12\4\1\74"+
    "\1\4\1\75\6\4\11\0\2\4\7\0\1\4\2\0"+
    "\4\4\1\76\16\4\51\0\1\77\1\0\1\100\46\0"+
    "\1\101\1\102\47\0\1\103\47\0\1\104\51\0\1\105"+
    "\50\0\1\106\2\0\1\41\10\0\1\42\12\0\1\42"+
    "\23\0\1\107\11\0\2\110\33\0\5\43\1\0\42\43"+
    "\6\44\1\111\41\44\7\0\1\112\41\0\1\113\5\0"+
    "\1\112\6\0\1\114\1\115\11\0\1\116\17\0\2\4"+
    "\7\0\1\4\2\0\2\4\1\117\20\4\11\0\2\4"+
    "\7\0\1\4\2\0\5\4\1\120\15\4\11\0\2\4"+
    "\7\0\1\4\2\0\21\4\1\121\1\4\11\0\2\4"+
    "\7\0\1\4\2\0\10\4\1\122\12\4\11\0\2\4"+
    "\7\0\1\4\2\0\17\4\1\123\3\4\11\0\2\4"+
    "\7\0\1\4\2\0\13\4\1\124\7\4\11\0\2\4"+
    "\7\0\1\4\2\0\17\4\1\125\3\4\11\0\2\4"+
    "\7\0\1\4\2\0\3\4\1\126\17\4\11\0\2\4"+
    "\7\0\1\4\2\0\2\4\1\127\10\4\1\130\7\4"+
    "\11\0\2\4\7\0\1\4\2\0\1\131\22\4\11\0"+
    "\2\4\7\0\1\4\2\0\1\132\22\4\11\0\2\4"+
    "\7\0\1\4\2\0\14\4\1\133\6\4\11\0\2\4"+
    "\7\0\1\4\2\0\1\134\22\4\11\0\2\4\7\0"+
    "\1\4\2\0\1\135\22\4\11\0\2\4\7\0\1\4"+
    "\2\0\1\136\22\4\11\0\2\4\7\0\1\4\2\0"+
    "\14\4\1\137\6\4\11\0\1\107\46\0\4\44\1\140"+
    "\1\44\1\111\41\44\1\0\1\113\5\0\1\141\47\0"+
    "\1\142\47\0\1\143\47\0\1\144\41\0\2\4\7\0"+
    "\1\4\2\0\6\4\1\145\14\4\11\0\2\4\7\0"+
    "\1\4\2\0\13\4\1\146\7\4\11\0\2\4\7\0"+
    "\1\4\2\0\13\4\1\147\7\4\11\0\2\4\7\0"+
    "\1\4\2\0\10\4\1\150\12\4\11\0\2\4\7\0"+
    "\1\4\2\0\14\4\1\151\6\4\11\0\2\4\7\0"+
    "\1\4\2\0\10\4\1\152\12\4\11\0\2\4\7\0"+
    "\1\4\2\0\5\4\1\153\15\4\11\0\2\4\7\0"+
    "\1\4\2\0\3\4\1\154\17\4\11\0\2\4\7\0"+
    "\1\4\2\0\1\4\1\155\21\4\11\0\2\4\7\0"+
    "\1\4\2\0\3\4\1\156\17\4\11\0\2\4\7\0"+
    "\1\4\2\0\5\4\1\157\15\4\11\0\2\4\7\0"+
    "\1\4\2\0\2\4\1\160\20\4\11\0\2\4\7\0"+
    "\1\4\2\0\7\4\1\161\13\4\11\0\2\4\7\0"+
    "\1\4\2\0\2\4\1\162\20\4\11\0\2\4\7\0"+
    "\1\4\2\0\7\4\1\163\13\4\11\0\2\4\7\0"+
    "\1\4\2\0\5\4\1\164\15\4\11\0\2\4\7\0"+
    "\1\4\2\0\22\4\1\165\11\0\2\4\7\0\1\4"+
    "\2\0\14\4\1\166\6\4\11\0\2\4\7\0\1\4"+
    "\2\0\11\4\1\167\11\4\11\0\2\4\7\0\1\4"+
    "\2\0\11\4\1\170\11\4\11\0\2\4\7\0\1\4"+
    "\2\0\10\4\1\171\12\4\11\0\2\4\7\0\1\4"+
    "\2\0\10\4\1\172\12\4\11\0\2\4\7\0\1\4"+
    "\2\0\10\4\1\173\12\4\11\0\2\4\7\0\1\4"+
    "\2\0\7\4\1\174\13\4\11\0\2\4\7\0\1\4"+
    "\2\0\1\4\1\175\21\4\11\0\2\4\7\0\1\4"+
    "\2\0\2\4\1\176\20\4\11\0\2\4\7\0\1\4"+
    "\2\0\12\4\1\177\10\4\11\0\2\4\7\0\1\4"+
    "\2\0\2\4\1\200\20\4\10\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3440];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\26\1\1\11\6\1\1\11\1\1\1\0"+
    "\1\1\1\0\2\11\2\0\4\11\22\1\10\11\1\1"+
    "\2\0\1\11\4\0\21\1\5\11\34\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[128];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the textposition at the last state to be included in yytext */
  private int zzPushbackPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
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




  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Scanner(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  public Scanner(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 126) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzPushbackPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead < 0) {
      return true;
    }
    else {
      zzEndRead+= numRead;
      return false;
    }
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = zzPushbackPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public int yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = zzLexicalState;


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 40: 
          { yylval = "while";
					  return Parser.WHILE;
          }
        case 47: break;
        case 27: 
          { yylval = "and";
					  return Parser.AND;
          }
        case 48: break;
        case 1: 
          { System.err.println("Lexical error with element "+yytext()+" in line "+getLine()+" and column "+getColumn());
          }
        case 49: break;
        case 44: 
          { yylval = "struct";
					  return Parser.STRUCT;
          }
        case 50: break;
        case 39: 
          { yylval = "break";
					  return Parser.BREAK;
          }
        case 51: break;
        case 31: 
          { this.yylval = '\t';
					  return Parser.CHAR_CONSTANT;
          }
        case 52: break;
        case 28: 
          { yylval = "for";
					  return Parser.FOR;
          }
        case 53: break;
        case 29: 
          { this.yylval = (char)Integer.parseInt(yytext().split("'")[1].substring(1));
					  return Parser.CHAR_CONSTANT;
          }
        case 54: break;
        case 36: 
          { yylval = "read";
					  return Parser.READ;
          }
        case 55: break;
        case 38: 
          { yylval = "void";
					  return Parser.VOID;
          }
        case 56: break;
        case 34: 
          { yylval = "char";
					  return Parser.CHAR;
          }
        case 57: break;
        case 9: 
          { this.yylval = yytext();
					  return Parser.TIMESEQUALS;
          }
        case 58: break;
        case 3: 
          { this.yylval = yytext();
					  return Parser.ID;
          }
        case 59: break;
        case 23: 
          { this.yylval = yytext();
					  return Parser.AND;
          }
        case 60: break;
        case 2: 
          { this.yylval = new Integer(yytext());
         			  return Parser.INT_CONSTANT;
          }
        case 61: break;
        case 35: 
          { yylval = "case";
					  return Parser.CASE;
          }
        case 62: break;
        case 22: 
          { this.yylval = yytext();
					  return Parser.DIFFERENT;
          }
        case 63: break;
        case 6: 
          { this.yylval = yytext();
					  return Parser.POTENTIAL;
          }
        case 64: break;
        case 41: 
          { yylval = "write";
					  return Parser.WRITE;
          }
        case 65: break;
        case 11: 
          { this.yylval = yytext();
					  return Parser.MINUSEQUALS;
          }
        case 66: break;
        case 30: 
          { this.yylval = '\n';
					  return Parser.CHAR_CONSTANT;
          }
        case 67: break;
        case 26: 
          { yylval = "int";
					  return Parser.INT;
          }
        case 68: break;
        case 33: 
          { yylval = "else";
					  return Parser.ELSE;
          }
        case 69: break;
        case 18: 
          { this.yylval = yytext();
					  return Parser.GEQ;
          }
        case 70: break;
        case 32: 
          { this.yylval = '\r';
					  return Parser.CHAR_CONSTANT;
          }
        case 71: break;
        case 19: 
          { this.yylval = yytext();
					  return Parser.SHIFTLEFT;
          }
        case 72: break;
        case 14: 
          { yylval = "if";
					  return Parser.IF;
          }
        case 73: break;
        case 20: 
          { this.yylval = yytext();
					  return Parser.LEQ;
          }
        case 74: break;
        case 43: 
          { yylval = "return";
					  return Parser.RETURN;
          }
        case 75: break;
        case 21: 
          { this.yylval = yytext();
					  return Parser.DOUBLEEQUALS;
          }
        case 76: break;
        case 5: 
          { this.yylval = yytext();
															  		  		  			  return (int) yytext().charAt(0);
          }
        case 77: break;
        case 37: 
          { yylval = "main";
					  return Parser.MAIN;
          }
        case 78: break;
        case 17: 
          { this.yylval = yytext();
					  return Parser.SHIFTRIGHT;
          }
        case 79: break;
        case 46: 
          { yylval = "default";
					  return Parser.DEFAULT;
          }
        case 80: break;
        case 15: 
          { yylval = "do";
					  return Parser.DO;
          }
        case 81: break;
        case 16: 
          { yylval = "or";
					  return Parser.OR;
          }
        case 82: break;
        case 7: 
          { this.yylval = new Double(yytext());
					  return Parser.REAL_CONSTANT;
          }
        case 83: break;
        case 42: 
          { yylval = "double";
					  return Parser.DOUBLE;
          }
        case 84: break;
        case 8: 
          { this.yylval = yytext();
					  return Parser.DIVIDEEQUALS;
          }
        case 85: break;
        case 13: 
          { this.yylval = yytext();
					  return Parser.PLUSEQUALS;
          }
        case 86: break;
        case 10: 
          { this.yylval = yytext();
					  return Parser.DEC;
          }
        case 87: break;
        case 12: 
          { this.yylval = yytext();
					  return Parser.INC;
          }
        case 88: break;
        case 25: 
          { this.yylval = yytext().charAt(1);
					  return Parser.CHAR_CONSTANT;
          }
        case 89: break;
        case 45: 
          { yylval = "switch";
					  return Parser.SWITCH;
          }
        case 90: break;
        case 4: 
          { 
          }
        case 91: break;
        case 24: 
          { this.yylval = yytext();
					  return Parser.OR;
          }
        case 92: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return 0; }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
