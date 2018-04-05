import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HealingItemTest {

	@Test
	final void testHealingItem() {
		// Create new HealingItem, check that properties are assigned correctly.
		HealingItem item = new HealingItem("potion", "a potion", 2, 3, 20);
		assertEquals("potion", item.getName());
		assertEquals("a potion", item.getDescription());
		assertEquals(20, item.getCost());
		assertEquals(2, item.getIncrementsRemaining());
	}

	@Test
	final void testGetStatus() {
		// Create a new HealingItem
		HealingItem item = new HeartyMeal();
		item.applyToHero(false);
		
		// Check that getStatus returns the expected string
		assertEquals(item.getStatus(), "Increments remaining: 2\nTime to next increment: 40 seconds.");
		
		// Create a new HealingItem, this time halving the time per increment
		HealingItem item2 = new HeartyMeal();
		item2.applyToHero(true);
		
		// Check that getStatus returns the expected string
		assertEquals(item2.getStatus(), "Increments remaining: 2\nTime to next increment: 20 seconds.");
	}
	
	@Test
	final void testApplyToHero() {
		// Create a new HealingItem and set its last application time to now
		HealingItem item = new SuspiciousTonic();
		item.applyToHero(false);
		
		// Check that, when called with false, the time per increment is unchanged
		assertEquals(item.getTimePerIncrement(), 60);
		
		// Check that, when called with true, the time per increment is multiplied
		// by the correct amount
		item.applyToHero(true);
		assertEquals(item.getTimePerIncrement(), 30);
	}
	
	@Test
	final void testApplyIncrement() {
		// Create a new HealingItem
		HealingItem item = new AlicornDust();
		
		// Check that incrementsRemaining is initialised correctly
		assertEquals(item.getIncrementsRemaining(), 3);
		
		// Check that applying the item properly decrements incrementsRemaining
		item.applyIncrement();
		assertEquals(item.getIncrementsRemaining(), 2);
	}
	
	@Test
	final void testReadyToIncrement() throws InterruptedException {
		// Create a new HealingItem
		HealingItem item = new AlicornDust();
		item.applyToHero(false);
		
		// Wait 20 seconds and check that the item is ready to increment
		Thread.sleep(20000);
		assertTrue(item.readyToIncrement());
		
		// Apply the increment, resetting the timer
		item.applyIncrement();
		
		//Wait 1 second and check that the item is not ready to increment
		Thread.sleep(1000);
		assertFalse(item.readyToIncrement());
		
		// Create a new HealingItem, this time with the fasterHealing ability
		HealingItem item2 = new AlicornDust();
		item2.applyToHero(true);
		
		// Wait 10 seconds and check that the item is ready to increment
		// i.e. that the fasterHealing ability is correctly applied
		Thread.sleep(10000);
		assertTrue(item2.readyToIncrement());
		
		// Apply the increment, resetting the timer
		item2.applyIncrement();
		
		// Wait 1 second and check that the item is not ready to increment
		Thread.sleep(1000);
		assertFalse(item2.readyToIncrement());
	}
		
}
