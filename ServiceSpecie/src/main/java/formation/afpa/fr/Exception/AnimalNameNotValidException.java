package formation.afpa.fr.Exception;

public class AnimalNameNotValidException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AnimalNameNotValidException() {
		super("Animal name is invalid");
	}

}
