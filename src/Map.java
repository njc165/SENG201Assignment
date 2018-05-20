/**
 * The Map class contains static attributes and methods
 * representing a map object in the game. This information
 * is displayed to the user in the shop. Map should never be
 * instantiated, as the number of maps owned by a team is stored
 * as a counter, not as individual map objects. 
 */
public class Map {
	
	/**
	 * A string description of the map item.
	 */
	private static final String DESCRIPTION = "The local public library has put out a notice asking for the return of several missing maps and atlases."
			+ " The shopkeeper assures you that this isn't one of them."
			+ "\n\nCan be used in the home base to reveal the location of each sector in the city.";
	
	/**
	 * The cost, in in-game currency, of one map item.
	 */
	private static final int COST = 10;
	
	/**
	 * Getter method for description.
	 * @return The value of description.
	 */
	public static String getDescription() {
		return DESCRIPTION;
	}

	/**
	 * Getter method for cost.
	 * Multiplies the cost by Hero.STORE_DISCOUNT_MULTIPLIER once for each hero
	 * on the team with the store discount special ability.
	 * @param numDiscountHeroes	The number of heroes on the team with the store
	 * 							discount special ability.
	 * @return The value of cost.
	 */
	public static int getCost(int numDiscountHeroes) {
		int cost = COST;
		for (int i = 0; i < numDiscountHeroes; i++) {
			cost = (int) (cost * Hero.STORE_DISCOUNT_MULTIPLIER);
		}
		return cost;
	}
	
}
