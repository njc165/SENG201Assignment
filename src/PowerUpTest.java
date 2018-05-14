import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PowerUpTest {

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
	final void testPowerUp() {
		// Create a new PowerUp item
		PowerUp pu = new PowerUp(PowerUpType.TIEBREAKER, 100, "description");
		
		// Check that the constructor correctly initialises values.
		assertEquals(pu.getType(), PowerUpType.TIEBREAKER);
		assertEquals(pu.getCost(0), 100);

	}
	
	@Test
	final void testShopDecription() {
		// Create new team and powerUp
		Team team = new Team("TeamName");
		PowerUp pu = new PowerUp(PowerUpType.MINDREADER, 20, "d");
		
		// Create a shop description of the power up item
		String returnedString = pu.shopDescription(team);
		
		// Check that the returned string has the expected value
		assertEquals("Mindreader\nNumber currently owned: 0\nPrice: 20 coins\nd\n", returnedString);
		
	}
	
	@Test
	final void testToString() {
		// Create a new powerUp
		PowerUp pu = new ExtraGuess();
		
		// Check that toString() returns the expected value
		assertEquals("Extra Guess", pu.toString());
	}

}
