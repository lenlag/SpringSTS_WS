package fr.afpa.formation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.afpa.formation.entity.Vehicle;

@Component
public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

}
