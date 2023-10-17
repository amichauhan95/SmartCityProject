package gov.smartCityGUI.hospital.gui;

/*
@author Dylan Moran
Project: Smart City
@date 9/19/2023
I recieved help from: Ami Chauhan
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class BookApptForm implements ActionListener {

  User user;
  HospitalSystem system;
  JFrame frame;
  JLabel firstNameLabel;
  JLabel lastNameLabel;
  JLabel emailLabel;
  JLabel phoneLabel;
  JLabel birthdayLabel;
  JLabel addressLabel;
  JLabel insuranceIDLabel;
  JTextField firstNameText;
  JTextField lastNameText;
  JTextField emailText;
  JTextField phoneText;
  JTextField birthdayText;
  JTextField addressText;
  JTextField insuranceText;
  JTextField dateText;
  JTextArea symptomsText;
  JButton button;
  JButton backButton;

  /**
    * public BookApptForm(User user, HospitalSystem system) does ...
    * @param User user represents ...
    * @param HospitalSystem system represents ...
  **/
  public BookApptForm(User user, HospitalSystem system) {
    this.user = user;
    this.system = system;
    createFrame();
    createTextFields();
    createLabels();
    createButtons();
  }

  /**
    * public void createFrame() does ...
  **/
  public void createFrame() {
    frame = new JFrame("Book Appointment");
    frame.setSize(475, 570);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setBackground(new Color(75, 75, 75));
    frame.setVisible(true);
  }

  /**
    * public void createTextFields() does ...
  **/
  public void createTextFields() {
    firstNameText = new JTextField();
    firstNameText.setBounds(75, 140, 150, 25);
    frame.add(firstNameText);

    lastNameText = new JTextField();
    lastNameText.setBounds(250, 140, 150, 25);
    frame.add(lastNameText);

    emailText = new JTextField();
    emailText.setBounds(75, 200, 150, 25);
    frame.add(emailText);

    phoneText = new JTextField();
    phoneText.setBounds(250, 200, 150, 25);
    frame.add(phoneText);

    birthdayText = new JTextField();
    birthdayText.setBounds(75, 260, 150, 25);
    frame.add(birthdayText);

    addressText = new JTextField();
    addressText.setBounds(250, 260, 150, 25);
    frame.add(addressText);

    insuranceText = new JTextField();
    insuranceText.setBounds(75, 320, 150, 25);
    frame.add(insuranceText);

    dateText = new JTextField();
    dateText.setBounds(250, 320, 150, 25);
    dateText.setText("yyyy/mm/dd");
    frame.add(dateText);

    symptomsText = new JTextArea();
    symptomsText.setBounds(75, 380, 325, 50);
    symptomsText.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.darkGray));
    frame.add(symptomsText);
  }

  /**
    * public void createLabels() does ...
  **/
  public void createLabels() {

    JLabel disclaimer = Gui.whiteLabel(
        "<html>Walkin Appointments<br/>Only from 8:00 AM to 4:00 PM everyday<br/>Address: Albany Med 1, 1220 Lawerence Ave ,Albany NY 12208<br/>Phone: (999)-999-9911</html>");
    disclaimer.setBounds(75, 25, 300, 85);
    disclaimer.setForeground(Color.white);
    frame.add(disclaimer);

    firstNameLabel = new JLabel("First Name:");
    firstNameLabel.setBounds(75, 115, 150, 25);
    firstNameLabel.setForeground(new Color(255, 255, 255));
    frame.add(firstNameLabel);

    lastNameLabel = new JLabel("Last Name:");
    lastNameLabel.setBounds(250, 115, 150, 25);
    lastNameLabel.setForeground(new Color(255, 255, 255));
    frame.add(lastNameLabel);

    emailLabel = new JLabel("Email:");
    emailLabel.setBounds(75, 175, 150, 25);
    emailLabel.setForeground(new Color(255, 255, 255));
    frame.add(emailLabel);

    phoneLabel = new JLabel("Phone Number:");
    phoneLabel.setBounds(250, 175, 150, 25);
    phoneLabel.setForeground(new Color(255, 255, 255));
    frame.add(phoneLabel);

    birthdayLabel = new JLabel("Birth Date:");
    birthdayLabel.setBounds(75, 235, 150, 25);
    birthdayLabel.setForeground(new Color(255, 255, 255));
    frame.add(birthdayLabel);

    addressLabel = new JLabel("Address:");
    addressLabel.setBounds(250, 235, 150, 25);
    addressLabel.setForeground(new Color(255, 255, 255));
    frame.add(addressLabel);

    insuranceIDLabel = new JLabel("Insurance ID #:");
    insuranceIDLabel.setBounds(75, 295, 150, 25);
    insuranceIDLabel.setForeground(new Color(255, 255, 255));
    frame.add(insuranceIDLabel);

    JLabel dateLabel = new JLabel("Date of Appt.");
    dateLabel.setBounds(250, 295, 150, 25);
    dateLabel.setForeground(new Color(255, 255, 255));
    frame.add(dateLabel);

    JLabel symptomsLabel = new JLabel("Symptoms:");
    symptomsLabel.setBounds(75, 355, 150, 25);
    symptomsLabel.setForeground(new Color(255, 255, 255));
    frame.add(symptomsLabel);
  }

  /**
    * public void createButtons() does ...
  **/
  public void createButtons() {
    button = new JButton("Submit");
    button.setBounds(275, 495, 100, 25);
    button.setBackground(new Color(235, 235, 235));
    button.addActionListener(this);
    button.setFocusable(false);
    frame.add(button);

    backButton = new JButton("Back");
    backButton.setBounds(100, 495, 100, 25);
    backButton.setBackground(new Color(235, 235, 235));
    backButton.addActionListener(this);
    backButton.setFocusable(false);
    frame.add(backButton);
  }

  /**
    * public void actionPerformed(ActionEvent e) does ...
    * @param ActionEEvent e represents ...
  **/
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button) {

      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      String email = emailText.getText();
      String phone = phoneText.getText();
      String bday = birthdayText.getText();
      String address = addressText.getText();
      String insuranceID = insuranceText.getText();
      String date = dateText.getText();
      String symptoms = symptomsText.getText();

      if (this.system.b1.addAppointment(this.user.getID(), firstName, lastName, email, phone, bday, address,
          insuranceID, date, symptoms)) {
        frame.dispose();
        HospitalWindow hospital = new HospitalWindow(this.user);

      } 

    }
    if (e.getSource() == backButton) {
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }
  }
}