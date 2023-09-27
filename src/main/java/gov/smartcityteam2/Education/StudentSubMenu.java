package gov.smartcityteam2.Education;

import gov.smartcityteam2.*;
import java.io.FileWriter;
import java.io.IOException;
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

public class StudentSubMenu {

  private boolean housing = false;

  private List<Credentials> credentialsList;

  private double studentGPA;
  private int desiredNumCredits;
  private double schoolTution = 0;

  ArrayList<String> schoolMatch = new ArrayList<String>();
  ArrayList<Double> tutionList = new ArrayList<Double>();

  SchoolSystem system = new SchoolSystem();
  Enrollment enroll = new Enrollment(credentialsList);

  Scanner scanner = new Scanner(System.in);

  String type = "";

  public StudentSubMenu(List<Credentials> userInput) {
    credentialsList = userInput;
  }

  public void menu() {

    int userInput = 0;

    System.out.println("");

    while (true) {

      System.out.print(Util.BORADER);
      System.out.println("\n\t\tEducation System+\n");
      System.out.println("1. School match based on your previous GPA:");
      System.out.println("\n2. Number of desired credits:");
      System.out.println("\n3. Apply for financial aid:");
      System.out.println("\n4. Apply to housing:");
      System.out.println("\n5. View the estimated school tution:");
      System.out.println("\n6. Select/Enroll into College:");
      System.out.println("\n");
      System.out.print("Your choice, 0 to Home Page: ");

      try {
        userInput = scanner.nextInt();
      } catch (Exception e) {
        System.out.println(String.format("\n---------------  %s    ---------------", Util.INVALID_OPTION_MESSAGE));
        System.out.println("Please enter a digits from 1 - 4, try it again!...");
        System.out.println(Util.BORADER + "/n");
      }

      // System.out.println("Please enter your name, lastname")

      switch (userInput) {

        case 0: {
          return;
        }
        case 1: {

          studentGPA = Double.parseDouble(credentialsList.get(credentialsList.size() - 1).getGPA());

          int usersAge = Integer.parseInt(credentialsList.get(credentialsList.size() - 1).getAge());

          if (usersAge >= 12 && usersAge <= 14) {

            for (String key : system.middleSchoolsGPA.keySet()) {
              double schoolGPA = system.middleSchoolsGPA.get(key);

              if (studentGPA >= schoolGPA) {
                schoolMatch.add(key);

              }
            }
            for (String schools : schoolMatch) {
              System.out.println(schools);
            }
            type = "middle";
          }

          if (usersAge > 14 && usersAge <= 17) {

            for (String key : system.highSchoolsGPA.keySet()) {

              double schoolGPA = system.highSchoolsGPA.get(key);

              if (studentGPA >= schoolGPA) {
                schoolMatch.add(key);

              }
            }
            for (String schools : schoolMatch) {
              System.out.println(schools);
            }
            type = "high";
          }

          if (usersAge > 17) {
            for (String key : system.collegesGPA.keySet()) {

              double schoolGPA = system.collegesGPA.get(key);

              if (studentGPA >= schoolGPA) {
                schoolMatch.add(key);

              }
            }
            for (String schools : schoolMatch) {
              System.out.println(schools);
            }
            type = "college";
          }

          break;

        }

        case 2: {

          desiredNumCredits = scanner.nextInt();

          if (type == "middle") {
            for (String school : schoolMatch) {
              double price = system.middleSchoolsPerCredit.get(school);
              tutionList.add(price * desiredNumCredits);
              System.out.println(school + ": $" + price * desiredNumCredits);
            }
          } else if (type == "high") {
            for (String school : schoolMatch) {
              double price = system.highSchoolsPerCredit.get(school);
              tutionList.add(price * desiredNumCredits);
              System.out.println(school + ": $" + price * desiredNumCredits);
            }
          } else {
            for (String school : schoolMatch) {
              double price = system.collegesPerCredit.get(school);
              tutionList.add(price * desiredNumCredits);
              System.out.println(school + ": $" + price * desiredNumCredits);
            }
          }

          break;
        }

        case 3: {
          if (studentGPA <= 3.0) {
            if (type == "middle") {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.middleSchoolsPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 10));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 10)));
              }
            } else if (type == "high") {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.highSchoolsPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 10));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 10)));
              }
            } else {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.collegesPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 10));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 10)));
              }
            }

          } else if (studentGPA > 3.0 && studentGPA <= 3.5) {
            if (type == "middle") {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.middleSchoolsPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 20));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 20)));

              }
            } else if (type == "high") {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.highSchoolsPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 20));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 20)));
              }
            } else if (studentGPA >= 3.0) {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.collegesPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 20));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 20)));
              }
            }
          } else if (studentGPA > 3.5) {
            if (type == "middle") {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.middleSchoolsPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 30));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 30)));
              }
            } else if (type == "high") {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.highSchoolsPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 30));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 30)));
              }
            } else {
              for (int i = 0; i < schoolMatch.size(); i++) {
                double price = system.collegesPerCredit.get(schoolMatch.get(i));
                tutionList.set(i, (price * desiredNumCredits) - ((price * desiredNumCredits) / 30));
                System.out.println(
                    schoolMatch.get(i) + ": $" + ((price * desiredNumCredits) - ((price * desiredNumCredits) / 30)));
              }
            }

            break;
          }
        }

        case 4: {
          for (int i = 0; i < tutionList.size(); i++) {
            tutionList.set(i, tutionList.get(i) + 15000);
          }
          System.out.println("Added $15,000 to the tution");
          break;
        }

        case 5: {
          System.out.println("School Tuition Information:");
          for (int i = 0; i < schoolMatch.size(); i++) {
            System.out.println(schoolMatch.get(i) + ": $" + tutionList.get(i));
          }
          break;
        }

        case 6: {
          enroll.menu();
          break;
        }

        default:
          System.out.println("Invalid input please enter 1-6:");

      }
    }
  }
}
