/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


/**
 * GameCourt
 * 
 * This class holds the primary game logic for how different objects interact
 * with one another. Take time to understand how the timer interacts with the
 * different methods and how it repaints the GUI on every tick().
 * 
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private Paddle paddle; // the paddle
	private Ball ball; // the main ball
	private Ball b2;

	private Block[][] blocks = new Block[12][10]; // the blocks
	private int lives;
	private int score;
	private boolean isNextLevel = false;
	private boolean isOtherBallReleased = false;

	public boolean playing = false; // whether the game is running
	private JLabel status; // Current status text (i.e. Running...)
	private JLabel score_label;
	private JLabel lives_left;

	// Game constants
	public static final int COURT_WIDTH = 312;
	public static final int COURT_HEIGHT = 300;
	public static final int SQUARE_VELOCITY = 4;
	// Update interval for timer, in milliseconds
	public static final int INTERVAL = 35;

	public GameCourt(JLabel lives_left, JLabel status, JLabel score_label) {
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lives = 3;

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 10; j++) {

				double rand = Math.random() * 100;
				int num = (int) (rand);

				if (num % 9 == 0) {
					Block b = new Block(COURT_WIDTH, COURT_HEIGHT, i*26, 32 + j*9);
					blocks[i][j] = b;

					if (j < 2)
						blocks[i][j].setColor(Color.RED);
					else if (j == 2 || j == 3)
						blocks[i][j].setColor(Color.MAGENTA);
					else if (j == 4 || j == 5)
						blocks[i][j].setColor(Color.ORANGE);
					else if (j == 6 || j == 7)
						blocks[i][j].setColor(Color.GREEN);
					else if (j == 8 || j == 9)
						blocks[i][j].setColor(Color.YELLOW);

				}
			}
		}

		// add extra ball power-up to random block
		while (!isOtherBallReleased) {
			double r1 = Math.random();
			double r2 = Math.random();
			int randI = (int) (r1 * 11);
			int randJ = (int) (r2 * 9);

			if (blocks[randI][randJ] != null) {
				blocks[randI][randJ].setPowerUp(true);
				break;
			}
		}

		// The timer is an object which triggers an action periodically
		// with the given INTERVAL. One registers an ActionListener with
		// this timer, whose actionPerformed() method will be called
		// each time the timer triggers. We define a helper method
		// called tick() that actually does everything that should
		// be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area.
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// This key listener allows the square to move as long
		// as an arrow key is pressed, by changing the square's
		// velocity accordingly. (The tick method below actually
		// moves the square.)
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_LEFT)
					paddle.v_x = -SQUARE_VELOCITY;
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
					paddle.v_x = SQUARE_VELOCITY;
			}

			public void keyReleased(KeyEvent e) {
				paddle.v_x = 0;
				paddle.v_y = 0;
			}
		});

		this.lives_left = lives_left;
		this.status = status;
		this.score_label = score_label;

	}

	public void setScore() {
		this.score = 0;
	}

	public void setLives() {
		this.lives = 3;
	}

	public void resetBlocks() {

		// clear existing blocks
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 10; j++) {
				blocks[i][j] = null;
			}
		}

		// re-populate blocks for first level
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 10; j++) {

				double rand = Math.random() * 100;
				int num = (int) (rand);

				if (num % 9 == 0) {
					Block b = new Block(COURT_WIDTH, COURT_HEIGHT, i*26, 32 + j*9);
					blocks[i][j] = b;

					if (j < 2)
						blocks[i][j].setColor(Color.RED);
					else if (j == 2 || j == 3)
						blocks[i][j].setColor(Color.MAGENTA);
					else if (j == 4 || j == 5)
						blocks[i][j].setColor(Color.ORANGE);
					else if (j == 6 || j == 7)
						blocks[i][j].setColor(Color.GREEN);
					else if (j == 8 || j == 9)
						blocks[i][j].setColor(Color.YELLOW);

				}
			}
		}

		isOtherBallReleased = false;

		// add extra ball power-up to random block
		while (!isOtherBallReleased) {
			double r1 = Math.random();
			double r2 = Math.random();
			int randI = (int) (r1 * 11);
			int randJ = (int) (r2 * 9);

			if (blocks[randI][randJ] != null) {
				blocks[randI][randJ].setPowerUp(true);
				break;
			}
		}
	}

	public void arrangeLevelTwo() {

		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 10; j++) {

				double rand = Math.random() * 100;
				int num = (int) (rand);

				if (num % 4 == 1) {

					Block b = new Block(COURT_WIDTH, COURT_HEIGHT, 
							i*26, 32 + j*9);
					blocks[i][j] = b;

					if (j < 2)
						blocks[i][j].setColor(Color.RED);
					else if (j == 2 || j == 3)
						blocks[i][j].setColor(Color.MAGENTA);
					else if (j == 4 || j == 5)
						blocks[i][j].setColor(Color.ORANGE);
					else if (j == 6 || j == 7)
						blocks[i][j].setColor(Color.GREEN);
					else if (j == 8 || j == 9)
						blocks[i][j].setColor(Color.YELLOW);
				}
			}
		}


		// add "extra ball" power-up to random block
		while (!isOtherBallReleased) {
			double r1 = Math.random();
			double r2 = Math.random();
			int randI = (int) (r1 * 11);
			int randJ = (int) (r2 * 9);

			if (blocks[randI][randJ] != null) {
				blocks[randI][randJ].setPowerUp(true);
				break;
			}
		}
	}

	public boolean areBlocksDone(Block[][] b) {

		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++) {

				if (blocks[i][j] != null)
					return false;
			}
		}

		return true;
	}

	/**
	 * (Re-)set the game to its initial state.
	 */
	public void reset() {

		paddle = new Paddle(COURT_WIDTH, COURT_HEIGHT);
		ball = new Ball(COURT_WIDTH, COURT_HEIGHT);
		b2 = null;
		isOtherBallReleased = false;

		playing = true;
		lives_left.setText("Lives left: " + lives + " |");
		status.setText("Playing...");
		score_label.setText("| Score: " + score);

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

	/**
	 * This method is called every time the timer defined in the constructor
	 * triggers.
	 */
	void tick() {
		if (playing) {

			if (lives == 0) {
				playing = false;
				status.setText("SORRY...YOU LOST!");
			}

			if (areBlocksDone(blocks) && !isNextLevel) {
				playing = false;
				isNextLevel = true;
				arrangeLevelTwo();
				status.setText("STARTING NEXT LEVEL");
				Timer temp_timer = new Timer(2500, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reset();
					}
				});
				temp_timer.setRepeats(false);
				temp_timer.start();
			}

			if (isNextLevel && areBlocksDone(blocks)) {
				playing = false;
				status.setText("YOU WIN!!!");
			}

			Direction d = ball.hitWall();
			Direction d2 = Direction.UP;
			if (b2 != null)
				d2 = b2.hitWall();

			// checks if ball is lower than the paddle
			if (d == Direction.DOWN) {
				playing = false;
				lives--;
				status.setText("You lost a life!");
				lives_left.setText("Lives left: " + lives + " |");
				Timer temp_timer = new Timer(2500, new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reset();
					}
				});
				temp_timer.setRepeats(false);
				temp_timer.start();
			}

			if (d2 == Direction.DOWN)
				b2 = null;


			// advance the paddle and ball in their
			// current direction.
			paddle.move();
			ball.move();
			if (b2 != null)
				b2.move();

			// make the ball bounce off walls...
			ball.bounce(d);
			if (b2 != null)
				b2.bounce(d2);

			// ...and the paddle
			ball.hitPaddle(paddle);
			if (b2 != null)
				b2.hitPaddle(paddle);

			for (int i = 0; i < blocks.length; i++) {
				for (int j = 0; j < blocks[0].length; j++) {

					if (blocks[i][j] == null)
						continue;

					if (ball.willIntersect(blocks[i][j])) {

						ball.bounce(ball.hitObj(blocks[i][j]));

						if (blocks[i][j].getPowerUp() && !isOtherBallReleased) {
							b2 = new Ball(COURT_WIDTH, COURT_HEIGHT);
							isOtherBallReleased = true;
							b2.setColor(Color.BLUE);
							b2.v_x = 3;
							b2.v_y = -2;
						}

						if (blocks[i][j].getColor().equals(Color.RED)) {
							blocks[i][j].setColor(Color.MAGENTA);
							score += 5;
						}
						else if (blocks[i][j].getColor().equals(Color.MAGENTA)) {
							blocks[i][j].setColor(Color.ORANGE);
							score += 4;
						}
						else if (blocks[i][j].getColor().equals(Color.ORANGE)) {
							blocks[i][j].setColor(Color.GREEN);
							score += 3;
						}
						else if (blocks[i][j].getColor().equals(Color.GREEN)) {
							blocks[i][j].setColor(Color.YELLOW);
							score += 2;
						}
						else if (blocks[i][j].getColor().equals(Color.YELLOW)) {
							blocks[i][j] = null;
							score += 1;
						}

						score_label.setText("| Score: " + score);
					}

					else if (b2 != null) {

						if (b2.willIntersect(blocks[i][j])) {

							b2.bounce(b2.hitObj(blocks[i][j]));

							if (blocks[i][j].getColor().equals(Color.RED)) {
								blocks[i][j].setColor(Color.MAGENTA);
								score += 5;
							}
							else if (blocks[i][j].getColor().equals(Color.MAGENTA)) {
								blocks[i][j].setColor(Color.ORANGE);
								score += 4;
							}
							else if (blocks[i][j].getColor().equals(Color.ORANGE)) {
								blocks[i][j].setColor(Color.GREEN);
								score += 3;
							}
							else if (blocks[i][j].getColor().equals(Color.GREEN)) {
								blocks[i][j].setColor(Color.YELLOW);
								score += 2;
							}
							else if (blocks[i][j].getColor().equals(Color.YELLOW)) {
								blocks[i][j] = null;
								score += 1;
							}
						}

						score_label.setText("| Score: " + score);
					}
				}
			}

			// update the display
			repaint();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paddle.draw(g);

		// draw blocks
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++) {

				if (blocks[i][j] == null)
					continue;

				else {
					blocks[i][j].draw(g, blocks[i][j].getColor());
				}

			}

		}

		ball.draw(g);

		if (b2 != null)
			b2.draw(g);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(COURT_WIDTH, COURT_HEIGHT);
	}
}
