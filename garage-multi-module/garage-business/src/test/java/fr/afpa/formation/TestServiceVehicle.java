package fr.afpa.formation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import fr.afpa.formation.entity.Vehicle;
import fr.afpa.formation.repository.VehicleRepository;
import fr.afpa.formation.service.ServiceVehicle;




@RunWith(MockitoJUnitRunner.class) // On indique qu’on utilise Mockito
public class TestServiceVehicle {
	
	@Mock // Ici on indique qu’on a un mock qui simule le repository de Specie
	private VehicleRepository repoMock;

	@InjectMocks // Ici, on indique que le mock est injecté/utilisé par le service à tester (ici
					// ServiceSpecie)
	private ServiceVehicle service;
	// on va utiliser le mock comme si c’était un repository.
	
	List<Vehicle> vList = new ArrayList<>();
	int listSize;
	
	
	@Before
	public void setUp() {
		vList.add(new Vehicle(0l, "CG44", "Range Rover", new Date(), "HJ55KJ"));
		vList.add(new Vehicle(1l, "In33", "Infinity", new Date(), "TR3232PL"));
		vList.add(new Vehicle(2l, "Ferrari", "La Ferrari", new Date(), "FERRARI"));
		listSize = vList.size();
		
		//mocks used in several tests
		when(repoMock.findById(0l)).thenReturn(Optional.of(getVehicleById(0l)));
		when(repoMock.findAll()).thenReturn(vList);
		
		}
	
	
	private Vehicle getVehicleById(Long id) {
		Vehicle v = null;
		
		for (Vehicle vehicle : vList) {
			if(vehicle.getId().equals(id)) {
				v = vehicle;
			}
		}
		return v;
	}
	
	
	@Test
	public void findAll() throws Exception {
		
		try {
			assertEquals(listSize, service.findAll().size());
		} catch (Exception e) {
			Assert.fail("Test failed: the exception was not expected");
		}
		
	}
	
	@Test
	public void findById() throws Exception {
		
		try {
			Vehicle v = service.findById(0l);
			assertNotNull(v);
			assertEquals(v.getBrand(), "Range Rover");
		} catch (Exception e) {
			Assert.fail("Test failed: the exception was not expected");
		}
	}
	
	
	@Test
	public void create() throws Exception {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Vehicle v = (Vehicle) os[0];
					v.setId(12l);
					vList.add(v);
				}
				return null;
			}
		}).when(repoMock).save(Mockito.any(Vehicle.class));
		
		Vehicle v = new Vehicle();
		v.setModel("PO543");
		v.setBrand("Aston M");
		
		try {
			service.create(v); 
		} catch (Exception e) {
			Assert.fail("Test failed: the exception was not expected");
		}
		
		assertEquals(vList.size(), listSize +1);
		assertEquals(12l, vList.get(3).getId().longValue());
	}
}
