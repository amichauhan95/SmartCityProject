package gov.smartCityGUI.education.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;

public class ViewAllSchools implements ActionListener {

  User user;
  EducationSystem system;

  // Main Frame
  JFrame frame;
  JLabel label1;
  JLabel label2;
  JLabel label3;
  JButton backButton;
  JButton middleSchoolButton;
  JButton highSchoolButton;
  JButton universityButton;
  JButton button1;
  JButton button2;
  JButton button3;

  JPanel schoolPanel = new JPanel();
  JScrollPane scroll = new JScrollPane(schoolPanel);
  // Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  String[] data = { "No Data Available" };
  // Scroll Pane
  ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();

  // ***********************************************************************//

  public ViewAllSchools(User user, EducationSystem system) {
    this.user = user;
    this.system = system;

    createFrames();
    createButtons();
    createLabels();
    createScrollPane(data);

  }

  // ***********************************************************************//

  public void createLabels() {

    JLabel label1 = new JLabel("List of Schools");
    label1.setBounds(380, 35, 250, 40);
    label1.setForeground(new Color(230, 230, 230));
    frame.add(label1);
  }

  // ***********************************************************************//

  public void createFrames() {
    frame = Gui.bigFrame("View Middle Schools");
  }

  // ***********************************************************************//

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    middleSchoolButton = Gui.genericButton("View Middle Schools");
    middleSchoolButton.addActionListener(this);
    middleSchoolButton.setBounds(90, 90, 175, 35);
    frame.add(middleSchoolButton);

    highSchoolButton = Gui.genericButton("View High Schools");
    highSchoolButton.addActionListener(this);
    highSchoolButton.setBounds(90, 150, 175, 35);
    frame.add(highSchoolButton);

    universityButton = Gui.genericButton("View Universities");
    universityButton.addActionListener(this);
    universityButton.setBounds(90, 220, 175, 35);
    frame.add(universityButton);

  }

  public void createScrollPane(String[] allSchools) {

    schoolPanel.setBackground(new Color(100, 100, 100));
    schoolPanel.setLayout(new BoxLayout(schoolPanel, BoxLayout.Y_AXIS));
    schoolPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel name;
    int count = 0;

    for (String schools : allSchools) {
      name = Gui.scrollLabel(" " + schools + " \n");
      schoolPanel.add(name);
      count++;
    }

    schoolPanel.setPreferredSize(new Dimension(350, count * 100));
    scroll.setBounds(300, 75, 400, 300);
    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    frame.add(scroll);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backButton) {
      frame.dispose();
      EducationWindow hospital = new EducationWindow(this.user);
    }
    if (e.getSource() == middleSchoolButton) {

      schoolPanel.removeAll();
      String[] allMiddleSchools = system.s.getMiddleSchools();
      createScrollPane(allMiddleSchools);
      schoolPanel.revalidate();
      schoolPanel.repaint();
    }

    if (e.getSource() == highSchoolButton) {

      schoolPanel.removeAll();
      String[] allHighSchools = system.s.getHighSchools();
      createScrollPane(allHighSchools);
      schoolPanel.revalidate();
      schoolPanel.repaint();
    }

    if (e.getSource() == universityButton) {

      schoolPanel.removeAll();
      String[] allUniversities = system.s.getUniversities();
      createScrollPane(allUniversities);
      schoolPanel.revalidate();
      schoolPanel.repaint();
    }
  }
}