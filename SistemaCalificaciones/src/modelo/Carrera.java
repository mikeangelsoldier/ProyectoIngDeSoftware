
package modelo;

/**
 *
 * @author alfre
 */
public class Carrera {
    private String idCarrera;
    private String nombreCarrera;
    private String descripcion;
    private String usuarioAlta;
    private String fechaAlta;
    private String usuarioMod;
    private String fechaMod;

    public Carrera(String idCarrera, String nombreCarrera, String descripcion, String usuarioAlta, String fechaAlta, String usuarioMod, String fechaMod) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
        this.descripcion = descripcion;
        this.usuarioAlta = usuarioAlta;
        this.fechaAlta = fechaAlta;
        this.usuarioMod = usuarioMod;
        this.fechaMod = fechaMod;
    }

    public String getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(String idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getNombreCarrera() {
        return nombreCarrera;
    }

    public void setNombreCarrera(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    public String getUsuarioMod() {
        return usuarioMod;
    }

    public void setUsuarioMod(String usuarioMod) {
        this.usuarioMod = usuarioMod;
    }

    public String getFechaMod() {
        return fechaMod;
    }

    public void setFechaMod(String fechaMod) {
        this.fechaMod = fechaMod;
    }

        
}
