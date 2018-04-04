public class PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private PowerUpType type;
	
	/**
	 * The type of the power-up as a String.
	 * Each subclass of PowerUp has a different type.
	 */
	private String typeString;
	
	/**
	 * The cost of the power-up.
	 * Each subclass of PowerUp has a different cost.
	 */
	private int cost;
	
	/**
	 * A description of the power-up, detailing the benefits it gives a hero
	 * in each of the three mini games.
	 * Each subclass of PowerUp has a different description.
	 */
	private String description;
	
	public PowerUp(PowerUpType type, String typeString, int cost, String description) {
		this.type = type;
		this.typeString = typeString;
		this.cost = cost;
		this.description = description;
	}
	
}
