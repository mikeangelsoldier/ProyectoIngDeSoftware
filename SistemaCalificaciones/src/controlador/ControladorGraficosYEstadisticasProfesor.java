/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Pane;

public class ControladorGraficosYEstadisticasProfesor implements Initializable {

    final static String PARCIAL1 = "Parcial 1";
    final static String PARCIAL2 = "Parcial 2";
    final static String PARCIAL3 = "Parcial 3";
    final static String PARCIALFINAL = "Parcial Final";
    
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        XYChart.Series series1 = new XYChart.Series();
        bc.setTitle("Gráfica del grupo x");
        bc.setCategoryGap(30);
        xAxis.setLabel("Materia");       
        yAxis.setLabel("Porcentaje");
        
        series1.setName("Aprobados");
        series1.getData().add(new XYChart.Data(PARCIAL1, 75));
        series1.getData().add(new XYChart.Data(PARCIAL2, 50));
        series1.getData().add(new XYChart.Data(PARCIAL3, 90));
        series1.getData().add(new XYChart.Data(PARCIALFINAL, 85));
        
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Reprobados");
        series2.getData().add(new XYChart.Data(PARCIAL1, 20));
        series2.getData().add(new XYChart.Data(PARCIAL2, 30));
        series2.getData().add(new XYChart.Data(PARCIAL3, 9));
        series2.getData().add(new XYChart.Data(PARCIALFINAL, 11));
        
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Desertores");
        series3.getData().add(new XYChart.Data(PARCIAL1, 5));
        series3.getData().add(new XYChart.Data(PARCIAL2, 20));
        series3.getData().add(new XYChart.Data(PARCIAL3, 1));
        series3.getData().add(new XYChart.Data(PARCIALFINAL, 4));
                
        bc.getData().addAll(series1, series2, series3);
        
        /*Colores
        bc.lookupAll(".default-color0.chart-bar")
            .forEach(n -> n.setStyle("-fx-bar-fill: rgb(40,170,70);"));
        bc.lookupAll(".default-color1.chart-bar")
            .forEach(n -> n.setStyle("-fx-bar-fill: rgb(250,95,50);"));
        bc.lookupAll(".default-color2.chart-bar")
            .forEach(n -> n.setStyle("-fx-bar-fill: rgb(255,45,45);"));
        */
    }    
    
    @FXML
    private void btnVerTabla(ActionEvent event) {
        panelTabla.setVisible(true);
        panelGraficas.setVisible(false);
    }
    
    @FXML
    private void btnVerGraficas(ActionEvent event) {
        panelTabla.setVisible(false);
        panelGraficas.setVisible(true);
    }
    
}
