/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.controller.page;
import gov.smartcityteam2.Tourism.service.*;
import gov.smartcityteam2.User;
public class Menu{
  
  private PlaceFilter placeFilter = new PlaceFilter();
  private CommentsService commentsService = new CommentsService();
  private PlaceService placeService = new PlaceService();
  private User user;

  public Menu(User user){
    this.user = user;
  }

  public Menu(){
  }
  
  
  public CommentsService getCommentsService(){
    return commentsService;
  }

  public PlaceFilter getPlaceFilter(){
    return placeFilter;
  }

  public PlaceService getPlaceService(){
    return placeService;
  }

  /* */
  public User getUser(){
    return user;
  }

  public void mainPage(){

  }

  public void commentPage(){

  }

  public void eventPage(){

  }

  
}