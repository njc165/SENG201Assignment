public class Invictus extends Villain {
	
	/**
	 * The villain's name.
	 */
	private static final String NAME = "Invictus the Unconquered";
	
	/**
	 * Taunt shouted by the villain at the start of a battle.
	 */
	private static final String TAUNT = "I am the biggest, the baddest, most evil! Now you die!";
	
	/**
	 * Damage dealt by a villain to a hero's health when it wins the game.
	 */
	private static final double DAMAGE_DEALT = 50;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGame[] GAMES_PLAYED = {MiniGame.PAPER_SCISSORS_ROCK, MiniGame.GUESS_NUMBER, MiniGame.DICE_ROLL};
	
	/**
	 * A constructor for Invictus.
	 */
	public Invictus() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
