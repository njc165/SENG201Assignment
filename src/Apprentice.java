public class Apprentice extends Hero {
	
	private static final String TYPE = "Apprentice";
	private static final String SPECIAL_ABILITY = "Healing items apply twice as fast";
	private static final String DESCRIPTION = "Being the slow learners that they, Apprentices make a lot of mistakes and often injure themselves. Over time, they have become proficient at applying medication.\n\nHealing items applies to an Apprentice will be consumed 50% faster.";
	private static final double MAX_HEALTH = 100;
	
	public Apprentice(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasFasterHealing(true);
	}
	
}
