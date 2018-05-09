import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;

public class VillainsLairPanel extends JPanel implements Refreshable {

	public static final String VILLAINS_LAIR_PANEL_STRING = "Villain's Lair Panel";
	
	private static final String START_ENCOUNTER_PANEL_STRING = "Start Encounter Panel";
	
	private static final String SELECT_HERO_PANEL_STRING = "Select Hero Panel";
	
	private static final String BATTLE_PANEL_STRING = "Battle Panel";
	
	private JPanel titlePanel;
	
	private CardLayout subContentPanelCardLayout;
	
	private JPanel contentPanel;
	
	private JPanel subContentPanel;
	
	private JPanel startEncounterPanel;
	
	private JPanel selectHeroPanel;
	
	private JPanel heroListPanel;
	
	/**
	 * A panel showing the selected hero's image, and a panel for the mini-game
	 * being played.
	 */
	private JPanel battlePanel;
	
	private ButtonGroup heroSelectButtonGroup;
	
	private JButton btnGo;
	
	private Game gameWindow;
	
	/**
	 * The hero which the user has chosen to battle the villain.
	 * Updated when the user chooses a new hero at the start of each round.
	 */
	private Hero currentHero;
	
	public VillainsLairPanel(Game game) {
		super();
		this.gameWindow = game;
		
		setPreferredSize(new Dimension(880, 610));
		setLayout(null);
		
		addTitlePanel();
		addContentPanel();
	}

	public void refresh() {
		refreshContentPanel();
		
	}
	
	/**
	 * Returns the villain in the current city.
	 * @return	The villain in the current city.
	 */
	private Villain villain() {
		return gameWindow.getGame().currentCity().getVillain();
	}
	
	/**
	 * Returns the team of the game environment.
	 * @return	The team of the game environment.
	 */
	private Team team() {
		return gameWindow.getGame().getTeam();
	}
	
	private void addTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setBounds(10, 10, 870, 64);
		titlePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("VILLAIN'S LAIR");
		lblTitle.setBounds(10, 0, 850, 64);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 60));
		titlePanel.add(lblTitle);		
		
		add(titlePanel);		
	}
	
	private void addContentPanel() {
		contentPanel = new JPanel(null);
		contentPanel.setBounds(10, 85, 870, 515);
		add(contentPanel);
		
		//=============================================
//		contentPanel.removeAll();
//		
//		JLabel lblVillainImage = new JLabel("");
//		lblVillainImage.setBounds(714, -24, 150, 375);
//		lblVillainImage.setIcon(new ImageIcon(VillainsLairPanel.class.getResource(Image.villainImageFilepath(villain()))));
//		contentPanel.add(lblVillainImage);
//		
//		String villainFirstName = villain().toString().split(" ")[0];
//		String villainTitle     = villain().toString().split(" ")[2];
//		
//		JLabel lblVillainName = new JLabel(villainFirstName);
//		lblVillainName.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		lblVillainName.setHorizontalAlignment(SwingConstants.CENTER);
//		lblVillainName.setBounds(680, 362, 184, 25);
//		contentPanel.add(lblVillainName);
//		
//		JLabel lblVillainTitle = new JLabel(String.format("the %s", villainTitle));
//		lblVillainTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
//		lblVillainTitle.setHorizontalAlignment(SwingConstants.CENTER);
//		lblVillainTitle.setBounds(680, 390, 180, 25);
//		contentPanel.add(lblVillainTitle);
//		
//		addSubContentPanel();
		
		
		
		

	}
	
	private void refreshContentPanel() {
		contentPanel.removeAll();
		
		JLabel lblVillainImage = new JLabel("");
		lblVillainImage.setBounds(714, -24, 150, 375);
		lblVillainImage.setIcon(new ImageIcon(VillainsLairPanel.class.getResource(Image.villainImageFilepath(villain()))));
		contentPanel.add(lblVillainImage);
		
		String villainFirstName = villain().toString().split(" ")[0];
		String villainTitle     = villain().toString().split(" ")[2];
		
		JLabel lblVillainName = new JLabel(villainFirstName);
		lblVillainName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVillainName.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainName.setBounds(693, 362, 190, 25);
		contentPanel.add(lblVillainName);
		
		JLabel lblVillainTitle = new JLabel(String.format("the %s", villainTitle));
		lblVillainTitle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVillainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainTitle.setBounds(693, 387, 190, 25);
		contentPanel.add(lblVillainTitle);
		
		addSubContentPanel();
	}
		
	private void addSubContentPanel() {
		subContentPanelCardLayout = new CardLayout();
		subContentPanel = new JPanel(subContentPanelCardLayout);
		subContentPanel.setBounds(0, 0, 670, 516);
		
		addStartEncounterPanel();
		addSelectHeroPanel();
		addBattlePanel();
		
		subContentPanelCardLayout.show(subContentPanel, START_ENCOUNTER_PANEL_STRING);
		
		contentPanel.add(subContentPanel);
	}
	
	private void addStartEncounterPanel() {
		startEncounterPanel = new JPanel(null);
		
		subContentPanel.add(startEncounterPanel, START_ENCOUNTER_PANEL_STRING);
		
		JLabel lblYouFound = new JLabel("You have found the lair of");
		lblYouFound.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouFound.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYouFound.setBounds(10, 57, 664, 37);
		startEncounterPanel.add(lblYouFound);
				
		JLabel lblVillainName = new JLabel(villain().toString());
		lblVillainName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblVillainName.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainName.setBounds(10, 105, 664, 71);
		startEncounterPanel.add(lblVillainName);
		
		JLabel txtVillainTaunt = new JLabel();
		txtVillainTaunt.setHorizontalAlignment(SwingConstants.CENTER);
		txtVillainTaunt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtVillainTaunt.setText(String.format("\"%s\"", villain().getTaunt()));
		txtVillainTaunt.setBounds(10, 187, 664, 53);
		startEncounterPanel.add(txtVillainTaunt);
		
		JButton btnFight = new JButton("Fight!");
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshSelectHeroPanel();
				subContentPanelCardLayout.show(subContentPanel, SELECT_HERO_PANEL_STRING);
			}
		});
		btnFight.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFight.setBounds(148, 382, 145, 35);
		startEncounterPanel.add(btnFight);
		
		JButton btnRunAway = new JButton("Run Away!");
		btnRunAway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setPanel(HomeBasePanel.HOME_BASE_PANEL_STRING);
			}
		});
		btnRunAway.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRunAway.setBounds(393, 382, 145, 35);
		startEncounterPanel.add(btnRunAway);
	}
	
	private void addSelectHeroPanel() {
		selectHeroPanel = new JPanel();	
		subContentPanel.add(selectHeroPanel, SELECT_HERO_PANEL_STRING);
		selectHeroPanel.setLayout(null);		
	}
	
	private void refreshSelectHeroPanel() {
		selectHeroPanel.removeAll();
		
		JLabel lblChooseAHero = new JLabel("Choose a Hero for the Battle...");
		lblChooseAHero.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAHero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseAHero.setBounds(10, 36, 612, 42);
		selectHeroPanel.add(lblChooseAHero);
		
		addHeroListPanel();
		
		btnGo = new JButton("Go!");
		btnGo.setEnabled(false);
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGo.setBounds(250, 474, 183, 29);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentHero = (Hero) Util.selectedButton(heroSelectButtonGroup)
										.getClientProperty("Hero");
				refreshBattlePanel();
				subContentPanelCardLayout.show(subContentPanel, BATTLE_PANEL_STRING);
			}
		});
		selectHeroPanel.add(btnGo);

	}
	
	private void addHeroListPanel() {
		heroListPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		heroListPanel.setBounds(10, 89, 612, 374);
		
		heroSelectButtonGroup = new ButtonGroup();
		for (Hero hero : team().getHeroes()) {
			JPanel heroPanel = new JPanel();
			heroListPanel.add(heroPanel);
			heroPanel.setLayout(null);
			
			JLabel heroImage = new JLabel();
			heroImage.setBounds(35, 10, 150, 150);
			heroImage.setIcon(new ImageIcon(VillainsLairPanel.class.getResource(Image.heroImageFilepath(hero, 150, 150))));
			heroImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			heroPanel.add(heroImage);
			
			JLabel heroName = new JLabel(String.format("%s the %s", hero.getName(), hero.getType()));
			heroName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			heroName.setHorizontalAlignment(SwingConstants.CENTER);
			heroName.setBounds(8, 171, 204, 27);
			heroPanel.add(heroName);
			
			JLabel lblHealth = new JLabel("Health:");
			lblHealth.setHorizontalAlignment(SwingConstants.LEFT);
			lblHealth.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblHealth.setBounds(18, 209, 56, 27);
			heroPanel.add(lblHealth);
			
			JLabel lblHealthAmount = new JLabel();
			lblHealthAmount.setHorizontalAlignment(SwingConstants.LEFT);
			lblHealthAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHealthAmount.setBounds(84, 209, 71, 27);
			lblHealthAmount.setText(String.format("%d/%d", hero.getCurrentHealth(), hero.getMaxHealth()));
			heroPanel.add(lblHealthAmount);
			
			JLabel lblSpecialAbility = new JLabel("Special Ability:");
			lblSpecialAbility.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSpecialAbility.setBounds(18, 242, 107, 27);
			heroPanel.add(lblSpecialAbility);
			
			JTextPane txtSpecialAbility = new JTextPane();
			txtSpecialAbility.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtSpecialAbility.setText(hero.getSpecialAbility());
			txtSpecialAbility.setBackground(UIManager.getColor("contentPanel.background"));
			txtSpecialAbility.setEditable(false);
			txtSpecialAbility.setBounds(28, 270, 160, 45);
			heroPanel.add(txtSpecialAbility);
			
			JRadioButton radioButton = new JRadioButton("");
			radioButton.setHorizontalAlignment(SwingConstants.CENTER);
			radioButton.setBounds(6, 322, 206, 45);
			radioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refreshGoButton();
				}
			});
			radioButton.putClientProperty("Hero", hero);
			heroSelectButtonGroup.add(radioButton);
			heroPanel.add(radioButton);			
			heroListPanel.add(heroPanel);
		}
		
		selectHeroPanel.add(heroListPanel);
	}
	
	private void refreshGoButton() {
		btnGo.setEnabled(heroSelectButtonGroup.getSelection() != null);
	}
	
	/**
	 * Adds the panel showing the hero's image, and the mini-game panel.
	 */
	private void addBattlePanel() {
		battlePanel = new JPanel();
		subContentPanel.add(battlePanel, BATTLE_PANEL_STRING);
		battlePanel.setLayout(null);
	}
	
	/**
	 * Removes all components from the battle panel and reloads them.
	 * Should be called before each round of the battle, to display the
	 * correct hero's image, and create a new mini-game.
	 */
	private void refreshBattlePanel() {
		battlePanel.removeAll();
		
		JPanel heroImagePanel = new JPanel();
		heroImagePanel.setBounds(10, 11, 170, 494);
		battlePanel.add(heroImagePanel);
		heroImagePanel.setLayout(null);
		
		JLabel lblHeroImage = new JLabel("");
		// TODO change to heroes' images
		lblHeroImage.setIcon(new ImageIcon(VillainsLairPanel.class.getResource(Image.heroImageFilepath(currentHero, 150, 300))));
		lblHeroImage.setBounds(10, 51, 150, 300);
		heroImagePanel.add(lblHeroImage);
		
		JLabel lblHeroName = new JLabel((currentHero.getName()));
		lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeroName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHeroName.setBounds(10, 362, 150, 25);
		heroImagePanel.add(lblHeroName);
		
		JLabel lblHeroType = new JLabel(String.format("the %s", currentHero.getType()));
		lblHeroType.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeroType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblHeroType.setBounds(5, 385, 150, 25);
		heroImagePanel.add(lblHeroType);
		
		JPanel miniGamePanel = new JPanel();
		miniGamePanel.setBounds(190, 11, 484, 494);
		battlePanel.add(miniGamePanel);
		
		miniGamePanel.add(newMiniGamePanel());
	}
	
	/**
	 * Creates and returns a new mini-game panel of one of the three possible types,
	 * depending on the games played by the current villain.
	 */
	private JPanel newMiniGamePanel() {
		MiniGames miniGameType = villain().getGame();
		
		// TODO
		return new JPanel();
		
		
	}
	
	
	
	
	
}
