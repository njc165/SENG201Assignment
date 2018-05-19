import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.BorderLayout;

/**
 * Game objects represent entire playthroughs of the game. In any
 * playthrough of the game, exacty one Game object should be
 * created. They contain all components related to the game.
 */
public class Game {
	
	/**
	 * The GameEnvironment object for the current game.
	 */
	private GameEnvironment game;
	
	/**
	 * The frame of the main game window.
	 */
	private JFrame frame;
	
	/**
	 * The main panel in the game window.
	 * Fills the whole frame, and contains all the content of the game. 
	 */
	private JPanel mainPanel;
	
	/**
	 * The CardLayout of the main panel, which contains all the sub panels
	 * of the game.
	 */
	private CardLayout cardLayout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game window = new Game();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Game() {
		
		MyFont.registerAll();
//		UIManager.put("Panel.background", new Color(0, 0, 0));
		
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(200, 50, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);
		
		addPanels();
		
		cardLayout.show(mainPanel, SetUpPanel.SET_UP_PANEL_STRING);
	}
	
	/**
	 * Add all major subpanels to the main panel.
	 */
	public void addPanels() {
		SetUpPanel setUpPanel = new SetUpPanel(this);
		mainPanel.add(setUpPanel, SetUpPanel.SET_UP_PANEL_STRING);
		
		HomeBasePanel homeBasePanel = new HomeBasePanel(this);
		mainPanel.add(homeBasePanel, HomeBasePanel.HOME_BASE_PANEL_STRING);
		
		ShopPanel shopPanel = new ShopPanel(this);
		mainPanel.add(shopPanel, ShopPanel.SHOP_PANEL_STRING);
		
		HospitalPanel hospitalPanel = new HospitalPanel(this);
		mainPanel.add(hospitalPanel, HospitalPanel.HOSPITAL_PANEL_STRING);
		
		PowerUpDenPanel powerUpDenPanel = new PowerUpDenPanel(this);
		mainPanel.add(powerUpDenPanel, PowerUpDenPanel.POWER_UP_DEN_PANEL_STRING);
		
		VillainsLairPanel villainsLairPanel = new VillainsLairPanel(this);
		mainPanel.add(villainsLairPanel, VillainsLairPanel.VILLAINS_LAIR_PANEL_STRING);
	}
	
	/**
	 * Takes a string representation of a panel in the main panel card layout,
	 * and sets that panel to be visible.
	 * @param panelString	A string representation of the panel to be shown.
	 */
	public void setPanel(String panelString) {
		
		cardLayout.show(mainPanel, panelString);
		game.healHeroes();
		((Refreshable) visiblePanel()).refresh();
		
		if (visiblePanel() instanceof HomeBasePanel) {
			game.currentCity().setCurrentLocation(Location.CENTRE);
			
			String randomEventText = game.randomEvent();
			if (randomEventText != null) {
				JOptionPane.showMessageDialog(frame, randomEventText);
			}
		}
	}
	
	/**
	 * Ends the current game by displaying a victory panel
	 * or defeat panel as appropriate.
	 * @param won true if the team won the game, false otherwise.
	 */
	public void endGame(boolean won) {
		if (won) {
			VictoryPanel victoryPanel = new VictoryPanel(this);
			mainPanel.add(victoryPanel, VictoryPanel.VICTORY_PANEL_STRING);
			cardLayout.show(mainPanel, VictoryPanel.VICTORY_PANEL_STRING);
		} else {
			DefeatPanel defeatPanel = new DefeatPanel(this);
			mainPanel.add(defeatPanel, DefeatPanel.DEFEAT_PANEL_STRING);
			cardLayout.show(mainPanel, DefeatPanel.DEFEAT_PANEL_STRING);
		}
	}
	
	/**
	 * Determines which panel in the main panel card layout of the
	 * game window is currently visible.
	 * @return	The currently visible panel in the main game window.
	 */
	private JPanel visiblePanel() {
		JPanel visiblePanel = null;
		for (Component panel: mainPanel.getComponents()) {
			if (panel.isVisible()) {
				visiblePanel = (JPanel) panel;
			}
		}
		return visiblePanel;
	}

	/**
	 * Getter method for game.
	 * @return The value of game.
	 */
	public GameEnvironment getGame() {
		return game;
	}

	/**
	 * Setter method for game.
	 * @param game The new value of game to set.
	 */
	public void setGame(GameEnvironment game) {
		this.game = game;
	}
	
	/**
	 * Getter method for frame.
	 * @return The value of frame.
	 */
	public JFrame getFrame() {
		return frame;
	}
}
