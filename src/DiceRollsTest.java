import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class DiceRollsTest {

	@Test
	final void testDiceRolls() {
		// Create a new instance of Dice Rolls
		Hero hero = new Apprentice("");
		Villain villain = new Evan();
		DiceRolls game = new DiceRolls(hero, villain);
		
		// Dice Rolls is a subclass of MiniGame
		assertTrue(game instanceof MiniGame);
		
		// Hero and villain getters from MiniGame superclass work.
		assertEquals(hero, game.getHero());
		assertEquals(villain, game.getVillain());
		
		// Initially, hasWon is false
		assertFalse(game.getHasWon());
		
		// hasWon can be set to true
		game.setHasWon(true);
		assertTrue(game.getHasWon());
		
		// Roll increase is set to 0 if hero has no Increase Roll
		// power ups and no battle advantage special ability.
		assertEquals(0, game.getRollIncrease());
		
		// If hero has battle advantage special ability, roll increase is
		// set to 1.
		hero = new Gambler("");
		game = new DiceRolls(hero, villain);
		assertEquals(1, game.getRollIncrease());
		
		// If hero has two Increase Roll power ups and has battle advantage
		// special ability, roll increase is set to 3.
		hero.addPowerUp(new IncreaseRoll());
		hero.addPowerUp(new IncreaseRoll());
		game = new DiceRolls(hero, villain);
		assertEquals(3, game.getRollIncrease());
		
	}

	@Test
	final void testRoll() {
		Hero hero = new Bulwark("");
		Villain villain = new Maverick();
		DiceRolls game = new DiceRolls(hero, villain);
		
		// Roll several times and check that hero roll and villain roll
		// are within the correct ranges each time
		for (int i = 0; i < 5; i++) {
			game.roll();
			assertTrue(game.getHeroRoll() >= 1 && game.getHeroRoll() <= 6);
			assertTrue(game.getVillainRoll() >= 1 && game.getVillainRoll() <= 6);
		}

		
	}

	@Test
	final void testNumIncreaseRolls() {
		Hero hero = new Mercenary("");
		Villain villain = new Invictus();
		DiceRolls game = new DiceRolls(hero, villain);
		
		// Returns the correct number of increase rolls applied to the hero.
		assertEquals(0, game.numIncreaseRolls());
		
		hero.addPowerUp(new IncreaseRoll());
		hero.addPowerUp(new IncreaseRoll());
		
		assertEquals(2, game.numIncreaseRolls());
	}

	@Test
	final void testGetResult() {
		Hero hero = new Mercenary("");
		Villain villain = new Invictus();
		DiceRolls game = new DiceRolls(hero, villain);
		
		// Initially, result is null
		assertNull(game.getResult());
		
		// After rolling, the result is set to one of the three correct values
		game.roll();
		assertTrue(Arrays.asList(new String[] {"Win", "Draw", "Lose"}).contains(game.getResult()));
		
		// When the hero has no Tiebreaker power ups, usedTiebreaker remains false
		assertFalse(game.getUsedTieBreaker());
	}

}
