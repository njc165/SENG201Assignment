import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DefeatPanel extends JPanel {
	
	public static final String DEFEAT_PANEL_STRING = "Defeat Panel";
	
	private Game gameWindow;
	
	public DefeatPanel(Game game) {
		super();
		this.gameWindow = game;
		
		setLayout(null);
		setPreferredSize(new Dimension(880, 610));
		
		JLabel lblBanner = new JLabel("DEFEAT");
		lblBanner.setFont(new Font("Ringbearer", Font.PLAIN, 50));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setBounds(10, 290, 860, 65);
		add(lblBanner);
		
		JLabel lblGravestone = new JLabel("");
		lblGravestone.setIcon(new ImageIcon(VictoryPanel.class.getResource(Image.GRAVESTONE_IMAGE_FILEPATH)));
		lblGravestone.setBounds(342, 22, 207, 268);
		add(lblGravestone);
		
		JLabel lblYouLost = new JLabel("Your team was defeated and villains took over the world.");
		lblYouLost.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYouLost.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouLost.setBounds(169, 366, 560, 65);
		add(lblYouLost);
		
		long timeTaken = gameWindow.getGame().getStartTime().until(LocalTime.now(), ChronoUnit.SECONDS);
		long minutesTaken = timeTaken / 60;
		long secondsTaken = timeTaken - 60 * minutesTaken;
		
		JLabel lblTimeTaken = new JLabel();
		lblTimeTaken.setText(String.format("You played for %d minutes and %d seconds.", minutesTaken, secondsTaken));
		lblTimeTaken.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimeTaken.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTimeTaken.setBounds(213, 442, 470, 57);
		add(lblTimeTaken);
		
		JButton btnEndGame = new JButton("End Game");
		btnEndGame.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEndGame.setBounds(359, 522, 178, 50);
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnEndGame);
		
	}
}
