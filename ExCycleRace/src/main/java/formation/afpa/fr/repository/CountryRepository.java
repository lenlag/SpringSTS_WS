package formation.afpa.fr.repository;

import org.springframework.data.repository.CrudRepository;

import formation.afpa.fr.entity.Country;

public interface CountryRepository extends CrudRepository<Country, Long> {

}
