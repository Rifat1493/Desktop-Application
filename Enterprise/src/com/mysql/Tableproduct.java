/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mysql;

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
public class Tableproduct {
   
    private final StringProperty ProductID;
    private final StringProperty Productname;
    private final StringProperty Company;
    private final StringProperty Procategory;
    private final IntegerProperty Quantity;
    private final FloatProperty Price;
    

    //Default constructor
    public Tableproduct(String ProductID,String Productname,String Company,String Procategory, int Quantity,float Price) {
        this.ProductID=new SimpleStringProperty( ProductID);
        this.Productname = new SimpleStringProperty(Productname);
        this.Company = new SimpleStringProperty(Company);
        this.Procategory = new SimpleStringProperty(Procategory);
        this.Quantity = new SimpleIntegerProperty(Quantity);
        this.Price = new SimpleFloatProperty(Price);
        
    }

    //Getters
    public String getProductID() {
        return ProductID.get();
    }

    public String getProductname () {
        return Productname .get();
    }

    public String getCompany() {
        return Company.get();
    }
        public String getProcategory() {
        return Procategory.get();
    }

    public int getQuantity() {
        return Quantity.get();
    }

    public float getPrice() {
        return Price.get();
    }

    //Setters
    public void setProductID(String value) {
        ProductID.set(value);
    }

    public void setProductname (String value) {
        Productname .set(value);
    }

    public void setCompany(String value) {
       Company.set(value);
    }
    public void setProcategory(String value) {
        Procategory.set(value);
    }

    public void setQuantity(int value) {
        Quantity.set(value);
    }

    public void setPrice(float value) {
        Price.set(value);
    }
    //Property values
    public StringProperty ProductIDProperty() {
        return ProductID;
    }

    public StringProperty ProductnameProperty() {
        return Productname ;
    }

    public StringProperty CompanyProperty() {
        return Company;
    }
    public StringProperty ProcategoryProperty() {
        return Procategory;
    }

    public IntegerProperty QuantityProperty() {
        return Quantity;
    }

    public FloatProperty PriceProperty() {
        return Price;
    }
}
