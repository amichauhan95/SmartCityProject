package gov.smartCityGUI.hospital.gui;

/*
@author Dylan Moran
Project: Smart City
@date 9/19/2023
I recieved help from: Ami Chauhan
*/

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import gov.smartCityGUI.menu.Menu;
import gov.smartCityGUI.utilities.Gui;
import gov.smartCityGUI.admin.models.*;
import gov.smartCityGUI.hospital.model.*;
import gov.smartCityGUI.hospital.service.*;
import gov.smartCityGUI.hospital.controller.*;
//Admin buttons:  

public class HospitalWindow implements ActionListener {

  User user;
  HospitalSystem system;

  // Main Frame
  JFrame frame;
  JButton backButton;
  JButton bookButton;
  JButton viewHospitals;
  JButton viewApptButton;
  JButton checkInButton;
  JButton reportButton;
  JButton payButton;
  JButton addHospitalButton;
  JButton addDoctorButton;
  JButton viewDoctorsButton;
  JButton updateBillButton;
  JButton viewReportsButton;
  JButton viewAllApptsButton;

  // Scroll Pane
  ArrayList<JCheckBox> checks = new ArrayList<JCheckBox>();
  JScrollPane scroll;

  /**
    * public HospitalWindow(User user) does ...
    * @param User user represents ...
  **/
  public HospitalWindow(User user) {

    this.system = new HospitalSystem(this.user);
    this.user = user;

    createFrames();
    createButtons();
    createLabels();
  }

  /**
    * public void createLabels() does ...
  **/
  public void createLabels() {

    JLabel resources = Gui.whiteLabel("Hospital Resources");
    resources.setBounds(255, 20, 200, 40);
    resources.setFont(new Font(resources.getFont().getName(), Font.PLAIN, 20));
    frame.add(resources);

    if (this.user.getRole().equals("1234")) {
      JLabel admin = Gui.whiteLabel("Admin Privileges");
      admin.setBounds(275, 215, 200, 35);
      admin.setFont(new Font(admin.getFont().getName(), Font.PLAIN, 18));
      frame.add(admin);
    }
    if (this.user.getRole().equals("1444")) {
      JLabel doctor = Gui.whiteLabel("Doctor Privileges");
      doctor.setBounds(275, 215, 200, 35);
      doctor.setFont(new Font(doctor.getFont().getName(), Font.PLAIN, 18));
      frame.add(doctor);
    }
  }

  /**
    * public void createFrames() does ...
  **/
  public void createFrames() {
    frame = Gui.bigFrame("Hospital System");
  }

  /**
    * public void createButtons() does ...
  **/
  public void createButtons() {

    backButton = Gui.back();
    backButton.addActionListener(this);
    frame.add(backButton);

    viewHospitals = Gui.genericButton("All City Hospitals");
    viewHospitals.setBounds(25, 75, 200, 35);
    viewHospitals.addActionListener(this);
    frame.add(viewHospitals);

    bookButton = Gui.genericButton("Book Appointment");
    bookButton.setBounds(250, 75, 200, 35);
    bookButton.addActionListener(this);
    frame.add(bookButton);

    viewApptButton = Gui.genericButton("Pending Appointments");
    viewApptButton.setBounds(475, 75, 200, 35);
    viewApptButton.addActionListener(this);
    frame.add(viewApptButton);

    checkInButton = Gui.genericButton("Appointment Check-in");
    checkInButton.setBounds(25, 145, 200, 35);
    checkInButton.addActionListener(this);
    frame.add(checkInButton);

    reportButton = Gui.genericButton("Appointment Reports");
    reportButton.setBounds(250, 145, 200, 35);
    reportButton.addActionListener(this);
    frame.add(reportButton);

    payButton = Gui.genericButton("View/Pay Bill");
    payButton.setBounds(475, 145, 200, 35);
    payButton.addActionListener(this);
    frame.add(payButton);

    if (this.user.getRole().equals("1234")) {

      addHospitalButton = Gui.genericButton("Add Hospital");
      addHospitalButton.setBounds(25, 255, 200, 35);
      addHospitalButton.addActionListener(this);
      frame.add(addHospitalButton);

      addDoctorButton = Gui.genericButton("Add Doctor");
      addDoctorButton.setBounds(250, 255, 200, 35);
      addDoctorButton.addActionListener(this);
      frame.add(addDoctorButton);

      viewDoctorsButton = Gui.genericButton("View All Doctors");
      viewDoctorsButton.setBounds(475, 255, 200, 35);
      viewDoctorsButton.addActionListener(this);
      frame.add(viewDoctorsButton);
    }
    if (this.user.getRole().equals("1444")) {

      updateBillButton = Gui.genericButton("Submit Report and Bill");
      updateBillButton.setBounds(100, 255, 200, 35);
      updateBillButton.addActionListener(this);
      frame.add(updateBillButton);

      viewReportsButton = Gui.genericButton("View App./Reports");
      viewReportsButton.setBounds(400, 255, 200, 35);
      viewReportsButton.addActionListener(this);
      frame.add(viewReportsButton);

    }

  } // End createButtons() method

  // ***********************************************************************//

  /**
    * public void actionPerformed(ActionEvent e) does ...
    * @param ActionEvent e represents ...
  **/
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == backButton) {
      frame.dispose();
      Menu menu = new Menu(this.user);
    }

    // If view hospitals button is clicked
    else if (e.getSource() == viewHospitals) {
      frame.dispose();
      ViewHospitals view = new ViewHospitals(this.user, this.system);
    }

    // If book appointment button is pressed
    else if (e.getSource() == bookButton) {
      frame.dispose();
      BookApptForm book = new BookApptForm(this.user, this.system);
    }

    else if (e.getSource() == viewApptButton) {
      frame.dispose();
      ViewAppointment view = new ViewAppointment(this.user, system.b1.getAppointments());
    }

    // If check in button is pressed
    else if (e.getSource() == checkInButton) {
      frame.dispose();
      CheckInForm check = new CheckInForm(this.user, this.system);
    }

    else if (e.getSource() == reportButton) {
      frame.dispose();
      ViewReport view = new ViewReport(this.user, this.system);
    }

    else if (e.getSource() == payButton) {
      frame.dispose();
      PayBillForm pay = new PayBillForm(this.user, this.system);
    }

    else if (e.getSource() == addHospitalButton) {
      frame.dispose();
      AddHospitalForm add = new AddHospitalForm(this.user, this.system);
    }

    else if (e.getSource() == addDoctorButton) {
      frame.dispose();
      AddDoctorForm doctor = new AddDoctorForm(this.user, this.system);
    }

    else if (e.getSource() == viewDoctorsButton) {
      frame.dispose();
      ViewDoctors view = new ViewDoctors(this.user, this.system);
    }

    else if (e.getSource() == updateBillButton) {
      frame.dispose();
      SubmitReportForm view = new SubmitReportForm(this.user, this.system);

    }

    else if (e.getSource() == viewReportsButton) {
      frame.dispose();
      ViewReportDoctor view = new ViewReportDoctor(this.user, this.system);
    }

  }
}