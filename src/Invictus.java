/**
 * Invictus objects represent the super villain final
 * boss in the game. The last city of the game should
 * always have Invictus as its villain, and no other cities
 * should use Invictus. The game is considered won when this
 * villain is defeated.
 */
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
	private static final int DAMAGE_DEALT = 50;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGameType[] GAMES_PLAYED = {MiniGameType.PAPER_SCISSORS_ROCK, MiniGameType.GUESS_NUMBER, MiniGameType.DICE_ROLLS};
	
	/**
	 * A constructor for Invictus.
	 */
	public Invictus() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
