/**
  * Team Member(s) working on this class: Ami Chauhan, Melih Kartal, Dylan Moran
  * Project: Smart City
  * @author: Ami Chauhan
**/

package gov.smartCityGUI.education.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;


public class EducationWindow implements ActionListener {

	//Resources
  	User user;
  	EducationSystem system;
	
  	// Main Frame
  	JFrame frame;
  	JLabel label1;
  	JLabel label2;
  	JLabel label3;
  	JButton backButton;
  	JButton viewSchoolsButton;
  	JButton enrollButton;
  	JButton sPortalButton;
  	JButton tPortalButton;
  	JButton dPortalButton;

 	// Input Frame
  	JFrame inputFrame;
  	JLabel prompt;
  	JTextField input;
  	JButton enterButton;

	// ***********************************************************************//

  	public EducationWindow(User user) {
	    this.system = new EducationSystem(this.user);
	    this.user = user;
	
	    createFrames();
	    createButtons();
  	}

	// ***********************************************************************//


  	public void createFrames() {
    	frame = Gui.bigFrame("Education System");
  	}

	// ***********************************************************************//

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    viewSchoolsButton = Gui.genericButton("View Schools");
    viewSchoolsButton.addActionListener(this);
    viewSchoolsButton.setBounds(120, 90, 175, 35);
    frame.add(viewSchoolsButton);

    enrollButton = Gui.genericButton("Enroll to Schools");
    enrollButton.addActionListener(this);
    enrollButton.setBounds(360, 190, 175, 35);
    frame.add(enrollButton);

    sPortalButton = Gui.genericButton("Students Portal");
    sPortalButton.addActionListener(this);
    sPortalButton.setBounds(360, 90, 175, 35);
    frame.add(sPortalButton);

    tPortalButton = Gui.genericButton("Teachers Portal");
    tPortalButton.addActionListener(this);
    tPortalButton.setBounds(120, 190, 175, 35);
    frame.add(tPortalButton);
    
    dPortalButton = Gui.genericButton("Directors Portal");
    dPortalButton.addActionListener(this);
    dPortalButton.setBounds(250, 300, 175, 35);
    frame.add(dPortalButton);

  }

	// ***********************************************************************//

	/**
 	  * This method is used to handle button clicks
	**/
  	public void actionPerformed(ActionEvent e) {

		//Back to menu
	    if (e.getSource() == backButton) {
	     	frame.dispose();
	      	Menu menu = new Menu(this.user);
	    }

		//View all Schools
	    if (e.getSource() == viewSchoolsButton) {
	      	frame.dispose();
			@SuppressWarnings("unused")
	      	ViewAllSchools view = new ViewAllSchools(this.user, this.system);
	    }
	
		//Student portal
	    if(e.getSource() == sPortalButton){
			frame.dispose();
			@SuppressWarnings("unused")
			StudentPortal portal = new StudentPortal(this.user);
	    }
		
		//Enter credentials
		if(e.getSource() == enrollButton){
			frame.dispose();
			@SuppressWarnings("unused")
			CredentialsWindow cred = new CredentialsWindow(this.user, this.system);
	    }

		//Teachers Portal
	    if (e.getSource() == tPortalButton) {
	      	frame.dispose();
			@SuppressWarnings("unused")
	      	TeachersPortal portal = new TeachersPortal(this.user, this.system);
	    }

		//Directors Portal
	    if(e.getSource() == dPortalButton){
	    	frame.dispose();
			@SuppressWarnings("unused")
	    	DirectorsPortal portal = new DirectorsPortal(this.user, this.system);
	    }
  	} // End actionPerformed() method
} // End class