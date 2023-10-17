package gov.smartCityGUI.forum;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 9/5/2023
  * Description: This class is used to create a Message object that contains an author, a body, a date, and comments.
**/

import java.util.*;
import java.text.SimpleDateFormat;

public class Message{

	//Resources
  	private String author;
  	private String body;
  	private String date;
  	private Comment comment;

	/**
      * Default constructor
	**/
  	public Message(){
  	}

	/**
      * Constructor to assign the author, body, and date
	  * @param author the author of the message
      * @param body the body of the message
	**/
  	public Message(String author, String body){
	    SimpleDateFormat dateFormatter = new SimpleDateFormat("MMMMMMMM dd yyyy ");
	    SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm a");
	    TimeZone zone = TimeZone.getTimeZone("EST");   
	    timeFormatter.setTimeZone(zone);
	    String time = timeFormatter.format(new Date());
	    int hour = Integer.parseInt(time.substring(0,2));
	    hour += 1;
	    time = hour + time.substring(2);
	    this.date = dateFormatter.format(new Date()) + time;
	    this.author = author;
	    this.body = body;
  	}

	/**
      * Constructor to only assign an author and body
	  * @param code the message code identifier
      * @param author the author of the message
	  * @param body the body of the message
	**/
  	public Message(int code, String author, String body){
	    this.author = author;
	    this.body = body;
  	}

	/**
      * Method to return the author of the message
	  * @return the author of the message
	**/
  	public String getAuthor(){
    	return this.author;
  	}

	/**
      * Method to set the author of the message 
	**/
  	public void setAuthor(String author){
    	this.author = author;
  	}

	/**
      * Method to return the date of the message
	  * @return the date
	**/
  	public String getDate(){
    	return this.date;
  	}

	/**
      * Method to set the date of the message
	  * @param date the new date
	**/
  	public void setDate(String date){
    	this.date = date;
  	}

	/**
      * Method to return the body of the message
	  * @return the body of the message
	**/
  	public String getBody(){
    	return this.body;
  	}

	/**
      * Method to set the body of the message
	  * @param body the body of the message
	**/
  	public void setBody(String body){
    	this.body = body;
  	}

	/**
      * Method to add a comment to the message
	  * @param comment a Comment object 
	**/
 	public void addComment(Comment comment){
    	this.comment = comment;
  	}

	/**
      * Method to return the comment on the message
	  * @return the comment object associated with the message
	**/
  	public Comment getComment(){
    	return this.comment;
  	}
} // End class