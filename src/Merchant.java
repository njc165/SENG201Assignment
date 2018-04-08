public class Merchant extends Hero {
	
	/**
	 * A string description of the hero's type.
	 */
	private static final String TYPE = "Merchant";
	
	/**
	 * A brief string description of the hero's special ability.
	 */
	private static final String SPECIAL_ABILITY = String.format("%s%% Store Discount",
												(int) (100 - (Hero.STORE_DISCOUNT_MULTIPLIER * 100)));
	
	/**
	 * An extended string description of the hero.
	 * This description is shown to the player when selecting heroes for their team.
	 */
	private static final String DESCRIPTION = "The silver-tongued merchant is experienced in the art of economics, able to secure deals no ordinary tradesman could pull off.\nFor each Merchant on your team, receive a discount at the store.\n";
	
	/**
	 * The maximum health the hero can have.
	 */
	private static final int MAX_HEALTH = 100;
	
	/**
	 * A constructor for the Merchant class.
	 * @param name The name of the hero, chosen by the player.
	 */
	public Merchant(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasStoreDiscount(true);
	}
	
}
