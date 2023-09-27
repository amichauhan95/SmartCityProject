package gov.smartCityGUI.hospital.model;

public class Hospital {

  private int hospitalID;
  private String hospitalName;
  private String hospitalAddress;
  private String hospitalPhone;
  private String hospitalEmail;

  public Hospital() {
    this.hospitalID = 0;
    this.hospitalName = "";
    this.hospitalAddress = "";
    this.hospitalPhone = "";
    this.hospitalEmail = "";
  }

  public Hospital(int id, String name, String address, String phone, String email) {
    this.hospitalID = id;
    this.hospitalName = name;
    this.hospitalAddress = address;
    this.hospitalPhone = phone;
    this.hospitalEmail = email;
  }

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

  public String toString() {
    return String.format("\n\tName: %s\n\tAddress: %s\n\tPhone: %s\n\tEmail: %s\n\n", hospitalName, hospitalAddress,
        hospitalPhone, hospitalEmail);
  }
}