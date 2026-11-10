import javax.swing.JFrame;

public class Game {

	static JFrame window;
	static GamePanel gamePanel;
	static menuWindow menu;
	static settingsWindow settings;

	public static void main(String[] args) {
		window = new JFrame("Bomber friends"); // game window
		gamePanel = new GamePanel();
		menu = new menuWindow();
		settings = new settingsWindow();
		settings.setVisible(false);
		window.setSize(895, 620);
		window.add(gamePanel);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(false);

	}

}
