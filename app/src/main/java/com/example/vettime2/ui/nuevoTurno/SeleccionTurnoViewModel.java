package com.example.vettime2.ui.nuevoTurno;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettime2.modelos.Cliente_mascota;
import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.TurnosPorTarea;
import com.example.vettime2.request.ApiClient;
import com.example.vettime2.utils.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeleccionTurnoViewModel extends AndroidViewModel {

    private MutableLiveData<List<String>> mMascotas;
    private MutableLiveData<List<String>> mHorarios;
    private MutableLiveData<Consulta> mConsulta;
    private Context context;
    private String fecha;
    private Utils utils;
    private ApiClient.EndPointVetTime end;
    private String tiempoTarea;
    private List<Cliente_mascota> clientes_mascotas;
    private ArrayList<String> horariosDisponibles;
    private SharedPreferences sp;
    private String token;


    public SeleccionTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        utils = new Utils();
        end = ApiClient.getEndpointVetTime();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<List<String>> getMascotas() {
        if (mMascotas == null) {
            mMascotas = new MutableLiveData<>();
        }
        return mMascotas;
    }

    public LiveData<List<String>> getHorarios() {
        if (mHorarios == null) {
            mHorarios = new MutableLiveData<>();
        }
        return mHorarios;
    }

    public LiveData<Consulta> getConsulta() {
        if (mConsulta == null) {
            mConsulta = new MutableLiveData<>();
        }
        return mConsulta;
    }

    public void setHorarios(int dia, int mes, int anio,String tarea, String empleado) {
        String nombreDia = utils.getDate(anio, mes, dia);
        fecha = utils.convertirFechaMysql(anio, mes, dia);
        ArrayList<String> horarios = new ArrayList<>();
        try {
            Call<List<TurnosPorTarea>> call = end.obtenerTurnosPorFecha(token,tarea, fecha, empleado);
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
                            filtraTurnosOcupados(horarios, empleado);
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

    public void filtraTurnosOcupados(List<String> turnos,String empleado) {
        List<Consulta> consultas = new ArrayList<>();
        try {
            Call<List<Consulta>> call = end.obtenerConsultasPorFecha(token,fecha, empleado);
            call.enqueue(new Callback<List<Consulta>>() {
                @Override
                public void onResponse(Call<List<Consulta>> call, Response<List<Consulta>> response) {
                    if (response.body() != null) {
                        consultas.addAll(response.body());
                        List<String> intervalosConsultas = utils.getTurnosEntregados(consultas);
                        ArrayList<String> arrayList = new ArrayList<>(utils.getTurnosNoEntregados(turnos, intervalosConsultas));
                        horariosDisponibles = arrayList;
                        mHorarios.setValue(arrayList);
                    }
                }
                @Override
                public void onFailure(Call<List<Consulta>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

    public void setMascotas() {
        ArrayList<String> mascotas = new ArrayList<>();
        try {
            Call<List<Cliente_mascota>> call = end.obtenerClientesMascotas(token);
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

    public void crearConsulta(String empleado, String hora, String mascota) {

            String tiempoFin = utils.sumaHoraAFechaFin(hora, tiempoTarea, fecha);
            String tiempoInicio = utils.sumaHoraAFecha(hora, fecha);
            int mascotaid = clientes_mascotas.stream().filter(c -> c.getMascota().getNombre().equals(mascota)).findFirst().get().getId();
            Consulta consulta = new Consulta();
            consulta.setCliente_mascotaId(mascotaid);
            consulta.setTiempoInicio(tiempoInicio);
            consulta.setTiempoFin(tiempoFin);
            Log.d("salida", consulta.toString());

            if (compruebaLapso(hora)) {

            }else {
            try {
                Call<Consulta> call = end.nuevaConsultas(token,consulta, empleado);
                Log.d("salida", call.request().url().toString());
                call.enqueue(new Callback<Consulta>() {
                    @Override
                    public void onResponse(Call<Consulta> call, Response<Consulta> response) {
                        if (response.body() != null) {
                            mConsulta.setValue(response.body());
                            Toast.makeText(context, "Consulta agendada, fecha: "+response.body().getTiempoInicio(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Consulta> call, Throwable t) {
                        Log.d("salida 1", t.getMessage());
                    }
                });
            } catch (Exception e) {
                Log.d("salida 2", e.getMessage());
            }
}}

    public boolean compruebaLapso(String hora) {

        List<String> lapsoTarea = new ArrayList<>();
        lapsoTarea = utils.lapsoTarea(hora, tiempoTarea);

        if (horariosDisponibles.containsAll(lapsoTarea)) {
            return false;
        }else {
            Toast.makeText(context, "Turnos solapados seleccione otro horario. Tiempo necesario: "+tiempoTarea+".", Toast.LENGTH_LONG).show();
            Log.d("salida", tiempoTarea);
            return true;
        }

    }

}