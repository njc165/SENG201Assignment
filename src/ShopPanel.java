import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTextField;

public class ShopPanel extends JPanel {
	
	/**
	 * A string representation of this panel, used by the CardLayout in Game.
	 */
	public static final String SHOP_PANEL_STRING = "Shop Panel";
	
	
	/**
	 * The main Game window that this panel is a part of. Used by event handlers
	 * to change the panel shown in game.
	 */
	private Game game;
	
	public ShopPanel(Game game) {
		super();
		this.game = game;
		
		setPreferredSize(new Dimension(880, 610));
		setLayout(null);
		
	}
	
}
