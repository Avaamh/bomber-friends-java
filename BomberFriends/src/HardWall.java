
import java.awt.Image;

import javax.swing.ImageIcon;

public class HardWall extends Tile {

	static Image hardWall = new ImageIcon("assets/wall/hardWall.png").getImage();

	HardWall(int i, int j) {
		super(hardWall, i, j);
		this.isEmpty(false);
	}

	public boolean isBreakable() {
		return false;
	}

}
