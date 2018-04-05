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
		Maverick villain = new Maverick();
		City city = new City(villain);
		
		// The current location is initialised to the right location and sector
		assertEquals(Location.CENTRE, city.getCurrentLocation());
		assertEquals(SectorType.HOME_BASE, city.getCurrentSectorType());
		
		// Two consecutively created cities have different sector locations
		// Will fail occasionally when the two cities are created identical by chance
		City city1 = new City(villain);
		city1.setAllDiscovered();
		City city2 = new City(villain);
		city2.setAllDiscovered();
		assertFalse(city1.toString().equals(city2.toString()));
	}

	@Test
	final void testToString() {
		// A newly created city returns the correct String
		Evan villain = new Evan();
		City city = new City(villain);
		assertEquals("North: ?\nEast: ?\nSouth: ?\nWest: ?\n", city.toString());
		
		// When all sectors are discovered, the string contains the names of some sectors
		city.setAllDiscovered();
		String string = city.toString();
		assertTrue(string.contains("Hospital"));
		assertTrue(string.contains("Power-up Den"));
	}
	
	@Test
	final void testStringWithNumbers() {
		// A newly created city returns the correct string
		City city = new City(new Janken());
		assertEquals("1. North: ?\n2. East: ?\n3. South: ?\n4. West: ?\n",
					city.stringWithNumbers(true));
	}
	
	@Test
	final void testSetCurrentLocationByNumber() {
		// Initial location is set correctly
		City city = new City(new Bucephalus());
		assertEquals(Location.CENTRE, city.getCurrentLocation());
		
		// Location can be set correctly by number
		city.setCurrentLocationByNumber(1);
		assertEquals(Location.NORTH, city.getCurrentLocation());
		
		city.setCurrentLocationByNumber(4);
		assertEquals(Location.WEST, city.getCurrentLocation());
	}
}


