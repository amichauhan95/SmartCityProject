/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.service;


import java.util.Collections;
import java.util.List;
import gov.smartcityteam2.Tourism.repository.PlaceRepo;
import gov.smartcityteam2.Tourism.model.Place;
import gov.smartcityteam2.Tourism.util.MenuUtil;

public class PlaceFilter{
  private PlaceRepo placeRepo = new PlaceRepo();

  /* Handle Sorting service */
  
  public void sortByDefault(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Sort By Default");
    List<Place> places = placeRepo.getPlaceList();

    Collections.sort(places);
    
    for(Place p: places){
      System.out.print(p.toString());
    }
  }  
  
  public void sortByRate(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Sort By Rate");
    List<Place> places = placeRepo.getPlaceList();

    Collections.sort(places, new Place.PlaceByRate());
    
    for(Place p: places){
      System.out.print(p.toString());
    }
  }

  public void sortByReviews(){
    MenuUtil.setHeader("-", 35);
    MenuUtil.setTitle("Sort By Number of Reviews");
    List<Place> places = placeRepo.getPlaceList();

    Collections.sort(places, new Place.PlaceByReview());
    
    for(Place p: places){
      System.out.print(p.toString());
    }
  }

}