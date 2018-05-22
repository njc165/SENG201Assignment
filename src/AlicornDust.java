/**
 * Instances of AlicornDust represent a type of healing item
 * that the player can obtain in the game. Each object
 * represents one healing item. A new instance should be created
 * whenever the team obtains an Alicorn Dust item, and deleted
 * when all of its increments have been applied to a hero.
 */
public class AlicornDust extends HealingItem {
	
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
	private static final long TIME_PER_INCREMENT = 45;
	
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
