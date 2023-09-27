/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.service;
// import gov.smartcityteam2.Tourism.repository.PlaceRepo;
import gov.smartcityteam2.Tourism.model.UserComments;
import gov.smartcityteam2.User;
import gov.smartcityteam2.Tourism.util.*;
import gov.smartcityteam2.Tourism.repository.UserCommentsRepo;
public class CommentsService {
  private UserCommentsRepo commentRepo = new UserCommentsRepo();
  
  /* Handle UserComments service */
  
  // Services for User
  public void addComment(Object[] o, User user){
    int placeId = (Integer)o[0];
    String comment = (String)o[1];
    double rate = (Double)o[2];
    boolean isSucceed = commentRepo.addComment(placeId, new UserComments(user, comment, rate));
    ValidationUtil.messagePopUp(isSucceed, "Added Successfully...", "PlaceID is not found");

  }

  public void updateComment(Object[] o, User user){
    int commentId = (Integer)o[0];
    String comment = (String)o[1];
    double rate = (Double)o[2];
    boolean isSucceed = commentRepo.updateComment(commentId, user, comment, rate);

    ValidationUtil.messagePopUp(isSucceed, "Updated Successfully...", "CommentId is not found");
    // if(isSucceed){
    //   commentRepo.increaseReview((Integer)o[0]);
    //   commentRepo.updateRate((Integer)o[0], (Double)o[2]);
    // }
  }

  public void deleteComment(Object[] o, User user){
    int commentId = (Integer)o[0];
    boolean isSucceed = commentRepo.deleteComment(commentId, user);
    ValidationUtil.messagePopUp(isSucceed, "Deletion Successfully", "CommentId is not found");
  }
  
  public boolean allCommentsByAUser(User user){
    String data = commentRepo.getAllCommentsByAUser(user);
    if(!data.equals("")){
      System.out.println(data + "\n");
      return true;
    } else {
      System.out.println("\n*Your haven't made any comments yet*\n");
      return false;
    }
  }

  // Services for Admin
  public void addPlace(Object[] o){
    // String name = (String)o[0];
    // String des = (String)o[1];
    // double rate = (double)o[2];
    // int numberOfReviews = (Integer)o[3];
    // boolean isSucceed = commentRepo.addPlace(new Place(name, des, numberOfReviews, rate));

    // ValidationUtil.messagePopUp(isSucceed, "New Place has been added", "Error, cant add a place");
  }

  public boolean allCommentsByUsers(){
    // MenuUtil.setHeader("-", 35);
    // MenuUtil.setTitle("All User Comments");
    String data = commentRepo.getAllCommentsByUsers();
    if(!data.equals("")){
      System.out.println(data + "\n");
      return true;
    } else {
      System.out.println("\n*No user comments is found*\n");
      return false;
    }
  }

  public void deleteUserComment(Object[] o){
    if(allCommentsByUsers()){
      int commentId = (Integer)o[0];
      boolean isSucceed = commentRepo.deleteUserComment(commentId);
      ValidationUtil.messagePopUp(isSucceed, "Deletion Successfully", "CommentId is not found"); 
    }
  }
}