package AccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Carrera;
import modelo.Fecha;


public class FechaBD {
     Connection connect;
    public FechaBD(Connection connect) {
        this.connect = connect;
    }
    
     public Fecha getFecha(){
        Fecha fecha = null;
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            ResultSet rs = connect.prepareCall("CALL getFecha").executeQuery(); //Para MySql
            while (rs.next()){
                fecha = new Fecha(rs.getString(1), rs.getString(2), 
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fecha;
    }
    
    public void modificarFecha(String fecha1, String fecha2, String fecha3, String fechaFinal, String periodo) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL updateFecha(?, ?, ?, ?, ?)");
            
            statement.setString(1, fecha1);
            statement.setString(2, fecha2);
            statement.setString(3, fecha3);
            statement.setString(4, fechaFinal);
            statement.setString(5, periodo);
            
            System.out.println(statement);
            statement.execute();
        
    }
}
