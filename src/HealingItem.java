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

	/**
	 * Returns a description of the item as a formatted String to be displayed
	 * to the user in the shop.
	 * Includes:
	 * - name of item
	 * - description of item's use
	 * - price of item
	 * - number currently owned by the team
	 * @param team The team currently playing the game.
	 * @return	A description of a purchasable item to be displayed in the shop.
	 */
	public String shopDescription(Team team) {
		int numOwned = team.numHealingItemsOwned(name);
		
		String returnString = name + "\n";
		returnString += String.format("Number currently owned: %s\n", numOwned);
		returnString += String.format("Price: %s coins\n", cost);
		returnString += description + "\n";
		returnString += String.format("Total health restored: %s%% of the hero's max health in %s%% increments.\n",
										percentageHealthRestored(),
										INCREMENT_SIZE * 100);
		returnString += String.format("Time taken to apply each increment: %s seconds.\n",
										timePerIncrement);
		return returnString;
	}
	
	/**
	 * Calculate the total percentage of a hero's max health which will be restored
	 * by the healing item.
	 * @return	The total percentage of health restored by the healing item.
	 */
	private int percentageHealthRestored() {
		return (int) INCREMENT_SIZE * numIncrements * 100;
	}
	
	/**
	 * Gives the number of increments remaining and the time until the next
	 * increment is applied.
	 * @return A string representing the number of increments remaining and the
	 * time until the next increment is applied.
	 */
	public String getStatus() {
		return String.format("Increments remaining: %d\nTime to next increment: %d seconds.",
							 incrementsRemaining,
							 LocalTime.now().until(nextApplicationTime(), ChronoUnit.SECONDS));
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
	 * Getter method for price.
	 * @return The value of price.
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * Getter method for incrementsRemaining.
	 * @return The value of incrementsRemaining.
	 */
	public int getIncrementsRemaining() {
		return incrementsRemaining;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name;
	}

    /**
	 * Getter method for timePerIncrement.
	 * Used only in testing.
	 * @return The value of timePerIncrement.
	 */
	public long getTimePerIncrement() {
		return timePerIncrement;
	}
}
