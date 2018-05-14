public class MindReader extends PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private final static PowerUpType TYPE = PowerUpType.MINDREADER;
	
	/**
	 * The cost of the power-up
	 */
	private final static int COST = 20;
	
	/**
	 * A description of the power-up.
	 */
	private final static String DESCRIPTION = "When playing Paper Scissors Rock, if the hero has a Mindreader power up applied, it will reveal one option which the villian will not play."
											+ " Applying multiple Mindreader power ups to a single hero will have no extra effect."
											+ "\n\nMindreader power ups have no effect when applied to a Gambler."
											+ "\n\nOne Mindreader power up will be consumed each time it is used.";
	
	public MindReader() {
		super(TYPE, COST, DESCRIPTION);
	}
		
}
