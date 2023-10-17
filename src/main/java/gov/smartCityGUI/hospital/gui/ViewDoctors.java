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

public class ViewDoctors implements ActionListener {

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

  // Scroll Pane
  ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
  JScrollPane scroll;

  // ***********************************************************************//

  public ViewDoctors(User user, HospitalSystem system) {
    this.user = user;
    this.system = system;

    createFrames();
    createButtons();
    createLabels();
    createScrollPane();
  }

  // ***********************************************************************//

  public void createLabels() {

    JLabel label1 = new JLabel("All Doctors");
    label1.setBounds(275, 35, 250, 40);
    label1.setForeground(new Color(230, 230, 230));
    frame.add(label1);
  }

  // ***********************************************************************//

  public void createFrames() {
    frame = Gui.bigFrame("View Hospitals");
  }

  // ***********************************************************************//

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

  }

  // ***********************************************************************//

  public void createScrollPane() {

    List<Doctor> allDoctors = system.d1.getDoctorList();

    JPanel doctorPanel = new JPanel();
    doctorPanel.setBackground(new Color(100, 100, 100));
    doctorPanel.setLayout(new BoxLayout(doctorPanel, BoxLayout.Y_AXIS));
    doctorPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel name;
    JLabel id;
    JLabel email;
    JLabel phone;
    JLabel availability;
    int count = 0;

    for (Doctor d : allDoctors) {
      id = Gui.scrollLabel("Doctor ID: " + d.getUserID());
      name = Gui.scrollLabel("    Doctor Name: " + d.getFirstName() + " " + d.getLastName());
      phone = Gui.scrollLabel("    Doctor Phone: " + d.getPhone());
      email = Gui.scrollLabel("    Doctor Email: " + d.getEmail());
      availability = Gui.scrollLabel("    Availability Status: " + d.getAvailiblity());

      JLabel space = Gui.scrollLabel("              ");
      doctorPanel.add(id);
      doctorPanel.add(name);
      doctorPanel.add(phone);
      doctorPanel.add(email);
      doctorPanel.add(availability);
      doctorPanel.add(space);
      count++;

    }
    doctorPanel.setPreferredSize(new Dimension(350, count * 100));
    JScrollPane scroll = new JScrollPane(doctorPanel);
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