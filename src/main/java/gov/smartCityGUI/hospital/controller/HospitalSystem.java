package gov.smartCityGUI.hospital.controller;

import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.model.*;
import java.io.*;

public class HospitalSystem {

  int hospitalID;
  String hospitalName;
  String hospitalAddress;
  String hospitalPhone;
  String hospitalEmail;

  public static HospitalList h1;
  public static BookAppointment b1;
  public static CheckInHospital b2;
  public static DoctorList d1;

  public HospitalSystem(User currentUser) {

    h1 = new HospitalList();
    b1 = new BookAppointment();
    b2 = new CheckInHospital();
    d1 = new DoctorList();

  }

  public static Hospital getHospitalDetails(String id) {

    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartCityGUI/hospital/static/Hospital.txt"));
      String line;
      int hospitalID = 101;

      while ((line = br.readLine()) != null) {

        String split[] = line.split(";");
        if (hospitalID == Integer.parseInt(id)) {
          br.close();
          return new Hospital(hospitalID, split[0], split[1], split[2], split[3]);
        } else {

          hospitalID++;
        }
      }
      br.close();
      return new Hospital();

    } catch (IOException e) {
      e.printStackTrace();
    }
    return new Hospital();
  }

}