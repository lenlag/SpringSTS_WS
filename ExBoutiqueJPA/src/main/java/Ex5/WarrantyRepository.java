package Ex5;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface WarrantyRepository extends CrudRepository<Warranty, Long> {

}
