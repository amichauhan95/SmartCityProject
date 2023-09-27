/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/

package gov.smartCityGUI.tourism.service;
import java.util.List;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.tourism.repository.UserCommentsRepo;
import gov.smartCityGUI.tourism.model.UserComments;

public class CommentsService {
  private UserCommentsRepo commentRepo = new UserCommentsRepo();
  
  /* Handle UserComments service */
  
  // Services for User
  public void addComment(Object[] o, User user){
    String comment = (String)o[1];
    double rate = Double.parseDouble((String)o[0]);
    int placeId = (Integer)o[2];
    boolean isSucceed = commentRepo.addComment(placeId, new UserComments(user, comment, rate));
    

  }

  public void updateComment(Object[] o, User user){
    int commentId = (Integer)o[0];
    String comment = (String)o[1];
    double rate = Double.parseDouble((String)o[2]);
    boolean isSucceed = commentRepo.updateComment(commentId, user, comment, rate);
  }

  public void deleteComment(int commentId){
    boolean isSucceed = commentRepo.deleteComment(commentId);
  }

  public void deleteComment(int commentId, User user){
    boolean isSucceed = commentRepo.deleteComment(commentId, user);
  }
  
  public List<List<String>> allCommentsByAUser(User user){
    return commentRepo.getAllCommentsByAUser(user);
  }

  // Services for Admin
  public List<List<String>>  allCommentsByUsers(){
    return commentRepo.getAllCommentsByUsers();
  }

  public void deleteUserComment(Object[] o){
    int commentId = (Integer)o[0];
    boolean isSucceed = commentRepo.deleteUserComment(commentId);
  }
}