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

public class TeacherMenu extends SchoolSystem {
  Teacher newTeacher = new Teacher();
  PayCheckMenu employee;

  String name, lastName, fieldOfExpertise, gradeLevel, yearsOfExperience, schoolSelection, startWorkDate;
  String type;

  int daysWorkedEstimate;

  Scanner scanner = new Scanner(System.in);

  public void menu() {

    int userInput = 0;

    System.out.println("");

    while (true) {
      System.out.println("\n\t\tTeacher System+\n");
      System.out.println("\n1. Please enter your name, last name, and field of expertise.");
      System.out.println("\n2. Please enter the grade level:");
      System.out.println("\n3. Please enter years of experience.");
      System.out.println("\n4. View the list of Middle Schools.");
      System.out.println("\n5. View the list of High Schools.");
      System.out.println("\n6. View the list of Universities.");
      System.out.println("\n7. Select your school.");
      System.out.println("\n8. Start work date.");
      System.out.println("\n9. Calculate your paycheck");
      System.out.println("\n0. Exit");

      System.out.print("\nYour choice: ");

      try {
        userInput = scanner.nextInt();
      } catch (Exception e) {
        System.out.println("\nInvalid input. Please enter a valid option (0-9).");
        scanner.nextLine(); // Consume the invalid input
        continue;
      }

      switch (userInput) {

        case 0: {
          System.out.println("Exiting the menu.");
          return; // Exit the menu
        }

        case 1: {
          System.out.print("Name: ");
          name = scanner.next();
          System.out.print("Last Name: ");
          lastName = scanner.next();
          System.out.print("Field of Expertise: ");
          fieldOfExpertise = scanner.next();
          break;
        }

        case 2: {
          scanner.nextLine(); // Consume the newline character left by previous nextInt()
          System.out.print("Grade Level: ");
          type = scanner.nextLine();
          break;
        }

        case 3: {
          System.out.print("Years of Experience: ");
          yearsOfExperience = scanner.next();
          break;
        }

        case 4: {
          if ("middle".equalsIgnoreCase(type)) {
            String[] middleSchools = getMiddleSchools();
            for (String school : middleSchools) {
              System.out.println(school);
            }
          } else {
            System.out.println("Wrong selection!");
          }
          break;
        }

        case 5: {
          if ("high".equalsIgnoreCase(type)) {
            String[] highSchools = getHighSchools();
            for (String school : highSchools) {
              System.out.println(school);
            }
          } else {
            System.out.println("Wrong selection!");
          }
          break;
        }

        case 6: {
          if ("university".equalsIgnoreCase(type) || "college".equalsIgnoreCase(type)) {
            String[] universities = getUniversities();
            for (String school : universities) {
              System.out.println(school);
            }
          } else {
            System.out.println("Wrong selection!");
          }
          break;
        }
        case 7: {
          scanner.nextLine(); // Consume the newline character left by previous input
          System.out.print("Select your school: ");
          schoolSelection = scanner.nextLine();
          break;
        }
        case 8: {
          scanner.nextLine();
          System.out.print("Start Work Date:");
          startWorkDate = scanner.nextLine();

          {
            newTeacher.setFirstName(name);
            newTeacher.setLastName(lastName);
            newTeacher.setDepartment(fieldOfExpertise);
            newTeacher.setgradeLevel(type);
            newTeacher.setEmail();
            newTeacher.setselectedSchool(schoolSelection);
            newTeacher.setJoiningDate(startWorkDate);
            newTeacher.writeToFile();
          }

          break;
        }

        case 9: {

          employee = new PayCheckMenu(startWorkDate);

          break;
        }

        default: {
          System.out.println("Invalid input. Please enter a valid option (0-9).");
          break;
        }
      }
    }
  }
}
