package gov.smartCityGUI.hospital.gui;

/*
@author Dylan Moran
Project: Smart City
@date 9/19/2023
I recieved help from: Ami Chauhan
*/

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.controller.*;

public class AddDoctorForm implements ActionListener {

  // Resources
  User user;
  HospitalSystem system;

  // UI Components
  JFrame frame;
  JLabel firstNameLabel;
  JLabel lastNameLabel;
  JLabel phoneLabel;
  JLabel emailLabel;
  JLabel consultingFeeLabel;
  JLabel doctorIDLabel;
  JTextField firstNameText;
  JTextField lastNameText;
  JTextField phoneText;
  JTextField emailText;
  JTextField consultingFeeText;
  JTextField doctorIDText;
  JButton button;
  JButton backButton;

  /**
    * public AddDoctorForm(User user, HospitalSystem system) does intitalizes User,System and UI
    * @param User user represents an instance of User class
    * @param HospitalSystem system represents an instance of HospitalSystem Class
  **/
  public AddDoctorForm(User user, HospitalSystem system) {
    this.user = user;
    this.system = system;
    createFrame();
    createTextFields();
    createLabels();
    createButtons();
  }

  /**
    * public void createFrame() creates main frame Add Doctor Form UI
  **/
  public void createFrame() {
    frame = new JFrame("Add Doctor");
    frame.setSize(475, 315);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setBackground(new Color(75, 75, 75));
    frame.setVisible(true);
  }

  /**
    * public void createTextFields() creates text fields Add Doctor Form UI
  **/
  public void createTextFields() {
    firstNameText = new JTextField();
    firstNameText.setBounds(75, 80, 150, 25);
    frame.add(firstNameText);

    lastNameText = new JTextField();
    lastNameText.setBounds(250, 80, 150, 25);
    frame.add(lastNameText);

    phoneText = new JTextField();
    phoneText.setBounds(75, 140, 150, 25);
    frame.add(phoneText);

    emailText = new JTextField();
    emailText.setBounds(250, 140, 150, 25);
    frame.add(emailText);

    consultingFeeText = new JTextField();
    consultingFeeText.setBounds(75, 200, 150, 25);
    frame.add(consultingFeeText);

    doctorIDText = new JTextField();
    doctorIDText.setBounds(250, 200, 150, 25);
    frame.add(doctorIDText);
  }

  /**
    * public void createLabels() creates labels Add Doctor Form UI
  **/
  public void createLabels() {

    firstNameLabel = new JLabel("Doctor's First Name:");
    firstNameLabel.setBounds(75, 50, 150, 25);
    firstNameLabel.setForeground(new Color(255, 255, 255));
    frame.add(firstNameLabel);

    lastNameLabel = new JLabel("Doctor's Last Name:");
    lastNameLabel.setBounds(250, 50, 150, 25);
    lastNameLabel.setForeground(new Color(255, 255, 255));
    frame.add(lastNameLabel);

    phoneLabel = new JLabel("Doctor's Phone:");
    phoneLabel.setBounds(75, 110, 150, 25);
    phoneLabel.setForeground(new Color(255, 255, 255));
    frame.add(phoneLabel);

    emailLabel = new JLabel("Doctor's Email:");
    emailLabel.setBounds(250, 110, 150, 25);
    emailLabel.setForeground(new Color(255, 255, 255));
    frame.add(emailLabel);

    consultingFeeLabel = new JLabel("Consulting Fee:");
    consultingFeeLabel.setBounds(75, 170, 150, 25);
    consultingFeeLabel.setForeground(new Color(255, 255, 255));
    frame.add(consultingFeeLabel);

    doctorIDLabel = new JLabel("Doctor's ID:");
    doctorIDLabel.setBounds(250, 170, 150, 25);
    doctorIDLabel.setForeground(new Color(255, 255, 255));
    frame.add(doctorIDLabel);
  }

  /**
    * public void createButtons() creates buttons for Add Doctor Form UI
  **/
  public void createButtons() {
    button = new JButton("Submit");
    button.setBounds(275, 240, 100, 25);
    button.setBackground(new Color(235, 235, 235));
    button.addActionListener(this);
    button.setFocusable(false);
    frame.add(button);

    backButton = new JButton("Back");
    backButton.setBounds(100, 240, 100, 25);
    backButton.setBackground(new Color(235, 235, 235));
    backButton.addActionListener(this);
    backButton.setFocusable(false);
    frame.add(backButton);
  }

  /**
    * public void actionPerformed(ActionEvent e) adds doctor or go backs to the home page
    * @param ActionEvent e represents when any action occurs in Add Doctor form ex, button clicks
  **/
  public void actionPerformed(ActionEvent e) {

    // If back button is pressed
    if (e.getSource() == backButton) {
      frame.dispose();
      new HospitalWindow(this.user);
    }

    // If Submit button is pressed
    if (e.getSource() == button) {
      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      String phone = phoneText.getText();
      String email = emailText.getText();
      String fee = consultingFeeText.getText();
      String id = doctorIDText.getText();

       // If doctor is already present show alert else add doctor
      if (!system.d1.doctorPresent(id)) {

        if (system.d1.addDoctor(id, firstName, lastName, phone, email, Double.parseDouble(fee))) {
          
          frame.dispose();
          new HospitalWindow(this.user);

        } else {
         
          JOptionPane.showMessageDialog(null, "Doctor ID is taken", "Invalid", JOptionPane.WARNING_MESSAGE);
          doctorIDText.setText("");
        }
      }
    }
  }
}