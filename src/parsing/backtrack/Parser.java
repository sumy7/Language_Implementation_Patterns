package parsing.backtrack;

import java.util.ArrayList;
import java.util.List;

import parsing.exception.MismatchedTokenException;
import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class Parser {
    Lexer input; // 词法单元的来源
    List<Integer> markers;// 栈，存放用于记录位置的位标（标记）
    List<Token> lookahead;// 大小可变的缓冲区
    int p = 0;// 当前向前看词法单元的下标

    public Parser(Lexer input) {
        this.input = input;
        markers = new ArrayList<Integer>();
        lookahead = new ArrayList<Token>();
    }

    public Token LT(int i) {
        sync(i);
        return lookahead.get(p + i - 1);
    }

    public int LA(int i) {
        return LT(i).type;
    }

    public void match(int x) throws MismatchedTokenException {
        if (LA(1) == x)
            consume();
        else
            throw new MismatchedTokenException("expecting "
                    + Token.getTokenName(x) + " found " + LT(1));
    }

    /**
     * 确保当前位置p之后有i个词法单元
     * 
     * @param i
     *            词法单元的个数
     */
    public void sync(int i) {
        if (p + i - 1 > (lookahead.size() - 1)) // 词法单元是否越界
        {
            int n = (p + i - 1) - (lookahead.size() - 1); // 需要的词法单元的个数
            fill(n);
        }
    }

    /**
     * 加入n个词法单元
     * 
     * @param n
     *            需加入的词法单元的个数
     */
    public void fill(int n) {
        for (int i = 1; i <= n; i++) {
            lookahead.add(input.nextToken());
        }
    }

    public void consume() {
        p++;
        // 非推断状态，而且到达向前看缓冲区的末尾
        if (p == lookahead.size() && !isSpeculating()) {
            // 到了末尾，应该重新从0开始填入新的词法单元
            p = 0;
            lookahead.clear();
        }
        sync(1);// 取一个新的词法单元
    }

    public int mark() {
        markers.add(p);
        return p;
    }

    public void release() {
        int marker = markers.get(markers.size() - 1);
        markers.remove(markers.size() - 1);
        seek(marker);
    }

    public void seek(int index) {
        p = index;
    }

    /**
     * 当前是否在推断状态
     * 
     * @return 是否是推断状态
     */
    public boolean isSpeculating() {
        return markers.size() > 0;
    }
}
