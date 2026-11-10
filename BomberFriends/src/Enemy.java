import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy extends GameObject {

	    Image type; 
	    private int health;
	    private int speed;
	    Direction state = Direction.LEFT;
	    
	    Enemy(Image type, int speed, int health) {
	    	this.WIDTH = Tile.WIDTH;
	    	this.HEIGHT = Tile.HEIGHT;
	    	this.type = type;
	        this.speed = speed;
	        this.setHealth(health);
	        this.isEnemy = true;
	    }
	    
	    public Direction randomDirection() {   // returns a random direction from directions enum
	        int rand = (int) (Math.random()*3);
			return Direction.values()[rand];
	    	
	    }

	    public void move(Direction direction) {
	        switch(direction) {
	            case UP:
	            {
	                y -= speed;
	                state = Direction.UP;
	                break;
	            }
	            case DOWN:{
	                y += speed;
	                state = Direction.DOWN;
	                break;
	            }
	            case LEFT: {
	                x -= speed;
	                state = Direction.LEFT;
	                break;
	            }
	            case RIGHT:
	            {
	                x += speed;
	                state = Direction.RIGHT;
	                break;
	            }
	        }
	    }
	    
	    public void plantBomb(int i, int j) {
	    }
	    
	    public Rectangle getBounds() {
			return new Rectangle(this.x,this.y,this.WIDTH,this.HEIGHT);
		}

	    public void draw(Graphics g) {
	    	if (this.isBomber())
	    		this.draw(g);
	    	else
	    	g.drawImage(type,x,y,WIDTH,HEIGHT,null);
	    }

		public void harm(int power) {
			this.health = health-power;
			if (this.health<=0)
				World.removeEnemy(this);
		}

		public void setHealth(int health) {
			this.health = health;
		}
		
		public int getHealth() {
			return this.health;
		}
		
		public boolean isBomber() {
			return false;
		}
}
