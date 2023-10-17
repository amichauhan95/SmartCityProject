/*
@author Ami Chauhan
Project: Smart City
@date 9/27/2023
I recieved help from: Huiying Lin
*/
package gov.smartCityGUI.hospital.controller;

import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.hospital.service.BookAppointment;
import gov.smartCityGUI.hospital.service.CheckInHospital;
import gov.smartCityGUI.hospital.service.DoctorList;
import gov.smartCityGUI.hospital.service.HospitalList;

/**
   * public HospitalSystem(User currentUser) intializes instances for HospitaList,
   * BookAppointment, CheckInHopital, DoctorList Classes
   * 
   * @param User currentUser is the User login instance
   **/
public class HospitalSystem {

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

}