/*
@author Ami Chauhan
Project: Smart City
@date 9/27/2023
I recieved help from: N/A
*/
package gov.smartCityGUI.hospital.model;

public class Appointment {

  // Variables for all data in Appointments
  private String appointmentID;
  private String appointmentTime;
  private String appointmentDoctor;
  private double paymentBill;
  private String reportSymptoms;
  private String reportTreatments;

  // consutructor to intialize data
  public Appointment(String appointmentID, String appointmentTime,
      String appointmentDoctor, double paymentBill, String reportSymptoms, String reportTreatments) {

    this.appointmentID = appointmentID;
    this.appointmentTime = appointmentTime;
    this.appointmentDoctor = appointmentDoctor;
    this.reportSymptoms = reportSymptoms;
    this.reportTreatments = reportTreatments;
    this.paymentBill = paymentBill;
  }

  // Getter and Setters 
  public String getAppointmentID() {
    return appointmentID;
  }

  public void setAppointmentID(String appointmentID) {
    this.appointmentID = appointmentID;
  }

  public String getAppointmentTime() {
    return appointmentTime;
  }

  public void setAppointmentTime(String appointmentTime) {
    this.appointmentTime = appointmentTime;
  }

  public String getAppointmentDoctor() {
    return appointmentDoctor;
  }

  public void setAppointmentDoctor(String appointmentDoctor) {
    this.appointmentDoctor = appointmentDoctor;
  }

  public String getReportSymptoms() {
    return reportSymptoms;
  }

  public void setReportSymptoms(String reportSymptoms) {
    this.reportSymptoms = reportSymptoms;
  }

  public String getReportTreatments() {
    return reportTreatments;
  }

  public void setReportTreatments(String reportTreatments) {
    this.reportTreatments = reportTreatments;
  }

  public double getPaymentBill() {
    return paymentBill;
  }

  public void setPaymentBill(double paymentBill) {
    this.paymentBill = paymentBill;
  }

}