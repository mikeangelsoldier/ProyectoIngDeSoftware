/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import AccesoBD.AlumnoBD;
import AccesoBD.ConectaBD_IS;
import Reportes.GeneradorReportes;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Alumno;



/**
 *
 * @author alfre
 */
public class ControladorAlumno implements Initializable {
    ConectaBD_IS conectaBD;
    AlumnoBD alumnoBD;
    TextField[] txtCAMPOS;
    ManejadorFiltroKey manejador;
    
    
    private boolean filtrarActivado;
    
    @FXML
    private JFXTextField txtID, txtCurp, txtNombre, txtApellidoP, txtApellidoM, txtSexo, txtDomicilio, txtTelefono, txtCorreo, txtUser;
    
    @FXML
    private JFXTextField txtPassword;
    
    @FXML
    private DatePicker dpkFechaNac;
    
    @FXML
    private TableView<Alumno> tblDatosAlumno;
    
    @FXML
    private TableColumn<Alumno, String> tbcID, tbcCurp, tbcNombre, tbcApellidoP, tbcApellidoM, 
            tbcSexo, tbcFechaNac, tbcDomicilio, tbcTelefono, tbcCorreo, tbcUser, tbcPassword, 
            tbcUsuarioAlta, tbcFechaAlta, tbcUsuarioMod, tbcFechaMod, tbcSemestreAlumno;
    
    @FXML
    private JFXButton btnAgregar, btnActualizar, btnEliminar;
    
    @FXML
    private JFXComboBox cboSemestreAlumno;
    
     @FXML
    private MenuItem jmiReporte,jmiGrafico;
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD = new ConectaBD_IS();
        alumnoBD = new AlumnoBD(conectaBD.getConnection());
        filtrarActivado = false;
        llenarTabla(alumnoBD.getAlumnos());
        txtCAMPOS = new TextField[]{txtID, txtCurp, txtNombre, txtApellidoP, 
            txtApellidoM, txtSexo, txtDomicilio, txtTelefono, txtCorreo, txtUser};
        manejador = new ManejadorFiltroKey();
        cboSemestreAlumno.setItems(FXCollections.observableArrayList("", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"));
    }   
    
    @FXML
    private void handleMenuCargarBD(ActionEvent event) throws IOException {
        llenarTabla(alumnoBD.getAlumnos());
    }
    
    @FXML
    private void handleButtonAgregar(ActionEvent event) {
        agregarAlumno();
        llenarTabla(alumnoBD.getAlumnos());
    }
    
    @FXML
    private void handleButtonActualizar(ActionEvent event) {
        actualizarAlumno();
        llenarTabla(alumnoBD.getAlumnos());
    }
    
    @FXML
    private void handleButtonEliminar(ActionEvent event) {
        eliminarAlumno();
        llenarTabla(alumnoBD.getAlumnos());
    }
   
    @FXML
    private void filtroBusqueda(ActionEvent event) {
        ManejadorFiltro();
    }

          @FXML
    private void handleJmi(ActionEvent event) throws IOException {
      System.out.println("Me presionaste wee");  
      GeneradorReportes reporte =new GeneradorReportes();
      reporte.reporteSemestre("0002"); 
    }
     
    
        @FXML   
    private void handleJmiGrafico(ActionEvent e){
        System.out.println("Yo soy grafico"); 
    /*Parent panelTabla = FXMLLoader.load(getClass().getResource("/javafxapplication1/VistaReportes.fxml"));
    Scene panelTablaScene = new Scene(panelTabla);
     System.out.println("En el sistema yo soy "+loginMeta.idUsuario);
    //Esta linea obtiene la informacion de los componentes del scene 
    Stage window=(Stage)((Node)e.getSource()).getScene().getWindow();
    window.setScene(panelTablaScene); 
    window.show();
*/          
    }
    
    
    
    @FXML
    private void generarPassw(ActionEvent event){
        String cadena = "";
        for (int i = 0; i < 10; i++) {
            cadena = cadena + (char)(Math.random()*57+65);
        }
        txtPassword.setText(cadena);
    }
    
    @FXML
    private void handleTableChange(Event event) {
        Alumno alumno = tblDatosAlumno.getSelectionModel().getSelectedItem();
        
        if(alumno != null){
            txtID.setText(alumno.getID());
            txtCurp.setText(alumno.getCurp());
            txtNombre.setText(alumno.getNombre());
            txtApellidoP.setText(alumno.getApellidoP());
            txtApellidoM.setText(alumno.getApellidoM());
            txtSexo.setText(alumno.getSexo());
            dpkFechaNac.setValue(LocalDate.parse(alumno.getFechaNac()));
            txtDomicilio.setText(alumno.getDomicilio());
            txtTelefono.setText(alumno.getTelefono());
            txtCorreo.setText(alumno.getCorreo());
            txtUser.setText(alumno.getUser());
            txtPassword.setText(alumno.getPassword());
            cboSemestreAlumno.getSelectionModel().select(alumno.getSemestreAlumno());
            
        }
        
    }
    
     
    
    private void llenarTabla(ArrayList<Alumno> listaAlumnos){
               
        tblDatosAlumno.getItems().clear();
        
        tbcID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tbcCurp.setCellValueFactory(new PropertyValueFactory<>("curp"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcApellidoP.setCellValueFactory(new PropertyValueFactory<>("apellidoP"));
        tbcApellidoM.setCellValueFactory(new PropertyValueFactory<>("apellidoM"));
        tbcSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        tbcFechaNac.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
        tbcDomicilio.setCellValueFactory(new PropertyValueFactory<>("domicilio"));
        tbcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tbcCorreo.setCellValueFactory(new PropertyValueFactory<>("correo"));
        tbcUser.setCellValueFactory(new PropertyValueFactory<>("user"));
        tbcPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tbcUsuarioAlta.setCellValueFactory(new PropertyValueFactory<>("usuarioAlta"));
        tbcFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        tbcUsuarioMod.setCellValueFactory(new PropertyValueFactory<>("usuarioMod"));
        tbcFechaMod.setCellValueFactory(new PropertyValueFactory<>("fechaMod"));
        tbcSemestreAlumno.setCellValueFactory(new PropertyValueFactory<>("semestreAlumno"));
  
        for (Alumno alumno : listaAlumnos) {
            tblDatosAlumno.getItems().add(alumno);
        }
    }
    
    private void agregarAlumno(){
        try {
//            if(tblDatosAlumno.getSelectionModel().getSelectedItems().isEmpty()){
//                Alert alert = new Alert(Alert.AlertType.WARNING);
//                alert.setTitle("Advertencia");
//                alert.setHeaderText(null);
//                alert.setContentText("Por favor elige un registro");
//                alert.show();
//                return;
//            }
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas agregar a este alumno?");           
            if(alert.showAndWait().get() == ButtonType.OK){
                alumnoBD.addAlumno(txtID.getText(), txtCurp.getText(), txtNombre.getText(),txtApellidoP.getText(), 
                    txtApellidoM.getText(), txtSexo.getText(), dpkFechaNac.getValue().format(DateTimeFormatter.ISO_DATE), 
                    txtDomicilio.getText(), txtTelefono.getText(), txtCorreo.getText(), cboSemestreAlumno.getSelectionModel().getSelectedItem().toString(), "1", txtUser.getText(), txtPassword.getText());
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
            alert.setContentText("La matrícula " + txtID.getText() + " ya existe en la base de datos");
            alert.show();
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El alumno no se ha podido agregar. Error al acceder a la base de datos");
            alert.show();
        }       
        
        
    }
    private void actualizarAlumno(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas modificar el registro de este alumno?");
            
            if(alert.showAndWait().get() == ButtonType.OK){
            
                alumnoBD.updateAlumno(tblDatosAlumno.getSelectionModel().getSelectedItem().getID(), txtCurp.getText(), txtNombre.getText(),txtApellidoP.getText(), 
                    txtApellidoM.getText(), txtSexo.getText(), dpkFechaNac.getValue().format(DateTimeFormatter.ISO_DATE), 
                    txtDomicilio.getText(), 
                    txtTelefono.getText(), 
                    txtCorreo.getText(), 
                    cboSemestreAlumno.getSelectionModel().getSelectedItem().toString(),
                    txtUser.getText(), 
                    txtPassword.getText(), "1");
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
            alert.setContentText("El alumno no se ha podido actualizar. Error al acceder a la base de datos");
            alert.show();
            ex.printStackTrace();
        }
 
    }
    private void eliminarAlumno(){
        try {
            if(tblDatosAlumno.getSelectionModel().getSelectedItems().isEmpty()){
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
            alert.setContentText("¿Realmente deseas eliminar el registro de este alumno?");
            if(alert.showAndWait().get() == ButtonType.OK){
                String ID = tblDatosAlumno.getSelectionModel().getSelectedItem().getID();
                alumnoBD.deleteAlumno(ID);
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
                
            
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El alumno no se ha podido eliminar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    
    private void limpiarCampos(){
        for (int i = 0; i < txtCAMPOS.length; i++) {
                txtCAMPOS[i].clear();
        }
        txtPassword.clear();
        cboSemestreAlumno.getSelectionModel().select(0);
    }
    
    @FXML
    private void filtrarAlumno(){
        filtrarActivado=!filtrarActivado;
        if(filtrarActivado){
            btnAgregar.setDisable(true);
            btnActualizar.setDisable(true);
            btnEliminar.setDisable(true);
            for (int i = 0; i < txtCAMPOS.length; i++) {
                txtCAMPOS[i].textProperty().addListener(manejador);
            }
            cboSemestreAlumno.promptTextProperty().addListener(manejador);
            limpiarCampos();
            
        }else{
            btnAgregar.setDisable(false);
            btnActualizar.setDisable(false);
            btnEliminar.setDisable(false);
            for (int i = 0; i < txtCAMPOS.length; i++) {
                txtCAMPOS[i].textProperty().removeListener(manejador);
            }
            cboSemestreAlumno.promptTextProperty().removeListener(manejador);
        }
    }
    
    void ManejadorFiltro(){
        System.out.println("si entra al metodo");
        if(filtrarActivado){
            String id, curp, nombre, apellidoP, apellidoM, sexo, domicilio, telefono, correo, semestreAlumno;
                    System.out.println("Si entra if");
            id = txtID.getText(); 
            curp = txtCurp.getText(); 
            nombre = txtNombre.getText(); 
            apellidoP = txtApellidoP.getText(); 
            apellidoM = txtApellidoM.getText(); 
            sexo = txtSexo.getText(); 
            domicilio = txtDomicilio.getText(); 
            telefono = txtTelefono.getText(); 
            correo = txtCorreo.getText();
            semestreAlumno = cboSemestreAlumno.getSelectionModel().getSelectedItem().toString();
            leerFiltrarTabla(id, curp, nombre, apellidoP, apellidoM, sexo, domicilio, telefono, correo, semestreAlumno);
            System.out.println(nombre);
        }
    }
    
    private void leerFiltrarTabla(String id, String curp, String nombre, String apellidoP, 
                                  String apellidoM, String sexo, String domicilio, String telefono, String correo, String semestreAlumno){
        
        llenarTabla(alumnoBD.getAlumnosFiltro(id, curp, nombre, apellidoP, apellidoM, sexo, domicilio, telefono, correo, semestreAlumno));
    }
    
    class ManejadorFiltroKey implements ChangeListener{

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            ManejadorFiltro();
        }
        
    }
}


