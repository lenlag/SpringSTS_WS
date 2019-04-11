package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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

import formation.afpa.fr.Specie;
import formation.afpa.fr.SpecieRepository;
import formation.afpa.fr.Exception.SpecieAlreadyExistsException;
import formation.afpa.fr.Exception.SpecieCommonNameNotValidException;
import formation.afpa.fr.Exception.SpecieLatinNameNotValidException;
import formation.afpa.fr.Exception.SpecieNotAvailableException;
import formation.afpa.fr.Exception.SpecieNotFoundException;
import formation.afpa.fr.Exception.SpecieNotValidException;

@RunWith(MockitoJUnitRunner.class) // On indique qu’on utilise Mockito
public class TestServiceSpecie {

	@Mock // Ici on indique qu’on a un mock qui simule le repository de Specie
	private SpecieRepository repoMock;

	@InjectMocks // Ici, on indique que le mock est injecté/utilisé par le service à tester (ici
					// ServiceSpecie)
	private ServiceSpecie service;

	// on va utiliser le mock comme si c’était un repository.

	List<Specie> list = new ArrayList<>();
	String beginLatinName = "Gal";
	String commonName = "Chicken";
	Long id = 0l;
	String latinName = "Cygnus";
	int listSize;

	@Before
	public void setUp() {
		list.add(new Specie(0l, "Chicken", "Gallus domesticus"));
		list.add(new Specie(1l, "Swan", "Cygnus"));
		listSize = list.size();
		
		//used in several tests
		when(repoMock.findById(id)).thenReturn(Optional.of(getSpecieById(id)));
		when(repoMock.findById(0l)).thenReturn(Optional.of(getSpecieById(0l)));
		when(repoMock.findById(1l)).thenReturn(Optional.of(getSpecieById(1l)));
		when(repoMock.findAll()).thenReturn(list);
		
	}

	@Test
	public void findAll() throws SpecieNotAvailableException {
// refactored=>		when(repoMock.findAll()).thenReturn(list);
		// test the list's size

		try {
			assertEquals(listSize, service.findAll().size());
		} catch (SpecieNotAvailableException e) {
			// assertTrue(false);
			Assert.fail("Test failed : SpecieNotAviableException");
		}

		//we initialise the list to 0 and test that the exception is well thrown
		list = new ArrayList<Specie>();
		try {
			service.findAll();
		} catch (SpecieNotAvailableException e) {
			e.printStackTrace();
			assertTrue(true);
		}

	}

	@Test
	public void findByLatinName() {
		when(repoMock.findByLatinName(latinName)).thenReturn(getSpecieByLatinName(latinName));

		try {
			assertEquals(1, service.findByLatinName(latinName).size());
			assertEquals(service.findByLatinName(latinName).get(0).getLatinName(), latinName);
		} catch (SpecieLatinNameNotValidException e) {
			// assertTrue(false);
			Assert.fail("Test failed : SpecieLatinNameNotFoundException");
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed: SpecieNotFoundException");
		}

		try {
			service.findByLatinName(null);
		} catch (SpecieLatinNameNotValidException e) {
			assertTrue(true);
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieLatinNameNotValidException was not expected");
		}

		try {
			service.findByLatinName("Chévrum biquette");
		} catch (SpecieLatinNameNotValidException e) {
			Assert.fail("Test failed : SpecieLatinNameNotValidException was not expected");
		} catch (SpecieNotFoundException e) {
			assertTrue(true);
		}

	}

	private List<Specie> getSpecieByLatinName(String latinName) {
		List<Specie> listToReturn = new ArrayList<>();

		for (Specie specie : list) {
			if (specie.getLatinName().equals(latinName)) {
				listToReturn.add(specie);
			}
		}
		return listToReturn;
	}

	@Test
	public void findByBeginLatinName() {
		when(repoMock.findByBeginLatinName(beginLatinName)).thenReturn(getSpecieBYBeginLatinName(beginLatinName));
		try {
			assertEquals(1, service.findByBeginLatinName(beginLatinName).size());
			assertTrue(service.findByBeginLatinName(beginLatinName).get(0).getLatinName().startsWith(beginLatinName));
		} catch (SpecieLatinNameNotValidException e) {
			Assert.fail("Test failed : SpecieLatinNameNotValidException was not expected");
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed: SpecieNotFoundException was not expected");
		}

		try {
			service.findByBeginLatinName(null);
		} catch (SpecieLatinNameNotValidException e) {
			assertTrue(true);
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieNotFoundException was not expected"); // as it was not defined in
																					// ServiceSpecie.java
		}

		try {
			service.findByBeginLatinName("Chév");
		} catch (SpecieLatinNameNotValidException e) {
			Assert.fail("Test failed : SpecieLatinNameNotValidException was not expected");
		} catch (SpecieNotFoundException e) {
			assertTrue(true);
		}

	}

	private List<Specie> getSpecieBYBeginLatinName(String s) {
		List<Specie> listToReturn = new ArrayList<>();

		for (Specie specie : list) {
			if (specie.getLatinName().startsWith(s)) {
				listToReturn.add(specie);
			}
		}
		return listToReturn;

	}

	@Test
	public void findByCommonName() {
		when(repoMock.findByCommonName(commonName)).thenReturn(listCommon(commonName));
		try {
			assertEquals(1, service.findByCommonName(commonName).size());
			assertEquals(service.findByCommonName(commonName).get(0).getCommonName(), commonName);
		} catch (SpecieCommonNameNotValidException e) {
			Assert.fail("Test failed : SpecieCommonNameNotValidException was not expected");
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieCommonNameNotValidException was not expected");
		}

		try {
			service.findByCommonName(null);
		} catch (SpecieCommonNameNotValidException e) {
			assertTrue(true);
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieNotFoundException was not expected"); // as it was not defined in
																					// ServiceSpecie.java
		}

		try {
			service.findByCommonName("Cat");
		} catch (SpecieCommonNameNotValidException e) {
			Assert.fail("Test failed : SpecieCommonNameNotValidException was not expected");
		} catch (SpecieNotFoundException e) {
			assertTrue(true);
		}

	}

	private List<Specie> listCommon(String s) {
		List<Specie> listToReturn = new ArrayList<>();

		for (Specie specie : list) {
			if (specie.getCommonName().equals(commonName)) {
				listToReturn.add(specie);
			}
		}
		return listToReturn;
	}

	@Test
	public void findById() {
//refactored=>		when(repoMock.findById(id)).thenReturn(Optional.of(getSpecieById(id)));
		try {	
			assertNotNull(service.findById(id));
			assertEquals(service.findById(id).getCommonName(), commonName);
		
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieNotFoundException was not expected");
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}

		try {
			service.findById(null);
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieNotFoundException was not expected");
		} catch (Exception e) {
			assertTrue(true);
		}
/*
		try {
			service.findById(3l);
		} catch (SpecieNotFoundException e) {
			assertTrue(true);
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}
*/
	}

	private Specie getSpecieById(Long id) {
		Specie sp = null;

		for (Specie specie : list) {
			if (specie.getId().equals(id)) {
				sp = specie;
			}
		}

		return sp;

	}

	@Test
	public void create() throws SpecieNotValidException, Exception {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Specie sp = (Specie) os[0];
					sp.setId(12L);
					list.add(sp);
				}
				return null;
			}
		}).when(repoMock).save(Mockito.any(Specie.class));
		//refactored=>		when(repoMock.findById(id)).thenReturn(Optional.of(getSpecieById(id))); 
		
		Specie sp = new Specie();
		sp.setCommonName("AA");
		sp.setLatinName("BB");

		try {
			service.create(sp);
		} catch (SpecieNotValidException e) {
			Assert.fail("Test failed : SpecieNotValidException was not expected");
		} catch (SpecieAlreadyExistsException e) {
			Assert.fail("Test failed : SpecieAlreadyExistsException was not expected");
		}

		assertEquals(list.size(), listSize + 1);
		assertEquals(12L, list.get(2).getId().longValue());

		try {
			service.create(null);
		} catch (SpecieNotValidException e) {
			assertTrue(true);
		} catch (SpecieAlreadyExistsException e) {
			Assert.fail("Test failed : SpecieAlreadyExistsException was not expected");
		}

		Specie sp2 = new Specie();
		sp2.setId(456456L); 
		sp2.setCommonName("AA");
		sp2.setLatinName("BB");

		try {
			service.create(sp2);
		} catch (SpecieNotValidException e) {
			assertTrue(true);// as id id not null
		} catch (SpecieAlreadyExistsException e) {
			Assert.fail("Test failed : SpecieAlreadyExistsException was not expected");
		}

		try {
			service.create(service.findById(0l));
		} catch (SpecieNotValidException e) {
			Assert.fail("Test failed: SpecieNotValidException was not expected");
		} catch (SpecieAlreadyExistsException e) {
			assertTrue(true);
		}

	}

	@SuppressWarnings("unchecked")
	@Test
	public void createAll() throws Exception {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					List<Specie> species = (List<Specie>) os[0];

					for (Specie specie : species) {
						long newId = (long) list.size();
						specie.setId(newId);
						list.add(specie);
					}
				}
				return null;
			}
		}).when(repoMock).saveAll(Mockito.any(Iterable.class));
	//refactored
	//when(repoMock.findById(1l)).thenReturn(Optional.of(getSpecieById(1l)));

		List<Specie> speciesToAdd = new ArrayList<>();
		Specie sp1 = new Specie();
		sp1.setCommonName("AA");
		sp1.setLatinName("BB");

		Specie sp2 = new Specie();
		sp2.setCommonName("CC");
		sp2.setLatinName("DD");

		speciesToAdd.add(sp1);
		speciesToAdd.add(sp2);

		try {
			service.createAll(speciesToAdd);

			int newSize = listSize + speciesToAdd.size();
			assertEquals(newSize, list.size());

			Specie newSpecie = list.get(newSize - 1);
			assertEquals(3l, newSpecie.getId().longValue());

		} catch (SpecieNotValidException e) {
			Assert.fail("Test failed: SpecieNotValidException was not expected");
		} catch (SpecieAlreadyExistsException e) {
			Assert.fail("Test failed: SpecieAlreadyExistsException was not expected");
		}

		try {
			service.createAll(null);
		} catch (SpecieNotValidException e) {
			assertTrue(true);
		} catch (SpecieAlreadyExistsException e) {
			Assert.fail("Test failed: SpecieAlreadyExistsException was not expected");
		}

		try {
			speciesToAdd = new ArrayList<Specie>();
			speciesToAdd.add(service.findById(1l));
			service.createAll(speciesToAdd);

		} catch (SpecieNotValidException e) {
			Assert.fail("Test failed: SpecieNotValidException was not expected");
		} catch (SpecieAlreadyExistsException e) {
			assertTrue(true);
		}
	}

	@Test
	public void update() throws Exception {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Specie sp = (Specie) os[0];
					Integer indexOf = list.indexOf(sp);

					if (indexOf != null) {
						Specie oldSpecie = list.get(indexOf);
						oldSpecie.setId(sp.getId());
						oldSpecie.setLatinName(sp.getLatinName());
						oldSpecie.setCommonName(sp.getCommonName());
						oldSpecie.setLatinName(sp.getLatinName());
					}
				}
				return null;
			}
		}).when(repoMock).save(Mockito.any(Specie.class));
		when(repoMock.findById(id)).thenReturn(Optional.of(getSpecieById(id)));

		Specie sp = service.findById(0l);
		sp.setCommonName("AA");

		try {
			service.update(sp);
		} catch (SpecieNotValidException e) {
			Assert.fail("Test failed: SpecieNotValidException was not expected");
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed: SpecieNotFoundException was not expected");
		}
		assertEquals(listSize, list.size());
		assertEquals(0L, list.get(0).getId().longValue());
		assertEquals("AA", service.findById(0l).getCommonName());

		try {
			service.update(null);
		} catch (SpecieNotValidException e) {
			assertTrue(true);
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed: SpecieNotFoundException was not expected");
		}

		try {
			service.update(new Specie(null, "toto", "titouan"));
		} catch (SpecieNotValidException e) {
			assertTrue(true);
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed: SpecieNotFoundException was not expected");
		}
	}

	@Test
	public void delete() throws Exception {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Specie sp = (Specie) os[0];

					if (list.contains(sp)) {
						list.remove(sp);
					}
				}
				return null;
			}
		}).when(repoMock).delete(Mockito.any(Specie.class));
	// refactored	
	//	when(repoMock.findById(0l)).thenReturn(Optional.of(getSpecieById(0l)));
	//	when(repoMock.findById(1l)).thenReturn(Optional.of(getSpecieById(1l)));
	//	when(repoMock.findAll()).thenReturn(list);

		// trying to delete a specie with NO ID
		try {
			Specie sp = service.findById(0l);
			sp.setId(null);
			service.delete(sp);

		} catch (SpecieNotFoundException e) {
			assertTrue(true);

		} catch (SpecieNotValidException e) {
			Assert.fail("Test failed : SpecieNotValidException was not expected");

		} catch (SpecieNotAvailableException e) {
			Assert.fail("Test failed : SpecieNotAvailableException was not expected");

		}

		// trying to delete a null specie
		try {
			service.delete(null);

		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieNotFoundException was not expected");

		} catch (SpecieNotValidException e) {
			assertTrue(true);

		} catch (SpecieNotAvailableException e) {
			Assert.fail("Test failed : SpecieNotAvailableException was not expected");

		}

		// case where you try to delete an object that is not the the DB
		Specie sp3 = new Specie();
		sp3.setId(5l);
		sp3.setCommonName("AA");
		sp3.setLatinName("BB");
		try {
			service.delete(sp3);

		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieNotFoundException was not expected");

		} catch (SpecieNotValidException e) {
			Assert.fail("Test failed : SpecieNotValidException was not expected");

		} catch (SpecieNotAvailableException e) {
			assertTrue(true);
		}

		// normal case
		assertNotNull(service.findById(1l));
		Specie sp = service.findById(1l);
		try {
			service.delete(sp);
			assertEquals(listSize - 1, list.size());
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieNotFoundException was not expected");
		} catch (SpecieNotValidException e) {
			Assert.fail("Test failed : SpecieNotValidException was not expected");
		} catch (SpecieNotAvailableException e) {
			Assert.fail("Test failed : SpecieNotAvailableException was not expected");
		}

	}

	@Test
	public void deleteById(){

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Long id = (Long) os[0];

					for (Specie specie : list) {
						if (specie.getId() == id) {
							list.remove(specie);
							break;
						}
					}
				}
				return null;
			}
		}).when(repoMock).deleteById(Mockito.any(Long.class));

		try {
			service.deleteById(0l);
		} catch (SpecieNotFoundException e) {
			Assert.fail();
		}
		assertEquals(listSize - 1, list.size());

		try {
			service.deleteById(null);
			assertTrue(false);
		} catch (SpecieNotFoundException e) {
			assertTrue(true);
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void deleteAll() throws Exception {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					@SuppressWarnings("unchecked")
					List<Specie> species = (List<Specie>) os[0];

					for (Specie specie : species) {
						if (list.contains(specie)) {
							list.remove(specie);
						}
					}
				}
				return null;
			}
		}).when(repoMock).deleteAll(Mockito.any(Iterable.class));
	//uses=>	when(repoMock.findById(0l)).thenReturn(Optional.of(getSpecieById(0l)));
	//uses=>	when(repoMock.findById(1l)).thenReturn(Optional.of(getSpecieById(1l)));
	
		Specie sp1 = service.findById(0l);
		Specie sp2 = service.findById(1l);

		List<Specie> species = new ArrayList<>();

		species.add(sp1);
		species.add(sp2);

		try {
		service.deleteAll(species);
		} catch (SpecieNotFoundException e) {
			Assert.fail("Test failed : SpecieNotFoundException was not expected");
		}
		assertEquals(listSize - 2, list.size());

		try {
			service.deleteAll(new ArrayList<>());
			assertTrue(false);
		} catch (SpecieNotFoundException e) {
			assertTrue(true);
		}

	}

}
