package gov.smartCityGUI.tax;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 8/31/2023
  * Description: This class will create TaxUser objects to be able to access anyones tax account. This object contains their 
  * userID, first name, last name, email, phone number, and their role code which determines admin or special privileges.
  * It also contains their tax account balance and their total taxes. All info is stored in TaxUsers.txt
*/

public class TaxUser{

	private String userID;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String role;
	public static double balance;
	public static double totalTax;

	/**
      * Default constructor
	**/
	public TaxUser(){
  	}

	/**
      * Constructor to assign only the users taxID
	**/
  	public TaxUser(String userID){
    	this.userID = userID;
  	}

	/**
      * Constructor to assign all fields except balance and totalTax 
	  * @param userID
      * @param firstname
	  * @param lastName
      * @param email
	  * @param phoneNumber
      * @param role
	**/
  	public TaxUser(String userID, String firstName, String lastName, String email, String phoneNumber, String role){
	    this.userID = userID;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.email = email;
	    this.phoneNumber = phoneNumber;
	    this.role = role;
  	}

	/**
      * Method to return user ID
	  * @return the users ID as String
	**/
  	public String getID(){
    	return this.userID;
  	}

	/**
      * Method to return the users first name
	  * @return the users first name
	**/
  	public String getFirst(){
    	return this.firstName;
  	}

	/**
      * Method to return the users last name
	  * @return the users last name 
	**/
  	public String getLast(){
    	return this.lastName;
  	}

	/**
      * Method to return the users email
	  * @return the users email
	**/
  	public String getEmail(){
    	return this.email;
  	}

	/**
      * Method to return the users phone number
	  * @return the users phone number
	**/
  	public String getPhone(){
    	return this.phoneNumber;
  	}

	/**
      * Method to return thr users role
	  * @return the users role as String
	**/
  	public String getRole(){
    	return this.role;
  	}

	/**
      * Method to return the users balance
	  * @return the users balance as double
	**/
 	 public static double getBalance(){
    	return balance;
  	}

	/**
      * Method to return the users total tax
	  * @return the users total tax
	**/
  	public static double getTotalTax(){
    	return totalTax;
  	}

	/**
      * Method to set the users first name
	  * @param firstName
	**/
  	public void setFirst(String firstName){
    	this.firstName = firstName;
  	}

	/**
      * Method to set the users last name
	  * @param lastname
	**/
  	public void setLast(String lastName){
    	this.lastName = lastName;
  	}

	/**
      * Method to set the users email
	  * @param email
	**/
  	public void setEmail(String email){
    	this.email = email;
  	}

	/**
      * Method to set the users phone number
	  * @param phoneNumber
	**/
  	public void setPhone(String phoneNumber){
    	this.phoneNumber = phoneNumber;
  	}

	/**
      * Method to set the users role
	  * @param role
	**/
  	public void setRole(String role){
    	this.role = role;
  	}

	/**
      * Method to set the users Balance
	  * @param balance
	**/
  	public void setBalance(double balance){
	    this.balance = balance;
	    TaxAccount.updateTaxInfo(this.userID, this.lastName, balance, this.totalTax);
  	}

	/**
      * Method to set the users total tax
	  * @param totaltax
	**/
  	public void setTotalTax(double totalTax){
    	this.totalTax = totalTax;
    	TaxAccount.updateTaxInfo(this.userID, this.lastName, this.balance, totalTax);
  	}
} // End class