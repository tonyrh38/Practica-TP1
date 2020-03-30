package invaders.exceptions;

public class FileContentsException extends Exception {

	private static final long serialVersionUID = 7851771435975128128L;

	public FileContentsException() { super(); }
	public FileContentsException(String msg) { super(msg); }
	public FileContentsException(String msg, Throwable cause) { super(msg, cause); }
	public FileContentsException(String msg, Throwable cause, boolean suppression, boolean stackTrace) {
		super(msg, cause, suppression, stackTrace);
	}
}
