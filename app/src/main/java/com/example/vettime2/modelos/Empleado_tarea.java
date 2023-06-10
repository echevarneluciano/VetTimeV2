package com.example.vettime2.modelos;

import java.io.Serializable;

public class Empleado_tarea implements Serializable {

    private int id;
    private int empleadoId;
    private int tareaId;
    private int activo;
    private Empleado empleado;
    private Tarea tarea;

    public Empleado_tarea() {

    }

    public Empleado_tarea(int id, int empleadoId, int tareaId, int activo, Empleado empleado, Tarea tarea) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.tareaId = tareaId;
        this.activo = activo;
        this.empleado = empleado;
        this.tarea = tarea;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getTareaId() {
        return tareaId;
    }

    public void setTareaId(int tareaId) {
        this.tareaId = tareaId;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        return "Empleado_tarea{" +
                "id=" + id +
                ", empleadoId=" + empleadoId +
                ", tareaId=" + tareaId +
                ", activo=" + activo +
                ", empleado=" + empleado +
                ", tarea=" + tarea +
                '}';
    }
}
