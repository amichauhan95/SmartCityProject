
// import java.util.*;
// import java.io.*;
// import java.io.BufferedReader;
// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import java.io.FileReader;
// import java.io.IOException;
// import javax.swing.BorderFactory;
// import javax.swing.JFrame;
// import javax.swing.JOptionPane;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTextArea;
// import java.awt.BorderLayout;
// import java.awt.FlowLayout; 
// import javax.swing.JButton;  

// public class Contact extends JFrame implements ActionListener { 
//   /**
//     * This method reads all the data from the file and outputs it
//     * @throws IOException if the file isn't read from properly
//   **/
//   public void showContacts() throws IOException {
//     JFrame frame = new JFrame("EMS | Contacts");  
//     JPanel panel = new JPanel();  
//     panel.setLayout(new FlowLayout());  
//     JLabel label = new JLabel("JFrame By Example");  
//     JButton button = new JButton();  
//     button.setText("Button");  
//     panel.add(label);  
//     panel.add(button);  
//     frame.add(panel);  
//     frame.setSize(200, 300);  
//     frame.setLocationRelativeTo(null);  
//     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
//     frame.setVisible(true);  

//     BufferedReader reader;

// 		try {
      
// 			reader = new BufferedReader(new FileReader("Scenes/EMS/data/contacts.txt"));
// 			String line = reader.readLine();

// 			while (line != null) {
// 				System.out.println(line);
// 				line = reader.readLine();
// 			}

// 			reader.close();
// 		} catch (IOException e) {
// 			e.printStackTrace();
//     }
//   }

//   public void actionPerformed(ActionEvent e) {
//     showContacts();
//   }
// }