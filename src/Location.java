/**
 * The Location enumerable contains one value for each
 * location in a city. A city object should associate each
 * of these location with a particular sector.
 */
public enum Location {
	CENTRE("Centre"),
	NORTH("North"),
	EAST("East"),
	SOUTH("South"),
	WEST("West");
	
	/**
	 * A string representation of the Location object.
	 */
	private String string;
	
	/**
	 * Initialises the string attribute to a String representation of
	 * the Location object.
	 * @param string	The string representation.
	 */
	Location(String string) {
		this.string = string;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return string;
	}
}
