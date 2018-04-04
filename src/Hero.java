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
	 * The name of the player assigned to this hero.
	 */
	private String name;
	
	/**
	 * A String description of the hero's type.
	 * Each subclass of Hero has a different type.
	 */
	private String type;
	
	/**
	 * A String description of the hero's special ability.
	 * Each subclass of Hero has a different special ability.
	 */
	private String specialAbility;
	
	/**
	 * A detailed String description of the hero's special ability and backstory, formatted so that it
	 * can be printed directly. Each subclass of Hero has a different description.
	 */
	private String description;
	
	/**
	 * The maximum health the hero can have.
	 * Each subclass of Hero has a different maximum health, between 50 and 120.
	 */
	private double maxHealth;
	
	/**
	 * The current health of the hero. Initialised to maxHealth when the hero is constructed.
	 * The value can range from 0 to maxHealth. If currentHealth reaches 0, the hero is dead and cannot recover.
	 */
	private double currentHealth;
	
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
	
//	/**
//	 * A list of the power-ups which are currently applied to the hero.
//	 * Each hero can have an unlimited number of power-ups of each type at any time.
//	 */
//	private ArrayList<PowerUp> activePowerUps = new ArrayList<PowerUp>;
	
//	/**
//	 * The healing item currently applied to the hero. Set to null if there is no healing item applied.
//	 * Each hero can up to one healing item applied at any time.
//	 */
//	private HealingItem appliedHealingItem;
	
	/**
	 * Constructor called from the constructors of each Hero subclass.
	 * Initialises the instance variables of the hero to the values defined in that subclass.
	 * @param name	The name of the player assigned to the hero.
	 * @param type	The type of the Hero subclass.
	 * @param specialAbility	The special ability of the Hero subclass.
	 * @param description	The String description of the Hero subclass.
	 * @param maxHealth	The maximum health of the Hero subclass.
	 */
	public Hero(String name, String type, String specialAbility, String description, double maxHealth) {
		this.name = name;
		this.type = type;
		this.specialAbility = specialAbility;
		this.description = description;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
	}
	
	public static String allHeroes() {
		String returnString = "";
		for (int i = 1; i <= ALL_HEROES.length; i++) {
			returnString = returnString + String.format("\n%d: %s:\nMax Health: %.0f\nSpecial Ability: %s\n%s", i, ALL_HEROES[i-1].getType(), ALL_HEROES[i-1].getMaxHealth(), ALL_HEROES[i-1].getSpecialAbility(), ALL_HEROES[i-1].getDescription());
		}
		return returnString;
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
	 * Setter method for name.
	 * @param name The new value of name to set.
	 */
	public void setName(String name) {
		this.name = name;
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
	public double getCurrentHealth() {
		return currentHealth;
	}
	
	/**
	 * Setter method for currentHealth.
	 * @param newHealth The new value of currentHealth to set.
	 */
	public void setCurrentHealth(double newHealth) {
		currentHealth = newHealth;
	}
	
	/**
	 * Getter method for maxHealth.
	 * @return The value of maxHealth.
	 */
	public double getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * Getter method for specialAbility.
	 * @return The value of specialAbility.
	 */
	public String getSpecialAbility() {
		return specialAbility;
	}
}
