import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class World {

	static Player player;
	static ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	static int xPos[] = { 0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480, 520, 560, 600, 640, 680, 720,
			760, 800, 840, 880 };
	static int yPos[] = { 0, 40, 80, 120, 160, 200, 240, 280, 320, 360, 400, 440, 480, 520, 560 }; // tiles x and y

	World() {

		player = new Player(803, 45);
		setHardWalls();
		setNormalWalls();
		setAmazingWalls();
		setSouls(Soul.count);
		setSkelton(Skelton.count);
		setBomber(Bomber.count);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() { // moves enemies in random direction
			@Override // checks if game is over
			public void run() {
				moveEnemies();
				if (!running()) {
					showResult();
					Game.window.setVisible(false);
					Game.menu.setVisible(true);
					timer.cancel();
				}
			}
		}, 0, 100);

		timer.schedule(new TimerTask() {

			@Override
			public void run() { // bomber enemies plant bombs every 10 seconds
				for (Enemy enemy : enemies) {
					if (enemy.isBomber())
						enemy.plantBomb(enemy.findRow(), enemy.findCol());
				}
			}

		}, 0, 10000);
	}

	private void showResult() { // shows the result when game is over	
		if (player.getHealth() <= 0)
			JOptionPane.showMessageDialog(null, "YOU LOST.");
		else if (enemies.isEmpty())
			JOptionPane.showMessageDialog(null, "YOU WON!");
        enemies.removeAll(enemies);
	}

	private void setBomber(int count) { // sets bombers in random locations
		for (int i = 0; i < count; i++) {
			int randX = 0;
			int randY = 0;
			Enemy enemy = new Bomber();
			while (!Tile.tiles[randY][randX].isEmpty()) {
				randX = (int) (Math.random() * Tile.COLS / 2);
				randY = (int) (Math.random() * Tile.ROWS);
			}
			enemy.setX(randX * Tile.WIDTH);
			enemy.setY(randY * Tile.HEIGHT);
			enemies.add(enemy);
		}

	}

	private void setSkelton(int count) { // sets skeletons in random locations

		for (int i = 0; i < count; i++) {
			int randX = 0;
			int randY = 0;
			Enemy enemy = new Skelton();
			while (!Tile.tiles[randY][randX].isEmpty()) {
				randX = (int) (Math.random() * Tile.COLS / 2);
				randY = (int) (Math.random() * Tile.ROWS);
			}
			enemy.setX(randX * Tile.WIDTH);
			enemy.setY(randY * Tile.HEIGHT);
			enemies.add(enemy);
		}

	}

	private void moveEnemies() { // moves all enemies in random directions

		for (Enemy enemy : enemies) {
			Direction direction = enemy.state;
			while (wallCollision(enemy, direction)) {
				direction = enemy.randomDirection();
			}
			enemy.move(direction);

		}

	}

	static void checkEnemyCollision() { // checks the collision of enemies and the player
		for (int i = enemies.size() - 1; i > 0; i--) {
			Rectangle enemyBounds = enemies.get(i).getBounds();
			Rectangle playerBounds = player.getBounds();
			if (enemyBounds.intersects(playerBounds)) {
				player.harm(1);
				enemies.get(i).harm(enemies.get(i).getHealth());
			}
		}

	}

	private void setSouls(int count) { // sets souls in random locations

		for (int i = 0; i < count; i++) {
			int randX = 0;
			int randY = 0;
			Enemy enemy = new Soul();
			while (!Tile.tiles[randY][randX].isEmpty()) {
				randX = (int) (Math.random() * Tile.COLS / 2);
				randY = (int) (Math.random() * Tile.ROWS);
			}
			enemy.setX(randX * enemy.WIDTH);
			enemy.setY(randY * enemy.HEIGHT);
			enemies.add(enemy);
		}

	}

	private boolean running() {
		if (player.getHealth() <= 0 || World.enemies.isEmpty()) {
			player.state = Direction.DYING;
			return false;
		}

		return true;
	}

	private void setNormalWalls() { // sets 50 stone walls in a random map tile
		int randX = 0;
		int randY = 0;
		for (int count = 0; count < 50; count++) {
			while (!Tile.tiles[randY][randX].isEmpty()) {
				randX = (int) (Math.random() * Tile.COLS);
				randY = (int) (Math.random() * Tile.ROWS);
			}
			Tile.tiles[randY][randX] = new NormalWall(randY, randX);
		}

	}

	private void setAmazingWalls() {
		int randX = 0;
		int randY = 0;
		for (int count = 0; count < 20; count++) {
			while (!Tile.tiles[randY][randX].isEmpty()) {
				randX = (int) (Math.random() * Tile.COLS);
				randY = (int) (Math.random() * Tile.ROWS);
			}
			Tile.tiles[randY][randX] = new AmazingWall(randY, randX);
		}
		Tile.tiles[1][Tile.COLS - 2].isEmpty(true);

	}

	private void setHardWalls() {
		for (int i = 0; i < Tile.ROWS; i++) {
			for (int j = 0; j < Tile.COLS; j++) {
				if (i == 0 || i == 13 || j == 0 || j == 21)
					new HardWall(i, j);
				else if (i % 2 == 0 && j % 2 == 0)
					new HardWall(i, j);
				else
					new Tile(null, i, j);
			}
		}
		Tile.tiles[1][Tile.COLS - 2].isEmpty(false);

	}

	public static boolean wallCollision(GameObject object, Direction direction) { // checks wall collision of game
																					// characters
		Rectangle obj = object.getBounds();
		Rectangle wall = null;
		int row = object.findRow();
		int col = object.findCol();
		switch (direction) {
		case UP: {
			wall = Tile.tiles[row - 1][col].getBounds();
			if (Tile.tiles[row - 1][col].isEmpty()) {
				if (!object.isEnemy() && Tile.tiles[row - 1][col].hasBox())
					Tile.tiles[row - 1][col].getRewards(player);
				return false;
			}
			break;
		}
		case DOWN: {
			wall = Tile.tiles[row + 1][col].getBounds();
			if (Tile.tiles[row + 1][col].isEmpty()) {
				if (!object.isEnemy() && Tile.tiles[row + 1][col].hasBox())
					Tile.tiles[row + 1][col].getRewards(player);
				return false;
			}
			break;
		}
		case LEFT: {
			wall = Tile.tiles[row][col - 1].getBounds();
			if (Tile.tiles[row][col - 1].isEmpty()) {
				if (!object.isEnemy() && Tile.tiles[row][col - 1].hasBox())
					Tile.tiles[row][col - 1].getRewards(player);
				return false;
			}
			break;
		}
		case RIGHT: {
			wall = Tile.tiles[row][col + 1].getBounds();
			if (Tile.tiles[row][col + 1].isEmpty()) {
				if (!object.isEnemy() && Tile.tiles[row][col + 1].hasBox())
					Tile.tiles[row][col + 1].getRewards(player);
				return false;
			}
			break;
		}
		}
		if (obj.intersects(wall))
			return true;
		return false;
	}

	public static void draw(Graphics g) {
		player.draw(g);
		g.setColor(Color.white);
	//	g.setFont(Font.BOLD);
		g.drawString("Health : "+player.getHealth(), 100, 580);
		for (int i = 0; i < Tile.ROWS; i++) {
			for (int j = 0; j < Tile.COLS; j++) {
				Tile.tiles[i][j].draw(g, xPos[j], yPos[i]);
			}
		}
		for (Enemy enemy : enemies)
			enemy.draw(g);
	}

	public static void removeEnemy(Enemy enemy) { // removes enemy after damaging by player
		enemies.remove(enemy);
	}

}
