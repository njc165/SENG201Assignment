import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Test;

class PaperScissorsRockTest {

	@Test
	final void testUpdateVillainsChoice() {
		final String[] CHOICES = {"Paper", "Scissors", "Rock"};

		Hero hero = new Apprentice("Speed");
		Villain villain = new Evan();
		PaperScissorsRock psr = new PaperScissorsRock(hero, villain);
		
		// Check that the villain's choice is set to a valid value
		psr.updateVillainsChoice();
		assertTrue(Arrays.asList(CHOICES).contains(psr.getVillainsChoice()));
	}
	
	@Test
	final void testComputeOutcome() {
		final String[] CHOICES = {"Paper", "Scissors", "Rock"};
		Random rand = new Random();
		
		Hero hero = new Apprentice("Molly");
		Villain villain = new Evan();
		PaperScissorsRock psr = new PaperScissorsRock(hero, villain);
		
		// Set choices such that outcome should be draw
		psr.updateVillainsChoice();
		psr.setHerosChoice(psr.getVillainsChoice());
		String outcome = psr.computeOutcome();
		assertEquals("Draw", outcome);
		
		// 100 times, set choices such that outcome should not be draw
		for (int i = 0; i < 100; i++) {
			psr.setHerosChoice(CHOICES[rand.nextInt(3)]);
			while (psr.getVillainsChoice() == psr.getHerosChoice()) {
				psr.updateVillainsChoice();
			}
			assertNotEquals("Draw", psr.computeOutcome());
		}
	}
	
	@Test
	final void testRevealNot() {
		final String[] CHOICES = {"Paper", "Scissors", "Rock"};

		Hero hero = new Apprentice("X");
		Villain villain = new Evan();
		PaperScissorsRock psr = new PaperScissorsRock(hero, villain);
		
		// 100 times, check that revealNot reveals a valid choice
		// and that it is not the villain's choice
		for (int i = 0; i < 100; i++) {
			psr.updateVillainsChoice();
			String not = psr.revealNot();
			assertTrue(Arrays.asList(CHOICES).contains(not));
			assertNotEquals(not, psr.getVillainsChoice());
		}
	}

}
