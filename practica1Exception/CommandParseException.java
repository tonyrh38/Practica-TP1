package tp.practica1Exception;

public class CommandParseException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CommandParseException(String message) {
		super("Parse Exception: " + message);
	}

}
