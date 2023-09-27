/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.repository;

import gov.smartcityteam2.Tourism.model.*;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import gov.smartcityteam2.User;
import java.io.FileWriter;

public class PlaceRepo{
  private static List<Place> places = new ArrayList<Place>();
  private static UserCommentsRepo commentRepo = new UserCommentsRepo();

  static {
    readPlaceFile("src/main/java/gov/smartcityteam2/Tourism/static/place.json");
    commentRepo.readUserComments("src/main/java/gov/smartcityteam2/Tourism/static/userComments.json");
    // readUserComments("Tourism/userComments.json");
    // readPlaceFile("src/main/java/gov/smartcityteam2/Tourism/static/place.json");
    // readUserComments("src/main/java/gov/smartcityteam2/Tourism/static/userComments.json");
  }
  
  // Data-loadding
  public static void readPlaceFile(String filePath){
    try{
      JSONParser parser = new JSONParser();
      JSONArray jarr = (JSONArray) parser.parse(new FileReader(filePath));
      for (Object place : jarr){
        JSONObject data = (JSONObject) place;
    
        String name = (String) data.get("name");
        String placeDes = (String) data.get("placeDes");
        double rate = (double) data.get("rate");
        int numOfReviews = (int)(long) data.get("numOfReviews");
        places.add(new Place(name, placeDes, numOfReviews, rate));
    
      }
    }catch(Exception e ){
      e.printStackTrace();
    }
  }

  public String findPlaceNameById(int placeId){
    for(Place p: places){
      if(p.getPlaceId() == placeId) return p.getName();
    }
    return "";
  }
  
  /* User Comments up-to-date */
  public static void upToDateComments(UserComments c, int option){
    switch(option){
      /* Add new a Comment */
      case 1:
        for(Place p: places){
          if(p.getName().equals(c.getPlaceName()) || p.getPlaceId() == c.getPlaceId()){

            // Rate updated
            p.setRate((p.getRate() * p.getReviews() + c.getRate()) / (p.getReviews() + 1));
            // Reivew + 1
            p.setReviews(p.getReviews() + 1);

            p.addComments(c);
            return;
          }
        }
        break;
      /* Delete a comment */
      case 2:
        for(Place p: places){
          if(p.getName().equals(c.getPlaceName())){
            if(p.getReviews() - 1 == 0) {
              p.setRate(0.0);
              p.setReviews(p.getReviews() - 1);
            } else {
              p.setRate( (p.getRate() * p.getReviews() - c.getRate()) / (p.getReviews() - 1) );
              p.setReviews(p.getReviews() - 1);
            }
            p.getUserComments().remove(c);
            return;
          }
        }
        break;
      /* Update a comment */
      case 3:
        for(Place p: places){
          for(UserComments temp: p.getUserComments()){
            if(temp.getCommentId() == c.getCommentId()){
              p.setRate((p.getRate() * p.getReviews() + c.getRate() - temp.getRate()) / p.getReviews() );
              temp = c;
              return ;
            }
          }
        }
        break;
    }
  }
  
  // Place
  public Place getPlaceById(int placeId){
    for(Place target: places){
      if(target.getPlaceId() == placeId){
        return target;
      }
    }
    return null;
  }

  public List<Place> getPlaceList (){
    return places;
  }

  public void increaseReview(int placeId){
    for(Place target: places){
      if(target.getPlaceId() == placeId){
        target.setReviews(target.getReviews() + 1);
      }
    }
  }

  public void updateRate(int placeId, double rate){
    for(Place target: places){
      if(target.getPlaceId() == placeId){
        target.setReviews(target.getReviews() + 1);
        target.setRate((target.getRate() + rate) / target.getReviews());
      }
    }
  }

  public String getPlaceIdAndName(){
    String str = "";
    for(Place p: places){
      str += p.toStringIdAndName();
    }
    return str;
  }

  public boolean deletePlace(int placeId){
    for(Place p: places){
      if(p.getPlaceId() == placeId){
        places.remove(p);
        deleteCommentByPlaceName(p.getName());
        return true;
      }
    }
    return false;
  }

  public void deleteCommentByPlaceName(String placeName){
    System.out.println("P Repo, place Name" + placeName);
    commentRepo.deleteCommentByPlaceName(placeName);
  }
  
  public void decreseAllPlaceId(){
    for(Place p: places){
      p.setPlaceId(p.getPlaceId() - 1);
    }
    places.get(0).setCount(places.get(0).getCount() - 1);
  }
  // Admin - Place
  public boolean addPlace(Place p){
    for(Place temp: places){
      if(temp.getName().equals(p.getName())) return false;
    }
    places.add(p);
    return true;
  }

  public boolean updatePlace(int placeId, String name, String placeDes, int numOfReviews, double rate){
    for(Place temp: places){
      if(temp.getPlaceId() == placeId){
        if(name != "") temp.setName(name);
        if(placeDes != "") temp.setPlaceDes(placeDes);
        if(numOfReviews != -1) temp.setReviews(numOfReviews);
        if(rate != -1) temp.setRate(rate);
        return true;
      }
    }
    return false;
  }

  public boolean upToDateByAdmin(Character option){
    option = Character.toLowerCase(option);
    if(option.equals('y')){
      writePlaceToJson();
      return true;
    } else return false;
  }

  public void writePlaceToJson(){
    try {
      JSONArray placesArr = new JSONArray();
      for(Place p : places){
        JSONObject place = new JSONObject();
        String name = "name";
        String placeDes = "placeDes";
        String rate = "rate";
        String numOfReviews = "numOfReviews";
        
        place.put(name, p.getName());
        place.put(placeDes, p.getPlaceDes());
        place.put(rate, p.getRate());
        place.put(numOfReviews, p.getReviews());
        placesArr.add(place);
      }
      
      // write JSON to file
    	FileWriter file = new FileWriter("placeAdmin.json");
    	file.write(placesArr.toString());
    	file.flush();
    	file.close();
    	
    } catch (IOException e) {
    	e.printStackTrace();
    }
  }

  // To-do
  public void decreseAllCommentId(){
    
  }

  // check if placeId exists or not
  public boolean placeIdExist(int placeId){
    for(Place p: places){
      if(p.getPlaceId() == placeId) return true;
    }
    return false;
  }
}