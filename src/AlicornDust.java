
public class AlicornDust extends HealingItem{
	
	/**
	 * A string representation of AlicornDust.
	 */
	private static final String NAME = "Alicorn Dust";
	
	/**
	 * A string describing AlicornDust.
	 */
	private static final String DESCRIPTION = "Unicorn horns have incredibly potent healing properties.\nRestores 25% health every 20 seconds for 1 minute.";
	
	/**
	 * The number of times AlicornDust items can be applied.
	 */
	private static final int NUMBER_OF_INCREMENTS = 3;
	
	/**
	 * The time in seconds per application of a AlicornDust item.
	 */
	private static final long TIME_PER_INCREMENT = 20;
	
	/**
	 * The price of a AlicornDust item, for the store.
	 */
	private static final int PRICE = 100;

	/**
	 * A constructor for AlicornDust.
	 */
	public AlicornDust() {
		super(NAME, DESCRIPTION, NUMBER_OF_INCREMENTS, TIME_PER_INCREMENT, PRICE);
	}
	
}
