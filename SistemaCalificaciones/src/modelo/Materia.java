package modelo;

public class Materia {
    private String clave;
    private String nombre;
    private String tipo;
    private String satca;
    private String semestre;
    private String planEstudios;
    private String totalUnidades;
    private String descripcion;
    private String usuarioAlta;
    private String fechaAlta;
    private String usuarioMod;
    private String fechaMod;

    public Materia(String clave, String nombre, String tipo, String satca, String semestre, String planEstudios, String totalUnidades, String descripcion, String usuarioAlta, String fechaAlta, String usuarioMod, String fechaMod) {
        this.clave = clave;
        this.nombre = nombre;
        this.tipo = tipo;
        this.satca = satca;
        this.semestre = semestre;
        this.planEstudios = planEstudios;
        this.totalUnidades = totalUnidades;
        this.descripcion = descripcion;
        this.usuarioAlta = usuarioAlta;
        this.fechaAlta = fechaAlta;
        this.usuarioMod = usuarioMod;
        this.fechaMod = fechaMod;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSatca() {
        return satca;
    }

    public void setSatca(String satca) {
        this.satca = satca;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getPlanEstudios() {
        return planEstudios;
    }

    public void setPlanEstudios(String planEstudios) {
        this.planEstudios = planEstudios;
    }

    public String getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(String totalUnidades) {
        this.totalUnidades = totalUnidades;
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

   
