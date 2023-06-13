package com.example.vettime2.ui.inicio;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettime2.modelos.Cliente_mascota;
import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.request.ApiClient;
import com.example.vettime2.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InicioViewModel extends AndroidViewModel {

private MutableLiveData<List<Mascota>> mMascotas;
private Context context;
private ApiClient.EndPointVetTime end;

    public InicioViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
    }

    public LiveData<List<Mascota>> getMascotas() {
        if (mMascotas == null) {
            mMascotas = new MutableLiveData<>();
        }
        return mMascotas;
    }

    public void setmMascotas() {
        try {
            Call<List<Mascota>> call = end.obtenerMascotas();
            call.enqueue(new Callback<List<Mascota>>() {
                @Override
                public void onResponse(Call<List<Mascota>> call, Response<List<Mascota>> response) {
                    if (response.body() != null) {
                        mMascotas.setValue(response.body());
                    }
                }
                @Override
                public void onFailure(Call<List<Mascota>> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

}