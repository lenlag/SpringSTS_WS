package formation.afpa.fr;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AnimalRepository extends CrudRepository<Animal, Long> {

	List <Animal> findByName(String name);
	List <Animal> findDictinctBycoatColor(String coatColor);
	List <Animal> findBySexOrName(String sex,String name);
	
	
}
