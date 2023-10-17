/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/


package gov.smartCityGUI.tourism.repository;
import java.util.List;
import java.util.ArrayList;
import java.io.FileNotFoundException; 
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileWriter;
import gov.smartCityGUI.admin.models.User;
import gov.smartCityGUI.tourism.model.*;


public class PlaceRepo{
  private static List<Place> places = new ArrayList<Place>();
  private static UserCommentsRepo commentRepo = new UserCommentsRepo();

  // a path where place data stored
  final static String placeData = "src/main/java/gov/smartCityGUI/tourism/static/place.json";
  final static String commentData = "src/main/java/gov/smartCityGUI/tourism/static/userComments.json";

  static {
    readPlaceFile(placeData);
    commentRepo.readUserComments(commentData);
  }
  
  // Data-loadding
  public static void readPlaceFile(String filePath){
    try{
      JSONParser parser = new JSONParser();
      JSONArray jarr = (JSONArray) parser.parse(new FileReader(filePath));
      for (Object place : jarr){
        JSONObject data = (JSONObject) place;
        String image = (String) data.get("image");
        String name = (String) data.get("name");
        String placeDes = (String) data.get("placeDes");
        double rate = (double) data.get("rate");
        int numOfReviews = (int)(long) data.get("numOfReviews");
        places.add(new Place(image, name, placeDes, numOfReviews, rate));
    
      }
    }catch(Exception e ){
      e.printStackTrace();
    }
  }

  /**
    Search a place by given place id
    @param placeId 
    @return placeName 
  **/
  public String findPlaceNameById(int placeId){
    for(Place p: places){
      if(p.getPlaceId() == placeId) return p.getName();
    }
    return "";
  }

  /**
    Search a place image name by given place name
    @param placeName 
    @return image name 
  **/
  public String findImageByPlaceName(String placeName){
    for(Place p : places){

      if(p.getName().equals(placeName)) return p.getImage();
    }
    return "";
  }

  /**
    Search a place image name by given place id
    @param place id
    @return image name 
  **/
  public String findImageByPlaceId(int placeId){
    for(Place p : places){
      if(p.getPlaceId() == placeId) return p.getImage();
    }
    return "";
  }
  
  /* User Comments up-to-date */
  /**
    update-to-date a comment in comment repo
    @param c, a comments need to be CURD in comment repo
    option, for which CURD
  **/
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
  /**
    Retrieve place object by given place id
    @param placeId
    @return place object
  **/
  public Place getPlaceById(int placeId){
    for(Place target: places){
      if(target.getPlaceId() == placeId){
        return target;
      }
    }
    return null;
  }

  /**
    Retrieve a list of place object
  **/
  public List<Place> getPlaceList (){
    return places;
  }

  /**
    increase review number by one
    @param placeid, for which place 
  **/
  public void increaseReview(int placeId){
    for(Place target: places){
      if(target.getPlaceId() == placeId){
        target.setReviews(target.getReviews() + 1);
      }
    }
  }

  /**
    increase review number by one
    @param placeid, for which place 
  **/
  public void updateRate(int placeId, double rate){
    for(Place target: places){
      if(target.getPlaceId() == placeId){
        target.setReviews(target.getReviews() + 1);
        target.setRate((target.getRate() + rate) / target.getReviews());
      }
    }
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
  public List<Place> searchKeyword(String keyword){
    if(keyword.equals("")) return places;
    List<Place> data = new ArrayList<Place>();
    for(Place p : places){
      keyword = keyword.toLowerCase();
      String temp = p.getName().toLowerCase();
      if(temp.contains(keyword)) data.add(p);
      System.out.println(String.format("Original: %s, keyword: %s, Value: %s", temp, keyword, String.valueOf(temp.contains(keyword))));
    }
    return data;
  }
  
  public boolean addPlace(Place p){
    for(Place temp: places){
      if(temp.getName().equals(p.getName())) return false;
    }
    places.add(p);
    return true;
  }

  public boolean updatePlace(String placeId, String name, String placeDes, String numOfReviews, String rate, String img){
    for(Place temp: places){
      if(temp.getPlaceId() == Integer.parseInt(placeId)){
        if(!name.equals("")) temp.setName(name);
        if(!placeDes.equals("")) temp.setPlaceDes(placeDes);
        if(!numOfReviews.equals("")) temp.setReviews(Integer.parseInt(numOfReviews));
        if(!rate.equals("")) temp.setRate(Double.parseDouble(rate));
        if(!img.equals("")) temp.setImage(img);
        return true;
      }
    }
    return false;
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

  // check if placeId exists or not
  public boolean placeIdExist(int placeId){
    for(Place p: places){
      if(p.getPlaceId() == placeId) return true;
    }
    return false;
  }
}