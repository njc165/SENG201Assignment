/**
 * The PowerUpType enumberable holds all types of power ups
 * a player can obtain in the game.
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
