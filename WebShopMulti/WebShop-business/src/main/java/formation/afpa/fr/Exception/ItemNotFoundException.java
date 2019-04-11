package formation.afpa.fr.Exception;

public class ItemNotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException() {
		super("Item is not found");
	}
	
	public ItemNotFoundException(String message) {
		super(message);
	}

}
