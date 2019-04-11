package formation.afpa.fr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface PersonRepository extends CrudRepository<Person, Long> {

	List<Person> findByLastName(String lastName);
	List<Person> findDictinctByLastName(String lastName);
	List<Person> findByFirstNameOrLastName(String firstName,String lastName) ;
	
	
}
