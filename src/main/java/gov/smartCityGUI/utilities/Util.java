/**
Team Member(s) working on this class: Lin , Dylan
Project: Smart City
I recieved help from: N/A
*/

package gov.smartCityGUI.utilities;

import java.util.Scanner;

public class Util{
  public static final String BORADER = "----------------------------------\n";
  public static final String INVALID_OPTION_MESSAGE = "Invalid Option";
  public static final String BOLD_ON = "\u001B[1m";
  public static final String BOLD_OFF = "\u001B[0m";
  public static final String IT_ON = "\033[3m";
  public static final String IT_OFF = "\033[0m";
  public static final String DIV = "####################################";

  public static void printMenuError() {
    System.out.println(String.format("\n---------------  %s  ---------------", Util.INVALID_OPTION_MESSAGE));
    System.out.println("Please enter a valid menu choice.");
    System.out.println(Util.BORADER + "\n");
  }

  /**
   * Creates and runs through a standard text-based list menu.
   * The choices are provided via the IMenuProvider functional interface.
   *
   * @param menu an instance of functional interface IMenuProvider
   * @return the user's choice in the menu, or -1 if null passed in
   */
	/*
  public static int runMenu(IMenuProvider menu) {
    if(null == menu) {
      return -1;
    }

    int choice;
    while(true){
      System.out.print(Util.BORADER);
      menu.printOptions();
      System.out.print("Enter your choice, or 0 to return home: ");
      Scanner scanner = new Scanner(System.in);
      try {
        choice = scanner.nextInt();
        scanner.nextLine();
      } catch (Exception  e){
        Util.printMenuError();
        continue;
      }
      break;
    }
    return choice;
  }*/

  public static void header(String text){
    
    System.out.print("\n\n" + BOLD_ON + DIV + "\n\n\t" + text + "\n\n" + DIV + BOLD_OFF + "\n\n");
  }

  public static void printOptions(String options){
    String parts[] = options.split(",");
    for(int i = 0; i < parts.length; i++){
      System.out.print(i+1 + ". " + parts[i] + "\n\n");
    }
    System.out.print("0. Return to Menu\n\n" + Util.BOLD_ON + "Enter your choice:  " + Util.BOLD_OFF);
  }


    // ***********************************************************************//

  /**
   * This is a helper method to ensure correct user input.
   * 
   * @param scan scanner for user input
   */
  public static double doubleScan(Scanner scan) {
    while (true) {
      try {
        double amount = Double.parseDouble(scan.nextLine());
        return amount;
      } catch (Exception e) {
        System.out.print("\nInvalid Input. Try Again.\n\nEnter Choice:  ");
      } // End try-catch
    } // End while loop
  } // End doubleScan() method

  // ***********************************************************************//

  /**
   * This is a helper method to ensure correct user input.
   * 
   * @param scan scanner for user input
   */
  public static int intScan(Scanner scan) {
    while (true) {
      try {
        int amount = Integer.parseInt(scan.nextLine());
        return amount;
      } catch (Exception e) {
        System.out.print("\nInvalid Input. Try Again.\n\nEnter Choice:  ");
      } // End try-catch
    } // End while loop
  } // End intScan() method

}