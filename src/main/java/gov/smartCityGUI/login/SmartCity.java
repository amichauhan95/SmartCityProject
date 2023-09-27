package gov.smartCityGUI.login;

/*
@author Dylan Moran
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/

import java.io.*;
import javax.swing.*;
import gov.smartCityGUI.admin.models.*;

public class SmartCity{

  //***********************************************************************//

  /*
  *Default constructor for SmartCity Class.
  */
  public SmartCity(){
  }

  //***********************************************************************//

  /*
  *This method is used to check is a user exists or not
  @param userID the ID of the user
  @param lastName the last name of the user
  @return a boolean, true if user exists false if not
  */
  public static boolean userExists(String userID, String lastName){
      int idNum;
    try{
      idNum = Integer.parseInt(userID);
    } catch(NumberFormatException e){
      System.out.println("User ID must be an integer.");
      return false;
    }
    if(idNum == 0) return false;

     try{
        File readFile = new File("src/main/java/gov/smartCityGUI/data/Users.txt");
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        String line;
  
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(parts[0].equals(userID) && parts[2].equals(lastName)){
			   User.initiliazeUser(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
     
            return true;
          }
        } // End while loop
      } catch(IOException e){
        e.printStackTrace();
      } // End catch
    return false;
  } // End userExists() method

  //***********************************************************************//

  /*
  This method will return the user with the corresponding ID #
  @param userID the ID of the user that will be returned
  @return User a user object will be returned
  */
  public static User getUser(String userID){

     try{
        File readFile = new File("src/main/java/gov/smartCityGUI/data/Users.txt");
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        String line;
  
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(parts[0].equals(userID)){
            return new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
          }
        } // End while loop
       System.out.println("Error finding user that was said to exist.");
      } catch(IOException e){
        e.printStackTrace();
      } // End catch
    return new User();
  } // End getUser() method

  //***********************************************************************//

  /*
  This method is used to register a new user
  @param firstName the user's first name
  @param lastName the user's last name
  @param email the user's email
  @param phone the user's phone number
  @param role the role of the user (admin or not)
  @return a boolean, true if registered successfully, false if not
  */
  public static boolean register(String firstName, String lastName, String email, String phone, String role){

    if(!validate(firstName, lastName, email, phone, role)) return false;

    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/data/Users.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/data/temp.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
  
      String line;
      String index = "";
  
      while((line = reader.readLine()) != null){
        String parts[] = line.split(",");
        index = parts[0];
        writer.write(line + System.lineSeparator());
      }
      
      int temp = Integer.parseInt(index) + 1;
      index = Integer.toString(temp);
	  if(role.equals("")) role = "1";
      
      String newInfo[] = {index, firstName, lastName, email, phone, role};
      line = String.join(",", newInfo);
      writer.write(line + System.lineSeparator());

      reader.close();
      writer.close();

      if(readFile.delete()){
          writeFile.renameTo(readFile);
        }

      JOptionPane.showMessageDialog(null, "Your ID # is " + index + "\nYou will need your ID to log in!", "Registered!", JOptionPane.INFORMATION_MESSAGE);
      
      return true;
      
    } catch(IOException e){
      e.printStackTrace();
    }

    return false;
  } // End register() method

  //***********************************************************************//

  /*
  This method is used to validate that the registration information meets the criteria
  @param firstName the user's first name
  @param lastName the user's last name
  @param email the user's email
  @param phone the user's phone number
  @param role the role of the user (admin or not)
  @return a boolean, true if all criteria is met, false if not
  */
  public static boolean validate(String firstName, String lastName, String email, String phone, String role){

    if(firstName.length() < 3 || lastName.length() < 3) return false;
    if(!email.contains("@") || !(email.contains(".com") || email.contains(".org") || email.contains(".gov") || email.contains(".net"))){
      System.out.print("Invalid Email.");
      return false;
    }
    try{
      if(!role.equals("9850")) role = "1";
      int phoneNum = Integer.parseInt(phone);
      int roleNum = Integer.parseInt(role);
      if(phone.length() < 7 || phone.length() > 10) return false;
    } catch(NumberFormatException e){
      System.out.println("Phone number is not a number.");
    }
    return true;
  } // End validate() method
  
  //***********************************************************************//

} // End SmartCity class