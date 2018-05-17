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
	 * @param name 				The name of the healing item.
	 * @param description 		A description of the healing item.
	 * @param numIncrements 	The number of increments the healing item restores.
	 * @param timePerIncrement 	The time (in seconds) taken to apply one increment.
	 * @param cost 				The cost of the healing item in the shop.
	 */
	public HealingItem(String name, String description, int numIncrements, long timePerIncrement, int cost) {
		this.name = name;
		this.description = description;
		this.timePerIncrement = timePerIncrement;
		this.cost = cost;
		this.numIncrements = numIncrements;
		
		this.incrementsRemaining = numIncrements;
	}
	
	public String toString() {
		return name;
	}
	
	/**
	 * A string description of the healing item to be displayed in the shop,
	 * including its back story, the total percentage of a hero's health which it
	 * restores, and the time taken to apply each increment.
	 * @return	A description of the healing item to be displayed in
	 * 			the shop.
	 */
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
	 * Sets lastApplicationTime to the current system time.
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
	 * Called when this healing item is used to increase a hero's
	 * health. Decreases the number of increments remaining, and sets
	 * the last application time to the current system time.
	 */
	public void applyIncrement() {
		if (incrementsRemaining <= 0) {
			throw new RuntimeException("Tried to apply a healing item with no remaining increments");
		}
		incrementsRemaining--;
		lastApplicationTime = LocalTime.now();
	}
	
	/**
	 * Returns the time in seconds until the next increment should be
	 * applied.
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
	 * Getter method for incrementsRemaining.
	 * @return The value of incrementsRemaining.
	 */
	public int getIncrementsRemaining() {
		return incrementsRemaining;
	}

	

}
