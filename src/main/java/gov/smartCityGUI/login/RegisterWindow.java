package gov.smartCityGUI.login;

/*
@author Dylan Moran
Project: Smart City
@date 9/19/2023
I recieved help from: N/A
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gov.smartCityGUI.utilities.*;


public class RegisterWindow implements ActionListener{

  //UI Components
  JFrame frame;
  JLabel firstNameLabel;
  JLabel lastNameLabel;
  JLabel emailLabel;
  JLabel phoneLabel;
  JLabel adminLabel;
  JTextField firstNameText;
  JTextField lastNameText;
  JTextField emailText;
  JTextField phoneText;
  JTextField adminText;
  JButton button;
  JButton backButton;

  //***********************************************************************//

  /*
  * The default constructor is used to build the window and add all UI Components
  */
  public RegisterWindow(){
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
    frame = Gui.formFrame("Register");
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
    emailText = new JTextField();
    emailText.setBounds(75, 160, 150, 25);
    frame.add(emailText);

    //Phone # text field
    phoneText = new JTextField();
    phoneText.setBounds(75, 220, 150, 25);
    frame.add(phoneText);

    //Admin code text field
    adminText = new JTextField();
    adminText.setBounds(75, 280, 150, 25);
    frame.add(adminText);
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
    emailLabel = new JLabel("Email:");
    emailLabel.setBounds(75, 135, 150, 25);
    emailLabel.setForeground(new Color(255,255,255));
    frame.add(emailLabel);

    //Phone # label
    phoneLabel = new JLabel("Phone Number:");
    phoneLabel.setBounds(75,195, 150, 25);
    phoneLabel.setForeground(new Color(255,255,255));
    frame.add(phoneLabel);

    //Admin code label
    adminLabel = new JLabel("Admin Code:");
    adminLabel.setBounds(75, 255, 150, 25);
    adminLabel.setForeground(new Color(255,255,255));
    frame.add(adminLabel);
  } // End createLabels() method

  //***********************************************************************//

  /*
  * This method is used to create and position buttons within the frame, also adding action listeners
  */
  public void createButtons(){

    //Register button
    button = new JButton("Register");
    button.setBounds(100, 330, 100, 25);
    button.setBackground(new Color(235,235,235));
    button.addActionListener(this);
    button.setFocusable(false);
    frame.add(button);

    //Back button
    backButton = new JButton("Back");
    backButton.setBounds(100, 365, 100, 25);
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
      String email = emailText.getText();
      String phone = phoneText.getText();
      String role = adminText.getText();

      if(SmartCity.register(firstName, lastName, email, phone, role)){
        frame.dispose();
        LoginWindow login = new LoginWindow();
      } else {
        JOptionPane.showMessageDialog(null,
                                      "Requirements:\nFirst and Last Name must be longer than 3 characters.\nEmail: x@xxx.domain\nPhone Number: xxxxxxx (7-10 digits).",
                                      "Error Logging In",
                                      JOptionPane.WARNING_MESSAGE);
      }
    }

    //If back button is pressed
    if(e.getSource() == backButton){
      frame.dispose();
      LoginScene back = new LoginScene();
    }
  }
}