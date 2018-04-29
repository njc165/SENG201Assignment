import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
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
	public static final String STATUS_PANEL = "Status Panel";
	
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
	 * A string description of mapPanel, used by contentPanelCardLayout.
	 */
	private final String MAP_PANEL_NAME = "Map Panel";
	
	/**
	 * A panel component of contentPanel
	 */
	private JPanel statusPanel;
	
	/**
	 * A string description of statusPanel, used by contentPanelCardLayout.
	 */
	private final String STATUS_PANEL_NAME = "Status Panel";
	
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
		
		
	public HomeBasePanel(Game game) {
		super();
		this.gameWindow = game;
		
		setLayout(null);
		setPreferredSize(new Dimension(880, 610));
		
		addTitlePanel();
		addSidePanel();
		addContentPanel();
		}
	
	private Team getTeam() {
		return gameWindow.getGame().getTeam();
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
		sidePanel.setLayout(null);
		
		JLabel lblCurrentCity = new JLabel();
		lblCurrentCity.setBounds(65, 11, 52, 25);
		lblCurrentCity.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurrentCity.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentCity.setText("City X");
		sidePanel.add(lblCurrentCity);
		
		btnDisplayCity = new JButton("Display City");
		btnDisplayCity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPanelCardLayout.show(contentPanel, MAP_PANEL_NAME);
				btnDisplayCity.setEnabled(false);
				btnViewStatus.setEnabled(true);
			}
		});
		btnDisplayCity.setBounds(36, 97, 120, 25);
		sidePanel.add(btnDisplayCity);
		
		btnViewStatus = new JButton("View Team Status");
		btnViewStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, STATUS_PANEL_NAME);
				btnViewStatus.setEnabled(false);
				btnDisplayCity.setEnabled(true);
			}
		});
		btnViewStatus.setBounds(36, 157, 120, 25);
		sidePanel.add(btnViewStatus);
		
		btnUseMap = new JButton("Use Map");
		btnUseMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.getGame().getCurrentCity().setAllDiscovered();
				getTeam().setNumMaps(getTeam().getNumMaps() - 1);
			}
		});
		btnUseMap.setBounds(36, 217, 120, 25);
		if (getTeam().getNumMaps() <= 0) {
			btnUseMap.setEnabled(false);
		}
		sidePanel.add(btnUseMap);
		
		JLabel lblOwned = new JLabel(String.format("Owned: %d", getTeam().getNumMaps()));
		lblOwned.setHorizontalAlignment(SwingConstants.CENTER);
		lblOwned.setBounds(36, 242, 120, 14);
		sidePanel.add(lblOwned);
				
		JTextPane txtRandomEvent = new JTextPane();
		txtRandomEvent.setBackground(new Color(240, 240, 240));
		txtRandomEvent.setEditable(false);
		txtRandomEvent.setText("Sample Text\r\nThe villagers aid you in your fight againts the villains!\r\nYou received: Alicorn Dust");
		txtRandomEvent.setBounds(10, 340, 170, 97);
		sidePanel.add(txtRandomEvent);

		JLabel lblRandomEvent = new JLabel("Random Event!");
		lblRandomEvent.setBounds(10, 326, 82, 14);
		sidePanel.add(lblRandomEvent);
	
		add(sidePanel);	
		}
	
	private void addContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBounds(190, 75, 690, 535);
		contentPanelCardLayout = new CardLayout(0, 0);
		contentPanel.setLayout(contentPanelCardLayout);

		Hero hero1 = getTeam().getHeroes().get(0);
				
		mapPanel = new JPanel();
		contentPanel.add(mapPanel, MAP_PANEL_NAME);
		mapPanel.setLayout(new GridLayout(3, 3, 0, 0));
		
		JLabel lblNorthWest = new JLabel("");
		lblNorthWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblNorthWest);
		
		JLabel lblNorth = new JLabel("");
		lblNorth.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblNorth);
		
		JLabel lblNorthEast = new JLabel("");
		lblNorthEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblNorthEast);
		
		JLabel lblEast = new JLabel("");
		lblEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblEast);
		
		JLabel lblWest = new JLabel("");
		lblWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblWest);
		
		JLabel lblCentre = new JLabel("");
		lblCentre.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblCentre);
		
		JLabel lblSouthWest = new JLabel("");
		lblSouthWest.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblSouthWest);
		
		JLabel lblSouth = new JLabel("");
		lblSouth.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblSouth);
		
		JLabel lblSouthEast = new JLabel("");
		lblSouthEast.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/mountains.png")));
		mapPanel.add(lblSouthEast);
		
		statusPanel = new JPanel();
		contentPanel.add(statusPanel, STATUS_PANEL_NAME);
		statusPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel pnlHero1Status = new JPanel();
		statusPanel.add(pnlHero1Status);
		pnlHero1Status.setLayout(null);
		
		JLabel imgHero1Portrait = new JLabel("");
		imgHero1Portrait.setBounds(33, 11, 150, 150);
		imgHero1Portrait.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		imgHero1Portrait.setIcon(new ImageIcon(HomeBasePanel.class.getResource("/img/bulwark_portrait.png"))); //update this to 150x150 version
		pnlHero1Status.add(imgHero1Portrait);
		
		JLabel lblHero1Name = new JLabel(String.format("%s the %s", hero1.getName(),
																	hero1.getType()));
		lblHero1Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHero1Name.setHorizontalAlignment(SwingConstants.CENTER);
		lblHero1Name.setBounds(10, 164, 199, 32);
		pnlHero1Status.add(lblHero1Name);
		
		JLabel lblHero1Ability = new JLabel(String.format("%s", hero1.getSpecialAbility()));
		lblHero1Ability.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHero1Ability.setHorizontalAlignment(SwingConstants.CENTER);
		lblHero1Ability.setBounds(20, 203, 181, 53);
		pnlHero1Status.add(lblHero1Ability);
		
		JLabel lblHero1Health = new JLabel(String.format("Health: %d/%d", hero1.getCurrentHealth(), hero1.getMaxHealth()));
		lblHero1Health.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHero1Health.setHorizontalAlignment(SwingConstants.CENTER);
		lblHero1Health.setBounds(10, 267, 199, 44);
		pnlHero1Status.add(lblHero1Health);
		
		JLabel lblHero1HealingItem = new JLabel(String.format("Healing Item:\r\n%s", hero1.getAppliedHealingItem().getName()));
		lblHero1HealingItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHero1HealingItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblHero1HealingItem.setBounds(33, 318, 165, 32);
		pnlHero1Status.add(lblHero1HealingItem);
		
		JLabel lblHero1PowerUp = new JLabel("Power Ups:");
		lblHero1PowerUp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHero1PowerUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblHero1PowerUp.setBounds(10, 361, 210, 32);
		pnlHero1Status.add(lblHero1PowerUp);
		
		JTextPane txtHero1PowerUps = new JTextPane();
		txtHero1PowerUps.setBackground(new Color(240, 240, 240));
		for (PowerUp pu : hero1.getActivePowerUps()) {
			txtHero1PowerUps.setText(txtHero1PowerUps.getText() + String.format("\n%s", pu.getType().toString()));
		}
		txtHero1PowerUps.setEditable(false);
		txtHero1PowerUps.setBounds(10, 402, 210, 122);
		pnlHero1Status.add(txtHero1PowerUps);
		
		if (getTeam().getHeroes().size() >= 2) {
			
			Hero hero2 = getTeam().getHeroes().get(1);
			
			JPanel pnlHero2Status = new JPanel();
			statusPanel.add(pnlHero2Status);
			pnlHero2Status.setLayout(null);
			
			JLabel imgHero2Portrait = new JLabel("");
			imgHero2Portrait.setBounds(33, 24, 165, 134);
			imgHero2Portrait.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pnlHero2Status.add(imgHero2Portrait);
			
			JLabel lblHero2Name = new JLabel(String.format("%s the %s", hero2.getName(),
																		hero2.getType()));
			lblHero2Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero2Name.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero2Name.setBounds(10, 164, 199, 32);
			pnlHero2Status.add(lblHero2Name);
			
			JLabel lblHero2Ability = new JLabel(String.format("%s", hero2.getSpecialAbility()));
			lblHero2Ability.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero2Ability.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero2Ability.setBounds(20, 203, 181, 53);
			pnlHero2Status.add(lblHero2Ability);
			
			JLabel lblHero2Health = new JLabel(String.format("Health: %d/%d", hero2.getCurrentHealth(), hero2.getMaxHealth()));
			lblHero2Health.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero2Health.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero2Health.setBounds(10, 267, 199, 44);
			pnlHero2Status.add(lblHero2Health);
			
			JLabel lblHero2HealingItem = new JLabel(String.format("Healing Item:\r\n%s", hero2.getAppliedHealingItem().getName()));
			lblHero2HealingItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero2HealingItem.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero2HealingItem.setBounds(33, 318, 165, 32);
			pnlHero2Status.add(lblHero2HealingItem);
			
			JLabel lblHero2PowerUp = new JLabel("Power Ups:");
			lblHero2PowerUp.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero2PowerUp.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero2PowerUp.setBounds(10, 361, 210, 32);
			pnlHero2Status.add(lblHero2PowerUp);
			
			JTextPane txtHero2PowerUps = new JTextPane();
			txtHero2PowerUps.setBackground(new Color(240, 240, 240));
			for (PowerUp pu : hero2.getActivePowerUps()) {
				txtHero2PowerUps.setText(txtHero2PowerUps.getText() + String.format("\n%s", pu.getType().toString()));
			}
			txtHero2PowerUps.setEditable(false);
			txtHero2PowerUps.setBounds(10, 402, 210, 122);
			pnlHero2Status.add(txtHero2PowerUps);
			
		}
		
		if (getTeam().getHeroes().size() >= 3) {
			
			Hero hero3 = getTeam().getHeroes().get(2);
			
			JPanel pnlHero3Status = new JPanel();
			statusPanel.add(pnlHero3Status);
			pnlHero3Status.setLayout(null);
			
			JLabel imgHero3Portrait = new JLabel("");
			imgHero3Portrait.setBounds(33, 24, 165, 134);
			imgHero3Portrait.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pnlHero3Status.add(imgHero3Portrait);
			
			JLabel lblHero3Name = new JLabel(String.format("%s the %s", hero3.getName(),
																		hero3.getType()));
			lblHero3Name.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero3Name.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero3Name.setBounds(10, 164, 199, 32);
			pnlHero3Status.add(lblHero3Name);
			
			JLabel lblHero3Ability = new JLabel(String.format("%s", hero3.getSpecialAbility()));
			lblHero3Ability.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero3Ability.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero3Ability.setBounds(20, 203, 181, 53);
			pnlHero3Status.add(lblHero3Ability);
			
			JLabel lblHero3Health = new JLabel(String.format("Health: %d/%d", hero3.getCurrentHealth(), hero3.getMaxHealth()));
			lblHero3Health.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero3Health.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero3Health.setBounds(10, 267, 199, 44);
			pnlHero3Status.add(lblHero3Health);
			
			JLabel lblHero3HealingItem = new JLabel(String.format("Healing Item:\r\n%s", hero3.getAppliedHealingItem().toString()));
			lblHero3HealingItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero3HealingItem.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero3HealingItem.setBounds(33, 318, 165, 32);
			pnlHero3Status.add(lblHero3HealingItem);
			
			JLabel lblHero3PowerUp = new JLabel("Power Ups:");
			lblHero3PowerUp.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHero3PowerUp.setHorizontalAlignment(SwingConstants.CENTER);
			lblHero3PowerUp.setBounds(10, 361, 210, 32);
			pnlHero3Status.add(lblHero3PowerUp);
			
			JTextPane txtHero3PowerUps = new JTextPane();
			txtHero3PowerUps.setBackground(new Color(240, 240, 240));
			for (PowerUp pu : hero3.getActivePowerUps()) {
				txtHero3PowerUps.setText(txtHero3PowerUps.getText() + String.format("\n%s", pu.getType().toString()));
			}
			txtHero3PowerUps.setEditable(false);
			txtHero3PowerUps.setBounds(10, 402, 210, 122);
			pnlHero3Status.add(txtHero3PowerUps);
			
		}
		
		contentPanelCardLayout.show(contentPanel, MAP_PANEL_NAME);
		btnDisplayCity.setEnabled(false);
		add(contentPanel);
	}
}
