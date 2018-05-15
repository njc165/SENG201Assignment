public class Maverick extends Villain {
	
	/**
	 * The villain's name.
	 */
	private static final String NAME = "Maverick the Strong";
	
	/**
	 * Taunt shouted by the villain at the start of a battle.
	 */
	private static final String TAUNT = "Come! Face the great Maverick!";
	
	/**
	 * Damage dealt by a villain to a hero's health when it wins the game.
	 */
	private static final int DAMAGE_DEALT = 20;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGameType[] GAMES_PLAYED = {MiniGameType.PAPER_SCISSORS_ROCK, MiniGameType.GUESS_NUMBER, MiniGameType.DICE_ROLLS};
	
	/**
	 * A constructor for Maverick.
	 */
	public Maverick() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
