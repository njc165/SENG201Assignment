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
	private static final double DAMAGE_DEALT = 20;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGame[] GAMES_PLAYED = {MiniGame.PAPER_SCISSORS_ROCK, MiniGame.GUESS_NUMBER, MiniGame.DICE_ROLL};
	
	/**
	 * A constructor for Maverick.
	 */
	public Maverick() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
