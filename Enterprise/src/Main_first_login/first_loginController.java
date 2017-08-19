/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.*;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author Rifat
 */
public class first_loginController implements Initializable {
   
    @FXML private Label login_label;
    @FXML private TextField Username; 
    @FXML private TextField Password;
    @FXML private TextField Branchname;
    int check=0;
   //String  dbname=Branchname.getText();
 public static String  dbname="head_office" ;
 String name="Head_office";
  String temp;
    @FXML
private void button_signin(ActionEvent event) throws Exception {
try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/"+name,"root","besimple0");
         PreparedStatement mystat=null ;
        mystat = myconn.prepareStatement("select*from login where Username=? and Password=? and  Branchname=?");
       
      mystat.setString(1,Username.getText());
       mystat.setString(2,Password.getText());
        mystat.setString(3,Branchname.getText());
        ResultSet myRs=mystat.executeQuery();
      
        if(myRs.next()){
            check=1;
        }
        else{
            check=0;
        }
      }
       catch(SQLException e){
           System.out.println(e);
       } 
 dbname =  Branchname.getText();
        if(check==1)
        {
            login_label.setText("Successfully logged in");
            
         /* ((Node)event.getSource()).getScene().getWindow().hide();
            Stage stage =new Stage();
              Parent root = FXMLLoader.load(getClass().getResource("Main_Terminal.fxml"));
        
        Scene scene = new Scene(root);
      
        scene.getStylesheets().add(getClass().getResource("Main_Terminal.css").toExternalForm());
        stage.setScene(scene);
//        stage.show();*/
        
      Parent main_parent = FXMLLoader.load(getClass().getResource("Main_Terminal.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
         }
        else
        {
            login_label.setText("Login failed");
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
       
    }
}