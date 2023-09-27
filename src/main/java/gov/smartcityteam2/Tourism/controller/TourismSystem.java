/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.controller;

import gov.smartcityteam2.User;
import gov.smartcityteam2.Tourism.controller.page.*;


public class TourismSystem{

  private Menu m;
  private User user;


  public TourismSystem(){}
  public TourismSystem (User user){
    this.user = user;
  }

  /* Binding a user to system, and set whether a admin page or user page based on user's role*/
  public void setMenu(User user){
    if(user.getRole().equals("Admin")) this.m = new AdminPage(user);
    else this.m = new UserPage(user);
  }

  public void menue(User user){
    setMenu(user);
    m.mainPage();
  }
}