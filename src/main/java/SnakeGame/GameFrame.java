package com.Snakegame;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	// JFrame is a class in Java Swing that represents a window with a title and a
	// border that can be used to hold and display other components. It is a
	// top-level container in Swing, which means it can contain other components
	// such as buttons, labels, text fields, etc.
	
	
	
	//ensures the sender and receiver of a serialized object are using compatible versions of the class.
	private static final long serialVersionUID = 1L;

	GameFrame() {

		// add the game panel
		this.add(new GamePanel());

		// set the title for this game
		this.setTitle("Snake");

		// used to close the jframe
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// disables the ability for the user to resize the window.
		this.setResizable(true);

		// adjusts the window size just enough to fit its components and their layout
		// before the window is shown.
		this.pack();

		// used to visible the jframe
		this.setVisible(true);

		// usedto appear the j frame in the middle
		this.setLocationRelativeTo(null);
	}

}
