/*
@author Dylan Moran
Project: Smart City
@date 8/31/2023
I recieved help from: N/A
*/

package gov.smartcityteam2;

import gov.smartcityteam2.Util;
import java.util.Scanner;
import java.io.*;
import gov.smartcityteam2.Banking.BankAccount;

public class SmartCity{

  //***********************************************************************//

  /*
  *Default constructor for SmartCity Class.
  */
  public SmartCity(){
  }

  //***********************************************************************//

  /*
  *This method is used to prompt the user to login or register.
  *@return User a user object will be returned
  */
  public User login(){

    Scanner scan = new Scanner(System.in);
    boolean notNum = false;
    int choice = -1;

    // Feedback loop
    while(true){       
      notNum = false;
      System.out.print(Util.BOLD_ON + "\n1. Login\n2. Register\n\n0. Exit\n\nChoose an option: " + Util.BOLD_OFF);
      // Catch Number Format Exception
      try {
        choice = Integer.parseInt(scan.nextLine());
    } catch (NumberFormatException nfe) {
        System.out.println(Util.DIV + "\t\tMust be a number!");
        notNum = true;
    }
      
        switch(choice){
          case 1: 
            return returningUser(scan);
         
          case 2: 
            return newUser(scan);
            
          case 0: 
            System.out.println("Exiting...");
            return new User();
           
          default:
            if(!notNum){
              System.out.println(Util.BORADER + "   Not a valid entry. Try Again\n");
            }
            break;
        } // End switch
    } // End while loop
  } // End login() method

  //***********************************************************************//

  /*
  *This method is used to login a returning user and will return the object of the user that logs in.
  *@param scan passes a scanner through to allow for system input
  *@return User a user object will be returned
  */
  public User returningUser(Scanner scan){

   boolean loop = true;
    
    while(loop){
      System.out.print("\n" + Util.BOLD_ON + Util.DIV + "\n\nENTER 0 ANYTIME TO RETURN TO MENU\n\n" + Util.DIV + "\n\n\tEnter your user ID: " + Util.BOLD_OFF);
      String userID = scan.nextLine();
      
      try{
        if(Integer.parseInt(userID) == 0){
          SmartCity city = new SmartCity();
          return city.login();
        }
      } catch(NumberFormatException nfe){}
      
      System.out.print(Util.BOLD_ON + "\n\tEnter your last name: " + Util.BOLD_OFF);
      String lastName = scan.nextLine();
      try{
        if(Integer.parseInt(lastName) == 0){
          SmartCity city = new SmartCity();
          return city.login();
        }
      } catch(NumberFormatException nfe){}
    
      try{
        File readFile = new File("src/main/java/gov/smartcityteam2/Users.txt");
        BufferedReader reader = new BufferedReader( new FileReader(readFile));
        String line;
  
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(parts[0].equals(userID) && parts[2].equals(lastName)){
            loop = false;
            return new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
          }
        } // End while loop
        System.out.println("\nThe information you entered doesnt match our records. Try Again\n");
        
      } catch(IOException e){
        e.printStackTrace();
      } // End catch
    } // End while loop
    return new User();
  } // End returningUser() method

  //***********************************************************************//

  /*
  *This method is used to register a new user.
  *@param scan passes a scanner through to allow for system input
  *@return User a user object will be returned
  */
  public User newUser(Scanner scan){

    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Users.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/temp.txt");
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
      
      String newInfo[] = gatherInfo(index, scan);
      line = String.join(",", newInfo);
      writer.write(line + System.lineSeparator());
      User newUser = new User(newInfo[0], newInfo[1], newInfo[2], newInfo[3], newInfo[4], newInfo[5]);

      System.out.println("Your User ID # is: " + newInfo[0] + "\n\tDO NOT FORGET\n");

      reader.close();
      writer.close();

      if(readFile.delete()){
          writeFile.renameTo(readFile);
        }
      
      return newUser;
      
    } catch(IOException e){
      e.printStackTrace();
    }
    return new User();
  } // End newUser() method

  //***********************************************************************//

  /*
  *This method is used to gather information used for registering a new user.
  *@param userID used to identify the user by their new ID number
  *@param scan passes a scanner through to allow for system input
  *@return String[] returns a string array of the new users information
  */
  public String[] gatherInfo(String userID, Scanner scan){
    
    boolean first = false;
    boolean last = false;
    boolean emailVal = false;
    boolean phone = false;
    boolean code = false;
    String firstName = new String();
    String lastName = new String();
    String email = new String();
    String phoneNumber = new String();
    String role = new String();

    while(!first){
      System.out.print("Enter your first name:  ");
      firstName = scan.nextLine();
      if(firstName.length() >= 3){
        first = true;
      } else {
        System.out.println("\nFirst name must be at least 3 characters.\n\n");
      }
    }
    while(!last){
      System.out.print("Enter your last name:  ");
      lastName = scan.nextLine();
      if(lastName.length() >= 3){
        last = true;
      } else {
        System.out.println("\nLast name must be at least 3 characters.\n\n");
      }
    }
    while(!emailVal){
      System.out.print("Enter your email:  ");
      email = scan.nextLine();
      if(email.contains("@")){
        if(email.contains(".com") || email.contains(".org") || email.contains(".gov") || email.contains(".net")){
        emailVal = true;
        } else {
        System.out.println("\nNot a valid email. Must contain '@' and proper domain extension.\n\n");
        }
      }
    }
    while(!phone){
      System.out.print("Enter your phone number:  ");
      int phoneNum = Util.intScan(scan);
      phoneNumber = Integer.toString(phoneNum);
      if(phoneNumber.length() >= 7 && phoneNumber.length() < 11){
        phone = true;
      } else {
        System.out.println("\nNot a valid phone number. Must be 7-10 digits.\n\n");
      }
    }
    while(!code){
      System.out.print("Enter admin code (*Click Enter for regular users*):  ");
      int codeNum = Util.intScan(scan);
      role = Integer.toString(codeNum);
      if(role.equals("")){
        System.out.println("Empty");
        role = "1";
        code = true;
      }
      else if(role.length() == 4){
        code = true;
      }
      else {
        System.out.println("\nNot a valid code. Must be 4 digits or empty for regular users.\n\n");
      }
    }
    String parts[] = {userID,firstName, lastName, email, phoneNumber, role};
    return parts;
  } // End gatherInfo() method

  //***********************************************************************//
  
} // End SmartCity class