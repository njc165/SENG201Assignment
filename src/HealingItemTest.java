import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HealingItemTest {

	@Test
	final void testHealingItem() {
		// Create new HealingItem, check that properties are assigned correctly.
		HealingItem item = new HealingItem("potion", "a potion", 2, 3, 20);
		assertEquals("potion", item.getName());
		assertEquals("a potion", item.getDescription());
		assertEquals(20, item.getPrice());
		assertEquals(2, item.getIncrementsRemaining());
	}

	@Test
	final void testGetStatus() {
		// Create a new HealingItem
		HealingItem item = new HeartyMeal();
		
		// Check that getStatus returns the expected string.
		assertEquals(item.getStatus(), "Increments remaining: 2\nTime to next increment: 40 seconds.");
	}
	
	@Test
	final void testApply() {
		// Create a new HealingItem
		HealingItem item = new SuspiciousTonic();
		
		// Check that incrementsRemaining is 1
		assertEquals(1, item.getIncrementsRemaining());
		
		// Apply the item
		item.apply(false);
		
		// Check that incrementsRemaining is now 0
		assertEquals(0, item.getIncrementsRemaining());
		
		// Try to apply the item again. Check that a RuntimeException is thrown.
		boolean gotException = false;
		try {
			item.apply(false);
		}
		catch (RuntimeException e) {
			gotException = true;
		}
		assertTrue(gotException);
	}
	
	@Test
	final void testReadyToIncrement() throws InterruptedException {
		// Create a new HealingItem
		HealingItem item = new AlicornDust();
		
		// Wait 20 seconds, the time per increment for AlicornDust
		Thread.sleep(20000);
		
		// Check that the HealingItem is ready to be applied
		assertTrue(item.readyToIncrement());
		
		// Apply the item, resetting nextApplicationTime
		item.apply(true);
		
		// Wait 1 second, an amount less than the time per increment
		Thread.sleep(1000);
		
		// Check that the HealingItem is not ready to be applied
		assertFalse(item.readyToIncrement());
	}
	
}
