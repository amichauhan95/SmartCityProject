package gov.smartCityGUI.education.gui;

import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.education.models.*;
import gov.smartCityGUI.education.service.*;
import gov.smartCityGUI.admin.models.*;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TeachersDetails implements ActionListener {

  User user;
  EducationSystem system;

  // Main Frame
  JFrame frame;

  // Teacher Labels
  JLabel firstNameLabel;
  JLabel lastNameLabel;
  JLabel expertLabel;
  JLabel gradeLevelLabel;
  JLabel yearsLabel;
  JLabel enterSchoolLabel;
  JLabel startworkLabel;

  // Teacher Text Fields
  JTextField firstNameText;
  JTextField lastNameText;
  JTextField expertText;
  JTextField gradeLevelText;
  JTextField yearsText;
  JTextField enterSchoolText;
  JTextField startworkText;

  JLabel prompt;
  JTextField input;
  JButton button;
  JButton backButton;

  public TeachersDetails(User user, EducationSystem education) {
    this.user = user;
    this.system = education;
    createFrame();
    createTextFields();
    createLabels();
    createButtons();
  }

  public void createFrame() {
    frame = new JFrame("Enter Details");
    frame.setSize(475, 570);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setBackground(new Color(75, 75, 75));
    frame.setVisible(true);
  }

  public void createTextFields() {
    firstNameText = new JTextField();
    firstNameText.setBounds(75, 140, 150, 25);
    frame.add(firstNameText);

    lastNameText = new JTextField();
    lastNameText.setBounds(250, 140, 150, 25);
    frame.add(lastNameText);

    gradeLevelText = new JTextField();
    gradeLevelText.setBounds(75, 200, 150, 25);
    frame.add(gradeLevelText);

    expertText = new JTextField();
    expertText.setBounds(250, 200, 150, 25);
    frame.add(expertText);

    enterSchoolText = new JTextField();
    enterSchoolText.setBounds(75, 260, 150, 25);
    frame.add(enterSchoolText);

    startworkText = new JTextField();
    startworkText.setBounds(250, 260, 150, 25);
    frame.add(startworkText);
  }

  public void createLabels() {
    firstNameLabel = new JLabel("First Name: ");
    firstNameLabel.setBounds(75, 115, 150, 25);
    firstNameLabel.setForeground(new Color(230, 230, 230));
    frame.add(firstNameLabel);

    JLabel lastNameLabel = new JLabel("Last Name: ");
    lastNameLabel.setBounds(250, 115, 150, 25);
    lastNameLabel.setForeground(new Color(230, 230, 230));
    frame.add(lastNameLabel);

    JLabel gradeLevelLabel = new JLabel("Grade Level: ");
    gradeLevelLabel.setBounds(75, 175, 150, 25);
    gradeLevelLabel.setForeground(new Color(230, 230, 230));
    frame.add(gradeLevelLabel);

    JLabel expertLabel = new JLabel("Expertise: ");
    expertLabel.setBounds(250, 175, 150, 25);
    expertLabel.setForeground(new Color(230, 230, 230));
    frame.add(expertLabel);

    JLabel enterSchoolLabel = new JLabel("School Name: ");
    enterSchoolLabel.setBounds(75, 235, 150, 25);
    enterSchoolLabel.setForeground(new Color(230, 230, 230));
    frame.add(enterSchoolLabel);

    JLabel startworkLabel = new JLabel("Work Start Date: ");
    startworkLabel.setBounds(250, 235, 150, 25);
    startworkLabel.setForeground(new Color(230, 230, 230));
    frame.add(startworkLabel);
  }

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

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button) {

      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      String expert = expertText.getText();
      String gradeLevel = gradeLevelText.getText();
      String enterSchool = enterSchoolText.getText();
      String startwork = startworkText.getText();

      Teacher newTeacher = new Teacher();
      newTeacher.setFirstName(firstName);
      newTeacher.setLastName(lastName);
      newTeacher.setDepartment(expert);
      newTeacher.setgradeLevel(gradeLevel);
      newTeacher.setEmail();
      newTeacher.setselectedSchool(enterSchool);
      newTeacher.setJoiningDate(startwork);
      newTeacher.writeToFile();

      frame.dispose();
      TeachersPortal view = new TeachersPortal(this.user, this.system);

    }

    if (e.getSource() == backButton) {
      frame.dispose();
      TeachersPortal view = new TeachersPortal(this.user, this.system);
    }
  }
}