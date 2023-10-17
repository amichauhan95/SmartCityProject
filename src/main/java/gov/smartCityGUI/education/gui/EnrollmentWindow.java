
/**
  * Team Member(s) working on this class: Ami Chauhan, Melih Kartal, Dylan Moran
  * Project: Smart City
  * @author: Ami Chauhan
**/

package gov.smartCityGUI.education.gui;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import gov.smartCityGUI.education.models.*;
import gov.smartCityGUI.education.service.*;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;


public class EnrollmentWindow implements ActionListener, listOfMajors {

	//Resources
 	User user;
	EducationSystem system;
	String major;
	SchoolEnrollment newEnrollment; // newEnrollment is used for new enrollee
	List<Course> allCourses;
	private List<Credentials> credentialsList = new ArrayList<>();
	ArrayList<String> newList = new ArrayList<String>();
	int totalCredits = 0;
	
  	// Main Frame
  	JFrame frame;
  	JButton backButton;
	JButton majorButton;
	ButtonGroup buttonGroup;
	JLabel totalCreds;

	JTextField courseText;
	JButton courseButton;
	JPanel coursePanel;

  	// Input Frame
  	JFrame inputFrame;
  	JLabel prompt;
  	JTextField input;
  	JButton enterButton;
  	JButton inputBack;

	// ***********************************************************************//

	/**
      * This constructor is used to assign resources and create the input frame to get the users ID
    **/
	public EnrollmentWindow(User user, List<Credentials> credentialsList) {
	    this.system = new EducationSystem(this.user);
		this.credentialsList = credentialsList;
	    this.user = user;
		inputFrame();
  	} // End constructor

	// ***********************************************************************//

	/**
 	  * This method is used to create the labels for the window
 	**/
	public void createLabels() {

		JLabel majorLabel = Gui.whiteLabel("All Majors");
		majorLabel.setBounds(300, 50, 200, 25);
		frame.add(majorLabel);

  	}

	// ***********************************************************************//

	/**
 	  * This method is used to create the input frame and all of it's components
 	**/
	public void inputFrame() {
		//Input frame
	     inputFrame = Gui.smallFrame("Enrollment");
	     inputFrame.setVisible(true);

		//input text field
	     input = new JTextField();
	     input.setBounds(75, 100, 150, 25);
	     inputFrame.add(input);

		//Input description label
		 prompt = new JLabel("<html>Which School are you<br/>enrolling with?</html>");
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
   }

	// ***********************************************************************//

	/**
 	  * This method is used to create the main frame
 	**/
  	public void createFrames() {
    	frame = Gui.bigFrame("Enrollment");
  	} // End createFrames() method

	// ***********************************************************************//

	/**
 	  * This method is used to create the buttons for the window
 	**/
	public void createButtons() {

		//Choose major button
		majorButton = Gui.genericButton("Choose Major");
		majorButton.setBounds(50, 100, 200, 25);
		majorButton.addActionListener(this);
		frame.add(majorButton);

		//Back button
	    backButton = Gui.back();
	    backButton.addActionListener(this);
	    frame.add(backButton);
  	} // End createButtons() method

	// ***********************************************************************//

	/**
 	  * This method is used to create the scroll pane to list all of the majors
 	**/
	public void createMajorPane() {

		JPanel majorPanel = new JPanel();
	    majorPanel.setBackground(new Color(100, 100, 100));
	    majorPanel.setLayout(new BoxLayout(majorPanel, BoxLayout.Y_AXIS));
	    majorPanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
	    JLabel name;
		buttonGroup = new ButtonGroup();
	    int count = 0;
	
	    for (String major : allMajors) {
			JRadioButton radioButton = new JRadioButton(major);
			radioButton.setActionCommand(major);
			radioButton.setForeground(Color.white);
			radioButton.setBackground(new Color(100,100,100));
			
			buttonGroup.add(radioButton);
			majorPanel.add(radioButton);
	      	count++;
	    } // End for loop

		JScrollPane scroll = new JScrollPane(majorPanel);
	    majorPanel.setPreferredSize(new Dimension(350, count * 25));
	    scroll.setBounds(300, 75, 400, 300);
	    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
	    frame.add(scroll);
	} // End createMajorPane() method

		// ***********************************************************************//

	/**
 	  * This method is used to create the course pane to display all of the courses that are added
 	**/
	public void createCoursePane() {

		allCourses = newEnrollment.getCourses();

		coursePanel = new JPanel();
	    coursePanel.setBackground(new Color(100, 100, 100));
	    coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
	    coursePanel.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
	    int count = 0;
	
	    for (Course courses : allCourses) {
			JLabel coursesLabel = Gui.scrollLabel(courses.getCourseName());
			JLabel courseCredits = Gui.scrollLabel("   " + courses.getCourseCredits() + " credits");
			coursePanel.add(coursesLabel);
			coursePanel.add(courseCredits);
	      	count++;
	    } // End for loop

		JScrollPane scroll = new JScrollPane(coursePanel);
	    coursePanel.setPreferredSize(new Dimension(350, count * 25));
	    scroll.setBounds(300, 75, 400, 300);
	    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
	    frame.add(scroll);
	} // End createCoursePane()

	// ***********************************************************************//

	/**
 	  * This method is used to create the new frame for adding courses and all of its components
 	**/
	public void addCourses(){
		createFrames();
		createCoursePane();

		//Major label
		JLabel majorLabel = Gui.whiteLabel("Major: " + major);
		majorLabel.setBounds(50, 100, 200, 35);
		frame.add(majorLabel);

		//Add courses label
		JLabel courseLabel = Gui.whiteLabel("Add Courses: ");
		courseLabel.setBounds(50, 135, 200, 35);
		frame.add(courseLabel);

		//Course text field
		courseText = new JTextField();
		courseText.setBounds(50, 170, 200, 35);
		frame.add(courseText);

		//Add course button
		courseButton = Gui.genericButton("Add Course");
		courseButton.addActionListener(this);
		courseButton.setBounds(50, 210, 200, 35);
		frame.add(courseButton);

		//Total credits label
		totalCreds = Gui.whiteLabel("Total credits: " + totalCredits);
		totalCreds.setBounds(50, 300, 200, 35);
		frame.add(totalCreds);

		//Back button
		backButton = Gui.back();
		backButton.addActionListener(this);
		frame.add(backButton);
	} // End addCourses() method

	/**
 	  * This method is used to read the file and populate the list with data
 	**/
	public void readFile() throws FileNotFoundException, IOException {
	    newList.clear();
	    BufferedReader bufReader = new BufferedReader(
	        new FileReader("src/main/java/gov/smartCityGUI/education/static/credentials.txt"));
	    String line = bufReader.readLine();
	    while (line != null) {
	      newList.add(line);
	      line = bufReader.readLine();
	    }
	    bufReader.close();
	} // End readFile() method

	// ***********************************************************************//

	/**
 	  * This method is used to handle button presses
 	**/
	public void actionPerformed(ActionEvent e) {

		//If back button is pressed
	    if (e.getSource() == backButton) {
	    	frame.dispose();
	        StudentPortal portal = new StudentPortal(this.user);
    	} // End handle

		//If back button is pressed in input frame
		 if (e.getSource() == inputBack) {
	    	inputFrame.dispose();
	        StudentPortal portal = new StudentPortal(this.user);
    	} // End handle
   
 		//If enter button is pressed in input frame
	    if(e.getSource() == enterButton){
	
			String school = input.getText(); 					// Read the chosen school
			newEnrollment = new SchoolEnrollment(school); 		// create a new enrollment
			try {
            	readFile();										// Populate credentials
          	} catch (IOException io) {
            	io.printStackTrace();
          	}

			for (int i = 0; i < newList.size(); i++) { 	 				// Loop to update school associated with user
            	if ((newList.get(i).substring(0, 1).equals(User.getInstance().getID()))) {
              		String temp = newList.get(i);
              		newList.set(i, (temp + "," + newEnrollment.getName()));
            	}
          	}
          	for (String each : newList) {
            	System.out.println(each);
          	}
          	Path output = Paths.get("src/main/java/gov/smartCityGUI/education/static/credentials.txt");
          	try {
           		Files.write(output, newList); 				// Write updated user infor to credentials.txt
          	} catch (Exception ex) {
            	ex.printStackTrace();
          	}
			 inputFrame.dispose(); 				// Close input frame and open main frame
			 createFrames();
			 createLabels();
			 createButtons();
			 createMajorPane();
     	} // End handle

		//If Major button is pressed
		if(e.getSource() == majorButton){

			ButtonModel selectedButton = buttonGroup.getSelection();

			if (selectedButton != null) {
			    major = selectedButton.getActionCommand();
				frame.dispose();
				addCourses(); 
			}
		} // End handle

		//If add course button is pressed
		if(e.getSource() == courseButton){
			String enteredCourse = courseText.getText();
			totalCredits += 3;
			courseText.setText("");
			newEnrollment.addCourse(enteredCourse); 		//add the chosen course to enrollment

			createCoursePane(); 					// Update course pane
			coursePanel.revalidate();
			coursePanel.repaint();

			totalCreds.setText("Total credits: " + totalCredits); 		//  Update total credits
			
		} //End handle
  	} // End actionPerformed() method
} // End class