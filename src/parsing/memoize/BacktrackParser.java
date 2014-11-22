package parsing.memoize;

import java.util.HashMap;
import java.util.Map;

import parsing.exception.NoViableAltException;
import parsing.exception.RecognitionException;
import parsing.lexer.Lexer;
import parsing.lexer.Token;

public class BacktrackParser extends Parser {

    Map<Integer, Integer> list_memo;

    public BacktrackParser(Lexer input) {
        super(input);
        list_memo = new HashMap<Integer, Integer>();
    }

    /**
     * stat : list EOF | assign EOF ;
     */
    public void stat() throws RecognitionException {
        // 尝试解析选项 1： list EOF
        if (speculate_stat_alt1()) {
            list();
            match(Token.EOF_TYPE);
            // 尝试解析选项2： assign EOF
        } else if (speculate_stat_alt2()) {
            assign();
            match(Token.EOF_TYPE);
            // 两个都不匹配，出错
        } else
            throw new NoViableAltException("expection stat found " + LT(1));
    }

    public boolean speculate_stat_alt1() {
        System.out.println("attempt alternative 1");
        boolean success = true;
        mark(); // 标记当前位置
        try {
            list();
            match(Token.EOF_TYPE);
        } catch (RecognitionException e) {
            success = false;
        }
        release(); // 回溯
        return success;
    }

    public boolean speculate_stat_alt2() {
        System.out.println("attempt alternative 2");
        boolean success = true;
        mark(); // 标记当前位置
        try {
            assign();
            match(Token.EOF_TYPE);
        } catch (RecognitionException e) {
            success = false;
        }
        release(); // 回溯
        return success;
    }

    /**
     * assign : list '=' list ;
     */
    public void assign() throws RecognitionException {
        list();
        match(Token.EQUALS);
        list();
    }

    /**
     * list : '[' elements ']' ;
     */
    public void list() throws RecognitionException {
        boolean failed = false;
        int startTokenIndex = index(); // 获取当前词法单元的位置
        if (isSpeculating() && alreadyParsedRule(list_memo))
            return;
        // 之前没有在tokenIndex处解析过
        try {
            _list();
        } catch (RecognitionException re) {
            failed = true;
            throw re;
        } finally {
            if (isSpeculating()) {
                // 回溯记录解析结果
                memoize(list_memo, startTokenIndex, failed);
            }
        }
    }

    /**
     * list : '[' elements ']' ;
     */
    public void _list() throws RecognitionException {
        System.out.println("parsing list rule at token index: " + index());
        match(Token.LBRACK);
        elements();
        match(Token.RBRACK);
    }

    /**
     * elements : element (',' element)* ;
     */
    void elements() throws RecognitionException {
        element();
        while (LA(1) == Token.COMMA) {
            match(Token.COMMA);
            element();
        }
    }

    /**
     * element : NAME '=' NAME | NAME | list ;
     */
    void element() throws RecognitionException {
        if (LA(1) == Token.NAME && LA(2) == Token.EQUALS) {
            match(Token.NAME);
            match(Token.EQUALS);
            match(Token.NAME);
        } else if (LA(1) == Token.NAME)
            match(Token.NAME);
        else if (LA(1) == Token.LBRACK)
            list();
        else
            throw new Error("expecting name or list; found " + LT(1));
    }

    @Override
    public void clearMemo() {
        list_memo.clear();
    }
}
