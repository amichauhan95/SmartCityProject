/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.model;

import gov.smartcityteam2.User;
import java.util.Date;
import java.text.SimpleDateFormat;

public class UserComments{
  private static int id_count = 0;
  private int placeId;
  private int commentId;
  private String placeName;
  private User user;
  private String date;
  private String comments;
  private double rate;

  // Constructer: when a user is adding a new comments
  public UserComments(User user, String comments, double rate) {
    this.commentId = ++id_count;
    this.user = user;
    this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    this.comments = comments;
    this.rate = rate;
  }

  public UserComments(String comments, double rate) {
    this.commentId = ++id_count;
    this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    this.comments = comments;
    this.rate = rate;
  }

  public UserComments(int commentId, User user, String comment, double rate){
    this.commentId = commentId;
    this.user = user;
    this.comments = comment;
    this.rate = rate;
  }
  
  public UserComments(int commentId, String placeName, User user, String date, String comments, double rate){
    this.commentId = commentId;
    this.placeName = placeName;
    this.user = user;
    this.date = date;
    this.comments = comments;
    this.rate = rate;
    ++id_count;
  }

  // Constructer: Loading data
  public UserComments(String placeName, User user, String date, String comments, double rate){
    this.placeName = placeName;
    this.user = user;
    this.date = date;
    this.comments = comments;
    this.rate = rate;
    this.commentId = ++id_count;
  }
  
  // Update
  public void updateUserComments(String comments, double rate){
    this.comments = comments;
    this.rate = rate;
    this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }
  
  // Setter & Getter: Missing setCommentId
  public void setPlaceId(int placeId){
    this.placeId = placeId;
  }
  
  public void setPlaceName(String placeName){
    this.placeName = placeName;
  }
  
  public void setUser(User user){
    this.user = user;
  }

  public void setComment(String comments){
    this.comments = comments;
  }

  public void setDate(String date){
    this.date = date;
  }

  public void setRate(double rate){
    this.rate = rate;
  }
  
  public String getDate(){
    return date;
  }

  public String getComments(){
    return comments;
  }

  public double getRate(){
    return rate;
  }

  public int getPlaceId(){
    return placeId;
  }

  public int getCommentId(){
    return this.commentId;
  }

  public User getUser(){
    return this.user;
  }

  public String getPlaceName(){
    return this.placeName;
  }

  public String rateFormat(){
    if(this.rate == 5.0) return "★★★★★";
    else if (this.rate >= 4) return "★★★★";
    else if (this.rate >= 3) return "★★★";
    else if (this.rate >= 2) return "★★";
    else if (this.rate >= 1) return "★";
    else return "★";
  }

  public String toString(){
    return String.format("\n\tComments: %s\n\tRate: %s %.2f\n\tDate posted: %s\n\n", comments, rateFormat(), rate, date);
  }

  // Consume by getAllCommentsByUser()
  public String toStringGetAll(){
    return String.format("\n\tCommentId: %s\n\tName: %s, %s\n\tPlaceName: %s\n\tComments: %s\n\tRate: %s %.2f\n\tDate posted: %s\n\n", commentId, user.getLast(), user.getFirst(), placeName, comments, rateFormat(), rate, date);
  }


}