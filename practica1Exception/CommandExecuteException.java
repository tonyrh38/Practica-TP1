package tp.practica1Exception;

public class CommandExecuteException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandExecuteException(String message) {
		super("Execute Exception: " + message);
	}

}
