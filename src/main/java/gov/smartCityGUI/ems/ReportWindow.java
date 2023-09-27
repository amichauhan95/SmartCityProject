// package gov.smartCityGUI.ems;

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import java.io.*;

// import gov.smartCityGUI.admin.models.*;

// public class ReportWindow implements ActionListener {
//   JFrame frame;
//   JTextArea rTextArea;
//   JTextField ftext, ltext, etext;
//   JButton backButton, submitButton;
//   JLabel title, flabel, llabel, elabel, rlabel;
//   User user;
//   FileWriter fw = null;

//   public ReportWindow(User user) {
//     filloutForm();
//   }

//   public void filloutForm() {
    
//     //frame stuff
//     frame = new JFrame("EMS | Report");
//     frame.setSize(900, 800);
//     frame.setLayout(null);
//     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//     frame.setResizable(false);
//     frame.getContentPane().setBackground(new Color(75,75,75));
//     frame.setVisible(true);
    
//     //title stuff
//     title = new JLabel("File a Report");
//     title.setFont(new Font("Arial", Font.PLAIN, 30));
//     title.setForeground(Color.white);
//     title.setSize(300, 30);
//     title.setLocation(300, 30);
//     frame.add(title);
    
//     //label stuff
//     flabel = new JLabel("First Name: ");
//     flabel.setFont(new Font("Arial", Font.PLAIN, 15));
//     flabel.setForeground(Color.white);
//     flabel.setSize(100, 20);
//     flabel.setLocation(100, 100);
//     frame.add(flabel);
    
//     llabel = new JLabel("Last Name: ");
//     llabel.setFont(new Font("Arial", Font.PLAIN, 15));
//     llabel.setForeground(Color.white);
//     llabel.setSize(100, 20);
//     llabel.setLocation(100, 150);
//     frame.add(llabel);
    
//     elabel = new JLabel("Email: ");
//     elabel.setFont(new Font("Arial", Font.PLAIN, 15));
//     elabel.setForeground(Color.white);
//     elabel.setSize(100, 20);
//     elabel.setLocation(100, 200);
//     frame.add(elabel);
    
//     rlabel = new JLabel("Make your report here: ");
//     rlabel.setFont(new Font("Arial", Font.PLAIN, 15));
//     rlabel.setForeground(Color.white);
//     rlabel.setSize(200, 100);
//     rlabel.setLocation(100, 300);
//     frame.add(rlabel);

//     // text area stuff
//     rTextArea = new JTextArea();
//     rTextArea.setFont(new Font("Arial", Font.PLAIN, 15));
//     rTextArea.setSize(500, 150);
//     rTextArea.setLocation(100, 400);
//     rTextArea.setLineWrap(true);
//     frame.add(rTextArea);

//     // text field stuff
//     ftext = new JTextField();
//     ftext.setFont(new Font("Arial", Font.PLAIN, 15));
//     ftext.setBackground(Color.white);
//     ftext.setSize(190, 20);
//     ftext.setLocation(200, 100);
//     frame.add(ftext);

//     ltext = new JTextField();
//     ltext.setFont(new Font("Arial", Font.PLAIN, 15));
//     ltext.setSize(190, 20);
//     ltext.setLocation(200, 150);
//     frame.add(ltext);

//     etext = new JTextField();
//     etext.setFont(new Font("Arial", Font.PLAIN, 15));
//     etext.setSize(190, 20);
//     etext.setLocation(200, 200);
//     frame.add(etext);

//     // button stuff
//     backButton = new JButton("Back");
//     backButton.setFont(new Font("Arial", Font.PLAIN, 15));
//     backButton.setBackground(Color.white);
//     backButton.setSize(100, 40);
//     backButton.setLocation(200, 600);
//     backButton.addActionListener(this);
//     frame.add(backButton);
    
//     submitButton = new JButton("Submit");
//     submitButton.setFont(new Font("Arial", Font.PLAIN, 15));
//     submitButton.setBackground(Color.white);
//     submitButton.setSize(100, 40);
//     submitButton.setLocation(400, 600);
//     submitButton.addActionListener(this);
//     frame.add(submitButton);
    
//   }

//   public void afterSubmitClicked() throws Exception {
//     //get data from each text field
//     String[] userData = {
//       ftext.getText(),
//       ltext.getText(),
//       etext.getText(),
//       rTextArea.getText()
//     };

//     try {
//       fw = new FileWriter("Scenes/EMS/data/reports.txt", true);
//       for (String s : userData) {
//         fw.write(s + ", ");
//       }
//       fw.flush();
//       fw.close();
//     } catch (Exception e) {
//       System.out.println("An error has occurred");
//       e.printStackTrace();
//     }
//   }
  
//   public void actionPerformed(ActionEvent e) {
//     if(e.getSource() == backButton){
//       frame.dispose();
//       EMSWindow ems = new EMSWindow(this.user);
//     }

//     if (e.getSource() == submitButton) {
//       try {
//         frame.dispose();
//         afterSubmitClicked();
//         ReportWindow reportWindow = new ReportWindow(this.user);
//       } catch (Exception error) {
//         System.out.println("An error has occurred");
//         error.printStackTrace();
//       }
//     }
//   }
// }