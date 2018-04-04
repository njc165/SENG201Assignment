import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HeroTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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
		assertEquals(0, hero.getActivePowerUps().size());
		
		// Special ability boolean values are false
		assertFalse(hero.getHasStoreDiscount());
		assertFalse(hero.getHasBattleAdvantage());
		
		// Current health initialised to max health, and can be changed
		assertEquals(hero.getMaxHealth(), hero.getCurrentHealth());
		hero.setCurrentHealth(10);
		assertEquals(10, hero.getCurrentHealth());
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

	@Test
	final void testAllHerosDescription() {
		String description = Hero.allHeroesDescription();
		
		// description contains names of some heroes
		assertTrue(description.contains("Apprentice"));
		assertTrue(description.contains("Merchant"));
		assertTrue(description.contains("Gambler"));
		
		// description contains special abilities of some heroes
		assertTrue(description.contains(new Bulwark("").getSpecialAbility()));
		assertTrue(description.contains(new Mercenary("").getSpecialAbility()));
		assertTrue(description.contains(new Merchant("").getSpecialAbility()));
	}
	
	
	
}
