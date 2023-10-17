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
import gov.smartCityGUI.tourism.service.CommentFilter;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.tourism.util.ValidationUtil;
import gov.smartCityGUI.menu.Menu;

public class CommentPage implements ActionListener{
  private CommentsService commentsService = new CommentsService();
  private final String IMAGE_PATH = "src/main/java/gov/smartCityGUI/tourism/static/placeImg/";
  private CommentFilter filter = new CommentFilter();
  User user;

  //Main Frame

  JButton backButton;
  JButton homeButton;
  JButton button2;
  JButton button3;
  JButton searchButton;
  JButton tourismButton;
  JButton searchKeyWordButton;

  // img section
  JFrame frame = Gui.bigFrame("Comment System");
  JPanel centerPanel = new JPanel();
  JScrollPane scrlpanel;
  JPanel topPanel;
  JPanel searchPanel;
  JTextField searchText;

  JComboBox cb;
  
  
  public CommentPage(User user){
    this.user = user;
    frame.setSize(800,700);
    createHeader();
    /* Default value: 1*/
    createFrames(1, "");
  }

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

  // search Bar component for the page
  public void searchBar(){
    searchPanel = new JPanel();
    String sortOption[]={"Recent","Latest"}; 
    cb = new JComboBox(sortOption);    
    searchButton = new JButton("Search"); 
    searchText = new JTextField(11);  
    searchKeyWordButton = new JButton("Search"); 

    searchButton.addActionListener(this);  
    searchKeyWordButton.addActionListener(this);   
    
    searchPanel.add(cb);
    searchPanel.add(searchButton);     
    

    searchPanel.setVisible(true);

    JPanel searchPanel2 = new JPanel();
    
    searchPanel2.add(searchText);
    searchPanel2.add(searchKeyWordButton);

    
    centerPanel.add(searchPanel);
    centerPanel.add(searchPanel2);
  }
  
  public void createFrames(){
    List<List<String>> data = commentsService.allCommentsByAUser(user);

    if(data.isEmpty()){
      JOptionPane.showMessageDialog(frame,"You have not made any comment yet! \uD83D\uDE00","No Comment",JOptionPane.WARNING_MESSAGE);
      frame.dispose();
      new UserPage (user);
      return;
    }
    
    centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    
    for(List<String> temp : data){
      int commentId = Integer.parseInt(temp.get(0));
      String comment = temp.get(1);
      String image = temp.get(2);
      System.out.println(String.format("comment: %s\nimage: %s\n\n", comment, image));
      JPanel panel = new JPanel();
      JLabel imageLabel = imageFormat(image);
      /* Place Info construct */
      JTextArea text = new JTextArea(3,23);
      text.setEditable(false);
      text.setText(comment);
      
      panel.add(imageLabel);

      Box verticalBox = Box.createVerticalBox();
      Button editButton = new Button("Edit");
      Button deleteButton = new Button("Delet");
      editButton.setBounds(50,50,1, 23);
      deleteButton.setBounds(50,50,1, 23);
      editButton.setName(String.valueOf(commentId));
      deleteButton.setName(String.valueOf(commentId));
      
      verticalBox.add(text);
      verticalBox.add(editButton);
      verticalBox.add(deleteButton);

      panel.add(imageLabel);
      panel.add(verticalBox);
      centerPanel.add(panel);
      centerPanel.add(Box.createVerticalStrut(20));
      
      editButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
          Button button = (Button) e.getSource();
          int commentId = Integer.parseInt(button.getName());
          editCommentPage(commentId);
        } 
      });

      deleteButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
          Button button = (Button) e.getSource();
          int commentId = Integer.parseInt(button.getName());
          deleteCommentPage(commentId);
        } 
      });
    }


    scrlpanel = new JScrollPane(centerPanel);  

    // Keep scroll bar on the top
    SwingUtilities.invokeLater(new Runnable() {
      public void run() { 
        scrlpanel.getVerticalScrollBar().setValue(0);
      }
    });

    
    frame.add(scrlpanel, BorderLayout.CENTER);
    
    centerPanel.setVisible(true);
    frame.setVisible(true);
  }

  // main frams contains all diff component
  public void createFrames(int option, String keyword){
    // Data panel
    if(scrlpanel != null) frame.remove(scrlpanel);
    centerPanel = new JPanel();
    
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    searchBar();
    commentFilter(option, keyword);
    
    scrlpanel = new JScrollPane(centerPanel);  

    // Keep scroll bar on the top
    SwingUtilities.invokeLater(new Runnable() {
      public void run() { 
        scrlpanel.getVerticalScrollBar().setValue(0);
      }
    });

    frame.add(scrlpanel, BorderLayout.CENTER);
    
    centerPanel.setVisible(true);
    frame.setVisible(true);
  }

  /**
    Filters the current user's comments by given option and keyword
    @param option takes {1,2,3} which triggers the diff sorting algo; keyword is a string that finds mathcing comment records
  **/
  public void commentFilter(int option, String keyword){
    String [][] data = new String[0][0];

    data = filter.sortAlgo(option, keyword, user);

    if(data.length < 1){
      JOptionPane.showMessageDialog(frame,"No data found","Alert",JOptionPane.WARNING_MESSAGE);
      data = filter.sortAlgo(1, "", user);
    } else {
      if(!keyword.equals("")){
      
        JLabel l = new JLabel("Result for: ");
        JLabel l1 = new JLabel(", has");
        JLabel l2 = new JLabel(" found.");
        JLabel k = new JLabel(keyword);
        JLabel length = new JLabel(String.valueOf(data.length));
        k.setForeground(Color.red);
        length.setForeground(Color.red);
  
        k.setFont(new Font(l.getFont().getName(), l.getFont().getStyle(), 20));
        length.setFont(new Font(l.getFont().getName(), l.getFont().getStyle(), 20));
        l.setFont(new Font(l.getFont().getName(), l.getFont().getStyle(), 15));
        l1.setFont(new Font(l.getFont().getName(), l.getFont().getStyle(), 15));
        l2.setFont(new Font(l.getFont().getName(), l.getFont().getStyle(), 15));
        JPanel p = new JPanel();
        p.add(Box.createRigidArea(new Dimension(19, 0)));
        p.add(l);
        p.add(k);
        p.add(l1);
        p.add(length);
        p.add(l2);
        centerPanel.add(p);
      }
    }

    for(String[] temp: data){
      
      JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());

      /* Image construct */
      JLabel imageLabel = imageFormat(temp[0]);

      /* Place Info construct */
      JTextArea text = new JTextArea(3,23);
      text.setEditable(false);
      text.setText(temp[1]);
      text.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

      /*  Button */
      Button deleteB = new Button("Delete");
      deleteB.setBounds(50,50,1, 23);
      deleteB.setName(temp[2]);
      deleteB.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
          Button button = (Button) e.getSource();
          int commentId = Integer.parseInt(button.getName());
          deleteCommentPage(commentId);
        } 
      });

      
      Box verticalBox = Box.createVerticalBox();
      verticalBox.add(text);
      verticalBox.add(deleteB);

      /* Add data into panel and frame */
      panel.add(imageLabel);
      panel.add(verticalBox);
      centerPanel.add(panel);
      centerPanel.add(Box.createVerticalStrut(20));
    }

    centerPanel.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e){
    if(e.getSource() == backButton){
		  frame.dispose();
      new UserPage(user);
    }
    if(e.getSource() == homeButton){
		  frame.dispose();
      new Menu(user);
    }
    if(e.getSource() == tourismButton){
      frame.dispose();
      new UserPage(user);
    }
    if(e.getSource() == searchButton){
      String sortOption = (String)cb.getItemAt(cb.getSelectedIndex());  
      
      if(searchText.getText().isEmpty()){
        if(sortOption.equals("Recent")) createFrames(1, "");
        else if (sortOption.equals("Latest"))createFrames(2, "");
      } else {
        String keyword = searchText.getText();
        if(sortOption.equals("Recent")) createFrames(1, keyword);
        else if (sortOption.equals("Latest"))createFrames(2, keyword);
      }
    }
    if(e.getSource() == searchKeyWordButton){
      createFrames(1, searchText.getText());
    }


  }

  /**
    Fomattring a image into JLabel object
    @param image is the image file name
    @return return the JLable image
  **/
  public JLabel imageFormat(String image){
    ImageIcon imageIcon = new ImageIcon(IMAGE_PATH + image);
    Image scaledImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_FAST);
    imageIcon = new ImageIcon(scaledImage);
    return new JLabel(imageIcon);
  }

  /**
    Display a edit comment page for a place by given placeid
    @param placeId
  **/
  public void editCommentPage(int placeId){
    
    JFrame f=new JFrame("Edit Comment Form");
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
          o[2] = text;
          o[1] = cText;
          o[0] = placeId;
          commentsService.updateComment(o, user);
          
          JOptionPane.showMessageDialog(f,"Updated Successfully","Alert",JOptionPane.WARNING_MESSAGE);
          f.dispose();
          frame.dispose();
          new CommentPage(user);
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

  /**
    Display a delete comment page for a place by given placeid
    @param placeId
  **/
  public void deleteCommentPage(int  commentId){
    int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete this comment? This cannot be undone.", "Delete Comment",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
    if(result == JOptionPane.YES_OPTION){
      commentsService.deleteComment(commentId, user);
      frame.dispose();
      new CommentPage(user);
    }
  }

}