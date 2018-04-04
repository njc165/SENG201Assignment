import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CityTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	final void testCity() {
		// A new City can be created without any errors
		City city = new City();
		
		// Two consecutively created cities have different sector locations
		// Will fail occasionally when the two cities are created identical by chance
		City city1 = new City();
		city1.setAllDiscovered();
		City city2 = new City();
		city2.setAllDiscovered();
		assertFalse(city1.toString().equals(city2.toString()));
	}

	@Test
	final void testToString() {
		// A newly created city returns the correct String
		City city = new City();
		assertEquals("North: ?\nEast: ?\nSouth: ?\nWest: ?\n", city.toString());
		
		// When all sectors are discovered, the string contains the name of each sector
		city.setAllDiscovered();
		String string = city.toString();
		for (SectorType sectorType: SectorType.values()) {
			assertTrue(string.contains(sectorType.toString()));
		}
	}

}
