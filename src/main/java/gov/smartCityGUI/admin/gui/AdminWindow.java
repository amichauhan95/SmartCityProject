package gov.smartCityGUI.admin.gui;

/*
@author Dylan Moran
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.admin.service.*;
import gov.smartCityGUI.utilities.*;

//***********************************************************************//

public class AdminWindow implements ActionListener{

  //UI Elements

  //Main Frame
  User user;
  User editUser;
  JFrame frame;
  ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
  JScrollPane scroll;
  JButton backButton;
  JButton manageButton;

  //Account Frame
  JFrame accountFrame;
  JButton editButton;
  JButton adminToggle;
  JButton deleteButton;
  JButton backToAdmin;

  //Edit Frame
  JFrame editFrame;
  JLabel firstNameLabel;
  JLabel lastNameLabel;
  JLabel emailLabel;
  JLabel phoneLabel;
  JTextField firstNameText;
  JTextField lastNameText;
  JTextField emailText;
  JTextField phoneText;
  JButton confirmButton;
  JButton backToAccount;

  //***********************************************************************//

  /*
  Constructor to build the UI and save user to the class
  */
  public AdminWindow(User user){
    this.user = user;
    createFrames();
    createButtons();
    createScrollPane();
    createLabels();
  } // End Constructor

  //***********************************************************************//

  /*
  This method is used to create the main frame
  */
  public void createFrames(){
    frame = Gui.bigFrame("Manage Users");
  } // End createFrames() method

  //***********************************************************************//

  /*
  This method is used to create the account view frame
  @param id the ID # of the user whos account is being viewed
  */
  public void manageFrame(String id){
    //Account frame
    accountFrame = Gui.mediumFrame("Manage User");
    this.editUser = UserManager.getUser(id);

    //Edit button
    editButton = Gui.genericButton("Edit");
    editButton.setBounds(300, 120, 150, 35);
    editButton.addActionListener(this);
    accountFrame.add(editButton);

    //Toggle Admin button
    adminToggle = Gui.genericButton("Toggle Admin");
    adminToggle.setBounds(300, 170, 150, 35);
    adminToggle.addActionListener(this);
    accountFrame.add(adminToggle);

    //Delete button
    deleteButton = Gui.genericButton("Delete User");
    deleteButton.setBounds(300, 220, 150, 35);
    deleteButton.addActionListener(this);
    accountFrame.add(deleteButton);

    //Back button
    backToAdmin = Gui.back();
    backToAdmin.addActionListener(this);
    accountFrame.add(backToAdmin);

    //User ID label
    JLabel userID = Gui.whiteLabel("User ID: " + this.editUser.getID());
    userID.setBounds(30,60,250, 35);
    accountFrame.add(userID);

    //Name label
    JLabel name = Gui.whiteLabel("Name: " + this.editUser.getFirst() + " " + this.editUser.getLast());
    name.setBounds(30,100,250,35);
    accountFrame.add(name);

    //Email label
    JLabel email = Gui.whiteLabel("Email: " + this.editUser.getEmail());
    email.setBounds(30,140,250, 35);
    accountFrame.add(email);

    //Phone label
    JLabel phone = Gui.whiteLabel("Phone Number: " + this.editUser.getPhone());
    phone.setBounds(30,180,250, 35);
    accountFrame.add(phone);

    //Admin Status label
    String status = new String();
    if(this.editUser.isAdmin()) status = "Admin";
    else status = "General User";
    JLabel role = Gui.whiteLabel("Role: " + status);
    role.setBounds(30,220,250, 35);
    accountFrame.add(role);
    
  } // End manageFrame() method

  //***********************************************************************//

  /*
  This method is used to create the edit frame and all of it's components
  */
  public void editFrame(User user){
    //Edit Frame
    editFrame = Gui.formFrame("Edit");

    //First name text field
    firstNameText = new JTextField();
    firstNameText.setBounds(75, 40, 150, 25);
    firstNameText.setText(user.getFirst());
    editFrame.add(firstNameText);

    //Last name text field
    lastNameText = new JTextField();
    lastNameText.setBounds(75, 100, 150, 25);
    lastNameText.setText(user.getLast());
    editFrame.add(lastNameText);

    //Email text field
    emailText = new JTextField();
    emailText.setBounds(75, 160, 150, 25);
    emailText.setText(user.getEmail());
    editFrame.add(emailText);

    //Phone text field
    phoneText = new JTextField();
    phoneText.setBounds(75, 220, 150, 25);
    phoneText.setText(user.getPhone());
    editFrame.add(phoneText);

    //First name label
    firstNameLabel = new JLabel("First Name:");
    firstNameLabel.setBounds(75, 15, 150, 25);
    firstNameLabel.setForeground(new Color(255,255,255));
    editFrame.add(firstNameLabel);

    //Last name label
    lastNameLabel = new JLabel("Last Name:");
    lastNameLabel.setBounds(75, 75, 150, 25);
    lastNameLabel.setForeground(new Color(255,255,255));
    editFrame.add(lastNameLabel);

    //Email label
    emailLabel = new JLabel("Email:");
    emailLabel.setBounds(75, 135, 150, 25);
    emailLabel.setForeground(new Color(255,255,255));
    editFrame.add(emailLabel);

    //Phone label
    phoneLabel = new JLabel("Phone Number:");
    phoneLabel.setBounds(75,195, 150, 25);
    phoneLabel.setForeground(new Color(255,255,255));
    editFrame.add(phoneLabel);

    //Confirm button
    confirmButton = new JButton("Confirm");
    confirmButton.setBounds(100, 330, 100, 25);
    confirmButton.setBackground(new Color(235,235,235));
    confirmButton.addActionListener(this);
    confirmButton.setFocusable(false);
    editFrame.add(confirmButton);

    //Back button
    backToAccount = new JButton("Back");
    backToAccount.setBounds(100, 365, 100, 25);
    backToAccount.setBackground(new Color(235,235,235));
    backToAccount.addActionListener(this);
    backToAccount.setFocusable(false);
    editFrame.add(backToAccount);
  } // End editFrame() method

  //***********************************************************************//

  /*
  This method is used to create all of the buttons in the main frame
  */
  public void createButtons(){
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    manageButton = Gui.genericButton("Manage User");
    manageButton.setBounds(500,80,150,35);
    manageButton.addActionListener(this);
    frame.add(manageButton);
  } // End createButtons() method
  //***********************************************************************//

  /*
  This method is used to create all the labels
  */
  public void createLabels(){

    JLabel scrollTitle = Gui.whiteLabel("All Users");
    scrollTitle.setBounds(20,50,150,35);
    frame.add(scrollTitle);

    JLabel tip = Gui.whiteLabel("Only select 1 user");
    tip.setBounds(510,110,150,35);
    frame.add(tip);
  } // End createLabels method()

  //***********************************************************************//

  /*
  This method is used to create the scroll pane
  */
  public void createScrollPane(){

    ArrayList<User> allUsers = UserManager.fetchUsers();
      
    JPanel userPanel = new JPanel();
    userPanel.setBackground(new Color(100,100,100));
    userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
    userPanel.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
    JCheckBox c1;
    
    for(User currentUser : allUsers){
      c1 = new JCheckBox("[" + currentUser.getID() + "] : " + currentUser.getFirst() + " " + currentUser.getLast());
      c1.setBackground(new Color(100,100,100));
      c1.setForeground(new Color(235,235,235));
      c1.setFocusable(false);
      checks.add(c1);
      userPanel.add(c1);
    }
      userPanel.setPreferredSize(new Dimension(350,500));
      JScrollPane scroll = new JScrollPane(userPanel);
      scroll.setBounds(20,75,430,300);
      scroll.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
      frame.add(scroll);
  } // End createScrollPane() method

  //***********************************************************************//

  /*
  This method is used to handle all button presses
  */
  public void actionPerformed(ActionEvent e){

    //If Back button is pressed
    if(e.getSource() == backButton){
      frame.dispose();
      Menu menu = new Menu(this.user);
    }

    //If Manage User Button is pressed
    if(e.getSource() == manageButton){
      boolean selected = false;
      String user = new String();
      String id = new String();
      for(JCheckBox check : checks){
        if(check.isSelected()) {
          selected = true;
          user = check.getText();
          user = user.substring(1);
          char characters[] = user.toCharArray();
          for(char letter : characters){
            if(letter == ']') break;
            else{
              id += letter;
            }
          }
          frame.dispose();
          manageFrame(id);
          break;
        }
      }
    }

    //If Back button is pressed from the account frame
    if(e.getSource() == backToAdmin){
      accountFrame.dispose();
      AdminWindow manage = new AdminWindow(this.user);
    }

    //If Toggle Admin button is pressed
    if(e.getSource() == adminToggle){
      UserManager.toggleAdmin(this.editUser);
      accountFrame.dispose();
      manageFrame(this.editUser.getID());
    }

    //If Delete button is pressed
    if(e.getSource() == deleteButton){
      if(!(this.user.getID().equals(this.editUser.getID()))){
        UserManager.deleteUser(this.editUser);
        accountFrame.dispose();
        this.editUser = new User();
        AdminWindow manage = new AdminWindow(this.user);
      } else {
        JOptionPane.showMessageDialog(null,
                                      "You cannot delete your own account.",
                                      "Unable to execute",
                                      JOptionPane.WARNING_MESSAGE);
      }
    }

    //If Edit button is pressed
    if(e.getSource() == editButton){
      accountFrame.dispose();
      editFrame(this.editUser);
    }

    //If Back button is pressed in the edit frame
    if(e.getSource() == backToAccount){
      editFrame.dispose();
      manageFrame(this.editUser.getID());
    }

    //If the confirm button is pressed
    if(e.getSource() == confirmButton){

      String firstName = firstNameText.getText();
      String lastName = lastNameText.getText();
      String email = emailText.getText();
      String phone = phoneText.getText();
      
      if(UserManager.editUser(this.editUser, firstName, lastName, email, phone)){
        editFrame.dispose();
        manageFrame(this.editUser.getID());
      } else {
        JOptionPane.showMessageDialog(null,
                                      "Requirements:\nFirst and Last Name must be longer than 3 characters.\nEmail: x@xxx.domain\nPhone Number: xxxxxxx (7-10 digits).",
                                      "Error Updating Account",
                                      JOptionPane.WARNING_MESSAGE);
      }
    }
  } // End actionPerformed() method
} // End AdminWindow class