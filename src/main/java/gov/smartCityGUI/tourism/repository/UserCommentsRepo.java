package gov.smartCityGUI.tourism.repository;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.tourism.model.UserComments;

public class UserCommentsRepo{
  private static List<UserComments> userComments = new ArrayList<UserComments>();
  private static PlaceRepo placeRepo = new PlaceRepo();


  /* Read UserComments.json data */
  public void readUserComments(String filePath){
    try{
      JSONParser parser = new JSONParser();
      JSONArray jarr = (JSONArray) parser.parse(new FileReader(filePath));
      for (Object c : jarr){
        JSONObject data = (JSONObject) c;
    
        // int commentId = (int)(long) data.get("commentId");
        String placeName = (String) data.get("placeName");
  
        JSONArray user = (JSONArray) data.get("user");
        User userinfo = new User();
        for (Object tempUser : user){
          JSONObject temp = (JSONObject) tempUser;
          
          String userId = (String) temp.get("userId");
          String first = (String) temp.get("first");
          String last = (String) temp.get("last");
          String email = (String) temp.get("email");
          String number = (String) temp.get("number");
          String role = (String) temp.get("role");
          userinfo = new User( userId,  first,  last,  email,  number,  role);
        }
        String date = (String) data.get("date");
        String comments = (String) data.get("comments");
        double rate = (double) data.get("rate");

        UserComments userComment = new UserComments( placeName,  userinfo,  date,  comments,  rate);
        userComments.add(userComment);
        upToDate(userComment, 1);

      } 
    }catch(Exception e ){
      e.printStackTrace();
    }
  }
  
  /* up to date PlaceRepo */
  public static void upToDate(UserComments c, int option){
    placeRepo.upToDateComments(c, option);
  }
  
  // User 
  public boolean addComment(int placeId, UserComments c){
    // System.out.println("Repo layer...");
    if(placeRepo.placeIdExist(placeId)){
      c.setPlaceId(placeId);
      c.setPlaceName(placeRepo.findPlaceNameById(placeId));
      userComments.add(c);
      upToDate(c, 1);
      return true;
    }
    return false;
  }

  public boolean deleteComment(int commentId, User user){
    for(UserComments c: userComments){
      System.out.println("comment id: " + commentId + "Current id: "+ c.getCommentId() + "value: " + (c.getCommentId() == commentId));
      if(c.getCommentId() == commentId) {
        placeRepo.upToDateComments(c, 2);
        userComments.remove(c);
        return true;
      }
    }
    
    return false;
  }

  public boolean updateComment(int commentId, User user, String comment, double rate){
    if(commentBelongsToUser(commentId, user)){
      for(UserComments temp: userComments){
        if(temp.getCommentId() == commentId){
          placeRepo.upToDateComments(new UserComments(commentId, user, comment, rate), 3);
          temp.updateUserComments(comment, rate);
          return true;
        }
      }
    }
    return false;
  }

  public List<List<String>> getAllCommentsByAUser(User user){
    // return commentId, String comment, string img
    List<List<String>> data = new ArrayList<List<String>>();
    
    for(UserComments c: userComments){
      List<String> temp = new ArrayList<String>();
      if (user == c.getUser()) {
        temp.add(String.valueOf(c.getCommentId()));
        temp.add(c.toStringV1());
        String image = placeRepo.findImageByPlaceId(c.getPlaceId());
        temp.add(image);
        data.add(temp);
      }
    }
    return data;
  }

  public List<UserComments> getCommentsList(User user){
    List<UserComments> data = new ArrayList<>();
    for(UserComments c : userComments){
      if(c.getUser() == user) data.add(c);
    }
    return data;
  }
  
  public List<UserComments> searchKeyword(String keyword, User user){
    List<UserComments> data = new ArrayList<UserComments>();
    
    if(keyword.equals("")) {
      
      for(UserComments c : userComments){
        if(c.getUser() == user) data.add(c);
      }
      return data;
    }

    for(UserComments c : userComments){
      String temp = c.getComments().toLowerCase();
      if(temp.contains(keyword) && c.getUser() == user) data.add(c);
    }
    return data;
  }
  // Admin 
  public List<UserComments> getCommentsList(){
    return userComments;
  }
  
  public boolean deleteComment(int commentId){
    System.out.println(String.format("UserC Repo, admin delete c, target id: %s \n\n", commentId));
    for(UserComments c: userComments){
      System.out.println("current Id: " + c.getCommentId() + "\n");
      if(c.getCommentId() == commentId) {
        placeRepo.upToDateComments(c, 2);
        userComments.remove(c);
        return true;
      }
    }
    return false;
  }
  
  public List<UserComments> searchKeyword(String keyword){
    if(keyword.equals("")) return userComments;
    List<UserComments> data = new ArrayList<UserComments>();
    for(UserComments c : userComments){
      String temp = c.getComments().toLowerCase();
      if(temp.contains(keyword)) data.add(c);
      System.out.println(String.format("Original: %s, keyword: %s, Value: %s", temp, keyword, String.valueOf(temp.contains(keyword))));
    }
    return data;
  }
  
  public boolean deleteUserComment(int commentId){
    for(UserComments c: userComments){
      if(c.getCommentId() == commentId) {
        placeRepo.upToDateComments(c, 2);
        userComments.remove(c);
        return true;
      }
    }
    return false;
  }

  public void deleteCommentByPlaceName(String placeName){
    List<UserComments> deleteComments = new ArrayList();
    for(UserComments c: userComments){
      if(c.getPlaceName().equals(placeName)) {
        deleteComments.add(c);
      }
    }
    userComments.removeAll(deleteComments);
  }

  public List<List<String>> getAllCommentsByUsers(){

    List<List<String>> data = new ArrayList<List<String>>();
    
    for(UserComments c: userComments){
      List<String> temp = new ArrayList<String>();
      String image = placeRepo.findImageByPlaceName(c.getPlaceName());
      temp.add(image);
      temp.add(c.toStringV2());
      temp.add(String.valueOf(c.getCommentId()));
      data.add(temp);
    }

    return data;
  }

  public boolean commentBelongsToUser(int commentId, User user){
    for(UserComments c: userComments){
      if(c.getCommentId() == commentId && c.getUser() == user) return true;
    }
    return false;
  }
  
}

// add comments: 