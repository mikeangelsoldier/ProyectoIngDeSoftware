/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Reportes;

import AccesoBD.ConectaBD_IS;
import AccesoBD.ConexionJavaSQLServer;
import static AccesoBD.ConexionJavaSQLServer.connect;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer; 


public class GeneradorReportes {  
   public void reporteSemestre(String nombre){ 
        
        try{
            JasperReport reporte = (JasperReport) JRLoader.loadObject("ReporteParcial.jasper");
            Map parametro = new HashMap();
            parametro.put("grupo", nombre);
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, ConexionJavaSQLServer.connect);
            JasperViewer jv = new JasperViewer(j,false);
            jv.setTitle("Reporte parcial");
            jv.setVisible(true);
            
        }catch(Exception e){ 
            JOptionPane.showMessageDialog(null, "Error "); 
          e.printStackTrace();
            System.out.println(e.getMessage());
        } 
    } 
     
    public void reporteCalificaciones(String grupo){
           try{
                connect= DriverManager.getConnection("jdbc:mysql://localhost:3306/IngenieriaSoftware","root","");
            JasperReport reporte = (JasperReport) JRLoader.loadObject("ReporteCalificaciones.jasper"); 
            Map parametro = new HashMap();
          parametro.put("grp", grupo);
            JasperPrint j = JasperFillManager.fillReport(reporte, parametro, ConexionJavaSQLServer.connect);
            JasperViewer jv = new JasperViewer(j,false);
            jv.setTitle("Reporte calificaciones");
            jv.setVisible(true);
            
        }catch(Exception e){   
            JOptionPane.showMessageDialog(null, "Error ");
          e.printStackTrace();
            System.out.println(e.getMessage());  
        } 
    }
}   
  
   



