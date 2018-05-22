import javax.swing.JPanel;
import javax.swing.JLabel;
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

import javax.swing.border.LineBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JSlider;

/**
 * A SetUpPanel is a panel contained in the main CardLayout of
 * the Game window. A SetUpPanel contains all components related to
 * setting up a game, including creating a team, adding heroes, and
 * choosing the number of cities to play. A SetUpPanel is shown
 * whenever a new Game window is created.
 */
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
	 * A string representation of the team summary panel, used by the CardLayout
	 * of the main content panel.
	 */
	private static final String TEAM_SUMMARY_PANEL_STRING = "Team Summary Panel";
	
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
	
	/**
	 * A panel which contains components to display a summary of the newly created
	 * team to the user, and allow them to begin the game.
	 */
	private JPanel teamSummaryPanel;
	
	/**
	 * A label instructing the user to create a hero.
	 * Changes depending on how many heroes have been added
	 * already, eg "Create Hero 2:".
	 */
	private JLabel lblCreateHero;
	
	/**
	 * A panel containing information about each hero subclass,
	 * stored in a card layout.
	 */
	private JPanel heroInfoPanel;
	
	/**
	 * A CardLayout containing an information panel for each hero
	 * subclass. The panel shown depends on which hero type is selected
	 * in the combo box.
	 */
	private CardLayout heroInfoPanelCardLayout;
	
	/**
	 * The panel containing components to ask the user to enter a
	 * hero name and select a hero type.
	 */
	private JPanel inputPanel;
	
	/**
	 * The error message displayed if the hero name entered is invalid.
	 * Text is set to empty when the panel is refreshed, and changed to
	 * the appropriate error message depending on the hero name entered.
	 */
	private JLabel lblInvalidNameErrorMessage;
	
	/**
	 * The text field where the user enters the name of a hero.
	 */
	private JTextField txtfName;

	/**
	 * A panel showing a summary of each hero on the team once all the
	 * heroes have been created.
	 */
	private JPanel heroSummariesPanel;
	
	/**
	 * A label showing the team name on the team summary panel.
	 */
	private JLabel lblTeamName;	
	
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
	 * Helper method to return the team of the current GameEnvironment.
	 * @return		The team of the current GameEnvirionment.
	 */
	private Team team() {
		return gameWindow.getGame().getTeam();
	}
	
	
	/**
	 * Creates a label containing the game's title and adds it to the panel.
	 */
	private void addTitle() {
		JLabel lblTitle = new JLabel("HEROES AND VILLAINS");
		lblTitle.setFont(new Font("Ringbearer", Font.PLAIN, 70));
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
		
		initialiseTeamSummaryPanel();
		
		contentPanelCardLayout.show(contentPanel, START_SCREEN_PANEL_STRING);
		
	}
	
	/* 
	 * -------------------------------------------------------------------------------------
	 * Start Screen Panel
	 * -------------------------------------------------------------------------------------
	 */

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
	
	/* 
	 * -------------------------------------------------------------------------------------
	 * Create Team Panel
	 * -------------------------------------------------------------------------------------
	 */
	
	/**
	 * Add components to the create team panel.
	 */
	private void initialiseCreateTeamPanel() {
		createTeamPanel.setLayout(null);
		
		JLabel lblEnterTeamName = new JLabel("Enter a name for your team of heroes:");
		lblEnterTeamName.setHorizontalAlignment(SwingConstants.TRAILING);
		lblEnterTeamName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterTeamName.setBounds(10, 74, 414, 32);
		createTeamPanel.add(lblEnterTeamName);
		
		JTextField txtfTeamName = new JTextField();
		txtfTeamName.setBounds(447, 74, 222, 32);
		createTeamPanel.add(txtfTeamName);
		txtfTeamName.setColumns(10);
		
		JLabel lblTeamNameErrorMessage = new JLabel("");
		lblTeamNameErrorMessage.setBounds(447, 113, 403, 15);
		createTeamPanel.add(lblTeamNameErrorMessage);
		
		JLabel lblChooseNumHeroes = new JLabel("How many heroes would you like on your team?");
		lblChooseNumHeroes.setHorizontalAlignment(SwingConstants.TRAILING);
		lblChooseNumHeroes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseNumHeroes.setBounds(10, 150, 414, 32);
		createTeamPanel.add(lblChooseNumHeroes);
		
		JSlider sliderNumHeroes = new JSlider();
		sliderNumHeroes.setSnapToTicks(true);
		sliderNumHeroes.setPaintTicks(true);
		sliderNumHeroes.setPaintLabels(true);
		sliderNumHeroes.setMinimum(1);
		sliderNumHeroes.setMaximum(3);
		sliderNumHeroes.setMajorTickSpacing(1);
		sliderNumHeroes.setBounds(447, 156, 200, 45);
		sliderNumHeroes.setValue(1);
		createTeamPanel.add(sliderNumHeroes);
		
		JLabel lblChooseNumCities = new JLabel("How many cities would you like to play?");
		lblChooseNumCities.setHorizontalAlignment(SwingConstants.TRAILING);
		lblChooseNumCities.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChooseNumCities.setBounds(10, 230, 414, 32);
		createTeamPanel.add(lblChooseNumCities);
				
		JSlider sliderNumCities = new JSlider();
		sliderNumCities.setSnapToTicks(true);
		sliderNumCities.setPaintTicks(true);
		sliderNumCities.setPaintLabels(true);
		sliderNumCities.setMinimum(3);
		sliderNumCities.setMaximum(6);
		sliderNumCities.setMajorTickSpacing(1);
		sliderNumCities.setBounds(447, 236, 200, 45);
		sliderNumCities.setValue(3);
		createTeamPanel.add(sliderNumCities);
		
		JButton btnCreateTeamNext = new JButton("Next");
		btnCreateTeamNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String teamName = txtfTeamName.getText().trim();
				int numHeroes = sliderNumHeroes.getValue();
				int numCities = sliderNumCities.getValue();
				
				if (teamName.length() < Team.MIN_TEAM_NAME_LENGTH
						|| teamName.length() > Team.MAX_TEAM_NAME_LENGTH) {
					lblTeamNameErrorMessage.setText(String.format(
							"Team name must be between %s and %s characters long.",
							Team.MIN_TEAM_NAME_LENGTH,
							Team.MAX_TEAM_NAME_LENGTH));

				} else {
					gameWindow.setGame(new GameEnvironment(teamName, numHeroes, numCities));
					refreshAddHeroPanel();
					contentPanelCardLayout.show(contentPanel, ADD_HERO_PANEL_STRING);
				}
			}
		});
		btnCreateTeamNext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreateTeamNext.setBounds(381, 320, 98, 32);
		createTeamPanel.add(btnCreateTeamNext);
	}
	
	/* 
	 * -------------------------------------------------------------------------------------
	 * Add Hero Panel
	 * -------------------------------------------------------------------------------------
	 */
	
	/**
	 * Adds components to the add hero panel.
	 */
	private void initialiseAddHeroPanel() {
		addHeroPanel.setLayout(null);
		
		inputPanel = new JPanel();
		inputPanel.setBounds(10, 11, 300, 405);
		addHeroPanel.add(inputPanel);
		
		initialiseInputPanel();
		
		heroInfoPanel = new JPanel();
		heroInfoPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		heroInfoPanel.setBounds(320, 11, 530, 405);
		addHeroPanel.add(heroInfoPanel);
		
		heroInfoPanelCardLayout = new CardLayout();
		heroInfoPanel.setLayout(heroInfoPanelCardLayout);
				
		addHeroInfoPanels();
	}
	
	/**
	 * Adds components to the input panel.
	 */
	private void initialiseInputPanel() {
		inputPanel.setLayout(null);
		
		lblCreateHero = new JLabel("Create Hero");
		lblCreateHero.setBounds(10, 11, 280, 29);
		lblCreateHero.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateHero.setFont(new Font("Tahoma", Font.PLAIN, 24));
		inputPanel.add(lblCreateHero);
		
		JLabel lblEnterAName = new JLabel("Enter a name:");
		lblEnterAName.setBounds(20, 73, 260, 20);
		lblEnterAName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inputPanel.add(lblEnterAName);
		
		txtfName = new JTextField();
		txtfName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtfName.setBounds(30, 104, 240, 29);
		txtfName.setColumns(10);
		inputPanel.add(txtfName);
		
		lblInvalidNameErrorMessage = new JLabel("");
		lblInvalidNameErrorMessage.setBounds(30, 142, 270, 14);
		inputPanel.add(lblInvalidNameErrorMessage);
		
		JLabel lblSelectHeroType = new JLabel("Select a hero type:");
		lblSelectHeroType.setBounds(20, 177, 260, 20);
		lblSelectHeroType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		inputPanel.add(lblSelectHeroType);
		
		JComboBox<String> cmbHeroTypes = new JComboBox<String>();
		cmbHeroTypes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				heroInfoPanelCardLayout.show(heroInfoPanel,
										(String) cmbHeroTypes.getSelectedItem());
			}
		});
		cmbHeroTypes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cmbHeroTypes.setModel(new DefaultComboBoxModel<String>(Hero.allHeroTypes()));
		cmbHeroTypes.setBounds(30, 221, 240, 29);
		inputPanel.add(cmbHeroTypes);
		
		JButton btnAddHeroToTeam = new JButton("Add Hero to Team");
		btnAddHeroToTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String heroName = txtfName.getText().trim();
				String type = (String) cmbHeroTypes.getSelectedItem();
				
				if (!team().isUniqueHeroName(heroName)) {
					lblInvalidNameErrorMessage.setText("That name is already taken by another hero.");
					
				} else if (heroName.length() < Hero.MIN_HERO_NAME_LENGTH
						|| heroName.length() > Hero.MAX_HERO_NAME_LENGTH) {
					lblInvalidNameErrorMessage.setText(String.format(
							"Required: %s-%s characters.",
							Hero.MIN_HERO_NAME_LENGTH,
							Hero.MAX_HERO_NAME_LENGTH));
									
				} else {
					team().addHero(heroName, type);
					if (team().getHeroes().size() < team().getStartNumHeroes()) {
						refreshAddHeroPanel();
					} else {
						contentPanelCardLayout.show(contentPanel, TEAM_SUMMARY_PANEL_STRING);
						refreshTeamSummaryPanel();
					}
				}
			}
		});
		btnAddHeroToTeam.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAddHeroToTeam.setBounds(43, 335, 214, 29);
		inputPanel.add(btnAddHeroToTeam);
		
	}

	/**
	 * Adds an information panel for each hero to the hero info panel
	 * card layout, using the hero's type as the String reference.
	 */
	private void addHeroInfoPanels() {
		for (Hero hero: Hero.ALL_HEROES) {
			heroInfoPanel.add(makeHeroInfoPanel(hero), hero.getType());
		}
		
	}

	/**
	 * Takes a hero subclass instance, and creates an information panel for
	 * a hero of that type, including its image, type, special ability,
	 * max health and description.
	 * @param hero		An instance of the hero subclass which the information
	 * 					panel is describing.
	 * @return			The hero information JPanel.
	 */
	private JPanel makeHeroInfoPanel(Hero hero) {
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(null);
		
		JLabel lblHeroImage = new JLabel("");
		lblHeroImage.setIcon(new ImageIcon(SetUpPanel.class.getResource(
				  				Image.heroImageFilepath(hero, 200, 200))));
		lblHeroImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblHeroImage.setBounds(10, 11, 200, 200);
		infoPanel.add(lblHeroImage);
		
		JLabel lblType = new JLabel(hero.getType());
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblType.setHorizontalAlignment(SwingConstants.CENTER);
		lblType.setBounds(220, 11, 298, 37);
		infoPanel.add(lblType);
		
		JLabel lblSpecialAbility = new JLabel("Special Ability:");
		lblSpecialAbility.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSpecialAbility.setBounds(230, 59, 278, 20);
		infoPanel.add(lblSpecialAbility);
		
		JTextPane txtpnHerosSpecialAbility = new JTextPane();
		txtpnHerosSpecialAbility.setBackground(UIManager.getColor("Panel.background"));
		txtpnHerosSpecialAbility.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnHerosSpecialAbility.setText(hero.getSpecialAbility());
		txtpnHerosSpecialAbility.setBounds(240, 90, 268, 50);
		txtpnHerosSpecialAbility.setEditable(false);
		infoPanel.add(txtpnHerosSpecialAbility);
		
		JLabel lblMaxHealth = new JLabel(String.format("Max Health: %s", hero.getMaxHealth()));
		lblMaxHealth.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMaxHealth.setBounds(230, 160, 278, 20);
		infoPanel.add(lblMaxHealth);
		
		JTextPane txtpnDescription = new JTextPane();
		txtpnDescription.setBackground(UIManager.getColor("Panel.background"));
		txtpnDescription.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnDescription.setText(hero.getDescription());
		txtpnDescription.setBounds(10, 232, 508, 160);
		txtpnDescription.setEditable(false);
		infoPanel.add(txtpnDescription);
		
		return infoPanel;
	}

	/**
	 * Called after each hero is added. Updates the add hero panel by
	 * updating the hero number, emptying the name text field, and
	 * resetting the selected hero type the the default.
	 */
	private void refreshAddHeroPanel() {
		lblCreateHero.setText(String.format("Create Hero %s:",
				team().getHeroes().size() + 1));
		
		txtfName.setText("");
		
		lblInvalidNameErrorMessage.setText("");

	}
	
	/* 
	 * -------------------------------------------------------------------------------------
	 * Team Summary Panel
	 * -------------------------------------------------------------------------------------
	 */
	
	/**
	 * Adds components to the team summary panel.
	 */
	private void initialiseTeamSummaryPanel() {
		teamSummaryPanel = new JPanel();
		contentPanel.add(teamSummaryPanel, TEAM_SUMMARY_PANEL_STRING);
		teamSummaryPanel.setLayout(null);
		
		lblTeamName = new JLabel("");
		lblTeamName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTeamName.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamName.setBounds(10, 11, 840, 70);
		teamSummaryPanel.add(lblTeamName);
		
		JButton btnStartGame = new JButton("Start Game");
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.getGame().enterFirstCity();
				gameWindow.setPanel(HomeBasePanel.HOME_BASE_PANEL_STRING);
			}
		});
		btnStartGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStartGame.setBounds(369, 367, 122, 36);
		teamSummaryPanel.add(btnStartGame);
		
		heroSummariesPanel = new JPanel();
		heroSummariesPanel.setBounds(10, 92, 840, 262);
		teamSummaryPanel.add(heroSummariesPanel);		
	}
	
	/**
	 * Adds the hero summaries to the team summary panel.
	 * Called once the team has been created.
	 */
	private void addHeroSummaries() {
		GridLayout teamSummaryGridLayout = new GridLayout(0, team().getHeroes().size(), 0, 0);
		heroSummariesPanel.setLayout(teamSummaryGridLayout);
		
		for (Hero hero: team().getHeroes()) {
			heroSummariesPanel.add(heroSummaryPanel(hero));
		}
	}
	
	/**
	 * Takes a hero, and produces a panel containing the hero's image,
	 * name and type.
	 * @param hero		The hero for which to make a summary panel.
	 * @return			A summary panel for the given hero.
	 */
	private JPanel heroSummaryPanel(Hero hero) {
		JPanel heroSummaryPanel = new JPanel();
		
		heroSummaryPanel.setLayout(new BorderLayout());
		
		JLabel lblHeroImage = new JLabel("");
		lblHeroImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeroImage.setIcon(new ImageIcon(SetUpPanel.class.getResource(
								Image.heroImageFilepath(hero, 200, 200))));
		heroSummaryPanel.add(lblHeroImage, BorderLayout.NORTH);
		
		JLabel lblHeroName = new JLabel(String.format(
								"%s the %s", hero.getName(), hero.getType()));
		lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeroName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		heroSummaryPanel.add(lblHeroName, BorderLayout.CENTER);
		
		return heroSummaryPanel;
	}
	
		/**
	 * Shows the team name, and creates an information panel for each
	 * hero in the team.
	 * Called once the team of heroes has been created.
	 */
	private void refreshTeamSummaryPanel() {
		lblTeamName.setText(team().getName());
		addHeroSummaries();
	}
}
