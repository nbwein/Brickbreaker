/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

// imports necessary libraries for Java swing
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
	public void run() {
		// NOTE : recall that the 'final' keyword notes immutability
		// even for local variables.

		// Top-level frame in which game components live
		final JFrame frame = new JFrame("Breakout");
		frame.setLocation(300, 300);
		
		File file = new File("Instructions.txt");
		String path = file.getAbsolutePath();
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String message = "";
			String line = null;
			while ((line = br.readLine()) != null) {
				message += line;
				message += "\n";
			}
			br.close();
			JOptionPane.showMessageDialog(frame, message);
		} catch (IOException e) {
			System.out.println(e.toString());
		}

		// Status panel
		final JPanel status_panel = new JPanel();
		frame.add(status_panel, BorderLayout.SOUTH);
		final JLabel lives_left = new JLabel("Lives left: 3 |");
		status_panel.add(lives_left);
		final JLabel status = new JLabel("Playing...");
		status_panel.add(status);
		final JLabel score = new JLabel("| Score: 0");
		status_panel.add(score);
		
		

		// Main playing area
		final GameCourt court = new GameCourt(lives_left, status, score);
		frame.add(court, BorderLayout.CENTER);

		// Reset button
		final JPanel control_panel = new JPanel();
		frame.add(control_panel, BorderLayout.NORTH);

		// Note here that when we add an action listener to the reset
		// button, we define it as an anonymous inner class that is
		// an instance of ActionListener with its actionPerformed()
		// method overridden. When the button is pressed,
		// actionPerformed() will be called.
		final JButton reset = new JButton("New Game");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				court.resetBlocks();
				court.setScore();
				court.setLives();
				court.reset();
			}
		});
		control_panel.add(reset);

		// Put the frame on the screen
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// Start game
		court.reset();
	}

	/*
	 * Main method run to start and run the game Initializes the GUI elements
	 * specified in Game and runs it IMPORTANT: Do NOT delete! You MUST include
	 * this in the final submission of your game.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Game());
	}
}
