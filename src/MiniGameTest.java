import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MiniGameTest {

	@Test
	final void testCreateGame() {
		// Create a new hero and villain
		Hero hero = new Merchant("");
		Villain villain = new Maverick();
		
		// Create new MiniGames
		MiniGame game1 = MiniGame.createGame(MiniGames.PAPER_SCISSORS_ROCK, hero, villain);
		MiniGame game2 = MiniGame.createGame(MiniGames.GUESS_NUMBER, hero, villain);
		MiniGame game3 = MiniGame.createGame(MiniGames.DICE_ROLLS, hero, villain);
		
		// Check that each game is correctly instantiatied
		assertTrue(game1 instanceof PaperScissorsRockCMD);
		assertTrue(game2 instanceof GuessNumber);
		assertTrue(game3 instanceof DiceRolls);
		
		
	}

}
