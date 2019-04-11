package fr.afpa.formation;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fr.afpa.formation.SmallApp;
import fr.afpa.formation.entity.Vehicle;
import fr.afpa.formation.repository.VehicleRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class MyVehicleTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private VehicleRepository repo;
	
	private Long id = 0l;
	int nbVeh;
	
	@Before //as H2 test DBase
	public void setUp() {
		Vehicle v = new Vehicle();
		v.setBrand("Peugeot");
		v.setModel("RCZ");
		v.setYear(new Date());
		v.setNumberPlate("JH5454KJK");
		entityManager.persist(v);
		
		v = new Vehicle();
		v.setBrand("Infinity");
		v.setModel("HJ");
		v.setYear(new Date());
		v.setNumberPlate("HJ777TT");
		entityManager.persist(v);
		
		v = new Vehicle();
		v.setBrand("Mazda");
		v.setModel("II5");
		v.setYear(new Date());
		v.setNumberPlate("TR555RE");
		id = (Long) entityManager.persistAndGetId(v); // id extraction from the last created object		
		
		nbVeh = 3;
	}
	
	private Vehicle addVehicle() {
		Vehicle v = new Vehicle();
		v.setBrand("Ferrari");
		v.setModel("La Ferrari");
		v.setYear(new Date());
		v.setNumberPlate("LK555KL");
		
		entityManager.persist(v);
		
		return v;
	}
	
	@Test
	public void insert() {
		Vehicle v = addVehicle();
		assertEquals(repo.count(), nbVeh +1);
		Vehicle test = repo.findById(v.getId()).get();
		assertEquals(test.getBrand(), v.getBrand());
		assertEquals(test.getModel(), v.getModel());
		
	}
	
	@Test
	public void update() {
		Vehicle v = addVehicle();
		String brand = "Lamborgini";
		v.setBrand(brand);
		repo.save(v);
		Vehicle test = repo.findById(v.getId()).get();
		assertEquals(test.getBrand(), brand);
	}
	
	@Test
	public void delete() {
		Vehicle v = addVehicle();
		assertEquals(nbVeh+1, repo.count());
		repo.delete(v);
		assertEquals(nbVeh,  repo.count());
	}
	
	
	public void findAll() {
		assertEquals(nbVeh,  repo.findAll());
	}
	
}
