public class IncreaseRoll extends PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private final static PowerUpType TYPE = PowerUpType.INCREASE_ROLL;
	
	/**
	 * The cost of the power-up
	 */
	private final static int COST = 20;
	
	/**
	 * A description of the power-up.
	 */
	private final static String DESCRIPTION = "When applied to a hero, they gain the following benefits:\nDice Roll:\nIncrease your roll by one.\nApplying multiple Increase Roll power-ups to a single hero is allowed. All Increase Roll power-ups will be consumed at the end of Dice Roll, even if they were not used.";
	
	public IncreaseRoll() {
		super(TYPE, COST, DESCRIPTION);
	}
	
}
