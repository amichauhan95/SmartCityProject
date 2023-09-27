/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.util;
import gov.smartcityteam2.Util;
public class ValidationUtil {

  /* Display a message based on whatever the data */
  public static void messagePopUp(boolean isSucceed, String successfulMesaage, String unsuccessfulMessage){
    System.out.println(Util.BORADER);
    
    if(isSucceed) System.out.println(String.format("%s", successfulMesaage));
    else System.out.println(String.format("%s", unsuccessfulMessage)); 
    
    System.out.println(Util.BORADER);
  }
}