package formation.afpa.fr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import formation.afpa.fr.entity.OrderItem;

@Component
public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}
