import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;

public class HomeBasePanel extends JPanel {
	
	/**
	 * A string representation of this panel, used by the CardLayout in Game.
	 */
	public static final String HOME_BASE_PANEL_STRING = "Home Base Panel";
	
	/** A string representation of the map panel, used by the cardlayout of the
	 * main content panel.
	 */
	public static final String MAP_PANEL_STRING = "Map Panel";
	
	/**
	 * A string representation of the status panel, used by the cardlayout of the
	 * main content panel.
	 */
	public static final String STATUS_PANEL = "Status Panel";
	
	/**
	 * The main game window that this panel is a part of. Used by event handlers
	 * to change the panel shown in game.
	 */
	private Game gameWindow;
	
	/**
	 * The main content panel for the home base.
	 */
	private JPanel contentPanel;
	
	/**
	 * The side panel which holds buttons to change between views in the main
	 * content panel, and other information.
	 */
	private JPanel sidePanel;
	
	/**
	 * The title panel which holds the title.
	 */
	private JPanel titlePanel;
	
	/**
	 * The card layout belonging to the main content panel.
	 */
	private CardLayout contentPanelCardLayout;
	
	/**
	 * The panel which will enable players to view the map and change locations.
	 */
	private JPanel mapPanel;
	
	/**
	 * The panel which will enable players to view the statuses of their heroes.
	 */
	private JPanel statusPanel;
	
	/**
	 * The grid layout used by the map panel.
	 */
	private GridLayout mapPanelGridLayout;
	
		
	public HomeBasePanel(Game game) {
		super();
		this.gameWindow = game;
		
		setLayout(null);
		setPreferredSize(new Dimension(880, 610));
		
		addTitlePanel();
		addSidePanel();
		addContentPanel();
		}
	
	private void addTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setBounds(0, 0, 880, 75);
		
		JLabel lblTitle = new JLabel("HOME BASE");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 60));
		titlePanel.add(lblTitle);		
		
		add(titlePanel);		
	}
	
	private void addSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setBounds(0, 75, 190, 535);
		add(sidePanel);
	}
	
	private void addContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBounds(190, 75, 690, 535);
		add(contentPanel);
	}
}
