import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaperScissorsRockTest {

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

	// TODO redo tests
//	@Test
//	final void testPaperScissorsRock() {
//		// Create paper scissors rock number game
//		PaperScissorsRockCMD game = new PaperScissorsRockCMD(new Apprentice(""), new John());
//		
//		// Superclass getters and setters can be used
//		assertEquals("Apprentice", game.getHero().getType());
//		assertEquals("John the Lucky", game.getVillain().toString());
//		
//		// hasWon is initialised to false, and can be changed using setter
//		assertFalse(game.getHasWon());
//		game.setHasWon(true);
//		assertTrue(game.getHasWon());	}
//
//	@Test
//	final void testRemoveRelevantPowerUps() {
//		Hero hero = new Apprentice("");
//		PaperScissorsRockCMD game = new PaperScissorsRockCMD(hero, new John());
//		
//		// No effect if hero has no power-ups
//		game.removeRelevantPowerUps();
//		assertArrayEquals(new PowerUp[0], hero.getActivePowerUps().toArray());
//		
//		// Only the correct power-ups are removed
//		hero.addPowerUp(new IncreaseRoll());
//		hero.addPowerUp(new TieBreaker());
//		hero.addPowerUp(new ExtraGuess());
//		hero.addPowerUp(new MindReader());
//		game.removeRelevantPowerUps();
//		assertArrayEquals(new PowerUp[] {new IncreaseRoll(), new ExtraGuess()},
//				hero.getActivePowerUps().toArray());	
//	}

}
