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
	//private ArrayList<HealingItem> healingItemsOwned = new ArrayList<HealingItem>();
	
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
	 * Adds the given Hero object to the teams list of heroes.
	 * @param hero		The hero to be added.
	 */
	public void addHero(Hero hero) {
		heroes.add(hero);
	}

	/**
	 * Add heroes to the team after the team is constructed.
	 * Asks the user for a hero name and type, disallows names
	 * that have already been taken.
	 */
	public void addHeroFromInput() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a name for your Hero:");
		
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
		
		System.out.println(String.format("\nEnter a number to select a hero type for %s:",
				heroName));
		System.out.println(Hero.allHeroesDescription());
		
		int choice = Util.getIntFromUser(Hero.ALL_HEROES.length, "Enter your choice:");
		int heroIndex = choice - 1;
		
		// Class<? extends Hero> type = ALL_HEROES[choice].getClass();
		// heroes.add(new type(heroName))
		
		// Something like this can be done with 'reflection', whatever that is.
		// If we can figure out how to use it, it will be fewer lines of code than
		// the switch statement and will not break if we add more hero types.
		
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
	 * Getter method for heroes.
	 * @return The value of heroes.
	 */
	public ArrayList<Hero> getHeroes() {
		return heroes;
	}

	/**
	 * Getter method for the current number of heroes on the team.
	 * @return	The number of heroes currently on the team.
	 */
	public int getNumHeroes() {
		return heroes.size();
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
	
	public static void main(String[] args) {
		Team team = new Team("TEAM");
	}
	
}
