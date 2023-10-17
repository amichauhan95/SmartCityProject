/*
Team Member(s) working on this class: Natalie Darsillo, Dylan Moran
Project: Smart City
I recieved help from: N/A
*/

package gov.smartCityGUI.transit;

import java.awt.Color;
import java.awt.event.*;
import java.awt.Component;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.utilities.Gui;

class ScheduleWindow implements ActionListener{

  private User user;
  private ITransitService service;
  private boolean uiActive;

  // GUI elements
  private JFrame frame;
  private JLabel crumbsLabel;
  private JButton backButton;
  private JLabel routeLabel;
  private JComboBox<TransitRoute> routeBox;
  private JTable scheduleTable;
  
  ScheduleWindow(User user, ITransitService service){
    this.user = user;
    this.service = service;
    this.frame = Gui.bigFrame("Albany+ Consolidated Transit System");
    initGUI();
  }

  private void initGUI(){
    uiActive = false;
    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    // crumbsLabel = new JLabel("TRANSIT // SCHEDULES");
    // crumbsLabel.setForeground(new Color(0, 0, 0));
    // crumbsLabel.setBounds(120, 10, 150, 25);
    // frame.add(crumbsLabel);

    routeLabel = Gui.whiteLabel("Select your route:");
    routeLabel.setBounds(50, 60, 150, 20);
    frame.add(routeLabel);

    routeBox = new JComboBox<>(service.getTransitRoutes().toArray(new TransitRoute[0]));
    routeBox.setBounds(50, 85, 400, 25);
    routeBox.setSelectedItem(null);
    routeBox.addActionListener(this);
    frame.add(routeBox);

    scheduleTable = new JTable();
    scheduleTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer)scheduleTable.getDefaultRenderer(String.class);
    stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    stringRenderer.setVerticalAlignment(SwingConstants.TOP);
    JScrollPane tablePane = new JScrollPane(scheduleTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    tablePane.setBounds(15, 120, 670, 245);
    frame.add(tablePane);
    uiActive = true;
  }

  public void actionPerformed(ActionEvent e){
    if(!uiActive) {
      return;
    }
    if(e.getSource() == backButton){
      frame.dispose();
      new TransitMenu(user, service);
    }
    if(e.getSource() == routeBox) {
      int index = routeBox.getSelectedIndex();
      TransitRoute route = routeBox.getItemAt(index);
      displayRouteSchedule(route);
    }
  }

  private void displayRouteSchedule(final TransitRoute route) {
    Thread worker = new Thread(() -> {
      LinkedHashMap<TransitStop, List<LocalTime>> stopTimes = route.getTimes();
      final DefaultTableModel tableModel = new DefaultTableModel();
      for (Map.Entry<TransitStop, List<LocalTime>> entry : stopTimes.entrySet()) {
        TransitStop stop = entry.getKey();
        List<LocalTime> times = entry.getValue();
        List<HourChunk> chunks = HourChunk.getChunks(times);
        tableModel.addColumn(stop, chunks.toArray(new HourChunk[0]));
      }
      SwingUtilities.invokeLater(() -> {
        scheduleTable.setModel(tableModel);
        updateRowHeights(scheduleTable);
      });
    });
    worker.run();
  }

  private void updateRowHeights(JTable table) {
    for (int row = 0; row < table.getRowCount(); row++) {
      int rowHeight = table.getRowHeight();
      for (int column = 0; column < table.getColumnCount(); column++) {
        Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);
        rowHeight = Math.max(rowHeight, comp.getPreferredSize().height);
      }
      table.setRowHeight(row, rowHeight);
    }
  }

  // collects multiple LocalTimes into a single time chunk of an hour
  private static class HourChunk implements Comparable<HourChunk> {

    private LocalTime baseHour; // the hour-long chunk this chunk delineates. range: X:00 - X:59
    private List<LocalTime> times; // a list of the specific times contained in this chunk

    // make a new hourchunk.
    // the times can be out of range; just give a baseHour and it'll get filtered down
    // baseHour just needs to be *a* time in the correct hour range; it'll get pared down correctly
    private HourChunk(LocalTime baseHour, List<LocalTime> times) {
      if(baseHour.getMinute() != 0) {
        baseHour = LocalTime.of(baseHour.getHour(), 0);
      }
      this.baseHour = baseHour;
      this.times = times.stream()
        .filter(time -> LocalTime.of(time.getHour(), 0).equals(this.baseHour))
        .collect(Collectors.toList());
    }

    private static List<HourChunk> getChunks(List<LocalTime> times) {
      List<HourChunk> chunks = new ArrayList<>();
      int lastHour = 0;
      for(LocalTime time : times) {
        int thisHour = time.getHour();
        if(thisHour > lastHour) {
          chunks.add(new HourChunk(time, times));
        }
        lastHour = thisHour;
      }
      return chunks;
    }

    /**
     * Gets a string representation of this LocalTime.
     *
     * @return representation of this LocalTime.
     */
    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("<html>");
      for(LocalTime time : times) {
        sb.append(time.toString()).append("<br>");
      }
      sb.append("</html>");
      return sb.toString();
    }

    /**
     * Compare the given HourChunk to this one.
     * Negative return -> before chunk
     * 0 -> same time as chunk
     * Positive value -> after chunk
     *
     * @param chunk the HourChunk to compare to
     * @return an int comparing the two times. See above for meaning.
     */
    public int compareTo(HourChunk chunk) {
      int thisHour = baseHour.getHour();
      int chunkHour = chunk.baseHour.getHour();
      return thisHour - chunkHour;
    }
  }
}