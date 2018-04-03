import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

class VillainTest {

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

	/**
	 * Test method for {@link Villain#getGame()}.
	 */
	@Test
	void testGetGame() {
		// If villain only plays one game, test that getGame() returns this game.
		Villain villain1 = new Villain("", "", 0, new MiniGames[]{MiniGames.PAPER_SCISSORS_ROCK});
		assertEquals(MiniGames.PAPER_SCISSORS_ROCK, villain1.getGame());
		
		// If villain plays several games, test that getGame() returns one of these games.
		MiniGames[] games = new MiniGames[]{MiniGames.PAPER_SCISSORS_ROCK, MiniGames.GUESS_NUMBER, MiniGames.DICE_ROLLS};
		Villain villain2 = new Villain("", "", 0, games);
		assertTrue(Arrays.asList(games).contains(villain2.getGame()));
		
		// gamesPlayed can't be empty, as this throws an exception in the Villain constructor.
	}

}
