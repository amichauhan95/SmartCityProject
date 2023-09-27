
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

	public EnrollmentWindow(User user, List<Credentials> credentialsList) {
	    this.system = new EducationSystem(this.user);
		this.credentialsList = credentialsList;
	    this.user = user;
		inputFrame();
  	}

	// ***********************************************************************//

	public void createLabels() {

		JLabel majorLabel = Gui.whiteLabel("All Majors");
		majorLabel.setBounds(300, 50, 200, 25);
		frame.add(majorLabel);

  	}

	// ***********************************************************************//

	public void inputFrame() {
	     inputFrame = Gui.smallFrame("Enrollment");
	     inputFrame.setVisible(true);
	
	     input = new JTextField();
	     input.setBounds(75, 100, 150, 25);
	     inputFrame.add(input);
	
		 prompt = new JLabel("<html>Which School are you<br/>enrolling with?</html>");
	     prompt.setBounds(75, 40, 200, 25);
	     prompt.setForeground(new Color(255, 255, 255));
	     inputFrame.add(prompt);
	
	     enterButton = Gui.smallButton("Enter");
	     enterButton.addActionListener(this);
	     enterButton.setBounds(100, 145, 100, 25);
	     enterButton.setBackground(new Color(235, 235, 235));
	     inputFrame.add(enterButton);

		 inputBack = Gui.back();
		 inputBack.setBounds(100,175,100,25);
		 inputBack.addActionListener(this);
		 inputFrame.add(inputBack);
   }

	// ***********************************************************************//

  public void createFrames() {
    frame = Gui.bigFrame("Enrollment");
  }

	// ***********************************************************************//

	public void createButtons() {

		majorButton = Gui.genericButton("Choose Major");
		majorButton.setBounds(50, 100, 200, 25);
		majorButton.addActionListener(this);
		frame.add(majorButton);
	
	    backButton = Gui.back();
	    backButton.addActionListener(this);
	    frame.add(backButton);
  	}

	// ***********************************************************************//

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
	    }

		JScrollPane scroll = new JScrollPane(majorPanel);
	    majorPanel.setPreferredSize(new Dimension(350, count * 25));
	    scroll.setBounds(300, 75, 400, 300);
	    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
	    frame.add(scroll);
	}

		// ***********************************************************************//

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
	    }

		JScrollPane scroll = new JScrollPane(coursePanel);
	    coursePanel.setPreferredSize(new Dimension(350, count * 25));
	    scroll.setBounds(300, 75, 400, 300);
	    scroll.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
	    frame.add(scroll);
	}

	// ***********************************************************************//

	public void addCourses(){
		createFrames();
		createCoursePane();

		JLabel majorLabel = Gui.whiteLabel("Major: " + major);
		majorLabel.setBounds(50, 100, 200, 35);
		frame.add(majorLabel);

		JLabel courseLabel = Gui.whiteLabel("Add Courses: ");
		courseLabel.setBounds(50, 135, 200, 35);
		frame.add(courseLabel);

		courseText = new JTextField();
		courseText.setBounds(50, 170, 200, 35);
		frame.add(courseText);

		courseButton = Gui.genericButton("Add Course");
		courseButton.addActionListener(this);
		courseButton.setBounds(50, 210, 200, 35);
		frame.add(courseButton);

		totalCreds = Gui.whiteLabel("Total credits: " + totalCredits);
		totalCreds.setBounds(50, 300, 200, 35);
		frame.add(totalCreds);

		backButton = Gui.back();
		backButton.addActionListener(this);
		frame.add(backButton);
	}

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
	}

	// ***********************************************************************//

	public void actionPerformed(ActionEvent e) {
	    if (e.getSource() == backButton) {
	    	frame.dispose();
	        StudentPortal portal = new StudentPortal(this.user);
    	}

		 if (e.getSource() == inputBack) {
	    	inputFrame.dispose();
	        StudentPortal portal = new StudentPortal(this.user);
    	}
   
 
	    if(e.getSource() == enterButton){
	
			 String school = input.getText();
			 newEnrollment = new SchoolEnrollment(school);
			try {
            	readFile();
          	} catch (IOException io) {
            	io.printStackTrace();
          	}

			for (int i = 0; i < newList.size(); i++) {
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
            Files.write(output, newList);
          } catch (Exception ex) {
            ex.printStackTrace();
          }
			 inputFrame.dispose();
			 createFrames();
			 createLabels();
			 createButtons();
			 createMajorPane();
     	}

		if(e.getSource() == majorButton){

			ButtonModel selectedButton = buttonGroup.getSelection();

			if (selectedButton != null) {
			    major = selectedButton.getActionCommand();
				frame.dispose();
				addCourses();
			    
			} else {
			  
			}

		}

		if(e.getSource() == courseButton){
			String enteredCourse = courseText.getText();
			totalCredits += 3;
			courseText.setText("");
			newEnrollment.addCourse(enteredCourse);

			createCoursePane();
			coursePanel.revalidate();
			coursePanel.repaint();

			totalCreds.setText("Total credits: " + totalCredits);
			
		}

  }
}