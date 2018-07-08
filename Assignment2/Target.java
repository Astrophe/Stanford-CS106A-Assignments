/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/*set center*/
		int centerX = getWidth()/2;
		int centerY = getHeight()/2;
		/*draw large red outer circle*/
		GOval a = new GOval(centerX-72, centerY-72, 144, 144);
		a.setFillColor(Color.RED);
		a.setFilled(true);
		add(a);
		/*draw middle white circle*/
		GOval b = new GOval(centerX-46.8, centerY-46.8, 93.6, 93.6);
		b.setFillColor(Color.WHITE);
		b.setFilled(true);
		add(b);
		/*draw small inner red circle*/
		GOval c = new GOval(centerX-21.6, centerY-21.6, 43.2, 43.2);
		c.setFillColor(Color.RED);
		c.setFilled(true);
		add(c);
	}
}
