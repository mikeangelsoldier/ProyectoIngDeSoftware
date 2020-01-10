
package AccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Alumno;
import modelo.Grupo;
import modelo.Materia;

public class GrupoBD {
    Connection connect;
    public GrupoBD(Connection connect) {
        this.connect = connect;
    }
    
    
      
    public ArrayList<Grupo> getGrupos(String idCarreraMateria){
        ArrayList<Grupo> listaGrupos = new ArrayList<>();
        
        try {
            PreparedStatement ps = connect.prepareCall("CALL getGruposDeUnaCarrera(?)");
            ps.setString(1, idCarreraMateria);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listaGrupos.add(new Grupo(rs.getString(1), rs.getString(2),  rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGrupos;
    } 
    
    public void addGrupo(String claveGrupo, String grupo, String campus, String horaAsignada, String aula, 
                         String capacidad, String maximoFaltas, String catedraticoFK, String carreraMateriaFK) throws SQLException{
        PreparedStatement statement = connect.prepareCall("CALL addGrupo(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, claveGrupo);
            statement.setString(2, grupo);
            statement.setString(3, campus);
            statement.setString(4, horaAsignada);
            statement.setString(5, aula);
            statement.setString(6, capacidad);
            statement.setString(7, maximoFaltas);
            statement.setString(8, catedraticoFK);
            statement.setString(9, carreraMateriaFK);
            System.out.println(statement.toString());
            statement.execute();
    }
    
    public void updateGrupo(String claveGrupo, String grupo, String campus, String horario, String aula, String capacidad, String maxFaltas, String numControl) throws SQLException {
        PreparedStatement statement = connect.prepareCall("CALL updateGrupo(?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, claveGrupo);
            statement.setString(2, grupo);
            statement.setString(3, campus);
            statement.setString(4, horario);
            statement.setString(5, aula);
            statement.setString(6, capacidad);
            statement.setString(7, maxFaltas);
            statement.setString(8, numControl);
            System.out.println(statement.toString());
            statement.execute();
    }
    
    public void deleteGrupo(String ID) throws SQLException {
           PreparedStatement statement = connect.prepareCall("CALL deleteGrupo(?)");
            statement.setString(1, ID);
            System.out.println(statement);
            statement.execute();   
    }

    
    public void addAlumnoAGrupo(String IDCalificacion, String numControl) throws SQLException{
            PreparedStatement statement = connect.prepareCall("CALL addAlumnoAGrupo(?, ?)");
            statement.setString(1, IDCalificacion);
            statement.setString(2, numControl);
            System.out.println(statement.toString());
            statement.execute();
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
    
    public ArrayList<Alumno> getAlumnosFromGrupos(String grupo){
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        
        try {
            PreparedStatement ps = connect.prepareCall("CALL getAlumnosFromGrupo(?)"); //Para MySql
            ps.setString(1, grupo);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
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
    
    public ArrayList<Materia> getMateriasDeCarrera(String idCarrera){
        ArrayList<Materia> listaMaterias = new ArrayList<>();
        
        try {
            PreparedStatement ps = connect.prepareCall("CALL getMateriasDeCarrera(?)"); //Para MySql
            ps.setString(1, idCarrera);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                listaMaterias.add(new Materia(rs.getString(1), rs.getString(2),  rs.getString(3), rs.getString(4), rs.getString(5), 
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlumnoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMaterias;
    }
    
    
    
    
    public ArrayList<Alumno> filtrarAlumnosGrupo(String numControl, String apellidoPaterno,  String semestre){
        System.out.println("Estás filtsando");
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareStatement("CALL   filtrarAlumnosGrupos(?, ?, ?)"); //Para MySql
            
            ps.setString(1, numControl);
            ps.setString(2, apellidoPaterno);
            ps.setString(3, semestre);
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
    
    public String getIdCarreraMateria(String idCarrera, String idMateria){
        String idCarreraMateria = "";
        PreparedStatement ps;
        try {
            ps = connect.prepareStatement("CALL getIdCarreraMateria(?, ?)");
            ps.setString(1, idCarrera);
            ps.setString(2, idMateria);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                idCarreraMateria = String.valueOf(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GrupoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idCarreraMateria;
    }
    
    public void deleteAlumnosDeGrupo(String idGrupo, String idAlumno) throws SQLException{
         PreparedStatement statement = connect.prepareCall("CALL deleteAlumnoDeGrupo(?, ?)");
            statement.setString(1, idGrupo);
            statement.setString(2, idAlumno);
            System.out.println(statement);
            statement.execute();   
    }
    

}
