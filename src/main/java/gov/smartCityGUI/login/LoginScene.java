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

public class LoginScene implements ActionListener {

	// UI Components
	JFrame frame;
	JButton loginButton;
	JButton registerButton;

	// ***********************************************************************//

	/*
	 * The default constructor is used to create the frame and buttons for the
	 * window
	 */
	public LoginScene() {
		createFrame();
		createButtons();
	}

	// ***********************************************************************//

	/*
	 * This method is used to build the frame for the window
	 */
	public void createFrame() {

		// Main frame
		frame = new JFrame("Login");
		frame.setSize(300, 200);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(75, 75, 75));
		frame.setVisible(true);
	} // End createFrame() method

	// ***********************************************************************//

	/*
	 * This method is used to create and add the buttons to the frame, also adding
	 * action listeners
	 */
	public void createButtons() {

		// Login button
		loginButton = new JButton("Login");
		loginButton.setBounds(75, 30, 150, 40);
		loginButton.setFocusable(false);
		loginButton.setBackground(new Color(235, 235, 235));
		loginButton.addActionListener(this);
		frame.add(loginButton);

		// Register button
		registerButton = new JButton("Register");
		registerButton.setBounds(75, 85, 150, 40);
		registerButton.setFocusable(false);
		registerButton.setBackground(new Color(235, 235, 235));
		registerButton.addActionListener(this);
		frame.add(registerButton);
	} // End createButtons() method

	// ***********************************************************************//

	/*
	 * This method is used to perform actions when buttons are pressed
	 */
	public void actionPerformed(ActionEvent e) {

		// If login button is pressed
		if (e.getSource() == loginButton) {
			frame.dispose();
			LoginWindow login = new LoginWindow();
		}

		// If register button is pressed
		if (e.getSource() == registerButton) {
			frame.dispose();
			RegisterWindow register = new RegisterWindow();
		}
	} // End actionPerformed() method

	// ***********************************************************************//

}