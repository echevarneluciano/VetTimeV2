package com.example.vettime2.modelos;

import java.io.Serializable;

public class TurnosPorTarea implements Serializable {

    private String monday_ini;
    private String monday_fin;
    private String tuesday_ini;
    private String tuesday_fin;
    private String wednesday_ini;
    private String wednesday_fin;
    private String thursday_ini;
    private String thursday_fin;
    private String friday_ini;
    private String friday_fin;
    private String saturday_ini;
    private String saturday_fin;
    private String sunday_ini;
    private String sunday_fin;
    private Integer empleadoId;
    private String tiempoTarea;

    public TurnosPorTarea() {
    }

    public TurnosPorTarea(String monday_ini, String monday_fin, String tuesday_ini, String tuesday_fin,
                          String wednesday_ini, String wednesday_fin, String thursday_ini, String thursday_fin,
                          String friday_ini, String friday_fin, String saturday_ini, String saturday_fin,
                          String sunday_ini, String sunday_fin, Integer empleadoId, String tiempoTarea) {
        this.monday_ini = monday_ini;
        this.monday_fin = monday_fin;
        this.tuesday_ini = tuesday_ini;
        this.tuesday_fin = tuesday_fin;
        this.wednesday_ini = wednesday_ini;
        this.wednesday_fin = wednesday_fin;
        this.thursday_ini = thursday_ini;
        this.thursday_fin = thursday_fin;
        this.friday_ini = friday_ini;
        this.friday_fin = friday_fin;
        this.saturday_ini = saturday_ini;
        this.saturday_fin = saturday_fin;
        this.sunday_ini = sunday_ini;
        this.sunday_fin = sunday_fin;
        this.empleadoId = empleadoId;
        this.tiempoTarea = tiempoTarea;
    }

    public String getTiempoTarea() {
        return tiempoTarea;
    }

    public void setTiempoTarea(String tiempoTarea) {
        this.tiempoTarea = tiempoTarea;
    }

    public String getMonday_ini() {
        return monday_ini;
    }

    public void setMonday_ini(String monday_ini) {
        this.monday_ini = monday_ini;
    }

    public String getMonday_fin() {
        return monday_fin;
    }

    public void setMonday_fin(String monday_fin) {
        this.monday_fin = monday_fin;
    }

    public String getTuesday_ini() {
        return tuesday_ini;
    }

    public void setTuesday_ini(String tuesday_ini) {
        this.tuesday_ini = tuesday_ini;
    }

    public String getTuesday_fin() {
        return tuesday_fin;
    }

    public void setTuesday_fin(String tuesday_fin) {
        this.tuesday_fin = tuesday_fin;
    }

    public String getWednesday_ini() {
        return wednesday_ini;
    }

    public void setWednesday_ini(String wednesday_ini) {
        this.wednesday_ini = wednesday_ini;
    }

    public String getWednesday_fin() {
        return wednesday_fin;
    }

    public void setWednesday_fin(String wednesday_fin) {
        this.wednesday_fin = wednesday_fin;
    }

    public String getThursday_ini() {
        return thursday_ini;
    }

    public void setThursday_ini(String thursday_ini) {
        this.thursday_ini = thursday_ini;
    }

    public String getThursday_fin() {
        return thursday_fin;
    }

    public void setThursday_fin(String thursday_fin) {
        this.thursday_fin = thursday_fin;
    }

    public String getFriday_ini() {
        return friday_ini;
    }

    public void setFriday_ini(String friday_ini) {
        this.friday_ini = friday_ini;
    }

    public String getFriday_fin() {
        return friday_fin;
    }

    public void setFriday_fin(String friday_fin) {
        this.friday_fin = friday_fin;
    }

    public String getSaturday_ini() {
        return saturday_ini;
    }

    public void setSaturday_ini(String saturday_ini) {
        this.saturday_ini = saturday_ini;
    }

    public String getSaturday_fin() {
        return saturday_fin;
    }

    public void setSaturday_fin(String saturday_fin) {
        this.saturday_fin = saturday_fin;
    }

    public String getSunday_ini() {
        return sunday_ini;
    }

    public void setSunday_ini(String sunday_ini) {
        this.sunday_ini = sunday_ini;
    }

    public String getSunday_fin() {
        return sunday_fin;
    }

    public void setSunday_fin(String sunday_fin) {
        this.sunday_fin = sunday_fin;
    }

    public Integer getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(Integer empleadoId) {
        this.empleadoId = empleadoId;
    }

    @Override
    public String toString() {
        return "TurnosPorTarea{" +
                "monday_ini='" + monday_ini + '\'' +
                ", monday_fin='" + monday_fin + '\'' +
                ", tuesday_ini='" + tuesday_ini + '\'' +
                ", tuesday_fin='" + tuesday_fin + '\'' +
                ", wednesday_ini='" + wednesday_ini + '\'' +
                ", wednesday_fin='" + wednesday_fin + '\'' +
                ", thursday_ini='" + thursday_ini + '\'' +
                ", thursday_fin='" + thursday_fin + '\'' +
                ", friday_ini='" + friday_ini + '\'' +
                ", friday_fin='" + friday_fin + '\'' +
                ", saturday_ini='" + saturday_ini + '\'' +
                ", saturday_fin='" + saturday_fin + '\'' +
                ", sunday_ini='" + sunday_ini + '\'' +
                ", sunday_fin='" + sunday_fin + '\'' +
                ", empleadoId=" + empleadoId +
                ", tiempoTarea='" + tiempoTarea +
                '}';
    }

}
