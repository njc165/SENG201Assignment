import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Font;
import java.awt.GridLayout;

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
import java.awt.Component;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class HospitalPanel extends JPanel implements Refreshable {

	/**
	 * A String representation of the Hospital Panel
	 */
	public static final String HOSPITAL_PANEL_STRING = "Hospital Panel";
	
	/**
	 * A String representation of the status panel
	 */
	private static final String STATUS_PANEL_STRING = "Status Panel";
	
	/**
	 * A String representation of the apply panel.
	 */
	private static final String APPLY_PANEL_STRING = "Apply Panel";
	
	/**
	 * The card layout used by the main content panel.
	 */
	private CardLayout contentPanelCardLayout;
	
	/**
	 * The main content panel for the hospital.
	 */
	private JPanel contentPanel;
	
	/**
	 * A subpanel of the hospital content panel.
	 */
	private JPanel statusPanel;
	
	/**
	 * The main game window.
	 */
	private Game gameWindow;
	
	/**
	 * A constructor for the HospitalPanel class.
	 * @param game
	 */
	public HospitalPanel(Game game) {
		super();
		this.gameWindow = game;
		
		setPreferredSize(new Dimension(880, 610));
		setLayout(null);
		
		addTitlePanel();
		addSidePanel();
		addContentPanel();
		}

	/**
	 * Provides access to the team associated with the current Game.
	 * @return
	 */
	private Team team() {
		return gameWindow.getGame().getTeam();
	}
	
	/**
	 * Refresh all panels belonging to HospitalPanel which
	 * display information which may have changed.
	 */
	public void refresh() {
		// TODO remove after testing
//		team().getHeroes().get(0).setAppliedHealingItem(new AlicornDust());
//		team().getHeroes().get(1).setAppliedHealingItem(new SuspiciousTonic());
		refreshStatusPanel();
		refreshApplyPanel();
	}
	
	/**
	 * Create the title panel and add it to the main window.
	 */
	private void addTitlePanel() {
		JPanel titlePanel = new JPanel();
		titlePanel.setBounds(10, 11, 860, 64);
		titlePanel.setLayout(null);
		
		JLabel lblTitle = new JLabel("HOSPITAL");
		lblTitle.setBounds(10, 0, 850, 64);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 60));
		titlePanel.add(lblTitle);		
		
		add(titlePanel);		
	}
	
	/**
	 * Create the side panel and add it to the HospitalPanel
	 */
	private void addSidePanel() {
		JPanel sidePanel = new JPanel();
		sidePanel.setBounds(10, 86, 215, 513);
		sidePanel.setLayout(null);
		
		JButton btnStatus = new JButton("View Statuses");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshStatusPanel();
				contentPanelCardLayout.show(contentPanel, STATUS_PANEL_STRING);
			}
		});
		btnStatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnStatus.setBounds(10, 70, 195, 30);
		sidePanel.add(btnStatus);
		
		JButton btnApply = new JButton("Apply Items");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshApplyPanel();
				contentPanelCardLayout.show(contentPanel, APPLY_PANEL_STRING);
			}
		});
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApply.setBounds(10, 138, 195, 30);
		sidePanel.add(btnApply);
		
		JLabel lblInfo = new JLabel("Info:");
		lblInfo.setLocation(10, 265);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInfo.setSize(45, 20);
		sidePanel.add(lblInfo);
		
		JTextArea txtAreaInfo = new JTextArea();
		txtAreaInfo.setBackground(new Color(240, 240, 240));
		txtAreaInfo.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtAreaInfo.setLineWrap(true);
		txtAreaInfo.setText("Healing Items can be purchased\r\nfrom the Shop.\r\n\r\nOnly one healing item may be\r\napplied to a Hero at any time.");
		txtAreaInfo.setEditable(false);
		txtAreaInfo.setBounds(10, 296, 195, 152);
		sidePanel.add(txtAreaInfo);
		
		JButton btnBack = new JButton("Return to Home Base");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setPanel(HomeBasePanel.HOME_BASE_PANEL_STRING);
			}
		});
		btnBack.setBounds(10, 462, 195, 30);	
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sidePanel.add(btnBack);
		
		add(sidePanel);
	}
	
	/**
	 * Create the main content panel and add it to the window.
	 */
	private void addContentPanel() {
		contentPanel = new JPanel();
		contentPanel.setBounds(235, 86, 632, 513);
		contentPanelCardLayout = new CardLayout(0, 0);
		contentPanel.setLayout(contentPanelCardLayout);
    
		addStatusPanel();
		addApplyPanel();
		
		contentPanelCardLayout.show(contentPanel, STATUS_PANEL_STRING);
		add(contentPanel);
	}
	
	/**
	 * Create the status panel used by the main content panel.
	 * Remains bare until populated by refreshStatusPanel.
	 */
	private void addStatusPanel() {	
		statusPanel = new JPanel(null);
		contentPanel.add(statusPanel, STATUS_PANEL_STRING);
	}
	
	/**
	 * Create the apply panel used by the main content panel.
	 * Remains bare until populated by refreshApplyPanel.
	 */
	private void addApplyPanel() {
		JPanel applyPanel = new JPanel();
		contentPanel.add(applyPanel, APPLY_PANEL_STRING);
	}
	
	/**
	 * Refreshes the status panel by removing and rebuilding all its
	 * components. This ensures any changes in variable components
	 * are displayed correctly.
	 */
	private void refreshStatusPanel() {
		
		statusPanel.removeAll();
		
		JButton btnRefresh = new JButton("Refresh Times");
		btnRefresh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnRefresh.setBounds(241, 474, 149, 30);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshStatusPanel();
			}
		});
		statusPanel.add(btnRefresh);
		
		JPanel heroStatusPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		heroStatusPanel.setBounds(0, 0, 632, 460);		
		statusPanel.add(heroStatusPanel);
		
		for (Hero hero : team().getHeroes()) {
			
			//TODO remove this once Apply panel is created
//			HealingItem someItem = new AlicornDust();
//			hero.setAppliedHealingItem(someItem);
			//
			
			HealingItem healingItem = hero.getAppliedHealingItem();
			
			JPanel heroPanel = new JPanel();
			heroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			heroStatusPanel.add(heroPanel);
			heroPanel.setLayout(null);
			
			JLabel lblImage = new JLabel("");
			lblImage.setBounds(28, 11, 150, 150);
			lblImage.setIcon(new ImageIcon(HospitalPanel.class.getResource(Image.heroPortraitFilepath(hero, 150))));
			lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			heroPanel.add(lblImage);
			
			JLabel lblName = new JLabel(String.format("%s the %s", hero.getName(), hero.getType()));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblName.setBounds(10, 161, 190, 34);
			heroPanel.add(lblName);
			
			JLabel lblHealth = new JLabel("Health:");
			lblHealth.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblHealth.setBounds(10, 209, 54, 22);
			heroPanel.add(lblHealth);
			
			JLabel lblHealthValue = new JLabel(String.format("%d/%d",
												hero.getCurrentHealth(),
												hero.getMaxHealth()));
			lblHealthValue.setBounds(65, 208, 104, 25);
			heroPanel.add(lblHealthValue);
			lblHealthValue.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHealthValue.setHorizontalAlignment(SwingConstants.LEFT);
			
			JLabel lblAppliedItem = new JLabel("Applied healing item:");
			lblAppliedItem.setHorizontalAlignment(SwingConstants.LEFT);
			lblAppliedItem.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblAppliedItem.setBounds(10, 250, 168, 22);
			heroPanel.add(lblAppliedItem);
			
			if (healingItem != null) {
				int incrementsRemaining = healingItem.getIncrementsRemaining();
				
				JLabel lblHealingItemIcon = new JLabel("");
				lblHealingItemIcon.setBounds(26, 277, 38, 38);
				heroPanel.add(lblHealingItemIcon);
				lblHealingItemIcon.setIcon(new ImageIcon(HospitalPanel.class.getResource(
						Image.healingItemImageFilepath(healingItem, 38))));
				
				JLabel lblAppliedItemName = new JLabel(healingItem.toString());
				lblAppliedItemName.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblAppliedItemName.setBounds(74, 277, 126, 38);
				heroPanel.add(lblAppliedItemName);
				
				JLabel lblHealingItemStatus = new JLabel("Healing item status:");
				lblHealingItemStatus.setHorizontalAlignment(SwingConstants.LEFT);
				lblHealingItemStatus.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblHealingItemStatus.setBounds(10, 326, 168, 22);
				heroPanel.add(lblHealingItemStatus);
				
				JTextPane txtpnOfHealth = new JTextPane();
				txtpnOfHealth.setBackground(UIManager.getColor("Panel.background"));
				txtpnOfHealth.setText(String.format("Remaining health to restore:\r\n    %s%%\r\n\r\n"
						+ "Time until next 25%% increment in health:\r\n    %s seconds.\r\n",
						(int) (incrementsRemaining * HealingItem.INCREMENT_SIZE * 100),
						healingItem.secondsRemaining()));
				txtpnOfHealth.setBounds(20, 350, 175, 102);
				heroPanel.add(txtpnOfHealth);
			}
			else {
				JLabel lblNoItem = new JLabel("No applied Healing Item");
				lblNoItem.setFont(new Font("Tahoma", Font.PLAIN, 13));
				lblNoItem.setBounds(20, 277, 158, 38);
				heroPanel.add(lblNoItem);
			}
		}
	}
	
	/**
	 * Refreshes the apply panel by removing and reconstructing
	 * all its components. This ensures any changes in variable
	 * components are displayed correctly.
	 */
	private void refreshApplyPanel() {
		//TODO
	}
}
