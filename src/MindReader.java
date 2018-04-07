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
	private final static String DESCRIPTION = "When applied to a hero, gain the following benefits:\nPaper Scissors Rock:\nReveal one option that the villain will not play. Has no effect when applied to Gamblers.\nApplying multiple Mindreader power-ups to a single hero will have no extra effect. All Mindreader power-ups will be consumed at the end of Paper Scissors Rock, even if they were not used.";
	
	public MindReader() {
		super(TYPE, COST, DESCRIPTION);
	}
		
}
