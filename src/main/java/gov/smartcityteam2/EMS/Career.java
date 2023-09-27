/**
  * Team Member(s) working on this class: Keith Austin
  * Project: Smart City
  * @author: Keith Austin
  * I received help from everyone on my team
**/

package gov.smartcityteam2.EMS;

import java.util.Scanner;
import java.io.File;
import gov.smartcityteam2.User;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Career {
  private String firstName, lastName, email, phoneNumber, dob, coverLetter, position;

  /**
    * public String getFirstName() returns the user's first name
    * @return the first name of the user
  **/
  public String getFirstName() {
    return firstName;
  }

  /**
    * public void setFirstName(String firstName) sets the user's first name
    * @param String firstName is the first name created by the user
  **/
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
    * public String getLastName() returns the last name created by the user
    * @return the last name of the user
  **/
  public String getLastName() {
    return lastName;
  }

  /**
    * public void setLastName(String lastName) sets the user's last name 
    * @param String lastName is the last name created by the user
  **/
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  /**
    * public String getEmail() returns the email created by the user
    * @return the email of the user
  **/
  public String getEmail() {
    return email;
  }

  /**
    * public void setEmail(String email) sets the user's email
    * @param String email is the email created by the user
  **/
  public void setEmail(String email) {
    this.email = email;
  }

  /**
    * public String getPhoneNumber() returns the phone number created by the user
    * @return the user's phone number
  **/
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
    * public void setPhoneNumber(String phoneNumber) sets the user's phone number 
    * @param String phoneNumber is the phone number created by the user
  **/
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
    * public String getDOB() returns the date of birth entered by the user
    * @return the date of birth created by the user
  **/
  public String getDOB() {
    return dob;
  }

  /**
    * public void setDOB(String dob) sets the user's date of birth
    * @param String dob is the date of birth created by the user
  **/
  public void setDOB(String dob) {
    this.dob = dob;
  }

  /**
    * public String getPosition() returns the position entered by the user
    * @return the report from the user
  **/
  public String getPosition() {
    return position;
  }

  /**
    * public void setPosition(String position) sets the user's position
    * @param String position is the position entered by the user
  **/
  public void setPosition(String position) {
    this.position = position;
  }

  /**
    * public String getCoverLetter() returns the cover letter created by the user
    * @return the user's cover letter 
  **/
  public String getCoverLetter() {
    return coverLetter;
  }

  /**
    * public void setCoverLetter(String coverLetter) sets the user's cover letter
    * @param String coverLetter is the cover letter created by the user
  **/
  public void setCoverLetter(String coverLetter) {
    this.coverLetter = coverLetter;
  }

  FileWriter fw = null;
  BufferedWriter bw = null;
  
  /**
    * public void readFile() reads the user's data from the file
    * @throws FileNotFoundException if the file is not found
  **/
  public void readFile() throws FileNotFoundException {
    try {
      Scanner sc = new Scanner("src/main/java/gov/smartcityteam2/EMS/data/careers.txt");
      while (sc.hasNextLine()) {
        String line = sc.nextLine();
        System.out.println(line);
      }
      sc.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  /**
    * public void writeToFile(ArrayList<String> newApplicant) writes the users data to the file
    * @param ArrayList<String> newApplicant stores the user's data in a ArrayList
    * @throws IOException if the data isn't written to the file successfully
  **/
  public void writeToFile(ArrayList<String> newApplicant) throws IOException {
    try {
      fw = new FileWriter("src/main/java/gov/smartcityteam2/EMS/data/applicants.txt", true);
      bw = new BufferedWriter(fw);
      bw.write(newApplicant.toString());
      bw.flush();
      bw.close();
    } catch (IOException e) {
      System.out.println("An error has occurred");
      e.printStackTrace();
    }
  }

  /**
    * public void apply() prompts the user with a job application fill out form
    * @throws Exception if the data isn't written to the file correctly
  **/
  public void apply() throws Exception {
    ArrayList<String> newApplicant = new ArrayList<String>();
    Scanner sc = new Scanner(System.in);
    
    System.out.println("EMS | Career");
    System.out.println();
    System.out.println("Current Job Listing");
    readFile();
    System.out.println();
    System.out.print("First Name: ");
    String firstName = sc.nextLine();
    newApplicant.add(firstName);
    System.out.print("Last Name: ");
    String lastName = sc.nextLine();
    newApplicant.add(lastName);
    System.out.print("Email: ");
    String email = sc.nextLine();
    newApplicant.add(email);
    System.out.print("Phone number: ");
    String phoneNumber = sc.nextLine();
    newApplicant.add(phoneNumber);
    System.out.print("Date of Birth (mm/dd/yyyy): ");
    String dob = sc.nextLine();
    newApplicant.add(dob);
    System.out.print("Position: ");
    String pos = sc.nextLine();
    newApplicant.add(pos);
    System.out.print("Cover Letter (Optional): ");
    String coverLetter = sc.nextLine();
    newApplicant.add(coverLetter);
    System.out.println("Thank you for applying! We'll get back to you soon!");
    sc.close();

    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setDOB(dob);
    setPosition(pos);
    setCoverLetter(coverLetter);

    writeToFile(newApplicant);
  }

  /**
    * public String toString() displays the string representation of the class object
    * @return the string representation of the class object
    * @Override
  **/
  @Override
  public String toString() {
    return User.getInstance().getID() + ", " + getFirstName() + ", " + getLastName() + ", " + getEmail() + ", " + getPhoneNumber() + ", " + getDOB() + ", " + getPosition() + ", " + getCoverLetter();
  }
}