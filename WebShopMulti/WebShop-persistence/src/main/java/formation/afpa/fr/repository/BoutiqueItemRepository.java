package formation.afpa.fr.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import formation.afpa.fr.entity.BoutiqueItem;

@Component
public interface BoutiqueItemRepository extends CrudRepository<BoutiqueItem, Long> {
	
	public BoutiqueItem findByCode (String code);

}
