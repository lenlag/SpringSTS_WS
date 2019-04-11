package formation.afpa.fr.Exception;

public class MyOrderAlreadyExistsException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MyOrderAlreadyExistsException() {
		super("MyOrder already exists");
	}
}
