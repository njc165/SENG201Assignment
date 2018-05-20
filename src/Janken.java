/**
 * Janken objects represent a type of villain that the
 * player can encounter in the game. At most one city
 * should use Janken to avoid duplicate encounters.
 */
public class Janken extends Villain {
	
	/**
	 * The villain's name.
	 */
	private static final String NAME = "Janken the Pon";
	
	/**
	 * Taunt shouted by the villain at the start of a battle.
	 */
	private static final String TAUNT = "Jan Ken PON!";
	
	/**
	 * Damage dealt by a villain to a hero's health when it wins the game.
	 */
	private static final int DAMAGE_DEALT = 30;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGameType[] GAMES_PLAYED = {MiniGameType.PAPER_SCISSORS_ROCK};
	
	/**
	 * A constructor for Janken.
	 */
	public Janken() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
