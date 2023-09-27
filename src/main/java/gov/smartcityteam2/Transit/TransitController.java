/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import gov.smartcityteam2.IMenuProvider;
import gov.smartcityteam2.Util;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.GraphPath;
import org.json.JSONObject;

/**
 * This class is an implementation of a text-based controller
 * for the Smart City transit system.
 */
public class TransitController {

  private ITransitService service;

  /**
   * Public constructor.
   *
   * @param service a service layer instance
   */
  public TransitController(ITransitService service) {
    this.service = service;
  }

  /**
   * Display the transit system menu.
   */
  public void menu() {
    int choice;
    while(true) {
      choice = Util.runMenu(() -> {      
        System.out.println("\t\tTransit+\n");
        System.out.println("1. Display transit map\n");
        System.out.println("2. Find transit directions\n");
        System.out.println("3. Show transit schedules\n");
        System.out.println("4. View fare information\n");
      });
      switch(choice){
        case 1:
          mapMenu();
          break;
          
        case 2:
          directionsMenu();
          break;
          
        case 3:
          schedulesMenu();
          break;
  
        case 4:
          faresMenu();
          break;
          
        case 0:
          return;
          
        default:
          Util.printMenuError();
      }
    }      
  }

  // display the map menu
  private void mapMenu() {
    final List<TransitGraph> graphs = service.getTransitGraphs();
    int choice;
    while(true) {
      choice = Util.runMenu(() -> {
        System.out.println("Choose which map to display.\n");
        for(int i = 0; i < graphs.size(); ++i) {
          System.out.printf("%d. %s\n\n", i + 1, graphs.get(i).getName());
        }
      });
      if(choice == 0) {
        return;
      } else if(choice < 1 || choice > graphs.size()) {
        Util.printMenuError();
        continue;
      }
      break;
    }
    TransitGraph selectedGraph = graphs.get(choice - 1);
    System.out.println("displaying graph: " + selectedGraph.getName());
    //JGraphXAdapter<TransitStop, TransitPath> jgraphx = new JGraphXAdapter<>(selectedGraph);
  }

  // display the directions menu
  private void directionsMenu() {
    final List<TransitMode> modes = Arrays.asList(TransitMode.values());
    int modeChoice, fromChoice, toChoice;
    while(true) {
      modeChoice = Util.runMenu(() -> {
        System.out.println("Choose the mode of transit.\n");
        for(int i = 0; i < modes.size(); ++i) {
          System.out.printf("%d. %s\n\n", i + 1, modes.get(i).toString());
        }
      });
      if(modeChoice == 0) {
        return;
      } else if(modeChoice < 1 || modeChoice > modes.size()) {
        Util.printMenuError();
        continue;
      }
      break;
    }
    TransitMode selectedMode = modes.get(modeChoice - 1);
    final TransitGraph entireModeGraph = service.getWholeTransitGraphForMode(selectedMode);
    final List<TransitStop> stops = service.getTransitStopsInGraph(entireModeGraph);
    while(true) {
      // pick the from stop
      fromChoice = Util.runMenu(() -> {
        System.out.println("Choose your starting stop.\n");
        for(int i = 0; i < stops.size(); ++i) {
          System.out.printf("%d. %s\n\n", i + 1, stops.get(i).getName());
        }
      });
      if(fromChoice == 0) {
        return;
      } else if(fromChoice < 1 || fromChoice > stops.size()) {
        Util.printMenuError();
        continue;
      }
      break;
    }
    while(true) {
      // pick the to stop
      toChoice = Util.runMenu(() -> {
        System.out.println("Choose your destination stop.\n");
        for(int i = 0; i < stops.size(); ++i) {
          System.out.printf("%d. %s\n\n", i + 1, stops.get(i).getName());
        }
      });
      if(toChoice == 0) {
        return;
      } else if(toChoice < 1 || toChoice > stops.size()) {
        Util.printMenuError();
        continue;
      }
      break;
    }
    // calculate the shortest path
    TransitStop from = stops.get(fromChoice - 1), to = stops.get(toChoice - 1);
    GraphPath<TransitStop, TransitPath> optimalPath = entireModeGraph.getShortestPath(from, to);
    List<TransitStop> pathStops = optimalPath.getVertexList();
    List<TransitPath> pathPaths = optimalPath.getEdgeList();
    System.out.println("\nDirections:");
    System.out.println("-------------------------------------------------------");
    for(int i = 0; i < pathStops.size() - 1; ++i) {
      System.out.printf("%s -> %s VIA %s\n", pathStops.get(i), pathStops.get(i + 1), pathPaths.get(i));
      System.out.println("-------------------------------------------------------");
    }
    System.out.printf("Total trip time: %.0f minutes\n\n", optimalPath.getWeight());
  }

  // display the schedules menu
  private void schedulesMenu() {
    final List<TransitRoute> routes = service.getTransitRoutes();
    int choice;
    while(true) {
      choice = Util.runMenu(() -> {
        System.out.println("Choose which schedule to display.\n");
        for(int i = 0; i < routes.size(); ++i) {
          TransitRoute route = routes.get(i);
          TransitGraph routeGraph = (TransitGraph)route.getGraph();
          System.out.printf("%d. %s - %s\n\n", i + 1, routeGraph.getName(), route.getName());
        }
      });
      if(choice == 0) {
        return;
      } else if(choice < 1 || choice > routes.size()) {
        Util.printMenuError();
        continue;
      }
      break;
    }
    TransitRoute selectedRoute = routes.get(choice - 1);
    LinkedHashMap<TransitStop, List<LocalTime>> stopTimes = selectedRoute.getTimes();
    int count = 0;
    for (Map.Entry<TransitStop, List<LocalTime>> entry : stopTimes.entrySet()) {
      TransitStop stop = entry.getKey();
      List<LocalTime> times = entry.getValue();
            System.out.println("\n--------------------------------------------------------------------------------------------------");
      System.out.printf("%s  \t|", stop.getName());
      for(LocalTime time : times) {
        if(count >= 10) {
          System.out.printf("\n\t\t\t\t|");
          count = 0;
        }
        System.out.printf("\t%s", time.toString());
        ++count;
      }
      count = 0;
    }
    System.out.println("\n--------------------------------------------------------------------------------------------------\n");
  }

  // display the fares menu
  private void faresMenu() {
    final List<TransitMode> modes = Arrays.asList(TransitMode.values());
    int choice;
    while(true) {
      choice = Util.runMenu(() -> {
        System.out.println("Choose the mode of transit.\n");
        for(int i = 0; i < modes.size(); ++i) {
          System.out.printf("%d. %s\n\n", i + 1, modes.get(i).toString());
        }
      });
      if(choice == 0) {
        return;
      } else if(choice < 1 || choice > modes.size()) {
        Util.printMenuError();
        continue;
      }
      break;
    }
    JSONObject fareInfo = service.getFareInformation(modes.get(choice - 1));
    parseAndPrintFares(fareInfo.toMap(), 0);
  }

  // callers, please be nice and don't give me bad objects
  // the fares map should contain string -> double or string -> map associations,
  // and those inner maps should abide by the same contract
  // and yes, this is recursive, but if anyone gives me a deep enough map to
  // pop the stack i would love to know their use case
  @SuppressWarnings("unchecked")
  private void parseAndPrintFares(Map<String, Object> fares, int indentLevel) {
    String indent = "";
    for(int i = 0; i < indentLevel; ++i) {
      indent += "\t";
    }
    for(Map.Entry<String, Object> entry : fares.entrySet()) {    
      String label = entry.getKey();
      Object value = entry.getValue();
      if(value instanceof Map) {
        System.out.printf("\n%s%s: \n", indent, label);
        Map<String, Object> deeperMap = (Map<String, Object>)value;
        parseAndPrintFares(deeperMap, indentLevel + 1);
      } else {
        System.out.printf("\n%s%s: ", indent, label);
        double price = (double)value;
        System.out.printf("$%.2f\n", price);
      }
    }
  }
}