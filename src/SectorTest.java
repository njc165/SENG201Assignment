import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SectorTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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
