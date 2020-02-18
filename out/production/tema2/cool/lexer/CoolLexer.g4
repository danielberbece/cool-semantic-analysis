lexer grammar CoolLexer;

tokens { ERROR } 

@header{
    package cool.lexer;	
}

@members{
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
}

/* Fragment spune că acea categorie este utilizată doar în interiorul
 * analizorului lexical, nefiind trimisă mai departe analizorului sintactic.
 */	
fragment A:('a'|'A');
fragment B:('b'|'B');
fragment C:('c'|'C');
fragment D:('d'|'D');
fragment E:('e'|'E');
fragment F:('f'|'F');
fragment G:('g'|'G');
fragment H:('h'|'H');
fragment I:('i'|'I');
fragment J:('j'|'J');
fragment K:('k'|'K');
fragment L:('l'|'L');
fragment M:('m'|'M');
fragment N:('n'|'N');
fragment O:('o'|'O');
fragment P:('p'|'P');
fragment Q:('q'|'Q');
fragment R:('r'|'R');
fragment S:('s'|'S');
fragment T:('t'|'T');
fragment U:('u'|'U');
fragment V:('v'|'V');
fragment W:('w'|'W');
fragment X:('x'|'X');
fragment Y:('y'|'Y');
fragment Z:('z'|'Z');
fragment BIGLETTER: [A-Z];
fragment SMALLLETTER: [a-z];
fragment LETTER : [a-zA-Z];
fragment DIGIT : [0-9];
fragment DIGITS : DIGIT+;
fragment EXPONENT : 'e' ('+' | '-')? DIGITS;
fragment NEW_LINE : '\r'? '\n';
fragment ESC_SEQ : '\\'('b'|'t'|'n'|'f'|'r');

BOOL : 't' R U E | 'f' A L S E;
NOT : N O T;

CLASS : C L A S S;
INHERITS : I N H E R I T S;

IF : I F;
THEN : T H E N;
ELSE : E L S E;
FI: F I;

WHILE : W H I L E ;
LOOP : L O O P;
POOL : P O O L;

LET : L E T;
IN : I N;

CASE : C A S E;
OF : O F;
ESAC : E S A C;

NEW : N E W;
ISVOID : I S V O I D;


/* Tip, Identificator.
 */
TYPE : BIGLETTER (LETTER | '_' | DIGIT)*;
ID : SMALLLETTER (LETTER | '_' | DIGIT)*;

/* Număr întreg.
 */
INT : DIGIT+;

/* Șir de caractere.
 * 
 * Poate conține caracterul '"', doar precedat de backslash.
 * . reprezintă orice caracter în afară de EOF.
 * *? este operatorul non-greedy, care încarcă să consume caractere cât timp
 * nu a fost întâlnit caracterul ulterior, '"'.
 * 
 * Acoladele de la final pot conține secvențe arbitrare de cod Java,
 * care vor fi executate la întâlnirea acestui token.
 	'"' ('\\"' | .)*? '"';
	STRING : '"' ( ESC_SEQ | ~('\r'|'\n') )*? '"';
 */

STR_OPEN : '"' -> pushMode(IN_STR), more;


SEMICOLON : ';';

COLON : ':';

COMMA : ',';

DOT : '.';

AT : '@';

LPAREN : '(';

RPAREN : ')';

LBRACE : '{';

RBRACE : '}';

PLUS : '+';

MINUS : '-';

MULT : '*';

DIV : '/';

EQUAL : '=';

LT : '<';

LE : '<=';

NON : '~';

ASSIGN : '<-';

DO : '=>';


LINE_COMMENT
    : '--' .*? (NEW_LINE | EOF) -> skip
    ;


/* 
      (BLOCK_COMMENT | .)*?
      ('*)' | EOF { raiseError("EOF in comment"); }) -> skip
    ;
    BL_END : '*)' { raiseError("Unmatched *)"); };
*/
BLOCK_COMMENT : '(*' -> pushMode(BL_COMM), more;
BL_END : '*)' { raiseError("Unmatched *)"); };


WS
    :   [ \n\f\r\t]+ -> skip
    ;

UNKNOWN: . {raiseError("Invalid character: " + getText());};

mode BL_COMM;
SUDDENEND: EOF {raiseError("EOF in comment");};
START: '(*' -> pushMode(BL_COMM), more;
STOP: '*)' -> popMode, more;
CH: . -> more;

mode IN_STR;

ERR_NEWLINE : NEW_LINE {raiseError("Unterminated string constant");} -> popMode;
ERR_NULL_CHAR : '\u0000' {raiseError("String contains null character");};
ERR_EOF : EOF {raiseError("EOF in string constant");};

STRING : '"' { checkString(); } -> popMode;
ESCAPED : ('\\' NEW_LINE | ESC_SEQ | LETTER | DIGIT) {stringSize++;} -> more;
CHAR : (~('"')) {stringSize++;} -> more;