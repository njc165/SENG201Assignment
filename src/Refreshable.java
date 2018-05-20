/**
 * Panels which contain dynamic components should implement the
 * Refreshable interface. This enforces implementation of a
 * refresh() method which is required to update dynamic components.
 * The refresh() method should be called each time the panel shown by
 * the main CardLayout in the Game window is changed to ensure that
 * the panel is updated before it is shown to the user.
 */
public interface Refreshable {
	
	/**
	 * Called each time the panel shown by the main CardLayout in
	 * the Game window is changed to ensure that the panel is
	 * updated before it is shown to the user.
	 */
	public void refresh();
	
}
