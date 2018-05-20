import java.util.Random;

/**
 * Instances of GuessNumber represent a type of minigame
 * that the player used to battle villains. An instance of
 * GuessNumber is created each time a villain chooses to play
 * Guess the Number.
 */
public class GuessNumber extends MiniGame {
	
	/**
	 * The upper limit of the number to be guessed.
	 * The number to be guessed will be between 1 and MAX_NUMBER inclusive.
	 */
	public static final int MAX_NUMBER = 10;

	/**
	 * The default number of guesses received by the hero
	 * (without power-ups or special abilities).
	 */
	private final int DEFAULT_GUESSES = 2;
	
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
	 * The value of the most recent guess made by the user.
	 */
	private int guess;
	
	/**
	 * Constructor for a new Guess the Number game.
	 * Initialises guessesLeft to the appropriate value.
	 * @param hero		The hero playing the game.
	 * @param villain	The villain playing the game.
	 */
	public GuessNumber(Hero hero, Villain villain) {
		super(hero, villain);
		
		guessesLeft = initialGuesses();
		numberToGuess = numberToGuess();
	}
	

	/**
	 * Takes a player's guess and sets the minigame as won
	 * if the guess matches the villain's number. Otherwise,
	 * decrements the number of guesses the player has remaining.
	 * @param guess The player's guess.
	 */
	public void guess(int guess) {
		if (guessesLeft <= 0)
			throw new RuntimeException("No guesses left.");
		
		this.guess = guess;
		
		if (guess == numberToGuess) {
			setHasWon(true);
			
		} else {
			guessesLeft--;
		}
	}
	
	/**
	 * Returns the number of extra guess power ups applied to the hero
	 * playing the game.
	 * @return	The number of extra guess power ups applied to the hero.
	 */
	public int numExtraGuesses() {
		return getHero().numPowerUps(PowerUpType.EXTRA_GUESS);
	}
	
	/**
	 * Calculates the initial number of guesses the hero has as follows:
	 * -default number is given by DEFAULT_GUESSES
	 * -one extra guess if the hero has the battle advantage special ability
	 * -one extra guess for each ExtraGuess power-up currently applied to the hero
	 * @return	The initial number of guesses the hero has.
	 */
	private int initialGuesses() {
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
	private int numberToGuess() {
		Random generator = new Random();
		return generator.nextInt(MAX_NUMBER) + 1;
	}


	/**
	 * Getter method for numberToGuess.
	 * @return The value of numberToGuess.
	 */
	public int getNumberToGuess() {
		return numberToGuess;
	}


	/**
	 * Getter method for guessesLeft.
	 * @return The value of guessesLeft.
	 */
	public int getGuessesLeft() {
		return guessesLeft;
	}


	/**
	 * Getter method for guess.
	 * @return The value of guess.
	 */
	public int getGuess() {
		return guess;
	}
	
	
}
