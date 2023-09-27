//Ami Chauhan, Mel Kartal
package gov.smartCityGUI.education.gui;


import gov.smartCityGUI.education.models.*;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DirectorsPortal implements ActionListener {

  User user;
  EducationSystem system;

  // Main Frame
  JFrame frame;
  
  JLabel label1;
  
  JButton backButton;
  JButton middleSchoolButton;
  JButton highSchoolButton;
  JButton universityButton;
  JButton studentsButton;
  JButton teachersButton;
  
  JPanel schoolPanel = new JPanel();
  JScrollPane scroll = new JScrollPane(schoolPanel);
  // Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  HashMap<String, String> data =new HashMap<String, String>(){
    {
      put("No Data Available", "No Data Available");
    }
  };
  
 

  // ***********************************************************************//

  public DirectorsPortal(User user, EducationSystem system) {
    this.user = user;
    this.system = system;
    createFrames();
    createButtons();
    createLabels();
    createScrollPane(data);

  }

  // ***********************************************************************//

  public void createLabels() {

    
    JLabel label1 = new JLabel("List of Schools & Executives");
    label1.setBounds(380, 35, 250, 40);
    label1.setForeground(new Color(230, 230, 230));
    frame.add(label1);
  }

  // ***********************************************************************//

  public void createFrames() {
    frame = Gui.bigFrame("View Schools");
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
    universityButton.setBounds(90, 210, 175, 35);
    frame.add(universityButton);

    studentsButton = Gui.genericButton("View Students");
    studentsButton.addActionListener(this);
    studentsButton.setBounds(90, 270, 175, 35);
    frame.add(studentsButton);

    teachersButton = Gui.genericButton("View Teachers");
    teachersButton.addActionListener(this);
    teachersButton.setBounds(90, 330, 175, 35);
    frame.add(teachersButton);

  }

  public void createScrollPane(HashMap<String, String> allSchools) {

    schoolPanel.setBackground(new Color(100, 100, 100));
    schoolPanel.setLayout(new BoxLayout(schoolPanel, BoxLayout.Y_AXIS));
    schoolPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel name;
    JLabel principal;
    JLabel space;
    int count = 0;


    for (Map.Entry<String, String> entry : allSchools.entrySet()) {
      name = Gui.scrollLabel(" School: " +  entry.getKey() + " \n");
      principal =  Gui.scrollLabel(" Principal: " +  entry.getValue() + " \n");
      space = Gui.scrollLabel("                      ");
      schoolPanel.add(name);
      schoolPanel.add(principal);
      schoolPanel.add(space);
      count++;
    }
    
    schoolPanel.setPreferredSize(new Dimension(350, count * 100));
    scroll.setBounds(300, 75, 400, 300);
    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    frame.add(scroll);
  }

   public void createScrollPane( ArrayList<String> allStudents, String type) {

    schoolPanel.setBackground(new Color(100, 100, 100));
    schoolPanel.setLayout(new BoxLayout(schoolPanel, BoxLayout.Y_AXIS));
    schoolPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel firstName;
    JLabel ageLabel;
    JLabel gpaLabel;
    JLabel schoolLabel;
     JLabel emailLabel;
    JLabel space;
    int count = 0;
     if(type.equals("student"))
     {
     for (String student : allStudents) {
                  StringTokenizer token = new StringTokenizer(student, ",");
                    String id = token.nextToken();
                    String name = token.nextToken();
                    String lastName = token.nextToken();
                    String age = token.nextToken();
                    String GPA = token.nextToken();
                    String school = token.nextToken();
                    firstName = Gui.scrollLabel(" Name: " + name + " "+lastName+" \n");
                    ageLabel =  Gui.scrollLabel(" Age: " + age + " \n");
                    gpaLabel = Gui.scrollLabel(" GPA: " + GPA + " \n");
                    schoolLabel = Gui.scrollLabel(" School: " + school + " \n");
                    space = Gui.scrollLabel("                      ");
                    schoolPanel.add(firstName);
                    schoolPanel.add(ageLabel);
                    schoolPanel.add(gpaLabel);
                    schoolPanel.add(schoolLabel);
                    schoolPanel.add(space);
              count++;
           }
     }
     if(type.equals("teacher"))
     {
       for (String student : allStudents) {
                  StringTokenizer token = new StringTokenizer(student, ",");
                    String id = token.nextToken();
                    String name = token.nextToken();
                    String lastName = token.nextToken();
                    String email = token.nextToken();
                    String school = token.nextToken();
                    firstName = Gui.scrollLabel(" Name: " + name + " "+lastName+" \n");
                    emailLabel =  Gui.scrollLabel(" Email: " + email + " \n");
                    schoolLabel = Gui.scrollLabel(" School: " + school + " \n");
                    space = Gui.scrollLabel("                      ");
                    schoolPanel.add(firstName);
                    schoolPanel.add(emailLabel);
                    schoolPanel.add(schoolLabel);
                    schoolPanel.add(space);
              count++;
           }
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
      HashMap<String, String> allMiddleSchools = system.s1.viewMiddleSchoolsAndPrincipals();
      createScrollPane(allMiddleSchools);
      schoolPanel.revalidate();
      schoolPanel.repaint();
    }

    if (e.getSource() == highSchoolButton) {

      schoolPanel.removeAll();
      HashMap<String, String> allHighSchools = system.s1.viewHighSchoolsAndPrincipals();
      createScrollPane(allHighSchools);
      schoolPanel.revalidate();
      schoolPanel.repaint();
    }

    if (e.getSource() == universityButton) {

      schoolPanel.removeAll();
      HashMap<String, String> allUniversities = system.s1.viewUniversityPresidents();
      createScrollPane(allUniversities);
      schoolPanel.revalidate();
      schoolPanel.repaint();
    }

    if (e.getSource() ==  studentsButton) {

      schoolPanel.removeAll();
      ArrayList<String> allStudents = system.s1.viewStudents();
      createScrollPane(allStudents,"student");
      schoolPanel.revalidate();
      schoolPanel.repaint();
    }

    if (e.getSource() ==  teachersButton) {

      schoolPanel.removeAll();
       ArrayList<String> allTeachers = system.s1.viewTeachers();
      createScrollPane(allTeachers,"teacher");
      schoolPanel.revalidate();
      schoolPanel.repaint();
    }
  }
}