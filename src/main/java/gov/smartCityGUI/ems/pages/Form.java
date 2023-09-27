package gov.smartCityGUI.ems.pages;

import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

import gov.smartCityGUI.ems.util.*;

public class Form{

  public void careerForm(){

    JFrame f = new JFrame("Career Form");
  
    f.setSize(500, 400);
    f.setLocation(300, 200);
    f.setLayout(new FlowLayout());
    
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
      new JLabel("Position: "),
      new JLabel("Cover Letter: "),
    };

    JTextField firstText = new JTextField(20);
    JTextField lastText = new JTextField(20);
    JTextField emailText = new JTextField(20);
    JTextField phoneText = new JTextField(20);
    JTextField dobText = new JTextField(20);
    JTextField positionText = new JTextField(20);
    JTextArea letterText = new JTextArea(8, 20);

    JTextField[] inputList = new JTextField[]{
      firstText,
      lastText,
      emailText,
      phoneText,
      dobText,
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
    temp.add(positionText);
    panel.add(temp);
    
    temp = new JPanel();
    temp.add(labelList[6]);
    temp.add(letterText);
    panel.add(temp);

    temp = new JPanel();
    temp.add(submitButton);
    temp.add(exitButton);
    panel.add(temp);

    
    f.add(panel);
    f.setVisible(true);

    submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        String[] form = new String[7];
        int index = 0;
        for(JTextField temp : inputList){
          form[index] = temp.getText();
          index++;
        }
        form[6] = letterText.getText();

        if(FormValidation.checkCareerForm(form)){
          /* To-do: need to call a service takes form object*/
          
          /*-------------------------------------------------*/
          JOptionPane.showMessageDialog(f,"Thank you for applying!","Alert",JOptionPane.WARNING_MESSAGE);
          f.dispose();
        } else {
          JOptionPane.showMessageDialog(f,"Each section need to be filled out","Alert",JOptionPane.WARNING_MESSAGE);
        }

			}
		});

    exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        f.dispose();
			}
		});
  
  }
}