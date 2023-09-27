/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/

// package gov.smartcityteam2.Tourism.model;
// import gov.smartcityteam2.User;
package gov.smartCityGUI.tourism.model;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.text.DateFormat;
import gov.smartCityGUI.admin.models.User;


public class UserComments implements Comparable<UserComments>{
  private static int id_count = 0;
  private int placeId;
  private int commentId;
  private String placeName;
  private User user;
  private String date;
  private String comments;
  private double rate;


  // Constructor: when a user is adding a new comments
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

  // Constructor: updateComment() in UserCommentsRepo
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

  // Constructor: Loading data
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
  
  // Setter & Getter
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
    return commentId;
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

  // User - view themself comments
  public String toStringV1(){
    return String.format("PlaceName: %s\nComments: %s\nRate: %s %.2f\nDate posted: %s\n", placeName, comments, rateFormat(), rate, date);
  }

  // Admin - view all user comments
  public String toStringV2(){
    return String.format("User: %s, %s\nPlaceName: %s\nComments: %s\nRate: %s %.2f\nDate posted: %s\n", user.getLast(), user.getFirst(), placeName, comments, rateFormat(), rate, date);
  }

  public int compareTo(UserComments c) {
    // return this.getRate() > p.getRate() ? 1 : (this.getRate() < p.getRate() ? -1 : 0);
    return this.getDate().compareTo(c.getDate());
  }
  
  public static class CommentByLatest implements Comparator<UserComments> {
    public int compare(UserComments o1, UserComments o2) {
      
      try {
        DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        return dfm.parse(o1.getDate()).compareTo(dfm.parse(o2.getDate()));
      } catch (Exception e) {
            
      }
      return 0;
    }
  }

  public static class CommentByEarliest implements Comparator<UserComments> {
    public int compare(UserComments o1, UserComments o2) {
      
        try {
          DateFormat dfm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          return dfm.parse(o2.getDate()).compareTo(dfm.parse(o1.getDate()));
        } catch (Exception e) {
            
        }
      return 0;
    }
  }

}