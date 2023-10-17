/**
  * Team Member(s) working on this class: Melih
  * Project: Smart City
  * @author: Melih Kartal
  * I received help from everyone on my team
**/
package gov.smartCityGUI.education.controller;


import gov.smartCityGUI.education.service.*;
import gov.smartCityGUI.admin.models.*;

public class EducationSystem {

 
   public static SchoolSystem s;
   public static PaymentCal p;
   public static SchoolAdmin s1;

  /**
    * public EducationSystem(User currentUser) initializes public variables to class instances
    * @param User currentUser acts as the user the instance that's passed in when the function is ran
  **/
  public EducationSystem(User currentUser) {

    s = new SchoolSystem();
    p = new PaymentCal();
    s1 = new SchoolAdmin();


  }

}