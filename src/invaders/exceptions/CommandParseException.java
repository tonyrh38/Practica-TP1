package invaders.exceptions;

public class CommandParseException extends Exception{
	
	public CommandParseException() { super(); }
	public CommandParseException(String msg) { super(msg); }
	public CommandParseException(String msg, Throwable cause) { super(msg, cause); }
	public CommandParseException(String msg, Throwable cause, boolean suppression, boolean stackTrace) {
		super(msg, cause, suppression, stackTrace);
	}
}
