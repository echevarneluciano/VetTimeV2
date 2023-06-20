package com.example.vettime2.utils;

import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.TurnosPorTarea;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
        Locale locale = Locale.ENGLISH;
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.getDisplayName(TextStyle.FULL, locale);
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
            if (tiempoInicio != null && tiempoFin != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime inicio = LocalTime.parse(tiempoInicio, formatter);
            LocalTime fin = LocalTime.parse(tiempoFin, formatter);

            LocalTime tiempoActual = inicio;
            while (tiempoActual.isBefore(fin)) {
                intervalos.add(tiempoActual.format(formatter));
                tiempoActual = tiempoActual.plusMinutes(30);
            }
        }
        }
        return intervalos;

    }

    public List<String> getTurnosEntregados(List<Consulta> consultas) {
        List<String> intervalos = new ArrayList<>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        for (Consulta consulta : consultas) {
            String tiempoInicioTexto = consulta.getTiempoInicio();
            String tiempoFinTexto = consulta.getTiempoFin();

            LocalDateTime tiempoInicio = LocalDateTime.parse(tiempoInicioTexto, inputFormatter);
            LocalDateTime tiempoFin = LocalDateTime.parse(tiempoFinTexto, inputFormatter);

            LocalDateTime intervaloActual = tiempoInicio;
            while (intervaloActual.isBefore(tiempoFin)) {
                intervalos.add(intervaloActual.format(outputFormatter));
                intervaloActual = intervaloActual.plusMinutes(30);
            }
        }

        return intervalos;
    }

    public List<String> getTurnosNoEntregados(List<String> disponibles, List<String> entregados) {
        List<String> lista1 = new ArrayList<>(entregados);
        List<String> lista2 = new ArrayList<>(disponibles);
        for (String intervalo : lista1) {
            for (int i = 0; i < lista2.size(); i++) {
                if (intervalo.equals(lista2.get(i))) {
                    lista2.remove(i);
                    i--;
                }
            }
        }
        return lista2;
    }

    public String sumaHoraAFechaFin(String time1, String time2,String date) {

        // Obtener objetos LocalTime de las cadenas de hora
        LocalTime hora1 = LocalTime.parse(time1);
        LocalTime hora2 = LocalTime.parse(time2);

        // Obtener objeto LocalDate de la cadena de fecha
        LocalDate fecha = LocalDate.parse(date);

        // Combina la fecha y la hora utilizando LocalDateTime
        LocalDateTime dateTime1 = LocalDateTime.of(fecha, hora1);
        LocalDateTime dateTime2 = LocalDateTime.of(fecha, hora2);

        // Suma las dos fechas y obtiene el resultado
        LocalDateTime resultado = dateTime1.plusHours(dateTime2.getHour())
                .plusMinutes(dateTime2.getMinute())
                .plusSeconds(dateTime2.getSecond());

        // Formatea el resultado en una cadena con el formato deseado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        return resultado.format(formatter);
    }

    public String sumaHoraAFecha(String hora   , String fecha) {

        LocalTime horaParse = LocalTime.parse(hora);
        LocalDate fechaParse = LocalDate.parse(fecha);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

        LocalDateTime dateTime = LocalDateTime.of(fechaParse, horaParse);

        return dateTime.format(formatter);
    }

    public List<String> lapsoTarea(String tiempoInicio, String tiempoFin) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime inicio = LocalTime.parse(tiempoInicio, formatter);
        LocalTime fin1 = LocalTime.parse(tiempoFin, formatter);
        LocalTime fin = inicio.plusHours(fin1.getHour())
                          .plusMinutes(fin1.getMinute())
                          .plusSeconds(fin1.getSecond())
                          .minusMinutes(30);
        inicio = inicio.plusMinutes(30);
        List<String> intervalos = new ArrayList<>();

        LocalTime tiempoActual = inicio;
        while (tiempoActual.isBefore(fin)) {
            intervalos.add(tiempoActual.format(formatter));
            tiempoActual = tiempoActual.plusMinutes(30);
        }
        return intervalos;
    }

}
