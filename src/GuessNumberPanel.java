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


public class GuessNumberPanel extends JPanel {

	/**
	 * The VillainsLairPanel which this panel is being added to.
	 */
	private VillainsLairPanel villainsLairPanel;
	
	/**
	 * The Hero playing the game.
	 */
	private Hero hero;
	
	/**
	 * The villain playing the game.
	 */
	private Villain villain;

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
		this.hero = hero;
		this.villain = villain;
		
		setPreferredSize(new Dimension(484, 494));
		setLayout(null);
		
		// testing
		JLabel lblHello = new JLabel("Hello");
		lblHello.setBounds(192, 169, 46, 14);
		add(lblHello);
	}
}
