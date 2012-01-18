// $ANTLR 3.0.1 D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g 2012-01-18 23:21:05

package edu.hiro.hcv.sequences;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class HcvLexer extends Lexer {
    public static final int TERM=4;
    public static final int LT=16;
    public static final int LETTER=19;
    public static final int GTE=13;
    public static final int WHITESPACE=23;
    public static final int EQUALS=11;
    public static final int NOT=7;
    public static final int AND=6;
    public static final int Tokens=24;
    public static final int EOF=-1;
    public static final int NOT_EQUALS=12;
    public static final int LTE=15;
    public static final int LPAREN=8;
    public static final int QUOTE=22;
    public static final int RPAREN=9;
    public static final int IDENTIFIER=10;
    public static final int OR=5;
    public static final int GT=14;
    public static final int QUOTED_STRING=17;
    public static final int DIGIT=20;
    public static final int DOT=21;
    public static final int STRING=18;
    public HcvLexer() {;} 
    public HcvLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g"; }

    // $ANTLR start EQUALS
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:56:7: ( '=' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:56:9: '='
            {
            match('='); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end EQUALS

    // $ANTLR start NOT_EQUALS
    public final void mNOT_EQUALS() throws RecognitionException {
        try {
            int _type = NOT_EQUALS;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:57:11: ( '!=' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:57:13: '!='
            {
            match("!="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT_EQUALS

    // $ANTLR start GT
    public final void mGT() throws RecognitionException {
        try {
            int _type = GT;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:58:3: ( '>' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:58:5: '>'
            {
            match('>'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GT

    // $ANTLR start LT
    public final void mLT() throws RecognitionException {
        try {
            int _type = LT;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:59:3: ( '<' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:59:5: '<'
            {
            match('<'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LT

    // $ANTLR start LTE
    public final void mLTE() throws RecognitionException {
        try {
            int _type = LTE;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:60:4: ( '<=' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:60:6: '<='
            {
            match("<="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LTE

    // $ANTLR start GTE
    public final void mGTE() throws RecognitionException {
        try {
            int _type = GTE;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:61:4: ( '>=' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:61:6: '>='
            {
            match(">="); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GTE

    // $ANTLR start LPAREN
    public final void mLPAREN() throws RecognitionException {
        try {
            int _type = LPAREN;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:63:7: ( '(' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:63:9: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LPAREN

    // $ANTLR start RPAREN
    public final void mRPAREN() throws RecognitionException {
        try {
            int _type = RPAREN;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:64:7: ( ')' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:64:9: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RPAREN

    // $ANTLR start AND
    public final void mAND() throws RecognitionException {
        try {
            int _type = AND;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:65:4: ( 'AND' | 'and' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='A') ) {
                alt1=1;
            }
            else if ( (LA1_0=='a') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("65:1: AND : ( 'AND' | 'and' );", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:65:6: 'AND'
                    {
                    match("AND"); 


                    }
                    break;
                case 2 :
                    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:65:14: 'and'
                    {
                    match("and"); 


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end AND

    // $ANTLR start OR
    public final void mOR() throws RecognitionException {
        try {
            int _type = OR;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:66:3: ( 'OR' | 'or' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='O') ) {
                alt2=1;
            }
            else if ( (LA2_0=='o') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("66:1: OR : ( 'OR' | 'or' );", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:66:5: 'OR'
                    {
                    match("OR"); 


                    }
                    break;
                case 2 :
                    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:66:12: 'or'
                    {
                    match("or"); 


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end OR

    // $ANTLR start NOT
    public final void mNOT() throws RecognitionException {
        try {
            int _type = NOT;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:67:4: ( 'NOT' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:67:6: 'NOT'
            {
            match("NOT"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NOT

    // $ANTLR start LETTER
    public final void mLETTER() throws RecognitionException {
        try {
            int _type = LETTER;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:68:7: ( 'a' .. 'z' | 'A' .. 'Z' | '\\u0080' .. '\\uFFFE' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u0080' && input.LA(1)<='\uFFFE') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end LETTER

    // $ANTLR start DIGIT
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:69:6: ( '0' .. '9' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:69:8: '0' .. '9'
            {
            matchRange('0','9'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DIGIT

    // $ANTLR start DOT
    public final void mDOT() throws RecognitionException {
        try {
            int _type = DOT;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:70:4: ( '.' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:70:6: '.'
            {
            match('.'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DOT

    // $ANTLR start QUOTE
    public final void mQUOTE() throws RecognitionException {
        try {
            int _type = QUOTE;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:71:6: ( '\"' )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:71:8: '\"'
            {
            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUOTE

    // $ANTLR start IDENTIFIER
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:73:11: ( LETTER ( LETTER | DIGIT | DOT )* )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:73:13: LETTER ( LETTER | DIGIT | DOT )*
            {
            mLETTER(); 
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:73:20: ( LETTER | DIGIT | DOT )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0=='.'||(LA3_0>='0' && LA3_0<='9')||(LA3_0>='A' && LA3_0<='Z')||(LA3_0>='a' && LA3_0<='z')||(LA3_0>='\u0080' && LA3_0<='\uFFFE')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u0080' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IDENTIFIER

    // $ANTLR start STRING
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:74:7: ( ( LETTER | DIGIT | DOT )+ )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:74:9: ( LETTER | DIGIT | DOT )+
            {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:74:9: ( LETTER | DIGIT | DOT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0=='.'||(LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||(LA4_0>='a' && LA4_0<='z')||(LA4_0>='\u0080' && LA4_0<='\uFFFE')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:
            	    {
            	    if ( input.LA(1)=='.'||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||(input.LA(1)>='a' && input.LA(1)<='z')||(input.LA(1)>='\u0080' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STRING

    // $ANTLR start QUOTED_STRING
    public final void mQUOTED_STRING() throws RecognitionException {
        try {
            int _type = QUOTED_STRING;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:77:14: ( QUOTE (~ ( QUOTE | '\\t' | '\\r' | '\\n' | '(' | ')' ) )* QUOTE )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:77:16: QUOTE (~ ( QUOTE | '\\t' | '\\r' | '\\n' | '(' | ')' ) )* QUOTE
            {
            mQUOTE(); 
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:77:22: (~ ( QUOTE | '\\t' | '\\r' | '\\n' | '(' | ')' ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='\u0000' && LA5_0<='\b')||(LA5_0>='\u000B' && LA5_0<='\f')||(LA5_0>='\u000E' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='\'')||(LA5_0>='*' && LA5_0<='\uFFFE')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:77:23: ~ ( QUOTE | '\\t' | '\\r' | '\\n' | '(' | ')' )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\b')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='\'')||(input.LA(1)>='*' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            mQUOTE(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end QUOTED_STRING

    // $ANTLR start WHITESPACE
    public final void mWHITESPACE() throws RecognitionException {
        try {
            int _type = WHITESPACE;
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:78:11: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:78:13: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:78:13: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='\t' && LA6_0<='\n')||LA6_0=='\r'||LA6_0==' ') ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            channel=HIDDEN;

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WHITESPACE

    public void mTokens() throws RecognitionException {
        // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:8: ( EQUALS | NOT_EQUALS | GT | LT | LTE | GTE | LPAREN | RPAREN | AND | OR | NOT | LETTER | DIGIT | DOT | QUOTE | IDENTIFIER | STRING | QUOTED_STRING | WHITESPACE )
        int alt7=19;
        alt7 = dfa7.predict(input);
        switch (alt7) {
            case 1 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:10: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 2 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:17: NOT_EQUALS
                {
                mNOT_EQUALS(); 

                }
                break;
            case 3 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:28: GT
                {
                mGT(); 

                }
                break;
            case 4 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:31: LT
                {
                mLT(); 

                }
                break;
            case 5 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:34: LTE
                {
                mLTE(); 

                }
                break;
            case 6 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:38: GTE
                {
                mGTE(); 

                }
                break;
            case 7 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:42: LPAREN
                {
                mLPAREN(); 

                }
                break;
            case 8 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:49: RPAREN
                {
                mRPAREN(); 

                }
                break;
            case 9 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:56: AND
                {
                mAND(); 

                }
                break;
            case 10 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:60: OR
                {
                mOR(); 

                }
                break;
            case 11 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:63: NOT
                {
                mNOT(); 

                }
                break;
            case 12 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:67: LETTER
                {
                mLETTER(); 

                }
                break;
            case 13 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:74: DIGIT
                {
                mDIGIT(); 

                }
                break;
            case 14 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:80: DOT
                {
                mDOT(); 

                }
                break;
            case 15 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:84: QUOTE
                {
                mQUOTE(); 

                }
                break;
            case 16 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:90: IDENTIFIER
                {
                mIDENTIFIER(); 

                }
                break;
            case 17 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:101: STRING
                {
                mSTRING(); 

                }
                break;
            case 18 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:108: QUOTED_STRING
                {
                mQUOTED_STRING(); 

                }
                break;
            case 19 :
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:1:122: WHITESPACE
                {
                mWHITESPACE(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA7_eotS =
        "\3\uffff\1\22\1\24\2\uffff\6\26\1\34\1\36\1\37\5\uffff\1\42\1\uffff"+
        "\2\42\2\44\1\42\5\uffff\1\46\1\uffff\1\46\1\uffff\1\47\2\uffff";
    static final String DFA7_eofS =
        "\50\uffff";
    static final String DFA7_minS =
        "\1\11\2\uffff\2\75\2\uffff\10\56\1\0\5\uffff\1\56\1\uffff\5\56\5"+
        "\uffff\1\56\1\uffff\1\56\1\uffff\1\56\2\uffff";
    static final String DFA7_maxS =
        "\1\ufffe\2\uffff\2\75\2\uffff\11\ufffe\5\uffff\1\ufffe\1\uffff\5"+
        "\ufffe\5\uffff\1\ufffe\1\uffff\1\ufffe\1\uffff\1\ufffe\2\uffff";
    static final String DFA7_acceptS =
        "\1\uffff\1\1\1\2\2\uffff\1\7\1\10\11\uffff\1\23\1\6\1\3\1\5\1\4"+
        "\1\uffff\1\14\5\uffff\1\15\1\21\1\16\1\17\1\22\1\uffff\1\20\1\uffff"+
        "\1\12\1\uffff\1\11\1\13";
    static final String DFA7_specialS =
        "\50\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\20\2\uffff\1\20\22\uffff\1\20\1\2\1\17\5\uffff\1\5\1\6\4"+
            "\uffff\1\16\1\uffff\12\15\2\uffff\1\4\1\1\1\3\2\uffff\1\7\14"+
            "\14\1\13\1\11\13\14\6\uffff\1\10\15\14\1\12\13\14\5\uffff\uff7f"+
            "\14",
            "",
            "",
            "\1\21",
            "\1\23",
            "",
            "",
            "\1\27\1\uffff\12\27\7\uffff\15\27\1\25\14\27\6\uffff\32\27\5"+
            "\uffff\uff7f\27",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\15\27\1\30\14\27\5"+
            "\uffff\uff7f\27",
            "\1\27\1\uffff\12\27\7\uffff\21\27\1\31\10\27\6\uffff\32\27\5"+
            "\uffff\uff7f\27",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\21\27\1\32\10\27\5"+
            "\uffff\uff7f\27",
            "\1\27\1\uffff\12\27\7\uffff\16\27\1\33\13\27\6\uffff\32\27\5"+
            "\uffff\uff7f\27",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\32\27\5\uffff\uff7f"+
            "\27",
            "\1\35\1\uffff\12\35\7\uffff\32\35\6\uffff\32\35\5\uffff\uff7f"+
            "\35",
            "\1\35\1\uffff\12\35\7\uffff\32\35\6\uffff\32\35\5\uffff\uff7f"+
            "\35",
            "\11\40\2\uffff\2\40\1\uffff\32\40\2\uffff\uffd5\40",
            "",
            "",
            "",
            "",
            "",
            "\1\27\1\uffff\12\27\7\uffff\3\27\1\41\26\27\6\uffff\32\27\5"+
            "\uffff\uff7f\27",
            "",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\32\27\5\uffff\uff7f"+
            "\27",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\3\27\1\43\26\27\5"+
            "\uffff\uff7f\27",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\32\27\5\uffff\uff7f"+
            "\27",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\32\27\5\uffff\uff7f"+
            "\27",
            "\1\27\1\uffff\12\27\7\uffff\23\27\1\45\6\27\6\uffff\32\27\5"+
            "\uffff\uff7f\27",
            "",
            "",
            "",
            "",
            "",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\32\27\5\uffff\uff7f"+
            "\27",
            "",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\32\27\5\uffff\uff7f"+
            "\27",
            "",
            "\1\27\1\uffff\12\27\7\uffff\32\27\6\uffff\32\27\5\uffff\uff7f"+
            "\27",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( EQUALS | NOT_EQUALS | GT | LT | LTE | GTE | LPAREN | RPAREN | AND | OR | NOT | LETTER | DIGIT | DOT | QUOTE | IDENTIFIER | STRING | QUOTED_STRING | WHITESPACE );";
        }
    }
 

}