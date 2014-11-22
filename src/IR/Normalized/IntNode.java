package IR.Normalized;

import parsing.lexer.Token;

public class IntNode extends ExprNode {
    public IntNode(Token t) {
        super(t);
        evalType = tINTEGER;
    }
}
