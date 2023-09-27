// package gov.smartCityGUI.ems.pages;

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;
// import javax.swing.JFrame;
// import java.util.List;
// import java.util.ArrayList;
// import java.io.*;
// import java.nio.charset.Charset;
// import java.nio.file.Files;
// import gov.smartCityGUI.admin.models.User;
// import gov.smartCityGUI.utilities.*;



// public class CareerPage implements ActionListener {
  
//   private final String CAREERS_FILE_PATH = "src/main/java/gov/smartCityGUI/ems/data/careers.txt";
//   User user;

//   // Main Frame

//   JButton backButton;
//   JButton homeButton;
//   JButton button2;
//   JButton button3;
//   JButton searchButton;
//   JButton searchKeyWordButton;
//   JButton tourismButton;

//   // img section
//   JFrame frame;
//   JPanel centerPanel = new JPanel();
//   JScrollPane scrlpanel;
//   JPanel topPanel;
//   JPanel searchPanel;
//   JTextField searchText;

//   JComboBox cb;

//   public CareerPage(User user) {
//     this.user = user;
//     createFrames();
//   }

//   public void createHeader(){
    
//     topPanel = new JPanel();
//     backButton = Gui.back();
//     backButton.addActionListener(this);
//     topPanel.add(backButton);
//     topPanel.add(Box.createRigidArea(new Dimension(400, 0)));

//     frame.add(topPanel, BorderLayout.NORTH);
//   }

//   public void createFrames() {
//     frame = new JFrame();  
//     createHeader();

    
//     JLabel label = new JLabel("Career Listing");

//     // read career file 
//     String [][] data = readCareerFile();
//     String column[]={"department", "company", "position", "city", "state", "annual_salary"};   

//     // put data into a table
//     JTable jt=new JTable(data,column);  
//     JScrollPane sp = new JScrollPane(jt); 
    
//     JPanel centerPanel = new JPanel();
//     centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
//     label.setAlignmentX(Component.CENTER_ALIGNMENT);

//     centerPanel.add(label);
//     centerPanel.add(sp);
    
//     frame.add(centerPanel, BorderLayout.CENTER);
        
//     frame.setSize(600,500);    
//     frame.setVisible(true);     
//   }

//   /* Reading data from careers.txt */
//   public String [][] readCareerFile(){
//     List<String> list = new ArrayList<String>(); 
//     File file = new File(CAREERS_FILE_PATH);
//     if(file.exists()){
//         try { 
//             list = Files.readAllLines(file.toPath(),Charset.defaultCharset());
//         } catch (Exception ex) {
//             ex.printStackTrace();
//         }

//       String [][] data = new String[list.size()][];
//       int index = 0;
//       for(String line : list){
//         String [] res = line.split(",");
//         data[index] = res;
//         index++;
//       }
//       return data;
//     }
//     return null;
//   }


//   // Todo
//   public void actionPerformed(ActionEvent e) {
//     if (e.getSource() == backButton) {
//       frame.dispose();
//       /* Todo - it needs to return back some page*/
//       EMSWindow ems = new EMSWindow(this.user);
//     }

//     if (e.getSource() == applyNowButton) {
//       frame.dispose();
//       Form form = new Form(this.user);
//     }
//   }



// }