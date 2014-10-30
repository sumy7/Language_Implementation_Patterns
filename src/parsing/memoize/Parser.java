package parsing.memoize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parsing.exception.MismatchedTokenException;
import parsing.exception.PreviousParseFailedException;
import parsing.lexer.Lexer;
import parsing.lexer.Token;

public abstract class Parser {
	public static final int FAILED = -1; // 表示上一次解析失败

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
			clearMemo(); // 清除相关记录
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

	/**
	 * 判断在当前位置是否解析过这个规则 <br>
	 * 如果查不到相关记录，表示没有解析过。 如果返回值是FAILED，那么上次解析失败。
	 * 如果返回值大于等于0，这是词法单元缓冲区的下标，表示上次解析成功。
	 */
	public boolean alreadyParsedRule(Map<Integer, Integer> memoization)
			throws PreviousParseFailedException {
		Integer memoI = memoization.get(index());
		if (memoI == null) {
			return false;
		}
		int memo = memoI.intValue();
		System.out.println("parsed list before at index " + index()
				+ "; ship ahead to token index " + memo + ": "
				+ lookahead.get(memo).text);
		if (memo == FAILED)
			throw new PreviousParseFailedException();

		seek(memo);
		return true;
	}

	/**
	 * 回溯时，记录解析的中间结果<br>
	 * 如果解析失败，记录FAILED；如果解析成功，记录匹配的结束位置。
	 */
	public void memoize(Map<Integer, Integer> memoization, int startTokenIndex,
			boolean failed) {
		int stopTokenIndex = failed ? FAILED : index();
		memoization.put(startTokenIndex, stopTokenIndex);
	}

	public abstract void clearMemo();

	/**
	 * 返回当前输入流的位置
	 */
	public int index() {
		return p;
	}
}
