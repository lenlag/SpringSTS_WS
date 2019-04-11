package formation.afpa.fr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import formation.afpa.fr.entity.Country;
import formation.afpa.fr.repository.CountryRepository;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SmallApp.class)
public class CountryTest  extends TestParent {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CountryRepository countryRep;

	@Test
	public void List() {
		try {
			List<Country> countries = (java.util.List<Country>) countryRep.findAll();
			assertNotNull(countries);
			assertEquals(countries.size(), 4);
		} catch (Exception e) {
			assertTrue(false);
		}
	}

}
