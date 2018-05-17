import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class IncreaseRollTest {

	@Test
	final void testIncreaseRoll() {
		// Constructor works and sets type to correct value
		IncreaseRoll increaseRoll = new IncreaseRoll();
		assertEquals(PowerUpType.INCREASE_ROLL, increaseRoll.getType());
		
		// Is a subclass of PowerUp
		assertTrue(increaseRoll instanceof PowerUp);	}

}
