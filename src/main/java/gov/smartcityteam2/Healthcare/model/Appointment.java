
package gov.smartcityteam2.Healthcare.model;

public class Appointment {

  private String appointmentID;
  private String appointmentTime;
  private String appointmentDoctor;
  private double paymentBill;
  private String reportSymptoms;
  private String reportTreatments;

  public Appointment(String appointmentID, String appointmentTime,
      String appointmentDoctor, double paymentBill, String reportSymptoms, String reportTreatments) {

    this.appointmentID = appointmentID;
    this.appointmentTime = appointmentTime;
    this.appointmentDoctor = appointmentDoctor;
    this.reportSymptoms = reportSymptoms;
    this.reportTreatments = reportTreatments;
    this.paymentBill = paymentBill;
  }

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