package gov.smartCityGUI.forum;

/*
@author Dylan Moran
Project: Smart City
@date 9/5/2023
I recieved help from: N/A
*/

public class Comment{

  private String author;
  private String body;

  public Comment(){
    
  }

  public Comment(String author, String body){
    this.author = author;
    this.body = body;
  }

  public String getAuthor(){
    return this.author;
  }

  public void setAuthor(String author){
    this.author = author;
  }

  public String getBody(){
    return this.body;
  }

  public void setBody(String body){
    this.body = body;
  }
}