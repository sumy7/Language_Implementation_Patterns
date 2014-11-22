package IR;

import parsing.lexer.Token;

public class TokenNode extends ParseTree {

    public Token value;

    public TokenNode(Token value) {
        this.value = value;
    }

}
