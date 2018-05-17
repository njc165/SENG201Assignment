/**
 * This exception should be thrown when a player attempts
 * to buy an item that they cannot afford. Buttons to purchase
 * items should be disabled if the player cannot afford them,
 * thus avoiding this exception.
 */
public class NotEnoughMoneyException extends RuntimeException {

	public NotEnoughMoneyException(String message) {
		super(message);
	}
	
}
