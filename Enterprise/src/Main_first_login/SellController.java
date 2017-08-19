/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

import com.mysql.temptable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Abdullah Al Munzir
 */
public class SellController implements Initializable {

    /**
     * Initializes the controller class.
     */
    ArrayList proName = new ArrayList();
    
    @FXML private Label label_subtotal;
    @FXML private Label label_discount;
    @FXML private Label label_vat;
    @FXML private Label label_netbill;
    
    @FXML private Label qty_label;
    
    @FXML
    private TextField pro_text;
    
    @FXML
    private TextField pro_qty;
    
    @FXML
    private TextField pro_price;
    
    @FXML
    private TextField pro_discount;
    
    @FXML
    private TextField cust_name;
    
    @FXML
    private TextField cust_contact;
    
    @FXML
    private TextField cust_address;
    
    String temp;
    public float subtotal, totalp, discount, total_discount,D, net_bill;
    //public int D;
    public String id;
    
    @FXML
    public TableView<temptable> temp_table;
    @FXML
    private TableColumn<temptable,String> col1;
    @FXML
    private TableColumn<temptable, String> col2;
    @FXML
    private TableColumn<temptable, Float> col3;;
    @FXML
    private TableColumn<temptable, Integer> col4;
    @FXML
    private TableColumn<temptable, Float> col5;
    @FXML
    private TableColumn<temptable, Float> col6;
    @FXML
    private TableColumn<temptable, Float> col7;

    ObservableList<temptable> data;
    
    @FXML
    private void home_button(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main_Terminal.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    
    @FXML
    private void slaesReports_button(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("Sales_reports.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    
    
    
    
    @FXML
    void AddButtonAction(ActionEvent event) {
        
        if("0".equals(pro_qty.getText())){
            qty_label.setText("Must be at least 1");
        }
        else{
      
      try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
      
        PreparedStatement mystatement=null ;
        mystatement = myconn.prepareStatement("select ProductID, Price from store where Productname=?");
        temp= pro_text.getText();
       mystatement.setString(1,temp);
      
        ResultSet rs=mystatement.executeQuery();
        
      
        
        if(rs.next()){
            
            totalp=Float.parseFloat(rs.getString("Price"))*Float.parseFloat(pro_qty.getText());
            D = Float.parseFloat(pro_discount.getText());
            discount=(totalp*D)/100;
        
       PreparedStatement mystat=null ;
        
        
       mystat = myconn.prepareStatement("insert into sell_temp (ProductID,Productname,Unit_Price ,Quantity, Amount, VAT, Discount) values(?,?,?,?,?,?,?)");
       
       mystat.setString(1, rs.getString("ProductID"));
       mystat.setString(2,pro_text.getText());
       mystat.setFloat(3,Float.parseFloat(rs.getString("Price")));
       mystat.setInt(4,Integer.parseInt(pro_qty.getText()));
       mystat.setFloat(5, Float.parseFloat(rs.getString("Price"))*Float.parseFloat(pro_qty.getText())); 
       mystat.setFloat(6, 4);
       mystat.setFloat(7,discount);
       mystat.executeUpdate();
       
       
       
       subtotal= subtotal+totalp;
       
       total_discount=total_discount+discount;
       
       net_bill=subtotal-total_discount;
        
       }
      
      }
       catch(SQLException e){
           System.out.println(e);
       } 
      
      showtemptable(); // show product to the table one by one that will be added
      pro_text.setText("");// clearing the product text field
      pro_qty.setText("1");
      pro_discount.setText("0");
      qty_label.setText("");
        }
      
    }    
    
     @FXML
    private void reset(ActionEvent event) throws Exception {

    
        try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
        //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
        
         
        PreparedStatement mystat=null ;

        mystat = myconn.prepareStatement("delete from sell_temp");
        mystat.executeUpdate();

         } catch (SQLException ex) {
             System.err.println(ex);
         }
        
        showtemptable();
        pro_text.setText("");// clearing the product text field
        pro_qty.setText("1");
        pro_discount.setText("0");
        qty_label.setText("");
        label_subtotal.setText("0.0");
        label_discount.setText("0.0");
        label_vat.setText("0.0");
        label_netbill.setText("0.0");
        
        
}
    
    
    @FXML
    private void delete_product(ActionEvent event) throws Exception {

    int selectedIndex = temp_table.getSelectionModel().getSelectedIndex();
    temptable  selected = temp_table.getItems().get(selectedIndex);
    id = selected.getProductID();
    if ( 0== 0) {
       temp_table.getItems().remove(selectedIndex);
   
    } else {
        // Nothing selected.
        System.out.println("error");
    }
    
        subtract_price(); // subtract the price from subtotal before deleting
    
         try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
         //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
        
         
        PreparedStatement mystat=null ;

        mystat = myconn.prepareStatement("delete from sell_temp where ProductID=?");

        mystat.setString(1,id);

        mystat.executeUpdate();

         } catch (SQLException ex) {
             System.err.println(ex);
         }
}
    
    
    private void subtract_price(){
        
         try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
         //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
        PreparedStatement mystatement=null ;
        mystatement = myconn.prepareStatement("select Amount, VAT, Discount from sell_temp where ProductID=?");
        mystatement.setString(1,id);
        
        ResultSet rs = mystatement.executeQuery();
           
    if(rs.next()){
            
            float tp= Float.parseFloat(rs.getString("Amount"));
            float vt= Float.parseFloat(rs.getString("VAT"));
            //float dc= Float.parseFloat(rs.getString("Discount"));
            float dc=rs.getFloat("Discount");
            
            System.out.print(dc);
            
            if(subtotal>0){
                
            subtotal=subtotal-tp;
            
            total_discount=total_discount-dc;
            
            
            
            //discount
            }
           }
    
            mystatement.close();
        }
         
         catch (SQLException ex) {
            System.err.println(ex);
        }
}
 
    
    @FXML
    private void total(ActionEvent event) throws Exception {
        
        net_bill=subtotal-total_discount;
        
        String subt = Float.toString(subtotal);
       
        String dcount = Float.toString(total_discount);
        
        String nbill = Float.toString(net_bill);
        
        
        
        label_subtotal.setText(subt);
        label_discount.setText(dcount);
        label_netbill.setText(nbill);
    
    }
   
    
    public void showtemptable() {
        // TODO
          
       
        try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
            //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
       Statement mystat=myconn.createStatement();
       ResultSet  rs=mystat.executeQuery("select * from sell_temp");
           data = FXCollections.observableArrayList();
      
       while (rs.next()) {
              
                 data.add(new temptable(rs.getString(1), rs.getString(2), rs.getFloat(3), rs.getInt(4), rs.getFloat(5), rs.getFloat(6), rs.getFloat(7))); 
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        col1.setCellValueFactory(new PropertyValueFactory<>("ProductID"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Productname"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        col4.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        col5.setCellValueFactory(new PropertyValueFactory<>("Amount"));
        col6.setCellValueFactory(new PropertyValueFactory<>("VAT"));
        col7.setCellValueFactory(new PropertyValueFactory<>("Discount"));
      
   
        temp_table.setItems(null);
        temp_table.setItems(data);
        
        System.out.println("hello12");
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
         try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
         //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
       Statement mystat=myconn.createStatement();
       ResultSet  rs=mystat.executeQuery("select Productname from store");
       
       
       while(rs.next()){
           
           String p_name = rs.getString("Productname");
           proName.add(p_name);
        }
       
        } catch (SQLException ex) {
            System.err.println(ex);
        }
         
         
       TextFields.bindAutoCompletion(pro_text, proName);
       
       
    }  
}
