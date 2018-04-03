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
	private static final String DESCRIPTION = "Mercenaries are renowned as the best fighters, motivated by the prospect of riches.\n\nWhen chosen to fight villains, Mercenaries will deal double damage.";
	
	/**
	 * The maximum health the hero can have.
	 */
	private static final double MAX_HEALTH = 50;
	
	/**
	 * A constructor for the Mercenary class.
	 * @param name The name of the hero, chosen by the player.
	 */
	public Mercenary(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasDoubleDamage(true);
	}
	
}