package IR.Normalized;

import parsing.lexer.Token;
import IR.Homo.AST;

public class ExprNode extends AST {
    public static final int tINVALID = 0;// 非法表达式
    public static final int tINTEGER = 1;// 整数表达式
    public static final int tVECTOR = 2;// 向量表达式

    /**
     * 记录节点的表达式类型（整数型或向量型）
     */
    int evalType;

    public int getEvalType() {
        return evalType;
    }

    public ExprNode(Token payload) {
        super(payload);
    }

    /**
     * 返回节点文本表示。若已知节点类型，则加上
     */
    public String toString() {
        if (evalType != tINVALID) {
            return super.toString() + "<type="
                    + (evalType == tINTEGER ? "tINTEGER" : "tVECTOR") + ">";
        }
        return super.toString();
    }

}
