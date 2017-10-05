/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

import com.mysql.temptable;
import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
    @FXML private AnchorPane NewAP;
    @FXML private AnchorPane root;
    
    @FXML
    private CheckBox vat_ck;
    @FXML
    private TextField pro_text;
    @FXML
    private TextField pro_qty;
    @FXML
    private TextField pro_price;
    @FXML
    private TextField pro_discount;
    @FXML
    public TextField cust_name;
    @FXML
    private TextField cust_contact;
    @FXML
    private TextField cust_address;
    String temp;
    
    public float subtotal, totalp, discount,totalVAT, VAT, total_discount,D,V, net_bill;
    
    public String id, C_name_receipt;
    
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
        
        
        
        if("".equals(cust_contact.getText()) || "".equals(pro_qty.getText()) || "0".equals(pro_qty.getText())){
            
            //***Dialog box***//
            Alert alert = new Alert(Alert.AlertType.ERROR);
            
            //***Setting icon of alert box***//
            Stage stage =(Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(this.getClass().getResource("/bag4.png").toString()));
            
            
            alert.setTitle("ERROR");
            alert.setHeaderText("Ooopss, there was an error!");

            if("".equals(cust_contact.getText())){
                alert.setContentText("Contact Number is required.\n");
            }
            else{
                alert.setContentText("Quantity must be at least One.");
            }
            alert.showAndWait();
        }
        else{      
            
            if(vat_ck.isSelected()){
                V=4;}
            else{
                V=0;}
      
      try{Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
      
        PreparedStatement mystatement=null ;
        mystatement = myconn.prepareStatement("select ProductID, Price from store where Productname=?");
        temp= pro_text.getText();
       mystatement.setString(1,temp);
      
        ResultSet rs=mystatement.executeQuery();
        
      
        
        if(rs.next()){
            
            totalp=Float.parseFloat(rs.getString("Price"))*Float.parseFloat(pro_qty.getText());
            D = Float.parseFloat(pro_discount.getText());
            discount=(totalp*D)/100;
            VAT=(totalp*V)/100;
            
           
        
       PreparedStatement mystat=null ;
        
        
       mystat = myconn.prepareStatement("insert into sell_temp (Cust_Name, Cust_Phone, Product_Id,Product_Name,Unit_Price ,Qty, Amount, VAT, Discount, Net_Amount) values(?,?,?,?,?,?,?,?,?,?)");
       
       mystat.setString(1,cust_name.getText());
       mystat.setString(2,cust_contact.getText());
       mystat.setString(3, rs.getString("ProductID"));
       mystat.setString(4,pro_text.getText());
       mystat.setFloat(5,Float.parseFloat(rs.getString("Price")));
       mystat.setInt(6,Integer.parseInt(pro_qty.getText()));
       mystat.setFloat(7, Float.parseFloat(rs.getString("Price"))*Float.parseFloat(pro_qty.getText())); 
       mystat.setFloat(8, VAT);
       mystat.setFloat(9,discount);
       mystat.setFloat(10,totalp-discount);
       mystat.executeUpdate();
       
       
       
       subtotal= subtotal+totalp;
       
       total_discount=total_discount+discount;
       
       totalVAT=totalVAT+VAT;
       
       net_bill=subtotal-total_discount+totalVAT;
        
       }
      
      }
       catch(SQLException e){
           System.out.println(e);
       } 
      
      showtemptable(); // show product to the table one by one that will be added
      pro_text.setText("");// clearing the product text field
      pro_qty.setText("1");
      pro_discount.setText("0");
      
            }
       
        total();
       
        
      
    }    
    
     @FXML
    private void reset(ActionEvent event) throws Exception {

    
        delete_tempTable();
        
        showtemptable();
        cust_name.setText("");
        cust_contact.setText("");
        cust_address.setText("");
        pro_text.setText("");// clearing the product text field
        pro_qty.setText("1");
        pro_discount.setText("0");
        label_subtotal.setText("0.0");
        label_discount.setText("0.0");
        label_vat.setText("0.0");
        label_netbill.setText("0.0");
        subtotal=0;
        total_discount=0;
        totalVAT=0;
        net_bill=0;

        
        
}
    
    
    @FXML
    private void delete_product(ActionEvent event) throws Exception {

    int selectedIndex = temp_table.getSelectionModel().getSelectedIndex();
    temptable  selected = temp_table.getItems().get(selectedIndex);
    id = selected.getProduct_Id();
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

        mystat = myconn.prepareStatement("delete from sell_temp where Product_Id=?");

        mystat.setString(1,id);

        mystat.executeUpdate();

         } catch (SQLException ex) {
             System.err.println(ex);
         }
         
        total();
}
    
    
    private void subtract_price(){
        
         try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
         //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
        PreparedStatement mystatement=null ;
        mystatement = myconn.prepareStatement("select Amount, VAT, Discount from sell_temp where Product_Id=?");
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
    
    private void delete_tempTable(){
     
        try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
        //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
        
         
        PreparedStatement mystat=null ;

        mystat = myconn.prepareStatement("delete from sell_temp");
        mystat.executeUpdate();

         } catch (SQLException ex) {
             System.err.println(ex);
         }
        
        
    }
 
    
 
    public void total(){
        
        //net_bill=subtotal-total_discount;
        net_bill=subtotal-total_discount+totalVAT;
        
        String subt = Float.toString(subtotal);
        
        String tVAT = Float.toString(totalVAT);
       
        String dcount = Float.toString(total_discount);
        
        String nbill = Float.toString(net_bill);
        
        
        
        label_subtotal.setText(subt);
        label_discount.setText(dcount);
        label_netbill.setText(nbill);
        label_vat.setText(tVAT);
    
    }
    
    
    @FXML
    private void Confirm(ActionEvent event) throws Exception {
        
       try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
         //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
         
     
        
       Statement mystat=myconn.createStatement();
       int r = mystat.executeUpdate("insert into sales_report select * from sell_temp");
       
       if(r==0){
       System.out.println("Not affected");
       }
       else{
           System.out.println("Affected");
       }
      
       
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    
    }
    
    @FXML
     private void receipt(ActionEvent event) throws IOException{
         
        /* Calendar timer=Calendar.getInstance();
     
      SimpleDateFormat tTime= new SimpleDateFormat("HH:mm:ss a");
     // tTime.format(timer.getTime());
      
        SimpleDateFormat Tdate= new SimpleDateFormat("dd-MMM-yyyy");
          Stage primarystage=new Stage(); 
       FileChooser fileChooser = new FileChooser();
  
              //Set extension filter
              FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF files", "*.pdf");
              fileChooser.getExtensionFilters().add(extFilter);
              
              //Show save file dialog
              File file = fileChooser.showSaveDialog(primarystage);
              
               final String receipt = "Enterprise Solution\n + \n Money Receipt"+"\nDate:"+Tdate.format(timer.getTime())+"\t\t\t\t\t\t\t\tTime:"+ tTime.format(timer.getTime())+"\n\n\t\t\t\t\t\tThank you";
              
              if(file != null){
                  SaveFile(receipt, file);
              }
              // Node node = new Circle(100, 200, 200);
            PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(NewAP.getScene().getWindow())){
            boolean success = job.printPage(NewAP);
            if (success) {
              job.endJob();
         
    }
}*/             
                
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Recpt.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                
                
                ReceiptController controller = fxmlLoader.<ReceiptController>getController();
                controller.Cname(cust_name.getText(), subtotal, totalVAT, total_discount, net_bill);
                
                
                Stage stage = new Stage();
                
                stage.setTitle("Invoice");
        
                Image micon = new Image("/bag4.png");
                stage.getIcons().add(micon);
                
                
                
                
                //rc.Cname("Munzir");
                
                
                
                stage.setScene(new Scene(root1));  
                
                //stage.setResizable(false);
                stage.show();
                
                System.out.println("5");
                
                controller.print();
     
     }
    
    
 private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;
             
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            
        }
         
    }
   
    
    public void showtemptable() {
          
       
        try{ Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","12345");
            //Connection myconn= DriverManager.getConnection("jdbc:mysql://localhost:3306/head_office","root","p123456");
       Statement mystat=myconn.createStatement();
       ResultSet  rs=mystat.executeQuery("select * from sell_temp");
           data = FXCollections.observableArrayList();
      
       while (rs.next()) {
              
                 data.add(new temptable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), rs.getFloat(6), rs.getInt(7), rs.getFloat(8), rs.getFloat(9), rs.getFloat(10),rs.getFloat(11))); 
            }

        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        col1.setCellValueFactory(new PropertyValueFactory<>("Product_Id"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Product_Name"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        col4.setCellValueFactory(new PropertyValueFactory<>("Qty"));
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
        mystat.close();
       
        } catch (SQLException ex) {
            System.err.println(ex);
        }
         
        delete_tempTable();
         
         
       TextFields.bindAutoCompletion(pro_text, proName);
       
       
       
    }  
}
