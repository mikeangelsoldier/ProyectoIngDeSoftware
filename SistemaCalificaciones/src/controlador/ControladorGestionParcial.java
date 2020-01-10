/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import AccesoBD.CalificacionesBD;
import AccesoBD.ConectaBD_IS;
import Reportes.GeneradorReportes;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import modelo.Calificaciones;
import modelo.Grupo;
import modelo.Materia;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class ControladorGestionParcial implements Initializable {

    ConectaBD_IS conectaBD;
    CalificacionesBD calificacionesBD;
    
    ArrayList<Materia> listaMateriasProfesor;
    ArrayList<Grupo> listaGruposProfesorMateria;
    
    ManejadorEventos manejador;
    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXComboBox<String> cboGruposProfesor, cboMateriasProfesor;
    
    @FXML
    private TableView<Calificaciones> tblDatosCalificaciones;
    
    @FXML
    private TableColumn<Calificaciones, String> tbcNum, tbcNumControl, tbcNombre, tbcCurso, tbcSemestre, tbcCal1, tbcFal1, tbcEstado1, 
            tbcCal2, tbcFal2, tbcEstado2, tbcCal3, tbcFal3, tbcEstado3, tbcCalFinal, tbcFalFinal, tbcEstadoFinal;  
    
    @FXML
    private Label lblCatedratico, lblAsignatura, lblCarrera, lblClaveGrupo, lblGrupo, lblCampus, lblAula, lblHorario;
    
    @FXML 
    private Button btnImprimirCalificacion,btnImprimirReporte;
    
   
    private ObservableList<String> oblEstadosAprobacion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD = new ConectaBD_IS();
        calificacionesBD = new CalificacionesBD(conectaBD.getConnection());
        manejador = new ManejadorEventos();
        cboGruposProfesor.valueProperty().addListener(manejador);
        llenarComboMateriasProfesor();
        llenarComboGrupos();
        
    }    
   @FXML
    private void imprimirCalificacion(ActionEvent e){
        System.out.println("Imprimiendo");
         String grupoSeleccionado = cboGruposProfesor.getSelectionModel().getSelectedItem();
         System.out.println(grupoSeleccionado);
         GeneradorReportes reporte =new GeneradorReportes();     
      reporte.reporteCalificaciones(grupoSeleccionado); 
    }
       @FXML
    private void imprimirReporte(ActionEvent e){
        System.out.println("Imprimiendo reporte");
         String grupoSeleccionado = cboGruposProfesor.getSelectionModel().getSelectedItem();
         System.out.println(grupoSeleccionado);
         GeneradorReportes reporte =new GeneradorReportes(); 
      reporte.reporteSemestre(grupoSeleccionado);  
    }
    
     
    private void llenarComboMateriasProfesor(){
        cboGruposProfesor.valueProperty().removeListener(manejador);
        listaMateriasProfesor = calificacionesBD.getMateriaDeProfesor("P0001");
        for (int i = 0; i < listaMateriasProfesor.size(); i++) {
            cboMateriasProfesor.getItems().add(listaMateriasProfesor.get(i).getNombre());
             
        }   
        cboMateriasProfesor.setValue(listaMateriasProfesor.get(0).getNombre());
        cboGruposProfesor.valueProperty().addListener(manejador);
    }
    
    private void llenarComboGruposDeMateriaDeProfesor(){
        
        ArrayList<Grupo> listaGrupos = calificacionesBD.getGruposDeMateriaDeProfesor("P0001", 
                listaMateriasProfesor.get(cboMateriasProfesor.getSelectionModel().getSelectedIndex()).getClave());
        for (int i = 0; i < listaMateriasProfesor.size(); i++) {
            cboMateriasProfesor.getItems().add(listaMateriasProfesor.get(i).getNombre());
            
        }
        cboMateriasProfesor.setValue(listaMateriasProfesor.get(0).getNombre());
    }
    
    private void llenarTablaCalificaciones(ArrayList<Calificaciones> calificaciones) {
        tblDatosCalificaciones.getItems().clear();
        System.out.println("Jola");
        tbcNum.setCellValueFactory(new PropertyValueFactory<>("num"));
        tbcNumControl.setCellValueFactory(new PropertyValueFactory<>("numControl"));
        tbcNombre.setCellValueFactory(new PropertyValueFactory<>("nombreAlumno"));
        tbcCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));
        tbcSemestre.setCellValueFactory(new PropertyValueFactory<>("semestre"));
        tbcCal1.setCellValueFactory(new PropertyValueFactory<>("cp1"));
        tbcFal1.setCellValueFactory(new PropertyValueFactory<>("faltas1"));
        tbcEstado1.setCellValueFactory(new PropertyValueFactory<>("estado1"));
        tbcCal2.setCellValueFactory(new PropertyValueFactory<>("cp2"));
        tbcFal2.setCellValueFactory(new PropertyValueFactory<>("faltas2"));
        tbcEstado2.setCellValueFactory(new PropertyValueFactory<>("estado2"));
        tbcCal3.setCellValueFactory(new PropertyValueFactory<>("cp3"));
        tbcFal3.setCellValueFactory(new PropertyValueFactory<>("faltas3"));
        tbcEstado3.setCellValueFactory(new PropertyValueFactory<>("estado3"));
        tbcCalFinal.setCellValueFactory(new PropertyValueFactory<>("cpFinal"));
        tbcFalFinal.setCellValueFactory(new PropertyValueFactory<>("faltasFinal"));
        tbcEstadoFinal.setCellValueFactory(new PropertyValueFactory<>("estadoFinal"));
  
        for (int i = 0; i < calificaciones.size(); i++) {
            tblDatosCalificaciones.getItems().add(calificaciones.get(i));
            
        }
        
        oblEstadosAprobacion = FXCollections.observableArrayList();
        oblEstadosAprobacion.add("Aprobado");
        oblEstadosAprobacion.add("Reprobado");
        oblEstadosAprobacion.add("Desertor");
        
        
        //---------------------------Columnas de TextField configurar
        
        tbcCal1.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tbcCal1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setCp1(event.getNewValue());
                updateCalificacion();
            }
        });
        
        tbcCal2.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tbcCal2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setCp2(event.getNewValue());
                updateCalificacion();
                
            }
            
        });
        
        tbcCal3.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tbcCal3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setCp3(event.getNewValue());
                
                updateCalificacion();
            }
            
        });
        
        tbcCalFinal.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tbcCalFinal.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setCpFinal(event.getNewValue());
                updateCalificacion();
                
            }
            
        });
        
        
        tbcFal1.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tbcFal1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setFaltas1(event.getNewValue());
                updateCalificacion();
                
            }
            
        });
        
        tbcFal2.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tbcFal2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setFaltas2(event.getNewValue());
                updateCalificacion();
                
            }
            
        });
        
        tbcFal3.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tbcFal3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setFaltas3(event.getNewValue());
                updateCalificacion();
                
            }
            
        });
        
        tbcFalFinal.setCellFactory(TextFieldTableCell.forTableColumn(new DefaultStringConverter()));
        tbcFalFinal.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setFaltasFinal(event.getNewValue());
                updateCalificacion();
                
            }
            
        });
        
        tbcEstado1.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), oblEstadosAprobacion));
        tbcEstado1.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setEstado1(event.getNewValue());
                updateCalificacion();
                
            }
            
        });
        tbcEstado2.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), oblEstadosAprobacion));
        tbcEstado2.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setEstado2(event.getNewValue());
                updateCalificacion();
            }
            
        });
        tbcEstado3.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), oblEstadosAprobacion));
        tbcEstado3.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setEstado3(event.getNewValue());
                updateCalificacion();
            }
            
        });
        tbcEstadoFinal.setCellFactory(ComboBoxTableCell.forTableColumn(new DefaultStringConverter(), oblEstadosAprobacion));
        tbcEstadoFinal.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Calificaciones, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Calificaciones, String> event) {
                tblDatosCalificaciones.getSelectionModel().getSelectedItem().setEstadoFinal(event.getNewValue());
                updateCalificacion();
            }
            
        });
        
    }
    
    private void updateCalificacion() {
        Calificaciones calificacion = tblDatosCalificaciones.getSelectionModel().getSelectedItem();
        String idGrupo = listaGruposProfesorMateria.get(cboGruposProfesor.getSelectionModel().getSelectedIndex()).getClaveGrupo();
        String idAlumno = calificacion.getNumControl();
        String Cp1 = calificacion.getCp1();
        String Faltas1 = calificacion.getFaltas1();
        String Estado1 = calificacion.getEstado1();
        String Cp2 = calificacion.getCp2();
        String Faltas2 = calificacion.getFaltas2();
        String Estado2 = calificacion.getEstado2();
        String Cp3 = calificacion.getCp3();
        String Faltas3 = calificacion.getFaltas3();
        String Estado3 = calificacion.getEstado3();
        String CpFinal = calificacion.getCpFinal();
        String FaltasFinal = calificacion.getFaltasFinal();
        String EstadoFinal = calificacion.getEstadoFinal();
        try {
            calificacionesBD.updateCalificacionesDeUnGrupo(idGrupo, idAlumno, Cp1, Faltas1, Estado1, Cp2, Faltas2, Estado2, Cp3, Faltas3, Estado3, CpFinal, FaltasFinal, EstadoFinal);
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("La calificación no se ha actualizado. Error al acceder a la base de datos");
            alert.show();
            ex.printStackTrace();
        }
        llenarTablaCalificaciones(calificacionesBD.getAlumnosYCalificacionesDeUnGrupo(listaGruposProfesorMateria.get(cboGruposProfesor.getSelectionModel().getSelectedIndex()).getClaveGrupo()));
    }
    
    @FXML
    private void cboLlenarComboGrupos(ActionEvent event) {
        llenarComboGrupos();
    }

    private void llenarComboGrupos() {
        cboGruposProfesor.getItems().clear();
        String materiaSeleccionada = listaMateriasProfesor.get(cboMateriasProfesor.getSelectionModel().getSelectedIndex()).getClave();
        listaGruposProfesorMateria = calificacionesBD.getGruposDeMateriaDeProfesor("P0001", materiaSeleccionada);
        for (int i = 0; i < listaGruposProfesorMateria.size(); i++) {
            cboGruposProfesor.getItems().add(listaGruposProfesorMateria.get(i).getClaveGrupo());
        }
        cboGruposProfesor.setValue(listaGruposProfesorMateria.get(0).getClaveGrupo());
    }

    /*Clases internas-----------------------------------------------------------------------------------*/
    class ManejadorEventos implements ChangeListener{

        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            String grupoSeleccionado = cboGruposProfesor.getSelectionModel().getSelectedItem();
            ArrayList<Calificaciones> listaCalificaciones = calificacionesBD.getAlumnosYCalificacionesDeUnGrupo(grupoSeleccionado);
            llenarTablaCalificaciones(listaCalificaciones);
            if(!cboGruposProfesor.getItems().isEmpty())
                llenarEncabezado();
            
        }

        
        
    }   
    private void llenarEncabezado() {
            ArrayList<String> datosEncabezado = calificacionesBD.getDatosEncabezado(cboGruposProfesor.getSelectionModel().getSelectedItem());
            lblCatedratico.setText(datosEncabezado.get(0));
            lblAsignatura.setText(datosEncabezado.get(1));
            lblCarrera.setText(datosEncabezado.get(2));
            lblClaveGrupo.setText(datosEncabezado.get(3));
            lblGrupo.setText(datosEncabezado.get(4));
            lblCampus.setText(datosEncabezado.get(5));
            lblAula.setText(datosEncabezado.get(6));
            lblHorario.setText(datosEncabezado.get(7));
        }
    
}
