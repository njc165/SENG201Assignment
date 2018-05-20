import java.util.ArrayList;

/**
 * Instances of Team represent teams of heroes in the game.
 * Every Team object contains a number of associated Hero objects.
 * Each instance of GameEnvironment has an associated Team object.
 * A game is considered lost when the corresponding Team object
 * loses all of its heroes.
 */
public class Team {
	
	/**
	 * The minimum length of a valid team name.
	 */
	public static final int MIN_TEAM_NAME_LENGTH = 2;
	
	/**
	 * The maximum length of a valid team name.
	 */
	public static final int MAX_TEAM_NAME_LENGTH = 10;
	
	/**
	 * The amount of money a team starts with.
	 */
	private final int STARTING_MONEY = 50;
	
	/**
	 * All heroes currently on the team.
	 * This ArrayList is populated when a Team is created and depopulated as heroes die.
	 * The game is lost when this ArrayList becomes empty.
	 */
	private ArrayList<Hero> heroes = new ArrayList<Hero>();
	
	/**
	 * The number of heroes to be added to the team initially.
	 */
	private int startNumHeroes;
	
	/**
	 * A String representing the name of the team.
	 */
	private String name;
	
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
	 * An integer representing the number of maps the team owns.
	 * Can be any non-negative integer.
	 * Having an Explorer on the team does not affect this value.
	 */
	private int numMapsOwned;
	
	/**
	 * An integer representing the amount of money the team currently has.
	 * Could be any non-negative integer.
	 */
	private int currentMoney;
		
	/**
	 * A constructor for team, which sets the starting number of
	 * heroes which need to be added to the team.
	 * @param name				The team's name.
	 * @param startNumHeroes	The starting number of heroes of the team.
	 */
	public Team(String name, int startNumHeroes) {
		this.name = name;
		this.startNumHeroes = startNumHeroes;
		this.currentMoney = STARTING_MONEY;
	}
	
	/**
	 * Creates a new hero with the given name of the given type,
	 * and adds it to the team's list of heroes.
	 * @param heroName	The name of the new hero.
	 * @param type	The type of the new hero.
	 */
	public void addHero(String heroName, String type) {
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
	 * @param heroName   The name to be checked
	 * @return	true if the name is valid, false otherwise.
	 */
	public boolean isUniqueHeroName(String heroName) {
		boolean isValid  = true;
		for (Hero hero: heroes) {
			if (hero.getName().equals(heroName)) {
				isValid = false;
			}
		}
		return isValid;
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
		if (powerUp.getCost(numDiscountHeroes()) > currentMoney) {
			throw new NotEnoughMoneyException("Not enough money to buy that power up");
		} else {
			currentMoney -= powerUp.getCost(numDiscountHeroes());
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
		if (healingItem.getCost(numDiscountHeroes()) > currentMoney) {
			throw new NotEnoughMoneyException("Not enough money to buy that healing item");
		} else {
			currentMoney -= healingItem.getCost(numDiscountHeroes());
			healingItemsOwned.add(healingItem);
		}
	}
	
	/**
	 * If the team has enough money to buy a map, increments the number of maps owned,
	 * and decreases the team's current money by the appropriate amount.
	 * If the team doesn't have enough money, a NotEnoughMoneyException is thrown.
	 */
	public void buyMap() {
		if (Map.getCost(numDiscountHeroes()) > currentMoney) {
			throw new NotEnoughMoneyException("Not enough money to buy a map");
		} else {
			currentMoney -= Map.getCost(numDiscountHeroes());
			numMapsOwned++;
		}
	}
	
	/**
	 * Takes a PowerUpType, and returns a PowerUp object of that type from the team's
	 * list of owned powerUps, removing it from the list.
	 * If the team doesn't own any power ups of that type, throws a RuntimeException.
	 * @param powerUpType	The type of the power up to be removed from the list.
	 * @return A PowerUp object of the given type.
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
	 * If the team doesn't own any healing items of that type, raises a NoneOwnedException.
	 * @param name	The name of the healing item to be found
	 * @return		A HealingItem object with the given name from the teams list of
	 * 				owned healing items.
	 */
	public HealingItem healingItemOfGivenType(String name) {
		boolean found = false;
		HealingItem itemToReturn = null;
		
		for (HealingItem healingItem: healingItemsOwned) {
			if (healingItem.toString() == name) {
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
			if (healingItem.toString().equals(name))
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
	 * Returns the number of Merchants on the team.
	 * @return The number of Merchants on the team.
	 */
	public int numDiscountHeroes() {
		int numDiscountHeroes = 0;
		for (Hero hero : heroes) {
			if (hero.getHasStoreDiscount()) {
				numDiscountHeroes++;
			}
		}
		return numDiscountHeroes;
	}

	/**
	 * Getter method for name.
	 * @return The value of name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method for heroes.
	 * @return The value of heroes.
	 */
	public ArrayList<Hero> getHeroes() {
		return heroes;
	}
	
	/**
	 * Getter method for startNumHeroes.
	 * @return The value of startNumHeroes.
	 */
	public int getStartNumHeroes() {
		return startNumHeroes;
	}

	/**
	 * Getter method for numMaps.
	 * @return The value of numMaps.
	 */
	public int getNumMapsOwned() {
		return numMapsOwned;
	}

	/**
	 * Setter method for numMaps.
	 * @param numMaps The new value of numMaps to set.
	 */
	public void setNumMapsOwned(int numMaps) {
		if (numMaps < 0) {
			throw new IllegalArgumentException("The number of maps can't be negative.");
		}
		this.numMapsOwned = numMaps;
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
