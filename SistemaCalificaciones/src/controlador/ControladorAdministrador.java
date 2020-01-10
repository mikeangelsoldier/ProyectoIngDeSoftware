/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import AccesoBD.AdministradorBD;
import AccesoBD.ConectaBD_IS;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import modelo.Administrador;



/**
 *
 * @author alfre
 */
public class ControladorAdministrador implements Initializable {
    ConectaBD_IS conectaBD;
    AdministradorBD administradorBD;
    TextField[] txtCAMPOS;
    ManejadorFiltroKey manejador;
    
    private boolean filtrarActivado;
    
    @FXML
    private JFXTextField txtNumControl, txtNombre, txtPrimerApellido, txtSegundoApellido, txtSexo, txtCargo, txtTelefono, txtCorreo, txtUser;
    
    @FXML
    private JFXPasswordField txtPassword;
    
    @FXML
    private TableView<Administrador> tblDatosAdministrador;
     
    @FXML
    private TableColumn<Administrador, String> tbcNumControl, tbcNombre, tbcPrimerApellido, tbcSegundoApellido, 
            tbcSexo, tbcCargo, tbcTelefono, tbcCorreo, tbcUser, tbcPassword, 
            tbcUsuarioAlta, tbcFechaAlta, tbcUsuarioMod, tbcFechaMod;
    
    @FXML
    private JFXButton btnInsertar, btnActualizar, btnEliminar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD = new ConectaBD_IS();
        administradorBD = new AdministradorBD(conectaBD.getConnection());
        filtrarActivado = false;
        llenarTabla(administradorBD.getAdministradores()); 
        txtCAMPOS = new TextField[]{txtNumControl, txtNombre, txtPrimerApellido, 
            txtSegundoApellido, txtSexo, txtCargo, txtTelefono, txtCorreo, txtUser};
        manejador = new ManejadorFiltroKey();
    }   
    
    @FXML
    private void handleMenuCargarBD(ActionEvent event) throws IOException {
        llenarTabla(administradorBD.getAdministradores());
    }
    
    @FXML
    private void handleButtonAgregar(ActionEvent event) {
        agregarAdministrador();
        llenarTabla(administradorBD.getAdministradores());
    }
    
    @FXML
    private void handleButtonActualizar(ActionEvent event) {
        actualizarAdministrador();
        llenarTabla(administradorBD.getAdministradores());
    }
    
    @FXML
    private void handleButtonEliminar(ActionEvent event) {
        eliminarAdministrador();
        llenarTabla(administradorBD.getAdministradores());
    }
   
    @FXML
    private void filtroBusqueda(ActionEvent event) {
        ManejadorFiltro();
    }

    
    @FXML
    private void handleTableChange(Event event) {
        Administrador administrador = tblDatosAdministrador.getSelectionModel().getSelectedItem();
        
        if(administrador != null){
            txtNumControl.setText(administrador.getNumControl());
            txtNombre.setText(administrador.getNombre());
            txtPrimerApellido.setText(administrador.getPrimerApellido());
            txtSegundoApellido.setText(administrador.getSegundoApellido());
            txtSexo.setText(administrador.getSexo());
            txtCargo.setText(administrador.getCargo());
            txtTelefono.setText(administrador.getTelefono());
            txtCorreo.setText(administrador.getCorreo());
            txtUser.setText(administrador.getLogin());
            txtPassword.setText(administrador.getPassw());
        }
        
    }
    
     
    
    private void llenarTabla(ArrayList<Administrador> listaAdministradores){
               
        tblDatosAdministrador.getItems().clear();
        
        tbcNumControl.setCellValueFactory(new PropertyValueFactory<>("numControl"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcPrimerApellido.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
        tbcSegundoApellido.setCellValueFactory(new PropertyValueFactory<>("segundoApellido"));
        tbcSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tbcCargo.setCellValueFactory(new PropertyValueFactory<>("cargo"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        tbcUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        tbcPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tbcUsuarioAlta.setCellValueFactory(new PropertyValueFactory<>("usuarioAlta"));
        tbcFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        tbcUsuarioMod.setCellValueFactory(new PropertyValueFactory<>("usuarioMod"));
        tbcFechaMod.setCellValueFactory(new PropertyValueFactory<>("fechaMod"));
        
  
        for (Administrador administrador : listaAdministradores) {
            tblDatosAdministrador.getItems().add(administrador);
        }
    }
    
    private void agregarAdministrador(){
        try {
            if(tblDatosAdministrador.getSelectionModel().getSelectedItems().isEmpty()){
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
            alert.setContentText("¿Realmente deseas agregar a este administrador?");           
            if(alert.showAndWait().get() == ButtonType.OK){
                administradorBD.addAdministrador(txtNumControl.getText(), txtNombre.getText(),txtPrimerApellido.getText(), 
                    txtSegundoApellido.getText(), txtSexo.getText(), txtCargo.getText(), 
                    txtTelefono.getText(), txtCorreo.getText(), txtUser.getText(), txtPassword.getText(), "1");
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
            alert.setContentText("El número de control " + txtNumControl.getText() + " ya existe en la base de datos");
            alert.show();
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El administrador no se ha podido agregar. Error al acceder a la base de datos");
            alert.show();
        }       
        
        
    }
    private void actualizarAdministrador(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas modificar el registro de este administrador?");
            
            if(alert.showAndWait().get() == ButtonType.OK){
                administradorBD.updateAdministrador(txtNumControl.getText(), txtNombre.getText(),txtPrimerApellido.getText(), 
                    txtSegundoApellido.getText(), txtSexo.getText(), txtCargo.getText(), 
                    txtTelefono.getText(), txtCorreo.getText(), txtUser.getText(), txtPassword.getText(), "1");
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
            alert.setHeaderText(null);
            alert.setContentText("El administrador no se ha podido actualizar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    private void eliminarAdministrador(){
        try {
            if(tblDatosAdministrador.getSelectionModel().getSelectedItems().isEmpty()){
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
            alert.setContentText("¿Realmente deseas eliminar el registro de este administrador?");
            if(alert.showAndWait().get() == ButtonType.OK){
                String ID = tblDatosAdministrador.getSelectionModel().getSelectedItem().getNumControl();
                administradorBD.deleteAdministrador(ID);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("La operación se ha realizado con éxito");
                alert.show();
            }else{
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información");
                alert.setHeaderText(null);
                alert.setContentText("Se ha cancelado la operación");
                alert.show();
            }
                
        }catch(SQLIntegrityConstraintViolationException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("Hay registros que dependen de este Administrador, por favor elimínelos antes de continuar");
            alert.show();
        } catch (SQLException ex2) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El administrador no se ha podido eliminar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    
    private void limpiarCampos(){
        for (int i = 0; i < txtCAMPOS.length; i++) {
                txtCAMPOS[i].clear();
        }
        txtPassword.clear();
    }
    
    @FXML
    private void filtrarAdministrador(){
        filtrarActivado=!filtrarActivado;
        if(filtrarActivado){
            btnInsertar.setDisable(true);
            btnActualizar.setDisable(true);
            btnEliminar.setDisable(true);
            for (int i = 0; i < txtCAMPOS.length; i++) {
                txtCAMPOS[i].textProperty().addListener(manejador);
            }
            limpiarCampos();
            
        }else{
            btnInsertar.setDisable(false);
            btnActualizar.setDisable(false);
            btnEliminar.setDisable(false);
            for (int i = 0; i < txtCAMPOS.length; i++) {
                txtCAMPOS[i].textProperty().removeListener(manejador);
            }
            
            
        }
    }
    
    void ManejadorFiltro(){
        if(filtrarActivado){
            String numControl, nombre, primerApellido, segundoApellido, sexo, cargo, telefono, correo;
                    System.out.println("Si entra if");
            numControl = txtNumControl.getText();
            nombre = txtNombre.getText(); 
            primerApellido = txtPrimerApellido.getText(); 
            segundoApellido = txtSegundoApellido.getText(); 
            sexo = txtSexo.getText();
            cargo = txtCargo.getText();
            telefono = txtTelefono.getText(); 
            correo = txtCorreo.getText();
            leerFiltrarTabla(numControl, nombre, primerApellido, segundoApellido, sexo, cargo, telefono, correo);
            System.out.println(nombre);
        }
    }
    
    private void leerFiltrarTabla(String numControl, String nombre, String primerApellido, String segundoApellido, String sexo, 
            String cargo, String telefono, String correo){
        
        llenarTabla(administradorBD.getAdministradoresFiltro(numControl, nombre, primerApellido, segundoApellido, sexo, cargo, telefono, correo));
    }
    
    class ManejadorFiltroKey implements ChangeListener{

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            ManejadorFiltro();
        }
        
    }
}


