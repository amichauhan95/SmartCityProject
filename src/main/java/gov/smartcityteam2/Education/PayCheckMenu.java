package gov.smartcityteam2.Education;

import gov.smartcityteam2.*;
import java.io.FileWriter;
import java.io.IOException;
import java.beans.Encoder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.*;
import java.text.SimpleDateFormat;  
import java.text.DateFormat;
import java.text.ParseException;



public class PayCheckMenu {

  Scanner scanner = new Scanner(System.in);

  private String beginning = "", end = "";
  private int daysWorked = 0;
  private int yearsOfExperience = 0;
  private double basePay = 0;
  private double hourlyBonus = 0;
  private double totalPay = 0;
  private String workDate = "";


  public PayCheckMenu(String workDate) {
    this.workDate = workDate;
    yearsOfExperience = extractYear(workDate);
    menu();  
  }  

  public void menu() {

    int userInput = 0;

    System.out.println("");

    while (true) {

      System.out.print(Util.BORADER);
      System.out.println("\nPay System+\n");
      System.out.println("\n1. Enter Work Day (Beginning format: \"MM-dd-yyyy\"):");
      System.out.println("\n2. Enter Work Day (End format: \"MM-dd-yyyy\"):");
      System.out.println("\n3.Days Worked:");
      System.out.println("\n4.Enter your base pay:");
      System.out.println("\n5.Hourly pay after bonuses:");
      System.out.println("\n6.Calculate Paycheck:");
      System.out.println("\n7.Calculate Annual Pay:");
      System.out.print("\nYour choice, 0 to Home Page: ");

      try {
        userInput = scanner.nextInt();
      } catch (Exception e) {
        System.out.println(String.format("\n---------------  %s    ---------------", Util.INVALID_OPTION_MESSAGE));
        System.out.println("Please enter a digits from 1 - 4, try it again!...");
        System.out.println(Util.BORADER + "/n");
      }

      switch (userInput) {

        case 1: {
          scanner.nextLine();
          beginning = scanner.nextLine();
          break;
        }
          case 2: {
          scanner.nextLine();
          end = scanner.nextLine();
          break;
          
        }
          case 3: {

            setDaysWorked(calculateWorkingDaysBetween(beginning,end));
            System.out.println("You worked: " + daysWorked + " days.");


            
          break;
        }
          case 4: {
            scanner.nextLine();
            basePay = scanner.nextDouble();
        }
      case 5: {
    // Calculate hourly pay after bonuses
    double hourlyPayAfterBonuses = calculatePayAfterBonuses();
    System.out.println("Hourly pay after bonuses: $" + hourlyPayAfterBonuses);
    break;
}

    case 6: {
    // Calculate paycheck
    double hourlyPayAfterBonuses = calculatePayAfterBonuses();
    double paycheck = hourlyPayAfterBonuses * daysWorked * 7.5;
    System.out.println("Total paycheck: $" + paycheck);
    break;
}

      case 7: {
    // Calculate annual pay
    double hourlyPayAfterBonuses = calculatePayAfterBonuses();
    double annualPay = hourlyPayAfterBonuses * daysWorked * 365; // Assuming 365 workdays in a year
    System.out.println("Annual pay: $" + annualPay);
    break;
}


      
      }
    }
  }

  public final int calculateWorkingDaysBetween(String startDateStr, String endDateStr) {
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
    try {
      Date startDate = sdf.parse(startDateStr);
      Date endDate = sdf.parse(endDateStr);

      Calendar calendar = Calendar.getInstance();
      calendar.setTime(startDate);

      int workingDays = 0;

      while (!calendar.getTime().after(endDate)) {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek != Calendar.SATURDAY && dayOfWeek != Calendar.SUNDAY) {
          workingDays++;
        }
        calendar.add(Calendar.DAY_OF_MONTH, 1);
      }

      return workingDays;
    } catch (ParseException e) {
      e.printStackTrace();
      return -1; // Return a negative value to indicate an error
    }
  }



   public int extractYear(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        try {
            Date date = sdf.parse(dateStr);
            SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
            return Integer.parseInt(yearFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return -1; // Return a negative value to indicate an error
        }
    }

  

  public int getDaysWorked() {
        return daysWorked;
    }

    // Setter method for daysWorked
    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
    }

  public double calculatePayAfterBonuses(){

    if(this.yearsOfExperience >5 && this.yearsOfExperience < 10 ){
      hourlyBonus = 5.00;
      
    }

    else if(this.yearsOfExperience > 10) {
      hourlyBonus = 7.5;
    }


    
    totalPay = hourlyBonus + basePay;

    return totalPay;
    
  }

}


  
