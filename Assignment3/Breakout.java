/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;	

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setupBricks();
		Paddle = new GRect((WIDTH/2 - PADDLE_WIDTH/2), HEIGHT - 2*PADDLE_Y_OFFSET - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
		Paddle.setFilled(true);
		Paddle.setFillColor(Color.BLACK);
		add(Paddle);
		addMouseListeners();
		playGame();
	}
	
	private void setupBricks() {
		int initial_X = (WIDTH - (NBRICKS_PER_ROW*BRICK_WIDTH))/2;
		Color color = null;
		//build each row of bricks
		for (int i = 0; i < NBRICK_ROWS; i++){
			//SET COLOR
			switch (i) {
			case 0: color = color.RED; break;
			case 1: color = color.RED; break;
			case 2: color = color.ORANGE; break;
			case 3: color = color.ORANGE; break;
			case 4: color = color.YELLOW; break;
			case 5: color = color.YELLOW; break;
			case 6: color = color.GREEN; break;
			case 7: color = color.GREEN; break;
			case 8: color = color.CYAN; break;
			case 9: color = color.CYAN; break;
			}
			// build one row of bricks
			for (int j = 0; j < NBRICKS_PER_ROW; j++) {
				GRect Rect = new GRect(initial_X + BRICK_WIDTH*j, BRICK_Y_OFFSET + BRICK_HEIGHT*i, BRICK_WIDTH, BRICK_HEIGHT);
				Rect.setFilled(true);
				Rect.setFillColor(color);
				add(Rect);
			}
		}
	}
	
	private void playGame(){
		//Add ball to screen
		GOval ball = new GOval(WIDTH/2 - BALL_RADIUS/2,HEIGHT/2 - BALL_RADIUS/2,BALL_RADIUS*2,BALL_RADIUS*2);
		ball.setFilled(true);
		Color color = null;
		color = color.BLACK;
		ball.setFillColor(color);
		add(ball);
		int turnsRemaining = NTURNS;
		//Randomly generate starting x velocity and constant y velocity; move ball
		RandomGenerator rgen = RandomGenerator.getInstance();
		vx = rgen.nextDouble(1.0,3.0);
		if (rgen.nextBoolean(0.5)) vx = -vx;
		vy = 3.0;
		while (true) {
			//check if game is won!
			if (nBricksRemaining <= 0) {
				add(new GLabel("You Win",  0, 20));
				break;
			}
			if (turnsRemaining <= 0) {
				add(new GLabel("You Lose",  0, 20));
				break;
			}
			else {
			ball.move(vx,vy);
			double ball_x = ball.getX();
			double ball_y = ball.getY();
			pause(PAUSE_TIME);
			if (ball_x <= 0 || ball_x >= WIDTH - BALL_RADIUS*2) {
				vx=-vx;
			}
			else if (ball_y <= 0) {
				vy=-vy;
			}
			else if (ball_y >= HEIGHT - BALL_RADIUS*4) {
				turnsRemaining--;
				if (turnsRemaining >0) {
				GLabel message = new GLabel("You lose 1 life! You have " + turnsRemaining + " lives left", 0, 20);
				add(message);
				ball.setLocation(WIDTH/2 - BALL_RADIUS/2,HEIGHT/2 - BALL_RADIUS/2);
				vx = rgen.nextDouble(1.0,3.0);
				if (rgen.nextBoolean(0.5)) vx = -vx;
				pause(3000);
				remove(message);
				}
			}
			//GET COLLISIONS
			GObject collider = getCollidingObject(ball_x, ball_y);
			if (collider != null) {
				if (collider == Paddle) {
					vy=-vy;
				}
				else {
					remove(collider);
					vy=-vy;
					nBricksRemaining--;
				}
			}
		}
		}
	}
	
	private GObject getCollidingObject(double x, double y) {
		GObject collider = null;
		if (getElementAt(x,y) != null){
			collider = getElementAt(x,y);
		}
		else if (getElementAt(x+2*BALL_RADIUS,y) != null){
			collider = getElementAt(x+2*BALL_RADIUS,y);
		}
		else if (getElementAt(x,y+2*BALL_RADIUS) != null){
			collider = getElementAt(x,y+2*BALL_RADIUS);
		}
		else if (getElementAt(x+2*BALL_RADIUS,y+2*BALL_RADIUS) != null){
			collider = getElementAt(x+2*BALL_RADIUS,y+2*BALL_RADIUS);
		}
		return collider;
	}
	
	public void mouseMoved(MouseEvent e) {
		if (e.getX() <= WIDTH-PADDLE_WIDTH/2 && e.getX() >= PADDLE_WIDTH/2) {
			Paddle.setLocation(e.getX()-PADDLE_WIDTH/2, HEIGHT - 2*PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		}
		else if (e.getX() < PADDLE_WIDTH/2) {
			Paddle.setLocation(0, HEIGHT - 2*PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		}
		else {
			Paddle.setLocation(WIDTH-PADDLE_WIDTH, HEIGHT - 2*PADDLE_Y_OFFSET - PADDLE_HEIGHT);
		}
	}
	private GRect Paddle;
	private double vx, vy;
	private static final int PAUSE_TIME = 5;
	private int nBricksRemaining = NBRICKS_PER_ROW*NBRICK_ROWS;
}

