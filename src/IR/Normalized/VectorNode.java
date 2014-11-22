package IR.Normalized;

import java.util.List;

import parsing.lexer.Token;

public class VectorNode extends ExprNode {
    public VectorNode(Token t, List<ExprNode> elements) {
        super(t);
        evalType = tVECTOR;
        for (ExprNode e : elements) {
            addChild(e);
        }
    }
}
