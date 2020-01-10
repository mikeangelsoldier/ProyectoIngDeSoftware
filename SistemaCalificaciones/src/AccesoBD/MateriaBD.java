
package AccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Materia;


public class MateriaBD {
    Connection connect;
    public MateriaBD(Connection connect) {
        this.connect = connect;
    }
    
    public ArrayList<Materia> getMaterias(){
        ArrayList<Materia> listaMaterias = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            ResultSet rs = connect.prepareCall("CALL getMaterias").executeQuery(); //Para MySql
            while (rs.next()){
                listaMaterias.add(new Materia(rs.getString(1), rs.getString(2), 
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMaterias;
    }
    
    public void addMateria(String clave_materia, String nombre, String tipo, String SATCA, String semestre, 
                           String planEstudios, String totalUnidades, String descripcion, String usuarioMod) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL addMateria(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            statement.setString(1, clave_materia);
            statement.setString(2, nombre);
            statement.setString(3, tipo);
            statement.setString(4, SATCA);
            statement.setString(5, semestre);
            statement.setString(6, planEstudios);
            statement.setString(7, totalUnidades);
            statement.setString(8, descripcion);
            statement.setString(9, usuarioMod);
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
            Logger.getLogger(MateriaBD.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MateriaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return carreras;
    }
   
}
