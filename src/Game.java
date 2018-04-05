import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Game {
	
	/**
	 * The team of heroes playing the game.
	 */
	private Team team;
	
	/**
	 * The number of cities the team need to play through in order to win the game.
	 */
	private int numberOfCities;
	
	/**
	 * The start time of the game, as a LocalTime object.
	 * Used to calculate the time taken to finish the game.
	 */
	private LocalTime startTime;
	
	/**
	 * If gameOver is still true once all the cities have been completed, the game
	 * is won, and a victory message is displayed.
	 * If all heroes die (the team is empty), gameOver is set to true,
	 * causing the game to end and a game over message to be displayed.
	 */
	private boolean gameOver = false;
	
	/**
	 * An list of the cities to be played through in the game.
	 * The length of the list should be equal to the number of cities.
	 * The villain in each city should be randomised, and the last city should
	 * contain the super villian.
	 */
	private ArrayList<City> cities = new ArrayList<City>();
	
	/**
	 * The city currently being played by the team.
	 */
	private City currentCity;
	

	
	
/*
 * -----------------------------------------------------------------------------------
 * Constructor and methods related to game setup
 * -----------------------------------------------------------------------------------
 */
	
	/**
	 * Creates a new game with the given team of heroes and the given number
	 * of cities.
	 * The number of cities should be in the accepted range.
	 * The team should already have the correct number of heroes added to it,
	 * and have a valid team name within the accepted length.
	 * @param team				The team of heroes playing the game.
	 * @param numberOfCities	The number of cities the team needs to play through to win.
	 */
	public Game(Team team, int numberOfCities) {
		this.team = team;
		this.numberOfCities = numberOfCities;
		startTime = LocalTime.now();
		initialiseCities();
	}

	/**
	 * Initialises the list of cities to be played through in the game.
	 * The length of the list is equal to the number of cities.
	 * The villain in each city should be randomised, with the last city containing
	 * the super villain.
	 */
	private void initialiseCities() {
		ArrayList<Villain> regularVillains = new ArrayList<Villain>(
				Arrays.asList(Villain.REGULAR_VILLAINS));
		Collections.shuffle(regularVillains);

		ArrayList<Villain> villains = new ArrayList<Villain>(
				regularVillains.subList(0, numberOfCities - 1));
		villains.add(Villain.SUPER_VILLAIN);
		
		City city = null;
		for (Villain villain: villains) {
			city = new City();
			city.setVillain(villain);
			cities.add(city);
		}
	}

	
	
	
/*
 * -----------------------------------------------------------------------------------
 * Methods related to overall game play
 * -----------------------------------------------------------------------------------
 */	
	
	/**
	 * Controls all the game play of the game.
	 * Should be called once a new game has been created.
	 * When play() finishes, gameOver will be set to true if the team lost,
	 * and false if the team won.
	 */
	public void play() {
		for (int i = 0; i < cities.size(); i++) {
			
			System.out.println(String.format("Welcome to City %s.\n", i + 1));
			currentCity = cities.get(i);
			
			if (team.hasMapHero())
				currentCity.setAllDiscovered();
			
			playCity();
			
			if (gameOver)
				break;
		}
	}
	
	/**
	 * Carries out the game play in the current city.
	 * Moves around the city and plays each sector until either the villain is
	 * defeated, or all the heroes die and gameOver is set to true.
	 */
	private void playCity() {
		playCurrentSector();
		while (!(currentCity.getVillain().isDefeated()) && !(gameOver)) {
			move();
			playCurrentSector();
		}
	}
	
	/**
	 * Moves the team to a new sector of the city.
	 * If the current sector is not the home base, the team is automatically returned
	 * to the home base.
	 * If the current sector is the home base, the city is displayed, and the user is
	 * asked to input the sector to which they want to move.
	 */
	private void move() {
		if (currentCity.getCurrentLocation() != Location.CENTRE) {
			
			System.out.println("You have returned to the home base.\n");
			currentCity.setCurrentLocation(Location.CENTRE);
			
		} else {
			
			System.out.println("Where would you like to go from the home base?\n");
			System.out.println(currentCity.stringWithNumbers(true));
			int choice = Util.getIntFromUser(currentCity.numLocations(),
					"Enter a number to choose your destination:");
			currentCity.setCurrentLocationByNumber(choice);
		}
	}
	
	/**
	 * Carries out the appropriate actions depending on the current sector.
	 */
	private void playCurrentSector() {
		switch (currentCity.getCurrentSectorType()) {
			case SHOP: shop(); break;
			case POWER_UP_DEN: powerUpDen(); break;
			case HOSPITAL: hospital(); break;
			case VILLAINS_LAIR: villainsLair(); break;
		}
	}
	
/*
 * -----------------------------------------------------------------------------------
 * shop() and helper methods
 * -----------------------------------------------------------------------------------
 */

	
	
	
	
/*
 * -----------------------------------------------------------------------------------
 * powerUpDen() and helper methods
 * -----------------------------------------------------------------------------------
 */
	
	
	
	
	
	
/*
 * -----------------------------------------------------------------------------------
 * hospital() and helper methods
 * -----------------------------------------------------------------------------------
 */

	
	
	
	
/*
 * -----------------------------------------------------------------------------------
 * villainsLair() and helper methods
 * -----------------------------------------------------------------------------------
 */	
	
	
	
	
	
	
	public static void main(String[] args) {
		Game game = new Game(new Team("Team name"), 3);
//		for (City city: game.cities) {
//			System.out.println(city);
//			System.out.println("Villain: " + city.getVillain());
//		}
		
		game.currentCity = game.cities.get(0); 
		game.move();
		game.move();
		game.move();
	}
	
}
