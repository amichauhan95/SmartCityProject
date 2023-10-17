/*@author Huiying Lin
Project: Smart City
@date 9/27/2023
I recieved help from: N/A
*/
package gov.smartCityGUI.tourism.page.user;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;  
import java.util.List;
import java.util.ArrayList;
import gov.smartCityGUI.tourism.service.PlaceService;
import gov.smartCityGUI.tourism.service.CommentsService;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.tourism.util.ValidationUtil;
import gov.smartCityGUI.tourism.TourismWindow;
import gov.smartCityGUI.menu.Menu;

public class PlaceDetailPage implements ActionListener{

  private PlaceService placeService = new PlaceService();
  private CommentsService commentsService = new CommentsService();
  private final String IMAGE_PATH = "src/main/java/gov/smartCityGUI/tourism/static/placeImg/";
  private int currentPlaceId;
  User user;
  Button button;
  //Main Frame

  JButton backButton;
  JButton homeButton;
  JButton searchButton;
  JButton addCommentButton;
  JButton tourismButton;


  // img section
  JFrame frame = Gui.bigFrame("Place Detail");
  JPanel centerPanel;
  JScrollPane scrlpanel;
  JPanel topPanel;
  JPanel searchPanel;

  JComboBox cb;
  
  //Input Frame
  JFrame inputFrame;

  public PlaceDetailPage(User user, Button button){
    this.user = user;
    this.button = button;
    frame.setSize(800,700);

    createHeader();
    createPlaceInfo();
  }

  // header component for the page
  public void createHeader(){
    
    frame.setLayout(new BorderLayout());
    
    topPanel = new JPanel();

    backButton = Gui.back();
    backButton.addActionListener(this);
    topPanel.add(backButton);

    topPanel.add(Box.createRigidArea(new Dimension(400, 0)));
    
    homeButton = Gui.genericButton("Home");
    homeButton.addActionListener(this);
    tourismButton = Gui.genericButton("Tourism");
    tourismButton.addActionListener(this);
    topPanel.add(tourismButton);
    topPanel.add(homeButton);
    
    frame.add(topPanel, BorderLayout.NORTH);
  }

  public void createPlaceInfo(){

    /* Retriving place data*/
    String[] data = placeService.checkPlace(Integer.parseInt(button.getName()));

    
    centerPanel = new JPanel(); 
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

    
    JLabel about =new JLabel("About", JLabel.LEFT); 
    JLabel comments =new JLabel("Comments", JLabel.CENTER);
    JLabel imageLabel = imageFormat(data[0]); 
    JTextPane placeInfo = new JTextPane();
    JTextPane c = new JTextPane();
    about.setPreferredSize(new Dimension(50, 50));
    comments.setPreferredSize(new Dimension(40, 40));

    addCommentButton = new JButton("Add Comment");
    addCommentButton.addActionListener(this);
    placeInfo.setText(data[1]);
    c.setText(data[2]);
    currentPlaceId = Integer.parseInt(data[3]);
    placeInfo.setPreferredSize(new Dimension(600, 150));
    imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    centerPanel.add(imageLabel);
    centerPanel.add(about);
    centerPanel.add(placeInfo);
    centerPanel.add(comments);
    centerPanel.add(c);
    centerPanel.add(addCommentButton);
    
    scrlpanel = new JScrollPane(centerPanel);  
    frame.add(scrlpanel, BorderLayout.CENTER);
    frame.setVisible(true);

    /* Keep scrollbar on the top */
    SwingUtilities.invokeLater(new Runnable() {
      public void run() { 
        scrlpanel.getVerticalScrollBar().setValue(0);
      }
    });

  
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == backButton){
      frame.dispose();
      new PlacePage(user);
    }
    if(e.getSource() == homeButton){
      frame.dispose();
      new Menu(user);
    }
    if(e.getSource() == addCommentButton){
      addCommentForm(currentPlaceId);
    }
    if(e.getSource() == tourismButton){
      frame.dispose();
      new UserPage(user);
    }
    
  }

  /**
    Convert image to Jlabel component
    @param image name
    @return image Jlabel
  **/
  public JLabel imageFormat(String image){
    ImageIcon imageIcon = new ImageIcon(IMAGE_PATH + image);
    Image scaledImage = imageIcon.getImage().getScaledInstance(350, 250, Image.SCALE_FAST);
    imageIcon = new ImageIcon(scaledImage);
    return new JLabel(imageIcon);
  }

  /* UserComment input*/
  public void addCommentForm(int placeId){
    JFrame f=new JFrame("User Comment Form");
    f.setSize(500, 400);
    f.setLocation(300, 200);
    f.setLayout(new FlowLayout());
    
    f.setVisible(true);
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel title = new JLabel ("User Comment From");
    JLabel rateLabel = new JLabel("Rate:");
    JTextField rateText = new JTextField(20);
    JLabel commentLabel = new JLabel("Comment:");
    JTextArea commentLabelText = new JTextArea(10,20);
    JButton submitButton = new JButton ("Submit");
    JButton exitButton = new JButton ("Exit");
    commentLabelText.setLineWrap(true);

    JPanel temp = new JPanel ();
    JPanel temp1 = new JPanel ();
    JPanel temp2 = new JPanel ();
    
    temp.add(rateLabel);
    temp.add(Box.createRigidArea(new Dimension(25, 0)));
    temp.add(rateText);

    temp1.add(commentLabel);
    temp1.add(commentLabelText);

    temp2.add(submitButton);
    temp2.add(exitButton);

    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    rateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    rateText.setAlignmentX(Component.CENTER_ALIGNMENT);
    commentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    commentLabelText.setAlignmentX(Component.CENTER_ALIGNMENT);

// Add "glue" at the start and end of the panel
    panel.add(Box.createVerticalGlue());
    panel.add(title);
    panel.add(Box.createRigidArea(new Dimension(0, 35)));
    panel.add(temp);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp1);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp2);
    panel.add(Box.createVerticalGlue());
    f.add(panel);
    f.setVisible(true);

    submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        String text = rateText.getText();
        String cText = commentLabelText.getText();
        System.out.println("data: " + cText);
        System.out.println(cText.equals(""));
        if(ValidationUtil.isValidDouble(text) && cText.length() >= 1){
          /* Write to data */
          Object o [] = new Object[3];
          o[0] = text;
          o[1] = cText;
          o[2] = placeId;
          commentsService.addComment(o, user);
          
          JOptionPane.showMessageDialog(f,"Added Successfully","Alert",JOptionPane.WARNING_MESSAGE);
          f.dispose();
          frame.dispose();
          new PlaceDetailPage(user, button);
        } else {
          JOptionPane.showMessageDialog(f,"*Please enter 0 - 5\n*Comment must not be empty","Alert",JOptionPane.WARNING_MESSAGE);
        }
			}
		});

    exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        f.dispose();
        
			}
		});
  }
}