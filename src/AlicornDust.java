
public class AlicornDust extends HealingItem{
	
	/**
	 * A string representation of the name of AlicornDust.
	 */
	private static final String NAME = "Alicorn Dust";
	
	/**
	 * A string describing AlicornDust.
	 */
	private static final String DESCRIPTION = "Unicorn horns have incredibly potent healing properties.";
	
	/**
	 * The number of times AlicornDust items can be applied.
	 */
	private static final int NUMBER_OF_INCREMENTS = 3;
	
	/**
	 * The time in seconds per application of a AlicornDust item.
	 */
	private static final long TIME_PER_INCREMENT = 20;
	
	/**
	 * The cost of a AlicornDust item, for the store.
	 */
	private static final int COST = 100;

	/**
	 * A constructor for AlicornDust.
	 */
	public AlicornDust() {
		super(NAME, DESCRIPTION, NUMBER_OF_INCREMENTS, TIME_PER_INCREMENT, COST);
	}
	
}
