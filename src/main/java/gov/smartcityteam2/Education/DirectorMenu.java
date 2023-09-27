package gov.smartcityteam2.Education;

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

import java.util.Scanner;

public class DirectorMenu extends SchoolAdmin {

  private ArrayList<String> newList = new ArrayList<String>();

  private Scanner scanner = new Scanner(System.in);

  public DirectorMenu() {
    super();
  }

  public void menu() {
    int userInput;

    while (true) {

      System.out.println("\n\tDirector's System+\n");
      System.out.println("1. View the list of Middle Schools and Principals:");
      System.out.println("2. View the list of High Schools and Principals:");
      System.out.println("3. View the list of University/College Presidents:");
      System.out.println("4.View the list of Students:");
      System.out.println("5. View the list of Teachers:");
      System.out.println("\n");
      System.out.print("Your choice, 0 to return to Home Page: ");

      try {
        userInput = scanner.nextInt();
      } catch (Exception e) {
        System.out.println("\n---------------  Invalid option  ---------------");
        System.out.println("Please enter a digit from 0 to 3. Try again!\n");
        continue; // Continue the loop to ask for input again
      }

      switch (userInput) {
        case 0:
          return;

        case 1:
          viewMiddleSchoolsAndPrincipals();
          break;

        case 2:
          viewHighSchoolsAndPrincipals();
          break;

        case 3:
          viewUniversityPresidents();
          break;

        case 4:
          try{
            readFile("credentials.txt");
          }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          
           for (String student : newList) {
                  StringTokenizer token = new StringTokenizer(student, ",");
                    String id = token.nextToken();
                    String name = token.nextToken();
                    String lastName = token.nextToken();
                    String age = token.nextToken();
                    String gpa = token.nextToken();
                    String school = token.nextToken();
                    System.out.println("ID: " + id + " FName: " + name + " LName: " + lastName 
                                       + " Age: " + age + " GPA: " + gpa + " School: " + school);
                  
           }
              break;
                
        case 5:
          try{
            readFile("Teachers.txt");
          }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
          
           for (String student : newList) {
                  StringTokenizer token = new StringTokenizer(student, ",");
                    String id = token.nextToken();
                    String name = token.nextToken();
                    String lastName = token.nextToken();
                    String email = token.nextToken();
                    String school = token.nextToken();
                    System.out.println("ID: " + id + " FName: " + name + " LName: " + lastName 
                                       + " Email: " + email + " School: " + school);
                  
           }
              break;        

        default:
          System.out.println("\nInvalid option. Please enter a digit from 0 to 3. Try again!\n");
          break;
      }
    }
  }

  public void readFile(String fileName) throws FileNotFoundException, IOException {
    newList.clear();
    BufferedReader bufReader = new BufferedReader(
        new FileReader("src/main/java/gov/smartcityteam2/Education/" + fileName));
    String line = bufReader.readLine();
    while (line != null) {
      newList.add(line);
      line = bufReader.readLine();
    }
    bufReader.close();
  }
}

