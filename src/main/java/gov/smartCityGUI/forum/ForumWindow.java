package gov.smartCityGUI.forum;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 9/13/2023
  * Description: This class is used to display the Message Forum UI to the user. It will show the user a list of
  * messages previously posted and buttons to allow the user to add a message or add a comment. Admins will see 
  * buttons to delete one or all messages.
**/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;
import gov.smartCityGUI.menu.Menu;

public class ForumWindow implements ActionListener{

  //***********************************************************************//

  //Resources
  User user;
  String messageCode;
  
  //UI Elements

  //Main Frame
  JFrame frame;
  JScrollPane scroll;
  JButton backButton;
  JButton newButton;
  JButton commentButton;
  JButton deleteAllButton;
  JButton deleteButton;
  ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
  JPanel messagePanel;

  //Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JButton enterButton;
  JButton backToDash;
  JTextArea input;

  //Flags
  boolean addMess = false;
  boolean addComm = false;

  //***********************************************************************//

  /**
    * Constructor will build the UI and save the user to the class
  **/
  public ForumWindow(User user){
    this.user = user;
    createFrames();
    createScrollPane();
    createButtons();
  } // End Constructor

  //***********************************************************************//

  /**
    * This method is used to create the main frame
  **/
  public void createFrames(){
    frame = Gui.bigFrame("Public Forum");
  } // End createFrames() method

  //***********************************************************************//

  /**
    * This method is used to create the scroll pane to show all of the messages
  **/
  public void createScrollPane(){

	    ArrayList<String[]> messages = MessageSystem.fetchMessages();
	
	    //Container for checkboxes
	    messagePanel = new JPanel();
	    messagePanel.setBackground(new Color(100,100,100));
	    messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
	    messagePanel.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
	    messagePanel.setPreferredSize(new Dimension(350,500));
	    JCheckBox c1;
	
	    //Loop through all messages and add a line for checkboxes
	    for(String[] message : messages){
	      	c1 = new JCheckBox("[" + message[0] + "] " + message[1] + " - " + message[2]);
	     	c1.setBackground(new Color(100,100,100));
	     	c1.setForeground(new Color(235,235,235));
	      	c1.setFocusable(false);
	      
	      	JLabel entry = new JLabel("      " + message[3]);
	      	entry.setForeground(new Color(235,235,235));
	      	entry.setBorder(BorderFactory.createBevelBorder(2));
	
	      	checks.add(c1);
	      	messagePanel.add(c1);
	      	messagePanel.add(entry);
	      
	      	//If the message has comments, add the comments underneath the message checkbox
	      	String parts[] = message[4].split("@");
	      	JLabel comment;
	      	//Loop for multiple comments
	      	if(!parts[0].equals("-")) {
	        	for(int i = 0; i < parts.length; i++){
	          		String elements[] = parts[i].split("~");
	          		String comAuthor = elements[0];
	          		String comBody = elements[1];
	          		comment = new JLabel("              " + comAuthor + " - " + comBody);
	          		messagePanel.add(comment);
	        	}
	      	}
	    }
    
	    //Scroll view for all messages
	    JScrollPane scroll = new JScrollPane(messagePanel);
	    scroll.setBounds(20,75,430,300);
	    scroll.setBorder(BorderFactory.createLineBorder(new Color(100,100,100)));
	    frame.add(scroll);
	
	    //Title label for scroll view
	    JLabel scrollTitle = Gui.whiteLabel("All Messages");
	    scrollTitle.setBounds(20,50,150,35);
	    frame.add(scrollTitle);
  	} // End createScrollPane() method

  //***********************************************************************//

  /**
    * This method is used to create all buttons for the main frame
  **/
  	public void createButtons(){

	    //Back button
	    backButton = Gui.back();
	    backButton.addActionListener(this);
	    frame.add(backButton);
	
	    //New Message button
	    newButton = Gui.genericButton("New Message");
	    newButton.addActionListener(this);
	    newButton.setBounds(500,80,150,35);
	    frame.add(newButton);
	
	    //Comment button
	    commentButton = Gui.genericButton("Leave Comment");
	    commentButton.addActionListener(this);
	    commentButton.setBounds(500,130,150,35);
	    frame.add(commentButton);
	
	    //If user is admin, show delete all button
	    if(this.user.isAdmin()){
	      	deleteAllButton = Gui.genericButton("Delete All");
	      	deleteAllButton.addActionListener(this);
	      	deleteAllButton.setBounds(500,180,150,35);
	      	frame.add(deleteAllButton);
	
			deleteButton = Gui.genericButton("Delete");
			deleteButton.addActionListener(this);
			deleteButton.setBounds(500, 230, 150, 35);
			frame.add(deleteButton);
	    }
  	} // End createButtons() method

  //***********************************************************************//

  /**
    * This method is used to create the input frame and all of its components, used to gather user input
    *
    * @param messageCode the message code is only utilized for comments to assign the code of the message being commented on
  **/
 	 public void inputFrame(String messageCode){

	    //Input frame
	    this.messageCode = messageCode;
	    inputFrame = Gui.mediumFrame("Input");
	    inputFrame.setVisible(false);
	
	    //Input text field
	    input = new JTextArea();
	    input.setBounds(100, 100, 300, 150);
	    inputFrame.add(input);
	
	    //Input description label
	    prompt = new JLabel("Enter your message");
	    prompt.setBounds(180, 40, 200, 25);
	    prompt.setForeground(new Color(255,255,255));
	    inputFrame.add(prompt);
	
	    //Enter button
	    enterButton = Gui.smallButton("Enter");
	    enterButton.addActionListener(this);
	    enterButton.setBounds(200, 275, 100, 25);
	    enterButton.setBackground(new Color(235,235,235));
	    inputFrame.add(enterButton);
	
	    //Back button
	    backToDash = Gui.smallButton("Back");
	    backToDash.addActionListener(this);
	    backToDash.setBounds(200,305,100,25);
	    backToDash.setBackground(new Color(235,235,235));
	    inputFrame.add(backToDash);
  	} // End inputFrame() method

  //***********************************************************************//

  /**
    * This method is used to handle all button presses
	* @param e the button press
  **/
	public void actionPerformed(ActionEvent e){

	    //If backButton is pressed
	    if(e.getSource() == backButton){
	      	frame.dispose();
	      	Menu menu = new Menu(this.user);
	    }
	
	    //If New Message button is pressed
	    if(e.getSource() == newButton){
	      	addMess = true;
	      	frame.dispose();
	      	inputFrame("0");
	      	inputFrame.setVisible(true);
	    }
	
	    //If Enter button is pressed inside input frame
	    if(e.getSource() == enterButton){
	      	//If message flag is true
	      	if(addMess){
	        	String message = input.getText();
	       		MessageSystem.writeMessage(this.user, message);
	        	inputFrame.setVisible(false);
	        	addMess = false;
	        	ForumWindow forum = new ForumWindow(this.user);
	      	}
	      	//If comment flag is true
	      	if(addComm){
	        	String message = input.getText();
	        	int code = Integer.parseInt(this.messageCode);
	        	MessageSystem.leaveComment(this.user, code, message);
	        	inputFrame.setVisible(false);
	        	addComm = false;
	        	ForumWindow forum = new ForumWindow(this.user);
	     	}
	    }
	
	    //If Comment button is pressed
	    if(e.getSource() == commentButton){
	      	addComm = true;
	      	boolean selected = false;
	      	String message = new String();
	      	String code = new String();
	      	for(JCheckBox check : checks){
	        	if(check.isSelected()) {
	          		selected = true;
	          		message = check.getText();
	          		message = message.substring(1);
	          		char characters[] = message.toCharArray();
	          		for(char letter : characters){
	            		if(letter == ']') break;
	            		else{
	              			code += letter;
	            		}
	          		}
	          		frame.dispose();
	          		inputFrame(code);
	          		prompt.setText("Enter your comment");
	          		inputFrame.setVisible(true);
	          		break;
	        	}
	      	}
	    }
	
	    //If Delete All button is pressed
	    if(e.getSource() == deleteAllButton){
	      	MessageSystem.clearAllMessages();
	      	frame.dispose();
	      	ForumWindow forum = new ForumWindow(this.user);
	    }

	  	if(e.getSource() == deleteButton){
			boolean selected = false;
	      	String message = new String();
	      	String code = new String();
	      	for(JCheckBox check : checks){
		        if(check.isSelected()) {
		          	selected = true;
		         	message = check.getText();
		          	message = message.substring(1);
		          	char characters[] = message.toCharArray();
		          	for(char letter : characters){
		            	if(letter == ']') break;
		            	else{
		              		code += letter;
		            	}
		          	}
		  		}
			}
			MessageSystem.deleteMessage(Integer.parseInt(code));
			messagePanel.removeAll();
			createScrollPane();
			messagePanel.revalidate();
			messagePanel.repaint();
		}

    	//If back button is pressed in input frame
    	if(e.getSource() == backToDash){
      		inputFrame.dispose();
      		addMess = false;
      		addComm = false;
      		ForumWindow forum = new ForumWindow(this.user);
    	}
  	} // End actionPerformed() method

  //***********************************************************************//

} // End class
