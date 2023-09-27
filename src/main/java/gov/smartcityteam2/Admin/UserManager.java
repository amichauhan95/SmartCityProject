/*
@author Dylan Moran
Project: Smart City
@date 8/31/2023
I recieved help from: N/A
*/

package gov.smartcityteam2.Admin;

import java.io.*;
import java.util.Scanner;
import gov.smartcityteam2.Util;
import gov.smartcityteam2.User;
import gov.smartcityteam2.Banking.BankAccount;


public class UserManager{

  public UserManager(){}

  //***********************************************************************//

  /*
  This method is used to drive the UserManager class.
  @param currentUser the user who is accessing the user manager system
  */
  public void start(User currentUser){
    while(true){
      Util.header("\t\tAll Users");
      printAllUsers();
      boolean stop = prompt();
      if(stop){
        break;
      }
    }
  }

  //***********************************************************************//

  /*
  This method is used to print all existing users.
  */
  public void printAllUsers(){

    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Users.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
  
      String line;
      String admin;

      while((line = reader.readLine()) != null){
        String parts[] = line.split(",");
        if(parts[5].equals("9850")){
          admin = "Admin";
        }
        else if(parts[5].equals("1234"))
        {
          admin = "Hospital System Admin";
        }
        else if(parts[5].equals("1444"))
        {
          admin = "Hospital System Doctor";
        }
        else
        { admin = "General User";}
        System.out.println(parts[0] + ": " + parts[2] + ", " + parts[1] + " | " + parts[3] + " | " + parts[4] + " | " + admin + "\n" + Util.BORADER);       
      }

      reader.close();
    } catch(IOException e){
      e.printStackTrace();
    }
  }

  //***********************************************************************//

  /*
  This method is used to prompt the user for their input choice.
  @return a boolean which determines if the program will continue looping
  */
  public boolean prompt(){
    Scanner scan = new Scanner(System.in);
    System.out.print(Util.BOLD_ON + "Choose a user to manage, Enter 0 to quit:  " + Util.BOLD_OFF);
    int choice =Util.intScan(scan);
    if(choice == 0){
      return true;
    }
    User user = getUser(choice);
    if(user.getID() == null){
      return true;
    } else {
      return manage(user, scan);
    }
  }

  //***********************************************************************//

  /*
  This method is used to get the user object that corresponds to the given user ID #.
  @param userID the user ID of the user who will be returned
  @return the user being requested
  */
  public User getUser(int userID){
    String choice = Integer.toString(userID);
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Users.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;

      while((line = reader.readLine()) != null){
        String parts[] = line.split(",");
        if(parts[0].equals(choice)){
          return new User(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        }
      }
      System.out.println("User doesnt exist.");
      return null;
      
    } catch(IOException e){
      e.printStackTrace();
    }
    return null;
  }

  //***********************************************************************//

  /*
  This methd is used to manage the different elements of a user.
  @param user the user object of the user being managed
  @param scan a scanner used fo user input
  @return a boolean to determine if the input loop will continue
  */
  public boolean manage(User user, Scanner scan){
    
    while(true){
      user = getUser(Integer.parseInt(user.getID()));
      String isAdmin = new String();
      if(user.getRole().equals("9850")){
        isAdmin = "Yes";
      } else {
        isAdmin = "No";
      }
      System.out.print("\n" + Util.BOLD_ON + Util.DIV + "\nUser ID: " + user.getID() + "\nName: " + user.getFirst() + " " + user.getLast() + "\nEmail: " + user.getEmail() + "\nPhone: " + user.getPhone() + "\nAdmin: " + isAdmin + "\n" + Util.DIV + Util.BOLD_OFF + "\n\n");
      Util.printOptions("Change name,Change email,Change phone,Toggle Admin Privileges");

      int choice = Util.intScan(scan);
      try{
        File readFile = new File("src/main/java/gov/smartcityteam2/Users.txt");
        File writeFile = new File("src/main/java/gov/smartcityteam2/temp.txt");
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
  
        switch(choice){
          case 1: 
            changeName(user, readFile, writeFile, reader, writer, scan);
            break;
          case 2: 
            changeEmail(user, readFile, writeFile, reader, writer, scan); 
            break;
          case 3: 
            changePhone(user, readFile, writeFile, reader, writer, scan);
            break;
          case 4:
            {
              if(user.getRole().equals("1234")||user.getRole().equals("1444"))
              {
                System.out.println("\nUser Role cannot be toggled");
              }
              else
              {
               toggleAdmin(user, readFile, writeFile, reader, writer, scan);
              }
            }break;
          case 0:
            return false;
          default:
        }
      } catch(IOException e){
        e.printStackTrace();
      }
    } // End while loop
  } // End manage() method

  //***********************************************************************//

  /*
  This method is used to change the name of the user given.
  @param user the user object being edited
  @param readFile the Users.txt file
  @param writeFile temp.txt
  @param reader a BufferedReader object
  @param writer a BufferedWriter object
  @param scan a scanner used for user input
  */
  public void changeName(User user, File readFile, File writeFile, BufferedReader reader, BufferedWriter writer, Scanner scan){
   
      String line;
      boolean first = false, last = false;
       try{
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(parts[0].equals(user.getID())){
            while(!first){
              System.out.print("Enter your first name:  ");
              String firstName = scan.nextLine();
              if(firstName.length() >= 3){
                first = true;
                parts[1] = firstName;
              } else {
                System.out.println("\nFirst name must be at least 3 characters.\n\n");
              }
            }
            while(!last){
              System.out.print("Enter your last name:  ");
              String lastName = scan.nextLine();
              if(lastName.length() >= 3){
                last = true;
                parts[2] = lastName;
              } else {
                System.out.println("\nLast name must be at least 3 characters.\n\n");
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
  } // End changeName() method

  //***********************************************************************//

    /*
  This method is used to change the email of the user given.
  @param user the user object being edited
  @param readFile the Users.txt file
  @param writeFile temp.txt
  @param reader a BufferedReader object
  @param writer a BufferedWriter object
  @param scan a scanner used for user input
  */
  public void changeEmail(User user, File readFile, File writeFile, BufferedReader reader, BufferedWriter writer, Scanner scan){

      String line;
      boolean emailVal = false;
      try{
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(parts[0].equals(user.getID())){
            while(!emailVal){
              System.out.print("Enter your email:  ");
              String email = scan.nextLine();
              if(email.contains("@")){
                if(email.contains(".com") || email.contains(".org") || email.contains(".gov") || email.contains(".net")){
                emailVal = true;
                  parts[3] = email;
                } else {
                System.out.println("\nNot a valid email. Must contain '@' and proper domain extension.\n\n");
                } // End nested if-else
              } // End if
            } // End while loop
            line = String.join(",", parts);
          } // End if
          writer.write(line + System.lineSeparator());
        } // End while loop 
      reader.close();
      writer.close();
  
      if(readFile.delete()){                      // Overwrite the file with the edited version.
        writeFile.renameTo(readFile);
      } // End if
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch() method)
  } // End chaneEmail() method

  //***********************************************************************//

  /*
  This method is used to change the phone number of the user given.
  @param user the user object being edited
  @param readFile the Users.txt file
  @param writeFile temp.txt
  @param reader a BufferedReader object
  @param writer a BufferedWriter object
  @param scan a scanner used for user input
  */
  public void changePhone(User user, File readFile, File writeFile, BufferedReader reader, BufferedWriter writer, Scanner scan){
    
      String line;
      boolean phone = false;
      try{
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(parts[0].equals(user.getID())){
            while(!phone){
              System.out.print("Enter your phone number:  ");
              int phoneNum = Util.intScan(scan);
              String phoneNumber = Integer.toString(phoneNum);
              if(phoneNumber.length() >= 7 && phoneNumber.length() < 11){
                phone = true;
                parts[4] = phoneNumber;
              } else {
                System.out.println("\nNot a valid phone number. Must be 7-10 digits.\n\n");
              } // End if-else
            } // End while loop
            line = String.join(",", parts);
          } // End if
          writer.write(line + System.lineSeparator());
        } // End while loop 

      reader.close();
      writer.close();
  
      if(readFile.delete()){                      // Overwrite the file with the edited version.
        writeFile.renameTo(readFile);
      } // End if
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
    
  } // End changePhone() method

  //***********************************************************************//

    /*
  This method is used to change the admin status of the user given.
  @param user the user object being edited
  @param readFile the Users.txt file
  @param writeFile temp.txt
  @param reader a BufferedReader object
  @param writer a BufferedWriter object
  @param scan a scanner used for user input
  */
  public void toggleAdmin(User user, File readFile, File writeFile, BufferedReader reader, BufferedWriter writer, Scanner scan){

      String line;
   
      try{
        while((line = reader.readLine()) != null){
          String parts[] = line.split(",");
          if(parts[0].equals(user.getID())){
            if(parts[5].equals("9850")){
              parts[5] = "1";
            }
            else {
              parts[5] = "9850";
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
  }

  //***********************************************************************//
}