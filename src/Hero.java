import java.util.ArrayList;
import java.util.HashMap;

/**
 * Instances of Hero represent heroes on a team. Each Hero object
 * represents one hero in the game. Instances of Hero should only
 * be created via its children.
 */
public class Hero {
	
	/**
	 * The minimum length a valid hero name can have.
	 */
	public static final int MIN_HERO_NAME_LENGTH = 2;
	
	/**
	 * The maximum length a valid hero name can have.
	 */
	public static final int MAX_HERO_NAME_LENGTH = 7;
	
	/**
	 * An array of instances of each Hero subclass.
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
	 * The multiplier to be applied to all items in the shop if the team has
	 * a hero with the store discount special ability.
	 */
	public static final double STORE_DISCOUNT_MULTIPLIER = 0.8;
	
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
	 * The current health of the hero.
	 * Initialised to maxHealth when the hero is constructed.
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
	private ArrayList<PowerUp> appliedPowerUps = new ArrayList<PowerUp>();
	
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
	 * Returns an array containing a String representation of the type
	 * of each Hero subclass.
	 * Used by the combo box which asks the user to select a hero type.
	 * @return	A String array of the types of all Hero subclasses.
	 */
	public static String[] allHeroTypes() {
		String[] types = new String[ALL_HEROES.length];
		for (int i = 0; i < ALL_HEROES.length; i++) {
			types[i] = ALL_HEROES[i].getType();
		}
		return types;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return String.format("%s the %s", name, type);
	}
	
	/**
	 * Creates a HashMap mapping each power up type to the number
	 * of that power up applied to the hero. If no power ups of a type
	 * are applied to the hero, then that type does not appear in the
	 * HashMap.
	 * @return	A HashMap mapping each power up type to the number applied
	 * 			to the hero.
	 */
	public HashMap<PowerUpType, Integer> powerUpTypeCounts() {
		HashMap<PowerUpType, Integer> powerUpTypeCounts = new HashMap<PowerUpType, Integer>();
		for (PowerUpType powerUpType: PowerUpType.values()) {
			int count = numPowerUps(powerUpType);
			if (count > 0)
				powerUpTypeCounts.put(powerUpType, count);
		}
		return powerUpTypeCounts;
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
				int newHealth = (int) (currentHealth
										+ maxHealth * HealingItem.INCREMENT_SIZE);
				currentHealth = Integer.min(maxHealth, newHealth);
				
				if (appliedHealingItem.getIncrementsRemaining() <= 0) {
					appliedHealingItem = null;
					return;
				}
				
				appliedHealingItem.applyIncrement();
								
				if (appliedHealingItem.getIncrementsRemaining() <= 0) {
					appliedHealingItem = null;
				}
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
		for (PowerUp powerUp: appliedPowerUps) {
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
		appliedPowerUps.add(powerUp);
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
	public ArrayList<PowerUp> getAppliedPowerUps() {
		return appliedPowerUps;
	}

	/**
	 * Setter method for activePowerUps.
	 * @param activePowerUps The new value of activePowerUps to set.
	 */
	public void setAppliedPowerUps(ArrayList<PowerUp> activePowerUps) {
		this.appliedPowerUps = activePowerUps;
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
