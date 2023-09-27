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

public class Education {

  Scanner scanner = new Scanner(System.in);
  ArrayList<String> listOfLines = new ArrayList<String>();
  SchoolSystem system = new SchoolSystem();
  StudentSubMenu submenu;
  TeacherMenu teacher = new TeacherMenu();
  DirectorMenu director = new DirectorMenu();
  List<Credentials> credentialsList = new ArrayList<>();
  ArrayList<String> schoolMatch = new ArrayList<String>();

  // no-arg constructor:
  public Education() {
  }

  public void menu() {
    int userInput = 0;

    System.out.println("");

    while (true) {
      System.out.print(Util.BORADER);
      System.out.println("\n\t\tEducation System+\n");
      System.out.println("1. Show all the Middle schools in Albany:\n");
      System.out.println("2. Show all the High schools in Albany:\n");
      System.out.println("3. Show all the Universities in Albany:\n");
      System.out.println("4. Enter your credentials to be enrolled in a School System:");

      System.out.println("\n5. Show total number of new enrollments:\n");
      System.out.println("6. Student's portal:\n");
      System.out.println("7. Teacher's portal:\n");
      System.out.println("8. Director's portal:\n");
      System.out.println("\n");
      System.out.print("Your choice, 0 to Home Page: ");

      try {
        userInput = scanner.nextInt();
      } catch (Exception e) {
        System.out.println(String.format("\n---------------  %s    ---------------", Util.INVALID_OPTION_MESSAGE));
        System.out.println("Please enter a digits from 1 - 4, try it again!...");
        System.out.println(Util.BORADER + "/n");
      }

      switch (userInput) {
        case 0: {
          return;
        }
        case 1: {
          for (String schools : system.getMiddleSchools()) {
            System.out.println(schools);
          }
          break;
        }
        case 2: {
          for (String schools : system.getHighSchools()) {
            System.out.println();
            System.out.println(schools);
          }
          break;
        }
        case 3: {
          for (String schools : system.getUniversities()) {
            System.out.println(schools);
          }
          break;
        }
        case 4: {
          System.out.println("Please enter your credentials \nname, lastname, age, and GPA:");
          String tempName = scanner.next();
          String tempLastName = scanner.next();
          String tempAge = scanner.next();
          String tempGPA = scanner.next();
          credentialsList.add(new Credentials(tempName, tempLastName, tempAge, tempGPA, system));
          System.out.println("\n\tUsers name: " + credentialsList.get(credentialsList.size() - 1).getName()
              + "\n\tUsers last name: " + credentialsList.get(credentialsList.size() - 1).getLastName()
              + "\n\tUsers Age: " + credentialsList.get(credentialsList.size() - 1).getAge()
              + "\n\tUsers GPA: " + credentialsList.get(credentialsList.size() - 1).getGPA()
              + "\n\tUsers Email : " + credentialsList.get(credentialsList.size() - 1).getEmail()
              + "\n\tUsers ID: " + credentialsList.get(credentialsList.size() - 1).getStudentID());
          break;
        }

        case 5: {
          try {
            readFile();
            if (listOfLines.isEmpty()) {
              System.out.println("No credentials found. The file is empty. Please enter your credentials first.");
            } else {
              int totalEnrollments = listOfLines.size();
              System.out.println("Total number of enrollments: " + totalEnrollments);
              System.out.println("Enrollments:");

              for (String enrollment : listOfLines) {
                String[] parts = enrollment.split(",");
                if (parts.length >= 2) {
                  String name = parts[1];
                  String lastName = parts[2];
                  System.out.println("Name: " + name + " Last Name: " + lastName);
                }
              }
            }
          } catch (IOException io) {
            io.printStackTrace();
          }
          break;
        }

        case 6: {
          try {
            readFile();
            boolean found = false;
            if (listOfLines.isEmpty()) {
              System.out.println("No credentials found. Please enter your credentials first.");
            } else {
              System.out.println("Enter user ID:");
              String user = scanner.next();
              for (String info : listOfLines) {
                if (info.substring(0, info.indexOf(",")).equals(user)) {
                  StringTokenizer token = new StringTokenizer(info, ",");
                  if (token.countTokens() >= 3) {
                    String id = token.nextToken();
                    String name = token.nextToken();
                    String lastName = token.nextToken();
                    String age = token.nextToken();
                    String gpa = token.nextToken();

                    Credentials existingUser = new Credentials();
                    existingUser.setID(id);
                    existingUser.setName(name);
                    existingUser.setLastName(lastName);
                    existingUser.setAge(age);
                    existingUser.setGPA(gpa);
                    credentialsList.add(existingUser);
                  }
                  submenu = new StudentSubMenu(credentialsList);
                  submenu.menu();
                  found = true;
                  break;
                }
              }
              if (found == false)
                System.out.println("User not found!");
            }
          } catch (IOException io) {
            io.printStackTrace();
          }
          break;
        }

        case 7: {
          teacher.menu();
          break;
        }

        case 8: {
          director.menu();
          break;
        }

      }
    }
  }

  public void readFile() throws FileNotFoundException, IOException {
    listOfLines.clear();
    BufferedReader bufReader = new BufferedReader(
        new FileReader("src/main/java/gov/smartcityteam2/Education/credentials.txt"));
    String line = bufReader.readLine();
    while (line != null) {
      listOfLines.add(line);
      line = bufReader.readLine();
    }
    bufReader.close();
  }
}
