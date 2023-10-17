/*
@author Ami Chauhan
Project: Smart City
@date 9/27/2023
I recieved help from: N/A
*/
package gov.smartCityGUI.hospital.model;

public class Hospital {

   // Variables for all data in Hospital
  private int hospitalID;
  private String hospitalName;
  private String hospitalAddress;
  private String hospitalPhone;
  private String hospitalEmail;

  // consutructor to intialize data
  public Hospital() {
    this.hospitalID = 0;
    this.hospitalName = "";
    this.hospitalAddress = "";
    this.hospitalPhone = "";
    this.hospitalEmail = "";
  }

  // consutructor to intialize data
  public Hospital(int id, String name, String address, String phone, String email) {
    this.hospitalID = id;
    this.hospitalName = name;
    this.hospitalAddress = address;
    this.hospitalPhone = phone;
    this.hospitalEmail = email;
  }

   // Getter and Setters
  public int getHospitalID() {
    return hospitalID;
  }

  public void setHospitalID(int hospitalID) {
    this.hospitalID = hospitalID;
  }

  public void setHospitalName(String hospitalName) {
    this.hospitalName = hospitalName;
  }

  public String getHospitalName() {
    return hospitalName;
  }

  public void setHospitalAddress(String hospitalAddress) {
    this.hospitalAddress = hospitalAddress;
  }

  public String getHospitalAddress() {
    return hospitalAddress;
  }

  public void setHospitalPhone(String hospitalPhone) {
    this.hospitalPhone = hospitalPhone;
  }

  public String getHospitalPhone() {
    return hospitalPhone;
  }

  public String getHospitalEmail() {
    return hospitalEmail;
  }

  public void setHospitalEmail(String hospitalEmail) {
    this.hospitalEmail = hospitalEmail;
  }

  // funcation to display data in string
  public String toString() {
    return String.format("\n\tName: %s\n\tAddress: %s\n\tPhone: %s\n\tEmail: %s\n\n", hospitalName, hospitalAddress,
        hospitalPhone, hospitalEmail);
  }
}