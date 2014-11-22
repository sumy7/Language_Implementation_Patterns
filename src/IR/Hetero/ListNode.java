package IR.Hetero;

import java.util.ArrayList;
import java.util.List;

/**
 * 一棵平整的树，相当于根节点为nil：(nil child1 child2 ...)
 */
public class ListNode extends HeteroAST {
    List<HeteroAST> elements = new ArrayList<HeteroAST>();

    public ListNode(List<HeteroAST> elements) {
        this.elements = elements;
    }

    public String toStringTree() {
        if (elements == null || elements.size() == 0) {
            return this.toString();
        }
        StringBuilder buf = new StringBuilder();
        for (int i = 0; elements != null && i < elements.size(); i++) {
            HeteroAST t = (HeteroAST) elements.get(i);
            if (i > 0)
                buf.append(" ");
            buf.append(t.toStringTree());
        }
        return buf.toString();
    }
}
