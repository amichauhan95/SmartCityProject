/*
Team Member(s) working on this class: Natalie Darsillo, Dylan Moran 
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

import java.awt.event.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;
import gov.smartcityteam2.*;

class MapWindow implements ActionListener{

  private static final String TRAIN_MAP_URI = "resources/train map.png";
  private static final String BUS_MAP_URI = "resources/bus map.png";
  private static final String LIGHT_RAIL_MAP_URI = "resources/light rail map.png";

  private User user;
  private ITransitService service;

  // GUI elements
  private JFrame frame;
  private JButton backButton;
  private JLabel mapLabel;
  private JButton trainButton;
  private JButton busButton;
  private JButton railButton;
  
  MapWindow(User user, ITransitService service){
    this.user = user;
    this.service = service;
    this.frame = Gui.bigFrame("Albany+ Consolidated Transit System");
    initGUI();
  }

  private void initGUI(){
    mapLabel = new JLabel();
    mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
    frame.add(mapLabel);
    
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    trainButton = Gui.genericButton("Train");
    trainButton.setBounds(150, 300, 100, 50);
    trainButton.addActionListener(this);
    frame.add(trainButton);
    
    busButton = Gui.genericButton("Bus");
    busButton.setBounds(300, 300, 100, 50);
    busButton.addActionListener(this);
    frame.add(busButton);
    
    railButton = Gui.genericButton("Light Rail");
    railButton.setBounds(450, 300, 100, 50);
    railButton.addActionListener(this);
    frame.add(railButton);

    frame.repaint();
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == backButton){
      frame.dispose();
      new TransitMenu(user, service);
    }
    if(e.getSource() == trainButton) {
      displayMap(TransitMode.TRAIN);
    }
    if(e.getSource() == busButton) {
      displayMap(TransitMode.BUS);
    }
    if(e.getSource() == railButton) {
      displayMap(TransitMode.LIGHT_RAIL);
    }
  }

  private void displayMap(final TransitMode mode) {
    Thread worker = new Thread(() -> {
      String mapURI = null;
      final double IMAGE_SCALE = 0.75;
      switch(mode) {
        case TRAIN:
          mapURI = TRAIN_MAP_URI;
          break;
  
        case BUS:
          mapURI = BUS_MAP_URI;
          break;
  
        case LIGHT_RAIL:
          mapURI = LIGHT_RAIL_MAP_URI;
          break;
  
        default:
          break;
      }
      if(null == mapURI) {
        return;
      }
      BufferedImage mapImage;
      try {
        mapImage = ImageIO.read(new File(mapURI));
      } catch(Exception ex) {
        return;
      }
      final int scaledWidth = (int)(mapImage.getWidth() * IMAGE_SCALE);
      final int scaledHeight = (int)(mapImage.getHeight() * IMAGE_SCALE);
      final Image scaledMapImage = mapImage.getScaledInstance(scaledWidth,
                                                        scaledHeight,
                                                        Image.SCALE_SMOOTH);
      SwingUtilities.invokeLater(() -> {
        mapLabel.setSize(scaledWidth, scaledHeight);
        mapLabel.setLocation(frame.getWidth()/2 - scaledWidth/2, 50);
        mapLabel.setIcon(new ImageIcon(scaledMapImage));
        frame.repaint();
      });
    });
    worker.start();
  }
}