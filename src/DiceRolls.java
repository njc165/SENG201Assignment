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
	 * Set to true once the game is finished, regardless of the winner.
	 */
	private boolean gameFinished = false;
	
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
	
	/**
	 * Carries out a Dice Rolls game. When finished, hasWon will be true if the hero won and false otherwise.
	 * All power-ups relevant to Guess the Number will have been removed from the hero, whether or not they were used.
	 */
	public void play() {
		
		while (!gameFinished) {
			int villainRoll = getRoll();
			System.out.println(String.format("%s has rolled %s.", getVillain().getName(), villainRoll));
			
			Util.getIntFromUser(1, "Enter 1 to roll the die");
			int heroRoll = getRoll();
			System.out.println(String.format("You have rolled %s.", heroRoll));
			
			if (rollIncrease > 0) {
				heroRoll += rollIncrease;
				System.out.println(String.format("Since you have a roll bonus of %s, your score is %s.",
						rollIncrease, heroRoll));
			}
			
			determineResult(heroRoll, villainRoll);			
		}
		
		if (getHasWon()) {
			System.out.println(String.format("You have defeated %s!", getVillain().getName()));
		} else {
			System.out.println(String.format("%s has defeated you!", getVillain().getName()));
		}
		
		removeRelevantPowerUps();
	}
	
	/**
	 * Takes the hero's roll and the villian's roll, and determines the result of the game.
	 * If the game is a tie, checks for TieBreaker power-ups.
	 * If the hero has won, sets hasWon to true, otherwise it remains false.
	 * If the game is finished (either hero or villain has won), sets gameFinished to true,
	 * otherwise it remains false.
	 */
	private void determineResult(int heroRoll, int villainRoll) {
		
		if (heroRoll > villainRoll) {
			setHasWon(true);
			gameFinished = true;
			
		} else if (heroRoll == villainRoll) {
			System.out.println("It is a draw.");
			if (heroHasTieBreaker()) {
				System.out.println("You use your Tie Breaker power-up to win the game.");
				setHasWon(true);
				gameFinished = true;
			} else {
				System.out.println("Play again!\n");
			}
			
		} else {
			gameFinished = true;
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
		return getHero().numPowerUps(PowerUpType.TIEBREAKER) >= 0;
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

//	public static void main(String[] args) {
////		Merchant hero = new Merchant("");
//		Gambler hero = new Gambler("");
//		Invictus villain = new Invictus();
//		DiceRolls game = new DiceRolls(hero, villain);
//		game.play();
//	}
	
}
