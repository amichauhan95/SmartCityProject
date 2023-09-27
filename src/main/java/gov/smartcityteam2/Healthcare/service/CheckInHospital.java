package gov.smartcityteam2.Healthcare.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import gov.smartcityteam2.Healthcare.model.Appointment;


public class CheckInHospital {

  String patientFirstName;
  String patientLastName;
  String insuranceID;
  String appointmentID;
  String appointmentDoctor;
  boolean appointmentStatus = false;
  Scanner scan = new Scanner(System.in);
  BookAppointment b1 = new BookAppointment();
  DoctorList d1 = new DoctorList();
 
  ArrayList<Appointment> reportList = new ArrayList<Appointment>();

  // fucntion to check the patient into the hospital
  public boolean checkInAppointment() {

    System.out.println("Enter Patient's First Name: ");
    patientFirstName = scan.nextLine();
    System.out.println("Enter Patient's Last Name: ");
    patientLastName = scan.nextLine();
    System.out.println("Enter Patient's Insurance ID: ");
    insuranceID = scan.nextLine();
    System.out.println("Enter Patient's Appointment ID: ");
    appointmentID = scan.nextLine();
    appointmentStatus = b1.getAppointment(appointmentID, patientFirstName, patientLastName, insuranceID);
    if (appointmentStatus) {
      System.out.println("\nThank you for checking in ! ");
      assignDoctor(appointmentID);

      return true;
    } else {
      System.out.println("\nAppointment not available");
      return false;
    }
  }

  public void assignDoctor(String appointmentID) {
    // Remove Doctor Availiblity When Patient is assigned
    // Find the next available Doctor
    // If Doctor is not available show waiting time to patient
    // Assign doctor to patient in PatientDoctor File
    String time = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    String none = "N/A";
    appointmentDoctor = d1.getAvailableDoctor();

    try {
      FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/Healthcare/static/Appointment.txt", true);
      BufferedWriter bw = new BufferedWriter(fw);

      bw.newLine();
      bw.write(appointmentID + ";" + time + ";" + appointmentDoctor + ";" + none + ";" + none);

      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    b1.updateAppointmentStatus(appointmentID);

  }

  public void addReport() {
    reportList.clear();
    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartcityteam2/Healthcare/static/Appointment.txt"));
      String line;
      while ((line = br.readLine()) != null) {

        String split[] = line.split(";");
        double fee = Double.parseDouble(split[3]);
        reportList.add(new Appointment(split[0], split[1], split[2], fee, split[4], split[5]));

      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void updateReport() {

    int count = 0;
    try {

      FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/Healthcare/static/Appointment.txt", false);
      BufferedWriter bw = new BufferedWriter(fw);

      for (Appointment b : reportList) {

        bw.write(b.getAppointmentID() + ";" + b.getAppointmentTime() + ";" + b.getAppointmentDoctor() + ";"
            + b.getPaymentBill() + ";"
            + b.getReportSymptoms() + ";" + b.getReportTreatments());
        if (count < (reportList.size() - 1)) {
          bw.newLine();

        }

        count++;

      }

      bw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void submitReport(String doctorID) {
    // AppointmentID;AppointmentDate;AppointmentDoctor;DoctorFee;DoctorReportSymptoms;ReportTreatments
    // Update Doctor Availiblity When Patient Report is submitted
    addReport();
    System.out.println("Enter Patient's Appointment ID: ");
    String appointmentID = scan.nextLine();
    for (Appointment a : reportList) {
      if (a.getAppointmentID().equals(appointmentID) && a.getAppointmentDoctor().equals(doctorID)) {
        System.out.println("Enter Patient's Symptoms: ");
        String patientSymptoms = scan.nextLine();
        System.out.println("Enter Patient's Treatments: ");
        String patientTreatments = scan.nextLine();
        System.out.println("Update Total Bill for appointment: ");
        double patientCharges = scan.nextDouble();
        a.setReportSymptoms(patientSymptoms);
        a.setReportTreatments(patientTreatments);
        a.setPaymentBill(patientCharges);
        System.out.println("Report Updated Successfully! ");
        break;
      }
    }
    updateReport();
    d1.freeAvailiblity(doctorID);

  }


  public void updateFees(String appointmentID,double amount) {
    // AppointmentID;AppointmentDate;AppointmentDoctor;DoctorFee;DoctorReportSymptoms;ReportTreatments
    // Update Doctor Availiblity When Patient Report is submitted
    addReport();
    for (Appointment a : reportList) {
      if (a.getAppointmentID().equals(appointmentID)) {
        
        a.setPaymentBill(amount);
        System.out.println("Payment updated Successfully! ");
        break;
      }
    }
    updateReport();
  
  }
  
  public void getPatientsReport(String doctorID) {
    addReport();
    System.out.println("Enter Date: ");
    String date = scan.nextLine();
    for (Appointment a : reportList) {
      if (a.getAppointmentTime().equals(date) && a.getAppointmentDoctor().equals(doctorID)) {

        System.out.println("\n------------------------------------------");
        System.out.println("Patient's Appointment ID: " + a.getAppointmentID() + "\nPatients Symptoms: "
            + a.getReportSymptoms() + "\nPatient's Treatments: " + a.getReportTreatments()+"\nAppointment Bill: " + a.getPaymentBill());
        System.out.println("------------------------------------------");

      }

    }
  }

  public void viewPatientsReport() {
    addReport();
    System.out.println("Enter Appointment ID: ");
    String appointmentID = scan.nextLine();
    for (Appointment a : reportList) {
      if (a.getAppointmentID().equals(appointmentID)) {

        System.out.println("\n------------------------------------------");
        System.out.println(
            "Patient's Appointment ID: " + a.getAppointmentID() + "\nPatients Symptoms: " + a.getReportSymptoms()
                + "\nPatient's Treatments: " + a.getReportTreatments() + "\nAppointment Bill: " + a.getPaymentBill());
        System.out.println("------------------------------------------");

      }

    }
  }

  public double payBill(String appointmentID) {
    double totalBill=0;
    addReport();
    for (Appointment a : reportList) {
      if (a.getAppointmentID().equals(appointmentID)) {

        System.out.println("\n------------------------------------------");
        System.out.println(
            "Patient's Appointment ID: " + a.getAppointmentID() + "\nPatients Symptoms: " + a.getReportSymptoms()
                + "\nPatient's Treatments: " + a.getReportTreatments() + "\nTotal Bill Balance: " + a.getPaymentBill());
        System.out.println("------------------------------------------");
        totalBill = a.getPaymentBill();
        break;
        
      }

    }
 return totalBill;
    
  }

}
