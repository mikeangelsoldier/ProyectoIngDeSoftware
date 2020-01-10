
package modelo;


public class Grupo {
    private String ClaveGrupo;
    private String grupo;
    private String campus;
    private String horario;
    private String aula;
    private String capacidad;
    private String maxFaltas;
    private String numControl;
    private String carreraMateriaFK;

    public Grupo(String ClaveGrupo, String grupo, String campus, String horario, String aula, String capacidad, String maxFaltas, String numControl, String carreraMateriaFK) {
        this.ClaveGrupo = ClaveGrupo;
        this.grupo = grupo;
        this.campus = campus;
        this.horario = horario;
        this.aula = aula;
        this.capacidad = capacidad;
        this.maxFaltas = maxFaltas;
        this.numControl = numControl;
        this.carreraMateriaFK = carreraMateriaFK;
    }

    public String getClaveGrupo() {
        return ClaveGrupo;
    }

    public void setClaveGrupo(String ClaveGrupo) {
        this.ClaveGrupo = ClaveGrupo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getMaxFaltas() {
        return maxFaltas;
    }

    public void setMaxFaltas(String maxFaltas) {
        this.maxFaltas = maxFaltas;
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getCarreraMateriaFK() {
        return carreraMateriaFK;
    }

    public void setCarreraMateriaFK(String carreraMateriaFK) {
        this.carreraMateriaFK = carreraMateriaFK;
    }

        
}
