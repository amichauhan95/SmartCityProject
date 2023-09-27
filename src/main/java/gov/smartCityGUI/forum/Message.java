package gov.smartCityGUI.forum;

/*
@author Dylan Moran
Project: Smart City
@date 9/5/2023
I recieved help from: N/A
*/

import java.util.*;
import java.text.SimpleDateFormat;

public class Message{

  private String author;
  private String body;
  private String date;
  private Comment comment;

  public Message(){
    
  }

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

  public Message(int code, String author, String body){
    this.author = author;
    this.body = body;
  }

  public String getAuthor(){
    return this.author;
  }

  public void setAuthor(String author){
    this.author = author;
  }

  public String getDate(){
    return this.date;
  }

  public void setDate(String date){
    this.date = date;
  }

  public String getBody(){
    return this.body;
  }

  public void setBody(String body){
    this.body = body;
  }

  public void addComment(Comment comment){
    this.comment = comment;
  
  }

  public Comment getComment(){
    return this.comment;
  }
}