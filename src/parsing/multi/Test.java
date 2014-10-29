package parsing.multi;

import parsing.lexer.ListLexer;

public class Test {
	public static void main(String args[]) {
		ListLexer lexer = new ListLexer(args[0]);
		LookaheadParser parser = new LookaheadParser(lexer, 2);
		parser.list();
	}
}
