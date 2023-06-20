package com.example.vettime2.ui.turnos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettime2.modelos.Cliente;
import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TurnosViewModel extends AndroidViewModel {

    private MutableLiveData<List<Consulta>> mConsultasPendientes;
    private MutableLiveData<List<Consulta>> mConsultasHistorial;
    private Context context;
    private ApiClient.EndPointVetTime end;
    private SharedPreferences sp;
    private String token;

    public TurnosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<List<Consulta>> getConsultasPendientes() {
        if (mConsultasPendientes == null) {
            mConsultasPendientes = new MutableLiveData<>();
        }
        return mConsultasPendientes;
    }

    public LiveData<List<Consulta>> getConsultasHistorial() {
        if (mConsultasHistorial == null) {
            mConsultasHistorial = new MutableLiveData<>();
        }
        return mConsultasHistorial;
    }

    public void setmConsultasPendientes() {
        try {
            Call<List<Consulta>> call = end.obtenerConsultasPendientes(token);
            call.enqueue(new Callback<List<Consulta>>() {
                @Override
                public void onResponse(Call<List<Consulta>> call, Response<List<Consulta>> response) {
                    if (response.body() != null) {
                        mConsultasPendientes.setValue(response.body());
                    }
                }
                @Override
                public void onFailure(Call<List<Consulta>> call, Throwable t) {
                    Log.d("Error", "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("Error", "onFailure: " + e.getMessage());
        }
    }

    public void setmConsultasHistorial() {
        try {
            Call<List<Consulta>> call = end.obtenerConsultasHistorial(token);
            call.enqueue(new Callback<List<Consulta>>() {
                @Override
                public void onResponse(Call<List<Consulta>> call, Response<List<Consulta>> response) {
                    if (response.body() != null) {
                        mConsultasHistorial.setValue(response.body());
                    }
                }
                @Override
                public void onFailure(Call<List<Consulta>> call, Throwable t) {
                    Log.d("Error", "onFailure: " + t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("Error", "onFailure: " + e.getMessage());
        }
    }

}