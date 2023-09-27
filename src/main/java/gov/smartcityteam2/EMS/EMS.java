/**
  * Team Member(s) working on this class: Keith Austin
  * Project: Smart City
  * @author: Keith Austin
  * I received help from everyone on my team
**/

package gov.smartcityteam2.EMS;

import java.util.*;

public class EMS {
  Scanner sc = new Scanner(System.in);
  Contact contact = new Contact();
  Career career = new Career();
  Admin admin = new Admin();
  Report report = new Report();

  /**
    * public void showUserMenu() displays the choice menu for the user
    * @throws Exception if the file isn't read from or written to correctly
  **/
  public void showUserMenu() throws Exception {
    System.out.println("\nEMS | Home");
    System.out.println("1) View Department Contacts");
    System.out.println("2) View Job Opportunities");
    System.out.println("3) File a Report");
    System.out.println("0) Exit");
    int choice = sc.nextInt();

    do {
      switch(choice) {
        case 1:
          contact.showContacts();
        case 2:
          career.apply();
        case 3:
          report.createReport();
        case 0:
          return;
      }
    } while (true);
  }

  /**
    * public void showAdminMenu() displays the choice menu for the admin
    * @throws Exception if the file isn't read from or written to correctly 
  **/
  public void showAdminMenu() throws Exception {
    admin.homepage();
  }
}