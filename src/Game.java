import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
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
	
	
/*
 * -----------------------------------------------------------------------------------
 * Constructor and methods related to game setup
 * -----------------------------------------------------------------------------------
 */
	
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
	public Game(Team team, int numberOfCities) {
		this.team = team;
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


/*
 * -----------------------------------------------------------------------------------
 * Methods related to overall game play
 * -----------------------------------------------------------------------------------
 */	
	
	/**
	 * Controls all the gameplay of the game.
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
		
		displayResult();
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
		currentCity.setCurrentSectorDiscovered();
		switch (currentCity.getCurrentSectorType()) {
			case HOME_BASE: homeBase(); break;
			case SHOP: shop(); break;
			case POWER_UP_DEN: powerUpDen(); break;
			case HOSPITAL: hospital(); break;
			case VILLAINS_LAIR: villainsLair(); break;
		}
	}
	
	/**
	 * If gameOver is true, tells the team they have lost.
	 * If gameOver is false, tells the team that they have won, and the time it took
	 * to complete the game.
	 */
	private void displayResult() {
		if (gameOver) {
			System.out.println("You have lost.");
			
		} else {
			long timeTaken = startTime.until(LocalTime.now(), ChronoUnit.MINUTES);
			System.out.println("Congratulations!\nYour team has cleared all the cities of their villains.");
			System.out.println(String.format("You took %s minutes to complete the game.",
					                           timeTaken));
		}
	}
	
/*
 * -----------------------------------------------------------------------------------
 * shop() and helper methods
 * -----------------------------------------------------------------------------------
 */

	/**
	 * Allows the user to purchase power ups, healing items and maps.
	 */
	private void shop() {
		System.out.println("You have entered the shop.\n");
		System.out.println(String.format("Your team currently has %s coins.\n",
											team.getCurrentMoney()));
		
		boolean finishedShopping = false;
		
		while (!finishedShopping) {
			System.out.println("What would you like to do?\n"
								+ "1. View power ups\n"
								+ "2. View healing items\n"
								+ "3. View maps\n"
								+ "4. Leave the shop\n");
			int choice = Util.getIntFromUser(4, "Enter you choice:");
			
			switch (choice) {
				case 1: buyPowerUps(); break;
				case 2: buyHealingItems(); break;
				case 3: buyMaps(); break;
				case 4: finishedShopping = true;
			}
		}
	}
	
	/**
	 * Allows the user to buy as many power ups as they would like.
	 * Gives them an option to return to the main shop area.
	 */
	private void buyPowerUps() {
		System.out.println("Here are the power ups for sale:\n");
		
		for (int i = 0; i < ALL_POWER_UPS.length; i++) {
			PowerUp powerUp = ALL_POWER_UPS[i];
			System.out.println(String.format("%s. %s",
											  i+1,
											  powerUp.shopDescription(team)));
		}
	
		boolean finished = false;
		
		boolean answeredYes = Util.getYesNo("Would you like to buy a power up?");
		
		while (!finished) {
			
			if (answeredYes) {
				System.out.println("Which power up would you like to buy?\n");
				int choice = Util.getIntFromUser(ALL_POWER_UPS.length,
									"Enter a number to select your power up:");
				PowerUp powerUpToBuy = ALL_POWER_UPS[choice - 1];
				PowerUp newPowerUp = (PowerUp) Util.instantiate(powerUpToBuy.getClass());
				
				try {
					team.buyPowerUp(newPowerUp);
					System.out.println(String.format("One %s has been added to you inventory.\n",
														newPowerUp.getType().toString()));
					System.out.println(String.format("Your team now has %s coins remaining.\n",
														team.getCurrentMoney()));
					
				} catch (NotEnoughMoneyException e) {
					System.out.println("Your team doesn't have enough coins to buy that power up.\n");
				}
				
				answeredYes = Util.getYesNo("Would you like to buy another power up?");

			} else {
				finished = true;
			}
		}
	}
	
	/**
	 * Allows the user to buy as many healing items as they would like.
	 * Gives them an option to return to the main shop area.
	 */
	private void buyHealingItems() {
		System.out.println("Here are the healing items for sale:\n");
		
		for (int i = 0; i < ALL_HEALING_ITEMS.length; i++) {
			HealingItem healingItem = ALL_HEALING_ITEMS[i];
			System.out.println(String.format("%s. %s",
											  i+1,
											  healingItem.shopDescription(team)));
		}
	
		boolean finished = false;
		
		boolean answeredYes = Util.getYesNo("Would you like to buy a healing item?");
		
		while (!finished) {
			
			if (answeredYes) {
				System.out.println("Which healing item would you like to buy?\n");
				int choice = Util.getIntFromUser(ALL_HEALING_ITEMS.length,
									"Enter a number to select your healing item:");
				HealingItem itemToBuy = ALL_HEALING_ITEMS[choice - 1];
				HealingItem newHealingItem = (HealingItem) 
												Util.instantiate(itemToBuy.getClass());
				
				try {
					team.buyHealingItem(newHealingItem);
					System.out.println(String.format("One %s has been added to you inventory.\n",
														newHealingItem.getName()));
					System.out.println(String.format("Your team now has %s coins remaining.\n",
														team.getCurrentMoney()));
					
				} catch (NotEnoughMoneyException e) {
					System.out.println("Your team doesn't have enough coins to buy that healing item.\n");
				}
				
				answeredYes = Util.getYesNo("Would you like to buy another healing item?");

			} else {
				finished = true;
			}
		}
	}
	
	/**
	 * Allows the user to buy as many maps as they would like.
	 * Gives them an option to return to the main shop area.
	 */
	private void buyMaps() {
		System.out.println(Map.shopDescription(team));
		
		boolean finished = false;
		
		boolean answersYes = Util.getYesNo("Would you like to buy a map?");
		
		while(!finished) {
			
			if (answersYes) {
				try {
					team.buyMap();
					System.out.println(String.format("You now own %s map(s).\n",
														team.getNumMaps()));
					System.out.println(String.format("Your team now has %s coins remaining.\n",
							team.getCurrentMoney()));
					
					answersYes = Util.getYesNo("Would you like to buy another a map?");
					
				} catch (NotEnoughMoneyException e) {
					System.out.println("You do not have enough money to buy a map.\n");
					finished = true;
				}
				
			} else {
				finished = true;
			}
		}
	}
	
	
	
/*
 * -----------------------------------------------------------------------------------
 * powerUpDen() and helper methods
 * -----------------------------------------------------------------------------------
 */
	/**
	 * Called when the team has entered the Power up Den.
	 * Allows the user to apply power ups to heroes on the team.
	 */
	private void powerUpDen() {
		
		System.out.println("You have entered the Power-up Den.\n");
		
		boolean finished = false;
		boolean answeredYes = Util.getYesNo("Would you like to apply a power up?");
		
		while (!finished) {
			
			if (answeredYes) {
				applyPowerUp();
				answeredYes = Util.getYesNo("Would you like to apply another power up?");
				
			} else {
				finished = true;
			}			
		}
	}
	
	
	/**
	 * Asks the user to select a power up and a hero to apply it to.
	 * Applies the power up to the hero.
	 */
	private void applyPowerUp() {
		
		try {
			PowerUp powerUp = selectPowerUp();
			
			System.out.println(String.format("Which hero would you like to apply the %s to?",
												powerUp));								
			Hero hero = team.selectHero();
			hero.addPowerUp(powerUp);
			System.out.println(String.format("%s has been powered up with one %s.\n",
												hero,
												powerUp));
			
		} catch (NoneOwnedException e) {
			System.out.println("You don't have any of those.\n"
								+ "You can buy some from the Shop.\n");
		}
	}
	
	/**
	 * Asks the user to select the power up they would like to apply.
	 * Returns a PowerUp object of that type from the team's list of owned power ups,
	 * removing the power up from the list.
	 * If no power ups of that type are owned, throws a NoneOwnedException.
	 */
	private PowerUp selectPowerUp() {
		System.out.println("Choose a power up to apply:\n");
		
		PowerUpType[] allPowerUpTypes = PowerUpType.values();
		
		for (int i = 0; i < allPowerUpTypes.length; i++) {
			PowerUpType powerUpType = allPowerUpTypes[i];
			
			int optionNum = i + 1;
			System.out.println(String.format("%d: %s (%d currently owned)",
												optionNum,
												powerUpType.toString(),
												team.numPowerUpsOwned(powerUpType)));
		}
		System.out.println();
		
		int userChoice = Util.getIntFromUser(allPowerUpTypes.length, "Enter your choice:");
		PowerUpType powerUpType = allPowerUpTypes[userChoice - 1];
		
		try {
			return team.popPowerUpFromList(powerUpType);
		} catch (RuntimeException e) {
			throw new NoneOwnedException("No power ups of that type owned.");
		}
	}
	
	
	
/*
 * -----------------------------------------------------------------------------------
 * hospital() and helper methods
 * -----------------------------------------------------------------------------------
 */

	/**
	 * Called when the team enters the hospital.
	 * Gives the user the option to view the statuses of their currently applied 
	 * healing items.
	 * Allows the user to apply any healing items they own to their heroes.
	 */
	private void hospital() {
		
		System.out.println("You have entered the Hospital.\n");
		
		boolean displayStatuses = Util.getYesNo("Would you like to see the statuses of your currently applied healing items?");
		if (displayStatuses)
			displayHealingItemStatuses();
		
		boolean answeredYes = Util.getYesNo("Would you like to apply a healing item?");
		boolean finished = false;
		
		while (!finished) {
			
			if (answeredYes) {
				applyHealingItem();
				answeredYes = Util.getYesNo("Would you like to apply another healing item?");
				
			} else {
				finished = true;
			}
		}		
	}
	
	/**
	 * Lists each hero in the team with the status of their applied healing item,
	 * if they have one.
	 */
	public void displayHealingItemStatuses() {
		for (Hero hero: team.getHeroes()) {
			System.out.println(hero + ":");
			HealingItem healingItem = hero.getAppliedHealingItem();
			
			if (healingItem == null) {
				System.out.println("No currently applied healing item.");
				
			} else {
				System.out.println(String.format("%s is currently applied.",
													healingItem));
				System.out.println(healingItem.getStatus());
			}
			System.out.println();
		}
	}
	
	/**
	 * Asks the user to select a healing item to apply and a hero to apply it to.
	 */
	private void applyHealingItem() {
		
		if (team.getHealingItemsOwned().size() == 0) {
			System.out.println("You don't have any healing items.");
			System.out.println("You can buy some from the Shop.\n");
			
		} else {
			
			System.out.println("Which hero would you like to apply a healing item to?\n");
			Hero hero = team.selectHero();
		
			if (hero.getAppliedHealingItem() == null) {
				HealingItem healingItem = selectHealingItem();
				team.getHealingItemsOwned().remove(healingItem);
				hero.setAppliedHealingItem(healingItem);
				System.out.println(String.format("One %s has been applied to %s.\n",
													healingItem,
													hero));
			} else {
				System.out.println(String.format("%s already has an active healing item.",
													hero));
				System.out.println("Each hero may have only one healing item applied at a time.\n");
			}
		}
	}
	
	/**
	 * Asks the user to select the healing item they would like to apply to the hero.
	 * Displays all the healing items with the numbers currently owned by the team.
	 * Assumes that the team owns at least one healing item.
	 * @return		The selected HealingItem object from the team's list of currently
	 * 				owned healing items.
	 */
	private HealingItem selectHealingItem() {
		System.out.println("Which healing item would you like to apply to the hero?\n");
		
		ArrayList<HealingItem> ownedHealingItems  = new ArrayList<HealingItem>();
		
		for (HealingItem healingItem: ALL_HEALING_ITEMS) {
			if (team.numHealingItemsOwned(healingItem.getName()) > 0) {
				ownedHealingItems.add(healingItem);
			}
		}
		
		for (int i = 0; i < ownedHealingItems.size(); i++) {
			HealingItem healingItem = ownedHealingItems.get(i);
			System.out.println(String.format("%s. %s (%s owned)",
												i+1,
												healingItem,
												team.numHealingItemsOwned(healingItem.getName())));
		}
		System.out.println();
		
		int choice = Util.getIntFromUser(ownedHealingItems.size(), "Enter your choice:");
		HealingItem healingItem = ownedHealingItems.get(choice - 1);
		
		return team.healingItemOfGivenType(healingItem.getName());
	}
	
	
/*
 * -----------------------------------------------------------------------------------
 * villainsLair() and helper methods
 * -----------------------------------------------------------------------------------
 */	
	
	/**
	 * Called when the team enters the Villain's Lair.
	 * Gives the team the option to battle the villain, and carries out the
	 * battle game play.
	 */
	private void villainsLair() {
		
		System.out.println(String.format("You have found the lair of %s.\n",
										  currentCity.getVillain()));
		
		boolean answeredYes = Util.getYesNo("Would you like to enter?");
		
		if (answeredYes) {
			battle();
		} else {
			System.out.println(String.format("%s will be waiting for you.\n",
								currentCity.getVillain()));
		}
		
	}
	
	/**
	 * Carries out the battle game play.
	 * Once finished, either the villain has been defeated, or all the heroes on the
	 * team have died and gameOver has been set to true.
	 */
	private void battle() {
		
		Villain villain = currentCity.getVillain();
		
		System.out.println(String.format("I am %s!", villain));
		System.out.println(villain.getTaunt() + "\n");

		System.out.println("The first round of the battle has started!\n");
		
		while (!villain.isDefeated() && !gameOver) {
						
			System.out.println("Which hero will battle the villain?\n");
			Hero hero = team.selectHero();
			
			MiniGames miniGameType = villain.getGame();
			MiniGame miniGame = MiniGame.createGame(miniGameType, hero, villain);
			
			System.out.println(String.format("%s demands that you play %s!\n",
												villain, miniGameType));
			miniGame.play();
			
			if (miniGame.getHasWon()) {				
				System.out.println(String.format("Congratulations on defeating %s.\n",
													villain));
				
				villain.setTimesDefeated(villain.getTimesDefeated() + 1);	
				if (hero.getHasDoubleDamage()) {
					villain.setTimesDefeated(villain.getTimesDefeated() + 1);
					System.out.println(String.format("%s has done %s double damage!\n",
														hero, villain));
				}
		
				if (villain.isDefeated()) {
					team.setCurrentMoney(team.getCurrentMoney() + PRIZE_MONEY);
					System.out.println(String.format("Your team has been rewarded with %s coins.\n",
														PRIZE_MONEY));
				}
			} else {
				int damage = villain.getDamageDealt();
				team.takeDamage(hero, damage);
				
				System.out.println(String.format("%s has dealt %s %s damage!\n",
													villain,
													hero,
													damage));
				if (team.getHeroes().contains(hero)) {
					System.out.println(String.format("%s now has %s health remaining.\n",
														hero, hero.getCurrentHealth()));
				} else {
					System.out.println(String.format("%s has fallen in battle.\n",
														hero));
					System.out.println(String.format("Your team has %s hero(es) remaining.\n",
														team.getHeroes().size()));
				}
			}
			
			if (team.getHeroes().size() <= 0) {
				gameOver = true;
			}
			
			if (!villain.isDefeated() && !gameOver) {
				System.out.println(String.format("You need to win %s more round(s) to win the battle.\n",
						villain.remainingTimesToDefeat()));
				System.out.println("The next round of the battle has started!\n");
			}
			
		}
		
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
	 * The probability that a random event occurs each time the team enters the
	 * home base is given by RANDOM_EVENT_PROBABILITY.
	 * A random event can be:
	 * - the team loses a random item from their inventory (team gets robbed).
	 * - the team is gifted a random item to their inventory.
	 * The two types of random event occur with equal probability.
	 */
	private void randomEvent() {
		Random random = new Random();
		if(random.nextDouble() < RANDOM_EVENT_PROBABILITY) {
			
			if (random.nextDouble() < 0.5) {
				giftTeam();
			} else {
				robTeam();
			}
		}
	}
	
	/**
	 * Adds a random power up or healing item to the team's inventory
	 */
	private void giftTeam() {
		Random random = new Random();
		
		ArrayList<Object> allItems = new ArrayList<Object>();
		Collections.addAll(allItems, ALL_POWER_UPS);
		Collections.addAll(allItems, ALL_HEALING_ITEMS);
		
		int itemIndex = random.nextInt(allItems.size());
		Object itemToAdd = allItems.get(itemIndex);
		
		System.out.println("The citizens aid you in your fight!");
		System.out.println(String.format("They have gifted you one %s.\n", itemToAdd));
		
		if (itemToAdd instanceof PowerUp) {
			team.getPowerUpsOwned().add((PowerUp) itemToAdd);
		} else {
			team.getHealingItemsOwned().add((HealingItem) itemToAdd);
		}
	}
	
	/**
	 * Removes a random power up or healing item from the team.
	 * If the team doesn't own any power ups or healing items, there is no change
	 */
	private void robTeam() {
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
			System.out.println("You have been robbed!");
			System.out.println(String.format("A sneaky bandit has run off with your %s.\n",
											itemLost));
		}
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
		boolean answersYes = Util.getYesNo("Would you like to view the team's current status?");

		if (answersYes) {
			System.out.println("The status of each hero on the team is:\n");
			for (Hero hero: team.getHeroes()) {
				System.out.println(hero.status());
			}
		}
	}
	
	/**
	 * If the team currently owns at least one map and the sectors in the city
	 * are not all already discovered, asks the team if they want to use a map.
	 * When a map is used, it is removed from the inventory, all sectors of the city
	 * are set to discovered, and the city is displayed.
	 */
	private void checkUseMap() {
		if (team.getNumMaps() > 0 && !(currentCity.getAllDiscovered())) {
			
			System.out.println(String.format("You currently own %s map(s).\n",
												team.getNumMaps()));
			
			boolean answersYes = Util.getYesNo("Would you like to use a map?");
			
			if (answersYes) {
				currentCity.setAllDiscovered();
				team.setNumMaps(team.getNumMaps() - 1);
				System.out.println("The map has revealed the location of each sector.\n");
				
			} else {
				System.out.println("The location of each sector remains a mystery.\n");
			}
		}
	}
	
	public static void main(String[] args) {
		
		boolean validName = false;
		Scanner sc = new Scanner(System.in);
		String teamName = null;
		
		while (!validName) {
			System.out.println("Enter a name for your team of heroes:");
			teamName = sc.next();
			if (teamName.length() >= 2 && teamName.length() <= 10) {
				validName = true;
			} else {
				System.out.println("The name must be between 2 and 10 characters long.\n");
			}
		}
		
		Team team = new Team(teamName);
		
		System.out.println("How many cities would you like to play? (3, 4, 5, or 6)\n");
		int numCities = Util.getIntFromUser(3, 6, "Enter your choice:");
		
		System.out.println("How many heroes would you like on your team? (1, 2 or 3)\n");
		int numHeroes = Util.getIntFromUser(3, "Enter your choice:");
		
		for (int i = 0; i < numHeroes; i++) {
			team.addHeroFromInput();
		}
		
		Game game = new Game(team, numCities);

		game.play();
	}
}
