import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

public class Bomb extends Tile {

	static Image bomb = new ImageIcon("assets/bomb.png").getImage();
	private int power;
	private int rad;
	private long cooldown;
	Timer timer;
	int row, col;
	private boolean placedByBomber;
	TimerTask task = new TimerTask() { // explodes the bomb after its cooldown
		public void run() {
			explode();
			timer.cancel();
		}
	};

	Bomb(int row, int col, int power, int rad, int coolDown) {
		super(bomb, row, col);
		this.isPlacedByBomber(false);
		this.row = row;
		this.col = col;
		setPower(power);
		setRad(rad);
		this.setCooldown(coolDown);
		timer = new Timer("timer");
		timer.schedule(task, cooldown);
	}

	public void explode() {
		new Explosion(this);
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getRad() {
		return rad;
	}

	public void setRad(int rad) {
		this.rad = rad;
	}

	public long getCooldown() {
		return cooldown;
	}

	public void setCooldown(long cooldown) {
		this.cooldown = cooldown * 1000;
	}

	public boolean isPlacedByBomber() {
		return placedByBomber;
	}

	public void isPlacedByBomber(boolean bool) {
		this.placedByBomber = bool;
	}

}
