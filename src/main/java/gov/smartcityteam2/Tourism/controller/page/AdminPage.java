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
public class AdminPage extends Menu{

  public AdminPage(User user){
    super(user);
  }
  /* Admin home page*/
  public void mainPage(){
    while(true){
      String[] optionsList ={
      "Place Management",
      "Comments Management",
      // "Events Management",
    };
      int option = MenuUtil.setOptions("Tourism System *Admin*",optionsList, "-", 25);
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

  /*Admin Place Management page*/
  public void placePage(){
    while(true){
      String[] optionsList ={
      "Show all places",
      "Add a new place",
      "Update a place info",
      "Delete a place",
      "[Up-To-Date]"
      };
      int option = MenuUtil.setOptions("Place Management",optionsList, "-", 25);  
      switch(option){
        case 1:
          getPlaceFilter().sortByDefault();
          break;   
          
        case 2:
          Object[] o = addPlaceMenu();
          if(o != null) getPlaceService().addPlace(o);
          break;

        case 3:
          o = updatePlaceMenu();
          if(o != null) getPlaceService().updatePlace(o);
          break;

        case 4:
          o = deletePlaceMenu();
          if(o != null) getPlaceService().deletePlace(o);
          break;

        case 5:
          o = upToDateMenu();
          if(o != null) getPlaceService().upToDateByAdmin(o);
          break;
          
        case 0:
          return;

        default:
          Util.printMenuError();
      }
    }
    
  }

  /*Admin Comments Management page*/
  public void commentPage(){
    String[] optionsList ={
      "Show all User Comments",
      "Delete a user comment",
    };
    int option = MenuUtil.setOptions("Comments Management*",optionsList, "-", 25);
    switch (option){
      case 1:
        allUserCommentPage();
        break;
      case 2:
        Object [] o = deleteUserCommentMenu();
        if(o != null) getCommentsService().deleteUserComment(o);
        break;
      case 0:
        return;
      default:
        Util.printMenuError();
    }
  }

  public Object[] upToDateMenu(){
    String[] inputList = {
      "Do you want to up-to-date the *place* DataBase? Y/N"
    };
    String[] datatype = {
      "char"
    };
    return MenuUtil.inputCollector("Add a Place", "-", 35, inputList, datatype); 
  }
  
  /* Place Management - sub page */
  public Object[] addPlaceMenu(){
    String[] inputList = {
      "Please enter place name",
      "Please enter place Description",
      "Please enter rate",
      "Pleaes enter number of reviews"
    };
    String[] datatype = {
      "String",
      "String",
      "double",
      "int"
    };
    return MenuUtil.inputCollector("Add a Place", "-", 35, inputList, datatype); 
  }

  /* Place Management - sub page */
  public Object[] updatePlaceMenu(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Update Place Info");
    if(getPlaceService().showplaceIdAndName()){
      String[] inputList = {
        "Please enter place-Id",
        "Please enter Place-name or *ENTER* to skip",
        "Please enter place-Des or *ENTER* to skip",
        "Please enter place-Number-Reviews or *-1* to skip",
        "Please enter place-Rate or *-1* to skip",    
      };
      String[] datatype = {
        "int",
        "String",
        "String",
        "int",
        "double"
      };
      return MenuUtil.inputCollector("", "", 35, inputList, datatype);     
    } else {
      return null;
    }
  }

  /* Place Management - sub page */
  public Object[] deletePlaceMenu(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Delete a Place");
    if(getPlaceService().showplaceIdAndName()){
      String[] inputList = {
        "Please enter place-Id",
      };
      String[] datatype = {
        "int",
      };
      return MenuUtil.inputCollector("", "", 35, inputList, datatype);     
    } else {
      return null;
    }
  }

  /* Comment Management - sub page */
  public Object[] deleteUserCommentMenu(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Delete a Comment");
    if(getCommentsService().allCommentsByUsers()){
      String[] inputList = {
        "Please enter place-Id",
      };
      String[] datatype = {
        "int",
      };
      return MenuUtil.inputCollector("", "", 35, inputList, datatype);     
    } else {
      return null;
    }
  }

  /* Comment Management - sub page */
  public void allUserCommentPage(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("All User Comments");
    getCommentsService().allCommentsByUsers();
  }
  
}