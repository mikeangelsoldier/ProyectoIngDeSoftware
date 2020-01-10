/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import AccesoBD.CarreraBD;
import AccesoBD.GrupoBD;
import AccesoBD.ConectaBD_IS;
import Beans.Profesor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import modelo.Alumno;
import modelo.Carrera;
import modelo.Grupo;
import modelo.Materia;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class ControladorGestionGrupos implements Initializable {

    
    public final String[] HorariosGruposA = {"Lunes y Miercoles de 7:00 a.m. a 8:45 a.m.",
                                            "Martes y Jueves de 7:00 a.m.  a 8:45 a.m.",
                                            "Lunes y Miercoles de 7:00 a.m. a 8:45 a.m. y viernes de 7:00 a.m. a 7:50 a.m.",
                                            "Lunes y Miercoles de 7:00 a.m. a 8:45 a.m. y viernes de 7:50 a.m. a 8:45 a.m.",
                                            "Martes y Jueves de 7:00 a.m. a 8:45 a.m. y viernes de 7:00 a.m. a 7:50 a.m.",
                                            "Martes y Jueves de 7:00 a.m. a 8:45 a.m. y viernes de 7:50 a.m. a 8:45 a.m."};
    public final String[] HorariosGruposB = {"Lunes y Miercoles de 8:45 a.m. a 10:25 a.m.",
                                            "Martes y Jueves de 8:45 a.m. a 10:25 a.m.",
                                            "Lunes y Miercoles de 8:45 a.m. a 10:25 a.m. y viernes de 8:50 a.m. a 9:35 a.m.",
                                            "Lunes y Miercoles de 8:45 a.m. a 10:25 a.m. y viernes de 9:35 a.m. a 10:25 a.m.",
                                            "Martes y Jueves de 8:45 a.m. a 10:25 a.m. y viernes de 8:50 a.m. a 9:35 a.m.",
                                            "Martes y Jueves de 8:45 a.m. a 10:25 a.m. y viernes de 9:35 a.m. a 10:25 a.m."};
    public final String[] HorariosGruposC = {"Lunes y Miercoles de 10:30 a.m. a 12:10 p.m.",
                                            "Martes y Jueves de 10:30 a.m. a 12:10 p.m.",
                                            "Lunes y Miercoles de 10:30 a.m. a 12:10 p.m. y viernes de 10:30 a.m. a 11:20 a.m.",
                                            "Lunes y Miercoles de 10:30 a.m. a 12:10 p.m. y viernes de 11:20 a.m. a 12:10 p.m.",
                                            "Martes y Jueves de 10:30 a.m. a 12:10 p.m.  y viernes de 10:30 a.m. a 11:20 a.m.",
                                            "Martes y Jueves de 10:30 a.m. a 12:10 p.m.  y viernes de 11:20 a.m. a 12:10 p.m."};
    public final String[] HorariosGruposD = {"Lunes y Miercoles de 12:15 p.m. a 1:55 p.m.",
                                            "Martes y Jueves de 12:15 p.m. a 1:55 p.m.",
                                            "Lunes y Miercoles de 12:15 p.m. a 1:55 p.m. y viernes de 12:15 p.m. a 1:05 p.m.",
                                            "Lunes y Miercoles de 12:15 p.m. a 1:55 p.m. y viernes de 1:05 p.m. a 1:55 p.m.",
                                            "Martes y Jueves de 12:15 p.m. a 1:55 p.m.  y viernes de 12:15 p.m. a 1:05 p.m.",
                                            "Martes y Jueves de 12:15 p.m. a 1:55 p.m.  y viernes de 1:05 p.m. a 1:55 p.m."};
    
    
    ConectaBD_IS conectaBD;
    GrupoBD grupoBD;
    CarreraBD carreraBD;
    Profesor profesorBD;
    ManejadorEventos manejador;
    ManejadorFiltroKey manejadorFiltro;
    
    private JFXTextField[] txtCAMPOS;
    private JFXComboBox<String>[] cboCAMPOS;
    ArrayList<Materia> listaMaterias;
    ArrayList<Profesor> listaProfesores;
    
    @FXML
    private TableView<Alumno> tblAlumnosPorAgregar, tblAlumnosDelGrupo; 
    
    @FXML
    private TableView<Grupo> tblDatosGrupos;
    
    @FXML   //De Tercer Panel
    private JFXTextField txtNumControlB, txtApellidoPaternoB, txtSemestreB;
    
    @FXML   //De Primer Panel
    private JFXTextField txtClave, txtCampus, txtAula, txtCapacidad, txtMaxFaltas;
    
    @FXML
    private JFXComboBox<String> cboCarreras, cboMateriasDeCarrera, cboCatedratico, cboGrupo, cboHorario;
    
    @FXML
    JFXButton btnAgregarGrupo, btnModificarGrupo, btnEliminarGrupo, btnVerAlumnos, btnQuitarAlumnoDeGrupo,
              btnBuscarAlumnos, btnRegresarAGrupos, btnRegresar, btnAgregarAlumno;
    
    @FXML
    Pane panelPrincipalGrupos, panel2Grupos, panel3Grupos;
    
    @FXML   //Panel principal grupos
    TableColumn tbcClaveGrupo, tbcGrupo, tbcCampus, tbcHorario, tbcAula, tbcCapacidad, tbcMaxFaltas, tbcCatedratico;
    
    @FXML
    TableColumn tbcNumControlA,tbcNombreA,tbcApellidoPaternoA,tbcApellidoMaternoA, tbcSemestreA,
                tbcNumControlB,tbcNombreB,tbcApellidoPaternoB,tbcApellidoMaternoB, tbcSemestreB;
    
    
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD = new ConectaBD_IS();
        grupoBD = new GrupoBD(conectaBD.getConnection());
        carreraBD = new CarreraBD(conectaBD.getConnection());
        llenarTablaAlumnosPorAgregar(grupoBD.getAlumnos());
        manejadorFiltro = new ManejadorFiltroKey();
        manejador = new ManejadorEventos();
        cboMateriasDeCarrera.valueProperty().addListener(manejador);
        tblAlumnosDelGrupo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tblAlumnosPorAgregar.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        txtCAMPOS = new JFXTextField[]{txtClave, txtCampus, txtAula, txtCapacidad, txtMaxFaltas};
        txtApellidoPaternoB.textProperty().addListener(manejadorFiltro);
        txtSemestreB.textProperty().addListener(manejadorFiltro);
        txtNumControlB.textProperty().addListener(manejadorFiltro);
        
        llenarCombos();
        llenarTablaGrupos(grupoBD.getGrupos(grupoBD.getIdCarreraMateria(cboCarreras.getSelectionModel().getSelectedItem(), 
                listaMaterias.get(cboMateriasDeCarrera.getSelectionModel().getSelectedIndex()).getClave())));
    }    
/*-----------------------------------------------------------------------------------------------------------------
    Sección general de grupos*/


    @FXML
    private void btnAgregarGrupoEvento(ActionEvent event) {
        agregarGrupo();
        llenarTablaGrupos();
        limpiarCampos();
    }
    
    @FXML
    private void btnMoficarGrupoEvento(ActionEvent event) {
        updateGrupo();
        llenarTablaGrupos();
        limpiarCampos();
    }
    
    @FXML
    private void btnEliminarGrupoEvento(ActionEvent event) {
        deleteGrupo();
        llenarTablaGrupos();
        limpiarCampos();
    }
    
    @FXML
    private void cboActualizarMateriasDeCarrera(ActionEvent event) {
        cboMateriasDeCarrera.valueProperty().removeListener(manejador);
        actualizarComboMateriasDeCarrera();
        cboMateriasDeCarrera.valueProperty().addListener(manejador);
        llenarTablaGrupos();
    }
    
    @FXML
    private void cboActualizarHorarios(ActionEvent event) {
        llenarComboHorarios();
    }
    
    @FXML
    private void cboLlenarTablaGrupos(MouseEvent event) {
       llenarTablaGrupos();
    }
    
    @FXML
    private void btnBuscarAlumnosEvento(ActionEvent event) {
        //panel2Grupos.setVisible(false);
        panel2Grupos.setDisable(true);
        panel3Grupos.setVisible(true);
    }
    
    @FXML
    private void btnRegresarAGruposEvento(ActionEvent event) {
        panelPrincipalGrupos.setDisable(false);
        panel2Grupos.setVisible(false);
    }
    
    @FXML
    private void btnRegresarEvento(ActionEvent event) {
        panel2Grupos.setDisable(false);
        panel3Grupos.setVisible(false);
    }
    
    @FXML
    private void handleTableChange(Event event) {
        Grupo grupo = tblDatosGrupos.getSelectionModel().getSelectedItem();
        
        if(grupo != null){
            txtClave.setText(grupo.getClaveGrupo());
            cboGrupo.setValue(grupo.getGrupo());
            txtCampus.setText(grupo.getCampus());
            cboHorario.setValue(grupo.getHorario());
            txtAula.setText(grupo.getAula());
            txtCapacidad.setText(grupo.getCapacidad());
            txtMaxFaltas.setText(grupo.getMaxFaltas());
            cboCatedratico.setValue(grupo.getNumControl());
        }
        btnVerAlumnos.setDisable(false);
    }
    
    private void llenarTablaGrupos(){
         String idCarrera = cboCarreras.getSelectionModel().getSelectedItem();
        String idMateria = listaMaterias.get(cboMateriasDeCarrera.getSelectionModel().getSelectedIndex()).getClave();
        
        String idCarreraMateria = String.valueOf(grupoBD.getIdCarreraMateria(idCarrera, idMateria));
        
        ArrayList<Grupo> listaGrupos = grupoBD.getGrupos(idCarreraMateria);
        llenarTablaGrupos(listaGrupos);
    }
    
    private void agregarGrupo(){
        String ClaveGrupo = txtClave.getText();
        String grupo = cboGrupo.getSelectionModel().getSelectedItem();
        String campus = txtCampus.getText();
        String horario = cboHorario.getSelectionModel().getSelectedItem();
        String aula = txtAula.getText();
        String capacidad = txtCapacidad.getText();
        String maxFaltas = txtMaxFaltas.getText();
        String numControl = listaProfesores.get(cboCatedratico.getSelectionModel().getSelectedIndex()).getNumControlProfesor();
        String carreraMateriaFK = grupoBD.getIdCarreraMateria(cboCarreras.getSelectionModel().getSelectedItem(), 
                listaMaterias.get(cboMateriasDeCarrera.getSelectionModel().getSelectedIndex()).getClave());
        
        try{
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas agregar a este grupo?");           
            if(alert.showAndWait().get() == ButtonType.OK){
                grupoBD.addGrupo(ClaveGrupo, grupo, campus, horario, aula, capacidad, maxFaltas, numControl, carreraMateriaFK);
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
            alert.setContentText("el grupo con el " + txtClave.getText() + " ya existe en la base de datos");
            alert.show();
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El grupo no se ha podido agregar. Error al acceder a la base de datos");
            alert.show();
        }    
    }
    
    private void updateGrupo(){
        String ClaveGrupo = txtClave.getText();
        String grupo = cboGrupo.getSelectionModel().getSelectedItem();
        String campus = txtCampus.getText();
        String horario = cboHorario.getSelectionModel().getSelectedItem();
        String aula = txtAula.getText();
        String capacidad = txtCapacidad.getText();
        String maxFaltas = txtMaxFaltas.getText();
        String numControl = listaProfesores.get(cboCatedratico.getSelectionModel().getSelectedIndex()).getNumControlProfesor();
        
        try{
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas actualizar a este grupo?");           
            if(alert.showAndWait().get() == ButtonType.OK){
                grupoBD.updateGrupo(ClaveGrupo, grupo, campus, horario, aula, capacidad, maxFaltas, numControl);
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
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El grupo no se ha podido eliminar. Error al acceder a la base de datos");
            alert.show();
            ex.printStackTrace();
        }    
    }
    
     private void deleteGrupo(){
        try {
            if(tblDatosGrupos.getSelectionModel().getSelectedItems().isEmpty()){
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
            alert.setContentText("¿Realmente deseas eliminar el registro de este grupo?");
            if(alert.showAndWait().get() == ButtonType.OK){
                String ID = tblDatosGrupos.getSelectionModel().getSelectedItem().getClaveGrupo();
                grupoBD.deleteGrupo(ID);
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

        } catch(SQLIntegrityConstraintViolationException ex2){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("Es necesario eliminar primero los alumnos asignados al grupo " + txtClave.getText());
            alert.show();    
        } catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("El alumno no se ha podido eliminar. Error al acceder a la base de datos");
            alert.show();
        }
 
    }
    
    private void actualizarComboMateriasDeCarrera() {
        cboMateriasDeCarrera.getItems().clear();
          listaMaterias = grupoBD.getMateriasDeCarrera(cboCarreras.getSelectionModel().getSelectedItem());
        for (int i = 0; i < listaMaterias.size(); i++) {
            cboMateriasDeCarrera.getItems().add(listaMaterias.get(i).getNombre());
        }
        if(listaMaterias.size() > 0)
            cboMateriasDeCarrera.setValue(listaMaterias.get(0).getNombre());
    }
    
    
    private void llenarCombos(){
        ArrayList<Carrera> listaCarreras;
        listaCarreras = carreraBD.getCarreras();
        for (int i = 0; i < listaCarreras.size(); i++) {
            cboCarreras.getItems().add(listaCarreras.get(i).getIdCarrera());
        }
        cboCarreras.setValue(listaCarreras.get(0).getIdCarrera());
        
        listaMaterias = grupoBD.getMateriasDeCarrera(cboCarreras.getSelectionModel().getSelectedItem());
        for (int i = 0; i < listaMaterias.size(); i++) {
            cboMateriasDeCarrera.getItems().add(listaMaterias.get(i).getNombre());
        }
        if(listaMaterias.size() > 0)
            cboMateriasDeCarrera.setValue(listaMaterias.get(0).getNombre());
        
        
        guardarListaProfesor();
        for (int i = 0; i < listaProfesores.size(); i++) {
            cboCatedratico.getItems().add(listaProfesores.get(i).getPrimerApellidoProfesor() + " " + 
                    listaProfesores.get(i).getSegundoApellidoProfesor() + " " + listaProfesores.get(i).getNombreProfesor());
        }
        if(listaMaterias.size() > 0)
            cboCatedratico.setValue(listaProfesores.get(0).getPrimerApellidoProfesor() + " " + 
                    listaProfesores.get(0).getSegundoApellidoProfesor() + " " + listaProfesores.get(0).getNombreProfesor());
        
        //Llenar COmbo Grupo y Horarios
        cboGrupo.setItems(FXCollections.observableArrayList("A", "B", "C", "D"));
        cboGrupo.setValue("A");
        llenarComboHorarios();
        
    }
    
    private void llenarComboHorarios(){
        //Llenar grupos A-D
        cboHorario.getItems().clear();
        
        
        switch(cboGrupo.getSelectionModel().getSelectedItem()){
            case "A":
                cboHorario.setItems(FXCollections.observableArrayList(HorariosGruposA[0], HorariosGruposA[1], HorariosGruposA[2], 
                        HorariosGruposA[3], HorariosGruposA[4], HorariosGruposA[5]));
                cboHorario.setValue(HorariosGruposA[0]);
                break;
            case "B":
                cboHorario.setItems(FXCollections.observableArrayList(HorariosGruposB[0], HorariosGruposB[1], HorariosGruposB[2], 
                        HorariosGruposB[3], HorariosGruposB[4], HorariosGruposB[5]));
                cboHorario.setValue(HorariosGruposB[0]);
                break;
            case "C":
                cboHorario.setItems(FXCollections.observableArrayList(HorariosGruposC[0], HorariosGruposC[1], HorariosGruposC[2], 
                        HorariosGruposC[3], HorariosGruposC[4], HorariosGruposC[5]));
                cboHorario.setValue(HorariosGruposC[0]);
                break;
            case "D":
                cboHorario.setItems(FXCollections.observableArrayList(HorariosGruposD[0], HorariosGruposD[1], HorariosGruposD[2], 
                        HorariosGruposD[3], HorariosGruposD[4], HorariosGruposD[5]));
                cboHorario.setValue(HorariosGruposD[0]);
                break;
        }
    }
    
    private void guardarListaProfesor(){
        listaProfesores = new ArrayList<>();
                
        ResultSet rs = profesorBD.obtenerProfesor();
        try {
            while(rs.next()){
                listaProfesores.add(new Profesor(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
                        rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), 
                        rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControladorGestionGrupos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void llenarTablaGrupos(ArrayList<Grupo> listaGrupos){
        tblDatosGrupos.getItems().clear();
         
        tbcClaveGrupo.setCellValueFactory(new PropertyValueFactory<>("claveGrupo"));
        tbcGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        tbcCampus.setCellValueFactory(new PropertyValueFactory<>("campus"));
        tbcHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        tbcAula.setCellValueFactory(new PropertyValueFactory<>("aula"));
        tbcCapacidad.setCellValueFactory(new PropertyValueFactory<>("capacidad"));
        tbcMaxFaltas.setCellValueFactory(new PropertyValueFactory<>("maxFaltas"));
        tbcCatedratico.setCellValueFactory(new PropertyValueFactory<>("numControl"));    
  
        for (Grupo grupo : listaGrupos) {
            tblDatosGrupos.getItems().add(grupo);
        }
        if(tblDatosGrupos.getItems().isEmpty())
            btnVerAlumnos.setDisable(true);
        else
            if(tblDatosGrupos.getSelectionModel().getSelectedItems().size()>0)
            btnVerAlumnos.setDisable(false);   
    }

    private void limpiarCampos(){
        for (JFXTextField txt : txtCAMPOS) {
            txt.setText("");
        }
        cboCatedratico.getSelectionModel().select(0);
        cboGrupo.getSelectionModel().select(0);
        cboHorario.getSelectionModel().select(0);
    }


/*-----------------------------------------------------------------------------------------------------------------
    Sección Alumnos en el grupo*/

    @FXML
    private void btnQuitarAlumnoDeGrupoEvento(ActionEvent event) {
         try {
            if(tblAlumnosDelGrupo.getSelectionModel().getSelectedItems().isEmpty()){
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
            alert.setContentText("¿Realmente deseas eliminar los alumnos seleccionados?");
            if(alert.showAndWait().get() == ButtonType.OK){
                Iterator<Alumno> alumnosSeleccionados = tblAlumnosDelGrupo.getSelectionModel().getSelectedItems().iterator();
                while(alumnosSeleccionados.hasNext()){
                    grupoBD.deleteAlumnosDeGrupo(txtClave.getText(), alumnosSeleccionados.next().getID());
                }
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
            }catch (SQLException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("Los alumnos no se han podido eliminar. Error al acceder a la base de datos");
            alert.show();
        }
        llenarTablaAlumnosEnGrupo(grupoBD.getAlumnosFromGrupos(txtClave.getText()));
    }
    
    @FXML
    private void btnVerAlumnosEvento(ActionEvent event) {
        if(txtClave.getText().equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("Por favor selecciona un grupo");
            alert.show(); 
           return;
        }
        panelPrincipalGrupos.setDisable(true);
        panel2Grupos.setVisible(true);
        llenarTablaAlumnosEnGrupo(grupoBD.getAlumnosFromGrupos(txtClave.getText()));
    }

      private void llenarTablaAlumnosEnGrupo(ArrayList<Alumno> listaAlumnos){
        tblAlumnosDelGrupo.getItems().clear();
        
        tbcNumControlA.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tbcNombreA.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcApellidoPaternoA.setCellValueFactory(new PropertyValueFactory<>("apellidoP"));
        tbcApellidoMaternoA.setCellValueFactory(new PropertyValueFactory<>("apellidoM"));
        tbcSemestreA.setCellValueFactory(new PropertyValueFactory<>("semestreAlumno"));
        
        for (Alumno alumno : listaAlumnos) {
            tblAlumnosDelGrupo.getItems().add(alumno);
        }
    }
    


   






    /*-----------------------------------------------------------------------------------------------------------------
    Sección de Alumnos por agregar*/
      
    @FXML
    private void btnAgregarAlumnoEvento(ActionEvent event) { 
        
        try {
            if(tblAlumnosPorAgregar.getSelectionModel().getSelectedItems().isEmpty()){
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
            alert.setContentText("¿Realmente deseas agregar los alumnos seleccionados?");
            if(alert.showAndWait().get() == ButtonType.OK){
                Iterator<Alumno> alumnosSeleccionados = tblAlumnosPorAgregar.getSelectionModel().getSelectedItems().iterator();
                while(alumnosSeleccionados.hasNext()){
                    grupoBD.addAlumnoAGrupo(txtClave.getText(), alumnosSeleccionados.next().getID());
                }
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
            }catch (SQLIntegrityConstraintViolationException ex) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("Ha intentdo agregar alumnos ya existentes a este grupo. Revise la tabla de alumnos existentes en el grupo");
            alert.show();
            ex.printStackTrace();
            }catch (SQLException ex2) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Un error ha ocurrido");
            alert.setContentText("Los alumnos no se han podido agregar. Error al acceder a la base de datos");
            alert.show();
        }
        llenarTablaAlumnosEnGrupo(grupoBD.getAlumnosFromGrupos(txtClave.getText()));
    }
      
      private void llenarTablaAlumnosPorAgregar(ArrayList<Alumno> listaAlumnos){
               
        tblAlumnosPorAgregar.getItems().clear();
        
        tbcNumControlB.setCellValueFactory(new PropertyValueFactory<>("ID"));
        tbcNombreB.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tbcApellidoPaternoB.setCellValueFactory(new PropertyValueFactory<>("apellidoP"));
        tbcApellidoMaternoB.setCellValueFactory(new PropertyValueFactory<>("apellidoM"));
        tbcSemestreB.setCellValueFactory(new PropertyValueFactory<>("semestreAlumno"));
        
        for (Alumno alumno : listaAlumnos) {
            tblAlumnosPorAgregar.getItems().add(alumno);
        }
    }
   
        private void ManejadorFiltro() {
            String numControl, apellidoP, semestre;
            numControl = txtNumControlB.getText();
            apellidoP = txtApellidoPaternoB.getText();
            semestre = txtSemestreB.getText();
            llenarTablaAlumnosPorAgregar(grupoBD.filtrarAlumnosGrupo(numControl, apellidoP, semestre));
        }
    
    
  /*Clases internas-----------------------------------------------------------------------------------*/
    class ManejadorEventos implements ChangeListener{

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            String idCarrera = cboCarreras.getSelectionModel().getSelectedItem();
            String idMateria = listaMaterias.get(cboMateriasDeCarrera.getSelectionModel().getSelectedIndex()).getClave();

            String idCarreraMateria = String.valueOf(grupoBD.getIdCarreraMateria(idCarrera, idMateria));

            ArrayList<Grupo> listaGrupos = grupoBD.getGrupos(idCarreraMateria);
            llenarTablaGrupos(listaGrupos);
            limpiarCampos();
        }
        
    }    
    
    class ManejadorFiltroKey implements ChangeListener{

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            ManejadorFiltro();
        }

        
        
    }
}
