package com.SnakeGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.*;
import java.util.*;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;

	// Screen width and height
	static final int WIDTH = 600;
	static final int HEIGHT = 600;

	// how much object we want in the screen
	static final int UNIT_SIZE = 20;
	// how many objects can fit into the Screen
	static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

	// hold x and y coordinates for body parts of the snake
	final int x[] = new int[NUMBER_OF_UNITS];
	final int y[] = new int[NUMBER_OF_UNITS];

	// initial length of the snake
	int length = 1;

	int foodEaten;
	int foodX;
	int foody;

	// which direction that the snake should be goes first
	char direction = 'D';
	boolean running = false;
	Random random;
	Timer timer;

	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.setBackground(Color.BLACK);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		play();
	}

	// used to Start the game
	public void play() {
		addFood();
		running = true;
		timer = new Timer(75, this);
		timer.start();
	}

	// used to paint the components
	@Override
	protected void paintComponent(Graphics graphics) {

		super.paintComponent(graphics);
		draw(graphics);
	}

	// used to move the snake
	public void move() {
		for (int i = length; i > 0; i--) {
			// shift the snake one unit to the desired direction to create a move
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (direction) {
		case 'U':
			y[0] = y[0] - UNIT_SIZE;
			break;

		case 'D':
			y[0] = y[0] + UNIT_SIZE;
			;
			break;

		case 'R':
			x[0] = x[0] + UNIT_SIZE;
			break;

		case 'L':
			x[0] = x[0] - UNIT_SIZE;
			break;

		}
	}

	// uses to add the food
	int count =1;
	public void checkFood() {
		if ((x[0] == foodX) && (y[0] == foody)) {
			if(count%3 ==0) {
				length--;
			}
			else {
				length++;
			}
			count++;
			foodEaten++;
			addFood();
		}
	}

	//
	public void draw(Graphics graphics) {
		if (running) {
//			for (int i = 0; i < HEIGHT / UNIT_SIZE; i++) {
//				graphics.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, HEIGHT);
//				graphics.drawLine(0, i * UNIT_SIZE, WIDTH, i * UNIT_SIZE);
//			}
			graphics.setColor(Color.RED);
			graphics.fillOval(foodX, foody, UNIT_SIZE, UNIT_SIZE);

			for (int i = 0; i < length; i++) {
				if (i == 0) {
					graphics.setColor(Color.WHITE);
					graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					graphics.setColor(Color.GREEN);
					graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);

				}
			}
			 graphics.setColor(Color.WHITE);
		        graphics.setFont(new Font("Sans serif", Font.PLAIN, 20));
		        graphics.drawString("Score: " + foodEaten, 10, 20);
		}
		else {
			gameOver(graphics);
		}
	}

	//
	public void addFood() {
		foodX = random.nextInt((int) (WIDTH / UNIT_SIZE))*UNIT_SIZE;
		foody = random.nextInt((int) (HEIGHT / UNIT_SIZE))*UNIT_SIZE;
		

	}

	// check the snake is collision or not
	public void checkHit() {
		// check if head run into its body
		for (int i = length; i > 0; i--) {
			if (x[0] == x[i] && y[0] == y[i]) {
				running = false;
			}
		}
		// check if head runs into walls
		if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
			running = false;
		}
		if (!running) {
			timer.stop();
		}
	}

	// used for the gameover message
	public void gameOver(Graphics graphics) {
		graphics.setColor(Color.red);
		graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 50));
		FontMetrics metrics = getFontMetrics(graphics.getFont());
		graphics.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);

		graphics.setColor(Color.white);
		graphics.setFont(new Font("Sans serif", Font.ROMAN_BASELINE, 25));
		metrics = getFontMetrics(graphics.getFont());
		graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score :" + foodEaten)) / 2,
				graphics.getFont().getSize());
	}

	// it's a one method in the ActionListener
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (running) {
			move();
			checkFood();
			checkHit();
		}
		repaint();

	}

	// check for the which key i pressed
	public class MyKeyAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if (direction != 'R') {
					direction = 'L';
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (direction != 'L') {
					direction = 'R';
				}
				break;
			case KeyEvent.VK_UP:
				if (direction != 'D') {
					direction = 'U';
				}
				break;
			case KeyEvent.VK_DOWN:
				if (direction != 'U') {
					direction = 'D';
				}
				break;
			}

		}

	}
}
