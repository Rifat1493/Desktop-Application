/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ManageController implements Initializable {

    @FXML
    private JFXButton backButton;
    @FXML
    private JFXTextField Username;
    @FXML
    private JFXPasswordField Password;
    @FXML
    private JFXTextField Branchname;
    @FXML
    private JFXComboBox<String> BranchName;
      ObservableList <String> branchlist ;
    
    
    
    
    
    @FXML
    private void back_Button(ActionEvent event) throws IOException {  
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main_Terminal.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }   
        @FXML
    private void okButton(ActionEvent event) throws IOException {  
        Parent main_parent = FXMLLoader.load(getClass().getResource("manage.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }  
    @FXML
    void handleButtonAction(ActionEvent event) throws IOException {
         String branch=Branchname.getText();
      try{
         Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
        PreparedStatement mystat=null ;
         Statement stmt = null;
         mystat = myconn.prepareStatement("insert into login(Branchname,Username,Password) values(?,?,?)");
          myconn.setAutoCommit(false);
      stmt = myconn.createStatement();
         mystat.setString(1, branch);
       mystat.setString(2,Username.getText());
        mystat.setString(3,Password.getText());
        mystat.executeUpdate();
        String sql = "CREATE DATABASE "+branch;
        stmt.addBatch(sql);
        String sql1="Use "+branch;
        stmt.addBatch(sql1);
        String sql2="Create table Customer(Customername varchar(30),Contact varchar(30),Address varchar(30),Total_price double(20,2) not null,Total_quantity int not null,primary key (Customername))";
        stmt.addBatch(sql2);
        String sql3="Create table employee(EmployeeID varchar(20),Employeename varchar(20),Designation varchar(20),Branchname varchar(20),Enrollment date,Contact varchar(20),Salary double(20,2),Primary key(EmployeeID))";
        stmt.addBatch(sql3);
        String sql4= "create table Store(ProductID varchar(20),Productname varchar(20)not null,Company varchar(20)not null,Procategory varchar(20)not null,Quantity Int not null,Price double(20,2)not null,primary key (ProductID))";
        stmt.addBatch(sql4);
        int count[]=stmt.executeBatch();
        myconn.commit();
        
      }
       catch(SQLException e){
           System.out.println(e);
       }

   }
    



    @FXML
    void dropdatabase(ActionEvent event){
        String branch1=BranchName.getValue();
          try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
          Statement stmt1 = null;
        stmt1 = myconn.createStatement();
         String sql1 = "DROP DATABASE "+branch1;
         stmt1.executeUpdate(sql1);
         PreparedStatement stq=myconn.prepareStatement("delete from login where Branchname=?");
         stq.setString(1, branch1);
         stq.executeUpdate();
  
            }
       catch(SQLException e){
           System.out.println(e);
       }  
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         try{  Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
          Statement mystat=myconn.createStatement();
          ResultSet  rs=mystat.executeQuery("select Branchname from login");
      List<String> list=new ArrayList<String>();
                while (rs.next()) {
                  
                    String current = rs.getString(1);
                    list.add(current);
               
      }
               branchlist= FXCollections.observableArrayList(list);
                    BranchName.getItems().addAll(branchlist);
         
         }
       catch(SQLException e){
           System.out.println(e);
       } 
         
    }    
    
}
