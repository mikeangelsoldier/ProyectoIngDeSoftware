package modelo;



public class TablaGraficasAlumno {
    private String clave;
    private String nombreMateria;
    private String grupo;
    private String f1;
    private String p1;
    private String f2;
    private String p2;
    private String f3;
    private String p3;
    private String tF;
    private String cF;
    private String nombreMaestro;
    
    public TablaGraficasAlumno(String clave, String nombreMateria, String grupo, String f1, String p1, String f2, String p2, String f3, String p3, String tF, String cF, String nombreMaestro) {
        this.clave = clave;
        this.nombreMateria = nombreMateria;
        this.grupo = grupo;
        this.f1 = f1;
        this.p1 = p1;
        this.f2 = f2;
        this.p2 = p2;
        this.f3 = f3;
        this.p3 = p3;
        this.tF = tF;
        this.cF = cF;
        this.nombreMaestro = nombreMaestro;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getP1() {
        return p1;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }

    public String getP2() {
        return p2;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public String getF3() {
        return f3;
    }

    public void setF3(String f3) {
        this.f3 = f3;
    }

    public String getP3() {
        return p3;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public String getTF() {
        return tF;
    }

    public void setTF(String tF) {
        this.tF = tF;
    }

    public String getCF() {
        return cF;
    }

    public void setCF(String cF) {
        this.cF = cF;
    }

    public String getNombreMaestro() {
        return nombreMaestro;
    }

    public void setNombreMaestro(String nombreMaestro) {
        this.nombreMaestro = nombreMaestro;
    }

    
    
}
