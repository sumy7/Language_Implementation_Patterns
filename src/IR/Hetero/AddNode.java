package IR.Hetero;

import parsing.lexer.Token;

public class AddNode extends ExprNode {
    ExprNode left, right; // 不规则的子节点，有自定义的名字

    public AddNode(ExprNode left, Token addToken, ExprNode right) {
        super(addToken);
        this.left = left;
        this.right = right;
    }

    public String toStringTree() {
        if (left == null || right == null) {
            return this.toString();
        }
        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(this.toString());
        buf.append(" ");
        buf.append(left.toStringTree());
        buf.append(" ");
        buf.append(right.toStringTree());
        buf.append(")");
        return buf.toString();
    }

}
