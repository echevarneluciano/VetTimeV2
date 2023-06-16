package com.example.vettime2.ui.inicio;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.vettime2.modelos.Cliente;
import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.request.ApiClient;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Cliente> mCliente;
    private Context context;
    private ApiClient.EndPointVetTime end;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
    }

    public LiveData<Cliente> getCliente(){
        if(mCliente == null){
            mCliente = new MutableLiveData<>();
        }
        return mCliente;
    }



}