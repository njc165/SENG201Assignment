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

public class StartScreenPanel extends JPanel {
	
	public static final String START_SCREEN_PANEL_STRING = "Start Screen Panel";
	
	private Game game;
	
	public StartScreenPanel(Game game) {
		super();
		this.game = game;
		
		setPreferredSize(new Dimension(610, 880));
		
	}
	
}
