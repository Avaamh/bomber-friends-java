import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Explosion {

	Image core = new ImageIcon("assets/explosion/core.png").getImage();
	Image up = new ImageIcon("assets/explosion/up.png").getImage();
	Image down = new ImageIcon("assets/explosion/down.png").getImage();
	Image left = new ImageIcon("assets/explosion/left.png").getImage();
	Image right = new ImageIcon("assets/explosion/right.png").getImage();
	Tile coreTile, upTile, downTile, leftTile, rightTile;
	Bomb bomb;
	int rad, power;
	boolean exploded;

	public Explosion(Bomb bomb) {
		this.bomb = bomb;
		this.rad = bomb.getRad();
		this.power = bomb.getPower();
		coreTile = Tile.tiles[bomb.getRow()][bomb.getCol()];
		exploded = false;
		explode();
		destroyWalls();
	}

	private void explode() { // sets breakable tiles exploding
		coreTile.setTexture(core);
		for (int i = 1; i <= rad; i++) {
			if (coreTile.getRow() - i >= 0)
				upTile = Tile.tiles[coreTile.getRow() - i][coreTile.getCol()];
			if (coreTile.getRow() + i < Tile.ROWS)
				downTile = Tile.tiles[coreTile.getRow() + i][coreTile.getCol()];
			if (coreTile.getCol() - i >= 0)
				leftTile = Tile.tiles[coreTile.getRow()][coreTile.getCol() - i];
			if (coreTile.getCol() + i < Tile.COLS)
				rightTile = Tile.tiles[coreTile.getRow()][coreTile.getCol() + i];
			if (upTile.isBreakable())
				upTile.setTexture(up);
			if (downTile.isBreakable())
				downTile.setTexture(down);
			if (leftTile.isBreakable())
				leftTile.setTexture(left);
			if (rightTile.isBreakable())
				rightTile.setTexture(right);
			Tile exploding[] = { coreTile, upTile, downTile, leftTile, rightTile };
			checkCollision(exploding);

		}

	}

	private void checkCollision(Tile[] exploding) { // checks if player or enemies are in the exploding area
		Rectangle plyr = World.player.getBounds();
		for (Tile tile : exploding) {
			if (tile.getBounds().intersects(plyr)) {
				if (!exploded)
					World.player.harm(this.power);
				exploded = true;
				break;
			}
			for (Enemy enemy : World.enemies) {
				Rectangle enemyBounds = enemy.getBounds();
				if (tile.getBounds().intersects(enemyBounds)) {
					if (!this.bomb.isPlacedByBomber())
						enemy.harm(this.power);
					break;
				}
			}
		}

	}

	private void destroyWalls() { // removes removable walls
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (coreTile.isEmpty())
			coreTile = new Tile(null, coreTile.getRow(), coreTile.getCol());
		for (int i = 1; i <= rad; i++) {
			if (coreTile.getRow() - i >= 0)
				upTile = Tile.tiles[coreTile.getRow() - i][coreTile.getCol()];
			if (coreTile.getRow() + i < Tile.ROWS)
				downTile = Tile.tiles[coreTile.getRow() + i][coreTile.getCol()];
			if (coreTile.getCol() - i >= 0)
				leftTile = Tile.tiles[coreTile.getRow()][coreTile.getCol() - i];
			if (coreTile.getCol() + i < Tile.COLS)
				rightTile = Tile.tiles[coreTile.getRow()][coreTile.getCol() + i];
			Tile exploding[] = { upTile, downTile, leftTile, rightTile };
			for (Tile tile : exploding) {
				if (tile.isExploded() || tile.isEmpty()) { // shows the boxes if it is an amazing wall
					if (tile.hasBox()) {
						if (!tile.boxShowed())
							tile.showBox();
					} else
						tile = new Tile(null, tile.getRow(), tile.getCol());
				}
			}
		}
	}
}
