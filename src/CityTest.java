import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CityTest {

	@Test
	final void testCity() {
		// A new City can be created without any errors
		Maverick villain = new Maverick();
		City city = new City(villain);
		
		// Two consecutively created cities have different sector locations
		// Will fail occasionally when the two cities are created identical by chance
		City city1 = new City(villain);
		city1.setAllDiscovered();
		City city2 = new City(villain);
		city2.setAllDiscovered();
		assertFalse(city1.toString().equals(city2.toString()));
		
		// Test villain getter method
		city = new City(new John());
		assertTrue(city.getVillain() instanceof John);
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
	final void testSetAllDiscovered() {
		// A new city doesn't have all sectors discovered
		City city = new City(new Bucephalus());
		city.setCurrentLocation(Location.NORTH);
		assertFalse(city.getCurrentSector().getDiscovered());
		
		// Sectors are set to discovered.
		city.setAllDiscovered();
		assertTrue(city.getCurrentSector().getDiscovered());
		city.setCurrentLocation(Location.SOUTH);
		assertTrue(city.getCurrentSector().getDiscovered());
		city.setCurrentLocation(Location.EAST);
		assertTrue(city.getCurrentSector().getDiscovered());
	}
	
	@Test
	final void testSetCurrentSectorDiscovered() {
		City city = new City(new Invictus());
		city.setCurrentLocation(Location.NORTH);
		
		// Initially the current sector is not discovered
		assertFalse(city.getCurrentSector().getDiscovered());
		
		// Current sector is set to discovered
		city.setCurrentSectorDiscovered();
		assertTrue(city.getCurrentSector().getDiscovered());
		
		// Other sectors are still undiscovered
		city.setCurrentLocation(Location.SOUTH);
		assertFalse(city.getCurrentSector().getDiscovered());
		city.setCurrentLocation(Location.WEST);
		assertFalse(city.getCurrentSector().getDiscovered());
	}
	
	@Test
	final void testGetCurrentSector() {
		City city = new City(new Janken());
		// Initially, the current sector is home base
		assertEquals(SectorType.HOME_BASE, city.getCurrentSector().getType());
		
		// When the location is set to something other than center,
		// the sector type is no longer home base.
		city.setCurrentLocation(Location.NORTH);
		assertNotEquals(SectorType.HOME_BASE, city.getCurrentSector().getType());
		
		// The sector returned is correct
		Sector currentSector = city.sectorAtLocation(Location.NORTH);
		assertEquals(currentSector.getType(), city.getCurrentSector().getType());
	}
	

	

}


