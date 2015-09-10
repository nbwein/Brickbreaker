import java.awt.*;

/**
 * A basic game object displayed as a red ball
 * 
 */
public class Ball extends GameObj {

	public static final int SIZE = 6;
	public static final int INIT_POS_X = 147;
	public static final int INIT_POS_Y = 280;
	public static final int INIT_VEL_X = 2;
	public static final int INIT_VEL_Y = 3;
	private Color c = Color.RED;

	public Ball(int courtWidth, int courtHeight) {
		super(INIT_VEL_X, INIT_VEL_Y, INIT_POS_X, INIT_POS_Y, SIZE, SIZE,
				courtWidth, courtHeight);
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(this.c);
		g.fillOval(pos_x, pos_y, width, height);
	}
	

}