import java.awt.*;

public class Paddle extends GameObj {

	public static final int SIZE_WIDTH = 32;
	public static final int SIZE_HEIGHT = 6;
	public static final int INIT_X = 140;
	public static final int INIT_Y = 290;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;

	/**
	 * Note that, because we don't need to do anything special when constructing
	 * a Square, we simply use the superclass constructor called with the
	 * correct parameters
	 */
	public Paddle(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_X, INIT_Y, 
			  SIZE_WIDTH, SIZE_HEIGHT, courtWidth, courtHeight);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(pos_x, pos_y, width, height);
	}

}
