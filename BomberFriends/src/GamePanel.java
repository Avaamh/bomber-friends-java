import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

class Handler extends KeyAdapter {
	private Player player;

	Handler(Player plyr) {
		this.player = plyr;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		player.setIdle();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_W: { // handles player movement in map
			if (!World.wallCollision(World.player, Direction.UP))
				player.move(Direction.UP);
			break;
		}
		case KeyEvent.VK_S: {
			if (!World.wallCollision(World.player, Direction.DOWN))
				player.move(Direction.DOWN);
			break;
		}
		case KeyEvent.VK_A: {
			if (!World.wallCollision(World.player, Direction.LEFT))
				player.move(Direction.LEFT);
			break;
		}
		case KeyEvent.VK_D: {
			if (!World.wallCollision(World.player, Direction.RIGHT))
				player.move(Direction.RIGHT);
			break;
		}
		case KeyEvent.VK_SPACE: {
			player.plantBomb(player.findRow(), player.findCol());
			break;
		}

		}
		super.keyPressed(e);
	}
}

public class GamePanel extends JPanel {

	private static final long serialVersionUID = -721549582839512385L;

	static World world;

	GamePanel() {

		setBackground(Color.black);
		setFocusable(true);
		world = new World();
		addKeyListener(new Handler(World.player));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		World.draw(g);

		repaint();

	}

}
