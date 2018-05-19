import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Instances of PaperScissorsRock represent a minigame that
 * the player plays against villains. Each object represents
 * one minigame. Instances are created when a villain chooses
 * to play Paper Scissors Rock.
 */
public class PaperScissorsRock extends MiniGame {
	
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
		super(hero, villain);
	}
	
	/**
	 * Randomly selects a new value for the villain's choice, out of
	 * Paper, Scissors and Rock.
	 */
	public void updateVillainsChoice() {
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
				
			} else if (villainsChoice == "Scissors") {
				return "Loss";
				
			} else {
				return "Win";
			}
			
		} else if (herosChoice == "Scissors") {
			if (villainsChoice == "Paper") {
				return "Win";
				
			} else if (villainsChoice == "Scissors") {
				return "Draw";
				
			} else {
				return "Loss";
			}
			
		} else {
			if (villainsChoice == "Paper") {
				return "Loss";
				
			} else if (villainsChoice == "Scissors") {
				return "Win";
				
			} else {
				return "Draw";
			}
		}
		
	}
	
	/**
	 * Returns one of the choices that the villain did not make.
	 * @return A string reprenting a choice the villain did not make.
	 */
	public String revealNot() {
		String[] choices = CHOICES.clone();
		Collections.shuffle(Arrays.asList(choices));
		
		String returnString = "If you see this, PSR.revealNot() is broken";
		for (String choice : choices) {		
			if (choice != villainsChoice) {
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
	
	/**
	 * Getter method for villainsChoice.
	 * @return The value of villainsChoice.
	 */
	public String getVillainsChoice() {
		return villainsChoice;
	}

	/**
	 * Getter method for herosChoice.
	 * @return The value of herosChoice.
	 */
	public String getHerosChoice() {
		return herosChoice;
	}
	
}
