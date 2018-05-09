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
	 * A subpanel of gamePanel which holds all interactive elements.
	 */
	private JPanel interactivePanel;
	
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
		
		addHeadingPanel();
		addPowerUpPanel();
		addGamePanel();
		
		minigame.play();
	}
	
	private void addHeadingPanel() {
		JPanel headingPanel = new JPanel();
		headingPanel.setBounds(10, 10, 464, 130);
		headingPanel.setLayout(null);
		
		JLabel lblVillainName = new JLabel(minigame.getVillain().toString());
		lblVillainName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblVillainName.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainName.setBounds(40, 10, 405, 45);
		headingPanel.add(lblVillainName);
		
		JLabel lblPaperScissorsRock = new JLabel("Paper Scissors Rock!");
		lblPaperScissorsRock.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaperScissorsRock.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblPaperScissorsRock.setBounds(40, 80, 405, 45);
		headingPanel.add(lblPaperScissorsRock);
		
		JLabel lblDemandsYouPlay = new JLabel("has demanded that you play");
		lblDemandsYouPlay.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDemandsYouPlay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDemandsYouPlay.setBounds(40, 43, 405, 45);
		headingPanel.add(lblDemandsYouPlay);

		add(headingPanel);
		}
	
	private void addPowerUpPanel() {
		JPanel powerUpPanel = new JPanel();
		powerUpPanel.setBounds(10, 144, 464, 45);
		powerUpPanel.setLayout(null);
		
		String sense;		
		if (minigame.getHero().getHasBattleAdvantage()) {
			sense = "gambler's wisdom";
		}
		else {
			sense = "Mindreader power-up";
		}
		
		JLabel lblSource = new JLabel(String.format("Your %s lets you sense that %s will not play:",
													sense, minigame.getVillain().toString()));
		lblSource.setBounds(0, 5, 464, 14);
		lblSource.setHorizontalAlignment(SwingConstants.CENTER);
		lblSource.setFont(new Font("Tahoma", Font.PLAIN, 13));
		powerUpPanel.add(lblSource);
		
		String notPlayed = "";
		
		JLabel lblNotPlayed = new JLabel("New label");
		lblNotPlayed.setHorizontalAlignment(SwingConstants.CENTER);
		lblNotPlayed.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNotPlayed.setBounds(10, 20, 424, 20);
		powerUpPanel.add(lblNotPlayed);
		
		if (minigame.getHero().numPowerUps(PowerUpType.MINDREADER) > 0 || minigame.getHero().getHasBattleAdvantage()) {
			add(powerUpPanel);
		}
	}
	
	private void addGamePanel() {
		gamePanel = new JPanel();
		gamePanel.setBounds(10, 200, 464, 283);

		add(gamePanel);
		gamePanel.setLayout(null);
		
		JLabel lblHeroChoice = new JLabel("");
		lblHeroChoice.setBounds(10, 11, 100, 100);
		lblHeroChoice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//TODO add image
		gamePanel.add(lblHeroChoice);
		
		JLabel lblVillainChoice = new JLabel("");
		lblVillainChoice.setBounds(354, 11, 100, 100);
		lblVillainChoice.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		//TODO add image
		gamePanel.add(lblVillainChoice);
		
		addInteractivePanel();
	}
	
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
	
	private void addChoosePanel() {
		JPanel choosePanel = new JPanel();
		//TODO
		interactivePanel.add(choosePanel, CHOOSE_PANEL_STRING);
	}
	
	private void addEndGamePanel() {
		JPanel endGamePanel = new JPanel();
		//TODO
		interactivePanel.add(endGamePanel, END_GAME_PANEL_STRING);
	}
	
	private void addDrawPanel() {
		JPanel drawPanel = new JPanel();
		//TODO
		interactivePanel.add(drawPanel, DRAW_PANEL_STRING);
	}
}
