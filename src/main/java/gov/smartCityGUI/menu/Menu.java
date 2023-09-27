package gov.smartCityGUI.menu;

/*
@author Dylan Moran
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.admin.gui.AdminWindow;
import gov.smartCityGUI.banking.BankWindow;
import gov.smartCityGUI.education.gui.EducationWindow;
//import gov.smartCityGUI.ems.EMSWindow;
import gov.smartCityGUI.forum.ForumWindow;
import gov.smartCityGUI.hospital.HospitalWindow;
import gov.smartCityGUI.login.LoginScene;
import gov.smartCityGUI.tax.TaxWindow;
import gov.smartCityGUI.tourism.TourismWindow;
//import gov.smartCityGUI.transit.TransitWindow;
import gov.smartCityGUI.utilities.*;
import gov.smartcityteam2.Transit.*;


public class Menu implements ActionListener{

  //UI Elements
  User user;
  JFrame frame;
  JLabel title;
  JButton logout;
  JButton tourismButton;
  JButton hospitalButton;
  JButton transitButton;
  JButton educationButton;
  JButton emsButton;
  JButton taxButton;
  JButton bankButton;
  JButton forumButton;
  JButton manageButton;

  //***********************************************************************//

  /*
  Constructor used to create all UI elements and save the User's info in the class
  */
  public Menu(User user){

    this.user = user;
    createFrame();
    createButtons();
    createLabels();
  }

  //***********************************************************************//

  /*
  This method is used to create the frame
  */
  public void createFrame(){
    frame = Gui.bigFrame("Albany+");
  }

  //***********************************************************************//

  /*
  This method is used to create the labels
  */
  public void createLabels(){
    title = Gui.whiteLabel("Welcome to Albany+ " + user.getFirst());
    title.setBounds(220, 30, 400, 40);
    title.setFont(new Font("Verdana", Font.PLAIN, 18));
    frame.add(title);
  }
  
  //***********************************************************************//

  /*
  This method is used to create all of the buttons
  */
  public void createButtons(){

    //Tourism Button
    tourismButton = Gui.genericButton("Tourism");
    tourismButton.setBounds(20, 120, 150, 35);
    tourismButton.addActionListener(this);
    frame.add(tourismButton);

    //Hospital Button
    hospitalButton = Gui.genericButton("Hospitals");
    hospitalButton.setBounds(190, 120, 150, 35);
    hospitalButton.addActionListener(this);
    frame.add(hospitalButton);

    //Transit Button
    transitButton = Gui.genericButton("Transit");
    transitButton.setBounds(360, 120, 150, 35);
    transitButton.addActionListener(this);
    frame.add(transitButton);

    //Education Button
    educationButton = Gui.genericButton("Education");
    educationButton.setBounds(530, 120, 150, 35);
    educationButton.addActionListener(this);
    frame.add(educationButton);

    //EMS Button
    emsButton = Gui.genericButton("EMS");
    emsButton.setBounds(20, 190, 150, 35);
    emsButton.addActionListener(this);
    frame.add(emsButton);

    //Tax Button
    taxButton = Gui.genericButton("Tax/Finance");
    taxButton.setBounds(190, 190, 150, 35);
    taxButton.addActionListener(this);
    frame.add(taxButton);

    //Bank Button
    bankButton = Gui.genericButton("Banking");
    bankButton.setBounds(360, 190, 150, 35);
    bankButton.addActionListener(this);
    frame.add(bankButton);

    //Forum Button
    forumButton = Gui.genericButton("City Forum");
    forumButton.setBounds(530, 190, 150, 35);
    forumButton.addActionListener(this);
    frame.add(forumButton);

    //Logout Button
    logout = new JButton("Log Out");
    logout.setBounds(290, 300, 120, 35);
    logout.setBackground(new Color(255,40,40));
    logout.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    logout.addActionListener(this);
    logout.setFocusable(false);
    frame.add(logout);

    //If Admin, add an Admin Button and move the logout button
    if(this.user.isAdmin()){
      manageButton = new JButton("Admin");
      logout.setBounds(375, 300, 120, 35);
      manageButton.setBounds(205, 300, 120, 35);
      manageButton.setBackground(new Color(235,235,235));
      manageButton.addActionListener(this);
      manageButton.setFocusable(false);
      frame.add(manageButton);
      }
  }

  //***********************************************************************//

  /*
  This method is used to handle all button presses
  */

  public void actionPerformed(ActionEvent e){
    
    //If Tax button is pressed
    if(e.getSource() == taxButton){
      frame.dispose();
      TaxWindow tax = new TaxWindow(this.user);
    }
    
    //If Bank button is pressed
    if(e.getSource() == bankButton){
      frame.dispose();
      BankWindow bank = new BankWindow(this.user);
    }

    //If Logout button is pressed
    if(e.getSource() == logout){
      frame.dispose();
      LoginScene login = new LoginScene();
    }

    //If Forum button is pressed
    if(e.getSource() == forumButton){
      frame.dispose();
      ForumWindow forum = new ForumWindow(this.user);
    }

    //If Hospital Button is pressed
    if(e.getSource() == hospitalButton){
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }

    if(e.getSource() == emsButton){
      //frame.dispose();
      //EMSWindow ems = new EMSWindow(this.user);
    }

    if(e.getSource() == educationButton){
      frame.dispose();
      EducationWindow ed = new EducationWindow(this.user);
    }

    if(e.getSource() == transitButton){
      frame.dispose();
      ITransitRepository repository = new DummyTransitRepository();
      ITransitService service = new TransitService(repository);
      TransitMenu transit = new TransitMenu(this.user, service);
    }

    if(e.getSource() == tourismButton){
      frame.dispose();
      TourismWindow tour = new TourismWindow(this.user);
    }

    //If Admin button is pressed
    if(e.getSource() == manageButton){
      frame.dispose();
      AdminWindow admin = new AdminWindow(this.user);
    }

    
  }
}