/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.controller.page;
import gov.smartcityteam2.Util;
import gov.smartcityteam2.Tourism.util.MenuUtil;
import gov.smartcityteam2.User;
public class UserPage extends Menu{

  public UserPage(User user){
    super(user);
  }
  /* User home page */
  public void mainPage(){
    while(true){
      String[] optionsList ={
      " * Place * ",
      " * Comments *",
      // " * Events *",
    };
      int option = MenuUtil.setOptions("Tourism System *Role = User*",optionsList, "-", 35);
      switch(option){
        case 1:
          placePage();
          break;   
          
        case 2:
          commentPage();
          break;

        // case 3:
        //   eventPage();
        //   break;
          
        case 0:
          return;

        default:
          Util.printMenuError();
      }
    }
  }

  /* User Place page */
  public void placePage(){
    while(true){
      String[] optionsList ={
      "Show the places by name in Albany",
      "Show the places by Rate",
      "Show the places by Reviews",
      "Check out the place details (W/UserComments) by placeId",
      };
      int option = MenuUtil.setOptions("Place *Role = User*",optionsList, "-", 35);  
      switch(option){
        case 1:
          getPlaceFilter().sortByDefault();
          break;   
          
        case 2:
          getPlaceFilter().sortByRate();
          break;

        case 3:
          getPlaceFilter().sortByReviews();
          break;

        case 4:
          Object [] o = checkPlaceMenu();
          if(o != null) getPlaceService().checkPlace((Integer)o[0]);
          break;
          
        case 0:
          return;

        default:
          Util.printMenuError();
      }
    }
    
  }

  /* User Comment page */
  public void commentPage(){
    while(true){
      String[] optionsList ={
        "Add new comment to a place",
        "Update a comment",
        "Delete a comment",
      };
      int option = MenuUtil.setOptions("Comments *Role = User*",optionsList, "-", 35);
      switch (option){
        case 1:
          Object[] o = addCommentMenu();
          if(o != null) getCommentsService().addComment(o, getUser());
          break;
        case 2:
          /* Redirect to updateCommentMenu and get user's update info*/
          o = updateCommentMenu();
          // If no record found then return
          if(o != null) getCommentsService().updateComment(o, getUser());  
          break;
        case 3:
          o = deleteCommentMenu();
          if(o != null) getCommentsService().deleteComment(o, getUser());  
          break;
        case 0:
          return;
        default:
          Util.printMenuError();
      }
    }
  }


  /* Check Place Menu: Get user input {placeId} for checking a place Info */
  public Object[] checkPlaceMenu(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Check Place");
    if(getPlaceService().showplaceIdAndName()){
      String[] inputList = {
        "Please enter placeId",
      };
      String[] datatype = {
        "int",
      };
      return MenuUtil.inputCollector("", "", 35, inputList, datatype); 
    } else {
      return null;
    }    
  }

  /* Add Comment Menu: Get user inputs {placeid, comment, rate} for adding a comment*/
  public Object[] addCommentMenu(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Add new Comment");
    if(getPlaceService().showplaceIdAndName()){
      String[] inputList = {
        "Please enter placeId",
        "Please enter comment",
        "Please enter rate"
      };
      String[] datatype = {
        "int",
        "String",
        "double"
      };
      return MenuUtil.inputCollector("", "", 35, inputList, datatype); 
    } else {
      return null;
    }
       
  }

  /* Update Comment Menu: Get user input {comment-id, comment, rate} to update an exsting comment by current user */
  public Object[] updateCommentMenu(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Update Comment");
    if(getCommentsService().allCommentsByAUser(super.getUser())){
      String[] inputList = {
        "Please enter comment-Id",
        "Please enter comment",
        "Please enter rate"
      };
      String[] datatype = {
        "int",
        "String",
        "double"
      };
      return MenuUtil.inputCollector("", "", 35, inputList, datatype);     
    } else {
      return null;
    }
  }

  /* Delete Comment Menu: Get user input {commentId} to delete a existing comment made by current user */
  public Object[] deleteCommentMenu(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Delete Comment");
    if(getCommentsService().allCommentsByAUser(super.getUser())){
      String[] inputList = {
        "Please enter commentId",
      };
      String[] datatype = {
        "int",
      };
      return MenuUtil.inputCollector("", "", 35, inputList, datatype);         
    } else {
      return null;
    }
 
  }
}