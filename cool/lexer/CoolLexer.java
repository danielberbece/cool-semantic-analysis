// Generated from cool/lexer/CoolLexer.g4 by ANTLR 4.7.2

    package cool.lexer;	

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CoolLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ERROR=1, BOOL=2, NOT=3, CLASS=4, INHERITS=5, IF=6, THEN=7, ELSE=8, FI=9, 
		WHILE=10, LOOP=11, POOL=12, LET=13, IN=14, CASE=15, OF=16, ESAC=17, NEW=18, 
		ISVOID=19, TYPE=20, ID=21, INT=22, SEMICOLON=23, COLON=24, COMMA=25, DOT=26, 
		AT=27, LPAREN=28, RPAREN=29, LBRACE=30, RBRACE=31, PLUS=32, MINUS=33, 
		MULT=34, DIV=35, EQUAL=36, LT=37, LE=38, NON=39, ASSIGN=40, DO=41, LINE_COMMENT=42, 
		BL_END=43, WS=44, UNKNOWN=45, SUDDENEND=46, ERR_NEWLINE=47, ERR_NULL_CHAR=48, 
		ERR_EOF=49, STRING=50, STR_OPEN=51, BLOCK_COMMENT=52;
	public static final int
		BL_COMM=1, IN_STR=2;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE", "BL_COMM", "IN_STR"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", 
			"O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "BIGLETTER", 
			"SMALLLETTER", "LETTER", "DIGIT", "DIGITS", "EXPONENT", "NEW_LINE", "ESC_SEQ", 
			"BOOL", "NOT", "CLASS", "INHERITS", "IF", "THEN", "ELSE", "FI", "WHILE", 
			"LOOP", "POOL", "LET", "IN", "CASE", "OF", "ESAC", "NEW", "ISVOID", "TYPE", 
			"ID", "INT", "STR_OPEN", "SEMICOLON", "COLON", "COMMA", "DOT", "AT", 
			"LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", "MULT", "DIV", 
			"EQUAL", "LT", "LE", "NON", "ASSIGN", "DO", "LINE_COMMENT", "BLOCK_COMMENT", 
			"BL_END", "WS", "UNKNOWN", "SUDDENEND", "START", "STOP", "CH", "ERR_NEWLINE", 
			"ERR_NULL_CHAR", "ERR_EOF", "STRING", "ESCAPED", "CHAR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "';'", 
			"':'", "','", "'.'", "'@'", "'('", "')'", "'{'", "'}'", "'+'", "'-'", 
			"'*'", "'/'", "'='", "'<'", "'<='", "'~'", "'<-'", "'=>'", null, null, 
			null, null, null, null, "'\u0000'", null, null, "'\"'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ERROR", "BOOL", "NOT", "CLASS", "INHERITS", "IF", "THEN", "ELSE", 
			"FI", "WHILE", "LOOP", "POOL", "LET", "IN", "CASE", "OF", "ESAC", "NEW", 
			"ISVOID", "TYPE", "ID", "INT", "SEMICOLON", "COLON", "COMMA", "DOT", 
			"AT", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "PLUS", "MINUS", "MULT", 
			"DIV", "EQUAL", "LT", "LE", "NON", "ASSIGN", "DO", "LINE_COMMENT", "BL_END", 
			"WS", "UNKNOWN", "SUDDENEND", "ERR_NEWLINE", "ERR_NULL_CHAR", "ERR_EOF", 
			"STRING", "STR_OPEN", "BLOCK_COMMENT"
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


		static int stringSize = 0;
	    private void raiseError(String msg) {
	        setText(msg);
	        setType(ERROR);
	    }

	    private void checkString() {
	    	if (stringSize > 1024) {
	    		raiseError("String constant too long");
	    	}
	    	stringSize = 0;
	    }


	public CoolLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "CoolLexer.g4"; }

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
		case 77:
			BL_END_action((RuleContext)_localctx, actionIndex);
			break;
		case 79:
			UNKNOWN_action((RuleContext)_localctx, actionIndex);
			break;
		case 80:
			SUDDENEND_action((RuleContext)_localctx, actionIndex);
			break;
		case 84:
			ERR_NEWLINE_action((RuleContext)_localctx, actionIndex);
			break;
		case 85:
			ERR_NULL_CHAR_action((RuleContext)_localctx, actionIndex);
			break;
		case 86:
			ERR_EOF_action((RuleContext)_localctx, actionIndex);
			break;
		case 87:
			STRING_action((RuleContext)_localctx, actionIndex);
			break;
		case 88:
			ESCAPED_action((RuleContext)_localctx, actionIndex);
			break;
		case 89:
			CHAR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void BL_END_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 raiseError("Unmatched *)"); 
			break;
		}
	}
	private void UNKNOWN_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			raiseError("Invalid character: " + getText());
			break;
		}
	}
	private void SUDDENEND_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			raiseError("EOF in comment");
			break;
		}
	}
	private void ERR_NEWLINE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 3:
			raiseError("Unterminated string constant");
			break;
		}
	}
	private void ERR_NULL_CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 4:
			raiseError("String contains null character");
			break;
		}
	}
	private void ERR_EOF_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 5:
			raiseError("EOF in string constant");
			break;
		}
	}
	private void STRING_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 6:
			 checkString(); 
			break;
		}
	}
	private void ESCAPED_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 7:
			stringSize++;
			break;
		}
	}
	private void CHAR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 8:
			stringSize++;
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\66\u0203\b\1\b\1"+
		"\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4"+
		"\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t"+
		"\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t"+
		"\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t"+
		"\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4"+
		"*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63"+
		"\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;"+
		"\4<\t<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G"+
		"\tG\4H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR"+
		"\4S\tS\4T\tT\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\3\2\3\2\3\3\3\3"+
		"\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f"+
		"\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3"+
		"\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \6 \u00f7\n \r"+
		" \16 \u00f8\3!\3!\5!\u00fd\n!\3!\3!\3\"\5\"\u0102\n\"\3\"\3\"\3#\3#\3"+
		"#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$\u0114\n$\3%\3%\3%\3%\3&\3&\3&\3"+
		"&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3)\3)\3)\3)\3)\3"+
		"*\3*\3*\3*\3*\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3.\3.\3.\3.\3"+
		".\3/\3/\3/\3/\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\62\3\62\3\62\3"+
		"\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\65\3"+
		"\65\3\65\3\66\3\66\3\66\3\66\7\66\u016c\n\66\f\66\16\66\u016f\13\66\3"+
		"\67\3\67\3\67\3\67\7\67\u0175\n\67\f\67\16\67\u0178\13\67\38\68\u017b"+
		"\n8\r8\168\u017c\39\39\39\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3"+
		"@\3@\3A\3A\3B\3B\3C\3C\3D\3D\3E\3E\3F\3F\3G\3G\3H\3H\3I\3I\3I\3J\3J\3"+
		"K\3K\3K\3L\3L\3L\3M\3M\3M\3M\7M\u01b1\nM\fM\16M\u01b4\13M\3M\3M\5M\u01b8"+
		"\nM\3M\3M\3N\3N\3N\3N\3N\3N\3O\3O\3O\3O\3O\3P\6P\u01c8\nP\rP\16P\u01c9"+
		"\3P\3P\3Q\3Q\3Q\3R\3R\3R\3S\3S\3S\3S\3S\3S\3T\3T\3T\3T\3T\3T\3U\3U\3U"+
		"\3U\3V\3V\3V\3V\3V\3W\3W\3W\3X\3X\3X\3Y\3Y\3Y\3Y\3Y\3Z\3Z\3Z\3Z\3Z\5Z"+
		"\u01f9\nZ\3Z\3Z\3Z\3Z\3[\3[\3[\3[\3[\3\u01b2\2\\\5\2\7\2\t\2\13\2\r\2"+
		"\17\2\21\2\23\2\25\2\27\2\31\2\33\2\35\2\37\2!\2#\2%\2\'\2)\2+\2-\2/\2"+
		"\61\2\63\2\65\2\67\29\2;\2=\2?\2A\2C\2E\2G\2I\4K\5M\6O\7Q\bS\tU\nW\13"+
		"Y\f[\r]\16_\17a\20c\21e\22g\23i\24k\25m\26o\27q\30s\65u\31w\32y\33{\34"+
		"}\35\177\36\u0081\37\u0083 \u0085!\u0087\"\u0089#\u008b$\u008d%\u008f"+
		"&\u0091\'\u0093(\u0095)\u0097*\u0099+\u009b,\u009d\66\u009f-\u00a1.\u00a3"+
		"/\u00a5\60\u00a7\2\u00a9\2\u00ab\2\u00ad\61\u00af\62\u00b1\63\u00b3\64"+
		"\u00b5\2\u00b7\2\5\2\3\4$\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2"+
		"HHhh\4\2IIii\4\2JJjj\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4"+
		"\2QQqq\4\2RRrr\4\2SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYy"+
		"y\4\2ZZzz\4\2[[{{\4\2\\\\||\3\2C\\\3\2c|\4\2C\\c|\3\2\62;\4\2--//\7\2"+
		"ddhhppttvv\5\2\13\f\16\17\"\"\3\2$$\2\u01ef\2I\3\2\2\2\2K\3\2\2\2\2M\3"+
		"\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2"+
		"\2\2[\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2"+
		"g\3\2\2\2\2i\3\2\2\2\2k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3"+
		"\2\2\2\2u\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3"+
		"\2\2\2\2\u0081\3\2\2\2\2\u0083\3\2\2\2\2\u0085\3\2\2\2\2\u0087\3\2\2\2"+
		"\2\u0089\3\2\2\2\2\u008b\3\2\2\2\2\u008d\3\2\2\2\2\u008f\3\2\2\2\2\u0091"+
		"\3\2\2\2\2\u0093\3\2\2\2\2\u0095\3\2\2\2\2\u0097\3\2\2\2\2\u0099\3\2\2"+
		"\2\2\u009b\3\2\2\2\2\u009d\3\2\2\2\2\u009f\3\2\2\2\2\u00a1\3\2\2\2\2\u00a3"+
		"\3\2\2\2\3\u00a5\3\2\2\2\3\u00a7\3\2\2\2\3\u00a9\3\2\2\2\3\u00ab\3\2\2"+
		"\2\4\u00ad\3\2\2\2\4\u00af\3\2\2\2\4\u00b1\3\2\2\2\4\u00b3\3\2\2\2\4\u00b5"+
		"\3\2\2\2\4\u00b7\3\2\2\2\5\u00b9\3\2\2\2\7\u00bb\3\2\2\2\t\u00bd\3\2\2"+
		"\2\13\u00bf\3\2\2\2\r\u00c1\3\2\2\2\17\u00c3\3\2\2\2\21\u00c5\3\2\2\2"+
		"\23\u00c7\3\2\2\2\25\u00c9\3\2\2\2\27\u00cb\3\2\2\2\31\u00cd\3\2\2\2\33"+
		"\u00cf\3\2\2\2\35\u00d1\3\2\2\2\37\u00d3\3\2\2\2!\u00d5\3\2\2\2#\u00d7"+
		"\3\2\2\2%\u00d9\3\2\2\2\'\u00db\3\2\2\2)\u00dd\3\2\2\2+\u00df\3\2\2\2"+
		"-\u00e1\3\2\2\2/\u00e3\3\2\2\2\61\u00e5\3\2\2\2\63\u00e7\3\2\2\2\65\u00e9"+
		"\3\2\2\2\67\u00eb\3\2\2\29\u00ed\3\2\2\2;\u00ef\3\2\2\2=\u00f1\3\2\2\2"+
		"?\u00f3\3\2\2\2A\u00f6\3\2\2\2C\u00fa\3\2\2\2E\u0101\3\2\2\2G\u0105\3"+
		"\2\2\2I\u0113\3\2\2\2K\u0115\3\2\2\2M\u0119\3\2\2\2O\u011f\3\2\2\2Q\u0128"+
		"\3\2\2\2S\u012b\3\2\2\2U\u0130\3\2\2\2W\u0135\3\2\2\2Y\u0138\3\2\2\2["+
		"\u013e\3\2\2\2]\u0143\3\2\2\2_\u0148\3\2\2\2a\u014c\3\2\2\2c\u014f\3\2"+
		"\2\2e\u0154\3\2\2\2g\u0157\3\2\2\2i\u015c\3\2\2\2k\u0160\3\2\2\2m\u0167"+
		"\3\2\2\2o\u0170\3\2\2\2q\u017a\3\2\2\2s\u017e\3\2\2\2u\u0183\3\2\2\2w"+
		"\u0185\3\2\2\2y\u0187\3\2\2\2{\u0189\3\2\2\2}\u018b\3\2\2\2\177\u018d"+
		"\3\2\2\2\u0081\u018f\3\2\2\2\u0083\u0191\3\2\2\2\u0085\u0193\3\2\2\2\u0087"+
		"\u0195\3\2\2\2\u0089\u0197\3\2\2\2\u008b\u0199\3\2\2\2\u008d\u019b\3\2"+
		"\2\2\u008f\u019d\3\2\2\2\u0091\u019f\3\2\2\2\u0093\u01a1\3\2\2\2\u0095"+
		"\u01a4\3\2\2\2\u0097\u01a6\3\2\2\2\u0099\u01a9\3\2\2\2\u009b\u01ac\3\2"+
		"\2\2\u009d\u01bb\3\2\2\2\u009f\u01c1\3\2\2\2\u00a1\u01c7\3\2\2\2\u00a3"+
		"\u01cd\3\2\2\2\u00a5\u01d0\3\2\2\2\u00a7\u01d3\3\2\2\2\u00a9\u01d9\3\2"+
		"\2\2\u00ab\u01df\3\2\2\2\u00ad\u01e3\3\2\2\2\u00af\u01e8\3\2\2\2\u00b1"+
		"\u01eb\3\2\2\2\u00b3\u01ee\3\2\2\2\u00b5\u01f8\3\2\2\2\u00b7\u01fe\3\2"+
		"\2\2\u00b9\u00ba\t\2\2\2\u00ba\6\3\2\2\2\u00bb\u00bc\t\3\2\2\u00bc\b\3"+
		"\2\2\2\u00bd\u00be\t\4\2\2\u00be\n\3\2\2\2\u00bf\u00c0\t\5\2\2\u00c0\f"+
		"\3\2\2\2\u00c1\u00c2\t\6\2\2\u00c2\16\3\2\2\2\u00c3\u00c4\t\7\2\2\u00c4"+
		"\20\3\2\2\2\u00c5\u00c6\t\b\2\2\u00c6\22\3\2\2\2\u00c7\u00c8\t\t\2\2\u00c8"+
		"\24\3\2\2\2\u00c9\u00ca\t\n\2\2\u00ca\26\3\2\2\2\u00cb\u00cc\t\13\2\2"+
		"\u00cc\30\3\2\2\2\u00cd\u00ce\t\f\2\2\u00ce\32\3\2\2\2\u00cf\u00d0\t\r"+
		"\2\2\u00d0\34\3\2\2\2\u00d1\u00d2\t\16\2\2\u00d2\36\3\2\2\2\u00d3\u00d4"+
		"\t\17\2\2\u00d4 \3\2\2\2\u00d5\u00d6\t\20\2\2\u00d6\"\3\2\2\2\u00d7\u00d8"+
		"\t\21\2\2\u00d8$\3\2\2\2\u00d9\u00da\t\22\2\2\u00da&\3\2\2\2\u00db\u00dc"+
		"\t\23\2\2\u00dc(\3\2\2\2\u00dd\u00de\t\24\2\2\u00de*\3\2\2\2\u00df\u00e0"+
		"\t\25\2\2\u00e0,\3\2\2\2\u00e1\u00e2\t\26\2\2\u00e2.\3\2\2\2\u00e3\u00e4"+
		"\t\27\2\2\u00e4\60\3\2\2\2\u00e5\u00e6\t\30\2\2\u00e6\62\3\2\2\2\u00e7"+
		"\u00e8\t\31\2\2\u00e8\64\3\2\2\2\u00e9\u00ea\t\32\2\2\u00ea\66\3\2\2\2"+
		"\u00eb\u00ec\t\33\2\2\u00ec8\3\2\2\2\u00ed\u00ee\t\34\2\2\u00ee:\3\2\2"+
		"\2\u00ef\u00f0\t\35\2\2\u00f0<\3\2\2\2\u00f1\u00f2\t\36\2\2\u00f2>\3\2"+
		"\2\2\u00f3\u00f4\t\37\2\2\u00f4@\3\2\2\2\u00f5\u00f7\5?\37\2\u00f6\u00f5"+
		"\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f6\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9"+
		"B\3\2\2\2\u00fa\u00fc\7g\2\2\u00fb\u00fd\t \2\2\u00fc\u00fb\3\2\2\2\u00fc"+
		"\u00fd\3\2\2\2\u00fd\u00fe\3\2\2\2\u00fe\u00ff\5A \2\u00ffD\3\2\2\2\u0100"+
		"\u0102\7\17\2\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3"+
		"\2\2\2\u0103\u0104\7\f\2\2\u0104F\3\2\2\2\u0105\u0106\7^\2\2\u0106\u0107"+
		"\t!\2\2\u0107H\3\2\2\2\u0108\u0109\7v\2\2\u0109\u010a\5\'\23\2\u010a\u010b"+
		"\5-\26\2\u010b\u010c\5\r\6\2\u010c\u0114\3\2\2\2\u010d\u010e\7h\2\2\u010e"+
		"\u010f\5\5\2\2\u010f\u0110\5\33\r\2\u0110\u0111\5)\24\2\u0111\u0112\5"+
		"\r\6\2\u0112\u0114\3\2\2\2\u0113\u0108\3\2\2\2\u0113\u010d\3\2\2\2\u0114"+
		"J\3\2\2\2\u0115\u0116\5\37\17\2\u0116\u0117\5!\20\2\u0117\u0118\5+\25"+
		"\2\u0118L\3\2\2\2\u0119\u011a\5\t\4\2\u011a\u011b\5\33\r\2\u011b\u011c"+
		"\5\5\2\2\u011c\u011d\5)\24\2\u011d\u011e\5)\24\2\u011eN\3\2\2\2\u011f"+
		"\u0120\5\25\n\2\u0120\u0121\5\37\17\2\u0121\u0122\5\23\t\2\u0122\u0123"+
		"\5\r\6\2\u0123\u0124\5\'\23\2\u0124\u0125\5\25\n\2\u0125\u0126\5+\25\2"+
		"\u0126\u0127\5)\24\2\u0127P\3\2\2\2\u0128\u0129\5\25\n\2\u0129\u012a\5"+
		"\17\7\2\u012aR\3\2\2\2\u012b\u012c\5+\25\2\u012c\u012d\5\23\t\2\u012d"+
		"\u012e\5\r\6\2\u012e\u012f\5\37\17\2\u012fT\3\2\2\2\u0130\u0131\5\r\6"+
		"\2\u0131\u0132\5\33\r\2\u0132\u0133\5)\24\2\u0133\u0134\5\r\6\2\u0134"+
		"V\3\2\2\2\u0135\u0136\5\17\7\2\u0136\u0137\5\25\n\2\u0137X\3\2\2\2\u0138"+
		"\u0139\5\61\30\2\u0139\u013a\5\23\t\2\u013a\u013b\5\25\n\2\u013b\u013c"+
		"\5\33\r\2\u013c\u013d\5\r\6\2\u013dZ\3\2\2\2\u013e\u013f\5\33\r\2\u013f"+
		"\u0140\5!\20\2\u0140\u0141\5!\20\2\u0141\u0142\5#\21\2\u0142\\\3\2\2\2"+
		"\u0143\u0144\5#\21\2\u0144\u0145\5!\20\2\u0145\u0146\5!\20\2\u0146\u0147"+
		"\5\33\r\2\u0147^\3\2\2\2\u0148\u0149\5\33\r\2\u0149\u014a\5\r\6\2\u014a"+
		"\u014b\5+\25\2\u014b`\3\2\2\2\u014c\u014d\5\25\n\2\u014d\u014e\5\37\17"+
		"\2\u014eb\3\2\2\2\u014f\u0150\5\t\4\2\u0150\u0151\5\5\2\2\u0151\u0152"+
		"\5)\24\2\u0152\u0153\5\r\6\2\u0153d\3\2\2\2\u0154\u0155\5!\20\2\u0155"+
		"\u0156\5\17\7\2\u0156f\3\2\2\2\u0157\u0158\5\r\6\2\u0158\u0159\5)\24\2"+
		"\u0159\u015a\5\5\2\2\u015a\u015b\5\t\4\2\u015bh\3\2\2\2\u015c\u015d\5"+
		"\37\17\2\u015d\u015e\5\r\6\2\u015e\u015f\5\61\30\2\u015fj\3\2\2\2\u0160"+
		"\u0161\5\25\n\2\u0161\u0162\5)\24\2\u0162\u0163\5/\27\2\u0163\u0164\5"+
		"!\20\2\u0164\u0165\5\25\n\2\u0165\u0166\5\13\5\2\u0166l\3\2\2\2\u0167"+
		"\u016d\59\34\2\u0168\u016c\5=\36\2\u0169\u016c\7a\2\2\u016a\u016c\5?\37"+
		"\2\u016b\u0168\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016a\3\2\2\2\u016c\u016f"+
		"\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016en\3\2\2\2\u016f"+
		"\u016d\3\2\2\2\u0170\u0176\5;\35\2\u0171\u0175\5=\36\2\u0172\u0175\7a"+
		"\2\2\u0173\u0175\5?\37\2\u0174\u0171\3\2\2\2\u0174\u0172\3\2\2\2\u0174"+
		"\u0173\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174\3\2\2\2\u0176\u0177\3\2"+
		"\2\2\u0177p\3\2\2\2\u0178\u0176\3\2\2\2\u0179\u017b\5?\37\2\u017a\u0179"+
		"\3\2\2\2\u017b\u017c\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		"r\3\2\2\2\u017e\u017f\7$\2\2\u017f\u0180\3\2\2\2\u0180\u0181\b9\2\2\u0181"+
		"\u0182\b9\3\2\u0182t\3\2\2\2\u0183\u0184\7=\2\2\u0184v\3\2\2\2\u0185\u0186"+
		"\7<\2\2\u0186x\3\2\2\2\u0187\u0188\7.\2\2\u0188z\3\2\2\2\u0189\u018a\7"+
		"\60\2\2\u018a|\3\2\2\2\u018b\u018c\7B\2\2\u018c~\3\2\2\2\u018d\u018e\7"+
		"*\2\2\u018e\u0080\3\2\2\2\u018f\u0190\7+\2\2\u0190\u0082\3\2\2\2\u0191"+
		"\u0192\7}\2\2\u0192\u0084\3\2\2\2\u0193\u0194\7\177\2\2\u0194\u0086\3"+
		"\2\2\2\u0195\u0196\7-\2\2\u0196\u0088\3\2\2\2\u0197\u0198\7/\2\2\u0198"+
		"\u008a\3\2\2\2\u0199\u019a\7,\2\2\u019a\u008c\3\2\2\2\u019b\u019c\7\61"+
		"\2\2\u019c\u008e\3\2\2\2\u019d\u019e\7?\2\2\u019e\u0090\3\2\2\2\u019f"+
		"\u01a0\7>\2\2\u01a0\u0092\3\2\2\2\u01a1\u01a2\7>\2\2\u01a2\u01a3\7?\2"+
		"\2\u01a3\u0094\3\2\2\2\u01a4\u01a5\7\u0080\2\2\u01a5\u0096\3\2\2\2\u01a6"+
		"\u01a7\7>\2\2\u01a7\u01a8\7/\2\2\u01a8\u0098\3\2\2\2\u01a9\u01aa\7?\2"+
		"\2\u01aa\u01ab\7@\2\2\u01ab\u009a\3\2\2\2\u01ac\u01ad\7/\2\2\u01ad\u01ae"+
		"\7/\2\2\u01ae\u01b2\3\2\2\2\u01af\u01b1\13\2\2\2\u01b0\u01af\3\2\2\2\u01b1"+
		"\u01b4\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b2\u01b0\3\2\2\2\u01b3\u01b7\3\2"+
		"\2\2\u01b4\u01b2\3\2\2\2\u01b5\u01b8\5E\"\2\u01b6\u01b8\7\2\2\3\u01b7"+
		"\u01b5\3\2\2\2\u01b7\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba\bM"+
		"\4\2\u01ba\u009c\3\2\2\2\u01bb\u01bc\7*\2\2\u01bc\u01bd\7,\2\2\u01bd\u01be"+
		"\3\2\2\2\u01be\u01bf\bN\5\2\u01bf\u01c0\bN\3\2\u01c0\u009e\3\2\2\2\u01c1"+
		"\u01c2\7,\2\2\u01c2\u01c3\7+\2\2\u01c3\u01c4\3\2\2\2\u01c4\u01c5\bO\6"+
		"\2\u01c5\u00a0\3\2\2\2\u01c6\u01c8\t\"\2\2\u01c7\u01c6\3\2\2\2\u01c8\u01c9"+
		"\3\2\2\2\u01c9\u01c7\3\2\2\2\u01c9\u01ca\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb"+
		"\u01cc\bP\4\2\u01cc\u00a2\3\2\2\2\u01cd\u01ce\13\2\2\2\u01ce\u01cf\bQ"+
		"\7\2\u01cf\u00a4\3\2\2\2\u01d0\u01d1\7\2\2\3\u01d1\u01d2\bR\b\2\u01d2"+
		"\u00a6\3\2\2\2\u01d3\u01d4\7*\2\2\u01d4\u01d5\7,\2\2\u01d5\u01d6\3\2\2"+
		"\2\u01d6\u01d7\bS\5\2\u01d7\u01d8\bS\3\2\u01d8\u00a8\3\2\2\2\u01d9\u01da"+
		"\7,\2\2\u01da\u01db\7+\2\2\u01db\u01dc\3\2\2\2\u01dc\u01dd\bT\t\2\u01dd"+
		"\u01de\bT\3\2\u01de\u00aa\3\2\2\2\u01df\u01e0\13\2\2\2\u01e0\u01e1\3\2"+
		"\2\2\u01e1\u01e2\bU\3\2\u01e2\u00ac\3\2\2\2\u01e3\u01e4\5E\"\2\u01e4\u01e5"+
		"\bV\n\2\u01e5\u01e6\3\2\2\2\u01e6\u01e7\bV\t\2\u01e7\u00ae\3\2\2\2\u01e8"+
		"\u01e9\7\2\2\2\u01e9\u01ea\bW\13\2\u01ea\u00b0\3\2\2\2\u01eb\u01ec\7\2"+
		"\2\3\u01ec\u01ed\bX\f\2\u01ed\u00b2\3\2\2\2\u01ee\u01ef\7$\2\2\u01ef\u01f0"+
		"\bY\r\2\u01f0\u01f1\3\2\2\2\u01f1\u01f2\bY\t\2\u01f2\u00b4\3\2\2\2\u01f3"+
		"\u01f4\7^\2\2\u01f4\u01f9\5E\"\2\u01f5\u01f9\5G#\2\u01f6\u01f9\5=\36\2"+
		"\u01f7\u01f9\5?\37\2\u01f8\u01f3\3\2\2\2\u01f8\u01f5\3\2\2\2\u01f8\u01f6"+
		"\3\2\2\2\u01f8\u01f7\3\2\2\2\u01f9\u01fa\3\2\2\2\u01fa\u01fb\bZ\16\2\u01fb"+
		"\u01fc\3\2\2\2\u01fc\u01fd\bZ\3\2\u01fd\u00b6\3\2\2\2\u01fe\u01ff\n#\2"+
		"\2\u01ff\u0200\b[\17\2\u0200\u0201\3\2\2\2\u0201\u0202\b[\3\2\u0202\u00b8"+
		"\3\2\2\2\22\2\3\4\u00f8\u00fc\u0101\u0113\u016b\u016d\u0174\u0176\u017c"+
		"\u01b2\u01b7\u01c9\u01f8\20\7\4\2\5\2\2\b\2\2\7\3\2\3O\2\3Q\3\3R\4\6\2"+
		"\2\3V\5\3W\6\3X\7\3Y\b\3Z\t\3[\n";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}