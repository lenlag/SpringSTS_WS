package formation.afpa.fr.Exception;

public class OrderDetailNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrderDetailNotFoundException() {
		super("MyOrderDetail is not found");
	}
	
	public OrderDetailNotFoundException(String message) {
		super(message);
	}

}
