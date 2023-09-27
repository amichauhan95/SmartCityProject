/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/

package gov.smartCityGUI.tourism.service;

import gov.smartCityGUI.tourism.repository.PlaceRepo;
import gov.smartCityGUI.tourism.model.Place;

public class PlaceService{
  private PlaceRepo placeRepo = new PlaceRepo();

  /* Handle Places service */
  
  // Services for Admin
  public void addPlace(Object[] o){
    String name = (String)o[0];
    String des = (String)o[1];
    int numberOfReviews = Integer.parseInt((String)o[2]);
    double rate = Double.parseDouble((String)o[3]);
    String img = (String)o[4];
    boolean isSucceed = placeRepo.addPlace(new Place(name, des, numberOfReviews, rate, img));
  }

  public void updatePlace(Object[] o){
    String placeId = (String)o[0];
    String name = (String)o[1];
    String des = (String)o[2];
    String numberOfReviews = (String)o[3];
    String rate = (String)o[4];
    String img = (String)o[5];
    boolean isSucceed = placeRepo.updatePlace(placeId, name, des, numberOfReviews, rate, img);

  }

  public void deletePlace(int placeId){
    boolean isSucceed = placeRepo.deletePlace(placeId);

  }

  // Services for User
  public String[] checkPlace(int placeId){
    Place target = placeRepo.getPlaceById(placeId);
    String[] list = new String[4];
    list[0] = target.getImage();
    list[1] = target.toString();
    list[2] = target.getAllInfor();
    list[3] = String.valueOf(target.getPlaceId());
      
    return list;
  }
}