import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

class HeroTest {
	
	@Test
	final void testHero() {
		// Constructor sets the appropriate attributes
		Hero hero = new Hero("Name", "Type", "Special ability", "Description", 100);
		
		// Getters and setters work
		assertEquals("Name", hero.getName());
		assertEquals("Type", hero.getType());
		assertEquals("Special ability", hero.getSpecialAbility());
		assertEquals("Description", hero.getDescription());
		assertEquals(100, hero.getMaxHealth());
		
		// New hero has empty activePowerUps list
		assertEquals(0, hero.getAppliedPowerUps().size());
		
		// Special ability boolean values are false
		assertFalse(hero.getHasStoreDiscount());
		assertFalse(hero.getHasBattleAdvantage());
		
		// Current health initialised to max health, and can be changed
		assertEquals(hero.getMaxHealth(), hero.getCurrentHealth());
		hero.setCurrentHealth(10);
		assertEquals(10, hero.getCurrentHealth());
	}
	
	@Test
	final void testToString() {
		Hero hero = new Apprentice("John");
		assertEquals("John the Apprentice", hero.toString());
	}
	
	@Test
	final void testAllHeroTypes() {
		String[] allTypes = Hero.allHeroTypes();
		String[] expected = {"Apprentice", "Bulwark", "Explorer", "Gambler", "Mercenary", "Merchant"};
		
		Collections.sort(Arrays.asList(allTypes));
		Collections.sort(Arrays.asList(expected));
		
		assertArrayEquals(expected, allTypes);
	}
	
	@Test
	final void testPowerUpTypeCounts() {
		Hero hero = new Apprentice("Steve");
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new MindReader());
		hero.addPowerUp(new ExtraGuess());
		
		// Check that powerUpTypeCounts counts correctly
		HashMap<PowerUpType, Integer> powerUpTypeCounts = hero.powerUpTypeCounts();
		assertEquals(2, (int) powerUpTypeCounts.get(PowerUpType.TIEBREAKER));
		assertEquals(1, (int) powerUpTypeCounts.get(PowerUpType.MINDREADER));
		assertEquals(1, (int) powerUpTypeCounts.get(PowerUpType.EXTRA_GUESS));
		assertNull(powerUpTypeCounts.get(PowerUpType.INCREASE_ROLL));		
	}
	
	@Test
	final void testHeal() {
		// Hero without healsFaster ability is healed correctly
		Merchant hero = new Merchant("Name"); // Merchant maxHealth is 100
		hero.setCurrentHealth(60);
		
		int numIncrements = 2;
		int timePerIncrement = 0;
		HealingItem healingItem = new HealingItem("Name", "Description",
											numIncrements, timePerIncrement, 100);
		hero.setAppliedHealingItem(healingItem);
		
		// Wait 1s to make sure healing item is ready to apply
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		hero.heal();
		
		// Healing item's number of increments remaining has decreased by one
		assertEquals(1, hero.getAppliedHealingItem().getIncrementsRemaining());
		
		// Hero's health has increased by the correct amount
		assertEquals(85, hero.getCurrentHealth());
		
		// Healing the hero again doesn't increase their health beyond maxHealth
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertTrue(hero.getAppliedHealingItem().readyToIncrement());
		hero.heal();
		assertEquals(100, hero.getCurrentHealth());
		
		// After being incremented numIncrements times, appliedHealingItem has been
		// set to null
		assertNull(hero.getAppliedHealingItem());
		
		// Check that heal a hero with no applied healing item has no effect
		int initialHealth = hero.getCurrentHealth();
		hero.heal();
		assertEquals(initialHealth, hero.getCurrentHealth());
		assertNull(hero.getAppliedHealingItem());
		
		// If the healing item is not ready to increment, there is no change
		numIncrements = 2;
		timePerIncrement = 100;
		healingItem = new HealingItem("Name", "Description",
											numIncrements, timePerIncrement, 100);
		hero.setAppliedHealingItem(healingItem);
		hero.setCurrentHealth(50);
		hero.heal();
		assertEquals(50, hero.getCurrentHealth());
		assertEquals(2, hero.getAppliedHealingItem().getIncrementsRemaining());
	}
	
	@Test
	final void testNumPowerUps() {
		// Newly created hero has no power-ups
		Hero hero = new Hero("Name", "Type", "Special ability", "Description", 100);
		assertEquals(0, hero.numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(0, hero.numPowerUps(PowerUpType.TIEBREAKER));
		assertEquals(0, hero.numPowerUps(PowerUpType.INCREASE_ROLL));
		assertEquals(0, hero.numPowerUps(PowerUpType.MINDREADER));
		
		// Adding power-up increases the number of that power-up, but not the others
		hero.addPowerUp(new IncreaseRoll());
		assertEquals(0, hero.numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(0, hero.numPowerUps(PowerUpType.TIEBREAKER));
		assertEquals(1, hero.numPowerUps(PowerUpType.INCREASE_ROLL));
		assertEquals(0, hero.numPowerUps(PowerUpType.MINDREADER));
		
		// Adding multiple power-ups returns the appropriate values
		hero.addPowerUp(new TieBreaker());
		hero.addPowerUp(new IncreaseRoll());
		hero.addPowerUp(new MindReader());
		hero.addPowerUp(new TieBreaker());
		assertEquals(0, hero.numPowerUps(PowerUpType.EXTRA_GUESS));
		assertEquals(2, hero.numPowerUps(PowerUpType.TIEBREAKER));
		assertEquals(2, hero.numPowerUps(PowerUpType.INCREASE_ROLL));
		assertEquals(1, hero.numPowerUps(PowerUpType.MINDREADER));
	}
		

}
