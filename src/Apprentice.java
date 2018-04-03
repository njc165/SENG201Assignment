public class Apprentice extends Hero {
	
	/**
	 * A string description of the hero's type.
	 */
	private static final String TYPE = "Apprentice";
	
	/**
	 * A brief string description of the hero's special ability.
	 */
	private static final String SPECIAL_ABILITY = "Healing items apply twice as fast";
	
	/**
	 * An extended string description of the hero.
	 * This description is shown to the player when selecting heroes for their team.
	 */
	private static final String DESCRIPTION = "Being the slow learners that they, Apprentices make a lot of mistakes and often injure themselves. Over time, they have become proficient at applying medication.\n\nHealing items applies to an Apprentice will be consumed 50% faster.";
	
	/**
	 * The maximum health the hero can have.
	 */
	private static final double MAX_HEALTH = 100;
	
	/**
	 * A constructor for the Apprentice class.
	 * @param name The name of the hero, chosen by the player.
	 */
	public Apprentice(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasFasterHealing(true);
	}
	
}