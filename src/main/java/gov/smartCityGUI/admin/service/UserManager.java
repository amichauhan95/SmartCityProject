package gov.smartCityGUI.admin.service;

/**
* @author Dylan Moran
* Project: Smart City
* @date 9/13/2023
* I recieved help from: N/A
*/

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import gov.smartCityGUI.admin.models.*;

public class UserManager{

  public UserManager(){}

  // ***********************************************************************//

  /**
    * This method is used to return the user with the corresponding ID #
    * @param userID the id of the user to be returned
    * @return the user with corresponding ID #
  **/
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
      }
      return null;
    } catch(IOException e){
      e.printStackTrace();
    }
    return null;
  }

  // ***********************************************************************//

  /**
    * This method is used to obtain a list of all users who have registered
    * @return an array list of all of the users
  **/
  public static ArrayList<User> fetchUsers(){

    ArrayList<User> allUsers = new ArrayList<User>();
    
     try{
      File readFile = new File("src/main/java/gov/smartCityGUI/data/Users.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;
      String admin;

      while((line = reader.readLine()) != null){
        String parts[] = line.split(",");
        allUsers.add(new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
      }    
       reader.close();
       return allUsers;
    } catch(IOException e){
      e.printStackTrace();
    }
    return allUsers;
  } // End manage() method

  // ***********************************************************************//

  /**
    * This method is used to make a user an admin or general user
    * @param the user who's privileges are being altered
  **/
  public static void toggleAdmin(User user){
   
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/data/Users.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/data/temp.txt");

      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      String line;
        
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(parts[0].equals(user.getID())){
            if(parts[5].equals("9850")){
              parts[5] = "1";
            } else {
				if(parts[5] != "1234" && parts[5] != "1444"){
              		parts[5] = "9850";
				} else {
					JOptionPane.showMessageDialog(null, "This user has other roles and cannot be changed.", "Notification", JOptionPane.INFORMATION_MESSAGE);
				}
            }
            line = String.join(",", parts);
          }
          
          writer.write(line + System.lineSeparator());
        } // End while loop 
      reader.close();
      writer.close();
  
      if(readFile.delete()){                      // Overwrite the file with the edited version.
        writeFile.renameTo(readFile);
      }
    } catch(IOException e){
      e.printStackTrace();
    }  
  } // End toggleAdmin() method

  // ***********************************************************************//

  /**
    * This method is used to delete a user from the system
    * @param user the user who is being deleted
  **/
  public static void deleteUser(User user){
    
     try{

      File readFile = new File("src/main/java/gov/smartCityGUI/data/Users.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/data/temp.txt");

      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      String line;
        
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(!parts[0].equals(user.getID())){
            writer.write(line + System.lineSeparator());
          }
        } // End while loop 
      reader.close();
      writer.close();
  
      if(readFile.delete()){                      // Overwrite the file with the edited version.
        writeFile.renameTo(readFile);
      }
    } catch(IOException e){
      e.printStackTrace();
    }  
  } // End deleteUser() method

  // ***********************************************************************//

  /**
    * This method is used to edit a users information
    * @param user the user who is being edited
    * @param firstName their updated first name
    * @param lastName their updated last name
    * @param email their updated email
    * @param phone their updated phone number
    * @return a boolean for whether or not the changes are valid and successful
  **/
  public static boolean editUser(User user, String firstName, String lastName, String email, String phone){
  
    if(!validate(firstName, lastName, email, phone)) return false;

    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/data/Users.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/data/temp.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
  
      String line;
      String index = "";
  
      while((line = reader.readLine()) != null){
        String parts[] = line.split(",");
        if(parts[0].equals(user.getID())){
          parts[1] = firstName;
          parts[2] = lastName;
          parts[3] = email;
          parts[4] = phone;
          line = String.join(",", parts);
          writer.write(line + System.lineSeparator());
        }
        else { writer.write(line + System.lineSeparator()); }
      }
      reader.close();
      writer.close();

      if(readFile.delete()){
          writeFile.renameTo(readFile);
        }
      JOptionPane.showMessageDialog(null, "Account has been updated", "Success!", JOptionPane.INFORMATION_MESSAGE);
      return true;
      
    } catch(IOException e){
      e.printStackTrace();
    }
    return false;
  } // End editUser() method

  // ***********************************************************************//

  /**
    * This method is a helper method used to validate edited information to make sure it meets all criteria
    * @param firstName their updated first name
    * @param lastName their updated last name
    * @param email their updated email
    * @param phone their updated phone number
    * @return a boolean for whether or not the changes are valid
  **/
  public static boolean validate(String firstName, String lastName, String email, String phone){

    if(firstName.length() < 3 || lastName.length() < 3) return false;
    if(!email.contains("@") || !(email.contains(".com") || email.contains(".org") || email.contains(".gov") || email.contains(".net"))){
      System.out.print("Invalid Email.");
      return false;
    }
    try{
      int phoneNum = Integer.parseInt(phone);
      if(phone.length() < 7 || phone.length() > 10) return false;
    } catch(NumberFormatException e){
      System.out.println("Phone number is not a number.");
    }
    return true;
  } // End validate() method

  // ***********************************************************************//
  
}