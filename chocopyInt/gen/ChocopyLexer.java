// Generated from C:/Users/oscam/Desktop/InterpreteChocopy/Programming-Languages-2020/chocopyInt/grammar\Chocopy.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ChocopyLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SKIP_=1, COMMENT=2, SPACES=3, LINE_JOINING=4, CLASS=5, PRINT=6, PAR_IZQ=7, 
		PAR_DER=8, DOS_PUNTOS=9, NEWLINE=10, PASS=11, DEF=12, COMA=13, EJECUTA=14, 
		STRING=15, COR_IZQ=16, COR_DER=17, GLOBAL=18, NONLOCAL=19, ASIG=20, IF=21, 
		ELIF=22, ELSE=23, WHILE=24, FOR=25, IN=26, RETURN=27, NONE=28, TRUE=29, 
		FALSE=30, INTEGER=31, NOT=32, AND=33, OR=34, PUNTO=35, MENOS=36, SUMA=37, 
		MULTIPLICACION=38, DIVISION=39, MODULO=40, IGUAL=41, DIFERENTE=42, MENOR_IGUAL=43, 
		MAYOR_IGUAL=44, MENOR=45, MAYOR=46, IS=47, LEN=48, SELF=49, ID=50;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SKIP_", "COMMENT", "SPACES", "LINE_JOINING", "CLASS", "PRINT", "PAR_IZQ", 
			"PAR_DER", "DOS_PUNTOS", "NEWLINE", "PASS", "DEF", "COMA", "EJECUTA", 
			"STRING", "COR_IZQ", "COR_DER", "GLOBAL", "NONLOCAL", "ASIG", "IF", "ELIF", 
			"ELSE", "WHILE", "FOR", "IN", "RETURN", "NONE", "TRUE", "FALSE", "INTEGER", 
			"NOT", "AND", "OR", "PUNTO", "MENOS", "SUMA", "MULTIPLICACION", "DIVISION", 
			"MODULO", "IGUAL", "DIFERENTE", "MENOR_IGUAL", "MAYOR_IGUAL", "MENOR", 
			"MAYOR", "IS", "LEN", "SELF", "ID"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, "'class'", "'print'", "'('", "')'", "':'", 
			null, "'pass'", "'def'", "','", "'->'", null, "'['", "']'", "'global'", 
			"'nonlocal'", "'='", "'if'", "'elif'", "'else'", "'while'", "'for'", 
			"'in'", "'return'", "'None'", "'True'", "'False'", null, "'not'", "'and'", 
			"'or'", "'.'", "'-'", "'+'", "'*'", "'//'", "'%'", "'=='", "'!='", "'<='", 
			"'>='", "'<'", "'>'", "'is'", "'len'", "'self'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SKIP_", "COMMENT", "SPACES", "LINE_JOINING", "CLASS", "PRINT", 
			"PAR_IZQ", "PAR_DER", "DOS_PUNTOS", "NEWLINE", "PASS", "DEF", "COMA", 
			"EJECUTA", "STRING", "COR_IZQ", "COR_DER", "GLOBAL", "NONLOCAL", "ASIG", 
			"IF", "ELIF", "ELSE", "WHILE", "FOR", "IN", "RETURN", "NONE", "TRUE", 
			"FALSE", "INTEGER", "NOT", "AND", "OR", "PUNTO", "MENOS", "SUMA", "MULTIPLICACION", 
			"DIVISION", "MODULO", "IGUAL", "DIFERENTE", "MENOR_IGUAL", "MAYOR_IGUAL", 
			"MENOR", "MAYOR", "IS", "LEN", "SELF", "ID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	     // A queue where extra tokens are pushed on (see the NEWLINE lexer rule).
	     private java.util.LinkedList<Token> tokens = new java.util.LinkedList<>();
	     // The stack that keeps track of the indentation level.
	     private java.util.Stack<Integer> indents = new java.util.Stack<>();
	     // The amount of opened braces, brackets and parenthesis.
	     private int opened = 0;
	     // The most recently produced token.
	     private Token lastToken = null;
	     @Override
	     public void emit(Token t) {
	       super.setToken(t);
	       tokens.offer(t);
	     }

	     @Override
	     public Token nextToken() {
	       // Check if the end-of-file is ahead and there are still some DEDENTS expected.
	       if (_input.LA(1) == EOF && !this.indents.isEmpty()) {
	         // Remove any trailing EOF tokens from our buffer.
	         for (int i = tokens.size() - 1; i >= 0; i--) {
	           if (tokens.get(i).getType() == EOF) {
	             tokens.remove(i);
	           }
	         }

	         // First emit an extra line break that serves as the end of the statement.
	         this.emit(commonToken(ChocopyParser.NEWLINE, "\n"));

	         // Now emit as much DEDENT tokens as needed.
	         while (!indents.isEmpty()) {
	           this.emit(createDedent());
	           indents.pop();
	         }

	         // Put the EOF back on the token stream.
	         this.emit(commonToken(ChocopyParser.EOF, "<EOF>"));
	       }

	       Token next = super.nextToken();

	       if (next.getChannel() == Token.DEFAULT_CHANNEL) {
	         // Keep track of the last token on the default channel.
	         this.lastToken = next;
	       }

	       return tokens.isEmpty() ? next : tokens.poll();
	     }

	     private Token createDedent() {
	       CommonToken dedent = commonToken(ChocopyParser.DEDENT, "");
	       dedent.setLine(this.lastToken.getLine());
	       return dedent;
	     }

	     private CommonToken commonToken(int type, String text) {
	       int stop = this.getCharIndex() - 1;
	       int start = text.isEmpty() ? stop : stop - text.length() + 1;
	       return new CommonToken(this._tokenFactorySourcePair, type, DEFAULT_TOKEN_CHANNEL, start, stop);
	     }

	     // Calculates the indentation of the provided spaces, taking the
	     // following rules into account:
	     //
	     // "Tabs are replaced (from left to right) by one to eight spaces
	     //  such that the total number of characters up to and including
	     //  the replacement is a multiple of eight [...]"
	     //
	     //  -- https://docs.python.org/3.1/reference/lexical_analysis.html#indentation
	     static int getIndentationCount(String spaces) {
	       int count = 0;
	       for (char ch : spaces.toCharArray()) {
	         switch (ch) {
	           case '\t':
	             count += 8 - (count % 8);
	             break;
	           default:
	             // A normal space char.
	             count++;
	         }
	       }

	       return count;
	     }

	     boolean atStartOfInput() {
	       return super.getCharPositionInLine() == 0 && super.getLine() == 1;
	     }


	public ChocopyLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Chocopy.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 9:
			NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:

			        String newLine = getText().replaceAll("[^\r\n]+", "");
			        String spaces = getText().replaceAll("[\r\n]+", "");
			        int next = _input.LA(1);
			        if (opened > 0 || next == '\r' || next == '\n' || next == '#') {
			          // If we're inside a list or on a blank line, ignore all indents,
			          // dedents and line breaks.
			          skip();
			        }
			        else {
			          emit(commonToken(NEWLINE, newLine));
			          int indent = getIndentationCount(spaces);
			          int previous = indents.isEmpty() ? 0 : indents.peek();
			          if (indent == previous) {
			            // skip indents of the same size as the present indent-size
			            skip();
			          }
			          else if (indent > previous) {
			            indents.push(indent);
			            emit(commonToken(ChocopyParser.INDENT, spaces));
			          }
			          else {
			            // Possibly emit more than 1 DEDENT token.
			            while(!indents.isEmpty() && indents.peek() > indent) {
			              this.emit(createDedent());
			              indents.pop();
			            }
			          }
			        }
			      
			break;
		}
	}
	@Override
	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return NEWLINE_sempred((RuleContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean NEWLINE_sempred(RuleContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return atStartOfInput();
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\64\u0154\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\3\2"+
		"\3\2\3\2\5\2k\n\2\3\2\3\2\3\3\3\3\7\3q\n\3\f\3\16\3t\13\3\3\4\6\4w\n\4"+
		"\r\4\16\4x\3\5\3\5\5\5}\n\5\3\5\5\5\u0080\n\5\3\5\3\5\5\5\u0084\n\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n"+
		"\3\13\3\13\3\13\5\13\u009b\n\13\3\13\3\13\5\13\u009f\n\13\3\13\5\13\u00a2"+
		"\n\13\5\13\u00a4\n\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\7\20\u00c0\n\20\f\20\16\20\u00c3\13\20\3\20\3\20\3\21\3\21\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3"+
		"\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3"+
		"\35\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \7 \u0110"+
		"\n \f \16 \u0113\13 \3 \5 \u0116\n \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3#\3#"+
		"\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3*\3+\3+\3+\3,\3,\3"+
		",\3-\3-\3-\3.\3.\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\62\3\62\3"+
		"\62\3\62\3\62\3\63\5\63\u014d\n\63\3\63\7\63\u0150\n\63\f\63\16\63\u0153"+
		"\13\63\2\2\64\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34"+
		"\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64\3"+
		"\2\t\4\2\f\f\16\17\4\2\13\13\"\"\5\2\"#%]_\u0080\3\2\63;\3\2\62;\5\2C"+
		"\\aac|\6\2\62;C\\aac|\2\u0166\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2"+
		"\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2"+
		"+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2"+
		"\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2"+
		"C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3"+
		"\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2"+
		"\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\3j\3\2\2\2\5"+
		"n\3\2\2\2\7v\3\2\2\2\tz\3\2\2\2\13\u0085\3\2\2\2\r\u008b\3\2\2\2\17\u0091"+
		"\3\2\2\2\21\u0093\3\2\2\2\23\u0095\3\2\2\2\25\u00a3\3\2\2\2\27\u00a7\3"+
		"\2\2\2\31\u00ac\3\2\2\2\33\u00b0\3\2\2\2\35\u00b2\3\2\2\2\37\u00b5\3\2"+
		"\2\2!\u00c6\3\2\2\2#\u00c8\3\2\2\2%\u00ca\3\2\2\2\'\u00d1\3\2\2\2)\u00da"+
		"\3\2\2\2+\u00dc\3\2\2\2-\u00df\3\2\2\2/\u00e4\3\2\2\2\61\u00e9\3\2\2\2"+
		"\63\u00ef\3\2\2\2\65\u00f3\3\2\2\2\67\u00f6\3\2\2\29\u00fd\3\2\2\2;\u0102"+
		"\3\2\2\2=\u0107\3\2\2\2?\u0115\3\2\2\2A\u0117\3\2\2\2C\u011b\3\2\2\2E"+
		"\u011f\3\2\2\2G\u0122\3\2\2\2I\u0124\3\2\2\2K\u0126\3\2\2\2M\u0128\3\2"+
		"\2\2O\u012a\3\2\2\2Q\u012d\3\2\2\2S\u012f\3\2\2\2U\u0132\3\2\2\2W\u0135"+
		"\3\2\2\2Y\u0138\3\2\2\2[\u013b\3\2\2\2]\u013d\3\2\2\2_\u013f\3\2\2\2a"+
		"\u0142\3\2\2\2c\u0146\3\2\2\2e\u014c\3\2\2\2gk\5\7\4\2hk\5\5\3\2ik\5\t"+
		"\5\2jg\3\2\2\2jh\3\2\2\2ji\3\2\2\2kl\3\2\2\2lm\b\2\2\2m\4\3\2\2\2nr\7"+
		"%\2\2oq\n\2\2\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs\3\2\2\2s\6\3\2\2\2tr\3"+
		"\2\2\2uw\t\3\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\b\3\2\2\2z|"+
		"\7^\2\2{}\5\7\4\2|{\3\2\2\2|}\3\2\2\2}\u0083\3\2\2\2~\u0080\7\17\2\2\177"+
		"~\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0084\7\f\2\2\u0082"+
		"\u0084\7\17\2\2\u0083\177\3\2\2\2\u0083\u0082\3\2\2\2\u0084\n\3\2\2\2"+
		"\u0085\u0086\7e\2\2\u0086\u0087\7n\2\2\u0087\u0088\7c\2\2\u0088\u0089"+
		"\7u\2\2\u0089\u008a\7u\2\2\u008a\f\3\2\2\2\u008b\u008c\7r\2\2\u008c\u008d"+
		"\7t\2\2\u008d\u008e\7k\2\2\u008e\u008f\7p\2\2\u008f\u0090\7v\2\2\u0090"+
		"\16\3\2\2\2\u0091\u0092\7*\2\2\u0092\20\3\2\2\2\u0093\u0094\7+\2\2\u0094"+
		"\22\3\2\2\2\u0095\u0096\7<\2\2\u0096\24\3\2\2\2\u0097\u0098\6\13\2\2\u0098"+
		"\u00a4\5\7\4\2\u0099\u009b\7\17\2\2\u009a\u0099\3\2\2\2\u009a\u009b\3"+
		"\2\2\2\u009b\u009c\3\2\2\2\u009c\u009f\7\f\2\2\u009d\u009f\7\17\2\2\u009e"+
		"\u009a\3\2\2\2\u009e\u009d\3\2\2\2\u009f\u00a1\3\2\2\2\u00a0\u00a2\5\7"+
		"\4\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a4\3\2\2\2\u00a3"+
		"\u0097\3\2\2\2\u00a3\u009e\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5\u00a6\b\13"+
		"\3\2\u00a6\26\3\2\2\2\u00a7\u00a8\7r\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa"+
		"\7u\2\2\u00aa\u00ab\7u\2\2\u00ab\30\3\2\2\2\u00ac\u00ad\7f\2\2\u00ad\u00ae"+
		"\7g\2\2\u00ae\u00af\7h\2\2\u00af\32\3\2\2\2\u00b0\u00b1\7.\2\2\u00b1\34"+
		"\3\2\2\2\u00b2\u00b3\7/\2\2\u00b3\u00b4\7@\2\2\u00b4\36\3\2\2\2\u00b5"+
		"\u00c1\7$\2\2\u00b6\u00c0\t\4\2\2\u00b7\u00b8\7^\2\2\u00b8\u00c0\7$\2"+
		"\2\u00b9\u00ba\7^\2\2\u00ba\u00c0\7p\2\2\u00bb\u00bc\7^\2\2\u00bc\u00c0"+
		"\7v\2\2\u00bd\u00be\7^\2\2\u00be\u00c0\7^\2\2\u00bf\u00b6\3\2\2\2\u00bf"+
		"\u00b7\3\2\2\2\u00bf\u00b9\3\2\2\2\u00bf\u00bb\3\2\2\2\u00bf\u00bd\3\2"+
		"\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2"+
		"\u00c4\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c5\7$\2\2\u00c5 \3\2\2\2\u00c6"+
		"\u00c7\7]\2\2\u00c7\"\3\2\2\2\u00c8\u00c9\7_\2\2\u00c9$\3\2\2\2\u00ca"+
		"\u00cb\7i\2\2\u00cb\u00cc\7n\2\2\u00cc\u00cd\7q\2\2\u00cd\u00ce\7d\2\2"+
		"\u00ce\u00cf\7c\2\2\u00cf\u00d0\7n\2\2\u00d0&\3\2\2\2\u00d1\u00d2\7p\2"+
		"\2\u00d2\u00d3\7q\2\2\u00d3\u00d4\7p\2\2\u00d4\u00d5\7n\2\2\u00d5\u00d6"+
		"\7q\2\2\u00d6\u00d7\7e\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9\7n\2\2\u00d9"+
		"(\3\2\2\2\u00da\u00db\7?\2\2\u00db*\3\2\2\2\u00dc\u00dd\7k\2\2\u00dd\u00de"+
		"\7h\2\2\u00de,\3\2\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1\7n\2\2\u00e1\u00e2"+
		"\7k\2\2\u00e2\u00e3\7h\2\2\u00e3.\3\2\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6"+
		"\7n\2\2\u00e6\u00e7\7u\2\2\u00e7\u00e8\7g\2\2\u00e8\60\3\2\2\2\u00e9\u00ea"+
		"\7y\2\2\u00ea\u00eb\7j\2\2\u00eb\u00ec\7k\2\2\u00ec\u00ed\7n\2\2\u00ed"+
		"\u00ee\7g\2\2\u00ee\62\3\2\2\2\u00ef\u00f0\7h\2\2\u00f0\u00f1\7q\2\2\u00f1"+
		"\u00f2\7t\2\2\u00f2\64\3\2\2\2\u00f3\u00f4\7k\2\2\u00f4\u00f5\7p\2\2\u00f5"+
		"\66\3\2\2\2\u00f6\u00f7\7t\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7v\2\2\u00f9"+
		"\u00fa\7w\2\2\u00fa\u00fb\7t\2\2\u00fb\u00fc\7p\2\2\u00fc8\3\2\2\2\u00fd"+
		"\u00fe\7P\2\2\u00fe\u00ff\7q\2\2\u00ff\u0100\7p\2\2\u0100\u0101\7g\2\2"+
		"\u0101:\3\2\2\2\u0102\u0103\7V\2\2\u0103\u0104\7t\2\2\u0104\u0105\7w\2"+
		"\2\u0105\u0106\7g\2\2\u0106<\3\2\2\2\u0107\u0108\7H\2\2\u0108\u0109\7"+
		"c\2\2\u0109\u010a\7n\2\2\u010a\u010b\7u\2\2\u010b\u010c\7g\2\2\u010c>"+
		"\3\2\2\2\u010d\u0111\t\5\2\2\u010e\u0110\t\6\2\2\u010f\u010e\3\2\2\2\u0110"+
		"\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0116\3\2"+
		"\2\2\u0113\u0111\3\2\2\2\u0114\u0116\7\62\2\2\u0115\u010d\3\2\2\2\u0115"+
		"\u0114\3\2\2\2\u0116@\3\2\2\2\u0117\u0118\7p\2\2\u0118\u0119\7q\2\2\u0119"+
		"\u011a\7v\2\2\u011aB\3\2\2\2\u011b\u011c\7c\2\2\u011c\u011d\7p\2\2\u011d"+
		"\u011e\7f\2\2\u011eD\3\2\2\2\u011f\u0120\7q\2\2\u0120\u0121\7t\2\2\u0121"+
		"F\3\2\2\2\u0122\u0123\7\60\2\2\u0123H\3\2\2\2\u0124\u0125\7/\2\2\u0125"+
		"J\3\2\2\2\u0126\u0127\7-\2\2\u0127L\3\2\2\2\u0128\u0129\7,\2\2\u0129N"+
		"\3\2\2\2\u012a\u012b\7\61\2\2\u012b\u012c\7\61\2\2\u012cP\3\2\2\2\u012d"+
		"\u012e\7\'\2\2\u012eR\3\2\2\2\u012f\u0130\7?\2\2\u0130\u0131\7?\2\2\u0131"+
		"T\3\2\2\2\u0132\u0133\7#\2\2\u0133\u0134\7?\2\2\u0134V\3\2\2\2\u0135\u0136"+
		"\7>\2\2\u0136\u0137\7?\2\2\u0137X\3\2\2\2\u0138\u0139\7@\2\2\u0139\u013a"+
		"\7?\2\2\u013aZ\3\2\2\2\u013b\u013c\7>\2\2\u013c\\\3\2\2\2\u013d\u013e"+
		"\7@\2\2\u013e^\3\2\2\2\u013f\u0140\7k\2\2\u0140\u0141\7u\2\2\u0141`\3"+
		"\2\2\2\u0142\u0143\7n\2\2\u0143\u0144\7g\2\2\u0144\u0145\7p\2\2\u0145"+
		"b\3\2\2\2\u0146\u0147\7u\2\2\u0147\u0148\7g\2\2\u0148\u0149\7n\2\2\u0149"+
		"\u014a\7h\2\2\u014ad\3\2\2\2\u014b\u014d\t\7\2\2\u014c\u014b\3\2\2\2\u014d"+
		"\u0151\3\2\2\2\u014e\u0150\t\b\2\2\u014f\u014e\3\2\2\2\u0150\u0153\3\2"+
		"\2\2\u0151\u014f\3\2\2\2\u0151\u0152\3\2\2\2\u0152f\3\2\2\2\u0153\u0151"+
		"\3\2\2\2\24\2jrx|\177\u0083\u009a\u009e\u00a1\u00a3\u00bf\u00c1\u0111"+
		"\u0115\u014c\u014f\u0151\4\b\2\2\3\13\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}