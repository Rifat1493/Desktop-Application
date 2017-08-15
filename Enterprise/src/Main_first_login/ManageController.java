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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rifat
 */
public class ManageController implements Initializable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private void back_button(ActionEvent event) throws IOException {
       
            
        Parent main_parent = FXMLLoader.load(getClass().getResource("Main_Terminal.fxml"));
        Scene main_scene = new Scene(main_parent);
        Stage main_stage =(Stage) ((Node) event.getSource()).getScene().getWindow();
        main_stage.setScene(main_scene);
        main_stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
