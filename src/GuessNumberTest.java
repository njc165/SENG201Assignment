import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GuessNumberTest {

	@Test
	final void testGuessNumber() {
		Hero hero = new Merchant("");
		Villain villain = new Maverick();
		GuessNumber game = new GuessNumber(hero, villain);
		
		// The GuessNumber game is a subclass of MiniGame
		assertTrue(game instanceof MiniGame);
		
		// hasWon is initially false
		assertFalse(game.getHasWon());
		
		// Without Extra Guess power ups or special abilities,
		// guessLeft is set to 2.
		assertEquals(2, game.getGuessesLeft());
		
		// If the hero has the battle advantage special ability,
		// the initial guessesLeft is increased by one.
		hero = new Gambler("");
		game = new GuessNumber(hero, villain);
		assertEquals(3, game.getGuessesLeft());
		
		// The initial guessesLeft is increased by one for each
		// Extra Guess power up applied to the hero.
		hero.addPowerUp(new ExtraGuess());
		hero.addPowerUp(new ExtraGuess());
		game = new GuessNumber(hero, villain);
		assertEquals(5, game.getGuessesLeft());
		
		// The number to guess is set to a random number
		// between 1 and the max number.
		for (int i = 0; i < 10; i++) {
			game = new GuessNumber(hero, villain);
			assertTrue(game.getNumberToGuess() >= 1
						&& game.getNumberToGuess() <= GuessNumber.MAX_NUMBER);
		}
	}

	@Test
	final void testGuess() {
		Hero hero = new Mercenary("");
		Villain villain = new John();
		GuessNumber game = new GuessNumber(hero, villain);
		
		// When the guess is incorrect, guessesLeft is decremented, and
		// hasWon remains false.
		int guess = (game.getNumberToGuess() + 1) % 10;
		game.guess(guess);
		assertEquals(1, game.getGuessesLeft());
		assertFalse(game.getHasWon());
		
		assertEquals(guess, game.getGuess());
		
		// When the guess is correct, hasWon is set to true
		game.guess(game.getNumberToGuess());
		assertTrue(game.getHasWon());
		
		// An exception is thrown when if guess is called when
		// there are no guesses left
		boolean exceptionCaught = false;
		try {
			game.guess(guess);
			game.guess(guess);
		} catch (RuntimeException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
	}

	@Test
	final void testNumExtraGuesses() {
		Hero hero = new Gambler("");
		Villain villain = new Janken();
		GuessNumber game = new GuessNumber(hero, villain);
		
		assertEquals(0, game.numExtraGuesses());
		
		hero.addPowerUp(new ExtraGuess());
		assertEquals(1, game.numExtraGuesses());
		
		hero.addPowerUp(new ExtraGuess());
		hero.addPowerUp(new ExtraGuess());
		assertEquals(3, game.numExtraGuesses());

	}

	
	
}
