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
  
  /**
    Add a new comment into db, consume by user-level service
    @param o, is the object arr contains comment data and ready to added into db
    user, the comment is made by the user
  **/
  public void addComment(Object[] o, User user){
    String comment = (String)o[1];
    double rate = Double.parseDouble((String)o[0]);
    int placeId = (Integer)o[2];
    commentRepo.addComment(placeId, new UserComments(user, comment, rate));
    

  }

  /**
    update a exiting comment, consume by user-level service
    @param o, is the object arr contains {commentId, comment, rate} and ready to updated in db
    user, the comment is modifiy by the user
  **/ 
  public void updateComment(Object[] o, User user){
    int commentId = (Integer)o[0];
    String comment = (String)o[1];
    double rate = Double.parseDouble((String)o[2]);
    commentRepo.updateComment(commentId, user, comment, rate);
  }
  
  /**
    delete a exiting comment, consume by user-level service
    @param commentId, for which comment need to be deleted
    user, the comment was made by the user
  **/ 
  public void deleteComment(int commentId, User user){
    commentRepo.deleteComment(commentId, user);
  }

  /**
    Show all comments made by given user
    @param user, own all comments made by himself/herself
    @return list of comments object which contains {comment, date, rate}
  **/ 
  public List<List<String>> allCommentsByAUser(User user){
    return commentRepo.getAllCommentsByAUser(user);
  }

  // Services for Admin

  /**
    Show all comments made by existing all users
    @return list of comments object which contains {comment, date, rate, user}
  **/ 
  public List<List<String>>  allCommentsByUsers(){
    return commentRepo.getAllCommentsByUsers();
  }

  /**
    Delete a existng comment, consume by admin-level service
    @param commentId, for which comment need to be deleted
  **/
  public void deleteComment(int commentId){
    commentRepo.deleteComment(commentId);
  }

  /**
    Delete a existng comment, consume by admin-level service
    @param o, is the object array that contains commentId
  **/
  public void deleteUserComment(Object[] o){
    int commentId = (Integer)o[0];
    commentRepo.deleteUserComment(commentId);
  }
}