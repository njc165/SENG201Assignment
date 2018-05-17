/**
 * The Location enumerable contains information about
 * the locations in which Sectors could be located.
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
