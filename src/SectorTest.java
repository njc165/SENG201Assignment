import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SectorTest {

	@Test
	final void testSector() {
		// Create new sector, check that type is set correctly
		Sector hospital = new Sector(SectorType.HOSPITAL);
		assertEquals(SectorType.HOSPITAL, hospital.getType());
		
		// discovered defaults to false
		assertFalse(hospital.getDiscovered());
		hospital.setDiscovered(true);
		assertTrue(hospital.getDiscovered());
	}

}