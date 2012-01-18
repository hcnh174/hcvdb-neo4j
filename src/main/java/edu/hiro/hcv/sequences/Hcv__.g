lexer grammar Hcv;
options {
  language=Java;

}
@header {
package edu.hiro.hcv.sequences;
}

// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 56
EQUALS: '=';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 57
NOT_EQUALS: '!=';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 58
GT: '>';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 59
LT: '<';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 60
LTE: '<=';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 61
GTE: '>=';

// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 63
LPAREN: '(';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 64
RPAREN: ')';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 65
AND: 'AND' | 'and';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 66
OR: 'OR' | 'or';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 67
NOT: 'NOT';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 68
LETTER: 'a'..'z'|'A'..'Z'|'\u0080'..'\uFFFE';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 69
DIGIT: '0'..'9';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 70
DOT: '.';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 71
QUOTE: '"';
//IDENTIFIER: ('a'..'z'|'A'..'Z'|'\u0080'..'\uFFFE')('a'..'z'|'A'..'Z'|'0'..'9'|'.'|'\u0080'..'\uFFFE')*;
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 73
IDENTIFIER: LETTER (LETTER | DIGIT | DOT)*;
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 74
STRING: (LETTER | DIGIT | DOT)+;
//STRING: ('a'..'z'|'A'..'Z'|'0'..'9'|'.')+;
//QUOTED_STRING: '"' (~('"' | '\t' | '\r' | '\n' | '(' | ')' ))* '"';
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 77
QUOTED_STRING: QUOTE (~(QUOTE | '\t' | '\r' | '\n' | '(' | ')' ))* QUOTE;
// $ANTLR src "D:\workspace\hcvdb-neo4j\src\main\antlr\edu\hiro\hcv\sequences\Hcv.g" 78
WHITESPACE: (' '|'\t'|'\r'|'\n')+ {$channel=HIDDEN;};
