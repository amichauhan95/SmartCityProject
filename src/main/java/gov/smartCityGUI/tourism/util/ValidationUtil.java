/*@author Huiying Lin
Project: Smart City
@date 9/27/2023
I recieved help from: N/A
*/
package gov.smartCityGUI.tourism.util;
public class ValidationUtil{
  /*Check if input is valid double*/
  public static boolean isValidDouble(String input){
    if(input.equals("")) return true;
    try{
      double num = Double.parseDouble(input);
      return (num >= 0.0) && (num <= 5.0) ? true : false;
    } catch (NumberFormatException e){
        return false;
    }
  }

  /*Check if input is valid Integer*/
  public static boolean isInteger(String input) {
    if(input.equals("")) return true;
    try { 
        Integer.parseInt(input); 
    } catch(NumberFormatException e) { 
        return false; 
    } catch(NullPointerException e) {
        return false;
    }
    // only got here if we didn't return false
    return Integer.parseInt(input) >= 0 ? true : false;
  }

  /*Check if place form is valid*/
  public static boolean checkAddPlaceForm(String name, String placeDes, String numOfReviews, String rate, String img) {
    return isInteger(numOfReviews) && isValidDouble(rate) && !name.equals("") && !placeDes.equals("") && !img.equals("") ? true : false;
  }
}

