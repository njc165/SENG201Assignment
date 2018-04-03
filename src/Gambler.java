public class Gambler extends Hero {
	
	private static final String TYPE = "Gambler";
	private static final String SPECIAL_ABILITY = "Advantages in villain battles";
	private static final String DESCRIPTION = "You don't have to look very far to find a gambler these days, but true experts are few and far between. These players use more than luck to tip the odds in their favour.\n\nWhile fighting villains, Gamblers gain the following advantages:\n\nPaper Scissors Rock:\nReveal one option that the villain will not play.\n\nGuess the Number:\nGet an extra guess.\n\nDice Roll:\nIncrease your roll by one.";
	private static final double MAX_HEALTH = 80;
	
	public Gambler(String name) {
		super(name, TYPE, SPECIAL_ABILITY, DESCRIPTION, MAX_HEALTH);
		setHasBattleAdvantages(true);
	}
	
}
