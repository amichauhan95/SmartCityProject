package gov.smartCityGUI.banking;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 9/19/2023
  * Description: This class is the UI for the Bank system. Here users will be able to interact with their bank account
  * view their balance and perform operations.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.utilities.*;

public class BankWindow implements ActionListener{

  //UI Components

  //Main frame
  User user;
  JFrame frame;
  JLabel balance;
  JButton backButton;
  JButton withdrawButton;
  JButton depositButton;
  JButton loanButton;
  
  //Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  JButton backToDash;

  //Flags
  boolean wit = false, dep = false;

  //***********************************************************************//

  /**
  * The default constructor is used to create a new BankAccount instance and build the window
  * 
  * @param user the user accessing the Bank System
  */
  public BankWindow(User user){
    this.user = user;
    BankAccount.addAccount(user);
    String parts[] = BankAccount.findAccount(user.getID());
    createFrames();
    createButtons();
    createLabels(parts);
  } // End BankSystem() constructor

  //***********************************************************************//

  /**
  * This method is used to build the frame for the window
  */
  public void createFrames(){
    frame = Gui.bigFrame("Banking");
    frame.setSize(350, 400);
  } // End createFrames() method

  //***********************************************************************//

  /**
  * This method is used to create and position labels within the frame
  *
  * @param parts a String array containing the users account information
  */
  public void createLabels(String [] parts){

    // Account holder label
    JLabel name = new JLabel("<html>Account Holder:<br/>" + parts[1] + " " + parts[2] + "</html>");
    name.setBounds(75,60,250, 35);
    name.setForeground(new Color(230,230,230));
    frame.add(name);

    //Balance label
    balance = new JLabel("<html>Balance: <br/>$" + String.format("%,.2f", Double.parseDouble(parts[3])) + "</html>");
    balance.setBounds(75, 100, 250, 35);
    balance.setForeground(new Color(230,230,230));
    frame.add(balance);
  } // End createLabels() method

  //***********************************************************************//

  /**
  * This method is used to create and position the buttons in th frame, also adding action listeners
  */
  public void createButtons(){

    //Back button
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    //Withdraw button
    withdrawButton = Gui.genericButton("Withdraw");
    withdrawButton.addActionListener(this);
    withdrawButton.setBounds(75, 175, 200, 35);
    frame.add(withdrawButton);

    //Deposit button
    depositButton = Gui.genericButton("Deposit");
    depositButton.addActionListener(this);
    depositButton.setBounds(75, 225,200,35);
    frame.add(depositButton);

    //Loan button
    loanButton = Gui.genericButton("Request a Loan");
    loanButton.addActionListener(this);
    loanButton.setBounds(75,275, 200, 35);
    frame.add(loanButton);
  } // End createButtons() method

  //***********************************************************************//

  /**
  * This method is used to create the input frame and all of its own components to gather user input
  */
  public void inputFrame(){

    //Input frame
    inputFrame = Gui.smallFrame("Input");
    inputFrame.setVisible(false);

    //Input text field
    input = new JTextField();
    input.setBounds(75, 100, 150, 25);
    inputFrame.add(input);

    //Input description label
    prompt = new JLabel("Enter a valid number amount");
    prompt.setBounds(75, 40, 200, 25);
    prompt.setForeground(new Color(255,255,255));
    inputFrame.add(prompt);

    //Enter button
    enterButton = Gui.smallButton("Enter");
    enterButton.addActionListener(this);
    enterButton.setBounds(100, 145, 100, 25);
    enterButton.setBackground(new Color(235,235,235));
    inputFrame.add(enterButton);

    //Back button
    backToDash = Gui.smallButton("Back");
    backToDash.addActionListener(this);
    backToDash.setBounds(100,175,100,25);
    backToDash.setBackground(new Color(235,235,235));
    inputFrame.add(backToDash);
  } // End inputFrame() method

  //***********************************************************************//

  /**
  * This method is used to perform actions when buttons are pressed
  *
  * @param e the click, used to access the source that was clicked
  */
  public void actionPerformed(ActionEvent e){

    //If back button is pressed
    if(e.getSource() == backButton){
      frame.dispose();
      Menu menu = new Menu(this.user);
    }

    //If withdraw button is pressed
    if(e.getSource() == withdrawButton){
      wit = true;
      frame.setVisible(false);
      inputFrame();
      prompt.setText("Withdraw Amount:");
      inputFrame.setVisible(true);
    }

    //If deposit button is pressed
    if(e.getSource() == depositButton){
      dep = true;
      frame.setVisible(false);
      inputFrame();
      prompt.setText("Deposit Amount:");
      inputFrame.setVisible(true);
    }

    //If loan button is pressed
    if(e.getSource() == loanButton){
      if(BankAccount.requestLoan(this.user)){
        JOptionPane.showMessageDialog(null,"You have been approved\nfor a $10,000 loan.", "Notification",JOptionPane.INFORMATION_MESSAGE);
        String parts[] = BankAccount.findAccount(user.getID());
      balance.setText("<html>Balance:  <br/>$" + String.format("%,.2f", Double.parseDouble(parts[3])) + "</html>");
      } else {
        JOptionPane.showMessageDialog(null,"You have been denied a loan due to\nyour outstanding balance in Tax/Finance System", "Notification",JOptionPane.INFORMATION_MESSAGE);
      }
    }

    //If enter button is pressed in the input frame
    if(e.getSource() == enterButton){
      try{
        Double amount = Double.parseDouble(input.getText());
        if(wit) BankAccount.withdraw(user,amount);
        if(dep) BankAccount.deposit(user, amount);
        inputFrame.dispose();
        input.setText("");
        wit = false;
        dep = false;
        String parts[] = BankAccount.findAccount(user.getID());
        balance.setText("<html>Balance:  <br/>$" + String.format("%,.2f", Double.parseDouble(parts[3])) + "</html>");
        frame.setVisible(true);
      } catch(NumberFormatException nfe){
        JOptionPane.showMessageDialog(null,"You must enter a valid number", "Invalid",JOptionPane.WARNING_MESSAGE);
      }
    }

    //If back button is pressed in input frame
    if(e.getSource() == backToDash){
      inputFrame.dispose();
      wit = false;
      dep = false;
      frame.setVisible(true);
    }
  } // End actionPerformed() method
} // End class