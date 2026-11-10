import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

	public final static int ROWS = 14;
	public final static int COLS = 22;
	public static Tile tiles[][] = new Tile[ROWS][COLS];

	public final static int SIZE = 40;
	final static int WIDTH = 40;
	final static int HEIGHT = 40;
	private Image texture;
	private final int row;
	private final int col;
	private boolean isEmpty = true;
	private boolean isExploded = false;

	Tile(Image texture, int i, int j) { // inputs tile texture
		this.texture = texture;
		this.row = i;
		this.col = j;
		tiles[i][j] = this;
	}

	public boolean isBreakable() {
		return true;
	}

	public boolean hasBox() {
		return false;
	}

	public void draw(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, WIDTH, HEIGHT, null);
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void isEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public void setTexture(Image texture) {
		this.texture = texture;
	}

	public Image getTexture() {
		return this.texture;
	}

	public Rectangle getBounds() {
		return new Rectangle(this.col * this.WIDTH, this.row * this.HEIGHT, this.WIDTH, this.HEIGHT);
	}

	public boolean isExploded() {
		return isExploded;
	}

	public void isExploded(boolean isExploded) {
		this.isExploded = isExploded;
	}

	public void showBox() {

	}

	public void getRewards(Player player) {
		// TODO Auto-generated method stub

	}

	public boolean boxShowed() {
		// TODO Auto-generated method stub
		return false;
	}

}
