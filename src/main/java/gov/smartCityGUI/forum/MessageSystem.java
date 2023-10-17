package gov.smartCityGUI.forum;

/**
  * @author Dylan Moran
  * Project: Smart City
  * @date 9/13/2023
  * Description: This class is the logical layer of the message system that will handle messages that the user
  * creates and will store and handle comments, deletion, etc.
**/

import java.io.*;
import java.util.ArrayList;
import gov.smartCityGUI.admin.models.*;


public class MessageSystem{

  // Stores the codes for all messages. Max of 100!
  public static int codes[] = new int[100];

  //***********************************************************************//

  // Default constructor //
  public MessageSystem(){
  }

  //***********************************************************************//

  /**
    * This method will populate the most current message codes from the file to the class
  **/
  public static void getCodes(){
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/forum/static/messages.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;
      int i = 0;

      // Iterates through all messages in file
      while((line = reader.readLine()) != null){  //[0]code;[1]author;[2]date;[3]body;[4]comments
        String parts[] = line.split(";");
        codes[i] = Integer.parseInt(parts[0]);     // Adds code to array
        i++;
      }
      reader.close();
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
  } // End getCodes() method

  //***********************************************************************//

  /**
    * This method will display all the messages posted to the message board
  **/
  public static ArrayList<String[]> fetchMessages(){

    ArrayList<String[]> list = new ArrayList<String[]>();
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/forum/static/messages.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;

      // Iterates through all messages in file
      while((line = reader.readLine()) != null){  //[0]code;[1]author;[2]date;[3]body;[4]comments
        String parts[] = line.split(";");
       
        list.add(parts);                   
      }
      reader.close();
      return list;
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
    return list;
  } // End displayMessages() method

  //***********************************************************************//

  /**
    * This method will allow a user to post a message to the city message board
	*
    * @param currentUser the user who is posting the message
    * @param body the body of the message being posted
  **/
  public static void writeMessage(User currentUser, String body){

    if(body.equals("")) return;
    String name = currentUser.getFirst() + " " + currentUser.getLast();
    Message newMessage = new Message(name, body);     // Create message object with author and body
    fileMessage(newMessage);                          // Add that message to file
  } // End writeMessage() method

  //***********************************************************************//

  /**
    * This method will allow a user to leave a comment on an existing message
    * @param currentUser the user who is posting the comment
    * @param scan a scanner to gather user input for the message text
    * @param com the comment that the user entered
  **/
  public static void leaveComment(User currentUser, int code, String com){
      getCodes();                     // Populate most current message codes
      boolean exists = false;         // Message exists is set to false
      
      for(int i = 0; i < codes.length; i++){    // Iterate through the populated codes and check against code that the user input
        if(code == codes[i]){
          exists = true;                        // If the code matches a existing code, set exists to true
        } 
      } // End for loop
      if(exists){     // If the message exists
        try{
            Message message = fetchMessage(code);     // Fetch message from file
            if(com.equals("")) return;                // If comment is empty, return
            String author = currentUser.getFirst() + " " + currentUser.getLast();
            Comment comment = new Comment(author, com);     // Create new Comment object
            message.addComment(comment);                    // Add comment to the message
            addComToFile(message, code);                    // Add the comment to file, updating the message as well
            return;
          } catch(Exception e){
            e.printStackTrace();
              System.out.println("Message not found.");
          } // End try-catch
      } else {
      } // End if-else
  } // End leaveComment() method

  //***********************************************************************//

  /**
    * This method will take a message code as input and return the corresponding message from file
    * @param code the code of the message to be returned
    * @return the message that corresponds with the given code
  **/
  public static Message fetchMessage(int code){
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/forum/static/messages.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;
  
      while((line = reader.readLine()) != null){       // Iterate through all the messages in file
        String parts[] = line.split(";");
        if(parts[0].equals("")){                       // Skip empty lines
        } else {
          int num = Integer.parseInt(parts[0]);        // num equals the current message's code
          if(num == code){                             // If the current message matches the given code
            reader.close();  
            return new Message(parts[1], parts[3]);    // return that message
          } // End if
        } // End if-else
      } // End while loop
      reader.close();
      return null;
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
    return null;
  } // End fetchMessage() method

  //***********************************************************************//
  
  /**
    * This method wil write the data about the newly posted message to file
    * @param newMessage the new message that is being saved to file
  **/
  public static void fileMessage(Message newMessage){
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/forum/static/messages.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/forum/static/temp.txt");
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;
      String code = "0";
  
      while((line = reader.readLine()) != null){       // Iterate through all messages in file
        String parts[] = line.split(";");
        writer.write(line + System.lineSeparator());
        code = parts[0];
        if(code.equals("")){                           // If there are no messages, set code to 0. It will be incremented automatically
          code = "0";
        } // End if
      } // End while loop
      int num = Integer.parseInt(code);
      num++;
      code = Integer.toString(num);                   // Increment code to be the next entry
      String parts[] = {code, newMessage.getAuthor(), newMessage.getDate(), newMessage.getBody(), "-@"}; // Create string array of all message parts (**"-@" is used for empty comment section**)
      line = String.join(";", parts);                  // Join the message parts with delimeter ';'
      writer.write(line + System.lineSeparator());     // Write the new line to file
  
      reader.close();
      writer.close();
  
      if(readFile.delete()){                      // Overwrite the file with the edited version.
        writeFile.renameTo(readFile);
      } // End if
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
  } // End fileMessage() method

  //***********************************************************************//

  /**
    * This method will add the newly posted comment to the file
    * @param message the message that is being commented on
    * @param code the code of the message being commented on
  **/
  public static void addComToFile(Message message, int code){
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/forum/static/messages.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/forum/static/temp.txt");
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;
  
      while((line = reader.readLine()) != null){       // Iterate through all messages in file
        String parts[] = line.split(";");
        int num = Integer.parseInt(parts[0]);
        
        if(num == code){                               // If code matches
          String comments[] = parts[4].split("@");     // Separate comments using delimeter '@'
          
          if(comments[0].equals("-")){                         // If comment section is empty, add first comment
            comments[0] = message.getComment().getAuthor() + "~" + message.getComment().getBody(); // Making first comment format: "author~body"
            parts[4] = String.join("@", comments);             // Join all the comments together
            line = String.join(";", parts);                    // Join the comments with the message
            writer.write(line + System.lineSeparator());       // Write the new message to file with updated comments
          } else {
            
            String copy[] = new String[comments.length+1];     // Else create a new String array with length one greater than current length
            for(int i = 0; i < comments.length; i++){          // Loop to copy all comments to temporary array
              copy[i] = comments[i];
            } // End for loop
            
            copy[comments.length] = message.getComment().getAuthor() + "~" + message.getComment().getBody(); // Add new comment to the end of the existing comments 
            parts[4] = String.join("@", copy);             // Join all comments back together
            line = String.join(";", parts);                // Join the comments with the message
            writer.write(line + System.lineSeparator());   // Write the new message to file with updated comments
          } // End if-else
        } else {
          writer.write(line + System.lineSeparator());     // If code doesnt match, copy file
        } // End if-else
      } // End while loop
  
      reader.close();
      writer.close();
  
      if(readFile.delete()){                      // Overwrite the file with the edited version.
          writeFile.renameTo(readFile);
        } // End if
    } catch(IOException e){
        e.printStackTrace();
    } // End try-catch
  } // End addComToFile() method

  //***********************************************************************//

  /**
    * This method is used to delete a message from the message board
    * @param code the code of the message to be deleted
  **/
    public static void deleteMessage(int code){
    getCodes();
    boolean exists = false;
      
    for(int i = 0; i < codes.length; i++){       // Check for a matching message code
      if(code == codes[i]){
        exists = true;                           // If match set flag to true
      } 
    } // End for loop
    if(exists){
      delete(code);                              // If flag is true, delete message with given code
    } else {
  // Else print message
    } // End if-else
  } // End deleteMessage() method

  //***********************************************************************//

  /**
    * This method is used to delelte a message from file given the code
    * @param code the code of the message to be deleted
  **/
  public static void delete(int code){
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/forum/static/messages.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/forum/static/temp.txt");
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;

      while((line = reader.readLine()) != null){     // Iterate through all messages copying all lines except for the one with the matching message code
        String parts[] = line.split(";");
        if(parts[0].equals("")){
          
        } else {
          int num = Integer.parseInt(parts[0]);
          if(num == code){                                       // If the code matches, skip this line
            
          } else {
            writer.write(line + System.lineSeparator());         // Else copy the line to the edited file
          } // End nested if-else
        } // End if-else
      } // End while loop

      reader.close();
      writer.close();
  
      if(readFile.delete()){                      // Overwrite the file with the edited version.
          writeFile.renameTo(readFile);
        }
      
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
  } // End delete() method

  //***********************************************************************//

  /**
    * This method is used to clear all messages from the file
  **/
  public static void clearAllMessages(){
    try{
      File readFile = new File("src/main/java/gov/smartCityGUI/forum/static/messages.txt");
      File writeFile = new File("src/main/java/gov/smartCityGUI/forum/static/temp.txt");
      BufferedWriter writer = new BufferedWriter(new FileWriter(writeFile));
      writer.write("");                           // Creates an empty file
      writer.close();
  
      if(readFile.delete()){                      // Overwrite the current file with the empty file.
        writeFile.renameTo(readFile);
      } // End if
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
  } // End clearAllMessages() method
} // End MessageSystem class