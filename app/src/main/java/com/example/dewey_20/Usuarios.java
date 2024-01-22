package com.example.dewey_20;

public class Usuarios {
    int id;
    String Nombre, Apellido, Usuario, Password, Puesto="Jefe";

    //contructor vacio
    public Usuarios() {

    }

    public Usuarios(String nombre, String apellido, String usuario, String password, String puesto) {
        Nombre = nombre;
        Apellido = apellido;
        Usuario = usuario;
        Password = password;
        Puesto = puesto;
    }

    public boolean isNull(){
        if(Nombre.equals("")&&Apellido.equals("")&&Usuario.equals("")&&Password.equals("")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return "Usuarios{" +
                "id=" + id +
                ", Nombre='" + Nombre + '\'' +
                ", Apellido='" + Apellido + '\'' +
                ", Usuario='" + Usuario + '\'' +
                ", Password='" + Password + '\'' +
                ", Puesto='" + Puesto + '\'' +
                '}';
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
