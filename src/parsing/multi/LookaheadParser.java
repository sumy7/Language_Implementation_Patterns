package parsing.multi;

import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class LookaheadParser extends Parser {
    public LookaheadParser(Lexer input, int k) {
        super(input, k);
    }

    /**
     * list : '[' elements ']' ;
     */
    public void list() {
        match(Token.LBRACK);
        elements();
        match(Token.RBRACK);
    }

    /**
     * elements : element (',' element)* ;
     */
    void elements() {
        element();
        while (LA(1) == Token.COMMA) {
            match(Token.COMMA);
            element();
        }
    }

    /**
     * element : NAME '=' NAME | NAME | list ;
     */
    void element() {
        if (LA(1) == Token.NAME && LA(2) == Token.EQUALS) {
            match(Token.NAME);
            match(Token.EQUALS);
            match(Token.NAME);
        } else if (LA(1) == Token.NAME)
            match(Token.NAME);
        else if (LA(1) == Token.LBRACK)
            list();
        else
            throw new Error("expecting name or list; found " + LT(1));
    }
}
