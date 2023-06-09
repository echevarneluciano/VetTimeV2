package com.example.vettime2.modelos;

import java.io.Serializable;

public class Empleado implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private String mail;
    private String pass;
    private int rolId;
    private int activo;
    private String telefono;
    private int sucursalId;

    public Empleado() {

    }

    public Empleado(int id, String nombre, String apellido, String mail, String pass, int rolId, int activo, String telefono, int sucursalId) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.pass = pass;
        this.rolId = rolId;
        this.activo = activo;
        this.telefono = telefono;
        this.sucursalId = sucursalId;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", mail='" + mail + '\'' +
                ", pass='" + pass + '\'' +
                ", rolId=" + rolId +
                ", activo=" + activo +
                ", telefono='" + telefono + '\'' +
                ", sucursalId=" + sucursalId +
                '}';
    }

}
