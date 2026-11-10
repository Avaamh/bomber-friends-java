import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class settingsWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	JLabel soulLbl;
	JLabel skeltonLbl;
	JLabel bomberLbl;
	JLabel bombRadLbl;
	JLabel bombPowerLbl;
	JTextField soulCount;
	JTextField skeltonCount;
	JTextField bomberCount;
	JTextField bombRadius;
	JTextField bombPower;
	JButton setBtn;
	JButton backBtn;

	settingsWindow() {

		soulLbl = new JLabel("souls count : ");
		skeltonLbl = new JLabel("skeltons count : ");
		bomberLbl = new JLabel("bombers count : ");
		bombRadLbl = new JLabel("player's bomb radius : ");
		bombPowerLbl = new JLabel("player's bomb power : ");
		soulLbl.setBounds(50, 100, 100, 30);
		skeltonLbl.setBounds(50, 150, 100, 30);
		bomberLbl.setBounds(50, 200, 100, 30);
		bombRadLbl.setBounds(50, 250, 150, 30);
		bombPowerLbl.setBounds(50, 300, 150, 30);
		add(soulLbl);
		add(skeltonLbl);
		add(bomberLbl);
		add(bombRadLbl);
		add(bombPowerLbl);
		// default enemies count and player's bomb settings can be changed here
		soulCount = new JTextField(Soul.count);
		skeltonCount = new JTextField(Skelton.count);
		bomberCount = new JTextField(Bomber.count);
		bombRadius = new JTextField(Player.bombRad);
		bombPower = new JTextField(Player.bombPower);
		soulCount.setText(String.valueOf(Soul.count));
		skeltonCount.setText(String.valueOf(Skelton.count));
		bomberCount.setText(String.valueOf(Bomber.count));
		bombRadius.setText(String.valueOf(Player.bombRad));
		bombPower.setText(String.valueOf(Player.bombPower));
		soulCount.setBounds(250, 100, 50, 30);
		skeltonCount.setBounds(250, 150, 50, 30);
		bomberCount.setBounds(250, 200, 50, 30);
		bombRadius.setBounds(250, 250, 50, 30);
		bombPower.setBounds(250, 300, 50, 30);
		add(soulCount);
		add(skeltonCount);
		add(bomberCount);
		add(bombRadius);
		add(bombPower);
		setBtn = new JButton("set");
		setBtn.setBounds(230, 400, 70, 30);
		setBtn.addActionListener(this);
		add(setBtn);
		backBtn = new JButton("back");
		backBtn.setBounds(150, 400, 70, 30);
		backBtn.addActionListener(this);
		add(backBtn);
		setSize(895, 600);
		this.setLayout(null);
		this.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(Game.window);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == setBtn) {
			Soul.count = Integer.parseInt(soulCount.getText());
			Skelton.count = Integer.parseInt(skeltonCount.getText());
			Bomber.count = Integer.parseInt(bomberCount.getText());
			Player.bombRad = Integer.parseInt(bombRadius.getText());
			Player.bombPower = Integer.parseInt(bombPower.getText());
			JOptionPane.showMessageDialog(null, "settings changed.");
		} else if (e.getSource() == backBtn) {
			this.setVisible(false);
			Game.menu.setVisible(true);
		}
	}

}
