package formation.afpa.fr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.afpa.fr.Exception.PersonAlreadyExistsException;
import formation.afpa.fr.Exception.PersonFirstNameOrLastNameNotValidException;
import formation.afpa.fr.Exception.PersonLastNameNotValidException;
import formation.afpa.fr.Exception.PersonNotAvailableException;
import formation.afpa.fr.Exception.PersonNotFoundException;
import formation.afpa.fr.Exception.PersonNotValidException;
import formation.afpa.fr.interfaceService.IPersonService;

@Service
public class ServicePerson implements IPersonService {

	@Autowired
	PersonRepository persRep;

	public ServicePerson() {

	}

	public List<Person> findAll() throws PersonNotAvailableException { // ok
		List<Person> findAll = (List<Person>) persRep.findAll();

		if (findAll == null || findAll.size() == 0) {
			throw new PersonNotAvailableException();
		}
		return findAll;
	}

	public List<Person> findByLastName(String lastName) // ok
			throws PersonNotFoundException, PersonLastNameNotValidException {

		if (lastName == null) {
			throw new PersonLastNameNotValidException();
		}

		List<Person> foundByLastName = persRep.findByLastName(lastName);
		if ((foundByLastName == null) || (foundByLastName.size() == 0)) {
			throw new PersonNotFoundException();
		}

		return foundByLastName;
	}

	public List<Person> findDictinctByLastName(String lastName) // ok
			throws PersonNotFoundException, PersonLastNameNotValidException {

		if (lastName == null) {
			throw new PersonLastNameNotValidException();
		}

		List<Person> foundDistByLastName = persRep.findDictinctByLastName(lastName);
		if ((foundDistByLastName == null) || (foundDistByLastName.size() == 0)) {
			throw new PersonNotFoundException();
		}

		return foundDistByLastName;
	}

	public List<Person> findByFirstNameOrLastName(String firstName, String lastName) // ok
			throws PersonNotFoundException, PersonFirstNameOrLastNameNotValidException {

		if ((lastName == null) || (firstName == null)) {
			throw new PersonFirstNameOrLastNameNotValidException();
		}

		List<Person> foundByFNameOrLName = persRep.findByFirstNameOrLastName(firstName, lastName);
		if ((foundByFNameOrLName == null) || (foundByFNameOrLName.size() == 0)) {
			throw new PersonNotFoundException();
		}
		return foundByFNameOrLName;
	}

	public Person findById(Long id) throws PersonNotFoundException, Exception { // ++
		if (id == null) {
			throw new Exception("The id is invalid");
		}

		Person p = persRep.findById(id).get();

		if (p == null) {
			throw new PersonNotFoundException();
		}

		return p;
	}

	public Person create(Person p) throws PersonNotValidException, PersonAlreadyExistsException { // ok
		if (p == null) {
			throw new PersonNotValidException();
		}
		if (p.getId() != null) {

			Optional<Person> personFromDB = persRep.findById(p.getId());

			if (personFromDB.isPresent()) {
				throw new PersonAlreadyExistsException();
			} else {
				throw new PersonNotValidException();
			}
		}

		return persRep.save(p);
	}

	public List<Person> createAll(List<Person> list) 
			throws PersonNotValidException, PersonAlreadyExistsException { //ok

		if ((list == null) || (list.size() == 0)) {
			throw new PersonNotValidException();
		}

		for (Person person : list) {
			if (list == null) {
				throw new PersonNotValidException();
			}

			if (person.getId() != null) {

				Optional<Person> personFromDB = persRep.findById(person.getId());

				if (personFromDB.isPresent()) {
					throw new PersonAlreadyExistsException();
				} else {
					throw new PersonNotValidException();
				}
			}
		}
		return (List<Person>) persRep.saveAll(list);
	}

	public void deleteById(Long id) throws PersonNotFoundException { //++
		if (id == null) {
			throw new PersonNotFoundException();
		}
		
		Optional<Person> personFromDB = persRep.findById(id);
		if(!personFromDB.isPresent()) {
			throw new PersonNotFoundException("Person with requested id does not exist");
		}

		persRep.deleteById(id);
	}

	public void delete(Person p) 
			throws PersonNotValidException, PersonNotFoundException { // ok 
		if (p == null) {
			throw new PersonNotValidException();
		} else if (p.getId() == null) {
			throw new PersonNotFoundException();
		}
		
		persRep.delete(p);
	}

	public void deleteAll(List<Person> list) throws PersonNotFoundException { //ok
		if ((list == null) || (list.size() == 0)) {
			throw new PersonNotFoundException();
		}
		for (Person person : list) {
			if(person.getId() == null) {
				throw new PersonNotFoundException();
			}
		}
		persRep.deleteAll(list);
	}

	@Override
	public Person update(Person p) throws PersonNotValidException, PersonNotFoundException { // ok
		if((p == null)||(p.getId() == null)) {
			throw new PersonNotFoundException();
		}
		
		Optional<Person> persOptional = persRep.findById(p.getId());
		
		if(!persOptional.isPresent()) {
			throw new PersonNotFoundException();
		}
		return persRep.save(p);
	}

	
	public List<Person> updateAll(List<Person> list) throws Exception { // ok
		if((list == null)|| (list.size() == 0)) {
			throw new PersonNotValidException();
		}
		
		for (Person person : list) {
			if((person == null)||(person.getId() == null)) {
				throw new PersonNotValidException();
			} else {
				Optional<Person> persFromDB = persRep.findById(person.getId());
				
				if(!persFromDB.isPresent()) {
					throw new PersonNotFoundException();
				}
			}
		}
		return (List<Person>) persRep.saveAll(list);
	}

}
