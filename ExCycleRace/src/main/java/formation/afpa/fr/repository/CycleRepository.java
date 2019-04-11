package formation.afpa.fr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import formation.afpa.fr.entity.Cycle;

public interface CycleRepository extends CrudRepository<Cycle, Long> {

	@Query("SELECT c FROM Cycle c WHERE c.nbGearPlates*c.nbGears < :number") // JPQL query
	List<Cycle> findByTotalSpeeds(@Param("number") int nb);

}
