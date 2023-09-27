package gov.smartCityGUI.ems.util;

public class FormValidation{
  public static boolean checkCareerForm(String[] form){
    /*Only check whether the form is empty or not*/
    for(String data: form){
      if(data == null || data.equals("")) return false;
    }
    return true;
  }
}