package gov.smartCityGUI.hospital.gui;

/*
@author Ami Chauhan
Project: Smart City
@date 9/19/2023
I recieved help from: Dylan Moran
*/

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class SubmitReportForm implements ActionListener {

  User user;
  HospitalSystem system;
  JFrame frame;
  JLabel appointmentIDLabel;
  JLabel patientSymptomsLabel;
  JLabel patientTreatmentsLabel;
  JLabel patientChargesLabel;

  JTextField appointmentIDText;
  JTextField patientSymptomsText;
  JTextField patientTreatmentsText;
  JTextField patientChargesText;

  JButton button;
  JButton backButton;

  public SubmitReportForm(User user, HospitalSystem system) {
    this.user = user;
    this.system = system;
    createFrame();
    createTextFields();
    createLabels();
    createButtons();
  }

  public void createFrame() {
    frame = new JFrame("Submit Patient Report");
    frame.setSize(475, 570);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setBackground(new Color(75, 75, 75));
    frame.setVisible(true);
  }

  public void createTextFields() {
    appointmentIDText = new JTextField();
    appointmentIDText.setBounds(75, 80, 150, 25);
    frame.add(appointmentIDText);

    patientSymptomsText = new JTextField();
    patientSymptomsText.setBounds(75, 150, 200, 75);
    frame.add(patientSymptomsText);

    patientTreatmentsText = new JTextField();
    patientTreatmentsText.setBounds(75, 260, 200, 75);
    frame.add(patientTreatmentsText);

    patientChargesText = new JTextField();
    patientChargesText.setBounds(75, 380, 150, 25);
    frame.add(patientChargesText);

  }

  public void createLabels() {

    appointmentIDLabel = new JLabel("Appointment ID:");
    appointmentIDLabel.setBounds(75, 55, 150, 25);
    appointmentIDLabel.setForeground(new Color(255, 255, 255));
    frame.add(appointmentIDLabel);

    patientSymptomsLabel = new JLabel("Patient Symptoms:");
    patientSymptomsLabel.setBounds(75, 95, 150, 75);
    patientSymptomsLabel.setForeground(new Color(255, 255, 255));
    frame.add(patientSymptomsLabel);

    patientTreatmentsLabel = new JLabel("Patient Treatments:");
    patientTreatmentsLabel.setBounds(75, 210, 150, 75);
    patientTreatmentsLabel.setForeground(new Color(255, 255, 255));
    frame.add(patientTreatmentsLabel);

    patientChargesLabel = new JLabel("Bill Amount $:");
    patientChargesLabel.setBounds(75, 350, 150, 25);
    patientChargesLabel.setForeground(new Color(255, 255, 255));
    frame.add(patientChargesLabel);

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

      String appointmentID = appointmentIDText.getText();
      String patientSymptoms = patientSymptomsText.getText();
      String patientTreatments = patientTreatmentsText.getText();
      String patientChargeStr = patientChargesText.getText();
      double patientCharges = Double.parseDouble(patientChargeStr);

      // Add functionality so that only pending reports can be submited
      this.system.b2.submitReport(this.user.getID(), appointmentID, patientSymptoms, patientTreatments, patientCharges);
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);

    }
    if (e.getSource() == backButton) {
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }
  }
}