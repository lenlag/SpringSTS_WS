package Ex5;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


@Component
public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findByName(String name);
	
	
}
