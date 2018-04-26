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
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;

public class SetUpPanel extends JPanel {
	
	/**
	 * A string representation of this panel, used by the CardLayout in Game.
	 */
	public static final String SET_UP_PANEL_STRING = "Set Up Panel";

	/**
	 * A string representation of the start screen panel, used by the CardLayout
	 * of the main content panel.
	 */
	private static final String START_SCREEN_PANEL_STRING = "Start Screen Panel";

	/**
	 * A string representation of the create team panel, used by the CardLayout
	 * of the main content panel.
	 */
	private static final String CREATE_TEAM_PANEL_STRING = "Create Team Panel";

	/**
	 * A string representation of the add hero panel, used by the CardLayout
	 * of the main content panel.
	 */
	private static final String ADD_HERO_PANEL_STRING = "Add Hero Panel";
	
	/**
	 * The main Game window that this panel is a part of. Used by event handlers
	 * to change the panel shown in game.
	 */
	private Game gameWindow;
	
	/**
	 * The main sub-panel of the SetUpPanel, located below the game title.
	 * Uses a CardLayout to hold several other panels.
	 */
	private JPanel contentPanel;
	
	/**
	 * A CardLayout applied to the main content panel, used to hold several
	 * other panels.
	 */
	private CardLayout contentPanelCardLayout;
	
	/**
	 * A panel for the start screen, containing a "new game" button and an "exit" button.
	 */
	private JPanel startScreenPanel;

	/**
	 * A panel which contains components to ask the user for a team name,
	 * number of heroes, and number of cities.
	 */
	private JPanel createTeamPanel;

	/**
	 * A panel which contains components to ask the user to select a name
	 * and type for a hero, and display information about the different hero types.
	 */
	private JPanel addHeroPanel;
	
	// Add hero panel components
	private JLabel lblCreateHero;
	private JPanel heroInfoPanel;
	private CardLayout heroInfoPanelCardLayout;
	
	
	/**
	 * Constructor for a SetUpPanel. Creates a JPanel of the appropriate size,
	 * and adds all the required components.
	 * @param gameWindow		The main game window which this panel is a part of.
	 */
	public SetUpPanel(Game gameWindow) {
		super();
		setBounds(new Rectangle(0, 0, 880, 610));
		this.gameWindow = gameWindow;
		setLayout(null);
		
		addTitle();
		addContentPanel();
		addSubPanels();
	}
	
	/**
	 * Creates a label containing the game's title and adds it to the panel.
	 */
	private void addTitle() {
		JLabel lblTitle = new JLabel("HEROES AND VILLAINS");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 80));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(10, 11, 860, 150);
		add(lblTitle);		
		
	}

	/**
	 * Creates a JPanel below the title to hold the main content of the
	 * set up page, and gives it a CardLayout to hold the different panels
	 * shown during game set up.
	 */
	private void addContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBounds(10, 172, 860, 427);
		add(contentPanel);
		
		contentPanelCardLayout = new CardLayout();
		contentPanel.setLayout(contentPanelCardLayout);
		
	}
	
	/**
	 * Creates a JPanel for every sub-panel within the main content panel,
	 * and adds them to the CardLayout.
	 * Sets the start screen panel to be show initially.
	 */
	private void addSubPanels() {
		startScreenPanel = new JPanel();
		initialiseStartScreenPanel();
		contentPanel.add(startScreenPanel, START_SCREEN_PANEL_STRING);

		createTeamPanel = new JPanel();
		initialiseCreateTeamPanel();
		contentPanel.add(createTeamPanel, CREATE_TEAM_PANEL_STRING);
		
		addHeroPanel = new JPanel();
		initialiseAddHeroPanel();
		contentPanel.add(addHeroPanel, ADD_HERO_PANEL_STRING);
		
		contentPanelCardLayout.show(contentPanel, START_SCREEN_PANEL_STRING);
		
	}


	/**
	 * Adds components to the start screen panel.
	 */
	private void initialiseStartScreenPanel() {
		startScreenPanel.setLayout(null);
		
		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPanelCardLayout.show(contentPanel, CREATE_TEAM_PANEL_STRING);
			}
		});
		btnNewGame.setBounds(346, 116, 168, 57);
		startScreenPanel.add(btnNewGame);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnExit.setBounds(346, 256, 168, 57);
		startScreenPanel.add(btnExit);
	}
	
	/**
	 * Add components to the create team panel.
	 */
	private void initialiseCreateTeamPanel() {
		createTeamPanel.setLayout(null);
		
		JLabel lblEnterTeamName = new JLabel("Enter a name for you team of heroes:");
		lblEnterTeamName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnterTeamName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterTeamName.setBounds(10, 74, 414, 32);
		createTeamPanel.add(lblEnterTeamName);
		
		JTextField txtfTeamName = new JTextField();
		txtfTeamName.setBounds(447, 74, 222, 32);
		createTeamPanel.add(txtfTeamName);
		txtfTeamName.setColumns(10);
		
		JLabel lblTeamNameErrorMessage = new JLabel("Team name must be between 2 and 10 characters long.");
		lblTeamNameErrorMessage.setVisible(false);
		lblTeamNameErrorMessage.setBounds(447, 113, 403, 14);
		createTeamPanel.add(lblTeamNameErrorMessage);
		
		JLabel lblChooseNumHeroes = new JLabel("How many heroes would you like on your team?");
		lblChooseNumHeroes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblChooseNumHeroes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseNumHeroes.setBounds(10, 150, 414, 32);
		createTeamPanel.add(lblChooseNumHeroes);
		
		JComboBox<Integer> cmbNumHeroes = new JComboBox<Integer>();
		cmbNumHeroes.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3}));
		cmbNumHeroes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbNumHeroes.setBounds(447, 150, 66, 32);
		createTeamPanel.add(cmbNumHeroes);
		
		JLabel lblChooseNumCities = new JLabel("How many cities would you like to play?");
		lblChooseNumCities.setHorizontalAlignment(SwingConstants.TRAILING);
		lblChooseNumCities.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseNumCities.setBounds(10, 230, 414, 32);
		createTeamPanel.add(lblChooseNumCities);
		
		JComboBox<Integer> cmbNumCities = new JComboBox<Integer>();
		cmbNumCities.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {3, 4, 5, 6}));
		cmbNumCities.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbNumCities.setBounds(447, 230, 66, 32);
		createTeamPanel.add(cmbNumCities);
		
		JButton btnCreateTeamNext = new JButton("Next");
		btnCreateTeamNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teamName = txtfTeamName.getText();
				int numHeroes = (int) cmbNumHeroes.getSelectedItem();
				int numCities = (int) cmbNumCities.getSelectedItem();
				
				if (Team.isValidTeamName(teamName)) {
					gameWindow.setGame(new GameEnvironment(teamName, numHeroes, numCities));
					refreshAddHeroPanel();
					contentPanelCardLayout.show(contentPanel, ADD_HERO_PANEL_STRING);
				} else {
					lblTeamNameErrorMessage.setVisible(true);
				}
			}
		});
		btnCreateTeamNext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreateTeamNext.setBounds(381, 320, 98, 32);
		createTeamPanel.add(btnCreateTeamNext);
	}
	
	/**
	 * Adds components to the add hero panel.
	 */
	private void initialiseAddHeroPanel() {
		addHeroPanel.setLayout(null);
		
		lblCreateHero = new JLabel("Create Hero");
		lblCreateHero.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateHero.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCreateHero.setBounds(10, 11, 268, 41);
		addHeroPanel.add(lblCreateHero);
		
		JLabel lblEnterAName = new JLabel("Enter a name:");
		lblEnterAName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterAName.setBounds(10, 92, 117, 20);
		addHeroPanel.add(lblEnterAName);
		
		JLabel lblSelectAHero = new JLabel("Select a hero type");
		lblSelectAHero.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectAHero.setBounds(10, 172, 156, 20);
		addHeroPanel.add(lblSelectAHero);
		
		heroInfoPanel = new JPanel();
		heroInfoPanel.setBounds(288, 11, 562, 405);
		addHeroPanel.add(heroInfoPanel);
		
		heroInfoPanelCardLayout = new CardLayout();
		heroInfoPanel.setLayout(heroInfoPanelCardLayout);
		
		addHeroInfoPanels();
	}
	
	/**
	 * Adds an information panel for each hero to the
	 * hero info panel.
	 */
	private void addHeroInfoPanels() {
		for (Hero hero: Hero.ALL_HEROES) {
			
		}
		
	}

	/**
	 * Called after each hero is added. Updates the add hero panel by
	 * updating the hero number, emptyting the name text field, and
	 * resetting the selected hero type the the default.
	 */
	private void refreshAddHeroPanel() {
		lblCreateHero.setText(String.format("Create Hero %s:",
				gameWindow.getGame().getTeam().getHeroes().size() + 1));
	}
	
	
}
