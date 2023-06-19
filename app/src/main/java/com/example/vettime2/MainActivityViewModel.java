package com.example.vettime2;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.vettime2.modelos.Cliente;
import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.request.ApiClient;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Cliente> mCliente;
    private Context context;
    private SharedPreferences sp;
    private String token;
    private ApiClient.EndPointVetTime end;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }

    public LiveData<Cliente> getCliente(){
        if(mCliente == null){
            mCliente = new MutableLiveData<>();
        }
        return mCliente;
    }



}
