import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class menuWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JButton startBtn;
	JButton settingsBtn;
	JButton exitBtn;

	menuWindow() {

		setSize(895, 600);
		this.setLayout(null);
		setBackground(Color.DARK_GRAY);
		startBtn = new JButton("start game");
		startBtn.setBackground(Color.LIGHT_GRAY);
		startBtn.setBounds(350, 100, 200, 70);
		add(startBtn);
		startBtn.addActionListener(this);
		settingsBtn = new JButton("settings");
		settingsBtn.setBackground(Color.LIGHT_GRAY);
		settingsBtn.setBounds(350, 220, 200, 70);
		add(settingsBtn);
		settingsBtn.addActionListener(this);
		exitBtn = new JButton("exit");
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.setBounds(350, 340, 200, 70);
		add(exitBtn);
		exitBtn.addActionListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(Game.window);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == startBtn) {
			this.setVisible(false);
		//	new World();
			Game.window.setVisible(true);
		} else if (e.getSource() == settingsBtn) {
			this.setVisible(false);
			Game.settings.setVisible(true);
		} else if (e.getSource() == exitBtn) {
			System.exit(0);
		}
	}

}
