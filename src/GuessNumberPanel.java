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
import javax.swing.JSlider;
import java.awt.SystemColor;


public class GuessNumberPanel extends JPanel {

	/**
	 * The VillainsLairPanel which this panel is being added to.
	 */
	private VillainsLairPanel villainsLairPanel;
	
	/**
	 * The GuessNumber object which controls all the logic of the game.
	 */
	private GuessNumber guessNumber;
	
	/**
	 * A panel containing all the components of the guess the number
	 * panel except the title.
	 */
	private JPanel contentPanel;

	/**
	 * Creates a new panel which displays the game play for a single
	 * game of guess the number.
	 * @param villainsLairPanel	The panel which this panel is being added to.
	 * @param hero				The hero playing the game.
	 * @param villain			The villain playing the game.
	 */
	public GuessNumberPanel(VillainsLairPanel villainsLairPanel, Hero hero, Villain villain) {
		super();
		
		this.villainsLairPanel = villainsLairPanel;
		
		this.guessNumber = new GuessNumber(hero, villain);
		
		setPreferredSize(new Dimension(484, 494));
		setLayout(null);
		
		addTitle();
		
		contentPanel = new JPanel();
		contentPanel.setBounds(10, 119, 464, 365);
		add(contentPanel);
		contentPanel.setLayout(null);
		
		showGuessScreen();
//		showGuessAgainScreen();
//		showResultScreen();
	}
	
	
	/**
	 * Adds a title alerting the user that they are playing
	 * guess the number.
	 */
	private void addTitle() {
		JLabel lblVillainDemands = new JLabel(String.format("%s demands that you play",
															guessNumber.getVillain()));
		lblVillainDemands.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainDemands.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVillainDemands.setBounds(10, 22, 464, 35);
		add(lblVillainDemands);
		
		JLabel lblGuessNumber = new JLabel("Guess the Number");
		lblGuessNumber.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblGuessNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessNumber.setBounds(10, 68, 464, 40);
		add(lblGuessNumber);		
	}
	
	/**
	 * Removes all components from the content panel, and adds the
	 * components asking the user to guess a number between one
	 * and ten.
	 */
	private void showGuessScreen() {
		
		JLabel lblVillainHasChosen1 = new JLabel(String.format("%s has chosen a number",
																guessNumber.getVillain()));
		lblVillainHasChosen1.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainHasChosen1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVillainHasChosen1.setBounds(10, 25, 444, 25);
		contentPanel.add(lblVillainHasChosen1);
		
		JLabel lblVillainHasChosen2 = new JLabel(String.format("between 1 and %s.",
														GuessNumber.MAX_NUMBER));
		lblVillainHasChosen2.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainHasChosen2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVillainHasChosen2.setBounds(10, 50, 444, 25);
		contentPanel.add(lblVillainHasChosen2);
		
		JLabel lblUnknownNumberImage = new JLabel("?");
		lblUnknownNumberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUnknownNumberImage.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblUnknownNumberImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnknownNumberImage.setBounds(363, 98, 60, 60);
		contentPanel.add(lblUnknownNumberImage);
		
		if (guessNumber.getHero().getHasBattleAdvantage()) {
			JTextPane txtpnGambler = new JTextPane();
			txtpnGambler.setText("Your Gambler special ability gives you 1 extra guess.");
			txtpnGambler.setEditable(false);
			txtpnGambler.setBackground(SystemColor.menu);
			txtpnGambler.setBounds(56, 98, 310, 25);
			contentPanel.add(txtpnGambler);
		}
		
		if (guessNumber.numExtraGuesses() > 0) {
			JLabel lblExtraGuessImage = new JLabel("");
			lblExtraGuessImage.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblExtraGuessImage.setIcon(new ImageIcon(DiceRollsPanel.class.getResource(
											Image.powerUpImageFilepath(PowerUpType.EXTRA_GUESS, 25))));
			lblExtraGuessImage.setBounds(56, 127, 25, 25);
			contentPanel.add(lblExtraGuessImage);
			
			String extraGuessText = "";
			if (guessNumber.numExtraGuesses() == 1) {
				extraGuessText = "Your Extra Guess power up gives you 1 extra guess.";
			} else {
				extraGuessText = String.format("Your Extra Guess power ups give you %s extra guesses.",
										guessNumber.numExtraGuesses());
			}
			JTextPane txtpnExtraGuess = new JTextPane();
			txtpnExtraGuess.setText(extraGuessText);
			txtpnExtraGuess.setEditable(false);
			txtpnExtraGuess.setBackground(UIManager.getColor("Panel.background"));
			txtpnExtraGuess.setBounds(91, 122, 187, 35);
			contentPanel.add(txtpnExtraGuess);
		}
		
		JLabel lblGuessesLeft = new JLabel(String.format("You have %s chances to guess the number.",
															guessNumber.getGuessesLeft()));
		lblGuessesLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessesLeft.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGuessesLeft.setBounds(10, 178, 444, 30);
		contentPanel.add(lblGuessesLeft);
		
		JSlider sldGuess = new JSlider();
		sldGuess.setValue(1);
		sldGuess.setSnapToTicks(true);
		sldGuess.setPaintTicks(true);
		sldGuess.setMajorTickSpacing(1);
		sldGuess.setPaintLabels(true);
		sldGuess.setMinimum(1);
		sldGuess.setMaximum(GuessNumber.MAX_NUMBER);
		sldGuess.setBounds(56, 237, 352, 50);
		contentPanel.add(sldGuess);

		
		JButton btnGo = new JButton("Go!");
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGo.setBounds(187, 308, 89, 30);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int guess = sldGuess.getValue();
				guessNumber.guess(guess);
				if (guessNumber.getHasWon() || guessNumber.getGuessesLeft() <= 0) {
					showResultScreen();
				} else {
					showGuessAgainScreen();
				}
				
				repaint();
				revalidate();
			}
		});
		contentPanel.add(btnGo);
	}
	
	/**
	 * Removes all components from the content panel, and adds
	 * components to inform the user whether their guess was too high
	 * or too low, and allow them to guess again.
	 */
	private void showGuessAgainScreen() {
		contentPanel.removeAll();
		
		String text = "";
		if (guessNumber.getGuess() < guessNumber.getNumberToGuess()) {
			text = "Your guess was too low!";
		} else {
			text = "Your guess was too high!";
		}
		JLabel lblTooHighOrLow = new JLabel(text);
		lblTooHighOrLow.setHorizontalAlignment(SwingConstants.CENTER);
		lblTooHighOrLow.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTooHighOrLow.setBounds(117, 111, 230, 30);
		contentPanel.add(lblTooHighOrLow);
		
		JLabel lblUnknownNumberImage = new JLabel("?");
		lblUnknownNumberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblUnknownNumberImage.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblUnknownNumberImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnknownNumberImage.setBounds(363, 98, 60, 60);
		contentPanel.add(lblUnknownNumberImage);
		
		JLabel lblGuessesLeft = new JLabel(String.format("You have %s guesses remaining.",
													guessNumber.getGuessesLeft()));
		lblGuessesLeft.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuessesLeft.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblGuessesLeft.setBounds(10, 178, 444, 30);
		contentPanel.add(lblGuessesLeft);
		
		JSlider sldGuess = new JSlider();
		sldGuess.setValue(guessNumber.getGuess());
		sldGuess.setSnapToTicks(true);
		sldGuess.setPaintTicks(true);
		sldGuess.setMajorTickSpacing(1);
		sldGuess.setPaintLabels(true);
		sldGuess.setMinimum(1);
		sldGuess.setMaximum(GuessNumber.MAX_NUMBER);
		sldGuess.setBounds(56, 237, 352, 50);
		contentPanel.add(sldGuess);
		
		
		JButton btnGo = new JButton("Go!");
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGo.setBounds(187, 308, 89, 30);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int guess = sldGuess.getValue();
				guessNumber.guess(guess);
				if (guessNumber.getHasWon() || guessNumber.getGuessesLeft() <= 0) {
					showResultScreen();
				} else {
					showGuessAgainScreen();
				}
				
				repaint();
				revalidate();
			}
		});
		contentPanel.add(btnGo);
	}
	
	/**
	 * Removes all components from the content panel and adds components
	 * to inform the user when they have won or lost, and to reveal the
	 * chosen number.
	 */
	private void showResultScreen() {
		contentPanel.removeAll();
		
		JLabel lblNumberImage = new JLabel(Integer.toString(guessNumber.getNumberToGuess()));
		lblNumberImage.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNumberImage.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblNumberImage.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumberImage.setBounds(363, 98, 60, 60);
		contentPanel.add(lblNumberImage);
		
		String result = "";
		if (guessNumber.getHasWon()) {
			result = "You win!";
		} else {
			result = "You lost!";
		}
		JLabel lblResult = new JLabel(result);
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblResult.setBounds(116, 109, 232, 30);
		contentPanel.add(lblResult);
		
		String message = "";
		if (guessNumber.getHasWon()) {
			message = String.format("You have guessed %s's number correctly.",
									guessNumber.getVillain());
		} else {
			message = "You have run out of guesses.";
		}
		JLabel lblMessage = new JLabel(message);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMessage.setBounds(10, 199, 444, 30);
		contentPanel.add(lblMessage);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnContinue.setBounds(177, 308, 110, 30);
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				endGame();
			}
		});
		contentPanel.add(btnContinue);
	
	}
	
	/**
	 * Returns the result of the game to the VillainsLairPanel,
	 * and shows the game result panel.
	 */
	private void endGame() {
		guessNumber.removeAllPowerUps(PowerUpType.EXTRA_GUESS);
		// TODO
	}
	
	
}
