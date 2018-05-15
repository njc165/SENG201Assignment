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


public class PaperScissorsRockPanel extends JPanel {

	/**
	 * A string representation of this panel, used by the CardLayout
	 * in interactivePanel.
	 */
	private static final String CHOOSE_PANEL_STRING = "Choose Panel";
	
	/**
	 * A string representation of this panel, used by the CardLayout
	 * in interactive panel.
	 */
	private static final String END_GAME_PANEL_STRING = "EndGame Panel";
	
	/**
	 * A string representation of this panel, used by the CardLayout
	 * in interactivePanel.
	 */
	private static final String DRAW_PANEL_STRING = "Draw Panel";
	
	/**
	 * The VillainsLairPanel which this panel is being added to.
	 */
	private VillainsLairPanel villainsLairPanel;
	
	/**
	 * The CardLayout used by this panel.
	 */
	private CardLayout interactivePanelCardLayout;
	
	/**
	 * The instance of PaperScissorsRock being displayed on this panel.
	 */
	private PaperScissorsRock minigame;
	
	/**
	 * The subpanel which contains the choice images and the interactive panel.
	 */
	private JPanel gamePanel;
	
	/**
	 * A panel belonging to interactivePanelCardLayout which
	 * displays the result of a concluded (non-drawn) game.
	 */
	private JPanel endGamePanel;
	
	/**
	 * A subpanel of gamePanel which holds all interactive elements.
	 */
	private JPanel interactivePanel;
	
	/**
	 * A string representing the hero's choice of
	 * paper, scissors, or rock.
	 */
	private JLabel lblHeroChoice;
	
	/**
	 * A string representing the villain's choice of
	 * paper, scissors, or rock.
	 */
	private JLabel lblVillainChoice;
	
	/**
	 * Creates a new panel which displays the game play for a single
	 * game of paper scissors rock.
	 * @param villainsLairPanel	The panel which this panel is being added to.
	 * @param hero				The hero playing the game.
	 * @param villain			The villain playing the game.
	 */
	public PaperScissorsRockPanel(VillainsLairPanel villainsLairPanel, Hero hero, Villain villain) {
		super();
		
		this.villainsLairPanel = villainsLairPanel;
		this.minigame = new PaperScissorsRock(hero, villain);
		
		setPreferredSize(new Dimension(484, 494));
		setLayout(null);
		
		buildPanel();			
	}
	
	/**
	 * Creates and adds all components belonging to
	 * this instance of PaperScissorsRockPanel.
	 */
	private void buildPanel() {
		removeAll();
		minigame.updateVillainsChoice();
		addHeadingPanel();
		addPowerUpPanel();
		addGamePanel();
		revalidate();
		repaint();
	}
	
	/**
	 * Create and populate the heading panel,
	 * which contains an introduction to the minigame.
	 */
	private void addHeadingPanel() {
		JPanel headingPanel = new JPanel();
		headingPanel.setBounds(10, 10, 464, 111);
		headingPanel.setLayout(null);
		
		JLabel lblVillainName = new JLabel(minigame.getVillain().toString());
		lblVillainName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVillainName.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainName.setBounds(10, 10, 435, 31);
		headingPanel.add(lblVillainName);
		
		JLabel lblPaperScissorsRock = new JLabel("Paper Scissors Rock!");
		lblPaperScissorsRock.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaperScissorsRock.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPaperScissorsRock.setBounds(10, 63, 445, 45);
		headingPanel.add(lblPaperScissorsRock);
		
		JLabel lblDemandsYouPlay = new JLabel("has demanded that you play");
		lblDemandsYouPlay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDemandsYouPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDemandsYouPlay.setBounds(10, 43, 435, 24);
		headingPanel.add(lblDemandsYouPlay);

		add(headingPanel);
		}
	
	/**
	 * Create and populate the powerUp panel,
	 * which implements the Mindreader and Gambler abilities.
	 * This panel is only visible to the player if their
	 * hero is a Gambler or is using a Mindreader power-up.
	 */
	private void addPowerUpPanel() {
		JPanel powerUpPanel = new JPanel();
		powerUpPanel.setBounds(10, 132, 464, 69);
		powerUpPanel.setLayout(null);
		
		String sense;		
		if (minigame.getHero().getHasBattleAdvantage()) {
			sense = "gambler's wisdom";
		}
		else {
			sense = "Mindreader power-up";
		}
		
		JLabel lblSource = new JLabel(String.format("Your %s lets you sense that they will not play:",
													sense));
		lblSource.setBounds(35, 5, 397, 16);
		lblSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		powerUpPanel.add(lblSource);
		
		JLabel lblMindreaderIconLeft = new JLabel("");
		lblMindreaderIconLeft.setBounds(7, 1, 25, 25);
		lblMindreaderIconLeft.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblMindreaderIconLeft.setIcon(new ImageIcon(PaperScissorsRockPanel.class.getResource(
													Image.powerUpImageFilepath(PowerUpType.MINDREADER, 25))));
		lblMindreaderIconLeft.setVisible(false);
		powerUpPanel.add(lblMindreaderIconLeft);		
		
		String notPlayed = minigame.revealNot();
		
		JLabel lblNotPlayed = new JLabel(notPlayed);
		lblNotPlayed.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotPlayed.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNotPlayed.setBounds(185, 30, 94, 20);
		powerUpPanel.add(lblNotPlayed);
		
		if (minigame.getHero().getHasBattleAdvantage()) {
			add(powerUpPanel);
		} else if (minigame.getHero().numPowerUps(PowerUpType.MINDREADER) > 0) {
			minigame.removePowerUps(PowerUpType.MINDREADER, 1);
			lblMindreaderIconLeft.setVisible(true);
			add(powerUpPanel);
		}
		
	}
	
	/**
	 * Create and populate the game panel, which holds images of the current
	 * choices for the hero and villain, and contains the interactive panel.
	 */
	private void addGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setBounds(10, 200, 464, 283);

		add(gamePanel);
		gamePanel.setLayout(null);
		
		lblHeroChoice = new JLabel("");
		lblHeroChoice.setBounds(10, 11, 100, 100);
		lblHeroChoice.setIcon(new ImageIcon(PaperScissorsRockPanel.class.getResource(Image.PSR_UNDECIDED_FILEPATH)));
		gamePanel.add(lblHeroChoice);
		
		lblVillainChoice = new JLabel("");
		lblVillainChoice.setBounds(354, 11, 100, 100);
		lblVillainChoice.setIcon(new ImageIcon(PaperScissorsRockPanel.class.getResource(Image.PSR_UNDECIDED_FILEPATH)));
		gamePanel.add(lblVillainChoice);
		
		addInteractivePanel();
	}
	
	/**
	 * Create and populate the interactive panel.
	 * This panel holds all interactive elements of the game and
	 * uses a card layout to respond to the player's choices.
	 */
	private void addInteractivePanel() {
		interactivePanel = new JPanel();
		interactivePanel.setBounds(120, 11, 223, 260);
		interactivePanelCardLayout = new CardLayout(0, 0);	
		interactivePanel.setLayout(interactivePanelCardLayout);
		
		addChoosePanel();
		addEndGamePanel();
		addDrawPanel();
		
		interactivePanelCardLayout.show(interactivePanel, CHOOSE_PANEL_STRING);
		
		gamePanel.add(interactivePanel);
	}
	
	/**
	 * Create and populate the choose panel; a panel owned by interactive panel
	 * which has buttons to select a choice of
	 * paper, scissors or rock.
	 */
	private void addChoosePanel() {
		JPanel choosePanel = new JPanel();
		choosePanel.setLayout(null);
		
		JButton btnPaper = new JButton("Paper");
		btnPaper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minigame.setHerosChoice("Paper");
				refreshGamePanel();
				String result = minigame.computeOutcome();
				manageGameOutcome(result);
			}
		});
		btnPaper.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPaper.setBounds(10, 100, 203, 40);
		choosePanel.add(btnPaper);
		
		JButton btnScissors = new JButton("Scissors");
		btnScissors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minigame.setHerosChoice("Scissors");
				refreshGamePanel();
				String result = minigame.computeOutcome();
				manageGameOutcome(result);
			}
		});
		btnScissors.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnScissors.setBounds(10, 151, 203, 40);
		choosePanel.add(btnScissors);
		
		JButton btnRock = new JButton("Rock");
		btnRock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				minigame.setHerosChoice("Rock");
				refreshGamePanel();
				String result = minigame.computeOutcome();
				manageGameOutcome(result);
			}
		});
		btnRock.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRock.setBounds(10, 202, 203, 40);
		choosePanel.add(btnRock);
		
		interactivePanel.add(choosePanel, CHOOSE_PANEL_STRING);
		
		JLabel lblPromptChoice = new JLabel("What will you play?");
		lblPromptChoice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPromptChoice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPromptChoice.setBounds(10, 11, 203, 75);
		choosePanel.add(lblPromptChoice);
	}
	
	/**
	 * Create and populate the end game panel; a panel owned by the
	 * interactive panel which displays the result of a concluded,
	 * non-drawn game.
	 */
	private void addEndGamePanel() {
		endGamePanel = new JPanel();
		endGamePanel.setLayout(null);
		interactivePanel.add(endGamePanel, END_GAME_PANEL_STRING);
	}
	
	/**
	 * Create and populate the draw panel; a panel owned by the
	 * interactive panel which displays the result of a drawn game and
	 * prompts the player to play again.
	 */
	private void addDrawPanel() {
		JPanel drawPanel = new JPanel();
		drawPanel.setLayout(null);
				
		JLabel lblDraw = new JLabel("DRAW");
		lblDraw.setHorizontalAlignment(SwingConstants.CENTER);
		lblDraw.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDraw.setBounds(10, 11, 203, 80);
		drawPanel.add(lblDraw);
		
		JLabel lblPlayAgain = new JLabel("You'll have to play again.");
		lblPlayAgain.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPlayAgain.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayAgain.setBounds(0, 102, 223, 40);
		drawPanel.add(lblPlayAgain);
		
		JButton btnPlayAgain = new JButton("Play Again");
		btnPlayAgain.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlayAgain.setBounds(10, 167, 203, 40);
		btnPlayAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buildPanel();
			}
		});
		drawPanel.add(btnPlayAgain);
		
		interactivePanel.add(drawPanel, DRAW_PANEL_STRING);
	}
	
	/**
	 * Given a game outcome (win, loss, draw), responds appropriately by
	 * switching the current view of interactivePanel to the corresponding
	 * panel (endGamePanel or drawPanel). In the case of a draw, this method
	 * checks for a Tiebreaker and treats the game as a win if one is found.
	 * @param outcome A string representing the outcome of the game.
	 */
	private void manageGameOutcome(String outcome) {
		if (outcome == "Draw") {
			if (minigame.getHero().numPowerUps(PowerUpType.TIEBREAKER) > 0) {
				outcome = "Win";
				refreshEndGamePanel(outcome, true);
				interactivePanelCardLayout.show(interactivePanel, END_GAME_PANEL_STRING);
			}
			else {
				interactivePanelCardLayout.show(interactivePanel, DRAW_PANEL_STRING);
			}
		}
		else {
			refreshEndGamePanel(outcome, false);
			interactivePanelCardLayout.show(interactivePanel, END_GAME_PANEL_STRING);
		}
	}
	
	/**
	 * Refreshes gamePanel by updating labels to show the correct image.
	 */
	private void refreshGamePanel() {
		String heroChoiceImageFilepath = Image.getHeroPSRImage(minigame.getHerosChoice());
		String villainChoiceImageFilepath = Image.getVillainPSRImage(minigame.getVillainsChoice());
		
		lblHeroChoice.setIcon(new ImageIcon(PaperScissorsRockPanel.class.getResource(heroChoiceImageFilepath)));
		lblVillainChoice.setIcon(new ImageIcon(PaperScissorsRockPanel.class.getResource(villainChoiceImageFilepath)));
	}
	
	/**
	 * Refreshes the endGame panel by creating components and setting their
	 * attributed depening on the result of the game, including if the game
	 * was won via a Tiebreaker power-up.
	 * @param outcome A string representation of the outcome of the game.
	 * @param wonViaTiebreaker A boolean showing how the game was won.
	 *                         True if a Tiebreaker was used,
	 *                         False otherwise.
	 */
	private void refreshEndGamePanel(String outcome, boolean wonViaTiebreaker) {
		JLabel lblResult = new JLabel();
		String result;
		if (outcome == "Win") {
			minigame.setHasWon(true);
			result = "VICTORY";
		}
		else {
			result = "DEFEAT";
		}
		lblResult.setText(result);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(10, 11, 203, 80);
		endGamePanel.add(lblResult);
		
		JLabel lblTiebreakerActivated = new JLabel("Your Tiebreaker power-up activated!");
		lblTiebreakerActivated.setHorizontalAlignment(SwingConstants.CENTER);
		lblTiebreakerActivated.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTiebreakerActivated.setBounds(0, 82, 223, 40);
			
		JLabel lblTiebreakerIcon = new JLabel();
		lblTiebreakerIcon.setBounds(90, 116, 38, 38);
		lblTiebreakerIcon.setIcon(new ImageIcon(PaperScissorsRockPanel.class.getResource(Image.powerUpImageFilepath(PowerUpType.TIEBREAKER, 38))));
		
		if (wonViaTiebreaker) {
			endGamePanel.add(lblTiebreakerActivated);
			endGamePanel.add(lblTiebreakerIcon);
			minigame.removePowerUps(PowerUpType.TIEBREAKER, 1);
		}

		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnContinue.setBounds(10, 167, 203, 40);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGame();
			}
		});
		endGamePanel.add(btnContinue);
	}
	
	private void endGame() {
		villainsLairPanel.miniGameFinished(minigame.getHasWon());
	}
}
