/**
  * Team Member(s) working on this class: Ami Chauhan, Melih Kartal, Dylan Moran
  * Project: Smart City
  * @author: Ami Chauhan
**/

package gov.smartCityGUI.education.gui;

import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PaymentDetails implements ActionListener {

  User user;
  EducationSystem system;

  // Main Frame
  JFrame frame;

  // Teacher Labels
  JLabel workdayBeginLabel; // input
  JLabel workDayEndLabel; // input
  JLabel yearsOfExpLabel; // input
  JLabel basePayLabel; // input
  JLabel infoLabel;
  JLabel infoLabel1;
  // Teacher Text Fields
  JTextField workdayBeginText;
  JTextField workDayEndText;
  JTextField yearsOfExpText;
  JTextField basePayText;

  JLabel prompt;
  JTextField input;
  JButton daysWorkedButton;
  JButton hourlyPayButton;
  JButton calPayButton;
  JButton calAnnualButton;
  JButton backButton;

  public PaymentDetails(User user, EducationSystem education) {
    this.user = user;
    this.system = education;
    createFrame();
    createTextFields();
    createLabels();
    createButtons();
  }

  public void createFrame() {
    frame = new JFrame("Pay Check Calculator");
    frame.setSize(475, 570);
    frame.setLayout(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.getContentPane().setBackground(new Color(75, 75, 75));
    frame.setVisible(true);
  }

  public void createTextFields() {
    workdayBeginText = new JTextField();
    workdayBeginText.setBounds(75, 140, 150, 25);
    frame.add(workdayBeginText);

    workDayEndText = new JTextField();
    workDayEndText.setBounds(250, 140, 150, 25);
    frame.add(workDayEndText);

    yearsOfExpText = new JTextField();
    yearsOfExpText.setBounds(75, 200, 150, 25);
    frame.add(yearsOfExpText);

    basePayText = new JTextField();
    basePayText.setBounds(250, 200, 150, 25);
    frame.add(basePayText);

  }

  public void createLabels() {
    workdayBeginLabel = new JLabel("Work Day Begin: ");
    workdayBeginLabel.setBounds(75, 115, 150, 25);
    workdayBeginLabel.setForeground(new Color(230, 230, 230));
    frame.add(workdayBeginLabel);

    workDayEndLabel = new JLabel("Work Day End: ");
    workDayEndLabel.setBounds(250, 115, 150, 25);
    workDayEndLabel.setForeground(new Color(230, 230, 230));
    frame.add(workDayEndLabel);

    yearsOfExpLabel = new JLabel("Years of Experience: ");
    yearsOfExpLabel.setBounds(75, 175, 150, 25);
    yearsOfExpLabel.setForeground(new Color(230, 230, 230));
    frame.add(yearsOfExpLabel);

    basePayLabel = new JLabel("Base Pay: ");
    basePayLabel.setBounds(250, 175, 150, 25);
    basePayLabel.setForeground(new Color(230, 230, 230));
    frame.add(basePayLabel);

    infoLabel = new JLabel("Click to Calculate: ");
    infoLabel.setBounds(170, 250, 200, 25);
    infoLabel.setForeground(new Color(230, 230, 230));
    frame.add(infoLabel);

    infoLabel1 = new JLabel("Enter Details: ");
    infoLabel1.setBounds(170, 75, 200, 25);
    infoLabel1.setForeground(new Color(230, 230, 230));
    frame.add(infoLabel1);

  }

  public void createButtons() {
    daysWorkedButton = new JButton("Days Worked");
    daysWorkedButton.setBounds(75, 300, 150, 25);
    daysWorkedButton.setBackground(new Color(235, 235, 235));
    daysWorkedButton.addActionListener(this);
    daysWorkedButton.setFocusable(false);
    frame.add(daysWorkedButton);

    hourlyPayButton = new JButton("Hourly Pay");
    hourlyPayButton.setBounds(250, 300, 150, 25);
    hourlyPayButton.setBackground(new Color(235, 235, 235));
    hourlyPayButton.addActionListener(this);
    hourlyPayButton.setFocusable(false);
    frame.add(hourlyPayButton);

    calAnnualButton = new JButton("Annual Pay");
    calAnnualButton.setBounds(75, 350, 150, 25);
    calAnnualButton.setBackground(new Color(235, 235, 235));
    calAnnualButton.addActionListener(this);
    calAnnualButton.setFocusable(false);
    frame.add(calAnnualButton);

    calPayButton = new JButton("Pay Check");
    calPayButton.setBounds(250, 350, 150, 25);
    calPayButton.setBackground(new Color(235, 235, 235));
    calPayButton.addActionListener(this);
    calPayButton.setFocusable(false);
    frame.add(calPayButton);

    backButton = new JButton("Back");
    backButton.setBounds(100, 495, 150, 25);
    backButton.setBackground(new Color(235, 235, 235));
    backButton.addActionListener(this);
    backButton.setFocusable(false);
    frame.add(backButton);
  }

  public void actionPerformed(ActionEvent e) {
    try{
      String workdayBegin = workdayBeginText.getText();
      String workDayEnd = workDayEndText.getText();
      String yearsOfExpStr = yearsOfExpText.getText();
      String basePayStr = basePayText.getText();
	  
	      Double basePay = Double.parseDouble(basePayStr);
	      int yearsOfExp = Integer.parseInt(basePayStr);
	  
	    system.p.setDaysWorked(system.p.calculateWorkingDaysBetween(workdayBegin, workDayEnd));    
	      int daysworked = system.p.getDaysWorked();
	      double hourlyPayAfterBonuses = system.p.calculatePayAfterBonuses(basePay, yearsOfExp);
	      double paycheck = hourlyPayAfterBonuses * daysworked * 7.5 ;
	      double annualPay = hourlyPayAfterBonuses * 365 * 7.5; // Assuming 365 workdays in a year
			  
	
	    if (e.getSource() == daysWorkedButton) {
	     
	      JOptionPane.showMessageDialog(frame.getComponent(0), "You worked: " + daysworked + " days.");
	    }
	    if (e.getSource() == hourlyPayButton) {
	     
	      JOptionPane.showMessageDialog(frame.getComponent(0), "Hourly pay (Includes Bonus): $" + hourlyPayAfterBonuses);
	    }
	    if (e.getSource() == calPayButton) {
	       
	      JOptionPane.showMessageDialog(frame.getComponent(0), "Paycheck for date range (Includes Bonus): $" + paycheck);
	
	    }
	    if (e.getSource() == calAnnualButton) {
	       
	      JOptionPane.showMessageDialog(frame.getComponent(0), "Annual pay (Includes Bonus): $" + annualPay);
	
	    }
	
	    if (e.getSource() == backButton) {
	      frame.dispose();
	      TeachersPortal view = new TeachersPortal(this.user, this.system);
	    }
	  
	  } catch(NumberFormatException nfe){
		  	System.out.println("-Number format Exception-\nExiting to Menu...\n ");
			frame.dispose();
			TeachersPortal view = new TeachersPortal(this.user, this.system);
	  }
  }
}