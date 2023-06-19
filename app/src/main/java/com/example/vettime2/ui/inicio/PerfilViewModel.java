package com.example.vettime2.ui.inicio;

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

import com.example.vettime2.modelos.Cliente;
import com.example.vettime2.modelos.Mascota;
import com.example.vettime2.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Cliente> mCliente;
    private Context context;
    private ApiClient.EndPointVetTime end;
    private SharedPreferences sp;
    private String token;

    public PerfilViewModel(@NonNull Application application) {
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

    public void setCliente(){
        try {
            Call<Cliente> call = end.getCliente(token);
            call.enqueue(new Callback<Cliente>() {
                @Override
                public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                    if (response.body() != null) {
                        mCliente.setValue(response.body());
                    }
                }
                @Override
                public void onFailure(Call<Cliente> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

    public void modCliente(Cliente cliente){
        try {
            Call<Cliente> call = end.editaCliente(token,cliente);
            call.enqueue(new Callback<Cliente>() {
                @Override
                public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                    if (response.body() != null) {
                        mCliente.setValue(response.body());
                        Toast.makeText(context, "Perfil editado", Toast.LENGTH_LONG).show();
                    }
                }
                @Override
                public void onFailure(Call<Cliente> call, Throwable t) {
                    Log.d("salida 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("salida 2", e.getMessage());
        }
    }

}