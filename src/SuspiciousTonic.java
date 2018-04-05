
public class SuspiciousTonic extends HealingItem{
	
	/**
	 * A string representation of SuspiciousTonic.
	 */
	private static final String NAME = "Suspicious Tonic";
	
	/**
	 * A string describing SuspiciousTonic.
	 */
	private static final String DESCRIPTION = "A thick brown soup which your doctor assures you is perfectly safe.\nRestores 25% health every 60 seconds for 1 minute.";
	
	/**
	 * The number of times SuspiciousTonic items can be applied.
	 */
	private static final int NUMBER_OF_INCREMENTS = 1;
	
	/**
	 * The time in seconds per application of a SuspiciousTonic item.
	 */
	private static final long TIME_PER_INCREMENT = 60;
	
	/**
	 * The price of a SuspiciousTonic item, for the store.
	 */
	private static final int PRICE = 20;

	/**
	 * A constructor for SuspiciousTonic.
	 */
	public SuspiciousTonic() {
		super(NAME, DESCRIPTION, NUMBER_OF_INCREMENTS, TIME_PER_INCREMENT, PRICE);
	}
	
}
