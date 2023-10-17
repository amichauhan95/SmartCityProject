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

public class AddHospitalForm implements ActionListener {

  // Resources
  User user;
  HospitalSystem system;

  // UI Components
  JFrame frame;
  JLabel nameLabel;
  JLabel addressLabel;
  JLabel phoneLabel;
  JLabel emailLabel;
  JLabel authCodeLabel;
  JTextField nameText;
  JTextField addressText;
  JTextField phoneText;
  JTextField emailText;
  JTextField authCodeText;
  JButton button;
  JButton backButton;

  /**
    * public void AddHospitalForm does ...
    * @param User user represents ...
    * @param HospitalSystem system represents ...
  **/
  public AddHospitalForm(User user, HospitalSystem system) {
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
    frame = new JFrame("Add Hospital");
    frame.setSize(475, 315);
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
    nameText = new JTextField();
    nameText.setBounds(75, 80, 150, 25);
    frame.add(nameText);

    addressText = new JTextField();
    addressText.setBounds(250, 80, 150, 25);
    frame.add(addressText);

    phoneText = new JTextField();
    phoneText.setBounds(75, 140, 150, 25);
    frame.add(phoneText);

    emailText = new JTextField();
    emailText.setBounds(250, 140, 150, 25);
    frame.add(emailText);

    authCodeText = new JTextField();
    authCodeText.setBounds(162, 200, 150, 25);
    frame.add(authCodeText);
  }

  /**
    * public void createLabels() does ...
  **/
  public void createLabels() {

    JLabel title = Gui.whiteLabel("Add Hospital");
    title.setBounds(200, 10, 300, 25);
    title.setForeground(Color.white);
    frame.add(title);

    nameLabel = new JLabel("Hospital Name:");
    nameLabel.setBounds(75, 55, 150, 25);
    nameLabel.setForeground(new Color(255, 255, 255));
    frame.add(nameLabel);

    addressLabel = new JLabel("Hospital Address:");
    addressLabel.setBounds(250, 55, 150, 25);
    addressLabel.setForeground(new Color(255, 255, 255));
    frame.add(addressLabel);

    phoneLabel = new JLabel("Hospital Phone:");
    phoneLabel.setBounds(75, 115, 150, 25);
    phoneLabel.setForeground(new Color(255, 255, 255));
    frame.add(phoneLabel);

    emailLabel = new JLabel("Hospital Email:");
    emailLabel.setBounds(250, 115, 150, 25);
    emailLabel.setForeground(new Color(255, 255, 255));
    frame.add(emailLabel);

    authCodeLabel = new JLabel("Authorization Code");
    authCodeLabel.setBounds(162, 175, 150, 25);
    authCodeLabel.setForeground(Color.white);
    frame.add(authCodeLabel);
  }

  /**
    * public void createButtons does ...
  **/
  public void createButtons() {
    button = new JButton("Confirm");
    button.setBounds(275, 250, 100, 25);
    button.setBackground(new Color(235, 235, 235));
    button.addActionListener(this);
    button.setFocusable(false);
    frame.add(button);

    backButton = new JButton("Back");
    backButton.setBounds(100, 250, 100, 25);
    backButton.setBackground(new Color(235, 235, 235));
    backButton.addActionListener(this);
    backButton.setFocusable(false);
    frame.add(backButton);
  }

  /**
    * public void actionPerformed(ActionEvent e) does ...
    * @param ActionEvent e represents ...
  **/
  public void actionPerformed(ActionEvent e) {

    // If confirm button is pressed
    if (e.getSource() == button) {

      String name = nameText.getText();
      String address = addressText.getText();
      String phone = phoneText.getText();
      String email = emailText.getText();
      String authCode = authCodeText.getText();

      if (authCode.equals("ADMIN1234")) {

        if (system.h1.addHospitals(name, address, phone, email)) {
          frame.dispose();
          JOptionPane.showMessageDialog(null, "Hospital successfully added", "Success!",
              JOptionPane.INFORMATION_MESSAGE);
          HospitalWindow hospital = new HospitalWindow(this.user);
        }
      } else {
        JOptionPane.showMessageDialog(null, "Authorization code invalid", "Invalid", JOptionPane.WARNING_MESSAGE);
        authCodeText.setText("");
      }

    }

    // If back button is pressed
    if (e.getSource() == backButton) {
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }
  }
}