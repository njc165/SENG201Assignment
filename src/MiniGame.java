import java.util.ArrayList;
import java.util.Arrays;

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
	 * A list of the PowerUpTypes which are relevant to the particular game.
	 * Each Subclass of MiniGame has a different list of relevant power-ups.
	 */
	private PowerUpType[] relevantPowerUps;
	
	/**
	 * Constructor sets the hero and villain attributes.
	 * @param hero		The hero playing the game.
	 * @param villain	The villain playing the game.
	 */
	public MiniGame(Hero hero, Villain villain, PowerUpType[] relevantPowerUps) {
		this.hero = hero;
		this.villain = villain;
		this.relevantPowerUps = relevantPowerUps;
	}
	
	/**
	 * Creates a new MiniGame given a type, hero and villain.
	 * @param type The type of MiniGame to create
	 * @param hero The hero to play the MiniGame
	 * @param villain The villain to play the MiniGame
	 * @return A MiniGame instance to play
	 */
	public static MiniGame createGame(MiniGames type, Hero hero, Villain villain) {
		
		MiniGame game;
		
		switch (type) {
			case PAPER_SCISSORS_ROCK: game = new PaperScissorsRock(hero, villain); break;
			case DICE_ROLLS: game = new DiceRolls(hero, villain); break;
			case GUESS_NUMBER: game = new GuessNumberCMD(hero, villain); break;
			default: throw new RuntimeException("Invalid MiniGame type");
		}
		
		return game;
		
	}
	
	/**
	 * Carries out the gameplay of the mini game. Once completed, hasWon is set to true if the hero won and false otherwise.
	 * All the power-ups relevant to the particular game have been removed from the hero, whether or not they were required.
	 */
	public abstract void play();
	
	/**
	 * Removes all the power-ups relevant to the particular game from the
	 * hero's list of applied power-ups.
	 * Called at the end of the play() method in each MiniGame subclass.
	 */
	public void removeRelevantPowerUps() {
		ArrayList<PowerUp> newActivePowerUps = new ArrayList<PowerUp>();
		ArrayList<PowerUpType> relevantPowerUpTypes = new ArrayList<PowerUpType>(Arrays.asList(relevantPowerUps));
		
		for (PowerUp powerUp: hero.getActivePowerUps()) {
			if (!(relevantPowerUpTypes.contains(powerUp.getType()))) {
				newActivePowerUps.add(powerUp);
			}
		}
		hero.setActivePowerUps(newActivePowerUps);
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
		
		for (PowerUp powerUp: hero.getActivePowerUps()) {
			if (powerUp.getType() == type && numToRemove > 0) {
				numToRemove--;
			} else {
				newActivePowerUps.add(powerUp);
			}
		}
		
		if (numToRemove > 0) {
			throw new RuntimeException("The hero does not have that many power ups of the given type");									
		}

		hero.setActivePowerUps(newActivePowerUps);
	}
	
	/**
	 * Removes all the power ups of the given type from the hero
	 * playing the game.
	 * @param type	The type of power up to remove.
	 */
	public void removeAllPowerUps(PowerUpType type) {
		ArrayList<PowerUp> newActivePowerUps = new ArrayList<PowerUp>();
		
		for (PowerUp powerUp: hero.getActivePowerUps()) {
			if (! (powerUp.getType() == type)) {
				newActivePowerUps.add(powerUp);
			}
		}
		
		hero.setActivePowerUps(newActivePowerUps);
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
