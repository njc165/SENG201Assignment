import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

	@Test
	void testVillain() {
		// Create new villain, check that name is set correctly
		Villain villain = new Villain("Name", "", 0, new MiniGames[]{MiniGames.PAPER_SCISSORS_ROCK});
		assertEquals("Name", villain);
		
		// An exception is thrown if the constructor is given an empty gamesPlayed array
		boolean exceptionThrown = false;
		try {
			villain = new Villain("Name", "", 0, new MiniGames[0]);
		} catch (IllegalArgumentException iae) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	@Test
	final void testGetGame() {
		// If villain only plays one game, test that getGame() returns this game.
		MiniGames[] games = new MiniGames[]{MiniGames.PAPER_SCISSORS_ROCK};
		Villain villain1 = new Villain("", "", 0, games);
		assertEquals(MiniGames.PAPER_SCISSORS_ROCK, villain1.getGame());
		
		// If villain plays several games, test that getGame() returns one of these games.
		games = new MiniGames[]{MiniGames.PAPER_SCISSORS_ROCK, MiniGames.GUESS_NUMBER, MiniGames.DICE_ROLLS};
		Villain villain2 = new Villain("", "", 0, games);
		assertTrue(Arrays.asList(games).contains(villain2.getGame()));
		
		// gamesPlayed can't be empty, as this throws an exception in the
		// Villain constructor.
	}
	
	@Test
	final void testRandomisedVillains() {
		int numVillains = 3;
		ArrayList<Villain> villains = Villain.randomisedVillains(numVillains);
		
		// List is the right length
		assertEquals(3, villains.size());
		
		// Last villain is the super villain
		assertTrue(villains.get(2) instanceof Invictus);
		
		// Other villains aren't supper villains
		assertFalse(villains.get(0) instanceof Invictus);
		assertFalse(villains.get(1) instanceof Invictus);
		
		// No repetitions on list
		assertFalse(villains.get(0).getClass() == villains.get(1).getClass());
		
		// numVillains = 0 throws an IllegalArgumentException
		numVillains = 0;
		boolean exceptionThrown = false;
		try {
			villains = Villain.randomisedVillains(numVillains);
		} catch (IllegalArgumentException iae){
			exceptionThrown = true;
		}
		
		assertTrue(exceptionThrown);
		
		// numVillains = 1 returns a list containing only the super villain
		numVillains = 1;
		villains = Villain.randomisedVillains(numVillains);
		assertEquals(1, villains.size());
		assertTrue(villains.get(0) instanceof Invictus);
	}
	
	@Test
	final void testSetTimesDefeated() {
		Bucephalus villain = new Bucephalus();
		
		// Setting timesDefeated to a number less than MAX_TIMES_DEFEATED
		// doesn't change isDefeated
		villain.setTimesDefeated(1);
		assertEquals(1, villain.getTimesDefeated());
		assertFalse(villain.isDefeated());
		
		// Setting timesDefeated to a number greater than MAX_TIMES_DEFATED
		// sets isDefeated to true
		villain.setTimesDefeated(10);
		assertEquals(10, villain.getTimesDefeated());
		assertTrue(villain.isDefeated());
	}

	
	
}
