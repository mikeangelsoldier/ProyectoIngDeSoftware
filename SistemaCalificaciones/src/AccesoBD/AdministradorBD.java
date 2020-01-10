
package AccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Administrador;
import modelo.Alumno;


public class AdministradorBD {
    Connection connect;
    public AdministradorBD(Connection connect) {
        this.connect = connect;
    }
    
    public ArrayList<Administrador> getAdministradores(){
        ArrayList<Administrador> listaAdministradores = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAdministradores").executeQuery(); //Para SQL Server
            ResultSet rs = connect.prepareCall("CALL getAdministradores").executeQuery(); //Para MySql
            while (rs.next()){
                listaAdministradores.add(new Administrador(rs.getString(1), rs.getString(2),  rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), 
                        rs.getString(12), rs.getString(13), rs.getString(14)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAdministradores;
    }
    
    public void addAdministrador(String numControl, String nombre, String primerApellido, String segundoApellido, String sexo, 
            String cargo, String telefono, String correo, String login, String passw, String usuarioMod) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL addAdministrador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, numControl);
            statement.setString(2, nombre);
            statement.setString(3, primerApellido);
            statement.setString(4, segundoApellido);
            statement.setString(5, sexo);
            statement.setString(6, cargo);
            statement.setString(7, telefono);
            statement.setString(8, correo);
            statement.setString(9, login);
            statement.setString(10, passw);
            statement.setString(11, usuarioMod);
            System.out.println(statement.toString());
            statement.execute();
        
    }
    
    public void updateAdministrador(String numControl, String nombre, String primerApellido, String segundoApellido, String sexo, 
            String cargo, String telefono, String correo, String login, String passw, String usuarioMod) throws SQLException{
            PreparedStatement statement = connect.prepareCall("CALL updateAdministrador(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, numControl);
            statement.setString(2, nombre);
            statement.setString(3, primerApellido);
            statement.setString(4, segundoApellido);
            statement.setString(5, sexo);
            statement.setString(6, cargo);
            statement.setString(7, telefono);
            statement.setString(8, correo);
            statement.setString(9, login);
            statement.setString(10, passw);
            statement.setString(11, usuarioMod);
            System.out.println(statement);
            statement.execute();
        
    }

    public void deleteAdministrador(String ID) throws SQLException {
        
            PreparedStatement statement = connect.prepareCall("CALL deleteAdministrador(?)");
            statement.setString(1, ID);
            System.out.println(statement);
            statement.execute();   
    }
    
    public ArrayList<Administrador> getAdministradoresFiltro(String numControl, String nombre, String primerApellido, String segundoApellido, String sexo, 
            String cargo, String telefono, String correo){
        ArrayList<Administrador> listaAdministradores = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAdministradores").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareStatement("CALL   getBusquedaAdministrador(?, ?, ?, ?, ?, ?, ?, ?)"); //Para MySql
            
            ps.setString(1, numControl);
            ps.setString(2, nombre);
            ps.setString(3, primerApellido);
            ps.setString(4, segundoApellido);
            ps.setString(5, sexo);
            ps.setString(6, cargo);
            ps.setString(7, telefono);
            ps.setString(8, correo);
            
             System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                listaAdministradores.add(new Administrador(rs.getString(1), rs.getString(2),  rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), 
                        rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAdministradores;
    }
}
