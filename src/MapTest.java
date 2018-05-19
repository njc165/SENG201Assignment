import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MapTest {

	@Test
	final void testGetCost() {
		// If numDiscountHeroes is 0, the cost is unaffected.
		assertEquals(10, Map.getCost(0));
		
		// If numDiscountHeroes is 1, the cost is multiplied by the
		// discount multiplier
		int expectedCost = (int) (10 * Hero.STORE_DISCOUNT_MULTIPLIER);
		assertEquals(expectedCost, Map.getCost(1));
		
		// If numDiscountHeroes is greater than 1, the discounts stack
		expectedCost = (int) (10 * Hero.STORE_DISCOUNT_MULTIPLIER * Hero.STORE_DISCOUNT_MULTIPLIER);
		assertEquals(expectedCost, Map.getCost(2));
		
	}

}
