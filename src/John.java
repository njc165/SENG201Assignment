public class John extends Villain {
	
	/**
	 * The villain's name.
	 */
	private static final String NAME = "John the Lucky";
	
	/**
	 * Taunt shouted by the villain at the start of a battle.
	 */
	private static final String TAUNT = "Who will the dice favour today?";
	
	/**
	 * Damage dealt by a villain to a hero's health when it wins the game.
	 */
	private static final double DAMAGE_DEALT = 30;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGame[] GAMES_PLAYED = {MiniGame.DICE_ROLL};
	
	/**
	 * A constructor for John.
	 */
	public John() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}