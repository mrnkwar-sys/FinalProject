package exceptions;

/**
 * An exception that is thrown when there is insufficient stock of a weapon
 * @author Raquel Nkwar
 * @version 1.0
 */
public class OutOfStockException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "There is not enough stock/no stock available for the product you are looking for";
	}

}
