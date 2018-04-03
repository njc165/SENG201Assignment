public class Explorer extends Hero {
	
	private static final String TYPE = "Explorer";
	private static final String SPECIAL_ABILITY = "Cities start with all sectors revealed";
	private static final String DESCRIPTION = "These streetwise intrepids know every city inside and out.\n\nWith an Explorer on your team, new cities will start with all areas revealed.";
	private static final double MAX_HEALTH = 100;
	
	public Explorer(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasMapAbility(true);
	}
	
}
