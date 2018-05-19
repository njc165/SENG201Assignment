/**
 * Instances of Bulwark represent a type of Hero that
 * can be played in the game. Bulwark heroes take reduced damage
 * when they lose a battle.
 */
public class Bulwark extends Hero {
	
	/**
	 * A string description of the hero's type.
	 */
	private static final String TYPE = "Bulwark";
	
	/**
	 * A brief string description of the hero's special ability.
	 */
	private static final String SPECIAL_ABILITY = String.format("Take %s%% less damage.",
																(int) (100 - (Hero.DAMAGE_REDUCTION_MULTIPLIER * 100)));
	
	/**
	 * An extended string description of the hero.
	 * This description is shown to the player when selecting heroes for their team.
	 */
	private static final String DESCRIPTION = String.format("Bulwarks are infamous masochists. It's really quite concerning."
														  + " However, they provide useful protection against the wrath of evil villains."
														  + "\n\nWhen a Bulwark loses a game, they take %s%% less damage.\n",
														(int) (100 - (Hero.DAMAGE_REDUCTION_MULTIPLIER * 100)));
	
	/**
	 * The maximum health the hero can have.
	 */
	private static final int MAX_HEALTH = 120;
		
	/**
	 * A constructor for the Bulwark class.
	 * @param name The name of the hero, chosen by the player.
	 */
	public Bulwark(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasReducedDamage(true);
	}
	
}
