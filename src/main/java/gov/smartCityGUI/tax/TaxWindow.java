package gov.smartCityGUI.tax;

/*
@author Dylan Moran
Project: Smart City
@date 9/19/2023
I recieved help from: N/A
*/

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;
import gov.smartCityGUI.menu.Menu;

public class TaxWindow implements ActionListener{

  //Resources
  User user;
  TaxUser taxUser;
  TaxAccount account;

  //UI Components

  //Main frame
  JFrame frame;
  JButton accountButton;
  JButton paymentButton;
  JButton llcButton;
  JButton backButton;
  JButton changeRates;
  JButton viewLLCs;

  //Edit Frame
  JFrame editFrame;
  JLabel salesLabel;
  JLabel incomeLabel;
  JLabel corpLabel;
  JLabel propLabel;
  JTextField incomeText;
  JTextField salesText;
  JTextField corpText;
  JTextField propText;
  JButton confirmButton;
  JButton backToDash;

  //Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  JButton backToTax;

  //Flags
  boolean llc = false;
  boolean pay = false;

  //***********************************************************************//

  /*
  * The default constructor is used to instantiate a tax user and their account and build the window with all of its UI Components
  *
  * @param user the user accessing the Tax system
  */
  public TaxWindow(User user){
    this.user = user;
    account = new TaxAccount(user);       // Opens the users tax account
    this.taxUser = account.login(); 
    createFrame();
    createButtons();
    createLabels();
  } // End TaxWindow() constructor

  //***********************************************************************//

  /*
  * This method is used to build the frame for the window
  */
  public void createFrame(){
    frame = Gui.bigFrame("Tax and Finance System");
  } // End createFrame() method

  //***********************************************************************//

  /*
  * This method is used to create and position labels within the frame
  */
  public void createLabels(){

    //Account holder label
    JLabel name = new JLabel("Account Holder:   " + user.getFirst() + " " + user.getLast());
    name.setBounds(60,60,250, 35);
    name.setForeground(new Color(230,230,230));
    frame.add(name);

    //Balance label
    JLabel balance = new JLabel("Balance:  $" + String.format("%,.2f", this.taxUser.getBalance()));
    balance.setBounds(60, 100, 250, 35);
    balance.setForeground(new Color(230,230,230));
    frame.add(balance);

    //Total tax label
    JLabel taxes = new JLabel("Total taxed amount:  $" + String.format("%,.2f", this.taxUser.getTotalTax()));
    taxes.setBounds(60, 140, 250, 35);
    taxes.setForeground(new Color(230,230,230));
    frame.add(taxes);

    //Populate tax rates and create tax rates label
    account.populateTaxRates();
    JLabel taxRatesLabel = new JLabel("<html>Sales Tax: " + account.salesTax*100 + "%<br/><br/>Income Tax:      " + account.incomeTax*100 + "%<br/><br/>Corporation Tax:  " + account.corporationTax*100 + "%<br/><br/>Property Tax:     " + account.propertyTax*100 + "%</html>");
    taxRatesLabel.setBounds(450, 40, 175, 150);
    taxRatesLabel.setForeground(new Color(230,230,230));
    frame.add(taxRatesLabel);
  } // End createLabels() method

  //***********************************************************************//

  /*
  * This method is used to create and position buttons in the frame, also adding action listeners
  */
  public void createButtons(){

    //Back button
    backButton = new JButton("Back");
    backButton.setBounds(10, 10, 100, 35);
    backButton.setBackground(new Color(235,235,235));
    backButton.addActionListener(this);
    backButton.setFocusable(false);
    frame.add(backButton);

    //Payment button
    paymentButton = new JButton("Make a Payment");
    paymentButton.setBounds(60, 260, 200, 35);
    paymentButton.setBackground(new Color(235,235,235));
    paymentButton.addActionListener(this);
    paymentButton.setFocusable(false);
    frame.add(paymentButton);

    //Register LLC button
    llcButton = Gui.genericButton("Register an LLC");
    llcButton.setBounds(60, 310, 200, 35);   
    llcButton.addActionListener(this);
    frame.add(llcButton);

    //If the user is an admin...
    if(this.user.isAdmin()){
      //Modify rates button
      changeRates = Gui.genericButton("Modify Rates");
      changeRates.setBounds(450, 260, 200, 35);
      changeRates.addActionListener(this);
      frame.add(changeRates);

      //View all llcs button
      viewLLCs = Gui.genericButton("View LLCs");
      viewLLCs.setBounds(450, 310, 200, 35);
      viewLLCs.addActionListener(this);
      frame.add(viewLLCs);
    } // End if
  } // End createButtons() method

  //***********************************************************************//

  /*
  * This method is used to create the edit frame and all of its components, used to allow an admin user to edit tax rates
  */
  public void editFrame(User user){
    //Populate current tax rates
    account.populateTaxRates();
    
    //Edit Frame
    editFrame = Gui.formFrame("Edit");

    //Sales Tax text field
    salesText = new JTextField();
    salesText.setBounds(75, 40, 150, 25);
    salesText.setText(Double.toString(account.salesTax*100));
    editFrame.add(salesText);

    //Income Tax text field
    incomeText = new JTextField();
    incomeText.setBounds(75, 100, 150, 25);
    incomeText.setText(Double.toString(account.incomeTax*100));
    editFrame.add(incomeText);

    //Corporation Tax text field
    corpText = new JTextField();
    corpText.setBounds(75, 160, 150, 25);
    corpText.setText(Double.toString(account.corporationTax*100));
    editFrame.add(corpText);

    //Property Tax text field
    propText = new JTextField();
    propText.setBounds(75, 220, 150, 25);
    propText.setText(Double.toString(account.propertyTax*100));
    editFrame.add(propText);

    //Sales Tax label
    salesLabel = new JLabel("Sales Tax:");
    salesLabel.setBounds(75, 15, 150, 25);
    salesLabel.setForeground(new Color(255,255,255));
    editFrame.add(salesLabel);

    //Income Tax label
    incomeLabel = new JLabel("Income Tax:");
    incomeLabel.setBounds(75, 75, 150, 25);
    incomeLabel.setForeground(new Color(255,255,255));
    editFrame.add(incomeLabel);

    //Coropration Tax label
    corpLabel = new JLabel("Corporation Tax:");
    corpLabel.setBounds(75, 135, 150, 25);
    corpLabel.setForeground(new Color(255,255,255));
    editFrame.add(corpLabel);

    //Property Tax label
    propLabel = new JLabel("Property Tax:");
    propLabel.setBounds(75,195, 150, 25);
    propLabel.setForeground(new Color(255,255,255));
    editFrame.add(propLabel);

    //Confirm button
    confirmButton = new JButton("Confirm");
    confirmButton.setBounds(100, 330, 100, 25);
    confirmButton.setBackground(new Color(235,235,235));
    confirmButton.addActionListener(this);
    confirmButton.setFocusable(false);
    editFrame.add(confirmButton);

    //Back button
    backToDash = new JButton("Back");
    backToDash.setBounds(100, 365, 100, 25);
    backToDash.setBackground(new Color(235,235,235));
    backToDash.addActionListener(this);
    backToDash.setFocusable(false);
    editFrame.add(backToDash);
  } // End editFrame() method

   //***********************************************************************//

  /*
  * This method is used to create the input frame and all of its components, used to allow the user to input a payment amount or register an LLC
  */
  public void inputFrame(){

    //Input frame
    inputFrame = Gui.smallFrame("Input");

    //Input text field
    input = new JTextField();
    input.setBounds(75, 100, 150, 25);
    inputFrame.add(input);

    //Set the proper input description
    if(pay) prompt = new JLabel("Enter a valid amount");
    if(llc) prompt = new JLabel("<html>Enter the name<br/>of your Business</html>");

    //Input description label
    prompt.setBounds(75, 40, 200, 35);
    prompt.setForeground(new Color(255,255,255));
    inputFrame.add(prompt);

    //Enter button
    enterButton = Gui.smallButton("Enter");
    enterButton.addActionListener(this);
    enterButton.setBounds(100, 145, 100, 25);
    enterButton.setBackground(new Color(235,235,235));
    inputFrame.add(enterButton);

    //Back button
    backToTax = Gui.smallButton("Back");
    backToTax.addActionListener(this);
    backToTax.setBounds(100,175,100,25);
    backToTax.setBackground(new Color(235,235,235));
    inputFrame.add(backToTax);
  } // End inputFrame() method

  //***********************************************************************//

  /*
  * This method is used to perform actions when buttons are pressed
  */
  public void actionPerformed(ActionEvent e){

    //If back button is pressed
    if(e.getSource() == backButton){
      frame.dispose();
      Menu menu = new Menu(this.user);
    } // End if

    //************************************//

    //If modify rates button is pressed
    if(e.getSource() == changeRates){
      frame.dispose();
      editFrame(this.user);
    } // End if

    //************************************//

    //If confirm button is pressed in edit frame
    if(e.getSource() == confirmButton){
      
      if(account.changeRates(salesText.getText(), incomeText.getText(), corpText.getText(), propText.getText())){
        editFrame.dispose();
        TaxWindow tax = new TaxWindow(this.user);
      } else {
        JOptionPane.showMessageDialog(null,
                                      "Rates must only contain numbers and one decimal.",
                                      "Error Modifying Rates",
                                      JOptionPane.WARNING_MESSAGE);
          }
    } // End if

    //************************************//

    //If back button is pressed in edit frame
    if(e.getSource() == backToDash){
      editFrame.dispose();
      TaxWindow tax = new TaxWindow(this.user);
    } // End if

    //************************************//

    //If payment button is pressed
    if(e.getSource() == paymentButton){
      this.pay = true;
      frame.dispose();
      inputFrame();
    }

    //************************************//

    //If Register llc button is pressed
    if(e.getSource() == llcButton){
      this.llc = true;
      frame.dispose();
      inputFrame();
    }

    //************************************//

    //If enter button is pressed in input frame
    if(e.getSource() == enterButton){
      if(pay){
        try{
          double amount = Double.parseDouble(input.getText());
          account.makePayment(this.user, this.taxUser, amount);
          inputFrame.dispose();
          TaxWindow tax = new TaxWindow(this.user);
        } catch(Exception nfe){
          JOptionPane.showMessageDialog(null,
                                        "Rates must only contain numbers",
                                        "Error Making Payment",
                                        JOptionPane.WARNING_MESSAGE);
        } // End try-catch
      } // End if
      else if(llc){
        if(account.fileLLC(input.getText())){
          JOptionPane.showMessageDialog(null,
                                      "Congratulations on registering an LLC!",
                                      "Registration Successful",
                                      JOptionPane.INFORMATION_MESSAGE);
          inputFrame.dispose();
          TaxWindow tax = new TaxWindow(this.user);
        } // End nested if
      } // End else if
    } // End if

    //************************************//

    //If view llcs button is pressed
    if(e.getSource() == viewLLCs){
      frame.dispose();
      ArrayList<String> llcs = account.getLLCs();
      LLCWindow viewLLC = new LLCWindow(this.user, llcs);
    } // End if

     //************************************//

    //If back button is pressed in input frame
    if(e.getSource() == backToTax){
      inputFrame.dispose();
      this.pay = false;
      this.llc = false;
      TaxWindow tax = new TaxWindow(this.user);
    }
  } // End actionPerformed() method

 

} // End of class