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
import gov.smartCityGUI.tourism.repository.PlaceRepo;
import gov.smartCityGUI.tourism.model.Place;


public class PlaceFilter{
  private PlaceRepo placeRepo = new PlaceRepo();

  /* Handle Sorting service */
  
  public String[][] sortByDefault(){
    List<Place> places = placeRepo.getPlaceList();

    Collections.sort(places);

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
  
  public String[][] sortByRate(){
    List<Place> places = placeRepo.getPlaceList();

    Collections.sort(places, new Place.PlaceByRate());
    
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

  public String[][] sortByReviews(){
   
    List<Place> places = placeRepo.getPlaceList();

    Collections.sort(places, new Place.PlaceByReview());
    
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

  public String[][] sortAlgo(int option, String keyword){

    List<Place> places;
    
    if(keyword == null) places = placeRepo.getPlaceList();
    else places = placeRepo.searchKeyword(keyword);

    if(option == 1) Collections.sort(places);
    else if (option == 2) Collections.sort(places, new Place.PlaceByRate());
    else if (option == 3) Collections.sort(places, new Place.PlaceByReview());

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
  
}