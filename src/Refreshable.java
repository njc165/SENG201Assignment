/**
 * Panels which contain dynamic components should implement the
 * Refreshable interface. This enforces implementation of a
 * refresh() method which is required to update dynamic components.
 */
public interface Refreshable {
	
	public void refresh();
	
}
