package formation.afpa.fr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
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

import formation.afpa.fr.Exception.AnimalAlreadyExistsException;
import formation.afpa.fr.Exception.AnimalCoatColorNotValidException;
import formation.afpa.fr.Exception.AnimalNameNotValidException;
import formation.afpa.fr.Exception.AnimalNotAvailableException;
import formation.afpa.fr.Exception.AnimalNotFoundException;
import formation.afpa.fr.Exception.AnimalNotValidException;
import formation.afpa.fr.Exception.AnimalSexOrNameNotValidException;

@RunWith(MockitoJUnitRunner.class) // On indique qu’on utilise Mockito
public class TestServiceAnimal {

	@Mock // Ici on indique qu’on a un mock qui simule le repository d'Animal
	private AnimalRepository animRepMock;

	@Mock
	private SpecieRepository spRepMock;

	@InjectMocks // Ici, on indique que le mock est injecté/utilisé par le service à tester
	private ServiceAnimal animalService;

	// on va utiliser le mock comme si c’était un repository.

	List<Animal> animList = new ArrayList<>();
	List<Specie> specieList = new ArrayList<>();
	int speciesSize;
	int animalsSize;

	String animalName = "Simba";
	String coatColor = "black";
	String sex = "f";
	long id = 1l;
	String checkName = "Messi";

	@Before
	public void setUp() {
		Specie specie1 = new Specie(0l, "Chat", "Catus");
		Specie specie2 = new Specie(1l, "Chien", "Canis");

		specieList.add(specie1);
		specieList.add(specie2);
		speciesSize = specieList.size();

		animList.add(new Animal(0l, specie1, "Lucky", "gris", "f"));
		animList.add(new Animal(1l, specie2, "Messi", "black", "m"));
		animList.add(new Animal(2l, specie1, "Simba", "red", "m"));
		animList.add(new Animal(3l, specie2, "Jordy", "black", "m"));
		animList.add(new Animal(4l, specie2, "Maugli", "white", "m"));
		animalsSize = animList.size();

		when(animRepMock.findAll()).thenReturn(animList);
		when(animRepMock.findById(0l)).thenReturn(Optional.of(findAnimalById(0l)));
		when(animRepMock.findById(1l)).thenReturn(Optional.of(findAnimalById(1l)));
		when(animRepMock.findById(id)).thenReturn(Optional.of(findAnimalById(id)));

	}

	@Test
	public void findAll() throws AnimalNotAvailableException {
		List<Animal> list;
		list = animalService.findAll();
		assertEquals(animalsSize, list.size());

		list = new ArrayList<Animal>();
		try {
			assertEquals(animalsSize, animalService.findAll().size());
		} catch (AnimalNotAvailableException e) {
			assertTrue(true);
		}
	}

	@Test
	public void findByName() { // TO DO
		when(animRepMock.findByName(animalName)).thenReturn(getAnimalByName(animalName));

		try {
			assertEquals(1, animalService.findByName(animalName).size());
			assertTrue(animalService.findByName(animalName).get(0).getName().contains(animalName));
		} catch (AnimalNotFoundException e) {
			Assert.fail("Test failed: AnimalNotFoundException was not expected");
		} catch (AnimalNameNotValidException e) {
			Assert.fail("Test failed: AnimalNameNotValidException was not expected");
		}

	}

	private List<Animal> getAnimalByName(String s) {
		List<Animal> listToReturn = new ArrayList<>();

		for (Animal animal : animList) {
			if (animal.getName().equals(animalName)) {
				listToReturn.add(animal);
			}
		}
		return listToReturn;
	}

	@Test
	public void findDictinctBycoatColor() { // TO DO
		when(animRepMock.findDictinctBycoatColor(coatColor)).thenReturn(findDictinctAnimalBycoatColor(coatColor));

		try {
			assertEquals(animalService.findDictinctBycoatColor(coatColor).size(), 2);
			assertEquals(animalService.findDictinctBycoatColor(coatColor).get(1).getCoatColor(), coatColor);
			assertNotEquals(animalService.findDictinctBycoatColor(coatColor).get(1).getName(),
					animalService.findDictinctBycoatColor(coatColor).get(0).getName());

		} catch (AnimalNotFoundException e) {
			Assert.fail("Test failed: AnimalNotFoundException was not expected");
		} catch (AnimalCoatColorNotValidException e) {
			Assert.fail("Test failed: AnimalCoatColorNotValidException was not expected");
		}

	}

	private List<Animal> findDictinctAnimalBycoatColor(String s) {

		List<Animal> list = new ArrayList<>();
		for (Animal animal : animList) {
			if (animal.getCoatColor().equals(s)) {
				list.add(animal);
			}
		}
		return list;

	}

	@Test
	public void findBySexOrName() { // TO DO
		when(animRepMock.findBySexOrName(sex, animalName)).thenReturn(findAnimalBySexOrName(sex, animalName));

		try {
			assertEquals(animalService.findBySexOrName(sex, animalName).size(), 2);
			assertTrue(animalService.findBySexOrName(sex, animalName).get(1).getName().equals(animalName));
		} catch (AnimalNotFoundException e) {
			Assert.fail("Test failed: AnimalNotFoundException was not expected");
		} catch (AnimalSexOrNameNotValidException e) {
			Assert.fail("Test failed: AnimalSexOrNameNotValidException was not expected");
		}

	}

	private List<Animal> findAnimalBySexOrName(String s, String y) {

		List<Animal> myList = new ArrayList<>();
		for (Animal animal : animList) {
			if ((animal.getSex().equals(s)) || (animal.getName().equals(y))) {
				myList.add(animal);
			}
		}
		return myList;

	}

	@Test
	public void findById() {
		try {
			assertNotNull(animalService.findById(id));
			assertTrue(animalService.findById(id).getName().equals(checkName));
		} catch (AnimalNotFoundException e) {
			Assert.fail("Test failed : AnimalNotFoundException was not expected");
		} catch (Exception e) {
			Assert.fail("Test failed: exception was not expected");
		}

		try {
			assertTrue(animalService.findById(null).getName().equals(checkName));
		} catch (AnimalNotFoundException e) {
			assertTrue(true);
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	private Animal findAnimalById(long id) {

		Animal an = null;

		for (Animal animal : animList) {
			if (animal.getId() == id) {
				an = animal;
			}
		}
		return an;

	}
/*
	@Test
	public void create() throws Exception {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {

					Animal an = (Animal) os[0];
					an.setId((long) animalsSize);
					animList.add(an);

				}
				return null;
			}
		}).when(animRepMock).save(Mockito.any(Animal.class));

		Animal newAnimal = new Animal(5l, specieList.get(0), "Marusya", "multi", "f");

		try {
			animalService.create(newAnimal);
		} catch (AnimalNotValidException e) {
			Assert.fail("Test failed : AnimalNotValidException was not expected");
		} catch (AnimalAlreadyExistsException e) {
			Assert.fail("Test failed : AnimalAlreadyExistsException was not expected");
		}

		assertEquals(animalsSize + 1, animList.size());
		assertEquals(5L, animList.get(animalsSize).getId().longValue()); // on prend le dernier element de la liste, sa
																			// position = animalsSize

		try {
			animalService.create(null);
		} catch (AnimalNotValidException e1) {
			assertTrue(true);
		} catch (AnimalAlreadyExistsException e) {
			Assert.fail("Test failed : AnimalAlreadyExistsException was not expected");
		}

		Animal an2 = new Animal();
		an2.setId(456456L);
		an2.setName("AA");

		try {
			animalService.create(an2);
		} catch (AnimalNotValidException e) {
			assertTrue(true);
		} catch (AnimalAlreadyExistsException e) {
			Assert.fail("Test failed : AnimalAlreadyExistsException was not expected");
		}

		try {
			animalService.create(animalService.findById(0l));
		} catch (AnimalNotValidException e) {
			Assert.fail("Test failed : AnimalNotValidException was not expected");
		} catch (AnimalAlreadyExistsException e) {
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
					List<Animal> animalsToAdd = (List<Animal>) os[0];

					for (Animal animal : animalsToAdd) {
						long newId = (long) animList.size(); // nous avons 2 éléments dans la liste, au 1er tour de la
																// boucle, newId = 5l, 2ème tour = 6l
						animal.setId(newId);
						animList.add(animal);
					}
				}
				return null;
			}
		}).when(animRepMock).saveAll(Mockito.any(Iterable.class));

		List<Animal> animalsToAdd = new ArrayList<>();
		Animal newAnimal = new Animal(5l, specieList.get(0), "Marusya", "multi", "f");
		Animal newAnima2 = new Animal(6l, specieList.get(1), "Mishka", "brown", "m");

		animalsToAdd.add(newAnimal);
		animalsToAdd.add(newAnima2);

		try {
			animalService.createAll(animalsToAdd);
		} catch (AnimalNotValidException e) {
			Assert.fail("Test failed : AnimalNotValidException was not expected");
		} catch (AnimalAlreadyExistsException e) {
			Assert.fail("Test failed : AnimalAlreadyExistsException was not expected");
		}

		int newSize = animalsSize + animalsToAdd.size();
		assertEquals(newSize, animList.size());
		Animal animal = animList.get(newSize - 1);
		assertEquals(6l, animal.getId().longValue());

		try {
			animalService.createAll(null);
		} catch (AnimalNotValidException e) {
			assertTrue(true);
		} catch (AnimalAlreadyExistsException e) {
			Assert.fail("Test failed : AnimalAlreadyExistsException was not expected");
		}

		try {
			animalsToAdd = new ArrayList<>();
			animalsToAdd.add(animalService.findById(0l));

			animalService.createAll(animalsToAdd);
		} catch (AnimalNotValidException e) {
			Assert.fail("Test failed : AnimalNotValidException was not expected");
		} catch (AnimalAlreadyExistsException e) {
			assertTrue(true);
		}

	}
	
	*/

	@Test
	public void update() throws AnimalNotValidException {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Animal an = (Animal) os[0];
					Integer indexOf = animList.indexOf(an);

					if (indexOf != null) {
						Animal oldAnimal = animList.get(indexOf); // on extrait l'acien objet
						oldAnimal.setCoatColor(an.getCoatColor()); // on set des valeurs de lui-même
						oldAnimal.setName(an.getName());
						oldAnimal.setSex(an.getSex());
						oldAnimal.setSpecie(an.getSpecie());
					}
				}
				return null;
			}
		}).when(animRepMock).save(Mockito.any(Animal.class));

		Animal an = animList.get(0); // on extrait le nouvel objet
		an.setName("AA"); // on modif une valeur d'un nouvel objet
		try {
			animalService.update(an);
		} catch (AnimalNotValidException e) {
			Assert.fail("Test failed : AnimalNotValidException was not expected");
		} catch (AnimalNotFoundException e) {
			Assert.fail("Test failed : AnimalNotFoundException was not expected");
		}
		assertEquals(animalsSize, animList.size());
		assertEquals(0L, animList.get(0).getId().longValue());
		assertEquals("AA", animList.get(0).getName());

		try {
			animalService.update(null);
		} catch (AnimalNotValidException e) {
			assertTrue(true);
		} catch (AnimalNotFoundException e) {
			Assert.fail("Test failed : AnimalNotFoundException was not expected");
		}

		try {
			animalService.update(new Animal(500L, specieList.get(0), "Gourou", "black", "m"));
		} catch (AnimalNotValidException e) {
			Assert.fail("Test failed : AnimalNotValidException was not expected");
		} catch (AnimalNotFoundException e) {
			assertTrue(true);
		}

	}

	@Test
	public void deleteById() throws AnimalNotFoundException {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Long id = (Long) os[0];

					for (Animal animal : animList) {
						if (animal.getId() == id) {
							animList.remove(animal);
							break;
						}
					}
				}
				return null;
			}
		}).when(animRepMock).deleteById(Mockito.any(Long.class));

		try {
			animalService.deleteById(0l);
		} catch (AnimalNotFoundException e) {
			assertTrue(false);
		}

		assertEquals(animalsSize - 1, animList.size());

		try {
			animalService.deleteById(null);
			assertTrue(false);
		} catch (AnimalNotFoundException e) {
			assertTrue(true);
		}

	}

	@Test
	public void delete() {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Animal an = (Animal) os[0];

					if (animList.contains(an)) {
						animList.remove(an);
					}
				}
				return null;
			}
		}).when(animRepMock).delete(Mockito.any(Animal.class));

		try {
			Animal an = animList.get(0);
			an.setId(null);
			animalService.delete(an);
			assertTrue(false);
		} catch (AnimalNotValidException e) {
			assertTrue(true);
		} catch (AnimalNotFoundException e) {
			assertTrue(true);
		} catch (AnimalNotAvailableException e) {
			Assert.fail("Test failed : AnimalNotAvailableException was not expected");
		}

		try {
			animalService.delete(null);
			assertTrue(false);
		} catch (AnimalNotValidException | AnimalNotFoundException e) {
			assertTrue(true);
		} catch (AnimalNotAvailableException e) {
			Assert.fail("Test failed : AnimalNotAvailableException was not expected");
		}

		Animal an = animList.get(1);
		try {
			animalService.delete(an);
		} catch (AnimalNotValidException | AnimalNotFoundException e) {
			assertTrue(false);
		} catch (AnimalNotAvailableException e) {
			Assert.fail("Test failed : AnimalNotAvailableException was not expected");
		}

		assertEquals(animalsSize - 1, animList.size());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void deleteAll() {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					@SuppressWarnings("unchecked")
					List<Animal> animals = (List<Animal>) os[0];

					for (Animal a : animals) {
						if (animList.contains(a)) {
							animList.remove(a);
						}
					}
				}
				return null;
			}
		}).when(animRepMock).deleteAll(Mockito.any(Iterable.class));

		Animal an1 = animList.get(0);
		Animal an2 = animList.get(1);

		List<Animal> animalsToDelete = new ArrayList<>();

		animalsToDelete.add(an1);
		animalsToDelete.add(an2);

		try {
			animalService.deleteAll(animalsToDelete);
		} catch (AnimalNotFoundException e) {
			assertTrue(false);
		}

		assertEquals(animalsSize - 2, animList.size());
		try {
			animalService.deleteAll(null);
			assertTrue(false);
		} catch (AnimalNotFoundException e) {
			assertTrue(true);
		}

	}
}
