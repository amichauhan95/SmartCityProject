
/*
/*
@author Dylan Moran
Project: Smart City
@date 8/31/2023
I recieved help from: N/A
*/

package gov.smartcityteam2.Tax;

public class TaxUser{

  private String userID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String role;
  private double balance;
  private double totalTax;

  public TaxUser(){
  }

  public TaxUser(String userID){
    this.userID = userID;
  }

  public TaxUser(String userID, String firstName, String lastName, String email, String phoneNumber, String role){
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
  }

  public String getID(){
    return this.userID;
  }

  public String getFirst(){
    return this.firstName;
  }

  public String getLast(){
    return this.lastName;
  }

  public String getEmail(){
    return this.email;
  }

  public String getPhone(){
    return this.phoneNumber;
  }

  public String getRole(){
    return this.role;
  }

  public double getBalance(){
    return this.balance;
  }

  public double getTotalTax(){
    return this.totalTax;
  }

  public void setFirst(String firstName){
    this.firstName = firstName;
  }

  public void setLast(String lastName){
    this.lastName = lastName;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setPhone(String phoneNumber){
    this.phoneNumber = phoneNumber;
  }

  public void setRole(String role){
    this.role = role;
  }

  public void setBalance(double balance){
    this.balance = balance;
    TaxAccount.updateTaxInfo(this.userID, this.lastName, balance, this.totalTax);
  }

  public void setTotalTax(double totalTax){
    this.totalTax = totalTax;
    TaxAccount.updateTaxInfo(this.userID, this.lastName, this.balance, totalTax);
  }
}