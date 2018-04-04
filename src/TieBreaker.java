public class TieBreaker extends PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private final static PowerUpType TYPE = PowerUpType.TIEBREAKER;
	
	/**
	 * The String representation of the type of the power-up.
	 */
	private final static String TYPE_STRING = "Tiebreaker";
	
	/**
	 * The cost of the power-up
	 */
	private final static int COST = 50;
	
	/**
	 * A description of the power-up.
	 */
	private final static String DESCRIPTION = "When applied to a hero, gain the following benefits:\nPaper Scissors Rock:\nWin ties.\nDice Roll:\nWin ties.\nApplying multiple Tiebreaker power-ups to a single hero will have no extra effect. All Tiebreaker power-ups will be consumed at the end of Paper Scissors Rock, even if they were not used.";
	
	public TieBreaker() {
		super(TYPE, TYPE_STRING, COST, DESCRIPTION);
	}
	
}
