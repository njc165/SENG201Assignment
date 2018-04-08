import java.util.ArrayList;
import java.util.Scanner;

public class Team {
		
	/**
	 * The amount of money a team starts with.
	 */
	private final int STARTING_MONEY = 50;
	
	/**
	 * An ArrayList of power-ups currently owned and not applied,
	 * initialised to empty.
	 */
	private ArrayList<PowerUp> powerUpsOwned = new ArrayList<PowerUp>();
	
	/**
	 * An ArrayList of healing items currently owned and not applied.
	 */
	private ArrayList<HealingItem> healingItemsOwned = new ArrayList<HealingItem>();
	
	/**
	 * All heroes currently on the team.
	 * This ArrayList is populated when a Team is created and depopulated as heroes die.
	 * The game is lost when this ArrayList becomes empty.
	 */
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	
	/**
	 * A String representing the name of the team.
	 */
	private String name;
		
	/**
	 * An integer representing the number of maps the team owns.
	 * Can be any non-negative integer.
	 * Having an Explorer on the team does not affect this value.
	 */
	private int numMaps;
	
	/**
	 * An integer representing the amount of money the team currently has.
	 * Could be any non-negative integer.
	 */
	private int currentMoney;
	
	/**
	 * A constructor for Team, called before the game starts.
	 * Creates an empty team, to which heroes can be added.
	 * @param name 		The team's name.
	 */
	public Team(String name) {
		this.name = name;
		this.currentMoney = STARTING_MONEY;
	}

	/**
	 * Add heroes to the team after the team is constructed.
	 * Asks the user for a hero name and type, disallows names
	 * that have already been taken.
	 */
	public void addHeroFromInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a name for a hero:");
		
		boolean validName = false;
		String heroName = null;
		
		while (!validName) {
			heroName = sc.nextLine();
			if (isValidName(heroName)) {
				validName = true;
			} else {
				System.out.println("That name has already been taken. Please enter another:");
			}
		}
		
		System.out.println(String.format("Enter a number to select a hero type for %s:\n",
				heroName));
		System.out.println(Hero.allHeroesDescription());
		
		int choice = Util.getIntFromUser(Hero.ALL_HEROES.length, "Enter your choice:");
		int heroIndex = choice - 1;
		
		String type = Hero.ALL_HEROES[heroIndex].getType();
		
		switch (type) {
			case "Apprentice": heroes.add(new Apprentice(heroName));
							   break;
			case "Bulwark":    heroes.add(new Bulwark(heroName));
							   break;
			case "Explorer":   heroes.add(new Explorer(heroName));
			                   break;
			case "Gambler":    heroes.add(new Gambler(heroName));
			                   break;
			case "Mercenary":  heroes.add(new Mercenary(heroName));
			                   break;
			case "Merchant":   heroes.add(new Merchant(heroName));
							   break;
		    default:           throw new RuntimeException("No such hero type");
		}
	}
	
	/**
	 * Checks whether the given heroName is valid.
	 * A name is valid if it has not already been assigned to another hero
	 * on the team.
	 * @return	true if the name is valid, false otherwise.
	 */
	private boolean isValidName(String heroName) {
		boolean isValid  = true;
		for (Hero hero: heroes) {
			if (hero.getName().equals(heroName)) {
				isValid = false;
			}
		}
		return isValid;
	}
	
	/**
	 * Asks the user to select a hero from the team.
	 * @return The selected Hero.
	 */
	public Hero selectHero() {
		
		System.out.println("Choose a hero:\n");
		
		for (int i = 0; i < heroes.size(); i++) {
			int optionNum = i + 1;
			System.out.println(String.format("%d: %s", optionNum, heroes.get(i)));
		}
		System.out.println();
		
		int userChoice = Util.getIntFromUser(heroes.size(), "Enter your choice:");
		
		return heroes.get(userChoice - 1);
		
	}
	
	/**
	 * Reduces a given hero's current health by a given amount.
	 * If health falls below 0, remove the hero from the team.
	 * @param hero 		The hero to take damage.
	 * @param amount 	The amount of damage to be taken.
	 */
	public void takeDamage(Hero hero, int amount) {
		if (hero.getHasReducedDamage()) {
			amount *= Hero.DAMAGE_REDUCTION_MULTIPLIER;
		}
		hero.setCurrentHealth(hero.getCurrentHealth() - amount);
		if (hero.getCurrentHealth() <= 0) {
			heroes.remove(hero);
		}
	}
	
	/**
	 * Takes a new powerUp object and buys it if the team has enough money.
	 * If the team has enough money, the power up is added to the team's list of
	 * owned power ups, and the team's current money is decreased by the appropriate
	 * amount.
	 * If the team doesn't have enough money, an NotEnoughMoneyException is thrown.
	 * @param powerUp The new PowerUp instance to be bought.
	 */
	public void buyPowerUp(PowerUp powerUp) {
		if (powerUp.getCost(hasDiscountHero()) > currentMoney) {
			throw new NotEnoughMoneyException("Not enough money to buy that power up");
		} else {
			currentMoney -= powerUp.getCost(hasDiscountHero());
			powerUpsOwned.add(powerUp);
		}
	}
	
	/**
	 * Takes a new healingItem object and buys it if the team has enough money.
	 * If the team has enough money, the healing item is added to the team's list of
	 * owned healing item, and the team's current money is decreased by the appropriate
	 * amount.
	 * If the team doesn't have enough money, an NotEnoughMoneyException is thrown.
	 * @param healingItem The new HealingItem instance to be bought.
	 */
	public void buyHealingItem(HealingItem healingItem) {
		if (healingItem.getCost(hasDiscountHero()) > currentMoney) {
			throw new NotEnoughMoneyException("Not enough money to buy that healing item");
		} else {
			currentMoney -= healingItem.getCost(hasDiscountHero());
			healingItemsOwned.add(healingItem);
		}
	}
	
	/**
	 * If the team has enough money to buy a map, increments the number of maps owned,
	 * and decreases the team's current money by the appropriate amount.
	 * If the team doesn't have enough money, a NotEnoughMoneyException is thrown.
	 */
	public void buyMap() {
		if (Map.getCost(hasDiscountHero()) > currentMoney) {
			throw new NotEnoughMoneyException("Not enough money to buy a map");
		} else {
			currentMoney -= Map.getCost(hasDiscountHero());
			numMaps++;
		}
	}
	
	/**
	 * Takes a PowerUpType, and returns a PowerUp object of that type from the team's
	 * list of owned powerUps, removing it from the list.
	 * If the team doesn't own any power ups of that type, throws a RuntimeException.
	 * @param powerUpType	The type of the power up to be removed from the list.
	 */
	public PowerUp popPowerUpFromList(PowerUpType powerUpType) {
		boolean found = false;
		PowerUp powerUpToReturn = null;
		
		for (PowerUp powerUp : powerUpsOwned) {
			if (powerUp.getType() == powerUpType) {
				powerUpToReturn = powerUp;
				found = true;
			}
		}
		if (found) {
			powerUpsOwned.remove(powerUpToReturn);
			return powerUpToReturn;
		} else {
			throw new RuntimeException("The team doesn't own any power ups of that type");
		}
	}

	/**
	 * Takes the name of a healing item, and returns a healing item of that type
	 * from the team's list of owned healing items.
	 * If the team doesn't own any healing items of that type, raises a RuntimeException.
	 * @param name	The name of the healing item to be found
	 * @return		A HealingItem object with the given name from the teams list of
	 * 				owned healing items.
	 */
	public HealingItem healingItemOfGivenType(String name) {
		boolean found = false;
		HealingItem itemToReturn = null;
		
		for (HealingItem healingItem: healingItemsOwned) {
			if (healingItem.getName() == name) {
				found = true;
				itemToReturn = healingItem;
			}
		}
		
		if (found) {
			return itemToReturn;
		} else {
			throw new NoneOwnedException("No healing items with the given name owned.");
		}
	}
	
	/**
	 * Counts how many power-ups of the given type the team currently owns.
	 * @param powerUpType	The PowerUpType of interest.
	 * @return		The number of power-ups of the given type owned by the team.
	 */
	public int numPowerUpsOwned(PowerUpType powerUpType) {
		int count = 0;
		for (PowerUp powerUp: powerUpsOwned) {
			if (powerUp.getType() == powerUpType)
				count++;
		}
		return count;
	}
	
	/**
	 * Counts how many healing items with the given name the team currently owns.
	 * @param name	The name of the healing item of interest.
	 * @return		The number of healing items with the given name owned by the team.
	 */
	public int numHealingItemsOwned(String name) {
		int count = 0;
		for (HealingItem healingItem: healingItemsOwned) {
			if (healingItem.getName().equals(name))
				count++;
		}
		return count;
	}
	
	/**
	 * Checks if the team has an Explorer.
	 * @return true if the team has an Explorer, false otherwise.
	 */
	public boolean hasMapHero() {
		for (Hero hero: heroes) {
			if (hero.getHasMapAbility()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks if the team has a Merchant.
	 * @return true if the team has a Merchant, false otherwise.
	 */
	public boolean hasDiscountHero() {
		for (Hero hero : heroes) {
			if (hero.getHasStoreDiscount()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter method for heroes.
	 * @return The value of heroes.
	 */
	public ArrayList<Hero> getHeroes() {
		return heroes;
	}
	
	/**
	 * Getter method for numMaps.
	 * @return The value of numMaps.
	 */
	public int getNumMaps() {
		return numMaps;
	}

	/**
	 * Setter method for numMaps.
	 * @param numMaps The new value of numMaps to set.
	 */
	public void setNumMaps(int numMaps) {
		if (numMaps < 0) {
			throw new IllegalArgumentException("The number of maps can't be negative.");
		}
		this.numMaps = numMaps;
	}

	/**
	 * Getter method for currentMoney.
	 * @return The value of currentMoney.
	 */
	public int getCurrentMoney() {
		return currentMoney;
	}

	/**
	 * Setter method for currentMoney.
	 * @param currentMoney The new value of currentMoney to set.
	 */
	public void setCurrentMoney(int currentMoney) {
		this.currentMoney = currentMoney;
	}
	
	/**
	 * Getter method for healingItemsOwned.
	 * @return The value of healingItemsOwned.
	 */
	public ArrayList<HealingItem> getHealingItemsOwned() {
		return healingItemsOwned;
  }
    
	/** 
   * Getter method for powerUpsOwned.
	 * @return The value of powerUpsOwned.
	 */
	public ArrayList<PowerUp> getPowerUpsOwned() {
		return powerUpsOwned;
	}
}
