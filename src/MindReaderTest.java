import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class MindReaderTest {

	@Test
	final void testMindReader() {
		// Constructor works and sets type to correct value
		MindReader mindReader = new MindReader();
		assertEquals(PowerUpType.MINDREADER, mindReader.getType());
		
		// Is a subclass of PowerUp
		assertTrue(mindReader instanceof PowerUp);
	}

}
