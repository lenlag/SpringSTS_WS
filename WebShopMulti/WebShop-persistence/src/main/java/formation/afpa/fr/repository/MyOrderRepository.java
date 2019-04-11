package formation.afpa.fr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import formation.afpa.fr.entity.MyOrder;

@Component
public interface MyOrderRepository extends CrudRepository<MyOrder, Long> {

}
