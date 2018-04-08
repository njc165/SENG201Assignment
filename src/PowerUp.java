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
	 * Returns a description of the item as a formatted String to be displayed
	 * to the user in the shop.
	 * Includes:
	 * - name of item
	 * - description of item's use
	 * - price of item
	 * - number currently owned by the team
	 * @param team The team currently playing the game.
	 * @return	A description of a purchasable item to be displayed in the shop.
	 */
	public String shopDescription(Team team) {
		int numOwned = team.numPowerUpsOwned(type);
		
		String returnString = type.toString() + "\n";
		returnString += String.format("Number currently owned: %s\n", numOwned);
		returnString += String.format("Price: %s coins\n", getCost(team.hasDiscountHero()));
		returnString += description + "\n";
		
		return returnString;
		
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
	 * If hasDiscount is true, multiplies the cost by Hero.STORE_DISCOUNT_MULTIPLIER.
	 * @param hasDiscount	true if the team has a hero with the store discount
	 * 						special ability, false otherwise.
	 * @return The value of cost, with the discount applied if needed.
	 */
	public int getCost(boolean hasDiscount) {
		if (hasDiscount) {
			return (int) (cost * Hero.STORE_DISCOUNT_MULTIPLIER);
		} else {
			return cost;
		}
	}
	
}
