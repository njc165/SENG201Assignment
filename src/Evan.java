/**
 * Instances of Evan represent a type of Villain the player
 * could battle in the game. Each Game should use at most one
 * Evan villain to avoid duplicate encounters.
 */
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
	private static final int DAMAGE_DEALT = 25;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGameType[] GAMES_PLAYED = {MiniGameType.PAPER_SCISSORS_ROCK,
														MiniGameType.DICE_ROLLS};
	
	/**
	 * A constructor for Evan.
	 */
	public Evan() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
