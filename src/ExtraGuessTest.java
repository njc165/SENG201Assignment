import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExtraGuessTest {

	@Test
	final void testExtraGuess() {
		// Constructor works and sets type to correct value
		ExtraGuess extraGuess = new ExtraGuess();
		assertEquals(PowerUpType.EXTRA_GUESS, extraGuess.getType());
		
		// Is a subclass of PowerUp
		assertTrue(extraGuess instanceof PowerUp);
	}

}
