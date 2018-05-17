/**
 * SuspiciousTonic objects inherit from HealingItem and represent
 * specific healing items in the game. Each object corresponds to
 * one healing item, and a new object should be created every time
 * a Team receives a new healing item.
 */
public class SuspiciousTonic extends HealingItem{
	
	/**
	 * A string representation of the name of SuspiciousTonic.
	 */
	private static final String NAME = "Suspicious Tonic";
	
	/**
	 * A string describing SuspiciousTonic.
	 */
	private static final String DESCRIPTION = "A thick brown soup which your doctor assures you is perfectly safe.";
	
	/**
	 * The number of times SuspiciousTonic items can be applied.
	 */
	private static final int NUMBER_OF_INCREMENTS = 1;
	
	/**
	 * The time in seconds per application of a SuspiciousTonic item.
	 */
	private static final long TIME_PER_INCREMENT = 300;
	
	/**
	 * The cost of a SuspiciousTonic item, for the store.
	 */
	private static final int COST = 20;

	/**
	 * A constructor for SuspiciousTonic.
	 */
	public SuspiciousTonic() {
		super(NAME, DESCRIPTION, NUMBER_OF_INCREMENTS, TIME_PER_INCREMENT, COST);
	}
	
}
