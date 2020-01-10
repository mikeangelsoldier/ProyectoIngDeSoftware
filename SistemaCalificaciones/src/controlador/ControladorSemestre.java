/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import AccesoBD.ConectaBD_IS;
import AccesoBD.SemestreBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import modelo.Semestre;



/**
 *
 * @author alfre
 */
public class ControladorSemestre implements Initializable {
    ConectaBD_IS conectaBD;
    SemestreBD semestreBD;
    TextField[] txtCAMPOS;
    ManejadorFiltroKey manejador;
    
    private boolean filtrarActivado;
    
    @FXML
    private JFXTextField txtID, txtPeriodo, txtAnio;
    
    @FXML
    private JFXComboBox cboSemestre;
    
    @FXML
    private JFXComboBox cboPeriodo;
    
    @FXML
    private TableView<Semestre> tblDatosSemestre;
    
    @FXML
    private TableColumn<Semestre, String> tbcIDSemestre, tbcSemestre, tbcPeriodo, tbcAnio, tbcUsuarioAlta, tbcFechaAlta, tbcUsuarioMod, tbcFechaMod;
    
    @FXML
    private JFXButton btnAgregar, btnActualizar, btnEliminar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD = new ConectaBD_IS();
        semestreBD = new SemestreBD(conectaBD.getConnection());
        filtrarActivado = false;
        llenarTabla(semestreBD.getSemestres());
        txtCAMPOS = new TextField[]{txtID, txtPeriodo, txtAnio};
        
        //Llenar comboBox
        cboSemestre.getItems().addAll("", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12");
        cboPeriodo.getItems().addAll("", "Agosto-Diciembre","Enero-Julio");
        
        
        manejador = new ManejadorFiltroKey();
    }   
    
    @FXML
    private void handleMenuCargarBD(ActionEvent event) throws IOException {
        llenarTabla(semestreBD.getSemestres());
    }
    
    @FXML
    private void handleButtonAgregar(ActionEvent event) {
        agregarSemestre();
        llenarTabla(semestreBD.getSemestres());
    }
    
    @FXML
    private void handleButtonActualizar(ActionEvent event) {
        actualizarSemestre();
        llenarTabla(semestreBD.getSemestres());
    }
    
    @FXML
    private void handleButtonEliminar(ActionEvent event) {
        eliminarSemestre();
        llenarTabla(semestreBD.getSemestres());
    }

    
    @FXML
    private void filtroBusqueda(ActionEvent event) {
        ManejadorFiltro();
    }

    
    @FXML
    private void handleTableChange(Event event) {
        Semestre semestre = tblDatosSemestre.getSelectionModel().getSelectedItem();
        
        if(semestre != null){
            txtID.setText(semestre.getIDSemestre());
            cboSemestre.getSelectionModel().select(semestre.getSemestre());
            cboPeriodo.getSelectionModel().select(semestre.getSemestre());
            txtPeriodo.setText(semestre.getPeriodo());
            txtAnio.setText(semestre.getAño());
        }
        
    }
    
   
    
    private void llenarTabla(ArrayList<Semestre> listaSemestres){
               
        tblDatosSemestre.getItems().clear();
        
        tbcIDSemestre.setCellValueFactory(new PropertyValueFactory<>("IDSemestre"));
        tbcSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        tbcPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        tbcAnio.setCellValueFactory(new PropertyValueFactory<>("año"));
        tbcUsuarioAlta.setCellValueFactory(new PropertyValueFactory<>("usuarioAlta"));
        tbcFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        tbcUsuarioMod.setCellValueFactory(new PropertyValueFactory<>("usuarioMod"));
        tbcFechaMod.setCellValueFactory(new PropertyValueFactory<>("fechaMod"));
        
  
        for (Semestre semestre : listaSemestres) {
            tblDatosSemestre.getItems().add(semestre);
        }
    }
    
    private void agregarSemestre(){
        try {
                        
           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas agregar este semestre?");
            
            if(alert.showAndWait().get() == ButtonType.OK){
                semestreBD.addSemestre(txtID.getText(), cboSemestre.getSelectionModel().getSelectedItem().toString(), txtPeriodo.getText(),txtAnio.getText(), "1");
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
            alert.setContentText("El semestre con el id " + txtID.getText() + " ya existe en la base de datos");
            alert.show();
                    
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El semesre no se ha podido agregar. Error al acceder a la base de datos");
            alert.show();
                
        }
        
    }
    private void actualizarSemestre(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas actualizar el semestre seleccionado?");           
            if(alert.showAndWait().get() == ButtonType.OK){
                semestreBD.updateSemestre(txtID.getText(), cboSemestre.getSelectionModel().getSelectedItem().toString(), 
                        txtPeriodo.getText(),txtAnio.getText(), "1");
                
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
            alert.setContentText("El semestre no se ha podido actualizar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    private void eliminarSemestre(){
        try {
            if(tblDatosSemestre.getSelectionModel().getSelectedItems().isEmpty()){
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
            alert.setContentText("¿Realmente deseas modificar el registro de este semestre?");
            
            if(alert.showAndWait().get() == ButtonType.OK){
                String ID = tblDatosSemestre.getSelectionModel().getSelectedItem().getIDSemestre();
                semestreBD.deleteSemestre(ID);
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
            alert.setContentText("Es necesario eliminar primero las materias asignadas al semestre " + txtID.getText());
            alert.show();    
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El semestre no se ha podido eliminar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    
    @FXML
    private void filtrarSemestre(){
        filtrarActivado=!filtrarActivado;
        if(filtrarActivado){
            btnAgregar.setDisable(true);
            btnActualizar.setDisable(true);
            btnEliminar.setDisable(true);
            for (int i = 0; i < txtCAMPOS.length; i++) {
                txtCAMPOS[i].textProperty().addListener(manejador);
                cboSemestre.valueProperty().addListener(manejador);
                txtCAMPOS[i].clear();
                cboSemestre.getSelectionModel().select("");
            }
            
        }else{
            btnAgregar.setDisable(false);
            btnActualizar.setDisable(false);
            btnEliminar.setDisable(false);
            for (int i = 0; i < txtCAMPOS.length; i++){ 
                txtCAMPOS[i].textProperty().removeListener(manejador);
                cboSemestre.valueProperty().removeListener(manejador);
            }
            
            
        }
    }
    
    void ManejadorFiltro(){
        System.out.println("si entra al metodo");
        if(filtrarActivado){
            String idSemestre, semestre, periodo, anio;
            idSemestre = txtID.getText(); 
            semestre = cboSemestre.getSelectionModel().getSelectedItem().toString(); 
            periodo = txtPeriodo.getText(); 
            anio = txtAnio.getText();
            leerFiltrarTabla(idSemestre, semestre, periodo, anio);
        }
    }
    
    private void leerFiltrarTabla(String idSemestre, String semestre, String periodo, String anio){
        
        llenarTabla(semestreBD.getSemestreFiltro(idSemestre, semestre, periodo, anio));
    }
    
    class ManejadorFiltroKey implements ChangeListener{

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            ManejadorFiltro();
        }
        
    }
}


