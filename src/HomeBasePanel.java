import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

public class HomeBasePanel extends JPanel implements Refreshable {
	
	/**
	 * A string representation of this panel, used by the CardLayout in Game.
	 */
	public static final String HOME_BASE_PANEL_STRING = "Home Base Panel";
	
	/** A string representation of the map panel, used by the CardLayout of the
	 * main content panel.
	 */
	private static final String MAP_PANEL_STRING = "Map Panel";
	
	/**
	 * A string representation of the status panel, used by the CardLayout of the
	 * main content panel.
	 */
	private static final String STATUS_PANEL_STRING = "Status Panel";

	/**
	 * An array of the cardinal directions as Locations.
	 * Used to update images on the map when appropriate.
	 */
	private final Location[] LOCATIONS = {Location.NORTH, Location.EAST,
										  Location.SOUTH, Location.WEST};
	
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
	private JPanel mapPanel;
		
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
	
	/**
	 * A label showing the number of the current city.
	 */
	private JLabel lblCurrentCity;
	
	/**
	 * A label containing an image of the northern map sector.
	 */
	private JLabel lblNorth;
	
	/**
	 * A label containing an image of the eastern map sector.
	 */
	private JLabel lblEast;
	
	/**
	 * A label containing an image of the southern map sector.
	 */
	private JLabel lblSouth;
	
	/**
	 * A label containing an image of the western map sector.
	 */
	private JLabel lblWest;
	
	/**
	 * Constructor for an instance of HomeBasePanel
	 * @param game The game window to which this panel is being added.
	 */
	public HomeBasePanel(Game game) {
		super();
		this.gameWindow = game;
		
		setLayout(null);
		setPreferredSize(new Dimension(880, 610));
		
		addTitlePanel();
		addSidePanel();
		addContentPanel();
	}
	
	/**
	 * Refresh all components of the HomeBasePanel by refreshing
	 * any panels which contain components whose attributes may
	 * have changed since they were last viewed.
	 */
	public void refresh() {
		refreshSidePanel();
		refreshStatusPanel();
		refreshMapPanel();
		
		repaint();
		revalidate();
	}
	
	/**
	 * Gets the Team object associated with the current Game.
	 * @return the Team associated with the current Game.
	 */
	private Team team() {
		return gameWindow.getGame().getTeam();
	}
	
	/**
	 * Takes a sector, and returns a String representation of the
	 * panel for that sector of the city.
	 * @param sector	The sector of interest.
	 * @return			A String representation of the panel for the given sector.
	 */
	private String sectorPanelString(Sector sector) {
		return sector.getType().toString() + " Panel";
	}
	
	/**
	 * Event handler called when one of the four arrow buttons on the
	 * map is clicked.
	 * Sets the current city's current location to the given location,
	 * sets the new current sector discovered, and show the appropriate
	 * panel for the new sector.
	 * @param location	The location corresponding to the arrow button which
	 * 					was clicked.
	 */
	private void arrowButtonEventHandler(Location location) {
		City city = gameWindow.getGame().currentCity();
		city.setCurrentLocation(location);
		city.setCurrentSectorDiscovered();
		gameWindow.setPanel(sectorPanelString(city.getCurrentSector()));
	}
	
	/**
	 * Create the title panel and add it to the main window.
	 */
	private void addTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setBounds(10, 11, 860, 64);
		titlePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("HOME BASE");
		lblTitle.setBounds(10, 0, 850, 64);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Rockwell", Font.PLAIN, 60));
		titlePanel.add(lblTitle);		
		
		add(titlePanel);		
	}
	
	/**
	 * Create the side panel and add it to the main window.
	 */
	private void addSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setBounds(10, 86, 215, 513);
		sidePanel.setLayout(null);
		
		lblCurrentCity = new JLabel();
		lblCurrentCity.setBounds(10, 11, 195, 42);
		lblCurrentCity.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCurrentCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentCity.setText("City X");
		sidePanel.add(lblCurrentCity);
		
		btnDisplayCity = new JButton("Display City");
		btnDisplayCity.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnDisplayCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
				contentPanelCardLayout.show(contentPanel, MAP_PANEL_STRING);
			}
		});
		btnDisplayCity.setBounds(10, 76, 195, 30);
		sidePanel.add(btnDisplayCity);
		
		btnViewStatus = new JButton("View Team Status");
		btnViewStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
				contentPanelCardLayout.show(contentPanel, STATUS_PANEL_STRING);
			}
		});
		btnViewStatus.setBounds(10, 124, 195, 30);
		sidePanel.add(btnViewStatus);
		
		btnUseMap = new JButton("Use Map");
		btnUseMap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUseMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (team().getNumMaps() <= 0)
					throw new RuntimeException("Team doesn't own any maps. Use map button should be disabled.");
				
				team().setNumMaps(team().getNumMaps() - 1);
				gameWindow.getGame().currentCity().setAllDiscovered();
				refresh();
				contentPanelCardLayout.show(contentPanel, MAP_PANEL_STRING);
				JOptionPane.showMessageDialog(gameWindow.getFrame(), "The location of each sector in the city has been revealed!");
			}
		});
		btnUseMap.setBounds(10, 196, 195, 30);
		btnUseMap.setEnabled(false);
		sidePanel.add(btnUseMap);
		
		lblMapsOwned = new JLabel("Owned:");
		lblMapsOwned.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMapsOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblMapsOwned.setBounds(49, 228, 120, 20);
		sidePanel.add(lblMapsOwned);
		
		add(sidePanel);
	}
	
	/**
	 * Create the main content panel and add it to the window.
	 */
	private void addContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBounds(235, 86, 632, 513);
		contentPanelCardLayout = new CardLayout(0, 0);
		contentPanel.setLayout(contentPanelCardLayout);
    
		addMapPanel();		
		addStatusPanel();
		
		contentPanelCardLayout.show(contentPanel, MAP_PANEL_STRING);
		add(contentPanel);
	}
	
	/**
	 * Create the map panel and add it to the main content panel.
	 */
	private void addMapPanel() {
		
		mapPanel = new JPanel();
		mapPanel.setLayout(null);
		contentPanel.add(mapPanel, MAP_PANEL_STRING);
		
		addMapButtons();
		
		JPanel mapSquaresPanel = new JPanel();
		mapSquaresPanel.setBounds(0, 0, 632, 513);
		mapPanel.add(mapSquaresPanel);
		mapSquaresPanel.setLayout(new GridLayout(3, 3, 10, 10));
		
		mapSquaresPanel.add(forestImageLabel());
		
		lblNorth = new JLabel("");
		lblNorth.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.UNDISCOVERED_SECTOR_IMAGE_FILEPATH)));
		mapSquaresPanel.add(lblNorth);
		
		mapSquaresPanel.add(forestImageLabel());
		
		lblWest = new JLabel("");
		lblWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.UNDISCOVERED_SECTOR_IMAGE_FILEPATH)));
		mapSquaresPanel.add(lblWest);
		
		JLabel lblCentre = new JLabel("");
		lblCentre.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.HOME_BASE_IMAGE_FILEPATH)));
		mapSquaresPanel.add(lblCentre);
		
		lblEast = new JLabel("");
		lblEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.UNDISCOVERED_SECTOR_IMAGE_FILEPATH)));
		mapSquaresPanel.add(lblEast);
		
		mapSquaresPanel.add(forestImageLabel());
		
		lblSouth = new JLabel("");
		lblSouth.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.UNDISCOVERED_SECTOR_IMAGE_FILEPATH)));
		mapSquaresPanel.add(lblSouth);
		
		mapSquaresPanel.add(forestImageLabel());
	}
	
	/**
	 * Creates and returns a new JLabel with the forest image as its
	 * icon.
	 */
	private JLabel forestImageLabel() {
		JLabel lblForestImage = new JLabel("");
		lblForestImage.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.FOREST_IMAGE_FILEPATH)));
		return lblForestImage;
	}
	
	/**
	 * Adds buttons to the map, to allow the user to choose a direction in
	 * which to move.
	 */
	private void addMapButtons() {
		JButton btnGoWest = new JButton(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.WEST, "red"))));
		btnGoWest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrowButtonEventHandler(Location.WEST);
			}
		});
		btnGoWest.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnGoWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.WEST, "yellow"))));
			}
			
			public void mouseExited(MouseEvent e) {
				btnGoWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.WEST, "red"))));
			}
		});
		btnGoWest.setBorderPainted(false);
		btnGoWest.setContentAreaFilled(false);
		btnGoWest.setFocusPainted(false);
		btnGoWest.setOpaque(false);;
		btnGoWest.setBounds(159, 229, 68, 51);
		mapPanel.add(btnGoWest);
		
		JButton btnGoNorth = new JButton(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.NORTH, "red"))));
		btnGoNorth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arrowButtonEventHandler(Location.NORTH);
			}
		});
		btnGoNorth.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnGoNorth.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.NORTH, "yellow"))));
			}
			
			public void mouseExited(MouseEvent e) {
				btnGoNorth.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.NORTH, "red"))));
			}
		});
		btnGoNorth.setBorderPainted(false);
		btnGoNorth.setContentAreaFilled(false);
		btnGoNorth.setFocusPainted(false);
		btnGoNorth.setOpaque(false);;
		btnGoNorth.setBounds(290, 131, 51, 68);
		mapPanel.add(btnGoNorth);
		
		JButton btnGoEast = new JButton(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.EAST, "red"))));
		btnGoEast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrowButtonEventHandler(Location.EAST);
			}
		});
		btnGoEast.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnGoEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.EAST, "yellow"))));
			}
			
			public void mouseExited(MouseEvent e) {
				btnGoEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.EAST, "red"))));
			}
		});
		btnGoEast.setBorderPainted(false);
		btnGoEast.setContentAreaFilled(false);
		btnGoEast.setFocusPainted(false);
		btnGoEast.setOpaque(false);;
		btnGoEast.setBounds(405, 229, 68, 51);
		mapPanel.add(btnGoEast);
		
		JButton btnGoSouth = new JButton(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.SOUTH, "red"))));
		btnGoSouth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrowButtonEventHandler(Location.SOUTH);

			}
		});
		btnGoSouth.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				btnGoSouth.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.SOUTH, "yellow"))));
			}
			
			public void mouseExited(MouseEvent e) {
				btnGoSouth.setIcon(new ImageIcon(HomeBasePanel.class.getResource(Image.getMapArrowImage(Location.SOUTH, "red"))));
			}
		});
		btnGoSouth.setBorderPainted(false);
		btnGoSouth.setContentAreaFilled(false);
		btnGoSouth.setFocusPainted(false);
		btnGoSouth.setOpaque(false);;
		btnGoSouth.setBounds(290, 310, 51, 68);
		mapPanel.add(btnGoSouth);
	}
	

	/**
	 * Create the status panel and add it to the main content panel.
	 */
	private void addStatusPanel() {		
		statusPanel = new JPanel();
		contentPanel.add(statusPanel, STATUS_PANEL_STRING);
		statusPanel.setLayout(new GridLayout(0, 3, 0, 0));		

	}

	/**
	 * Update the side panel on the home base screen,
	 * by updating all components with variable attributes.
	 */
	private void refreshSidePanel() {
		lblCurrentCity.setText(String.format("City %s",
				gameWindow.getGame().getCurrentCityIndex() + 1));
		
		lblMapsOwned.setText(String.format("Owned: %d", team().getNumMaps()));
		btnUseMap.setEnabled(team().getNumMaps() > 0);
	}
	
	/**
	 * Update the map panel by updating all components with
	 * variable attributes.
	 */
	private void refreshMapPanel() {
		
		for (Location location : LOCATIONS) {
			JLabel targetLabel = getLabelAtLocation(location);
			Sector sectorAtLocation = gameWindow.getGame().currentCity().sectorAtLocation(location);
			
			String filepath = null;
			if (sectorAtLocation.getDiscovered()) {
				filepath = Image.sectorTypeFilepath(sectorAtLocation.getType());
			} else {
				filepath = Image.UNDISCOVERED_SECTOR_IMAGE_FILEPATH;

			}
			targetLabel.setIcon(new ImageIcon(HomeBasePanel.class.getResource(filepath)));
		}
	}
	
	/**
	 * Given a location on the map, return the label containing
	 * the image for that location on the map panel.
	 * @param location The location for which a label is retrieved.
	 * @return The label at the given location.
	 */
	private JLabel getLabelAtLocation(Location location) {
		JLabel returnLabel;
		switch (location) {
		case NORTH: returnLabel = lblNorth; break;
		case EAST: returnLabel = lblEast; break;
		case SOUTH: returnLabel = lblSouth; break;
		case WEST: returnLabel = lblWest; break;
		default: returnLabel = new JLabel();
		}
		return returnLabel;
	}
	
	/**
	 * Updates variable fields in the statusPanel
	 * Called whenever the statusPanel is displayed
	 */
	private void refreshStatusPanel() {

		statusPanel.removeAll();
		
		for (Hero hero : team().getHeroes()) {			
			JPanel heroPanel = new JPanel();
			heroPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
			heroPanel.setLayout(null);
			statusPanel.add(heroPanel);
			
			JLabel lblHeroPortrait = new JLabel("");
			lblHeroPortrait.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblHeroPortrait.setBounds(30, 13, 150, 150);
			lblHeroPortrait.setIcon(new ImageIcon(HomeBasePanel.class.getResource(
										Image.heroImageFilepath(hero, 150, 150))));
			heroPanel.add(lblHeroPortrait);
			
			JLabel lblHeroName = new JLabel(hero.getName());
			lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHeroName.setBounds(10, 172, 190, 25);
			heroPanel.add(lblHeroName);

			JLabel lblHeroType = new JLabel(String.format("the %s", hero.getType()));
			lblHeroType.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHeroType.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroType.setBounds(10, 195, 190, 25);
			heroPanel.add(lblHeroType);
			
			JLabel lblSpecialAbility = new JLabel("Special Ability:");
			lblSpecialAbility.setHorizontalAlignment(SwingConstants.LEFT);
			lblSpecialAbility.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblSpecialAbility.setBounds(10, 228, 190, 24);
			heroPanel.add(lblSpecialAbility);
			
			JTextPane txtpnTheSpecialAbility = new JTextPane();
			txtpnTheSpecialAbility.setBackground(UIManager.getColor("Panel.background"));
			txtpnTheSpecialAbility.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtpnTheSpecialAbility.setText(hero.getSpecialAbility());
			txtpnTheSpecialAbility.setBounds(20, 251, 170, 38);
			heroPanel.add(txtpnTheSpecialAbility);
			
			JLabel lblCurrentHealth = new JLabel("Current health:");
			lblCurrentHealth.setHorizontalAlignment(SwingConstants.LEFT);
			lblCurrentHealth.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblCurrentHealth.setBounds(10, 300, 110, 24);
			heroPanel.add(lblCurrentHealth);

			JLabel lblTheHealth = new JLabel(String.format("%d/%d", hero.getCurrentHealth(), hero.getMaxHealth()));
			lblTheHealth.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblTheHealth.setHorizontalAlignment(SwingConstants.LEFT);
			lblTheHealth.setBounds(119, 300, 81, 24);
			heroPanel.add(lblTheHealth);
						
			JLabel lblAppliedHealingItem = new JLabel("Applied Healing Item:");
			lblAppliedHealingItem.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAppliedHealingItem.setHorizontalAlignment(SwingConstants.LEFT);
			lblAppliedHealingItem.setBounds(10, 335, 190, 24);
			heroPanel.add(lblAppliedHealingItem);
			
			if (hero.getAppliedHealingItem() == null) {
				JLabel lblNoHealingItem = new JLabel("No applied healing item.");
				lblNoHealingItem.setHorizontalAlignment(SwingConstants.LEFT);
				lblNoHealingItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNoHealingItem.setBounds(20, 370, 170, 24);
				heroPanel.add(lblNoHealingItem);
				
			} else {
				JLabel lblHealingItemImage = new JLabel("");
				lblHealingItemImage.setIcon(new ImageIcon(HomeBasePanel.class.getResource(
						Image.healingItemImageFilepath(hero.getAppliedHealingItem(), 25))));
				lblHealingItemImage.setBounds(20, 370, 25, 25);
				heroPanel.add(lblHealingItemImage);
				
				JLabel lblHealingItemType = new JLabel(hero.getAppliedHealingItem().toString());
				lblHealingItemType.setHorizontalAlignment(SwingConstants.LEFT);
				lblHealingItemType.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblHealingItemType.setBounds(55, 371, 145, 24);
				heroPanel.add(lblHealingItemType);
			}

			JLabel lblAppliedPowerUps = new JLabel("Applied Power Ups:");
			lblAppliedPowerUps.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblAppliedPowerUps.setHorizontalAlignment(SwingConstants.LEFT);
			lblAppliedPowerUps.setBounds(10, 406, 190, 24);
			heroPanel.add(lblAppliedPowerUps);
			
			HashMap<PowerUpType, Integer> powerUpTypeCounts = hero.powerUpTypeCounts();
			
			if (powerUpTypeCounts.size() == 0) {
				JLabel lblNoAppliedPower = new JLabel("No applied power ups.");
				lblNoAppliedPower.setHorizontalAlignment(SwingConstants.LEFT);
				lblNoAppliedPower.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNoAppliedPower.setBounds(20, 441, 170, 24);
				heroPanel.add(lblNoAppliedPower);
				
			} else {
				int xCoord = 20;
				for (PowerUpType powerUpType: powerUpTypeCounts.keySet()) {
					JLabel lblPowerUpImage = new JLabel("");
					lblPowerUpImage.setToolTipText(powerUpType.toString());
					lblPowerUpImage.setIcon(new ImageIcon(HomeBasePanel.class.getResource(
							Image.powerUpImageFilepath(powerUpType, 25))));
					lblPowerUpImage.setBounds(xCoord, 441, 25, 25);
					heroPanel.add(lblPowerUpImage);
					
					JLabel lblNumPowerUps = new JLabel(String.format("(%s)",
															powerUpTypeCounts.get(powerUpType)));
					lblNumPowerUps.setHorizontalAlignment(SwingConstants.CENTER);
					lblNumPowerUps.setFont(new Font("Tahoma", Font.PLAIN, 13));
					lblNumPowerUps.setBounds(xCoord - 5, 466, 35, 25);
					heroPanel.add(lblNumPowerUps);
					
					xCoord += 35;
					
				}
			}
		}
		
	}	
}
