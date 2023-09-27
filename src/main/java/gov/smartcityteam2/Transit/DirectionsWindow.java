/*
Team Member(s) working on this class: Natalie Darsillo, Dylan Moran
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;
import gov.smartcityteam2.*;
import org.jgrapht.GraphPath;

class DirectionsWindow implements ActionListener{

  private User user;
  private ITransitService service;
  private TransitGraph graph;
  private boolean uiActive;

  // GUI elements
  private JFrame frame;
  private JButton backButton;
  private JLabel modeLabel;
  private JLabel fromLabel;
  private JLabel toLabel;
  private JComboBox<TransitMode> modeBox;
  private JComboBox<TransitStop> fromBox;
  private JComboBox<TransitStop> toBox;
  private JTextArea directionsTextArea;
  
  DirectionsWindow(User user, ITransitService service){
    this.user = user;
    this.service = service;
    this.frame = Gui.bigFrame("Albany+ Consolidated Transit System");
    initGUI();
  }

  private void initGUI(){
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    uiActive = false;
    modeLabel = Gui.whiteLabel("Select transit mode:");
    modeLabel.setBounds(50, 60, 150, 20);
    frame.add(modeLabel);
    
    modeBox = new JComboBox<>(TransitMode.values());
    modeBox.addActionListener(this);
    modeBox.setBounds(50, 85, 150, 25);
    modeBox.setSelectedItem(null);
    frame.add(modeBox);

    fromLabel = Gui.whiteLabel("Start:");
    fromLabel.setBounds(275, 60, 150, 20);
    frame.add(fromLabel);
    
    fromBox = new JComboBox<>();
    fromBox.addActionListener(this);
    fromBox.setBounds(275, 85, 150, 25);
    fromBox.setEnabled(false);
    frame.add(fromBox);

    toLabel = Gui.whiteLabel("Destination:");
    toLabel.setBounds(500, 60, 150, 20);
    frame.add(toLabel);
      
    toBox = new JComboBox<>();
    toBox.addActionListener(this);
    toBox.setBounds(500, 85, 150, 25);
    toBox.setEnabled(false);
    frame.add(toBox);

    directionsTextArea = new JTextArea();
    directionsTextArea.setBounds(50, 135, 600, 220);
    directionsTextArea.setEditable(false);
    directionsTextArea.setFont(directionsTextArea.getFont().deriveFont(16f));
    frame.add(directionsTextArea);
    uiActive = true;

    frame.repaint();
  }

  public void actionPerformed(ActionEvent e){
    if(!uiActive) {
      return;
    }
    if(e.getSource() == backButton){
      frame.dispose();
      new TransitMenu(user, service);
    }
    if(e.getSource() == modeBox) {
      int index = modeBox.getSelectedIndex();
      TransitMode mode = modeBox.getItemAt(index);
      resetBoxes();
      populateFromBox(mode);
    }
    if(e.getSource() == fromBox) {
      int index = fromBox.getSelectedIndex();
      TransitStop fromStop = fromBox.getItemAt(index);
      populateToBox(fromStop);
    }
    if(e.getSource() == toBox) {
      int fromIndex = fromBox.getSelectedIndex();
      int toIndex = toBox.getSelectedIndex();
      TransitStop fromStop = fromBox.getItemAt(fromIndex);
      TransitStop toStop = toBox.getItemAt(toIndex);
      populateDirections(fromStop, toStop);     
    }
  }

  private void populateFromBox(TransitMode mode) {
    uiActive = false;
    directionsTextArea.setText("");
    graph = service.getWholeTransitGraphForMode(mode);
    List<TransitStop> stops = service.getTransitStopsInGraph(graph);
    for(TransitStop stop : stops) {
      fromBox.addItem(stop);
    }
    fromBox.setSelectedItem(null);
    fromBox.setEnabled(true);
    uiActive = true;
  }

  private void populateToBox(TransitStop fromStop) {
    uiActive = false;
    toBox.removeAllItems();
    directionsTextArea.setText("");
    List<TransitStop> stops = service.getTransitStopsInGraph(graph);
    stops.remove(fromStop);
    for(TransitStop stop : stops) {
      toBox.addItem(stop);
    }
    toBox.setSelectedItem(null);
    toBox.setEnabled(true);
    uiActive = true;
  }

  private void resetBoxes() {
    uiActive = false;
    fromBox.removeAllItems();
    toBox.removeAllItems();
    fromBox.setSelectedItem(null);
    toBox.setSelectedItem(null);
    fromBox.setEnabled(false);
    toBox.setEnabled(false);
    uiActive = true;
  }

  private void populateDirections(final TransitStop from, final TransitStop to) {
    Thread worker = new Thread(() -> {
        GraphPath<TransitStop, TransitPath> optimalPath = graph.getShortestPath(from, to);                       List<TransitStop> pathStops = optimalPath.getVertexList();
        List<TransitPath> pathPaths = optimalPath.getEdgeList();
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i < pathStops.size() - 1; ++i) {
          sb.append(String.format("%s → %s VIA %s\n", pathStops.get(i), pathStops.get(i + 1), pathPaths.get(i)));
        }
        sb.append(String.format("Total trip time: %.0f minutes", optimalPath.getWeight()));
        SwingUtilities.invokeLater(() -> {
          directionsTextArea.setText(sb.toString());
        });
    });
    worker.run();
  }
}