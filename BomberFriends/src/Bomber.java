import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomber extends Enemy {

	static Image up = new ImageIcon("assets/enemy/bomber/up.gif").getImage();
	Image down = new ImageIcon("assets/enemy/bomber/down.gif").getImage();
	Image left = new ImageIcon("assets/enemy/bomber/left.gif").getImage();
	Image right = new ImageIcon("assets/enemy/bomber/right.gif").getImage();
	Image dying = new ImageIcon("assets/enemy/bomber/dying.gif").getImage();
	private int bombPower;
	private int bombRad;
	private int bombCoolDown;
	static int count = 1;

	Bomber() {
		super(up, 4, 1);
		this.setBombPower(4);
		this.setBombCoolDown(5);
		this.setBombRad(5);
	}

	public void plantBomb(int i, int j) {
		Bomb bomb = new Bomb(i, j, bombPower, bombRad, bombCoolDown);
		bomb.isPlacedByBomber(true);
	}

	public boolean isBomber() {
		return true;
	}

	public int getBombPower() {
		return bombPower;
	}

	public void setBombPower(int bombPower) {
		this.bombPower = bombPower;
	}

	public int getBombRad() {
		return bombRad;
	}

	public void setBombRad(int bombRad) {
		this.bombRad = bombRad;
	}

	public int getBombCoolDown() {
		return bombCoolDown;
	}

	public void setBombCoolDown(int bombCoolDown) {
		this.bombCoolDown = bombCoolDown;
	}

	public void draw(Graphics g) {
		switch (state) {
		case UP: {
			g.drawImage(up, x, y, WIDTH, HEIGHT, null);
			break;
		}
		case DOWN: {
			g.drawImage(down, x, y, WIDTH, HEIGHT, null);
			break;
		}
		case LEFT: {
			g.drawImage(left, x, y, WIDTH, HEIGHT, null);
			break;
		}
		case RIGHT: {
			g.drawImage(right, x, y, WIDTH, HEIGHT, null);
			break;
		}
		}
	}

}
