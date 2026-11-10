import java.awt.Image;

import javax.swing.ImageIcon;

public class NormalWall extends Tile {

	static Image normalWall = new ImageIcon("assets/wall/normalWall.png").getImage();

	NormalWall(int i, int j) {
		super(normalWall, i, j);
		this.isEmpty(false);
	}

	public void destroy() {

	}

	public boolean isBreakable() {
		this.isExploded(true);
		return true;
	}

}
