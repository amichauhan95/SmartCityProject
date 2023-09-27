/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
// package gov.smartcityteam2.Tourism.service;

package gov.smartCityGUI.tourism.service;
import java.util.Collections;
import java.util.List;
import gov.smartCityGUI.tourism.repository.*;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.tourism.model.Place;
import gov.smartCityGUI.tourism.model.UserComments;


public class CommentFilter{
  private UserCommentsRepo commentRepo = new UserCommentsRepo();
  private PlaceRepo placeRepo = new PlaceRepo();

  /* Handle Sorting service */

  public String[][] searchKeyword(String keyword){
   
    List<Place> places = placeRepo.searchKeyword(keyword);

    int row = places.size();
    int col = 3;
    String [][] list = new String [row][col];
    for(int i = 0; i < row; i ++){
      list[i][0] = places.get(i).getImage();
      list[i][1] = places.get(i).toStringV1();
      list[i][2] = String.valueOf(places.get(i).getPlaceId());
    }
    return list;
  }

  // Consume by a Admin
  public String[][] sortAlgo(int option, String keyword){

    List<UserComments> comments;
    
    if(keyword == null) comments = commentRepo.getCommentsList();
    else comments = commentRepo.searchKeyword(keyword);

    if(option == 1) Collections.sort(comments, new UserComments.CommentByEarliest());
    else if (option == 2) Collections.sort(comments, new UserComments.CommentByLatest());
    
    return formatData(comments);
    
  }

  // Consume by a user
  public String[][] sortAlgo(int option, String keyword, User user){

    List<UserComments> comments;

    /* Getting data from commentRepo*/
    if(keyword == null) comments = commentRepo.getCommentsList(user);
    else comments = commentRepo.searchKeyword(keyword, user);

    /* Sorting algo applying by given option*/
    if(option == 1) Collections.sort(comments, new UserComments.CommentByEarliest());
    else if (option == 2) Collections.sort(comments, new UserComments.CommentByLatest());

    return formatData(comments);
    
  }
  
  public String [][] formatData(List<UserComments> comments){
    int row = comments.size();
    int col = 3;
    String [][] list = new String [row][col];
    for(int i = 0; i < row; i ++){
      String img = comments.get(i).getPlaceName();
      list[i][0] = placeRepo.findImageByPlaceName(img);
      list[i][1] = comments.get(i).toStringV2();
      list[i][2] = String.valueOf(comments.get(i).getCommentId());
    }
    return list;
  }
}