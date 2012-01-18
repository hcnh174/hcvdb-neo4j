grammar Hcv;
options
{
  language=Java;
  output=AST;
  ASTLabelType=CommonTree;
}

tokens
{
TERM; // imaginary token
}

@lexer::header
{
package edu.hiro.hcv.sequences;
}

@parser::header
{
package edu.hiro.hcv.sequences;
}

@members
{

}

expr: orexpression EOF -> orexpression;

orexpression: andexpression (OR^ andexpression)*;
andexpression: notexpression (AND^ notexpression)*;
notexpression: NOT? atom;
atom
	: term
	| LPAREN! orexpression RPAREN!
	;
term: keyword operator value -> ^(TERM keyword operator value);

keyword: IDENTIFIER;

operator
  : EQUALS 
  | NOT_EQUALS
  | GTE
  | GT
  | LTE
  | LT
  ;
  
value
  : QUOTED_STRING
  | STRING
  ;

EQUALS: '=';
NOT_EQUALS: '!=';
GT: '>';
LT: '<';
LTE: '<=';
GTE: '>=';

LPAREN: '(';
RPAREN: ')';
AND: 'AND' | 'and';
OR: 'OR' | 'or';
NOT: 'NOT';
LETTER: 'a'..'z'|'A'..'Z'|'\u0080'..'\uFFFE';
DIGIT: '0'..'9';
DOT: '.';
QUOTE: '"';
//IDENTIFIER: ('a'..'z'|'A'..'Z'|'\u0080'..'\uFFFE')('a'..'z'|'A'..'Z'|'0'..'9'|'.'|'\u0080'..'\uFFFE')*;
IDENTIFIER: LETTER (LETTER | DIGIT | DOT)*;
STRING: (LETTER | DIGIT | DOT)+;
//STRING: ('a'..'z'|'A'..'Z'|'0'..'9'|'.')+;
//QUOTED_STRING: '"' (~('"' | '\t' | '\r' | '\n' | '(' | ')' ))* '"';
QUOTED_STRING: QUOTE (~(QUOTE | '\t' | '\r' | '\n' | '(' | ')' ))* QUOTE;
WHITESPACE: (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;};
