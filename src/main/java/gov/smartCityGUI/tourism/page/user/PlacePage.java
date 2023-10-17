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
import java.awt.Color;
import java.awt.Font;
import gov.smartCityGUI.tourism.service.PlaceFilter;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.menu.Menu;
public class PlacePage implements ActionListener {
  private PlaceFilter filter = new PlaceFilter();
  private final String IMAGE_PATH = "src/main/java/gov/smartCityGUI/tourism/static/placeImg/";
  User user;

  // Main Frame
  JButton backButton;
  JButton homeButton;
  JButton tourismButton;
  JButton searchButton;
  JButton searchKeyWordButton;

  // img section
  JFrame frame = Gui.bigFrame("Place System");
  JPanel centerPanel = new JPanel();
  JScrollPane scrlpanel;
  JPanel topPanel;
  JPanel searchPanel;
  JTextField searchText;

  JComboBox cb;

  public PlacePage(User user) {
    this.user = user;
    frame.setSize(800,700);
    createHeader();
    /* Default value: 1 */
    createFrames(1, "");
  }

  public void createHeader() {

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

  public void createFrames(int option, String keyword) {
    // Data panel
    if (scrlpanel != null) frame.remove(scrlpanel);
    centerPanel = new JPanel();
    centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    
    searchBarPanel();
    placeFilter(option, keyword);

    scrlpanel = new JScrollPane(centerPanel);

    frame.add(scrlpanel, BorderLayout.CENTER);

    centerPanel.setVisible(true);
    frame.setVisible(true);
    
    // Keep scroll bar on the top
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        scrlpanel.getVerticalScrollBar().setValue(0);
      }
    });
  }

  /* A panel that dispaly the content after searching a keyword */
  public JPanel searchResultPanel(String keyword, String[][] data) {
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
    // l.setHorizontalAlignment(SwingConstants.CENTER);
    JPanel p = new JPanel();
    p.add(Box.createRigidArea(new Dimension(19, 0)));
    p.add(l);
    p.add(k);
    p.add(l1);
    p.add(length);
    p.add(l2);
    return p;
  }

  /* A panel that constructs placeInfo UI */
  public JPanel placeDataPanel(String[] temp, User user){
    JPanel panel = new JPanel();
      panel.setLayout(new FlowLayout());

      /* Image construct */
      JLabel imageLabel = imageFormat(temp[0]);

      /* Place Info construct */
      JTextArea text = new JTextArea(3, 23);
      text.setEditable(false);
      text.setText(temp[1]);
      text.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));

      /* Button */
      Button placeButton = new Button("More Detail");
      placeButton.setBounds(50, 50, 1, 23);
      placeButton.setName(temp[2]);
      placeButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          Button button = (Button) e.getSource();
          frame.dispose();
          new PlaceDetailPage(user, button);
        }
      });

      Box verticalBox = Box.createVerticalBox();
      verticalBox.add(text);
      verticalBox.add(placeButton);

      /* Add data into panel and frame */
      panel.add(imageLabel);
      panel.add(verticalBox);
    return panel;
  }

  public void searchBarPanel() {
    searchPanel = new JPanel();
    String sortOption[] = { "Sort By name", "Rate", "Reviews" };
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

  public void placeFilter(int option, String keyword) {

    String[][] data = filter.sortAlgo(option, keyword);

    if (data.length < 1) {
      JOptionPane.showMessageDialog(frame, "No data found", "Alert", JOptionPane.WARNING_MESSAGE);
      data = filter.sortAlgo(1, "");
    } else {
      if (!keyword.equals("")) {
        JPanel p = searchResultPanel(keyword, data);
        centerPanel.add(p);
      }
    }

    for (String[] temp : data) {
      JPanel panel = placeDataPanel(temp, user);
      centerPanel.add(panel);
      centerPanel.add(Box.createVerticalStrut(20));
    }
    centerPanel.setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backButton) {
      frame.dispose();
      new UserPage(this.user);
    }
    if (e.getSource() == homeButton) {
      frame.dispose();
      new Menu(this.user);
    }
    if (e.getSource() == tourismButton) {
      frame.dispose();
      new UserPage(user);
    }
    if (e.getSource() == searchButton) {
      String sortOption = (String) cb.getItemAt(cb.getSelectedIndex());

      if (searchText.getText().isEmpty()) {
        if (sortOption.equals("Sort By name"))
          createFrames(1, "");
        else if (sortOption.equals("Rate"))
          createFrames(2, "");
        else if (sortOption.equals("Reviews"))
          createFrames(3, "");
      } else {
        String keyword = searchText.getText();
        if (sortOption.equals("Sort By name"))
          createFrames(1, keyword);
        else if (sortOption.equals("Rate"))
          createFrames(2, keyword);
        else if (sortOption.equals("Reviews"))
          createFrames(3, keyword);
      }
    }
    if (e.getSource() == searchKeyWordButton) {
      createFrames(1, searchText.getText());
    }

  }

  public JLabel imageFormat(String image) {
    ImageIcon imageIcon = new ImageIcon(IMAGE_PATH + image);
    Image scaledImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_FAST);
    imageIcon = new ImageIcon(scaledImage);
    return new JLabel(imageIcon);
  }

}