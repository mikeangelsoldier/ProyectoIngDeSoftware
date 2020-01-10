package modelo;



public class Semestre {
    private String IDSemestre;
    private String semestre;
    private String periodo;
    private String año;
    private String usuarioAlta;
    private String fechaAlta;
    private String usuarioMod;
    private String fechaMod;


    public Semestre() {}

    public Semestre(String IDSemestre, String semestre, String periodo, String año, String usuarioAlta, String fechaAlta, String usuarioMod, String fechaMod) {
        this.IDSemestre = IDSemestre;
        this.semestre = semestre;
        this.periodo = periodo;
        this.año = año;
        this.usuarioAlta = usuarioAlta;
        this.fechaAlta = fechaAlta;
        this.usuarioMod = usuarioMod;
        this.fechaMod = fechaMod;
    }

    public String getIDSemestre() {
        return IDSemestre;
    }

    public void setIDSemestre(String IDSemestre) {
        this.IDSemestre = IDSemestre;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
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

    public void setFechaAla(String fechaAla) {
        this.fechaAlta = fechaAla;
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
