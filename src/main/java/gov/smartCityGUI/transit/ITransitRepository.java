/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartCityGUI.transit;

import java.util.List;
import org.json.JSONObject;

/**
 * This interface defines a contract for a repository-level construct
 * which handles data queries between the upper service layer and the
 * underlying database.
 */
public interface ITransitRepository {
  /**
   * Get a list of stored transit graphs from the database.
   *
   * @return a list of stored transit graphs from the database
   */
  List<TransitGraph> getTransitGraphs();

  /**
   * Get a list of stored transit stops from the database.
   *
   * @return a list of stored transit stops from the database
   */
  List<TransitStop> getTransitStops();

  /**
   * Get a list of stored transit routes from the database.
   *
   * @return a list of stored transit routes from the database
   */
  List<TransitRoute> getTransitRoutes();

  /**
   * Get fare informaton from the database for the given mode.
   *
   * @return fare information from the database for the given mode
   */
  JSONObject getFareInformation(TransitMode mode);
}