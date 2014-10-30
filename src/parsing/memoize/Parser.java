package parsing.memoize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parsing.exception.MismatchedTokenException;
import parsing.exception.PreviousParseFailedException;
import parsing.lexer.Lexer;
import parsing.lexer.Token;

public abstract class Parser {
	public static final int FAILED = -1; // ��ʾ��һ�ν���ʧ��

	Lexer input; // �ʷ���Ԫ����Դ
	List<Integer> markers;// ջ��������ڼ�¼λ�õ�λ�꣨��ǣ�
	List<Token> lookahead;// ��С�ɱ�Ļ�����
	int p = 0;// ��ǰ��ǰ���ʷ���Ԫ���±�

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
	 * ȷ����ǰλ��p֮����i���ʷ���Ԫ
	 * 
	 * @param i
	 *            �ʷ���Ԫ�ĸ���
	 */
	public void sync(int i) {
		if (p + i - 1 > (lookahead.size() - 1)) // �ʷ���Ԫ�Ƿ�Խ��
		{
			int n = (p + i - 1) - (lookahead.size() - 1); // ��Ҫ�Ĵʷ���Ԫ�ĸ���
			fill(n);
		}
	}

	/**
	 * ����n���ʷ���Ԫ
	 * 
	 * @param n
	 *            �����Ĵʷ���Ԫ�ĸ���
	 */
	public void fill(int n) {
		for (int i = 1; i <= n; i++) {
			lookahead.add(input.nextToken());
		}
	}

	public void consume() {
		p++;
		// ���ƶ�״̬�����ҵ�����ǰ����������ĩβ
		if (p == lookahead.size() && !isSpeculating()) {
			// ����ĩβ��Ӧ�����´�0��ʼ�����µĴʷ���Ԫ
			p = 0;
			lookahead.clear();
			clearMemo(); // �����ؼ�¼
		}
		sync(1);// ȡһ���µĴʷ���Ԫ
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
	 * ��ǰ�Ƿ����ƶ�״̬
	 * 
	 * @return �Ƿ����ƶ�״̬
	 */
	public boolean isSpeculating() {
		return markers.size() > 0;
	}

	/**
	 * �ж��ڵ�ǰλ���Ƿ������������� <br>
	 * ����鲻����ؼ�¼����ʾû�н������� �������ֵ��FAILED����ô�ϴν���ʧ�ܡ�
	 * �������ֵ���ڵ���0�����Ǵʷ���Ԫ���������±꣬��ʾ�ϴν����ɹ���
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
	 * ����ʱ����¼�������м���<br>
	 * �������ʧ�ܣ���¼FAILED����������ɹ�����¼ƥ��Ľ���λ�á�
	 */
	public void memoize(Map<Integer, Integer> memoization, int startTokenIndex,
			boolean failed) {
		int stopTokenIndex = failed ? FAILED : index();
		memoization.put(startTokenIndex, stopTokenIndex);
	}

	public abstract void clearMemo();

	/**
	 * ���ص�ǰ��������λ��
	 */
	public int index() {
		return p;
	}
}
