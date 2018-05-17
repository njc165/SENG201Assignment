import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;

/**
 * Instances of VillainsLairPanel are components of Game objects.
 * They contain all components related to the Villain's Lair in the
 * game, and are displayed when the player enters the Villain's Lair.
 */
public class VillainsLairPanel extends JPanel implements Refreshable {

	/**
	 * A string representation of this panel.
	 */
	public static final String VILLAINS_LAIR_PANEL_STRING = "Villain's Lair Panel";
	
	/**
	 * A string representation of the start encounter panel, used
	 * by the subContentPanel card layout.
	 */
	private static final String START_ENCOUNTER_PANEL_STRING = "Start Encounter Panel";
	
	/**
	 * A string representation of the select hero panel, used
	 * by the subContentPanel card layout.
	 */
	private static final String SELECT_HERO_PANEL_STRING = "Select Hero Panel";
	
	/**
	 * A string representation of the battle panel, used
	 * by the subContentPanel card layout.
	 */
	private static final String BATTLE_PANEL_STRING = "Battle Panel";
	
	/**
	 * A string representation of the game result panel, used
	 * by the subContentPanel card layout.
	 */
	private static final String GAME_RESULT_PANEL_STRING = "Game Result Panel";
		
	/**
	 * A JPanel containing all components related to the title of this panel.
	 */
	private JPanel titlePanel;
	
	/**
	 * A card layout used by the sunContentPanel.
	 */
	private CardLayout subContentPanelCardLayout;
	
	/**
	 * A JPanel containing all major components related to this panel.
	 */
	private JPanel contentPanel;
	
	/**
	 * A JPanel containing interactive components related to this panel.
	 */
	private JPanel subContentPanel;
	
	/**
	 * A JPanel containing buttons to start fighting the villain
	 * or to run away.
	 */
	private JPanel startEncounterPanel;
	
	/**
	 * A JPanel which allows the user to select a hero with which
	 * to fight the villain.
	 */
	private JPanel selectHeroPanel;
	
	/**
	 * A JPanel which lists all heros currently alive on the team and
	 * prompts the user to select one.
	 */
	private JPanel heroListPanel;
	
	/**
	 * A panel showing the selected hero's image, and a panel for the mini-game
	 * being played.
	 */
	private JPanel battlePanel;
	
	/**
	 * A panel show the result of the mini game.
	 */
	private JPanel gameResultPanel;
	
	/**
	 * A group of radio buttons which represent the player's selection of
	 * which hero will battle the villain.
	 */
	private ButtonGroup heroSelectButtonGroup;
	
	/**
	 * A button which takes the villain and a selected hero and begins a minigame
	 * between them.
	 */
	private JButton btnGo;
	
	/**
	 * The main game window.
	 */
	private Game gameWindow;
	
	/**
	 * The hero which the user has chosen to battle the villain.
	 * Updated when the user chooses a new hero at the start of each round.
	 */
	private Hero currentHero;
	
	/**
	 * True if the hero won the last mini game that was played, false otherwise.
	 */
	private boolean wonLastMinigame;
	
	/**
	 * A constructor for VillainsLairPanel.
	 * @param game The game window to which this panel belongs.
	 */
	public VillainsLairPanel(Game game) {
		super();
		this.gameWindow = game;
		
		setPreferredSize(new Dimension(880, 610));
		setLayout(null);
		
		addTitlePanel();
		addContentPanel();
	}
	
	/**
	 * Called by a mini game panel class when the game has finished.
	 * Takes the result of the game, and processes it appropriately,
	 * showing the game result panel.
	 * @param hasWon
	 */
	public void miniGameFinished(boolean hasWon) {
		wonLastMinigame = hasWon;
		
		gameWindow.getGame().processMiniGameResult(hasWon, currentHero, villain());
		
		refreshGameResultPanel();
		subContentPanelCardLayout.show(subContentPanel, GAME_RESULT_PANEL_STRING);
	}

	/**
	 * Refresh all dynamic components belonging to this panel.
	 */
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
	
	/**
	 * Create and populate the title panel.
	 */
	private void addTitlePanel() {
		titlePanel = new JPanel();
		titlePanel.setBounds(10, 10, 870, 64);
		titlePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("VILLAIN'S LAIR");
		lblTitle.setBounds(10, 0, 850, 64);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Rockwell", Font.PLAIN, 60));
		titlePanel.add(lblTitle);		
		
		add(titlePanel);		
	}
	
	/**
	 * Create and populate the content panel.
	 */
	private void addContentPanel() {
		contentPanel = new JPanel(null);
		contentPanel.setBounds(10, 85, 870, 515);
		add(contentPanel);
	}
	
	/**
	 * Refresh all dynamic components belonging to contentPanel.
	 */
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
		
	/**
	 * Create and populate the sub-content panel.
	 */
	private void addSubContentPanel() {
		subContentPanelCardLayout = new CardLayout();
		subContentPanel = new JPanel(subContentPanelCardLayout);
		subContentPanel.setBounds(0, 0, 670, 516);
		
		addStartEncounterPanel();
		addSelectHeroPanel();
		addBattlePanel();
		addGameResultPanel();
		
		subContentPanelCardLayout.show(subContentPanel, START_ENCOUNTER_PANEL_STRING);
		
		contentPanel.add(subContentPanel);
	}
	
	/**
	 * Create and populate the start encounter panel.
	 */
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
	
	/**
	 * Create and populate the select hero panel.
	 */
	private void addSelectHeroPanel() {
		selectHeroPanel = new JPanel();	
		subContentPanel.add(selectHeroPanel, SELECT_HERO_PANEL_STRING);
		selectHeroPanel.setLayout(null);		
	}
	
	/**
	 * Refresh all dynamic components belonging to the
	 * select hero panel.
	 */
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
	
	/**
	 * Create and populate the hero list panel.
	 */
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
			
			heroPanel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (radioButton.isEnabled()) {
						radioButton.setSelected(true);
						refreshGoButton();
					}
				}
			});
		}
		
		selectHeroPanel.add(heroListPanel);
	}
	
	/**
	 * Refresh the 'Go' button by enabling it if and only if
	 * a valid hero has been selected.
	 */
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
		
		battlePanel.add(heroImagePanel());
		
		JPanel miniGamePanel = new JPanel();
		miniGamePanel.setBounds(190, 11, 484, 494);
		battlePanel.add(miniGamePanel);
		
		miniGamePanel.add(newMiniGamePanel());
	}
	
	/**
	 * Returns a panel showing the hero's image, name and type,
	 * to displayed during and after the battle.
	 */
	private JPanel heroImagePanel() {
		JPanel heroImagePanel = new JPanel();
		heroImagePanel.setBounds(10, 11, 170, 494);
		heroImagePanel.setLayout(null);
		
		JLabel lblHeroImage = new JLabel("");
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
		
		return heroImagePanel;
	}
	
	/**
	 * Creates and returns a new mini-game panel of one of the three possible types,
	 * depending on the games played by the current villain.
	 */
	private JPanel newMiniGamePanel() {
		MiniGameType miniGameType = villain().getGame();
		
		switch (miniGameType) {
			case PAPER_SCISSORS_ROCK: return new PaperScissorsRockPanel(this, currentHero, villain());
			case DICE_ROLLS: return new DiceRollsPanel(this, currentHero, villain());
			case GUESS_NUMBER: return new GuessNumberPanel(this, currentHero, villain());
			default: throw new RuntimeException("Incorrect MiniGame type");
		}
		
	}
	
	/**
	 * Adds a panel to show the result of a mini game.
	 */
	private void addGameResultPanel() {
		gameResultPanel = new JPanel();
		gameResultPanel.setLayout(null);
		subContentPanel.add(gameResultPanel, GAME_RESULT_PANEL_STRING);
	}
	
	/**
	 * Adds all the required components to show the result of the most
	 * recent mini game.
	 * Includes whether the hero won or lost, how much damage was done to
	 * the hero or villain and how many further rounds need to be won.
	 * Alerts the user if the hero has died or if the villain has been
	 * defeated.
	 */
	private void refreshGameResultPanel() {
		gameResultPanel.removeAll();
		gameResultPanel.add(heroImagePanel());
				
		if (wonLastMinigame) {
			addWonComponents();
		} else {
			addLostComponents();
		}
	}
	
	/**
	 * Adds components to the game result panel showing that the hero won
	 * the mini game.
	 */
	private void addWonComponents() {
		JLabel lblCongratulations = new JLabel("Congratulations on winning the round!");
		lblCongratulations.setHorizontalAlignment(SwingConstants.CENTER);
		lblCongratulations.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCongratulations.setBounds(190, 69, 470, 31);
		gameResultPanel.add(lblCongratulations);
		
		if (currentHero.getHasDoubleDamage()) {
			JLabel txtpnMercenary = new JLabel();
			txtpnMercenary.setHorizontalAlignment(SwingConstants.CENTER);
			txtpnMercenary.setFont(new Font("Tahoma", Font.PLAIN, 13));
			txtpnMercenary.setText(String.format("%s used their special ability to attack twice!", currentHero.getName()));
			txtpnMercenary.setBounds(190, 111, 470, 39);
			gameResultPanel.add(txtpnMercenary);
		}

		
		if (villain().isDefeated()) {
			JLabel lblDefeatedVillain = new JLabel();
			lblDefeatedVillain.setText(String.format("You have defeated %s.", villain().getName()));
			lblDefeatedVillain.setHorizontalAlignment(SwingConstants.CENTER);
			lblDefeatedVillain.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblDefeatedVillain.setBounds(190, 233, 470, 31);
			gameResultPanel.add(lblDefeatedVillain);
			
			JLabel lblPrizeMoney = new JLabel();
			lblPrizeMoney.setText(String.format("The citizens have rewarded you with %d coins.", gameWindow.getGame().getPrizeMoney()));
			lblPrizeMoney.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrizeMoney.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblPrizeMoney.setBounds(190, 296, 470, 31);
			gameResultPanel.add(lblPrizeMoney);
			
			JButton btnContinue = new JButton();
			btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnContinue.setBounds(353, 425, 150, 30);
			if (villain() instanceof Invictus) {
				btnContinue.setText("Continue");
				btnContinue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gameWindow.endGame(true);
					}
				});
			} else {
				btnContinue.setText("Next City");
				btnContinue.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						gameWindow.getGame().nextCity();
						gameWindow.setPanel(HomeBasePanel.HOME_BASE_PANEL_STRING);
					}
				});
			}		
			gameResultPanel.add(btnContinue);
			
		} else {
			
			String plural;
			if (villain().remainingTimesToDefeat() == 1) {
				plural = "";
			} else {
				plural = "s";
			}

			JLabel lblRoundsRemaining = new JLabel(String.format("You need to win %s more round%s",
									villain().remainingTimesToDefeat(), 
									plural));
			lblRoundsRemaining.setHorizontalAlignment(SwingConstants.CENTER);
			lblRoundsRemaining.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblRoundsRemaining.setBounds(190, 233, 470, 31);
			gameResultPanel.add(lblRoundsRemaining);
			
			JLabel lblToDefeat = new JLabel(String.format("to defeat %s.", villain().getName()));
			lblToDefeat.setHorizontalAlignment(SwingConstants.CENTER);
			lblToDefeat.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblToDefeat.setBounds(190, 263, 470, 31);
			gameResultPanel.add(lblToDefeat);
			
			JButton btnNextRound = new JButton("Next Round");
			btnNextRound.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refreshSelectHeroPanel();
					subContentPanelCardLayout.show(subContentPanel, SELECT_HERO_PANEL_STRING);
				}
			});
			btnNextRound.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnNextRound.setBounds(353, 425, 150, 30);
			gameResultPanel.add(btnNextRound);
		}

	}
	
	/**
	 * Adds components to the game result panel showing that the hero lost
	 * the mini game.
	 */
	private void addLostComponents() {
		JLabel lblLost = new JLabel("Oh no! You lost the round!");
		lblLost.setHorizontalAlignment(SwingConstants.CENTER);
		lblLost.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLost.setBounds(190, 71, 470, 31);
		gameResultPanel.add(lblLost);
		
		JLabel lblVillainAttacked = new JLabel();
		lblVillainAttacked.setText(String.format("%s attacks %s!", villain().getName(), currentHero.getName()));
		lblVillainAttacked.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainAttacked.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblVillainAttacked.setBounds(190, 153, 470, 31);
		gameResultPanel.add(lblVillainAttacked);
		
		int damageTaken = villain().getDamageDealt();
		if (currentHero.getHasReducedDamage()) {
			damageTaken = (int) (damageTaken * Hero.DAMAGE_REDUCTION_MULTIPLIER);
		}
		
		JLabel lblDamageTaken = new JLabel();
		lblDamageTaken.setText(String.format("%s took %d damage.", currentHero.getName(), damageTaken));
		lblDamageTaken.setHorizontalAlignment(SwingConstants.CENTER);
		lblDamageTaken.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDamageTaken.setBounds(190, 195, 470, 31);
		gameResultPanel.add(lblDamageTaken);
				
		if (! team().getHeroes().contains(currentHero)) {
			JLabel lblHeroDied = new JLabel();
			lblHeroDied.setText(String.format("%s has fallen in battle!", currentHero.getName()));
			lblHeroDied.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroDied.setFont(new Font("Tahoma", Font.PLAIN, 18));
			lblHeroDied.setBounds(190, 250, 470, 31);
			gameResultPanel.add(lblHeroDied);
		}
		
		String plural;
		if (villain().remainingTimesToDefeat() == 1) {
			plural = "";
		} else {
			plural = "s";
		}
		
		JLabel lblRoundsRemaining = new JLabel(String.format("You need to win %s more round%s",
															villain().remainingTimesToDefeat(), 
															plural));
		lblRoundsRemaining.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoundsRemaining.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRoundsRemaining.setBounds(190, 331, 470, 31);
		gameResultPanel.add(lblRoundsRemaining);
		
		JLabel lblToDefeat = new JLabel(String.format("to defeat %s.", villain().getName()));
		lblToDefeat.setHorizontalAlignment(SwingConstants.CENTER);
		lblToDefeat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblToDefeat.setBounds(190, 361, 470, 31);
		gameResultPanel.add(lblToDefeat);
		
		JButton btnContinue = new JButton("Continue");	
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnContinue.setBounds(373, 425, 110, 30);
		if (team().getHeroes().isEmpty()) {
			btnContinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gameWindow.endGame(false);
				}
			});
		}
		else {
			btnContinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refreshSelectHeroPanel();
					subContentPanelCardLayout.show(subContentPanel, SELECT_HERO_PANEL_STRING);
				}
			});
		}
		
		gameResultPanel.add(btnContinue);
	}
}
