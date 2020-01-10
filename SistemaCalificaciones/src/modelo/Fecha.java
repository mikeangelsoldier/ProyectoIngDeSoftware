
package modelo;


public class Fecha {
    private String idRegistro; 
    private String fecha1; 
    private String fecha2; 
    private String fecha3; 
    private String fechaFinal;
    private String periodo;

    public Fecha(String idRegistro, String fecha1, String fecha2, String fecha3, String fechaFinal, String periodo) {
        this.idRegistro = idRegistro;
        this.fecha1 = fecha1;
        this.fecha2 = fecha2;
        this.fecha3 = fecha3;
        this.fechaFinal = fechaFinal;
        this.periodo = periodo;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(String idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getFecha1() {
        return fecha1;
    }

    public void setFecha1(String fecha1) {
        this.fecha1 = fecha1;
    }

    public String getFecha2() {
        return fecha2;
    }

    public void setFecha2(String fecha2) {
        this.fecha2 = fecha2;
    }

    public String getFecha3() {
        return fecha3;
    }

    public void setFecha3(String fecha3) {
        this.fecha3 = fecha3;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    
}
