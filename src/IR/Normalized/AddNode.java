package IR.Normalized;

import parsing.lexer.Token;

public class AddNode extends ExprNode {
    public AddNode(ExprNode left, Token addToken, ExprNode right) {
        super(addToken);
        addChild(left);
        addChild(right);
    }

    public int getEvalType() {
        return super.getEvalType();
    }
}
