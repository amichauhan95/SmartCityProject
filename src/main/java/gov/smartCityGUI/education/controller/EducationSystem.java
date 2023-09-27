package gov.smartCityGUI.education.controller;


import gov.smartCityGUI.education.service.*;
import gov.smartCityGUI.admin.models.*;

public class EducationSystem {

 
   public static SchoolSystem s;
   public static PaymentCal p;
   public static SchoolAdmin s1;


  public EducationSystem(User currentUser) {

    s = new SchoolSystem();
    p = new PaymentCal();
    s1 = new SchoolAdmin();


  }

}