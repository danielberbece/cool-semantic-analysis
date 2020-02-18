parser grammar CoolParser;

options {
    tokenVocab = CoolLexer;
}

@header{
    package cool.parser;
}

program
    :   (class_  SEMICOLON)+ EOF
    ;

class_
	:	CLASS type=TYPE (INHERITS inheritType=TYPE)? LBRACE (feature SEMICOLON)* RBRACE
	;

feature
    :   id=ID LPAREN (formals+=formal (COMMA formals+=formal)*)? RPAREN COLON type=TYPE LBRACE expr RBRACE  #method
    |   id=ID COLON type=TYPE (ASSIGN expr)?											  #attribute
    ;

formal
    :   name=ID COLON type=TYPE
    ;

expr
	: object=expr (AT TYPE)? DOT ID LPAREN (exprs+=expr (COMMA exprs+=expr)*)? RPAREN 	#dotExpr
	| ID LPAREN (exprs+=expr (COMMA exprs+=expr)*)? RPAREN 	#dispatchExpr
	| IF ifExpr=expr THEN thenExpr=expr ELSE elseExpr=expr FI 	#ifExpr
	| WHILE conditionExpr=expr LOOP bodyExpr=expr POOL 	#whileExpr
	| LBRACE (expr SEMICOLON)+ RBRACE 	#blockExpr
	| LET local (COMMA local)* IN body=expr 	#letExpr
	| CASE caseExpr=expr OF (ids+=ID COLON types+=TYPE DO exprs+=expr SEMICOLON)+ ESAC	#caseExpr
	| NEW TYPE 			#newTypeExpr
	| ISVOID expr 		#isvoidExpr
	| NON expr 			#nonExpr
	| expr MULT expr 	#multExpr
	| expr DIV expr 	#divExpr
	| expr PLUS expr 	#plusExpr
	| expr MINUS expr 	#minusExpr
	| expr LT expr 		#ltExpr
	| expr LE expr 		#leExpr
	| expr EQUAL expr 	#equalExpr
	| NOT expr 		#notExpr
	| LPAREN expr RPAREN 	#parenExpr
	| ID ASSIGN expr 	#assignExpr
	| ID 	#idExpr
    | INT 	#intExpr
    | STRING  #stringExpr
    | BOOL	#boolExpr
    ;

local
	:	ID COLON TYPE (ASSIGN expr)?
	;