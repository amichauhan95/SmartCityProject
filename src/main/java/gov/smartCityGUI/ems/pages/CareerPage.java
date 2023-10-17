package gov.smartCityGUI.ems.pages;

/**
  * Team Member(s) working on this class: Keith Austin
  * Project: Smart City
  * @author: Keith Austin
  * I received help from everyone on my team
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.*;
import gov.smartCityGUI.ems.EMSWindow;

public class CareerPage implements ActionListener {
  
  //assigns a constant to a file path
  private final String CAREERS_FILE_PATH = "src/main/java/gov/smartCityGUI/ems/data/careers.txt";
  
  //all class instances
  User user;
  JButton backButton;
  JButton homeButton;
  JButton button2;
  JButton button3;
  JButton searchButton;
  JButton searchKeyWordButton;
  JButton applyNowButton;
  JFrame frame;
  JPanel centerPanel = new JPanel();
  JScrollPane scrlpanel;
  JPanel topPanel;
  JPanel searchPanel;
  JTextField searchText;
  JComboBox cb;

  /**
    * public CareerPage(User user) initializes the current instance of the user and calls the create frame function
    * @param User user is the current instance of the user
  **/
  public CareerPage(User user) {
    this.user = user;
    createFrames();
  }

  /**
    * public void createHeader() creates the header of the career page
  **/
  public void createHeader(){
    
    topPanel = new JPanel();
    backButton = Gui.back();
    backButton.addActionListener(this);
    applyNowButton = new JButton("Apply Now");
    applyNowButton.addActionListener(this);
    applyNowButton.setBounds(10, 10, 150, 35);
    applyNowButton.setBackground(new Color(235,235,235));
    applyNowButton.setFocusable(false);
    topPanel.add(backButton);
    topPanel.add(applyNowButton);
    topPanel.add(Box.createRigidArea(new Dimension(400, 0)));
    
    frame.add(topPanel, BorderLayout.NORTH);
  }

  /**
    * public void createFrames() creates the frames for display
  **/
  public void createFrames() {
    frame = new JFrame("EMS | Careers");  
    createHeader();

    
    JLabel label = new JLabel("Career Listing");

    // reads the career file 
    String [][] data = readCareerFile();
    String column[]={"department", "company", "position", "city", "state", "annual_salary"};   

    // puts the data into a table
    JTable jt=new JTable(data,column);  
    JScrollPane sp = new JScrollPane(jt); 

    // creates the panel layout
    JPanel centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);

    centerPanel.add(label);
    centerPanel.add(sp);
    
    frame.add(centerPanel, BorderLayout.CENTER);
        
    frame.setSize(900,800);    
    frame.setVisible(true);     
  }

  /**
    * public String [][] readCareerFile() reads data from careers.txt
  **/
  public String [][] readCareerFile(){
    List<String> list = new ArrayList<String>(); 
    File file = new File(CAREERS_FILE_PATH);

    // checks if the file exists
    if(file.exists()){
      
      // the file's data is read into a list if the file exists
        try { 
            list = Files.readAllLines(file.toPath(),Charset.defaultCharset());
          // catches an Exception error if the file isn't read from properly
        } catch (Exception ex) {
            ex.printStackTrace();
        }

      // stores the data from the list into a 2d array
      String [][] data = new String[list.size()][];
      int index = 0;
      // loops through eact line in the list and splits each comma
      for(String line : list){
        String [] res = line.split(",");
        data[index] = res;
        index++;
      }
      // returns the data from the from the 2d array
      return data;
    }
    // returns a null value if there's no value in the list 
    return null;
  }

  /**  
    * public void actionPerformed(ActionEvent e) opens a window based on the user's selection
    * @param ActionEvent e gets the action instacne from the user
  **/
  public void actionPerformed(ActionEvent e) {
    // if the back button is clicked, the EMS window is opened
    if (e.getSource() == backButton) {
      frame.dispose();
      EMSWindow ems = new EMSWindow(this.user);
    }

    // if the apply now button is clicked, the Form window is opened
    if (e.getSource() == applyNowButton) {
      frame.dispose();
      Form form = new Form(this.user);
    }
  }
}