
/*
@author Dylan Moran
Project: Smart City
@date 8/31/2023
I recieved help from: N/A
*/

package gov.smartcityteam2.Tax;

import java.io.*;
import java.util.Scanner;
import gov.smartcityteam2.Banking.*;
import gov.smartcityteam2.User;
import gov.smartcityteam2.Util;

//#####################################################################//

public class TaxAccount{

  private String userID;

  /*
  Constructor method that will add the current user to the TaxUsers.txt file if not already
  @param currentUser the user who will be added for use of the tax system
  */
  public TaxAccount(User currentUser){
    this.userID = currentUser.getID();
    addAccount(currentUser);
  } // End of constructor

  //#####################################################################//
  
  /*
  *This method will login the current user with the Tax System.
  *@return a TaxUser object will be returned
  */
  public TaxUser login(){

    if(this.userID == null){
      System.out.println("No User ID.\nTaxAccount - login()\n");
      return new TaxUser();
    }
    else {
      try{
        File readFile = new File("src/main/java/gov/smartcityteam2/Users.txt");
        BufferedReader reader = new BufferedReader(new FileReader(readFile));
        String line;
  
        while((line = reader.readLine()) != null){         // Iterates through all general users
          String parts[] = line.split(",");
  
          if(parts[0].equals(this.userID)){                // If ID # matches

            TaxUser currentUser = new TaxUser(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]); // Create a TaxUser instance of the User
            reader.close();
            return populateTaxInfo(currentUser);      // Populate that users tax information that is on file
          }
        } // End while loop
        reader.close();
        System.out.println("No User exists.\nTaxAccount - login()");     // Else, print message
      } catch (IOException e){
        e.printStackTrace();
      } // End catch
      return new TaxUser();              // Returns null TaxUser, error to be handled elsewhere
    } // End if-else
  } // End login() method

  //#####################################################################//

  /*
  *This method will populate the current users tax information.
  *@param currentUser a TaxUser object is passed through to be modified and ultimately returned
  *@return a TaxUser object will be returned
  */
  public TaxUser populateTaxInfo(TaxUser currentUser){

    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Tax/data/TaxUsers.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Tax/data/temp.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      String line;

      while((line = reader.readLine()) != null){           // Iterates through all Tax Users
        String parts[] = line.split(",");
        writer.write(line + System.lineSeparator());

        if(parts[0].equals(currentUser.getID()) && parts[1].equals(currentUser.getLast())){    // If User matches
          double balance = Double.parseDouble(parts[2]);                                       // store their balance
          double totalTax = Double.parseDouble(parts[3]);                                     // store their totalTax
          currentUser.setBalance(balance);                                // Set the balance for the TaxUser object 
          currentUser.setTotalTax(totalTax);                              // Set the totalTax for the TaxUSer object
          reader.close();
          writer.close();
          return currentUser;                                            // Return the populated TaxUser
        }
      } // End while loop

      String parts[] = {currentUser.getID(), currentUser.getLast(), Double.toString(0.0), Double.toString(0.0)};    //If the user is not on file, set their balance and totalTax both to 0
      line = String.join(",", parts);
      writer.write(line + System.lineSeparator());          // Write them into the file

      currentUser.setBalance(0.0);                          // Set the TaxUSer object's balance to 0 as well
      currentUser.setTotalTax(0.0);                         // Set the TaxUser object's totalTax to 0 as well

      writer.close();
      reader.close();

      if(readFile.delete()){                                // Overwrite the file with edited version
          writeFile.renameTo(readFile);
        }  
      return currentUser;                                   // Return the populated TaxUser
    } // End of try
    catch (IOException e){
      e.printStackTrace();
    }
    return new TaxUser();                              // Return null TaxUser, error to be handled elsewhere
  } // End populateTaxInfo() method

  //#####################################################################//

  /*
  *This method will print the current users account information.
  *@param currentUser a TaxUser object is passed through to display information about
  */
  public void viewAccount(TaxUser currentUser){

    currentUser = populateTaxInfo(currentUser);       // Update the currentUsers data and then print it

    System.out.println(Util.BORADER + "\n\t" + currentUser.getFirst() + "\'s Account\n\n" + Util.BORADER + "\n\tBalance:  " + currentUser.getBalance() + "\n\n\tTotal Taxes:  " + currentUser.getTotalTax() + "\n\n" + Util.BORADER);
  } // End viewAccount() method

  //#####################################################################//

  /*
  * This method is used in TaxUser.java to update file when the objects attributes are modified.
  * 
  * @param userID the ID of the users account to be updated
  * @param lastName the last name of the users account to be updated
  * @param newBalance the updated balance
  * @param newTotalTax the updated total tax
  */
  public static void updateTaxInfo(String userID, String lastName, double newBalance, double newTotalTax){

    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Tax/data/TaxUsers.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Tax/data/temp.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));

      String line;
      while((line = reader.readLine()) != null){                   // Iterate through all TaxUsers
        String parts[] = line.split(",");
        
        if(parts[0].equals(userID) && parts[1].equals(lastName)){       // If user matches
          parts[2] = Double.toString(newBalance);                       // Set their new balance 
          parts[3] = Double.toString(newTotalTax);                      // Set their new totalTax
          line = String.join(",", parts);
          writer.write(line + System.lineSeparator());                  // Write new data to file
          continue;                                                    
        }
        writer.write(line + System.lineSeparator());                    // Else continue looping and copying
      } // End while loop

      reader.close();
      writer.close();

      if(readFile.delete()){                                      // Overwrite file with edited version
          writeFile.renameTo(readFile);
        }
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
  } // End updateTaxInfo() method

  //#####################################################################//

  /*
  This method is used to add the current user to the Tax Users file if they do not already exist there
  @param currentUser the user who will be added to the file
  */
  public void addAccount(User currentUser){

    try{ 
      File readFile = new File("src/main/java/gov/smartcityteam2/Tax/data/TaxUsers.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Tax/data/temp.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      String line;
      boolean exists = false;
            
      while((line = reader.readLine()) != null){                  // Iterate through all Tax Users  
        String parts[] = line.split(",");
        writer.write(line + System.lineSeparator());
        if(parts[0].equals(currentUser.getID()) && parts[1].equals(currentUser.getLast())){    // If user matches
          exists = true;                                                                       // Set flag to true
        } 
      } // End of while loop
      
      if(!exists){                                                                      // If the user doesn't exist 
        String parts[] = {currentUser.getID(), currentUser.getLast(), "0.00", "0.00"};  // Create a new line for them
        line = String.join(",", parts);
        writer.write(line + System.lineSeparator());                                    // Write that line into the file
      }
      reader.close();
      writer.close();

      if(readFile.delete()){                                          // Overwrite the file with the edited version
          writeFile.renameTo(readFile);
        }
    } catch(Exception e) {
          System.out.println("IO Exception" + e);
    } // End of Error catch
  } // End of addAccount() method

  //#####################################################################//

  /*
  This method will be used to make a payment on a users tax account balance
  @param user this is the general User object, used for the charge() method
  @param taxUser this is used to access the users tax account and to update their data
  @param scan this is used to get user input on how much the payment will be for
  */
  public void makePayment(User user, TaxUser taxUser, Scanner scan){

    taxUser = populateTaxInfo(taxUser);

    while(true){
      System.out.print(Util.BORADER + "\nCurrent Outstanding Balance:  $" + taxUser.getBalance() + "\nEnter the amount you would like to pay: ");
   
      double amount = Util.doubleScan(scan);
      if(BankAccount.charge(user, amount)){
        updateTaxInfo(taxUser.getID(), taxUser.getLast(), taxUser.getBalance()-amount, taxUser.getTotalTax());
        break;
      }
    } // End while loop
  } // End makePayment() method

  
} // End class