import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class HealingItem {
	
	/**
	 * The healing item's name.
	 */
	private String name;
	
	/**
	 * A description of the healing item.
	 */
	private String description;
	
	/**
	 * The price of the healing item.
	 */
	private int price;
	
	/**
	 * The time in seconds per increment of the healing item.
	 */
	private long timePerIncrement;
	
	/**
	 * The number of increments remaining on this healing item.
	 */
	private int incrementsRemaining;
	
	/**
	 * A LocalTime representing the last time an increment was applied.
	 */
	private LocalTime lastApplicationTime;
	
	/**
	 * A LocalTime representing the next time an increment should be applied.
	 */
	private LocalTime nextApplicationTime;
	
	/**
	 * A constructor for HealingItem.
	 * @param name The name of the healing item.
	 * @param description A description of what the healing item does.
	 * @param numIncrements An integer giving the number of increments the healing item has.
	 * @param timePerIncrement A long representing the time (in seconds) taken to apply one increment.
	 * @param price An integer representing the price of the healing item.
	 */
	public HealingItem(String name, String description, int numIncrements, long timePerIncrement, int price) {
		this.name = name;
		this.description = description;
		this.timePerIncrement = timePerIncrement;
		this.price = price;
		this.incrementsRemaining = numIncrements;
		this.lastApplicationTime = LocalTime.now();
		this.nextApplicationTime = this.lastApplicationTime.plus(this.timePerIncrement, ChronoUnit.SECONDS);
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
	public int getPrice() {
		return price;
	}
	
	/**
	 * Getter method for incrementsRemaining.
	 * @return The value of incrementsRemaining.
	 */
	public int getIncrementsRemaining() {
		return incrementsRemaining;
	}

	/**
	 * Gives the number of increments remaining and the time until the next increment is applied.
	 * @return A string representing the number of increments remaining and the time until the next increment is applied.
	 */
	public String getStatus() {
		return String.format("Increments remaining: %d\nTime to next increment: %d seconds.", incrementsRemaining, LocalTime.now().until(nextApplicationTime, ChronoUnit.SECONDS));
	}
	
	/**
	 * Applies an increment of the healing item.
	 * If healsFaster, halves the time until the next application.
	 * @param healsFaster A boolean reflecting hero.hasFasterHealing.
	 */
	public void apply(boolean healsFaster) {
		if (incrementsRemaining <= 0) {
			throw new RuntimeException("Tried to apply a healing item with no remaining increments");
		}
		incrementsRemaining--;
		lastApplicationTime = LocalTime.now();
		nextApplicationTime = lastApplicationTime.plus(timePerIncrement, ChronoUnit.SECONDS);
		if (healsFaster) {
			nextApplicationTime = nextApplicationTime.minus(lastApplicationTime.until(nextApplicationTime, ChronoUnit.SECONDS)/2, ChronoUnit.SECONDS);
		}
	}
	
	/**
	 * Checks if the healing item should be incremented.
	 * @return true if the item is ready to increment, false otherwise.
	 */
	public boolean readyToIncrement() {	
		return (LocalTime.now().isAfter(nextApplicationTime));
	}
		
}
