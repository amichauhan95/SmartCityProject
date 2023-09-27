
package gov.smartCityGUI.hospital.gui;

/*
@author Ami Chauhan
Project: Smart City
@date 9/19/2023
I recieved help from: Dylan Moran
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class ViewReportDoctor implements ActionListener {

  User user;
  HospitalSystem system;

  // Main Frame
  JFrame frame;
  JLabel label1;
  JLabel label2;
  JLabel label3;
  JButton backButton;
  JButton button1;
  JButton button2;
  JButton button3;

  // Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  JButton backFromInput;

  JPanel reportsPanel = new JPanel();
  JScrollPane scroll = new JScrollPane(reportsPanel);

  public ViewReportDoctor(User user, HospitalSystem system) {
    this.user = user;
    this.system = system;

    // createFrames();
    // createButtons();
    // createLabels();
    // createScrollPane();
    inputFrame();
  }

  public void createLabels() {

    JLabel label1 = new JLabel("View Reports By Date");
    label1.setBounds(275, 35, 250, 40);
    label1.setForeground(new Color(230, 230, 230));
    frame.add(label1);

  }

  public void inputFrame() {
    inputFrame = Gui.smallFrame("View Report");
    inputFrame.setVisible(true);

    input = new JTextField();
    input.setBounds(75, 100, 150, 25);
    inputFrame.add(input);

    prompt = new JLabel("Enter Date");
    prompt.setBounds(75, 40, 200, 25);
    prompt.setForeground(new Color(255, 255, 255));
    inputFrame.add(prompt);

    enterButton = Gui.smallButton("Enter");
    enterButton.addActionListener(this);
    enterButton.setBounds(100, 145, 100, 25);
    enterButton.setBackground(new Color(235, 235, 235));
    inputFrame.add(enterButton);

    backFromInput = Gui.smallButton("Back");
    backFromInput.addActionListener(this);
    backFromInput.setBounds(100, 175, 100, 25);
    backFromInput.setBackground(new Color(235, 235, 235));
    inputFrame.add(backFromInput);
  }

  public void createFrames() {
    frame = Gui.bigFrame("Reports by date");
  }

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

  }

  /*
  */
  public void createScrollPane(ArrayList<Appointment> allAppointments) {

    reportsPanel.setBackground(new Color(100, 100, 100));
    reportsPanel.setLayout(new BoxLayout(reportsPanel, BoxLayout.Y_AXIS));
    reportsPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel appointmentID;
    JLabel symptoms;
    JLabel treatment;
    JLabel bill;
    int count = 0;

    for (Appointment a : allAppointments) {
      appointmentID = Gui.scrollLabel("Appointment ID: " + a.getAppointmentID());
      symptoms = Gui.scrollLabel(" Symptoms: " + a.getReportSymptoms());
      treatment = Gui.scrollLabel(" Treatment: " + a.getReportTreatments());
      bill = Gui.scrollLabel(" Bill: " + a.getPaymentBill());
      JLabel space = Gui.scrollLabel("              ");
      reportsPanel.add(appointmentID);
      reportsPanel.add(symptoms);
      reportsPanel.add(treatment);
      reportsPanel.add(bill);
      reportsPanel.add(space);
      count++;
    }
    reportsPanel.setPreferredSize(new Dimension(350, count * 100));
    scroll.setBounds(150, 75, 400, 300);
    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    frame.add(scroll);
  } // End createScrollPane() method

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backButton) {
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }

    if (e.getSource() == backFromInput) {
      inputFrame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);

    }

    if (e.getSource() == enterButton) {
      String date = input.getText();
      ArrayList<Appointment> allAppointments = system.b2.getPatientsReport(this.user.getID(), date);
      if (allAppointments.isEmpty()) {
        JOptionPane.showMessageDialog(null, "No Data Available", "Notification",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        inputFrame.dispose();
        createFrames();
        createButtons();
        createLabels();
        createScrollPane(allAppointments);
      }
    }

  }
}