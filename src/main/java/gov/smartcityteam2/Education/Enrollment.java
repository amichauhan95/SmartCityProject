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

public class Enrollment implements listOfMajors {

  Scanner scanner = new Scanner(System.in);
  SchoolEnrollment newEnrollment; // newEnrollment is used for new enrollee
  String schoolChoice;
  String course;
  List<String> cirriculum = new ArrayList<>();
  ArrayList<String> newList = new ArrayList<String>();
  private List<Credentials> credentialsList;

  int courseCounter = 0;

  public Enrollment(List<Credentials> credentialsList) {
    this.credentialsList = credentialsList;
  }

  public void menu() {
    int userInput = 0;

    System.out.println("");

    while (true) {
      System.out.print(Util.BORADER);
      System.out.println("\n\tEnrollment System+\n");
      System.out.println("1. Please enter which school you wish to enroll:\n");
      System.out.println("2. View a list of majors:\n");
      System.out.println("3. Please select your major:\n");
      System.out.println("4. Add courses you wish to add:\n");
      System.out.println("5. View your cirriculum:\n");
      System.out.println("6. View the total number of credits:");
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
          scanner.nextLine();
          System.out.print("Please enter which school you wish to enroll: ");
          schoolChoice = scanner.nextLine();
          newEnrollment = new SchoolEnrollment(schoolChoice);
          System.out.println("You have enrolled in :" + newEnrollment.getName());
          try {
            readFile();
          } catch (IOException io) {
            io.printStackTrace();
          }

          // System.out.println(User.getInstance().getID());

          for (int i = 0; i < newList.size(); i++) {
            if ((newList.get(i).substring(0, 1).equals(User.getInstance().getID()))) {
              String temp = newList.get(i);
              newList.set(i, (temp + "," + newEnrollment.getName()));
            }
          }
          for (String each : newList) {
            System.out.println(each);
          }
          Path output = Paths.get("src/main/java/gov/smartcityteam2/Education/credentials.txt");
          try {
            Files.write(output, newList);
          } catch (Exception e) {
            e.printStackTrace();
          }

          break;
        }

        case 2: {

          for (String allSchools : allMajors) {
            System.out.println(allSchools);

          }
          break;
        }

        case 3: {
          scanner.nextLine();
          System.out.println("Please select your major: ");
          course = scanner.nextLine();

        }

        case 4: {
          scanner.nextLine();
          System.out.println("Please enter which course you wish to enroll: ");
          String courseChoice = scanner.nextLine();
          newEnrollment.addCourse(courseChoice);

          break;
        }

        case 5: {
          for (Course course : newEnrollment.getCourses()) {
            System.out.printf("%s - %d credits\n", course.getCourseName(), course.getCourseCredits());
            courseCounter += course.getCourseCredits();
          }
          break;
        }

        case 6: {
          System.out.printf("%s - %d credits\n", courseCounter);

        }

      }

    }
  }

  public void readFile() throws FileNotFoundException, IOException {
    newList.clear();
    BufferedReader bufReader = new BufferedReader(
        new FileReader("src/main/java/gov/smartcityteam2/Education/credentials.txt"));
    String line = bufReader.readLine();
    while (line != null) {
      newList.add(line);
      line = bufReader.readLine();
    }
    bufReader.close();
  }

}