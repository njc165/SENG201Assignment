import java.util.Arrays;
import java.util.Collections;
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
	}
	
	public void play() {
		this.villainsChoice = generateVillainsChoice();
	}

	/**
	 * Uses random numbers to select a random choice that
	 * the villain will play. Determined at the start of the game.
	 * @return A string representing the villain's choice of
	 *         paper, scissors, or rock.
	 */
	private String generateVillainsChoice() {
		Random rand = new Random();
		int randNum = rand.nextInt(CHOICES.length);
		return CHOICES[randNum];
	}
	
	/**
	 * Given the hero and villain choices of paper, scissors, or rock,
	 * determines who won the game.
	 * @return A String representing the outcome of the game, from the hero's perspective. Could be "Win", "Draw", or "Loss"
	 */
	public String computeOutcome() {
		if (herosChoice == "Paper") {
			if (villainsChoice == "Paper") {
				return "Draw";
			}
			else if (villainsChoice == "Scissors") {
				return "Loss";
			}
			else {
				return "Win";
			}
		}
		else if (herosChoice == "Scissors") {
			if (villainsChoice == "Paper") {
				return "Win";
			}
			else if (villainsChoice == "Scissors") {
				return "Draw";
			}
			else {
				return "Loss";
			}
		}
		else {
			if (villainsChoice == "Paper") {
				return "Loss";
			}
			else if (villainsChoice == "Scissors") {
				return "Win";
			}
			else {
				return "Draw";
			}
		}
		
	}
	
	/**
	 * Prints to output one choice that the villain did not make.
	 * @param villainChoice
	 */
	public String revealNot() {
		String[] choices = CHOICES.clone();
		Collections.shuffle(Arrays.asList(choices));
		String returnString = "If you see this, PSR.revealNot() is broken";
		for (String choice : choices) {		
			if ( choice != villainsChoice) {
				returnString = choice;
			}
		}
		return returnString;
	}
	
	/**
	 * Setter method for herosChoice.
	 * @param choice The new value for herosChoice.
	 */
	public void setHerosChoice(String choice) {
		herosChoice = choice;
	}
	
	public String getHeroChoiceImage() {
		return Image.getHeroPSRImage(herosChoice);
	}
	
	public String getVillainChoiceImage() {
		return Image.getVillainPSRImage(villainsChoice);
	}
	
}
