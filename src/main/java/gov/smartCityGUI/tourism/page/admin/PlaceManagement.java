package gov.smartCityGUI.tourism.page.admin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JFrame;  
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import gov.smartCityGUI.tourism.page.admin.*;
import gov.smartCityGUI.tourism.service.PlaceFilter;
import gov.smartCityGUI.tourism.service.PlaceService;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.tourism.page.user.UserPage;
import gov.smartCityGUI.tourism.TourismWindow;
import gov.smartCityGUI.tourism.util.ValidationUtil;

public class PlaceManagement implements ActionListener{
  private PlaceFilter filter = new PlaceFilter();
  private PlaceService placeService = new PlaceService();
  private final String IMAGE_PATH = "src/main/java/gov/smartCityGUI/tourism/static/placeImg/";
  User user;

  //Main Frame

  JButton backButton;
  JButton homeButton;
  JButton tourismButton;
  JButton searchButton;
  JButton addButton;
  JButton searchKeyWordButton;


  // img section
  JFrame frame = Gui.bigFrame("Place Management");
  JPanel centerPanel = new JPanel();
  JScrollPane scrlpanel;
  JPanel topPanel;
  JPanel searchPanel;
  JTextField searchText;
  

  String imageName = "";

  JComboBox cb;
  
  //Input Frame
  JFrame inputFrame;
  JLabel prompt;
  JTextField input;
  JButton enterButton;
  
  public PlaceManagement(User user){
    this.user = user;
    createHeader();
    /* Default value: 1*/
    createFrames(1, "");
  }

  public void createHeader(){
    
    frame.setLayout(new BorderLayout());
    
    topPanel = new JPanel();
    // topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
    // Back botton
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

  public void placeFilter(int option, String keyword){
    String [][] data = new String[0][0];

    data = filter.sortAlgo(option, keyword);

    if(data.length < 1){
      JOptionPane.showMessageDialog(frame,"No data found","Alert",JOptionPane.WARNING_MESSAGE);
      data = filter.sortAlgo(1, "");
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
      Button editButton = new Button("Edit");
      Button deleteButton = new Button("Delete");
      editButton.setBounds(50,50,1, 23);
      editButton.setName(temp[2]);
      deleteButton.setBounds(50,50,1, 23);
      deleteButton.setName(temp[2]);
      
      editButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
          Button button = (Button) e.getSource();
          int placeId = Integer.parseInt(button.getName());
          editPlacePage(placeId);
        } 
      });

      deleteButton.addActionListener(new ActionListener() { 
        public void actionPerformed(ActionEvent e) { 
          Button button = (Button) e.getSource();
          int placeId = Integer.parseInt(button.getName());
          deletePlacePage(placeId);
        } 
      });

      
      Box verticalBox = Box.createVerticalBox();
      verticalBox.add(text);
      verticalBox.add(editButton);
      verticalBox.add(deleteButton);

      /* Add data into panel and frame */
      panel.add(imageLabel);
      panel.add(verticalBox);
      centerPanel.add(panel);
      centerPanel.add(Box.createVerticalStrut(20));
    }

    centerPanel.setVisible(true);
  }

  public void searchBar(){
    searchPanel = new JPanel();
    // searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    String sortOption[]={"Sort By name","Rate","Reviews"}; 
    cb = new JComboBox(sortOption);    
    searchButton = new JButton("Search"); 
    addButton = new JButton("Add Place"); 
    searchText = new JTextField(11);  
    searchKeyWordButton = new JButton("Search"); 

    searchButton.addActionListener(this);  
    addButton.addActionListener(this);   
    searchKeyWordButton.addActionListener(this);   
    
    searchPanel.add(cb);
    searchPanel.add(searchButton);     
    

    searchPanel.setVisible(true);

    JPanel searchPanel2 = new JPanel();
    JPanel searchPanel3 = new JPanel();
    
    searchPanel2.add(searchText);
    searchPanel2.add(searchKeyWordButton);
    searchPanel3.add(addButton);

    
    centerPanel.add(searchPanel);
    centerPanel.add(searchPanel2);
    centerPanel.add(searchPanel3);
  }
  
  public void createFrames(int option, String keyword){
    // Data panel
    if(scrlpanel != null) frame.remove(scrlpanel);
    centerPanel = new JPanel();
    
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    searchBar();
    placeFilter(option, keyword);
    
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

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == backButton){
      frame.dispose();
      new UserPage(this.user);
    }
    if(e.getSource() == homeButton){
      frame.dispose();
      new Menu(this.user);
    }
    if(e.getSource() == tourismButton){
      frame.dispose();
      new TourismWindow(user);
    }
    if(e.getSource() == searchButton){
      String sortOption = (String)cb.getItemAt(cb.getSelectedIndex());  
      
      if(searchText.getText().isEmpty()){
        if(sortOption.equals("Sort By name")) createFrames(1, "");
        else if (sortOption.equals("Rate"))createFrames(2, "");
        else if (sortOption.equals("Reviews"))createFrames(3, "");
      } else {
        String keyword = searchText.getText();
        if(sortOption.equals("Sort By name")) createFrames(1, keyword);
        else if (sortOption.equals("Rate"))createFrames(2, keyword);
        else if (sortOption.equals("Reviews"))createFrames(3, keyword);
      }
    }
    if(e.getSource() == searchKeyWordButton){
      createFrames(1, searchText.getText());
    }
    if(e.getSource() == addButton){
      addPlacePage();
    }
  }

  public void addPlacePage(){
    JFrame f = new JFrame("Add Place Form");
  
    f.setSize(500, 400);
    f.setLocation(300, 200);
    f.setLayout(new FlowLayout());
    
    f.setVisible(true);
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(450, 350));
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel title = new JLabel ("Place information From");
 
    JTextField nameText = new JTextField(20);
    JTextArea desLabelText = new JTextArea(7,20);
    JTextField rateText = new JTextField(20);
    JTextField reviewlText = new JTextField(20);
    JButton submitButton = new JButton ("Submit");
    JButton exitButton = new JButton ("Exit");
    JButton selectButton = new JButton ("Select Img");
    JLabel path = new JLabel ("Image: ");
    desLabelText.setLineWrap(true);

    JPanel temp = new JPanel ();
    JPanel temp1 = new JPanel ();
    JPanel temp2 = new JPanel ();
    JPanel temp3 = new JPanel ();
    JPanel temp4 = new JPanel ();
    JPanel temp5 = new JPanel ();
    
    temp.add(new JLabel("Name:"));
    // temp.add(Box.createRigidArea(new Dimension(25, 0)));
    temp.add(nameText);

    temp1.add(new JLabel("Rate:"));
    temp.add(Box.createRigidArea(new Dimension(5, 0)));
    temp1.add(rateText);

    temp2.add(new JLabel("Reivew:"));
    // temp.add(Box.createRigidArea(new Dimension(8, 0)));
    temp2.add(reviewlText);

    temp3.add(new JLabel("Detail:"));
    temp3.add(desLabelText);

    temp4.add(selectButton);
    temp4.add(path);

    temp5.add(submitButton);
    temp5.add(exitButton);

    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    // rateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    // rateText.setAlignmentX(Component.CENTER_ALIGNMENT);
    // desLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    // desLabelText.setAlignmentX(Component.CENTER_ALIGNMENT);
    // reviewlText.setAlignmentX(Component.CENTER_ALIGNMENT);
    // rateText.setAlignmentX(Component.CENTER_ALIGNMENT);

// Add "glue" at the start and end of the panel
    panel.add(Box.createVerticalGlue());
    panel.add(title);
    panel.add(Box.createRigidArea(new Dimension(0, 35)));
    panel.add(temp);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp1);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp2);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp3);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp4);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp5);
    panel.add(Box.createVerticalGlue());

    
    f.add(new JScrollPane(panel));
    // f.add(panel);
    f.setVisible(true);

    submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        String rate = rateText.getText();
        String des = desLabelText.getText();
        String name = nameText.getText();
        String review = reviewlText.getText();
        String img = imageName; 

        if(ValidationUtil.checkAddPlaceForm(name, des, review, rate, img)){
          /* Write to data */
          Object o [] = new Object[6];
          o[0] = name;
          o[1] = des;
          o[2] = review;
          o[3] = rate;
          o[4] = img;
          placeService.addPlace(o);
          
          JOptionPane.showMessageDialog(f,"Added Successfully","Alert",JOptionPane.WARNING_MESSAGE);
          f.dispose();
          frame.dispose();
          new PlaceManagement(user);
          // te();
        } else {
          JOptionPane.showMessageDialog(f,"*Rate must be between 0 - 5\n*Review must be >= 0\n*Detail must not be empty\n*Place must not be empty\n*Image path must not be empty","Alert",JOptionPane.WARNING_MESSAGE);
        }
			}
		});

    exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        f.dispose();
			}
		});

    selectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
            JFileChooser getFile = new JFileChooser();
 
            getFile.setCurrentDirectory(new File(System.getProperty("user.home")));
            // Filter files
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("*.Images", "jpg",
                    "png");
            getFile.addChoosableFileFilter(filter1);
            int res = getFile.showSaveDialog(null);
            if(res == JFileChooser.APPROVE_OPTION) {
              File selFile1 = getFile.getSelectedFile();                                     
              BufferedImage getImg;
              imageName = selFile1.getName();
              File outputFile = new File(IMAGE_PATH + imageName);
              path.setText(path.getText() + imageName);
                try {
                  getImg = ImageIO.read(selFile1);
                  ImageIO.write(getImg, "jpg", outputFile);
                } catch (IOException ex) {
                }
            } // End if
        } // End actionPerformer
		});

    SwingUtilities.invokeLater(new Runnable() {
      public void run() { 
        scrlpanel.getVerticalScrollBar().setValue(0);
      }
    });
  
  }
  
  public JLabel imageFormat(String image){
    ImageIcon imageIcon = new ImageIcon(IMAGE_PATH + image);
    Image scaledImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_FAST);
    imageIcon = new ImageIcon(scaledImage);
    return new JLabel(imageIcon);
  }

  public void editPlacePage(int placeId){
    JFrame f = new JFrame("Edit Place Form");
  
    f.setSize(500, 400);
    f.setLocation(300, 200);
    f.setLayout(new FlowLayout());
    
    f.setVisible(true);
    JPanel panel = new JPanel();
    panel.setPreferredSize(new Dimension(450, 350));
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

    JLabel title = new JLabel ("Updae Place information From");
 
    JTextField nameText = new JTextField(20);
    JTextArea desLabelText = new JTextArea(7,20);
    JTextField rateText = new JTextField(20);
    JTextField reviewlText = new JTextField(20);
    JButton submitButton = new JButton ("Submit");
    JButton exitButton = new JButton ("Exit");
    JButton selectButton = new JButton ("Select Img");
    JLabel path = new JLabel ("Image: ");
    desLabelText.setLineWrap(true);

    JPanel temp = new JPanel ();
    JPanel temp1 = new JPanel ();
    JPanel temp2 = new JPanel ();
    JPanel temp3 = new JPanel ();
    JPanel temp4 = new JPanel ();
    JPanel temp5 = new JPanel ();
    
    temp.add(new JLabel("Name:"));
    // temp.add(Box.createRigidArea(new Dimension(25, 0)));
    temp.add(nameText);

    temp1.add(new JLabel("Rate:"));
    temp.add(Box.createRigidArea(new Dimension(5, 0)));
    temp1.add(rateText);

    temp2.add(new JLabel("Reivew:"));
    // temp.add(Box.createRigidArea(new Dimension(8, 0)));
    temp2.add(reviewlText);

    temp3.add(new JLabel("Detail:"));
    temp3.add(desLabelText);

    temp4.add(selectButton);
    temp4.add(path);

    temp5.add(submitButton);
    temp5.add(exitButton);

    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    // rateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    // rateText.setAlignmentX(Component.CENTER_ALIGNMENT);
    // desLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    // desLabelText.setAlignmentX(Component.CENTER_ALIGNMENT);
    // reviewlText.setAlignmentX(Component.CENTER_ALIGNMENT);
    // rateText.setAlignmentX(Component.CENTER_ALIGNMENT);

// Add "glue" at the start and end of the panel
    panel.add(Box.createVerticalGlue());
    panel.add(title);
    panel.add(Box.createRigidArea(new Dimension(0, 35)));
    panel.add(temp);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp1);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp2);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp3);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp4);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(temp5);
    panel.add(Box.createVerticalGlue());

    
    f.add(new JScrollPane(panel));
    // f.add(panel);
    f.setVisible(true);

    submitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        String rate = rateText.getText();
        String des = desLabelText.getText();
        String name = nameText.getText();
        String review = reviewlText.getText();
        String img = imageName; 

        if(ValidationUtil.isValidDouble(rate) && ValidationUtil.isInteger(review)){
          /* Write to data */
          Object o [] = new Object[6];
          o[0] = String.valueOf(placeId);
          o[1] = name;
          o[2] = des;
          o[3] = review;
          o[4] = rate;
          o[5] = img;
          placeService.updatePlace(o);
          
          JOptionPane.showMessageDialog(f,"Updated Successfully","Alert",JOptionPane.WARNING_MESSAGE);
          f.dispose();
          frame.dispose();
          new PlaceManagement(user);
          // te();
        } else {
          JOptionPane.showMessageDialog(f,"*Rate must be between 0 - 5\n*Review must be >= 0","Alert",JOptionPane.WARNING_MESSAGE);
        }
			}
		});

    exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
        f.dispose();
			}
		});

    selectButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
            JFileChooser getFile = new JFileChooser();
 
            getFile.setCurrentDirectory(new File(System.getProperty("user.home")));
            // Filter files
            FileNameExtensionFilter filter1 = new FileNameExtensionFilter("*.Images", "jpg",
                    "png");
            getFile.addChoosableFileFilter(filter1);
            int res = getFile.showSaveDialog(null);
            if(res == JFileChooser.APPROVE_OPTION) {
              File selFile1 = getFile.getSelectedFile();                                     
              BufferedImage getImg;
              imageName = selFile1.getName();
              File outputFile = new File(IMAGE_PATH + imageName);
              path.setText(path.getText() + imageName);
                try {
                  getImg = ImageIO.read(selFile1);
                  ImageIO.write(getImg, "jpg", outputFile);
                } catch (IOException ex) {
                }
            } // End if
        } // End actionPerformer
		});

    SwingUtilities.invokeLater(new Runnable() {
      public void run() { 
        scrlpanel.getVerticalScrollBar().setValue(0);
      }
    });
  
  }
  
  public void deletePlacePage(int  placeId){
    // commentsService.deleteComment(commentId, user);
    int result = JOptionPane.showConfirmDialog(frame,"Are you sure you want to delete this place? This cannot be undone.", "Delete Place",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
    if(result == JOptionPane.YES_OPTION){
      placeService.deletePlace(placeId);
      frame.dispose();
      new PlaceManagement(user);
    }
  }

}