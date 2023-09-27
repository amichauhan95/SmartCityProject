/*
@author Huiying Lin
Project: Smart City
@date 9/13/2023
I recieved help from: N/A
*/
package gov.smartcityteam2.Tourism.util;
import java.util.Scanner;

    /*
   * Provides a framework to creates neccessary components of list menu
   * Components contains: header, footer, title, options, inputCollector
   */
public class MenuUtil{

  /**
   * Displays a header
   *
   * @param layout: can be '-', '=', '#'; length: length of layout
   */
  public static void setHeader (String layout, int length){
    System.out.println(layout.repeat(length)); 
  }

  /**
   * Displays a footer
   *
   * @param layout: can be '-', '=', '#'; length: length of layout
   */
  public static void setFooter (String layout, int length){
    System.out.print(layout.repeat(length)); 
  }

  /**
   * Displays a title
   *
   * @param title: name of the menu
   */
  public static void setTitle(String title){
     System.out.println(String.format("\n\t\t%s\n\n", title));
  }

  /**
   * Displays a option-based menu, and get user's option
   *
   * @param title: the name of menu
   * optionsList: string array contains the different options
   * layout: can be '-', '=', '#'
   * length: the length of layout
   * @return user's option 
   */
  public static int setOptions(String title, String[] optionsList, String layout, int length){
    while(true){
      int count = 1;
      setHeader(layout, length);
      setTitle(title);
      String optionLayout = "";
      for(String option: optionsList){
        optionLayout += String.format("%s: %s\n\n", count++, option);
      }
      optionLayout += "Enter 0 to return to last page: ";
      System.out.print(optionLayout);
      return optionCollector(optionsList.length);
    }
  }

  /**
   * Collect the user's option number 
   *
   * @param range: Based on how many options a menu has 
   * @return user's option 
   */
  public static int optionCollector(int range){
    while(true){
      try{
        String str = new Scanner(System.in).nextLine();
        int option = Integer.parseInt(str);
        if(option > range || option < 0) throw new Exception();
        return option;
      } catch (Exception e){
        System.out.print("\n*WARNING* Invalid Input\n\n");
        System.out.print("Please re-enter: ");
      }
    }
  }
  
  /**
   * Displays a form menu, and get user's data 
   *
   * @param title: the name of menu
   * layout: can be '-', '=', '#'
   * length: the length of layout
   * inputList: string array contains each form elements 
   * dataType: String array contains required dataype of each form elements 
   * @return object array that contains all user's input
   */
  public static Object[] inputCollector(String title, String layout, int length, String inputList[], String dataType[]){
    while(true){
      setHeader(layout, length);
      setTitle(title);
      Object[] o = new Object[inputList.length];
      try{
        if(inputList.length != dataType.length) throw new Exception();
      
        for(int i = 0; i < inputList.length; i++){
          Scanner scanner = new Scanner(System.in);
          System.out.print(String.format("%s: ", inputList[i]));
          if(dataType[i].equals("String")) {
            o[i] = scanner.nextLine();
          } else if(dataType[i].equals("int")){
            o[i] = scanner.nextInt();
          } else if(dataType[i].equals("double")){
            o[i] = scanner.nextDouble();
          } else if(dataType[i].equals("char")){
            o[i] = scanner.next(".").charAt(0);
          } else {
            throw new Exception();
          }
          System.out.println();
        }
        return o;
        
      } catch(Exception e){
        System.out.println("\n*WARNING* Please enter a valid input\n");
        // System.out.println("");
      }
    }
  }
}