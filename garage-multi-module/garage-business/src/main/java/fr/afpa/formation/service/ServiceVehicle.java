package fr.afpa.formation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.afpa.formation.entity.Vehicle;
import fr.afpa.formation.repository.VehicleRepository;


@Service
public class ServiceVehicle {

	@Autowired
	VehicleRepository repo;
	
	public ServiceVehicle() {
		
	}
	
	public List<Vehicle> findAll() {
		List<Vehicle> vList = (List<Vehicle>) repo.findAll();
		
		return vList;
	}
	
	public void create(Vehicle v) {
		
		repo.save(v);
		
	}
	
	public Vehicle findById(Long id) {
		return repo.findById(id).get();
	}
}
