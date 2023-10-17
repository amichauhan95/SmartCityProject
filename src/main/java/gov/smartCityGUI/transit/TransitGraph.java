/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartCityGUI.transit;

import java.util.Set;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.WeightedMultigraph;
import org.jgrapht.GraphPath;

/**
 * This class defines a graph which represents some fraction of a city transit system.
 *
 * The design intends for a TransitGraph to contain the entirety of a mode of transit;
 * e.g., every bus stop in the city should be contained in our bus graph.
 * To represent a particular portion of a transit system,
 * like a bus route, consider using a TransitRoute instead.
 *
 * Note that weights in this graph correspond to typical transit times in minutes;
 * an edge weight of 3 means it takes 3 minutes to travel from the 
 * source vertex to the destination.
 */
public class TransitGraph extends WeightedMultigraph<TransitStop, TransitPath> {

  private int id;
  private String name;
  private TransitMode mode;

  /**
   * Public constructor.
   *
   * @param id the unique database id of the transit graph
   * @param name the name of the transit graph
   * @param mode the mode of transport represented in this graph
   * @param schedule the designated transit schedule for this graph
   */
  public TransitGraph(int id, String name, TransitMode mode) {
    super(TransitPath.class);
    this.id = id;
    this.name = name;
    this.mode = mode;
  }

  /**
   * Calculate the shortest path between the two transit stops.
   *
   * @param from the source stop
   * @return to the destination stop
   */
  public GraphPath<TransitStop, TransitPath> getShortestPath(TransitStop from, TransitStop to) {
    return DijkstraShortestPath.findPathBetween(this, from, to);
  }

  /**
   * Get the id of this transit graph.
   *
   * @return the id of this transit graph
   */
  public int getId() {
    return id;
  }

  /**
   * Get the name of this transit graph.
   *
   * @return the name of this transit graph
   */
  public String getName() {
    return name;
  }

  /**
   * Get the mode of transport of this transit graph.
   *
   * @return the mode of transport of this transit graph
   */
  public TransitMode getMode() {
    return mode;
  }

  /**
   * Determine if two graphs are the same graph.
   * Note: this does NOT check the actual graph data!
   * Equality checks are performed solely on id/name, as these are unique attributes in the database.
   * 
   * @param obj the object to check for equality
   * @return true iff the graphs share a name or an id
   */
  @Override
  public boolean equals(Object obj) {
    if(null == obj || !(obj instanceof TransitGraph)) {
      return false;
    }
    TransitGraph graph = (TransitGraph)obj;
    return (this.getId() == graph.getId()) || (this.getName().equals(graph.getName()));
  }

  /**
   * Disables the stop for graph traversal purposes by making the incoming edges infinite weight.
   *
   * @param stop the stop to disable
   * @return true if successful, false if not
   */
  public boolean disableStop(TransitStop stop) {
    if(null == stop || !containsVertex(stop)) {
      return false;
    }
    Set<TransitPath> incomingPaths = incomingEdgesOf(stop);
    for(TransitPath path : incomingPaths) {
      setEdgeWeight(path, Double.POSITIVE_INFINITY);
    }
    return true;
  }
}