public enum PowerUpType {
	MINDREADER("Mindreader"),
	EXTRA_GUESS("Extra Guess"),
	INCREASE_ROLL("Increase Roll"),
	TIEBREAKER("Tiebreaker");
	
	String string;
	
	private PowerUpType(String string) {
		this.string = string;
	}
	
	public String toString() {
		return string;
	}
}
