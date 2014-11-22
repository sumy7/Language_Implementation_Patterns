package IR.Homo;

import java.util.ArrayList;
import java.util.List;

import parsing.lexer.Token;

public class AST {
    Token token; // 同型AST节点类型
    List<AST> children;// 原始词法单元

    /**
     * 创建作为根节点的空节点
     */
    public AST() {
    }

    /**
     * 根据已有的词法单元创建节点
     * 
     * @param token
     *            词法单元
     */
    public AST(Token token) {
        this.token = token;
    }

    /**
     * 根据词法单元类型创建节点；主要用于虚节点
     * 
     * @param tokenType
     *            词法单元类型
     */
    public AST(int tokenType) {
        this.token = new Token(tokenType);
    }

    /**
     * 根据同一类型的节点，外部访问者会执行相同的代码
     */
    public int getNodeType() {
        return token.type;
    }

    public void addChild(Token token) {
        AST t = new AST(token);
        addChild(t);
    }

    public void addChild(AST t) {
        if (children == null) {
            children = new ArrayList<AST>();
        }
        children.add(t);
    }

    public boolean isNil() {
        return token == null;
    }

    /**
     * 单个节点的文本形式
     */
    public String toString() {
        return token != null ? token.toText() : "nil";
    }

    /**
     * 整个树的文本形式
     */
    public String toStringTree() {
        if (children == null || children.size() == 0) {
            return this.toString();
        }
        StringBuilder buf = new StringBuilder();
        if (!isNil()) {
            buf.append("(");
            buf.append(this.toString());
            buf.append(" ");
        }
        for (int i = 0; i < children.size(); i++) {
            AST t = (AST) children.get(i);
            if (i > 0)
                buf.append(' ');
            buf.append(t.toStringTree());
        }
        if (!isNil()) {
            buf.append(")");
        }
        return buf.toString();
    }
}
