/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;


import com.mysql.Tableproduct;
import com.mysql.temptable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdullah Al-Munzir
 */
public class Sales_reportsController implements Initializable {

    /**
     * Initializes the controller class.
     * 
     */
    
    @FXML
    private ComboBox <String> Total_sell_by;
    
    @FXML
    private TextField date_text;
    
    @FXML
    private TextField product_text;
    
    @FXML
    private TextField custname_text;
    
    @FXML
    private TextField custphone_text;
    
    @FXML
    public TableView<temptable> sr_table;
    @FXML
    private TableColumn<temptable,String> col1;
    @FXML
    private TableColumn<temptable, String> col2;
    @FXML
    private TableColumn<temptable, String> col3;
    @FXML
    private TableColumn<temptable,String> col4;
    @FXML
    private TableColumn<temptable,String> col5;
    @FXML
    private TableColumn<temptable, Float> col6;
    @FXML
    private TableColumn<temptable, Integer> col7;
    @FXML
    private TableColumn<temptable, Float> col8;
    @FXML
    private TableColumn<temptable, Float> col9;
    @FXML
    private TableColumn<temptable, Float> col10;
    @FXML
    private TableColumn<temptable, Float> col11;

    ObservableList<temptable> data;
    
    ObservableList <String> list = FXCollections.observableArrayList("Date", "Product", "Customer");
    
     @FXML
    private void back_button(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("Sell.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         Total_sell_by.setItems(list);
         
          try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
       Statement mystat=myconn.createStatement();
       ResultSet  rs=mystat.executeQuery("select * from sales_report order by Date_Time desc");
           data = FXCollections.observableArrayList();
      
       while (rs.next()) {
                //get string from db,whichever way 
                //rs.getFloat(6);
               
                data.add(new temptable(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getFloat(6), rs.getInt(7), rs.getFloat(8),rs.getFloat(9),rs.getFloat(10),rs.getFloat(11))); 
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        col1.setCellValueFactory(new PropertyValueFactory<>("Cust_Name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Cust_Phone"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Date_Time"));
        col4.setCellValueFactory(new PropertyValueFactory<>("Product_Id"));
        col5.setCellValueFactory(new PropertyValueFactory<>("Product_Name"));
        col6.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        col7.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        col8.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        col9.setCellValueFactory(new PropertyValueFactory<>("VAT"));
        col10.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        col11.setCellValueFactory(new PropertyValueFactory<>("Net_Amount"));
   
       sr_table.setItems(null);
       sr_table.setItems(data);
         
         
    }    
    
}
