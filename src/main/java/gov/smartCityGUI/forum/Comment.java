package gov.smartCityGUI.forum;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 9/5/2023
  * Description: This class will create a Comment object to be added in a text file to specified messages.
**/

public class Comment{

	//Resources
  	private String author;
  	private String body;

	/**
 	  * Default constructor
	**/
  	public Comment(){
  	}

	/**
 	  * Constructor to assign the author and the body of the comment
	  * @param author the author of the comment
      * @param body the comment text
	**/
 	public Comment(String author, String body){
    	this.author = author;
    	this.body = body;
  	}

	/**
 	  * Method to return the author
	  * @return the author of the comment
	**/
  	public String getAuthor(){
    	return this.author;
  	}

	/**
 	  * Method to set the author
	  * @param author the new author
	**/
  	public void setAuthor(String author){
    	this.author = author;
  	}

	/**
 	  * Method to return the body of the comment
	  * @return the body of the comment
	**/
  	public String getBody(){
    	return this.body;
  	}

	/**
 	  * Method to set the body 
	  * @param body the body of the comment
	**/
  	public void setBody(String body){
    	this.body = body;
  	}
} // End class