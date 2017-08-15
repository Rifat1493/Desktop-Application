/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rifat
 */

public class Update_productController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField ProductID;

@FXML
    private TextField Quantity;

@FXML
    private TextField Price;

@FXML
    private TextField Company;

@FXML
    private TextField Procategory;
@FXML
    private TextField Productname;
   String temp;
      @FXML
    private void back_Button(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("Store.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    
 @FXML
    void handleButtonAction(ActionEvent event) {
       
    try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
         PreparedStatement mystat=null ;
        mystat = myconn.prepareStatement("update store set ProductID=?,Productname=?,Company=?,Procategory=?,Quantity=?,Price=? where ProductID=?");
       
       mystat.setString(1,ProductID.getText());
       mystat.setString(2,Productname.getText());
       mystat.setString(3,Company.getText());
       mystat.setString(4,Procategory.getText());
       mystat.setInt(5,Integer.parseInt(Quantity.getText()));
       mystat.setFloat(6,Float.parseFloat(Price.getText()));  
      mystat.setString(7,temp);
       mystat.executeUpdate();
      
      }
       catch(SQLException e){
           System.out.println(e);
       }       

    

    }
     @FXML
    void search(ActionEvent event) {
          try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
         PreparedStatement mystat=null ;
        mystat = myconn.prepareStatement("select * from store where ProductID=?");
        temp= ProductID.getText();
       mystat.setString(1,temp);
      
        ResultSet rs=mystat.executeQuery();
        if(rs.next()){
         Productname.setText(rs.getString(2));
         Company.setText(rs.getString(3));
         Procategory.setText(rs.getString(4));
         Quantity.setText(String.valueOf(rs.getInt(5)));
         Price.setText(String.valueOf(rs.getString(6)));
           }
      }
       catch(SQLException e){
           System.out.println(e);
       } 

    

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
