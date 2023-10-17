/**
 * Team Member(s) working on this class: Melih Kartal
 * Project: Smart City
 * Author: Melih Kartal
**/

// Import statements for necessary packages and classes
package gov.smartCityGUI.education.gui;
import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.education.controller.*;
import gov.smartCityGUI.education.models.*;
import gov.smartCityGUI.utilities.*;

import java.util.ArrayList;
import java.util.List;

public class SchoolEnrollment {
  // Private member variables
  private int courseCounter = 0;  // Counter for the number of courses
  private String name;            // Name of the school enrollment
  private List<Course> courses;   // List to store enrolled courses

  // Constructor for creating a SchoolEnrollment instance
  public SchoolEnrollment(String name) {
    this.name = name;
    this.courses = new ArrayList<>();  // Initialize the courses list
  }

  // Method to add a course to the enrollment
  public void addCourse(String name) {
    courses.add(new Course(name));  // Create a new Course instance and add it to the list
  }

  // Method to retrieve the list of enrolled courses
  public List<Course> getCourses() {
    return courses;
  }

  // Method to retrieve the name of the school enrollment
  public String getName() {
    return name;
  }

  // Method to get the total number of courses enrolled
  public int getTotalCourses() {
    return courseCounter;
  }
}
