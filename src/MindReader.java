public class MindReader extends PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private final static PowerUpType TYPE = PowerUpType.MINDREADER;
	
	/**
	 * The String representation of the type of the power-up.
	 */
	private final static String TYPE_STRING = "Mindreader";
	
	/**
	 * The cost of the power-up
	 */
	private final static int COST = 20;
	
	/**
	 * A description of the power-up.
	 */
	private final static String DESCRIPTION = "";
	
	public MindReader() {
		super(TYPE, TYPE_STRING, COST, DESCRIPTION);
	}
	
}
