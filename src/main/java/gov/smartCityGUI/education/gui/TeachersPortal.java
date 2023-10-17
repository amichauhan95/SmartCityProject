/**
 * Team Member(s) working on this class: Ami Chauhan, Melih Kartal
 * Project: Smart City
 * Author: Ami Chauhan
 *
 * Description:
 * This class represents the teacher portal user interface. It provides options for teachers to navigate,
 * including registering as a teacher, calculating paychecks, and viewing school lists. Users can click on
 * these options to access specific functionalities.
 */
package gov.smartCityGUI.education.gui;

import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class TeachersPortal implements ActionListener {

  User user;
  EducationSystem system;

  // Main Frame
  JFrame frame;

  JButton backButton;
  JButton teacherDetails;
  JButton payCalculator;
  JButton schoolList;

  JLabel prompt;
  JTextField input;
  JButton enterButton;

  // ***********************************************************************//

  public TeachersPortal(User user, EducationSystem system) {
    this.user = user;
    this.system = system;

    createFrames();
    createButtons();
    createLabels();

  }

  // ***********************************************************************//

  public void createLabels() {

    JLabel label1 = new JLabel("Teachers Portal");
    label1.setBounds(300, 100, 175, 35);
    label1.setForeground(new Color(230, 230, 230));
    frame.add(label1);
  }

  // ***********************************************************************//

  public void createFrames() {
    frame = Gui.bigFrame("Teachers Portal");

  }

  // ***********************************************************************//

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    teacherDetails = Gui.genericButton("Register as a Teacher");
    teacherDetails.addActionListener(this);
    teacherDetails.setBounds(250, 140, 220, 35);
    frame.add(teacherDetails);

    payCalculator = Gui.genericButton("Pay Check Calculator");
    payCalculator.addActionListener(this);
    payCalculator.setBounds(250, 210, 220, 35);
    frame.add(payCalculator);

    schoolList = Gui.genericButton("View School List");
    schoolList.addActionListener(this);
    schoolList.setBounds(250, 280, 220, 35);
    frame.add(schoolList);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backButton) {
      frame.dispose();
      EducationWindow education = new EducationWindow(this.user);
    }
    if (e.getSource() == teacherDetails) {

      frame.dispose();
      TeachersDetails view = new TeachersDetails(this.user, this.system);

    }

    if (e.getSource() == payCalculator) {

      frame.dispose();
      PaymentDetails view = new PaymentDetails(this.user, this.system);

    }
    if (e.getSource() == schoolList) {
      frame.dispose();
      ViewAllSchools view = new ViewAllSchools(this.user, this.system);

    }
  }
}