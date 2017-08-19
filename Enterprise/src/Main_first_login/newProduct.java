/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Shifat
 */
public class newProduct implements Initializable {
    

@FXML
    private TextField ProductID;

@FXML
    private TextField Quantity;

@FXML
    private TextField Price;

@FXML
    private TextField Company;


@FXML
    private TextField Productname;
@FXML
  private ComboBox <String> Procategory;
  ObservableList <String> list = FXCollections.observableArrayList("Clothing","Footwear","Food","Machineries"); 



 @FXML
    void handleButtonAction(ActionEvent event) {
      
      try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
         PreparedStatement mystat=null ;
        mystat = myconn.prepareStatement("insert into store (ProductID,Productname,Company,Procategory,Quantity,Price) values(?,?,?,?,?,?)");
       
       mystat.setString(1,ProductID.getText());
       mystat.setString(2,Productname.getText());
       mystat.setString(3,Company.getText());
       mystat.setString(4,Procategory.getValue());
       mystat.setInt(5,Integer.parseInt(Quantity.getText()));
       mystat.setFloat(6,Float.parseFloat(Price.getText()));      
        mystat.executeUpdate();
         
      }
       catch(SQLException e){
           System.out.println(e);
       } 
 }


    @FXML
    private void back_Button(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("Store.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           Procategory.setItems(list);
    }  
}
