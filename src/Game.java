import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;

public class Game {
	
	private JFrame frame;
	private JPanel mainPanel;
	private CardLayout cardLayout;
	
	private StartScreenPanel startScreenPanel;
	private ShopPanel shopPanel;
	private HomeBasePanel homeBasePanel;
	private JPanel panel;
	

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
		frame.setBounds(200, 50, 880, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		cardLayout = new CardLayout();
		mainPanel.setLayout(cardLayout);
		
		addPanels();
		
		cardLayout.show(mainPanel, ShopPanel.SHOP_PANEL_STRING);
	}
	
	public void addPanels() {
		startScreenPanel = new StartScreenPanel(this);
		mainPanel.add(startScreenPanel, StartScreenPanel.START_SCREEN_PANEL_STRING);
		
		shopPanel = new ShopPanel(this);
		mainPanel.add(shopPanel, ShopPanel.SHOP_PANEL_STRING);
		
		homeBasePanel = new HomeBasePanel(this);
		mainPanel.add(homeBasePanel, HomeBasePanel.HOME_BASE_PANEL_STRING);
	}
	
	public void setPanel(String panelString) {
		cardLayout.show(mainPanel, panelString);
	}

}
