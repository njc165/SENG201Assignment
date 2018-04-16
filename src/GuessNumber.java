import java.util.Random;

public class GuessNumber extends MiniGame {
	
	/**
	 * An array of the power-up types which are relevant to Paper Scissors Rock.
	 */
	private static final PowerUpType[] RELEVANT_POWER_UPS = {PowerUpType.EXTRA_GUESS};

	
	/**
	 * The default number of guesses received by the hero (without power-ups or special abilities).
	 */
	private final int DEFAULT_GUESSES = 2;
	
	/**
	 * The upper limit of the number to be guessed.
	 * The number to be guessed will be between 1 and MAX_NUMBER_TO_BE_GUESSED inclusive.
	 */
	private final int MAX_NUMBER_TO_BE_GUESSED = 10;
	
	/**
	 * The number which the hero is trying to guess.
	 * Initialised to a random number between 1 and 10 inclusive.
	 */
	private int numberToGuess;
	
	/**
	 * The number of remaining guesses the hero has.
	 */
	private int guessesLeft;
	
	/**
	 * Constructor for a new Guess the Number game.
	 * Initialises guessesLeft to the appropriate value.
	 * @param hero		The hero playing the game.
	 * @param villain	The villain playing the game.
	 */
	public GuessNumber(Hero hero, Villain villain) {
		super(hero, villain, RELEVANT_POWER_UPS);
		guessesLeft = calculateGuessesLeft();
		numberToGuess = getNumberToGuess();
	}
	
	/**
	 * Carries out the Guess the Number game.
	 * Once complete, hasWon will be set to true if the hero won, and false if the villain won.
	 * All power-ups relevant to Guess the Number will have been removed from the hero, whether or not they were used.
	 */
	public void play() {
		System.out.println(String.format("%s has chosen a number between 1 and %s.\n",
				getVillain(), MAX_NUMBER_TO_BE_GUESSED));
		while ((guessesLeft > 0) && (!getHasWon())) {
			System.out.println(String.format("You have %s chance(s) left to guess the number.\n", guessesLeft));
			int guess = getGuessFromPlayer();
			if (guess == numberToGuess) {
				setHasWon(true);
			} else if (guess > numberToGuess) {
				System.out.println("Your guess was too high.\n");
			} else {
				System.out.println("Your guess was too low.\n");
			}
			guessesLeft--;
		}
		if (getHasWon()) {
			System.out.println(String.format("You guessed correctly! You have defeated %s!\n",
					getVillain()));
		}
		else {
			System.out.println(String.format("The chosen number was %s.\n",
												numberToGuess));
			System.out.println(String.format("You have run out of guesses.\n%s has defeated you!\n",
												getVillain()));
		}
		removeRelevantPowerUps();
	}
	
	/**
	 * Calculates the initial number of guesses the hero has as follows:
	 * -default number is given by DEFAULT_GUESSES
	 * -one extra guess if the hero has the battle advantage special ability
	 * -one extra guess for each ExtraGuess power-up currently applied to the hero
	 * @return	The initial number of guesses the hero has.
	 */
	private int calculateGuessesLeft() {
		int guesses = DEFAULT_GUESSES;
		if (getHero().getHasBattleAdvantage())
			guesses++;
		guesses += getHero().numPowerUps(PowerUpType.EXTRA_GUESS);
		return guesses;
	}
	
	/**
	 * Chooses a random number between 1 and MAX_NUMBER_TO_BE_GUESSED inclusive.
	 * @return	The random number.
	 */
	private int getNumberToGuess() {
		Random generator = new Random();
		return generator.nextInt(MAX_NUMBER_TO_BE_GUESSED) + 1;
	}
	
	/**
	 * Asks the user to input a number, until a valid number
	 * between 1 and MAX_NUMBER_TO_BE_GUESSED is entered.
	 * @return	The number entered by the user.
	 */
	private int getGuessFromPlayer() {
		return Util.getIntFromUser(MAX_NUMBER_TO_BE_GUESSED, "Enter your guess:");
	}
	
	public static void main(String[] args) {
		Gambler hero = new Gambler("");
		Invictus villain = new Invictus();
		GuessNumber game = new GuessNumber(hero, villain);
		game.play();
	}
	
}
