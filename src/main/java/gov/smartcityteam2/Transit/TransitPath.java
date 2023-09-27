/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * A representation of a transit path in the city transit network,
 * like a road or a rail line. TransitPaths are used as vertices 
 * within a TransitGraph to connect adjacent TransitStops.
 */
public class TransitPath extends DefaultWeightedEdge {

  private int id;
  private String name;
  private double weight;
  // weight is stored & accessed via the enclosing graph interface

  /**
   * Public constructor.
   *
   * @param id the unique database id of the transit path
   * @param name the name of the transit path
   */
  public TransitPath(int id, String name) {
    super();
    this.id = id;
    this.name = name;
  }

  /**
   * Get the id of this transit path.
   *
   * @return the id of this transit path
   */
  public int getId() {
    return id;
  }

  /**
   * Get the name of this transit path.
   *
   * @return the name of this transit path
   */
  public String getName() {
    return name;
  }

  public double getWeight() {
    return weight;
  }

  /**
   * Generate a string representation of this transit path.
   *
   * @return a string representation of this transit path
   */
  @Override
  public String toString() {
    return name;
  }

    /**
   * Determine if two paths are the same path.
   * Note: this does NOT check the actual path data!
   * Equality checks are performed solely on id/name, as these are unique attributes in the database.
   * 
   * @param obj the object to check for equality
   * @return true iff the paths share a name or an id
   */
  @Override
  public boolean equals(Object obj) {
    if(null == obj || !(obj instanceof TransitPath)) {
      return false;
    }
    TransitPath path = (TransitPath)obj;
    return (this.getId() == path.getId()) || (this.getName().equals(path.getName()));
  }
}