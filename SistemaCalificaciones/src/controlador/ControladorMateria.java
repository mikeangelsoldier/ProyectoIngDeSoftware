/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import AccesoBD.ConectaBD_IS;
import AccesoBD.MateriaBD;
import AccesoBD.MateriaBD;
import AccesoBD.SemestreBD;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Materia;
import modelo.Semestre;

/**
 *
 * @author Personal
 */
public class ControladorMateria implements Initializable {
    
    ConectaBD_IS conectaBD;
    MateriaBD materiaBD;
    SemestreBD semestreBD;
    TextField[] txtCAMPOS;
    ManejadorFiltroKey manejador;
    
    private boolean filtrarActivado;
    
    @FXML
    private Label lblSemestre;
    
    @FXML
    private JFXCheckBox cbxISC, cbxTICS;
    
    @FXML
    private JFXTextField txtClaveMateria, txtNombre, txtSATCA, txtIdSemestre, txtPlanEstudios, txtTotalUnidades;
    
    @FXML
    private TextArea txaDescripcion;
    
    private ToggleGroup tggGrupoTipo;
    
    @FXML
    private JFXRadioButton rbtComun, rbtEquivalente, rbtEspecialidad;
    
    @FXML
    private TableView<Materia> tblDatosMateria;
    
    @FXML
    private TableColumn<Materia, String> tbcClaveMateria, tbcNombre, tbcTipo, tbcSATCA, tbcSemestre, 
            tbcPlanEstudios, tbcDescripcion, tbcUsuarioAlta, tbcFechaAlta, tbcUsuarioMod, tbcFechaMod;
    
    @FXML
    private JFXButton btnInsertar, btnModificar, btnEliminar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD = new ConectaBD_IS();
        materiaBD = new MateriaBD(conectaBD.getConnection());
        semestreBD = new SemestreBD(conectaBD.getConnection());
        filtrarActivado = false;
        llenarTabla(materiaBD.getMaterias());
        
        txtCAMPOS = new TextField[]{txtClaveMateria, txtNombre, txtSATCA, txtIdSemestre, txtPlanEstudios};
        
        tggGrupoTipo = new ToggleGroup();
        rbtComun.setToggleGroup(tggGrupoTipo);
        rbtEquivalente.setToggleGroup(tggGrupoTipo);
        rbtEspecialidad.setToggleGroup(tggGrupoTipo);        
        
        manejador = new ManejadorFiltroKey();
    }   
    
    @FXML
    private void handleMenuCargarBD(ActionEvent event) throws IOException {
        llenarTabla(materiaBD.getMaterias());
    }
    
    @FXML
    private void handleButtonAgregar(ActionEvent event) {
        agregarMateria();
        llenarTabla(materiaBD.getMaterias());
    }
    
    @FXML
    private void handleButtonActualizar(ActionEvent event) {
        actualizarMateria();
        llenarTabla(materiaBD.getMaterias());
    }
    
    @FXML
    private void handleButtonEliminar(ActionEvent event) {
        eliminarMateria();
        llenarTabla(materiaBD.getMaterias());
    }

    
    @FXML
    private void filtroBusqueda(ActionEvent event) {
        ManejadorFiltro();
    }

    
    @FXML
    private void handleTableChange(Event event) {
        Materia materia = tblDatosMateria.getSelectionModel().getSelectedItem();
        
        if(materia != null){
            txtClaveMateria.setText(materia.getClave());
            txtNombre.setText(materia.getNombre());
            switch(materia.getTipo()){
                case "Común":
                    rbtComun.setSelected(true);
                    break;
                case "Equivalente":
                    rbtEquivalente.setSelected(true);
                    break;
                case "Especialidad":
                    rbtEspecialidad.setSelected(true);
                    break;
            }
            txtSATCA.setText(materia.getSatca());
            txtIdSemestre.setText(materia.getSemestre());
            txtPlanEstudios.setText(materia.getPlanEstudios());
            txaDescripcion.setText(materia.getDescripcion());
            
            //Validar los checkBox de carreras
            ArrayList<String> carreras = materiaBD.getCarrerasOfMateria(materia.getClave());
            cbxISC.setSelected(false);
            cbxTICS.setSelected(false);
            for (String carrera : carreras) {
                if(carrera.equals("ISC"))
                    cbxISC.setSelected(true);
                if(carrera.equals("TICS"))
                    cbxTICS.setSelected(true);
                
                    
            }
        }
        
    }
    
   @FXML
   private void cambiarTipoMateria(Event event){
       if(rbtComun.isSelected()){
           cbxISC.setSelected(true);
           cbxTICS.setSelected(true);
           cbxISC.setDisable(true);
           cbxISC.setStyle("-fx-opacity: 1");
           cbxTICS.setDisable(true);
           cbxTICS.setStyle("-fx-opacity: 1");
       }else if(rbtEquivalente.isSelected() || rbtEspecialidad.isSelected()){
           cbxISC.setSelected(false);
           cbxTICS.setSelected(false);
           cbxISC.setDisable(false);
           cbxTICS.setDisable(false);
       }
   }
    
    private void llenarTabla(ArrayList<Materia> listaMaterias){
               
        tblDatosMateria.getItems().clear();
        
        tbcClaveMateria.setCellValueFactory(new PropertyValueFactory<>("clave"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        tbcSATCA.setCellValueFactory(new PropertyValueFactory<>("satca"));
        tbcSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        tbcPlanEstudios.setCellValueFactory(new PropertyValueFactory<>("planEstudios"));
        tbcDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        tbcUsuarioAlta.setCellValueFactory(new PropertyValueFactory<>("usuarioAlta"));
        tbcFechaAlta.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        tbcUsuarioMod.setCellValueFactory(new PropertyValueFactory<>("usuarioMod"));
        tbcFechaMod.setCellValueFactory(new PropertyValueFactory<>("fechaMod"));
        
  
        for (Materia semestre : listaMaterias) {
            tblDatosMateria.getItems().add(semestre);
        }
    }
    
    private void agregarMateria(){
        try {
                        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas agregar esta materia?");
            
            if(alert.showAndWait().get() == ButtonType.OK){
                materiaBD.addMateria(txtClaveMateria.getText(), 
                        txtNombre.getText(), 
                        tggGrupoTipo.getSelectedToggle().getUserData().toString(), 
                        txtSATCA.getText(), 
                        txtIdSemestre.getText(),
                        txtPlanEstudios.getText(), 
                        txtTotalUnidades.getText(),
                        txaDescripcion.getText(), "1");
                validarCarreraMateria();
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
            alert.setContentText("La materia con el id " + txtClaveMateria.getText() + " ya existe en la base de datos");
            alert.show();
                    
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("La materia no se ha podido agregar. Error al acceder a la base de datos");
            alert.show();
                ex.printStackTrace();
        }
        
    }
    private void actualizarMateria(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas actualizar la materia seleccionada?");           
            if(alert.showAndWait().get() == ButtonType.OK){
                materiaBD.updateMateria(txtClaveMateria.getText(), txtNombre.getText(), tggGrupoTipo.getSelectedToggle().getUserData().toString(), txtSATCA.getText(), 
                        txtIdSemestre.getText(), txtPlanEstudios.getText(), txtTotalUnidades.getText(), txaDescripcion.getText(), "1");
                
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
            alert.setContentText("La materia no se ha podido actualizar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    private void eliminarMateria(){
        try {
            if(tblDatosMateria.getSelectionModel().getSelectedItems().isEmpty()){
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
            alert.setContentText("¿Realmente deseas eliminar el registro de esta materia?");
            
            if(alert.showAndWait().get() == ButtonType.OK){
                String ID = tblDatosMateria.getSelectionModel().getSelectedItem().getClave();
                materiaBD.deleteMateria(ID);
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
            alert.setContentText("La materia no se ha podido eliminar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    
    @FXML
    private void filtrarMateria(){
        filtrarActivado=!filtrarActivado;
        if(filtrarActivado){
            btnInsertar.setDisable(true);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
            for (int i = 0; i < txtCAMPOS.length; i++) {
                txtCAMPOS[i].textProperty().addListener(manejador);
                txaDescripcion.textProperty().addListener(manejador);
                tggGrupoTipo.selectedToggleProperty().addListener(manejador);
                
                txtCAMPOS[i].clear();
            }
            txaDescripcion.clear();
            rbtComun.setSelected(true);
            
        }else{
            btnInsertar.setDisable(false);
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
            for (int i = 0; i < txtCAMPOS.length; i++){ 
                txtCAMPOS[i].textProperty().removeListener(manejador);
                txaDescripcion.textProperty().removeListener(manejador);
                tggGrupoTipo.selectedToggleProperty().removeListener(manejador);
            }
            llenarTabla(materiaBD.getMaterias());
            
        }
    }
    
    void ManejadorFiltro(){
        if(filtrarActivado){
            String claveMateria, nombre, tipo, SATCA, semestre, planEstudios, descripcion;
            claveMateria = txtClaveMateria.getText();
            nombre = txtNombre.getText();
            tipo = tggGrupoTipo.getSelectedToggle().getUserData().toString();
            SATCA = txtSATCA.getText();
            semestre = txtIdSemestre.getText();
            planEstudios = txtPlanEstudios.getText();
            descripcion = txaDescripcion.getText();
            leerFiltrarTabla(claveMateria, nombre, tipo, SATCA, semestre, planEstudios, descripcion);
        }
    }
    
    private void leerFiltrarTabla(String claveMateria, String nombre, String tipo, String SATCA, String semestre, String planEstudios, String descripcion){
        
        llenarTabla(materiaBD.getMateriasFiltro(claveMateria, nombre, tipo, SATCA, semestre, planEstudios, descripcion));
    }
    
    private void validarCarreraMateria(){
        try{
            if(cbxISC.isSelected() && cbxTICS.isSelected()){
                materiaBD.addCarreraMateria(cbxTICS.getText(), txtClaveMateria.getText());
                materiaBD.addCarreraMateria(cbxISC.getText(), txtClaveMateria.getText());
            }else if(cbxISC.isSelected()){
                materiaBD.addCarreraMateria(cbxISC.getText(), txtClaveMateria.getText());
            }else if(cbxTICS.isSelected()){
                materiaBD.addCarreraMateria(cbxTICS.getText(), txtClaveMateria.getText());
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
    
    class ManejadorFiltroKey implements ChangeListener{

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            ManejadorFiltro();
        }
        
    }
    
}
