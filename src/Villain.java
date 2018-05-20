import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Instances of the Villain class represent opponents the
 * player will face in the game.
 * 
 * Outside of testing, the Villain class should not be instantiated
 * directly, but rather through its subclasses.
 * Each subclass of Villain should be instantiated at most
 * once to avoid duplicate encounters in the game.
 */
public class Villain {
	
	/**
	 * The class object of the super villain, to be used when initialising the list of
	 * villains in a new game.
	 */
	private static final Class<?> SUPER_VILLAIN = Invictus.class;
	
	/**
	 * An array of class objects of all the villain subclasses except the super villain,
	 * to be used when initialising the list of villains in a new game.
	 */
	private static final Class<?>[] REGULAR_VILLAINS = {Bucephalus.class,
													  Evan.class,
													  Janken.class,
													  John.class,
													  Maverick.class};
	
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
	 * An array of the games a villain may choose to play, represented as values of the
	 * enum type MiniGame.
	 * Each game on the list has an equal probability of being chosen.
	 * Each subclass of Villain has a different list of games played.
	 */
	private MiniGameType[] gamesPlayed;
	
	/**
	 * The number of times this villain has been defeated in a MiniGame.
	 */
	private int timesDefeated;
	
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
	public Villain(String name, String taunt, int damageDealt, MiniGameType[] gamesPlayed) {
		this.name = name;
		this.taunt = taunt;
		this.damageDealt = damageDealt;
		
		if (gamesPlayed.length > 0)
			this.gamesPlayed = gamesPlayed;
		else
			throw new IllegalArgumentException("gamesPlayed array cannot be empty");
	}
	
	/**
	 * Creates an ArrayList of instances of Villain subclasses of the given length.
	 * Called when creating a new game to initialise the villain in each city.
	 * The last villain in list is the super villain, and the other villains
	 * are randomised from the list of regular villains.
	 * There are no repeated villains.
	 * @param numVillains	The length of the returned list of villains.
	 * 						Must be at least one (always includes the super villain).
	 * @return	The randomised list of villains, with the super villain last.
	 */
	public static ArrayList<Villain> randomisedVillains(int numVillains) {
		if (numVillains < 1) {
			throw new IllegalArgumentException("numVillains must be at least 1.");
		}
		
		ArrayList<Class<?>> regularVillainClasses = new ArrayList<Class<?>>(
									Arrays.asList(Villain.REGULAR_VILLAINS));
		Collections.shuffle(regularVillainClasses);

		ArrayList<Class<?>> villainClasses = new ArrayList<Class<?>>(
				regularVillainClasses.subList(0, numVillains - 1));
		villainClasses.add(Villain.SUPER_VILLAIN);
		
		ArrayList<Villain> villains = new ArrayList<Villain>();
		for (Class<?> villainClass: villainClasses) {
			Villain villain = (Villain) Util.instantiate(villainClass);
			villains.add(villain);
		}

		return villains;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return getName();
	}
	
	/**
	 * Get a random game from the list of games the villain can play.
	 * @return	A game of the enum type MiniGame.
	 */
	public MiniGameType getGame() {
		Random generator = new Random();
		int randomIndex = generator.nextInt(gamesPlayed.length);
		return gamesPlayed[randomIndex];
	}
	
	/**
	 * Returns the number of defeats remaining until the villain is killed.
	 * @return		The remaining number of times the villian needs to be defeated.
	 */
	public int remainingTimesToDefeat() {
		return MAX_TIMES_DEFEATED - timesDefeated;
	}
	
	/**
	 * Getter method for taunt.
	 * @return The value of taunt.
	 */
	public String getTaunt() {
		return taunt;
	}
	
	/**
	 * Getter method for name.
	 * @return The value of name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for damageDealt.
	 * @return The value of damageDealt.
	 */
	public int getDamageDealt() {
		return damageDealt;
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
		} else {
			this.isDefeated = false;
		}
	}
	
	/**
	 * Getter method for isDefeated.
	 * @return The value of isDefeated.
	 */
	public boolean isDefeated() {
		return isDefeated;
	}
	
}

