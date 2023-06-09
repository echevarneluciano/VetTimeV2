package com.example.vettime2.utils;

import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.TurnosPorTarea;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Utils {

    public String getDate(int anio, int mes, int dia) {
        LocalDate date = LocalDate.of(anio, mes, dia);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault());
    }

    public String convertirFechaMysql(int anio, int mes, int dia) {
        LocalDate date = LocalDate.of(anio, mes, dia);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String mysqlFormat = date.format(formatter);
        return mysqlFormat;
    }


    public List<String> getTurnoTarea(List<TurnosPorTarea> tareas, String fecha) {

        List<String> intervalos = new ArrayList<>();

        for (TurnosPorTarea tarea : tareas) {
            String tiempoInicio = "";
            String tiempoFin = "";

            switch (fecha) {
                case "Monday":
                    tiempoInicio = tarea.getMonday_ini();
                    tiempoFin = tarea.getMonday_fin();
                    break;
                case "Tuesday":
                    tiempoInicio = tarea.getTuesday_ini();
                    tiempoFin = tarea.getTuesday_fin();
                    break;
                case "Wednesday":
                    tiempoInicio = tarea.getWednesday_ini();
                    tiempoFin = tarea.getWednesday_fin();
                    break;
                case "Thursday":
                    tiempoInicio = tarea.getThursday_ini();
                    tiempoFin = tarea.getThursday_fin();
                    break;
                case "Friday":
                    tiempoInicio = tarea.getFriday_ini();
                    tiempoFin = tarea.getFriday_fin();
                    break;
                case "Saturday":
                    tiempoInicio = tarea.getSaturday_ini();
                    tiempoFin = tarea.getSaturday_fin();
                    break;
                case "Sunday":
                    tiempoInicio = tarea.getSunday_ini();
                    tiempoFin = tarea.getSunday_fin();
                    break;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime inicio = LocalTime.parse(tiempoInicio, formatter);
            LocalTime fin = LocalTime.parse(tiempoFin, formatter);

            LocalTime tiempoActual = inicio;
            while (tiempoActual.isBefore(fin)) {
                intervalos.add(tiempoActual.format(formatter));
                tiempoActual = tiempoActual.plusMinutes(30);
            }
        }

        return intervalos;

    }

    public List<String> getTurnosEntregados(List<Consulta> consultas) {
        return null;
    }

    public List<String> getTurnosNoEntregados(List<String> disponibles, List<String> entregados) {

        return null;
    }

}
