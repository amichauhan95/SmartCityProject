package gov.smartcityteam2.Education;

import gov.smartcityteam2.*;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileWriter;
import java.io.IOException;




public class Teacher {
  private String firstName;
  private String lastName;
  private String email;
  private double salary; // Changed from baseSalary
  private String joiningDate;
  private String department;
  private String gradeLevel;
  private String selectedSchool;
  private int daysWorked;
  

  public Teacher() {

  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail() {
    this.email = this.firstName + "." +this.lastName + "@albany.edu";
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public String getJoiningDate() {
    return joiningDate;
  }

  public void setJoiningDate(String joiningDate) {
    this.joiningDate = joiningDate;

  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public void setgradeLevel(String gradeLevel){
    this.gradeLevel = gradeLevel;
  }

  public String gradeLevel(){

    if(gradeLevel == "college" || gradeLevel == "university")
    {
      return this.gradeLevel;
    }

    return gradeLevel + " school";
    
  }


  public void setselectedSchool(String selectedSchool){

    this.selectedSchool = selectedSchool;
  }


  public String selectedSchool(){
    return selectedSchool;
  }

  public void writeToFile(){
        try {
      FileWriter myWriter = new FileWriter("src/main/java/gov/smartcityteam2/Education/Teachers.txt", true);
      myWriter.write(User.getInstance().getID()+ "," + this.firstName + "," + this.lastName + "," + this.email+ "," + gradeLevel());
      myWriter.write("\n");
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  
  public int calculateDaysBetween(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            // Parse the input date string into a Date object
            Date date = sdf.parse(inputDate);

            // Get the current date
            Date today = new Date();

            // Calculate the difference in milliseconds between the two dates
            long timeDifference = date.getTime() - today.getTime();

            // Convert milliseconds to days
            long daysBetween = timeDifference / (1000 * 60 * 60 * 24);

            // Return the absolute value of the daysBetween (ignoring any negative sign)
            return Math.abs((int) daysBetween);
        } catch (ParseException e) {
            e.printStackTrace();
            // Handle parsing errors here
            return -1; // Return a negative value to indicate an error
        }

  }
}