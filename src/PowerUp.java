public class PowerUp {
	
	/**
	 * The type of the power-up as an object of the PowerUpType enum.
	 */
	private PowerUpType type;
	
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
	
	/**
	 * A constructor for PowerUp.
	 * @param type The type of power up
	 * @param cost The cost of the power up
	 * @param description A string description of the power up.
	 */
	public PowerUp(PowerUpType type, int cost, String description) {
		this.type = type;
		this.cost = cost;
		this.description = description;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return type.toString();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (!(other instanceof PowerUp)) {
			return false;
		} else {
			PowerUp powerUp = (PowerUp) other;
			return this.getType() == powerUp.getType();
		}
	}

	/**
	 * Getter method for type.
	 * @return The value of type.
	 */
	public PowerUpType getType() {
		return type;
	}
	
	/**
	 * Getter method for cost.
	 * Multiplies the cost by Hero.STORE_DISCOUNT_MULTIPLIER once for each hero
	 * on the team with the store discount special ability.
	 * @param numDiscountHeroes	The number of heroes on the team with the store
	 * 							discount special ability.
	 * @return The value of cost.
	 */
	public int getCost(int numDiscountHeroes) {
		int cost = this.cost;
		for (int i = 0; i < numDiscountHeroes; i++) {
			cost = (int) (cost * Hero.STORE_DISCOUNT_MULTIPLIER);
		}
		return cost;
	}

	/**
	 * Getter method for description.
	 * @return The value of description.
	 */
	public String getDescription() {
		return description;
	}
	
}
