package parsing.recursive_descent;

import parsing.lexer.ListLexer;

public class Test {
	public static void main(String args[]) {
		ListLexer lexer = new ListLexer(args[0]);
		ListParser parser = new ListParser(lexer);
		parser.list();
	}
}

/*
 * input: "[a, ]"
 * output:
 * Exception in thread "main" java.lang.Error: expecting name or list; found <']',RBACK>
 *     at parsing.recursive_descent.ListParser.element(ListParser.java:40)
 *     at parsing.recursive_descent.ListParser.elements(ListParser.java:27)
 *     at parsing.recursive_descent.ListParser.list(ListParser.java:16)
 *     at parsing.recursive_descent.Test.main(Test.java:9)
 */
