
package controlador;

import AccesoBD.AlumnoBD;
import AccesoBD.ConectaBD_IS;
import AccesoBD.FechaBD;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import modelo.Fecha;

public class controladorFechasParciales implements Initializable {
    ConectaBD_IS conectaBD;
    FechaBD fechaBD;
    
    @FXML
    private JFXComboBox cboPeriodo;
    
    @FXML
    private JFXDatePicker datParcial1, datParcial2, datParcial3, datParcialFinal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conectaBD = new ConectaBD_IS();
        fechaBD = new FechaBD(conectaBD.getConnection());
        cboPeriodo.getItems().add("Enero-Julio");
        cboPeriodo.getItems().add("Agosto-Diciembre");
        llenarCampos();
    }    
    
    
     @FXML
    private void handleButtonActualizar(ActionEvent event) {
        
        
        actualizarFechas();
        llenarCampos();
    }
    
      private void actualizarFechas(){
        try {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmación");
            alert.setHeaderText(null);
            alert.setContentText("¿Realmente deseas actualizar el las fechas del semestre?");           
            if(alert.showAndWait().get() == ButtonType.OK){
                fechaBD.modificarFecha(datParcial1.getValue().format(DateTimeFormatter.ISO_DATE), 
                        datParcial2.getValue().format(DateTimeFormatter.ISO_DATE), 
                        datParcial3.getValue().format(DateTimeFormatter.ISO_DATE), 
                        datParcialFinal.getValue().format(DateTimeFormatter.ISO_DATE), 
                        cboPeriodo.getSelectionModel().getSelectedItem().toString());
                
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
            alert.setContentText("Las fechas no se han podido actualizar. Error al acceder a la base de datos");
            alert.show();
            ex.printStackTrace();
        }
 
    }

    private void llenarCampos() {
        Fecha fecha = fechaBD.getFecha();
        cboPeriodo.getSelectionModel().select(fecha.getPeriodo());
        datParcial1.setValue(LocalDate.parse(fecha.getFecha1()));
        datParcial2.setValue(LocalDate.parse(fecha.getFecha2()));
        datParcial3.setValue(LocalDate.parse(fecha.getFecha3()));
        datParcialFinal.setValue(LocalDate.parse(fecha.getFechaFinal()));
    }
    
}










