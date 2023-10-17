package gov.smartCityGUI.banking;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 8/31/2023
  * Description: This class is made to allow users to open bank accounts withdraw/deposit money, and request loans.
  * It stores all of its data in BankAccounts.txt and its charge() method can be accessed anywhere in the system to
  * allow users to use their bank account balance to make payments in other modules.
**/

import java.io.*;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.tax.*;

public class BankAccount {

  //***********************************************************************//

  /**
   * This method takes a user and an amount as input and will adjust the record of
   * the account in BankAccounts.txt to reflect the deposited amount in their
   * balance.
   *
   * @param currentUser the user whos account will be deposited into
   * @param amount      the amount to be deposited into the account balance
 **/
  public static void deposit(User currentUser, Double amount) {
    String[] parts = findAccount(currentUser.getID()); // Finds the users account
    deduct(parts[0], amount * -1); // Deposits the correct amount into that account
  }

  //***********************************************************************//

  /**
   * This method takes a user and an amount as input and will adjust the record of
   * the account in BankAccounts.txt to reflect the withdrawn amount in their
   * balance.
   *
   * @param currentUser the user whos account will be withdrawn from
   * @param amount      the amount to be withdrawn from the account balance
   */
  public static void withdraw(User currentUser, Double amount) {
    String[] parts = findAccount(currentUser.getID()); // Finds the users account
    deduct(parts[0], amount); // Withdraws the correct amount from that account
  } // End of withdraw() method

  // ***********************************************************************//

  /**
   * This method takes a user as input and will do nothing if they already exist
   * or add them to the file if they dont exist.
   * 
   * @param currentUser the user who is accessing the banking system
   */
  public static void addAccount(User currentUser) {

    try {
      if (!(new File("src/main/java/gov/smartCityGUI/banking/static/BankAccounts.txt").exists())) {
        File createFile = new File("src/main/java/gov/smartCityGUI/banking/static/BankAccounts.txt.txt");
        createFile.createNewFile();
      }
      File writeFile = new File("src/main/java/gov/smartCityGUI/banking/static/temp.txt");
      File readFile = new File("src/main/java/gov/smartCityGUI/banking/static/BankAccounts.txt");
      BufferedWriter write = new BufferedWriter(new FileWriter(writeFile));
      BufferedReader read = new BufferedReader(new FileReader(readFile));
      String line;
      boolean exists = false;

      while ((line = read.readLine()) != null) { // Iterate over the file checking all account ID #s
        String[] parts = line.split(",");
        if (parts[0].equals(currentUser.getID())) { // Check if the account exists already
          exists = true;
        }
        write.write(line + System.lineSeparator());
      } // End while loop

      if (!exists) { // If the account doesn't already exist, create a new account in the file
        String[] newAccount = new String[] { "", "", "", "" };
        newAccount[0] = currentUser.getID();
        newAccount[1] = currentUser.getFirst();
        newAccount[2] = currentUser.getLast();
        newAccount[3] = "0.00";
        line = String.join(",", newAccount);
        write.write(line + System.lineSeparator());
        System.out.println("\n\n\t\tYour new account number is:  " + newAccount[0] + "\n");
      }

      read.close();
      write.close();

      if (readFile.delete()) { // Overwrite the file with the edited version.
        writeFile.renameTo(readFile);
      }
    } catch (Exception e) {
      System.out.println("IO Exception: " + e);
    } // End of try-catch
  } // End of addAccount method

  // ***********************************************************************//

  /**
   * This method takes an account number as a parameter and will iterate through
   * the MyBank.txt file, find the account and return the details of the account
   * as an array of strings.
   * 
   * @param accountNumber the account number being accessed
   * @return an array of strings containing the bank account information wil be
   *         returned. ->
   *         {0-[accountNumber],1-[firstName],2-[lastName],3-[accountBalance]}
   */
  public static String[] findAccount(String accountNumber) {

    try {
      File readFile = new File("src/main/java/gov/smartCityGUI/banking/static/BankAccounts.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;
      String accountNum;

      while ((line = reader.readLine()) != null) { // Loop to iterate through all of the existing accounts
        String parts[] = line.split(",");
        accountNum = parts[0]; // Check all account numbers

        if (accountNum.equals(accountNumber)) { // If account # matches, return all account details as a string array
          reader.close();
          return parts;
        }
      } // End of while loop

      // If account doesnt exist, print message:
    
      reader.close();
      return new String[1]; // Handled in other methods error catching.

    } catch (Exception e) {
      System.out.println("IO Exception" + e);
    } // End of Error catch

    // If account doesnt exist, print message:
   
    return new String[1]; // Handled in other methods error catching.
  } // End of findAccount() method

  // ***********************************************************************//

  /**
   * This method will charge a users bank account dependig on if they have
   * sufficient funds or not.
   * 
   * @param currentUser the user whos account is being charged
   * @param amount      the amount being charged to the account
   * @return a boolean will return true if their account has sufficient funds and
   *         false if it doesn't
   */
  public static boolean charge(User currentUser, double amount) {

    TaxAccount account = new TaxAccount(currentUser);
    TaxUser taxAccount = account.login();
    account.populateTaxRates();
    double tax = amount*(account.salesTax);
    System.out.println(tax);
    taxAccount.setTotalTax(taxAccount.getTotalTax() + tax);
    amount = amount + tax;
    System.out.println("\n\nSales Tax amount applied: $" + tax + "\n\nTotal Charge:  $" + amount + "\n\n");

    try {
      String parts[] = findAccount(currentUser.getID()); // Populates account data into parts[]
      double balance = Double.parseDouble(parts[3]); // Store the account balance
      if (balance < amount) { // If balance is insufficient, decline
        //Decline
        return false; // Return false if declined
      } else { // Else, approve
        deduct(parts[0], amount); // and deduct from balance
        //Approve
        return true; // Return true if apporved
      }
    } catch (Exception e) {
      // Exception caught if findAccount() returns new String[1], aka empty account
      // data, meaning their account didn't exist.
    }
    return false;
  } // End of charge() method

  // ***********************************************************************//

  /**
   * This method will deduct an amount from the specified account nmumber.
   * 
   * @param accountNumber the account that is being deducted from
   * 
   * @param amount the amount that will be deducted
   */
  public static boolean deduct(String accountNumber, double amount) {

    boolean success = false;
    try {
      File readFile = new File("src/main/java/gov/smartCityGUI/banking/static/BankAccounts.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/banking/static/temp.txt");

      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));

      String line;

      while ((line = reader.readLine()) != null) { // Iterates through all existing accounts
        String[] parts = line.split(",");
        String currentAccount = parts[0]; // Stores current account #
        if (currentAccount.equals(accountNumber)) { // If current account matches

          double currentBalance = Double.parseDouble(parts[3]); // Store the account balance
          if (currentBalance >= amount) { // If there are sufficient funds,
            currentBalance -= amount; // deduct the correct amount
            parts[3] = String.format("%.2f", currentBalance); // Reformat as a String
            line = String.join(",", parts);
            success = true;
          }
          writer.write(line + System.lineSeparator()); // Write the new account details into the file
        } else {
          writer.write(line + System.lineSeparator()); // Else continue iterating and copying
        }
      }

      writer.close();

      if (readFile.delete()) { // Overwrite the BankAccounts.txt file with updated file.
        writeFile.renameTo(readFile);
      }
      return success;

    } catch (IOException e) {
      e.printStackTrace();
      return success;
    }
  } // End deduct() method

  // ***********************************************************************//

  /**
   * This method will request a loan from the bank and depending on the tax
   * account balance, they will either be approved or denied.
   * 
   * @param currentUser the user object requesting a loan
   */
  public static boolean requestLoan(User currentUser) {
    TaxAccount taxAccount = new TaxAccount(currentUser);
    TaxUser taxUser = taxAccount.login(); // Login to the users tax account

    if (taxUser.getBalance() > 0.00) { // If tax account has outstanding balance, decline loan
           return false;
    } else { // Else, deposit $10,000 into their bank account
      deduct(currentUser.getID(), -10000.00);
      taxAccount.updateTaxInfo(currentUser.getID(), currentUser.getLast(), taxUser.getBalance() + 10000.00,
          taxUser.getTotalTax()); // Increment their tax account balance to reflect the $10,000 loan.
           return true;
    } // End of if-else
  } // End of requestLoan() method

  // ***********************************************************************//

} // End of class