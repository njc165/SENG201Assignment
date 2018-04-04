import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PowerUpTest {

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
	final void testEqualsObject() {
		// Two power-ups of the same type are considered equal
		TieBreaker tieBreaker1 = new TieBreaker();
		TieBreaker tieBreaker2 = new TieBreaker();
		assertTrue(tieBreaker1.equals(tieBreaker2));
		
		// Two power-ups of different types are not considered equal
		ExtraGuess extraGuess = new ExtraGuess();
		assertFalse(tieBreaker1.equals(extraGuess));
		
		// A power-up is not equal to an object of a different type
		assertFalse(tieBreaker1.equals(new String()));
	}

}
