public enum SectorType {
	HOME_BASE("Home Base"),
	SHOP("Shop"),
	POWER_UP_DEN("Power-up Den"),
	HOSPITAL("Hospital"),
	VILLAINS_LAIR("Villain's Lair");
	
	/**
	 * A string representation of the SectorType object.
	 */
	private String string;
	
	/**
	 * Initialises the string attribute to a String representation of
	 * the SectorType object.
	 * @param string	The string representation.
	 */
	SectorType(String string) {
		this.string = string;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return string;
	}
}
