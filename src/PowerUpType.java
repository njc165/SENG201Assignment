public enum PowerUpType {
	
	EXTRA_GUESS("Extra Guess"),
	INCREASE_ROLL("Increase Roll"),
	MINDREADER("Mindreader"),
	TIEBREAKER("Tiebreaker");
		
	/**
	 * A string representation of the given PowerUpType
	 */
	String string;
	
	private PowerUpType(String string) {
		this.string = string;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return string;
	}
}
