package com.example.vettime2.modelos;

import java.io.Serializable;
import java.util.Date;

public class Mascota implements Serializable {

    private int id;
    private String nombre;
    private String apellido;
    private int activo;
    private String fechaNacimiento;
    private float peso;
    private String foto;
    private String datos_varios;
    private String uid;

    public Mascota() {

    }

    public Mascota(int id, String nombre, String apellido, int activo, String fechaNacimiento, float peso, String foto, String datos_varios, String uid) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.activo = activo;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.foto = foto;
        this.datos_varios = datos_varios;
        this.uid = uid;
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

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDatos_varios() {
        return datos_varios;
    }

    public void setDatos_varios(String datos_varios) {
        this.datos_varios = datos_varios;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", activo=" + activo +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", peso=" + peso +
                ", foto='" + foto + '\'' +
                ", datos_varios='" + datos_varios + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
