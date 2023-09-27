package gov.smartcityteam2.Education;

public class Course {
  private String name;
  private int credits = 3;

  public Course(String name) {
    this.name = name;
  }

  public String getCourseName() {
    return name;
  }

  public int getCourseCredits() {
    return credits;
  }
}