
package modelo;


public class Calificaciones {
    private String num;
    private String numControl;
    private String nombreAlumno;
    private String curso;
    private String semestre;
    private String cp1;
    private String faltas1;
    private String estado1;
    private String cp2;
    private String faltas2;
    private String estado2;
    private String cp3;
    private String faltas3;
    private String estado3;
    private String cpFinal;
    private String faltasFinal;
    private String estadoFinal;

    public Calificaciones(String num, String numControl, String nombreAlumno, String curso, String semestre, String cp1, String faltas1, String estado1, String cp2, String faltas2, String estado2, String cp3, String faltas3, String estado3, String cpFinal, String faltasFinal, String estadoFinal) {
        this.num = num;
        this.numControl = numControl;
        this.nombreAlumno = nombreAlumno;
        this.curso = curso;
        this.semestre = semestre;
        this.cp1 = cp1;
        this.faltas1 = faltas1;
        this.estado1 = estado1;
        this.cp2 = cp2;
        this.faltas2 = faltas2;
        this.estado2 = estado2;
        this.cp3 = cp3;
        this.faltas3 = faltas3;
        this.estado3 = estado3;
        this.cpFinal = cpFinal;
        this.faltasFinal = faltasFinal;
        this.estadoFinal = estadoFinal;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public String getCp1() {
        return cp1;
    }

    public void setCp1(String cp1) {
        this.cp1 = cp1;
    }

    public String getFaltas1() {
        return faltas1;
    }

    public void setFaltas1(String faltas1) {
        this.faltas1 = faltas1;
    }

    public String getEstado1() {
        return estado1;
    }

    public void setEstado1(String estado1) {
        this.estado1 = estado1;
    }

    public String getCp2() {
        return cp2;
    }

    public void setCp2(String cp2) {
        this.cp2 = cp2;
    }

    public String getFaltas2() {
        return faltas2;
    }

    public void setFaltas2(String faltas2) {
        this.faltas2 = faltas2;
    }

    public String getEstado2() {
        return estado2;
    }

    public void setEstado2(String estado2) {
        this.estado2 = estado2;
    }

    public String getCp3() {
        return cp3;
    }

    public void setCp3(String cp3) {
        this.cp3 = cp3;
    }

    public String getFaltas3() {
        return faltas3;
    }

    public void setFaltas3(String faltas3) {
        this.faltas3 = faltas3;
    }

    public String getEstado3() {
        return estado3;
    }

    public void setEstado3(String estado3) {
        this.estado3 = estado3;
    }

    public String getCpFinal() {
        return cpFinal;
    }

    public void setCpFinal(String cpFinal) {
        this.cpFinal = cpFinal;
    }

    public String getFaltasFinal() {
        return faltasFinal;
    }

    public void setFaltasFinal(String faltasFinal) {
        this.faltasFinal = faltasFinal;
    }

    public String getEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(String estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

         
}
