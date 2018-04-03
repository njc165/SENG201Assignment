public class Merchant extends Hero {
	
	private static final String TYPE = "Merchant";
	private static final String SPECIAL_ABILITY = "20% Store Discount";
	private static final String DESCRIPTION = "The silver-tongued merchant is experienced in the art of economics, able to secure deals no ordinary tradesman could pull off.\n\nFor each Merchant on your team, receive a 20% discount at the store.";
	private static final double MAX_HEALTH = 100;
	
	public Merchant(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasStoreDiscount(true);
	}
	
}
