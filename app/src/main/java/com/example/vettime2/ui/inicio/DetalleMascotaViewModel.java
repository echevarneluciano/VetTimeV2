package com.example.vettime2.ui.inicio;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.request.ApiClient;

import java.net.MulticastSocket;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleMascotaViewModel extends AndroidViewModel {

    private MutableLiveData<Mascota> mMascota;
    private Context context;
    private ApiClient.EndPointVetTime end;

    public DetalleMascotaViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
    }

    public LiveData<Mascota> getMascota(){
        if(mMascota == null){
            mMascota = new MutableLiveData<>();
        }
        return mMascota;
    }

    public void setMascota(Mascota mascota){

        try {
            Call<Mascota> call = end.editaMascota(mascota);
            Log.d("salida", mascota.toString()+" "+call.request().toString());
            call.enqueue(new Callback<Mascota>() {
                @Override
                public void onResponse(Call<Mascota> call, Response<Mascota> response) {
                    if (response.body() != null) {
                        mMascota.setValue(response.body());
                        Toast.makeText(context, "Mascota editada", Toast.LENGTH_LONG).show();
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