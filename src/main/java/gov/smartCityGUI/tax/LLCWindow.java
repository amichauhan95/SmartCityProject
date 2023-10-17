package gov.smartCityGUI.tax;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 9/19/2023
  * Description: This class is used to display all registered LLCs in a list and allow editing and deletion fo any LLC by an admin.
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;


public class LLCWindow implements ActionListener{

  //Resources
  User user;
  ArrayList<String> llcs;

  //Main Frame
  JFrame frame;
  JLabel scrollTitle;
  JButton backButton;
  JButton editButton;
  JButton deleteButton;
  ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
  JScrollPane scroll;

  
  //Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;

  //***********************************************************************//

  /**
    * The default constructor is used to instantiate resources and build the window
    *
    * @param user the user accessing the tax system
    * @param llcs and array list of business names to be displayed
  **/
  public LLCWindow(User user, ArrayList<String> llcs){
    this.user = user;
    this.llcs = llcs;
    createFrames();
    createButtons();
    createLabels();
    createScrollPane();
  } // End LLCWindow constructor

  //***********************************************************************//

  /**
    * This method is used to create and position labels within the frame
  **/
  public void createLabels(){

    JLabel scrollTitle = new JLabel("All LLCs");
    scrollTitle.setBounds(20,50,150,35);
    scrollTitle.setForeground(new Color(230,230,230));
    frame.add(scrollTitle);
  } // End createLabels() method

  //***********************************************************************//

  /**
    * This method is used to create the input frame and all of its componenets, used to get the edited name of a business
  **/
  public void inputFrame(){

    //Input frame
    inputFrame = Gui.smallFrame("Input");
    inputFrame.setVisible(false);

    //Input text field
    input = new JTextField();
    input.setBounds(75, 100, 150, 25);
    inputFrame.add(input);

    //Input description label
    prompt = new JLabel("Enter ...");
    prompt.setBounds(75, 40, 200, 25);
    prompt.setForeground(new Color(255,255,255));
    inputFrame.add(prompt);

    //Enter button
    enterButton = Gui.smallButton("Enter");
    enterButton.addActionListener(this);
    enterButton.setBounds(100, 145, 100, 25);
    enterButton.setBackground(new Color(235,235,235));
    inputFrame.add(enterButton);
  } // End inputFrame() method

  //***********************************************************************//

  /**
    * This method is used to build the frame for the window
  **/
  public void createFrames(){
    frame = Gui.bigFrame("View LLCs");
  } // End createFrames() method

  //***********************************************************************//

  /**
    * This method is used to create and position the buttons in the frame, also adding action listeners
  **/
  public void createButtons(){

    //Back button
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    /*//Edit button
    editButton = Gui.genericButton("Edit Name");
    editButton.addActionListener(this);
    editButton.setBounds(450, 80, 200, 35);
    frame.add(editButton);

    //Delete button
    deleteButton = Gui.genericButton("Delete");
    deleteButton.addActionListener(this);
    deleteButton.setBounds(450, 130,200,35);
    frame.add(deleteButton);*/
  } // End createButtons() method

  //***********************************************************************//

  /**
    * This method is used to create the scroll pane containing all of the registered business names
  **/
  public void createScrollPane(){

    ArrayList<String> allLLCs = llcs;

    //Container holding checkboxes
    JPanel llcPanel = new JPanel();
    llcPanel.setBackground(new Color(100,100,100));
    llcPanel.setLayout(new BoxLayout(llcPanel, BoxLayout.Y_AXIS));
    llcPanel.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
    llcPanel.setPreferredSize(new Dimension(350,500));
    JCheckBox c1;

    //Loop through all business names and assign to a check box
    for(String currentLLC : allLLCs){
      c1 = new JCheckBox(currentLLC);
      c1.setBackground(new Color(100,100,100));
      c1.setForeground(new Color(235,235,235));
      c1.setFocusable(false);
      checks.add(c1);
      llcPanel.add(c1);
    }

    //Scroll pane to contain all check boxes
    JScrollPane scroll = new JScrollPane(llcPanel);
    scroll.setBounds(20,75,430,300);
    scroll.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
    frame.add(scroll);
  } // End createScrollPane() method

  //***********************************************************************//

  /**
    * This method is used to perform actions when a button is pressed
  **/
  public void actionPerformed(ActionEvent e){

    //If back button is pressed
    if(e.getSource() == backButton){
      frame.dispose();
      TaxWindow tax = new TaxWindow(this.user);
    }
    
  } // End actionPerformed() method
} // End class