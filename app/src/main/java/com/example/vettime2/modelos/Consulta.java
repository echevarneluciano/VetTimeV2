package com.example.vettime2.modelos;

import java.io.Serializable;

public class Consulta implements Serializable {

    private int id;
    private int estado;
    private String tiempoInicio;
    private String tiempoFin;
    private int cliente_mascotaId;
    private int empleadoId;
    private int activo;
    private String detalle;
    private Empleado empleado;
    private Cliente cliente;

    public Consulta() {

    }

    public Consulta(int id, int estado, String tiempoInicio, String tiempoFin, int cliente_mascotaId, int empleadoId, int activo, String detalle, Empleado empleado, Cliente cliente) {
        this.id = id;
        this.estado = estado;
        this.tiempoInicio = tiempoInicio;
        this.tiempoFin = tiempoFin;
        this.cliente_mascotaId = cliente_mascotaId;
        this.empleadoId = empleadoId;
        this.activo = activo;
        this.detalle = detalle;
        this.empleado = empleado;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getTiempoInicio() {
        return tiempoInicio;
    }

    public void setTiempoInicio(String tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    public String getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(String tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public int getCliente_mascotaId() {
        return cliente_mascotaId;
    }

    public void setCliente_mascotaId(int cliente_mascotaId) {
        this.cliente_mascotaId = cliente_mascotaId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", estado=" + estado +
                ", tiempoInicio=" + tiempoInicio +
                ", tiempoFin=" + tiempoFin +
                ", cliente_mascotaId=" + cliente_mascotaId +
                ", empleadoId=" + empleadoId +
                ", activo=" + activo +
                ", detalle='" + detalle + '\'' +
                ", empleado=" + empleado +
                ", cliente=" + cliente +
                '}';
    }

}
