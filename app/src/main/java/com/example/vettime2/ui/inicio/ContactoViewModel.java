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
import com.example.vettime2.modelos.Sucursal;
import com.example.vettime2.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactoViewModel extends AndroidViewModel {

    private MutableLiveData<Sucursal> mSucursal;
    private Context context;
    private ApiClient.EndPointVetTime end;

    public ContactoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
    }

    public LiveData<Sucursal> getSucursal(){
        if(mSucursal == null){
            mSucursal = new MutableLiveData<>();
        }
        return mSucursal;
    }

    public void setSucursal(){
        try {
            Call<Sucursal> call = end.getSucursal();
            call.enqueue(new Callback<Sucursal>() {
                @Override
                public void onResponse(Call<Sucursal> call, Response<Sucursal> response) {
                    if (response.body() != null) {
                        mSucursal.setValue(response.body());
                    }
                }
                @Override
                public void onFailure(Call<Sucursal> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

}