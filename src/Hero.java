import java.util.ArrayList;

public class Hero {
	
	/**
	 * An array of all subtypes of Hero.
	 */
	public static final Hero[] ALL_HEROES = {new Apprentice(""),
			  								 new Bulwark(""),
			  								 new Explorer(""),
			  								 new Gambler(""),
			  								 new Mercenary(""),
			  								 new Merchant("")};
	
	/**
	 * The damage reduction multiplier applied when a
	 * hero with the damage reduction ability takes damage.
	 */
	public static final double DAMAGE_REDUCTION_MULTIPLIER = 0.75;
	
	/**
	 * The multiplier applied to the application time of a healing item
	 * when it is applied to a hero with the faster healing special ability.
	 */
	public static final double FASTER_HEALING_MULTIPLIER = 0.5;
	
	/**
	 * The name of the player assigned to this hero.
	 */
	private String name;
	
	/**
	 * A String representation of the hero's type, e.g. "Gambler".
	 * Each subclass of Hero has a different type.
	 */
	private String type;
	
	/**
	 * A String description of the hero's special ability,
	 * e.g. "Advantages in villain battles".
	 * Each subclass of Hero has a different special ability.
	 */
	private String specialAbility;
	
	/**
	 * A detailed String description of the hero's special ability and backstory,
	 * formatted so that it can be printed directly.
	 * Each subclass of Hero has a different description.
	 */
	private String description;
	
	/**
	 * The maximum health the hero can have.
	 * Each subclass of Hero has a different maximum health, between 50 and 120.
	 */
	private int maxHealth;
	
	/**
	 * The current health of the hero. Initialised to maxHealth when the hero is constructed.
	 * The value can range from 0 to maxHealth.
	 * If currentHealth reaches 0, the hero is dead and cannot recover.
	 */
	private int currentHealth;
	
	/**
	 * Set to true if hero has the special ability "20% store discount".
	 */
	private boolean hasStoreDiscount;
	
	/**
	 * Set to true if the hero has the special ability "Advantages in villain battles".
	 */
	private boolean hasBattleAdvantage;
	
	/**
	 * Set to true if the hero has the special ability "Double damage to villains".
	 */
	private boolean hasDoubleDamage;
	
	/**
	 * Set to true if the hero has the special ability "Cities start with all sectors revealed".
	 */
	private boolean hasMapAbility;
	
	/**
	 * Set to true if the hero has the special ability "25% damage reduction".
	 */
	private boolean hasReducedDamage;
	
	/**
	 * Set to true if hero has the special ability "Healing items apply twice as fast".
	 */
	private boolean hasFasterHealing;
	
	/**
	 * A list of the power-ups which are currently applied to the hero, initialised empty.
	 * Each hero can have an unlimited number of power-ups of each type at any time.
	 */
	private ArrayList<PowerUp> activePowerUps = new ArrayList<PowerUp>();
	
	/**
	 * The healing item currently applied to the hero. Set to null if there is no
	healing item applied.
	 * Each hero can have at most one healing item applied at any time.
	 */
	private HealingItem appliedHealingItem;
	
	/**
	 * Constructor called from the constructors of each Hero subclass.
	 * Initialises the instance variables of the hero to the values defined in that
	 * subclass.
	 * @param name	The name of the player assigned to the hero.
	 * @param type	The type of the Hero subclass.
	 * @param specialAbility	The special ability of the Hero subclass.
	 * @param description	The String description of the Hero subclass.
	 * @param maxHealth	The maximum health of the Hero subclass.
	 */
	public Hero(String name, String type, String specialAbility, String description, int maxHealth) {
		this.name = name;
		this.type = type;
		this.specialAbility = specialAbility;
		this.description = description;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
	}
	
	/**
	 * Returns a string description of all the hero subclasses.
	 * Used to give the user information when asking them to choose their hero type,
	 * and includes a number before each hero to allow the user to enter their choice.
	 * Formatted with newline characters to allow it to be printed directly.
	 * Each hero's description includes their type, special ability, maximum health and description.
	 * @return	A description of all the hero subclasses.
	 */
	public static String allHeroesDescription() {
		String returnString = "";
		for (int i = 0; i < ALL_HEROES.length; i++) {
			
			Hero hero = ALL_HEROES[i];
			int heroNumber = i + 1;
			
			returnString += String.format("\n%d: %s\nMax Health: %s\nSpecial Ability: %s\n%s",
					heroNumber,
					hero.getType(),
					hero.getMaxHealth(),
					hero.getSpecialAbility(),
					hero.getDescription());
		}
		return returnString;
	}
	
	/**
	 * If the hero has a currently applied healing item, updates the healing item,
	 * increasing the hero's current health if necessary.
	 * If the healing item has no increments left, the hero's applied healing item is
	 * set to null.
	 */
	public void heal() {
		if (appliedHealingItem != null) {
			
			if (appliedHealingItem.readyToIncrement()) {
				int newHealth = (int) (currentHealth +
										maxHealth * HealingItem.INCREMENT_SIZE);
				currentHealth = Integer.min(maxHealth, newHealth);
				
				appliedHealingItem.applyIncrement();
				
				if (appliedHealingItem.getIncrementsRemaining() <= 0)
					appliedHealingItem = null;
			}
		}
	}
	
	/**
	 * Counts how many power-ups of the given type the hero currently has
	 * in its list of active power-ups.
	 * @param powerUpType	The PowerUpType of interest.
	 * @return		The number of power-ups of the given type posessed by the hero.
	 */
	public int numPowerUps(PowerUpType powerUpType) {
		int count = 0;
		for (PowerUp powerUp: activePowerUps) {
			if (powerUp.getType() == powerUpType)
				count++;
		}
		return count;
	}
	
	/**
	 * Adds the given power-up to the hero's list of active power-ups
	 * @param powerUp	The power-up to be added.
	 */
	public void addPowerUp(PowerUp powerUp) {
		activePowerUps.add(powerUp);
	}

	/**
	 * Getter method for hasStoreDiscount.
	 * @return The value of hasStoreDiscount.
	 */
	public boolean getHasStoreDiscount() {
		return hasStoreDiscount;
	}

	/**
	 * Setter method for hasStoreDiscount.
	 * @param hasStoreDiscount The new value of hasStoreDiscount to set.
	 */
	public void setHasStoreDiscount(boolean hasStoreDiscount) {
		this.hasStoreDiscount = hasStoreDiscount;
	}

	/**
	 * Getter method for hasBattleAdvantage.
	 * @return The value of hasBattleAdvantage.
	 */
	public boolean getHasBattleAdvantage() {
		return hasBattleAdvantage;
	}

	/**
	 * Setter method for hasBattleAdvantage.
	 * @param hasBattleAdvantage The new value of hasBattleAdvantage to set.
	 */
	public void setHasBattleAdvantage(boolean hasBattleAdvantage) {
		this.hasBattleAdvantage = hasBattleAdvantage;
	}

	/**
	 * Getter method for hasDoubleDamage.
	 * @return The value of hasDoubleDamage.
	 */
	public boolean getHasDoubleDamage() {
		return hasDoubleDamage;
	}

	/**
	 * Setter method for hasDoubleDamage.
	 * @param hasDoubleDamage The new value of hasDoubleDamage to set.
	 */
	public void setHasDoubleDamage(boolean hasDoubleDamage) {
		this.hasDoubleDamage = hasDoubleDamage;
	}

	/**
	 * Getter method for hasMapAbility.
	 * @return The value of hasMapAbility.
	 */
	public boolean getHasMapAbility() {
		return hasMapAbility;
	}

	/**
	 * Setter method for hasMapAbility.
	 * @param hasMapAbility The new value of hasMapAbility to set.
	 */
	public void setHasMapAbility(boolean hasMapAbility) {
		this.hasMapAbility = hasMapAbility;
	}

	/**
	 * Getter method for hasReducedDamage.
	 * @return The value of hasReducedDamage.
	 */
	public boolean getHasReducedDamage() {
		return hasReducedDamage;
	}

	/**
	 * Setter method for hasReducedDamage.
	 * @param hasReducedDamage The new value of hasReducedDamage to set.
	 */
	public void setHasReducedDamage(boolean hasReducedDamage) {
		this.hasReducedDamage = hasReducedDamage;
	}

	/**
	 * Getter method for hasFasterHealing.
	 * @return The value of hasFasterHealing.
	 */
	public boolean getHasFasterHealing() {
		return hasFasterHealing;
	}

	/**
	 * Setter method for hasFasterHealing.
	 * @param hasFasterHealing The new value of hasFasterHealing to set.
	 */
	public void setHasFasterHealing(boolean hasFasterHealing) {
		this.hasFasterHealing = hasFasterHealing;
	}
	
	/**
	 * Getter method for name.
	 * @return The value of name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for type.
	 * @return The value of type.
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Getter method for description.
	 * @return The value of description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Getter method for currentHealth.
	 * @return The value of currentHealth.
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}
	
	/**
	 * Setter method for currentHealth.
	 * Ensures that the new currentHealth is not greater than maxHealth.
	 * @param newHealth The new value of currentHealth to set.
	 */
	public void setCurrentHealth(int newHealth) {
		currentHealth = Integer.min(newHealth, maxHealth);
	}
	
	/**
	 * Getter method for maxHealth.
	 * @return The value of maxHealth.
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Getter method for specialAbility.
	 * @return The value of specialAbility.
	 */
	public String getSpecialAbility() {
		return specialAbility;
	}

	/**
	 * Getter method for activePowerUps.
	 * @return The value of activePowerUps.
	 */
	public ArrayList<PowerUp> getActivePowerUps() {
		return activePowerUps;
	}

	/**
	 * Setter method for activePowerUps.
	 * @param activePowerUps The new value of activePowerUps to set.
	 */
	public void setActivePowerUps(ArrayList<PowerUp> activePowerUps) {
		this.activePowerUps = activePowerUps;
	}
	
	public String toString() {
		return String.format("%s the %s", name, type);
	}

	/**
	 * Getter method for appliedHealingItem.
	 * @return The value of appliedHealingItem.
	 */
	public HealingItem getAppliedHealingItem() {
		return appliedHealingItem;
	}

	/**
	 * Setter method for appliedHealingItem.
	 * Calls healingItem.applyToHero() on the given healing item.
	 * @param appliedHealingItem The new value of appliedHealingItem to set.
	 */
	public void setAppliedHealingItem(HealingItem appliedHealingItem) {
		this.appliedHealingItem = appliedHealingItem;
		appliedHealingItem.applyToHero(hasFasterHealing);
	}
}
