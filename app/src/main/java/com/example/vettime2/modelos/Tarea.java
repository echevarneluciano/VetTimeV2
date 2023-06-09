package com.example.vettime2.modelos;

import java.io.Serializable;

public class Tarea implements Serializable {

    private int id;
    private String tarea;
    private int activo;
    private String tiempo;
    private float precio;

    public Tarea() {

    }

    public Tarea(int id, String tarea, int activo, String tiempo, float precio) {
        this.id = id;
        this.tarea = tarea;
        this.activo = activo;
        this.tiempo = tiempo;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", tarea='" + tarea + '\'' +
                ", activo=" + activo +
                ", tiempo=" + tiempo +
                ", precio=" + precio +
                '}';
    }

}
