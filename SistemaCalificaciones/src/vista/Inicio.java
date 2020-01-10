/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
 
/**
 *
 * @author alfre
 */
public class Inicio extends Application {
     
    @Override 
    public void start(Stage stage) throws Exception { 
      //  Parent root = FXMLLoader.load(getClass().getResource("VistaGestionGrupos.fxml")); 
//        Parent root = FXMLLoader.load(getClass().getResource("VistaAlumno.fxml")); 
      Parent root = FXMLLoader.load(getClass().getResource("VistaLogin.fxml"));
     //    Parent root = FXMLLoader.load(getClass().getResource("MenuPrincipal.fxml"));
        
 //Parent root = FXMLLoader.load(getClass().getResource("VistaGestionParcial.fxml"));
 
    //Parent root = FXMLLoader.load(getClass().getResource("VistaGraficosYEstadisticasAlumno.fxml"));

        
        Scene scene = new Scene(root); 
        
        stage.setScene(scene);
        stage.show();
    }  
   

    public static void main(String[] args) { 
         
        launch(args);
    }
    
} 
