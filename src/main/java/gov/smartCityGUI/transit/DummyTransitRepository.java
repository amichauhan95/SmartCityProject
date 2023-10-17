/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartCityGUI.transit;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jgrapht.graph.builder.GraphBuilder;
import org.json.JSONObject;

/**
 * This is a dummy implementation of the ITransitRepository contract
 * which constructs all of the transit data in-code rather than pulling from
 * an actual database.
 */
public class DummyTransitRepository implements ITransitRepository {

 // Any additional methods needed in here should be reflected in the ITransitRepository
 // interface as well; we only take the interface type in-code, not the concrete type.

  private final List<TransitGraph> graphs;
  private final List<TransitStop> stops;
  private final HashMap<TransitMode, JSONObject> fares;
  private final List<TransitRoute> routes;

  /**
   * Public constructor.
   *
   * Since this is just a dummy repository, this constructor builds the repo data in code.
   * As such, running this may be resource-intensive. Try putting it on a thread if that's an issue.
   */
  public DummyTransitRepository() {
    // if this wasn't a dummy, we would probably take a database connection here
    graphs = new ArrayList<>();
    stops = new ArrayList<>();
    fares = new HashMap<>();
    routes = new ArrayList<>();

    // building stops
    stops.add(new TransitStop(0, "University Station"));
    stops.add(new TransitStop(1, "State Campus Station"));
    stops.add(new TransitStop(2, "Central Station"));
    stops.add(new TransitStop(3, "East Station"));
    stops.add(new TransitStop(4, "Downtown Station"));
    stops.add(new TransitStop(5, "Washington Station"));
    stops.add(new TransitStop(6, "Pine Hills Station"));
    stops.add(new TransitStop(7, "Madison Station"));
    stops.add(new TransitStop(8, "College Station"));

    // building graphs
    TransitGraph trainGraph = new TransitGraph(2, "Albany+ Commuter Rail", TransitMode.TRAIN);
    GraphBuilder<TransitStop, TransitPath, TransitGraph> buildTrain = new GraphBuilder<>(trainGraph);
    buildTrain.addVertex(stops.get(0)).addVertex(stops.get(2)).addVertex(stops.get(3))
      .addVertex(stops.get(4)).addVertex(stops.get(5));
    buildTrain.addEdge(stops.get(0), stops.get(2), new TransitPath(0, "University-Central Rail"), 5)
      .addEdge(stops.get(2), stops.get(3), new TransitPath(1, "East-Central Rail"), 3)
      .addEdge(stops.get(3), stops.get(4), new TransitPath(2, "East-Downtown Rail"), 4)
      .addEdge(stops.get(4), stops.get(5), new TransitPath(3, "Downtown-Washington Rail"), 5)
      .addEdge(stops.get(5), stops.get(8), new TransitPath(17, "Washington-College Rail"), 2)
      .build();

    TransitGraph busGraph = new TransitGraph(1, "Albany+ Bus Rapid Transit", TransitMode.BUS);
    GraphBuilder<TransitStop, TransitPath, TransitGraph> buildBus = new GraphBuilder<>(busGraph);
    buildBus.addVertex(stops.get(0)).addVertex(stops.get(1)).addVertex(stops.get(2))
      .addVertex(stops.get(3)).addVertex(stops.get(4)).addVertex(stops.get(5))
      .addVertex(stops.get(6)).addVertex(stops.get(7)).build();
    buildBus.addEdge(stops.get(0), stops.get(1), new TransitPath(4, "University-Campus Street"), 4)
      .addEdge(stops.get(1), stops.get(2), new TransitPath(5, "Campus-Central Street"), 8)
      .addEdge(stops.get(2), stops.get(3), new TransitPath(6, "East-Central Street"), 7)
      .addEdge(stops.get(3), stops.get(4), new TransitPath(7, "East-Downtown Street"), 9)
      .addEdge(stops.get(4), stops.get(5), new TransitPath(8, "Downtown-Washington Street"), 10)
      .addEdge(stops.get(5), stops.get(6), new TransitPath(9, "Washington-Pine Street"), 2)
      .addEdge(stops.get(6), stops.get(7), new TransitPath(10, "Pine-Madison Street"), 1)
      .addEdge(stops.get(7), stops.get(0), new TransitPath(11, "Madison-University Street"), 8).build();
    
    TransitGraph railGraph = new TransitGraph(3, "Albany+ Urban Inter-rail", TransitMode.LIGHT_RAIL);
    GraphBuilder<TransitStop, TransitPath, TransitGraph> buildRail = new GraphBuilder<>(railGraph);
    buildRail.addVertex(stops.get(1)).addVertex(stops.get(2)).addVertex(stops.get(5))
      .addVertex(stops.get(6)).addVertex(stops.get(7));
    buildRail.addEdge(stops.get(1), stops.get(2), new TransitPath(12, "Campus-Central Line"), 5)
      .addEdge(stops.get(2), stops.get(5), new TransitPath(13, "Central-Washington Line"), 2)
      .addEdge(stops.get(5), stops.get(6), new TransitPath(14, "Washington-Pine Line"), 1)
      .addEdge(stops.get(6), stops.get(7), new TransitPath(15, "Pine-Madison Line"), 1)
      .addEdge(stops.get(7), stops.get(1), new TransitPath(16, "Madison-Campus Line"), 7).build();

    graphs.add(trainGraph);
    graphs.add(busGraph);
    graphs.add(railGraph);

    // building schedules & routes
    TransitSchedule busSchedule = new TransitSchedule(0, LocalTime.parse("06:00"), LocalTime.parse("23:00"));
    TransitSchedule trainSchedule = new TransitSchedule(2, LocalTime.parse("05:00"), LocalTime.parse("23:30"));
    TransitSchedule railSchedule1 = new TransitSchedule(4, LocalTime.parse("05:00"), LocalTime.parse("22:00"));
    TransitSchedule railSchedule2 = new TransitSchedule(5, LocalTime.parse("05:10"), LocalTime.parse("22:00"));

    List<TransitStop> busStops1 = new ArrayList<>();
    busStops1.add(stops.get(0));
    busStops1.add(stops.get(1));
    busStops1.add(stops.get(2));
    busStops1.add(stops.get(3));
    busStops1.add(stops.get(4));
    busStops1.add(stops.get(5));
    busStops1.add(stops.get(6));
    busStops1.add(stops.get(7));
    List<TransitStop> busStops2 = new ArrayList<>();
    busStops2.add(stops.get(4));
    busStops2.add(stops.get(3));
    busStops2.add(stops.get(2));
    busStops2.add(stops.get(1));
    busStops2.add(stops.get(0));
    busStops2.add(stops.get(7));
    busStops2.add(stops.get(6));
    busStops2.add(stops.get(5));
    routes.add(new TransitRoute(0, "Bus Route 1", busGraph, busStops1, busSchedule));
    routes.add(new TransitRoute(1, "Bus Route 2", busGraph, busStops2, busSchedule));
    List<TransitStop> trainStops1 = new ArrayList<>();
    trainStops1.add(stops.get(0));
    trainStops1.add(stops.get(2));
    trainStops1.add(stops.get(3));
    trainStops1.add(stops.get(4));
    trainStops1.add(stops.get(5));
    trainStops1.add(stops.get(8));
    trainStops1.add(stops.get(5));
    trainStops1.add(stops.get(4));
    trainStops1.add(stops.get(3));
    trainStops1.add(stops.get(2));
    List<TransitStop> trainStops2 = new ArrayList<>();
    trainStops2.add(stops.get(8));
    trainStops2.add(stops.get(5));
    trainStops2.add(stops.get(4));
    trainStops2.add(stops.get(3));
    trainStops2.add(stops.get(2));
    trainStops2.add(stops.get(0));
    trainStops2.add(stops.get(2));
    trainStops2.add(stops.get(3));
    trainStops2.add(stops.get(4));
    trainStops2.add(stops.get(5));
    routes.add(new TransitRoute(2, "Train Route 1", trainGraph, trainStops1, trainSchedule));
    routes.add(new TransitRoute(3, "Train Route 2", trainGraph, trainStops2, trainSchedule));
    List<TransitStop> railStops = new ArrayList<>();
    railStops.add(stops.get(1));
    railStops.add(stops.get(2));
    railStops.add(stops.get(5));
    railStops.add(stops.get(6));
    railStops.add(stops.get(7));
    routes.add(new TransitRoute(4, "Light Rail Route 1", railGraph, railStops, railSchedule1));
    routes.add(new TransitRoute(5, "Light Rail Route 2", railGraph, railStops, railSchedule2));
    for(TransitRoute route : routes) {
      route.verify();
    }

    //building fares
    JSONObject trainFares = new JSONObject();
    trainFares.put("Adult (peak)", 12.00);
    trainFares.put("Adult (off-peak)", 9.00);
    trainFares.put("Child (peak)", 6.00);
    trainFares.put("Child (off-peak)", 4.00);
    trainFares.put("Senior", 8.00);

    JSONObject busFares = new JSONObject();
    busFares.put("Standard bus", 2.00);
    busFares.put("Rapid bus", 3.00);

    JSONObject railFares = new JSONObject();
    railFares.put("Ticket", 2.50);

    fares.put(TransitMode.TRAIN, trainFares);
    fares.put(TransitMode.BUS, busFares);
    fares.put(TransitMode.LIGHT_RAIL, railFares);
  }

  /**
   * Get a list of stored transit graphs from the database.
   *
   * @return a list of stored transit graphs from the database
   */
  public List<TransitGraph> getTransitGraphs() {
    return graphs;
  }

  /**
   * Get a list of stored transit stops from the database.
   *
   * @return a list of stored transit stops from the database
   */
  public List<TransitStop> getTransitStops() {
    return stops;
  }

  /**
   * Get fare informaton from the database for the given mode.
   *
   * @return fare information from the database for the given mode
   */
  public JSONObject getFareInformation(TransitMode mode) {
    return fares.get(mode);
  }

  /**
   * Get a list of stored transit routes from the database.
   *
   * @return a list of stored transit routes from the database
   */
  public List<TransitRoute> getTransitRoutes() {
    return routes;
  }
}