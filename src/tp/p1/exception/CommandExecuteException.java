package tp.p1.exception;

public class CommandExecuteException extends Exception{

	public CommandExecuteException() { super(); }
	public CommandExecuteException(String msg) { super(msg); }
	public CommandExecuteException(String msg, Throwable cause) { super(msg, cause); }
	public CommandExecuteException(String msg, Throwable cause, boolean suppression, boolean stackTrace) {
		super(msg, cause, suppression, stackTrace);
	}
}
