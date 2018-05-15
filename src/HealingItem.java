import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HealingItem {
	
	/**
	 * The percentage of a hero's health which is restored by each increment of a
	 * healing item, expressed as a decimal.
	 */
	public static final double INCREMENT_SIZE = 0.25;
	
	/**
	 * The healing item's name.
	 */
	private String name;
	
	/**
	 * A description of the healing item.
	 */
	private String description;
	
	/**
	 * The cost of the healing item.
	 */
	private int cost;
	
	/**
	 * The time in seconds per increment of the healing item.
	 */
	private long timePerIncrement;
	
	/**
	 * The total number of increments restored by the healing item.
	 */
	private int numIncrements;
	
	/**
	 * The number of increments remaining on this healing item.
	 */
	private int incrementsRemaining;
	
	/**
	 * A LocalTime representing the last time an increment was applied.
	 */
	private LocalTime lastApplicationTime;
	
	/**
	 * A constructor for HealingItem.
	 * @param name The name of the healing item.
	 * @param description A description of what the healing item does.
	 * @param numIncrements An integer giving the number of increments the healing item has.
	 * @param timePerIncrement A long representing the time (in seconds) taken to apply one increment.
	 * @param cost An integer representing the cost of the healing item.
	 */
	public HealingItem(String name, String description, int numIncrements, long timePerIncrement, int cost) {
		this.name = name;
		this.description = description;
		this.timePerIncrement = timePerIncrement;
		this.cost = cost;
		this.numIncrements = numIncrements;
		this.incrementsRemaining = numIncrements;
	}
	
	public String shopDescription() {
		String returnString = description + "\n\n";
		returnString += String.format("Restores %s%% of the hero's maximum health in %s%% increments.\n\n",
											percentageHealthRestored(),
											(int) (INCREMENT_SIZE * 100));
		returnString += String.format("Time taken to apply each increment: %s seconds.",
										timePerIncrement);
		return returnString;
	}
	
	/**
	 * Calculate the total percentage of a hero's max health which will be restored
	 * by the healing item.
	 * @return	The total percentage of health restored by the healing item.
	 */
	private int percentageHealthRestored() {
		return (int) (INCREMENT_SIZE * numIncrements * 100);
	}
	
	/**
	 * Called when a hero's applied healing item is set to this healing item.
	 * Initialises lastApplicationTime to the current system time.
	 * If the hero it is applied to has the faster healing special ability,
	 * multiplies the time per increment of the healing item by Hero.FASTER_HEALING_MULTIPLIER
	 * @param hasFasterHealing	true if the hero it is applied to has the faster healing
	 * 							special ability.
	 */
	public void applyToHero(boolean hasFasterHealing) {
		lastApplicationTime = LocalTime.now();
		if (hasFasterHealing) {
			timePerIncrement *= Hero.FASTER_HEALING_MULTIPLIER;
		}
	}
	
	/**
	 * Applies an increment of the healing item.
	 */
	public void applyIncrement() {
		if (incrementsRemaining <= 0) {
			throw new RuntimeException("Tried to apply a healing item with no remaining increments");
		}
		incrementsRemaining--;
		lastApplicationTime = LocalTime.now();
	}
	
	/**
	 * Returns the time in seconds remaining until the next increment.
	 * @return The number of seconds until the next increment.
	 */
	public long secondsRemaining() {
		return Long.max(0, LocalTime.now().until(nextApplicationTime(), ChronoUnit.SECONDS));
	}
	
	/**
	 * Checks if the healing item should be incremented.
	 * @return true if the item is ready to increment, false otherwise.
	 */
	public boolean readyToIncrement() {	
		return (LocalTime.now().isAfter(nextApplicationTime()));
	}
	
	/**
	 * Calculates the LocalTime when the next application should occur.
	 * @return	The next application time.
	 */
	public LocalTime nextApplicationTime() {
		return lastApplicationTime.plus(timePerIncrement, ChronoUnit.SECONDS);
	}		
	
	/**
	 * Getter method for name.
	 * @return The value of name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for description.
	 * @return The value of description.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Getter method for cost.
	 * Multiplies the cost by Hero.STORE_DISCOUNT_MULTIPLIER once for each hero
	 * on the team with the store discount special ability.
	 * @param numDiscountHeroes	The number of heroes on the team with the store
	 * 							discount special ability.
	 * @return The value of cost.
	 */
	public int getCost(int numDiscountHeroes) {
		int cost = this.cost;
		for (int i = 0; i < numDiscountHeroes; i++) {
			cost = (int) (cost * Hero.STORE_DISCOUNT_MULTIPLIER);
		}
		return cost;
	}

    /**
	 * Getter method for timePerIncrement.
	 * Used only in testing.
	 * @return The value of timePerIncrement.
	 */
	public long getTimePerIncrement() {
		return timePerIncrement;
	}
	
	/**
	 * Getter method for incrementsRemaining.
	 * @return The value of incrementsRemaining.
	 */
	public int getIncrementsRemaining() {
		return incrementsRemaining;
	}
	
	/**
	 * Getter method for numIncrements.
	 * @return The value of numIncrements.
	 */
	public int getNumIncrements() {
		return numIncrements;
	}
	
	public String toString() {
		return name;
	}
}
