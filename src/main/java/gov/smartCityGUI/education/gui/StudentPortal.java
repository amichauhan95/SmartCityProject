/**
  * Team Member(s) working on this class: Dylan Moran, Melih Kartal
  * Project: Smart City
  * @author: Melih Kartal
**/

package gov.smartCityGUI.education.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;
import java .io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.StringTokenizer;

import gov.smartCityGUI.education.models.*;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;

public class StudentPortal implements ActionListener {

	//Resources
 	User user;
	EducationSystem system;
	String major;
	SchoolEnrollment newEnrollment; // newEnrollment is used for new enrollee
	List<Course> allCourses;
	int totalCredits = 0;
	private int desiredNumCredits;
	ArrayList<String> listOfLines = new ArrayList<String>();
	private List<Credentials> credentialsList = new ArrayList<>();
	String type = "";
	ArrayList<String> schoolMatch = new ArrayList<String>();
	ArrayList<Double> tutionList = new ArrayList<Double>();
	private double studentGPA;
	
  	// Main Frame
  	JFrame frame;
  	JButton backButton;
	JButton applyButton;
	JButton aidButton;
	JButton housingButton;
	JButton enrollButton;
	JTextField creditsText;
	JPanel schoolPanel;

  	// Input Frame
  	JFrame inputFrame;
  	JLabel prompt;
  	JTextField input;
  	JButton enterButton;
  	JButton inputBack;

	// ***********************************************************************//

	/**
 	  * This constructor will assign resources and build the input frame
 	**/
	public StudentPortal(User user) {
	    this.system = new EducationSystem(this.user);
	    this.user = user;
		inputFrame();
  	} // End constructor

	// ***********************************************************************//

	/**
 	  * This method will create the labels for the main frame
 	**/
	public void createLabels() {

		//Matched schools label
		JLabel label = Gui.whiteLabel("Matched Schools");
		label.setBounds(300, 40, 200, 35);
		frame.add(label);

		//Desired credits label
		JLabel desCredsLabel = Gui.whiteLabel("Desired Credits");
		desCredsLabel.setBounds(50, 75, 150, 35);
		frame.add(desCredsLabel);
  	} // End createLabels() method

	// ***********************************************************************//

	/**
 	  * This method is used to create the text fields for the main frame
 	**/
	public void createTextFields(){

		creditsText = new JTextField();
		creditsText.setBounds(50, 110, 150, 35);
		frame.add(creditsText);
	} // End createTextFields() method

	// ***********************************************************************//

	/**
 	  * This method is used to create the input frame and all of its components to get the users ID 
 	**/
	public void inputFrame() {
		//Input frame
	     inputFrame = Gui.smallFrame("Student Portal");
	     inputFrame.setVisible(true);

		//Input text field
	     input = new JTextField();
	     input.setBounds(75, 100, 150, 25);
	     inputFrame.add(input);

		//Input description label
		 prompt = new JLabel("Enter User ID");
	     prompt.setBounds(75, 40, 200, 25);
	     prompt.setForeground(new Color(255, 255, 255));
	     inputFrame.add(prompt);

		//Enter button
	     enterButton = Gui.smallButton("Enter");
	     enterButton.addActionListener(this);
	     enterButton.setBounds(100, 145, 100, 25);
	     enterButton.setBackground(new Color(235, 235, 235));
	     inputFrame.add(enterButton);

		//Back button
		 inputBack = Gui.back();
		 inputBack.setBounds(100,175,100,25);
		 inputBack.addActionListener(this);
		 inputFrame.add(inputBack);
   } // End inputFrame() method

	// ***********************************************************************//

	/**
 	  * This method is used to create the main frame
 	**/
  	public void createFrames() {
    	frame = Gui.bigFrame("Student Portal");
  	} // End createFrames() method

	// ***********************************************************************//

	/**
 	  * This method is used to create the buttons for the main frame
 	**/
	public void createButtons() {
		//Bacik button
	    backButton = Gui.back();
	    backButton.addActionListener(this);
	    frame.add(backButton);

		//Apply button
		applyButton = Gui.genericButton("Apply");
		applyButton.setBounds(50, 150, 150, 35);
		applyButton.addActionListener(this);
		frame.add(applyButton);

		//Apply aid button
		aidButton = Gui.genericButton("<html>Apply for<br/>Financial Aid</html>");
		aidButton.addActionListener(this);
		aidButton.setBounds(50, 200, 150, 35);
		aidButton.setEnabled(false);
		frame.add(aidButton);

		//Apply housing button
		housingButton = Gui.genericButton("<html>Apply for<br/>housing</html>");
		housingButton.addActionListener(this);
		housingButton.setBounds(50, 240, 150, 35);
		housingButton.setEnabled(false);
		frame.add(housingButton);

		//Enroll button
		enrollButton = Gui.genericButton("Enroll");
		enrollButton.addActionListener(this);
		enrollButton.setBounds(50, 280, 150, 35);
		frame.add(enrollButton);
  	} // End createButtons() method

	// ***********************************************************************//

	/**
 	  * This method is used to create the scroll pane that will display all of the schools and estimated tuitions 
 	**/
	public void createScrollPane() {

		getSchools();
		schoolPanel = new JPanel();
	    schoolPanel.setBackground(new Color(100, 100, 100));
	    schoolPanel.setLayout(new BoxLayout(schoolPanel, BoxLayout.Y_AXIS));
	    schoolPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
	    int count = 0;
	
	    for (String schools : schoolMatch) {
			JLabel label = Gui.scrollLabel(schools);
			schoolPanel.add(label);
	      	count++;
	    }

		JScrollPane scroll = new JScrollPane(schoolPanel);
	    schoolPanel.setPreferredSize(new Dimension(350, count * 25));
	    scroll.setBounds(300, 75, 400, 300);
	    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
	    frame.add(scroll);
	} // End createScrollPane() method

	// ***********************************************************************//

	/**
 	  * This method is used to read the file and populate credentials in the list 
 	**/
	public void readFile() throws FileNotFoundException, IOException {
	    listOfLines.clear();
	    BufferedReader bufReader = new BufferedReader(
	        new FileReader("src/main/java/gov/smartCityGUI/education/static/credentials.txt"));
	    String line = bufReader.readLine();
	    while (line != null) {
	      listOfLines.add(line);
	      line = bufReader.readLine();
	    }
	    bufReader.close();
	} // End readFile() method

		// ***********************************************************************//

	/**
 	  * This method is used to get the GPA matched schools for the current user 
 	**/
	public void getSchools(){
		
		studentGPA = Double.parseDouble(credentialsList.get(credentialsList.size() - 1).getGPA());

        int usersAge = Integer.parseInt(credentialsList.get(credentialsList.size() - 1).getAge());

        if (usersAge >= 12 && usersAge <= 14) {

        	for (String key : system.s.middleSchoolsGPA.keySet()) {
            	double schoolGPA = system.s.middleSchoolsGPA.get(key);

            	if (studentGPA >= schoolGPA) {
                schoolMatch.add(key);

              	}
            }
            type = "middle";
        }

        if (usersAge > 14 && usersAge <= 17) {

        	for (String key : system.s.highSchoolsGPA.keySet()) {

            	double schoolGPA = system.s.highSchoolsGPA.get(key);

            	if (studentGPA >= schoolGPA) {
                	schoolMatch.add(key);
            	}
        	}
            type = "high";
        }

        if (usersAge > 17) {
        	for (String key : system.s.collegesGPA.keySet()) {

            	double schoolGPA = system.s.collegesGPA.get(key);

              	if (studentGPA >= schoolGPA) {
                	schoolMatch.add(key);
              	}
            }
            type = "college";
          }
	} // End getSchools() method

	// ***********************************************************************//

	/**
 	  * This method is used to handle button presses 
 	**/
	public void actionPerformed(ActionEvent e) {

		//If back button is pressed
	    if (e.getSource() == backButton) {
	    	frame.dispose();
	        EducationWindow menu = new EducationWindow(this.user);
    	} // End handle

		//**********************************************//

		//If back button is pressed in input frame
		if (e.getSource() == inputBack) {
	    	inputFrame.dispose();
	        EducationWindow menu = new EducationWindow(this.user);
    	} // End handle

		//**********************************************//
   
 		//If enter button is pressed in input frame
	    if(e.getSource() == enterButton){
	
			String user = input.getText();
			 
			try {
	            readFile();
	            boolean found = false;
	            if (listOfLines.isEmpty()) {
	       
					JOptionPane.showMessageDialog(null,"No credentials found. Please enter your credentials first.", "Notification",JOptionPane.INFORMATION_MESSAGE);
	        	} else {
	             
	            	for (String info : listOfLines) {
	                	if (info.substring(0, info.indexOf(",")).equals(user)) {
	                  	StringTokenizer token = new StringTokenizer(info, ",");
	                  		if (token.countTokens() >= 3) {
			                    String id = token.nextToken();
			                    String name = token.nextToken();
			                    String lastName = token.nextToken();
			                    String age = token.nextToken();
			                    String gpa = token.nextToken();
			
			                    Credentials existingUser = new Credentials();
			                    existingUser.setID(id);
			                    existingUser.setName(name);
			                    existingUser.setLastName(lastName);
			                    existingUser.setAge(age);
			                    existingUser.setGPA(gpa);
			                    credentialsList.add(existingUser);
	                  		}
							found = true;
		                  	
		                  	
		                  	//Show main frame with GPA schools
							inputFrame.dispose();
							createFrames();
							createButtons();
							createLabels();
							createTextFields();
							createScrollPane();
							//Have field to enter desired credits and apply button to see different prices.
							//Deal with financial aid and housing after.
	                	}
	            	}
	            	if (found == false)
	            	JOptionPane.showMessageDialog(null,"You have not enrolled yet.", "Notification",JOptionPane.INFORMATION_MESSAGE);
	            }
	        } catch (IOException io) {
	            io.printStackTrace();
	        }
     	} // End handle

		//**********************************************//

		//If apply button is pressed
		if(e.getSource() == applyButton){

			schoolPanel.removeAll();

			int count = 0;

			String credits = creditsText.getText();
			
			desiredNumCredits = Integer.parseInt(credits);
			
          	if (type == "middle") {
            	for (String schools : schoolMatch) {
              		double price = system.s.middleSchoolsPerCredit.get(schools);
              		tutionList.add(price * desiredNumCredits);

					JLabel label = Gui.scrollLabel(schools);
					JLabel priceLabel = Gui.scrollLabel("   $" + (price * desiredNumCredits));
					schoolPanel.add(label);
					schoolPanel.add(priceLabel);
					count++;
            	}
          	} else if (type == "high") {
            	for (String schools : schoolMatch) {
              		double price = system.s.highSchoolsPerCredit.get(schools);
              		tutionList.add(price * desiredNumCredits);

					JLabel label = Gui.scrollLabel(schools);
					JLabel priceLabel = Gui.scrollLabel("   $" + (price * desiredNumCredits));
					schoolPanel.add(label);
					schoolPanel.add(priceLabel);
              		count++;
            	}
          	} else {
            	for (String schools : schoolMatch) {
              		double price = system.s.collegesPerCredit.get(schools);
              		tutionList.add(price * desiredNumCredits);

					JLabel label = Gui.scrollLabel(schools);
					JLabel priceLabel = Gui.scrollLabel("   $" + (price * desiredNumCredits));
					schoolPanel.add(label);
					schoolPanel.add(priceLabel);
					count++;
            	}
          	} // End if-else chain
			schoolPanel.setPreferredSize(new Dimension(350, count * 50));
			schoolPanel.revalidate();
			schoolPanel.repaint();

			//Enable other buttons
			aidButton.setEnabled(true);
			housingButton.setEnabled(true);
		} // End handle

		//**********************************************//

		//If aid button is pressed
		if(e.getSource() == aidButton){

			schoolPanel.removeAll();
			int count = 0;

			if (studentGPA <= 3.0) {
            	if (type == "middle") {
            		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.middleSchoolsPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 10));
			
						JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 10))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;

              		}
            	} else if (type == "high") {
              		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.highSchoolsPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 10));
		
						JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 10))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;
              		}
            	} else {
            		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.collegesPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 10));
		
						JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 10))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;
              		}
            	}

          	} else if (studentGPA > 3.0 && studentGPA <= 3.5) {
            	if (type == "middle") {
              		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.middleSchoolsPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 20));
		
						JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 20))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;

              		}
           	 	} else if (type == "high") {
              		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.highSchoolsPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 20));
		
						JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 20))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;
              		}
            	} else if (studentGPA >= 3.0) {
              		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.collegesPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 20));
		
						JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 20))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;
              		}
            	}
          	} else if (studentGPA > 3.5) {
            	if (type == "middle") {
              		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.middleSchoolsPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 30));
		
						JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 30))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;
              		}
            	} else if (type == "high") {
              		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.highSchoolsPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 30));
						  
		                JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 30))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;
              		}
            	} else {
              		for (int i = 0; i < schoolMatch.size(); i++) {
		                double price = system.s.collegesPerCredit.get(schoolMatch.get(i));
		                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 30));
		
						JLabel label = Gui.scrollLabel(schoolMatch.get(i));
						JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", ((price * desiredNumCredits) - ((price * desiredNumCredits) / 30))));
						schoolPanel.add(label);
						schoolPanel.add(priceLabel);
						count++;
              		}
            	}
			}
			aidButton.setEnabled(false);
			schoolPanel.setPreferredSize(new Dimension(350, count * 50));
			schoolPanel.revalidate();
			schoolPanel.repaint();
  		} // End handle

		//**********************************************//

		//If housing button is pressed
		if(e.getSource() == housingButton){

			schoolPanel.removeAll();
			int count = 0;

			for (int i = 0; i < tutionList.size(); i++) {
            	tutionList.set(i, tutionList.get(i) + 15000);
          	}

			for (int i = 0; i < schoolMatch.size(); i++) {
				JLabel label = Gui.scrollLabel(schoolMatch.get(i));
				JLabel priceLabel = Gui.scrollLabel("   $" + String.format("%,.2f", tutionList.get(i)));
				schoolPanel.add(label);
				schoolPanel.add(priceLabel);
				count++;
			}
			housingButton.setEnabled(false);
			schoolPanel.setPreferredSize(new Dimension(350, count * 50));
			schoolPanel.revalidate();
			schoolPanel.repaint();
		} // End housing button

		//**********************************************//

		//If enroll button is pressed
		if(e.getSource() == enrollButton){
			frame.dispose();
			EnrollmentWindow enroll = new EnrollmentWindow(this.user, credentialsList);
		} // End handle
	} // End actionPerformed() method
} // End class
  