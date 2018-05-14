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
	private final static String DESCRIPTION = "When playing Guess the Number, each Extra Guess power up applied to a hero gives them one extra guess."
											+ "\n\nAll Extra Guess power ups applied to the hero will be consumed at the end of a game of Guess the Number.";
	
	public ExtraGuess() {
		super(TYPE, COST, DESCRIPTION);
	}
	
}
