package gov.smartcityteam2.Healthcare.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gov.smartcityteam2.Healthcare.model.Hospital;

public class HospitalList {

  public String div = "------------------------------------------";
  private List<Hospital> hospitals = new ArrayList<Hospital>();

  String hospitalName;
  String hospitalAddress;
  String hospitalPhone;
  String hospitalEmail;

  public void addHospitals() {

    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartcityteam2/Healthcare/static/Hospital.txt"));
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
      FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/Healthcare/static/Hospital.txt", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.newLine();
      bw.write(hospitalName + ";" + hospitalAddress + ";" + hospitalPhone + ";" + hospitalEmail);
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;

  }

  public void getHospitalList() {

    addHospitals();
    for (Hospital b : hospitals) {
      System.out.println(div);
      System.out.println("\nHospital Name : " + b.getHospitalName()
          + "\nHospital Address : " + b.getHospitalAddress() + "\nHospital Phone : " + b.getHospitalPhone()
          + "\nHospital Email : " + b.getHospitalEmail());
      System.out.println(div);
    }
    hospitals.clear();
  }
}
