/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

import java.time.LocalTime;

/**
 * A class defining a transit schedue for a given TransitRoute.
 * The stored times correspond to transit start and stop times (e.g., a route
 * with start = 6:00 and stop = 18:00 means that a bus route starts at 6:00am
 * and runs until 6:00pm. The remainder of the times for the day are calculated 
 * dynamically from graph data via the TransitRoute.getTimes() method.
 */
public class TransitSchedule {

  private int id;
  private LocalTime start;
  private LocalTime stop;

  /**
   * Public constructor.
   *
   * @param id    the unique database id of this schedule
   * @param start the start time for this transit schedule
   * @param stop  the stop time for this transit schedule
   */
  public TransitSchedule(int id, LocalTime start, LocalTime stop) {
    this.id = id;
    this.start = start;
    this.stop = stop;
  }

  /**
   * Get the unique database id of this schedule.
   *
   * @return the unique database id of this schedule
   */
  public int getId() {
    return id;
  }

  /**
   * Get the start time for this transit schedule.
   *
   * @return the start time for this transit schedule
   */
  public LocalTime getStartTime() {
    return start;
  }

  /**
   * Get the stop time for this transit schedule.
   *
   * @return the stop time for this transit schedule
   */
  public LocalTime getStopTime() {
    return stop;
  }
}