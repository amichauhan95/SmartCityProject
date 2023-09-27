package gov.smartcityteam2.Transit;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.graph.GraphWalk;

/**
 * A class representing a particular transit route, like a particular subway line
 * or a bus route. It consists of a series of TransitStops connected by TransitPaths.
 *
 * NOTE: the validity of a TransitRoute is not checked on construction;
 * they absolutely will let you make a TransitRoute that can't actually be traversed.
 * To get around this, make sure your incoming vertex list corresponds to a
 * traversable path *prior* to creating the route, and then, if you like,
 * include a route.verify() check just to be sure.
 */
public class TransitRoute extends GraphWalk<TransitStop, TransitPath> {

  private int id;
  private String name;
  private TransitSchedule schedule;

  /**
   * Public constructor.
   *
   * @param id the unique database id of this transit route
   * @param graph the graph this route is contained in
   * @param vertexList the list of stops this route covers
   * @param schedule the assigned schedule for ths route
   */
  public TransitRoute(int id, String name, Graph<TransitStop, TransitPath> graph, List<TransitStop> vertexList, TransitSchedule schedule) {
    super(graph, vertexList, 1);
    this.id = id;
    this.name = name;
    this.schedule = schedule;
  }

  /**
   * Get the id of this transit route.
   *
   * @return the id of this transit route
   */
  public int getId() {
    return id;
  }

  /**
   * Get the name of this transit route.
   *
   * @return the name of this transit route
   */
  public String getName() {
    return name;
  }

  /**
   * Get the schedule associated with this transit route.
   *
   * @return the schedule associated with this transit route
   */
  public TransitSchedule getSchedule() {
    return schedule;
  }

  /**
   * Get a mapping of transit stops to a list of their scheduled times.
   *
   * @return a mapping of transit stops to a list of their scheduled times
   */
  public LinkedHashMap<TransitStop, List<LocalTime>> getTimes() {
    LinkedHashMap<TransitStop, List<LocalTime>> times = new LinkedHashMap<>();
    List<TransitStop> stops = vertexList;
    TransitGraph graph = (TransitGraph)getGraph();
    LocalTime startTime = schedule.getStartTime();
    LocalTime stopTime = schedule.getStopTime();
    LocalTime currentTime = startTime;
    int i = 0;
    for(TransitStop stop : stops) {
      times.put(stop, new ArrayList<>());
    }
    while(currentTime.compareTo(stopTime) < 0) {
      TransitStop currentStop = stops.get(i);
      TransitStop nextStop = (i == stops.size() - 1)?stops.get(0):stops.get(i + 1);
      TransitPath currentPath = graph.getEdge(currentStop, nextStop);
      times.get(stops.get(i)).add(currentTime);
      currentTime = currentTime.plusSeconds((long)(graph.getEdgeWeight(currentPath) * 60));
      ++i;
      if(i >= stops.size() - 1) {
        i %= stops.size();
      }
    }
    return times;
  }
  
  /**
   * Get a string representation of this transit route.
   *
   * @return a string representation of this transit route
   */
  @Override
  public String toString() {
    TransitGraph castGraph = (TransitGraph)graph; // it's safe. trust :)
    return new StringBuilder().append(castGraph.getName()).append(" - ").append(this.name).toString();
  }

  @Override
  public boolean equals(Object obj) {
    if(null == obj || !(obj instanceof TransitRoute)) {
      return false;
    }
    TransitRoute route = (TransitRoute)obj;
    return this.id == route.getId() || this.name.equals(route.getName());
  }
}