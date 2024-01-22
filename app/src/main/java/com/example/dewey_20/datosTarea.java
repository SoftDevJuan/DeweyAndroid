package com.example.dewey_20;

public class datosTarea {
    private String nombre,descripcion,status;
    private int id, idEmpleado;

    public datosTarea(){

    }
    public datosTarea(Integer id, String nombre, String descripcion, String status, int idEmpleado){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.status = status;
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
}//class