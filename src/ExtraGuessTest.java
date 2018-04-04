import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExtraGuessTest {

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
	final void testExtraGuess() {
		// Constructor works and sets type to correct value
		ExtraGuess extraGuess = new ExtraGuess();
		assertEquals(PowerUpType.EXTRA_GUESS, extraGuess.getType());
		
		// Is a subclass of PowerUp
		assertTrue(extraGuess instanceof PowerUp);
	}

}
