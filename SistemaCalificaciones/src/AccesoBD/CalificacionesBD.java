
package AccesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Calificaciones;
import modelo.Carrera;
import modelo.Grupo;
import modelo.Materia;


public class CalificacionesBD {
    Connection connect;
    public CalificacionesBD(Connection connect) {
        this.connect = connect;
    }
    
    public ArrayList<Materia> getMateriaDeProfesor(String idProfesor){
        ArrayList<Materia> listaMaterias = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareStatement("CALL getMateriasDeProfesor(?)");
            ps.setString(1, idProfesor);
            ResultSet rs = ps.executeQuery(); //Para MySql
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
    
    public void updateCalificacionesDeUnGrupo(String idGrupo, String idAlumno, String Cp1, String Faltas1, String Estado1, String 
            Cp2, String Faltas2, String Estado2, String Cp3, String Faltas3, String Estado3, String CpFinal, String FaltasFinal, String EstadoFinal) throws SQLException{
        
        PreparedStatement statement = connect.prepareCall("CALL updateCalificacionesDeUnGrupo(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        statement.setString(1, idGrupo);
        statement.setString(2, idAlumno);
        statement.setFloat(3, Float.parseFloat(Cp1));
        statement.setInt(4, Integer.parseInt(Faltas1));
        statement.setString(5, Estado1);
        statement.setFloat(6, Float.parseFloat(Cp2));
        statement.setInt(7, Integer.parseInt(Faltas2));
        statement.setString(8, Estado2);
        statement.setFloat(9, Float.parseFloat(Cp3));
        statement.setInt(10, Integer.parseInt(Faltas3));
        statement.setString(11, Estado3);
        statement.setFloat(12, Float.parseFloat(CpFinal));
        statement.setInt(13, Integer.parseInt(FaltasFinal));
        statement.setString(14, EstadoFinal);
        System.out.println(statement);
        statement.execute();
    }
    
    public ArrayList<Grupo> getGruposDeMateriaDeProfesor(String idProfesor, String idMateria){
        ArrayList<Grupo> listaGrupos = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareStatement("CALL getGruposDeMateriaDeProfesor(?, ?)");
            ps.setString(1, idProfesor);
            ps.setString(2, idMateria);
            ResultSet rs = ps.executeQuery(); //Para MySql
            while (rs.next()){
                listaGrupos.add(new Grupo(rs.getString(1), rs.getString(2), 
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7), rs.getString(8), rs.getString(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGrupos;
    }
    
    public ArrayList<Calificaciones> getAlumnosYCalificacionesDeUnGrupo(String idGrupo){
        int numAlumno=1;
        ArrayList<Calificaciones> listaCalificaciones = new ArrayList<>();
        
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareStatement("CALL getAlumnosYCalificacionDeUnGrupo(?)");
            ps.setString(1, idGrupo);
            ResultSet rs = ps.executeQuery(); //Para MySql
            while (rs.next()){
                listaCalificaciones.add(new Calificaciones(String.valueOf(numAlumno), rs.getString(1), rs.getString(2), "CO", 
                            rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), 
                            rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), 
                            rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15)));
                numAlumno++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MateriaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaCalificaciones;
    }
    
    public ArrayList<String> getDatosEncabezado(String idGrupo){
        ArrayList<String> listaDatos = new ArrayList<>();
        try {
            //ResultSet rs = connect.prepareCall("EXEC getAlumnos").executeQuery(); //Para SQL Server
            PreparedStatement ps = connect.prepareStatement("CALL getEncabezadoGestionParcial(?)");
            ps.setString(1, idGrupo);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery(); //Para MySql
            rs.next();
            for (int i = 1; i <= 8; i++) {
                listaDatos.add(rs.getString(i));
            }
            System.out.println(listaDatos.get(0));
        } catch (SQLException ex) {
            Logger.getLogger(MateriaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaDatos;
    }
    
}

