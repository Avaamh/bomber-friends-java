import java.awt.Image;

import javax.swing.ImageIcon;

public class Soul extends Enemy {

	static Image soul = new ImageIcon("assets/enemy/soul.png").getImage();
	static int count = 3;

	Soul() {
		super(soul, 6, 1);
	}

}
