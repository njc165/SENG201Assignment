import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;

public class PowerUpDenPanel extends JPanel implements Refreshable {
	
	public static final String POWER_UP_DEN_PANEL_STRING = "Power-up Den Panel";
	
	/**
	 * The game window which this panel is a part of.
	 */
	private Game gameWindow;
	
	/**
	 * The main content panel of the PowerUpDenPanel.
	 */
	private JPanel contentPanel;
	
	/**
	 * A ButtonGroup holding the radio buttons for each hero.
	 */
	private ButtonGroup heroRadioButtonGroup;
	
	/**
	 * A ButtonGroup holding the radio buttons for each power up.
	 */
	private ButtonGroup powerUpRadioButtonGroup;
	
	/**
	 * A button which applies the selected power up to the selected
	 * hero when pressed. Only enabled if there is a power up and a hero
	 * selected.
	 */
	private JButton btnApply;
	
	/**
	 * Displays a message informing the user when a power up has been
	 * applied to a hero.
	 */
	private JTextPane txtpnPowerUpApplied;
	
	/**
	 * Creates a new PowerUpDenPanel and adds all the required components.
	 * @param game	The main game window which this panel is a part of.
	 */
	public PowerUpDenPanel(Game game) {
		super();
		this.gameWindow = game;
		
		setPreferredSize(new Dimension(880, 610));
		setLayout(null);
		
		addTitle();
		addSidePanel();
		addContentPanel();
	}
	
	public void refresh() {
		refreshContentPanel();
	}
	
	/**
	 * Helper method to return the team of the current GameEnvironment.
	 * @return		The team of the current GameEnvirionment.
	 */
	private Team team() {
		return gameWindow.getGame().getTeam();
	}
	
	/**
	 * Takes a ButtonGroup of JRadioButtons and returns the selected button.
	 * @param buttonGroup	The relevant ButtonGroup.
	 * @return				The currently selected button in the ButtonGroup.
	 */
	private JRadioButton selectedButton(ButtonGroup buttonGroup) {
		JRadioButton selectedButton = null;
		for (Enumeration<AbstractButton> e = buttonGroup.getElements(); e.hasMoreElements();) {
			JRadioButton button = (JRadioButton) e.nextElement();
			if (button.isSelected()) {
				selectedButton = button;
			}
		}
		return selectedButton;
		       
	}
	
	/**
	 * Adds the power up den title label.
	 */
	private void addTitle() {
		JLabel lblPowerUpDen = new JLabel("POWER UP DEN");
		lblPowerUpDen.setHorizontalAlignment(SwingConstants.CENTER);
		lblPowerUpDen.setFont(new Font("Tahoma", Font.PLAIN, 60));
		lblPowerUpDen.setBounds(10, 11, 860, 75);
		add(lblPowerUpDen);		
	}

	/**
	 * Adds the side panel and all its components to the PowerUpDenPanel.
	 */
	private void addSidePanel() {
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBounds(10, 97, 215, 502);
		add(sidePanel);
		sidePanel.setLayout(null);
		
		JLabel lblWelcome1 = new JLabel("Welcome to the");
		lblWelcome1.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWelcome1.setBounds(10, 11, 195, 25);
		sidePanel.add(lblWelcome1);
		
		JLabel lblWelcome2 = new JLabel("Power Up Den!");
		lblWelcome2.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblWelcome2.setBounds(10, 32, 195, 25);
		sidePanel.add(lblWelcome2);

		JTextPane txtpnInfo = new JTextPane();
		txtpnInfo.setEditable(false);
		txtpnInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnInfo.setBackground(UIManager.getColor("Panel.background"));
		txtpnInfo.setText("Here you can apply power ups you own to heroes on your team.\r\n\r\nIf you don't own any power ups, you can buy them from the Shop.");
		txtpnInfo.setBounds(10, 68, 205, 97);
		sidePanel.add(txtpnInfo);
		
		JButton btnReturnToHomeBase = new JButton("Return to Home Base");
		btnReturnToHomeBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setPanel(HomeBasePanel.HOME_BASE_PANEL_STRING);
			}
		});
		btnReturnToHomeBase.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturnToHomeBase.setBounds(10, 450, 195, 30);
		sidePanel.add(btnReturnToHomeBase);		
	}
	
	/**
	 * Adds the content panel to the PowerUpDenPanel.
	 * The components of the content panel are added every time the
	 * content panel is refreshed.
	 */
	private void addContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPanel.setBounds(235, 97, 635, 502);
		add(contentPanel);		
		contentPanel.setLayout(null);
	}
	
	/**
	 * Removes all components from the content panel, and adds them again.
	 */
	private void refreshContentPanel() {
		contentPanel.removeAll();
		
		JLabel lblSelectHero = new JLabel("Select a Hero:");
		lblSelectHero.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectHero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelectHero.setBounds(10, 6, 615, 40);
		contentPanel.add(lblSelectHero);
		
		JLabel lblSelectPowerUp = new JLabel("Select a Power Up:");
		lblSelectPowerUp.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectPowerUp.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSelectPowerUp.setBounds(10, 222, 615, 40);
		contentPanel.add(lblSelectPowerUp);
		
		addSelectHeroPanel();
		
		addSelectPowerUpPanel();

		btnApply = new JButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JRadioButton selectedHeroRadioButton = selectedButton(heroRadioButtonGroup);
				JRadioButton selectedPowerUpRadioButton = selectedButton(powerUpRadioButtonGroup);
				
				Hero hero = (Hero) selectedHeroRadioButton.getClientProperty("Hero");
				PowerUp powerUp = (PowerUp) selectedPowerUpRadioButton.getClientProperty("PowerUp");
				
				PowerUp powerUpFromTeam = team().popPowerUpFromList(powerUp.getType());
				hero.addPowerUp(powerUpFromTeam);
				
				refresh();
				
				txtpnPowerUpApplied.setText(String.format(
						"%s has been powered up with one %s!",
						hero,
						powerUp));
			}
		});
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApply.setBounds(250, 446, 135, 30);
		contentPanel.add(btnApply);
		
		refreshApplyButton();
		
		txtpnPowerUpApplied = new JTextPane();
		txtpnPowerUpApplied.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtpnPowerUpApplied.setBackground(UIManager.getColor("Panel.background"));
		txtpnPowerUpApplied.setBounds(395, 442, 230, 50);
		contentPanel.add(txtpnPowerUpApplied);


	}
	
	/**
	 * Adds a panel showing each hero on the team, with a radio button
	 * allowing the user to select that hero.
	 */
	private void addSelectHeroPanel() {
		JPanel selectHeroPanel = new JPanel();
		selectHeroPanel.setBounds(21, 57, 593, 154);
		contentPanel.add(selectHeroPanel);
		selectHeroPanel.setLayout(new GridLayout(0, team().getHeroes().size(), 0, 0));
		
		heroRadioButtonGroup = new ButtonGroup();
		
		for (Hero hero: team().getHeroes()) {
			
			JPanel heroPanel = new JPanel();
			selectHeroPanel.add(heroPanel);
			heroPanel.setLayout(new BorderLayout(0, 0));
			
			JLabel lblHeroImage = new JLabel("");
			lblHeroImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeroImage.setIcon(new ImageIcon(PowerUpDenPanel.class.getResource(
									Image.heroPortraitFilepath(hero, 100))));
			heroPanel.add(lblHeroImage, BorderLayout.NORTH);
			
			JLabel lblHeroName = new JLabel(hero.toString());
			lblHeroName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
			heroPanel.add(lblHeroName, BorderLayout.CENTER);
			
			JRadioButton radioButton = new JRadioButton("");
			radioButton.setHorizontalAlignment(SwingConstants.CENTER);
			radioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refreshApplyButton();
				}
			});
			radioButton.putClientProperty("Hero", hero);
			heroPanel.add(radioButton, BorderLayout.SOUTH);
			heroRadioButtonGroup.add(radioButton);
		}
	}
	
	/**
	 * Adds a panel showing each power up and the number of that power up
	 * owned by the team. Each power up has a radio button allowing the user
	 * to select it, which is only enabled if the team owns at least one of
	 * that power up.
	 */
	private void addSelectPowerUpPanel() {
		JPanel selectPowerUpPanel = new JPanel();
		selectPowerUpPanel.setBounds(20, 273, 593, 154);
		contentPanel.add(selectPowerUpPanel);
		selectPowerUpPanel.setLayout(new GridLayout(0, GameEnvironment.ALL_POWER_UPS.length, 0, 0));
		
		powerUpRadioButtonGroup = new ButtonGroup();
		
		for (PowerUp powerUp: GameEnvironment.ALL_POWER_UPS) {
			
			JPanel powerUpPanel = new JPanel();
			selectPowerUpPanel.add(powerUpPanel);
			powerUpPanel.setLayout(null);
			
			JLabel lblPowerUpImage = new JLabel("");
			//TODO add power up images
			lblPowerUpImage.setBorder(new LineBorder(new Color(0, 0, 0)));
			lblPowerUpImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblPowerUpImage.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblPowerUpImage.setBounds(34, 0, 80, 80);
			powerUpPanel.add(lblPowerUpImage);
			
			JLabel lblPowerUpType = new JLabel(powerUp.toString());
			lblPowerUpType.setHorizontalAlignment(SwingConstants.CENTER);
			lblPowerUpType.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblPowerUpType.setBounds(10, 82, 128, 25);
			powerUpPanel.add(lblPowerUpType);
			
			JLabel lblNumOwned = new JLabel(String.format("(%s owned)",
									team().numPowerUpsOwned(powerUp.getType())));
			lblNumOwned.setHorizontalAlignment(SwingConstants.CENTER);
			lblNumOwned.setFont(new Font("Tahoma", Font.PLAIN, 11));
			lblNumOwned.setBounds(10, 106, 128, 25);
			powerUpPanel.add(lblNumOwned);
			
			JRadioButton radioButton = new JRadioButton("");
			radioButton.setHorizontalAlignment(SwingConstants.CENTER);
			radioButton.setBounds(0, 133, 148, 21);
			radioButton.setEnabled(team().numPowerUpsOwned(powerUp.getType()) > 0);
			radioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					refreshApplyButton();
				}
			});
			radioButton.putClientProperty("PowerUp", powerUp);
			powerUpPanel.add(radioButton);
			powerUpRadioButtonGroup.add(radioButton);
		}
	}
	
	/**
	 * Enables the Apply button if there is a hero and a power up
	 * selected, otherwise disables it.
	 */
	private void refreshApplyButton() {
		btnApply.setEnabled(heroRadioButtonGroup.getSelection() != null
						 	&& powerUpRadioButtonGroup.getSelection() != null);
	}

}
