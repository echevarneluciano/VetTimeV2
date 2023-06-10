package com.example.vettime2.ui.nuevoTurno;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.vettime2.R;
import com.example.vettime2.modelos.Cliente_mascota;
import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.Empleado_tarea;
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
    private MutableLiveData<List<String>> mEmpleados;
    private Context context;
    private List<Cliente_mascota> clientes_mascotas;
    private ArrayList<String> tareas = new ArrayList<>();
    private ArrayList<String> empleados = new ArrayList<>();
    private ArrayList<Empleado_tarea> empleados_tareas = new ArrayList<>();
    private ApiClient.EndPointVetTime end;
    private Utils utils;

    public NuevoTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        utils = new Utils();
        end = ApiClient.getEndpointVetTime();
    }

    public LiveData<List<String>> getTareas() {
        if (mTareas == null) {
            mTareas = new MutableLiveData<>();
        }
        return mTareas;
    }

    public LiveData<List<String>> getEmpleados() {
        if (mEmpleados == null) {
            mEmpleados = new MutableLiveData<>();
        }
        return mEmpleados;
    }

    public void setmTareas() {
        try {
            Call<List<Empleado_tarea>> call = end.obtenerEmpleadosTareas();
            call.enqueue(new Callback<List<Empleado_tarea>>() {
                @Override
                public void onResponse(Call<List<Empleado_tarea>> call, Response<List<Empleado_tarea>> response) {
                    if (response.body() != null) {
                     response.body().forEach(empleado_tarea -> {
                         tareas.add(empleado_tarea.getTarea().getTarea());
                     });
                     HashSet<String> tareasSet = new HashSet<>(tareas);
                     empleados_tareas.addAll(response.body());
                     tareas = new ArrayList<>(tareasSet);
                     mTareas.setValue(tareas);
                    }
                }
                @Override
                public void onFailure(Call<List<Empleado_tarea>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

    public void setmEmpleados(String tarea) {
        empleados.clear();
        mEmpleados.setValue(empleados);
        empleados_tareas.forEach(empleado_tarea -> {
            if (empleado_tarea.getTarea().getTarea().equals(tarea)) {
                empleados.add(empleado_tarea.getEmpleado().getNombre()+" "+empleado_tarea.getEmpleado().getApellido());
            }
            mEmpleados.setValue(empleados);
        });
    }

    public void setConfirmado(String tarea, String empleado, Activity activity) {
        Bundle bundle = new Bundle();
        bundle.putString("tarea", tarea);
        bundle.putString("empleado", empleado);
        Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main).navigate(R.id.action_navigation_home_to_seleccionTurnoFragment,bundle);
    }
}