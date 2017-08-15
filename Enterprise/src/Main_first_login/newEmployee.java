/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Rifat
 */
public class newEmployee {
 @FXML   
   private TextField employeeID;

@FXML
    private TextField employeeName;

@FXML
    private TextField designation;

@FXML
    private TextField branchName;
@FXML
    private TextField enrollment;

@FXML
    private TextField contact;

@FXML private TextField salary;






 @FXML
    void handleButtonAction(ActionEvent event) {
     
      try{
 
            Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Employee","root","besimple0");
         
            PreparedStatement mystat=null ;
          
        mystat = myconn.prepareStatement("insert into employeeinfo(EmployeeID,EmployeeName,Designation,Branchname,Enrollment,Contact,Salary) values(?,?,?,?,?,?,?)");
       
       mystat.setString(1,employeeID.getText());
       mystat.setString(2,employeeName.getText());
       mystat.setString(3,designation.getText());
       mystat.setString(4,branchName.getText());
       mystat.setString(5,enrollment.getText());
       mystat.setString(6,contact.getText()); 
     //  mystat.setDouble(7,Double.parseDouble(salary.getText()));System.out.println("hello");
     Float ab= Float.parseFloat(salary.getText());  
       mystat.setFloat(7,ab);
        mystat.executeUpdate();
   

        
      }
       catch(SQLException e){
           System.out.println(e);
       } 
 }
        @FXML
    private void back_Button(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("employee.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }

}
