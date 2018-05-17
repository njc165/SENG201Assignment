import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MiniGameTest {

	@Test
	final void testRemovePowerUps() {
		Hero hero = new Bulwark("Adam");
		Villain villain = new Invictus();
		MiniGame game = new PaperScissorsRock(hero, villain);
		
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new MindReader());
		hero.addPowerUp(new IncreaseRoll());
		hero.addPowerUp(new ExtraGuess());
		
		// Check that the correct number of power-ups is removed
		game.removePowerUps(PowerUpType.TIEBREAKER, 1);
		assertEquals(1, hero.numPowerUps(PowerUpType.TIEBREAKER));
		
		// Check that other applied power-ups are not affected
		game.removePowerUps(PowerUpType.TIEBREAKER, 1);
		assertEquals(0, hero.numPowerUps(PowerUpType.TIEBREAKER));
		assertEquals(1, hero.numPowerUps(PowerUpType.MINDREADER));
		assertEquals(1, hero.numPowerUps(PowerUpType.INCREASE_ROLL));
		assertEquals(1, hero.numPowerUps(PowerUpType.EXTRA_GUESS));
		
		// Check that an exception is thrown when too many power-ups
		// are removed
		boolean exceptionThrown = false;
		try {
			game.removePowerUps(PowerUpType.TIEBREAKER, 100);
		} catch (RuntimeException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}
	
	@Test
	final void testRemoveAllPowerUps() {
		Hero hero = new Bulwark("Eve");
		Villain villain = new Invictus();
		MiniGame game = new PaperScissorsRock(hero, villain);
		
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new MindReader());
		hero.addPowerUp(new IncreaseRoll());
		hero.addPowerUp(new ExtraGuess());

		// Check that all power-ups are removed
		game.removeAllPowerUps(PowerUpType.TIEBREAKER);
		assertEquals(0, hero.numPowerUps(PowerUpType.TIEBREAKER));
		
		// Check that other applied power-ups are not affected
		assertEquals(1, hero.numPowerUps(PowerUpType.MINDREADER));
		assertEquals(1, hero.numPowerUps(PowerUpType.INCREASE_ROLL));
		assertEquals(1, hero.numPowerUps(PowerUpType.EXTRA_GUESS));
	}

}
