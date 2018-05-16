import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TieBreakerTest {

	@Test
	final void testTieBreaker() {
		// Constructor works and sets type to correct value
		TieBreaker tieBreaker = new TieBreaker();
		assertEquals(PowerUpType.TIEBREAKER, tieBreaker.getType());
		
		// Is a subclass of PowerUp
		assertTrue(tieBreaker instanceof PowerUp);
	}

}
