import java.awt.Image;

import javax.swing.ImageIcon;

public class Skelton extends Enemy {

	static Image skelton = new ImageIcon("assets/enemy/skelton.png").getImage();
	static int count = 2;

	Skelton() {
		super(skelton, 2, 3);
		this.WIDTH = 35;
		this.HEIGHT = 35;
	}

}
