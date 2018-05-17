import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameEnvironmentTest {

	@Test
	final void testGameEnvironment() {
		String teamName = "Team name";
		int numHeroes = 3;
		int numCities = 4;
		GameEnvironment game = new GameEnvironment(teamName, numHeroes, numCities);
		
		// A new team is created with the given name and no heroes initially
		Team team = game.getTeam();
		assertEquals(teamName, team.getName());
		assertEquals(0, team.getHeroes().size());
		
		// Prize money is set correctly
		assertEquals(100, game.getPrizeMoney());
	
		// The team's startNumHeroes is set to the correct value
		assertEquals(3, team.getStartNumHeroes());
		
		// If number of heroes or number of cities is less than 1,
		// an exception is thrown.
		boolean exceptionCaught = false;
		numHeroes = 0;
		try {
			game = new GameEnvironment(teamName, numHeroes, numCities);
		} catch (RuntimeException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		
		numHeroes = 2;
		numCities = 0;
		exceptionCaught = false;
		try {
			game = new GameEnvironment(teamName, numHeroes, numCities);
		} catch (RuntimeException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		
		
	}

	@Test
	final void testEnterFirstCity() {
		String teamName = "Team name";
		int numHeroes = 3;
		int numCities = 4;
		GameEnvironment game = new GameEnvironment(teamName, numHeroes, numCities);
		
		// currentCityIndex is set to 0.
		game.enterFirstCity();
		assertEquals(0, game.getCurrentCityIndex());
		
		// The current city can be accessed
		City city = game.currentCity();
		
		// The first city contains a villain that is not the super villain
		assertFalse(city.getVillain() instanceof Invictus);
		
		// If the team doesn't have an Explorer, the city is not initially discovered
		city.setCurrentLocation(Location.NORTH);
		assertFalse(city.getCurrentSector().getDiscovered());
		
		city.setCurrentLocation(Location.EAST);
		assertFalse(city.getCurrentSector().getDiscovered());
		
		// If the team has an Explorer, the first city is completely discovered
		game = new GameEnvironment(teamName, numHeroes, numCities);
		game.getTeam().getHeroes().add(new Explorer(""));
		game.enterFirstCity();
		city = game.currentCity();
		
		city.setCurrentLocation(Location.NORTH);
		assertTrue(city.getCurrentSector().getDiscovered());
		
		city.setCurrentLocation(Location.EAST);
		assertTrue(city.getCurrentSector().getDiscovered());
		
	}

	@Test
	final void testNextCity() {
		String teamName = "Team name";
		int numHeroes = 3;
		int numCities = 2;
		GameEnvironment game = new GameEnvironment(teamName, numHeroes, numCities);
		
		game.enterFirstCity();
		City city1 = game.currentCity();
		
		// currentCityIndex is incremented by one.
		game.nextCity();
		assertEquals(1, game.getCurrentCityIndex());
		
		// The current city changes
		City city2 = game.currentCity();
		assertFalse(city1 == city2);
		
		// An exception is thrown if nextCity is called when already in
		// the last city.
		boolean exceptionCaught = false;
		try {
			game.nextCity();
		} catch (RuntimeException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		
		// If team doesn't have an Explorer, new city is undiscovered.
		numCities = 4;
		game = new GameEnvironment(teamName, numHeroes, numCities);
		game.enterFirstCity();
		game.nextCity();
		City city = game.currentCity();
		
		city.setCurrentLocation(Location.SOUTH);
		assertFalse(city.getCurrentSector().getDiscovered());
		
		city.setCurrentLocation(Location.WEST);
		assertFalse(city.getCurrentSector().getDiscovered());
		
		// If the team has an Explorer, the first city is completely discovered
		game.getTeam().getHeroes().add(new Explorer(""));
		game.nextCity();
		city = game.currentCity();
		
		city.setCurrentLocation(Location.SOUTH);
		assertTrue(city.getCurrentSector().getDiscovered());
		
		city.setCurrentLocation(Location.WEST);
		assertTrue(city.getCurrentSector().getDiscovered());
		
	}

	@Test
	final void testProcessMiniGameResult() {
		String teamName = "Team name";
		int numHeroes = 3;
		int numCities = 2;
		GameEnvironment game = new GameEnvironment(teamName, numHeroes, numCities);
		
		Hero hero = new Apprentice("");
		game.getTeam().getHeroes().add(hero);
		
		// An exception is thrown if the villain argument is not the
		// villain of the current city.
		Villain villain = new Bucephalus();
		
		boolean exceptionCaught = false;
		try {
			game.processMiniGameResult(true, hero, villain);
		} catch (RuntimeException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
		
		// If the hero won, the villain's times defeated is incremented
		villain = game.currentCity().getVillain();
		game.processMiniGameResult(true, hero, villain);
		assertEquals(1, villain.getTimesDefeated());
		
		// If the hero has the double damage special ability, the villian's
		// times defeated is incremented by two.
		hero = new Mercenary("");
		game.getTeam().getHeroes().add(hero);
		game.processMiniGameResult(true, hero, villain);
		assertEquals(3, villain.getTimesDefeated());
		
		// If the hero won, the team's current money is increased.
		int startMoney = game.getTeam().getCurrentMoney();
		game.processMiniGameResult(true, hero, villain);
		assertEquals(startMoney + game.getPrizeMoney(), game.getTeam().getCurrentMoney());
		
		// If the hero won, the hero's health is unaffected
		assertEquals(hero.getMaxHealth(), hero.getCurrentHealth());
		
		// If the hero lost, the hero takes the appropriate amount of damage
		game.processMiniGameResult(false, hero, villain);
		assertEquals(villain.getDamageDealt(), hero.getMaxHealth() - hero.getCurrentHealth());
		
		// If the hero has the takes less damage special ability, the damage
		// dealt to the hero is lower.
		hero = new Bulwark("");
		game.getTeam().getHeroes().add(hero);
		game.processMiniGameResult(false, hero, villain);
		assertTrue(hero.getMaxHealth() - hero.getCurrentHealth() < villain.getDamageDealt());
	}
	
	
	
	

}
