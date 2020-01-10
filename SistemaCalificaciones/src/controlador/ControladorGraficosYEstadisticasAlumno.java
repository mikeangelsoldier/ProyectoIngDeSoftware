/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import AccesoBD.ConectaBD_IS;
import AccesoBD.TablaGraficasAlumnoBD;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import modelo.TablaGraficasAlumno;
/**
 * FXML Controller class
 *
 * @author Personal
 */
public class ControladorGraficosYEstadisticasAlumno implements Initializable {
    ConectaBD_IS conectaBD;
    TablaGraficasAlumnoBD tablaGraficasAlumnoBD;
    ArrayList<TablaGraficasAlumno> listaTablaGraficasAlumnosArray;
    
    final static String IS = "Ingeniería de software";
    final static String TSO = "TSO";
    final static String REDES = "Redes de computadoras";
    final static String LYA = "Lenguajes y Autómatas";
    final static String ABD = "ABD";
    final static String LI = "Lenguajes de interfaz";
    
    int contadorMateriasAprobadas, contadorMateriasReprobadas;
    
    @FXML
    private Label lblMateriasReprobadas, lblMateriasAprobadas;
    
    @FXML
    private TableView tblTablaGraficaAlumno;
    
    @FXML
    private TableColumn tbcClave, tbcNombreMateria, tbcGrupo, tbcF1, tbcP1,
                        tbcF2, tbcP2,tbcF3, tbcP3, tbcTF,tbcCF,tbcNombreMaestro;
    
    @FXML
    private CategoryAxis xAxis;
    
    @FXML
    private NumberAxis yAxis;
    
    @FXML
    private BarChart<String,Number> bc;
    
    @FXML
    private JFXButton btnTabla, btnGraficas;
    
    @FXML
    private Pane panelTabla, panelGraficas;
    
    //Arreglo de datas
    //XYChart.Data<String,Number> datas = new XYChart.Data<>();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        conectaBD = new ConectaBD_IS();
        tablaGraficasAlumnoBD = new TablaGraficasAlumnoBD(conectaBD.getConnection());
        llenarTabla(tablaGraficasAlumnoBD.getMaterias());
        contarMateriasAprobadasyReprobadas();
        
        XYChart.Series series1 = new XYChart.Series();
        bc.setTitle("Gráfica del alumno");
        bc.setCategoryGap(30);
        xAxis.setLabel("Parciales");       
        yAxis.setLabel("Calificación");
        
        //series1.getData().add(datas);
        //Calificaciones del parcial 1:
        series1.setName("Parcial 1");
        /*series1.getData().add(new XYChart.Data(IS, 75));
        series1.getData().add(new XYChart.Data(TSO, 50));
        series1.getData().add(new XYChart.Data(REDES, 100));
        series1.getData().add(new XYChart.Data(LYA, 75));
        series1.getData().add(new XYChart.Data(ABD, 82));
        series1.getData().add(new XYChart.Data(LI, 30));*/
        for (int i = 0; i < listaTablaGraficasAlumnosArray.size(); i++) {
            series1.getData().add(new XYChart.Data(listaTablaGraficasAlumnosArray.get(i).getNombreMateria(), 
                                                   Double.parseDouble(listaTablaGraficasAlumnosArray.get(i).getP1())));
        }

        //Calificaciones del parcial 2:
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Parcial 2");
        /*series2.getData().add(new XYChart.Data(IS, 77));
        series2.getData().add(new XYChart.Data(TSO, 91));
        series2.getData().add(new XYChart.Data(REDES, 65));
        series2.getData().add(new XYChart.Data(LYA, 90));
        series2.getData().add(new XYChart.Data(ABD, 80));
        series2.getData().add(new XYChart.Data(LI, 10));*/
        for (int i = 0; i < listaTablaGraficasAlumnosArray.size(); i++) {
            series2.getData().add(new XYChart.Data(listaTablaGraficasAlumnosArray.get(i).getNombreMateria(), 
                                                   Double.parseDouble(listaTablaGraficasAlumnosArray.get(i).getP2())));
        }
        
        //Calificaciones del parcial 3:
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Parcial 3");
        /*series3.getData().add(new XYChart.Data(IS, 75));
        series3.getData().add(new XYChart.Data(TSO, 82));
        series3.getData().add(new XYChart.Data(REDES, 98));
        series3.getData().add(new XYChart.Data(LYA, 70));
        series3.getData().add(new XYChart.Data(ABD, 64));
        series3.getData().add(new XYChart.Data(LI, 12));*/
        for (int i = 0; i < listaTablaGraficasAlumnosArray.size(); i++) {
            series3.getData().add(new XYChart.Data(listaTablaGraficasAlumnosArray.get(i).getNombreMateria(), 
                                                   Double.parseDouble(listaTablaGraficasAlumnosArray.get(i).getP3())));
        }
        
        //Calificaciones del parcial Final:
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Parcial Final");
        /*series4.getData().add(new XYChart.Data(IS, 75));
        series4.getData().add(new XYChart.Data(TSO, 84));
        series4.getData().add(new XYChart.Data(REDES, 87));
        series4.getData().add(new XYChart.Data(LYA, 97));
        series4.getData().add(new XYChart.Data(ABD, 73));
        series4.getData().add(new XYChart.Data(LI, 15));*/
        for (int i = 0; i < listaTablaGraficasAlumnosArray.size(); i++) {
            series4.getData().add(new XYChart.Data(listaTablaGraficasAlumnosArray.get(i).getNombreMateria(), 
                                                   Double.parseDouble(listaTablaGraficasAlumnosArray.get(i).getCF())));
        }
        
        //Añadir parciales a la gráfica
        bc.getData().addAll(series1, series2, series3, series4);
    }   
    
    @FXML
    private void btnVerTabla(ActionEvent event) {
        contarMateriasAprobadasyReprobadas();
        panelTabla.setVisible(true);
        panelGraficas.setVisible(false);
    }
    
    @FXML
    private void btnVerGraficas(ActionEvent event) {
        panelTabla.setVisible(false);
        panelGraficas.setVisible(true);
    }
    
    private void llenarTabla(ArrayList<TablaGraficasAlumno> listaTablaGraficasAlumnos){
               
        tblTablaGraficaAlumno.getItems().clear();
        
        tbcClave.setCellValueFactory(new PropertyValueFactory<>("clave"));
        tbcNombreMateria.setCellValueFactory(new PropertyValueFactory<>("nombreMateria"));
        tbcGrupo.setCellValueFactory(new PropertyValueFactory<>("grupo"));
        tbcF1.setCellValueFactory(new PropertyValueFactory<>("f1"));
        tbcP1.setCellValueFactory(new PropertyValueFactory<>("p1"));
        tbcF2.setCellValueFactory(new PropertyValueFactory<>("f2"));
        tbcP2.setCellValueFactory(new PropertyValueFactory<>("p2"));
        tbcF3.setCellValueFactory(new PropertyValueFactory<>("f3"));
        tbcP3.setCellValueFactory(new PropertyValueFactory<>("p3"));
        tbcTF.setCellValueFactory(new PropertyValueFactory<>("tF"));
        tbcCF.setCellValueFactory(new PropertyValueFactory<>("cF"));
        tbcNombreMaestro.setCellValueFactory(new PropertyValueFactory<>("nombreMaestro"));
        
  
        for (TablaGraficasAlumno tga : listaTablaGraficasAlumnos) {
            tblTablaGraficaAlumno.getItems().add(tga);
        }
        
        listaTablaGraficasAlumnosArray = listaTablaGraficasAlumnos;
    }
    
    void contarMateriasAprobadasyReprobadas(){
        contadorMateriasAprobadas=0;
        contadorMateriasReprobadas=0;
        for (int i = 0; i < listaTablaGraficasAlumnosArray.size(); i++) {
            if (Integer.parseInt(listaTablaGraficasAlumnosArray.get(i).getCF()) < 70) {
                contadorMateriasReprobadas++;
            }else{
                contadorMateriasAprobadas++;
            }
        }
        lblMateriasReprobadas.setText(contadorMateriasReprobadas+"");
        lblMateriasAprobadas.setText(contadorMateriasAprobadas+"");
    }
    
}
