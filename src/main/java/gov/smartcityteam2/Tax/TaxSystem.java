
/*
@author Dylan Moran
Project: Smart City
@date 8/31/2023
I recieved help from: N/A
*/

package gov.smartcityteam2.Tax;

import java.io.*;
import java.util.Scanner;
import gov.smartcityteam2.User;
import gov.smartcityteam2.Util;
import gov.smartcityteam2.Banking.*;


public class TaxSystem{

  public double salesTax, incomeTax, corporationTax, propertyTax;

   //####################################################################//

  /*
  This method displays the menu for the Tax System
  @param user this is the user hwo is accessing the tax system
  */
  public void menu(User user){

    TaxAccount account = new TaxAccount(user);       // Opens the users tax account
    TaxUser taxUser = account.login();               // login() will populate all their tax info

    while(true){                                     // User input loop
      
      Util.header("Public Tax System\n\n\tLogged In: " + taxUser.getFirst() + " " + taxUser.getLast() + "\n\tUser ID # : " + taxUser.getID());
	  if(user.isAdmin()) Util.printOptions("View Current Tax Rates,Register a new LLC,View My Account,Make a Payment,Modify Tax Rates");
		else Util.printOptions("View Current Tax Rates,Register a new LLC,,View My Account,Make a Payment");
      

      int option = 0;
      Scanner scan = new Scanner(System.in);

      try{
        option = Integer.parseInt(scan.nextLine());         // try catch prevents user form entering invalid characters
      }
    catch(Exception e){
        System.out.println(String.format("\n---------------  %s    ---------------", Util.INVALID_OPTION_MESSAGE));
        System.out.println("Please enter a number from 0 - 4, try again!...");
        System.out.println(Util.BORADER + "\n");
        continue;
      }
      
      switch(option){
        case 1:                                      // 1: View Tax Rates
            displayAll();
          break;
        case 2:                                      // 2: Register an LLC
            fileLLC(scan);
          break;
        case 3:                                      // 3: Modify Tax Rates
            account.makePayment(user, taxUser, scan);
          break;
        case 4:                                      // 4: View Account
            account.viewAccount(taxUser);
          break;
        case 5:                                      // 5: Make a payment 
			if(user.isAdmin()) modifyTaxRates(scan);
          break;
        case 0:                                      // 0: Exit
          scan.close();
          return;
        default:
      }
    } // End while loop
  } // End of menu method

  //####################################################################//

    /*
    This method accesses a Tax Account and returns the TaxUser object with populated attributes
    @param user the user whos account is being accessed
    */
    public TaxUser accessAccount(User user){
      TaxAccount account = new TaxAccount(user);           // Create an account object
      return account.login();                              // return the logged in TaxUser object
    } // End accessAccount() method

  //####################################################################//

    /*
    This method will populate the tax rates for the class with the most up to date data from file.
    */
    public void populateTaxRates(){
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Tax/data/taxRates.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line = reader.readLine();
      String[] parts = line.split(",");
      salesTax = Double.parseDouble(parts[0])/100;               // Populate the current tax values from taxRates.txt
      this.incomeTax = Double.parseDouble(parts[1])/100;
      corporationTax = Double.parseDouble(parts[2])/100;
      propertyTax = Double.parseDouble(parts[3])/100;
      reader.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  } // End populateTaxRates()

  //####################################################################//

    /*
    This method allows for the modification of the tax rates stored in file
    @param scan a scanner object used for user input
    */
    public void modifyTaxRates(Scanner scan){
    
    populateTaxRates();          // Populate most current tax rates before displaying
    
    System.out.print("\n\t\tTax Rates:   \n\n\t1. Sales Tax:        " + String.format("%.2f", salesTax*100) + "\n\t2. Income Tax:       " + incomeTax*100 + "\n\t3. Corporation Tax:  " + corporationTax*100 + "\n\t4. Property Tax:     " + propertyTax*100 + "\n\n\tEnter the number of the tax rate you would like to modify:  ");

    int choice = Util.intScan(scan);
    System.out.print("\n\tEnter the new rate:  ");
    double newRate = Util.doubleScan(scan);           // Get user inputs 
    
    switch(choice){                                          // Switch routes the user to change the correct tax rate
      case 1: 
        changeRate(1, newRate);
        break;
      case 2: 
        changeRate(2, newRate);
        break;
      case 3: 
        changeRate(3, newRate);
        break;
      case 4: 
        changeRate(4, newRate);
        break;
    }

  } // End of modifyTaxRates()

  //####################################################################//

    /*
    This method helps the modifyTaxRates() method by modifying the taxRates.txt file directly
    @param index this indicates which tax rate is being changed
    @param newRate this is the rate that will replace the current one
    */
    public void changeRate(int index, double newRate){

    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Tax/data/taxRates.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Tax/data/temp.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));

      String line = reader.readLine();                 // No need to loop because the file is only one line.
      String[] parts = line.split(",");
      parts[index-1] = Double.toString(newRate);       // Changes the correct rate at specified index

      line = String.join(",", parts);
      writer.write(line);                              //Writes the new line

      reader.close();
      writer.close();

      if(readFile.delete()){                           // Overwrites the file
          writeFile.renameTo(readFile);
        }  
    }
    catch(IOException e){
      e.printStackTrace();
    }
  } // End changeRate() method

  //####################################################################//

  /*
  This method will register a business with the tax system.
  @param scan a scanner used for user input
  */
  public void fileLLC(Scanner scan){

    System.out.print("\nEnter the name of your business: ");       // User input
    String name = scan.nextLine();
        
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Tax/data/companies.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Tax/data/temp.txt");
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
        System.out.println("\n\n\tA business with that name already exists.\n\n");      // Else, print message
      }
      writer.close();
      if(readFile.delete()){                                     // Overwrite companies.txt with edited version
        writeFile.renameTo(readFile);
      }
    } catch(IOException e){
        e.printStackTrace();
    }
  } //  End of fileLLC()

  //####################################################################//

  /*
  This method will display the most up to date tax rates.
  */
  public void displayAll(){
    populateTaxRates();

    System.out.print("\n" + Util.BORADER + "\n\t\tTax Rates:   \n\n\t1. Sales Tax:        " + String.format("%.2f", salesTax*100) + "\n\t2. Income Tax:       " + incomeTax*100 + "\n\t3. Corporation Tax:  " + corporationTax*100 + "\n\t4. Property Tax:     " + propertyTax*100 + "\n\n");
  } // End of displayAll() method

  //####################################################################//
  
} // End of class