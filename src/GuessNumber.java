import java.util.Random;
import java.util.Scanner;

public class GuessNumber {
	
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
	 * The hero playing the game.
	 */
	private Hero hero;
	
	/**
	 * The villain playing the game.
	 */
	private Villain villain;
	
	/**
	 * The number which the hero is trying to guess.
	 * Initialised to a random number between 1 and 10 inclusive.
	 */
	private int numberToGuess;
	
	/**
	 * Set to true if the hero wins the game, otherwise remains false.
	 */
	private boolean hasWon = false;
	
	/**
	 * The number of remaining guesses the hero has.
	 */
	private int guessesLeft;
	
	/**
	 * A constructor which creates a new Guess the Number game.
	 * Initialises guessesLeft to the appropriate value.
	 * @param hero		The hero playing the game.
	 * @param villain	The villain playing the game.
	 */
	public GuessNumber(Hero hero, Villain villain) {
		this.hero = hero;
		this.villain = villain;
		guessesLeft = calculateGuessesLeft();
		numberToGuess = getNumberToGuess();
	}
	
	/**
	 * Carries out the Guess the Number game.
	 * Once complete, hasWon will be set to true if the hero won, and false if the villain won.
	 * All power-ups relevant to Guess the Number will be removed from the hero, whether or not they were used.
	 */
	public void play() {
		while ((guessesLeft > 0) && (!hasWon)) {
			System.out.println(String.format("You have %s chance(s) left to guess the number.", guessesLeft));
			int guess = getGuessFromPlayer();
			if (guess == numberToGuess) {
				hasWon = true;
			} else if (guess > numberToGuess) {
				System.out.println("Your guess was too high.");
			} else {
				System.out.println("Your guess was too low.");
			}
			guessesLeft--;
		}
		if (hasWon) {
			System.out.println(String.format("You guessed correctly! You have defeated %s!",
					villain.getName()));
		}
		else {
			System.out.println(String.format("You have run out of guesses. %s has defeated you.\n"
					+ "%s's number was %s.",
					villain.getName(), villain.getName(), numberToGuess));
		}
		// Remove all ExtraGuess power-ups from hero
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
		if (hero.getHasBattleAdvantage())
			guesses++;
		// add extra guess for each ExtraGuess power-up
		return guesses;
	}
	
	/**
	 * Chooses a random number between 1 and MAX_NUMBER_TO_BE_GUESSED inclusive.
	 * @return	The random number.
	 */
	private int getNumberToGuess() {
		System.out.println(String.format("%s has chosen a number between 1 and %s.",
				villain.getName(), MAX_NUMBER_TO_BE_GUESSED));
		Random generator = new Random();
		return generator.nextInt(MAX_NUMBER_TO_BE_GUESSED) + 1;
	}
	
	/**
	 * Asks the user to input a number, until a valid number
	 * between 1 and MAX_NUMBER_TO_BE_GUESSED is entered.
	 * @return	The number entered by the user.
	 */
	private int getGuessFromPlayer() {
		return Util.getIntFromUser(MAX_NUMBER_TO_BE_GUESSED, "Enter your next guess:");
	}
	
	public static void main(String[] args) {
		Hero hero = new Hero("", "", "", "", 0);
		Villain villain = new Villain("John", "", 0, new MiniGame[] {MiniGame.GUESS_NUMBER});
		GuessNumber game = new GuessNumber(hero, villain);
		game.play();
	}
	
}
