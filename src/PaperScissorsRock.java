import java.util.Random;

public class PaperScissorsRock extends MiniGame {

	/**
	 * An array of the power-up types which are relevant to Paper Scissors Rock.
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
		super(hero, villain, RELEVANT_POWER_UPS);
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
						
			if (hasMindReader()) {
				revealNot(villainChoice);
			}
			
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
				gameConcluded = handleDraw();
			} else if (gameOutcome == "Loss") {
				System.out.println("You lost!");
				gameConcluded = true;
			} else {
				throw new RuntimeException("Unexpected game outcome");
			}
		}
	
	removeRelevantPowerUps();
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
		
	}
	
	/**
	 * When a game is drawn, checks if hero has a Tiebreaker power-up
	 * and responds appropriately.
	 */
	private boolean handleDraw() {
		
		boolean hasTiebreaker = getHero().numPowerUps(PowerUpType.TIEBREAKER) > 0;
		
		if (hasTiebreaker) {
			System.out.println("It was a draw, but your Tiebreaker power-up gives you the win!");
			this.setHasWon(true);
			return true;
		}
		else {
			System.out.println("It was a draw! Play again!");
			return false;
		}
				
	}
	
	/**
	 * Checks if the current hero has a Mindreader power-up.
	 * @return true if the hero has a Mindreader power-up, false otherwise.
	 */
	private boolean hasMindReader() {
		Hero hero = getHero();
		if (hero.getType() == "Gambler") {
			return true;
		}
		if (hero.numPowerUps(PowerUpType.MINDREADER) > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Prints to output one choice that the villain did not make.
	 * @param villainChoice
	 */
	private void revealNot(String villainChoice) {
		for (String choice : CHOICES) {		
			if (!(choice == villainChoice)) {
				String str = String.format("The villain did not play %s.", choice);
				System.out.println(str);
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		Merchant good = new Merchant("James");
		good.getActivePowerUps().add(new TieBreaker());
		Invictus evil = new Invictus();
		PaperScissorsRock psr = new PaperScissorsRock(good, evil);
		psr.play();
	}
	
}
