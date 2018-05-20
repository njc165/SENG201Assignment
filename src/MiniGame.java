import java.util.ArrayList;

/**
 * Instances of MiniGame represent games that the player
 * can play to defeat villains. Each instance represents
 * one minigame. This class should never be instantiated
 * directly, but rather through its subclasses. The MiniGame
 * class contains methods which are used by all three of its
 * subclasses.
 */
public class MiniGame {
	
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
	 * Constructor sets the hero and villain attributes.
	 * @param hero		The hero playing the game.
	 * @param villain	The villain playing the game.
	 */
	public MiniGame(Hero hero, Villain villain) {
		this.hero = hero;
		this.villain = villain;
	}
	
	/**
	 * Removes the given number of power ups of the given type from the
	 * hero playing the game.
	 * Throws a RuntimeException if the hero does not contain at least
	 * this many power ups of the given type.
	 * @param type	The type of power up to remove.
	 * @param numToRemove	The number of power ups to remove.
	 */
	public void removePowerUps(PowerUpType type, int numToRemove) {
		ArrayList<PowerUp> newActivePowerUps = new ArrayList<PowerUp>();
		
		for (PowerUp powerUp: hero.getAppliedPowerUps()) {
			if (powerUp.getType() == type && numToRemove > 0) {
				numToRemove--;
			} else {
				newActivePowerUps.add(powerUp);
			}
		}
		
		if (numToRemove > 0) {
			throw new RuntimeException("The hero does not have that many power ups of the given type");									
		}

		hero.setAppliedPowerUps(newActivePowerUps);
	}
	
	/**
	 * Removes all the power ups of the given type from the hero
	 * playing the game.
	 * @param type	The type of power up to remove.
	 */
	public void removeAllPowerUps(PowerUpType type) {
		ArrayList<PowerUp> newActivePowerUps = new ArrayList<PowerUp>();
		
		for (PowerUp powerUp: hero.getAppliedPowerUps()) {
			if (!(powerUp.getType() == type)) {
				newActivePowerUps.add(powerUp);
			}
		}
		
		hero.setAppliedPowerUps(newActivePowerUps);
	}

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
