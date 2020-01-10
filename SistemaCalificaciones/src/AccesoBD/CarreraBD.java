/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Carrera;

/**
 *
 * @author alfre
 */
public class CarreraBD {
    Connection connect;
    public CarreraBD(Connection connect) {
        this.connect = connect;
    }
    
    public ArrayList<Carrera> getCarreras(){
        ArrayList<Carrera> listaMaterias = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            ResultSet rs = connect.prepareCall("CALL getCarreras").executeQuery(); //Para MySql
            while (rs.next()){
                listaMaterias.add(new Carrera(rs.getString(1), rs.getString(2), 
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMaterias;
    }
    
    public void addCarrera(String idCarrera, String nombre, String descripcion, String usuarioMod) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL addCarrera(?, ?, ?, ?)");
            
            statement.setString(1, idCarrera);
            statement.setString(2, nombre);
            statement.setString(3, descripcion);
            statement.setString(4, usuarioMod);
            System.out.println(statement);
            statement.execute();
        
    }
    
    public void updateCarrera(String idCarrera, String nombre, String descripcion, String usuarioMod) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL updateCarrera(?, ?, ?, ?)");
            statement.setString(1, idCarrera);
            statement.setString(2, nombre);
            statement.setString(3, descripcion);
            statement.setString(4, usuarioMod);
            System.out.println(statement);
            statement.execute();
        
    }

    public void deleteCarrera(String idCarrera) throws SQLException {
        
            PreparedStatement statement = connect.prepareCall("CALL deleteCarrera(?)");
            statement.setString(1, idCarrera);
            statement.execute();   
    }
}
