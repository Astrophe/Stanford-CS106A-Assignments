/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	public void run() {
		while (frontIsClear()) {
			repairColumn();
		}
		repairColumn();
	}
			
	private void repairColumn() {
		turnLeft();
		if (noBeepersPresent()) {
			putBeeper();
		}
		/* climb column and add a beeper where one is missing */
		while (frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();
			}
			move();
			if (noBeepersPresent()) {
				putBeeper();
			}
		}
		/* descend column and move to next column if not already at last one */
		turnLeft();
		turnLeft();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
		if (frontIsClear()) {
			move();
			move();
			move();
			move();
		}
		else {
		}
	}
}