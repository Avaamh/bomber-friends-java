import java.awt.Image;
import javax.swing.ImageIcon;

public class AmazingWall extends Tile {

	int explosionCount, count;
	static Image amazingWall = new ImageIcon("assets/wall/amazingWall.png").getImage();
	Image heart = new ImageIcon("assets/wall/heart.png").getImage();
	Image power = new ImageIcon("assets/wall/p.png").getImage();
	Image rad = new ImageIcon("assets/wall/r.png").getImage();
	private boolean boxShowed;

	AmazingWall(int i, int j) {
		super(amazingWall, i, j);
		this.isEmpty(false);
		explosionCount = 0;
		count = 3;
		boxShowed = false;
	}

	public boolean isBreakable() { // amazing wall will become breakable after third damage
		explosionCount++;
		if (explosionCount >= count) {
			this.isExploded(true);
			return true;
		}
		return false;
	}

	public void showBox() { // shows a random box after breaking
		int rand = (int) (Math.random() * 3);
		switch (rand) {
		case 0: { // heart for players health
			this.setTexture(heart);
			this.isEmpty(true);
			break;
		}
		case 1: { // p for players bomb power
			this.setTexture(power);
			this.isEmpty(true);
			break;
		}
		case 2: { // R for players bomb radius
			this.setTexture(rad);
			this.isEmpty(true);
		}
		}
		this.boxShowed = true;
	}

	public void getRewards(Player player) {
		if (this.getTexture() == heart)
			player.setHealth(player.getHealth() + 1);
		else if (this.getTexture() == power)
			player.setBombPower(player.getBombPower() + 1);
		else if (this.getTexture() == rad)
			player.setBombRad(player.getBombRad() + 1);
		new Tile(null, this.getRow(), this.getCol());
	}

	public boolean hasBox() {
		return true;
	}

	public boolean BoxShowed() {
		return boxShowed;
	}

}
