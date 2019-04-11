package formation.afpa.fr.Exception;

public class OrderDetailAlreadyExistsException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderDetailAlreadyExistsException() {
		super("MyOrderDetail already exists");
	}
}
