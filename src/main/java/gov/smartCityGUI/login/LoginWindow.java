package gov.smartCityGUI.login;

/*
@author Dylan Moran
Project: Smart City
@date 9/19/2023
I recieved help from: N/A
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;
import gov.smartCityGUI.menu.Menu;

public class LoginWindow implements ActionListener {

	// UI Components
	JFrame frame;
	JLabel idLabel;
	JLabel lastNameLabel;
	JTextField userIDText;
	JTextField lastNameText;
	JButton button;
	JButton backButton;

	// ***********************************************************************//

	/*
	 * The default constructor is used to create the frame and other components for
	 * the window
	 */
	public LoginWindow() {
		createFrame();
		createTextFields();
		createLabels();
		createButtons();
	}

	// ***********************************************************************//

	/*
	 * This method is used to build the frame for the window
	 */
	public void createFrame() {
		frame = new JFrame("Login");
		frame.setSize(300, 250);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(75, 75, 75));
		frame.setVisible(true);
	}

	// ***********************************************************************//

	/*
	 * This method is used to create and position the text fields in the frame
	 */
	public void createTextFields() {
		userIDText = new JTextField();
		userIDText.setBounds(75, 40, 150, 25);
		frame.add(userIDText);

		lastNameText = new JTextField();
		lastNameText.setBounds(75, 100, 150, 25);
		frame.add(lastNameText);
	}

	// ***********************************************************************//

	/*
	 * This method is used to create and position the labels in the frame
	 */
	public void createLabels() {
		idLabel = new JLabel("User ID:");
		idLabel.setBounds(75, 15, 150, 25);
		idLabel.setForeground(new Color(255, 255, 255));
		frame.add(idLabel);

		lastNameLabel = new JLabel("Last Name:");
		lastNameLabel.setBounds(75, 75, 150, 25);
		lastNameLabel.setForeground(new Color(255, 255, 255));
		frame.add(lastNameLabel);
	}

	// ***********************************************************************//

	/*
	 * This method is used to create and position the buttons in the frame, also
	 * adding action listeners
	 */
	public void createButtons() {
		button = new JButton("Login");
		button.setBounds(100, 145, 100, 25);
		button.setBackground(new Color(235, 235, 235));
		button.addActionListener(this);
		frame.add(button);

		backButton = new JButton("Back");
		backButton.setBounds(100, 180, 100, 25);
		backButton.setBackground(new Color(235, 235, 235));
		backButton.addActionListener(this);
		frame.add(backButton);
	}

	// ***********************************************************************//

	/*
	 * This method is used to perform actions when buttons are pressed
	 */
	public void actionPerformed(ActionEvent e) {

		// If login button is pressed
		if (e.getSource() == button) {
			String userID = userIDText.getText();
			String lastName = lastNameText.getText();
			if (SmartCity.userExists(userID, lastName)) {
				frame.dispose();
				Menu menu = new Menu(SmartCity.getUser(userID));
			} else {
				JOptionPane.showMessageDialog(null, "Incorrect Information", "Error Logging In",
						JOptionPane.WARNING_MESSAGE);
				userIDText.setText("");
				lastNameText.setText("");
			}
		}

		// If back button is pressed
		if (e.getSource() == backButton) {
			frame.dispose();
			LoginScene back = new LoginScene();
		}
	}
}