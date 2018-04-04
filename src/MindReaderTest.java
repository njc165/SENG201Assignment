import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MindReaderTest {

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
	final void testMindReader() {
		// Constructor works and sets type to correct value
		MindReader mindReader = new MindReader();
		assertEquals(PowerUpType.MINDREADER, mindReader.getType());
		
		// Is a subclass of PowerUp
		assertTrue(mindReader instanceof PowerUp);
	}

}
