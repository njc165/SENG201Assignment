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
	final void testTeam() {
	}

	@Test
	final void testAddHero() {
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
	}

}
