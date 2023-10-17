// /**
//   * Team Member(s) working on this class: Keith Austin
//   * Project: Smart City
//   * @author: Keith Austin
//   * I received help from everyone on my team
// **/

// package gov.smartcityteam2.EMS;

// import java.io.BufferedReader;
// import java.io.FileReader;
// import java.io.IOException;

// public class Contact {
  
//   /**
//     * public void showContacts() reads all the data from the file and displays it
//     * @throws IOException if the file isn't read from properly
//   **/
//   public void showContacts() throws IOException {

//     BufferedReader reader;

// 		try {
      
// 			reader = new BufferedReader(new FileReader("src/main/java/gov/smartcityteam2/EMS/data/contacts.txt"));
// 			String line = reader.readLine();

// 			while (line != null) {
// 				System.out.println(line);
// 				line = reader.readLine();
// 			}

// 			reader.close();
// 		} catch (IOException e) {
// 			e.printStackTrace();
// 		}
//   }
// }