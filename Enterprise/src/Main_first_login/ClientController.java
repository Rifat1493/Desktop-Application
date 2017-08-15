/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main_first_login;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rifat
 */
public class ClientController implements Initializable {
    @FXML
    public  Label username;
     ClientNickName cl;
     Thread nickName;
    String nick =first_loginController.dbname;
    @FXML public   TextArea inputbox; 
    @FXML public    TextArea outputbox;
    @FXML public    TextArea OnlineLable;
    @FXML public   Button sendMessage;
    @FXML
    private void home_button1(ActionEvent event) throws IOException {
       
                 
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main_Terminal.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ClientSetting.inputbox = inputbox;
      ClientSetting.outputbox = outputbox;
      ClientSetting.sendMessage = sendMessage;
      ClientSetting.OnlineLable = OnlineLable;

      ClientSetting.username = username;
    
       
     String ipaddress = "localhost";

    System.err.println("ip........................"+ipaddress+"\n"+"****"+nick);
    cl  = new ClientNickName(ipaddress,nick); 
    username.setText("You Are Signed In As :\t "+nick);
    nickName = new Thread(cl);
    nickName.start();  
     
     }
     }

