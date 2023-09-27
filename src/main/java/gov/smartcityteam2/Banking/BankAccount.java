/*
@author Dylan Moran
Project: Smart City
@date 8/31/2023
I recieved help from: N/A
*/

package gov.smartcityteam2.Banking;

import java.io.*;
import java.util.Scanner;
import gov.smartcityteam2.Util;
import gov.smartcityteam2.Tax.*;
import gov.smartcityteam2.User;

public class BankAccount {

  // ***********************************************************************//

  /*
   * This method will open the banking system, load the users account and prompt
   * them for input about what actions they want to perform.
   * 
   * @param currentUser the user who will be accessing the banking system
   */
  public void open(User currentUser) {

    addAccount(currentUser); // This will check if the user has an existing account, if not it will create
                             // one.

    while (true) {
      Util.header("\t\tWelcome to Banking +");
      Util.printOptions("View my balance,Request a loan,Withdraw money,Deposit money");

      int option = 0;
      Scanner scan = new Scanner(System.in);

      try {
        option = Integer.parseInt(scan.nextLine());
      } catch (Exception e) {
        System.out.println(String.format("\n---------------  %s    ---------------", Util.INVALID_OPTION_MESSAGE));
        System.out.println("Please enter a number from 0 - 4, try again!...");
        System.out.println(Util.BORADER + "\n");
        continue;
      }

      // Input switch
      switch (option) {
        case 1: // 1: View my account
          displayAccount(currentUser);
          break;
        case 2: // 2: Request a loan
          requestLoan(currentUser);
          break;
        case 3: // 3: Withdraw
          System.out.print("\nAmount you want to withdraw:  ");
          double amount = Util.doubleScan(scan);
          withdraw(currentUser, amount);
          break;
        case 4: // 4: Deposit
          System.out.print("\nAmount you want to deposit:  ");
          amount = Util.doubleScan(scan);
          deposit(currentUser, amount);
          break;
        case 0:
          return;
        default:
      }
    } // End while loop
  } // End open() method

  // ***********************************************************************//

  /**
   * This method takes a user and an amount as input and will adjust the record of
   * the account in BankAccounts.txt to reflect the deposited amount in their
   * balance.
   *
   * @param currentUser the user whos account will be deposited into
   * @param amount      the amount to be deposited into the account balance
   */
  public void deposit(User currentUser, Double amount) {
    String[] parts = findAccount(currentUser.getID()); // Finds the users account
    deduct(parts[0], amount * -1); // Deposits the correct amount into that account
    System.out.println("\nSuccessfully withdrew\n\nNew Account Details:\n");
    displayAccount(currentUser); // Displays updated account
  }

  // ***********************************************************************//

  /**
   * This method takes a user and an amount as input and will adjust the record of
   * the account in BankAccounts.txt to reflect the withdrawn amount in their
   * balance.
   *
   * @param currentUser the user whos account will be withdrawn from
   * @param amount      the amount to be withdrawn from the account balance
   */
  public void withdraw(User currentUser, Double amount) {
    String[] parts = findAccount(currentUser.getID()); // Finds the users account
    deduct(parts[0], amount); // Withdraws the correct amount from that account
    System.out.println("\nSuccessfully deposited\n\nNew Account Details:\n");
    displayAccount(currentUser); // Displays updated account

  } // End of withdraw() method

  // ***********************************************************************//

  /*
   * This method takes a user as input and will do nothing if they already exist
   * or add them to the file if they dont exist.
   * 
   * @param currentUser the user who is accessing the banking system
   */
  public void addAccount(User currentUser) {

    try {
      if (!(new File("src/main/java/gov/smartcityteam2/Banking/data/BankAccounts.txt").exists())) {
        File createFile = new File("src/main/java/gov/smartcityteam2/Banking/data/BankAccounts.txt");
        createFile.createNewFile();
      }
      File writeFile = new File("src/main/java/gov/smartcityteam2/Banking/data/temp.txt");
      File readFile = new File("src/main/java/gov/smartcityteam2/Banking/data/BankAccounts.txt");
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
      File readFile = new File("src/main/java/gov/smartcityteam2/Banking/data/BankAccounts.txt");
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
      System.out.println("\n\tNo account exists under that user.\nPlease open an account with the Banking System");
      reader.close();
      return new String[1]; // Handled in other methods error catching.

    } catch (Exception e) {
      System.out.println("IO Exception" + e);
    } // End of Error catch

    // If account doesnt exist, print message:
    System.out.println("\n\tNo account exists under that user.\nPlease open an account with the Banking System");
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

    try {
      TaxSystem system = new TaxSystem();
      system.populateTaxRates();
      double tax = amount * (system.salesTax);
      TaxUser taxAccount = system.accessAccount(currentUser);
      taxAccount.setTotalTax(taxAccount.getTotalTax() + tax);
      amount = amount + tax;
      System.out.println("\n\nSales Tax amount applied: $" + String.format("%.2f", tax) + "\n\nTotal Charge:  $"
          + String.format("%,.2f", amount) + "\n\n");

      String parts[] = findAccount(currentUser.getID()); // Populates account data into parts[]
      double balance = Double.parseDouble(parts[3]); // Store the account balance
      if (balance < amount) { // If balance is insufficient, decline
        System.out.println(Util.BORADER + "\tTransaction Declined\n\tInsufficient Balance\n" + Util.BORADER);
        return false; // Return false if declined
      } else { // Else, approve
        deduct(parts[0], amount); // and deduct from balance
        System.out.println(Util.BORADER + "\tTransaction Approved!\n" + Util.BORADER);
        return true; // Return true if apporved
      }
    } catch (Exception e) {
      // Exception caught if findAccount() returns new String[1], aka empty account
      // data, meaning their account didn't exist.
    }
    return false;
  } // End of charge() method

  // ***********************************************************************//

  /*
   * This method will deduct an amount from the specified account nmumber.
   * 
   * @param accountNumber the account that is being deducted from
   * 
   * @param amount the amount that will be deducted
   */
  public static void deduct(String accountNumber, double amount) {

    try {
      File readFile = new File("src/main/java/gov/smartcityteam2/Banking/data/BankAccounts.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Banking/data/temp.txt");

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

    } catch (IOException e) {
      e.printStackTrace();
    }
  } // End deduct() method

  // ***********************************************************************//

  /*
   * This method will display the users account information.
   * 
   * @param currentUser the user whos account is being displayed
   */
  public static void displayAccount(User currentUser) {
    String parts[] = findAccount(currentUser.getID());
    System.out.println(Util.BORADER + Util.BORADER + "\nAccount Number:   " + parts[0] + "\n\nAccount Holder:   "
        + parts[1] + " " + parts[2] + "\n\nAccount Balance:  $" + parts[3] + "\n\n" + Util.BORADER + Util.BORADER);
  } // End displayAccount() method

  // ***********************************************************************//

  /*
   * This method will request a loan from the bank and depending on the tax
   * account balance, they will either be approved or denied.
   * 
   * @param currentUser the user object requesting a loan
   */
  public void requestLoan(User currentUser) {
    TaxAccount taxAccount = new TaxAccount(currentUser);
    TaxUser taxUser = taxAccount.login(); // Login to the users tax account

    if (taxUser.getBalance() > 0.00) { // If tax account has outstanding balance, decline loan
      System.out.println(Util.BORADER
          + "\n You have been declined for a loan\ndue to your outstanding balance\n\t    in the Tax System.\n\n"
          + Util.BORADER);
    } else { // Else, deposit $10,000 into their bank account
      deduct(currentUser.getID(), -10000.00);
      taxAccount.updateTaxInfo(currentUser.getID(), currentUser.getLast(), taxUser.getBalance() + 10000.00,
          taxUser.getTotalTax()); // Increment their tax account balance to reflect the $10,000 loan.
      System.out.println(Util.BORADER + "\nYou have been approved for a $10,000 loan!\n");
    } // End of if-else
  } // End of requestLoan() method

  // ***********************************************************************//

} // End of class