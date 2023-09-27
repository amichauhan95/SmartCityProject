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

import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class CheckInForm implements ActionListener {

  // Resources
  User user;
  HospitalSystem system;

  // UI Components
  JFrame frame;
  JLabel firstNameLabel;
  JLabel lastNameLabel;
  JLabel insuranceIDLabel;
  JLabel appointmentIDLabel;
  JTextField firstNameText;
  JTextField lastNameText;
  JTextField insuranceIDText;
  JTextField appointmentIDText;
  JButton button;
  JButton backButton;

  public CheckInForm(User user, HospitalSystem system) {
    this.user = user;
    this.system = system;
    createFrame();
    createTextFields();
    createLabels();
    createButtons();
  }

  public void createFrame() {
    frame = new JFrame("Check-in");
    frame.setSize(475, 315);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setBackground(new Color(75, 75, 75));
    frame.setVisible(true);
  }

  public void createTextFields() {
    firstNameText = new JTextField();
    firstNameText.setBounds(75, 80, 150, 25);
    frame.add(firstNameText);

    lastNameText = new JTextField();
    lastNameText.setBounds(250, 80, 150, 25);
    frame.add(lastNameText);

    insuranceIDText = new JTextField();
    insuranceIDText.setBounds(75, 140, 150, 25);
    frame.add(insuranceIDText);

    appointmentIDText = new JTextField();
    appointmentIDText.setBounds(250, 140, 150, 25);
    frame.add(appointmentIDText);
  }

  public void createLabels() {

    JLabel disclaimer = Gui.whiteLabel("Check-In");
    disclaimer.setBounds(200, 10, 300, 25);
    disclaimer.setForeground(Color.white);
    frame.add(disclaimer);

    firstNameLabel = new JLabel("First Name:");
    firstNameLabel.setBounds(75, 55, 150, 25);
    firstNameLabel.setForeground(new Color(255, 255, 255));
    frame.add(firstNameLabel);

    lastNameLabel = new JLabel("Last Name:");
    lastNameLabel.setBounds(250, 55, 150, 25);
    lastNameLabel.setForeground(new Color(255, 255, 255));
    frame.add(lastNameLabel);

    insuranceIDLabel = new JLabel("Insurance ID:");
    insuranceIDLabel.setBounds(75, 115, 150, 25);
    insuranceIDLabel.setForeground(new Color(255, 255, 255));
    frame.add(insuranceIDLabel);

    appointmentIDLabel = new JLabel("Appointment ID:");
    appointmentIDLabel.setBounds(250, 115, 150, 25);
    appointmentIDLabel.setForeground(new Color(255, 255, 255));
    frame.add(appointmentIDLabel);
  }

  public void createButtons() {
    button = new JButton("Confirm");
    button.setBounds(275, 185, 100, 25);
    button.setBackground(new Color(235, 235, 235));
    button.addActionListener(this);
    button.setFocusable(false);
    frame.add(button);

    backButton = new JButton("Back");
    backButton.setBounds(100, 185, 100, 25);
    backButton.setBackground(new Color(235, 235, 235));
    backButton.addActionListener(this);
    backButton.setFocusable(false);
    frame.add(backButton);
  }

  public void actionPerformed(ActionEvent e) {

    // If confirm button is pressed
    if (e.getSource() == button) {

      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      String insuranceID = insuranceIDText.getText();
      String appointmentID = appointmentIDText.getText();

      // Implement necessary methods
      if (system.b2.checkInAppointment(firstName, lastName, insuranceID, appointmentID)) {
        JOptionPane.showMessageDialog(null,
            "Thank you for Checking In. Your appointment ID is " + appointmentID, "Check-In Successful",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null,
            "Sorry! Appointments are full for that date.", "Appointment Date Not Available",
            JOptionPane.WARNING_MESSAGE);
      }
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);

      // checkInAppointment has to be modified to get rid of scenners and pass the
      // Strings above as parameters

    }

    // If back button is pressed
    if (e.getSource() == backButton) {
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }
  }
}