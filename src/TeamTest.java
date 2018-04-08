import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamTest {

	@Test
	final void testAddHeroFromInput() {		
		
	}

	@Test
	final void testHasMapHero() {
		Team team = new Team("Team name");
		
		// Empty team doesn't have map hero
		assertFalse(team.hasMapHero());
		
		// Team without Explorer doesn't have map hero
		team.addHero(new Apprentice("Name"));
		team.addHero(new Merchant("Name"));
		assertFalse(team.hasMapHero());
		
		// Team with Explorer does have map hero
		team.addHero(new Explorer("Name"));
		assertTrue(team.hasMapHero());
		
		// Team with more than one Explorer does have map hero
		team.addHero(new Explorer("Name"));
		assertTrue(team.hasMapHero());
	}

	@Test
	final void testHasDiscountHero() {
		Team team = new Team("Team name");
		
		// Empty team doesn't have discount hero
		assertFalse(team.hasDiscountHero());
		
		// Team without Merchant doesn't have discount hero
		team.addHero(new Apprentice("Name"));
		team.addHero(new Explorer("Name"));
		assertFalse(team.hasDiscountHero());
		
		// Team with Merchant does have discount hero
		team.addHero(new Merchant("Name"));
		assertTrue(team.hasDiscountHero());
		
		// Team with more than one Merchant does have discount hero
		team.addHero(new Merchant("Name"));
		assertTrue(team.hasDiscountHero());
	}

	@Test
	final void testTakeDamage() {
		// A hero's health is reduced by the right amount
		Apprentice apprentice = new Apprentice("Name");
		int startHealth = apprentice.getCurrentHealth();
		
		Team team = new Team("Team name");
		team.addHero(apprentice);
		
		team.takeDamage(apprentice, 10);
		int finalHealth = apprentice.getCurrentHealth();
		
		assertEquals(10, startHealth - finalHealth);
		
		// If a hero's health drops to 0, the hero is removed from the team
		apprentice.setCurrentHealth(10);
		team.takeDamage(apprentice, 10);
		assertEquals(0, team.getNumHeroes());
		
		// If a hero's health drops below 0, the hero is removed from the team
		team.addHero(apprentice);
		apprentice.setCurrentHealth(10);
		team.takeDamage(apprentice, 20);
		assertEquals(0, team.getNumHeroes());
		
		// A hero with the reduced damage ability takes the right amount of damage
		Bulwark bulwark = new Bulwark("Name");
		startHealth = bulwark.getCurrentHealth();
		team.addHero(bulwark);
		
		team.takeDamage(bulwark, 20);
		finalHealth = bulwark.getCurrentHealth();
		assertEquals(20 * Hero.DAMAGE_REDUCTION_MULTIPLIER, startHealth - finalHealth);
		
		// A hero with the reduced damage ability is removed from the team if their
		// health drops below zero
		team.addHero(apprentice);
		bulwark.setCurrentHealth(10);
		team.takeDamage(bulwark, 20);
		assertEquals(1, team.getNumHeroes());
		
	}
	
	@Test
	final void testBuyPowerUp() {
		ExtraGuess powerUp = new ExtraGuess();
		Team team = new Team("Team name");
		team.setCurrentMoney(200);
		
		// The team has no power ups initially
		assertEquals(0, team.getPowerUpsOwned().size());
		
		// Buying a power up adds it to the team's inventory and decreases the
		// team's current money
		team.buyPowerUp(powerUp);
		assertEquals(200 - powerUp.getCost(false), team.getCurrentMoney());
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
		team.setCurrentMoney(mindReader.getCost(false));
		team.buyPowerUp(mindReader);
		assertEquals(0, team.getCurrentMoney());
		assertEquals(2, team.getPowerUpsOwned().size());		
	}

	
	@Test
	final void testBuyHealingItem() {
		AlicornDust alicornDust = new AlicornDust();
		Team team = new Team("Team name");
		team.setCurrentMoney(200);
		
		// The team has no healing items initially
		assertEquals(0, team.getHealingItemsOwned().size());
		
		// Buying a healing item adds it to the team's inventory and decreases the
		// team's current money
		team.buyHealingItem(alicornDust);
		assertEquals(200 - alicornDust.getCost(false), team.getCurrentMoney());
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
		team.setCurrentMoney(tonic.getCost(false));
		team.buyHealingItem(tonic);
		assertEquals(0, team.getCurrentMoney());
		assertEquals(2, team.getHealingItemsOwned().size());		
	}
	
	@Test
	final void testBuyMap() {
		Team team = new Team("Team name");
		team.setCurrentMoney(200);
		
		// The team has no maps initially
		assertEquals(0, team.getNumMaps());
		
		// Buying a map increases the teams number of maps owned and decreases the
		// team's current money
		team.buyMap();
		assertEquals(200 - Map.getCost(false), team.getCurrentMoney());
		assertEquals(1, team.getNumMaps());
		
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
		team.setCurrentMoney(Map.getCost(false));
		team.buyMap();
		assertEquals(0, team.getCurrentMoney());
		assertEquals(2, team.getNumMaps());		
	}
	
	@Test
	final void testPopPowerUpFromList() {
		Team team = new Team("Team name");
		
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
	final void testNumPowerUpsOwned() {
		Team team = new Team("Team name");
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
		Team team = new Team("Team name");
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
	final void testHealingItemOfGivenType() {
		Team team = new Team("Team name");
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
		
	
	
	
	
}
