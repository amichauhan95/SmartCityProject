/*
Team Member(s) working on this class: Natalie Darsillo
Project: Smart City
I recieved help from: N/A
*/

package gov.smartcityteam2;

/**
 * A functional interface for a menu provider.
 * Implementations provide a void functon, printOptions(), which prints the options for a text-based menu.
 * NOTE: 0 is always the quit option, and -1 is the error response.
 * Therefore, choices provided should be integers >= 1.
 */
public interface IMenuProvider {
  void printOptions();
}