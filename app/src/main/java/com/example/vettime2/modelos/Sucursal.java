package com.example.vettime2.modelos;

import java.io.Serializable;

public class Sucursal implements Serializable {

    private int id;
    private String nombre;
    private String direccion;
    private String redSocial;
    private String horario;
    private String telefono;

    public Sucursal(){}
    public Sucursal(int id, String nombre, String direccion, String redSocial, String horario, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.redSocial = redSocial;
        this.horario = horario;
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRedSocial() {
        return redSocial;
    }

    public void setRedSocial(String redSocial) {
        this.redSocial = redSocial;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
