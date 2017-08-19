/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

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
 * FXML Controller class
 *
 * @author Rifat
 */
public class Update_employeeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField EmployeeID;
    @FXML
    private TextField  Employeename;
 @FXML  private ComboBox<String> Designation;

@FXML private ComboBox<String> Branchname;
@FXML private  DatePicker Enrollment;
    @FXML
    private TextField  Contact;
    @FXML
    private TextField  Salary;
    
     ObservableList <String> listdesig = FXCollections.observableArrayList("Manager","Asst manager","Administrator","Worker"); 
 // should retriev from database
  ObservableList <String> listbran ;
    
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
          try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
         PreparedStatement mystat=null ;
         String datevalue=Date.valueOf(Enrollment.getValue()).toString();System.out.println("hello");
        mystat = myconn.prepareStatement("update employee set EmployeeID=?,Employeename=?,Designation=?,Branchname=?,Enrollment=?,Contact=?,Salary=? where EmployeeID=?");
       
       mystat.setString(1, EmployeeID.getText());
       mystat.setString(2,Employeename.getText());
       mystat.setString(3, Designation.getValue());
       mystat.setString(4,Branchname.getValue());
       mystat.setString(5, datevalue);
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
    
         @FXML
    void search_emp(ActionEvent event) {
       
          try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
         PreparedStatement mystat=null ;
        mystat = myconn.prepareStatement("select * from employee where EmployeeID=?");
        temp= EmployeeID.getText();
       mystat.setString(1,temp);
      
        ResultSet rs=mystat.executeQuery();
        if(rs.next()){
         Employeename.setText(rs.getString(2));
         Designation.setValue(rs.getString(3));
         Branchname.setValue(rs.getString(4));
      //   Enrollment.setText(Date.valueOf(rs.getInt(5)).toString());
         Contact.setText(rs.getString(6));
         Salary.setText(String.valueOf(rs.getString(7)));
           }
      }
       catch(SQLException e){
           System.out.println(e);
       } 

    

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              Designation.setItems(listdesig);
       
         try{  Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
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
