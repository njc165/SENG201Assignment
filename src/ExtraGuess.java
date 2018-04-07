public class ExtraGuess extends PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private final static PowerUpType TYPE = PowerUpType.EXTRA_GUESS;
	
	/**
	 * The cost of the power-up
	 */
	private final static int COST = 20;
	
	/**
	 * A description of the power-up.
	 */
	private final static String DESCRIPTION = "When applied to a hero, gain the following benefits:\nGuess the Number:\nGet an extra guess.\nApplying multiple extra guess power-ups to a single hero is allowed. All extra guess power-ups will be consumed at the end of Guess the Number, even if they were not used.";
	
	public ExtraGuess() {
		super(TYPE, COST, DESCRIPTION);
	}
	
}
