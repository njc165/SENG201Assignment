public class Maverick extends Villain {
	
	private static final String NAME = "Maverick the Strong";
	private static final String TAUNT = "Come! Face the great Maverick!";
	private static final double DAMAGE_DEALT = 20;
	private static final MiniGame[] GAMES_PLAYED = {MiniGame.PAPER_SCISSORS_ROCK, MiniGame.GUESS_NUMBER, MiniGame.DICE_ROLL};
	
	public Maverick() {
		super(NAME, TAUNT, DAMAGE_DEALT, GAMES_PLAYED);
	}
	
}
