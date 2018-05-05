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

public class HospitalPanel extends JPanel implements Refreshable {

	public static final String HOSPITAL_PANEL_STRING = "Hospital Panel";
	
	private static final String STATUS_PANEL_STRING = "Status Panel";
	
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
	
	private Game gameWindow;
	
	public HospitalPanel(Game game) {
		super();
		this.gameWindow = game;
		
		setPreferredSize(new Dimension(880, 610));
		setLayout(null);
		
		addTitlePanel();
		addSidePanel();
		addContentPanel();
		}

	private Team team() {
		return gameWindow.getGame().getTeam();
	}
	
	public void refresh() {
		// TODO Auto-generated method stub
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
	
	private void addSidePanel() {
		JPanel sidePanel = new JPanel();
		sidePanel.setBounds(10, 86, 215, 513);
		sidePanel.setLayout(null);
		
		JButton btnBack = new JButton("Exit Hospital");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameWindow.setPanel(HomeBasePanel.HOME_BASE_PANEL_STRING);
			}
		});
		btnBack.setBounds(37, 462, 140, 30);	
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
		sidePanel.add(btnBack);
		
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
	
	private void addStatusPanel() {	
		statusPanel = new JPanel(null);
		contentPanel.add(statusPanel, STATUS_PANEL_STRING);
		
		/*JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(527, 479, 95, 23);
		statusPanel.add(btnRefresh);*/
	}
	
	private void addApplyPanel() {
		JPanel applyPanel = new JPanel();
		contentPanel.add(applyPanel, APPLY_PANEL_STRING);
	}
	
	private void refreshStatusPanel() {
		
		statusPanel.removeAll();
		
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.setBounds(537, 479, 95, 23);
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshStatusPanel();
			}
		});
		statusPanel.add(btnRefresh);
		
		JPanel heroStatusPanel = new JPanel(new GridLayout(0, 3, 0, 0));
		heroStatusPanel.setBounds(0, 0, 632, 463);		
		statusPanel.add(heroStatusPanel);
		
		for (Hero hero : team().getHeroes()) {
			
			//TODO remove this once Apply panel is created
			hero.setAppliedHealingItem(new AlicornDust());
			
			HealingItem healingItem = hero.getAppliedHealingItem();
			
			JPanel heroPanel = new JPanel();
			heroPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			heroStatusPanel.add(heroPanel);
			heroPanel.setLayout(null);
			
			JLabel lblImage = new JLabel("");
			lblImage.setBounds(28, 11, 150, 150);
			// TODO lblImage.setIcon(new ImageIcon(HospitalPanel.class.getResource(filepath)));
			lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			heroPanel.add(lblImage);
			
			JLabel lblName = new JLabel(String.format("%s the %s", hero.getName(), hero.getType()));
			lblName.setHorizontalAlignment(SwingConstants.CENTER);
			lblName.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblName.setBounds(28, 164, 150, 22);
			heroPanel.add(lblName);
			
			JLabel lblHealth = new JLabel(String.format("Health: %d/%d", hero.getCurrentHealth(), hero.getMaxHealth()));
			lblHealth.setHorizontalAlignment(SwingConstants.CENTER);
			lblHealth.setBounds(28, 197, 150, 22);
			heroPanel.add(lblHealth);
			
			JLabel lblAppliedItem = new JLabel("Applied Item:");
			lblAppliedItem.setHorizontalAlignment(SwingConstants.CENTER);
			lblAppliedItem.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblAppliedItem.setBounds(28, 230, 150, 22);
			heroPanel.add(lblAppliedItem);
			
			JPanel appliedItemPanel = new JPanel();
			appliedItemPanel.setBounds(28, 255, 150, 175);
			heroPanel.add(appliedItemPanel);
			appliedItemPanel.setLayout(null);
			
			if (healingItem instanceof HealingItem) {
				int incrementsRemaining = healingItem.getIncrementsRemaining();
				int numIncrements = healingItem.getNumIncrements();
				
				JLabel lblItemIcon = new JLabel("");
				lblItemIcon.setBounds(10, 10, 38, 38);
				lblItemIcon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				appliedItemPanel.add(lblItemIcon);
				
				JLabel lblAppliedItemName = new JLabel(hero.getAppliedHealingItem().getName());
				lblAppliedItemName.setHorizontalAlignment(SwingConstants.RIGHT);
				lblAppliedItemName.setBounds(60, 22, 80, 15);
				appliedItemPanel.add(lblAppliedItemName);
				
				JLabel lblTimeRemaining = new JLabel("Time Until Heal:");
				lblTimeRemaining.setHorizontalAlignment(SwingConstants.CENTER);
				lblTimeRemaining.setBounds(10, 69, 130, 21);
				appliedItemPanel.add(lblTimeRemaining);
				
				JLabel lblSeconds = new JLabel(String.format("%d seconds", hero.getAppliedHealingItem().secondsRemaining()));
				lblSeconds.setHorizontalAlignment(SwingConstants.CENTER);
				lblSeconds.setBounds(10, 104, 130, 15);
				appliedItemPanel.add(lblSeconds);
				
				JLabel lblApplied = new JLabel(String.format("%d%% of %d%% applied",
															 (int)(HealingItem.INCREMENT_SIZE*100)*(numIncrements - incrementsRemaining),
															 (int)(HealingItem.INCREMENT_SIZE*100)*numIncrements));				
				lblApplied.setHorizontalAlignment(SwingConstants.CENTER);
				lblApplied.setBounds(10, 132, 130, 15);
				appliedItemPanel.add(lblApplied);
			}
			else {
				JLabel lblNoItem = new JLabel("No applied Healing Item");
				lblNoItem.setHorizontalAlignment(SwingConstants.CENTER);
				lblNoItem.setBounds(0, 5, 150, 21);
				appliedItemPanel.add(lblNoItem);
			}
		}
	}
	
	private void refreshApplyPanel() {
		//TODO
	}
}
