import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;

class VillainTest {

	@Test
	void testVillain() {
		// Create new villain, check that name is set correctly
		Villain villain = new Villain("Name", "", 0, new MiniGameType[]{MiniGameType.PAPER_SCISSORS_ROCK});
		assertEquals("Name", villain.toString());
		
		// An exception is thrown if the constructor is given an empty gamesPlayed array
		boolean exceptionThrown = false;
		try {
			villain = new Villain("Name", "", 0, new MiniGameType[0]);
		} catch (IllegalArgumentException iae) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	@Test
	final void testGetGame() {
		// If villain only plays one game, test that getGame() returns this game.
		MiniGameType[] games = new MiniGameType[]{MiniGameType.PAPER_SCISSORS_ROCK};
		Villain villain1 = new Villain("", "", 0, games);
		assertEquals(MiniGameType.PAPER_SCISSORS_ROCK, villain1.getGame());
		
		// If villain plays several games, test that getGame() returns one of these games.
		games = new MiniGameType[]{MiniGameType.PAPER_SCISSORS_ROCK, MiniGameType.GUESS_NUMBER, MiniGameType.DICE_ROLLS};
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
		
		// Other villains aren't super villains
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

	@Test
	final void testToString() {
		
		// Check that the villain's name and title are returned correctly
		Invictus villain = new Invictus();
		assertEquals(villain.toString(), "Invictus the Unconquered");
		
		John johnny = new John();
		assertEquals(johnny.toString(), "John the Lucky");
	}
	
	@Test
	final void testRemainingTimesToDefeat() {
		
		// Check that a new villain has 'full health'
		John johnny = new John();
		assertEquals(johnny.remainingTimesToDefeat(), Villain.MAX_TIMES_DEFEATED);
		
		// Check that remainingTimesToDefeat returns 1 when the villain
		// will be defeated after one more loss.
		johnny.setTimesDefeated(Villain.MAX_TIMES_DEFEATED-1);
		assertEquals(johnny.remainingTimesToDefeat(), 1);
	}
	
}
