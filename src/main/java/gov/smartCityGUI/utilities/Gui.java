package gov.smartCityGUI.utilities;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 9/19/2023
  * Description: This class contains shortcuts for creating GUI components. Helps to lessen repeated code, resulting in less lines of code overall.
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui {

	public static JButton smallButton(String title) {
		JButton button = new JButton(title);
		button.setPreferredSize(new Dimension(100, 30));
		button.setBackground(new Color(235, 235, 235));
		button.setFocusable(false);
		return button;
	}

	public static JButton longButton(String title) {
		JButton button = new JButton(title);
		button.setPreferredSize(new Dimension(200, 35));
		button.setBackground(new Color(235, 235, 235));
		button.setFocusable(false);
		return button;
	}

	public static JFrame bigFrame(String title) {
		JFrame frame = new JFrame(title);
		frame.setSize(700, 400);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(75, 75, 75));
		frame.setVisible(true);
		return frame;
	}

	public static JFrame smallFrame(String title) {
		JFrame frame = new JFrame(title);
		frame.setSize(300, 250);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(75, 75, 75));
		frame.setVisible(true);
		return frame;
	}

	public static JFrame mediumFrame(String title) {
		JFrame frame = new JFrame(title);
		frame.setSize(500, 400);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(75, 75, 75));
		frame.setVisible(true);
		return frame;
	}

	public static JFrame formFrame(String title) {
		JFrame frame = new JFrame(title);
		frame.setSize(300, 450);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(75, 75, 75));
		frame.setVisible(true);
		return frame;
	}

	public static JButton back() {
		JButton backButton = new JButton("Back");
		backButton.setBounds(10, 10, 100, 35);
		backButton.setBackground(new Color(235, 235, 235));
		backButton.setFocusable(false);
		return backButton;
	}

	public static JButton genericButton(String name) {
		JButton button = new JButton(name);
		button.setBackground(new Color(235, 235, 235));
		button.setFocusable(false);
		return button;
	}

	public static JLabel whiteLabel(String name) {
		JLabel label = new JLabel(name);
		label.setForeground(new Color(230, 230, 230));
		return label;
	}

	public static JLabel scrollLabel(String name) {
		JLabel label = new JLabel(name);
		label.setBackground(new Color(100, 100, 100));
		label.setForeground(new Color(235, 235, 235));
		label.setFocusable(false);
		return label;
	}

	 //Back button for career 
    public static JButton backForCareer() {
      JButton cBackButton = new JButton("Back");
      cBackButton.setBounds(10, 300, 100, 35);
      cBackButton.setBackground(new Color(235,235,235));
      cBackButton.setFocusable(false);
      return cBackButton;
    }

}