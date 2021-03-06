/**
 * Instances of IncreaseRoll represent a type of power-up
 * in the game. Each object represents one power-up. A new
 * object should be created whenever the team obtains a new
 * Increase Roll power-up, and destroyed when a hero uses the
 * power up in a villain battle.
 */
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
	private final static String DESCRIPTION = "When playing Dice Rolls, each Increase Roll power up applied to a hero will increase their roll by one."
											+ "\n\nAll Increase Roll power ups applied to the hero will be consumed at the end of a game of Dice Rolls.";
	
	/**
	 * A constructor for IncreaseRoll.
	 */
	public IncreaseRoll() {
		super(TYPE, COST, DESCRIPTION);
	}
	
}
