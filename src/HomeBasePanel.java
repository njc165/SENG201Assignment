import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
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
import javax.swing.JTextPane;

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
	public static final String STATUS_PANEL_STRING = "Status Panel";
	
	/**
	 * The main game that this panel is a part of. Used by event handlers
	 * to change the panel shown in game.
	 */
	private Game gameWindow;

	/**
	 * The main content panel for the home base.
	 */
	private JPanel contentPanel;
	
	/**
	 * A panel component of contentPanel
	 */
	private JLayeredPane mapPanel;
		
	/**
	 * A panel component of contentPanel
	 */
	private JPanel statusPanel;
		
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
	 * The CardLayout used by contentPanel.
	 */
	private CardLayout contentPanelCardLayout;
	
	/**
	 * A button which, when clicked, displays an interactive map of the current city.
	 */
	private JButton btnDisplayCity;
	
	/**
	 * A button which, when clicked, displays an overview of the team's heroes.
	 */
	private JButton btnViewStatus;
	
	/**
	 * A button which, when clicked, consumes a map and reveals the current city.
	 */
	private JButton btnUseMap;
	
	/**
	 * A label counting the number of maps the team owns.
	 */
	private JLabel lblMapsOwned;
		
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
		sidePanel.setBounds(10, 86, 215, 513);
		sidePanel.setLayout(null);
		
		JLabel lblCurrentCity = new JLabel();
		lblCurrentCity.setBounds(77, 11, 52, 25);
		lblCurrentCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurrentCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentCity.setText("City X");
		sidePanel.add(lblCurrentCity);
		
		btnDisplayCity = new JButton("Display City");
		btnDisplayCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPanelCardLayout.show(contentPanel, MAP_PANEL_STRING);
				btnDisplayCity.setEnabled(false);
				btnViewStatus.setEnabled(true);
				refreshMapPanel();
			}
		});
		btnDisplayCity.setBounds(46, 64, 120, 25);
		sidePanel.add(btnDisplayCity);
		
		btnViewStatus = new JButton("View Team Status");
		btnViewStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, STATUS_PANEL_STRING);
				btnViewStatus.setEnabled(false);
				btnDisplayCity.setEnabled(true);
				refreshStatusPanel();
			}
		});
		btnViewStatus.setBounds(46, 124, 120, 25);
		sidePanel.add(btnViewStatus);
		
		btnUseMap = new JButton("Use Map");
		btnUseMap.setBounds(46, 184, 120, 25);
		btnUseMap.setEnabled(false);
		sidePanel.add(btnUseMap);
		
		lblMapsOwned = new JLabel("Owned: 0");
		lblMapsOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblMapsOwned.setBounds(46, 207, 120, 14);
		sidePanel.add(lblMapsOwned);
				
		JTextPane txtRandomEvent = new JTextPane();
		txtRandomEvent.setBackground(new Color(240, 240, 240));
		txtRandomEvent.setEditable(false);
		txtRandomEvent.setText("Sample Text\r\nThe villagers aid you in your fight againts the villains!\r\nYou received: Alicorn Dust");
		txtRandomEvent.setBounds(10, 292, 170, 97);
		sidePanel.add(txtRandomEvent);

		JLabel lblRandomEvent = new JLabel("Random Event!");
		lblRandomEvent.setBounds(10, 279, 82, 14);
		sidePanel.add(lblRandomEvent);
	
		add(sidePanel);	
		}
	
	private void addContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBounds(235, 86, 632, 513);
		contentPanelCardLayout = new CardLayout(0, 0);
		contentPanel.setLayout(contentPanelCardLayout);
    
		addMapPanel();		
		addStatusPanel();
		
		contentPanelCardLayout.show(contentPanel, MAP_PANEL_STRING);
		btnDisplayCity.setEnabled(false);
		add(contentPanel);
	}
	
	private void addMapPanel() {

		mapPanel = new JLayeredPane();
		contentPanel.add(mapPanel, MAP_PANEL_STRING);
		mapPanel.setLayout(null);
		
		JLabel lblNorthWest = new JLabel("");
		lblNorthWest.setBounds(1, 0, 210, 171);
		lblNorthWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblNorthWest);
		
		JLabel lblNorth = new JLabel("");
		lblNorth.setBounds(211, 0, 210, 171);
		lblNorth.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblNorth);
		
		JLabel lblNorthEast = new JLabel("");
		lblNorthEast.setBounds(421, 0, 210, 171);
		lblNorthEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblNorthEast);
		
		JLabel lblEast = new JLabel("");
		lblEast.setBounds(1, 171, 210, 171);
		lblEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblEast);
		
		JLabel lblWest = new JLabel("");
		lblWest.setBounds(211, 171, 210, 171);
		lblWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblWest);
		
		JLabel lblCentre = new JLabel("");
		lblCentre.setBounds(421, 171, 210, 171);
		lblCentre.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblCentre);
		
		JLabel lblSouthWest = new JLabel("");
		lblSouthWest.setBounds(1, 342, 210, 171);
		lblSouthWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblSouthWest);
		
		JLabel lblSouth = new JLabel("");
		lblSouth.setBounds(211, 342, 210, 171);
		lblSouth.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblSouth);
		
		JLabel lblSouthEast = new JLabel("");
		lblSouthEast.setBounds(421, 342, 210, 171);
		lblSouthEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblSouthEast);
		
		JButton btnGoWest = new JButton("Go West");
		btnGoWest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nextSector = gameWindow.getGame().getCurrentCity().getPanelStringFromLocation(Location.WEST);
				gameWindow.setPanel(nextSector);
			}
		});
		mapPanel.setLayer(btnGoWest, 1);
		btnGoWest.setBounds(159, 239, 89, 23);
		mapPanel.add(btnGoWest);
		
		JButton btnGoNorth = new JButton("Go North");
		btnGoNorth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nextSector = gameWindow.getGame().getCurrentCity().getPanelStringFromLocation(Location.NORTH);
				gameWindow.setPanel(nextSector);
			}
		});
		mapPanel.setLayer(btnGoNorth, 1);
		btnGoNorth.setBounds(270, 158, 89, 23);
		mapPanel.add(btnGoNorth);
		
		JButton btnGoEast = new JButton("Go East");
		btnGoEast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nextSector = gameWindow.getGame().getCurrentCity().getPanelStringFromLocation(Location.EAST);
				gameWindow.setPanel(nextSector);
			}
		});
		mapPanel.setLayer(btnGoEast, 1);
		btnGoEast.setBounds(379, 239, 89, 23);
		mapPanel.add(btnGoEast);
		
		JButton btnGoSouth = new JButton("Go South");
		btnGoSouth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nextSector = gameWindow.getGame().getCurrentCity().getPanelStringFromLocation(Location.SOUTH);
				gameWindow.setPanel(nextSector);
			}
		});
		mapPanel.setLayer(btnGoSouth, 1);
		btnGoSouth.setBounds(270, 330, 89, 23);
		mapPanel.add(btnGoSouth);
	}
	
	private void addStatusPanel() {		
		statusPanel = new JPanel();
		contentPanel.add(statusPanel, STATUS_PANEL_STRING);
		statusPanel.setLayout(new GridLayout(0, 3, 0, 0));
	}
	
	private void refreshMapPanel() {
		
	}
	
	/**
	 * Updates variable fields in the statusPanel
	 * Called whenever the statusPanel is displayed
	 */
	private void refreshStatusPanel() {

		statusPanel.removeAll();
		
		for (Hero hero : getTeam().getHeroes()) {			
			JPanel heroPanel = new JPanel();
			statusPanel.add(heroPanel);
			heroPanel.setLayout(null);
			
			JLabel imgHeroPortrait = new JLabel("");
			imgHeroPortrait.setBounds(33, 24, 165, 134);
			imgHeroPortrait.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			heroPanel.add(imgHeroPortrait);

			JLabel lblHeroName = new JLabel(String.format("%s the %s", hero.getName(),
																	   hero.getType()));
			lblHeroName.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroName.setBounds(10, 164, 199, 32);
			heroPanel.add(lblHeroName);

			JLabel lblHeroAbility = new JLabel(String.format("%s", hero.getSpecialAbility()));
			lblHeroAbility.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHeroAbility.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroAbility.setBounds(20, 203, 181, 53);
			heroPanel.add(lblHeroAbility);

			JLabel lblHeroHealth = new JLabel(String.format("Health: %d/%d", hero.getCurrentHealth(), hero.getMaxHealth()));
			lblHeroHealth.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHeroHealth.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroHealth.setBounds(10, 257, 199, 44);
			heroPanel.add(lblHeroHealth);
			
			String healingItem;
			if (hero.getAppliedHealingItem() instanceof HealingItem) {
				healingItem = String.format("Applied Healing Item:\n%s", hero.getAppliedHealingItem().toString());
			}
			else {
				healingItem = "No Applied Healing Item";
			}
						
			JLabel lblHeroHealingItem = new JLabel(String.format("%s", healingItem));
			lblHeroHealingItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHeroHealingItem.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroHealingItem.setBounds(33, 313, 165, 32);
			heroPanel.add(lblHeroHealingItem);

			JLabel lblHeroPowerUp = new JLabel("Power Ups:");
			lblHeroPowerUp.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHeroPowerUp.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroPowerUp.setBounds(10, 361, 210, 32);
			heroPanel.add(lblHeroPowerUp);

			JTextPane txtHeroPowerUps = new JTextPane();
			txtHeroPowerUps.setBackground(new Color(240, 240, 240));
			for (PowerUp pu : hero.getActivePowerUps()) {
				txtHeroPowerUps.setText(txtHeroPowerUps.getText() + String.format("\n%s", pu.getType().toString()));
			}
			
			txtHeroPowerUps.setEditable(false);
			txtHeroPowerUps.setBounds(10, 402, 210, 122);
			heroPanel.add(txtHeroPowerUps);
		}
		
	}
	
	private void refreshSidePanel() {
		for (ActionListener listener : btnUseMap.getActionListeners()) {
			btnUseMap.removeActionListener(listener);
		}
		
		btnUseMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.getGame().getCurrentCity().setAllDiscovered();
				getTeam().setNumMaps(getTeam().getNumMaps() - 1);
				contentPanelCardLayout.show(contentPanel, MAP_PANEL_STRING);
			}
		});
		
		lblMapsOwned.setText(String.format("Owned: %d", getTeam().getNumMaps()));

	}
	
	public void refreshHomeBase() {
		refreshSidePanel();
		refreshStatusPanel();
		refreshMapPanel();
	}
	
	/**
	 * Gets the Team object associated with the current Game.
	 * @return the Team associated with the current Game.
	 */
	private Team getTeam() {
		return gameWindow.getGame().getTeam();
	}
}
