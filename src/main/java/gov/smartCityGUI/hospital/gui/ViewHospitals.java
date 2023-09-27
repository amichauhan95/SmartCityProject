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

import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class ViewHospitals implements ActionListener {

  User user;
  HospitalSystem system;

  // Main Frame
  JFrame frame;
  JLabel label1;
  JButton backButton;

  // Scroll Pane
  JScrollPane scroll;

  // ***********************************************************************//

  public ViewHospitals(User user, HospitalSystem system) {
    this.user = user;
    this.system = system;

    createFrames();
    createButtons();
    createLabels();
    createScrollPane();
  }

  // ***********************************************************************//

  public void createLabels() {

    JLabel label1 = new JLabel("All Hospitals in Albany");
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

  /*
  */
  public void createScrollPane() {

    ArrayList<Hospital> allHospitals = system.h1.getHospitalList();

    JPanel hospitalPanel = new JPanel();
    hospitalPanel.setBackground(new Color(100, 100, 100));
    hospitalPanel.setLayout(new BoxLayout(hospitalPanel, BoxLayout.Y_AXIS));
    hospitalPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel name;
    JLabel addr;
    JLabel email;
    JLabel phone;
    int count = 0;

    for (Hospital hospital : allHospitals) {
      name = Gui.scrollLabel("Hospital Name: " + hospital.getHospitalName());
      addr = Gui.scrollLabel("    Address: " + hospital.getHospitalAddress());
      email = Gui.scrollLabel("    Email: " + hospital.getHospitalEmail());
      phone = Gui.scrollLabel("    Phone: " + hospital.getHospitalPhone());
      JLabel space = Gui.scrollLabel("              ");
      hospitalPanel.add(name);
      hospitalPanel.add(addr);
      hospitalPanel.add(email);
      hospitalPanel.add(phone);
      hospitalPanel.add(space);
      count++;

    }
    hospitalPanel.setPreferredSize(new Dimension(350, count * 100));
    JScrollPane scroll = new JScrollPane(hospitalPanel);
    scroll.setBounds(150, 75, 400, 300);
    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    system.h1.hospitals.clear();
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