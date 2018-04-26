import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class GameEnvironment {
	
	/**
	 * An array of instances of all the PowerUp subclasses.
	 */
	private final PowerUp[] ALL_POWER_UPS = {new ExtraGuess(),
			  								 new IncreaseRoll(),
			  								 new MindReader(),
			  								 new TieBreaker()};
	
	/**
	 * An array of instances of all the HealingItem subclasses.
	 */
	private final  HealingItem[] ALL_HEALING_ITEMS = {new AlicornDust(),
													  new HeartyMeal(),
													  new SuspiciousTonic()};
	
	/**
	 * The amount of money the team is given when they defeat a villain.
	 */
	private final int PRIZE_MONEY = 100;
	
	/**
	 * The probability as a decimal that a random event will occur each time the
	 * team returns to the home base.
	 */
	private final double RANDOM_EVENT_PROBABILITY = 0.2;
	
	/**
	 * The team of heroes playing the game.
	 */
	private Team team;
	
	/**
	 * The number of cities the team need to play through in order to complete
	 * the game.
	 * User is asked to choose the number of cities before the game is created.
	 * Can be between 3 and 6.
	 */
	private int numberOfCities;
	
	/**
	 * The start time of the game, as a LocalTime object.
	 * Used to calculate the time taken to finish the game.
	 */
	private LocalTime startTime;
	
	/**
	 * If gameOver is still false once all the cities have been completed, the game
	 * is won, and a victory message is displayed.
	 * If all heroes die (the team is empty), gameOver is set to true,
	 * causing the game to end and a game over message to be displayed.
	 */
	private boolean gameOver = false;
	
	/**
	 * An list of the cities to be played in the game.
	 * The length of the list should be equal to numberOfCities.
	 * The villain in each city is randomised, and the last city contains
	 * the super villain.
	 */
	private ArrayList<City> cities = new ArrayList<City>();
	
	/**
	 * The city currently being played by the team.
	 */
	private City currentCity;
	
	/**
	 * Creates a new game with the given team of heroes and the given number
	 * of cities.
	 * The number of cities is assumed to be in the accepted range.
	 * The team should already have the correct number of heroes added to it,
	 * and have a valid team name within the accepted length.
	 * @param team				The team of heroes playing the game.
	 * @param numberOfCities	The number of cities the team needs to play
	 * 							through to complete the game.
	 */
	public GameEnvironment(String teamName, int numberOfHeroes, int numberOfCities) {
		this.team = new Team(teamName, numberOfHeroes);
		this.numberOfCities = numberOfCities;
		startTime = LocalTime.now();
		initialiseCities();
	}

	/**
	 * Initialises the list of cities to be played through in the game.
	 * The length of the list is equal to numberOfCities.
	 * The villain in each city is randomised, with the last city containing
	 * the super villain.
	 */
	private void initialiseCities() {
		ArrayList<Villain> villains = Villain.randomisedVillains(numberOfCities);
		
		for (Villain villain: villains) {
			City city = new City(villain);
			cities.add(city);
		}
	}

	/**
	 * Getter method for team.
	 * @return The value of team.
	 */
	public Team getTeam() {
		return team;
	}
}