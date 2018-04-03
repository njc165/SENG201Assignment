public class Bulwark extends Hero {
	
	private static final String TYPE = "Bulwark";
	private static final String SPECIAL_ABILITY = "25% Damage reduction";
	private static final String DESCRIPTION = "Bulwarks are infamous masochists. It's really quite concerning. However, they provide useful protection against the wrath of evil villains.\n\nWhen a Bulwark loses a game, they take 25% less damage.";
	private static final double MAX_HEALTH = 120;
	
	public Bulwark(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasReducedDamage(true);
	}
	
}
