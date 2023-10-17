package gov.smartCityGUI.ems;

/**
  * Team Member(s) working on this class: Keith Austin
  * Project: Smart City
  * @author: Keith Austin
  * I received help from everyone on my team
**/

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;

public class ContactWindow implements ActionListener {
  JFrame frame;
  JPanel panel;
  JTextArea textArea;
  JScrollPane scrollPane;
  JButton backButton;
  User user;

  /**
    * public ContactWindow(User user) is the runs all the creation methods
    * @param User user is the current user that's logged in
  **/
  public ContactWindow(User user) {
    this.user = user;
    
    createFrame();
    createPanel();
    createButtons();
    createTextArea();
    createScrollPane();
  }

  /**
    * public void createButtons() creates the buttons for the GUI
  **/
  public void createButtons() {
    backButton = Gui.backForCareer();
    backButton.addActionListener(this);
    frame.add(backButton);
  }

  /**
    * public void createFrame() creates the main frame for the GUI
  **/
  public void createFrame() {
    frame = new JFrame("EMS | Department Contacts");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(900, 800);
  }

  /**
    * public void createFrame() creates the main frame for the GUI
  **/
  public void createPanel() {
    panel = new JPanel(new BorderLayout());
    panel.setBorder(BorderFactory.createTitledBorder("Department Contacts"));
  }

  /**
    * public void createFrame() creates the main frame for the GUI
  **/
  public void createTextArea() {
    textArea = new JTextArea();
    textArea.setEditable(false);
  }

  /**
    * public void createFrame() creates the main frame for the GUI
  **/
  public void createScrollPane() {
    scrollPane = new JScrollPane(textArea);
    panel.add(scrollPane, BorderLayout.CENTER);
    frame.getContentPane().add(panel);
    // try statement that reads the contacts.txt file 
    try {
     String filePath = "src/main/java/gov/smartCityGUI/ems/data/contacts.txt";
          BufferedReader reader = new BufferedReader(new FileReader(filePath));
          String line;
          while ((line = reader.readLine()) != null) {
              textArea.append(line + "\n");
          }
          reader.close();
      // catches an Exception error if the file isn't read from properly
      } catch (Exception err) {
          err.printStackTrace();
          JOptionPane.showMessageDialog(frame, "An error occurred while reading the file.", "Error", JOptionPane.ERROR_MESSAGE);
      }
    frame.setVisible(true);
  }

  /**
    * public void actionPerformed(ActionEvent e) displays a window based on the selection from the user
    * @param ActionEvent e represents the action that occurs when the user clicks a button
  **/
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == backButton){
      frame.dispose();
      EMSWindow ems = new EMSWindow(this.user);
    }
  }
}