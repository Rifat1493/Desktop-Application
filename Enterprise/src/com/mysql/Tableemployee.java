/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql;

/**
 *
 * @author Rifat
 */

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Rifat
 */
public class Tableemployee {
   
    private final StringProperty EmployeeID;
    private final StringProperty Employeename;
    private final StringProperty Designation;
    private final StringProperty Branchname;
      private final StringProperty Enrollment;
        private final StringProperty Contact;
    private final FloatProperty Salary;
    

    //Default constructor
    public Tableemployee(String EmployeeID,String Employeename,String Designation,String Branchname, String Enrollment,String Contact,float Salary) {
        this.EmployeeID=new SimpleStringProperty( EmployeeID);
        this.Employeename= new SimpleStringProperty(Employeename);
        this.Designation = new SimpleStringProperty(Designation);
        this.Branchname = new SimpleStringProperty(Branchname);
        this.Enrollment= new SimpleStringProperty(Enrollment);
        this.Contact = new SimpleStringProperty(Contact);

        this.Salary = new SimpleFloatProperty(Salary);
        
    }

    //Getters
    public String getEmployeeID() {
        return EmployeeID.get();
    }

    public String getEmployeename () {
        return Employeename .get();
    }

    public String getDesignation() {
        return Designation.get();
    }
        public String getBranchname() {
        return Branchname.get();
    }

    public String getEnrollment() {
        return Enrollment.get();
    }
      public String getContact() {
        return Contact.get();
    }

    public float getSalary() {
        return Salary.get();
    }

    //Setters
    public void setEmployeeID(String value) {
        EmployeeID.set(value);
    }

    public void setEmployeename (String value) {
        Employeename .set(value);
    }

    public void setDesignation(String value) {
       Designation.set(value);
    }
    public void setBranchname(String value) {
        Branchname.set(value);
    }

 public void setEnrollment(String value) {
       Enrollment.set(value);
    }
  public void setContact(String value) {
        Contact.set(value);
    }

    public void setSalary(float value) {
        Salary.set(value);
    }
    //Property values
    public StringProperty EmployeeIDProperty() {
        return EmployeeID;
    }

    public StringProperty EmployeenameProperty() {
        return Employeename ;
    }

    public StringProperty DesignationProperty() {
        return Designation;
    }
    public StringProperty BranchnameProperty() {
        return Branchname;
    }

 public StringProperty EnrollmentProperty() {
        return Enrollment;
    }
  public StringProperty ContactProperty() {
        return Contact;
    }

    public FloatProperty SalaryProperty() {
        return Salary;
    }
}

