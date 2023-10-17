package gov.smartCityGUI.ems.util;

/**
  * Team Member(s) working on this class: Keith Austin
  * Project: Smart City
  * @author: Keith Austin
  * I received help from everyone on my team
**/

public class FormValidation{
  public static boolean checkCareerForm(String[] form){
    /*Only check whether the form is empty or not*/
    for(String data: form){
      // checks if the data is nulls and returns true if so
      if(data == null || data.equals("")) return false;
    }
    return true;
  }
}