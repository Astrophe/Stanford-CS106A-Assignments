/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
					implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		
		//add face pamphlet canvas
		facePamphlet = new FacePamphletCanvas();
		add(facePamphlet);
		
		//populate north interactors
		add(new JLabel("Name"), NORTH);
		tfName = new JTextField(TEXT_FIELD_SIZE);
		add(tfName, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		//populate west interactors
		tfStatus = new JTextField(TEXT_FIELD_SIZE);
		add(tfStatus, WEST);
		tfStatus.addActionListener(this);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		tfChangePicture = new JTextField(TEXT_FIELD_SIZE);
		add(tfChangePicture, WEST);
		tfChangePicture.addActionListener(this);
		add(new JButton("Change Picture"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		tfAddFriend = new JTextField(TEXT_FIELD_SIZE);
		add(tfAddFriend, WEST);
		tfAddFriend.addActionListener(this);
		add(new JButton("Add Friend"), WEST);
		
		addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
    	String cmd = e.getActionCommand();
    	
    	if (cmd.equals("Add") && !tfName.getText().equals("") || e.getSource() == tfName) {
    		currentProfile = new FacePamphletProfile(tfName.getText());
    		facePamphlet.displayProfile(currentProfile);
    		db.addProfile(currentProfile);
    		facePamphlet.showMessage("Profile Added!");
    	}
    	else if (cmd.equals("Delete") && !tfName.getText().equals("") || e.getSource() == tfName) {
    		db.deleteProfile(tfName.getText());
    		currentProfile = nullProfile;
    		facePamphlet.showMessage("Profile Deleted!");
    		facePamphlet.displayProfile(currentProfile);
    	}
    	else if (cmd.equals("Lookup") && !tfName.getText().equals("") || e.getSource() == tfName) {
    		currentProfile = db.getProfile(tfName.getText());
    		if (currentProfile == null) {
    			String name = tfName.getText();
    			facePamphlet.showMessage(name + " is not in our network!");
    			currentProfile = nullProfile;
    			facePamphlet.displayProfile(currentProfile);
    		}
    		else {
    			facePamphlet.showMessage("");
    			facePamphlet.displayProfile(currentProfile);
    		}
    	}
    	else if (!tfStatus.getText().equals("") && cmd.equals("Change Status") || e.getSource() == tfStatus) {
    		if (currentProfile == nullProfile) {
    			facePamphlet.showMessage("Please select a profile first!");
    		}
    		else {
    			currentProfile.setStatus(tfStatus.getText());
    			facePamphlet.showMessage("Status Updated!");
    			facePamphlet.displayProfile(currentProfile);
    		}
    	}
    	else if (!tfChangePicture.getText().equals("") && cmd.equals("Change Picture") || e.getSource() == tfChangePicture) {
    		GImage image = null;
    		if (currentProfile == nullProfile) {
    			facePamphlet.showMessage("Please select a profile first!");
    		}
    		else {
    			try {
    				image = new GImage(tfChangePicture.getText());
    				currentProfile.setImage(image);
    				facePamphlet.showMessage("Image Updated!");
    				facePamphlet.displayProfile(currentProfile);
    			}
    			catch (ErrorException ex) {
    				facePamphlet.showMessage("Invalid image format, please try again!");
    			}
    		}
    	}
    	else if (!tfAddFriend.getText().equals("") && cmd.equals("Add Friend") || e.getSource() == tfAddFriend) {
    		if (currentProfile == nullProfile){
    			facePamphlet.showMessage("Please select a profile first!");
    		}
    		else {
    			if (tfAddFriend.getText().equals(currentProfile.getFriends())) {
    				facePamphlet.showMessage(tfAddFriend.getText() + " is already your friend!");
    			}
    			else if (db.containsProfile(tfAddFriend.getText())) {
    				currentProfile.addFriend(tfAddFriend.getText());
    				db.getProfile(tfAddFriend.getText()).addFriend(currentProfile.getName());
    				facePamphlet.showMessage("Friend Added!");
    			}
    			else {
    				facePamphlet.showMessage(tfAddFriend.getText() + " is not in our network!");
    			}
    		}
    		facePamphlet.displayProfile(currentProfile);
    	}
	}
    private JTextField tfName;
    private JTextField tfStatus;
    private JTextField tfChangePicture;
    private JTextField tfAddFriend;
    private FacePamphletDatabase db = new FacePamphletDatabase();
    private FacePamphletProfile nullProfile = new FacePamphletProfile("None");
    private FacePamphletProfile currentProfile = nullProfile;
    private FacePamphletCanvas facePamphlet;
}