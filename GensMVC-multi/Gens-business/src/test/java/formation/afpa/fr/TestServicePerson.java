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

import formation.afpa.fr.entity.Person;
import formation.afpa.fr.exception.PersonAlreadyExistsException;
import formation.afpa.fr.exception.PersonFirstNameNotValidException;
import formation.afpa.fr.exception.PersonNotAvailableException;
import formation.afpa.fr.exception.PersonNotFoundException;
import formation.afpa.fr.exception.PersonNotValidException;
import formation.afpa.fr.repository.PersonRepository;

@RunWith(MockitoJUnitRunner.class) // On indique qu’on utilise Mockito
public class TestServicePerson {

	@Mock // Ici on indique qu’on a un mock qui simule le repository de Person
	private PersonRepository repoMock;

	@InjectMocks // On indique que le mock est utilisé par le ServicePerson
	private ServicePerson service;

	List<Person> pList = new ArrayList<>();
	int listSize = 0;
	Long id = 0l;
	String firstName = "Camille";
	Integer age = 29;

	@Before
	public void setUp() {
		pList.add(new Person(0l, "Natalia", "MATHIEU", 29));
		pList.add(new Person(1l, "Kiab", "THAO", 35));
		pList.add(new Person(2l, "Camille", "Lacour", 37));

		listSize = pList.size();
		// refactored for several tests
		when(repoMock.findById(id)).thenReturn(Optional.of(getPersonById(id)));
		when(repoMock.findById(0l)).thenReturn(Optional.of(getPersonById(0l)));
		when(repoMock.findAll()).thenReturn(pList);
	}

	@Test
	public void findAll() {
		try {
			assertEquals(listSize, service.list().size());
		} catch (PersonNotAvailableException e) {
			Assert.fail("Test failed : the exception was not expected"); // failed = should not be thrown
		}

		pList = new ArrayList<>(); // we initialise the list to 0 and test that the exception is well thrown
		assertEquals(pList.size(), 0);

		try {
			service.list();
		} catch (PersonNotAvailableException e) {
			e.printStackTrace();
			assertTrue(true); // true = is thrown
		}
	}

	@Test
	public void findByFirstName() {
		when(repoMock.findByFirstName(firstName)).thenReturn(getPersonByFName(firstName));

		try {
			assertEquals(service.findByFirstName(firstName).size(), 1);
			assertEquals(service.findByFirstName(firstName).get(0).getFirstName(), firstName);
		} catch (PersonFirstNameNotValidException e) {
			Assert.fail("The exception was not expected");
		} catch (PersonNotFoundException e) {
			Assert.fail("The exception was not expected");
		}

		try {
			service.findByFirstName(null);
		} catch (PersonFirstNameNotValidException e) {
			assertTrue(true); // as this exception should be arisen
		} catch (PersonNotFoundException e) {
			Assert.fail("The exception was not expected"); // as the error is not appropriate if the fName is null &
															// service conditions could even not have reached this step,
															// should get out the loop before
		}

		try {
			service.findByFirstName("Lola");
		} catch (PersonFirstNameNotValidException e) {
			Assert.fail("The exception was not expected"); // as the exception is not appropriate, the String fName is
															// not null
		} catch (PersonNotFoundException e) {
			assertTrue(true); // as this is the correct exception to come out
		}
	}

	private List<Person> getPersonByFName(String firstName) {
		List<Person> listToReturn = new ArrayList<>();

		for (Person person : pList) {
			if (person.getFirstName().equals(firstName)) {
				listToReturn.add(person);
			}
		}
		return listToReturn;
	}

	@Test
	public void findById() {

		try {
			assertNotNull(service.findById(id));
			assertEquals(service.findById(id).getAge(), age);

		} catch (PersonNotFoundException e) {
			Assert.fail("The exception was not expected");
		}

		try {
			service.findById(-1l);
		} catch (PersonNotFoundException e) {
			assertTrue(true);
		}
	}

	private Person getPersonById(Long id) {
		Person p = new Person();

		for (Person person : pList) {
			if (person.getId() == id) {
				p = person;
			}
		}
		return p;

	}

	@Test
	public void create() throws PersonNotFoundException {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Person p = (Person) os[0];
					p.setId(12L);
					pList.add(p);
				}
				return null;
			}
		}).when(repoMock).save(Mockito.any(Person.class));

		Person p = new Person();
		p.setFirstName("Coucou");
		p.setLastName("salut");
		p.setAge(7);

		try {
			service.create(p);
		} catch (PersonNotValidException e) {
			Assert.fail("The exception was not expected");
		} catch (PersonAlreadyExistsException e) {
			Assert.fail("The exception was not expected");
		}
		assertEquals(listSize + 1, pList.size());
		assertEquals(pList.get(3).getId().longValue(), 12L);

		try {
			service.create(null);
		} catch (PersonNotValidException e) {
			assertTrue(true); // as id == null
		} catch (PersonAlreadyExistsException e) {
			Assert.fail("Test failed : PersonAlreadyExistsException was not expected");
		}

		Person p2 = new Person();
		p2.setId(456456L);
		p2.setFirstName("AA");
		p2.setLastName("BB");
		p2.setAge(10);

		try {
			service.create(p2);
		} catch (PersonNotValidException e) {
			assertTrue(true); // as id id not null
		} catch (PersonAlreadyExistsException e) {
			Assert.fail("Test failed : PersonAlreadyExistsException was not expected");
		}

		try {
			service.create(service.findById(0l));
		} catch (PersonNotValidException e) {
			Assert.fail("Test failed: PersonNotValidException was not expected");
		} catch (PersonAlreadyExistsException e) {
			assertTrue(true);
		}

	}

	@Test
	public void update() throws PersonNotFoundException {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Person p = (Person) os[0];
					Integer indexOf = pList.indexOf(p);

					if (indexOf != null) {
						Person oldPerson = pList.get(indexOf);
						oldPerson.setId(p.getId());
						oldPerson.setFirstName(p.getFirstName());
						oldPerson.setLastName(p.getLastName());
						oldPerson.setAge(p.getAge());
					}
				}
				return null;
			}
		}).when(repoMock).save(Mockito.any(Person.class));

		Person sp = service.findById(0l);
		sp.setFirstName("AA");

		try {
			service.update(sp);
		} catch (PersonNotValidException e) {
			Assert.fail("Test failed: PersonNotValidException was not expected");
		} catch (PersonNotFoundException e) {
			Assert.fail("Test failed: PersonNotFoundException was not expected");
		}
		assertEquals(listSize, pList.size());
		assertEquals(0L, pList.get(0).getId().longValue());
		assertEquals("AA", service.findById(0l).getFirstName());

		try {
			service.update(null);
		} catch (PersonNotValidException e) {
			assertTrue(true);
		} catch (PersonNotFoundException e) {
			Assert.fail("Test failed: PersonNotFoundException was not expected");
		}

		try {
			service.update(new Person(null, "test", "test", 11));
		} catch (PersonNotValidException e) {
			assertTrue(true); // as the id cannot be null for an existing person
		} catch (PersonNotFoundException e) {
			Assert.fail("Test failed: PersonNotFoundException was not expected");
		}

	}
	
	@Test
	public void deleteById() {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Long id = (Long) os[0];

					for (Person person : pList) {
						if (person.getId() == id) {
							pList.remove(person);
							break;
						}
					}
				}
				return null;
			}
		}).when(repoMock).deleteById(Mockito.any(Long.class));
		
		try {
			service.deleteById(id);		
			assertEquals(listSize - 1, pList.size());
		} catch (PersonNotFoundException e) {
			Assert.fail();
		}


		try {
			service.deleteById(null);
		} catch (PersonNotFoundException e) {
			assertTrue(true);
		}
	}

}
