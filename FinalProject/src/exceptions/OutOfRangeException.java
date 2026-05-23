package exceptions;

public class OutOfRangeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "The value introduced must be within the specified range";
	}

}
