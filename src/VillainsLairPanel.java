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
	
	private JPanel titlePanel;
	
	private CardLayout subContentPanelCardLayout;
	
	private JPanel contentPanel;
	
	private JPanel subContentPanel;
	
	private JPanel startEncounterPanel;
	
	private JPanel selectHeroPanel;
	
	private JPanel heroListPanel;
	
	private ButtonGroup heroSelectButtonGroup;
	
	private JButton btnGo;
	
	private Villain villain;
	
	private Team team;
	
	private Game gameWindow;
	
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
		contentPanel.setBounds(10, 84, 870, 527);
	}
		
	private void addSubContentPanel() {
		subContentPanelCardLayout = new CardLayout();
		subContentPanel = new JPanel(subContentPanelCardLayout);
		subContentPanel.setBounds(0, 0, 634, 516);
		
		addStartEncounterPanel();
		addSelectHeroPanel();
		
		subContentPanelCardLayout.show(subContentPanel, START_ENCOUNTER_PANEL_STRING);
		
		contentPanel.add(subContentPanel);
	}
	
	private void addStartEncounterPanel() {
		startEncounterPanel = new JPanel(null);
		
		subContentPanel.add(startEncounterPanel, START_ENCOUNTER_PANEL_STRING);
		
		JLabel lblYouFound = new JLabel("You found");
		lblYouFound.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblYouFound.setBounds(274, 53, 89, 37);
		startEncounterPanel.add(lblYouFound);
		
		JLabel lblVillainName = new JLabel(villain.toString());
		lblVillainName.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblVillainName.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainName.setBounds(32, 105, 571, 71);
		startEncounterPanel.add(lblVillainName);
		
		JTextArea txtVillainTaunt = new JTextArea();
		txtVillainTaunt.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtVillainTaunt.setLineWrap(true);
		txtVillainTaunt.setEditable(false);
		txtVillainTaunt.setText(villain.getTaunt());
		txtVillainTaunt.setBackground(UIManager.getColor("contentPanel.background"));
		txtVillainTaunt.setBounds(103, 210, 432, 105);
		startEncounterPanel.add(txtVillainTaunt);
		
		JButton btnFight = new JButton("Fight Him!");
		btnFight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshSelectHeroPanel();
				subContentPanelCardLayout.show(subContentPanel, SELECT_HERO_PANEL_STRING);
			}
		});
		btnFight.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnFight.setBounds(123, 375, 145, 35);
		startEncounterPanel.add(btnFight);
		
		JButton btnRunAway = new JButton("Run Away!");
		btnRunAway.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setPanel(HomeBasePanel.HOME_BASE_PANEL_STRING);
			}
		});
		btnRunAway.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnRunAway.setBounds(366, 376, 145, 35);
		startEncounterPanel.add(btnRunAway);
	}
	
	private void addSelectHeroPanel() {
		selectHeroPanel = new JPanel();	
		subContentPanel.add(selectHeroPanel, SELECT_HERO_PANEL_STRING);
		selectHeroPanel.setLayout(null);
	}
	
	private void addHeroListPanel() {
		heroSelectButtonGroup = new ButtonGroup();
		for (Hero hero : team.getHeroes()) {
			JPanel heroPanel = new JPanel();
			heroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			heroListPanel.add(heroPanel);
			heroPanel.setLayout(null);
			
			JLabel heroImage = new JLabel();
			heroImage.setBounds(25, 10, 150, 150);
			heroImage.setIcon(new ImageIcon(VillainsLairPanel.class.getResource(Image.heroPortraitFilepath(hero, 150))));
			heroImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			heroPanel.add(heroImage);
			
			JLabel heroName = new JLabel(String.format("%s the %s", hero.getName(), hero.getType()));
			heroName.setFont(new Font("Tahoma", Font.PLAIN, 18));
			heroName.setHorizontalAlignment(SwingConstants.CENTER);
			heroName.setBounds(10, 158, 184, 27);
			heroPanel.add(heroName);
			
			JLabel lblHealth = new JLabel("Health:");
			lblHealth.setHorizontalAlignment(SwingConstants.LEFT);
			lblHealth.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblHealth.setBounds(25, 196, 67, 27);
			heroPanel.add(lblHealth);
			
			JLabel lblHealthAmount = new JLabel();
			lblHealthAmount.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHealthAmount.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHealthAmount.setBounds(72, 196, 107, 27);
			lblHealthAmount.setText(String.format("%d/%d", hero.getCurrentHealth(), hero.getMaxHealth()));
			heroPanel.add(lblHealthAmount);
			
			JLabel lblSpecialAbility = new JLabel("Special Ability:");
			lblSpecialAbility.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblSpecialAbility.setBounds(10, 233, 107, 27);
			heroPanel.add(lblSpecialAbility);
			
			JTextArea txtSpecialAbility = new JTextArea(hero.getSpecialAbility());
			txtSpecialAbility.setBackground(UIManager.getColor("contentPanel.background"));
			txtSpecialAbility.setEditable(false);
			txtSpecialAbility.setWrapStyleWord(true);
			txtSpecialAbility.setLineWrap(true);
			txtSpecialAbility.setBounds(10, 260, 184, 46);
			heroPanel.add(txtSpecialAbility);
			
			JRadioButton radioButton = new JRadioButton("");
			radioButton.setBounds(89, 326, 26, 23);
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
	
	private void refreshContentPanel() {
		contentPanel.removeAll();
		
		villain = gameWindow.getGame().currentCity().getVillain();
		team = gameWindow.getGame().getTeam();
		
		JLabel lblVillainImage = new JLabel("");
		lblVillainImage.setBounds(633, 32, 200, 400);
		lblVillainImage.setIcon(new ImageIcon(VillainsLairPanel.class.getResource(Image.villainImageFilepath(villain))));
		contentPanel.add(lblVillainImage);
		
		JLabel lblVillainName = new JLabel(villain.toString());
		lblVillainName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVillainName.setHorizontalAlignment(SwingConstants.CENTER);
		lblVillainName.setBounds(633, 443, 200, 25);
		contentPanel.add(lblVillainName);
		
		addSubContentPanel();
		
		add(contentPanel);
	}
	
	private void refreshSelectHeroPanel() {
		selectHeroPanel.removeAll();
		
		JLabel lblChooseAHero = new JLabel("Choose a Hero for the Battle...");
		lblChooseAHero.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseAHero.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblChooseAHero.setBounds(10, 36, 612, 42);
		selectHeroPanel.add(lblChooseAHero);
		
		heroListPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		heroListPanel.setBounds(10, 89, 612, 374);
		
		addHeroListPanel();
		
		btnGo = new JButton("Go!");
		btnGo.setEnabled(false);
		btnGo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnGo.setBounds(217, 474, 183, 29);
		btnGo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO implement this - starts the fight
			}
		});
		selectHeroPanel.add(btnGo);

	}
	
	private void refreshGoButton() {
		btnGo.setEnabled(heroSelectButtonGroup.getSelection() != null);
	}
}
