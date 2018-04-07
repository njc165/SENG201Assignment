public enum MiniGames {
	PAPER_SCISSORS_ROCK("Paper Scissors Rock"),
	DICE_ROLLS("Dice Rolls"),
	GUESS_NUMBER("Guess Number");
	
	/**
	 * A string representation of the given MiniGame
	 */
	String string;
	
	private MiniGames(String string) {
		this.string = string;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return string;
	}
}
