import static org.junit.jupiter.api.Assertions.*;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import org.junit.jupiter.api.Test;

class UtilTest {
		
	@Test
	final void testInstantiate() {
		// Create a new villain
		Class<?> villainClass = Invictus.class;
		Villain villain = (Villain) Util.instantiate(villainClass);
		assertTrue(villain instanceof Invictus);
		assertTrue(villain instanceof Villain);
		assertEquals("Invictus the Unconquered", villain.toString());
		
		// Create a new power up
		Class<?> powerUpClass = TieBreaker.class;
		PowerUp powerUp = (PowerUp) Util.instantiate(powerUpClass);
		assertTrue(powerUp instanceof TieBreaker);
		assertTrue(powerUp instanceof PowerUp);
		assertEquals(PowerUpType.TIEBREAKER, powerUp.getType());
	}

	@Test
	final void testSelectedButton() {
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton b1 = new JRadioButton();
		JRadioButton b2 = new JRadioButton();
		
		bg.add(b1);
		bg.add(b2);
		
		// Check that selectedButton returns b1 as expected
		b1.setSelected(true);
		assertEquals(Util.selectedButton(bg), b1);
		
		/* Check that when a new button is selected, b1
		* becomes unselected and selectedButton returns
		* the new button
		*/
		b2.setSelected(true);
		assertNotEquals(b1.isSelected(), true);
		assertEquals(Util.selectedButton(bg), b2);
	}
	
	
	
}
