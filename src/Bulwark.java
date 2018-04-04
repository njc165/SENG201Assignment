public class Bulwark extends Hero {
	
	/**
	 * A string description of the hero's type.
	 */
	private static final String TYPE = "Bulwark";
	
	/**
	 * A brief string description of the hero's special ability.
	 */
	private static final String SPECIAL_ABILITY = "25% Damage reduction";
	
	/**
	 * An extended string description of the hero.
	 * This description is shown to the player when selecting heroes for their team.
	 */
	private static final String DESCRIPTION = "Bulwarks are infamous masochists. It's really quite concerning. However, they provide useful protection against the wrath of evil villains.\nWhen a Bulwark loses a game, they take 25% less damage.\n";
	
	/**
	 * The maximum health the hero can have.
	 */
	private static final double MAX_HEALTH = 120;
	
	/**
	 * A constructor for the Bulwark class.
	 * @param name The name of the hero, chosen by the player.
	 */
	public Bulwark(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasReducedDamage(true);
	}
	
}
