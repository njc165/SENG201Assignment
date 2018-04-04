import java.util.ArrayList;

public class Hero {
	
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
	
	/**
	 * A list of the power-ups which are currently applied to the hero, initialised empty.
	 * Each hero can have an unlimited number of power-ups of each type at any time.
	 */
	private ArrayList<PowerUp> activePowerUps = new ArrayList<PowerUp>();
	
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
	
}
