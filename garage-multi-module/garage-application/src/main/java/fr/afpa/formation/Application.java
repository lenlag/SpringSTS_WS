package fr.afpa.formation;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.afpa.formation.entity.Vehicle;
import fr.afpa.formation.repository.VehicleRepository;
import fr.afpa.formation.service.ServiceVehicle;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	private Log log = LogFactory.getLog(Application.class);
	
    @Autowired
	VehicleRepository rep;
    
    @Autowired
    ServiceVehicle service;
    
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		List<Vehicle> list = (List<Vehicle>) rep.findAll();
		for (Vehicle v :list) {
			log.info("**** "+v.toString());
		}
		
		List<Vehicle> myList = service.findAll();
		for (Vehicle vehicle : myList) {
			log.info("####" + vehicle.toString());
		}
		
		Vehicle v = new Vehicle(null, "CG44", "Range Rover", new Date(), "HJ55KJ");
	//	rep.save(v);
		
	}
    
}
