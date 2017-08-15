/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author Abdullah Al Munzir
 * @author shifat
 */
public class employeeController implements Initializable {



    /*@FXML
    private TableColumn<?, ?> EmployeeID;
    @FXML
    private TableColumn<?,? > EmployeeName;
    @FXML
    private TableColumn<?, ?> Designation;
    @FXML
    private TableColumn<?,?> Branchname;
    @FXML
    private TableColumn<?,?> Enrollment;
    @FXML
    private TableColumn<?,?> Contact;
    @FXML
    private TableColumn<?,?> Salary;
*/
    @FXML  
    private TableView table_employee;
     private ObservableList<ObservableList> data;
    
    /*DBconnection connection= (DBconnection) DBconnection.loginConnector();
    ObservableList<employeetable>data=FXCollections.observableArrayList();
    PreparedStatement preparedStatement=null;
    ResultSet rs=null; */


    @FXML
    private void button_home(ActionEvent event) throws IOException {

        Parent main_parent = FXMLLoader.load(getClass().getResource("Main_Terminal.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }

    @FXML
    private void employee_insert(ActionEvent event) throws IOException {

        Parent main_parent = FXMLLoader.load(getClass().getResource("newEmployee.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }

    @FXML
    private void employee_update(ActionEvent event) throws IOException {

        Parent main_parent = FXMLLoader.load(getClass().getResource("update_employee.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
            @FXML
    private void delete_employee(ActionEvent event) throws Exception {

    int selectedIndex = table_employee.getSelectionModel().getSelectedIndex();
                System.out.println(selectedIndex );

    if (selectedIndex >= 0) {
        table_employee.getItems().remove(selectedIndex);
   
    } else {
       
        System.out.println("error in data deletion");
    }
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {

       /* EmployeeID.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        EmployeeName.setCellValueFactory(new PropertyValueFactory<>("EmployeeName"));
        Designation.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        Branchname.setCellValueFactory(new PropertyValueFactory<>("Branchname"));
        Enrollment.setCellValueFactory(new PropertyValueFactory<>("Enrollment"));
        Contact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        Salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));*/
           //TABLE VIEW AND DATA



         
          data = FXCollections.observableArrayList();
          try{
             Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/employee","root","besimple0");
       
        data = FXCollections.observableArrayList();
       Statement mystat=myconn.createStatement();
     
            String SQL = "SELECT * from CUSTOMer";
            //ResultSet
            ResultSet  rs=mystat.executeQuery("select * from employeeinfo");

            /**********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             **********************************/
            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;                
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
                    }                    
                });
                           table_employee.getColumns().addAll(col); 
                System.out.println("] "+"Column ["+i);
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            table_employee.setItems(data);
          }catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");             
          }
      }

    }

  /*  public void loaddatabase(){ 

        String query="select * from employeeinfo";
        try {
            preparedStatement=connection.prepareStatement(query);
            rs=preparedStatement.executeQuery();
            while(rs.next())
            {
                data.add(new employeetable(
                rs.getString("EmployeeID"), 
                rs.getString("EmployeeName"),
                 rs.getString("Designation"),
                  rs.getString("Branchname"),
                  rs.getString("Enrollment"),
                   rs.getString("Contact"),
                    rs.getString("Salary")
                ) );
            }
            preparedStatement.close();
            rs.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
}    
    }
 */

