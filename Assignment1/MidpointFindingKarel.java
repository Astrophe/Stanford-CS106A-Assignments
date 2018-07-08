/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	public void run() {
		if (frontIsClear()) {
			move();
			/*create row of beepers less one on each end*/
			BeeperRowPut();
		}
		else {
			putBeeper();
		}
	}
	/*Creates a row with a beeper in every column except the first and last ones*/
	private void BeeperRowPut() {
		while (frontIsClear()) {
			putBeeper();
			move();
		}
		BeeperRowPull();			
	}
	/*Pulls the edge beepers of the row in a loop until there is only 1 beeper left and terminates the program on the middle corner*/
	private void BeeperRowPull() {
		turnLeft();
		turnLeft();
		move();
		while (beepersPresent()) {
			pickBeeper();
			move();
			while (beepersPresent()) {
				move();
			}
			BeeperRowPull();
		}
	}
}
