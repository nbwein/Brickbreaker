import java.awt.Color;
import java.awt.Graphics;

public class Block extends GameObj {

	public static final int SIZE_WIDTH = 25;
	public static final int SIZE_HEIGHT = 8;
	private Color c;
	private boolean hasPowerUp = false;

	/**
	 * Note that, because we don't need to do anything special when constructing
	 * a Square, we simply use the superclass constructor called with the
	 * correct parameters
	 */
	public Block(int courtWidth, int courtHeight, int init_x, int init_y) {
		super(0, 0, init_x, init_y, SIZE_WIDTH, SIZE_HEIGHT, 
			  courtWidth, courtHeight);
	}

	public void draw(Graphics g, Color c) {
		this.c = c;
		g.setColor(c);
		g.fillRect(pos_x, pos_y, width, height);
	}
	
	public Color getColor() {
		return this.c;
	}
	
	public void setColor(Color c) {
		this.c = c;
	}

	public boolean getPowerUp() {
		return this.hasPowerUp;
	}
	
	public void setPowerUp(boolean b) {
		this.hasPowerUp = b;
	}
}