package Main_first_login;
import com.mysql.Tableproduct;
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
 * @author shifat
 */
public class Storecontroller implements Initializable {
    
     @FXML
    private TextField search_bar;
  
     @FXML
    public TableView<Tableproduct> table_product;
    @FXML
    private TableColumn<Tableproduct,String> col1;
    @FXML
    private TableColumn<Tableproduct, String> col2;
    @FXML
    private TableColumn<Tableproduct, String> col3;;
    @FXML
    private TableColumn<Tableproduct,String> col4;
    @FXML
    private TableColumn<Tableproduct, Integer> col5;
    @FXML
    private TableColumn<Tableproduct, Float> col6;

 ObservableList<Tableproduct> data;



  
   @FXML
    private void new_Product(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("newProduct.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    @FXML
    private void home_button1(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main_Terminal.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
        @FXML
    private void store_update(ActionEvent event) throws Exception {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("update_product.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
        
        
    }
            @FXML
    private void delete_product(ActionEvent event) throws Exception {

     int selectedIndex = table_product.getSelectionModel().getSelectedIndex();
Tableproduct  selected = table_product.getItems().get(selectedIndex);
 String id = selected.getProductID();
         try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
      PreparedStatement mystat=null ;
        mystat = myconn.prepareStatement("delete from store where ProductID=?");
       
       mystat.setString(1,id);
        mystat.executeUpdate();
        
        } catch (SQLException ex) {
            System.err.println(ex);
        }
      show();
   
}
    @FXML
public   void show(){
       
   try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
       Statement mystat=myconn.createStatement();
       ResultSet  rs=mystat.executeQuery("select * from store");
           data = FXCollections.observableArrayList();
      
       while (rs.next()) {
                //get string from db,whichever way 
                rs.getFloat(6);
               
                data.add(new Tableproduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6))); 
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        col1.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Productname"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Company"));
        col4.setCellValueFactory(new PropertyValueFactory<>("Procategory"));
        col5.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        col6.setCellValueFactory(new PropertyValueFactory<>("Price"));
   
       // table_product.setItems(null);
        //table_product.setItems(data);
    
    
    


        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Tableproduct> filteredData = new FilteredList<>(data, t -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search_bar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tableproduct -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (tableproduct.getProductID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (tableproduct.getProductname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Tableproduct> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table_product.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table_product.setItems(null);
        table_product.setItems(sortedData);
}    
        
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
  
         try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","besimple0");
       Statement mystat=myconn.createStatement();
       ResultSet  rs=mystat.executeQuery("select * from store");
           data = FXCollections.observableArrayList();
      
       while (rs.next()) {
                //get string from db,whichever way 
                rs.getFloat(6);
               
                data.add(new Tableproduct(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6))); 
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        col1.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Productname"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Company"));
        col4.setCellValueFactory(new PropertyValueFactory<>("Procategory"));
        col5.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        col6.setCellValueFactory(new PropertyValueFactory<>("Price"));
   
       // table_product.setItems(null);
        //table_product.setItems(data);
    
    
    


        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Tableproduct> filteredData = new FilteredList<>(data, t -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        search_bar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(tableproduct -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (tableproduct.getProductID().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (tableproduct.getProductname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Tableproduct> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table_product.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table_product.setItems(null);
        table_product.setItems(sortedData);
      
       

    }    
    
    
}
