/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import AccesoBD.ConectaBD_IS;
import AccesoBD.CarreraBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Carrera;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class ControladorCarrera implements Initializable {

    /**
     * Initializes the controller class.
     */
    ConectaBD_IS conectaBD;
    CarreraBD carreraBD;
    TextField[] txtCAMPOS;
    
    @FXML
    private JFXTextField txtIdCarrera, txtNombre, txtDescripcion;
    
    @FXML
    private TableView<Carrera> tblDatosCarrera;
    
    @FXML
    private TableColumn<Carrera, String> tbcIdCarrera, tbcNombre, tbcDescripcion, tbcUsuarioAlta, tbcFechaAlta, tbcUsuarioMod, tbcFechaMod;
    
    @FXML
    private JFXButton btnAgregar, btnActualizar, btnEliminar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD = new ConectaBD_IS();
        carreraBD = new CarreraBD(conectaBD.getConnection());
        llenarTabla(carreraBD.getCarreras());
        txtCAMPOS = new TextField[]{txtIdCarrera, txtNombre, txtDescripcion};
    }   
    
    @FXML
    private void handleButtonAgregar(ActionEvent event) {
        agregarCarrera();
        llenarTabla(carreraBD.getCarreras());
    }
    
    @FXML
    private void handleButtonActualizar(ActionEvent event) {
        actualizarCarrera();
        llenarTabla(carreraBD.getCarreras());
    }
    
    @FXML
    private void handleButtonEliminar(ActionEvent event) {
        eliminarCarrera();
        llenarTabla(carreraBD.getCarreras());
    }

    
    @FXML
    private void handleTableChange(Event event) {
        Carrera carrera = tblDatosCarrera.getSelectionModel().getSelectedItem();
        
        if(carrera != null){
            txtIdCarrera.setText(carrera.getIdCarrera());
            txtNombre.setText(carrera.getNombreCarrera());
            txtDescripcion.setText(carrera.getDescripcion());
        }
        
    }
    
   
    
    private void llenarTabla(ArrayList<Carrera> listaCarreras){
               
        tblDatosCarrera.getItems().clear();
        
        tbcIdCarrera.setCellValueFactory(new PropertyValueFactory<>("idCarrera"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCarrera"));
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcUsuarioAlta.setCellValueFactory(new PropertyValueFactory<>("usuarioAlta"));
        tbcFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        tbcUsuarioMod.setCellValueFactory(new PropertyValueFactory<>("usuarioMod"));
        tbcFechaMod.setCellValueFactory(new PropertyValueFactory<>("fechaMod"));
        
  
        for (Carrera carrera : listaCarreras) {
            tblDatosCarrera.getItems().add(carrera);
        }
    }
    
    private void agregarCarrera(){
        try {
                        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas agregar esta carrera?");
            
            if(alert.showAndWait().get() == ButtonType.OK){
                carreraBD.addCarrera(txtIdCarrera.getText(), txtNombre.getText(),txtDescripcion.getText(), "1");
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("La operación se ha realizado con éxito");
                alert.show();
            }else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Se ha cancelado la operación");
                alert.show();
            }
        }catch(SQLIntegrityConstraintViolationException ex2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("La carrera con el id " + txtIdCarrera.getText() + " ya existe en la base de datos");
            alert.show();
                    
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("La carrera no se ha podido agregar. Error al acceder a la base de datos");
            alert.show();
                
        }
        
    }
    private void actualizarCarrera(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas actualizar el carrera seleccionado?");           
            if(alert.showAndWait().get() == ButtonType.OK){
                carreraBD.updateCarrera(txtIdCarrera.getText(), txtNombre.getText(),txtDescripcion.getText(), "1");
                
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("La operación se ha realizado con éxito");
                alert.show();
            }else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Se ha cancelado la operación");
                alert.show();
            }
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("La carrera no se ha podido actualizar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    private void eliminarCarrera(){
        try {
            if(tblDatosCarrera.getSelectionModel().getSelectedItems().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Advertencia");
                alert.setHeaderText(null);
                alert.setContentText("Por favor elige un registro");
                alert.show();
                return;
            }
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas modificar el registro de este carrera?");
            
            if(alert.showAndWait().get() == ButtonType.OK){
                String ID = tblDatosCarrera.getSelectionModel().getSelectedItem().getIdCarrera();
                carreraBD.deleteCarrera(ID);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("La operación se ha realizado con éxito");
                alert.show();
            }else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Se ha cancelado la operación");
                alert.show();
            }
        }catch(SQLIntegrityConstraintViolationException ex2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("Es necesario eliminar primero los grupos y las materias asignados a la carrera " + txtIdCarrera.getText());
            alert.show();    
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El carrera no se ha podido eliminar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    
}
