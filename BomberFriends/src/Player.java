import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Player extends GameObject {

	Image up = new ImageIcon("assets/player/up.gif").getImage();
	Image down = new ImageIcon("assets/player/down.gif").getImage();
	Image left = new ImageIcon("assets/player/left.gif").getImage();
	Image right = new ImageIcon("assets/player/right.gif").getImage();
	Image idle = new ImageIcon("assets/player/idle.png").getImage();
	Image dying = new ImageIcon("assets/player/dying.png").getImage();

	private int health;
	static int bombPower = 1;
	static int bombRad = 1;
	private int bombCoolDown;
	int speed = 5;
	Direction state = Direction.IDLE;

	Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.setHealth(3);
		this.setBombCoolDown(2);
	}

	public void move(Direction direction) {
		switch (direction) {
		case UP: {
			y -= speed;
			state = Direction.UP;
			break;
		}
		case DOWN: {
			y += speed;
			state = Direction.DOWN;
			break;
		}
		case LEFT: {
			x -= speed;
			state = Direction.LEFT;
			break;
		}
		case RIGHT: {
			x += speed;
			state = Direction.RIGHT;
			break;
		}
		}
		World.checkEnemyCollision();
	}

	public void plantBomb(int i, int j) { // plants bomb where player is
		new Bomb(i, j, bombPower, bombRad, bombCoolDown);
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
		case IDLE: {
			g.drawImage(idle, x, y, WIDTH, HEIGHT, null);
			break;
		}
		case DYING: {
			g.drawImage(dying, x, y, WIDTH, HEIGHT, null);
		}
		}
	}

	public void setIdle() {
		state = Direction.IDLE;
	}

	public void harm(int power) {
		System.out.println(this.health);
		this.health = health - power;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getHealth() {
		return this.health;
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

	public void setBombCoolDown(int time) {
		this.bombCoolDown = time;
	}
}
