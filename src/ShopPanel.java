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
import java.awt.Window;

import javax.swing.border.BevelBorder;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;


public class ShopPanel extends JPanel {
	
	/**
	 * A string representation of this panel, used by the CardLayout in Game.
	 */
	public static final String SHOP_PANEL_STRING = "Shop Panel";


	/**
	 * A string representation of the power up menu panel, used by the
	 * contentPanelCardLayout
	 */
	private static final String POWER_UP_MENU_STRING = "Power Up Menu Panel";
	
	/**
	 * A string representation of the healing item menu panel, used by the
	 * contentPanelCardLayout
	 */
	private static final String HEALING_ITEM_MENU_STRING = "Healing Item Menu Panel";
	
	/**
	 * A string representation of the inventory panel, used by the
	 * contentPanelCardLayout
	 */
	private static final String INVENTORY_PANEL_STRING = "Inventory Panel";
	
	/**
	 * A string representation of the power up map panel, used by the
	 * contentPanelCardLayout
	 */
	private static final String MAP_PANEL_STRING = "Map Panel";
	
	
	/**
	 * The main Game window that this panel is a part of. Used by event handlers
	 * to change the panel shown in game.
	 */
	private Game gameWindow;


	/**
	 * The main content panel in the ShopPanel. Has a CardLayout to contain
	 * a panel for each shop item, and an inventory panel.
	 */
	private JPanel contentPanel;
	
	/**
	 * The CardLayout of the content panel.
	 */
	private CardLayout contentPanelCardLayout;

	/**
	 * The side panel of the ShopPanel.
	 * Displays the team's current money, and has buttons to allow the user
	 * to select a shop item to view, view the inventory, or exit the shop.
	 */
	private JPanel sidePanel;
	
	public ShopPanel(Game gameWindow) {
		super();
		this.gameWindow = gameWindow;
		
		setPreferredSize(new Dimension(880, 610));
		setLayout(null);
		
		addTitle();
		addSidePanel();
		addContentPanel();
	}

	
	/**
	 * Adds the Shop title label to the top of the ShopPanel.
	 */
	private void addTitle() {
		JLabel lblShop = new JLabel("SHOP");
		lblShop.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblShop.setHorizontalAlignment(SwingConstants.CENTER);
		lblShop.setBounds(0, 0, 880, 75);
		add(lblShop);
	}
	
	
	
	/*
	 * ==========================================================================
	 * Side Panel
	 * ==========================================================================
	 */
	
	/**
	 * Adds the side panel and all its components to the ShopPanel.
	 */
	private void addSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setBounds(10, 86, 215, 513);
		add(sidePanel);
		sidePanel.setLayout(null);	
		
		JLabel lblNewLabel = new JLabel("Gold: 100");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 11, 120, 25);
		sidePanel.add(lblNewLabel);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInventory.setBounds(10, 47, 195, 30);
		sidePanel.add(btnInventory);
		
		JButton btnViewPowerUps = new JButton("View Power Ups");
		btnViewPowerUps.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewPowerUps.setBounds(10, 128, 195, 30);
		sidePanel.add(btnViewPowerUps);
		
		JButton btnViewHealingItems = new JButton("View Healing Items");
		btnViewHealingItems.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewHealingItems.setBounds(10, 169, 195, 30);
		sidePanel.add(btnViewHealingItems);
		
		JButton btnViewMaps = new JButton("View Maps");
		btnViewMaps.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewMaps.setBounds(10, 210, 195, 30);
		sidePanel.add(btnViewMaps);
		
		JButton btnExitShop = new JButton("Exit Shop");
		btnExitShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setPanel(HomeBasePanel.HOME_BASE_PANEL_STRING);
			}
		});
		btnExitShop.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnExitShop.setBounds(47, 462, 120, 30);
		sidePanel.add(btnExitShop);
		
		
	}
	
	
	/*
	 * ==========================================================================
	 *  Content Panel
	 * ==========================================================================
	 */
	
	
	/**
	 * Adds the main content panel and all its components to the ShopPanel.
	 * Uses a CardLayout to contain a panel for each shop item, and an
	 * inventory panel.
	 */
	private void addContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBounds(235, 86, 635, 513);
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));

		add(contentPanel);
		contentPanel.setLayout(new CardLayout(0, 0));
		
		contentPanelCardLayout = new CardLayout();
		contentPanel.setLayout(contentPanelCardLayout);
		
		addInventoryPanel();
		
		addPowerUpMenuPanel();
		addHealingItemMenuPanel();
		
		for (PowerUp powerUp: gameWindow.getGame().ALL_POWER_UPS) {
			addPowerUpPanel(powerUp);
		}
		
		for (HealingItem healingItem: gameWindow.getGame().ALL_HEALING_ITEMS) {
			addHealingItemPanel(healingItem);
		}
		
		addMapPanel();
		
	}
	
	
	
	/**
	 * Creates a panel showing the team's current inventory, and adds it to
	 * the contentPanelCardLayout.
	 */
	private void addInventoryPanel() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Creates a panel for the power up menu, containing an icon and button for each
	 * power up, and adds it to the contentPanelCardLayout.
	 */
	private void addPowerUpMenuPanel() {
		JPanel powerUpMenuPanel = new JPanel();
		
		powerUpMenuPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		for (PowerUp powerUp: gameWindow.getGame().ALL_POWER_UPS) {
			JPanel powerUpPanel = new JPanel();
			powerUpPanel.setLayout(null);
			
			JLabel lblPowerUpImage = new JLabel("");
			// TODO add power up icon
			lblPowerUpImage.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblPowerUpImage.setBounds(83, 33, 150, 150);
			powerUpPanel.add(lblPowerUpImage);
			
			JButton btnPowerUp = new JButton(powerUp.getType().toString());
			btnPowerUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnPowerUp.setBounds(93, 194, 130, 30);
			powerUpPanel.add(btnPowerUp);
			
			powerUpMenuPanel.add(powerUpPanel);
		}
		
		contentPanel.add(powerUpMenuPanel, POWER_UP_MENU_STRING);
		
	}
	
	/**
	 * Creates a panel for the healing item menu, containing an icon and button for each
	 * healing item, and adds it to the contentPanelCardLayout.
	 */
	private void addHealingItemMenuPanel() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Creates a information panel for the given power up, and adds it to
	 * the contentPanelCardLayout.
	 * @param powerUp	An instance of the PowerUp subclass for which the
	 * 					information panel is being made.
	 */
	private void addPowerUpPanel(PowerUp powerUp) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Creates a information panel for the given healing item, and adds it to
	 * the contentPanelCardLayout.
	 * @param healingItem	An instance of the HealingItem subclass for which the
	 * 						information panel is being made.
	 */
	private void addHealingItemPanel(HealingItem healingItem) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Creates an information panel for a map, and adds it to the
	 * contentPanelCardLayout.
	 */
	private void addMapPanel() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
