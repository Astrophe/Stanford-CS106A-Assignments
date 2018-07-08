/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	public void run() {
		fillRowAndReturn();
		while (frontIsClear()) {
			if (beepersPresent()) {
				move();
				turnLeft();
				turnLeft();
				turnLeft();
				if (frontIsClear()) {
					move();
					fillRowAndReturn();
				}
				else {
					turnLeft();
				}
			}
			else {
				move();
				turnLeft();
				turnLeft();
				turnLeft();
				fillRowAndReturn();
			}
		}
	}
	
	/*Fills the row on every other corner and returns to the first column facing up ready to move to the next row*/
	private void fillRowAndReturn() {
		putBeeper();
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
		turnLeft();
		turnLeft();
		while (frontIsClear()) {
		move();	
		}
		turnLeft();
		turnLeft();
		turnLeft();
	}

}
