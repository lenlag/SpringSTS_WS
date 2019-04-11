package fr.afpa.formation.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import fr.afpa.formation.entity.Car;

@Component
public interface CarRepository extends CrudRepository<Car, Long> {

}
