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
import java.sql.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Rifat
 */
public class newEmployee implements Initializable{
 @FXML   
   private TextField EmployeeID;
@FXML
    private TextField Employeename;

@FXML  private ComboBox<String> Designation;

@FXML private ComboBox<String> Branchname;
@FXML private  DatePicker Enrollment;

@FXML private TextField Contact;
@FXML private TextField Salary;

 ObservableList <String> listdesig = FXCollections.observableArrayList("Manager","Asst manager","Administrator","Worker"); 
 // should retriev from database
  ObservableList <String> listbran ;




 @FXML
    void handleButtonAction(ActionEvent event) {
      try{
 
            Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
         PreparedStatement mystat=null ;
         String datevalue=Date.valueOf(Enrollment.getValue()).toString();
         mystat = myconn.prepareStatement("insert into employee(EmployeeID,Employeename,Designation,Branchname,Enrollment,Contact,Salary) values(?,?,?,?,?,?,?)");
       mystat.setString(1,EmployeeID.getText());
       mystat.setString(2,Employeename.getText());
       mystat.setString(3,Designation.getValue());
       mystat.setString(4,Branchname.getValue());
       mystat.setString(5,datevalue);
       mystat.setString(6,Contact.getText()); 
      Float ab= Float.parseFloat(Salary.getText());  
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       Designation.setItems(listdesig);
       
         try{  Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
          Statement mystat=myconn.createStatement();
          ResultSet  rs=mystat.executeQuery("select Branchname from login");
      List<String> list=new ArrayList<String>();
                while (rs.next()) {
                  
                    String current = rs.getString(1);
                    list.add(current);
               
      }
               listbran= FXCollections.observableArrayList(list);
                    Branchname.getItems().addAll(listbran);
         
         }
       catch(SQLException e){
           System.out.println(e);
       } 
         
    

}
    }

