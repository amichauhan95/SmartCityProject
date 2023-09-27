/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.service;
import gov.smartcityteam2.Tourism.repository.PlaceRepo;
import gov.smartcityteam2.Tourism.model.Place;
import gov.smartcityteam2.Tourism.util.*;
import gov.smartcityteam2.Util;
public class PlaceService{
  private PlaceRepo placeRepo = new PlaceRepo();

  /* Handle Places service */
  
  // Services for Admin
  public void addPlace(Object[] o){
    String name = (String)o[0];
    String des = (String)o[1];
    double rate = (Double)o[2];
    int numberOfReviews = (Integer)o[3];
    boolean isSucceed = placeRepo.addPlace(new Place(name, des, numberOfReviews, rate));

    ValidationUtil.messagePopUp(isSucceed, "New Place has been added", "The place already exists");
  }

  public void updatePlace(Object[] o){
    int placeId = (Integer)o[0];
    String name = (String)o[1];
    String des = (String)o[2];
    int numberOfReviews = (Integer)o[3];
    double rate = (Double)o[4];
    boolean isSucceed = placeRepo.updatePlace(placeId, name, des, numberOfReviews, rate);

    ValidationUtil.messagePopUp(isSucceed, "Place has been updated", "Error, cant update the place");
  }

  public void deletePlace(Object[] o){
    int placeId = (Integer)o[0];
    boolean isSucceed = placeRepo.deletePlace(placeId);
    ValidationUtil.messagePopUp(isSucceed, "Deletion Successfully", "PlaceId is not found");
    // placeRepo.decreseAllPlaceId();
  }

  public void upToDateByAdmin(Object[] o){
    char option = (Character)o[0];
    boolean isSucceed = placeRepo.upToDateByAdmin(option);
    ValidationUtil.messagePopUp(isSucceed, "Up-To-Date Successfully", "Error occurs");
  }

  // Services for User
  public void checkPlace(int placeId){
    Place target = placeRepo.getPlaceById(placeId);
    if(target != null){
      System.out.println(Util.BORADER);
      System.out.print("\t\tPlace Found\n");
      System.out.print(target.getAllInfor());
    } else {
      System.out.println(Util.BORADER);
      System.out.print("\t\tNo record match\n");
      System.out.println(Util.BORADER);
    }
  }

  public boolean showplaceIdAndName(){
    String info = placeRepo.getPlaceIdAndName();
    if(!info.equals("")){
      System.out.println(info + "\n");
      return true;
    } else{
      System.out.println("\n*No places in the database*\n");
      return false;
    }
  }
}