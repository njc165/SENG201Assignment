public class Mercenary extends Hero {
	
	/**
	 * A string description of the hero's type.
	 */
	private static final String TYPE = "Mercenary";
	
	/**
	 * A brief string description of the hero's special ability.
	 */
	private static final String SPECIAL_ABILITY = "Double damage to villains";
	
	/**
	 * An extended string description of the hero.
	 * This description is shown to the player when selecting heroes for their team.
	 */
	private static final String DESCRIPTION = "Mercenaries are renowned as the best fighters, motivated by the prospect of riches.\nWhen chosen to fight villains, Mercenaries will deal double damage.\n";
	
	/**
	 * The maximum health the hero can have.
	 */
	private static final int MAX_HEALTH = 50;
	
	/**
	 * Filepath to the Mercenary portrait image.
	 */
	private static final String PORTRAIT_FILEPATH = "/img/mercenary_200x200.png";
	
	/**
	 * Filepath to the Mercenary full-body image.
	 */
	private static final String FULL_FILEPATH = "not yet imported";
	
	/**
	 * A constructor for the Mercenary class.
	 * @param name The name of the hero, chosen by the player.
	 */
	public Mercenary(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH, PORTRAIT_FILEPATH, FULL_FILEPATH);
		setHasDoubleDamage(true);
	}
	
}
