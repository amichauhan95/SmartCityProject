package gov.smartCityGUI.admin.models;

/**
*User Class
*@author Dylan Moran
*@date 8/29/2023
*/


public class User{

  public static User user = null;

  private String userID;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private String role;

  /**
    * This method represents the default constructor
  */
  public User(){
    
  }

  /**
    * Sets the parameters to the current instance
    * @param userID The user id
    * @param firstName   The last name
    * @param lastName The last name
    * @param email The email
    * @param phoneNumber The phone number 
    * @param role The role
  **/
  public User(String userID, String firstName, String lastName, String email, String phoneNumber, String role){
    this.userID = userID;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.role = role;
  }

	/**
 	  * Singleton use to initialize a User instance
 	  */
	public static synchronized void initiliazeUser(String userID, String firstName, String lastName, String email, String phoneNumber, String role){
    	if(user == null){
      		user = new User(userID, firstName, lastName, email, phoneNumber, role);
    	}
  	}

	/**
 	* Singleton use to get the singleton user instance
 	*/
	public static synchronized User getInstance(){
 		 return user;
	}

  /**
    * Returns the user's id
    * @return userID
  **/
  public String getID(){
    return this.userID;
  }

  /**
    * Returns the user's first name
    * @return fristName
  **/
  public String getFirst(){
    return this.firstName;
  }

  /**
    * Returns the user's last name
    * @return lastName
  **/
  public String getLast(){
    return this.lastName;
  }

  /**
    * Returns the user's email
    * @return email
  **/
  public String getEmail(){
    return this.email;
  }

  /**
    * Returns the user's phone number
    * @return phoneNumber
  **/
  public String getPhone(){
    return this.phoneNumber;
  }

  /**
    * Returns the user's role
    * @return role
  **/
  public String getRole(){
    return this.role;
  }

  /**
    * Returns true or false based on the condition
    * @return boolean
  **/
  public boolean isAdmin(){
    if(this.role.equals("9850")){
      return true;
    } else {
      return false;
    }
  }
  
}