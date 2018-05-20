/**
 * The MiniGameType enumerable contains a value for each
 * type of minigame the player could be required to play to defeat
 * villains.
 */
public enum MiniGameType {
	PAPER_SCISSORS_ROCK("Paper Scissors Rock"),
	DICE_ROLLS("Dice Rolls"),
	GUESS_NUMBER("Guess the Number");
	
	/**
	 * A string representation of the given MiniGame
	 */
	String string;
	
	private MiniGameType(String string) {
		this.string = string;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return string;
	}
}
