package invaders.exceptions;

public class CommandExecuteException extends Exception{

	private static final long serialVersionUID = -7554502780325854772L;
	
	public CommandExecuteException() { super(); }
	public CommandExecuteException(String msg) { super(msg); }
	public CommandExecuteException(String msg, Throwable cause) { super(msg, cause); }
	public CommandExecuteException(String msg, Throwable cause, boolean suppression, boolean stackTrace) {
		super(msg, cause, suppression, stackTrace);
	}
}
