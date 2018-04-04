import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TeamTest {

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

}
