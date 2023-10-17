/*
@author Ami Chauhan
Project: Smart City
@date 9/27/2023
I recieved help from: N/A
*/
package gov.smartCityGUI.hospital.model;

public class Doctor {


  // Variables for all data in Doctor
  private String userID;
  private String firstName;
  private String lastName;
  private String phone;
  private String email;
  private double conslutingFee;
  private boolean available;

  // consutructor to intialize data
  public Doctor(String userID, String firstName, String lastName, String phone, String email, double conslutingFee,
      boolean available) {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.email = email;
    this.conslutingFee = conslutingFee;
    this.available = available;

  }

  // Getter and Setters 
  public String getUserID() {
    return userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
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

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public double getConslutingFee() {
    return conslutingFee;
  }

  public void setConslutingFee(double conslutingFee) {
    this.conslutingFee = conslutingFee;
  }

  public boolean getAvailiblity() {
    return available;
  }

  public void setAvailiblity(boolean available) {
    this.available = available;
  }
}