import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UtilTest {

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
	final void testGetIntFromUser() {
		// Set System.in to a different input stream for testing
		ByteArrayInputStream in = new ByteArrayInputStream("3\n".getBytes());
		System.setIn(in);
		
		// Entering a number in the correct range returns that number
		int input = Util.getIntFromUser(5, "Prompt");
		assertEquals(3, input);
		
		// Entering 1 returns 1
		in = new ByteArrayInputStream("1\n".getBytes());
		System.setIn(in);
		input = Util.getIntFromUser(5, "Prompt");
		assertEquals(1, input);
		
		// 0 is not accepted
		in = new ByteArrayInputStream("0\n1\n".getBytes());
		System.setIn(in);
		input = Util.getIntFromUser(5, "Prompt");
		assertEquals(1, input);
		
		// A number greater than maxValue is not accepted
		in = new ByteArrayInputStream("6\n1\n".getBytes());
		System.setIn(in);
		input = Util.getIntFromUser(5, "Prompt");
		assertEquals(1, input);
		
		// A String is not accepted
		in = new ByteArrayInputStream("string\n1\n".getBytes());
		System.setIn(in);
		input = Util.getIntFromUser(5, "Prompt");
		assertEquals(1, input);
		
		// A decimal number is not accepted
		in = new ByteArrayInputStream("3.5\n1\n".getBytes());
		System.setIn(in);
		input = Util.getIntFromUser(5, "Prompt");
		assertEquals(1, input);
		
		// Repeatedly asks for input until a valid number is entered
		in = new ByteArrayInputStream("0\n6\n2.5\n-3\nstring\nanother string\n1\n".getBytes());
		System.setIn(in);
		input = Util.getIntFromUser(5, "Prompt");
		assertEquals(1, input);
		
		// An exception is thrown if maxValue is less than 1
		boolean exceptionThrown = false;
		try {
			in = new ByteArrayInputStream("0\n".getBytes());
			System.setIn(in);
			input = Util.getIntFromUser(0, "Prompt");
		} catch (IllegalArgumentException iae) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		// maxValue can be 1
		exceptionThrown = false;
		input = -1;
		try {
			in = new ByteArrayInputStream("1\n".getBytes());
			System.setIn(in);
			input = Util.getIntFromUser(1, "Prompt");
		} catch (IllegalArgumentException iae) {
			exceptionThrown = true;
		}
		assertFalse(exceptionThrown);
		assertEquals(1, input);
		
		// Restore System.in
		System.setIn(System.in);
	}

}
