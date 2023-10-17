/*
@author Ami Chauhan
Project: Smart City
@date 9/27/2023
I recieved help from: N/A
*/
package gov.smartCityGUI.hospital.service;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import gov.smartCityGUI.hospital.model.*;

public class BookAppointment {
  public String div = "------------------------------------------";

  public List<Patient> patient = new ArrayList<Patient>();
  public HashMap<String, Integer> appointmentDates = new HashMap<String, Integer>();
  String firstName;
  String lastName;
  String birthDate;
  String phone;
  String address;
  String email;
  String appointmentTime;
  String appointmentID;
  String appointmentReport;
  String insuranceID;

	//***********************************************************************//
 // function to read list and add all apointments into the list
  public void addAppointment() {

    patient.clear();
    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartCityGUI/hospital/static/Patient.txt"));
      String line;
      while ((line = br.readLine()) != null) {

        String split[] = line.split(";");
        boolean status = Boolean.valueOf(split[10]);
        patient.add(new Patient(split[0], split[1], split[2], split[3], split[4], split[5], split[6], split[7],
            split[8], split[9], status));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

	//***********************************************************************//
  // function to add a new appointment into the file
  public boolean addAppointment(String userID, String firstName, String lastName, String email, String phone, String bday, String address, String insuranceID, String appointmentTime, String symptoms) {

    if (checkAvailibility(appointmentTime)) {
      appointmentID = "I" + appointmentTime + insuranceID;
      JOptionPane.showMessageDialog(null,
                                      "Thank you for booking an apointment. Your appointment ID is " + appointmentID,"Booking Successful", JOptionPane.INFORMATION_MESSAGE);
      boolean status = true;

      try {
        FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/hospital/static/Patient.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(userID + ";" + firstName + ";" + lastName + ";" + bday + ";" + phone + ";" + address + ";" + email
            + ";" + appointmentTime + ";" + insuranceID + ";" + appointmentID + ";" + status);
		addAppointment();
  
        bw.close();
        return true;
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
       JOptionPane.showMessageDialog(null,
                                      "Sorry! Appointments are full for that date.","Appointment Date Not Available", JOptionPane.WARNING_MESSAGE);
      return false;
    }
    return false;
  }

	//***********************************************************************//
  //method to print available appointments for console system
  public void viewAppointments(String userID) {
    addAppointment();
    for (Patient b : patient) {

      if ((b.getID().equals(userID)) && (b.getStatus())) {
        System.out.println(div);
        System.out.println("First Name: " + b.getFirst() + "\nLast Name: " + b.getLast() + "\nAppointment Time: "
            + b.getAppointmentTime());
        System.out.println(div);
      }
    }
    patient.clear();
  }

	//***********************************************************************//

	//New method needed for UI
	public List<Patient> getAppointments(){
		addAppointment();
		return patient;
	}

	//***********************************************************************//

	  // function checks if appointment is present in the patient list and returns a boolean
  public boolean getAppointment(String appointmentID, String patientFirstName, String patientLastName,
      String insuranceID) {
    addAppointment();
    String time = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    for (Patient b : patient) {

      if (b.getAppointmentID().equals(appointmentID) && b.getFirst().equals(patientFirstName)
          && b.getLast().equals(patientLastName)
          && b.getInsuranceID().equals(insuranceID) && b.getAppointmentTime().equals(time) && b.getStatus()) {
        return true;
      }
    }
    return false;
  }

	//***********************************************************************//

	//functions updates the appointment status to false in patient list and writes to the patient list  
  public void updateAppointmentStatus(String appointmentID) {

    addAppointment();
    for (Patient b : patient) {
      if (b.getAppointmentID().equals(appointmentID)) {
        b.setStatus(false);
      }
    }
    try {
      FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/hospital/static/Patient.txt", false);
      BufferedWriter bw = new BufferedWriter(fw);
      int count = 0;
      for (Patient b : patient) {

        bw.write(b.getID() + ";" + b.getFirst() + ";" + b.getLast() + ";" + b.getBirthDate() + ";" + b.getPhone() + ";"
            + b.getAddress() + ";" + b.getEmail() + ";" + b.getAppointmentTime() + ";" + b.getInsuranceID() + ";"
            + b.getAppointmentID() + ";" + (b.getStatus()));
        if (count < (patient.size() - 1)) {
          bw.newLine();
        }
        count++;
      }
      bw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

	//***********************************************************************//
  // function checks if appointment is available and returns a boolean
  public boolean checkAvailibility(String appointmentTime) {
    try {
      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartCityGUI/hospital/static/AppointmentDates.txt"));
      String line;
      while ((line = br.readLine()) != null) {
        String split[] = line.split(";");

        appointmentDates.put(split[0], Integer.parseInt(split[1]));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (!appointmentDates.containsKey(appointmentTime)) {

      appointmentDates.put(appointmentTime, 1);
    } else if (appointmentDates.containsKey(appointmentTime)) {
      int b = appointmentDates.get(appointmentTime);
      if (b <= 100) {
        appointmentDates.put(appointmentTime, appointmentDates.get(appointmentTime) + 1);
      } else {
        return false;
      }
    }
    try {
      FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/hospital/static/AppointmentDates.txt", false);

      BufferedWriter bw = new BufferedWriter(fw);

      for (Map.Entry<String, Integer> entry : appointmentDates.entrySet()) {
        String key = entry.getKey();
        Integer b = entry.getValue();

        bw.write(key + ";" + b);
        bw.newLine();
      }
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

	//***********************************************************************//

}