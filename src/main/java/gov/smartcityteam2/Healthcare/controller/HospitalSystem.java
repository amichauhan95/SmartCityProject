package gov.smartcityteam2.Healthcare.controller;

import java.util.Scanner;

import gov.smartcityteam2.User;
import gov.smartcityteam2.Healthcare.service.DoctorList;
import gov.smartcityteam2.Healthcare.service.BookAppointment;
import gov.smartcityteam2.Healthcare.service.CheckInHospital;
import gov.smartcityteam2.Healthcare.service.HospitalList;
import gov.smartcityteam2.Banking.BankAccount;

public class HospitalSystem {

  int hospitalID;
  String hospitalName;
  String hospitalAddress;
  String hospitalPhone;
  String hospitalEmail;
  String authcode;

  public void menu(User currentUser) {

    Scanner scan = new Scanner(System.in);
    HospitalList h1 = new HospitalList();
    BookAppointment b1 = new BookAppointment();
    CheckInHospital b2 = new CheckInHospital();
    DoctorList d1 = new DoctorList();
    BankAccount account = new BankAccount();

    int choice;
    try {
      do {
        System.out.println("\n--------Hospital Resources--------\n");
        System.out.println("1.View All Hospitals in Albany\n");
        System.out.println("2.Book Walk-In Appointment\n");
        System.out.println("3.View Walk-In Appointment\n");
        System.out.println("4.Check-In for Walk-In Appointment(Non-Emergency)\n");
        System.out.println("5.View Report for appointment\n");
        System.out.println("6.Pay Bill for appointment\n");
        if (currentUser.getRole().equals("1234")) {
          System.out.println("\n--------Admin Menu--------\n");
          System.out.println("7.Add Hospital Details in List\n");
          System.out.println("8.Add Doctor for Walk-In Hospital\n");
          System.out.println("9.View All Doctors for Walk-In Hospital\n");
        }
        if (currentUser.getRole().equals("1444")) {
          System.out.println("\n--------Doctor Menu--------\n");
          System.out.println("7.Update Patient Report & Bill for Walk-In Appointments\n");
          System.out.println("8.View All Patient's Reports by Date\n");

        }

        System.out.print("Enter Choice: ,0 to Return to Main Menu: ");
        choice = scan.nextInt();
        System.out.println();
        scan.nextLine();
        switch (choice) {
          case 1: {

            System.out.println("\n----List of Hospitals in Albany----");
            h1.getHospitalList();

          }
            break;
          case 2: {
            b1.addAppointment(currentUser.getID());
          }
            break;
          case 3: {
            b1.viewAppointments(currentUser.getID());

          }
            break;
          case 4: {
            b2.checkInAppointment();
          }
            break;
          case 5: {
            b2.viewPatientsReport();
          }
            break;
          case 6: {
            System.out.println("Enter Appointment ID: ");
            String appointmentID = scan.nextLine();
            double totalBill = b2.payBill(appointmentID);
            if (totalBill <= 0) {
              System.out.println("\nBill is Already Payed !");
            } else {
              System.out.println("Enter Amount to Pay: ");
              double charge = scan.nextDouble();
              scan.nextLine();
              totalBill = totalBill - charge;
              System.out.println("Enter CONFIRM to pay: ");
              String check = scan.nextLine();
              if (check.equals("CONFIRM")) {
                System.out.println("\nTransaction for $" + charge + " is processing...");
                if (totalBill >= 0) {
                  if (account.charge(currentUser, charge)) {
                    b2.updateFees(appointmentID, totalBill);
                  }
                } else {
                  System.out.println("\nTransaction for $" + charge + " cannot be processed");
                }
              }
            }

          }
            break;
          case 7: {

            if (currentUser.getRole().equals("1234")) {
              System.out.println("Enter Hospital Name: ");
              hospitalName = scan.nextLine();
              System.out.println("Enter Hospital Address: ");
              hospitalAddress = scan.nextLine();
              System.out.println("Enter Hospital Phone: ");
              hospitalPhone = scan.nextLine();
              System.out.println("Enter Hospital Email: ");
              hospitalEmail = scan.nextLine();
              System.out.println("Enter Authentication Code: ");
              authcode = scan.nextLine();
              if (authcode.equals("ADMIN1234")) {
                boolean success = h1.addHospitals(hospitalName, hospitalAddress, hospitalPhone, hospitalEmail);
                if (success == true) {
                  System.out.println("\nHospital Added to the List Successfully!");
                }
              } else {
                System.out.println("\nPlease Try Again!");
              }
            }
            if (currentUser.getRole().equals("1444")) {
              b2.submitReport(currentUser.getID());
            }

          }
            break;
          case 8: {
            if (currentUser.getRole().equals("1234")) {
              System.out.println("Enter Doctor's First Name: ");
              String doctorFirstName = scan.nextLine();
              System.out.println("Enter Doctor's Last Name: ");
              String doctorLastName = scan.nextLine();
              System.out.println("Enter Doctor's Phone: ");
              String doctorPhone = scan.nextLine();
              System.out.println("Enter Doctor's Email: ");
              String doctorEmail = scan.nextLine();
              System.out.println("Enter Doctor's Consulting Fee: ");
              double doctorFee = scan.nextDouble();
              scan.nextLine();
              System.out.println("Enter Doctor's ID: ");
              String doctorID = scan.nextLine();
              if (!d1.doctorPresent(doctorID)) {
                System.out.print(
                    d1.addDoctor(doctorID, doctorFirstName, doctorLastName, doctorPhone, doctorEmail, doctorFee));
              } else {
                System.out.println("\nDoctor Already Exists");
              }
            }
            if (currentUser.getRole().equals("1444")) {
              b2.getPatientsReport(currentUser.getID());
            }

          }
            break;
          case 9: {
            d1.getDoctorList();
          }
            break;

          default: {
            System.out.println("Invalid input");
          }

        }

      } while (choice != 0);
    } catch (Exception e) {
      System.out.println(e);

    }
  }

}