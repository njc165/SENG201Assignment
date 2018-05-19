import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HealingItemTest {

	@Test
	final void testHealingItem() {
		// Create new HealingItem, check that properties are assigned correctly.
		int numIncrements = 2;
		int timePerIncrement = 3;
		int cost = 20;
		HealingItem item = new HealingItem("potion", "a potion", numIncrements, timePerIncrement, cost);
		assertEquals("potion", item.toString());
		assertEquals(2, item.getIncrementsRemaining());
		assertEquals(20, item.getCost(0));
	}
	
	@Test
	final void testShopDescription() {
		int numIncrements = 2;
		int timePerIncrement = 3;
		int cost = 20;
		HealingItem item = new HealingItem("potion", "a potion", numIncrements, timePerIncrement, cost);
		
		String shopDescription = item.shopDescription();
		assertTrue(shopDescription.contains("a potion"));
		assertTrue(shopDescription.contains("Restores 50%"));
		assertTrue(shopDescription.contains("3 seconds"));

	}
	
	@Test
	final void testApplyToHero() {		
		// Create a new HealingItem and set its last application time to now
		int timePerIncrement = 60;
		HealingItem item = new HealingItem("", "", 2, timePerIncrement, 20);
		
		// Check that, when called with false, the time per increment is unchanged
		item.applyToHero(false);
		assertEquals(60.0, item.secondsRemaining(), 1.0);
		
		// Check that, when called with true, the time per increment is multiplied
		// by the correct amount
		item = new HealingItem("", "", 2, timePerIncrement, 20);
		item.applyToHero(true);
		assertEquals(60.0 * Hero.FASTER_HEALING_MULTIPLIER, item.secondsRemaining(), 1.0);
	}
	
	@Test
	final void testApplyIncrement() {
		// Create a new HealingItem
		HealingItem item = new AlicornDust();
		
		// Check that incrementsRemaining is set to the correct initial value
		assertEquals(3, item.getIncrementsRemaining());
		
		// Check that applying the item decrements incrementsRemaining
		item.applyIncrement();
		assertEquals(2, item.getIncrementsRemaining());
		
		// An exception is thrown when there are no increments remaining
		item.applyIncrement();
		item.applyIncrement();
		
		boolean exceptionCaught = false;
		try {
			item.applyIncrement();
		} catch (RuntimeException e) {
			exceptionCaught = true;
		}
		assertTrue(exceptionCaught);
	}
	
	@Test
	final void testReadyToIncrement() throws InterruptedException {
		// Create a new HealingItem
		int timePerIncrement = 2;
		HealingItem item = new HealingItem("name", "description", 3, timePerIncrement, 100);
		item.applyToHero(false);
		
		// Wait just over 2 seconds and check that the item is ready to increment
		Thread.sleep(2100);
		assertTrue(item.readyToIncrement());
		
		// Apply the increment, resetting the timer
		item.applyIncrement();
		
		// Wait 1 second and check that the item is not ready to increment
		Thread.sleep(1000);
		assertFalse(item.readyToIncrement());
		
		// Create a new HealingItem, this time with the fasterHealing ability
		HealingItem item2 = new HealingItem("name", "description", 3, timePerIncrement, 100);
		item2.applyToHero(true);
		
		// Wait just over 1 second and check that the item is ready to increment
		// i.e. that the fasterHealing ability is correctly applied
		Thread.sleep(1100);
		assertTrue(item2.readyToIncrement());
		
		// Apply the increment, resetting the timer
		item2.applyIncrement();
		
		// Check that the item is not ready to increment
		assertFalse(item2.readyToIncrement());
	}
	
	@Test
	final void testGetCost() {
		// If numDiscountHeroes is 0, the cost is unaffected.
		int cost = 100;
		HealingItem item = new HealingItem("name", "description", 3, 2, cost);
		
		assertEquals(100, item.getCost(0));
		
		// If numDiscountHeroes is 1, the cost is multiplied by the
		// discount multiplier
		int expectedCost = (int) (100 * Hero.STORE_DISCOUNT_MULTIPLIER);
		assertEquals(expectedCost, item.getCost(1));
		
		// If numDiscountHeroes is greater than 1, the discounts stack
		expectedCost = (int) (100 * Hero.STORE_DISCOUNT_MULTIPLIER * Hero.STORE_DISCOUNT_MULTIPLIER);
		assertEquals(expectedCost, item.getCost(2));	
	}
	
	
		
}
