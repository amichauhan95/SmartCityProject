/**
  * Team Member(s) working on this class: Keith Austin
  * Project: Smart City
  * @author: Keith Austin
  * I received help from everyone on my team
**/

package gov.smartcityteam2.EMS;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.FileNotFoundException;

public class Report {
  private String firstName, lastName, email, phoneNumber, streetAddress, report;
  public User user;
  Scanner sc = new Scanner(System.in);

  /**
    * public String getFirstName() returns the first name of the user
    * @return the first name of the user
  **/
  public String getFirstName() {
    return firstName;
  }

  /**
    * public void setFirstName(String firstName) sets the current instance of firstName to the parameter instance
    * @param String firstName is the first name created by the user
  **/
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  /**
    * public String getLastName() returns the last name of the user
    * @return the last name of the user
  **/
  public String getLastName() {
    return lastName;
  }

  /**
    * public void setLastName(String lastName) sets the current instance of lastName to the parameter instance
    * @param String lastName is the last name created by the user
  **/
  public void setLastName(String lastName) {
    this.lastName = firstName;
  }

  /**
    * public String getEmail() returns the email of the user
    * @return the email of the user
  **/
  public String getEmail() {
    return email;
  }

  /**
    * public void setEmail(String email) sets the current instance of email to the parameter instance
    * @param String email is the email created by the user
  **/
  public void setEmail(String email) {
    this.email = email;
  }

  /**
    * public String getPhoneNumber() returns the phone number of the user
    * @return the phone number of the user
  **/
  public String getPhoneNumber() {
    return phoneNumber;
  }

  /**
    * public void setPhoneNumber(String phoneNumber) sets the current instance of phoneNumber to the parameter instance
    * @param String phoneNumber is the phone number created by the user
  **/
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  /**
    * public String getStreetAddress() returns the street address of the user
    * @return the street address of the user
  **/
  public String getStreetAddress() {
    return streetAddress;
  }

  /**
    * public void setStreetAddress(String streetAddress) the current instance of streetAddress to the parameter instance
    * @param String streetAddress is the street address created by the user
  **/
  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  /**
    * public String getReport() returns the report created by the user
    * @return the report from the user
  **/
  public String getReport() {
    return report;
  }

  /**
    * public void setReport(String report) sets the current instance of report to the parameter instance
    * @param report is the report created by the user
  **/
  public void setReport(String report) {
    this.report = report;
  }
  
  LocalDateTime x = LocalDateTime.now();
  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
  DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
  String formattedDate = x.format(dateFormatter);
  String formattedTime = x.format(timeFormatter);
  String date = formattedDate;
  String time = formattedTime;
  
  /**
    * public void writeToFile() writes the users data to the file
    * @throws IOException if the data isn't written to the file successfully
  **/
  public void writeToFile() throws IOException {
    try {
      FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/EMS/data/reports.txt", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(toString());
      bw.flush();
      bw.close();
    } catch (IOException e) {
      System.out.println("An error has occurred");
      e.printStackTrace();
    }
  }
  
  /**
    * public void readFile() reads the user's data from the file
    * @throws FileNotFoundException if the file is not found
  **/
  public void readFile() throws FileNotFoundException {
    try {
      Scanner sc = new Scanner("src/main/java/gov/smartcityteam2/EMS/data/reports.txt");
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
    * public void createReport() prompts the user to enter in data related to their report and sets the attributes
    * @throws IOException if the data isn't written to the file successfully
  **/
  public void createReport() throws IOException {
    System.out.print("First Name: ");
    firstName = sc.next();
    System.out.print("Last Name: ");
    lastName = sc.next();
    System.out.print("Email: ");
    email = sc.next();
    System.out.print("Phone Number: ");
    phoneNumber = sc.next();
    System.out.print("Street Address (62 Peachwood Road, Albany NY, 12208): ");
    streetAddress = sc.next();
    System.out.print("Enter your report here: ");
    report = sc.next();
    System.out.println();
    System.out.println("Your report has successfully been recorded!");
    System.out.println();

    setFirstName(firstName);
    setLastName(lastName);
    setEmail(email);
    setPhoneNumber(phoneNumber);
    setStreetAddress(streetAddress);
    setReport(report);
    
    writeToFile();
  }

  /**
    * public String toString() displays the string representaion of the class object
    * @return the string representation of the class object
  **/
  @Override
  public String toString() {
    return getFirstName() + ", " + getLastName() + ", " + getEmail() + ", " + getPhoneNumber() + ", " + getReport() + ", " + date + ", " + time;
  }
}