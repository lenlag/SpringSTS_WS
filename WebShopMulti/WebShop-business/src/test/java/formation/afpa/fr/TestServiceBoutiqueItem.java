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

import formation.afpa.fr.Exception.ItemAlreadyExistsException;
import formation.afpa.fr.Exception.ItemNotAvailableException;
import formation.afpa.fr.Exception.ItemNotFoundException;
import formation.afpa.fr.Exception.ItemNotValidException;
import formation.afpa.fr.entity.BoutiqueItem;
import formation.afpa.fr.repository.BoutiqueItemRepository;

@RunWith(MockitoJUnitRunner.class) // On indique qu’on utilise Mockito
public class TestServiceBoutiqueItem {

	@Mock // Ici on indique qu’on a un mock qui simule le repository
	private BoutiqueItemRepository repoMock;

	@InjectMocks // Ici, on indique que le mock est injecté/utilisé par le service à tester 
	private ServiceBoutiqueItem service;

	
	List<BoutiqueItem> list = new ArrayList<>();
	String label = "chemise";
	float price = 300.5f;
	Long id = 1l;
	int listSize;

	@Before
	public void setUp() {
		list.add(new BoutiqueItem(0l, "a", "robe", 200));
		list.add(new BoutiqueItem(1l, "b", "chemise", 150));
		listSize = list.size();

		// used in several tests
		
		when(repoMock.findById(id)).thenReturn(Optional.of(getItemById(id)));
		when(repoMock.findById(0l)).thenReturn(Optional.of(getItemById(0l)));
		when(repoMock.findById(1l)).thenReturn(Optional.of(getItemById(1l)));
		
		when(repoMock.findAll()).thenReturn(list);

	}

	@Test
	public void findAll() throws ItemNotAvailableException {

		// test the list's size

		try {
			assertEquals(listSize, service.findAll().size());
		} catch (ItemNotAvailableException e) {
			// assertTrue(false);
			Assert.fail("Test failed : ItemNotAviableException was not expected");
		}

		// initialise the list to 0 and test that the exception is well thrown
		list = new ArrayList<BoutiqueItem>();
		try {
			service.findAll();
		} catch (ItemNotAvailableException e) {
			e.printStackTrace();
			assertTrue(true);
		}

	}

	
	@Test
	public void findById() {
		when(repoMock.findById(id)).thenReturn(Optional.of(getItemById(id)));
		try {
			assertNotNull(service.findById(id));
			assertEquals(service.findById(id).getLabel(), label);

		} catch (ItemNotFoundException e) {
			Assert.fail("Test failed : ItemNotFoundException was not expected");
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(true);
		}

		try {
			service.findById(null);
		} catch (ItemNotFoundException e) {
			assertTrue(true);
		}

	}

	private BoutiqueItem getItemById(Long id) {
		BoutiqueItem i = null;

		for (BoutiqueItem boutiqueItem : list) {
			if (boutiqueItem.getId().equals(id)) {
				i = boutiqueItem;
			}

		}
		return i;
	}

	
	@Test
	public void create() throws ItemNotValidException {
		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					BoutiqueItem it = (BoutiqueItem) os[0];
					it.setId(12L);
					list.add(it);
				}
				return null;
			}
		}).when(repoMock).save(Mockito.any(BoutiqueItem.class));
		// refactored=>
		// when(repoMock.findById(id)).thenReturn(Optional.of(getItemById(id)));

		BoutiqueItem it = new BoutiqueItem();
		it.setLabel("bonnet");
		it.setPrice(50.3f);

		try {
			service.create(it);
		} catch (ItemNotValidException e) {
			Assert.fail("Test failed : ItemNotValidException was not expected");
		} catch (ItemAlreadyExistsException e) {
			Assert.fail("Test failed : ItemAlreadyExistsException was not expected");
		}

		assertEquals(list.size(), listSize + 1);
		assertEquals(12L, list.get(2).getId().longValue());

		try {
			service.create(null);
		} catch (ItemNotValidException e) {
			assertTrue(true);
		} catch (ItemAlreadyExistsException e) {
			Assert.fail("Test failed : ItemAlreadyExistsException was not expected");
		}
	}

	@Test
	public void update() throws Exception {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					BoutiqueItem it = (BoutiqueItem) os[0];
					Integer indexOf = list.indexOf(it);

					if (indexOf != null) {
						BoutiqueItem oldSpecie = list.get(indexOf);
						oldSpecie.setId(it.getId());
						oldSpecie.setCode(it.getCode());
						oldSpecie.setLabel(it.getLabel());
						oldSpecie.setPrice(it.getPrice());
					}
				}
				return null;
			}
		}).when(repoMock).save(Mockito.any(BoutiqueItem.class));
	//	when(repoMock.findById(id)).thenReturn(Optional.of(getItemById(id)));

		BoutiqueItem it = service.findById(0l);
		it.setCode("AA");

		try {
			service.update(it);
		} catch (ItemNotValidException e) {
			Assert.fail("Test failed: ItemNotValidException was not expected");
		} catch (ItemNotFoundException e) {
			Assert.fail("Test failed: ItemNotFoundException was not expected");
		}
		assertEquals(listSize, list.size());
		assertEquals(0L, list.get(0).getId().longValue());
		assertEquals("AA", service.findById(0l).getCode());

		try {
			service.update(null);
		} catch (ItemNotValidException e) {
			assertTrue(true);
		} catch (ItemNotFoundException e) {
			Assert.fail("Test failed: ItemNotFoundException was not expected");
		}

	}

	@Test
	public void delete() throws Exception {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					BoutiqueItem it = (BoutiqueItem) os[0];

					if (list.contains(it)) {
						list.remove(it);
					}
				}
				return null;
			}
		}).when(repoMock).delete(Mockito.any(BoutiqueItem.class));
		
		try {
			BoutiqueItem it = service.findById(0l);
			it.setId(null);
			service.delete(it);

		} catch (ItemNotFoundException e) {
			assertTrue(true);

		} catch (ItemNotValidException e) {
			Assert.fail("Test failed : ItemNotValidException was not expected");

		} catch (ItemNotAvailableException e) {
			Assert.fail("Test failed : ItemNotAvailableException was not expected");

		}

		// trying to delete a null specie
		try {
			service.delete(null);

		} catch (ItemNotFoundException e) {
			Assert.fail("Test failed : ItemNotFoundException was not expected");

		} catch (ItemNotValidException e) {
			assertTrue(true);

		} catch (ItemNotAvailableException e) {
			Assert.fail("Test failed : ItemNotAvailableException was not expected");

		}

		// case where you try to delete an object that is not the the DB
		BoutiqueItem it3 = new BoutiqueItem();
		
		it3.setCode("AA");
		it3.setLabel("BB");
		it3.setPrice(150f);
		
		
		try {
			service.delete(it3);

		} catch (ItemNotFoundException e) {
			assertTrue(true);

		} catch (ItemNotValidException e) {
			Assert.fail("Test failed : ItemNotValidException was not expected");

		} catch (ItemNotAvailableException e) {
			assertTrue(true);
		}

		// normal case
		assertNotNull(service.findById(1l));
		BoutiqueItem it = service.findById(1l);
		try {
			service.delete(it);
			assertEquals(listSize - 1, list.size());
		} catch (ItemNotFoundException e) {
			Assert.fail("Test failed : ItemNotFoundException was not expected");
		} catch (ItemNotValidException e) {
			Assert.fail("Test failed : ItemNotValidException was not expected");
		} catch (ItemNotAvailableException e) {
			Assert.fail("Test failed : ItemNotAvailableException was not expected");
		}

	}

	@Test
	public void deleteById() throws ItemNotFoundException {

		doAnswer(new Answer<Void>() {
			@Override
			public Void answer(InvocationOnMock invocation) throws Throwable {
				Object[] os = invocation.getArguments();
				if (os != null && os.length != 0 && os[0] != null) {
					Long id = (Long) os[0];

					for (BoutiqueItem i : list) {
						if(i.getId() == id) {
							list.remove(i);
							break;
						}
					}
					
				}
				return null;
			}
		}).when(repoMock).deleteById(Mockito.any(Long.class));

		try {
			service.deleteById(0l);
		} catch (ItemNotFoundException e) {
			Assert.fail();
		}
		assertEquals(listSize - 1, list.size());

		try {
			service.deleteById(null);
			assertTrue(false);
		} catch (ItemNotFoundException e) {
			assertTrue(true);
		}
	}

}
