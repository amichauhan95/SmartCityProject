/**
  * Team Member(s) working on this class: Melih Kartal
  * Project: Smart City
  * @author: Melih Kartal
**/
package gov.smartCityGUI.education.models;

public class Course {
	private String name;
	private int credits = 3;

  /**
    * public Course(String name) sets the name of the course
    * @param String name represents the name passed in by the user
  **/
	public Course(String name) {
		this.name = name;
	}

  /**
    * public String getCourseName() gets the name of the course 
    * @return name returns the name of the course
  **/
	public String getCourseName() {
		return name;
	}

  /**
    * public String getCourseCredits() gets the name course credits
    * @return name returns the number of course credits
  **/
	public int getCourseCredits() {
		return credits;
	}
}