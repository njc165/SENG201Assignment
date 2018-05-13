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

public class VictoryPanel extends JPanel {
	
	public static final String VICTORY_PANEL_STRING = "Victory Panel";
	
	private Game gameWindow;
	
	public VictoryPanel(Game game) {
		super();
		this.gameWindow = game;
		
		setLayout(null);
		setPreferredSize(new Dimension(880, 610));
		
		JLabel lblBanner = new JLabel("VICTORY");
		lblBanner.setFont(MyFont.getHeadingFont(50));
		lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
		lblBanner.setBounds(10, 290, 860, 65);
		add(lblBanner);
		
		JLabel lblFireworks = new JLabel("");
		lblFireworks.setIcon(new ImageIcon(VictoryPanel.class.getResource(Image.FIREWORKS_IMAGE_FILEPATH)));
		lblFireworks.setBounds(243, 23, 383, 268);
		add(lblFireworks);
		
		JLabel lblYouWon = new JLabel("You defeated all the villains and liberated the world!");
		lblYouWon.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblYouWon.setHorizontalAlignment(SwingConstants.CENTER);
		lblYouWon.setBounds(203, 366, 480, 65);
		add(lblYouWon);
		
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
		btnEndGame.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnEndGame.setBounds(359, 522, 178, 50);
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(btnEndGame);
		
	}
}
