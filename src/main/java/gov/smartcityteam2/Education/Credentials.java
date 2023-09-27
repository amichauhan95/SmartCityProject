package gov.smartcityteam2.Education;

import gov.smartcityteam2.*;
import java.io.FileWriter;
import java.io.IOException;

import java.lang.Math;

public class Credentials {

  private String studentID;
  private String name;
  private String lastName;
  private String age;
  private String address;
  private String email;
  private String GPA;
  private String schoolEnrollment;
  SchoolSystem userDefined;

  // no-arg constructor
  public Credentials() {

  }

  Credentials(String name, String lastName, String age, String GPA, SchoolSystem userDefined) {
    this.name = name;
    this.lastName = lastName;
    this.age = age;
    this.userDefined = userDefined;
    this.GPA = GPA;
    setStudentID();
    setEmail();

    try {
      FileWriter myWriter = new FileWriter("src/main/java/gov/smartcityteam2/Education/credentials.txt", true);
      myWriter.write(
          "" + User.getInstance().getID() + "," + this.name + "," + this.lastName + "," + this.age + "," + this.GPA);
      myWriter.write("\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }

  public void setStudentID() {
    int num1 = 1 + (int) (Math.random() * (9));
    int num2 = (int) (Math.random() * (10));
    int num3 = (int) (Math.random() * (10));
    int num4 = (int) (Math.random() * (10));

    this.studentID = ("" + num1 + num2 + num3 + num4);
  }

  public String getStudentID() {
    return this.studentID;
  }

  public void setEmail() {
    this.email = name.toLowerCase() + this.lastName.toLowerCase() + "@albany.edu.com";
  }

  public String getEmail() {
    return this.email;
  }

  public String getName() {
    return this.name;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getAge() {
    return this.age;
  }

  public String getGPA() {
    return this.GPA;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public void setGPA(String gpa) {
    GPA = gpa;
  }

  public int selectFromSchools() {

    int tempAge = Integer.parseInt(getAge());

    return tempAge;

  }

  public void setID(String id) {
    studentID = id;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
