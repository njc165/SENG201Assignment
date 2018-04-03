public class Mercenary extends Hero {
	
	private static final String TYPE = "Mercenary";
	private static final String SPECIAL_ABILITY = "Double damage to villains";
	private static final String DESCRIPTION = "Mercenaries are renowned as the best fighters, motivated by the prospect of riches.\n\nWhen chosen to fight villains, Mercenaries will deal double damage.";
	private static final double MAX_HEALTH = 50;
	
	public Mercenary(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasDoubleDamage(true);
	}
	
}
