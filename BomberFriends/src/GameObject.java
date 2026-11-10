import java.awt.Graphics;
import java.awt.Rectangle;

public class GameObject {

	int x;
	int y;
	int WIDTH = 35;
	int HEIGHT = 35;
	boolean isEnemy = false;

	public void draw(Graphics g) {

	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	public int findRow() { // finds the tile number using x and y
		for (int i = 0; i < World.yPos.length; i++) {
			if (y + HEIGHT / 2 < World.yPos[i])
				return i - 1;
		}
		return World.yPos.length;
	}

	public int findCol() {
		for (int i = 0; i < World.xPos.length; i++) {
			if (x + WIDTH / 2 < World.xPos[i])
				return i - 1;
		}
		return World.xPos.length;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.WIDTH, this.HEIGHT);
	}

	public boolean isEnemy() {
		return isEnemy;
	}

}
