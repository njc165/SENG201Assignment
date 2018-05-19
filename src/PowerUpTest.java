import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PowerUpTest {

	@Test
	final void testPowerUp() {
		// Create a new PowerUp item
		PowerUp pu = new PowerUp(PowerUpType.TIEBREAKER, 100, "description");
		
		// Check that the constructor correctly initialises values.
		assertEquals(pu.getType(), PowerUpType.TIEBREAKER);
		assertEquals(pu.getCost(0), 100);
	}
	
	@Test
	final void testToString() {
		// Create a new powerUp
		PowerUp pu = new ExtraGuess();
		
		// Check that toString() returns the expected value
		assertEquals("Extra Guess", pu.toString());
	}
	
	@Test
	final void testEqualsObject() {
		// Two power-ups of the same type are considered equal
		TieBreaker tieBreaker1 = new TieBreaker();
		TieBreaker tieBreaker2 = new TieBreaker();
		assertTrue(tieBreaker1.equals(tieBreaker2));
		
		// Two power-ups of different types are not considered equal
		ExtraGuess extraGuess = new ExtraGuess();
		assertFalse(tieBreaker1.equals(extraGuess));
		
		// A power-up is not equal to an object of a different type
		assertFalse(tieBreaker1.equals(new String()));
	}
	
	@Test
	final void testGetCost() {
		// If numDiscountHeroes is 0, the cost is unaffected.
		int cost = 50;
		PowerUp powerUp = new PowerUp(PowerUpType.TIEBREAKER, cost, "description");
		assertEquals(cost, powerUp.getCost(0));
		
		// If numDiscountHeroes is 1, the cost is multiplied by the
		// discount multiplier
		int expectedCost = (int) (cost * Hero.STORE_DISCOUNT_MULTIPLIER);
		assertEquals(expectedCost, powerUp.getCost(1));
		
		// If numDiscountHeroes is greater than 1, the discounts stack
		expectedCost = (int) (cost * Hero.STORE_DISCOUNT_MULTIPLIER * Hero.STORE_DISCOUNT_MULTIPLIER);
		assertEquals(expectedCost, powerUp.getCost(2));	
	}
	


}
