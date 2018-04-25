import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeBasePanel extends JPanel {
	
	public static final String HOME_BASE_PANEL_STRING = "Other Panel";
	
	private Game game;
	
	public HomeBasePanel(Game game) {
		super();
		this.game = game;
		
		setLayout(null);
		setPreferredSize(new Dimension(610, 880));
		
		addHomeBaseLabel();
		addShopButton();
	}
	
	private void addShopButton() {
		JButton btnGoToShop = new JButton("Go to Shop");
		btnGoToShop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				game.setPanel(ShopPanel.SHOP_PANEL_STRING);
			}
		});
		btnGoToShop.setBounds(109, 124, 231, 51);
		add(btnGoToShop);
	}
	
	private void addHomeBaseLabel() {
		JLabel lblHomeBase = new JLabel("HOME BASE");
		lblHomeBase.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblHomeBase.setHorizontalAlignment(SwingConstants.CENTER);
		lblHomeBase.setBounds(10, 11, 430, 51);
		add(lblHomeBase);
	}
}
