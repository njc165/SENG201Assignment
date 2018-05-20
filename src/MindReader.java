/**
 * MindReader instances represent a type of power-up
 * in the game. A new instance should be created whenever the team
 * obtains a new Mindreader power-up, and destroyed once the
 * power-up is used by a hero in a villain battle.
 */
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
	/**
	 * A constructor for MindReader.
	 */
	public MindReader() {
		super(TYPE, COST, DESCRIPTION);
	}
		
}