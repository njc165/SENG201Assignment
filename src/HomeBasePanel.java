import java.awt.Dimension;
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

public class HomeBasePanel extends JPanel {
	
	/**
	 * A string representation of this panel, used by the CardLayout in Game.
	 */
	public static final String HOME_BASE_PANEL_STRING = "Home Base Panel";
	
	/**
	 * The main Game window that this panel is a part of. Used by event handlers
	 * to change the panel shown in game.
	 */
	private Game game;
	
	public HomeBasePanel(Game game) {
		super();
		this.game = game;
		
		setLayout(null);
		setPreferredSize(new Dimension(610, 880));
		
	}
	
}
