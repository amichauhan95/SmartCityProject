
package gov.smartCityGUI.hospital.model;

public class Patient {

  private String userID;
  private String firstName;
  private String lastName;
  private String birthDate;
  private String phone;
  private String address;
  private String email;
  private String appointmentTime;
  private String insuranceID;
  private String appointmentID;
  private boolean status;

  public Patient() {
    this.userID = "";
    this.firstName = "";
    this.lastName = "";
    this.birthDate = "";
    this.phone = "";
    this.address = "";
    this.email = "";
    this.appointmentTime = "";
    this.insuranceID = "";
    this.appointmentID = "";
    this.status = false;
  }

  public Patient(String userID, String firstName, String lastName, String birthDate, String phone, String address,
      String email, String appointmentTime, String insuranceID, String appointmentID, boolean status) {
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthDate = birthDate;
    this.phone = phone;
    this.address = address;
    this.email = email;
    this.appointmentTime = appointmentTime;
    this.insuranceID = insuranceID;
    this.appointmentID = appointmentID;
    this.status = status;
  }

  public String getID() {
    return this.userID;
  }

  public String getFirst() {
    return this.firstName;
  }

  public String getLast() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPhone() {
    return this.phone;
  }

  public String getBirthDate() {
    return this.birthDate;
  }

  public String getAddress() {
    return this.address;
  }

  public String getAppointmentTime() {
    return this.appointmentTime;
  }

  public String getInsuranceID() {
    return this.insuranceID;
  }

  public String getAppointmentID() {
    return this.appointmentID;
  }

  public boolean getStatus() {
    return this.status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

}