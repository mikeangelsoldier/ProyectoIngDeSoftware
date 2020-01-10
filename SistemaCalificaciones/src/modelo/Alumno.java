package modelo;



public class Alumno {
    private String ID;
    private String curp;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String sexo;
    private String fechaNac;
    private String domicilio;
    private String telefono;
    private String correo;
    private String semestreAlumno;
    private String user;
    private String password;
    private String usuarioAlta;
    private String fechaAlta;
    private String usuarioMod;
    private String fechaMod;
    //Adicinal
    public Alumno(String ID, String curp, String nombre, String apellidoP, String apellidoM, String sexo, String fechaNac, String domicilio, String telefono, String correo, String semestreAlumno, String user, String password, String usuarioAlta, String fechaAlta, String usuarioMod, String fechaMod) {
        this.ID = ID;
        this.curp = curp;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.sexo = sexo;
        this.fechaNac = fechaNac;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.correo = correo;
        this.semestreAlumno = semestreAlumno;
        this.user = user;
        this.password = password;
        this.usuarioAlta = usuarioAlta;
        this.fechaAlta = fechaAlta;
        this.usuarioMod = usuarioMod;
        this.fechaMod = fechaMod;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getApellidoM() {
        return apellidoM;
    }

    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getSemestreAlumno() {
        return semestreAlumno;
    }

    public void setSemestreAlumno(String semestreAlumno) {
        this.semestreAlumno = semestreAlumno;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
