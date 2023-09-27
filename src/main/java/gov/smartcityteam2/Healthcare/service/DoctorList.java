package gov.smartcityteam2.Healthcare.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gov.smartcityteam2.Healthcare.model.Doctor;

public class DoctorList {

  private List<Doctor> doctorList = new ArrayList<Doctor>();

  public String getAvailableDoctor() {
    String availableDoctor = "Test";
    double doctorFee = 0;
    int count = 0;
    boolean assigned = false;
    addDoctor();
    for (Doctor d : doctorList) {

      if (d.getAvailiblity() == true) {
        count++;
        assigned = true;
        availableDoctor = d.getUserID();
        doctorFee = d.getConslutingFee();
        d.setAvailiblity(false);
        break;
      } else {
        count++;
        assigned = false;
        availableDoctor = " ";
      }
    }
    if (assigned) {
      System.out.println("\nWaiting Time is " + (count * 10) + " minutes");
    } else {
      System.out.println("\nPlease wait for doctor to be assigned. ");
      System.out.println("\nWaiting Time is " + (count * 10) + " minutes");
    }
    updateDoctor();

    doctorList.clear();
    return availableDoctor + ";" + doctorFee;
  }

  public void freeAvailiblity(String userID) {
    addDoctor();
    for (Doctor d : doctorList) {
      if (d.getUserID().equals(userID)) {
        d.setAvailiblity(true);
      }
    }
    updateDoctor();

  }

  public void updateDoctor() {

    int count = 0;
    try {

      FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/Healthcare/static/Doctor.txt", false);
      BufferedWriter bw = new BufferedWriter(fw);

      for (Doctor b : doctorList) {

        bw.write(b.getUserID() + ";" + b.getFirstName() + ";" + b.getLastName() + ";" + b.getPhone() + ";"
            + b.getEmail() + ";" + b.getConslutingFee() + ";" + b.getAvailiblity());
        if (count < (doctorList.size() - 1)) {
          bw.newLine();

        }

        count++;

      }

      bw.close();

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public String addDoctor(String doctorID, String doctorFirstName, String doctorLastName, String doctorPhone,
      String doctorEmail, double doctorFee) {
    boolean doctorAvailiblity = true;
    try {
      FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/Healthcare/static/Doctor.txt", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.newLine();
      bw.write(doctorID + ";" + doctorFirstName + ";" + doctorLastName + ";" + doctorPhone + ";" + doctorEmail + ";"
          + doctorFee + ";" + doctorAvailiblity);

      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "Doctor Added Successfully!";
  }

  public void addDoctor() {
    doctorList.clear();
    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartcityteam2/Healthcare/static/Doctor.txt"));
      String line;
      while ((line = br.readLine()) != null) {

        String split[] = line.split(";");
        boolean status = Boolean.valueOf(split[6]);
        double fee = Double.parseDouble(split[5]);
        doctorList.add(new Doctor(split[0], split[1], split[2], split[3], split[4], fee, status));

      }
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public void getDoctorList() {
    addDoctor();
    for (Doctor b : doctorList) {
      System.out.println("----------------------------");
      System.out.println("\nDoctor ID : " + b.getUserID() + "\nDoctor Name : " + b.getFirstName()
          + " " + b.getLastName() + "\nDoctor Phone : " + b.getPhone()
          + "\nDoctor Email : " + b.getEmail() + "\nAvailablity Status: " + b.getAvailiblity());
      System.out.println("----------------------------");

    }

  }

  public boolean doctorPresent(String doctorID) {
    addDoctor();
    for (Doctor b : doctorList) {
      if (b.getUserID().equals(doctorID)) {
        return true;
      }
    }
    return false;
  }

}