/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.model;

import java.util.Comparator;
import java.util.ArrayList;

public class Place implements Comparable<Place>  {
  private static int count = 0;
  private int placeId;
  private String name;
  private String placeDes;
  private double rate;
  private int numOfReviews;
  private ArrayList<UserComments> userCommentsList = new ArrayList();
  // private ArrayList<Events> eventsList = new ArrayList();
  


  public Place(String name, double rate){
    this.name = name;
    this.rate = rate;
  }

  // Admin - add new place
  public Place(String name, String placeDes, int numOfReviews, double rate){
    this.placeId = ++count;
    this.name = name;
    this.rate = rate;
    this.numOfReviews = numOfReviews;
    this.placeDes = placeDes;
  }

  public Place(int placeId, String name, String placeDes, int numOfReviews, double rate){
    this.placeId = placeId;  
    this.name = name;
    this.rate = rate;
    this.numOfReviews = numOfReviews;
    this.placeDes = placeDes;
  }

  public void addComments(UserComments usercomment){
    userCommentsList.add(usercomment);
  }

  
  // Getter
  public String getPlaceDes(){
    return this.placeDes;
  }

  public int getPlaceId(){
    return this.placeId;
  }
  
  public String getName( ){
    return this.name;
  }
  
  public double getRate( ){
    return this.rate;
  }

  public int getReviews( ){
    return this.numOfReviews;
  }

  public ArrayList<UserComments> getUserComments(){
    return this.userCommentsList;
  }

  // public ArrayList<Events> getEventsList(){
  //   return this.eventsList;
  // }

  public int getCount(){
    return count;
  }

  // Setter
  public void setCount(int count){
    this.count = count;
  }
  
  public void setUserComments(UserComments usercomment){
    userCommentsList.add(usercomment);
  }
  
  public void setPlaceDes(String placeDes){
    this.placeDes = placeDes;
  }

  public void setName(String name){
    this.name = name;
  }
  
  public void setRate(double rate){
    this.rate = rate;
  }

  public void setPlaceId(int placeId){
    this.placeId = placeId;
  }

  public void setReviews(int numOfReviews){
    this.numOfReviews = numOfReviews;
  }

  // Update Rate
  public void updateRate(int placeId, double rate){
    if(this.placeId == placeId) {
      int tempReviews = getReviews();
      setReviews(getReviews() + 1);
      setRate((getRate() * tempReviews + rate) / (getReviews()));
    }
  }

  // ToString: {placeID, Name, Description, Reviews, Rate}
  public String toString(){
    return String.format("\n\tPlaceId: %s\n\tName: %s\n\tDescription: %s\n\tReviews: %s\n\tRate: %s %.2f\n\n", placeId, name, placeDes, numOfReviews, rateFormat() ,rate);
  }

  // ToString: {placeId, Name}
  public String toStringIdAndName(){
    return String.format("\n\tPlaceId: %s\n\tName: %s\n\t", placeId, name);
  }

  public String rateFormat(){
    if(this.rate == 5.0) return "★★★★★";
    else if (this.rate >= 4) return "★★★★";
    else if (this.rate >= 3) return "★★★";
    else if (this.rate >= 2) return "★★";
    else if (this.rate >= 1) return "★";
    else return "★";
  }
  
  public String getAllInfor(){
    String str = this.toString();
    str += "\n\t\tUser Comments\n\n";
    for(UserComments temp: userCommentsList){
      str += String.format("\tUser name: %s, %s\n\tComments: %s\n\tDate: %s\n\tRate : %s %.2f\n\n", temp.getUser().getLast(), temp.getUser().getFirst(), temp.getComments(), temp.getDate(), temp.rateFormat(), temp.getRate());
    }
    return str;
    
  }
  
  public int compareTo(Place p) {
    // return this.getRate() > p.getRate() ? 1 : (this.getRate() < p.getRate() ? -1 : 0);
    return this.getName().compareTo(p.getName());
  }

  public static class PlaceByRate implements Comparator<Place> { 
    public int compare(Place p1, Place p2) {
        return p1.getRate() < p2.getRate() ? 1 : (p1.getRate() > p2.getRate() ? -1 : 0);
    }
  }

  public static class PlaceByReview implements Comparator<Place> {  
    public int compare(Place p1, Place p2) {
        return p1.getReviews() < p2.getReviews() ? 1 : (p1.getReviews() > p2.getReviews() ? -1 : 0);
    }
  }


}