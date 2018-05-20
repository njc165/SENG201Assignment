/**
 * The PowerUpType enumerable contains a value for each
 * power up in the game. Each subclass of PowerUp has an
 * associated PowerUpType.
 */
public enum PowerUpType {
	
	EXTRA_GUESS("Extra Guess"),
	INCREASE_ROLL("Increase Roll"),
	MINDREADER("Mindreader"),
	TIEBREAKER("Tiebreaker");
		
	/**
	 * A string representation of the given PowerUpType
	 */
	String string;
	
	/**
	 * A constructor for PowerUpType.
	 * @param string A string representation of the power up type.
	 */
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
