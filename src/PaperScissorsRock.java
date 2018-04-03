import java.util.Random;
import java.util.Scanner;

public class PaperScissorsRock {

	/**
	 * The possible choices in a game of PaperScissorsRock.
	 */
	private static final String[] choices = {"Paper", "Scissors", "Rock"};
	
	/**
	 * An ArrayList of power-ups which are relevant to Paper Scissors Rock.
	 */
	//private static final PowerUp[] relevantPowerUps = {tiebreaker, mindreader}

	/**
	 * The hero playing Paper Scissors Rock.
	 */
	private Hero hero;
	
	/**
	 * The villain playing Paper Scissors Rock.
	 */
	private Villain villain;
	
	/**
	 * The result of the game. False for defeat, true for victory.
	 * Defaults to false.
	 */
	private boolean hasWon;
		
	/**
	 * A constructor for PaperScissorsRock.
	 * @param hero The hero playing Paper Scissors Rock.
	 * @param villain The villain playing Paper Scissors Rock.
	 */
	public PaperScissorsRock(Hero hero, Villain villain) {
		this.hero = hero;
		this.villain = villain;
		this.hasWon = false;
	}
	
	/**
	 * Getter method for hasWon.
	 * @return The value of hasWon.
	 */
	public boolean getHasWon() {
		return hasWon;
	}

	/**
	 * Plays through a game of PaperScissorsRock.
	 * Sets victory depending on the outcome.
	 */
	public void play() {
		
		Random rand = new Random();
		boolean gameConcluded = false;
		
		while (!gameConcluded) {
			int randNum = rand.nextInt(choices.length);
			String villainChoice = choices[randNum];
			//missing functionality: hero power-ups.
			Scanner sc = new Scanner(System.in);
			System.out.println("Select a number to play paper, scissors, or rock:\n\n1: Paper\n2: Scissors\n3: Rock\n");
			
			boolean validInput = false;
			String heroChoice = null;
			
			while (!validInput) {
				int input = sc.nextInt();
				switch (input) {
					case 1: heroChoice = "Paper";
							validInput = true;
							break;
					case 2: heroChoice = "Scissors";
							validInput = true;
							break;
					case 3: heroChoice = "Rock";
							validInput = true;
							break;
					default:System.out.println("Invalid input. Enter 1 for paper, 2 for scissors, or 3 for rock.");
				}
			}
			
			sc.close();
			
			System.out.println("You played " + heroChoice + ". " + villain.getName() + " played " + villainChoice + ".");
			
			String gameOutcome = computeOutcome(heroChoice, villainChoice);
			
			if (gameOutcome == "Win") {
				System.out.println("You won!");
				this.hasWon = true;
				gameConcluded = true;
			} else if (gameOutcome == "Draw") {
				handleDraw();
			} else if (gameOutcome == "Loss") {
				System.out.println("You lost!");
				gameConcluded = true;
			} else {
				System.out.println("Unexpected game outcome. Error in PaperScissorsRock.java");
				throw new RuntimeException();
			}
		}
		
	}
	
	/**
	 * Given the hero and villain choices of paper, scissors, or rock,
	 * determines who won the game.
	 * @param heroChoice The choice made by the hero.
	 * @param villainChoice The choice made by the villain.
	 * @return A String representing the outcome of the game, from the hero's perspective. Could be "Win", "Draw", or "Loss"
	 */
	private String computeOutcome(String heroChoice, String villainChoice) {
		if (heroChoice == "Paper") {
			if (villainChoice == "Paper") {
				return "Draw";
			}
			else if (villainChoice == "Scissors") {
				return "Loss";
			}
			else {
				return "Win";
			}
		}
		else if (heroChoice == "Scissors") {
			if (villainChoice == "Paper") {
				return "Win";
			}
			else if (villainChoice == "Scissors") {
				return "Draw";
			}
			else {
				return "Loss";
			}
		}
		else {
			if (villainChoice == "Paper") {
				return "Loss";
			}
			else if (villainChoice == "Scissors") {
				return "Win";
			}
			else {
				return "Draw";
			}
		}
		
		/*for (int i = 0; i <= relevantPowerUps.size(); i++) {
			if (relevantPowerUps.contains(hero.getActivePowerUps()[i])) {
				ArrayList<PowerUp> newActivePowerUps = hero.getActivePowerUps.remove(i);
				hero.setActivePowerUps(newActivePowerUps0);
			}
		}*/
		
	}
	
	/**
	 * When a game is drawn, checks if hero has a Tiebreaker power-up
	 * and responds appropriately.
	 */
	private void handleDraw() {
		
		/*boolean hasTiebreaker = false;
		
		for (PowerUp pu : hero.activePowerUps) {
			if (pu.type == "Tiebreaker") { // this may change once PowerUp is implemented
				hasTiebreaker = true;
			}
		}
		
		if (hasTiebreaker) {
			System.out.println("It was a draw, but your Tiebreaker power-up gives you the win!");
			this.hasWon = true;
		}
		else {
			System.out.println("It was a draw! Play again!");
			play();
		}*/
		
		//remove the following when PowerUps are implemented.
		System.out.println("It was a draw! Play again!");
		
	}
	
	public static void main(String[] args) {
		Merchant good = new Merchant("James");
		Invictus evil = new Invictus();
		PaperScissorsRock psr = new PaperScissorsRock(good, evil);
		psr.play();
	}
	
}
