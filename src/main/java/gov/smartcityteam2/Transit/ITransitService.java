/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

import java.util.List;
import org.json.JSONObject;

/**
 * This interface defines a contract for a service-level construct
 * which handles data processing between the upper controller layer
 * and the below repository layer.
 */
public interface ITransitService {

  /**
   * Get a list of valid transit stops.
   * 
   * @return a list of valid transit stops
   */
  List<TransitStop> getTransitStops();

  /**
   * Get fare information for a given mode of transport.
   *
   * @return fare information for the given mode of transport
   */
  JSONObject getFareInformation(TransitMode mode);
  
  /**
   * Get a union of transit graphs for the given mode of transport.
   *
   * @param mode the mode of transport
   * @return a graph which is the union of every transit graph for the given mode
   */
  TransitGraph getWholeTransitGraphForMode(TransitMode mode);

  /**
   * Get a list of transit graph names.
   * 
   * @return a list of transit graph names
   */
  List<String> getTransitGraphNames();

  /**
   * Get a graph which is the union of every transit graph in the city;
   * i.e., a graph including every transit graph.
   * @return a graph which is the union of every transit graph in the city
   */
  TransitGraph getWholeTransitGraph();

  /**
   * Get a list of every transit graph.
   *
   * @return a list of every transit graph
   */
  List<TransitGraph> getTransitGraphs();

  /**
   * Get a list of all transit stops in the graph.
   *
   * @param graph the transit graph to pull the stops from
   * @return a list of all transit stops in the graph
   */
  List<TransitStop> getTransitStopsInGraph(TransitGraph graph);

  /**
   * Get a list of every transit route.
   *
   * @return a list of every transit route
   */
  List<TransitRoute> getTransitRoutes();
}