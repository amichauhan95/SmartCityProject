/*
Team Member(s) working on this class: Natalie Darsillo, Dylan Moran 
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

import java.awt.event.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.utilities.Gui;
import gov.smartcityteam2.*;

public class ManagementWindow implements ActionListener{

  private User user;
  private ITransitService service;

  // GUI elements
  private JFrame frame;
  private JButton backButton;
  private JComboBox<TransitStop> stopsBox;
  
  public ManagementWindow(User user, ITransitService service){
    this.user = user;
    this.service = service;
    this.frame = Gui.bigFrame("Albany+ Consolidated Transit System");
    initGUI();
  }
  
  public void initGUI(){
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    stopsBox = new JComboBox<TransitStop>();
    stopsBox.addActionListener(this);
    stopsBox.setBounds(50, 85, 150, 25);
    stopsBox.setSelectedItem(null);
    frame.add(stopsBox);
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == backButton){
      frame.dispose();
      Menu menu = new Menu(this.user);
    }
    if(e.getSource() == stopsBox) {
      
    }
  }
}