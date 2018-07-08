/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	   
		//Construct graph
		graph = new NameSurferGraph();
		add(graph);
		
		//Initialize db
		db = new NameSurferDataBase(NAMES_DATA_FILE);
		
		//create interactors and action listener
		add(new JLabel("Name"), SOUTH);
		tf = new JTextField(10);
		tf.addActionListener(this);
	    add(tf, SOUTH);
	    add(new JButton("Graph"), SOUTH);
	    add(new JButton("Clear"), SOUTH);
	    addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Clear")) {
			graph.clear();
		}
		else {
			NameSurferEntry entry = db.findEntry(tf.getText());
			graph.addEntry(entry);
			graph.update();
		}
	}
	//IVars
	private JTextField tf;
	private NameSurferDataBase db;
	private NameSurferGraph graph;
}
