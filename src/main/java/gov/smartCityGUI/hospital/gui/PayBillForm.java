package gov.smartCityGUI.hospital.gui;

/*
@author Ami Chauhan
Project: Smart City
@date 9/19/2023
I recieved help from: Dylan Moran
*/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.banking.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class PayBillForm implements ActionListener {

  User user;
  HospitalSystem system;

  // Main Frame
  JFrame frame;
  JLabel label1;
  JLabel label2;
  JLabel label3;

  JButton backButton;

  JButton button1;
  JButton button2;
  JButton button3;

  // Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  JButton backFromInput;

  // Input PayFrame
  JLabel amountLabel;
  JTextField amount;
  JLabel confirmLabel;
  JTextField confirm;
  JButton payButton;

  BankAccount account = new BankAccount();

  /**
    * public PayBillForm(User user, HospitalSystem system) does ...
    * @param User user represents ...
    * @param HospitalSystem system represents ...
  **/
  public PayBillForm(User user, HospitalSystem system) {
    this.user = user;
    this.system = system;

    inputFrame();
  }

  
  public void createLabels() {

    JLabel label1 = new JLabel("Pay Bill");
    label1.setBounds(275, 35, 250, 40);
    label1.setForeground(new Color(230, 230, 230));
    frame.add(label1);

  }

  public void inputFrame() {
    inputFrame = Gui.smallFrame("Pay Bill");
    inputFrame.setVisible(true);

    input = new JTextField();
    input.setBounds(75, 100, 150, 25);
    inputFrame.add(input);

    prompt = new JLabel("Enter Appointment ID");
    prompt.setBounds(75, 40, 200, 25);
    prompt.setForeground(new Color(255, 255, 255));
    inputFrame.add(prompt);

    enterButton = Gui.smallButton("Enter");
    enterButton.addActionListener(this);
    enterButton.setBounds(100, 145, 100, 25);
    enterButton.setBackground(new Color(235, 235, 235));
    inputFrame.add(enterButton);

    backFromInput = Gui.smallButton("Back");
    backFromInput.addActionListener(this);
    backFromInput.setBounds(100, 175, 100, 25);
    backFromInput.setBackground(new Color(235, 235, 235));
    inputFrame.add(backFromInput);
  }

  public void inputPayFrame() {
    amountLabel = new JLabel("Enter Amount:");
    amountLabel.setBounds(75, 115, 150, 25);
    amountLabel.setForeground(new Color(255, 255, 255));
    frame.add(amountLabel);

    amount = new JTextField();
    amount.setBounds(75, 140, 150, 25);
    frame.add(amount);

    confirmLabel = new JLabel("Enter \"CONFIRM\" :");
    confirmLabel.setBounds(75, 170, 150, 25);
    confirmLabel.setForeground(new Color(255, 255, 255));
    frame.add(confirmLabel);

    confirm = new JTextField();
    confirm.setBounds(75, 200, 150, 25);
    frame.add(confirm);

    payButton = new JButton("Pay");
    payButton.setBounds(75, 250, 150, 25);
    payButton.setBackground(new Color(235, 235, 235));
    payButton.addActionListener(this);
    payButton.setFocusable(false);
    frame.add(payButton);
  }

  public void createFrames() {
    frame = Gui.bigFrame("Window Title");
  }

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

  }

  /*
  */
  public void createScrollPane(String[] parts) {

    JPanel reportsPanel = new JPanel();
    reportsPanel.setBackground(new Color(100, 100, 100));
    reportsPanel.setLayout(new BoxLayout(reportsPanel, BoxLayout.Y_AXIS));
    reportsPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    JLabel appointmentID;
    JLabel symptoms;
    JLabel treatment;
    JLabel bill;
    int count = 0;

    appointmentID = Gui.scrollLabel("Appointment ID: " + parts[0]);
    symptoms = Gui.scrollLabel("Symptoms: " + parts[1]);
    treatment = Gui.scrollLabel("Treatment: " + parts[2]);
    bill = Gui.scrollLabel("Bill: $" + parts[3]);
    JLabel space = Gui.scrollLabel("              ");
    reportsPanel.add(appointmentID);
    reportsPanel.add(symptoms);
    reportsPanel.add(treatment);
    reportsPanel.add(bill);
    reportsPanel.add(space);

    reportsPanel.setPreferredSize(new Dimension(350, 100));
    JScrollPane scroll = new JScrollPane(reportsPanel);
    scroll.setBounds(250, 75, 400, 300);
    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
    frame.add(scroll);

    inputPayFrame();

  } // End createScrollPane() method

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backButton) {
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }
    if (e.getSource() == backFromInput) {
      inputFrame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }
    if (e.getSource() == payButton) {
      String appointmentID = input.getText();
      String check = confirm.getText();
      String chargeStr = amount.getText();
      double charge = Double.parseDouble(chargeStr);
      double totalBill = system.b2.payBill(appointmentID);
      if (totalBill <= 0) {
        JOptionPane.showMessageDialog(null, "Bill is not Due", "Notification", JOptionPane.INFORMATION_MESSAGE);
        System.out.println("\nBill is not Due");
      } else {
        totalBill = totalBill - charge;
        if (check.equals("CONFIRM")) {
          JOptionPane.showMessageDialog(null, "\nTransaction for $" + charge + " is processing...", "Notification",
              JOptionPane.INFORMATION_MESSAGE);
          System.out.println("\nTransaction for $" + charge + " is processing...");
          if (totalBill >= 0) {
            if (account.charge(user, charge)) {
              system.b2.updateFees(appointmentID, totalBill);
              JOptionPane.showMessageDialog(null, "\nTransaction is Successful", "Notification",
                  JOptionPane.INFORMATION_MESSAGE);
            } else {
              JOptionPane.showMessageDialog(null, "\nTransaction for $" + charge + " cannot be processed",
                  "Notification", JOptionPane.INFORMATION_MESSAGE);
            }
          } else {

            JOptionPane.showMessageDialog(null, "\nTransaction for $" + charge + " cannot be processed", "Notification",
                JOptionPane.INFORMATION_MESSAGE);
          }
        } else {

          JOptionPane.showMessageDialog(null, "\nTransaction for $" + charge + " cannot be processed", "Notification",
              JOptionPane.INFORMATION_MESSAGE);

        }
      }
      frame.dispose();
      HospitalWindow hospital = new HospitalWindow(this.user);
    }

    if (e.getSource() == enterButton) {
      String appointmentID = input.getText();
      String parts[] = system.b2.viewPatientsReport(appointmentID);
      if (parts == null || parts[2].equals("N/A")) {
        JOptionPane.showMessageDialog(null, "There is no report filed under that appointment ID", "Notification",
            JOptionPane.INFORMATION_MESSAGE);
      } else {
        inputFrame.dispose();
        createFrames();
        createButtons();
        createLabels();
        createScrollPane(parts);
      }
    }

  }
}