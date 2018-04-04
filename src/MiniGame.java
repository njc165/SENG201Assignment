public abstract class MiniGame {
	
	/**
	 * The hero playing the game.
	 */
	private Hero hero;
	
	/**
	 * The villain playing the game.
	 */
	private Villain villain;
	
	/**
	 * Set to true if the hero wins the game, otherwise remains false.
	 */
	private boolean hasWon = false;
	
	/**
	 * A list of the PowerUp subclasses which 
	 */
	private Class<?>[] relevantPowerUps;
	
	/**
	 * Constructor sets the hero and villain attributes.
	 * @param hero		The hero playing the game.
	 * @param villain	The villain playing the game.
	 */
	public MiniGame(Hero hero, Villain villain) {
		this.hero = hero;
		this.villain = villain;
	}
	
	/**
	 * Carries out the gameplay of the mini game. Once completed, hasWon is set to true if the hero won and false otherwise.
	 * All the power-ups relevant to the particular game have been removed from the hero, whether or not they were required.
	 */
	public abstract void play();

	/**
	 * Getter method for hero.
	 * @return The value of hero.
	 */
	public Hero getHero() {
		return hero;
	}

	/**
	 * Getter method for villain.
	 * @return The value of villain.
	 */
	public Villain getVillain() {
		return villain;
	}

	/**
	 * Getter method for hasWon.
	 * @return The value of hasWon.
	 */
	public boolean getHasWon() {
		return hasWon;
	}

	/**
	 * Setter method for hasWon.
	 * @param hasWon The new value of hasWon to set.
	 */
	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}
	
}
