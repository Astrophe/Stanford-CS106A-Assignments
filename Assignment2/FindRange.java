/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	
	/** Set sentinel */
	private static final int SENTINEL = 0;
	
	public void run() {
		println("This program finds the largest and smallest numbers.");
		int num = 1;
		int num_max = SENTINEL;
		int num_min = SENTINEL;
		num = readInt("? ");
		if (num == SENTINEL) {
			println("No numbers have been entered before the Sentinel. No max or min exists.");
		}
		else {
			num_max = num;
			num_min = num;
			while (true) {
				num = readInt("? ");
				if (num == SENTINEL) break;
				if (num > num_max) {
					num_max = num;
				}
				if (num < num_min) {
					num_min = num;
				}
			}
			println("smallest = " + num_min);
			println("largest = " + num_max);
		}
	}
}

