package gov.smartCityGUI.education.models;

import java.util.ArrayList;
import java.util.List;

public class SchoolEnrollment {
	private int courseCounter = 0;
	private String name;
	private List<Course> courses;

	public SchoolEnrollment(String name) {
		this.name = name;
		this.courses = new ArrayList<>();
	}

	public void addCourse(String name) {
		courses.add(new Course(name));
	}

	public List<Course> getCourses() {
		return courses;
	}

	public String getName() {
		return name;
	}

	public int getTotalCourses() {
		return courseCounter;
	}
}
