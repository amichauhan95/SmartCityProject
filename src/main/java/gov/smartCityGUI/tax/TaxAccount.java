package gov.smartCityGUI.tax;

/*
@author Dylan Moran
Project: Smart City
@date 8/31/2023
I recieved help from: N/A
*/

import java.io.*;
import javax.swing.*;
import java.util.ArrayList;
import gov.smartCityGUI.banking.BankAccount;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.utilities.*;

public class TaxAccount{

  //Resources
  private String userID;
  public static double salesTax, incomeTax, corporationTax, propertyTax;

  //#####################################################################//

  /*
  * Constructor method that will add the current user to the TaxUsers.txt file if not already
  *
  * @param currentUser the user who will be added for use of the tax system
  */
  public TaxAccount(User currentUser){
    this.userID = currentUser.getID();
    addAccount(currentUser);
  } // End of constructor

  //#####################################################################//
  
  /*
  * This method will login the current user with the Tax System.
  *
  * @return a TaxUser object will be returned
  */
  public TaxUser login(){

    if(this.userID == null){
      return new TaxUser();
    }
    else {
      try{
        File readFile = new File("src/main/java/gov/smartCityGUI/data/Users.txt");
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
           // Else, print message
      } catch (IOException e){
        e.printStackTrace();
      } // End catch
      return new TaxUser();              // Returns null TaxUser, error to be handled elsewhere
    } // End if-else
  } // End login() method

  //#####################################################################//

  /*
  * This method will populate the current users tax information.
  *
  * @param currentUser a TaxUser object is passed through to be modified and ultimately returned
  * @return a TaxUser object will be returned
  */
  public static TaxUser populateTaxInfo(TaxUser currentUser){

    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/tax/static/TaxUsers.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/tax/static/temp.txt");
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
    This method will populate the tax rates for the class with the most up to date data from file.
    */
    public static void populateTaxRates(){
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/tax/static/taxRates.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line = reader.readLine();
      String[] parts = line.split(",");
      salesTax = Double.parseDouble(parts[0])/100;               // Populate the current tax values from taxRates.txt
      incomeTax = Double.parseDouble(parts[1])/100;
      corporationTax = Double.parseDouble(parts[2])/100;
      propertyTax = Double.parseDouble(parts[3])/100;
      reader.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  } // End populateTaxRates()

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
      File readFile = new File("src/main/java/gov/smartCityGUI/tax/static/TaxUsers.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/tax/static/temp.txt");
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
  * This method is used to add the current user to the Tax Users file if they do not already exist there
  *
  * @param currentUser the user who will be added to the file
  */
  public void addAccount(User currentUser){

    try{ 
      File readFile = new File("src/main/java/gov/smartCityGUI/tax/static/TaxUsers.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/tax/static/temp.txt");
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

  //####################################################################//

    /*
    * This method helps the modifyTaxRates() method by modifying the taxRates.txt file directly
    * 
    * @param salesTax the edited sales tax
    * @param incomeTax the edited income tax
    * @param corpTax the edited corporation tax
    * @param propTax the edited property tax
    * @return boolean true if successful, false if not
    */
  public static boolean changeRates(String salesTax, String incomeTax, String corpTax, String propTax){

    try{
      double sales = Double.parseDouble(salesTax);
      double income = Double.parseDouble(incomeTax);
      double corp = Double.parseDouble(corpTax);
      double prop = Double.parseDouble(propTax);

    } catch(NumberFormatException nfe){
        return false;
    }
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/tax/static/taxRates.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/tax/static/temp.txt");
      
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));

      String parts[] = {salesTax,incomeTax, corpTax, propTax};
      String line = String.join(",", parts);
      writer.write(line);                              //Writes the new line

      writer.close();

      if(readFile.delete()){                           // Overwrites the file
          writeFile.renameTo(readFile);
        }
      return true;
    }
    catch(IOException e){
      e.printStackTrace();
      return false;
    }

  } // End changeRate() method
  
  //####################################################################//
  
  /*
  * This method will be used to make a payment on a users tax account balance
  *
  * @param user this is the general User object, used for the charge() method
  * @param taxUser this is used to access the users tax account and to update their data
  * @param amount the amount that is being paid
  */
  public static void makePayment(User user, TaxUser taxUser, double amount){

    taxUser = populateTaxInfo(taxUser);

    if(BankAccount.deduct(user.getID(), amount)){
      updateTaxInfo(taxUser.getID(), taxUser.getLast(), taxUser.getBalance()-amount, taxUser.getTotalTax());
    }
  
  } // End makePayment() method

  //####################################################################//

  /*
  * This method is used to add a newly registered business to file
  *
  * @param name the name of the business
  * @reutrn boolean true if successful, false if not or if it already exists
  */
   public static boolean fileLLC(String name){
    if(name.equals("")) return true;
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/tax/static/companies.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/tax/static/temp.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      String line;
      boolean exists = false;
  
      while((line = reader.readLine()) != null){                 // Iterates through companies.txt
        writer.write(line + System.lineSeparator());
        if(line.toUpperCase().equals(name.toUpperCase())){       // Check if the business is already registered
          exists = true;
        }
      }
      if(!exists){                                               // If not, add it to the file
        writer.write(name.toUpperCase() + System.lineSeparator());
      } else {
        JOptionPane.showMessageDialog(null,
                                      "A business with that name already exists.",
                                      "Registration Error",
                                      JOptionPane.WARNING_MESSAGE);
        return false;
      }
      writer.close();
      reader.close();
      if(readFile.delete()){                                     // Overwrite companies.txt with edited version
        writeFile.renameTo(readFile);
      }
      return true;
    } catch(IOException e){
        e.printStackTrace();
      return false;
    }
  } //  End of fileLLC()

  //####################################################################//

  /*
  * This method is used to get all LLCs from file
  *
  * @return ArrayList<String> an array list of all registered businesses
  */
  public static ArrayList<String> getLLCs(){

    ArrayList<String> llcs = new ArrayList<String>();
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/tax/static/companies.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;
  
      while((line = reader.readLine()) != null){        
          llcs.add(line);
      }
      reader.close();
      return llcs;

    } catch(IOException e){
        e.printStackTrace();
        return llcs;
    }
  } // End getLLCs() method
  
} // End class