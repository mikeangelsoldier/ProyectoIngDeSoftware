package modelo;



public class Administrador {
    private String numControl;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String sexo;
    private String cargo;
    private String telefono;
    private String correo;
    private String user;
    private String passw;
    private String usuarioAlta;
    private String fechaAlta;
    private String usuarioMod;
    private String fechaMod;

    public Administrador(String numControl, String nombre, String primerApellido, String segundoApellido, String sexo, String cargo, String telefono, String correo, String login, String passw, String usuarioAlta, String fechaAlta, String usuarioMod, String fechaMod) {
        this.numControl = numControl;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.sexo = sexo;
        this.cargo = cargo;
        this.telefono = telefono;
        this.correo = correo;
        this.user = login;
        this.passw = passw;
        this.usuarioAlta = usuarioAlta;
        this.fechaAlta = fechaAlta;
        this.usuarioMod = usuarioMod;
        this.fechaMod = fechaMod;
    }

    public String getNumControl() {
        return numControl;
    }

    public void setNumControl(String numControl) {
        this.numControl = numControl;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
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

    public String getLogin() {
        return user;
    }

    public void setLogin(String login) {
        this.user = login;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
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
