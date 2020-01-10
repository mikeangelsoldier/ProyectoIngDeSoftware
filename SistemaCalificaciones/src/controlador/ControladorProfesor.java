/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;
//esta clase, la modificque, el procedure de profesor

import AccesoBD.ConexionJavaSQLServer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import Beans.Profesor;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Mike
 */
public class ControladorProfesor implements Initializable {

    ResultSet rsProfesor = null;
    ResultSetMetaData metaDatosProfesor = null;
    ManejadorEventos manejador;

    private String usrId;//para poner el id del usuario que esta usando el sistema y es quien insertara o modificara
    private boolean filtrarActivado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usrId = "Ad0001";//el usuario administrador logeado de prueba
        leerBDCrearYLlenarTabla();
        filtrarActivado = false;
        manejador = new ManejadorEventos();
    }

    @FXML
    private JFXButton btnInsertarProfesor,
            btnActualizarProfesor,
            btnEliminarProfesor;

    @FXML
    private JFXTextField txtNumControlProfesor,
            txtCurpProfesor,
            txtNombreProfesor,
            txtPrimerApellidoProfesor,
            txtSegundoApellidoProfesor,
            txtSexoProfesor,
            txtTelefonoProfesor,
            txtCorreoProfesor,
            txtUsuarioProfesor,
            txtContraseñaProfesor;

    @FXML
    JFXDatePicker dpkFechaNacimientoProfesor;

    private JFXTextField[] txtCAMPOS = new JFXTextField[]{txtNumControlProfesor,
        txtCurpProfesor,
        txtNombreProfesor,
        txtPrimerApellidoProfesor,
        txtSegundoApellidoProfesor,
        txtSexoProfesor,
        txtTelefonoProfesor,
        txtCorreoProfesor,
        txtUsuarioProfesor,
        txtContraseñaProfesor};

    @FXML
    private TableView<Profesor> tblProfesor;

    @FXML
    private TableColumn<Profesor, String> colTablaProfesor0,
            colTablaProfesor1,
            colTablaProfesor2,
            colTablaProfesor3,
            colTablaProfesor4,
            colTablaProfesor5,
            colTablaProfesor6,
            colTablaProfesor7,
            colTablaProfesor8,
            colTablaProfesor9,
            colTablaProfesor10,
            colTablaProfesor11,
            colTablaProfesor12,
            colTablaProfesor13,
            colTablaProfesor14;

    @FXML
    private void btnInsertarButtonAction(ActionEvent event) {
        prepararInsertar();
    }

    @FXML
    private void btnModifcarButtonAction(ActionEvent event) {
        prepararModificar();
    }

    @FXML
    private void btnEliminarButtonAction(ActionEvent event) {
        prepararEliminar();
    }

    @FXML
    private void btnCancelarButtonAction(ActionEvent event) {
        cancelar();
    }

    @FXML
    public void tablaKeyProfesorAction(KeyEvent event) {
        mostrarFilaSeleccionada();
    }

    @FXML
    private void tablaProfesorAction(MouseEvent ev) {
        mostrarFilaSeleccionada();
    }
    
    @FXML
    private void txtNumeroControlAction(ActionEvent ev) {
        String usuario=(String.valueOf(txtNumControlProfesor.getText()));
        txtUsuarioProfesor.setText(usuario);
    }

    public void leerBDCrearYLlenarTabla() {
        try {
            ResultSet rs = Profesor.obtenerProfesor();

            metaDatosProfesor = rs.getMetaData();
            int cols = metaDatosProfesor.getColumnCount();

            while (rs.next()) {//Con este ciclo recorremos el Result set, me devuelve falso si llega al puntero EDF(End Of File)
                Object[] fila = new Object[cols];//este arreglo tiene 13 elementos 
                for (int i = 0; i < cols; i++) {
                    if (rs.getObject(i + 1) == null) {//si es null en la BD
                        fila[i] = "";
                    } else {
                        fila[i] = rs.getObject(i + 1);//Empieza en 1 por eso es i+1,  getObject, le decimos el indice del elemento a obtener, 
                        //System.out.println(rs.getObject(i + 1));
                    }
                }//Aqui ya lleno la fila
                Profesor p = new Profesor(String.valueOf(fila[0]), String.valueOf(fila[1]),
                        String.valueOf(fila[2]), String.valueOf(fila[3]),
                        String.valueOf(fila[4]), String.valueOf(fila[5]),
                        String.valueOf(fila[6]), String.valueOf(fila[7]),
                        String.valueOf(fila[8]), String.valueOf(fila[9]),
                        String.valueOf(fila[10]), String.valueOf(fila[11]),
                        String.valueOf(fila[12]), String.valueOf(fila[13]), String.valueOf(fila[14]));
                /*for (int i = 0; i < fila.length; i++) {
                    System.out.println("Valor guardado en fila [" + i + "] es : " + fila[i]);
                }*/

                //colocar cellFactoryValue A Tablas
                colocarCellValueFactoryAcolumnas();
                
                tblProfesor.getItems().add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void LeerBDYActualizarTabla(ResultSet result) {
        ResultSet rs;
        try {
            if (filtrarActivado) {
                rs = result;
            } else {
                rs = Profesor.obtenerProfesor();
            }
            metaDatosProfesor = rs.getMetaData();
            int cols = metaDatosProfesor.getColumnCount();

            while (rs.next()) {//Con este ciclo recorremos el Result set, me devuelve falso si llega al puntero EDF(End Of File)
                Object[] fila = new Object[cols];//este arreglo tiene 13 elementos 
                for (int i = 0; i < cols; i++) {
                    if (rs.getObject(i + 1) == null) {//si es null en la BD
                        fila[i] = "";
                    } else {
                        fila[i] = rs.getObject(i + 1);//Empieza en 1 por eso es i+1,  getObject, le decimos el indice del elemento a obtener, 
                    }
                }//Aqui ya lleno la fila
                Profesor p = new Profesor(String.valueOf(fila[0]), String.valueOf(fila[1]),
                        String.valueOf(fila[2]), String.valueOf(fila[3]),
                        String.valueOf(fila[4]), String.valueOf(fila[5]),
                        String.valueOf(fila[6]), String.valueOf(fila[7]),
                        String.valueOf(fila[8]), String.valueOf(fila[9]),
                        String.valueOf(fila[10]), String.valueOf(fila[11]),
                        String.valueOf(fila[12]), String.valueOf(fila[13]), String.valueOf(fila[14]));
                /*for (int i = 0; i < fila.length; i++) {
                    System.out.println("Valor guardado en fila [" + i + "] es : " + fila[i]);
                }*/

                //colocar cellFactoryValue A Tablas
                colocarCellValueFactoryAcolumnas();
                
                tblProfesor.getItems().add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void colocarCellValueFactoryAcolumnas() {
        colTablaProfesor0.setCellValueFactory(new PropertyValueFactory<>("numControlProfesor"));
        colTablaProfesor1.setCellValueFactory(new PropertyValueFactory<>("curpProfesor"));
        colTablaProfesor2.setCellValueFactory(new PropertyValueFactory<>("nombreProfesor"));
        colTablaProfesor3.setCellValueFactory(new PropertyValueFactory<>("primerApellidoProfesor"));
        colTablaProfesor4.setCellValueFactory(new PropertyValueFactory<>("segundoApellidoProfesor"));
        colTablaProfesor5.setCellValueFactory(new PropertyValueFactory<>("sexoProfesor"));
        colTablaProfesor6.setCellValueFactory(new PropertyValueFactory<>("telefonoProfesor"));
        colTablaProfesor7.setCellValueFactory(new PropertyValueFactory<>("correoProfesor"));
        colTablaProfesor8.setCellValueFactory(new PropertyValueFactory<>("fechaNacimientoProfesor"));
        colTablaProfesor9.setCellValueFactory(new PropertyValueFactory<>("usuarioProfesor"));
        colTablaProfesor10.setCellValueFactory(new PropertyValueFactory<>("contraseñaProfesor"));
        colTablaProfesor11.setCellValueFactory(new PropertyValueFactory<>("usuarioAlta"));
        colTablaProfesor12.setCellValueFactory(new PropertyValueFactory<>("fechaAlta"));
        colTablaProfesor13.setCellValueFactory(new PropertyValueFactory<>("usuarioMofidicacion"));
        colTablaProfesor14.setCellValueFactory(new PropertyValueFactory<>("fechaModificacion"));
    }

    public void prepararInsertar() {
        int confirmacion;//variable para guardar valores de confirmacion

        //obtener valores de campos de texto
        String numControlProfesor = (String.valueOf(txtNumControlProfesor.getText()));
        String curpProfesor = String.valueOf(txtCurpProfesor.getText());
        String nombreProfesor = String.valueOf(txtNombreProfesor.getText());
        String primerApellidoProfesor = String.valueOf(txtPrimerApellidoProfesor.getText());
        String segundoApellidoProfesor = (String.valueOf(txtSegundoApellidoProfesor.getText()));
        String sexoProfesor = String.valueOf(txtSexoProfesor.getText());
        String telefonoProfesor = String.valueOf(txtTelefonoProfesor.getText());
        String correoProfesor = String.valueOf(txtCorreoProfesor.getText());
        String fechaNacimientoProfesor = dpkFechaNacimientoProfesor.getValue().format(DateTimeFormatter.ISO_DATE);
        String loginProfesor = "";
        String contraseñaProfesor = "";
        if (txtUsuarioProfesor.getText() == null) {
            loginProfesor = "";
        } else {
            loginProfesor = String.valueOf(txtUsuarioProfesor.getText());
        }

        if (txtContraseñaProfesor.getText() == null) {
            contraseñaProfesor = "";
        } else {
            contraseñaProfesor = (String.valueOf(txtContraseñaProfesor.getText()));
        }

        if (!numControlProfesor.equals("")) {//Si es un profesor nuevo no tiene asignado id aun, entonces este campo esta en blanco
            //verificar que los campos no esten vacios
            if ((!(numControlProfesor.equals("")))
                    && (!(curpProfesor.equals(""))) && (!(nombreProfesor.equals("")))
                    && (!(primerApellidoProfesor.equals(""))) && (!(segundoApellidoProfesor.equals("")))
                    && (!(sexoProfesor.equals(""))) && (!(telefonoProfesor.equals("")))
                    && (!(correoProfesor.equals(""))) && (!(fechaNacimientoProfesor.equals("")))) {

                confirmacion = JOptionPane.showConfirmDialog(null, "Realmente desea agregar un nuevo Profesor?", "Confirmar agregar Profesor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirmacion == 0) {//Se confirmo con un Si
                    try {

                        //Se crea objeto paciente
                        Profesor profesor = new Profesor();

                        //Llenar atributos de objeto
                        profesor.setNumControlProfesor(numControlProfesor);
                        profesor.setCurpProfesor(curpProfesor);
                        profesor.setNombreProfesor(nombreProfesor);
                        profesor.setPrimerApellidoProfesor(primerApellidoProfesor);
                        profesor.setSegundoApellidoProfesor(segundoApellidoProfesor);
                        profesor.setSexoProfesor(sexoProfesor);
                        profesor.setTelefonoProfesor(telefonoProfesor);
                        profesor.setCorreoProfesor(correoProfesor);
                        profesor.setFechaNacimientoProfesor(fechaNacimientoProfesor);
                        profesor.setUsuarioProfesor(loginProfesor);
                        profesor.setContraseñaProfesor(contraseñaProfesor);
                        profesor.setUsuarioAlta(usrId);
                        profesor.setUsuarioMofidicacion(usrId);

                        //se deben validar las 4 inseciones si una falla las demas no deben continuar
                        if (profesor.insertarProfesor() == true) {//si se agrega profesor correctamente
                            JOptionPane.showMessageDialog(null, "Profesor agregado exitosamente");
                            insertarYRefrescar();//ir a método agregar
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al intentar agregar Profesor");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Dato invalido", "Error al ingresar profesor, dato invalido", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor termine de llenar los campos", "Error al agregar profesor, datos incompletos", JOptionPane.PLAIN_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "por favor ingrese el ID", "Error al agregar profesor, aun falta el ID", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void prepararModificar() {
        int confirmacion;//variable para guardar valores de confirmacion
        Profesor prof = tblProfesor.getSelectionModel().getSelectedItem();

        //Se obtienen datos de los campos
        //obtener valores de campos de texto
        String numControlProfesor = (String.valueOf(txtNumControlProfesor.getText()));
        String curpProfesor = String.valueOf(txtCurpProfesor.getText());
        String nombreProfesor = String.valueOf(txtNombreProfesor.getText());
        String primerApellidoProfesor = String.valueOf(txtPrimerApellidoProfesor.getText());
        String segundoApellidoProfesor = (String.valueOf(txtSegundoApellidoProfesor.getText()));
        String sexo = String.valueOf(txtSexoProfesor.getText());
        String tel = String.valueOf(txtTelefonoProfesor.getText());
        String correo = String.valueOf(txtCorreoProfesor.getText());
        String fecNac = dpkFechaNacimientoProfesor.getValue().format(DateTimeFormatter.ISO_DATE);
        String loginProfesor = "";
        String contraseñaProfesor = "";
        if (txtUsuarioProfesor.getText() == null) {
            loginProfesor = "";
        } else {
            loginProfesor = String.valueOf(txtUsuarioProfesor.getText());
        }

        if (txtContraseñaProfesor.getText() == null) {
            contraseñaProfesor = "";
        } else {
            contraseñaProfesor = (String.valueOf(txtContraseñaProfesor.getText()));
        }

        if (prof != null) {//si es null no se a selecionado ninguna fila
            //verificar que los campos no esten vacios
            if ((!(numControlProfesor.equals("")))
                    && (!(curpProfesor.equals(""))) && (!(nombreProfesor.equals("")))
                    && (!(primerApellidoProfesor.equals(""))) && (!(segundoApellidoProfesor.equals("")))
                    && (!(sexo.equals(""))) && (!(tel.equals("")))
                    && (!(correo.equals(""))) && (!(fecNac.equals("")))) {

                confirmacion = JOptionPane.showConfirmDialog(null, "Realmente desea modificar el profesor seleccionado?", "Confirmar modificar Profesor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirmacion == 0) {//Se confirmo con un Si
                    try {

                        //Se crea objeto paciente
                        Profesor profesor = new Profesor();

                        //Llenar atributos de objeto
                        profesor.setNumControlProfesor(numControlProfesor);
                        profesor.setCurpProfesor(curpProfesor);
                        profesor.setNombreProfesor(nombreProfesor);
                        profesor.setPrimerApellidoProfesor(primerApellidoProfesor);
                        profesor.setSegundoApellidoProfesor(segundoApellidoProfesor);
                        profesor.setSexoProfesor(sexo);
                        profesor.setTelefonoProfesor(tel);
                        profesor.setCorreoProfesor(correo);
                        profesor.setUsuarioProfesor(loginProfesor);
                        profesor.setContraseñaProfesor(contraseñaProfesor);
                        profesor.setFechaNacimientoProfesor(fecNac);
                        profesor.setUsuarioMofidicacion(usrId);

                        if (profesor.actualizarProfesor()) {//si se modifica profesor correctamente
                            actualizarYRefrescar();//ir a método 
                            JOptionPane.showMessageDialog(null, "Profesor modificado exitosamente");
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al modificar ");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Dato invalido", "Error al modificar Profesor", JOptionPane.PLAIN_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor termine de llenar los campos", "Error al modificar Profesor ", JOptionPane.PLAIN_MESSAGE);
            }

        } else if (prof == null) {
            JOptionPane.showMessageDialog(null, "No se a seleccionado  ningun profesor", "Error al modificar Profesor", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void actualizarYRefrescar() {

        limpiarTabla();
        ResultSet r = null;
        LeerBDYActualizarTabla(r);
        limpiarCampos();
    }

    public void insertarYRefrescar() {
        limpiarTabla();
        ResultSet r = null;
        LeerBDYActualizarTabla(r);
        limpiarCampos();
    }

    public void prepararEliminar() {
        int confirmacion;//variable para guardar valores de confirmacion
        Profesor prof = tblProfesor.getSelectionModel().getSelectedItem();

        if (prof != null) {//Si da -1 no se a selecionado ninguna fila
            //se verifica que campo id no este vacio, ya que al seleccionar en tabla se llena este campo
            if (!(txtNumControlProfesor.getText().equals(""))) {
                confirmacion = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar el profesor seleccionado?", "Confirmar eliminar profesor", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (confirmacion == 0) {//Se confirmo con un Si
                    //Se obtiene id de los campos
                    String id = (String.format(txtNumControlProfesor.getText()));

                    //se crea objeto paciente
                    Profesor profesor = new Profesor();

                    //se agrea atributo ID y status 0 que es baja
                    profesor.setNumControlProfesor(id);

                    if (profesor.eliminarProfesor()) {//si se pudo eliminar
                        eliminarYActualizar();//ir a método 
                        JOptionPane.showMessageDialog(null, "Profesor eliminado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar ");
                    }
                }
            } else if (txtTelefonoProfesor.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "No se a seleccionado ningun profesor", "Eliminar", JOptionPane.PLAIN_MESSAGE);
            }
        } else if (prof == null) {
            JOptionPane.showMessageDialog(null, "No se a seleccionado  ningun profesor", "Eliminar", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void limpiarTabla() {
        for (int i = 0; i < tblProfesor.getItems().size(); i++) {
            tblProfesor.getItems().clear();
        }
    }

    public void limpiarCampos() {
        //Poner campos vacios
        txtNumControlProfesor.clear();
        txtCurpProfesor.clear();
        txtNombreProfesor.clear();
        txtPrimerApellidoProfesor.clear();
        txtSegundoApellidoProfesor.clear();
        txtSexoProfesor.clear();
        txtTelefonoProfesor.clear();
        txtCorreoProfesor.clear();
        dpkFechaNacimientoProfesor.setValue(LocalDate.parse("2000-01-01"));
        txtUsuarioProfesor.clear();
        txtContraseñaProfesor.clear();
    }

    public void mostrarFilaSeleccionada() {
        Profesor prof = tblProfesor.getSelectionModel().getSelectedItem();

        if (prof == null) {
            limpiarCampos();
            //System.out.println("nada seleccionado");
        } else {
            //System.out.println(prof.getNumControlProfesor());
            //System.out.println(prof.getNombreProfesor());
            //Obtener datos de la tablaDatosProfesor en fila selecionada
            String numControl = prof.getNumControlProfesor();
            String curp = prof.getCurpProfesor();
            String nombre = prof.getNombreProfesor();
            String primerAp = prof.getPrimerApellidoProfesor();
            String segundoAp = prof.getSegundoApellidoProfesor();
            String sexo = prof.getSexoProfesor();
            String telefono = prof.getTelefonoProfesor();
            String correo = prof.getCorreoProfesor();
            String fechaNac = prof.getFechaNacimientoProfesor();
            String login = prof.getUsuarioProfesor();
            String pass = prof.getContraseñaProfesor();

            //Se pone la informacion en los campos correspondientes
            txtNumControlProfesor.setText(numControl);
            txtCurpProfesor.setText(curp);
            txtNombreProfesor.setText(nombre);
            txtPrimerApellidoProfesor.setText(primerAp);
            txtSegundoApellidoProfesor.setText(segundoAp);
            txtSexoProfesor.setText(sexo);
            txtTelefonoProfesor.setText(telefono);
            txtCorreoProfesor.setText(correo);
            dpkFechaNacimientoProfesor.setValue(LocalDate.parse(fechaNac));
            txtUsuarioProfesor.setText(login);
            txtContraseñaProfesor.setText(pass);

        }

    }

    public void cancelar() {
        limpiarCampos();
    }

    public void eliminarYActualizar() {
        try {
            limpiarCampos();
            limpiarTabla();
            ResultSet r = null;
            LeerBDYActualizarTabla(r);

            //JOptionPane.showMessageDialog(VistaProductos.this, "Se elimino el  con exito", "Eliminar", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception error) {
            JOptionPane.showMessageDialog(null, "No se a seleccionado ningun Profesor", "Eliminar", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @FXML
    private void handleFiltrarProfesor() {
        filtrarActivado = !filtrarActivado;
        if (filtrarActivado) {
            btnInsertarProfesor.setDisable(true);
            btnActualizarProfesor.setDisable(true);
            btnEliminarProfesor.setDisable(true);

            txtNumControlProfesor.textProperty().addListener(manejador);
            txtCurpProfesor.textProperty().addListener(manejador);
            txtNombreProfesor.textProperty().addListener(manejador);
            txtPrimerApellidoProfesor.textProperty().addListener(manejador);
            txtSegundoApellidoProfesor.textProperty().addListener(manejador);
            txtSexoProfesor.textProperty().addListener(manejador);
            txtTelefonoProfesor.textProperty().addListener(manejador);
            txtCorreoProfesor.textProperty().addListener(manejador);

            limpiarCampos();

        } else {
            btnInsertarProfesor.setDisable(false);
            btnActualizarProfesor.setDisable(false);
            btnEliminarProfesor.setDisable(false);

            txtNumControlProfesor.textProperty().removeListener(manejador);
            txtCurpProfesor.textProperty().removeListener(manejador);
            txtNombreProfesor.textProperty().removeListener(manejador);
            txtPrimerApellidoProfesor.textProperty().removeListener(manejador);
            txtSegundoApellidoProfesor.textProperty().removeListener(manejador);
            txtSexoProfesor.textProperty().removeListener(manejador);
            txtTelefonoProfesor.textProperty().removeListener(manejador);
            txtCorreoProfesor.textProperty().removeListener(manejador);

        }
    }

    void ManejadorFiltro() {
        //System.out.println("si entra al metodo");
        if (filtrarActivado) {
            String numControl, curp, nombre, apellidoP, apellidoM, sexo, telefono, correo;
            //System.out.println("Si entra if");
            numControl = txtNumControlProfesor.getText();
            curp = txtCurpProfesor.getText();
            nombre = txtNombreProfesor.getText();
            apellidoP = txtPrimerApellidoProfesor.getText();
            apellidoM = txtSegundoApellidoProfesor.getText();
            sexo = txtSexoProfesor.getText();
            telefono = txtTelefonoProfesor.getText();
            correo = txtCorreoProfesor.getText();

            leerFiltrarTabla(numControl, curp, nombre, apellidoP, apellidoM, sexo, telefono, correo);
           // System.out.println(nombre);
        }
    }

    private void leerFiltrarTabla(String id, String curp, String nombre, String apellidoP,
            String apellidoM, String sexo, String telefono, String correo) {
       // System.out.println("entro a leerFiltrarTabla");
        limpiarTabla();
        LeerBDYActualizarTabla(Profesor.filtrarProfesor(id, curp, nombre, apellidoP, apellidoM, sexo, telefono, correo));
    }

    class ManejadorEventos implements ChangeListener {
        @Override
        public void changed(ObservableValue observable, Object oldValue, Object newValue) {
            ManejadorFiltro();
            
            
        }
    }

}
