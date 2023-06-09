package com.example.vettime2.modelos;

import java.io.Serializable;
import java.sql.Time;

public class Turno implements Serializable {

    private int id;
    private Time lunes_ini;
    private Time lunes_fin;
    private Time martes_ini;
    private Time martes_fin;
    private Time miercoles_ini;
    private Time miercoles_fin;
    private Time jueves_ini;
    private Time jueves_fin;
    private Time viernes_ini;
    private Time viernes_fin;
    private Time sabado_ini;
    private Time sabado_fin;
    private Time domingo_ini;
    private Time domingo_fin;
    private int empleadoId;
    private Empleado empleado;
    private int activo;

    public Turno() {

    }

    public Turno(int id, Time lunes_ini, Time lunes_fin, Time martes_ini, Time martes_fin, Time miercoles_ini, Time miercoles_fin, Time jueves_ini, Time jueves_fin, Time viernes_ini, Time viernes_fin, Time sabado_ini, Time sabado_fin, Time domingo_ini, Time domingo_fin, int empleadoId, Empleado empleado, int activo) {
        this.id = id;
        this.lunes_ini = lunes_ini;
        this.lunes_fin = lunes_fin;
        this.martes_ini = martes_ini;
        this.martes_fin = martes_fin;
        this.miercoles_ini = miercoles_ini;
        this.miercoles_fin = miercoles_fin;
        this.jueves_ini = jueves_ini;
        this.jueves_fin = jueves_fin;
        this.viernes_ini = viernes_ini;
        this.viernes_fin = viernes_fin;
        this.sabado_ini = sabado_ini;
        this.sabado_fin = sabado_fin;
        this.domingo_ini = domingo_ini;
        this.domingo_fin = domingo_fin;
        this.empleadoId = empleadoId;
        this.empleado = empleado;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getLunes_ini() {
        return lunes_ini;
    }

    public void setLunes_ini(Time lunes_ini) {
        this.lunes_ini = lunes_ini;
    }

    public Time getLunes_fin() {
        return lunes_fin;
    }

    public void setLunes_fin(Time lunes_fin) {
        this.lunes_fin = lunes_fin;
    }

    public Time getMartes_ini() {
        return martes_ini;
    }

    public void setMartes_ini(Time martes_ini) {
        this.martes_ini = martes_ini;
    }

    public Time getMartes_fin() {
        return martes_fin;
    }

    public void setMartes_fin(Time martes_fin) {
        this.martes_fin = martes_fin;
    }

    public Time getMiercoles_ini() {
        return miercoles_ini;
    }

    public void setMiercoles_ini(Time miercoles_ini) {
        this.miercoles_ini = miercoles_ini;
    }

    public Time getMiercoles_fin() {
        return miercoles_fin;
    }

    public void setMiercoles_fin(Time miercoles_fin) {
        this.miercoles_fin = miercoles_fin;
    }

    public Time getJueves_ini() {
        return jueves_ini;
    }

    public void setJueves_ini(Time jueves_ini) {
        this.jueves_ini = jueves_ini;
    }

    public Time getJueves_fin() {
        return jueves_fin;
    }

    public void setJueves_fin(Time jueves_fin) {
        this.jueves_fin = jueves_fin;
    }

    public Time getViernes_ini() {
        return viernes_ini;
    }

    public void setViernes_ini(Time viernes_ini) {
        this.viernes_ini = viernes_ini;
    }

    public Time getViernes_fin() {
        return viernes_fin;
    }

    public void setViernes_fin(Time viernes_fin) {
        this.viernes_fin = viernes_fin;
    }

    public Time getSabado_ini() {
        return sabado_ini;
    }

    public void setSabado_ini(Time sabado_ini) {
        this.sabado_ini = sabado_ini;
    }

    public Time getSabado_fin() {
        return sabado_fin;
    }

    public void setSabado_fin(Time sabado_fin) {
        this.sabado_fin = sabado_fin;
    }

    public Time getDomingo_ini() {
        return domingo_ini;
    }

    public void setDomingo_ini(Time domingo_ini) {
        this.domingo_ini = domingo_ini;
    }

    public Time getDomingo_fin() {
        return domingo_fin;
    }

    public void setDomingo_fin(Time domingo_fin) {
        this.domingo_fin = domingo_fin;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

}
