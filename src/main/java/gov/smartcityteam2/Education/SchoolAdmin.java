
package gov.smartcityteam2.Education;

import java.util.HashMap;
import java.util.Map;

abstract class SchoolAdmin {

  private HashMap<String, String> middleSchoolPrincipals = new HashMap<String, String>();
  private HashMap<String, String> highSchoolPrincipals = new HashMap<String, String>();
  private HashMap<String, String> collegePresidents = new HashMap<String, String>();

  public SchoolAdmin() {

    initializeMiddleSchoolPrincipals();
    initializeHighSchoolPrincipals();
    initializeCollegePres();

  };

  public void initializeMiddleSchoolPrincipals() {
    middleSchoolPrincipals = new HashMap<String, String>();
    middleSchoolPrincipals.put("Van Atwerp Middle School", "John Smith");
    middleSchoolPrincipals.put("Iroquois Middle School", "Mary Johnson");
    middleSchoolPrincipals.put("Bethlehem Central Middle School", "David Williams");
    middleSchoolPrincipals.put("Gowana Middle School", "Susan Brown");
    middleSchoolPrincipals.put("Acadia Middle School", "Michael Davis");
    middleSchoolPrincipals.put("Richard H. O'Rourke Middle School", "Karen Wilson");
    middleSchoolPrincipals.put("Koda Middle School", "Robert Jones");
    middleSchoolPrincipals.put("Voorheesville Middle School", "Jennifer Miller");
    middleSchoolPrincipals.put("Howard L.Goff School", "William Moore");
    middleSchoolPrincipals.put("Algonquin Middle School", "Linda Lee");
    middleSchoolPrincipals.put("Maple Avenue Middle School", "Thomas Taylor");
    middleSchoolPrincipals.put("Farnsworth Middle School", "Patricia White");
    middleSchoolPrincipals.put("Menands Middle School", "James Anderson");
  }

  public void initializeHighSchoolPrincipals() {
    highSchoolPrincipals = new HashMap<String, String>();
    highSchoolPrincipals.put("Behlehem Central Senior High School", "Elizabeth Davis");
    highSchoolPrincipals.put("Clayton A Bouton High School", "Daniel Brown");
    highSchoolPrincipals.put("Maple Hill High School", "Susan Smith");
    highSchoolPrincipals.put("Niskayuna High School", "John Johnson");
    highSchoolPrincipals.put("Burnt Hills-Ballston Lake Senior High School", "Maria Martinez");
    highSchoolPrincipals.put("Shaker High School", "Michael Wilson");
    highSchoolPrincipals.put("Saratoga Springs High School", "Jennifer Taylor");
    highSchoolPrincipals.put("Columbia High School", "David Clark");
  }

  public void initializeCollegePres() {
    collegePresidents = new HashMap<String, String>();
    collegePresidents.put("SUNY Adirondack Community College", "Karen Thompson");
    collegePresidents.put("Albany College of Pharmacy Health Sciences", "Robert Lee");
    collegePresidents.put("Albany Law School", "Jennifer Davis");
    collegePresidents.put("Albany Medical College", "William Smith");
    collegePresidents.put("Bryant & Stratton College", "Mary Wilson");
    collegePresidents.put("The College of Saint Rose", "Michael Anderson");
    collegePresidents.put("Columbia-Greene Community College", "Susan Martin");
    collegePresidents.put("Empire State College", "Linda Moore");
    collegePresidents.put("Excelsior College", "Daniel Taylor");
    collegePresidents.put("Fulton-Montgomery Community College", "Elizabeth Harris");
    collegePresidents.put("Hudson Valley Community College", "David Wilson");
    collegePresidents.put("Graduate College of Union University", "Jennifer Thomas");
    collegePresidents.put("Maria College", "John Brown");
    collegePresidents.put("Mildred Elley", "Maria Clark");
    collegePresidents.put("Rensselaer Polytechnic Institute", "William Anderson");
    collegePresidents.put("Russell Sage College", "Patricia Martin");
    collegePresidents.put("Sage College of Albany", "Thomas Jones");
    collegePresidents.put("Sage Graduate School", "Robert Taylor");
  }

  public void viewMiddleSchoolsAndPrincipals() {

    System.out.println("List of Middle Schools and Principals:");
    for (Map.Entry<String, String> entry : this.middleSchoolPrincipals.entrySet()) {
      System.out.println("School: " + entry.getKey() + ", Principal: " + entry.getValue());
    }
  }

  public void viewHighSchoolsAndPrincipals() {
    System.out.println("List of High Schools and Principals:");
    for (Map.Entry<String, String> entry : highSchoolPrincipals.entrySet()) {
      System.out.println("School: " + entry.getKey() + ", Principal: " + entry.getValue());
    }
  }

  public void viewUniversityPresidents() {
    System.out.println("List of University/College Presidents:");
    for (Map.Entry<String, String> entry : collegePresidents.entrySet()) {
      System.out.println("University/College: " + entry.getKey() + ", President: " + entry.getValue());
    }
  }

}
