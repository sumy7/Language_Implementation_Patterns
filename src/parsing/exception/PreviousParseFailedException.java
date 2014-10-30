package parsing.exception;

public class PreviousParseFailedException extends RecognitionException {

	private static final long serialVersionUID = -7266107606570387545L;

	public PreviousParseFailedException() {
		super();
	}

	public PreviousParseFailedException(String msg) {
		super(msg);
	}
}
