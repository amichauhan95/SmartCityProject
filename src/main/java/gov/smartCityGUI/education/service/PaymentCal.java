package gov.smartCityGUI.education.service;

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

public class PaymentCal {

  Scanner scanner = new Scanner(System.in);

  private String beginning = "", end = "";
  private int daysWorked = 0;
  private int yearsOfExperience = 0;
  private double basePay = 0;
  private double hourlyBonus = 0;
  private double totalPay = 0;
  private String workDate = "";

  public PaymentCal() {

  }

  public PaymentCal(String workDate) {
    this.workDate = workDate;
    yearsOfExperience = extractYear(workDate);
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

  public double calculatePayAfterBonuses(double basePay,int yearsOfExperience) {

    if (yearsOfExperience > 5 && yearsOfExperience < 10) {
      hourlyBonus = 5.00;

    }

    else if (yearsOfExperience > 10) {
      hourlyBonus = 7.5;
    }

    totalPay = hourlyBonus + basePay;

    return totalPay;

  }

}
