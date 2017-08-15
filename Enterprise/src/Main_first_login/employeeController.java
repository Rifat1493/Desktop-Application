/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;
import com.mysql.Tableemployee;
import com.mysql.Tableproduct;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 *
 * @author Abdullah Al Munzir
 * @author shifat
 */
public class employeeController implements Initializable {
@FXML
    private TextField search_bar;
  

     @FXML
    public TableView<Tableemployee> table_employee;
    @FXML
    private TableColumn<Tableemployee,String> col1;
    @FXML
  private TableColumn<Tableemployee,String> col2;
    @FXML
   private TableColumn<Tableemployee,String> col3;
    @FXML
private TableColumn<Tableemployee,String> col4;
    @FXML
   private TableColumn<Tableemployee,String> col5;
    @FXML
   private TableColumn<Tableemployee,String> col6;
    @FXML
   private TableColumn<Tableemployee, Float> col7;
 ObservableList<Tableemployee> data;

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
Tableemployee  selected = table_employee.getItems().get(selectedIndex);
 String id = selected.getEmployeeID();
    if ( 0== 0) {
       table_employee.getItems().remove(selectedIndex);
   
    } else {
        // Nothing selected.
        System.out.println("error");
    }
         try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
      PreparedStatement mystat=null ;
        mystat = myconn.prepareStatement("delete from employee where EmployeeID=?");
       
       mystat.setString(1,id);
        mystat.executeUpdate();
        
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
    
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
       Statement mystat=myconn.createStatement();
       ResultSet  rs=mystat.executeQuery("select * from employee");
           data = FXCollections.observableArrayList();
      
       while (rs.next()) {
                //get string from db,whichever way 
                rs.getFloat(7);
               
                data.add(new Tableemployee(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getFloat(7))); 
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        col1.setCellValueFactory(new PropertyValueFactory<>("EmployeeID"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Employeename"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        col4.setCellValueFactory(new PropertyValueFactory<>("Branchname"));
        col5.setCellValueFactory(new PropertyValueFactory<>("Enrollment"));
        col6.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        col7.setCellValueFactory(new PropertyValueFactory<>("Salary"));
   
     //   table_employee.setItems(null);
    //    table_employee.setItems(data);
    
        FilteredList<Tableemployee> filteredData = new FilteredList<>(data, t -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search_bar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tableproduct -> { //tableproduct line can be used by other name
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (tableproduct.getEmployeeID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (tableproduct.getEmployeename().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Tableemployee> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table_employee.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table_employee.setItems(sortedData);
      
    }    
    
    }
