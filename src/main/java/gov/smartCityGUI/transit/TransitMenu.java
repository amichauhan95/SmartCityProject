/*
Team Member(s) working on this class: Natalie Darsillo, Dylan Moran 
Project: Smart City
I recieved help from: N/A
*/

package gov.smartCityGUI.transit;

import java.awt.event.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.utilities.Gui;

public class TransitMenu implements ActionListener{

  private User user;
  private ITransitService service;

  // GUI elements
  private JFrame frame;
  private JLabel titleLabel;
  private JButton backButton;
  private JButton buttonMaps;
  private JButton buttonDirections;
  private JButton buttonSchedules;
  private JButton buttonFares;
  private JButton managementButton;
  
  public TransitMenu(User user, ITransitService service){
    this.user = user;
    this.service = service;
    this.frame = Gui.bigFrame("Albany+ Consolidated Transit System");
    initGUI();
  }
  
  public void initGUI(){
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    titleLabel = Gui.whiteLabel("Albany+ Consolidated Transit System");
    titleLabel.setBounds(220, 120, 350, 25);
    frame.add(titleLabel);

    buttonMaps = Gui.genericButton("Display maps");
    buttonMaps.addActionListener(this);
    buttonMaps.setBounds(130, 160, 200, 35);
    frame.add(buttonMaps);

    buttonDirections = Gui.genericButton("Get directions");
    buttonDirections.addActionListener(this);
    buttonDirections.setBounds(370, 160, 200,35);
    frame.add(buttonDirections);

    buttonSchedules = Gui.genericButton("Show schedules");
    buttonSchedules.addActionListener(this);
    buttonSchedules.setBounds(130, 240, 200, 35);
    frame.add(buttonSchedules);

    buttonFares = Gui.genericButton("View fares");
    buttonFares.addActionListener(this);
    buttonFares.setBounds(370, 240, 200, 35);
    frame.add(buttonFares);

    managementButton = Gui.genericButton("Station Management");
    managementButton.setBounds(250, 300, 200, 35);
    //if(user.isAdmin()) {
      //managementButton.addActionListener(this);
      //frame.add(managementButton);
    //}
    
    frame.repaint();
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == backButton){
      frame.dispose();
      Menu menu = new Menu(this.user);
    }
    if(e.getSource() == buttonMaps){
      frame.dispose();
      new MapWindow(user, service);
    }
    if(e.getSource() == buttonDirections){
      frame.dispose();
      new DirectionsWindow(user, service);
    }
    if(e.getSource() == buttonSchedules){
      frame.dispose();
      new ScheduleWindow(user, service);
    }
    if(e.getSource() == buttonFares){
      frame.dispose();
      new FareWindow(user, service);
    }
    if(e.getSource() == managementButton) {
      frame.dispose();
      new ManagementWindow(user, service);
    }
  }
}