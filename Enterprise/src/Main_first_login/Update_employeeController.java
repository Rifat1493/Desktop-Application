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
public class Update_employeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private TextField EmployeeID;
    @FXML
    private TextField  EmployeeName;
    @FXML
    private TextField  Designation;
    @FXML
    private TextField  Branchname;
    @FXML
    private TextField  Enrollment;
    @FXML
    private TextField  Contact;
    @FXML
    private TextField  Salary;
   String temp;
        @FXML
    private void back_Button(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("employee.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
     @FXML
    void handleButtonAction(ActionEvent event) {
          try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","besimple0");
         PreparedStatement mystat=null ;
        mystat = myconn.prepareStatement("update employee set EmployeeID=?,EmployeeName=?,Designation=?,,Branchname=?,Enrollment=?,Contact=?,Salary=? where ProductID=?");
       
       mystat.setString(1, EmployeeID.getText());
       mystat.setString(2,EmployeeName.getText());
       mystat.setString(3, Designation.getText());
       mystat.setString(4,Branchname.getText());
       mystat.setString(5, Enrollment.getText());
       mystat.setString(6, Contact.getText());
       Float ab= Float.parseFloat(Salary.getText());  
       mystat.setFloat(7,ab);
      mystat.setString(8,temp);
       mystat.executeUpdate();
      
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
