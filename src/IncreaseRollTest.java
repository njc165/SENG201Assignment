import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncreaseRollTest {

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
	final void testIncreaseRoll() {
		// Constructor works and sets type to correct value
		IncreaseRoll increaseRoll = new IncreaseRoll();
		assertEquals(PowerUpType.INCREASE_ROLL, increaseRoll.getType());
		
		// Is a subclass of PowerUp
		assertTrue(increaseRoll instanceof PowerUp);	}

}
