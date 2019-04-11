package formation.afpa.fr.Exception;

public class OrderDetailNotValidException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderDetailNotValidException () {
		super("MyOrderDetail is invalid");

	}
	
}
