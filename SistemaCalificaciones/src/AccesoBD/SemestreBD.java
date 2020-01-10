
package AccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Semestre;


public class SemestreBD {
    Connection connect;
    public SemestreBD(Connection connect) {
        this.connect = connect;
    }
    
    public ArrayList<Semestre> getSemestres(){
        ArrayList<Semestre> listaSemestres = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            ResultSet rs = connect.prepareCall("CALL getSemestres").executeQuery(); //Para MySql
            while (rs.next()){
                listaSemestres.add(new Semestre(rs.getString(1), rs.getString(2),rs.getString(3), 
                        rs.getString(4).substring(0, 4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SemestreBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSemestres;
    }
    
    public void addSemestre(String IDSemestre, String semestre, String periodo, String año, String usuarioMod) throws SQLException{
            PreparedStatement statement = connect.prepareCall("CALL addSemestre(?, ?, ?, ?, ?)");
            System.out.println("MNoansisadn");
            statement.setString(1, IDSemestre);
            statement.setString(2, semestre);
            statement.setString(3, periodo);
            statement.setString(4, año);
            statement.setString(5, usuarioMod);
            System.out.println(statement);
            statement.execute();
        
    }
    
    public void updateSemestre(String IDSemestre, String semestre, String periodo, String año, String usuarioMod) throws SQLException{
        
            PreparedStatement statement = connect.prepareCall("CALL updateSemestre(?, ?, ?, ?, ?)");
            statement.setString(1, IDSemestre);
            statement.setString(2, semestre);
            statement.setString(3, periodo);
            statement.setString(4, año);
            statement.setString(5, usuarioMod);
            System.out.println(statement);
            statement.execute();
        
    }

    public void deleteSemestre(String ID) throws SQLException {
        
            PreparedStatement statement = connect.prepareCall("CALL deleteSemestre(?)");
            statement.setString(1, ID);
            System.out.println(statement);
            statement.execute();   
    }
    
    public ArrayList<Semestre> getSemestreFiltro(String IDSemestre, String semestre, String periodo, String año){
        ArrayList<Semestre> listaSemestres = new ArrayList<>();
        
        try {
            PreparedStatement ps = connect.prepareStatement("CALL   getBusquedaSemestre(?, ?, ?, ?)"); //Para MySql
            
            ps.setString(1, IDSemestre);
            ps.setString(2, semestre);
            ps.setString(3, periodo);
            ps.setString(4, año);
            
            System.out.println(ps); 
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                listaSemestres.add(new Semestre(rs.getString(1), rs.getString(2),rs.getString(3), 
                        rs.getString(4).substring(0, 4), rs.getString(5), rs.getString(6), rs.getString(7),rs.getString(8)));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(SemestreBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaSemestres;
    }
}
