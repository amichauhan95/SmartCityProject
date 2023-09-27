package gov.smartCityGUI.education.gui;

/*
@author Dylan Moran
Project: Smart City
@date 9/19/2023
I recieved help from: N/A
*/

import gov.smartCityGUI.education.models.*;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java.util.ArrayList;

public class CredentialsWindow implements ActionListener{

	List<Credentials> credentialsList = new ArrayList<>();
	User user;
	EducationSystem system;
	

  //UI Components
  JFrame frame;
  JLabel firstNameLabel;
  JLabel lastNameLabel;
  JLabel ageLabel;
  JLabel gpaLabel;
  JTextField firstNameText;
  JTextField lastNameText;
  JTextField ageText;
  JTextField gpaText;
  JButton button;
  JButton backButton;

  //***********************************************************************//

  /*
  * The default constructor is used to build the window and add all UI Components
  */
	public CredentialsWindow(User user, EducationSystem system){
	  	this.user = user;
	 	this.system = system;
	    createFrame();
	    createTextFields();
	    createLabels();
	    createButtons();
  }

  //***********************************************************************//

  /*
  * This method is used to build the frame for the window
  */
  public void createFrame(){
    frame = Gui.formFrame("Enter Credentials");
  }

  //***********************************************************************//

  /*
  * This method is used to create and position the text fields within the frame
  */
  public void createTextFields(){

    //First name text field
    firstNameText = new JTextField();
    firstNameText.setBounds(75, 40, 150, 25);
    frame.add(firstNameText);

    //Last name text field
    lastNameText = new JTextField();
    lastNameText.setBounds(75, 100, 150, 25);
    frame.add(lastNameText);

    //Email text field
    ageText = new JTextField();
    ageText.setBounds(75, 160, 150, 25);
    frame.add(ageText);

    //Phone # text field
    gpaText = new JTextField();
    gpaText.setBounds(75, 220, 150, 25);
    frame.add(gpaText);

  } // End createTextFields() method

  //***********************************************************************//

  /*
  * This method is used to create and position labels within the frame
  */
  public void createLabels(){

    //First name label
    firstNameLabel = new JLabel("First Name:");
    firstNameLabel.setBounds(75, 15, 150, 25);
    firstNameLabel.setForeground(new Color(255,255,255));
    frame.add(firstNameLabel);

    //Last name label
    lastNameLabel = new JLabel("Last Name:");
    lastNameLabel.setBounds(75, 75, 150, 25);
    lastNameLabel.setForeground(new Color(255,255,255));
    frame.add(lastNameLabel);

    //Email label
    ageLabel = new JLabel("Age:");
    ageLabel.setBounds(75, 135, 150, 25);
    ageLabel.setForeground(new Color(255,255,255));
    frame.add(ageLabel);

    //Phone # label
    gpaLabel = new JLabel("GPA:");
    gpaLabel.setBounds(75,195, 150, 25);
    gpaLabel.setForeground(new Color(255,255,255));
    frame.add(gpaLabel);

  } // End createLabels() method

  //***********************************************************************//

  /*
  * This method is used to create and position buttons within the frame, also adding action listeners
  */
  public void createButtons(){

    //Register button
    button = new JButton("Enter");
    button.setBounds(100, 260, 100, 25);
    button.setBackground(new Color(235,235,235));
    button.addActionListener(this);
    button.setFocusable(false);
    frame.add(button);

    //Back button
    backButton = new JButton("Back");
    backButton.setBounds(100, 290, 100, 25);
    backButton.setBackground(new Color(235,235,235));
    backButton.addActionListener(this);
    backButton.setFocusable(false);
    frame.add(backButton);
  } // End createButtons() method

  //***********************************************************************//

  /*
  * This method is used to perform actions when buttons are pressed
  */
  public void actionPerformed(ActionEvent e){

    //If register button is pressed
    if(e.getSource() == button){
      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      String age = ageText.getText();
      String gpa = gpaText.getText();

		credentialsList.add(new Credentials(firstName, lastName, age, gpa, system.s));
        

		frame.dispose();
		EducationWindow education = new EducationWindow(this.user);

    }

    //If back button is pressed
    if(e.getSource() == backButton){
      frame.dispose();
      EducationWindow education = new EducationWindow(this.user);
    }
  }
}