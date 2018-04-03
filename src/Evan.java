public class Evan extends Villain {
	
	/**
	 * The villain's name.
	 */
	private static final String NAME = "Evan the Odd";
	
	/**
	 * Taunt shouted by the villain at the start of a battle.
	 */
	private static final String TAUNT = "It's time to even the odds!";
	
	/**
	 * Damage dealt by a villain to a hero's health when it wins the game.
	 */
	private static final double DAMAGE_DEALT = 25;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGames[] GAMES_PLAYED = {MiniGames.PAPER_SCISSORS_ROCK, MiniGames.DICE_ROLLS};
	
	/**
	 * A constructor for Evan.
	 */
	public Evan() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
