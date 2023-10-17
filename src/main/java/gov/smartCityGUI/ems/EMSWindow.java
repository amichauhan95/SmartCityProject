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

public class EMSWindow implements ActionListener{

  User user;
  JFrame frame;
  JButton backButton;
  JButton button1;
  JButton button2;
  JButton button3;

  /**
    * public EMSWindow(User user) calls the current instance of the class 
    * @param User user represents the current user that's logged in
  **/
  public EMSWindow(User user){
    this.user = user;
    
    createFrames();
    createButtons();
  }

  /**
    * public void createFrames() creates the main frame for the EMS System
  **/
  public void createFrames(){
    frame = Gui.bigFrame("EMS");
  }

  /**
    * public void createButtons() creates the button layout for each button
  **/
  public void createButtons(){

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    button1 = Gui.genericButton("View Contacts");
    button1.addActionListener(this);
    button1.setBounds(175, 80, 250, 35);
    frame.add(button1);

    button2 = Gui.genericButton("View Job Opportunities");
    button2.addActionListener(this);
    button2.setBounds(175, 130,250,35);
    frame.add(button2);

    button3 = Gui.genericButton("File a Report");
    button3.addActionListener(this);
    button3.setBounds(175, 180, 250, 35);
    frame.add(button3);
    
  }

  /**
    * public void actionPerformed(ActionEvent e) opens a window based on the user's selection 
    * @param ActionEvent e is the action that's passed in by the user
  **/
  public void actionPerformed(ActionEvent e){
    // opens the main menu if the back button is clicked
    if(e.getSource() == backButton){
      frame.dispose();
      Menu menu = new Menu(this.user);
    }
    // opens the contact window if button1 is clicked
    if(e.getSource() == button1){
      frame.dispose();
      ContactWindow contactWindow = new ContactWindow(this.user);
    }
    // opens the career window if button2 is clicked
    if(e.getSource() == button2){
      frame.dispose();
      CareerPage cp = new CareerPage(this.user);
    }
    // opens the report window if button3 is clicked
    if(e.getSource() == button3){
      frame.dispose();
      ReportWindow reportWindow = new ReportWindow(this.user);
    }
  }
}