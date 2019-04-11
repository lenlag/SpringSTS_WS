package formation.afpa.fr.interfaceService;

import java.util.List;

import formation.afpa.fr.Person;
import formation.afpa.fr.Exception.PersonFirstNameOrLastNameNotValidException;
import formation.afpa.fr.Exception.PersonLastNameNotValidException;
import formation.afpa.fr.Exception.PersonNotFoundException;

public interface IPersonService extends IService<Person, Exception> {
	
	public List<Person> findByLastName(String lastName) throws PersonNotFoundException, PersonLastNameNotValidException;
	
	public List<Person> findDictinctByLastName (String lastName) throws PersonNotFoundException, PersonLastNameNotValidException;
	
	public List<Person> findByFirstNameOrLastName(String firstName,String lastName) throws PersonNotFoundException, PersonFirstNameOrLastNameNotValidException;

}
