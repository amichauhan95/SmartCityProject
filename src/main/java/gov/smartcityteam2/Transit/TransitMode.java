/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2.Transit;

/**
 * An enum of the modes of public transit available in the city.
 */
public enum TransitMode {
  TRAIN,
  BUS,
  LIGHT_RAIL,
  ALL_MODES;

  /**
   * Gets a print-friendly version of the mode name.
   *
   * @return a print-friendly version of the mode name
   */
  @Override
  public String toString() {
    switch(this) {
      case TRAIN:
        return "Train";

      case BUS:
        return "Bus";
        
      case LIGHT_RAIL:
        return "Light rail";

      case ALL_MODES:
        return "All transit modes";
        
      default:
        return null;
    }
  }
}