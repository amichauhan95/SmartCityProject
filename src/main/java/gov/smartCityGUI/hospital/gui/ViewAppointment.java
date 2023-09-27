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
import java.util.ArrayList;
import java.util.List;

import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class ViewAppointment implements ActionListener {

  User user;
  public List<Patient> patients;;

  // Main Frame
  JFrame frame;
  JLabel label1;
  JButton backButton;

  // Scroll Pane
  JScrollPane scroll;

  // ***********************************************************************//

  public ViewAppointment(User user, List<Patient> patient) {
    this.user = user;
    patients = patient;

    createFrames();
    createButtons();
    createLabels();
    createScrollPane();
  }

  // ***********************************************************************//

  public void createLabels() {

    JLabel label1 = new JLabel("My Appointments");
    label1.setBounds(275, 35, 250, 40);
    label1.setForeground(new Color(230, 230, 230));
    frame.add(label1);
  }

  // ***********************************************************************//

  public void createFrames() {
    frame = Gui.bigFrame("Appointments");
  }

  // ***********************************************************************//

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

  }

  // ***********************************************************************//

  /*
  */
  public void createScrollPane() {

    JPanel hospitalPanel = new JPanel();
    hospitalPanel.setBackground(new Color(100, 100, 100));
    hospitalPanel.setLayout(new BoxLayout(hospitalPanel, BoxLayout.Y_AXIS));
    hospitalPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel firstName;
    JLabel lastName;
    JLabel appointmentTime;
    JLabel appointmentID;
    int count = 0;

    for (Patient b : patients) {

      if ((b.getID().equals(this.user.getID())) && (b.getStatus())) {
        firstName = Gui.scrollLabel("First Name: " + b.getFirst());
        lastName = Gui.scrollLabel("Last Name: " + b.getLast());
        appointmentTime = Gui.scrollLabel("Appointment Time: " + b.getAppointmentTime());
        appointmentID = Gui.scrollLabel("Appointment ID: " + b.getAppointmentID());
        JLabel space = Gui.scrollLabel("              ");
        hospitalPanel.add(firstName);
        hospitalPanel.add(lastName);
        hospitalPanel.add(appointmentTime);
        hospitalPanel.add(appointmentID);
        hospitalPanel.add(space);
      }
      count++;
    }
    hospitalPanel.setPreferredSize(new Dimension(350, count * 100));
    JScrollPane scroll = new JScrollPane(hospitalPanel);
    scroll.setBounds(150, 75, 400, 300);
    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    frame.add(scroll);
  } // End createScrollPane() method

  // ***********************************************************************//

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backButton) {
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }

  }
}