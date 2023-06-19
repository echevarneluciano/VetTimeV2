package com.example.vettime2.ui.mascotas;

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

import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevaMascotaViewModel extends AndroidViewModel {

    private MutableLiveData<Mascota> mMascota;
    private Context context;
    private ApiClient.EndPointVetTime end;
    private SharedPreferences sp;
    private String token;


    public NuevaMascotaViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<Mascota> getMascota() {
        if (mMascota == null) {
            mMascota = new MutableLiveData<>();
        }
        return mMascota;
    }

    public void setmMascota(Mascota mascota) {
        try {
            Call<Mascota> call = end.nuevaMascota(token,mascota);
            call.enqueue(new Callback<Mascota>() {
                @Override
                public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                    if (response.body() != null) {
                        mMascota.setValue(response.body());
                        Toast.makeText(context, "Mascota creada", Toast.LENGTH_SHORT).show();
                    }
                }
                @Override
                public void onFailure(Call<Mascota> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

}