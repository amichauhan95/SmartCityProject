package gov.smartCityGUI.hospital.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;

public class HospitalList {

  public String div = "------------------------------------------";
  public static ArrayList<Hospital> hospitals = new ArrayList<Hospital>();

  String hospitalName;
  String hospitalAddress;
  String hospitalPhone;
  String hospitalEmail;

  public static void addHospitals() {

    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartCityGUI/hospital/static/Hospital.txt"));
      String line;
      int hospitalID = 101;

      while ((line = br.readLine()) != null) {

        String split[] = line.split(";");

        hospitals.add(new Hospital(hospitalID, split[0], split[1], split[2], split[3]));
        hospitalID++;

      }

      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public boolean addHospitals(String hospitalName, String hospitalAddress, String hospitalPhone, String hospitalEmail) {
    try {
      FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/hospital/static/Hospital.txt", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.newLine();
      bw.write(hospitalName + ";" + hospitalAddress + ";" + hospitalPhone + ";" + hospitalEmail);
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;

  }

  public ArrayList<Hospital> getHospitalList() {

    addHospitals();
    return this.hospitals;
    
  }
}

///
/// Patients - Get Hospital List
/// Book Walk In Appointment at the location
/// Return wait time for the location
