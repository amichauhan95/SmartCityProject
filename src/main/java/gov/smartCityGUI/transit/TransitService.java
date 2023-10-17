/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartCityGUI.transit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.jgrapht.Graphs;
import org.json.JSONObject;

/**
 * This is an implementation of the ITransitService contract,
 * which defines a number of data operations to process incoming
 * data from the repository and pass it on to the controller.
 */
public class TransitService implements ITransitService {

  // Any additional methods needed in here should be reflected in the ITransitService
  // interface as well; we only take the interface type in-code, not the concrete type.

  private ITransitRepository repository;

  /**
   * Public constructor.
   *
   * @param repository a repository layer instance
   */
  public TransitService(ITransitRepository repository) {
    this.repository = repository;
  }

  /**
   * Get a list of valid transit stops.
   * 
   * @return a list of valid transit stops
   */
  public List<TransitStop> getTransitStops() {
    return repository.getTransitStops();
  }

  /**
   * Get fare information for a given mode of transport.
   *
   * @return fare information for the given mode of transport
   */
  public JSONObject getFareInformation(TransitMode mode) {
    if(mode.equals(TransitMode.ALL_MODES)) {
      JSONObject combined = new JSONObject();
      List<TransitMode> allModes = new ArrayList<>(Arrays.asList(TransitMode.values()));
      allModes.remove(TransitMode.ALL_MODES);
      for(TransitMode ourMode : allModes) {
        combined.put(ourMode.toString(), repository.getFareInformation(ourMode));
      }
      return combined;
    } else {
      return repository.getFareInformation(mode);
    }
  }
  
  /**
   * Get a union of transit graphs for the given mode of transport.
   *
   * @param mode the mode of transport
   * @return a graph which is the union of every transit graph for the given mode
   */
  public TransitGraph getWholeTransitGraphForMode(TransitMode mode) {
    List<TransitGraph> graphs = repository.getTransitGraphs();
    TransitGraph wholeGraph;
    if(!mode.equals(TransitMode.ALL_MODES)) {
      graphs = graphs.stream()
      .filter(graph -> graph.getMode().equals(mode))
      .collect(Collectors.toList());
      wholeGraph = new TransitGraph(-1, "Albany+ " + mode.toString() + " System", mode);
    } else {
      wholeGraph = new TransitGraph(-1, "Albany+ Consolidated Transit System", mode);
    }
    for(int i = 0; i < graphs.size(); ++i) {
      Graphs.addGraph(wholeGraph, graphs.get(i));
    }
    return wholeGraph;
  }

  /**
   * Get a list of transit graph names.
   * 
   * @return a list of transit graph names
   */
  public List<String> getTransitGraphNames() {
    List<TransitGraph> graphs = repository.getTransitGraphs();
    List<String> graphNames = graphs.stream()
      .map(graph -> graph.getName())
      .collect(Collectors.toList());
    return graphNames;
  }

    /**
   * Get a graph which is the union of every transit graph in the city;
   * i.e., a graph including every transit route.
   * @return a graph which is the union of every transit graph in the city
   */
  public TransitGraph getWholeTransitGraph() {
    List<TransitGraph> graphs = repository.getTransitGraphs();
    TransitGraph wholeGraph = new TransitGraph(-1, "Albany+ Consolidated Transit System", TransitMode.ALL_MODES);
    for(int i = 0; i < graphs.size(); ++i) {
      Graphs.addGraph(wholeGraph, graphs.get(i));
    }
    return wholeGraph;
  }

  /**
   * Get a list of every transit graph.
   *
   * @return a list of every transit graph
   */
  public List<TransitGraph> getTransitGraphs() {
    return repository.getTransitGraphs();
  }

  /**
   * Get a list of all transit stops in the graph.
   *
   * @param graph the transit graph to pull the stops from
   * @return a list of all transit stops in the graph
   */
  public List<TransitStop> getTransitStopsInGraph(TransitGraph graph) {
    return new ArrayList<TransitStop>(graph.vertexSet());
  }

    /**
   * Get a list of every transit route.
   *
   * @return a list of every transit route
   */
  public List<TransitRoute> getTransitRoutes() {
    return repository.getTransitRoutes();
  }
}