package Main_first_login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.mysql.temptable;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Abdullah Al-Munzir
 */
public class ReceiptController implements Initializable {
    
    

    
    @FXML
    private Label time_lb;
    @FXML
    private Label date_lb;
    @FXML
    private Label Cname_lb;
    @FXML
    private Label bill_label;
    
    @FXML private Label label_subtotal;
    @FXML private Label label_discount;
    @FXML private Label label_vat;
    @FXML private Label label_netbill;
    
    @FXML private AnchorPane anch_print;
    
    @FXML
    public TableView<temptable> recpt_table;
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
     
    ObservableList<temptable> data;
    

    /**
     * Initializes the controller class.
     * 
     */
    String Name;
    float subtotal, totalVAT, total_discount, net_bill;
    
    public void Cname(String n, float subt, float totalv, float totald, float netb) {
        
        System.out.println("3\n");
        
    Name=n;
    subtotal=subt;
    totalVAT=totalv;
    total_discount=totald;
    net_bill=netb;
    
    
        String sub = Float.toString(subtotal);
        
        String tVAT = Float.toString(totalVAT);
       
        String dcount = Float.toString(total_discount);
        
        String nbill = Float.toString(net_bill);
        
        
        Cname_lb.setText(Name);
        label_subtotal.setText(sub);
        label_discount.setText(dcount);
        label_netbill.setText(nbill);
        label_vat.setText(tVAT);
        
        System.out.println("4\n");
    }
    
    
    @FXML
     private void print(ActionEvent event) throws IOException{
     
         PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(anch_print.getScene().getWindow())){
            boolean success = job.printPage(anch_print);
            if (success) {
              job.endJob();
                }
            }
     }
     
      public void print(){
     
         PrinterJob job = PrinterJob.createPrinterJob();
            if (job != null && job.showPrintDialog(anch_print.getScene().getWindow())){
            boolean success = job.printPage(anch_print);
            if (success) {
              job.endJob();
                }
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
        //col1.setCellValueFactory(new PropertyValueFactory<>("Product_Id"));
        col2.setCellValueFactory(new PropertyValueFactory<>("Product_Name"));
        col3.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        col4.setCellValueFactory(new PropertyValueFactory<>("Unit_Price"));
        col5.setCellValueFactory(new PropertyValueFactory<>("Amount"));
       
      
   
        recpt_table.setItems(null);
        recpt_table.setItems(data);
        
        System.out.println("hello12");
    } 
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("1\n");
        // TODO\
        
        
        Calendar timer=Calendar.getInstance();
     
      SimpleDateFormat tTime= new SimpleDateFormat("h : mm : ss a");
     // tTime.format(timer.getTime());
      SimpleDateFormat tT= new SimpleDateFormat("ddHHss"); 
     
        SimpleDateFormat Tdate= new SimpleDateFormat("dd/MM/yyyy");
        
        date_lb.setText(Tdate.format(timer.getTime()));
        time_lb.setText(tTime.format(timer.getTime()));
        bill_label.setText(tT.format(timer.getTime()));
        
        
        
        System.out.println("2\n");
        
        showtemptable();
        
        
        
    }   
    
    
}
