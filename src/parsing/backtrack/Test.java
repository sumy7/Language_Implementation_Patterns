package parsing.backtrack;

import parsing.lexer.ListLexer;

public class Test {
	public static void main(String args[]) throws RecognitionException {
		ListLexer lexer = new ListLexer(args[0]);
		BacktrackParser parser = new BacktrackParser(lexer);
		parser.stat();
	}
}
