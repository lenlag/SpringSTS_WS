package formation.afpa.fr.repository;

import org.springframework.data.repository.CrudRepository;

import formation.afpa.fr.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

}
