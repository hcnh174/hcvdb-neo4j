// $ANTLR 3.0.1 D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g 2012-01-18 23:21:05

package edu.hiro.hcv.sequences;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;


import org.antlr.runtime.tree.*;

public class HcvParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "TERM", "OR", "AND", "NOT", "LPAREN", "RPAREN", "IDENTIFIER", "EQUALS", "NOT_EQUALS", "GTE", "GT", "LTE", "LT", "QUOTED_STRING", "STRING", "LETTER", "DIGIT", "DOT", "QUOTE", "WHITESPACE"
    };
    public static final int TERM=4;
    public static final int LT=16;
    public static final int LETTER=19;
    public static final int GTE=13;
    public static final int WHITESPACE=23;
    public static final int EQUALS=11;
    public static final int NOT=7;
    public static final int AND=6;
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

        public HcvParser(TokenStream input) {
            super(input);
        }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g"; }

    
    


    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start expr
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:29:1: expr : orexpression EOF -> orexpression ;
    public final expr_return expr() throws RecognitionException {
        expr_return retval = new expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token EOF2=null;
        orexpression_return orexpression1 = null;


        CommonTree EOF2_tree=null;
        RewriteRuleTokenStream stream_EOF=new RewriteRuleTokenStream(adaptor,"token EOF");
        RewriteRuleSubtreeStream stream_orexpression=new RewriteRuleSubtreeStream(adaptor,"rule orexpression");
        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:29:5: ( orexpression EOF -> orexpression )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:29:7: orexpression EOF
            {
            pushFollow(FOLLOW_orexpression_in_expr67);
            orexpression1=orexpression();
            _fsp--;

            stream_orexpression.add(orexpression1.getTree());
            EOF2=(Token)input.LT(1);
            match(input,EOF,FOLLOW_EOF_in_expr69); 
            stream_EOF.add(EOF2);


            // AST REWRITE
            // elements: orexpression
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 29:24: -> orexpression
            {
                adaptor.addChild(root_0, stream_orexpression.next());

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end expr

    public static class orexpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start orexpression
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:31:1: orexpression : andexpression ( OR andexpression )* ;
    public final orexpression_return orexpression() throws RecognitionException {
        orexpression_return retval = new orexpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token OR4=null;
        andexpression_return andexpression3 = null;

        andexpression_return andexpression5 = null;


        CommonTree OR4_tree=null;

        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:31:13: ( andexpression ( OR andexpression )* )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:31:15: andexpression ( OR andexpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_andexpression_in_orexpression80);
            andexpression3=andexpression();
            _fsp--;

            adaptor.addChild(root_0, andexpression3.getTree());
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:31:29: ( OR andexpression )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==OR) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:31:30: OR andexpression
            	    {
            	    OR4=(Token)input.LT(1);
            	    match(input,OR,FOLLOW_OR_in_orexpression83); 
            	    OR4_tree = (CommonTree)adaptor.create(OR4);
            	    root_0 = (CommonTree)adaptor.becomeRoot(OR4_tree, root_0);

            	    pushFollow(FOLLOW_andexpression_in_orexpression86);
            	    andexpression5=andexpression();
            	    _fsp--;

            	    adaptor.addChild(root_0, andexpression5.getTree());

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end orexpression

    public static class andexpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start andexpression
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:32:1: andexpression : notexpression ( AND notexpression )* ;
    public final andexpression_return andexpression() throws RecognitionException {
        andexpression_return retval = new andexpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token AND7=null;
        notexpression_return notexpression6 = null;

        notexpression_return notexpression8 = null;


        CommonTree AND7_tree=null;

        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:32:14: ( notexpression ( AND notexpression )* )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:32:16: notexpression ( AND notexpression )*
            {
            root_0 = (CommonTree)adaptor.nil();

            pushFollow(FOLLOW_notexpression_in_andexpression94);
            notexpression6=notexpression();
            _fsp--;

            adaptor.addChild(root_0, notexpression6.getTree());
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:32:30: ( AND notexpression )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==AND) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:32:31: AND notexpression
            	    {
            	    AND7=(Token)input.LT(1);
            	    match(input,AND,FOLLOW_AND_in_andexpression97); 
            	    AND7_tree = (CommonTree)adaptor.create(AND7);
            	    root_0 = (CommonTree)adaptor.becomeRoot(AND7_tree, root_0);

            	    pushFollow(FOLLOW_notexpression_in_andexpression100);
            	    notexpression8=notexpression();
            	    _fsp--;

            	    adaptor.addChild(root_0, notexpression8.getTree());

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end andexpression

    public static class notexpression_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start notexpression
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:33:1: notexpression : ( NOT )? atom ;
    public final notexpression_return notexpression() throws RecognitionException {
        notexpression_return retval = new notexpression_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NOT9=null;
        atom_return atom10 = null;


        CommonTree NOT9_tree=null;

        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:33:14: ( ( NOT )? atom )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:33:16: ( NOT )? atom
            {
            root_0 = (CommonTree)adaptor.nil();

            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:33:16: ( NOT )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==NOT) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:33:16: NOT
                    {
                    NOT9=(Token)input.LT(1);
                    match(input,NOT,FOLLOW_NOT_in_notexpression108); 
                    NOT9_tree = (CommonTree)adaptor.create(NOT9);
                    adaptor.addChild(root_0, NOT9_tree);


                    }
                    break;

            }

            pushFollow(FOLLOW_atom_in_notexpression111);
            atom10=atom();
            _fsp--;

            adaptor.addChild(root_0, atom10.getTree());

            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end notexpression

    public static class atom_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start atom
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:34:1: atom : ( term | LPAREN orexpression RPAREN );
    public final atom_return atom() throws RecognitionException {
        atom_return retval = new atom_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token LPAREN12=null;
        Token RPAREN14=null;
        term_return term11 = null;

        orexpression_return orexpression13 = null;


        CommonTree LPAREN12_tree=null;
        CommonTree RPAREN14_tree=null;

        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:35:2: ( term | LPAREN orexpression RPAREN )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==IDENTIFIER) ) {
                alt4=1;
            }
            else if ( (LA4_0==LPAREN) ) {
                alt4=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("34:1: atom : ( term | LPAREN orexpression RPAREN );", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:35:4: term
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_term_in_atom119);
                    term11=term();
                    _fsp--;

                    adaptor.addChild(root_0, term11.getTree());

                    }
                    break;
                case 2 :
                    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:36:4: LPAREN orexpression RPAREN
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    LPAREN12=(Token)input.LT(1);
                    match(input,LPAREN,FOLLOW_LPAREN_in_atom124); 
                    pushFollow(FOLLOW_orexpression_in_atom127);
                    orexpression13=orexpression();
                    _fsp--;

                    adaptor.addChild(root_0, orexpression13.getTree());
                    RPAREN14=(Token)input.LT(1);
                    match(input,RPAREN,FOLLOW_RPAREN_in_atom129); 

                    }
                    break;

            }
            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end atom

    public static class term_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start term
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:38:1: term : keyword operator value -> ^( TERM keyword operator value ) ;
    public final term_return term() throws RecognitionException {
        term_return retval = new term_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        keyword_return keyword15 = null;

        operator_return operator16 = null;

        value_return value17 = null;


        RewriteRuleSubtreeStream stream_keyword=new RewriteRuleSubtreeStream(adaptor,"rule keyword");
        RewriteRuleSubtreeStream stream_value=new RewriteRuleSubtreeStream(adaptor,"rule value");
        RewriteRuleSubtreeStream stream_operator=new RewriteRuleSubtreeStream(adaptor,"rule operator");
        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:38:5: ( keyword operator value -> ^( TERM keyword operator value ) )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:38:7: keyword operator value
            {
            pushFollow(FOLLOW_keyword_in_term138);
            keyword15=keyword();
            _fsp--;

            stream_keyword.add(keyword15.getTree());
            pushFollow(FOLLOW_operator_in_term140);
            operator16=operator();
            _fsp--;

            stream_operator.add(operator16.getTree());
            pushFollow(FOLLOW_value_in_term142);
            value17=value();
            _fsp--;

            stream_value.add(value17.getTree());

            // AST REWRITE
            // elements: value, operator, keyword
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 38:30: -> ^( TERM keyword operator value )
            {
                // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:38:33: ^( TERM keyword operator value )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(TERM, "TERM"), root_1);

                adaptor.addChild(root_1, stream_keyword.next());
                adaptor.addChild(root_1, stream_operator.next());
                adaptor.addChild(root_1, stream_value.next());

                adaptor.addChild(root_0, root_1);
                }

            }



            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end term

    public static class keyword_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start keyword
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:40:1: keyword : IDENTIFIER ;
    public final keyword_return keyword() throws RecognitionException {
        keyword_return retval = new keyword_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IDENTIFIER18=null;

        CommonTree IDENTIFIER18_tree=null;

        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:40:8: ( IDENTIFIER )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:40:10: IDENTIFIER
            {
            root_0 = (CommonTree)adaptor.nil();

            IDENTIFIER18=(Token)input.LT(1);
            match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_keyword161); 
            IDENTIFIER18_tree = (CommonTree)adaptor.create(IDENTIFIER18);
            adaptor.addChild(root_0, IDENTIFIER18_tree);


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end keyword

    public static class operator_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start operator
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:42:1: operator : ( EQUALS | NOT_EQUALS | GTE | GT | LTE | LT );
    public final operator_return operator() throws RecognitionException {
        operator_return retval = new operator_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set19=null;

        CommonTree set19_tree=null;

        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:43:3: ( EQUALS | NOT_EQUALS | GTE | GT | LTE | LT )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set19=(Token)input.LT(1);
            if ( (input.LA(1)>=EQUALS && input.LA(1)<=LT) ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set19));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_operator0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end operator

    public static class value_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start value
    // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:51:1: value : ( QUOTED_STRING | STRING );
    public final value_return value() throws RecognitionException {
        value_return retval = new value_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set20=null;

        CommonTree set20_tree=null;

        try {
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:52:3: ( QUOTED_STRING | STRING )
            // D:\\workspace\\hcvdb-neo4j\\src\\main\\antlr\\edu\\hiro\\hcv\\sequences\\Hcv.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set20=(Token)input.LT(1);
            if ( (input.LA(1)>=QUOTED_STRING && input.LA(1)<=STRING) ) {
                input.consume();
                adaptor.addChild(root_0, adaptor.create(set20));
                errorRecovery=false;
            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_value0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end value


 

    public static final BitSet FOLLOW_orexpression_in_expr67 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_expr69 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_andexpression_in_orexpression80 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_OR_in_orexpression83 = new BitSet(new long[]{0x0000000000000580L});
    public static final BitSet FOLLOW_andexpression_in_orexpression86 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_notexpression_in_andexpression94 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_AND_in_andexpression97 = new BitSet(new long[]{0x0000000000000580L});
    public static final BitSet FOLLOW_notexpression_in_andexpression100 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_NOT_in_notexpression108 = new BitSet(new long[]{0x0000000000000500L});
    public static final BitSet FOLLOW_atom_in_notexpression111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_term_in_atom119 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LPAREN_in_atom124 = new BitSet(new long[]{0x0000000000000580L});
    public static final BitSet FOLLOW_orexpression_in_atom127 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RPAREN_in_atom129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_term138 = new BitSet(new long[]{0x000000000001F800L});
    public static final BitSet FOLLOW_operator_in_term140 = new BitSet(new long[]{0x0000000000060000L});
    public static final BitSet FOLLOW_value_in_term142 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_keyword161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_value0 = new BitSet(new long[]{0x0000000000000002L});

}