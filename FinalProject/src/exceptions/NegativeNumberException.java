package exceptions;

public class NegativeNumberException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "The number introduced muts have a positive value";
	}

}
