/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.owl.pos.vistas.animations.FadeInLeftTransition;
import org.owl.pos.vistas.animations.FadeInRightTransition;
import org.owl.pos.vistas.animations.FadeInTransition;
import static org.owl.pos.vistas.helpers.StageManagementHelper.newStage;

/**
 * FXML Controller class
 *
 * @author Herudi
 */
public class ControladorSplash implements Initializable {
    @FXML
    private Text lblWelcome;
    @FXML
    private Text lblRudy;
    @FXML
    private VBox vboxBottom;
    @FXML
    private Label lblClose;
    Stage stage;
    @FXML
    private ImageView imgLoading;

    /**
     * Controller optional initialization method
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        longStart();
        lblClose.setOnMouseClicked((MouseEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
    }   
    
    private void longStart() {
        Service<String> service = new Service<String>() {
            @Override
            protected Task<String> createTask() {
                return new Task<String>() {           
                    @Override
                    protected String call() throws Exception {
                        
                        //Realizar toda la inicialización en segundo plano aquí
                        Thread.sleep(4000);
                        return "Started!";
                    }
                    
                };
            }
        };
        
        service.start();
        
        service.setOnRunning((WorkerStateEvent event) -> {
            new FadeInLeftTransition(lblWelcome).play();
            new FadeInRightTransition(lblRudy).play();
            new FadeInTransition(vboxBottom).play();
        });
        
        service.setOnSucceeded((WorkerStateEvent event) -> {
            newStage(stage, lblClose, "/fxml/Login.fxml", "O.W.L. POS App", true, StageStyle.UNDECORATED, false);
        });
    } 
}
