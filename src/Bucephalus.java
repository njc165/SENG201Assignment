/**
 * Instances of Bucephalus represent a type of Villain that the
 * player can battle in the game. Each Game object should use
 * at most one Bucephalus object, to avoid duplicate encounters
 * in the game.
 */
public class Bucephalus extends Villain {
	
	/**
	 * The villain's name.
	 */
	private static final String NAME = "Bucephalus the Binary";
	
	/**
	 * Taunt shouted by the villain at the start of a battle.
	 */
	private static final String TAUNT = "You won't get past me!";
	
	/**
	 * Damage dealt by a villain to a hero's health when it wins the game.
	 */
	private static final int DAMAGE_DEALT = 30;
	
	/**
	 * Array of games the villain could choose to play.
	 */
	private static final MiniGameType[] GAMES_PLAYED = {MiniGameType.GUESS_NUMBER};
	
	/**
	 * A constructor for Bucephalus.
	 */
	public Bucephalus() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
