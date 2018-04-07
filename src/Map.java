public class Map {
	
	/**
	 * A string representation of the name of the map.
	 */
	private static final String NAME = "Map";
	
	private static final String DESCRIPTION = "Can be used in the home base to reveal the location of each sector in the city.\n"
			+ "The local public library has put out a notice asking for the return of several missing maps and atlases.\n"
			+ "The shopkeeper assures you that this isn't one of them.";
	
	private static final int COST = 10;
	
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
	public static String shopDescription(Team team) {
		String returnString = NAME + "\n";
		returnString += String.format("Number currently owned: %s\n", team.getNumMaps());
		returnString += String.format("Price: %s coins\n", COST);
		returnString += DESCRIPTION + "\n";
		return returnString;
	}
	
	/**
	 * Getter method for cost.
	 * @return		Cost of a map.
	 */
	public static int getCost() {
		return COST;
	}
	
}
