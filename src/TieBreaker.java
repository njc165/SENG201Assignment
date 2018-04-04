public class TieBreaker extends PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private final static PowerUpType TYPE = PowerUpType.MINDREADER;
	
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
	private final static String DESCRIPTION = "";
	
	public TieBreaker() {
		super(TYPE, TYPE_STRING, COST, DESCRIPTION);
	}
	
}
