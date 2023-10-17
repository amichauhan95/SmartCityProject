/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartCityGUI.transit;

/**
 * A class representing a particular stop in the city transit system.
 * These are used as vertices in a TransitGraph, and are connected by TransitPaths.
 */
public class TransitStop {

  private int id;
  private String name;
  /**
   * Public constructor.
   * 
   * @param id the unique database id of the transit stop
   * @param name the name of the transit stop
   */
  public TransitStop(int id, String name) {
    this.id = id;
    this.name = name;
  }

  /**
   * Get the id of this transit stop.
   *
   * @return the id of this transit stop
   */
  public int getId() {
    return id;
  }

  /**
   * Get the name of this transit stop.
   *
   * @return the name of this transit stop.
   */
  public String getName() {
    return name;
  }

  /**
   * Generate a string representaiton of this transit stop.
   * 
   * @return a string representation of this transit stop
   */
  @Override
  public String toString()  {
    return name;
  }

  /**
   * Check for equality between two transit stops.
   *
   * @return iff the stops have the same id.
   */
  @Override
  public boolean equals(Object obj) {
    if(null == obj || !(obj instanceof TransitStop)) {
      return false;
    }
    TransitStop stop = (TransitStop)obj;
    return this.getId() == stop.getId();
  }
}