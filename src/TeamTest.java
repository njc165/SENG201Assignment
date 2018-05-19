import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TeamTest {
	
	@Test
	final void testAddHero() {
		Team team = new Team("Team name", 3);
		
		team.addHero("Name", "Apprentice");
		Hero hero = team.getHeroes().get(0);
		assertEquals("Name", hero.getName());
		assertTrue(hero instanceof Apprentice);
		
		team.addHero("Name", "Merchant");
		hero = team.getHeroes().get(1);
		assertEquals("Name", hero.getName());
		assertTrue(hero instanceof Merchant);
	}
	
	@Test
	final void testIsValidHeroName() {
		Team team = new Team("", 4);
		
		// When the team is empty, any name is valid
		assertTrue(team.isUniqueHeroName("John"));
		
		// A name which is already taken by another hero on the team
		// is not valid.
		team.addHero("John", "Bulwark");
		assertFalse(team.isUniqueHeroName("John"));
		assertTrue(team.isUniqueHeroName("Jack"));
		
		team.addHero("Jack", "Explorer");
		assertFalse(team.isUniqueHeroName("John"));
		assertFalse(team.isUniqueHeroName("Jack"));
	}
	
	@Test
	final void testTakeDamage() {
		// A hero's health is reduced by the right amount
		Apprentice apprentice = new Apprentice("Name");
		int startHealth = apprentice.getCurrentHealth();
		
		Team team = new Team("Team name", 4);
		team.getHeroes().add(apprentice);
		
		team.takeDamage(apprentice, 10);
		int finalHealth = apprentice.getCurrentHealth();
		
		assertEquals(10, startHealth - finalHealth);
		
		// If a hero's health drops to 0, the hero is removed from the team
		apprentice.setCurrentHealth(10);
		team.takeDamage(apprentice, 10);
		assertEquals(0, team.getHeroes().size());
		
		// If a hero's health drops below 0, the hero is removed from the team
		team.getHeroes().add(apprentice);
		apprentice.setCurrentHealth(10);
		team.takeDamage(apprentice, 20);
		assertEquals(0, team.getHeroes().size());
		
		// A hero with the reduced damage ability takes the right amount of damage
		Bulwark bulwark = new Bulwark("Name");
		startHealth = bulwark.getCurrentHealth();
		team.getHeroes().add(bulwark);
		
		team.takeDamage(bulwark, 20);
		finalHealth = bulwark.getCurrentHealth();
		assertEquals(20 * Hero.DAMAGE_REDUCTION_MULTIPLIER, startHealth - finalHealth);
		
		// A hero with the reduced damage ability is removed from the team if their
		// health drops below zero
		team.getHeroes().add(apprentice);
		bulwark.setCurrentHealth(10);
		team.takeDamage(bulwark, 20);
		assertEquals(1, team.getHeroes().size());
	}
	
	@Test
	final void testBuyPowerUp() {
		ExtraGuess powerUp = new ExtraGuess();
		Team team = new Team("Team name", 4);
		team.setCurrentMoney(200);
		
		// The team has no power ups initially
		assertEquals(0, team.getPowerUpsOwned().size());
		
		// Buying a power up adds it to the team's inventory and decreases the
		// team's current money
		team.buyPowerUp(powerUp);
		assertEquals(200 - powerUp.getCost(0), team.getCurrentMoney());
		assertEquals(1, team.getPowerUpsOwned().size());
		
		// If a team doesn't have enough money to buy the power up,
		// a NotEnoughMoneyException is thrown
		team.setCurrentMoney(10);
		boolean exceptionThrown = false;
		try {
			team.buyPowerUp(new TieBreaker());
		} catch (NotEnoughMoneyException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		// If a team has exactly enough money to buy a power up,
		// the power up is bought, and the team's current money ends up 0
		MindReader mindReader = new MindReader();
		team.setCurrentMoney(mindReader.getCost(0));
		team.buyPowerUp(mindReader);
		assertEquals(0, team.getCurrentMoney());
		assertEquals(2, team.getPowerUpsOwned().size());		
	}

	
	@Test
	final void testBuyHealingItem() {
		AlicornDust alicornDust = new AlicornDust();
		Team team = new Team("Team name", 4);
		team.setCurrentMoney(200);
		
		// The team has no healing items initially
		assertEquals(0, team.getHealingItemsOwned().size());
		
		// Buying a healing item adds it to the team's inventory and decreases the
		// team's current money
		team.buyHealingItem(alicornDust);
		assertEquals(200 - alicornDust.getCost(0), team.getCurrentMoney());
		assertEquals(1, team.getHealingItemsOwned().size());
		
		// If a team doesn't have enough money to buy the healing item,
		// a NotEnoughMoneyException is thrown
		team.setCurrentMoney(10);
		boolean exceptionThrown = false;
		try {
			team.buyHealingItem(new HeartyMeal());
		} catch (NotEnoughMoneyException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		// If a team has exactly enough money to buy a healing item,
		// the healing item is bought, and the team's current money ends up 0
		SuspiciousTonic tonic = new SuspiciousTonic();
		team.setCurrentMoney(tonic.getCost(0));
		team.buyHealingItem(tonic);
		assertEquals(0, team.getCurrentMoney());
		assertEquals(2, team.getHealingItemsOwned().size());		
	}
	
	@Test
	final void testBuyMap() {
		Team team = new Team("Team name", 4);
		team.setCurrentMoney(200);
		
		// The team has no maps initially
		assertEquals(0, team.getNumMapsOwned());
		
		// Buying a map increases the teams number of maps owned and decreases the
		// team's current money
		team.buyMap();
		assertEquals(200 - Map.getCost(0), team.getCurrentMoney());
		assertEquals(1, team.getNumMapsOwned());
		
		// If a team doesn't have enough money to buy a map,
		// a NotEnoughMoneyException is thrown
		team.setCurrentMoney(5);
		boolean exceptionThrown = false;
		try {
			team.buyMap();
		} catch (NotEnoughMoneyException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		// If a team has exactly enough money to buy a map,
		// the map is bought, and the team's current money ends up 0
		team.setCurrentMoney(Map.getCost(0));
		team.buyMap();
		assertEquals(0, team.getCurrentMoney());
		assertEquals(2, team.getNumMapsOwned());		
	}
	
	@Test
	final void testPopPowerUpFromList() {
		Team team = new Team("Team name", 4);
		
		// If the team has one power up of that type, it is removed from the list
		// and returned
		TieBreaker tieBreaker = new TieBreaker();
		team.getPowerUpsOwned().add(tieBreaker);
		assertEquals(1, team.getPowerUpsOwned().size());
		PowerUp poppedPowerUp = team.popPowerUpFromList(PowerUpType.TIEBREAKER);
		
		// The popped power up is the same object as the initial power up
		assertTrue(tieBreaker == poppedPowerUp);
		// The power up has been removed from the team's list of owned power ups
		assertEquals(0, team.getPowerUpsOwned().size());
		
		// If the team owns several power ups of one type, only one is removed
		team.getPowerUpsOwned().add(new MindReader());
		team.getPowerUpsOwned().add(new MindReader());
		team.getPowerUpsOwned().add(new MindReader());
		team.getPowerUpsOwned().add(new MindReader());

		poppedPowerUp = team.popPowerUpFromList(PowerUpType.MINDREADER);
		assertEquals(3, team.getPowerUpsOwned().size());
		
		// If the team doesn't own any power ups of that type, a RuntimeException
		// is thrown
		boolean exceptionThrown = false;
		try {
			team.popPowerUpFromList(PowerUpType.EXTRA_GUESS);
		} catch (RuntimeException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		// No power ups were removed from the list
		assertEquals(3, team.getPowerUpsOwned().size());		
	}
	
	@Test
	final void testHealingItemOfGivenType() {
		Team team = new Team("Team name", 4);
		AlicornDust alicornDust = new AlicornDust();
		
		team.getHealingItemsOwned().add(alicornDust);
		HealingItem returnedItem = team.healingItemOfGivenType("Alicorn Dust");
		
		// The returned item is from the team's list of owned healing items
		assertTrue(returnedItem == alicornDust);
		
		// The team's list of owned healing items is unchanged
		assertEquals(1, team.getHealingItemsOwned().size());
		assertTrue(team.getHealingItemsOwned().contains(alicornDust));
		
		// Exception is thrown if no healing items of the given type are owned
		boolean exceptionThrown = false;
		try {
			returnedItem = team.healingItemOfGivenType("Hearty Meal");
		} catch (NoneOwnedException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
		
		// The team's list of owned healing items is unchanged
		assertEquals(1, team.getHealingItemsOwned().size());
		assertTrue(team.getHealingItemsOwned().contains(alicornDust));
	}
	
	@Test
	final void testNumPowerUpsOwned() {
		Team team = new Team("Team name", 4);
		team.getPowerUpsOwned().add(new ExtraGuess());
		team.getPowerUpsOwned().add(new MindReader());
		team.getPowerUpsOwned().add(new TieBreaker());
		team.getPowerUpsOwned().add(new MindReader());
		
		assertEquals(0, team.numPowerUpsOwned(PowerUpType.INCREASE_ROLL));
		assertEquals(1, team.numPowerUpsOwned(PowerUpType.EXTRA_GUESS));
		assertEquals(1, team.numPowerUpsOwned(PowerUpType.TIEBREAKER));
		assertEquals(2, team.numPowerUpsOwned(PowerUpType.MINDREADER));
	}
	
	@Test
	final void testNumHealingItemsOwned() {
		Team team = new Team("Team name", 4);
		team.getHealingItemsOwned().add(new AlicornDust());
		team.getHealingItemsOwned().add(new AlicornDust());
		team.getHealingItemsOwned().add(new HeartyMeal());
		team.getHealingItemsOwned().add(new HeartyMeal());
		team.getHealingItemsOwned().add(new AlicornDust());

		assertEquals(0, team.numHealingItemsOwned("Suspicious Tonic"));
		assertEquals(2, team.numHealingItemsOwned("Hearty Meal"));
		assertEquals(3, team.numHealingItemsOwned("Alicorn Dust"));
	}

	@Test
	final void testHasMapHero() {
		Team team = new Team("Team name", 4);
		
		// Empty team doesn't have map hero
		assertFalse(team.hasMapHero());
		
		// Team without Explorer doesn't have map hero
		team.getHeroes().add(new Apprentice("Name"));
		team.getHeroes().add(new Merchant("Name"));
		assertFalse(team.hasMapHero());
		
		// Team with Explorer does have map hero
		team.getHeroes().add(new Explorer("Name"));
		assertTrue(team.hasMapHero());
		
		// Team with more than one Explorer does have map hero
		team.getHeroes().add(new Explorer("Name"));
		assertTrue(team.hasMapHero());
	}

	@Test
	final void testNumDiscountHeroes() {
		Team team = new Team("Team name", 4);
		
		// Empty team doesn't have discount hero
		assertEquals(0, team.numDiscountHeroes());
		
		// Team without Merchant doesn't have discount hero
		team.getHeroes().add(new Apprentice("Name"));
		team.getHeroes().add(new Explorer("Name"));
		assertEquals(0, team.numDiscountHeroes());
		
		// Team with Merchant has one discount hero
		team.getHeroes().add(new Merchant("Name"));
		assertEquals(1, team.numDiscountHeroes());
		
		// Team with two Merchants has two discount heroes
		team.getHeroes().add(new Merchant("Name"));
		assertEquals(2, team.numDiscountHeroes());
	}
		
	@Test
	final void testSetNumMaps() {
		Team team = new Team("Team name", 4);
		
		boolean exceptionThrown = false;
		try {
			team.setNumMapsOwned(-1);
		} catch (IllegalArgumentException e) {
			exceptionThrown = true;
		}
		
		// Check that an exception is thrown for invalid arguments.
		assertTrue(exceptionThrown);
		
		// Check that setter method works correctly.
		team.setNumMapsOwned(16);
		assertEquals(16, team.getNumMapsOwned());
	}
	
}
