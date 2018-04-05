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
		if (currentCity.getCurrentLocation() == Location.CENTRE) {
			homeBase();
		} else {
			switch (currentCity.getCurrentSectorType()) {
				case SHOP: shop(); break;
				case POWER_UP_DEN: powerUpDen(); break;
				case HOSPITAL: hospital(); break;
				case VILLAINS_LAIR: villainsLair(); break;
			}
		}
	}
	
/*
 * -----------------------------------------------------------------------------------
 * shop() and helper methods
 * -----------------------------------------------------------------------------------
 */

	private void shop() {
		
	}
	
	
	
/*
 * -----------------------------------------------------------------------------------
 * powerUpDen() and helper methods
 * -----------------------------------------------------------------------------------
 */
	
	private void powerUpDen() {
		final int NUM_CHOICES = 2;
		final int NUM_POWER_UPS = PowerUpType.values().length;
		System.out.println("You entered the Power-up Den.");
		boolean finished = false;
		while (!finished) {
			System.out.println("What would you like to do?");
			int userChoice = Util.getIntFromUser(NUM_CHOICES, "Choose an option:");
			System.out.println("1: Apply a power-up\n2:Go home");
			switch (userChoice) {
				case 1: selectPowerUp(NUM_POWER_UPS); break;
				case 2: finished = true; break;
				default: throw new RuntimeException("Choice does not exist.");
			}			
		}
		homeBase();
	}
	
	private void selectPowerUp(int numPowerUps) {
		System.out.println("Choose a power-up to apply:");
		PowerUpType[] allPowerUps = PowerUpType.values();
		for (int i = 0; i < numPowerUps; i++) {
			int optionNum = i + 1;
			System.out.println(String.format("%d: (Owned: %d)%s", optionNum, count(team.getPowerUpsOwned(), allPowerUps[i]), allPowerUps[i].toString()));
		}
		int userChoice = Util.getIntFromUser(numPowerUps, "Choose an option:");
		if (count(team.getPowerUpsOwned(), allPowerUps[userChoice-1]) == 0) {
			System.out.println("You don't have any of those. You can buy some from the Shop.");
		}
		else {
			for (PowerUp powerUp : team.getPowerUpsOwned()) {
				if (powerUp.getType() == allPowerUps[userChoice-1]) {
					selectHero(powerUp);
					team.getPowerUpsOwned().remove(powerUp);
					break;
				}
			}
		}
	}
	
	private void selectHero(PowerUp powerUp) {
		System.out.println("Choose a hero to power up:");
		ArrayList<Hero> heroes = team.getHeroes();
		
		for (int i = 0; i < heroes.size(); i++) {
			System.out.printf("%d: %s", i+1, heroes.get(i).toString());
		}
		
		int userChoice = Util.getIntFromUser(heroes.size(), "Choose an option:");
		
		heroes.get(userChoice-1).addPowerUp(powerUp);
	}
	
	private int count(ArrayList<PowerUp> list, PowerUpType type) {
		int counter = 0;
		for (PowerUp pu : list) {
			if (pu.getType().equals(type)) {
				counter++;
			}
		}
		return counter;
	}
	
	
	
/*
 * -----------------------------------------------------------------------------------
 * hospital() and helper methods
 * -----------------------------------------------------------------------------------
 */

	private void hospital() {
		
	}
	
	
	
/*
 * -----------------------------------------------------------------------------------
 * villainsLair() and helper methods
 * -----------------------------------------------------------------------------------
 */	
	
	private void villainsLair() {
		
	}
	
	
/*
 * -----------------------------------------------------------------------------------
 * homeBase() and helper methods
 * -----------------------------------------------------------------------------------
 */
	
	/**
	 * Called whenever the team enters the home base sector.
	 * Implements the following actions:
	 * - possibility of a random event occurring (team loses or gains a random object in
	 * 	 their inventory.
	 * - any healing items currently applied to heroes are updated, increasing the hero's
	 * 	 health if needed.
	 * - displays information about the status of each hero on the team.
	 * - if the team owns at least one map, gives them the option to use it.
	 */
	private void homeBase() {
		System.out.println("You are now in the home base.\n");
		randomEvent();
		healHeroes();
		displayTeamStatus();
		checkUseMap();
	}
	
	/**
	 * Implements the possibility of a random event occurring to the team.
	 * A random event can be:
	 * - the team loses a random item from their inventory (team gets robbed).
	 * - the team is gifted a random item to their inventory.
	 */
	private void randomEvent() {
		
	}
	
	/**
	 * Any healing items currently applied to heroes on the team are updated,
	 * increasing the hero's health if needed.
	 */
	private void healHeroes() {
		for (Hero hero: team.getHeroes()) {
			hero.heal();
		}
	}
	
	/**
	 * Displays the current status of each hero in the team.
	 */
	private void displayTeamStatus() {
		System.out.println("The status of each hero on the team is:\n");
		for (Hero hero: team.getHeroes()) {
			System.out.println(hero.status());
		}
	}
	
	/**
	 * If the team currently owns at least one map, asks the team if they want to
	 * use a map.
	 * When a map is used, it is removed from the inventory, all sectors of the city
	 * are set to discovered, and the city is displayed.
	 */
	private void checkUseMap() {
		if (team.getNumMaps() > 0) {
		}
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		Team team = new Team("Team name");
		for (int i = 0; i < 3; i++) {
			team.addHeroFromInput();
		}
		Game game = new Game(team, 3);
		game.play();
		
	}
}
