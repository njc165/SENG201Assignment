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
			case PAPER_SCISSORS_ROCK: game = new PaperScissorsRockCMD(hero, villain); break;
			case DICE_ROLLS: game = new DiceRolls(hero, villain); break;
			case GUESS_NUMBER: game = new GuessNumber(hero, villain); break;
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
