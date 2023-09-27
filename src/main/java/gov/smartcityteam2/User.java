
/*
*User Class
*@author Dylan Moran
*@date 8/29/2023
*/
package gov.smartcityteam2;

public class User{

  public static User user = null;
  
  private String userID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String role;

  public User(){
    
  }
  
  public User(String userID, String firstName, String lastName, String email, String phoneNumber, String role){
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
  }

  public static synchronized void initiliazeUser(String userID, String firstName, String lastName, String email, String phoneNumber, String role){
    
    if(user == null){
      user = new User(userID, firstName, lastName, email, phoneNumber, role);
    }
  }
  
public static synchronized User getInstance(){
  return user;
}
  
  public String getID(){
    return this.userID;
  }

  public String getFirst(){
    return this.firstName;
  }

  public String getLast(){
    return this.lastName;
  }

  public String getEmail(){
    return this.email;
  }

  public String getPhone(){
    return this.phoneNumber;
  }

  public String getRole(){
    return this.role;
  }

  public boolean isAdmin(){
    if(this.role.equals("9850")){
      return true;
    } else {
      return false;
    }
  }
  
}