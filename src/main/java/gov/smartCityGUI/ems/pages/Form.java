package gov.smartCityGUI.ems.pages;

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
import gov.smartCityGUI.ems.pages.*;
import gov.smartCityGUI.ems.util.*;
import gov.smartCityGUI.admin.models.*;

public class Form{
  User user;

  /**
    * public Form(User user) initializes the current user and calls a method that opens the career form fill out
    * @param User user represents the current instance of the user
  **/
  public Form(User user) {
    this.user = user;
    careerForm();
  }

  /**
    * public void careerForm() displays the form fill out for the user
  **/
  public void careerForm(){

    JFrame f = new JFrame("EMS | Career Form");
  
    f.setSize(500, 450);
    f.setLocation(300, 200);
    f.setLayout(new FlowLayout());
	f.setResizable(false);
    
    f.setVisible(true);
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    JLabel title = new JLabel ("Career Form");
    title.setFont(new Font(title.getFont().getName(), title.getFont().getStyle(), 20));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    
    JButton submitButton = new JButton ("Apply");
    JButton exitButton = new JButton ("Exit");
    
    JLabel[] labelList = new JLabel[]{
      new JLabel("First Name: "),
      new JLabel("Last Name: "),
      new JLabel("Email: "),
      new JLabel("Phone: "),
      new JLabel("Date of Birth: "),
      new JLabel("Company: "),
      new JLabel("Position: "),
      new JLabel("Cover Letter: "),
    };

    JTextField firstText = new JTextField(20);
    JTextField lastText = new JTextField(20);
    JTextField emailText = new JTextField(20);
    JTextField phoneText = new JTextField(20);
    JTextField dobText = new JTextField(20);
    JTextField compText = new JTextField(20);
    JTextField positionText = new JTextField(20);
    JTextArea letterText = new JTextArea(8, 30);

    JTextField[] inputList = new JTextField[]{
      firstText,
      lastText,
      emailText,
      phoneText,
      dobText,
      compText,
      positionText
    };
    
    dobText.setText("mm/dd/yyyy");
    letterText.setLineWrap(true);

    panel.add(title);
    panel.add(Box.createRigidArea(new Dimension(0, 12)));
    
    JPanel temp = new JPanel();
    temp.add(labelList[0]);
    temp.add(firstText);
    panel.add(temp);

    temp = new JPanel();
    temp.add(labelList[1]);
    temp.add(lastText);
    panel.add(temp);
    
    temp = new JPanel();
    temp.add(Box.createRigidArea(new Dimension(40, 0)));
    temp.add(labelList[2]);
    temp.add(emailText);
    panel.add(temp);
    
    temp = new JPanel();
    temp.add(Box.createRigidArea(new Dimension(35, 0)));
    temp.add(labelList[3]);
    temp.add(phoneText);
    panel.add(temp);
    
    temp = new JPanel();
    temp.add(labelList[4]);
    temp.add(dobText);
    panel.add(temp);

    temp = new JPanel();
    temp.add(Box.createRigidArea(new Dimension(33, 0)));
    temp.add(labelList[5]);
    temp.add(compText);
    panel.add(temp);
    
    temp = new JPanel();
    temp.add(Box.createRigidArea(new Dimension(31, 0)));
    temp.add(labelList[6]);
    temp.add(positionText);
    panel.add(temp);
    
    temp = new JPanel();
    temp.add(labelList[7]);
    temp.add(letterText);
    panel.add(temp);

    temp = new JPanel();
    temp.add(submitButton);
    temp.add(exitButton);
    panel.add(temp);

    
    f.add(panel);
    f.setVisible(true);

    submitButton.addActionListener(new ActionListener(){
      /**
        * public void actionPerformed(ActionEvent e) gets each text inputted by the user and checks 
        * @param ActionEvent e is the action performed by the user
      **/
			public void actionPerformed(ActionEvent e){
        String[] form = new String[8];
        int index = 0;
        for(JTextField temp : inputList){
          form[index] = temp.getText();
          index++;
        }
        form[7] = letterText.getText();

        if(FormValidation.checkCareerForm(form)){
          try {
            FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/ems/data/applicants.txt", true);
			  String[] formWrite = new String[8];
        int indexWrite = 0;
        for(JTextField temp : inputList){
          formWrite[indexWrite] = temp.getText();
          indexWrite++;
        }
            for (String s : formWrite) {
              fw.write(s + ", ");
            }
            fw.flush();
            fw.close();
          } catch (Exception ex) {
            System.out.println("An error has occurred");
            ex.printStackTrace();
          }
          
          JOptionPane.showMessageDialog(f,"Thank you for applying!","Alert",JOptionPane.WARNING_MESSAGE);
          f.dispose();
			CareerPage cp = new CareerPage(user);
			
        } else {
          JOptionPane.showMessageDialog(f,"Each section need to be filled out","Alert",JOptionPane.WARNING_MESSAGE);
        }

			}
		});

    exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        		f.dispose();
				CareerPage cp = new CareerPage(user);
			}
		});
  
  }
}