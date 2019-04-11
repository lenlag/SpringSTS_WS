package formation.afpa.fr.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import formation.afpa.fr.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
	
	public List<Person> findByFirstName(String firstName);
	
}
