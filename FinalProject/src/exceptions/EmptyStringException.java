package exceptions;

public class EmptyStringException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "A word must be introduced";
	}
}
