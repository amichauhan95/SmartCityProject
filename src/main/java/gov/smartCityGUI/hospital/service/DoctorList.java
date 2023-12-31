/*
@author Ami Chauhan
Project: Smart City
@date 9/27/2023
I recieved help from: N/A
*/
package gov.smartCityGUI.hospital.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

import gov.smartCityGUI.hospital.model.*;

public class DoctorList {

  private List<Doctor> doctorList = new ArrayList<Doctor>();

	//***********************************************************************//
  //function returns available doctor
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
       JOptionPane.showMessageDialog(null,"\nWaiting Time is " + (count * 10) + " minutes","Alert", JOptionPane.INFORMATION_MESSAGE);
      System.out.println("\nWaiting Time is " + (count * 10) + " minutes");
    } else {
      JOptionPane.showMessageDialog(null,"\nPlease wait for doctor to be assigned. \nWaiting Time is " + (count * 10) + " minutes","Alert", JOptionPane.INFORMATION_MESSAGE);
      System.out.println("\nPlease wait for doctor to be assigned. ");
      System.out.println("\nWaiting Time is " + (count * 10) + " minutes");
    }
    updateDoctor();

    doctorList.clear();
    return availableDoctor + ";" + doctorFee;
  }

	//***********************************************************************//
  // doctor availblity is changed to true when functin is called
  public void freeAvailiblity(String userID) {
    addDoctor();
    for (Doctor d : doctorList) {
      if (d.getUserID().equals(userID)) {
        d.setAvailiblity(true);
      }
    }
    updateDoctor();

  }

	//***********************************************************************//
  // funcation updates the doctor text file
  public void updateDoctor() {

    int count = 0;
    try {

      FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/hospital/static/Doctor.txt", false);
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

	//***********************************************************************//
  // function returns boolean when doctor is added to the text file
  public boolean addDoctor(String doctorID, String doctorFirstName, String doctorLastName, String doctorPhone,
      String doctorEmail, double doctorFee) {
    boolean doctorAvailiblity = true;
    try {
      FileWriter fw = new FileWriter("src/main/java/gov/smartCityGUI/hospital/static/Doctor.txt", true);
      BufferedWriter bw = new BufferedWriter(fw);
      bw.newLine();
      bw.write(doctorID + ";" + doctorFirstName + ";" + doctorLastName + ";" + doctorPhone + ";" + doctorEmail + ";"
          + doctorFee + ";" + doctorAvailiblity);

      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  }

	//***********************************************************************//
  // function adds doctor to the list
  public void addDoctor() {
    doctorList.clear();
    try {

      BufferedReader br = new BufferedReader(
          new FileReader("src/main/java/gov/smartCityGUI/hospital/static/Doctor.txt"));
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

	//***********************************************************************//
  // function returns list of doctors
	public List<Doctor> getDoctorList() {

		List<Doctor> list = new ArrayList<Doctor>();
	    addDoctor();
	    for (Doctor b : doctorList) {
			list.add(b);
    	}
		return list;
  	}

	//***********************************************************************//
  // funcation returns boolean if doctor id is present in doctor list
  public boolean doctorPresent(String doctorID) {
    addDoctor();
    for (Doctor b : doctorList) {
      if (b.getUserID().equals(doctorID)) {
        return true;
      }
    }
    return false;
  }

}//