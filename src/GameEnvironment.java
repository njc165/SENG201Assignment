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
	public static final PowerUp[] ALL_POWER_UPS = {new ExtraGuess(),
			  								 	   new IncreaseRoll(),
			  								 	   new MindReader(),
			  								 	   new TieBreaker()};
	
	/**
	 * An array of instances of all the HealingItem subclasses.
	 */
	public static final HealingItem[] ALL_HEALING_ITEMS = {new AlicornDust(),
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
	 * The start time of the game, as a LocalTime object.
	 * Used to calculate the time taken to finish the game.
	 */
	private LocalTime startTime;
	
	/**
	 * The number of cities the team need to play through in order to complete
	 * the game.
	 * User is asked to choose the number of cities before the game is created.
	 * Can be between 3 and 6.
	 */
	private int numberOfCities;
	
	/**
	 * An list of the cities to be played in the game.
	 * The length of the list should be equal to numberOfCities.
	 * The villain in each city is randomised, and the last city contains
	 * the super villain.
	 */
	private ArrayList<City> cities = new ArrayList<City>();
	
	/**
	 * The index of the city currently being played in the list of cities.
	 */
	private int currentCityIndex;
	

	/**
	 * Creates a new game.
	 * Creates a team with the given team name, and sets startNumHeroes of
	 * team to the given number of heroes, but doesn't add any heroes to the team.
	 * Initialises the list of cities and sets currentCityIndex to 0.
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
	 * Sets currentCityIndex to 0, and sets all the sectors of the first city
	 * to discovered if the team has a hero with the map special ability.
	 */
	public void enterFirstCity() {
		currentCityIndex = 0;
		if (team.hasMapHero()) {
			currentCity().setAllDiscovered();
		}
	}
	
	/**
	 * Increments the currentCityIndex, and sets all sectors in the new city
	 * to discovered if the team has a hero with the map specialAbility.
	 * Throws a RuntimeException if currentCityIndex >= numerOfCities.
	 */
	public void nextCity() {
		if (currentCityIndex < numberOfCities - 1) {
			currentCityIndex++;
			if (team.hasMapHero()) {
				currentCity().setAllDiscovered();
			}
			
		} else {
			throw new RuntimeException("Already in last city, can't move to next city");
		}
	}
	
	/**
	 * Any healing items currently applied to heroes on the team are updated,
	 * increasing the hero's health if needed.
	 */
	public void healHeroes() {
		for (Hero hero: team.getHeroes()) {
			hero.heal();
		}
	}
	
	/**
	 * Implements the possibility of a random event occurring to the team.
	 * The probability that a random event occurs each time the team enters the
	 * home base is given by RANDOM_EVENT_PROBABILITY.
	 * A random event can be:
	 * - the team loses a random item from their inventory (team gets robbed).
	 * - the team is gifted a random item to their inventory.
	 * The two types of random event occur with equal probability.
	 * @return		A string description of the random event that has occurred,
	 * 				or null if no event occurred.
	 */
	public String randomEvent() {
		String randomEventDescription = null;
		
		Random random = new Random();
		if(random.nextDouble() < RANDOM_EVENT_PROBABILITY) {
						
			if (random.nextDouble() < 0.5) {
				randomEventDescription = giftTeam();
			} else {
				randomEventDescription = robTeam();
			}
		}
		
		return randomEventDescription;
	}
	
	/**
	 * Adds a random power up or healing item to the team's inventory.
	 * @return A string description of the random event that has occurred.
	 */
	private String giftTeam() {
		Random random = new Random();
		
		ArrayList<Object> allItems = new ArrayList<Object>();
		Collections.addAll(allItems, ALL_POWER_UPS);
		Collections.addAll(allItems, ALL_HEALING_ITEMS);
		
		int itemIndex = random.nextInt(allItems.size());
		Object itemToAdd = allItems.get(itemIndex);
		
		if (itemToAdd instanceof PowerUp) {
			team.getPowerUpsOwned().add((PowerUp) itemToAdd);
		} else {
			team.getHealingItemsOwned().add((HealingItem) itemToAdd);
		}
		
		return String.format("Random Event!\n\n"
				+ "The citizens aid you in your fight against the villains.\n"
				+ "They have gifted you one %s.", itemToAdd);
	}
	
	/**
	 * Removes a random power up or healing item from the team.
	 * If the team doesn't own any power ups or healing items, there is no change.
	 * @return A string description of the random event that has occurred.
	 */
	private String robTeam() {
		Random random = new Random();
		Object itemLost = null;
		
		if (random.nextDouble() > 0.5) {
			
			if (team.getPowerUpsOwned().size() > 0) {
				int itemIndex = random.nextInt(team.getPowerUpsOwned().size());
				itemLost = team.getPowerUpsOwned().get(itemIndex);
				team.getPowerUpsOwned().remove(itemIndex);
			}
			
		} else {
			
			if (team.getHealingItemsOwned().size() > 0) {
				int itemIndex = random.nextInt(team.getHealingItemsOwned().size());
				itemLost = team.getHealingItemsOwned().get(itemIndex);
				team.getHealingItemsOwned().remove(itemIndex);
			}
		}
		
		if (itemLost != null) {
			return String.format("Random Event!\n\n"
					+ "You have been robbed.\nA sneaky bandit has run off with your %s.",
					itemLost);
		} else {
			return null;
		}
	}
	
	/**
	 * Takes the result of a completed mini game, and processes the result.
	 * If the hero won, does damage to the villain, checking whether the hero
	 * does double damage. If the villain has been defeated three times,
	 * rewards the team with coins.
	 * If the hero lost, the hero takes the appropriate amount of damage.
	 * @param hasWon	Whether or not the hero won the mini game.
	 * @param hero		The hero who was playing the mini game.
	 * @param villain 	The villain who was playing the mini game.
	 */
	public void processMiniGameResult(boolean hasWon, Hero hero, Villain villain) {
		if (villain != currentCity().getVillain()) {
			throw new IllegalArgumentException("Incorrect villain");
		}
		
		if (hasWon) {
			villain.setTimesDefeated(villain.getTimesDefeated() + 1);	
			if (hero.getHasDoubleDamage()) {
				villain.setTimesDefeated(villain.getTimesDefeated() + 1);
			}
			
			if (villain.isDefeated()) {
				team.setCurrentMoney(team.getCurrentMoney() + PRIZE_MONEY);
			}
			
		} else {
			int damage = villain.getDamageDealt();
			team.takeDamage(hero, damage);
		}
	}
	
	/**
	 * Getter method for team.
	 * @return The value of team.
	 */
	public Team getTeam() {
		return team;
	}
	
	/**
	 * Getter method for currentCityIndex.
	 * @return The value of currentCityIndex.
	 */
	public int getCurrentCityIndex() {
		return currentCityIndex;
	}
	
	/**
	 * Returns the current city being played.
	 * @return The current city.
	 */
	public City currentCity() {
		return cities.get(currentCityIndex);
	}
	
	/**
	 * Getter method for PRIZE_MONEY.
	 * @return the value of PRIZE_MONEY.
	 */
	public int getPrizeMoney() {
		return PRIZE_MONEY;
	}
	
	/**
	 * Getter method for startTime.
	 * @return The value of startTime.
	 */
	public LocalTime getStartTime() {
		return startTime;
	}
}