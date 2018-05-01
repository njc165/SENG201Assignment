import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.border.BevelBorder;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JTextField;
import java.awt.CardLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextPane;
import javax.swing.UIManager;


public class ShopPanel extends JPanel implements Refreshable {
	
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

	/**
	 * A JLabel showing the number of coins the team currently has.
	 * Should be updated when the ShopPanel is refreshed.
	 */
	private JLabel lblCurrentCoinsAmount;
	
	
	public ShopPanel(Game gameWindow) {
		super();
		this.gameWindow = gameWindow;
		
		setPreferredSize(new Dimension(880, 610));
		setLayout(null);
		
		addTitle();
		addSidePanel();
		addContentPanel();
	}
	
	public void refresh() {
		
		contentPanel.removeAll();
		addSubPanels();
		
		lblCurrentCoinsAmount.setText(Integer.toString(team().getCurrentMoney()));
		
	}
	
	private Team team() {
		return gameWindow.getGame().getTeam();
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
		
		JLabel lblTeamsCurrentCoins = new JLabel("Team's current coins:");
		lblTeamsCurrentCoins.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTeamsCurrentCoins.setBounds(10, 11, 195, 25);
		sidePanel.add(lblTeamsCurrentCoins);
		
		JLabel lblCoinImage = new JLabel("");
		lblCoinImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCoinImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCoinImage.setBounds(56, 47, 38, 38);
		sidePanel.add(lblCoinImage);
		
		lblCurrentCoinsAmount = new JLabel("20");
		lblCurrentCoinsAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCurrentCoinsAmount.setBounds(104, 47, 63, 38);
		sidePanel.add(lblCurrentCoinsAmount);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, INVENTORY_PANEL_STRING);
			}
		});
		btnInventory.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnInventory.setBounds(10, 126, 195, 30);
		sidePanel.add(btnInventory);
		
		JButton btnViewPowerUps = new JButton("View Power Ups");
		btnViewPowerUps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, POWER_UP_MENU_STRING);
			}
		});
		btnViewPowerUps.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewPowerUps.setBounds(10, 207, 195, 30);
		sidePanel.add(btnViewPowerUps);
		
		JButton btnViewHealingItems = new JButton("View Healing Items");
		btnViewHealingItems.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, HEALING_ITEM_MENU_STRING);
			}
		});
		btnViewHealingItems.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewHealingItems.setBounds(10, 248, 195, 30);
		sidePanel.add(btnViewHealingItems);
		
		JButton btnViewMaps = new JButton("View Maps");
		btnViewMaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, MAP_PANEL_STRING);
			}
		});
		btnViewMaps.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnViewMaps.setBounds(10, 289, 195, 30);
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
		
		contentPanelCardLayout = new CardLayout();
		contentPanel.setLayout(contentPanelCardLayout);
		
		// remove when finished testing
//		addSubPanels();
	}
	
	
	/**
	 * Adds all the panels and their components to the contentPanelCardLayout.
	 */
	private void addSubPanels() {
		
		addInventoryPanel();
		
		addPowerUpMenuPanel();
		addHealingItemMenuPanel();
		
		for (PowerUp powerUp: GameEnvironment.ALL_POWER_UPS) {
			addPowerUpPanel(powerUp);
		}
		
		for (HealingItem healingItem: GameEnvironment.ALL_HEALING_ITEMS) {
			addHealingItemPanel(healingItem);
		}
		
		addMapPanel();			
	}
	
	
	
	/**
	 * Creates a panel showing the team's current inventory, and adds it to
	 * the contentPanelCardLayout.
	 */
	private void addInventoryPanel() {
		JPanel inventoryPanel = new JPanel();
		
		inventoryPanel.setLayout(null);
		
		JLabel lblPowerUps = new JLabel("Power Ups");
		lblPowerUps.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPowerUps.setBounds(46, 32, 181, 30);
		inventoryPanel.add(lblPowerUps);
		
		int yCoord = 83;
		for (PowerUp powerUp: GameEnvironment.ALL_POWER_UPS) {
			JLabel lblPowerUpIcon = new JLabel("");
			// TODO add power up icons
			lblPowerUpIcon.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPowerUpIcon.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblPowerUpIcon.setBounds(56, yCoord, 38, 38);
			inventoryPanel.add(lblPowerUpIcon);
			
			JLabel lblPowerUpNumOwned = new JLabel(String.format("(%s)  %s",
													team().numPowerUpsOwned(powerUp.getType()),
													powerUp));
			lblPowerUpNumOwned.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPowerUpNumOwned.setBounds(104, yCoord, 187, 38);
			inventoryPanel.add(lblPowerUpNumOwned);
			
			yCoord += 50;
		}
		
		JLabel lblHealingItems = new JLabel("Healing Items");
		lblHealingItems.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHealingItems.setBounds(329, 32, 181, 30);
		inventoryPanel.add(lblHealingItems);
		
		yCoord = 83;
		for (HealingItem healingItem: GameEnvironment.ALL_HEALING_ITEMS) {
			JLabel lblHealingItemIcon = new JLabel("");
			// TODO add healing item icons
			lblHealingItemIcon.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblHealingItemIcon.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblHealingItemIcon.setBounds(339, yCoord, 38, 38);
			inventoryPanel.add(lblHealingItemIcon);
			
			JLabel lblHealingItemNumOwned = new JLabel(String.format("(%s)  %s",
														team().numHealingItemsOwned(healingItem.getName()),
														healingItem));
			lblHealingItemNumOwned.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblHealingItemNumOwned.setBounds(387, yCoord, 187, 38);
			inventoryPanel.add(lblHealingItemNumOwned);
			
			yCoord += 50;
		}
		
		JLabel lblMaps = new JLabel("Maps");
		lblMaps.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMaps.setBounds(46, 312, 181, 30);
		inventoryPanel.add(lblMaps);
		
		JLabel lblMapIcon = new JLabel("");
		// TODO add map icon
		lblMapIcon.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMapIcon.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMapIcon.setBounds(56, 363, 38, 38);
		inventoryPanel.add(lblMapIcon);
		
		JLabel lblMapNumOwned = new JLabel(String.format("(%s)  Map",
														 team().getNumMaps()));
		lblMapNumOwned.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMapNumOwned.setBounds(104, 363, 187, 38);
		inventoryPanel.add(lblMapNumOwned);
		
		contentPanel.add(inventoryPanel, INVENTORY_PANEL_STRING);
		
	}
	
	/**
	 * Creates a panel for the power up menu, containing an icon and button for each
	 * power up, and adds it to the contentPanelCardLayout.
	 */
	private void addPowerUpMenuPanel() {
		JPanel powerUpMenuPanel = new JPanel();
		
		powerUpMenuPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		for (PowerUp powerUp: GameEnvironment.ALL_POWER_UPS) {
			JPanel powerUpPanel = new JPanel();
			powerUpPanel.setLayout(null);
			
			JLabel lblPowerUpImage = new JLabel("");
			// TODO add power up icon
			lblPowerUpImage.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblPowerUpImage.setBounds(83, 33, 150, 150);
			powerUpPanel.add(lblPowerUpImage);
			
			JButton btnPowerUp = new JButton(powerUp.toString());
			btnPowerUp.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnPowerUp.setBounds(68, 194, 180, 30);
			btnPowerUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPanelCardLayout.show(contentPanel, powerUp.toString());
				}
			});
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
		JPanel healingItemMenuPanel = new JPanel();
		
		healingItemMenuPanel.setLayout(new GridLayout(2, 2, 0, 0));
		
		for (HealingItem healingItem: GameEnvironment.ALL_HEALING_ITEMS) {
			JPanel healingItemPanel = new JPanel();
			healingItemPanel.setLayout(null);
			
			JLabel lblHealingItemImage = new JLabel("");
			// TODO add healing item icon
			lblHealingItemImage.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblHealingItemImage.setBounds(83, 33, 150, 150);
			healingItemPanel.add(lblHealingItemImage);
			
			JButton btnHealingItem = new JButton(healingItem.toString());
			btnHealingItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnHealingItem.setBounds(68, 194, 180, 30);
			btnHealingItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					contentPanelCardLayout.show(contentPanel, healingItem.toString());
				}
			});
			healingItemPanel.add(btnHealingItem);
			
			healingItemMenuPanel.add(healingItemPanel);
		}
		
		contentPanel.add(healingItemMenuPanel, HEALING_ITEM_MENU_STRING);
		
	}


	/**
	 * Creates a information panel for the given power up, and adds it to
	 * the contentPanelCardLayout, using the String representation of the power up.
	 * @param powerUp	An instance of the PowerUp subclass for which the
	 * 					information panel is being made.
	 */
	private void addPowerUpPanel(PowerUp powerUp) {
		JPanel powerUpInfoPanel = new JPanel();
		powerUpInfoPanel.setLayout(null);
		
		JLabel lblPowerUpImage = new JLabel("");
		// TODO add power up icons
		lblPowerUpImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblPowerUpImage.setBounds(68, 50, 150, 150);
		powerUpInfoPanel.add(lblPowerUpImage);
				
		JLabel lblType = new JLabel(powerUp.toString());
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblType.setBounds(234, 50, 330, 37);
		powerUpInfoPanel.add(lblType);
		
		JLabel lblPrice = new JLabel(Integer.toString(powerUp.getCost(team().hasDiscountHero())));
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(336, 120, 50, 38);
		powerUpInfoPanel.add(lblPrice);
		
		JLabel lblCoinImage = new JLabel("");
		// TODO add coin image
		lblCoinImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCoinImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCoinImage.setBounds(288, 120, 38, 38);
		powerUpInfoPanel.add(lblCoinImage);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setEnabled(team().getCurrentMoney() >= powerUp.getCost(team().hasDiscountHero()));
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PowerUp newPowerUp = (PowerUp) Util.instantiate(powerUp.getClass());
				team().buyPowerUp(newPowerUp);
				refresh();
				contentPanelCardLayout.show(contentPanel, powerUp.toString());
			}
		});
		btnPurchase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPurchase.setBounds(407, 124, 157, 30);
		powerUpInfoPanel.add(btnPurchase);
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescription.setBackground(UIManager.getColor("Panel.background"));
		txtpnDescription.setText(powerUp.getDescription());
		txtpnDescription.setBounds(33, 234, 565, 176);
		powerUpInfoPanel.add(txtpnDescription);
		
		JButton btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, POWER_UP_MENU_STRING);
			}
		});
		btnBackToMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBackToMenu.setBounds(68, 442, 157, 30);
		powerUpInfoPanel.add(btnBackToMenu);
		
		contentPanel.add(powerUpInfoPanel, powerUp.toString());
		
	}
	
	/**
	 * Creates a information panel for the given healing item, and adds it to
	 * the contentPanelCardLayout, using the String representation of the healing item.
	 * @param healingItem	An instance of the HealingItem subclass for which the
	 * 						information panel is being made.
	 */
	private void addHealingItemPanel(HealingItem healingItem) {
		JPanel healingItemInfoPanel = new JPanel();
		healingItemInfoPanel.setLayout(null);
		
		JLabel lblhealingItemImage = new JLabel("");
		// TODO add healing item icons
		lblhealingItemImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblhealingItemImage.setBounds(68, 50, 150, 150);
		healingItemInfoPanel.add(lblhealingItemImage);
				
		JLabel lblType = new JLabel(healingItem.toString());
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblType.setBounds(234, 50, 330, 37);
		healingItemInfoPanel.add(lblType);
		
		JLabel lblPrice = new JLabel(Integer.toString(healingItem.getCost(team().hasDiscountHero())));
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(336, 120, 50, 38);
		healingItemInfoPanel.add(lblPrice);
		
		JLabel lblCoinImage = new JLabel("");
		// TODO add coin image
		lblCoinImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCoinImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCoinImage.setBounds(288, 120, 38, 38);
		healingItemInfoPanel.add(lblCoinImage);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setEnabled(team().getCurrentMoney() >= healingItem.getCost(team().hasDiscountHero()));
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HealingItem newHealingItem = (HealingItem) Util.instantiate(healingItem.getClass());
				team().buyHealingItem(newHealingItem);
				refresh();
				contentPanelCardLayout.show(contentPanel, healingItem.toString());
			}
		});
		btnPurchase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPurchase.setBounds(407, 124, 157, 30);
		healingItemInfoPanel.add(btnPurchase);
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescription.setBackground(UIManager.getColor("Panel.background"));
		txtpnDescription.setText(healingItem.shopDescriptionGUI());
		txtpnDescription.setBounds(33, 234, 565, 176);
		healingItemInfoPanel.add(txtpnDescription);
		
		JButton btnBackToMenu = new JButton("Back To Menu");
		btnBackToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, HEALING_ITEM_MENU_STRING);
			}
		});
		btnBackToMenu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBackToMenu.setBounds(68, 442, 157, 30);
		healingItemInfoPanel.add(btnBackToMenu);
		
		contentPanel.add(healingItemInfoPanel, healingItem.toString());
	}
	
	/**
	 * Creates an information panel for a map, and adds it to the
	 * contentPanelCardLayout.
	 */
	private void addMapPanel() {
		JPanel mapInfoPanel = new JPanel();
		mapInfoPanel.setLayout(null);
		
		JLabel lblMapImage = new JLabel("");
		// TODO add map icon
		lblMapImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMapImage.setBounds(68, 50, 150, 150);
		mapInfoPanel.add(lblMapImage);
				
		JLabel lblMap = new JLabel("Map");
		lblMap.setHorizontalAlignment(SwingConstants.CENTER);
		lblMap.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMap.setBounds(234, 50, 330, 37);
		mapInfoPanel.add(lblMap);
		
		JLabel lblPrice = new JLabel(Integer.toString(Map.getCost(team().hasDiscountHero())));
		lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPrice.setBounds(336, 120, 50, 38);
		mapInfoPanel.add(lblPrice);
		
		JLabel lblCoinImage = new JLabel("");
		// TODO add coin image
		lblCoinImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblCoinImage.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCoinImage.setBounds(288, 120, 38, 38);
		mapInfoPanel.add(lblCoinImage);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.setEnabled(team().getCurrentMoney() >= Map.getCost(team().hasDiscountHero()));
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				team().buyMap();
				refresh();
				contentPanelCardLayout.show(contentPanel, MAP_PANEL_STRING);
			}
		});
		btnPurchase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPurchase.setBounds(407, 124, 157, 30);
		mapInfoPanel.add(btnPurchase);
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnDescription.setBackground(UIManager.getColor("Panel.background"));
		txtpnDescription.setText(Map.getDescription());
		txtpnDescription.setBounds(33, 234, 565, 176);
		mapInfoPanel.add(txtpnDescription);
		
		contentPanel.add(mapInfoPanel, MAP_PANEL_STRING);
	}
	
}
