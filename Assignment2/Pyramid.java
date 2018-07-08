/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

/** Height of each brick in pixels */
	private static final int BRICK_HEIGHT = 11;

/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 5;
	
	public void run() {
		/*loop through all rows from base to top*/
		for (int i=0; i<BRICKS_IN_BASE; i++) {
			/*loop through all bricks on a given row starting with BRICKS_IN_BASE and ending with BIB - i forming a pyramid*/
			for (int j=0; j<BRICKS_IN_BASE-i; j ++){
				/*initializes starting x and y coordinates*/
				int startingX  = (getWidth() - (BRICKS_IN_BASE*BRICK_WIDTH))/2 + (BRICK_WIDTH/2)*i;
				int startingY = (getHeight() - (BRICK_HEIGHT));
				/*creates rectangular blocks with set heigh and widths, where x,y coordinates are adjusted by two for loops*/
				add(new GRect(startingX + BRICK_WIDTH*j, startingY - BRICK_HEIGHT*i, BRICK_WIDTH, BRICK_HEIGHT));
			}
		}
	}
}

