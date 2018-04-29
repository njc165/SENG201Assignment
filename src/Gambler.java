public class Gambler extends Hero {
	
	/**
	 * A string description of the hero's type.
	 */
	private static final String TYPE = "Gambler";
	
	/**
	 * A brief string description of the hero's special ability.
	 */
	private static final String SPECIAL_ABILITY = "Advantages in villain battles.";
	
	/**
	 * An extended string description of the hero.
	 * This description is shown to the player when selecting heroes for their team.
	 */
	private static final String DESCRIPTION = "You don't have to look very far to find a gambler these days, but true experts are few and far between.\nThese players use more than luck to tip the odds in their favour.\n\nWhile fighting villains, Gamblers gain the following advantages:\nPaper Scissors Rock: Reveal one option that the villain will not play.\nGuess the Number: Get an extra guess.\nDice Roll: Increase your roll by one.";
	
	/**
	 * The maximum health the hero can have.
	 */
	private static final int MAX_HEALTH = 80;
	
	/**
	 * A constructor for the Gambler class.
	 * @param name The name of the hero, chosen by the player.
	 */
	public Gambler(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasBattleAdvantage(true);
	}
	
}
