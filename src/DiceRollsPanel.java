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
import java.awt.SystemColor;


public class DiceRollsPanel extends JPanel {

	/**
	 * The VillainsLairPanel which this panel is being added to.
	 */
	private VillainsLairPanel villainsLairPanel;
	
	/**
	 * The DiceRolls object which controls all the logic of the game.
	 */
	private DiceRolls diceRolls;
	
	/**
	 * A panel containing all the components of the dice rolls panel
	 * except the title.
	 */
	private JPanel contentPanel;

	/**
	 * Creates a new panel which displays the game play for a single
	 * game of dice rolls.
	 * @param villainsLairPanel	The panel which this panel is being added to.
	 * @param hero				The hero playing the game.
	 * @param villain			The villain playing the game.
	 */
	public DiceRollsPanel(VillainsLairPanel villainsLairPanel, Hero hero, Villain villain) {
		super();
		
		this.villainsLairPanel = villainsLairPanel;
		
		this.diceRolls = new DiceRolls(hero, villain);
		
		setPreferredSize(new Dimension(484, 494));
		setLayout(null);
		
		addTitle();
		
		contentPanel = new JPanel();
		contentPanel.setBounds(10, 119, 464, 365);
		add(contentPanel);
		contentPanel.setLayout(null);
		
		showRollScreen();
	}

	/**
	 * Adds a title alerting the user that they are playing
	 * dice rolls.
	 */
	private void addTitle() {
		JLabel lblVillainDemands = new JLabel(String.format("%s demands that you play",
															diceRolls.getVillain()));
		lblVillainDemands.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainDemands.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVillainDemands.setBounds(10, 22, 464, 35);
		add(lblVillainDemands);
		
		JLabel lblDiceRolls = new JLabel("Dice Rolls");
		lblDiceRolls.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDiceRolls.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiceRolls.setBounds(10, 68, 464, 40);
		add(lblDiceRolls);		
	}
	
	/**
	 * Removes all components from the content panel and adds the components
	 * which allow the user to roll the dice.
	 */
	private void showRollScreen() {
		contentPanel.removeAll();
		
		JLabel lblVillainDice = new JLabel("");
		lblVillainDice.setBounds(80, 40, 80, 80);
		contentPanel.add(lblVillainDice);
		lblVillainDice.setIcon(new ImageIcon(DiceRollsPanel.class.getResource(Image.UNROLLED_DICE_IMAGE_FILEPATH)));
		
		JLabel lblHeroDice = new JLabel("");
		lblHeroDice.setBounds(304, 40, 80, 80);
		contentPanel.add(lblHeroDice);
		lblHeroDice.setIcon(new ImageIcon(DiceRollsPanel.class.getResource(Image.UNROLLED_DICE_IMAGE_FILEPATH)));
		
		JButton btnRoll = new JButton("Roll!");
		btnRoll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				diceRolls.roll();
				showResultScreen();
				revalidate();
				repaint();
			}
		});
		btnRoll.setBounds(192, 197, 80, 29);
		contentPanel.add(btnRoll);
		btnRoll.setFont(new Font("Tahoma", Font.PLAIN, 16));
	}
	
	/**
	 * Removes all components from the content panel and adds components to
	 * alert the user if they have won or lost, and let them know if power ups
	 * were used.
	 */
	private void showResultScreen() {
		contentPanel.removeAll();
		
		JLabel lblHeroDice = new JLabel("");
		lblHeroDice.setBounds(80, 40, 80, 80);
		contentPanel.add(lblHeroDice);
		lblHeroDice.setIcon(new ImageIcon(DiceRollsPanel.class.getResource(
								Image.diceImageFilepath(diceRolls.getHeroRoll()))));
		
		JLabel lblVillainDice = new JLabel("");
		lblVillainDice.setBounds(304, 40, 80, 80);
		contentPanel.add(lblVillainDice);
		lblVillainDice.setIcon(new ImageIcon(DiceRollsPanel.class.getResource(
								Image.diceImageFilepath(diceRolls.getVillainRoll()))));
		
		JLabel lblHeroRoll = new JLabel(String.format("You rolled a %s.",
															diceRolls.getHeroRoll()));
		lblHeroRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeroRoll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHeroRoll.setBounds(10, 132, 222, 20);
		contentPanel.add(lblHeroRoll);
		
		
		JLabel lblVillainRoll = new JLabel(String.format("%s rolled a %s.",
															diceRolls.getVillain().getName(),
															diceRolls.getVillainRoll()));
		lblVillainRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainRoll.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblVillainRoll.setBounds(232, 132, 222, 20);
		contentPanel.add(lblVillainRoll);
		
		JLabel lblResult = new JLabel("You have won!");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(10, 229, 444, 30);
		contentPanel.add(lblResult);
		if (diceRolls.getResult() == "Win") {
			lblResult.setText("You have won!");
		} else if (diceRolls.getResult() == "Draw") {
			lblResult.setText("It was a draw!");
		} else {
			lblResult.setText("You have lost!");
		}
		
		if (diceRolls.getHero().getHasBattleAdvantage()) {
			JTextPane txtpnGamblerAbility = new JTextPane();
			txtpnGamblerAbility.setText(String.format(String.format(
												"Your Gambler ability gives you a score of %s.",
												diceRolls.getHeroRoll() + 1)));
			txtpnGamblerAbility.setEditable(false);
			txtpnGamblerAbility.setBackground(SystemColor.menu);
			txtpnGamblerAbility.setBounds(10, 158, 250, 20);
			contentPanel.add(txtpnGamblerAbility);
		}

		if (diceRolls.numIncreaseRolls() > 0) {
			JLabel lblIncreaseRollImage = new JLabel("");
			lblIncreaseRollImage.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblIncreaseRollImage.setIcon(new ImageIcon(DiceRollsPanel.class.getResource(
											Image.powerUpImageFilepath(PowerUpType.INCREASE_ROLL, 25))));
			lblIncreaseRollImage.setBounds(10, 188, 25, 25);
			contentPanel.add(lblIncreaseRollImage);
			
			String template = "";
			if (diceRolls.numIncreaseRolls() == 1) {
				template = "Your Increase Roll power up gives you a score of %s.";
			} else {
				template = "Your Increase Roll power ups give you a score of %s.";
			}
			
			JTextPane txtpnIncreaseRoll = new JTextPane();
			txtpnIncreaseRoll.setEditable(false);
			txtpnIncreaseRoll.setBackground(UIManager.getColor("Panel.background"));
			txtpnIncreaseRoll.setText(String.format(template, diceRolls.getHeroRoll() + diceRolls.getRollIncrease()));
			txtpnIncreaseRoll.setBounds(45, 185, 187, 35);
			contentPanel.add(txtpnIncreaseRoll);
		}
		
		if (diceRolls.getUsedTieBreaker()) {
			JLabel lblTiebreakerImage = new JLabel("");
			lblTiebreakerImage.setIcon(new ImageIcon(DiceRollsPanel.class.getResource(
											Image.powerUpImageFilepath(PowerUpType.TIEBREAKER, 25))));
			lblTiebreakerImage.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblTiebreakerImage.setBounds(135, 270, 25, 25);
			contentPanel.add(lblTiebreakerImage);
			
			JTextPane txtpnTiebreaker = new JTextPane();
			txtpnTiebreaker.setEditable(false);
			txtpnTiebreaker.setText("You use you Tiebreaker power up to win the game!");
			txtpnTiebreaker.setBackground(SystemColor.menu);
			txtpnTiebreaker.setBounds(173, 265, 169, 34);
			contentPanel.add(txtpnTiebreaker);
		}
			
		if (diceRolls.getResult() == "Draw") {
			JButton btnPlayAgain = new JButton("Play Again");
			btnPlayAgain.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnPlayAgain.setBounds(173, 324, 117, 30);
			btnPlayAgain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showRollScreen();
					revalidate();
					repaint();
				}
			});
			contentPanel.add(btnPlayAgain);
			
		} else {
			JButton btnContinue = new JButton("Continue");
			btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnContinue.setBounds(173, 324, 117, 30);
			btnContinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					endGame();
				}
			});
			contentPanel.add(btnContinue);
		}
		
	}
	
	/**
	 * Returns the result of the game to the villain's lair panel, and
	 * shows the game result panel.
	 */
	private void endGame() {
		diceRolls.removeAllPowerUps(PowerUpType.INCREASE_ROLL);
		villainsLairPanel.miniGameFinished(diceRolls.getHasWon());
	}
	
}
