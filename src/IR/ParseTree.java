package IR;

import java.util.ArrayList;
import java.util.List;

import parsing.lexer.Token;

/**
 * 解析树——节点都是此类的实例，实际上并没有Node节点
 */
public abstract class ParseTree {
    public List<ParseTree> children;

    public RuleNode addChild(String value) {
        RuleNode r = new RuleNode(value);
        addChild(r);
        return r;
    }

    public TokenNode addChild(Token value) {
        TokenNode t = new TokenNode(value);
        addChild(t);
        return t;
    }

    public void addChild(ParseTree t) {
        if (children == null) {
            children = new ArrayList<ParseTree>();
        }
        children.add(t);
    }
}
