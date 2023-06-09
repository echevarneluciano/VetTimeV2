package com.example.vettime2.ui.nuevoTurno;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettime2.modelos.Cliente_mascota;
import com.example.vettime2.modelos.Tarea;
import com.example.vettime2.modelos.TurnosPorTarea;
import com.example.vettime2.request.ApiClient;
import com.example.vettime2.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoTurnoViewModel extends AndroidViewModel {

    private  MutableLiveData<List<String>> mTareas;
    private MutableLiveData<List<String>> mMascotas;
    private MutableLiveData<List<String>> mHorarios;
    private MutableLiveData<Boolean> mReset;
    private List<Tarea> tareasDisponibles;
    private String fecha;
    private Context context;
    private String tiempoTarea="00:30:00";
    private List<Cliente_mascota> clientes_mascotas;
    private ArrayList<String> tareas = new ArrayList<>();
    private ArrayList<String> mascotas = new ArrayList<>();
    private ApiClient.EndPointVetTime end;
    private Utils utils;



    public NuevoTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        utils = new Utils();
        end = ApiClient.getEndpointVetTime();
        tareas.add("1-Seleccione tarea...");
        mascotas.add("4-Seleccione mascota...");
    }

    public LiveData<List<String>> getTareas() {
        if(mTareas == null) {
            this.mTareas = new MutableLiveData<>();
        }
        return mTareas;
    }

    public LiveData<List<String>> getMascotas() {
        if(mMascotas == null) {
            this.mMascotas = new MutableLiveData<>();
        }
        return mMascotas;
    }

    public LiveData<List<String>> getHorarios() {
        if(mHorarios == null) {
            this.mHorarios = new MutableLiveData<>();
        }
        return mHorarios;
    }

    public LiveData<Boolean> getReset() {
        if(mReset == null) {
            this.mReset = new MutableLiveData<>();
        }
        return mReset;
    }

    public void setTareas() {
     try {
        Call<List<Tarea>> call = end.obtenerTareas();
        call.enqueue(new Callback<List<Tarea>>() {
            @Override
            public void onResponse(Call<List<Tarea>> call, Response<List<Tarea>> response) {
                if (response.body() != null) {
                    response.body().forEach(tarea -> {
                        tareas.add(tarea.getTarea());
                    });
                    mTareas.setValue(tareas);
                    tareasDisponibles = response.body();
                }
            }
            @Override
            public void onFailure(Call<List<Tarea>> call, Throwable t) {
                Log.d("salida 1", t.getMessage());
            }
        });
    } catch (Exception e){
        Log.d("salida 2", e.getMessage());
    }
    }

    public void setMascotas() {
        try {
            Call<List<Cliente_mascota>> call = end.obtenerClientesMascotas();
            call.enqueue(new Callback<List<Cliente_mascota>>() {
                @Override
                public void onResponse(Call<List<Cliente_mascota>> call, Response<List<Cliente_mascota>> response) {
                    if (response.body() != null) {
                        response.body().forEach(clientemascota -> {
                            mascotas.add(clientemascota.getMascota().getNombre());
                        });
                        clientes_mascotas = response.body();
                        mMascotas.setValue(mascotas);
                    }
                }
                @Override
                public void onFailure(Call<List<Cliente_mascota>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

    public void setHorarios(int dia, int mes, int anio,String tarea) {
        String nombreDia = utils.getDate(anio, mes, dia);
        fecha = utils.convertirFechaMysql(anio, mes, dia);
        if(tarea.equals("1-Seleccione tarea...")) {
            Toast.makeText(context, "Seleccione una tarea", Toast.LENGTH_LONG).show();
        }else {
            Tarea tareaSeleccionada = tareasDisponibles.stream().filter(t -> t.getTarea().equals(tarea)).findFirst().get();
            ArrayList<String> horarios = new ArrayList<>();
            mHorarios.setValue(horarios);
            try {
                Call<List<TurnosPorTarea>> call = end.obtenerTurnosPorFecha(tareaSeleccionada, fecha);
                Log.d("salida", call.request().url().toString());
                call.enqueue(new Callback<List<TurnosPorTarea>>() {
                    @Override
                    public void onResponse(Call<List<TurnosPorTarea>> call, Response<List<TurnosPorTarea>> response) {
                        if (response.body() != null) {
                            if (response.body().isEmpty()) {
                                Toast.makeText(context, "No hay turnos para esta tarea", Toast.LENGTH_LONG).show();
                            }else {
                                List<TurnosPorTarea> turnos = response.body();
                                tiempoTarea = turnos.get(0).getTiempoTarea();
                                horarios.addAll(utils.getTurnoTarea(turnos, nombreDia));
                                filtraTurnosOcupados(dia, mes, anio, horarios, tarea);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<TurnosPorTarea>> call, Throwable t) {
                        Log.d("salida 1", t.getMessage());
                    }
                });
            } catch (Exception e) {
                Log.d("salida 2", e.getMessage());
            }
        }
    }

    public void filtraTurnosOcupados(int dia, int mes, int anio,List<String> turnos,String tarea) {

    }

    public void crearConsulta(String tarea, String hora, String mascota) {

    }
}