package gov.smartCityGUI.ems;

/**
  * Team Member(s) working on this class: Keith Austin
  * Project: Smart City
  * @author: Keith Austin
  * I received help from everyone on my team
**/

import java.awt.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.ems.pages.*;
import gov.smartCityGUI.utilities.*;
import gov.smartCityGUI.menu.Menu;

public class ReportWindow implements ActionListener {
  JFrame frame;
  JTextArea rTextArea;
  JTextField ftext, ltext, etext;
  JButton backButton, submitButton;
  JLabel title, flabel, llabel, elabel, rlabel;
  User user;
  FileWriter fw = null;

  /**
    * public ReportWindow(User user) calls the form method that displays the fill ins for the user 
    * @param User user gets the current instance of the user
  **/
  public ReportWindow(User user) {
	  this.user = user;
    filloutForm();
  }

  /**
    * public void filloutForm() displays a fill out form for the user
  **/
  public void filloutForm() {

    // main frame
    frame = new JFrame("EMS | Report");
    frame.setSize(700, 500);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setBackground(new Color(75,75,75));
    frame.setVisible(true);

    // title for main frame
    title = new JLabel("File a Report");
    title.setFont(new Font("Arial", Font.PLAIN, 30));
    title.setForeground(Color.white);
    title.setSize(300, 30);
    title.setLocation(275, 30);
    frame.add(title);

    flabel = new JLabel("First Name: ");
    flabel.setFont(new Font("Arial", Font.PLAIN, 15));
    flabel.setForeground(Color.white);
    flabel.setSize(100, 20);
    flabel.setLocation(100, 100);
    frame.add(flabel);
    
    llabel = new JLabel("Last Name: ");
    llabel.setFont(new Font("Arial", Font.PLAIN, 15));
    llabel.setForeground(Color.white);
    llabel.setSize(100, 20);
    llabel.setLocation(100, 150);
    frame.add(llabel);
    
    elabel = new JLabel("Email: ");
    elabel.setFont(new Font("Arial", Font.PLAIN, 15));
    elabel.setForeground(Color.white);
    elabel.setSize(100, 20);
    elabel.setLocation(100, 200);
    frame.add(elabel);
    
    rlabel = new JLabel("Make your report here: ");
    rlabel.setFont(new Font("Arial", Font.PLAIN, 15));
    rlabel.setForeground(Color.white);
    rlabel.setSize(200, 35);
    rlabel.setLocation(100, 230);
    frame.add(rlabel);

    rTextArea = new JTextArea();
    rTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
    rTextArea.setSize(500, 150);
    rTextArea.setLocation(100, 275);
    rTextArea.setLineWrap(true);
    frame.add(rTextArea);

    ftext = new JTextField();
    ftext.setFont(new Font("Arial", Font.PLAIN, 15));
    ftext.setBackground(Color.white);
    ftext.setSize(190, 20);
    ftext.setLocation(200, 100);
    frame.add(ftext);

    ltext = new JTextField();
    ltext.setFont(new Font("Arial", Font.PLAIN, 15));
    ltext.setSize(190, 20);
    ltext.setLocation(200, 150);
    frame.add(ltext);

    etext = new JTextField();
    etext.setFont(new Font("Arial", Font.PLAIN, 15));
    etext.setSize(190, 20);
    etext.setLocation(200, 200);
    frame.add(etext);

    backButton = new JButton("Back");
    backButton.setFont(new Font("Arial", Font.PLAIN, 15));
    backButton.setBackground(Color.white);
    backButton.setSize(100, 40);
    backButton.setLocation(200, 435);
    backButton.addActionListener(this);
    frame.add(backButton);
    
    submitButton = new JButton("Submit");
    submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
    submitButton.setBackground(Color.white);
    submitButton.setSize(100, 40);
    submitButton.setLocation(400, 435);
    submitButton.addActionListener(this);
    frame.add(submitButton);
    
  }

  /**
    * public void afterSubmitClicked() stores the user's data into a list and sends the data to a text file
    * @throws Exception if the file isn't written to properly
  **/
  public void afterSubmitClicked() throws Exception {
    
    String[] userData = {
      ftext.getText(),
      ltext.getText(),
      etext.getText(),
      rTextArea.getText()
    };

    // writes to the user's data to the report file
    try {
      fw = new FileWriter("src/main/java/gov/smartCityGUI/ems/data/reports.txt", true);
      for (String s : userData) {
        fw.write(s + ", ");
      }
      fw.flush();
      fw.close();
      // catches an exception error if the file isn't written to properly
    } catch (Exception e) {
      System.out.println("An error has occurred");
      e.printStackTrace();
    }
  }

  /**
    * public void actionPerformed(ActionEvent e) opens a window based on the user's selection
    * @param ActionEvent e gets the current action instance
  **/
  public void actionPerformed(ActionEvent e) {
    // 
    if(e.getSource() == backButton){
      frame.dispose();
      EMSWindow ems = new EMSWindow(this.user);
    }

    //
    if (e.getSource() == submitButton) {
      try {
        frame.dispose();
        afterSubmitClicked();
        ReportWindow reportWindow = new ReportWindow(this.user);
      } catch (Exception error) {
        System.out.println("An error has occurred");
        error.printStackTrace();
      }
    }
  }
}