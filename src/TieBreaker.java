public class TieBreaker extends PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private final static PowerUpType TYPE = PowerUpType.TIEBREAKER;
	
	/**
	 * The cost of the power-up
	 */
	private final static int COST = 50;
	
	/**
	 * A description of the power-up.
	 */
	private final static String DESCRIPTION = "When a Tiebreaker power up is applied to a hero, they will win ties when playing Paper Scissors Rock or Dice Rolls."
											+ "\n\nApplying multiple Tiebreaker power ups to a single hero will have no extra effect."
											+ "\n\n One Tiebreaker will be consumed each time it is used to win a tie.";
	
	public TieBreaker() {
		super(TYPE, COST, DESCRIPTION);
	}
	
}
