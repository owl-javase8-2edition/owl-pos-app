/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.owl.pos.vistas.helpers;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Herudi
 */
public final class StageManagementHelper {

    private StageManagementHelper() {
    }
    
    public static void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
    
    public static void newStage(Stage stage, Label lb, String load, String judul, boolean resize, StageStyle style, boolean maximized){
       try {
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();
            Parent root = FXMLLoader.load(StageManagementHelper.class.getResource(load));
            Scene scene = new Scene(root);
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(judul);
            st.setScene(scene);
            st.show();
            stage.close();
        } catch (Exception e) {
            Logger.getLogger(StageManagementHelper.class.getName()).log(Level.INFO,"Error loading stage.",e);
        } 
    }
    
    public static void newStage2(Stage stage, Button lb, String load, String judul, boolean resize, StageStyle style, boolean maximized){
       try {
            Stage st = new Stage();
            stage = (Stage) lb.getScene().getWindow();
            Parent root = FXMLLoader.load(StageManagementHelper.class.getResource(load));
            Scene scene = new Scene(root);
            st.initStyle(style);
            st.setResizable(resize);
            st.setMaximized(maximized);
            st.setTitle(judul);
            st.setScene(scene);
            st.show();
            stage.close();
        } catch (Exception e) {
            Logger.getLogger(StageManagementHelper.class.getName()).log(Level.INFO,"In new stage2",e);
        } 
    }
    
    public static void loadAnchorPane(AnchorPane ap, String a){
        try {
            AnchorPane p = FXMLLoader.load(StageManagementHelper.class.getResource("/fxml/"+a));
            ap.getChildren().setAll(p);
        } catch (Exception e) {
            Logger.getLogger(StageManagementHelper.class.getName()).log(Level.INFO,"Exception adding child pane.",e);
        }  
    }
}
