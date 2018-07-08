/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {	
	
	/** Width of each box in pixels */
	private static final int BOX_WIDTH = 150;

/** Height of each box in pixels */
	private static final int BOX_HEIGHT = 100;
	
	public void run() {
		int centerX = getWidth()/2;
		int centerY = getHeight()/2;
		
		/*Program box*/
		double program_box_x = centerX - BOX_WIDTH/2;
		double program_box_y = centerY - BOX_HEIGHT*1.5;
		add(new GRect(program_box_x,program_box_y, BOX_WIDTH, BOX_HEIGHT));
		
		GLabel program = new GLabel("Program");
		double program_label_width = program.getWidth();
		double program_label_height = program.getAscent();
		add(program, centerX-program_label_width/2,program_box_y+BOX_HEIGHT/2+program_label_height/2);
		
		/*Console program box*/
		double console_Program_box_x = centerX - BOX_WIDTH/2;
		double console_Program_box_y = centerY + BOX_HEIGHT*.5;
		add(new GRect(console_Program_box_x,console_Program_box_y, BOX_WIDTH, BOX_HEIGHT));
		
		GLabel console_Program = new GLabel("ConsoleProgram");
		double console_program_label_width = console_Program.getWidth();
		double console_program_label_height = console_Program.getAscent();
		add(console_Program, centerX-console_program_label_width/2,console_Program_box_y+BOX_HEIGHT/2+console_program_label_height/2);
		
		/*Graphics program box*/
		double graphics_program_box_x = centerX - BOX_WIDTH*2;
		double graphics_program_box_y = centerY + BOX_HEIGHT*.5;
		add(new GRect(graphics_program_box_x,graphics_program_box_y, BOX_WIDTH, BOX_HEIGHT));
		
		GLabel graphics_Program = new GLabel("GraphicsProgram");
		double graphics_program_label_width = graphics_Program.getWidth();
		double graphics_program_label_height = graphics_Program.getAscent();
		add(graphics_Program, graphics_program_box_x + (BOX_WIDTH-graphics_program_label_width)/2,graphics_program_box_y+BOX_HEIGHT/2+graphics_program_label_height/2);
		
		/*Dialog program box*/
		double dialog_program_box_x = centerX + BOX_WIDTH;
		double dialog_program_box_y = centerY + BOX_HEIGHT*.5;
		add(new GRect(dialog_program_box_x,dialog_program_box_y, BOX_WIDTH, BOX_HEIGHT));
		
		GLabel dialog_Program = new GLabel("DialogProgram");
		double dialog_program_label_width = dialog_Program.getWidth();
		double dialog_program_label_height = dialog_Program.getAscent();
		add(dialog_Program, dialog_program_box_x + (BOX_WIDTH-dialog_program_label_width)/2,dialog_program_box_y+BOX_HEIGHT/2+dialog_program_label_height/2);
		
		/*connector lines*/
		add(new GLine(centerX,centerY+BOX_HEIGHT*.5,centerX,centerY-BOX_HEIGHT*.5));
		add(new GLine(centerX - BOX_WIDTH*1.5,centerY+BOX_HEIGHT*.5,centerX,centerY-BOX_HEIGHT*.5));
		add(new GLine(centerX + BOX_WIDTH*1.5,centerY+BOX_HEIGHT*.5,centerX,centerY-BOX_HEIGHT*.5));		
	}
}

