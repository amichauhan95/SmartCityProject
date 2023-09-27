package gov.smartCityGUI.tourism;


import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.tourism.page.admin.AdminPage;
import gov.smartCityGUI.tourism.page.user.UserPage;

public class TourismWindow{

  /* TourismWindow enrty point, show the page based on user's role*/
  public TourismWindow(User user){
    if(user.isAdmin()) new AdminPage(user);
    else new UserPage(user); 
  }

}