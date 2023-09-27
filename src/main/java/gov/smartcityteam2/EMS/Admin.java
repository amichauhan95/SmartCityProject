/**
  * Team Member(s) working on this class: Keith Austin
  * Project: Smart City
  * @author: Keith Austin
  * I received help from everyone on my team
**/

package gov.smartcityteam2.EMS;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Admin {
  Scanner sc = new Scanner(System.in);
  
  /**
    * public void homepage() displays the homepage for the admin
    * @throws Exception if the file isn't read from or written to correctly
  **/
  public void homepage() throws Exception {
    System.out.println("\nAdmin | Home\n");
    System.out.println("1) Manage Job Listings");
    System.out.println("2) Manage Contact Information");
    System.out.println("3) Manage Reports");
    System.out.println("0) Exit");
    int adminInput = sc.nextInt();

    do {
      switch(adminInput) {
        case 1:
          jobPage();
        case 2:
          contactPage();
        case 3:
          reportPage();
        case 0:
          return;
      }
      sc.close();
    } while(true);
    
    
  }

  /**
    * public void jobPage() displays the job page for the admin
    * @throws Exception if the file isn't read from or written to correctly
  **/
  public void jobPage() throws Exception {
    System.out.println("\nAdmin | Jobs\n");
    System.out.println("1) Add Job");
    System.out.println("2) Remove Job");
    System.out.println("3) View All Jobs");
    System.out.println("0) Exit");
    int adminInput = sc.nextInt();

    do {
      switch(adminInput) {
        case 1:
          addJob();
        case 2:
          deleteJob();
        case 3:
          viewJobs();
        case 0:
          return;
      }
      sc.close();
    } while (true);
  
  }

  /**
    * public void contactPage() displays the contact page for the admin
    * @throws Exception if the file isn't read from or written to correctly
  **/
  public void contactPage() throws Exception {
    System.out.println("\nAdmin | Contacts\n");
    System.out.println("1) Add Contact");
    System.out.println("2) Delete Contact");
    System.out.println("3) View Contacts");
    System.out.println("0) Exit");
    int adminInput = sc.nextInt();

    do {
      switch(adminInput) {
        case 1:
          addContact();
        case 2:
          deleteContact();
        case 3:
          viewContacts();
        case 0:
          return;
      }
      sc.close();
    } while (true);
  }

  /**
    * public void reportPage() displays the report page for the admin
    * @throws Exception if the file isn't read from or written to correctly
  **/
  public void reportPage() throws Exception {
    System.out.println("\nAdmin | Report\n");
    System.out.println("1) View Reports");
    System.out.println("2) Delete Report");
    System.out.println("0) Exit");
    int adminInput = sc.nextInt();

    do {
      switch(adminInput) {
        case 1:
          viewReports();
        case 2:
          deleteReport();
        case 0:
          return;
      }
      sc.close();
      
    } while (true);
  
  }

  /**
    * public void deleteJob() deletes a job from the list based on the admin's input
    * @throws Exception if the file isn't written or read to correctly
  **/
  public void deleteJob() throws Exception {
    Scanner myReader = new Scanner("src/main/java/gov/smartcityteam2/EMS/data/careers.txt");
    System.out.println("\nAdmin | Delete Job\n");
    
    try {
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (Exception e) {
      System.out.println("An error has occurred.");
      e.printStackTrace();
    }
    
    System.out.print("Choose the Deparment: ");
    String depSelection = sc.nextLine();
    System.out.print("Choose the Company: ");
    String companySelection = sc.nextLine();
    
    try {
      ArrayList<String> listOfCareers = new ArrayList<String>();
      File inputFile = new File("careers.txt");
      File tempFile = new File("myTemp.txt");
      myReader = new Scanner("src/main/java/gov/smartcityteam2/EMS/data/careers.txt");
      
      BufferedReader reader = new BufferedReader(new FileReader(inputFile));
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
      String currentLine;
      
      while (myReader.hasNext()) {
        String data = myReader.nextLine();
        listOfCareers.add(data);
      }
      
      while((currentLine = reader.readLine()) != null) {
        String trimmedLine = currentLine.trim();
        if(trimmedLine.equals(depSelection) && trimmedLine.equals(companySelection))
           continue;
        writer.write(currentLine + System.getProperty("line.separator"));
      }
      writer.close(); 
      reader.close(); 
      boolean successful = tempFile.renameTo(inputFile);
      System.out.println(successful);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
    * public void addJob() adds a job to the list based on the admin's input
    * @throws IOException if the file isn't written to correctly
  **/
  public void addJob() throws IOException {
    ArrayList<String> newCareer = new ArrayList<String>();
    StringJoiner joiner = new StringJoiner(",");
    System.out.println("\nAdmin | Add Career\n");
    System.out.println("Current Career Listing");
    System.out.println();
    FileWriter fw = null;
    BufferedWriter bw = null;
  
    try {
      String file = "src/main/java/gov/smartcityteam2/EMS/data/careers.txt";
      Scanner myReader = new Scanner(file);
      fw = new FileWriter(file);
      bw = new BufferedWriter(fw);
      
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
      }
      myReader.close();
    } catch (Exception e) {
      System.out.println("An error has occurred.");
      e.printStackTrace();
    }
    System.out.println();
    System.out.print("Enter the department (must be a fire department, police department or paramedic department): ");
    String depInput = sc.nextLine();
    newCareer.add(depInput);
    System.out.print("Enter the company: ");
    String comInput = sc.nextLine();
    newCareer.add(comInput);
    System.out.print("Enter the position: ");
    String posInput = sc.nextLine();
    newCareer.add(posInput);
    System.out.print("Enter the city: ");
    String cityInput = sc.nextLine();
    newCareer.add(cityInput);
    System.out.print("Enter the state: ");
    String stateInput = sc.nextLine();
    newCareer.add(stateInput);
    System.out.print("Enter annual salary (if the salary is $46,000, enter 46000): ");
    String salaryInput = sc.nextLine();
    newCareer.add(salaryInput);

    for (String i : newCareer) {
      try {
        bw.write(i);
        bw.flush();
        bw.close();
      } catch (IOException e) {
        System.out.println("An error has occurred");
        e.printStackTrace();
      }
    }
  }

  /**
    * public void viewJobs() displays all jobs in the list
    * @throws IOException if the file isn't read from correctly
  **/
  public void viewJobs() throws IOException {
    System.out.println("\nAdmin | View Jobs\n");
    BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader("src/main/java/gov/smartcityteam2/EMS/data/careers.txt"));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

  /**
    * public void deleteContact() deletes a contact from the list based on the admin's input
    * @throws Exception if the file isn't read from correctly
  **/
  public void deleteContact() throws Exception {
    System.out.println("Admin | Delete Contact\n");
    File inputFile = new File("src/main/java/gov/smartcityteam2/EMS/data/contacts.txt");
    File tempFile = new File("myTempFile.txt");

    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

    String[] keywords = {
      "Fire Department",
      "Fire",
      "Police Department",
      "Police",
      "Paramedic Department",
      "Paramedic"
    };

    String[] phoneNumbers = {
      "5184477879",
      "5184639494", 
      "5184632883",
      "3158966514",
      "5184384750",
      "5184653243",
      "5182863612",
      "5184344151",
      "5182628559",
      "5184344444",
      "5186944420",
      "5187822645",
      "5184585660",
      "5184628049",
      "5184627451",
      "5182703833",
      "5182732401"
    };

    System.out.println("Enter the Deparment (Fire, Police, Paramedic): ");
    String depName = sc.next();
    System.out.print("Enter the Department's Phone Number (5167781010): ");
    String depPhone = sc.next();

    try {
      for (String keyword : keywords) {
        for (String number : phoneNumbers) {
          if (depName.toLowerCase().equals(keyword) && depPhone.toLowerCase().equals(number)) {
            String lineToRemove = depName;
            String currentLine;
            
            while((currentLine = reader.readLine()) != null) {
              String trimmedLine = currentLine.trim();
                
              if(trimmedLine.equals(lineToRemove)) continue;
                  writer.write(currentLine + System.getProperty("line.separator"));
              }
              writer.close(); 
              reader.close(); 
              boolean successful = tempFile.renameTo(inputFile);
              System.out.println(successful);
          }
        }
    }
    } catch (Exception e) {
      System.out.println("An error has occurred");
      e.printStackTrace();
    }
    
  }

  /**
    * public void addContact() adds a contact to the list based on the admin's input
    * @throws Exception if the file isn't written to correctly
  **/
  public void addContact() throws Exception {
    System.out.println("Admin | Add Contact\n");
    ArrayList<String> newContact = new ArrayList<String>();
    StringJoiner joiner = new StringJoiner(",");
    String file = "src/main/java/gov/smartcityteam2/EMS/data/contacts.txt";
    FileWriter fw = null;
    BufferedWriter bw = null;

    try {
      fw = new FileWriter(file);
      bw = new BufferedWriter(fw);
      System.out.print("Enter the department (must be a fire department, police department or paramedic department): ");
      String depInput = sc.nextLine();
      newContact.add(depInput);
      System.out.print("Enter Company Name: ");
      String comInput = sc.nextLine();
      newContact.add(comInput);
      System.out.print("Enter Street Address: ");
      String streetInput = sc.nextLine();
      newContact.add(streetInput);
      System.out.print("Enter City: ");
      String cityInput = sc.nextLine();
      newContact.add(cityInput);
      System.out.print("Enter State: ");
      String stateInput = sc.nextLine();
      newContact.add(stateInput);
      System.out.print("Enter Zip: ");
      String zipInput = sc.nextLine();
      newContact.add(zipInput);
      System.out.print("Enter Phone Number: ");
      String phoneInput = sc.next();
      newContact.add(phoneInput);
    } catch (Exception e) {
      System.out.println("An error has occurred");
      e.printStackTrace();
    }
    
    for (String i : newContact) {
      try {
        bw.write(i);
        bw.flush();
        bw.close();
      } catch (IOException e) {
        System.out.println("An error has occurred");
        e.printStackTrace();
      }
    }
  }

  /**
    * public void viewContacts() displays all contacts from the list
    * @throws Exception if the file isn't read from correctly
  **/
  public void viewContacts() throws Exception {
    System.out.println("Admin | View Contacts\n");
    BufferedReader reader;

		try {
      
			reader = new BufferedReader(new FileReader("src/main/java/gov/smartcityteam2/EMS/data/contacts.txt"));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }

  /**
    * public void deleteReport() deletes a report from the list based on the admin's input
    * @throws Exception if the file isn't read from or written to correctly
  **/
  public void deleteReport() throws Exception {
    System.out.println("Admin | Delete Report\n");
    Scanner sc = new Scanner(System.in);
    
    File inputFile = new File("src/main/java/gov/smartcityteam2/EMS/data/reports.txt");
    File tempFile = new File("myTempFile.txt");

    BufferedReader reader = new BufferedReader(new FileReader(inputFile));
    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

    System.out.print("Enter the Report ID to Delete: ");
    String repID = sc.next();
    
    String lineToRemove = repID;
    String currentLine;

    while((currentLine = reader.readLine()) != null) {
        String trimmedLine = currentLine.trim();
        if(trimmedLine.equals(lineToRemove)) continue;
        writer.write(currentLine + System.getProperty("line.separator"));
    }
    writer.close(); 
    reader.close(); 
    boolean successful = tempFile.renameTo(inputFile);
    System.out.println(successful);

    sc.close();
  }

  /**
    * public void viewReports() displays all reports from the list
    * @throws Exception if the file isn't read from correctly
  **/
  public void viewReports() throws Exception {
    System.out.println("\nAdmin | View Report\n");
    
    BufferedReader reader;

		try {
  
			reader = new BufferedReader(new FileReader("src/main/java/gov/smartcityteam2/EMS/data/reports.txt"));
			String line = reader.readLine();

			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
  }
}