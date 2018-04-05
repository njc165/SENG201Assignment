public class Sector {
	
	/**
	 * The type of the sector, as a value of the enum SectorType.
	 */
	private SectorType type;
	
	/**
	 * Default value is false. A sector is set to discovered if:
	 * -the team includes a hero with the map special ability
	 * -the team uses a map to reveal all the sectors in the current city
	 * -the team has already visited the sector
	 */
	private boolean discovered = false;
	
	public Sector(SectorType type) {
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (discovered) {
			return type.toString();
		} else {
			return "?";
		}
	}

	/**
	 * Getter method for discovered.
	 * @return The value of discovered.
	 */
	public boolean getDiscovered() {
		return discovered;
	}

	/**
	 * Setter method for discovered.
	 * @param discovered The new value of discovered to set.
	 */
	public void setDiscovered(boolean discovered) {
		this.discovered = discovered;
	}

	/**
	 * Getter method for type.
	 * @return The value of type.
	 */
	public SectorType getType() {
		return type;
	}
	
}
