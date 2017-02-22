/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.owl.pos.vistas.animations.FadeInLeftTransition;
import org.owl.pos.vistas.animations.FadeInLeftTransition1;
import org.owl.pos.vistas.animations.FadeInRightTransition;
import static org.owl.pos.vistas.helpers.StageManagementHelper.dialog;
import static org.owl.pos.vistas.helpers.StageManagementHelper.newStage;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class ControladorLogin implements Initializable {
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblUserLogin;
    @FXML
    private Text lblUsername;
    @FXML
    private Text lblPassword;
    @FXML
    private Button btnLogin;
    @FXML
    private Text lblRudyCom;
    @FXML 
    private Label lblClose;        
    Stage stage;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            new FadeInRightTransition(lblUserLogin).play();
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInLeftTransition1(lblPassword).play();
            new FadeInLeftTransition1(lblUsername).play();
            new FadeInLeftTransition1(txtUsername).play();
            new FadeInLeftTransition1(txtPassword).play();
            new FadeInRightTransition(btnLogin).play();
            lblClose.setOnMouseClicked((MouseEvent event) -> {
                Platform.exit();
                System.exit(0);
            });
        });
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) {
        if (txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) {
            newStage(stage, lblClose, "/fxml/Dashboard.fxml", "O.W.L. POS App", true, StageStyle.UNDECORATED, false);
        }else{
            dialog(Alert.AlertType.ERROR, "Error al iniciar sesión, verifique su usuario y contraseña.");
        }
    }
    
    @FXML
    public void loginOnEnter(KeyEvent e) {
        if(KeyCode.ENTER.equals(e.getCode())) {
            login(null);
        }
    }
}
