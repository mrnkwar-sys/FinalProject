package exceptions;

public class OutOfStockException extends Exception {

	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "There is not enough stock/no stock available for the product you are looking for";
	}

}
