package gov.smartcityteam2;

import java.util.Scanner;
import java.util.Map;
import gov.smartcityteam2.Tourism.controller.TourismSystem;
import gov.smartcityteam2.Tax.*;
import gov.smartcityteam2.Transit.*;
import gov.smartcityteam2.SmartCity;
import gov.smartcityteam2.User;
import gov.smartcityteam2.Healthcare.controller.*;
import gov.smartcityteam2.EMS.*;
import gov.smartcityteam2.Education.*;
import gov.smartcityteam2.Banking.*;
import gov.smartcityteam2.Forum.*;
import gov.smartcityteam2.Admin.*;
import gov.smartCityGUI.Driver;

public class Menue {

  private SmartCity city = new SmartCity(); // User Login System
  private TourismSystem tourism = new TourismSystem();
  private TaxSystem tax = new TaxSystem();
  private MessageSystem messages = new MessageSystem();
  private BankAccount bank = new BankAccount();
  private StudentSystem student = new StudentSystem();
  private UserManager userManager = new UserManager();
  private EMS ems = new EMS();

  /**
    * Menue displays the menu options at start up
    * @throws Exception if input choice is invalid
  **/
  public Menue() {

    User currentUser = city.login(); // Logs in or registers a user for system use

    while (currentUser.getID() != null) {

      Util.header("Welcome to Albany+ " + currentUser.getFirst());
      System.out.println("\n1. Tourism System\n");
      System.out.println("2. Hospital System\n");
      System.out.println("3. Transportation System \n");
      System.out.println("4. Education System\n");
      System.out.println("5. EMS\n");
      System.out.println("6. Tax\n");
      System.out.println("7. Banking System\n");
      System.out.println("8. City Message Board\n");
      if(currentUser.isAdmin()){
        Util.header("\tAdmin Panel");
        System.out.println("9. Manage Users\n10. GUI\n\n" + Util.BOLD_ON + Util.DIV + Util.BOLD_OFF + "\n");
      }
      System.out.print(Util.BOLD_ON + "Enter your choice, or 0 to quit: " + Util.BOLD_OFF);

      Scanner scanner = new Scanner(System.in);
      int option = 0;

      try {
        option = scanner.nextInt();
      } catch (Exception e) {
        System.out.println(String.format("\n---------------  %s    ---------------", Util.INVALID_OPTION_MESSAGE));
        System.out.println("Please enter a valid menu choice.");
        System.out.println(Util.BORADER + "\n");
        continue;
      }

      try {
        switch (option) {
          case 1:
            tourism.menue(currentUser);
            break;
  
          case 2:
            HospitalSystem hospital = new HospitalSystem();
           // hospital.menu(currentUser);
  
            break;
  
          case 3:
            ITransitRepository repository = new DummyTransitRepository();
            ITransitService service = new TransitService(repository);
            TransitController controller = new TransitController(service);
            controller.menu();
  
            break;
  
          case 4:
  
            student.menu();
            break;
          // to-do: Education System
  
          case 5:
            // to-do: EMS
            if(currentUser.isAdmin()) {
              ems.showAdminMenu();
            }
            else {
              ems.showUserMenu();
            }
            
            break;
  
          case 6:
            tax.menu(currentUser);
            break;
          case 7:
            bank.open(currentUser);
            break;
          case 8:
            messages.start(currentUser);
            break;
          case 9:
            if(currentUser.isAdmin()){
              userManager.start(currentUser);
            }
            break;
  		  case 10:
  			  Driver driver = new Driver();
  			  driver.start();
  			  return;
          case 0:
            System.exit(0);
            break;
          default:
            System.out.println(String.format("\n---------------  %s    ---------------", Util.INVALID_OPTION_MESSAGE));
            System.out.println("Please enter a valid menu choice.");
            System.out.println("--------------------------------------------------\n");
        }
      } catch (Exception e) {
        System.out.println("An error has occurred");
        e.printStackTrace();
      }

      
    }

  }
}