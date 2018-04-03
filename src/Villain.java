import java.util.Random;

public class Villain {
	
	/**
	 * The name of the villain. Each subclass of Villain has a different name.
	 */
	private String name;
	
	/**
	 * The taunt which the villan displays when battling a hero.
	 * Each subclass of Villain has a different taunt.
	 */
	private String taunt;
	
	/**
	 * The damage to a hero's health when it loses a battle to this villain.
	 * Each subclass of Villain has a different damage dealt.
	 */
	private double damageDealt;
	
	/**
	 * An array of the games a villain may choose to play, represented as values of the
	 * enum type MiniGame.
	 * Each game on the list has an equal probability of being chosen.
	 * Each subclass of Villain has a different list of games played.
	 */
	private MiniGame[] gamesPlayed;
	
	/**
	 * Initialised to false when the villain is created, and set to false once the villain
	 * has been defeated three times in a mini game.
	 */
	private boolean isDefeated = false;
	
	/**
	 * Constructor called from the constructors of the subclasses of Villain.
	 * Sets the attribute variables of the villain to the values defined by the particular subclass.
	 * @param name	The name of the Villain subclass.
	 * @param taunt	The taunt displayed by the Villian subclass.
	 * @param damageDealt	The damage dealt by the Villain subclass.
	 * @param gamesPlayed	The list of games which are played by the villain subclass.
	 */
	public Villain(String name, String taunt, double damageDealt, MiniGame[] gamesPlayed) {
		this.name = name;
		this.taunt = taunt;
		this.damageDealt = damageDealt;
		
		if (gamesPlayed.length > 0)
			this.gamesPlayed = gamesPlayed;
		else
			throw new IllegalArgumentException("gamesPlayed array cannot be empty");
	}	
	
	/**
	 * Getter method for name.
	 * @return The value of name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get a random game from the list of games the villain can play.
	 * @return	A game of the enum type MiniGame.
	 */
	public MiniGame getGame() {
		Random generator = new Random();
		int randomIndex = generator.nextInt(gamesPlayed.length);
		return gamesPlayed[randomIndex];
	}
	
}
