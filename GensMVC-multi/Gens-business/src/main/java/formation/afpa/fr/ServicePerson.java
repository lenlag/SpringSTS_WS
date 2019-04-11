package formation.afpa.fr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import formation.afpa.fr.entity.Person;
import formation.afpa.fr.exception.PersonAlreadyExistsException;
import formation.afpa.fr.exception.PersonFirstNameNotValidException;
import formation.afpa.fr.exception.PersonNotAvailableException;
import formation.afpa.fr.exception.PersonNotFoundException;
import formation.afpa.fr.exception.PersonNotValidException;
import formation.afpa.fr.interfaceService.IPersonService;
import formation.afpa.fr.repository.PersonRepository;

@Service
public class ServicePerson implements IPersonService {

	@Autowired
	PersonRepository repo;

	@Override
	public Person create(Person p) throws PersonNotValidException, PersonAlreadyExistsException {
		if (p == null) {
			throw new PersonNotValidException();
		}

		if (p.getId() != null) {
			Optional<Person> pOptional = repo.findById(p.getId());

			if (pOptional.isPresent()) {
				throw new PersonAlreadyExistsException();
			} else {
				throw new PersonNotValidException();
			}
		}

		return repo.save(p);

	}

	@Override
	public List<Person> list() throws PersonNotAvailableException {

		List<Person> findAll = (List<Person>) repo.findAll();

		if ((findAll == null) || (findAll.size() == 0)) {
			throw new PersonNotAvailableException();

		}
		return findAll;
	}

	@Override
	public List<Person> findByFirstName(String firstName)
			throws PersonNotFoundException, PersonFirstNameNotValidException {

		if (firstName == null) {
			throw new PersonFirstNameNotValidException();
		}

		List<Person> foundByFName = repo.findByFirstName(firstName);

		if ((foundByFName == null) || (foundByFName.size() == 0)) {
			throw new PersonNotFoundException();
		}

		return foundByFName;
	}

	@Override
	public Person findById(Long id) throws PersonNotFoundException {

		if ((id == null) || (id < 0)) {
			throw new PersonNotFoundException("The id is invalid");
		}

		Person p = repo.findById(id).get();

		if (p == null) {
			throw new PersonNotFoundException();
		}

		return p;

	}

	@Override
	public Person update(Person p) throws PersonNotValidException, PersonNotFoundException {

		if ((p == null) || (p.getId() == null)) {
			throw new PersonNotValidException();
		}

		Long idAChercher = p.getId();
		Optional<Person> pOptional = repo.findById(idAChercher);

		if (!pOptional.isPresent()) {
			throw new PersonNotFoundException();
		}
		return repo.save(p);

	}

	@Override
	public void deleteById(Long id) throws PersonNotFoundException {
		
		if((id == null) || (id < 0)) {
			throw new PersonNotFoundException("The id is invalid");
		}
		
		Optional<Person> pOptional = repo.findById(id);
		if (!pOptional.isPresent()) {
			throw new PersonNotFoundException("Person with requested id does not exist");
		}
		
		repo.deleteById(id);
	}

}
