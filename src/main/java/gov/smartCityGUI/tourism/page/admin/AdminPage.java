/*@author Huiying Lin
Project: Smart City
@date 9/27/2023
I recieved help from: N/A
*/
package gov.smartCityGUI.tourism.page.admin;


import java.awt.event.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.menu.Menu;


public class AdminPage implements ActionListener {

  User user;

  JFrame frame;

  JButton backButton;
  JButton button1;
  JButton button2;

  public AdminPage(User user) {
    this.user = user;

    createFrames();
    createButtons();

  }

  public void createFrames() {
    frame = Gui.bigFrame("Tourism Role = *Admin*");
  }

  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    button1 = Gui.genericButton("Place Management");
    button1.addActionListener(this);
    button1.setBounds(50, 130, 200, 60);
    frame.add(button1);

    button2 = Gui.genericButton("Comment Management");
    button2.addActionListener(this);
    button2.setBounds(450, 130, 200, 60);
    frame.add(button2);

  }

  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == backButton) {
      frame.dispose();
      new Menu(this.user);
    }
    if (e.getSource() == button1) {
      frame.dispose();
      new PlaceManagement(user);
    }
    if (e.getSource() == button2) {
      frame.dispose();
      new CommentManagement(user);
    }

  }

}