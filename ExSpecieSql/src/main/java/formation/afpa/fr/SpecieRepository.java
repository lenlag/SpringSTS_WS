package formation.afpa.fr;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;


@Component
public interface SpecieRepository extends CrudRepository<Specie, Long> {
	
	List <Specie> findByCommonName(String commonName);

	List<Specie> findByLatinName(String latinName);
	
	@Query("SELECT s FROM Specie s WHERE s.latinName LIKE :name%")
	List<Specie> findByBeginLatinName (@Param("name") String name);

	
}
