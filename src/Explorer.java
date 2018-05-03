public class Explorer extends Hero {
	
	/**
	 * A string description of the hero's type.
	 */
	private static final String TYPE = "Explorer";
	
	/**
	 * A brief string description of the hero's special ability.
	 */
	private static final String SPECIAL_ABILITY = "Cities start with locations of all sectors revealed";
	
	/**
	 * An extended string description of the hero.
	 * This description is shown to the player when selecting heroes for their team.
	 */
	private static final String DESCRIPTION = "These streetwise intrepids know every city inside and out.\nWith an Explorer on your team, new cities will start with all areas revealed.\n";
	
	/**
	 * The maximum health the hero can have.
	 */
	private static final int MAX_HEALTH = 100;
	
	/**
	 * Filepath to the Explorer portrait image.
	 */
	private static final String PORTRAIT_FILEPATH = "/img/explorer_200x200.png";
	
	/**
	 * Filepath to the Explorer full-body image.
	 */
	private static final String FULL_FILEPATH = "not yet imported";
	
	/**
	 * A constructor for the Explorer class.
	 * @param name The name of the hero, chosen by the player.
	 */
	public Explorer(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH, PORTRAIT_FILEPATH, FULL_FILEPATH);
		setHasMapAbility(true);
	}
	
}
