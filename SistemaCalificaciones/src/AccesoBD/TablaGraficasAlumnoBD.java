
package AccesoBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.TablaGraficasAlumno;


public class TablaGraficasAlumnoBD {
    Connection connect;
    public TablaGraficasAlumnoBD(Connection connect) {
        this.connect = connect;
    }
    
    public ArrayList<TablaGraficasAlumno> getMaterias(){
        ArrayList<TablaGraficasAlumno> listaTablaGraficasAlumnos = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            ResultSet rs = connect.prepareCall("CALL getTablaVistaGraficas(10)").executeQuery(); //Para MySql
            while (rs.next()){
                listaTablaGraficasAlumnos.add(new TablaGraficasAlumno(rs.getString(1), rs.getString(2), 
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaGraficasAlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaTablaGraficasAlumnos; 
    }
    
    /*
    public void addRegistroMateria(String clave, String nombreMateria, String grupo, String f1, String p1, String f2, String p2, 
                                   String f3, String p3, String fFinal, String pFinal, String nombreMaestro) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL addMateria(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            statement.setString(1, clave);
            statement.setString(2, nombreMateria);
            statement.setString(3, grupo);
            statement.setString(4, f1);
            statement.setString(5, p1);
            statement.setString(6, f2);
            statement.setString(7, p2);
            statement.setString(8, f3);
            statement.setString(9, p3);
            statement.setString(10, fFinal);
            statement.setString(11, pFinal);
            statement.setString(12, nombreMaestro);
            System.out.println(statement);
            statement.execute();
        
    }
    
    public void updateMateria(String clave_materia, String nombre, String tipo, String SATCA, String semestre, 
                           String planEstudios, String descripcion, String totalUnidades, String usuarioMod) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL updateMateria(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, clave_materia);
            statement.setString(2, nombre);
            statement.setString(3, tipo);
            statement.setString(4, SATCA);
            statement.setString(5, semestre);
            statement.setString(6, planEstudios);
            statement.setString(7, totalUnidades);
            statement.setString(8, descripcion);
            statement.setString(9, usuarioMod);
            statement.execute();
        
    }

    public void deleteMateria(String ID) throws SQLException {
        
            PreparedStatement statement = connect.prepareCall("CALL deleteMateria(?)");
            statement.setString(1, ID);
            statement.execute();   
    }
    
    public ArrayList<Materia> getMateriasFiltro(String clave_materia, String nombre, String tipo, String SATCA, String semestre, 
                           String planEstudios, String descripcion){
        ArrayList<Materia> listaMaterias = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareStatement("CALL   getBusquedaMateria(?, ?, ?, ?, ?, ?, ?)"); //Para MySql
            
            ps.setString(1, clave_materia);
            ps.setString(2, nombre);
            ps.setString(3, tipo);
            ps.setString(4, SATCA);
            ps.setString(5, semestre);
            ps.setString(6, planEstudios);
            ps.setString(7, descripcion);
            
             
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                listaMaterias.add(new Materia(rs.getString(1), rs.getString(2), 
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(TablaGraficasAlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMaterias;
    }
    
    public void addCarreraMateria(String IDCarrera, String IDMateria) throws SQLException{
         PreparedStatement statement = connect.prepareCall("CALL addCarreraMateria(?, ?)");
            
            statement.setString(1, IDCarrera);
            statement.setString(2, IDMateria);
            System.out.println(statement);
            statement.execute(); 
    }
    
     public ArrayList<String> getCarrerasOfMateria(String IDMateria){
        ArrayList<String> carreras = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareCall("CALL getCarrerasOfMateria(?)");
            ps.setString(1, IDMateria);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                carreras.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TablaGraficasAlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carreras;
    }
   */
}
