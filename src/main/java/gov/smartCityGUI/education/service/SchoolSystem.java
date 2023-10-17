/**
  * Team Member(s) working on this class: Melih Kartal
  * Project: Smart City
  * @author: Melih Kartal
**/
package gov.smartCityGUI.education.service;

import org.hamcrest.StringDescription;
import java.lang.*;
import java.util.*;

public class SchoolSystem {

  public SchoolSystem() {
    // no-arg constructor;
  }

  public HashMap<String, Double> middleSchoolsGPA = new HashMap<String, Double>() {
    {
      put("Van Atwerp Middle School", 1.9);
      put("Iroquois Middle School", 2.1);
      put("Bethlehem Central Middle School", 2.3);
      put("Gowana Middle School", 2.5);
      put("Acadia Middle School", 2.7);
      put("Richard H. O'Rourke Middle School", 2.9);
      put("Koda Middle School", 3.1);
      put("Voorheesville Middle School", 3.3);
      put("Howard L.Goff School", 3.5);
      put("Algonquin Middle School", 3.7);
      put("Farnsworth Middle School", 3.9);
      put("Menands Middle School", 4.0);
    }
  };

  public HashMap<String, Double> middleSchoolsPerCredit = new HashMap<String, Double>() {

    {
      put("Van Atwerp Middle School", 100.0);
      put("Iroquois Middle School", 200.0);
      put("Bethlehem Central Middle School", 300.0);
      put("Gowana Middle School", 400.0);
      put("Acadia Middle School", 500.0);
      put("Richard H. O'Rourke Middle School", 600.0);
      put("Koda Middle School", 700.0);
      put("Voorheesville Middle School", 800.0);
      put("Howard L.Goff School", 900.0);
      put("Algonquin Middle School", 100.0);
      put("Farnsworth Middle School", 200.0);
      put("Menands Middle School", 300.0);
    }

  };

  public HashMap<String, Double> highSchoolsGPA = new HashMap<String, Double>() {
    {
      put("Behlehem Central Senior High School", 1.9);
      put("Clayton A Bouton High School", 2.1);
      put("Maple Hill High School", 2.3);
      put("Niskayuna High School", 2.5);
      put("Burnt Hills-Ballston Lake Senior High School", 2.7);
      put("Shaker High School", 2.9);
      put("Saratoga Springs High School", 3.1);
      put("Columbia High School", 3.3);
    }
  };

  public HashMap<String, Double> highSchoolsPerCredit = new HashMap<String, Double>() {
    {
      put("Behlehem Central Senior High School", 100.0);
      put("Clayton A Bouton High School", 200.0);
      put("Maple Hill High School", 300.0);
      put("Niskayuna High School", 400.0);
      put("Burnt Hills-Ballston Lake Senior High School", 500.0);
      put("Shaker High School", 600.0);
      put("Saratoga Springs High School", 700.0);
      put("Columbia High School", 800.0);
    }
  };

  public HashMap<String, Double> collegesGPA = new HashMap<String, Double>() {
    {
      put("SUNY Adirondack Community College", 1.9);
      put("Albany College of Pharmacy & Health Sciences", 2.1);
      put("Albany Law School", 2.3);
      put("Albany Medical College", 2.5);
      put("Bryant & Stratton College", 2.7);
      put("The College of Saint Rose", 2.9);
      put("Columbia-Greene Community College", 3.1);
      put("Empire State College", 3.3);
      put("Excelsior College", 3.5);
      put("Fulton-Montgomery Community College", 3.7);
      put("Hudson Valley Community College", 3.9);
      put("Graduate College of Union University", 4.0);
      put("Maria College", 4.0);
      put("Mildred Elley", 4.0);
      put("Rensselaer Polytechnic Institute", 4.0);
      put("Russell Sage College", 4.0);
      put("Sage College of Albany", 4.0);
      put("Sage Graduate School", 4.0);
    }
  };

  public HashMap<String, Double> collegesPerCredit = new HashMap<String, Double>() {
    {
      put("SUNY Adirondack Community College", 100.0);
      put("Albany College of Pharmacy & Health Sciences", 200.0);
      put("Albany Law School", 300.0);
      put("Albany Medical College", 400.0);
      put("Bryant & Stratton College", 500.0);
      put("The College of Saint Rose", 600.0);
      put("Columbia-Greene Community College", 700.0);
      put("Empire State College", 800.0);
      put("Excelsior College", 900.0);
      put("Fulton-Montgomery Community College", 100.0);
      put("Hudson Valley Community College", 200.0);
      put("Graduate College of Union University", 300.0);
      put("Maria College", 400.0);
      put("Mildred Elley", 500.0);
      put("Rensselaer Polytechnic Institute", 600.0);
      put("Russell Sage College", 700.0);
      put("Sage College of Albany", 800.0);
      put("Sage Graduate School", 900.0);
    }
  };

  private String[] middleSchools = { "Van Atwerp Middle School",
      "Iroquois Middle School",
      "Bethlehem Central Middle School",
      "Gowana Middle School",
      "Acadia Middle School",
      "Richard H. O'Rourke Middle School",
      "Koda Middle School",
      "Voorheesville Middle School",
      "Howard L.Goff School",
      "Algonquin Middle School",
      "Maple Avenue Middle School",
      "Farnsworth Middle School",
      "Menands Middle School" };

  private String[] highSchools = { "Behlehem Central Senior High School",
      "Clayton A Bouton High School",
      "Maple Hill High School",
      "Niskayuna High School",
      "Burnt Hills-Ballston Lake Senior High School",
      "Shaker High School",
      "Saratoga Springs High School",
      "Columbia High School"
  };

  private String[] universities = { "SUNY Adirondack	Community college",
      "Albany College of Pharmacy Health Sciences	",
      "Albany Law School",
      "Albany Medical College",
      "Bryant & Stratton College",
      "The College of Saint Rose	",
      "Columbia-Greene Community College",
      "Empire State College",
      "Excelsior College",
      "Fulton-Montgomery Community College",
      "Hudson Valley Community College",
      "Graduate College of Union University",
      "Maria College",
      "Mildred Elley",
      "Rensselaer Polytechnic Institute",
      "Russell Sage College",
      "Sage College of Albany",
      "Sage Graduate School" };

  public String[] getMiddleSchools() {
    return middleSchools;
  }

  public String[] getHighSchools() {

    return highSchools;

  }

  public String[] getUniversities() {

    return universities;

  }

}