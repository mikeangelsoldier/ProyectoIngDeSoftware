/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import vista.loginMeta;


public class MenuPrincipalController implements Initializable {

 @FXML
 public Menu menuGestionar,menuVer,menuCatalogos,menuPermisos; 
 
 @FXML
 public MenuItem itemGrupos,itemFechasParciales,itemCaliParcial,itemUnidadesParcial,itemGraficaGrupo,itemGraficaEstudiante,itemGestionar,itemPermisosValidados,itemValidarPermiso;
 
 @FXML
 Pane panelPrincipal;
 
      @FXML   
    private void handleItemGestionarGrupos(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaGestionGrupos.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
       @FXML   
    private void handleItemFechasParciales(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaFechasParciales.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
       @FXML   
    private void handleItemCaliParcial(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaGestionParcial.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
       @FXML   
    private void handleItemUnidadesParcial(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaUnidadesPorParcial.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     } 
        
    }
       @FXML   
    private void handleItemGraficaGrupo(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaGraficosYEstadisticasProfesor.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    } 
     
         @FXML   
    private void handleItemGraficaEstudiante(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaGraficosYEstadisticasAlumno.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
         @FXML   
    private void handleItemGestionar(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaAlumno.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
         @FXML   
    private void handleItemPermisosValidados(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaPermisosValidadosYExpirados.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
         @FXML   
    private void handleItemValidarPermiso(ActionEvent e) throws IOException{
           try {
         panelPrincipal.getChildren().add(FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaValidacionPermisos.fxml")));
     } catch (IOException ex) {
         Logger.getLogger(MenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
    
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        System.out.println("YO SOY EL "+loginMeta.tipoUsuario);
     /*   if(loginMeta.tipoUsuario.equals("profesor")){
            itemGrupos.setVisible(false);
        }*/
    }    

    public Menu getMenuVer() {
        return menuVer;
    }

    public void setMenuVer(Menu menuVer) {
        this.menuVer = menuVer;
    }
     
}
