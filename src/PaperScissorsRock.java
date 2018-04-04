import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PaperScissorsRock extends MiniGame {

	/**
	 * An array of power-ups which are relevant to Paper Scissors Rock.
	 */
	private static final PowerUpType[] RELEVANT_POWER_UPS = {PowerUpType.MINDREADER, PowerUpType.TIEBREAKER};
	
	/**
	 * The possible choices in a game of PaperScissorsRock.
	 */
	private final String[] CHOICES = {"Paper", "Scissors", "Rock"};
		
	/**
	 * A constructor for PaperScissorsRock.
	 * @param hero The hero playing Paper Scissors Rock.
	 * @param villain The villain playing Paper Scissors Rock.
	 */
	public PaperScissorsRock(Hero hero, Villain villain) {
		super(hero, villain);
	}

	/**
	 * Plays through a game of PaperScissorsRock.
	 * Sets victory depending on the outcome.
	 */
	public void play() {
		
		Random rand = new Random();
		boolean gameConcluded = false;
		
		while (!gameConcluded) {
			int randNum = rand.nextInt(CHOICES.length);
			String villainChoice = CHOICES[randNum];
			//missing functionality: hero power-ups.			
			
			String heroChoice = null;
			int input = Util.getIntFromUser(3, "Select a number to play paper, scissors, or rock:\n1: Paper\n2: Scissors\n3: Rock");
			
			switch (input) {
				case 1: heroChoice = "Paper";
						break;
				case 2: heroChoice = "Scissors";
						break;
				case 3: heroChoice = "Rock";
						break;
			}
						
			System.out.println("You played " + heroChoice + ". " + getVillain().getName() + " played " + villainChoice + ".");
			
			String gameOutcome = computeOutcome(heroChoice, villainChoice);
			
			if (gameOutcome == "Win") {
				System.out.println("You won!");
				setHasWon(true);
				gameConcluded = true;
			} else if (gameOutcome == "Draw") {
				handleDraw();
			} else if (gameOutcome == "Loss") {
				System.out.println("You lost!");
				gameConcluded = true;
			} else {
				throw new RuntimeException("Unexpected game outcome");
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
		System.out.println("It was a draw! Play again!\n");
		
	}
	
	public static void main(String[] args) {
		Merchant good = new Merchant("James");
		Invictus evil = new Invictus();
		PaperScissorsRock psr = new PaperScissorsRock(good, evil);
		psr.play();
	}
	
}
