package com.example.vettime2.ui.mascotas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettime2.modelos.Cliente_mascota;
import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotasViewModel extends AndroidViewModel {

    private MutableLiveData<List<Mascota>> mMascotas;
    private Context context;
    private ApiClient.EndPointVetTime end;
    private SharedPreferences sp;
    private String token;

    public MascotasViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<List<Mascota>> getMascotas() {
        if (mMascotas == null) {
            mMascotas = new MutableLiveData<>();
        }
        return mMascotas;
    }

    public void setmMascotas() {
        try {
            ArrayList<Mascota> mascotas = new ArrayList<>();
            Call<List<Cliente_mascota>> call = end.obtenerMascotas(token);
            call.enqueue(new Callback<List<Cliente_mascota>>() {
                @Override
                public void onResponse(Call<List<Cliente_mascota>> call, Response<List<Cliente_mascota>> response) {
                    if (response.body() != null) {
                        response.body().forEach(mascota -> {
                            mascotas.add(mascota.getMascota());
                        });
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

}