import java.util.Random;

public class DiceRolls extends MiniGame{
	
	/**
	 * An array of the power-up types which are relevant to Paper Scissors Rock.
	 */
	private static final PowerUpType[] RELEVANT_POWER_UPS = {PowerUpType.INCREASE_ROLL, PowerUpType.TIEBREAKER};

	/**
	 * The number of sides on the dice being used in the game.
	 */
	private final int NUM_DICE_SIDES = 6;
	
	/**
	 * The value of the hero's last dice roll.
	 */
	private int heroRoll;
	
	/**
	 * The value of the villain's last dice roll.
	 */
	private int villainRoll;
	
	/**
	 * The result of the last round of the game.
	 * Possible values are "Win", "Draw" and "Lose".
	 */
	private String result;
	
	/**
	 * After each roll, set to true if it was a draw and the hero has
	 * a tiebreaker, and false otherwise.
	 */
	private boolean usedTieBreaker;
	
	/**
	 * The bonus added to each die roll of the hero.
	 */
	private int rollIncrease;
	
	/**
	 * Constructor for a new Dice Rolls game. Calculates the bonus which will be added to each die roll of the hero.
	 * @param hero		The hero playing the game.
	 * @param villain	The villain playing the game.
	 */
	public DiceRolls(Hero hero, Villain villain) {
		super(hero, villain, RELEVANT_POWER_UPS);
		
		rollIncrease = calculateRollIncrease();
	}
	
	public void play() {
		//TODO remove play method from MiniGame?
	}
	
	/**
	 * Carries out one round of a dice rolls game.
	 * Sets heroRoll and villainRoll to new randomised numbers,
	 * determines the result and whether or not a Tiebreaker was used.
	 * Removes a Tiebreaker from the hero if it is used, and removes all
	 * Increase Roll power ups from the hero when the game is finished.
	 * Sets hasWon to true if the hero wins the game.
	 */
	public void roll() {
		villainRoll = getRoll();
		heroRoll = getRoll();
		
		determineResult();
		
		if (result == "Win" || result == "Lose") {
			removeAllPowerUps(PowerUpType.INCREASE_ROLL);
		}
	}
	
	/**
	 * Takes the hero's roll and the villian's roll, and determines the result of the game.
	 * If the game is a tie, checks for TieBreaker power-ups.
	 * If the hero has won, sets hasWon to true, otherwise it remains false.
	 * If the game is finished (either hero or villain has won), sets gameFinished to true,
	 * otherwise it remains false.
	 */
	private void determineResult() {
		usedTieBreaker = false;
		
		if (heroRoll + rollIncrease > villainRoll) {
			setHasWon(true);
			result = "Win";
			
		} else if (heroRoll + rollIncrease == villainRoll) {
			if (heroHasTieBreaker()) {
				usedTieBreaker = true;
				removePowerUps(PowerUpType.TIEBREAKER, 1);
				setHasWon(true);
				result = "Win";
				
			} else {
				result = "Draw";
			}
			
		} else {
			result = "Lose";
		}
	}
	
	/**
	 * Generates a random die roll between 1 and NUM_DICE_SIDES inclusive.
	 * @return	The random die roll.
	 */
	private int getRoll() {
		Random generator = new Random();
		return generator.nextInt(NUM_DICE_SIDES) + 1;
	}
	
	/**
	 * Checks whether the hero currently has any TieBreaker power-ups applied.
	 * @return	Returns true if the hero has at least one TieBreaker power-up applied, other false.
	 */
	private boolean heroHasTieBreaker() {
		return getHero().numPowerUps(PowerUpType.TIEBREAKER) > 0;
	}
	
	/**
	 * Calculates the bonus added to each of the hero's die rolls, as follows:
	 * -default is 0
	 * -add 1 if the hero has the battle advantage special ability
	 * -add 1 for each IncreaseRoll power up currently applied to the hero
	 * @return The roll increase added to the hero's die rolls.
	 */
	private int calculateRollIncrease() {
		int increase = 0;
		if (getHero().getHasBattleAdvantage())
			increase++;
		increase += getHero().numPowerUps(PowerUpType.INCREASE_ROLL);
		return increase;
	}
	
	/**
	 * Returns the number of increase roll power ups applied to the hero
	 * playing the game.
	 * @return	The number of increase roll power ups applied to the hero.
	 */
	public int numIncreaseRolls() {
		return getHero().numPowerUps(PowerUpType.INCREASE_ROLL);
	}

	/**
	 * Getter method for rollIncrease.
	 * @return The value of rollIncrease.
	 */
	public int getRollIncrease() {
		return rollIncrease;
	}

	/**
	 * Getter method for heroRoll.
	 * @return The value of heroRoll.
	 */
	public int getHeroRoll() {
		return heroRoll;
	}

	/**
	 * Getter method for villainRoll.
	 * @return The value of villainRoll.
	 */
	public int getVillainRoll() {
		return villainRoll;
	}

	/**
	 * Getter method for result.
	 * @return The value of result.
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Getter method for usedTieBreaker.
	 * @return The value of usedTieBreaker.
	 */
	public boolean getUsedTieBreaker() {
		return usedTieBreaker;
	}
		
}
