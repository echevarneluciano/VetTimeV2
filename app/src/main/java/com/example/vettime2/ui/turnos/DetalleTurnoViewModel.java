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

import com.example.vettime2.modelos.Consulta;
import com.example.vettime2.request.ApiClient;

import java.util.List;

public class DetalleTurnoViewModel extends AndroidViewModel {

    private MutableLiveData<Consulta> mConsulta;
    private Context context;
    private ApiClient.EndPointVetTime end;
    private SharedPreferences sp;
    private String token;

    public DetalleTurnoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        end = ApiClient.getEndpointVetTime();
        sp = context.getSharedPreferences("token.xml",0);
        token = sp.getString("token","");
    }


}