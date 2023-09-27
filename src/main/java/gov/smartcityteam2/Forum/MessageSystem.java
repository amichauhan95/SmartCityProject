/*
@author Dylan Moran
Project: Smart City
@date 9/5/2023
I recieved help from: N/A
*/

package gov.smartcityteam2.Forum;

import java.io.*;
import java.util.Scanner;
import gov.smartcityteam2.Util;
import gov.smartcityteam2.User;
import gov.smartcityteam2.Banking.BankAccount;

public class MessageSystem{

  // Stores the codes for all messages. Max of 100!
  private int codes[] = new int[100];

  //***********************************************************************//

  // Default constructor //
  public MessageSystem(){
  }

  //***********************************************************************//

  /* 
  Driver method to run the class
  @param currentUser the user who will be using the MessageSystem
  */
  public void start(User currentUser){
    if(currentUser.isAdmin()){
      admin(currentUser);
    } else {
      prompt(currentUser);
    }
  } // End start() method

  //***********************************************************************//

  /*
  This method is used when the current user has admin privileges
  @param currentUser the admin user who is accessing the system
  */
  public void admin(User currentUser){
     Scanner scan = new Scanner(System.in);

    while(true){
      Util.header("Message Board Admin Panel");
      Util.printOptions("View Message Board,Leave a Message,Leave a Comment,Delete Message,Clear All Messages");
      
       int choice = Util.intScan(scan);
  
      switch(choice){
        case 1: 
          displayMessages();
          break;
        case 2:
          writeMessage(currentUser, scan);
          break;
        case 3:
          leaveComment(currentUser, scan);
          break;
        case 4:
          deleteMessage(scan);
          break;
        case 5: 
          clearAllMessages();
          break;
        case 0:
          return;
        default:
      }
    }
  }

  //***********************************************************************//

  /*
  This method prompts the user to decide what functions they wnat to perform in the MessageSystem
  @param currentUser the user who is using the MessageSystem
  */
  public void prompt(User currentUser){
    
    Scanner scan = new Scanner(System.in);

    getCodes();           // Populates the most up to date message codes 

    while(true){         // User input loop
      Util.header("Welcome to the City Message Board!");
      Util.printOptions("View Message Board,Leave a Message,Leave a Comment");
    
      int choice = Util.intScan(scan);
  
      switch(choice){
        case 1: 
          displayMessages();                     // View Message Board
          break;
        case 2:
          writeMessage(currentUser, scan);       // Leave a Message
          break;
        case 3:
          leaveComment(currentUser, scan);       // Leave a Comment
          break;
        case 0:                                  // Exit
          return;
        default:
      } // End switch
    } // End while loop
  } // End prompt() method

  //***********************************************************************//

  /*
  This method will populate the most current message codes from the file to the class
  */
  public void getCodes(){
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Forum/messages.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;
      int i = 0;

      // Iterates through all messages in file
      while((line = reader.readLine()) != null){  //[0]code;[1]author;[2]date;[3]body;[4]comments
        String parts[] = line.split(";");
        this.codes[i] = Integer.parseInt(parts[0]);     // Adds code to array
        i++;
      }
      reader.close();
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
  } // End getCodes() method

  //***********************************************************************//

  /*
  This method will display all the messages posted to the message board
  */
  public void displayMessages(){
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Forum/messages.txt");
      BufferedReader reader = new BufferedReader(new FileReader(readFile));
      String line;

      // Iterates through all messages in file
      while((line = reader.readLine()) != null){  //[0]code;[1]author;[2]date;[3]body;[4]comments
        String parts[] = line.split(";");
        printMessage(parts);                     // Prints the formatted message
      }
      reader.close();
    } catch(IOException e){
      e.printStackTrace();
    } // End try-catch
  } // End displayMessages() method

  //***********************************************************************//

  /*
  This method will print the formatted message
  @param parts the parts of the message separated into a String array
  */
  public void printMessage(String[] parts){
    String ANSI_BOLD = "\u001B[1m";
    String ANSI_RESET = "\u001B[0m";
    String itON = "\033[3m";
    String itOff = "\033[0m";
    String separate = ANSI_BOLD + "------------------------------------------------\n" + ANSI_RESET;
    
    if(parts[0].equals("")){     // If the line is empty, alert user of no messages
      System.out.println("\n" + separate + "There are currently no messages.\n" + separate);
    } else {    // Else print formatted message
      System.out.println(separate +"\nMessage:\nCode: " + parts[0] + "\n\n\t" + itON + parts[3] + itOff + "\n\n\t" + ANSI_BOLD + parts[1] + ANSI_RESET +"\n\t" + parts[2] + "\n\nComments:\n");
      gatherComments(parts[4]);
      System.out.print(separate);
    } // End if-else
  } // End printMessage() method

  //***********************************************************************//

  /*
  This method formats the comments correctly from the given text data
  @param line the line of text with data about the comments separated by delimeters, different comments are separated by '@', and data about a specific comment is separated by '~' (commentAuthor~commentText@commentAuthor~commentText)
  */
  public void gatherComments(String line){
    String ANSI_BOLD = "\u001B[1m";
    String ANSI_RESET = "\u001B[0m";
    String itON = "\033[3m";
    String itOff = "\033[0m";
    String parts[] = line.split("@");
    // If comment author equals '-', return because there are no comments
    if(parts[0].equals("-")){
      return;
    } else {    // Else print formatted comment
      for(int i = 0; i < parts.length; i ++){
        String comment[] = parts[i].split("~");
        System.out.println("\t\t" + itON + comment[1] + itOff + "\n\t\t" + ANSI_BOLD + comment[0] + ANSI_RESET + "\n");
      } // End for loop
    } // End if-else
  } // End gatherComments() method

  //***********************************************************************//

  /*
  This method will allow a user to post a message to the city message board
  @param currentUser the user who is posting the message
  @param scan a scanner to gather user input for the message text
  */
  public void writeMessage(User currentUser, Scanner scan){
    System.out.print("\nWhat would you like your message to say?\n---");
    String body = scan.nextLine();
    if(body.equals("")) return;
    String name = currentUser.getFirst() + " " + currentUser.getLast();
    Message newMessage = new Message(name, body);     // Create message object with author and body
    fileMessage(newMessage);                          // Add that message to file
  } // End writeMessage() method

  //***********************************************************************//

  /*
  This method will allow a user to leave a comment on an existing message
  @param currentUser the user who is posting the comment
  @param scan a scanner to gather user input for the message text
  */
  public void leaveComment(User currentUser, Scanner scan){
    getCodes();     // Populate most current message codes

    while(true){
      System.out.print("Enter the code for the message you'd like to comment on:  ");
      int code = Util.intScan(scan);     // User inputs message code to specify what they are commenting on
      boolean exists = false;                   // Message exists is set to false
      
      for(int i = 0; i < codes.length; i++){    // Iterate through the populated codes and check against code that the user input
        if(code == this.codes[i]){
          exists = true;                        // If the code matches a existing code, set exists to true
        } 
      }
      if(exists){     // If the message exists
        try{
            Message message = fetchMessage(code);     // Fetch message from file
            System.out.print("\nMessage Found:\n\t" + message.getBody() + "\n\nEnter your comment: ");
            String com = scan.nextLine();             // User inputs their comment
            if(com.equals("")){                       // If comment is empty, break
              break;
            } // End if
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
        System.out.println("\nInvalid Code.\n\n");
      } // End if-else
    } // End while loop
  } // End leaveComment() method

  //***********************************************************************//

  /*
  This method will take a message code as input and return the corresponding message from file
  @param code the code of the message to be returned
  @return the message that corresponds with the given code
  */
  public Message fetchMessage(int code){
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Forum/messages.txt");
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
  
  /*
  This method wil write the data about the newly posted message to file
  @param newMessage the new message that is being saved to file
  */
  public void fileMessage(Message newMessage){
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Forum/messages.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Forum/temp.txt");
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

  /*
  This method will add the newly posted comment to the file
  @param message the message that is being commented on
  @param code the code of the message being commented on
  */
  public void addComToFile(Message message, int code){
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Forum/messages.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Forum/temp.txt");
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

  /*
  This method is used to delete a message from the message board
  @param scan scans for user input to determine which message to delete
  */
    public void deleteMessage(Scanner scan){
    getCodes();
    System.out.print("Enter the code for the message to be deleted:  ");
    int code = Util.intScan(scan);

    boolean exists = false;
      
    for(int i = 0; i < codes.length; i++){       // Check for a matching message code
      if(code == this.codes[i]){
        exists = true;                           // If match set flag to true
      } 
    } // End for loop
    if(exists){
      delete(code);                              // If flag is true, delete message with given code
    } else {
      System.out.println("\nInvalid Code.\n\n"); // Else print message
    } // End if-else
  } // End deleteMessage() method

  //***********************************************************************//

  /*
  This method is used to delelte a message from file given the code
  @param code the code of the message to be deleted
  */
  public void delete(int code){
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Forum/messages.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Forum/temp.txt");
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

  /*
  This method is used to clear all messages from the file
  */
  public void clearAllMessages(){
    try{
      File readFile = new File("src/main/java/gov/smartcityteam2/Forum/messages.txt");
      File writeFile = new File("src/main/java/gov/smartcityteam2/Forum/temp.txt");
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