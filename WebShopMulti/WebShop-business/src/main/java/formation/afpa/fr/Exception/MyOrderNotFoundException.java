package formation.afpa.fr.Exception;

public class MyOrderNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyOrderNotFoundException() {
		super("MyOrder is not found");
	}
	
	public MyOrderNotFoundException(String message) {
		super(message);
	}

}
