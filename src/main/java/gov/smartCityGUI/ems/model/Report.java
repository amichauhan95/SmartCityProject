// /**
//   * @author: Keith Austin
// **/

// package gov.smartcityteam2.EMS;

// import java.io.File;
// import java.io.FileWriter;
// import java.io.BufferedWriter;
// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.Scanner;
// import java.util.List;
// import java.util.ArrayList;
// import java.time.LocalDateTime;
// import gov.smartcityteam2.*;
// import java.io.FileNotFoundException;

// // This class gets the users report information
// public class Report implements ActionListener {
//   private String firstName, lastName, email, phoneNumber, streetAddress, report, date, time;
//   private int id = 0;
  
//   Scanner sc = new Scanner(System.in);

//   public int getId() {
//     return id;
//   }

//   public void setId(int id) {
//     this.id = id;
//     id++;
//   }

//   public String getFirstName() {
//     return firstName;
//   }
  
//   public void setFirstName(String firstName) {
//     this.firstName = firstName;
//   }

//   public String getLastName() {
//     return lastName;
//   }
  
//   public void setLastName(String lastName) {
//     this.lastName = lastName;
//   }

//   public String getEmail() {
//     return email;
//   }

//   public void setEmail(String email) {
//     this.email = email;
//   }
  
//   public String getPhoneNumber() {
//     return phoneNumber;
//   }

//   public void setPhoneNumber(String phoneNumber) {
//     this.phoneNumber = phoneNumber;
//   }

//   public String getStreetAddress() {
//     return streetAddress;
//   }

//   public void setStreetAddress(String streetAddress) {
//     this.streetAddress = streetAddress;
//   }

//   public String getDate() {
//     return date;
//   }

//   public void setDate(String date) {
//     LocalDateTime x = LocalDateTime.now();
//     DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
//     String formattedDate = x.format(dateFormatter);
//     this.date = formattedDate;
//   }

//   public String getTime() {
//     return time;
//   }

//   public void setTime(String time) {
//     LocalDateTime x = LocalDateTime.now();
//     DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//     String formattedTime = x.format(timeFormatter);
//     this.time = formattedTime;
//   }

//   public String getReport() {
//     return report;
//   }

//   public void setReport (String report) {
//     this.report = report;
//   }
  
//   /**
//     *  This method acts a a default constructor to initialize variables that  
//     *  we have not yet set
//   **/
//   public Report(int id, String firstName, String lastName, String email, String phoneNumber, String streetAddress, String report, String date, String time) {
//     this.id = id;
//     this.firstName = firstName;
//     this.lastName = lastName;
//     this.email = email;
//     this.phoneNumber = phoneNumber;
//     this.streetAddress = streetAddress;
//     this.report = report;
//     this.date = date;
//     this.time = time;
//   }

//   Report r = new Report(0, " ", " ", " ", " ", " ", " ", " ", " ");
  
//   /**
//     * This method writes the user's report to the txt file
//   **/
//   public void writeToFile() throws IOException {
//     try {
//       FileWriter fw = new FileWriter("src/main/java/gov/smartcityteam2/EMS/Static/reports.txt");
//       BufferedWriter bw = new BufferedWriter(fw);
//       bw.write(r.toString());
//       bw.close();
//     } catch (IOException e) {
//       System.out.println("An error has occurred");
//       e.printStackTrace();
//     }
//   }

//   /**
//     * This method reads all reports from the txt file
//   **/
//   public void readFile() throws FileNotFoundException {
//     try {
//       Scanner sc = new Scanner("src/main/java/gov/smartcityteam2/EMS/Static/reports.txt");
//       while (sc.hasNextLine()) {
//         String line = sc.nextLine();
//         System.out.println(line);
//       }
//       sc.close();
//     } catch(Exception e) {
//       e.printStackTrace();
//     }
//   }
  
//   /**
//     * This method prompts the user to file a report
//   **/
//   public void createReport() {
//     System.out.print("First Name: ");
//     String firstName = sc.next();
//     System.out.print("Last Name: ");
//     String lastName = sc.next();
//     System.out.print("Phone Number: ");
//     String phoneNumber = sc.next();
//     System.out.print("Email: ");
//     String email = sc.next();
//     System.out.print("Street Address (62 Peachwood Road, Albany NY, 12208): ");
//     String streetAddress = sc.next();
//     System.out.print("Enter your report here: ");
//     report = sc.next();
//     System.out.println();
//     System.out.println("Your report has successfully been recorded!");
//     System.out.println();

//     setFirstName(firstName);
//     setLastName(lastName);
//     setPhoneNumber(phoneNumber);
//     setEmail(email);
//     setStreetAddress(streetAddress);
//     setReport(report);
//     writeToFile();
//   }

//   @Override
//   public String toString() {
//     return getId() + ", " + getFirstName() + ", " + getLastName() + ", " + getEmail() + ", " + getPhoneNumber() + ", " + getStreetAddress() + ", " + getReport() + ", " + getDate() + ", " + getTime();
//   }
// }