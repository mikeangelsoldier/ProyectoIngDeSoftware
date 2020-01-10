
package AccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Alumno;


public class AlumnoBD {
    Connection connect;
    public AlumnoBD(Connection connect) {
        this.connect = connect;
    }
    
    public ArrayList<Alumno> getAlumnos(){
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            ResultSet rs = connect.prepareCall("CALL getAlumnos").executeQuery(); //Para MySql
            while (rs.next()){
                listaAlumnos.add(new Alumno(rs.getString(1), rs.getString(2),  rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), 
                        rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAlumnos;
    }
    
    public void addAlumno(String ID, String curp, String nombre, String apellidoP, String apellidoM, 
            String sexo, String fechaNac, String domicilio, String telefono, String correo, String semestreAlumno, String usuarioMod, String user, String password) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL addAlumno(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, ID);
            statement.setString(2, curp);
            statement.setString(3, nombre);
            statement.setString(4, apellidoP);
            statement.setString(5, apellidoM);
            statement.setString(6, sexo);
            statement.setString(7, fechaNac);
            statement.setString(8, domicilio);
            statement.setString(9, telefono);
            statement.setString(10, correo);
            statement.setString(11, semestreAlumno);
            statement.setString(12, user);
            statement.setString(13, password);
            statement.setString(14, usuarioMod);
            System.out.println(statement.toString());
            statement.execute();
        
    }
    
    public void updateAlumno(String actualID, String curp, String nombre, String apellidoP, String apellidoM, 
            String sexo, String fechaNac, String domicilio, String telefono, String correo, String semestreAlumno, String user, String password, String usuarioMod) throws SQLException{
            PreparedStatement statement = connect.prepareCall("CALL updateAlumno(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, actualID);
            statement.setString(2, curp);
            statement.setString(3, nombre);
            statement.setString(4, apellidoP);
            statement.setString(5, apellidoM);
            statement.setString(6, sexo);
            statement.setString(7, fechaNac);
            statement.setString(8, domicilio);
            statement.setString(9, telefono);
            statement.setString(10, correo);
            statement.setString(11, semestreAlumno);
            statement.setString(12, user);
            statement.setString(13, password);
            statement.setString(14, usuarioMod);
            System.out.println(statement);
            statement.execute();
        
    }

    public void deleteAlumno(String ID) throws SQLException {
        
            PreparedStatement statement = connect.prepareCall("CALL deleteAlumno(?)");
            statement.setString(1, ID);
            System.out.println(statement);
            statement.execute();   
    }
    
    public ArrayList<Alumno> getAlumnosFiltro(String id, String curp, String nombre, String apellidoP, 
                                  String apellidoM, String sexo, String domicilio, String telefono, String correo, String semestreAlumno){
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareStatement("CALL   getBusquedaAlumno(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"); //Para MySql
            
            ps.setString(1, id);
            ps.setString(2, curp);
            ps.setString(3, nombre);
            ps.setString(4, apellidoP);
            ps.setString(5, apellidoM);
            ps.setString(6, sexo);
            ps.setString(7, domicilio);
            ps.setString(8, telefono);
            ps.setString(9, correo);
            ps.setString(10, semestreAlumno);
            System.out.println(ps); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                listaAlumnos.add(new Alumno(rs.getString(1), rs.getString(2),  rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), 
                        rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17)));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaAlumnos;
    }
}
