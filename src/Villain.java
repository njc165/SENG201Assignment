import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Villain {
	
	/**
	 * An instance of the super villain, to be used when initialising the list of
	 * villains in a new game.
	 */
//	public static final Villain SUPER_VILLAIN = new Invictus();
	
	/**
	 * An array of instances of all the villain subclasses except the super villain,
	 * to be used when initialising the list of villains in a new game.
	 */
//	public static final Villain[] REGULAR_VILLAINS = {new Bucephalus(),
//													  new Evan(),
//													  new Janken(),
//													  new John(),
//													  new Maverick()};
	
	/**
	 * The number of times a villain can lose MiniGames before it is killed.
	 */
	public static final int MAX_TIMES_DEFEATED = 3;
	
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
	private int damageDealt;
	
	/**
	 * The number of times this villain has been defeated in a MiniGame.
	 */
	private int timesDefeated;
	
	/**
	 * An array of the games a villain may choose to play, represented as values of the
	 * enum type MiniGame.
	 * Each game on the list has an equal probability of being chosen.
	 * Each subclass of Villain has a different list of games played.
	 */
	private MiniGames[] gamesPlayed;
	
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
	public Villain(String name, String taunt, int damageDealt, MiniGames[] gamesPlayed) {
		this.name = name;
		this.taunt = taunt;
		this.damageDealt = damageDealt;
		
		if (gamesPlayed.length > 0)
			this.gamesPlayed = gamesPlayed;
		else
			throw new IllegalArgumentException("gamesPlayed array cannot be empty");
	}
	
	/**
	 * Getter method for taunt.
	 * @return The value of taunt.
	 */
	public String getTaunt() {
		return taunt;
	}
	
	/**
	 * Getter method for damageDealt.
	 * @return The value of damageDealt.
	 */
	public int getDamageDealt() {
		return damageDealt;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
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
	public MiniGames getGame() {
		Random generator = new Random();
		int randomIndex = generator.nextInt(gamesPlayed.length);
		return gamesPlayed[randomIndex];
	}
	
	/**
	 * Getter method for isDefeated.
	 * @return The value of isDefeated.
	 */
	public boolean isDefeated() {
		return isDefeated;
	}

	/**
	 * Setter method for isDefeated.
	 * @param isDefeated The new value of isDefeated to set.
	 */
	public void setDefeated(boolean isDefeated) {
		this.isDefeated = isDefeated;
	}
	
	/**
	 * Getter method for timesDefeated.
	 * @return The value of timesDefeated.
	 */
	public int getTimesDefeated() {
		return timesDefeated;
	}
	
	/**
	 * Setter method for timesDefeated.
	 * Also marks villain as defeated if newTime exceeds MAX_TIMES_DEFEATED.
	 * @param newTimes The new value of timesDefeated to set.
	 */
	public void setTimesDefeated(int newTimes) {
		timesDefeated = newTimes;
		if (timesDefeated >= MAX_TIMES_DEFEATED) {
			this.isDefeated = true;
		}
		else {
			this.isDefeated = false;
		}
	}

	public static void main(String[] args) {
//		Villain superVillain = null;
//		try {
//			superVillain = (Villain) Villain.SUPER_VILLAIN.getConstructor().newInstance();
//		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
//				| NoSuchMethodException | SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(superVillain instanceof Invictus);
		
	}
	
}

