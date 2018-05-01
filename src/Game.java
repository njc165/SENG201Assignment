import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class Game {
	
	/**
	 * The GameEnvironment object for the current game.
	 */
	private GameEnvironment game;

	private JFrame frame;
	private JPanel mainPanel;
	private CardLayout cardLayout;
	
	private SetUpPanel startScreenPanel;
	private HomeBasePanel homeBasePanel;
	private ShopPanel shopPanel;
	private HospitalPanel hospitalPanel;
	private PowerUpDenPanel powerUpDenPanel;
	private VillainsLairPanel villainsLairPanel;	
	

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
	
	public void addPanels() {
		startScreenPanel = new SetUpPanel(this);
		mainPanel.add(startScreenPanel, SetUpPanel.SET_UP_PANEL_STRING);
		
		homeBasePanel = new HomeBasePanel(this);
		mainPanel.add(homeBasePanel, HomeBasePanel.HOME_BASE_PANEL_STRING);
		
		shopPanel = new ShopPanel(this);
		mainPanel.add(shopPanel, ShopPanel.SHOP_PANEL_STRING);
		
		hospitalPanel = new HospitalPanel(this);
		mainPanel.add(hospitalPanel, HospitalPanel.HOSPITAL_PANEL_STRING);
		
		powerUpDenPanel = new PowerUpDenPanel(this);
		mainPanel.add(powerUpDenPanel, PowerUpDenPanel.POWER_UP_DEN_PANEL_STRING);
		
		villainsLairPanel = new VillainsLairPanel(this);
		mainPanel.add(villainsLairPanel, VillainsLairPanel.VILLAINS_LAIR_PANEL_STRING);
	}
	
	/**
	 * @param panelString
	 */
	public void setPanel(String panelString) {
		cardLayout.show(mainPanel, panelString);
		((Refreshable) visiblePanel()).refresh();
	}
	
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
	

}
