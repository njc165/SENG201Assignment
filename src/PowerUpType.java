public enum PowerUpType {
	
	EXTRA_GUESS("Extra Guess"),
	INCREASE_ROLL("Increase Roll"),
	MINDREADER("Mindreader"),
	TIEBREAKER("Tiebreaker");
		
	String string;
	
	private PowerUpType(String string) {
		this.string = string;
	}
	
	public String toString() {
		return string;
	}
}
