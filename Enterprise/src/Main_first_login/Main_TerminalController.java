/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package Main_first_login;
package Main_first_login;

//import static Main_first_login.ClientController.username;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Abdullah Al Munzir
 */
public class Main_TerminalController implements Initializable {

    /**
     * Initializes the controller class.
     */

    @FXML private Label msglabel;
    static int a=1;
    @FXML
    private void button_sell(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("Sell.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    
    @FXML
    private void log_out(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("first_login.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    
    @FXML
    private void button_store (ActionEvent event)throws Exception{
        
        Parent main = FXMLLoader.load(getClass().getResource("Store.fxml"));
        Scene main_scene = new Scene(main);
        Stage mainstage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        mainstage.setScene(main_scene);
        mainstage.show();
 }  
    
    @FXML
    private void button_employee(ActionEvent event)throws Exception{
        
        Parent main = FXMLLoader.load(getClass().getResource("employee.fxml"));
        Scene main_scene = new Scene(main);
        Stage mainstage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        mainstage.setScene(main_scene);
        mainstage.show();
  }  
       @FXML
    private void manage (ActionEvent event)throws Exception{
        
        Parent main = FXMLLoader.load(getClass().getResource("manage.fxml"));
        Scene main_scene = new Scene(main);
        Stage mainstage=(Stage) ((Node) event.getSource()).getScene().getWindow();
        mainstage.setScene(main_scene);
        mainstage.show();
 }    
        @FXML
    private void message(ActionEvent event) throws IOException {
        Parent main_parent = FXMLLoader.load(getClass().getResource("Client.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
     msglabel.setText("You are signed in as :   "+first_loginController.dbname);
     }
 
    }   
       
        
    

