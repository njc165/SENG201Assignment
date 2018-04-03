public class Hero {
	
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
	
	public Hero(String name, String type, String specialAbility, String description, double maxHealth) {
		this.name = name;
		this.type = type;
		this.specialAbility = specialAbility;
		this.description = description;
		this.maxHealth = maxHealth;
		this.currentHealth = maxHealth;
	}
	
}
