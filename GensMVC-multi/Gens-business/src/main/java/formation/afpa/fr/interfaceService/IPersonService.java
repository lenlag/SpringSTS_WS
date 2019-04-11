package formation.afpa.fr.interfaceService;

import java.util.List;

import formation.afpa.fr.entity.Person;
import formation.afpa.fr.exception.PersonAlreadyExistsException;
import formation.afpa.fr.exception.PersonFirstNameNotValidException;
import formation.afpa.fr.exception.PersonNotAvailableException;
import formation.afpa.fr.exception.PersonNotFoundException;
import formation.afpa.fr.exception.PersonNotValidException;
/**
 * 
 * @author 34011-65-01
 *
 */
public interface IPersonService {
	/**
	 * 
	 * @return List of all the objects type Person from he database
	 * @throws PersonNotAvailableException
	 */
	public List<Person> list() throws PersonNotAvailableException;
	
	
	/***
	 * 
	 * @param firstName
	 * @return object of type Person found by the given String firstName
	 * @throws PersonNotFoundException
	 * @throws PersonFirstNameNotValidException
	 */
	public List<Person> findByFirstName(String firstName) throws PersonNotFoundException, PersonFirstNameNotValidException;
	
	
	
	/**
	 * 
	 * @param id
	 * @return object of type Person found by the given id
	 * @throws PersonNotFoundException
	 */
	public Person findById(Long id) throws PersonNotFoundException;
	
	/**
	 * 
	 * @param p
	 * @return creates a new object of type Person
	 * @throws PersonNotValidException
	 * @throws PersonAlreadyExistsException
	 */
	
	public Person create(Person p) throws PersonNotValidException, PersonAlreadyExistsException;
	
	
	/**
	 * 
	 * @param p
	 * @return update object of type Person
	 * @throws PersonNotValidException
	 * @throws PersonNotFoundException
	 */
	public Person update(Person p) throws PersonNotValidException, PersonNotFoundException;
	
	
	/**
	 * 
	 * @param id
	 * @throws PersonNotFoundException
	 */
	public void deleteById(Long id) throws PersonNotFoundException;
	
	

}
