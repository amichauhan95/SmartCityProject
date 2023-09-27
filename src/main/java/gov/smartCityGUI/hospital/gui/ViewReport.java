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

public class ViewReport implements ActionListener {

  User user;
  HospitalSystem system;

  // Main Frame
  JFrame frame;
  JLabel label1;
  JButton backButton;
  JButton button1;

  // Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  JButton backFromInput;

	public ViewReport(User user, HospitalSystem system) {
    	this.user = user;
    	this.system = system;
    	inputFrame();
  	}

  public void createLabels() {

    JLabel label1 = new JLabel("Appointment Report");
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

    prompt = new JLabel("Enter Appointment ID");
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
    frame = Gui.bigFrame("View Reports");
  }

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

  }

  /*
  */
  public void createScrollPane(String[] parts) {

    JPanel reportsPanel = new JPanel();
    reportsPanel.setBackground(new Color(100, 100, 100));
    reportsPanel.setLayout(new BoxLayout(reportsPanel, BoxLayout.Y_AXIS));
    reportsPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel appointmentID;
    JLabel symptoms;
    JLabel treatment;
    JLabel bill;
    int count = 0;

    appointmentID = Gui.scrollLabel("Appointment ID: " + parts[0]);
    symptoms = Gui.scrollLabel("Symptoms: " + parts[1]);
    treatment = Gui.scrollLabel("Treatment: " + parts[2]);
    bill = Gui.scrollLabel("Bill: " + parts[3]);
    JLabel space = Gui.scrollLabel("              ");
    reportsPanel.add(appointmentID);
    reportsPanel.add(symptoms);
    reportsPanel.add(treatment);
    reportsPanel.add(bill);
    reportsPanel.add(space);

    reportsPanel.setPreferredSize(new Dimension(350, 100));
    JScrollPane scroll = new JScrollPane(reportsPanel);
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
      String appointmentID = input.getText();

      String parts[] = system.b2.viewPatientsReport(appointmentID);
      if (parts == null || parts[2].equals("N/A")) {
        JOptionPane.showMessageDialog(null, "There is no report filed under that appointment ID", "Notification",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        inputFrame.dispose();
        createFrames();
        createButtons();
        createLabels();
        createScrollPane(parts);
      }
    }

  }
}