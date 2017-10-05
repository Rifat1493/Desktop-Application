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
    private final StringProperty Cust_Name;
    private final StringProperty Cust_Phone;
    private final StringProperty Date_Time;
    private final StringProperty Product_Id;
    private final StringProperty Product_Name;
    private final FloatProperty Unit_Price;
    private final IntegerProperty Qty;
    private final FloatProperty Amount;
    private final FloatProperty VAT;
    private final FloatProperty Discount;
    private final FloatProperty Net_Amount;
    

    //Default constructor
    public temptable(String Cust_Name, String Cust_Phone, String Date_Time, String Product_Id, String Product_Name, float Unit_Price, int Qty, float Amount, float VAT, float Discount, float Net_Amount) {
        this.Cust_Name=new SimpleStringProperty(Cust_Name);
        this.Cust_Phone=new SimpleStringProperty(Cust_Phone);
        this.Date_Time=new SimpleStringProperty(Date_Time);
        this.Product_Id=new SimpleStringProperty( Product_Id);
        this.Product_Name = new SimpleStringProperty(Product_Name);
        this.Unit_Price = new SimpleFloatProperty(Unit_Price);
        this.Qty = new SimpleIntegerProperty(Qty);
        this.Amount = new SimpleFloatProperty(Amount);
        this.VAT = new SimpleFloatProperty(VAT);
        this.Discount = new SimpleFloatProperty(Discount);
        this.Net_Amount = new SimpleFloatProperty(Net_Amount);
        
    }

    public temptable(String string, float aFloat, int aInt, float aFloat0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //Getters
    public  String getCust_Name() {
        return Cust_Name.get();
    }
    
    public  String getCust_Phone() {
        return Cust_Phone.get();
    }
    
    public  String getDate_Time() {
        return Date_Time.get();
    }
    
    public  String getProduct_Id() {
        return Product_Id.get();
    }

    public String getProduct_Name () {
        return Product_Name.get();
    }

    public float getUnit_Price() {
        return Unit_Price.get();
    }
    public int getQty() {
        return Qty.get();
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
    
     public float getNet_Amount() {
        return Net_Amount.get();
    }
     
     

    //Setters
     public void setCust_Name(String value) {
       Cust_Name.set(value);
    }
     
     public void setCust_Phone(String value) {
        Cust_Phone.set(value);
    }
     
     public void setDate_Time(String value) {
       Date_Time.set(value);
    }

     
    public void setProduct_Id(String value) {
        Product_Id.set(value);
    }

    public void setProduct_Name (String value) {
        Product_Name .set(value);
    }

    public void setUnit_Price(float value) {
       Unit_Price.set(value);
    }
    public void setQty(int value) {
        Qty.set(value);
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
     
      public void setNet_Amount(float value) {
        Net_Amount.set(value);
    }
     
     
    //Property values
      public StringProperty Cust_NameProperty() {
        return Cust_Name;
    }

       public StringProperty Cust_PhoneProperty() {
        return Cust_Phone;
    }

        public StringProperty Date_TimeProperty() {
        return Date_Time;
    }

    public StringProperty Product_IdProperty() {
        return Product_Id;
    }

    public StringProperty Product_NameProperty() {
        return Product_Name ;
    }

    public FloatProperty Unit_PriceProperty() {
        return Unit_Price;
    }
    public IntegerProperty QtyProperty() {
        return Qty;
    }

    public FloatProperty AmountProperty() {
        return Amount;
    }
    
    public FloatProperty VATProperty() {
        return VAT;
    }
   
     public FloatProperty Net_AmountProperty() {
        return Net_Amount;
    }
}
