import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TieBreakerTest {

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
	final void testTieBreaker() {
		// Constructor works and sets type to correct value
		TieBreaker tieBreaker = new TieBreaker();
		assertEquals(PowerUpType.TIEBREAKER, tieBreaker.getType());
		
		// Is a subclass of PowerUp
		assertTrue(tieBreaker instanceof PowerUp);
	}

}
