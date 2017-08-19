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
public class temptable{
   
    private final StringProperty ProductID;
    private final StringProperty Productname;
    private final FloatProperty Unit_Price;
    private final IntegerProperty Quantity;
    private final FloatProperty Amount;
    private final FloatProperty VAT;
    private final FloatProperty Discount;
    

    //Default constructor
    public temptable(String ProductID, String Productname, float Unit_Price, int Quantity, float Amount, float VAT, float Discount) {
        this.ProductID=new SimpleStringProperty( ProductID);
        this.Productname = new SimpleStringProperty(Productname);
        this.Unit_Price = new SimpleFloatProperty(Unit_Price);
        this.Quantity = new SimpleIntegerProperty(Quantity);
        this.Amount = new SimpleFloatProperty(Amount);
        this.VAT = new SimpleFloatProperty(VAT);
        this.Discount = new SimpleFloatProperty(Discount);
        
    }

    public temptable(String string, float aFloat, int aInt, float aFloat0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Getters
    public  String getProductID() {
        return ProductID.get();
    }

    public String getProductname () {
        return Productname.get();
    }

    public Float getUnit_Price() {
        return Unit_Price.get();
    }
    public int getQuantity() {
        return Quantity.get();
    }

    public float getAmount() {
        return Amount.get();
    }
    
    public float getVAT() {
        return VAT.get();
    }
    
    public float getDiscount() {
        return Discount.get();
    }

    //Setters
    public void setProductID(String value) {
        ProductID.set(value);
    }

    public void setProductname (String value) {
        Productname .set(value);
    }

    public void setUnit_Price(Float value) {
       Unit_Price.set(value);
    }
    public void setQuantity(int value) {
        Quantity.set(value);
    }

    public void setAmount(float value) {
        Amount.set(value);
    }
    
     public void setVAT(float value) {
        VAT.set(value);
    }
     
     public void setDiscount(float value) {
        Discount.set(value);
    }
    //Property values
    public StringProperty ProductIDProperty() {
        return ProductID;
    }

    public StringProperty ProductnameProperty() {
        return Productname ;
    }

    public FloatProperty Unit_PriceProperty() {
        return Unit_Price;
    }
    public IntegerProperty QuantityProperty() {
        return Quantity;
    }

    public FloatProperty AmountProperty() {
        return Amount;
    }
    
    public FloatProperty VATProperty() {
        return VAT;
    }
    
    public FloatProperty DiscountProperty() {
        return Discount;
    }
}
