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
	 * A string representation of what the villain associated with this game
	 * will play (paper, scissors, or rock).
	 */
	private String villainsChoice;
	
	/**
	 * A string representation of what the hero associated with this game
	 * chose to play.
	 */
	private String herosChoice;
		
	/**
	 * A constructor for PaperScissorsRock.
	 * @param hero The hero playing Paper Scissors Rock.
	 * @param villain The villain playing Paper Scissors Rock.
	 */
	public PaperScissorsRock(Hero hero, Villain villain) {
		super(hero, villain, RELEVANT_POWER_UPS);
		this.villainsChoice = getVillainChoice();
	}
	
	public void play() {
		//TODO what to do here?
	}

	/**
	 * Uses random numbers to select a random choice that
	 * the villain will play. Determined at the start of the game.
	 * @return A string representing the villain's choice of
	 *         paper, scissors, or rock.
	 */
	private String getVillainChoice() {
		Random rand = new Random();
		int randNum = rand.nextInt(CHOICES.length);
		return CHOICES[randNum];
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
			System.out.println("It was a draw, but your Tiebreaker power-up gives you the win!\n");
			this.setHasWon(true);
			return true;
		}
		else {
			System.out.println("It was a draw! Play again.\n");
			return false;
		}
				
	}
	
	/**
	 * Checks if the current hero has a Mindreader power-up.
	 * @return true if the hero has a Mindreader power-up, false otherwise.
	 */
	private boolean hasMindReader() {
		Hero hero = getHero();
		return (hero.getHasBattleAdvantage()
				|| hero.numPowerUps(PowerUpType.MINDREADER) > 0);
	}
	
	/**
	 * Prints to output one choice that the villain did not make.
	 * @param villainChoice
	 */
	private void revealNot(String villainChoice) {
		for (String choice : CHOICES) {		
			if (!(choice == villainChoice)) {
				if (getHero().getHasBattleAdvantage()) {
					System.out.println(String.format("Your special ability lets you sense that "
														+ "%s did not play %s.\n",
															getVillain(),
															choice));
				} else {
					System.out.println(String.format("Your Mindreader power up lets you sense that "
													+ "%s did not play %s.\n",
													getVillain(),
													choice));
				}
				break;
			}
		}
	}
	
	/**
	 * Setter method for herosChoice.
	 * @param choice The new value for herosChoice.
	 */
	public void setHerosChoice(String choice) {
		herosChoice = choice;
	}
	
}
