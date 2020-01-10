package Beans;

import AccesoBD.AlumnoBD;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import AccesoBD.ConexionJavaSQLServer;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Profesor {

    private String numControlProfesor, curpProfesor,
            nombreProfesor, primerApellidoProfesor,
            segundoApellidoProfesor,
            sexoProfesor, telefonoProfesor,
            correoProfesor, fechaNacimientoProfesor, usuarioAlta, fechaAlta,
            usuarioMofidicacion, fechaModificacion, usuarioProfesor, contraseñaProfesor;

    private String sql;

    public Profesor() {
    }

    public Profesor(String numControlProfesor, String curpProfesor,
            String nombreProfesor, String primerApellidoProfesor,
            String segundoApellidoProfesor, String sexoProfesor,
            String telefonoProfesor, String correoProfesor,
            String fechaNacimientoProfesor, String usuarioProfesor,
            String contraseñaProfesor, String usuarioAlta, String fechaAlta,
            String usuarioMofidicacion, String fechaModificacion) {

        this.numControlProfesor = numControlProfesor;
        this.curpProfesor = curpProfesor;
        this.nombreProfesor = nombreProfesor;
        this.primerApellidoProfesor = primerApellidoProfesor;
        this.segundoApellidoProfesor = segundoApellidoProfesor;
        this.sexoProfesor = sexoProfesor;
        this.telefonoProfesor = telefonoProfesor;
        this.correoProfesor = correoProfesor;
        this.fechaNacimientoProfesor = fechaNacimientoProfesor;
        this.usuarioProfesor = usuarioProfesor;
        this.contraseñaProfesor = contraseñaProfesor;
        this.usuarioAlta = usuarioAlta;
        this.fechaAlta = fechaAlta;
        this.usuarioMofidicacion = usuarioMofidicacion;
        this.fechaModificacion = fechaModificacion;
    }

    public boolean insertarProfesor() {
        ConexionJavaSQLServer.conectarBD();
        try {
            PreparedStatement ps = ConexionJavaSQLServer.connect.prepareCall("CALL addProfesor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?)");

            ps.setString(1, numControlProfesor);
            ps.setString(2, curpProfesor);
            ps.setString(3, nombreProfesor);
            ps.setString(4, primerApellidoProfesor);
            ps.setString(5, segundoApellidoProfesor);
            ps.setString(6, sexoProfesor);
            ps.setString(7, telefonoProfesor);
            ps.setString(8, correoProfesor);
            ps.setString(9, fechaNacimientoProfesor);
            ps.setString(10, usuarioProfesor);
            ps.setString(11, contraseñaProfesor);
            ps.setString(12, usuarioMofidicacion);

            ps.executeUpdate();
            ps.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al dar de alta el Profesor");
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarProfesor() {
        ConexionJavaSQLServer.conectarBD();
        try {
            PreparedStatement ps = AccesoBD.ConexionJavaSQLServer.connect.prepareCall("CALL updateProfesor(?,?,?,?,?,?,?,?,?,?,?,?)");

            ps.setString(1, numControlProfesor);
            ps.setString(2, curpProfesor);
            ps.setString(3, nombreProfesor);
            ps.setString(4, primerApellidoProfesor);
            ps.setString(5, segundoApellidoProfesor);
            ps.setString(6, sexoProfesor);
            ps.setString(7, telefonoProfesor);
            ps.setString(8, correoProfesor);
            ps.setString(9, fechaNacimientoProfesor);
            ps.setString(10, usuarioProfesor);
            ps.setString(11, contraseñaProfesor);
            ps.setString(12, usuarioMofidicacion);

            ps.executeUpdate();
            ps.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error al modificar Profesor");
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarProfesor() {

        ConexionJavaSQLServer.conectarBD();
        try {
            PreparedStatement ps = AccesoBD.ConexionJavaSQLServer.connect.prepareCall("CALL deleteProfesor(?)");
            ps.setString(1, numControlProfesor);
            ps.executeUpdate();
            ps.close();

            return true;
        } catch (Exception e) {
            System.out.println("Error al eliminar Profesor");
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet obtenerProfesor() {/*Estructura como una matriz donde se guardan los datos */
        ResultSet resultado = null;
        ConexionJavaSQLServer.conectarBD();
        try {
            resultado = ConexionJavaSQLServer.connect.prepareCall("CALL getProfesor").executeQuery(); //Para MySql
        } catch (SQLException ex) {
            System.out.println("entro al catch");
        }
        return resultado;
    }

    public static ResultSet filtrarProfesor(String id, String curp, String nombre, String apellidoP,
            String apellidoM, String sexo, String telefono, String correo) {
        
        ResultSet resultado=null;
        System.out.println("entro a filtrarProfesor");
        ConexionJavaSQLServer.conectarBD();
        try {
           PreparedStatement ps = ConexionJavaSQLServer.connect.prepareStatement("CALL getBusquedaProfesor (?, ?, ?, ?, ?, ?, ?, ?)"); //Para MySql
            System.out.println("si paso el preparedStatement");
            ps.setString(1, id);
            ps.setString(2, curp);
            ps.setString(3, nombre);
            ps.setString(4, apellidoP);
            ps.setString(5, apellidoM);
            ps.setString(6, sexo);
            ps.setString(7, telefono);
            ps.setString(8, correo);

            
            resultado = ps.executeQuery();
            if(ps==null){
             System.out.println("se devolvio nulo en ps de  filtrarProfesor()");   
            }else{
                System.out.println("se devolvio no nulo en ps de  filtrarProfesor()"); 
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return resultado;
    }

    /*Getters y Setters*/
    public String getNumControlProfesor() {
        return numControlProfesor;
    }

    public void setNumControlProfesor(String numControlProfesor) {
        this.numControlProfesor = numControlProfesor;
    }

    public String getCurpProfesor() {
        return curpProfesor;
    }

    public void setCurpProfesor(String curpProfesor) {
        this.curpProfesor = curpProfesor;
    }

    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getPrimerApellidoProfesor() {
        return primerApellidoProfesor;
    }

    public void setPrimerApellidoProfesor(String primerApellidoProfesor) {
        this.primerApellidoProfesor = primerApellidoProfesor;
    }

    public String getSegundoApellidoProfesor() {
        return segundoApellidoProfesor;
    }

    public void setSegundoApellidoProfesor(String segundoApellidoProfesor) {
        this.segundoApellidoProfesor = segundoApellidoProfesor;
    }

    public String getSexoProfesor() {
        return sexoProfesor;
    }

    public void setSexoProfesor(String sexoProfesor) {
        this.sexoProfesor = sexoProfesor;
    }

    public String getTelefonoProfesor() {
        return telefonoProfesor;
    }

    public void setTelefonoProfesor(String telefonoProfesor) {
        this.telefonoProfesor = telefonoProfesor;
    }

    public String getCorreoProfesor() {
        return correoProfesor;
    }

    public void setCorreoProfesor(String correoProfesor) {
        this.correoProfesor = correoProfesor;
    }

    public String getFechaNacimientoProfesor() {
        return fechaNacimientoProfesor;
    }

    public void setFechaNacimientoProfesor(String fechaNacimientoProfesor) {
        this.fechaNacimientoProfesor = fechaNacimientoProfesor;
    }

    public String getUsuarioAlta() {
        return usuarioAlta;
    }

    public void setUsuarioAlta(String usuarioAlta) {
        this.usuarioAlta = usuarioAlta;
    }

    public String getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(String fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getUsuarioMofidicacion() {
        return usuarioMofidicacion;
    }

    public void setUsuarioMofidicacion(String usuarioMofidicacion) {
        this.usuarioMofidicacion = usuarioMofidicacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getUsuarioProfesor() {
        return usuarioProfesor;
    }

    public void setUsuarioProfesor(String usuarioProfesor) {
        this.usuarioProfesor = usuarioProfesor;
    }

    public String getContraseñaProfesor() {
        return contraseñaProfesor;
    }

    public void setContraseñaProfesor(String contraseñaProfesor) {
        this.contraseñaProfesor = contraseñaProfesor;
    }

}
