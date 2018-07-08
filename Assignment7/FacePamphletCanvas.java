/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// done (not responsive)
	}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		remove(message);
		message = new GLabel(msg);
		//message.setLocation(LEFT_MARGIN, 400);
		message.setLocation(getWidth()/2 - message.getWidth()/2,getHeight() - BOTTOM_MESSAGE_MARGIN);
		message.setFont(MESSAGE_FONT);
		add(message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		//clears canvas
		removeAll();
		add(message);
		
		//adds name label
		GLabel name = new GLabel(profile.getName());
		name.setLocation(LEFT_MARGIN, name.getHeight()+ TOP_MARGIN);
		name.setFont(PROFILE_NAME_FONT);
		name.setColor(Color.BLUE);
		add(name);
		
		//adds image
		GImage image = profile.getImage();
		if (image == null) {
			add(new GRect(LEFT_MARGIN, IMAGE_MARGIN+TOP_MARGIN, LEFT_MARGIN+IMAGE_WIDTH, TOP_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT));
			GLabel nullImageNotice = new GLabel("No Image");
			nullImageNotice.setLocation(LEFT_MARGIN+IMAGE_WIDTH/2-nullImageNotice.getWidth()/2, TOP_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT/2-nullImageNotice.getHeight()/2);
			nullImageNotice.setFont(PROFILE_IMAGE_FONT);
			add(nullImageNotice);
		}
		else {
			image.scale(IMAGE_WIDTH, IMAGE_HEIGHT);
			image.setSize(IMAGE_WIDTH, IMAGE_HEIGHT);
			image.setLocation(LEFT_MARGIN, IMAGE_MARGIN+TOP_MARGIN);
			add(image);
		}
		
		//adds status
		String status = profile.getStatus();
		GLabel statusLabel;
		if (status == "") {
			statusLabel = new GLabel("No current status");
			statusLabel.setLocation(LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT+STATUS_MARGIN+statusLabel.getHeight()*2);
			add(statusLabel);
		}
		else {
			statusLabel = new GLabel(profile.getName() + " is " + profile.getStatus());
			statusLabel.setLocation(LEFT_MARGIN, TOP_MARGIN+IMAGE_MARGIN+IMAGE_HEIGHT+STATUS_MARGIN+statusLabel.getHeight()*2);
			add(statusLabel);
		}
		
		//adds friends' list
		GLabel friendsListHeader = new GLabel("Friends: ", getWidth()/2, TOP_MARGIN+IMAGE_MARGIN);
		friendsListHeader.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(friendsListHeader);
		
		int i = 0;
		Iterator<String> friendsIterator = profile.getFriends();
		while (friendsIterator.hasNext()) {
			GLabel friendsList = new GLabel(friendsIterator.next());
			friendsList.setLocation(getWidth()/2, TOP_MARGIN+IMAGE_MARGIN + friendsListHeader.getHeight() + friendsListHeader.getHeight()*i);
			add(friendsList);		
			//friendsIterator.next();
			i++;
		}
	}
	private GLabel message = new GLabel("");
}
