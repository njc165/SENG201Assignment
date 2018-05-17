/**
 * This exception is thrown when the player attempts to apply
 * a healing item that they do not own. This situation should
 * never arise due to radio buttons being disabled if the player
 * does not own a given item.
 */
public class NoneOwnedException extends RuntimeException {
	
	public NoneOwnedException(String message) {
		super(message);
	}
	
}
