package gov.smartCityGUI.hospital.service;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class CheckInHospital {

  String patientFirstName;
  String patientLastName;
  String insuranceID;
  String appointmentID;
  String appointmentDoctor;
  boolean appointmentStatus = false;
  BookAppointment b1 = new BookAppointment();
  DoctorList d1 = new DoctorList();

  ArrayList<Appointment> reportList = new ArrayList<Appointment>();

  // fucntion to check the patient into the hospital
  public boolean checkInAppointment(String patientFirstName, String patientLastName, String insuranceID,
      String appointmentID) {

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
      FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/hospital/static/Appointment.txt", true);
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
          new FileReader("src/main/java/gov/smartCityGUI/hospital/static/Appointment.txt"));
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

      FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/hospital/static/Appointment.txt",
          false);
      BufferedWriter bw = new BufferedWriter(fw);

      for (Appointment b : reportList) {

        bw.write(b.getAppointmentID() + ";" + b.getAppointmentTime() + ";" +
            b.getAppointmentDoctor() + ";"
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

  public void submitReport(String doctorID, String appointmentID, String patientSymptoms, String patientTreatments,
      double patientCharges) {
    // AppointmentID;AppointmentDate;AppointmentDoctor;DoctorFee;
    // DoctorReportSymptoms;ReportTreatments
    // Update Doctor Availiblity When Patient Report is submitted
    boolean status = false;
    addReport();
    for (Appointment a : reportList) {
      if (a.getAppointmentID().equals(appointmentID) &&
          a.getAppointmentDoctor().equals(doctorID)) {
        a.setReportSymptoms(patientSymptoms);
        a.setReportTreatments(patientTreatments);
        a.setPaymentBill(patientCharges);
        System.out.println("Report Updated Successfully! ");
        status = true;
        break;
      }
    }
    if (status) {
      updateReport();
      d1.freeAvailiblity(doctorID);
      JOptionPane.showMessageDialog(null, "Report Updated Successfully! ", "Report Updated Successfully! ",
          JOptionPane.INFORMATION_MESSAGE);
    } else {
      JOptionPane.showMessageDialog(null, "Please Try Again!", "Please Try Again!",
          JOptionPane.INFORMATION_MESSAGE);

    }

  }

  public void updateFees(String appointmentID, double amount) {
    // AppointmentID;AppointmentDate;AppointmentDoctor;DoctorFee;
    // DoctorReportSymptoms;ReportTreatments
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

  public ArrayList<Appointment> getPatientsReport(String doctorID, String date) {
    addReport();
    // System.out.println("Enter Date: ");
    // String date = scan.nextLine();
    ArrayList<Appointment> allAppointments = new ArrayList<Appointment>();
    for (Appointment a : reportList) {
      if (a.getAppointmentTime().equals(date) &&
          a.getAppointmentDoctor().equals(doctorID)) {

        // System.out.println("\n------------------------------------------");
        // System.out.println("Patient's Appointment ID: " + a.getAppointmentID() +
        //     "\nPatients Symptoms: "
        //     + a.getReportSymptoms() + "\nPatient's Treatments: " +
        //     a.getReportTreatments() + "\nAppointment Bill: " + a.getPaymentBill());
        // System.out.println("------------------------------------------");
        allAppointments.add(a);
        // String parts[] = { a.getAppointmentID(), a.getReportSymptoms(),
        // a.getReportTreatments(),
        // Double.toString(a.getPaymentBill()) };
        // return parts;
      }
    }
   
    return allAppointments;
  }

  public String[] viewPatientsReport(String appointmentID) {
    addReport();

    for (Appointment a : reportList) {
      if (a.getAppointmentID().equals(appointmentID)) {

        String parts[] = { a.getAppointmentID(), a.getReportSymptoms(), a.getReportTreatments(),
            Double.toString(a.getPaymentBill()) };
        return parts;
      }
    }
    return null;
  }

  public double payBill(String appointmentID) {
    double totalBill = 0;
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
