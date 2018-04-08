
public class HeartyMeal extends HealingItem{
	
	/**
	 * A string representation of the name of HeartyMeal.
	 */
	private static final String NAME = "Hearty Meal";
	
	/**
	 * A string describing HeartyMeal.
	 */
	private static final String DESCRIPTION = "Has everything you need to get back on your feet.";
	
	/**
	 * The number of times HeartyMeal items can be applied.
	 */
	private static final int NUMBER_OF_INCREMENTS = 2;
	
	/**
	 * The time in seconds per application of a HeartyMeal item.
	 */
	private static final long TIME_PER_INCREMENT = 180;
	
	/**
	 * The cost of a HeartyMeal item, for the store.
	 */
	private static final int COST = 40;

	/**
	 * A constructor for HeartyMeal.
	 */
	public HeartyMeal() {
		super(NAME, DESCRIPTION, NUMBER_OF_INCREMENTS, TIME_PER_INCREMENT, COST);
	}
	
}
