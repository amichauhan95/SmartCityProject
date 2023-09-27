/*
Team Member(s) working on this class: Natalie Darsillo, Dylan Moran
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;
import gov.smartcityteam2.*;
import org.json.JSONObject;

class FareWindow implements ActionListener{

  private User user;
  private ITransitService service;

  // GUI elements
  private JFrame frame;
  private JButton backButton;
  private JButton trainButton;
  private JButton busButton;
  private JButton railButton;
  private JTextArea fareTextArea;
  
  FareWindow(User user, ITransitService service){
    this.user = user;
    this.service = service;
    this.frame = Gui.bigFrame("Albany+ Consolidated Transit System");
    initGUI();
  }

  private void initGUI(){
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    trainButton = Gui.genericButton("Train");
    trainButton.setBounds(550, 75, 100, 50);
    trainButton.addActionListener(this);
    frame.add(trainButton);
    
    busButton = Gui.genericButton("Bus");
    busButton.setBounds(550, 175, 100, 50);
    busButton.addActionListener(this);
    frame.add(busButton);
    
    railButton = Gui.genericButton("Light Rail");
    railButton.setBounds(550, 275, 100, 50);
    railButton.addActionListener(this);
    frame.add(railButton);

    fareTextArea = new JTextArea();
    fareTextArea.setBounds(50, 75, 450, 250);
    fareTextArea.setEditable(false);
    fareTextArea.setFont(fareTextArea.getFont().deriveFont(20f));
    frame.add(fareTextArea);

    frame.repaint();
  }

  public void actionPerformed(ActionEvent e){
    if(e.getSource() == backButton){
      frame.dispose();
      new TransitMenu(user, service);
    }
    if(e.getSource() == trainButton) {
      populateFares(TransitMode.TRAIN);
    }
    if(e.getSource() == busButton) {
      populateFares(TransitMode.BUS);
    }
    if(e.getSource() == railButton) {
      populateFares(TransitMode.LIGHT_RAIL);
    }
  }

  @SuppressWarnings("unchecked")
  private void populateFares(final TransitMode mode) {
    Thread worker = new Thread(() -> {
      JSONObject fares = service.getFareInformation(mode);
      Map<String, Object> faresMap = fares.toMap();
      final StringBuilder sb = new StringBuilder();
      for(Map.Entry<String, Object> entry : faresMap.entrySet()) {
        String label = entry.getKey();
        double value = (double)entry.getValue();
        String valueString = String.format("$%.2f", value);
        sb.append(label).append(": ").append(valueString).append("\n");
      }
      SwingUtilities.invokeLater(() -> {
        fareTextArea.setText(sb.toString());
      });
    });
    worker.run();
  }
}