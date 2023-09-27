
package gov.smartCityGUI.transit;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.utilities.*;

public class TransitWindow implements ActionListener{

  User user;

  //Main Frame
  JFrame frame;
  JLabel label1;
  JLabel label2;
  JLabel label3;
  JButton backButton;
  JButton button1;
  JButton button2;
  JButton button3;
  
  //Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  
  public TransitWindow(User user){
    this.user = user;
  
    createFrames();
    createButtons();
    createLabels();
  }

  public void createLabels(){

    JLabel label1 = new JLabel("Label 1");
    label1.setBounds(60,80,250, 35);
    label1.setForeground(new Color(230,230,230));
    frame.add(label1);

    
    JLabel label2 = new JLabel("Label 2");
    label2.setBounds(60, 120, 250, 35);
    label2.setForeground(new Color(230,230,230));
    frame.add(label2);

    JLabel label3 = new JLabel("Label 3");
    label3.setBounds(60, 160, 250, 35);
    label3.setForeground(new Color(230,230,230));
    frame.add(label3);

  }

  public void inputFrame(){
    inputFrame = Gui.smallFrame("Input");
    inputFrame.setVisible(false);

    input = new JTextField();
    input.setBounds(75, 100, 150, 25);
    inputFrame.add(input);

    prompt = new JLabel("Enter ...");
    prompt.setBounds(75, 40, 200, 25);
    prompt.setForeground(new Color(255,255,255));
    inputFrame.add(prompt);

    enterButton = Gui.smallButton("Enter");
    enterButton.addActionListener(this);
    enterButton.setBounds(100, 145, 100, 25);
    enterButton.setBackground(new Color(235,235,235));
    inputFrame.add(enterButton);
  }

  public void createFrames(){
    frame = Gui.bigFrame("Window Title");
  }

  public void createButtons(){

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    button1 = Gui.genericButton("Button 1");
    button1.addActionListener(this);
    button1.setBounds(450, 80, 200, 35);
    frame.add(button1);

    button2 = Gui.genericButton("Button 2");
    button2.addActionListener(this);
    button2.setBounds(450, 130,200,35);
    frame.add(button2);

    button3 = Gui.genericButton("Button 3");
    button3.addActionListener(this);
    button3.setBounds(450,180, 200, 35);
    frame.add(button3);

    
    
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == backButton){
      frame.dispose();
      Menu menu = new Menu(this.user);
    }
    if(e.getSource() == button1){
   
    }
    if(e.getSource() == button2){

    }
    if(e.getSource() == button3){
    
    }
    if(e.getSource() == enterButton){
     
    }
    
  }
}