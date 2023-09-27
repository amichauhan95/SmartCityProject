package gov.smartcityteam2.Healthcare.service;

import gov.smartcityteam2.Healthcare.model.*;
import java.io.*;
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookAppointment {
  public String div = "------------------------------------------";
  Scanner scan = new Scanner(System.in);
  private List<Patient> patient = new ArrayList<Patient>();
  private HashMap<String, Integer> appointmentDates = new HashMap<String, Integer>();
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

  // functions reads file and adds into patient
  public void addAppointment() {

    patient.clear();
    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartcityteam2/Healthcare/static/Patient.txt"));
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

  // function takes input from the user and adds into the file and adds it into
  // patient.
  public void addAppointment(String userID) {
    System.out.println("Walkin Appointments only from [8:00 to 16:00] everyday");
    System.out.println("Address: Albany Med 1, 1220 Lawerence Ave ,Albany NY 12208\nPhone: (518)-223-324");
    System.out.println("Enter Patient First Name: ");
    firstName = scan.nextLine();
    System.out.println("Enter Patient Last Name: ");
    lastName = scan.nextLine();
    System.out.println("Enter Patient Birthdate: ");
    birthDate = scan.nextLine();
    System.out.println("Enter Patient Address: ");
    address = scan.nextLine();
    System.out.println("Enter Patient Phone: ");
    phone = scan.nextLine();
    System.out.println("Enter Patient Email: ");
    email = scan.nextLine();
    System.out.println("Enter Patient Insurance ID: ");
    insuranceID = scan.nextLine();
    System.out.println("Enter Symptoms: ");
    appointmentReport = scan.nextLine();
    System.out.println("Enter Walkin Appointment date in format [yyyy/mm/dd]: ");
    appointmentTime = scan.nextLine();
    if (checkAvailibility(appointmentTime)) {
      appointmentID = "I" + appointmentTime + insuranceID;
      System.out.println("Thank you for booking an appointment your appointment ID is " + appointmentID);
      boolean status = true;

      try {
        FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/Healthcare/static/Patient.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.newLine();
        bw.write(userID + ";" + firstName + ";" + lastName + ";" + birthDate + ";" + phone + ";" + address + ";" + email
            + ";" + appointmentTime + ";" + insuranceID + ";" + appointmentID + ";" + status);
        addAppointment();

        bw.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("Sorry! Appointments are full for the date");
    }

  }

  // functions displays patient list
  public void viewAppointments(String userID) {

    addAppointment();
    for (Patient b : patient) {

      if ((b.getID().equals(userID)) && (b.getStatus())) {
        System.out.println(div);
        System.out.println("First Name: " + b.getFirst() + "\nLast Name: " + b.getLast() + "\nAppointment Time: "
            + b.getAppointmentTime() + "\nAppointment ID: "
            + b.getAppointmentID());
        System.out.println(div);
      }

    }

  }

  // function checks if appointment is present in the patient list
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

  // functions updates the appointment in patient list and writes the patient list
  public void updateAppointmentStatus(String appointmentID) {

    addAppointment();
    for (Patient b : patient) {
      if (b.getAppointmentID().equals(appointmentID)) {
        b.setStatus(false);
      }
    }
    try {

      FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/Healthcare/static/Patient.txt", false);
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

  public boolean checkAvailibility(String appointmentTime) {
    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartcityteam2/Healthcare/static/AppointmentDates.txt"));
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
      FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/Healthcare/static/AppointmentDates.txt", false);

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

}