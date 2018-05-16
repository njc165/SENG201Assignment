import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MiniGameTest {

	@Test
	final void testRemovePowerUps() {
		Hero hero = new Merchant("");
		Villain villain = new Evan();
		DiceRolls game = new DiceRolls(hero, villain);
		
		// If the hero doesn't have enough power ups of the given
		// type, and exception is thrown
		boolean exceptionCaught = false;
		try {
			game.removePowerUps(PowerUpType.TIEBREAKER, 1);
		} catch (RuntimeException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		
		// If the hero has no power ups, 0 power ups can be removed
		exceptionCaught = false;
		try {
			game.removePowerUps(PowerUpType.MINDREADER, 0);
		} catch (RuntimeException e) {
			exceptionCaught = true;
		}
		assertFalse(exceptionCaught);
		
		// If the hero has several power ups, the correct number of
		// power ups of the correct type are removed
		hero.addPowerUp(new IncreaseRoll());
		hero.addPowerUp(new IncreaseRoll());
		hero.addPowerUp(new IncreaseRoll());
		hero.addPowerUp(new ExtraGuess());
		hero.addPowerUp(new ExtraGuess());
		hero.addPowerUp(new TieBreaker());
		
		game.removePowerUps(PowerUpType.INCREASE_ROLL, 2);
		assertEquals(1, game.getHero().numPowerUps(PowerUpType.INCREASE_ROLL));
		assertEquals(2, game.getHero().numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(1, game.getHero().numPowerUps(PowerUpType.TIEBREAKER));

		game.removePowerUps(PowerUpType.EXTRA_GUESS, 2);
		assertEquals(1, game.getHero().numPowerUps(PowerUpType.INCREASE_ROLL));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(1, game.getHero().numPowerUps(PowerUpType.TIEBREAKER));
		
		game.removePowerUps(PowerUpType.TIEBREAKER, 1);
		assertEquals(1, game.getHero().numPowerUps(PowerUpType.INCREASE_ROLL));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.TIEBREAKER));
		
	}

	@Test
	final void testRemoveAllPowerUps() {
		Hero hero = new Explorer("");
		Villain villain = new Bucephalus();
		GuessNumber game = new GuessNumber(hero, villain);
		
		// All the power ups of the given type are removed from the hero,
		// and power ups of other types are unaffected.
		
		hero.addPowerUp(new MindReader());
		hero.addPowerUp(new MindReader());
		hero.addPowerUp(new MindReader());
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new ExtraGuess());
		
		game.removeAllPowerUps(PowerUpType.INCREASE_ROLL);
		assertEquals(3, game.getHero().numPowerUps(PowerUpType.MINDREADER));
		assertEquals(2, game.getHero().numPowerUps(PowerUpType.TIEBREAKER));
		assertEquals(1, game.getHero().numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.INCREASE_ROLL));

		
		game.removeAllPowerUps(PowerUpType.MINDREADER);
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.MINDREADER));
		assertEquals(2, game.getHero().numPowerUps(PowerUpType.TIEBREAKER));
		assertEquals(1, game.getHero().numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.INCREASE_ROLL));
		
		game.removeAllPowerUps(PowerUpType.TIEBREAKER);
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.MINDREADER));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.TIEBREAKER));
		assertEquals(1, game.getHero().numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.INCREASE_ROLL));

		game.removeAllPowerUps(PowerUpType.EXTRA_GUESS);
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.MINDREADER));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.TIEBREAKER));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(0, game.getHero().numPowerUps(PowerUpType.INCREASE_ROLL));
		
	}

}
