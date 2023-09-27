package gov.smartCityGUI.education.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;


public class EducationWindow implements ActionListener {

  User user;
  EducationSystem system;
  // Main Frame
  JFrame frame;
  JLabel label1;
  JLabel label2;
  JLabel label3;
  JButton backButton;
  JButton button1;
  JButton button4;
  JButton button5;
  JButton button6;
  JButton button7;

  // Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;

  public EducationWindow(User user) {
    this.system = new EducationSystem(this.user);
    this.user = user;

    createFrames();
    createButtons();
  }


  public void createFrames() {
    frame = Gui.bigFrame("Education System");
  }

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    button1 = Gui.genericButton("View Schools");
    button1.addActionListener(this);
    button1.setBounds(120, 90, 175, 35);
    frame.add(button1);

    button4 = Gui.genericButton("Enroll to Schools");
    button4.addActionListener(this);
    button4.setBounds(360, 190, 175, 35);
    frame.add(button4);

    button5 = Gui.genericButton("Students Portal");
    button5.addActionListener(this);
    button5.setBounds(360, 90, 175, 35);
    frame.add(button5);

    button6 = Gui.genericButton("Teachers Portal");
    button6.addActionListener(this);
    button6.setBounds(120, 190, 175, 35);
    frame.add(button6);
    
    button7 = Gui.genericButton("Directors Portal");
    button7.addActionListener(this);
    button7.setBounds(250, 300, 175, 35);
    frame.add(button7);

  }

  	public void actionPerformed(ActionEvent e) {

		//Back to menu
	    if (e.getSource() == backButton) {
	     	frame.dispose();
	      	Menu menu = new Menu(this.user);
	    }

		//View all Schools
	    if (e.getSource() == button1) {
	      	frame.dispose();
	      	ViewAllSchools view = new ViewAllSchools(this.user, this.system);
	    }
	
		  //Student portal
	    if(e.getSource() == button5){
			frame.dispose();
			StudentPortal portal = new StudentPortal(this.user);
	    }
		  //Enter credentials
	    if(e.getSource() == button4){
			 frame.dispose();
			 CredentialsWindow cred = new CredentialsWindow(this.user, this.system);
	    }

		//Teachers Portal
	    if (e.getSource() == button6) {
	      	frame.dispose();
	      	TeachersPortal portal = new TeachersPortal(this.user, this.system);
	    }

		//Directors Portal
	    if(e.getSource() == button7){
	    	frame.dispose();
	    	DirectorsPortal portal = new DirectorsPortal(this.user, this.system);
	    }

  	}
}